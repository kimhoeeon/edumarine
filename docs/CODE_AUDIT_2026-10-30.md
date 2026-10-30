# edumarine 코드 점검 보고서 (2026-10-30)

운영 중인 경기해양레저인력양성센터(edumarine.org) 프로젝트 전체를 정적으로 분석한 결과다.
코드는 수정하지 않았고, `JAVA_HOME=~/.jdks/corretto-1.8.0_512 ./gradlew compileJava` 로 현재 소스가 정상 컴파일되는 것만 확인했다.
비밀값(비밀번호·API 키)은 이 문서에 적지 않았다. 위치만 적었다.

## 1. 규모와 구성

| 구분 | 수치 |
|---|---|
| 스택 | Spring Boot 2.7.13 / Java 8 / Gradle 7.6.1 / WAR → 외장 Tomcat / MariaDB / MyBatis / JSP |
| Java | 110개 파일, 36,853줄 (컨트롤러 2개 16,687줄, 서비스 구현 3개 14,077줄) |
| 매퍼 XML | 5개, 12,268줄, SQL 511개 |
| JSP | 202개, 117,270줄 (관리자 84개) |
| JS (자체) | 73개, 약 60,000줄 (jQuery 사본 등 포함) |
| 정적 리소스 | 약 108MB (Metronic assets 20MB, img 40MB, eng 26MB, file 19MB) |
| 엔드포인트 | 공개 컨트롤러 239개, 관리자 컨트롤러 305개 |
| 테스트 | contextLoads 1개(운영 DB 필요), DB 접속 테스트 1개(자격증명 포함) |
| git | 91 커밋, 원격 github.com/kimhoeeon/edumarine, 작업 트리 clean |

프로젝트에 `CLAUDE.md`, `docs/`, `deploy/`, `publish/` 가 없다. 사용자 표준 템플릿 이전에 만들어진 프로젝트다.

## 2. 즉시 조치가 필요한 보안 문제 (Critical)

운영 중이고 결제·개인정보를 다루므로 내부 정책상 S 등급인데, 아래 항목은 외부인이 로그인 없이 바로 악용할 수 있는 수준이다.

### C-1. 관리자 API 전체가 로그인 검사 없이 열려 있음
- 서버 측 인터셉터·필터가 없다. `EduMarineMngController.java` 305개 매핑 중 세션을 읽는 곳은 `329`, `4408` 두 줄뿐이고 그것도 작성자 기록용이다.
- 로그인 검사는 JSP 안의 스크립트 리다이렉트뿐이다. 예: [member.jsp:121](../src/main/webapp/WEB-INF/views/mng/customer/member.jsp:121) `<c:if test="${sessionScope.get('status') ne 'logon'}">`. HTML 화면만 막고 JSON API 와 엑셀 다운로드는 그대로 응답한다.
- 예시 (모두 무인증):
  - `POST /mng/customer/member/selectList.do` → 전체 회원 목록. SQL [EduMarineMngMapper.xml:1498-1518](../src/main/resources/mapper/EduMarineMngMapper.xml:1498) 이 비밀번호 해시·연락처·이메일·생년월일·주소까지 반환.
  - `GET /mng/customer/member/excel/download.do` ([EduMarineMngController.java:4866](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:4866)) 및 과정별 엑셀 19종 → 신청자 개인정보 엑셀 통째로.
  - `POST /mng/customer/member/delete.do`, `/mng/board/*/delete.do`, `/mng/adminMng/admin/insert.do` → 삭제·관리자 계정 생성.
- 조치: `/mng/**`, `/file/upload*`, `/mail/**`, `/sms/**`, `/train/**`, `/member/grade/**` 에 세션 검사 인터셉터를 두고, JSON 요청은 401 로 응답.

### C-2. 임의 파일 읽기 (경로 조작)
- [EduMarineMngController.java:4789](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:4789) `GET /board/uploadFileGet?fileName=` → `new File(fileName)` 그대로. 절대경로를 주면 서버의 어떤 파일이든 읽힌다 (`application.properties` 의 DB 비밀번호 포함).
- [EduMarineMngController.java:4825](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:4825) `GET /file/download.do?path=&fileName=` → `..` 검사 없음. 게시판 첨부 다운로드가 이 URL 을 쓰므로 공개 페이지에 노출되어 있다.
- 조치: DB 의 file 테이블 id 로만 내려주고, 실제 경로는 서버가 조합. 정규화 후 업로드 루트 밖이면 거부.

