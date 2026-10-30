# edumarine 프로젝트 지침 (경기해양레저인력양성센터 edumarine.org)

> 템플릿 미적용 레거시 프로젝트(2023-11 생성). 공통 지침은 `~/.claude/CLAUDE.md` 를 따르되 이 파일이 우선한다.
> 전체 점검 결과와 조치 순서는 [docs/CODE_AUDIT_2026-10-30.md](docs/CODE_AUDIT_2026-10-30.md).

## 스택·실행
- Spring Boot 2.7.13 / Java 8 (`~/.jdks/corretto-1.8.0_512`) / Gradle 7.6.1 / WAR → 외장 Tomcat(`/usr/local/tomcat`, ROOT 배포) / MariaDB / MyBatis XML / JSP+JSTL / 관리자 UI Metronic.
- **서버 사양 제약**: JDK·Spring Boot·Tomcat 버전을 올리지 않는다 (2026-10-30 사용자 결정). 라이브러리 추가는 Java 8 호환 버전만.
- 컴파일: `JAVA_HOME=~/.jdks/corretto-1.8.0_512 ./gradlew compileJava -q`
- **실제 배포 WAR 는 IntelliJ 로 만든다** (사용자 방식, 2026-10-30 확인): IntelliJ → Build → Build Artifacts… → `ROOT` → Build → `out/artifacts/ROOT/ROOT.war` 생성(아티팩트 정의 `.idea/artifacts/ROOT.xml`, Gradle exploded WAR 를 그대로 묶음, `src/main/resources/application.properties` 포함). 이 파일을 FTP 로 서버 `/usr/local/tomcat/webapps/ROOT.war` 에 올리면 Tomcat 이 재배포한다. `out/`·`.idea/` 는 git 제외.
- Gradle 로도 만들 수 있다(검증용): `JAVA_HOME=~/.jdks/corretto-1.8.0_512 ./gradlew clean bootWar -q` → `build/libs/edumarine-0.0.1-SNAPSHOT.war`. `build.gradle` 의 `War` 중복 제외(`DuplicatesStrategy.EXCLUDE`, 2026-10-30)가 없으면 `WEB-INF/lib/cos.jar` 중복으로 실패한다(IntelliJ 아티팩트 빌드에는 영향 없음).
- **파일을 삭제·이동한 변경을 배포할 때는 반드시 깨끗이 다시 빌드한다**: IntelliJ 아티팩트는 `build/libs/exploded/` 에 증분으로 쌓아 디스크에서 지운 파일이 출력에 남는다(2026-10-30 5차 배포에서 삭제한 JS·CSS·JSP·클래스 6종이 WAR 에 잔존, 참조가 없어 무해했음). Build → Build Artifacts… → ROOT → **Rebuild**, 또는 빌드 전에 `build/libs/exploded` 폴더를 지운다.
- 업로드 전 확인(사용자 터미널은 PowerShell 이라 `unzip` 이 없음): `Add-Type -AssemblyName System.IO.Compression.FileSystem; $z=[IO.Compression.ZipFile]::OpenRead("$PWD\out\artifacts\ROOT\ROOT.war"); $r=New-Object IO.StreamReader($z.GetEntry('WEB-INF/classes/application.properties').Open()); ($r.ReadToEnd() -split "`n") | Select-String 'security\.'; $r.Close(); $z.Dispose()` → `security.*` 값이 의도한 대로인지 본다. Git Bash 에서는 `unzip -p out/artifacts/ROOT/ROOT.war WEB-INF/classes/application.properties | grep security.`. `build.gradle` 을 바꾼 뒤에는 IntelliJ 가 Gradle 프로젝트 다시 읽기를 요청할 수 있다(동의하면 됨).
- 로컬 실행(운영 DB 직결, 로컬 DB 없음): `SPRING_DATASOURCE_JDBC_URL='jdbc:log4jdbc:mariadb://<운영DB호스트>:3306/edumarine?serverTimezone=UTC&characterEncoding=utf8&userUnicode=true' SECURITY_ADMIN_IP_CHECK=client ./gradlew bootRun --console=plain`. `SECURITY_ADMIN_IP_CHECK=client` 가 없으면 관리자 IP 검사가 서버 관측 IP(로컬은 `0:0:0:0:0:0:0:1`)로 비교해 "해당 계정에 등록된 IP가 아닙니다" 로 로그인이 안 된다(운영 설정 파일은 `server` 유지, 환경변수는 로컬 실행에만 적용). DB 주소 없이 띄우면 로그인 요청이 500 이 나고 화면은 알림창만 띄운다. 업로드·다운로드 경로가 리눅스 절대경로라 로컬에서는 파일 기능이 동작하지 않는다. JSP·정적 파일 변경은 재시작해야 반영된다.
- 설정 파일 `src/main/resources/application.properties` 는 비밀값(DB·이니시스·DirectSend·알리고)을 담고 있어 2026-10-30 부터 `.gitignore` 대상이며 git 히스토리에서도 제거했다. **로컬 사본은 반드시 유지한다** — IntelliJ 아티팩트(WAR)에 이 파일이 그대로 들어가 서버 설정이 된다. 사본은 Bitwarden 에 보관. 외부 서비스 키 항목은 `config/ExternalApiProperties` 주석 참조.
- 배포는 사용자가 위 방식으로 `ROOT.war` 를 FTP 업로드한다(템플릿 프로젝트의 `deploy/deploy.sh` 방식이 아님). Claude 는 배포하지 않고, 배포 후 확인 포인트만 정리한다.
- **정적 자원 관리**(2026-10-30 사용자 결정, 같은 날 수정): 이미지·폰트·Metronic assets(`static/img`, `static/assets`, `static/eng/img`, `static/eng/fonts`)는 코드와 같이 git 에서 관리한다(히스토리 정리 뒤 한 번만 추가됨, 거의 바뀌지 않음). **브로슈어 PDF(`static/file/*.pdf`)만 git 제외**이며 이 PC 와 배포 WAR 에만 있으므로 새 PC 에서는 서버 배포본에서 복사한다(`static/file/sms/` 의 SMS 업로드 양식은 추적). 퍼블리셔 전달 이미지는 폴더에 넣고 커밋한다.
- JSP 전체 번역·컴파일 검증(로그인 없이): Gradle init 스크립트로 런타임 클래스패스를 뽑은 뒤 `org.apache.jasper.JspC -uriroot src/main/webapp -webapp src/main/webapp -d <out> -compile -die1` (클래스패스에 Gradle 배포본의 `ant-1.10.11.jar` 추가 필요). 2026-10-30 기준 201개 JSP 오류 0.

