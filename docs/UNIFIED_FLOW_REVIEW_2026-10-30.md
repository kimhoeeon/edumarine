# 통합 교육 양식(UNIFIED) 흐름 점검 (2026-10-30)

보류했던 4단계 항목을 "앞으로 교육을 추가하고 → 회원이 신청·결제하고 → 관리자가 운영한다"는 기준으로 다시 점검했다.
레거시 신청 모듈 19벌(`eduApply01~22`, 과정별 테이블)은 과거 데이터와 연결돼 있어 건드리지 않고, **신규 교육이 쓰는 통합 양식 경로만** 코드 전체를 읽어 확인했다.
비밀값은 적지 않았다. 행 번호는 점검 시점 기준.

## 1. 결론 요약

- 교육 생성 → 신청 → 결제(카드·가상계좌, PC·모바일) → 취소·환불 → 마이페이지 → 관리자 상태 관리·엑셀의 **본 흐름은 통합 양식에서 끝까지 이어진다.** 오늘 운영에서 실제 카드 결제 1건(AU0000089)이 승인까지 정상 처리된 것도 코드와 일치한다.
- 다만 **새 교육을 통합 양식으로 만들면 빠지는 기능이 둘** 있다. (G1) 교육 2일 전 자동 안내 문자, (G2) 관리자 "과정별" 수동 문자 발송. 두 기능 모두 레거시 과정 테이블만 보도록 짜여 있다.
- 통합 신청 저장에는 **정원·마감·접수 기간·중복 신청을 서버에서 막는 검사가 없다** (G3). 레거시는 서비스에서 정원을 검사하는데 통합은 화면 버튼 표시와 화면 쪽 중복 검사에만 의존한다.
- 양식 필드 파이프라인(신청 화면 → DTO → INSERT → 마이페이지 수정/UPDATE → 관리자 상세 → 엑셀)은 현재 3개 양식(BASIC·PRO·BASE)의 필드에 대해 모두 일관된다(스크립트 교차 검사, 누락 0).
- 레거시 모듈 제거는 계속 보류가 맞다. 이유는 6절.
- **2026-10-30 사용자 결정("G1~G4 전부")으로 G1~G4 를 같은 날 수정했다(8절).** 이어서 G5~G8 도 같은 날 수정했다(9절). G1~G4 변경분은 판정 표 테스트 38건과 운영 DB 읽기 전용 SQL 로 재점검했다(QA 문서 14장). 커밋·배포는 사용자 결정 대기.

## 2. 흐름별 확인 결과

### 2-1. 교육 생성 (관리자)
- 교육 목록의 **"(신규) 통합 등록"** 버튼 → `/mng/education/train/detail.do?type=UNIFIED` → 컨트롤러가 `applicationSystemType=UNIFIED` 로 새 교육 객체를 만든다(`EduMarineMngController` `mng_education_train_detail`). "교육 등록" 버튼은 LEGACY 가 기본값.
- UNIFIED 교육은 **교육과정명을 직접 입력**(자유 문자열)하고 **신청서 양식(form_type)** 을 BASIC_FORM(작업복 사이즈·참여경로) / PRO_FORM(+추천인) / BASE_FORM(참여경로만) 중 고른다. 썸네일 업로드 가능.
- `insertTrain`/`updateTrain` 이 `application_system_type`, `form_type`, `pay_sum`(교육비), `train_cnt`(정원), `train_apply_cnt`, `apply_start/end_dttm`, `closing_yn`, `next_time` 을 저장한다.
- 교육 목록의 "신청자" 버튼은 `applicationSystemType` 이 UNIFIED 면 통합 신청 목록으로 연결된다(`mng/train.js` 606~).

