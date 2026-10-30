# edumarine 프로젝트 지침 (경기해양레저인력양성센터 edumarine.org)

> 템플릿 미적용 레거시 프로젝트(2023-11 생성). 공통 지침은 `~/.claude/CLAUDE.md` 를 따르되 이 파일이 우선한다.
> 전체 점검 결과와 조치 순서는 [docs/CODE_AUDIT_2026-10-30.md](docs/CODE_AUDIT_2026-10-30.md).

## 스택·실행
- Spring Boot 2.7.13 / Java 8 (`~/.jdks/corretto-1.8.0_512`) / Gradle 7.6.1 / WAR → 외장 Tomcat(`/usr/local/tomcat`, ROOT 배포) / MariaDB / MyBatis XML / JSP+JSTL / 관리자 UI Metronic.
- **서버 사양 제약**: JDK·Spring Boot·Tomcat 버전을 올리지 않는다 (2026-10-30 사용자 결정). 라이브러리 추가는 Java 8 호환 버전만.
- 컴파일: `JAVA_HOME=~/.jdks/corretto-1.8.0_512 ./gradlew compileJava -q`
- 로컬 실행(운영 DB 직결, 로컬 DB 없음): `SPRING_DATASOURCE_JDBC_URL='jdbc:log4jdbc:mariadb://<운영DB호스트>:3306/edumarine?serverTimezone=UTC&characterEncoding=utf8&userUnicode=true' ./gradlew bootRun --console=plain`. 업로드·다운로드 경로가 리눅스 절대경로라 로컬에서는 파일 기능이 동작하지 않는다. JSP·정적 파일 변경은 재시작해야 반영된다.
- 설정 파일 `src/main/resources/application.properties` 는 비밀값을 담고 있어 2026-10-30 부터 `.gitignore` 대상. 아직 git 추적 중이므로 사용자가 `git rm --cached` 로 해제한다. WAR 에는 작업 트리의 파일이 들어가므로 로컬 사본을 지운다.
- 배포는 사용자가 WAR 를 서버에 올려 Tomcat 을 재시작한다. Claude 는 배포하지 않는다.

## 운영 원칙 (2026-10-30 사용자 결정)
- 운영 중 사이트. 사용자·관리자가 쓰는 화면 흐름과 로그인 방식을 바꾸는 수정은 하지 않는다 (2단계 인증 추가, 로그인 정책 변경, 화면 동선 변경 등).
- 보안 조치는 서버 측 검사·검증을 더하는 방식으로만 한다. 기능 추가·개선은 발주사 비용 사항이므로 요청 없이는 하지 않는다.
- 롤백 가능성을 항상 남긴다: DB 데이터 형식을 바꾸는 조치(예: 관리자 비밀번호 해시 전환)는 설정값으로 켜고 끌 수 있게 한다.

## 구조 메모
- 컨트롤러 2개(공개 `EduMarineController`, 관리자 `EduMarineMngController`), 서비스 구현 3개, 매퍼 XML 5개. 세션은 회원·관리자 모두 `status=logon`, `id` 를 쓰고 관리자만 `gbn`·`note`·`adminYn` 이 추가된다.
- 교육 신청은 2026-01 부터 통합 양식(UNIFIED, `application_unified`, `UnifiedMapper`). `eduApply01~22` 레거시 모듈(컨트롤러·서비스·매퍼·JSP·JS 19벌)은 기존 신청 데이터 조회·수정·엑셀용으로 남아 있다. 신규 교육은 통합 양식으로만 만든다.
- 교육과정명(한글 문자열)이 라우팅 키다: `MenuService`, `static/js/front/schedule.js`, `EduMarineMngServiceImpl` 의 SMS 대상 switch, 매퍼 XML. 과정명을 바꾸면 모두 확인한다.
- 관리자 JSP 82개가 Metronic 레이아웃 전체를 각자 복제한다. 로그인 가드는 JSP 스크립트 + 서버 인터셉터(`config/WebMvcConfig`).
- 결제는 이니시스 STDPay. PC 복귀는 `merchantData`(tableSeq||id||ts||sign), 모바일은 `P_NOTI` 끝에 ts·sign 이 붙는다(`util/SignUtil`). 가상계좌 입금 통보는 `/pc/payment/vbank/vacct/noti.do`(이니시스 IP 검사).
- 공개 SMS 알림 `/sms/send/notify/sending.do` 는 회원가입(1)·수강신청(2)·취소완료(5)·키워드(8)만 허용하고 내용은 서버 템플릿으로 다시 만든다(IP 1분 40회, 같은 대상 10분 4회 제한). 비로그인 호출(가입 직후) 흐름을 위해 로그인 검사 대상이 아니다.
- 뉴스레터 구독 띠는 `views/inc/subscribe.jsp` 공통 파일로 메인과 뉴스레터 목록(`board/news_list.jsp`)에 노출된다. 구독 API 는 관리자 경로(`/mng/newsletter/subscriber/checkDuplicate.do`, `insert.do`)를 공개로 쓰므로 관리자 인터셉터 예외 목록에 들어 있다.

