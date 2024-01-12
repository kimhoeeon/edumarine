<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="format-detection" content="telephone=no" />
    <title>경기해양레저 인력양성센터</title>

    <!-- 캐시를 바로 만료시킴. -->
    <meta http-equiv="Expires" content="-1" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.0) -->
    <meta http-equiv="Pragma" content="no-cache" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.1) -->
    <meta http-equiv="Cache-Control" content="no-cache" />

    <!-- swiper 외부 라이브러리 -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <%-- sweetalert CDN --%>
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.min.css" rel="stylesheet">

    <link href="<%request.getContextPath();%>/static/css/reset.css" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/font.css" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/style.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/responsive.css" rel="stylesheet">
</head>

<body>

<c:if test="${status ne 'logon'}">
    <script>
        alert("로그인해 주세요.");
        location.href = '/member/login.do';
    </script>
</c:if>

<c:if test="${status eq 'logon'}">

    <c:import url="../header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top sub_top_my">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>마이페이지</span>
                    <span>회원 정보</span>
                </div>
                <h2 class="sub_top_title">회원 정보</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">마이페이지</div>
                <ul class="lnb">
                    <li><a href="/mypage/eduApplyInfo.do">교육이력조회</a></li>
                    <li><a href="/mypage/resume.do">나의 이력서</a></li>
                    <li><a href="/mypage/post.do">나의 게시글</a></li>
                    <li class="on"><a href="/mypage/modify.do">회원 정보</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_mypage">
                <div class="join_wrap form_wrap">

                    <!-- form box -->
                    <form id="joinForm" method="post" onsubmit="return false;">
                        <div class="form_box">
                            <div class="form_tit">
                                <div class="big">회원정보 수정</div>
                            </div>
                            <ul class="form_list">

                                <li>
                                    <div class="gubun req"><p>아이디</p></div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="text" id="id" name="id" class="w50" value="${info.id}" readonly>
                                        </div>
                                        <div class="cmnt">영문, 숫자 혼용 가능(5~12자리) 이내</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>비밀번호</p></div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="password" id="password" name="password" value="${info.password}" maxlength="16" onchange="f_pw_status_change(this)" placeholder="비밀번호 입력" class="w50">
                                            <button onclick="f_pw_check(this)">비밀번호 검사</button>
                                            <span class="pw_valid_result_cmnt"><%--비밀번호 검사 버튼을 클릭해 주세요.--%></span>
                                            <input type="hidden" id="pwCheck" value="true">
                                        </div>
                                        <div class="cmnt">9~16자의 영문, 숫자, 특수문자를 사용해주세요. </div>
                                        <div class="cmnt">비밀번호 유효성 검사 버튼을 클릭해주세요.</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>비밀번호 확인</p></div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="password" id="passwordCheck" maxlength="16" placeholder="비밀번호 입력" class="w50">
                                            <span class="pw_check_valid_result_cmnt" style="color: #AD1D1D">비밀번호가 일치하지 않습니다.</span>
                                            <input type="hidden" id="pwConfirmCheck" value="false">
                                        </div>
                                        <div class="cmnt">비밀번호를 다시 입력해주세요.</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>이름</p></div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="text" id="name" name="name" value="${info.name}" placeholder="이름 입력" class="w50">
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>연락처</p></div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="text" id="phone" name="phone" value="${info.phone}" maxlength="13" placeholder="하이픈 자동 입력" class="onlyTel w50">
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>이메일 주소</p></div>
                                    <div class="naeyong">
                                        <div class="input form_email">
                                            <input type="text" id="email" name="email" value="${fn:split(info.email,'@')[0]}" placeholder="이메일 입력" class="email_input_1">
                                            <span>@</span>
                                            <input type="text" id="domain" name="domain" value="${fn:split(info.email,'@')[1]}" placeholder="도메인 입력" class="email_input_2">
                                            <select class="email_select">
                                                <c:set var="domain" value="${fn:split(info.email,'@')[1]}"/>
                                                <option selected>직접입력</option>
                                                <option value="daum.net" <c:if test="${domain eq 'daum.net'}">selected</c:if> >daum.net</option>
                                                <option value="nate.com" <c:if test="${domain eq 'nate.com'}">selected</c:if> >nate.com</option>
                                                <option value="hanmail.net" <c:if test="${domain eq 'hanmail.net'}">selected</c:if> >hanmail.net</option>
                                                <option value="naver.com" <c:if test="${domain eq 'naver.com'}">selected</c:if> >naver.com</option>
                                                <option value="gmail.com" <c:if test="${domain eq 'gmail.com'}">selected</c:if> >gmail.com</option>
                                                <option value="hotmail.com" <c:if test="${domain eq 'hotmail.com'}">selected</c:if> >hotmail.com</option>
                                                <option value="yahoo.co.kr" <c:if test="${domain eq 'yahoo.co.kr'}">selected</c:if> >yahoo.co.kr</option>
                                                <option value="empal.com" <c:if test="${domain eq 'empal.com'}">selected</c:if> >empal.com</option>
                                                <option value="korea.com" <c:if test="${domain eq 'korea.com'}">selected</c:if> >korea.com</option>
                                                <option value="hanmir.com" <c:if test="${domain eq 'hanmir.com'}">selected</c:if> >hanmir.com</option>
                                                <option value="dreamwiz.com" <c:if test="${domain eq 'dreamwiz.com'}">selected</c:if> >dreamwiz.com</option>
                                                <option value="orgio.net" <c:if test="${domain eq 'orgio.net'}">selected</c:if> >orgio.net</option>
                                                <option value="korea.com" <c:if test="${domain eq 'korea.com'}">selected</c:if> >korea.com</option>
                                                <option value="hitel.net" <c:if test="${domain eq 'hitel.net'}">selected</c:if> >hitel.net</option>
                                            </select>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>관심 키워드</p></div>
                                    <div class="naeyong">
                                        <div class="input form_keyword">
                                            <label><input type="checkbox" name="keyword" value="선내기" <c:if test="${fn:contains(info.keyword, '선내기')}">checked</c:if> />선내기</label>
                                            <label><input type="checkbox" name="keyword" value="선외기" <c:if test="${fn:contains(info.keyword, '선외기')}">checked</c:if> />선외기</label>
                                            <label><input type="checkbox" name="keyword" value="마리나선박" <c:if test="${fn:contains(info.keyword, '마리나선박')}">checked</c:if> />마리나선박</label>
                                            <label><input type="checkbox" name="keyword" value="정비" <c:if test="${fn:contains(info.keyword, '정비')}">checked</c:if> />정비</label>
                                            <label><input type="checkbox" name="keyword" value="이직/커리어" <c:if test="${fn:contains(info.keyword, '이직/커리어')}">checked</c:if> />이직/커리어</label>
                                            <label><input type="checkbox" name="keyword" value="여행" <c:if test="${fn:contains(info.keyword, '여행')}">checked</c:if> />여행</label>
                                            <label><input type="checkbox" name="keyword" value="해양레저이슈" <c:if test="${fn:contains(info.keyword, '해양레저이슈')}">checked</c:if> />해양레저이슈</label>
                                            <label><input type="checkbox" name="keyword" value="취미" <c:if test="${fn:contains(info.keyword, '취미')}">checked</c:if> />취미</label>
                                            <label><input type="checkbox" name="keyword" value="취업" <c:if test="${fn:contains(info.keyword, '취업')}">checked</c:if> />취업</label>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </form>
                    <!-- form box -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">개인정보수집 및 SMS 알림서비스 동의</div>
                        </div>
                        <div class="form_privacy">
                            <div class="form_privacy_inner">

                                <!-- 개인정보 수집 동의 -->
                                <div class="box">
                                    <div class="tit">개인정보 수집 동의</div>
                                    <div class="text1">
                                        경기해양레저인력양성센터는 회원에게 해양레저 전문인력 양성교육 서비스와 회원관리서비스,
                                        그리고 보다 다양한 서비스 제공을 위하여 아래와 같이 회원의 개인정보를 수집, 활용합니다.
                                    </div>
                                    <div class="table">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>목적</th>
                                                    <th>항목</th>
                                                    <th>보유기간</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>이용자 식별 및 본인여부 확인</td>
                                                    <td>
                                                        - 성명, 휴대전화번호, 전자우편주소, 아이디, 비밀번호 : 회원가입시 수집<br>
                                                        - 생년월일, 성별, 연계정보(CI), 중복가입확인정보(DI) : 본인 인증시 수집
                                                    </td>
                                                    <td rowspan="2">
                                                        회원탈퇴 후 파기됩니다.<br>
                                                        다만 관계법령에 의해 보존할 경우 그 의무기간 동안 별도 보관되며 불편법 행위의 방지 및 대응의 목적으로 5년간
                                                        별도보관됩니다.
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>부정이용방지, 비인가 사용 방지, 분쟁조정 해결을 위한 기록보존</td>
                                                    <td>IP Address, 방문일시, 서비스 이용기록(자동으로 생성되는 개인정보)</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="text2">
                                        * 본 수집동의서 상의 용어의 정의는 "경기해양레저인력양성센터 이용약관 및 개인정보처리방침"에 준용하며
                                        경기해양레저인력양성센터 서비스 제공을 위해서 필요한 최소한의 개인정보이므로 동의를 해주셔야만
                                        서비스를 이용 하실 수 있습니다.
                                    </div>
                                </div>
                                <!-- //개인정보 수집 동의 -->

                                <!-- SMS 선택 동의 -->
                                <div class="box">
                                    <div class="tit">SMS 선택 동의</div>
                                    <div class="text1">
                                        경기해양레저인력양성센터는 회원에게 해양레저 전문인력 양성교육 서비스와 회원관리서비스,
                                        그리고 보다 다양한 서비스 제공을 위하여 아래와 같이 회원의 개인정보를 수집, 활용합니다.
                                    </div>
                                    <div class="table">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>목적</th>
                                                    <th>항목</th>
                                                    <th>보유기간</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>관심 키워드 알림 서비스, 정보제공, 홍보 활동, 교육안내 등의 고지를 위한 목적</td>
                                                    <td>
                                                        연락처, SMS수신여부, 성명, 생년월일
                                                    </td>
                                                    <td>
                                                        회원탈퇴 후 파기됩니다.
                                                        다만 관계법령에 의해 보존할 경우 그 의무기간 동안 별도 보관되며 불편법 행위의 방지 및 대응의 목적으로 5년간
                                                        별도보관됩니다.
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="text2">
                                        * 본 수집동의서 상의 용어의 정의는 "경기해양레저인력양성센터 이용약관 및 개인정보처리방침"에 준용하며
                                        경기해양레저인력양성센터의 보다 높은 서비스를 제공 받기 위해서는 동의해주셔야 합니다.
                                        (동의하지 아니한 경우에도 경기해양레저인력양성센터 서비스를 이용하실 수 있습니다.)
                                    </div>
                                </div>
                                <!-- //SMS 선택 동의 -->

                            </div>
                        </div>
                        <div class="form_privacy_check">
                            <p>
                                <label>
                                    <input type="checkbox" class="f_privcy_chk_all" <c:if test="${info.smsYn eq '1'}">checked</c:if> />개인정보수집 및 선택 사항에 모두 동의합니다.
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input type="checkbox" id="f_privcy_essential" class="f_privcy_chk" checked />
                                    <span style="color: #C00000;">(필수)</span> 개인정보수집 동의
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input type="checkbox" id="smsYn" name="smsYn" class="f_privcy_chk" <c:if test="${info.smsYn eq '1'}">checked</c:if> />
                                    (선택) SMS 알림서비스 동의
                                </label>
                            </p>
                        </div>
                    </div>
                    <!-- //form box -->
                    <div class="form_btn_box">
                        <a href="javascript:void(0);" onclick="f_main_member_modify();" class="btnSt01">저장하기</a>
                    </div>

                    <div class="form_delete_id">
                        <a href="javascript:void(0);" class="btnSt03 form_delete_id_btn">탈퇴하기</a>
                    </div>

                    <!-- popupDeleteId -->
                    <div class="popup" id="popupDeleteId">
                        <div class="popup_inner popup_form">
                            <div class="popup_box popup_form">
                                <div class="tit_box">탈퇴하기</div>
                                <div class="text_box">더 나은 서비스 제공을 위해<br>탈퇴 사유를 입력해 주세요</div>
                                <div class="cmnt_box"><span style="color: #C00000">10자 이상 입력해 주세요!</span></div>
                                <div class="input_box"><input type="text" placeholder="10자 이상 입력" class="delete_id_reason">
                                </div>
                                <div class="btn_box">
                                    <a href="javascript:void(0);" class="btnSt03 btn_cancel">취소</a>
                                    <a href="javascript:void(0);" class="btnSt04 btn_confirm">확인</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //popupDeleteId -->

                </div>
            </div>
            <!-- //content -->

        </div>
        <!-- content_wrap -->

    </div>
    <!-- //container -->

    <c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

<script src="<%request.getContextPath();%>/static/js/jquery-3.6.0.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery-migrate-3.3.0.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>

<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/form.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>
</c:if>
</body>
</html>