### 2-2. 일정 화면 → 신청 화면 (회원)
- `front/schedule.js` 가 교육 목록을 그릴 때 `applicationSystemType === 'UNIFIED'` 면 `/apply/eduApplyUnified.do` 로 보낸다(과정명 분기보다 뒤에서 덮어씀). 마감·정원·접수기간은 **화면에서** 버튼 문구(접수마감·교육마감)로만 처리한다.
- `/apply/eduApplyUnified.do`: 로그인 필수, 회원 정보 없으면 세션 무효화. `trainInfo.formType` 으로 `_form_unified_basic|pro|base.jsp` 를 include. 양식 값이 셋 중 하나가 아니면 "잘못된 신청서 양식" 안내(팸투어 양식은 미구현·보류).
- 제출 전 화면 JS 가 `/apply/eduApplyUnified/preCheck.do`(같은 교육에 취소 아닌 신청이 있는지)를 호출한다.

### 2-3. 신청 저장
- `/apply/eduApplyUnified/insert.do`: 세션 회원번호로 고정(10-30 보안 조치), 교육비 0원이면 `결제완료` 로 저장하고 신청 인원 +1, 유료면 `결제대기`. 신청번호 `AU0000001` 형식.
- 저장 직후 화면이 수강신청 안내 문자(템플릿 2)를 호출한다(10-30 추가).
- 서버 쪽에는 정원·마감·기간·중복 검사가 없다 → **G3**.

### 2-4. 결제·입금
- 결제 페이지는 교육비를 **DB(train.pay_sum)에서** 읽는다(화면 값 신뢰 안 함).
- PC: `merchantData = AU…||회원ID||발급시각||서명`. 복귀 시 `AU` 로 시작하면 UNIFIED 로 판정(`EduMarineController` 4824). 승인 성공 → `application_unified.apply_status` 를 결제 상태(카드 `결제완료`, 가상계좌 `입금대기`)로 갱신, `결제완료` 면 신청 인원 +1. 오늘 운영 결제 1건으로 검증됨.
- 모바일: `P_NOTI = 교육번호_신청번호_UNIFIED_상품명_회원ID_발급시각_서명`. 복귀에서 `_` 로 나눠 [1]=신청번호, [2]=UNIFIED 를 읽는다. 상품명에 `_` 가 있으면 상품명 표시만 잘리고 신청번호·타입·서명 검증은 영향 없음(**G7**, 표시용).
- 가상계좌 입금 통보(`/pc/payment/vbank/vacct/noti.do`, PC·모바일 공용): payment 테이블의 `table_seq` 가 `AU` 로 시작하면 UNIFIED 로 `결제완료` 갱신 + 인원 +1.
- 가상계좌 안내 문자(템플릿 6)는 회원번호 기준이라 통합도 동일하게 발송된다.

### 2-5. 취소·환불
- 회원 취소(`/mypage/eduApplyUnified/cancel.do`, 본인 확인): 무료 교육은 즉시 `취소완료` + 인원 -1, 유료는 `취소신청`(payment 도 같이 갱신) 후 관리자 승인 대기.
- 관리자 승인(`/mng/customer/unified/status/update.do` → `processUpdateUnifiedApplyStatus`): 결제 내역이 있으면 카드/가상계좌별로 이니시스 환불 API(전액/부분; 부분은 교육 시작 10일 전 100%, 5~10일 50%, 5일 미만 환불 없음) → `취소완료` → payment `취소완료` → 인원 -1. 결제 내역이 없으면 상태만 바꾸고 인원 가감. 완료 후 화면이 취소완료 문자(템플릿 5)를 보낸다.
- 환불 구분(전액/부분) 선택은 화면(`mng/unified.js` 591)에서만 강제한다. 서버는 구분이 없으면 환불 API 없이 취소완료 처리한다(**G8**, 레거시와 같은 구조).
- 상태 전이(`processUpdateUnifiedApplyStatusChange`): 결제완료→수강확정→수강완료→환급대기→환급완료, 입금대기→결제완료(수동)만 허용. 그 외는 "변경 불가" 메시지.

