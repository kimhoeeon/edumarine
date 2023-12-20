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

    <c:import url="../header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top sub_top_my">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>마이페이지</span>
                    <span>교육이력조회</span>
                </div>
                <h2 class="sub_top_title">교육이력조회</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">마이페이지</div>
                <ul class="lnb">
                    <li class="on"><a href="/mypage/eduApplyInfo.do">교육이력조회</a></li>
                    <li><a href="/mypage/resume.do">나의 이력서</a></li>
                    <li><a href="/mypage/post.do">나의 게시글</a></li>
                    <li><a href="/mypage/modify.do">회원 정보</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_mypage">
                <div class="my_edu_wrap">

                    <!-- sub_tab_btn -->
                    <ul class="sub_tab_btn">
                        <li class="on"><a href="/mypage/eduApplyInfo.do">교육 신청 정보</a></li>
                        <li><a href="/mypage/eduPayInfo.do">결제 내역</a></li>
                    </ul>
                    <!-- //sub_tab_btn -->

                    <!-- my_edu_list_wrap -->
                    <div class="my_edu_list_wrap">
                        <div class="sub_box_tit">
                            <div class="big">교육 신청 내역</div>
                        </div>
                        <div class="my_edu_list">
                            <ul class="list_head">
                                <li>
                                    <div class="number">번호</div>
                                    <div class="subject">교육명</div>
                                    <div class="venue">장소</div>
                                    <div class="state">신청현황</div>
                                    <div class="modify">신청서 수정</div>
                                </li>
                            </ul>
                            <ul class="list_body">
                                <li>
                                    <div class="number">3</div>
                                    <div class="subject">
                                        <a href="">교육명</a>
                                        <div class="edu_period">2023.11.03 ~ 2023.11.11</div>
                                        <div class="edu_time">00:00 ~ 00:00</div>
                                    </div>
                                    <div class="venue">경기테크노파크(안산)</div>
                                    <div class="state">교육예정</div>
                                    <div class="modify">
                                        <a href="javascript:void(0);" class="btn_cancel form_cancel_edu_btn">취소</a>
                                        <a href="/mypage/eduApply01_modify.do" class="btn_modify">수정</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="number">2</div>
                                    <div class="subject">
                                        <a href="">교육명</a>
                                        <div class="edu_period">2023.11.03 ~ 2023.11.11</div>
                                        <div class="edu_time">00:00 ~ 00:00</div>
                                    </div>
                                    <div class="venue">경기테크노파크(안산)</div>
                                    <div class="state">신청완료</div>
                                    <div class="modify">
                                        <a href="javascript:void(0);" class="btn_cancel form_cancel_edu_btn">취소</a>
                                        <a href="/mypage/eduApply01_modify.do" class="btn_modify">수정</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="number">1</div>
                                    <div class="subject">
                                        <a href="">교육명</a>
                                        <div class="edu_period">2023.11.03 ~ 2023.11.11</div>
                                        <div class="edu_time">00:00 ~ 00:00</div>
                                    </div>
                                    <div class="venue">경기테크노파크(안산)</div>
                                    <div class="state">교육중</div>
                                    <div class="modify">
                                        <a href="/mypage/eduApply01_modify.do" class="btn_modify">수정</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- //my_edu_list_wrap -->

                    <!-- my_edu_list_wrap -->
                    <div class="my_edu_list_wrap">
                        <div class="sub_box_tit">
                            <div class="big">교육 취소 내역</div>
                        </div>
                        <div class="my_edu_list">
                            <ul class="list_head">
                                <li>
                                    <div class="number">번호</div>
                                    <div class="subject">교육명</div>
                                    <div class="venue">장소</div>
                                    <div class="state">신청현황</div>
                                </li>
                            </ul>
                            <ul class="list_body">
                                <li>
                                    <div class="number">2</div>
                                    <div class="subject">
                                        <a href="">교육명</a>
                                        <div class="edu_period">2023.11.03 ~ 2023.11.11</div>
                                        <div class="edu_time">00:00 ~ 00:00</div>
                                    </div>
                                    <div class="venue">경기테크노파크(안산)</div>
                                    <div class="state">취소불가<p>(관리자문의)</p></div>
                                </li>
                                <li>
                                    <div class="number">1</div>
                                    <div class="subject">
                                        <a href="">교육명</a>
                                        <div class="edu_period">2023.11.03 ~ 2023.11.11</div>
                                        <div class="edu_time">00:00 ~ 00:00</div>
                                    </div>
                                    <div class="venue">경기테크노파크(안산)</div>
                                    <div class="state">신청취소</div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- //my_edu_list_wrap -->

                    <!-- popupCancelEdu -->
                    <div class="popup" id="popupCancelEdu">
                        <div class="popup_inner popup_form">
                            <div class="popup_box popup_form">
                                <div class="box_1">
                                    <div class="tit_box">교육 취소</div>
                                    <div class="text_box">[교육명]을 신청하셨습니다.<br>정말로 취소하시겠습니까?</div>
                                    <div class="cmnt_box"><span style="color: #C00000">10자 이상 입력해 주세요!</span></div>
                                    <div class="input_box"><input type="text" placeholder="10자 이상 입력" class="cancel_edu_reason">
                                    </div>
                                    <div class="btn_box">
                                        <a href="javascript:void(0);" class="btnSt03 btn_prev">이전</a>
                                        <a href="javascript:void(0);" class="btnSt04 btn_next">확인</a>
                                    </div>
                                </div>
                                <div class="box_2">
                                    <div class="text_box">
                                        취소 신청이 접수되었습니다.<br>
                                        담당자 승인 후 5일 내로, 취소 여부가 확정되며<br>
                                        취소 확정 여부는 추후 마이페이지를 통해<br>
                                        확인하실 수 있습니다.
                                    </div>
                                    <div class="btn_box">
                                        <a href="javascript:void(0);" class="btnSt04 btn_confirm">확인</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //popupCancelEdu -->

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

</body>
</html>