## 보안 조치 이력
### 2026-10-30 (점검 보고서 1단계 적용, 미배포)
- 추가: `config/WebMvcConfig`, `interceptor/AdminAuthInterceptor`·`LoginRequiredInterceptor`·`AuthResponses`. `/mng/**`(로그인 화면·처리·로그아웃·세션종료 제외)와 관리자 전용 API(`/mail/send.do`, `/sms/send.do`, `/sms/send/certNum.do`, `/sms/send/notify/train.do`, `/member/grade/update.do`, `/train/selectNextTime.do`, `/train/active.do`, `/file/upload/selectList.do`)는 관리자 세션 필요. 업로드·삭제 API(`/file/upload.do`, `/file/upload/save.do`, `/file/upload/update.do`, `/file/delete.do`, 경력·자격 삭제)는 로그인 필요. 화면 요청은 로그인 페이지로 리다이렉트, API 는 401 JSON.
- 추가: `util/PathSecurityUtil`(루트 밖 경로 차단, 실행·스크립트 확장자 차단), `util/SignUtil`(결제 복귀 서명), `util/PasswordUtil`(PBKDF2, JDK 내장), `util/SimpleRateLimiter`(메모리 횟수 제한), `util/HtmlSanitizer`(jsoup 1.15.3).
- 변경: `/file/download.do`, `/board/uploadFileGet` 는 업로드 루트·메일 이미지 폴더 밖이면 404. `/file/upload.do` 는 `gbn` 형식 검증과 확장자 차단(저장 직후 삭제), `uploadThumb.do` 도 확장자 차단.
- 변경: 결제 복귀 세션 복구는 서명이 맞을 때만. 서명 키는 기동 시 메모리 생성이라 재기동 전후에 걸친 결제는 복구만 생략되고 결제 처리는 그대로.
- 변경: 비밀번호 초기화는 서버가 임시 비밀번호를 만들고 DirectSend 로 본문을 직접 보낸 뒤 DB 를 바꾼다(템플릿 93 미사용). 이메일 조회 응답은 마스킹. `main.js` 의 `/mail/send.do` 호출 제거. 같은 ID 10분 6회, 같은 IP 10분 20회 제한.
- 변경: 마이페이지 신청 조회·수정·상태변경·취소(레거시 22종 + 통합) 소유자 검사. 신청 insert 는 로그인 시 세션 회원번호로 고정. 화면에서 보내는 `결제완료` 는 교육비가 1원 이상으로 확인되는 교육이면 거부(0원·미입력·교육 미확인은 허용). `/member/seq/selectSingle.do` 는 seq 와 마스킹 id 만 반환.
- 변경: 커뮤니티 본문은 저장·조회 시 jsoup 정화, 제목·작성자·해시태그 `c:out`, 목록 JS 이스케이프.
- 변경: 관리자 로그인 실패 같은 ID 10분 20회 제한, 성공 시 세션 ID 교체, 응답에서 password 제거, 서버가 본 접속 IP 를 로그에 기록. 해시 검증은 항상 지원하고, 해시 저장 전환은 `security.admin-password-hash=true` 일 때만(기본 false, 롤백 안전).
- 변경: 회원 로그인 실패 10분 20회 제한, 세션 ID 교체.
- 변경: 관리자 세션 종료 API 가 세션별 플래그 사용(B-2). electro SMS 대상 SQL 추가(B-1). 로그 레벨 `jdbc.sqltiming`/`resultsettable` OFF.
- 삭제: 동작하지 않던 `/uploadFile.do`, 자격증명이 든 `MySQLConnectionTest.java`, 미사용 `commons-fileupload`, `EduMarineApplication` 의 주석 블록.
- 화면에서 보이는 차이: 비밀번호 초기화 메일이 템플릿 대신 직접 작성 본문, 안내 팝업의 이메일이 마스킹(앞 3자). 로그인 20회 실패 시 10분 차단 메시지.