### 2-6. 마이페이지
- 신청 내역 목록(`selectEduApplyInfoList`)이 `application_unified` 를 UNION 으로 포함하고 `trainGbn='UNIFIED'` 로 구분한다.
- 상세/수정(`eduApplyUnified_modify.jsp`): 수정 가능 필드는 참여경로·작업복 사이즈·추천인이고 `updateUnifiedApplication` 이 모두 저장한다. 본인 확인 있음.
- 수정 가능 여부(`modYn`)가 항상 Y 다(상태·교육 시작 여부 검사가 주석 처리) → **G4**.

### 2-7. 관리자 운영
- 메뉴 "통합 신청 관리"(`MenuService`), 목록 필터(교육·상태·차시·연도), 상세(참여경로·작업복·추천인·추천인 생년월일·교육 이해도·환불 계좌·취소 사유 표시), 엑셀(회원 정보 + 작업복·참여경로·추천인 + 결제 방식·금액 + 상태·취소 정보).
- 교육 관리 목록에서 통합 교육의 신청자 목록으로 바로 이동.

### 2-8. 자동 문자·수동 문자·기타 공통 기능
| 기능 | 통합 양식 포함 여부 |
|---|---|
| 회원가입 직후(1), 수강신청 후(2, 10-30 추가), 가상계좌 안내(6), 취소완료(5), 키워드 알림(8) | 포함 (회원번호·교육번호 기준) |
| **교육 2일 전 안내(7, 매일 14시 자동)** | **미포함** — `CommMapper.getSmsSendingTrainNotiList` 가 레거시 테이블 16개만 UNION (electro 도 빠짐) → **G1** → 2026-10-30 수정(8절) |
| **관리자 수동 문자 "과정별" 발송** | **미포함** — `smsMng/sms/send.jsp` 의 과정 선택지가 레거시 19개 고정, 서비스는 과정명 switch → 통합 교육은 선택 불가(회원별·엑셀 업로드는 가능) → **G2** → 2026-10-30 수정(8절) |
| 매일 0시 회원 등급 갱신 | 교육과 무관, 영향 없음 |
| 공지·뉴스레터 등 게시판 | 무관 |

### 2-9. 양식 필드 교차 검사 (스크립트)
신청 화면 3종의 입력 필드(`clothesSize`, `participationPath`, `recommendPerson`)는 모두 DTO·INSERT 에 있고, 마이페이지 수정 가능 필드는 모두 UPDATE 에 있으며, 관리자 상세·엑셀도 보여 준다. 누락 0건.
`application_unified` 테이블·DTO 에는 지금 쓰지 않는 컬럼이 30여 개 더 있다(거주지역·희망 분야·교육 희망 시간·학력·병역·장애·지원 동기 등 레거시 양식에서 온 것). 새 양식이 이 범위 안의 항목을 쓰면 DB 변경 없이 추가할 수 있다.

## 3. 발견한 공백·결함