### C-3. 업로드 경로 조작, 확장자 미검사 → 원격 코드 실행 가능성
- [EduMarineMngController.java:4649](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:4649) `POST /file/upload.do`: `gbn` 파라미터가 `/usr/local/tomcat/webapps/upload/<gbn>/` 에 그대로 들어간다. 확장자 검사가 없고 cos.jar `DefaultFileRenamePolicy` 가 원본 파일명을 유지한다. `gbn=../ROOT` 로 보내면 운영 웹앱 루트에 `.jsp` 를 놓을 수 있다. 회원 마이페이지(`eduApply02_modify.jsp`, `eduApply03_modify.jsp`, `resume.jsp`)도 이 엔드포인트를 쓰므로 회원이면 누구나, 그리고 C-1 때문에 사실상 누구나 호출 가능하다.
- [EduMarineMngController.java:3390](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:3390) `/mng/education/train/uploadThumb.do` 도 무인증·무검사.
- `webapps/upload` 디렉터리가 Tomcat 에 자동 배포되는 컨텍스트라면 `/upload/...jsp` 가 바로 실행된다. 서버 `server.xml` 의 `autoDeploy` 설정 확인이 필요하다.
- 조치: 확장자·매직바이트 화이트리스트, UUID 파일명, 업로드 루트를 웹 루트 밖(`/usr/local/tomcat/upload` 등)으로, `gbn` 은 허용 목록으로 제한.

### C-4. 결제 콜백 파라미터만으로 아무 회원 세션 생성
- [EduMarineController.java:2727-2740](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:2727), [2771-2787](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:2771): `/mypage/eduApplyInfo.do` 에 `resultCode`(V801 아님) 와 `merchantData=x||<회원ID>` 또는 `P_STATUS`+`P_NOTI` 를 보내면 이니시스 검증 전에 `session.setAttribute("id", userId)`, `status=logon` 을 실행한다. 비밀번호 없이 임의 회원으로 로그인된다.
- 조치: 세션 복구를 제거하거나, 이니시스 승인 API 응답(`resultCode=0000` + 서명 검증) 성공 뒤에만, 그리고 주문번호(oid)로 DB 의 결제 요청 레코드와 대조한 뒤에만 세션을 만든다.

### C-5. 비밀번호 초기화가 계정 탈취 경로
- [EduMarineServiceImpl.java:349-380](../src/main/java/com/mtf/edumarine/service/impl/EduMarineServiceImpl.java:349): `/member/initPassword.do` 는 ID 만 받아 `UPDATE member ... WHERE id = #{id}` ([EduMarineMapper.xml:444](../src/main/resources/mapper/EduMarineMapper.xml:444)) 로 비밀번호를 **코드에 고정된 문자열**로 바꾼다. 초기화 안내 메일은 서버가 아니라 브라우저 JS([main.js:538-551](../src/main/resources/static/js/main.js:538))가 `/mail/send.do` 를 따로 호출한다. 즉 공격자는 메일 호출을 생략하고 ID 만 알면 그 계정의 비밀번호를 알려진 값으로 바꿔 로그인할 수 있다. 초기화된 모든 계정이 같은 비밀번호를 갖는다.
- `/member/getEmail.do` ([EduMarineController.java:230](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:230)) 는 ID 로 이메일을 돌려줘 계정 열거에 쓰인다.
- 조치: 서버에서 랜덤 임시 비밀번호 생성 → 서버가 메일 발송 → 응답에는 성공 여부만. 다음 로그인 시 변경 강제(`passwordChangeYn` 필드가 이미 있음).

### C-6. 비밀값이 git 과 소스에 들어 있음
- `src/main/resources/application.properties` 가 git 에 추적된다(최초 커밋 2023-11-20 부터, 원격 GitHub 에 푸시됨). DB root 비밀번호와 `#putty ...` 주석(서버 접속 비밀번호로 보임)이 들어 있다. `.gitignore` 에 이 파일이 없다.
- [MySQLConnectionTest.java](../src/test/java/dbTest/MySQLConnectionTest.java): DB 공인 IP + root 비밀번호 하드코딩. `application.properties` 의 로컬용 주석 URL 도 같은 공인 IP 3306 포트를 가리킨다. 즉 운영 DB 가 인터넷에서 root 로 접속 가능한 상태로 보인다.
- 코드 내 하드코딩: 이니시스 mid/signKey [EduMarineController.java:5063](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:5063), [5145](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:5145), [4124](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:4124); 이니시스 취소 API key/iv [EduMarineMngServiceImpl.java:8606](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:8606), [8697-8701](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:8697); DirectSend 메일 계정/키 [EduMarineMngServiceImpl.java:9586](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:9586) 부근; 알리고 SMS 계정/키 [CommServiceImpl.java:75](../src/main/java/com/mtf/edumarine/service/impl/CommServiceImpl.java:75), [175](../src/main/java/com/mtf/edumarine/service/impl/CommServiceImpl.java:175); [EduMarineApplication.java:25](../src/main/java/com/mtf/edumarine/EduMarineApplication.java:25) 주석에 평문 비밀번호 예시.
- 조치: 이미 노출된 것으로 보고 DB·서버·이니시스·알리고·DirectSend 비밀값 전부 교체. `application.properties` 를 git 에서 제거하고 `.gitignore` 추가(히스토리 정리는 교체 뒤 선택). 키는 `@Value` 로 설정 파일에서 읽기. DB 는 전용 계정 + 접속 IP 제한.