## 비밀값 관리 규칙 (2026-10-30)
- 비밀값(DB 계정·비밀번호, 이니시스·DirectSend·알리고 키)은 `src/main/resources/application.properties` 에만 둔다. 이 파일은 git 제외이며 WAR 에 포함돼 서버 설정이 된다. 사본은 Bitwarden 에 보관한다.
- 소스(Java·JSP·JS)·문서·커밋 메시지·채팅에 비밀값을 적지 않는다. 코드에서는 `config/ExternalApiProperties` 를 주입받아 쓰고, 새 외부 서비스 키가 생기면 그 클래스에 항목을 추가한다.
- 새 설정 항목을 만들면 `application.properties.example` 에 키 이름만(값 없이) 함께 추가한다. 새 PC 에서는 예시 파일을 복사해 Bitwarden 값으로 채운다.
- pre-commit 훅(`scripts/git-hooks/pre-commit`, 저장소에 포함)이 설정 파일 커밋과 `키=값` 형태의 비밀값 커밋을 막는다. 클론마다 한 번 `git config core.hooksPath scripts/git-hooks` 로 연결한다(연결 여부: `git config core.hooksPath`).
- 비밀값이 어디든 노출되면 24시간 내 교체가 원칙이다. 2026-10-30 과거 노출분은 사용자 결정으로 교체하지 않았다.

## 운영 원칙 (2026-10-30 사용자 결정)
- 운영 중 사이트. 사용자·관리자가 쓰는 화면 흐름과 로그인 방식을 바꾸는 수정은 하지 않는다 (2단계 인증 추가, 로그인 정책 변경, 화면 동선 변경 등).
- 보안 조치는 서버 측 검사·검증을 더하는 방식으로만 한다. 기능 추가·개선은 발주사 비용 사항이므로 요청 없이는 하지 않는다.
- 롤백 가능성을 항상 남긴다: DB 데이터 형식을 바꾸는 조치(예: 관리자 비밀번호 해시 전환)는 설정값으로 켜고 끌 수 있게 한다.

## 로그 규칙 (2026-10-30 도입)
- 로그는 Lombok `@Slf4j` 의 `log` 만 쓴다. `System.out`·`System.err`·`printStackTrace` 는 쓰지 않는다(전수 제거 완료, 다시 넣지 않는다).
- 예외는 `log.error("[메서드명] 처리 중 예외", e)` 형태로 스택을 함께 남긴다. 운영상 의미 있는 사건(결제 승인·취소 결과, 세션 복구, SMS·메일 발송 결과, 관리자 로그인)은 `log.info`/`log.warn`.
- 이름·연락처·이메일·주소·카드정보·인증번호·비밀번호·API 키·DTO 전체(`toString()`)는 로그에 남기지 않는다. 식별이 필요하면 seq·id·tid·resultCode 만.
- log4jdbc 쿼리 로그는 `logging.level.jdbc=OFF`. 디버깅 때만 `logging.level.jdbc.sqltiming=INFO` 를 잠시 켠다.
- 이니시스 샘플 라이브러리(`INIpaySample_v1.3.jar`)는 `logging.level.com.inicis=WARN` 으로 둔다(승인 전문에 구매자 개인정보가 들어 있음). 서명 계산은 라이브러리의 `SignatureUtil.makeSignature`(signKey 를 stdout 에 출력) 대신 `util/InicisSignUtil` 을 쓴다. 결제 추적은 우리 쪽 INFO(`결제 요청`, `결제 응답 resultCode`, `승인 결과 tid`)로 한다.