| # | 중요도 | 내용 | 위치 |
|---|---|---|---|
| G1 | **높음** | 매일 14시 "교육 2일 전 안내" 문자 대상에 통합 신청자가 없다(레저선박 해양전자장비 교육 신청자도 없음). 통합 양식으로 만든 모든 교육이 해당 | `CommMapper.xml` `getSmsSendingTrainNotiList` (레거시 16개 테이블 UNION) |
| G2 | **높음** | 관리자 수동 문자 "과정별" 발송에서 통합 교육을 고를 수 없다 | `mng/smsMng/sms/send.jsp` 215~ (고정 목록), `EduMarineMngServiceImpl` 7855~ (과정명 switch) |
| G3 | **높음** | 통합 신청 저장에 정원·마감(closing_yn)·접수 기간·중복 신청 서버 검사가 없다. 레거시 서비스는 정원 검사를 한다(`processInsert*` 18곳). 화면 버튼과 화면 쪽 preCheck 에만 의존하므로 URL 직접 접근·이중 제출로 정원 초과·중복 신청이 가능 | `EduMarineController` `apply_eduApplyUnified_insert`, `EduMarineServiceImpl.processInsertUnifiedApplication`(중복 검사 주석) |
| G4 | 중간 | 마이페이지 수정이 상태·시점과 무관하게 항상 허용된다(취소완료·교육 시작 후에도 수정 가능). 수정 가능 필드가 셋뿐이라 피해는 작다 | `EduMarineController` `mypage_eduApplyUnified_modify` (`modYn` 항상 Y) |
| G5 | 중간 | 새 양식을 추가할 때 화면 JS 검증이 양식 이름 목록으로 분기하므로 목록에 안 넣으면 검증 없이 제출된다. 절차 문서화로 대응(4절) | `eduApplyUnified.jsp` 309~310 |
| G6 | 낮음 | `updateUnifiedApplyStatus` 의 parameterType 이 `SternSpecialDTO` 로 잘못 적혀 있음(동작은 됨). `/apply/eduApplyUnified.do` 가 회원번호로 신청을 조회하는 죽은 코드(`appInfo` 미사용) | `UnifiedMapper.xml`, `EduMarineController` 2718~ |
| G7 | 낮음 | 모바일 P_NOTI 에 상품명이 들어가 과정명에 `_` 가 있으면 과정명 표시가 잘림(결제 처리에는 영향 없음) | `payment_m.jsp` 424 |
| G8 | 정보 | 취소 승인 시 환불 구분을 화면만 강제. 레거시와 동일 구조 | `mng/unified.js` 591 |

2026-10-30: **G1~G4 수정 완료(8절)**, 이어서 **G5~G8 수정 완료(9절)**.

## 4. 새 교육 양식(form_type) 추가 절차

신청 항목이 기존 3종과 다를 때. 레거시 모듈과 무관하게 통합 양식 안에서 끝난다.

1. 항목이 `application_unified` 의 기존 컬럼으로 충분한지 확인(`ApplicationUnifiedDTO` 필드 목록). 부족하면 `ALTER TABLE application_unified ADD …` + DTO 필드 + `UnifiedMapper.xml` 의 `insertUnifiedApplication`·`selectApplicationUnifiedSingle`·`selectExcelUnifiedApplicationList`(+ 수정 가능이면 `updateUnifiedApplication`) 에 컬럼 추가.
2. `views/apply/_form_unified_<이름>.jsp` 작성(기존 `_form_unified_basic.jsp` 복사). 입력 `name` 은 DTO 필드명과 같게.
3. `views/apply/eduApplyUnified.jsp`: `<c:when test="${trainInfo.formType == '<NEW>_FORM'}">` include 추가, **JS 검증의 양식 목록(309~310)에 `<NEW>_FORM` 추가**, 필수값 검증 추가.
4. `views/mng/education/train/detail.jsp` 의 form_type 선택지에 `<NEW>_FORM` 추가.
5. `views/mypage/eduApplyUnified_modify.jsp` 에 표시·수정 분기 추가(수정 가능 필드는 `updateUnifiedApplication` 에도).
6. `views/mng/customer/unified/detail.jsp` 와 통합 엑셀 핸들러(`/mng/customer/unified/excel/detail_download.do`)에 항목 추가.
7. 로컬에서 신청 → 결제(테스트) → 마이페이지 → 관리자 상세·엑셀을 TEST 데이터로 확인.

팸투어형(FAMTOUR_FORM)은 이 절차대로 만들면 된다(신청일 선택 항목은 기존 `choice_date`·`apply_day` 컬럼 활용 가능).

## 5. 과정명 문자열 분기·JDK 전환 (보류 유지)

- 과정명 switch 는 레거시 과정에만 쓰이고(레거시 테이블 선택), 통합 교육은 `application_system_type`·`form_type`·`train_seq` 로만 동작한다. 레거시 모듈을 없애기 전까지는 코드 테이블로 바꿀 이유가 약하다. G2 를 고치면 통합 교육은 과정명과 무관하게 `train_seq` 로 대상을 고르게 된다.
- JDK 17 전환은 서버(Tomcat 8.5·Java 8)를 바꾸지 않는 한 불가. 보류.