### C-7. 관리자 비밀번호 평문, IP 제한은 클라이언트가 보낸 값으로 검사
- [EduMarineMngServiceImpl.java:72](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:72) `db_pw.equals(param_pw)`: 브라우저가 보낸 평문과 DB 값을 그대로 비교한다. 관리자 등록([8527](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:8527))도 해시하지 않는다. DB 가 유출되면 관리자 비밀번호가 바로 드러난다.
- IP 검사([70](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:70))는 브라우저가 외부 서비스(`api.ip.pe.kr`)에서 받아 JSON 으로 보낸 `ipAddress` ([mngMain.js:150-161](../src/main/resources/static/js/mngMain.js:150)) 와 비교한다. 요청 본문을 바꾸면 통과한다. 서버의 `getRemoteAddr()`(프록시면 `X-Forwarded-For`)를 써야 한다.
- 2단계 인증(문자 인증번호) 코드는 전부 주석 처리되어 있고, 인증번호 API 는 번호를 응답 `note` 에 그대로 돌려준다([CommServiceImpl.java:239](../src/main/java/com/mtf/edumarine/service/impl/CommServiceImpl.java:239)).
- 조치: BCrypt(spring-security-crypto) 해시, 로그인 실패 횟수 제한, 서버 측 IP 확인, 인증번호는 세션/DB 에 두고 서버에서 대조.

## 3. 보안 문제 (High / Medium)

### H-1. 소유자 확인 없는 조회·수정 (IDOR)
- `POST /member/seq/selectSingle.do` ([EduMarineController.java:2939](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:2939)): seq 만으로 타 회원 정보 반환. SQL [EduMarineMapper.xml:480-506](../src/main/resources/mapper/EduMarineMapper.xml:480) 이 비밀번호 해시까지 돌려준다.
- `GET /mypage/eduApplyNN_modify.do?seq=` (예: [830](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:830), [2950](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:2950)) 와 `/mypage/eduApplyNN/update.do` 22종: 신청서 seq 와 세션 회원을 대조하지 않는다.
- 통합 신청 insert ([2200-2206](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:2200)) 는 `session.getAttribute("seq")` 를 읽지만 세션에 `seq` 를 넣는 코드가 어디에도 없어 항상 null 이고, 결국 요청 본문의 `memberSeq` 를 믿는다.
- 조치: 모든 mypage 핸들러에서 세션 id → member seq 를 서버가 구해 WHERE 조건에 넣는다.

### H-2. 스케줄러 메서드가 GET URL 로도 열려 있음
- [EduMarineMngController.java:10841-10865](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:10841): `@Scheduled` 와 `@GetMapping("/sms/send/notify/train.do")`, `@GetMapping("/member/grade/update.do")` 가 같은 메서드에 붙어 있다. 누구나 URL 을 호출해 교육 안내 SMS 대량 발송을 반복시킬 수 있다(문자 비용). 스케줄러는 `@Controller` 가 아니라 별도 `scheduler` 컴포넌트로 옮기고 매핑을 제거해야 한다.

### H-3. 발송 API 공개
- `POST /sms/send.do`, `/sms/send/certNum.do`, `/sms/send/notify/sending.do`, `/mail/send.do` ([10788-10840](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:10788)) 가 무인증이다. 임의 번호·임의 주소로 임의 내용을 보낼 수 있어 SMS 비용 소진과 스팸 릴레이에 쓰인다. 메일 본문·제목도 요청값 그대로다.

### H-4. 저장형 XSS
- 커뮤니티 본문 [community_view.jsp:159](../src/main/webapp/WEB-INF/views/job/community_view.jsp:159) `${info.content}` 가 이스케이프 없이 출력되고, 저장 시 [EduMarineServiceImpl.java:998](../src/main/java/com/mtf/edumarine/service/impl/EduMarineServiceImpl.java:998), [1071](../src/main/java/com/mtf/edumarine/service/impl/EduMarineServiceImpl.java:1071) 에서 `&lt;`/`&gt;` 를 `<`/`>` 로 되돌린다. 회원이 올린 글에 스크립트가 들어가면 다른 회원·관리자 브라우저에서 실행된다(관리자 상세 화면도 같은 내용을 보여줌). 제목·작성자([140](../src/main/webapp/WEB-INF/views/job/community_view.jsp:140), [144](../src/main/webapp/WEB-INF/views/job/community_view.jsp:144))도 같다. 댓글만 `c:out` 으로 처리되어 있다.
- 공지·보도자료·뉴스레터 본문은 관리자 입력이므로 위험이 낮지만, C-1 로 관리자 API 가 열려 있는 동안은 같은 경로가 된다.
- 조치: Quill 출력물을 서버에서 화이트리스트 기반으로 정화(jsoup `Safelist.basicWithImages`), 그 외 필드는 `c:out`.

### H-5. CSRF 무방비와 쿠키 SameSite=None
- CSRF 토큰이 어디에도 없다. [CookieAttributeFilter.java:244](../src/main/java/com/mtf/edumarine/util/CookieAttributeFilter.java:244) 가 모든 `Set-Cookie` 에 `Secure; SameSite=None` 을 붙여 세션 쿠키가 타 사이트 요청에도 실린다(이니시스 결제 복귀를 위해 넣은 것으로 보임). GET 으로 열린 H-2 와 폼 업로드(C-3) 는 외부 페이지에서 바로 트리거된다.
- 이 필터는 `@WebFilter` 만 있고 `@ServletComponentScan` 이 없어 외장 Tomcat(WAR) 에서는 서블릿 스캔으로 활성, 로컬 `bootRun` 에서는 미등록이다. 로컬과 운영의 쿠키 동작이 다르다.
- 조치: 결제 복귀 URL 에만 필요한 범위로 줄이고(Tomcat `CookieProcessor` 또는 특정 경로), 상태 변경 API 는 토큰 또는 커스텀 헤더 검사.