## 구조 메모
- 컨트롤러 2개(공개 `EduMarineController`, 관리자 `EduMarineMngController`), 서비스 구현 3개, 매퍼 XML 5개. 세션은 회원·관리자 모두 `status=logon`, `id` 를 쓰고 관리자만 `gbn`·`note`·`adminYn` 이 추가된다.
- 교육 신청은 2026-01 부터 통합 양식(UNIFIED, `application_unified`, `UnifiedMapper`). `eduApply01~22` 레거시 모듈(컨트롤러·서비스·매퍼·JSP·JS 19벌)은 기존 신청 데이터 조회·수정·엑셀용으로 남아 있다. 신규 교육은 통합 양식으로만 만든다.
- 교육과정명(한글 문자열)이 라우팅 키다: `MenuService`, `static/js/front/schedule.js`, `EduMarineMngServiceImpl` 의 SMS 대상 switch, 매퍼 XML. 과정명을 바꾸면 모두 확인한다.
- 관리자 JSP 공통 레이아웃(2026-10-30 공통화): `views/mng/inc/_*.jspf` 11개를 **정적 include**(`<%@ include %>`)로 쓴다. 각 JSP 의 순서는 page 지시자 → `_top`(taglib·doctype·html) → `_head`(+페이지별 `<style>`) → `</head>` → `_body_open` 또는 `_body_open_loader`(body 속성·테마 스크립트·미로그인 안내) → **`<c:if test="${sessionScope.get('status') eq 'logon'}">` 는 각 JSP 에 그대로 둔다**(Jasper 는 커스텀 태그가 같은 파일에서 열리고 닫혀야 함) → `_app_open`(앱 루트 div; 스피너·로더 div 가 있는 페이지는 inline) → `_header` → `_sidebar_main_open`(사이드바 `my:sidebarMenu` + main 열기) → Toolbar(페이지별 제목·경로) → 내용 → `_footer` → (페이지별 모달) → `_scrolltop` → `_scripts_common`(번들 JS) → 페이지 스크립트 → `</c:if>` → `_bottom`(`</body></html>`). 메뉴·헤더·푸터·공통 JS 를 바꿀 때는 include 파일만 고친다(펼친 결과가 원본과 같도록 만들었으므로 화면 동일). include 안에서 JSTL 태그를 열고 다른 파일에서 닫지 않는다. 예외로 일부를 inline 으로 둔 파일: `main.jsp`(들여쓰기가 달라 `_top`·`_head`·`_scrolltop` 만 include), `index.jsp`·`customer/resume/detail.jsp`(구조 다름, 미적용), `pop/banner/detail.jsp`·`pop/popup/detail.jsp`(헤더 문구가 다른 옛 복사본이라 헤더 inline), `smsMng/sms.jsp`(공통 JS 목록 다름), `smsMng/sms/send.jsp`(head 다름). 새 관리자 화면은 비슷한 기존 JSP 를 복사해 만든다. 로그인 가드는 `_body_open*` 의 JSP 스크립트 + 서버 인터셉터(`config/WebMvcConfig`).
- 관리자 엑셀 다운로드 19개 핸들러는 `util/ExcelExportUtil`(헤더·본문 스타일, 셀 쓰기, 열 너비)을 쓴다. 컬럼 정의·헤더 루프·응답 쓰기는 핸들러에 남아 있다. 이니시스 취소 API 두 메서드는 `EduMarineMngServiceImpl.callInicisRefundApi` 공통 helper 를 쓴다.
- 결제는 이니시스 STDPay. PC 복귀는 `merchantData`(tableSeq||id||ts||sign), 모바일은 `P_NOTI` 끝에 ts·sign 이 붙는다(`util/SignUtil`). 가상계좌 입금 통보는 `/pc/payment/vbank/vacct/noti.do`(이니시스 IP 검사).
- 공개 SMS 알림 `/sms/send/notify/sending.do` 는 회원가입(1)·수강신청(2)·취소완료(5)·키워드(8)만 허용하고 내용은 서버 템플릿으로 다시 만든다(IP 1분 40회, 같은 대상 10분 4회 제한). 수강신청(2)은 `seq` 가 회원번호든 신청번호든 서버가 회원을 찾아 발송한다. 비로그인 호출(가입 직후) 흐름을 위해 로그인 검사 대상이 아니다.
- 교육 2일 전 안내 문자(템플릿 7, 매일 14시 스케줄러)의 대상은 `CommMapper.getSmsSendingTrainNotiList` 가 레거시 신청 테이블 16개 + `electro` + `application_unified` 를 UNION ALL 로 모은다(2026-10-30 G1). 신청 테이블이 새로 생기면 여기에 추가한다.
- 관리자 과정별 문자 발송(`mng/smsMng/sms/send.jsp` → `/mng/smsMng/sms/send/selectList.do`): 레거시 과정은 고정 option + 과정명 switch(`processSelectSmsSendList`), 통합 교육은 컨트롤러가 `train` 에서 과정명을 모아(`unifiedGbnList`) option 값 `UNIFIED:<과정명>` 으로 그리고 서버가 접두어를 보고 `UnifiedMapper.selectSmsSendUnifiedList` 로 조회한다(2026-10-30 G2).
- 통합 신청 저장(`/apply/eduApplyUnified/insert.do`)은 서버에서 마감·접수기간·정원·중복을 검사해 `resultCode 99` + 사유로 거부한다(`checkUnifiedApplyAvailable`, 2026-10-30 G3). 마이페이지 통합 수정은 취소 상태·교육 종료 후 서버가 `modYn=N` 과 저장 거부로 막는다(`unifiedModifiable`, G4).
- 통합 신청 화면의 JS 검증(`eduApplyUnified.jsp` `fn_apply_submit`)은 양식 이름이 아니라 화면에 있는 필드(`#clothesSize`, `#participationPath`) 기준이고, 정의되지 않은 form_type 은 제출 버튼을 내지 않는다(2026-10-30 G5). 모바일 P_NOTI 의 과정명은 `joinNotiTrainName` 으로 복원한다(G7). 결제 내역이 있는 통합 취소 승인은 환불 구분(ALL/PART)이 없으면 서버가 거부한다(G8).
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
- 변경: 관리자 로그인 실패 같은 ID 10분 20회 제한, 성공 시 세션 ID 교체, 응답에서 password 제거, 서버가 본 접속 IP 를 로그에 기록. 해시 검증은 항상 지원하고, 해시 저장 전환은 `security.admin-password-hash=true` 일 때만(코드 기본값 false). 2026-10-30 2차 배포 확인 후 로컬 설정 파일에서 true 로 전환 — 관리자가 다음 로그인에 성공하면 평문이 `$pbkdf2$` 해시로 바뀌고, 이후 `PasswordUtil` 이 없는 구버전 WAR(`c8e3848` 이전)로는 롤백할 수 없다(설정을 false 로 되돌리는 것은 안전). IP 검사는 `security.admin-ip-check=server`(로컬 설정 파일에 server, 코드 기본값 client)면 서버가 본 접속 IP 로 비교.
- 변경: 회원 로그인 실패 10분 20회 제한, 세션 ID 교체.
- 변경: 관리자 세션 종료 API 가 세션별 플래그 사용(B-2). electro SMS 대상 SQL 추가(B-1). 로그 레벨 `jdbc.sqltiming`/`resultsettable` OFF.
- 삭제: 동작하지 않던 `/uploadFile.do`, 자격증명이 든 `MySQLConnectionTest.java`, 미사용 `commons-fileupload`, `EduMarineApplication` 의 주석 블록.
- 화면에서 보이는 차이: 비밀번호 초기화 메일이 템플릿 대신 직접 작성 본문, 안내 팝업의 이메일이 마스킹(앞 3자). 로그인 20회 실패 시 10분 차단 메시지.