## 6. 레거시 신청 모듈 제거 — 보류 유지 근거

- 레거시 19개 테이블의 과거 신청 데이터를 조회·수정·엑셀·환불하는 화면이 그 모듈에 묶여 있고, `payment.table_seq` 가 레거시 신청번호(접두어별)를 가리킨다. 제거하려면 데이터 이관(컬럼 매핑 19종)·결제 내역 재연결·과거 환불 가능성 검토가 선행돼야 한다.
- 신규 교육은 통합 양식으로만 만들면 레거시 코드는 "과거 데이터 열람용"으로 고정되므로, 당장 손대지 않아도 운영 위험이 늘지 않는다.
- 단, G1·G2 처럼 "레거시 테이블만 보는 공통 기능"은 통합 양식이 늘수록 빠지는 범위가 커지므로 먼저 고친다.

## 7. 권장 조치 (사용자 결정)

| 우선 | 조치 | 변경 범위 | 위험 |
|---|---|---|---|
| 1 | G1: 2일 전 안내 문자 대상에 `application_unified`(+`electro`) 추가 — **2026-10-30 적용(8절)** | `CommMapper.xml` SQL 한 곳에 UNION ALL 두 개 | 낮음 (조회 추가) |
| 2 | G3: 통합 신청 저장 시 서버에서 마감·접수기간·정원·중복 검사 후 거부 메시지 — **2026-10-30 적용(8절, 컨트롤러에서 검사)** | `EduMarineServiceImpl.processInsertUnifiedApplication` (+ 컨트롤러 메시지) | 낮음 (화면 흐름 동일, 거부 사유만 추가) |
| 3 | G2: 관리자 과정별 문자 발송에 통합 교육 포함 — **2026-10-30 적용(8절, 과정명 기준 조회)** | `sms/send.jsp` 과정 목록을 DB(train)에서 그리고 서비스에 `train_seq` 기준 조회 추가 | 중간 (관리자 화면 변경 — 레이아웃은 유지, 선택지만 확장) |
| 4 | G4: 취소완료·교육 종료 후 마이페이지 수정 차단 — **2026-10-30 적용(8절)** | 컨트롤러 조건 복원 | 낮음 |
| 5 | G6·G7: parameterType 수정, 죽은 코드 제거, P_NOTI 상품명 `_` 치환 — **2026-10-30 적용(9절, G5·G8 포함; P_NOTI 는 치환 대신 서버에서 조각 복원)** | 소규모 | 낮음 |

## 8. G1~G4 수정 내역 (2026-10-30, 사용자 결정 "G1~G4 전부")