### H-6. 운영 로그에 DB 결과 전체 기록
- [application.properties](../src/main/resources/application.properties): `logging.level.jdbc.resultsettable=INFO`, `jdbc.sqltiming=INFO`. 모든 SELECT 결과(비밀번호 해시·개인정보)가 Tomcat 로그 파일에 남는다. 운영은 `OFF`.

### H-7. 지원 종료 스택과 오래된 라이브러리
- Spring Boot 2.7.13 (OSS 지원 2023-11 종료), Java 8. 정책상 S 등급은 legacy 스택 불가.
- `commons-fileupload 1.4` (CVE-2023-24998 DoS) 는 코드에서 전혀 쓰지 않아 제거 가능. `cos.jar`(2002년 O'Reilly 라이브러리, 유지보수 없음), `json-simple 1.1.1`, `INIpaySample_v1.3.jar` 가 `WEB-INF/lib` 로 수동 포함. POI 4.1.2, gson 2.8.9, httpclient 4.5.13 도 구버전.
- 프런트: `js/jquery-1.9.1.min.js`, `js/jquery-1.11.1.js` 사본 보관(참조는 주석 안에서만), CDN `xlsx 0.15.5`, `flatpickr@latest`(버전 고정 없음).

### M-1. 세션 고정·타임아웃
- 로그인 성공 시 세션 ID 를 바꾸지 않는다([EduMarineController.java:213](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:213), [EduMarineMngServiceImpl.java:82](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:82)). 세션 타임아웃·쿠키 HttpOnly 설정이 없다(Tomcat 기본값 의존).
- HTTPS 강제는 [mngMain.js:34-40](../src/main/resources/static/js/mngMain.js:34) 의 JS 리다이렉트뿐이다.

### M-2. 회원 비밀번호 해시 방식
- 솔트 + SHA-512 1회([EduMarineServiceImpl.java:3364](../src/main/java/com/mtf/edumarine/service/impl/EduMarineServiceImpl.java:3364)). 평문은 아니지만 느린 해시가 아니다. 표준(BCrypt) 전환 시 로그인 시점 재해시 방식으로 무중단 이관 가능.

## 4. 런타임 버그 (기능 결함)

| # | 내용 | 위치 |
|---|---|---|
| B-1 | `selectSmsSendElectroList` 를 호출하지만 XML SQL 이 없다. '레저선박 해양전자장비 교육' 대상 SMS 발송 시 `BindingException` 으로 실패한다. | [EduMarineMngServiceImpl.java:8167](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:8167), [EduMarineMngMapper.java:590](../src/main/java/com/mtf/edumarine/mapper/EduMarineMngMapper.java:590) |
| B-2 | `/mng/session/kill/gbn.do` 가 컨트롤러 인스턴스 필드 `isClose` 를 공유하고 `Thread.sleep(2000)` 을 한다. 관리자 A 의 새로고침과 B 의 탭 닫기가 겹치면 서로의 세션이 끊기거나 유지된다. 요청 스레드를 2초씩 잡는다. | [EduMarineMngController.java:233-250](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:233) |
| B-3 | 통합 신청서의 `FAMTOUR_FORM` 분기가 주석 처리되어 있고 `_form_unified_famtour.jsp` 파일도 없다. 팸투어 과정을 UNIFIED 로 만들면 "잘못된 신청서 양식" 오류 화면이 뜬다. | [eduApplyUnified.jsp:196-205](../src/main/webapp/WEB-INF/views/apply/eduApplyUnified.jsp:196) |
| B-4 | `spring.servlet.multipart.enabled=false` 인데 `MultipartFile[]`(`/uploadFile.do`)과 `MultipartHttpServletRequest`(`/mng/excelUpload.do`)를 쓰는 핸들러가 있다. 멀티파트 리졸버가 없어 동작하지 않는다. 호출하는 화면도 없다(SMS 엑셀은 브라우저에서 xlsx.js 로 파싱). | [EduMarineController.java:5697](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:5697), [EduMarineMngController.java:10750](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:10750) |
| B-5 | 메인 페이지 요청마다 UPDATE 2건(방문자 통계, 교육 마감 처리)과 배너 파일 N+1 조회를 실행한다. 트래픽이 몰리면 같은 행 갱신으로 락 대기가 생긴다. 마감 처리는 스케줄러로, 통계는 비동기/배치로 옮기는 편이 낫다. | [EduMarineController.java:71-104](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:71) |
| B-6 | 서비스 메서드 406개가 전부 `@Transactional(REQUIRES_NEW)`(조회 포함). 컨트롤러가 여러 서비스를 이어 부르는 흐름(예: 통합 신청 insert 후 `processUpdateTrainApplyCnt`)은 중간 실패 시 반쯤 반영된다. 정원 체크(preCheck)와 insert 가 분리되어 동시 신청 시 정원 초과 가능. | [EduMarineController.java:2192-2225](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:2192) |
| B-7 | `inc/commonJS.jsp` 가 존재하지 않는 `js/resize/compressor.js` 를 로드한다. 다만 이 include 파일 자체를 쓰는 JSP 가 0개라 실제 영향은 없다. | [commonJS.jsp:20](../src/main/webapp/WEB-INF/views/inc/commonJS.jsp:20) |
| B-8 | `js/jui-ui.min.js`, `js/jquery.number.min.js` 가 0바이트 파일인데 `commonMngJS.jsp` 가 로드한다(역시 include 미사용). | static/js |
| B-9 | 예외를 `printStackTrace`(155곳)·`System.out`(103곳 활성, 1,151곳 주석) 로만 처리하고 로거가 없다. 운영에서 원인 추적이 어렵다. `//System.System.out.println` 오타 129줄. | 전체 |

## 5. 사용하지 않는 코드·파일 (삭제 후보)

삭제해도 동작에 영향이 없다고 판단한 것만 적었다. 정적 이미지는 DB 본문(뉴스레터·템플릿)에서 참조될 수 있어 마지막 항목처럼 따로 표시했다.

### Java
- `EduMarineMngMapper.java` 선언만 있고 SQL 없음: `getUnifiedAppSeq`, `insertUnifiedApplication`, `updateUnifiedApplicationPayStatus`(UnifiedMapper 로 이전됨), `selectFileList`(334), `selectSmsSendList`(340). `selectSmsSendElectroList`(590) 는 B-1 로 SQL 추가 필요.
- `EduMarineMngService.processSelectUnifiedApplicationList`(504), `processSelectUnifiedListCnt`(520): 호출처 없음.
- `EduMarineController.convertMap`(5752): 호출처 없음.
- DTO: `CommCodeDTO`, `KillSessionDTO`. entity: `AbstractPagingResponseVo`, `UserVO`. `Reference.xml`(pagingPrefix/Suffix) 참조 0건.
- `EduMarineApplication.java:25-69` 주석 블록(Salt/SHA512 복사본), `EduMarineMngServiceImpl` 의 `HttpSessionBindingListener` 구현(96-98, 의미 없음).
- 대형 주석 블록: `EduMarineController.java` 5246-5277, 5610-5673, `EduMarineMngServiceImpl.java` 9543-9576, 9788-9812.
- 미사용 의존성: `commons-fileupload`(build.gradle), `mybatis-spring-boot-starter-test`, `junit 4`.

### 매퍼 XML
- `EduMarineMapper.xml`: `getUnifiedAppSeq`, `insertUnifiedApplication`, `updateUnifiedApplicationPayStatus` (UnifiedMapper.xml 과 중복).
- `UnifiedMapper.xml`: `selectUnifiedApplicationCount`, `selectUnifiedApplicationSingle` (인터페이스에 없음).

### JSP
- `inc/commonJS.jsp`, `inc/commonMngJS.jsp`: include 하는 JSP 가 없다. 각 JSP 가 스크립트 태그를 직접 복제하고 있다(공통화하면 오히려 이 파일을 살려 써야 한다).
- `payment/INIstdpay_pc_return.jsp`: 뷰로 지정하는 핸들러 없음. `payment/close.jsp` 는 0바이트(`/payment/close.do` 가 빈 페이지를 반환하는 용도로 보임).
- `job/announcement_list/view.jsp` + 매핑 2개 + 관리자 announcement 7개 매핑: 전면 헤더·관리자 메뉴 모두 주석 처리(URL 직접 접근은 됨).
- `guide/guide02, guide04, guide12.jsp`: 어디서도 링크하지 않음(guide11/13/14 는 다른 페이지에서 링크).

### JS / CSS
- 참조 0건: `js/instagramapi.js`, `js/jquery-1.11.1.js`(10,308줄), `js/sido.js`, `js/tuiEditor.js`, `js/jquery.number.min.js`(0B), `eng/js/jquery.ui.touch-punch.min.js`, `js/jquery-1.9.1.min.js`(주석 안에서만).
- CSS 참조 0건: `css/jui-grid.classic.css`, `css/jui-grid.dark.css`, `css/jui-ui.classic.css`, `css/main.css`.
- `js/jui-core.js`, `js/jui-grid.js`, `js/jui-ui.min.js` 는 `commonMngJS.jsp`(미사용)에서만 로드 → 사실상 미사용.

### 정적 파일 (확인 후 삭제)
- 코드·JSP·CSS·JS 어디에서도 파일명이 나오지 않는 파일 126개, 약 36MB. 상위: `file/edumarine_brochure.pdf`(14MB), `file/2024_*.pdf`, `file/2025_*.pdf`, `img/img_main_slide_01.png`, `img/mailing/newsletter*.jpg`, `img/thumbnail_*.png/.jpg` 구버전.
- `img/mailing/*` 와 `file/*.pdf` 는 DB 에 저장된 뉴스레터 본문·외부 공유 링크에서 쓰일 수 있으므로 서버 접근 로그로 확인한 뒤 정리. 14MB PDF 는 WAR 크기와 배포 시간에 직접 영향을 준다.
- `src/main/webapp/.well-known/pki-validation/*.txt`, `static/.well-known/pki-validation/gsdv.txt`: 인증서 발급 검증용 잔재.

### 레거시 과정 모듈 (당장은 삭제 불가)
- `eduApply02~22` 신청 페이지 21개, `mypage/eduApplyNN_modify` 22개, 관리자 `customer/*` 19종(JSP 38개, JS 19개, 컨트롤러·서비스·매퍼 각 19벌)은 2026-01 UNIFIED 전환 이후 신규 교육에는 쓰지 않는다([schedule.js:245-248](../src/main/resources/static/js/front/schedule.js:245) 에서 UNIFIED 면 경로를 덮어씀). 그러나 과거 신청 데이터 조회·수정·엑셀은 아직 이 모듈에 의존한다. 레거시 테이블 데이터를 `application_unified` 로 이관한 뒤에야 제거할 수 있다.

## 6. 꼬인 구조 (리팩토링 대상)

1. **과정별 복제 모듈 19벌.** 관리자 JS 끼리 비교하면 609줄 중 50줄, JSP 는 1,177줄 중 32줄만 다르다(highhorsepower vs highself). 전면 신청 JSP 도 390줄 중 8줄 차이. 버그 하나를 19곳에서 고쳐야 하고 실제로 B-1 처럼 한 곳이 빠졌다.
2. **관리자 컨트롤러의 54%(5,884줄)가 과정별 엑셀 export 복붙**([EduMarineMngController.java:4866-10749](../src/main/java/com/mtf/edumarine/controller/EduMarineMngController.java:4866)). 컬럼 정의만 다르다. 공통 엑셀 유틸 + 컬럼 메타로 줄일 수 있다.
3. **관리자 JSP 82개가 Metronic 레이아웃 전체(헤더·사이드바·테마 스크립트·로그인 체크)를 각자 복제.** 로그인 체크 한 줄을 바꾸려면 82곳을 고쳐야 한다. `tags/sidebarMenu.tag` 가 있는데도 페이지 뼈대는 공통화되지 않았다. 레이아웃 태그 파일 하나로 묶어야 한다.
4. **교육과정명(한글 문자열)이 라우팅 키.** 예: '고마력 선외기 정비 중급 테크니션' 이 Java 15곳, XML 6곳, JS 18곳, JSP 59곳에 흩어져 있다. `MenuService`, [schedule.js:146-240](../src/main/resources/static/js/front/schedule.js:146) switch, [EduMarineMngServiceImpl.java:8100-8170](../src/main/java/com/mtf/edumarine/service/impl/EduMarineMngServiceImpl.java:8100) switch 가 모두 이름으로 분기한다. 과정명을 바꾸면 조용히 깨진다. 코드값(`gbn` 코드) + 코드 테이블로 바꿔야 한다.
5. **컨트롤러에 섞인 책임.** 이니시스 승인·취소 로직 약 1,000줄([EduMarineController.java:4063-5050](../src/main/java/com/mtf/edumarine/controller/EduMarineController.java:4063)), 파일 IO, 엑셀 생성, 스케줄러가 컨트롤러 안에 있다. 서비스 구현은 9,820줄 한 파일이고 결제 취소 호출 블록이 76곳에 반복된다.
6. **상수·경로 하드코딩.** 업로드 루트 `/usr/local/tomcat/webapps/upload` 가 컨트롤러 여러 곳에 직접, 결제 상태 문자열('결제완료','결제대기'), 이니시스 클라이언트 IP, 초기화 비밀번호, 고정 과정명. `CommConstants` 에는 SMS 발신번호와 결과코드만 있다.
7. **다른 프로젝트 잔재.** `KibsController`, `Sipa page controller`, `GoingSool service`, NSMALL/EDSK 저작권 헤더 등 복사 흔적. 로컬 업로드 경로 주석도 Kibs 경로.
8. **환경 분기가 주석 토글.** `application.properties` 의 prod/local URL, 컨트롤러의 `/* Prod */ /* Local */` 주석 교체 방식. 로컬 실행 시 업로드가 운영 경로를 쓰려다 실패한다. `@Value` 기본값 + 프로파일로 분리해야 한다.
9. **예외 처리 패턴.** try/catch 로 삼키고 `resultCode=-1` 만 돌려주며 로그는 `System.out`. 트랜잭션은 모두 REQUIRES_NEW 라 롤백 단위가 메서드 하나다.

## 7. 운영·설정 리스크

- `.gitignore` 에 `application.properties` 가 없고, 서버 사본과 레포 사본이 같은 파일이다(C-6).
- DB 를 `root` 로 접속한다. 전용 계정 + 최소 권한이 필요하다.
- `log4jdbc` 결과셋 로깅 ON(H-6).
- 멀티파트가 꺼져 있고 업로드는 cos.jar 가 직접 파싱한다. 50MB/100MB 제한이 코드마다 다르다.
- 스케줄러 2개(`@Scheduled` 14시 SMS, 0시 등급 갱신)가 컨트롤러에 있고 중복 실행 방지가 없다. 인스턴스를 2개 띄우면 문자가 두 번 간다.
- `main.do` 가 요청마다 DB 를 갱신한다(B-5).
- WAR 에 14MB PDF 등 대용량 정적 파일이 포함된다.
- 오류 페이지 커스터마이징이 없다(Boot 기본 Whitelabel).
- 프로젝트 문서(`CLAUDE.md`, 보안 체크리스트, 배포 절차)가 없어 변경 이력과 발주사 결정이 커밋 메시지("프로젝트 수정내용 커밋")에만 남는다.

## 8. 권장 조치 순서

### 1단계. 긴급 보안 패치 (배포 1회로 묶기, 기능 변화 없음)
1. 관리자 인증 인터셉터 추가: `/mng/**`, `/file/upload.do`, `/file/upload/**`, `/mng/education/train/uploadThumb.do`, `/mail/**`, `/sms/**`, `/train/**`, `/mng/customer/train/**` (C-1, H-3).
2. 파일 읽기 경로 정규화 + 업로드 루트 밖 거부, `/board/uploadFileGet` 는 file id 기반으로 교체 (C-2).
3. 업로드 확장자·매직바이트 화이트리스트, UUID 저장, `gbn` 허용 목록 (C-3).
4. 결제 콜백의 세션 복구 제거 또는 승인 검증 뒤로 이동 (C-4).
5. 비밀번호 초기화를 서버 생성 임시 비밀번호 + 서버 메일 발송으로 (C-5).
6. `@Scheduled` 메서드의 `@GetMapping` 제거, 스케줄러 분리 (H-2).
7. mypage 핸들러 소유자 검사 (H-1), 커뮤니티 본문 정화 (H-4).
8. 로그 레벨 `resultsettable/sqltiming=OFF` (H-6).

### 2단계. 비밀값 교체와 분리
1. DB root 비밀번호, 서버 접속 비밀번호, 이니시스 키, 알리고·DirectSend 키 교체(이미 GitHub 에 올라간 상태이므로 교체가 먼저).
2. `application.properties` git 추적 해제 + `.gitignore`, 키는 `@Value` 기본값 방식으로 설정 파일에서 로드.
3. DB 외부 접속 차단(방화벽/bind-address), 전용 DB 계정.
4. 관리자 비밀번호 BCrypt 해시 + 서버 측 IP 확인 + 로그인 실패 제한 (C-7).
5. 테스트 파일의 자격증명 제거.

### 3단계. 정리
1. 5장의 죽은 코드·파일 삭제, 미사용 의존성 제거.
2. 관리자 레이아웃 태그 공통화(82개 JSP), 공통 JS include 복원.
3. 엑셀 export 공통 유틸화, 결제 취소 블록 공통화.
4. 로거(slf4j) 도입, `System.out`·`printStackTrace` 제거.
5. `CLAUDE.md`(변경 이력·알려진 이슈·하드코딩 체크리스트)와 `docs/SECURITY_CHECKLIST.md` 작성.

### 4단계. 구조 전환 (별도 일정)
1. 레거시 과정 데이터 → `application_unified` 이관 후 19벌 모듈 제거.
2. 과정명 문자열 분기 → 코드 테이블.
3. JDK 17 + Spring Boot 3.x 전환(정책상 S 등급 대상, 서버 Tomcat 버전에 따라 boot3/boot4). 사용자 결정 사항.

## 9. 검증 방법 메모

- 엔드포인트 목록: `grep -nE '@(Request|Get|Post)Mapping'` 로 컨트롤러 2개에서 544개 추출.
- 인증 검사: 관리자 컨트롤러에서 `session.getAttribute` 2건(329, 4408)뿐임을 grep 으로 확인. JSP 84개 중 82개가 `sessionScope.get('status')` 스크립트 리다이렉트만 사용.
- 매퍼 정합성: XML `id=` 와 인터페이스 메서드명을 양방향 비교. 서비스·DTO 미사용은 호출 문자열 grep.
- 뷰·정적 파일: `setViewName` 값과 JSP 파일, `src=`/`href=` 와 static 파일을 비교. 이미지는 파일명 단위로 코드 전체 grep(DB 본문은 미포함).
- 중복도: 과정별 JS/JSP 쌍을 `diff` 로 비교.
- 컴파일: JDK 8 로 `compileJava` 성공(경고 unchecked 1건).
- 확인하지 못한 것: 서버 Tomcat 설정(`webapps/upload` 자동 배포 여부, 프록시 유무), DB 포트 외부 개방 여부, GitHub 레포 공개 여부, DB 본문에서 참조하는 이미지.

## 10. 조치 현황 (2026-10-30, 1단계 적용 · 미배포)

사용자 결정: 운영 중 사이트이므로 사용자·관리자 화면 흐름과 로그인 방식은 바꾸지 않고 서버 측 검사만 추가한다. JDK·Boot·Tomcat 버전은 올리지 않는다.

| 항목 | 상태 | 비고 |
|---|---|---|
| C-1 관리자 API 무인증 | 조치 | `config/WebMvcConfig` + 인터셉터 2종. 화면은 리다이렉트, API 는 401 JSON |
| C-2 임의 파일 읽기 | 조치 | `PathSecurityUtil` 루트 밖 404 |
| C-3 업로드 경로·확장자 | 조치 | gbn 형식 검증, 실행·스크립트 확장자 저장 직후 삭제. `webapps/upload` 자동 배포 여부는 서버 확인 필요 |
| C-4 결제 콜백 세션 복구 | 조치 | 서버 서명(HMAC) 검증 후에만 복구. 결제 처리는 기존대로 |
| C-5 비밀번호 초기화 | 조치 | 임시 비밀번호 서버 생성·서버 메일 발송(템플릿 미사용) 후 DB 변경. 이메일 조회 마스킹 |
| C-6 비밀값 노출 | 부분 | `.gitignore` 추가, 테스트 파일·주석 제거, 로그 레벨 OFF. 비밀값 교체·`git rm --cached`·코드 내 키 이동·DB 외부 접속 차단은 2단계(사용자 작업) |
| C-7 관리자 비밀번호 평문 | 부분 | 해시 검증·실패 제한·세션 ID 교체 적용. 해시 저장 전환은 `security.admin-password-hash=true` 로 켜야 동작(기본 false, 롤백 안전). 서버 측 IP 검사는 로그 수집 후 전환 |
| H-1 IDOR | 조치 | 마이페이지 조회·수정·상태변경·취소 소유자 검사, insert 회원번호 세션 고정, 회원 seq 조회 축소 |
| H-2 스케줄러 GET 공개 | 조치 | 관리자 세션 필요로 변경(스케줄 자체는 그대로) |
| H-3 발송 API 공개 | 조치 | 메일·SMS 단건은 관리자 전용. 알림 SMS 는 대상 제한·서버 내용 생성·횟수 제한 |
| H-4 저장형 XSS | 조치 | jsoup 정화(저장·조회), 제목·작성자 이스케이프 |
| H-5 CSRF·SameSite | 미조치 | 결제 복귀 의존. 로그인 필수화로 노출 축소. 토큰 도입은 별도 일정 |
| H-6 결과셋 로그 | 조치 | `sqltiming`/`resultsettable` OFF |
| H-7 지원 종료 스택 | 미조치 | 사용자 결정(서버 사양). `commons-fileupload` 만 제거 |
| M-1 세션 고정 | 조치 | 로그인 성공 시 `changeSessionId` |
| M-2 회원 해시 방식 | 미조치 | 로그인 흐름 영향 없이 바꾸려면 재해시 이관 설계 필요 |
| B-1 electro SMS SQL | 조치 | XML 추가 |
| B-2 세션 종료 API 공유 필드 | 조치 | 세션별 플래그 |
| B-3 팸투어 양식 파일 없음 | 미조치 | 발주사 확인 사항(FAMTOUR_FORM 사용 여부) |
| B-4 멀티파트 비활성 핸들러 | 부분 | `/uploadFile.do` 삭제. `/mng/excelUpload.do` 는 관리자 전용으로만 남김 |
| B-5~B-8 | 미조치 | 3단계 정리 대상 |
| B-9 System.out·printStackTrace | 조치 | `@Slf4j` 로거 도입, 활성 출력 258곳 전환·삭제, 주석 출력 1,274줄 제거 (2026-10-30 추가 작업) |

검증: JDK 8 `compileJava` 성공. 로컬 실행·브라우저 확인 결과는 CLAUDE.md 변경 이력과 세션 보고 참조.

### 로컬 검증 결과 (2026-10-30, 운영 DB 직결 로컬 실행, 읽기 전용 요청만)

| 검사 | 결과 |
|---|---|
| `POST /mng/customer/member/selectList.do` 무세션 | 401 JSON (`resultCode -9`) |
| `GET /mng/main.do`, 회원 엑셀 GET 무세션 | 302 → `/mng/index.do` |
| `GET /mng/index.do` | 200 (로그인 화면 정상) |
| `/board/uploadFileGet?fileName=<절대경로>`, 루트 밖 `../` | 404 |
| `/file/download.do` 경로 조작 2종 | 404 |
| `GET /mypage/eduApply04_modify.do?seq=…` 무세션 | 302 → `/member/login.do` |
| `POST /member/seq/selectSingle.do` 존재하지 않는 seq | 200, 빈 응답 |
| `POST /file/upload.do?gbn=../ROOT` 무세션 | 401 |
| 위조 결제 복귀 POST(`merchantData=AU…\|\|없는ID`) 후 마이페이지 | 로그인 상태 아님(세션 미생성) |
| 공개 페이지 공지·커뮤니티·일정·로그인 | 200 |
| 커뮤니티 상세(정화 적용) | 200, 제목 `&` 이스케이프 확인 |
| 앱 로그 | ERROR/Exception 0건 |

호출하지 않은 것: 로그인 시도 제한, 비밀번호 초기화, 신청 상태 변경·취소(공개 쓰기 API 는 직접 호출하지 않는 운영 원칙). 코드 검토로 대신했고 배포 후 확인 포인트에 포함.