## 배포 후 확인 포인트
1. 관리자 로그인 → 목록 JSON·엑셀 다운로드 정상. 서버 로그 `[admin login] ... clientIp= serverIp=` 두 값이 같은지(같으면 서버 측 IP 검사로 전환 가능).
2. 회원 로그인, 마이페이지 신청 내역·수정, 통합 신청·무료 신청 완료, 결제(PC·모바일) 복귀 화면.
3. 비밀번호 초기화 메일 수신·임시 비밀번호 로그인.
4. 게시판 첨부 다운로드, 관리자 첨부 업로드, 커뮤니티 이미지 표시.
5. 해시 전환 배포(2026-10-30 설정 true) 후: 관리자 로그인 정상, `admin.password` 가 `$pbkdf2$` 로 시작하는지, 관리자 관리 > 상세 화면 비밀번호란이 비어 있고 수정 저장이 되는지, 신규 관리자 등록·로그인이 되는지, 회원 관리 > 상세 화면 비밀번호란에 "설정됨" 만 보이고 회원 정보 수정 저장이 되는지, 회원 목록에 "회원 등록" 버튼이 없고 `/mng/customer/member/detail.do`(seq 없음)가 목록으로 돌아가는지.
6. 통합 양식 G1~G4(2026-10-30): 관리자 문자 발송 과정 목록에 통합 교육 과정명 표시·신청자 조회, 통합 교육 TEST 신청(정상 신청·문자 / 정원 초과·접수 종료 교육은 사유 팝업), 마이페이지 취소완료·종료 교육의 수정 화면에 저장 버튼 없음, 교육 2일 전 14시 안내 문자가 통합 신청자에게 발송. G5~G8: 통합 신청 화면 3종의 항목 미입력 안내와 정상 제출, 관리자 취소 승인(환불 구분 선택)이 전과 같은지.
## 진행 중 / 미완 (보고서 8장 참조)
- 2단계 진행 상황(2026-10-30): DB 외부 접속은 cafe24 에서 개발사무실 IP 만 허용하도록 제한 완료(사용자). 코드 내 키는 설정 파일로 이동 완료. `application.properties`·`MySQLConnectionTest.java` 는 git 히스토리에서 제거. **비밀값 재발급은 하지 않기로 결정(2026-10-30, 사용자)** — 과거 GitHub 에 올라갔던 값이지만 저장소는 비공개이고 DB 접속은 IP 제한으로 막혀 있어 교체 대신 앞으로의 관리로 대응한다. 히스토리는 제거했고 다른 클론은 없음. 관리 장치: `application.properties.example`(값 없는 예시, 커밋 대상), pre-commit 훅(`scripts/git-hooks/pre-commit`, 설정 파일·키=값 커밋 차단, `git config core.hooksPath scripts/git-hooks` 로 연결), 비밀값 관리 규칙(문서 상단). 전용 DB 계정(최소 권한) 전환은 여전히 권장 사항.
- 서버 확인 필요: Tomcat `webapps/upload` 자동 배포 여부, 프록시 유무.
- 통합 양식 흐름 점검(2026-10-30): [docs/UNIFIED_FLOW_REVIEW_2026-10-30.md](docs/UNIFIED_FLOW_REVIEW_2026-10-30.md). 본 흐름(생성→신청→결제→취소·환불→마이페이지→관리자)은 통합 양식에서 끝까지 이어짐. 공백: G1 교육 2일 전 자동 문자가 통합 신청자(와 electro)를 제외, G2 관리자 과정별 수동 문자에서 통합 교육 선택 불가, G3 통합 신청 저장에 정원·마감·기간·중복 서버 검사 없음, G4 마이페이지 수정이 항상 허용 → **G1~G8 모두 2026-10-30 수정(문서 8·9절)**. 새 양식 추가 절차는 문서 4절(JS 검증은 필드 기준이라 목록 수정 불필요).
- 3단계 정리는 2026-10-30 완료(변경 이력 참조). 4단계 중 레거시 신청 모듈 19벌 제거는 데이터 이관이 필요해 보류(사용자 결정, "좀 더 디테일하게 점검한 뒤"), 과정명 코드 테이블 전환은 레거시 모듈 제거와 함께 다룰 항목, JDK 17 전환은 서버 사양 제약으로 보류.
- 발주사 결정 항목: 팸투어 통합 양식(FAMTOUR_FORM)은 미구현 상태로 보류(코드의 주석 잔재는 제거). 통합 양식 신청 완료 문자는 2026-10-30 추가함.