| # | 수정 | 파일 | 동작·범위 |
|---|---|---|---|
| G1 | 교육 2일 전 안내 문자(템플릿 7, 매일 14시) 대상 SQL 에 `electro`·`application_unified` 를 UNION ALL 로 추가(취소 상태·`del_yn='N'` 아닌 행 제외) | `CommMapper.xml` `getSmsSendingTrainNotiList` | 기존 조건(회원 `sms_yn`·탈퇴 제외·교육 미마감·시작 2일 전)은 그대로. 통합 신청자와 레저선박 해양전자장비 교육 신청자가 대상에 들어간다 |
| G2 | 관리자 "과정별" 문자 발송의 과정 선택 목록 끝에 통합 교육 과정명(`train.application_system_type='UNIFIED'`, 과정명 중복 제거)을 추가. option 값은 `UNIFIED:<과정명>` 이고 서버가 이 접두어를 보고 `application_unified` 에서 신청자를 조회 | `EduMarineMngController.mng_smsMng_sms_send`(`unifiedGbnList`), `mng/smsMng/sms/send.jsp`(기존 `<select id="form_train_gbn">` 안에 `<c:forEach>` option), `EduMarineMngServiceImpl.processSelectSmsSendList`(접두어 분기)·`processSelectUnifiedSmsGbnList`, `UnifiedMapper.selectSmsSendUnifiedList` | 레거시 과정 option 과 과정명 switch 는 그대로. 화면 레이아웃 변경 없음(선택지만 늘어남). 결과 표의 컬럼(차시·ID·이름·연락처·상태)은 레거시 조회와 같은 `SmsSendDTO` |
| G3 | 통합 신청 저장 전 서버 검사: 교육 존재·`del_yn`·UNIFIED 여부 → 마감(`closing_yn`) → 접수 시작 전 → 접수 종료 → 정원(`train_apply_cnt >= train_cnt`, 레거시·화면과 같은 기준) → 중복(기존 `selectUnifiedPreCheck`, 취소 상태 제외). 거부 시 `resultCode 99` + 사유 메시지를 돌려주고 화면은 기존 "신청 실패" Swal 로 표시. 날짜는 `yyyy.MM.dd`(또는 `yyyy-MM-dd …`)를 정규화해 문자열 비교, 형식이 다르면 해당 검사만 건너뜀 | `EduMarineController.apply_eduApplyUnified_insert`, `checkUnifiedApplyAvailable`, `normalizeYmd` | 정상 신청 흐름·화면 변경 없음. 화면 버튼 판정(`schedule.js`)과 같은 기준이라 정상 사용자는 거부를 보지 않고, URL 직접 호출·이중 제출만 막힌다. 거부는 `log.warn` 으로 남김(교육번호·회원번호·사유만) |
| G4 | 마이페이지 수정 가능 여부를 서버가 판정: 신청 상태에 "취소" 가 들어 있거나(취소신청·취소완료) 교육 종료일이 지났으면 `modYn=N`(화면은 기존대로 저장 버튼 숨김·입력 비활성). 저장 API(`update.do`)도 같은 기준으로 `resultCode 99` 거부 | `EduMarineController.mypage_eduApplyUnified_modify`, `mypage_eduApplyUnified_update`, `unifiedModifiable` | 화면에서 `modYn=N` 으로 여는 조회 전용 동선은 그대로. 수강확정·수강완료 상태는 교육 종료 전까지 수정 가능(기존과 동일, 발주사 요청 없이는 더 좁히지 않음). **부수 효과**: 수정 화면의 신청취소 버튼이 저장 버튼과 같은 `modYn ne 'N'` 조건 안에 있어(`eduApplyUnified_modify.jsp` 288~294) 교육 종료 후에는 신청취소 버튼도 함께 숨겨진다(취소 API 는 그대로). 교육이 끝난 신청의 취소·환불은 관리자 처리로 본다. 사용자 취소를 종료 후에도 열어 두려면 JSP 의 조건을 분리해야 한다(사용자 결정) |

검증(로컬, 2026-10-30 16시): Java 컴파일 통과, 매퍼 XML 파싱 통과, 변경 JSP 3개 JspC 번역 0 오류, 로컬 기동(매퍼 XML 바인딩·외부 설정 8항목) 정상. DB 연결은 기동 시 지연 초기화라 별도로 G1·G2 SQL 을 운영 DB 에서 읽기 전용으로 실행해 문법·컬럼·건수를 확인했고, `train` 의 날짜 컬럼이 VARCHAR `yyyy.MM.dd` 임을 확인해 G3·G4 의 문자열 비교 전제가 성립한다(QA 문서 14장). 회원 로그인이 필요한 G3·G4 화면과 관리자 로그인이 필요한 G2 화면은 브라우저로 확인하지 못했다(QA 문서 14장).

배포 후 확인(사용자):
1. 관리자 → 문자 발송 → 과정 선택 목록 끝에 통합 교육 과정명이 보이고, 선택하면 신청자 목록(차시·ID·이름·연락처·상태)이 뜨는지. 레거시 과정 선택도 전과 같은지.
2. 통합 교육 TEST 신청: 정상 교육은 전과 같이 신청·문자. 정원이 찬 교육·접수 종료 교육을 URL 로 열어 저장하면 "신청 실패" 팝업에 사유가 뜨는지.
3. 마이페이지: 취소완료 신청·교육이 끝난 신청의 수정 화면에 저장 버튼이 없는지.
4. 다음 교육 시작 2일 전 14시에 통합 신청자에게 안내 문자가 가는지(알리고 발송 이력 또는 서버 로그).