## 배포 후 확인 포인트
1. 관리자 로그인 → 목록 JSON·엑셀 다운로드 정상. 서버 로그 `[admin login] ... clientIp= serverIp=` 두 값이 같은지(같으면 서버 측 IP 검사로 전환 가능).
2. 회원 로그인, 마이페이지 신청 내역·수정, 통합 신청·무료 신청 완료, 결제(PC·모바일) 복귀 화면.
3. 비밀번호 초기화 메일 수신·임시 비밀번호 로그인.
4. 게시판 첨부 다운로드, 관리자 첨부 업로드, 커뮤니티 이미지 표시.
5. 안정 확인 후 `security.admin-password-hash=true` 로 변경해 재배포(관리자 재로그인 시 해시 전환).

## 진행 중 / 미완 (보고서 8장 참조)
- 2단계: 노출된 비밀값(DB root·서버·이니시스·알리고·DirectSend) 교체, `git rm --cached src/main/resources/application.properties`, 코드 내 키를 설정으로 이동, DB 외부 접속 차단.
- 서버 확인 필요: Tomcat `webapps/upload` 자동 배포 여부, 프록시 유무.
- 3단계 정리(죽은 코드·레이아웃 공통화·로거 도입)와 4단계 구조 전환은 사용자 결정 대기.

## 하드코딩 체크리스트
- 업로드 루트 `/usr/local/tomcat/webapps/upload` (`PathSecurityUtil`, `EduMarineMngController` 업로드·다운로드·썸네일)
- 이니시스 mid/signKey (`EduMarineController` 결제 요청 2곳), 취소 API key/iv (`EduMarineMngServiceImpl` 취소 API 2곳), DirectSend 계정·키 (`processMailSend`), 알리고 SMS 계정·키 (`CommServiceImpl` 2곳)
- 결제·신청 상태 문자열 `결제완료`, `입금대기`, `결제대기`, `취소신청`, `취소완료`
- SMS 발신번호 `CommConstants.SMS_SENDER_NUM`, 이니시스 통보 IP 대역(`payment_vbank_vacct_noti`)
- 교육과정명 라우팅 (구조 메모 참조)

## 변경 이력
- 2026-10-30: 전체 코드 점검 보고서 작성, 1단계 보안 조치 적용(위 이력). 사용자 검토 반영: 화면 `결제완료` 거부 기준을 "교육비 1원 이상 확인 시" 로 완화, 횟수 제한 수치 2배(로그인 20회/10분, 초기화 ID 6회·IP 20회/10분, SMS 알림 IP 40회/1분·대상 4회/10분). 커밋 `c8e3848`, 배포는 사용자 결정 대기.
- 2026-10-30: 발주사 요청으로 뉴스레터 구독 섹션을 `inc/subscribe.jsp` 로 분리해 뉴스레터 목록 최하단에도 메인과 동일 노출(국문 전용, 상세 페이지는 요청 없음). 같은 날 발견한 회귀(공개 구독 API 가 관리자 인터셉터에 막힘)를 `WebMvcConfig` 예외 처리로 수정.