## 하드코딩 체크리스트
- 업로드 루트 `/usr/local/tomcat/webapps/upload` (`PathSecurityUtil`, `EduMarineMngController` 업로드·다운로드·썸네일)
- 외부 서비스 키는 2026-10-30 부터 소스에 없다. `config/ExternalApiProperties`(`inicis.mid`, `inicis.sign-key`, `inicis.cancel-key`, `inicis.cancel-iv`, `directsend.username`, `directsend.key`, `aligo.user-id`, `aligo.key`)를 주입받아 쓴다: 결제 요청·응답 검증(`EduMarineController` 3곳), 취소 API(`EduMarineMngServiceImpl` 2곳), 메일 발송(`processMailSend`), 문자 발송(`CommServiceImpl` 2곳). 키를 다시 소스에 적지 않는다. 설정이 비면 기동 로그에 `[external api config]` 경고.
- 이니시스 취소 API 의 `clientIp`(서버 공인 IP)와 DirectSend 발신 주소(`business@...`)는 비밀값이 아니라 소스에 남아 있다.
- 결제·신청 상태 문자열 `결제완료`, `입금대기`, `결제대기`, `취소신청`, `취소완료`
- SMS 발신번호 `CommConstants.SMS_SENDER_NUM`, 이니시스 통보 IP 대역(`payment_vbank_vacct_noti`)
- 교육과정명 라우팅 (구조 메모 참조)