## 9. G5~G8 수정 내역 (2026-10-30, G1~G4 에 이어 사용자 지시)

| # | 수정 | 파일 | 동작·범위 |
|---|---|---|---|
| G5 | 신청 화면 JS 검증을 양식 이름(formType) 목록 분기 대신 **화면에 있는 필드 기준**으로 바꿈(작업복 사이즈는 `#clothesSize` 가 있을 때, 참여경로는 `#participationPath` 가 있을 때). 정의되지 않은 form_type 이면 `formUnknown` 을 세워 "교육 신청하기" 버튼을 내지 않고 제출 함수도 안내 후 중단 | `apply/eduApplyUnified.jsp` | 기존 3개 양식(BASIC·PRO·BASE)의 검사 결과는 전과 같다(BASIC·PRO 두 항목, BASE 참여경로만). 새 양식을 추가할 때 JS 목록을 손대지 않아도 공통 검사가 적용된다(4절 절차의 JS 수정 단계 불필요) |
| G6 | `updateUnifiedApplyStatus` parameterType 을 `ApplicationUnifiedDTO` 로 정정(동작 동일). 통합 신청 GET 에서 회원번호(`V…`)로 신청(`AU…`)을 조회해 항상 null 이던 `appInfo` 조회 삭제, 양식 JSP 3개의 `appInfo` 참조(항상 거짓인 `selected` 조건 27곳, 추천인 value) 제거 | `UnifiedMapper.xml`, `EduMarineController.apply_eduApplyUnified`, `_form_unified_basic/pro/base.jsp` | 렌더링 결과 동일(조건이 참이 된 적이 없음). 신청 화면 진입 시 불필요한 조회 1회 감소 |
| G7 | 모바일 결제 복귀의 P_NOTI 파싱에서 과정명 조각을 회원ID·발급시각·서명 앞까지 다시 이어 붙임(`joinNotiTrainName`). 과정명에 `_` 가 있어도 결제 내역의 교육명이 잘리지 않음 | `EduMarineController`(모바일 결제 내역 저장부) | 결제 처리·세션 복구에는 영향 없음(둘 다 과정명을 쓰지 않음). `payment_m.jsp` 는 그대로 |
| G8 | 결제 내역이 있는 통합 신청의 취소 승인에서 환불 구분(ALL/PART)이 없으면 서버가 거부(resultCode 실패 + 메시지, 처리 중단). 화면(`unified.js`)은 이미 선택을 강제하므로 정상 사용에는 변화 없음 | `EduMarineMngServiceImpl.processUpdateUnifiedApplyStatus` | 구분 없이 호출되면 환불 API 없이 취소완료로 바뀌던 구멍을 막음. 관리자 "신청 상태 변경" 모달에는 취소 상태 선택지가 없어 그 경로는 해당 없음 |

검증: Java 컴파일 통과, `UnifiedMapper.xml` 파싱 통과, 변경 JSP 4개 JspC 번역 0 오류, `joinNotiTrainName` 복원 테스트 7건 통과(QA 문서 14장). G1~G4 변경분 재점검: `checkUnifiedApplyAvailable`·`unifiedModifiable`·`normalizeYmd` 판정 표 테스트 38건 통과(검사 순서, 당일 경계, 날짜 형식 2종, 정원 0 처리, 중복 검사 포함).

배포 후 확인(사용자, 8절 항목에 추가): 통합 신청 화면 3종(BASIC·PRO·BASE)에서 항목 미입력 시 전과 같은 안내가 뜨고 정상 제출되는지. 관리자 취소 승인(환불 구분 선택 후)이 전과 같이 처리되는지.