## 변경 이력
- 2026-10-30: 전체 코드 점검 보고서 작성, 1단계 보안 조치 적용(위 이력). 사용자 검토 반영: 화면 `결제완료` 거부 기준을 "교육비 1원 이상 확인 시" 로 완화, 횟수 제한 수치 2배(로그인 20회/10분, 초기화 ID 6회·IP 20회/10분, SMS 알림 IP 40회/1분·대상 4회/10분). 커밋 `c8e3848`, 배포는 사용자 결정 대기.
- 2026-10-30: 발주사 요청으로 뉴스레터 구독 섹션을 `inc/subscribe.jsp` 로 분리해 뉴스레터 목록 최하단에도 메인과 동일 노출(국문 전용, 상세 페이지는 요청 없음). 같은 날 발견한 회귀(공개 구독 API 가 관리자 인터셉터에 막힘)를 `WebMvcConfig` 예외 처리로 수정.
- 2026-10-30: 화면 전수점검 완료 — 공개 GET 111건(정상 84, 의도된 로그인 리다이렉트 25, 죽은 결제 샘플 핸들러 1, Accept 에 따른 정상 406 1)과 관리자 GET 84건 전부 렌더링 확인, 서버 예외 0건. 기록 `docs/QA_SWEEP_2026-10-30.md`. 쓰기 기능 점검(TEST 데이터)과 결제·파일·발송(서버에서만 가능)은 같은 문서 5장의 표대로 미실시.
- 2026-10-30: 메일 발송 요청 본문(API 키·본문) 콘솔 출력 제거(`68e57bf`). 이어서 `@Slf4j` 로거 도입: 컨트롤러 2·서비스 구현 3·SHA512 에서 활성 `System.out/err` 103곳과 `printStackTrace` 155곳을 `log.*` 로 전환하거나 삭제(개인정보·인증번호·DTO 덤프 출력은 삭제), 주석 처리된 디버그 출력 1,274줄 제거. 동작 변경 없음.
- 2026-10-30: 수강신청 안내 문자(템플릿 2) 결함 수정. 레거시 신청 화면 20종이 `/sms/send/notify/sending.do` 에 회원번호 대신 신청번호를 넘겨 문자가 나가지 않던 것을, 서버(`CommServiceImpl.resolveMemberSeqByApplySeq`)가 신청번호 접두어로 신청 테이블을 찾아 회원번호를 조회하도록 보완(`CommMapper.selectMemberSeqByApplySeq`, 테이블명은 고정 목록). 화면 변경 없음. 통합 양식(UNIFIED)은 신청 완료 문자 호출 자체가 없어 발주사 확인 후 결정.
- 2026-10-30: 위 6건(`c8e3848`~`41893e9`)을 origin 에 푸시하고 사용자가 운영 서버에 배포. 배포 직후 읽기 전용 확인 통과 — 새 JS·구독 섹션 반영, 무세션 관리자 API 401, 관리자 화면 302, 임의 파일 읽기 3종 404(설정 파일 포함), 미로그인 마이페이지 302, 공개 화면·sitemap 200, 기존 업로드 이미지 200. 사용자 확인 남은 항목: 관리자 로그인 후 목록·엑셀, 비밀번호 초기화 메일 수신, 레거시 과정 TEST 신청 문자, 구독 버튼.
- 2026-10-30: 운영 로그로 관리자 접속 IP(서버 관측)와 화면 전송 IP 가 같고 프록시가 없음을 확인 → 관리자 IP 검사를 서버 관측 IP 기준으로 전환(`security.admin-ip-check=server`, X-Forwarded-For 미신뢰). 로그인 절차 변경 없음. 문제 시 설정을 client 로 되돌려 재배포.
- 2026-10-30: 배포 후 확인에서 발견 — 세션이 사라진 상태에서 관리자 메인(로그인 폼 POST 로 진입)을 새로고침하면 401 JSON 이 그대로 보임. 인증 거부 응답의 화면 판정을 메서드가 아니라 Accept 헤더 기준으로 바꿔 POST 재전송도 로그인 페이지로 리다이렉트(`AuthResponses`). 커밋 `a822458` 푸시·배포 후 운영 확인(읽기 전용): 무세션 `/mng/main.do` GET·POST 모두 302 → `/mng/index.do`, AJAX 목록 조회 401 JSON 유지, 공개 화면·관리자 로그인 화면·sitemap 200, 공개 구독 중복검사 API 200. 발주사 관리자(eduadmin) 로그인은 `mode=server` 로 정상 통과 확인.
- 2026-10-30: 관리자 비밀번호 해시 저장 전환(`security.admin-password-hash=true`, 로컬 설정 파일). 코드 확인 근거 — 로그인 SQL 은 id 로만 조회하고 비교는 `PasswordUtil.matches`(평문·해시 모두), 관리자 수정 SQL(`updateAdminMng`)은 password 컬럼을 갱신하지 않음, 계정 인증(`updateValidYn`)은 비밀번호 미사용, 목록·엑셀 노출 없음. 함께 수정: 관리자 관리 상세 화면(`mng/adminMng/admin/detail.jsp`)이 저장된 비밀번호를 입력란 value 로 내보내던 것을 제거하고, `mng/admin.js` 의 비밀번호 필수 검사를 신규 등록 때만 하도록 변경(수정 저장은 원래 비밀번호를 쓰지 않으므로 동작 동일). 회원 상세(`mng/customer/member/detail.jsp`)도 회원 비밀번호(SHA512 해시)를 비활성 입력란에 출력하던 것을 사용자 결정으로 같은 날 수정 — 값 대신 "설정됨" 고정 문구만 표시하고 `mng/member.js` 의 비밀번호 필수 검사를 제거(입력란은 비활성이라 전송되지 않고 `updateMember` 도 비밀번호를 갱신하지 않으므로 동작 동일). 이어서 사용자 결정으로 **관리자 회원 등록 기능 제거**(원래부터 등록 화면에 비밀번호란이 없고 서버 로직에 솔트·해시 처리가 없어 사용 불가 상태였음): 목록(`mng/customer/member.jsp`)의 "회원 등록" 버튼 삭제, `/mng/customer/member/detail.do` 는 seq 없이 들어오면 목록으로 리다이렉트, `/mng/customer/member/insert.do` 핸들러·`processInsertMember`·`EduMarineMngMapper.getMemberSeq/insertMember` 삭제, `mng/member.js` 등록 분기는 안내 메시지로 대체. 공개 회원가입(`EduMarineMapper.insertMember`)은 그대로. 회원 수정·삭제·엑셀은 변경 없음. 위 내용을 `387b489` 로 커밋·푸시하고 사용자가 같은 날 운영 배포(3차). 배포 직후 읽기 전용 확인: 운영 `member.js`·`admin.js` 에 새 문구 반영(등록 호출 제거 확인), 공개 화면·관리자 로그인 화면·sitemap 200, 무세션 회원 목록·상세 302, `insert.do` AJAX 401, 관리자 메인 POST 302. 관리자 로그인이 필요한 항목(해시 전환, 상세 화면, 등록 버튼 부재)은 사용자 확인.
- 2026-10-30: 3차 배포 후 첫 관리자 로그인에서 `Data too long for column 'password'` 경고 — `admin.password` 컬럼이 해시(83자)보다 짧아 해시 전환이 건너뛰어짐(로그인 자체는 성공, 평문 유지). 이 상태에서는 신규 관리자 등록도 같은 오류로 실패. 사용자가 운영 DB 에서 `ALTER TABLE admin MODIFY password VARCHAR(255)` 로 컬럼을 넓혀 해결(코드·배포 변경 없음). 이후 로그인부터 해시 전환 정상(13:49 로그인 로그 경고 없음, DB `password` 가 `$pbkdf2$` 로 시작함을 사용자가 확인). 교훈: 저장 형식을 바꾸는 조치는 대상 컬럼 길이를 먼저 확인한다.
- 2026-10-30: 2단계 — 소스에 박혀 있던 외부 서비스 키 8종(이니시스 상점 ID·signKey·취소 key·iv, DirectSend 계정·키, 알리고 계정·키)을 `application.properties` 로 옮기고 `config/ExternalApiProperties` 로 주입(치환 9곳, 주석에 남아 있던 키·테스트 계정 주석도 제거). 기동 시 누락 항목을 이름만 경고. 소스 트리 전체 잔존 검사 통과. 같은 날 `application.properties`(24개 커밋)와 `MySQLConnectionTest.java`(3개 커밋)를 `git filter-branch` 로 전체 히스토리에서 제거하고 강제 푸시 — **이 시점 이전 커밋 해시는 모두 바뀌었다**(이 문서·QA 문서·점검 보고서에 적힌 `c8e3848`~`9c7bab0` 등은 옛 해시). 다른 PC 의 클론은 다시 받아야 한다. DB 외부 접속은 cafe24 에서 개발사무실 IP 로 제한(사용자). 강제 푸시는 사용자가 직접 실행(원격 HEAD `aebc96e` 확인), 다른 클론 없음. **비밀값 재발급은 하지 않기로 결정(사용자)**. 앞으로의 관리 장치 추가: `application.properties.example`(값 없는 예시), `scripts/git-hooks/pre-commit`(설정 파일·키=값 커밋 차단, 시험 통과) + `.gitattributes`(훅 LF 고정), 문서 상단 "비밀값 관리 규칙". 훅 연결(`git config core.hooksPath scripts/git-hooks`)은 정책상 Claude 가 못 해 사용자가 실행(완료). 키 이동분을 사용자가 14:14 운영 배포(4차) — 기동 로그에 설정 8항목 모두 설정됨, 예외 없음, Tomcat 기본 앱(examples·manager 등)은 외부 403 확인. QA 문서 10장.
- 2026-10-30 (3단계 정리, 사용자 지시 "코드는 정리하되 화면 레이아웃은 절대 불변"):
  - 정적 자원 git 추적 해제(517 파일, img 40MB·assets 20MB·file 19MB·eng/fonts 19MB·eng/img 7MB) + `.gitignore`. 인증서 검증 잔재 `.well-known/pki-validation` 2개 삭제. 히스토리에는 아직 남아 있어 저장소 용량은 그대로(제거하려면 filter-branch 재실행 + 강제 푸시).
  - 참조 0건 코드 삭제: 매퍼 선언만 있던 5개 메서드, 호출처 없는 서비스 2개·`convertMap`, 미사용 DTO 4개(`CommCodeDTO`·`KillSessionDTO`·`UserVO`·`AbstractPagingResponseVo`), `Reference.xml`, 중복 SQL(`EduMarineMapper.xml` 3개, `UnifiedMapper.xml` 4개), 의미 없던 `HttpSessionBindingListener` 구현, 주석 처리된 옛 코드 64줄, 이니시스 샘플 결제 페이지 2개와 핸들러, 미사용 JS 10개(`jquery-1.11.1.js` 1만 줄 포함)·CSS 4개·`inc/commonJS.jsp`·`inc/commonMngJS.jsp`, 미사용 테스트 의존성 2개. `guide02/04/12` 와 공지(announcement) 기능은 링크·매핑이 살아 있어 삭제하지 않음.
  - 엑셀 export 공통화: `util/ExcelExportUtil` 신설, 19개 핸들러의 스타일 블록 17개·셀 쓰기 3줄 패턴 356곳·열 너비 루프 19개를 치환(동일 POI 호출), 암호화 주석 블록 제거. 관리자 컨트롤러 78KB 감소. 헤더 LIGHT_GREEN/ORANGE 스타일도 유틸로 생성(색상 동일).
  - 이니시스 취소 API 2개 메서드의 해시·요청·응답 코드를 `callInicisRefundApi` helper 로 통합(87→39줄, 109→59줄).
  - 관리자 JSP 레이아웃 공통화: 82개 파일을 `views/mng/inc/_*.jspf` 11개 정적 include 로 전환. 스크립트가 각 파일의 include 를 글자 그대로 펼쳐 원본과 비교 — 65개 바이트 동일, 17개는 HTML 주석 한 줄 위치만 다름(보이는 결과 동일). 관리자 JSP 합계 74,214줄 → 46,271줄. 전 JSP JspC 번역·컴파일 전후 모두 오류 0.
  - 발주사 결정 반영: 통합 양식 신청 완료 직후 수강신청 안내 문자(템플릿 2) 호출 추가(`eduApplyUnified.jsp`, 신청번호 AU… 로 서버가 회원 조회). FAMTOUR_FORM 주석 잔재 제거(`eduApplyUnified.jsp`, 관리자 교육 등록 `form_type` 선택지).
  - 로컬 기동 정상(설정 8항목), Java 컴파일 통과. 사용자 로그인 후 관리자 화면 7종·상세 2종·엑셀 다운로드 확인(QA 문서 11장). 4개 커밋으로 푸시.
- 2026-10-30 (저장소 용량 정리, 사용자 결정): 정적 폴더 5개(`static/img`·`file`·`assets`·`eng/img`·`eng/fonts`)와 `.well-known` 2개를 `git filter-branch` 로 **전체 히스토리 110개 커밋에서 제거**. 최종 트리 동일, 커밋 수 동일, `.git` 103MB → 3.2MB. **이 시점 이전의 커밋 해시는 모두 다시 바뀌었다**(문서에 적힌 옛 해시는 기록용). 백업 번들은 세션 scratchpad 에 임시 보관. 강제 푸시는 사용자가 실행(완료). 이후 Claude 의 권장(assets 는 관리자 UI 코드, 이미지는 납품물이라 git 에 있어야 원본 보존·복구 가능)에 따라 사용자 결정으로 `img`·`assets`·`eng/img`·`eng/fonts`·`file/sms` 를 다시 추적하고 **브로슈어 PDF(`static/file/*.pdf`)만 제외 유지**. 저장소는 약 90MB 로 돌아가지만 히스토리 중복이 없어 더 늘지 않는다.
- 2026-10-30 (운영 로그 점검 후): 이니시스 샘플 라이브러리가 결제 signKey 를 `stringToSign.toString()=` 으로 stdout 에 찍고(기존부터), 승인 요청·응답 전문(구매자 이름·전화·이메일·카드번호 일부)을 INFO 로 남기던 것을 수정. `util/InicisSignUtil`(정렬·연결·SHA-256, 출력 없음)을 만들어 `EduMarineController` 의 서명 호출 6곳과 mKey 해시 2곳을 교체(라이브러리와 결과 동일함을 비교 테스트로 확인), 설정에 `logging.level.com.inicis=WARN` 추가. 서버 `catalina.out` 의 과거 로그에는 signKey 가 남아 있으므로 로그 파일 접근 권한을 유지한다. 그 외 `ADMH` 405, 클라이언트 중단 시 `CloseNowException`(ErrorPageFilter ERROR), HTTP/2 설정 경고는 무해. 같은 작업에서 Gradle `war`/`bootWar` 가 `cos.jar` 중복으로 실패하던 것을 `build.gradle` 중복 제외 설정으로 해결하고 WAR(`security.admin-password-hash=true` 포함) 빌드 확인. 관리자 상세 화면 변경은 로그인이 필요해 브라우저 확인은 배포 후 확인 포인트 5 로 넘김.
- 2026-10-30 (통합 양식 점검 G1~G4 수정, 사용자 결정 "G1~G4 전부"): (G1) 교육 2일 전 안내 문자 대상 SQL 에 `electro`·`application_unified` UNION ALL 추가(`CommMapper.getSmsSendingTrainNotiList`). (G2) 관리자 과정별 문자 발송의 과정 목록 끝에 통합 교육 과정명 추가 — option 값 `UNIFIED:<과정명>`, 서버 접두어 분기, `UnifiedMapper.selectSmsSendUnifiedList` 신설(`EduMarineMngController`·`EduMarineMngService(Impl)`·`sms/send.jsp`). (G3) 통합 신청 저장 전 서버 검사(교육 존재·UNIFIED·마감·접수기간·정원·중복, 거부 시 `resultCode 99` + 사유, `checkUnifiedApplyAvailable`·`normalizeYmd`). (G4) 마이페이지 통합 신청 수정을 취소 상태·교육 종료 후 서버가 차단(`unifiedModifiable`, 수정 화면 `modYn` 과 `update.do` 모두; 수정 화면의 신청취소 버튼도 같은 `modYn` 조건 안에 있어 교육 종료 후에는 함께 숨겨짐 — 취소 API 는 그대로). 화면 흐름·레이아웃 변경 없음(관리자 과정 선택지만 늘어남). 컴파일·XML·JspC·로컬 기동 확인, 상세와 배포 후 확인 항목은 점검 문서 8절. 커밋·배포는 사용자 결정 대기.
- 2026-10-30 (통합 양식 점검 G5~G8 수정 + G1~G4 재점검): (G5) 통합 신청 화면 JS 검증을 양식 이름 목록 대신 화면 필드 기준으로 바꾸고 미정의 form_type 은 제출 버튼을 내지 않음(`eduApplyUnified.jsp`). (G6) `updateUnifiedApplyStatus` parameterType 정정, 통합 신청 GET 의 항상 null 이던 `appInfo` 조회와 양식 JSP 3개의 `appInfo` 참조 제거(렌더링 동일). (G7) 모바일 P_NOTI 과정명 복원 `joinNotiTrainName`(과정명에 `_` 가 있어도 결제 내역 교육명이 안 잘림, `payment_m.jsp` 불변). (G8) 결제 내역 있는 통합 취소 승인에 환불 구분(ALL/PART) 서버 검사. 검증: 컴파일·XML·JspC(JSP 4개) 통과, G7 테스트 7건, G1~G4 판정 표 테스트 38건 통과(리플렉션으로 컨트롤러 메서드 직접 호출). 상세는 점검 문서 9절, QA 14장. 커밋·배포는 사용자 결정 대기(삭제 파일 없어 일반 Build 로 충분).
