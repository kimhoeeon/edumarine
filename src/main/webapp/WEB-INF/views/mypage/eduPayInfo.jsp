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
                        <li><a href="/mypage/eduApplyInfo.do">교육 신청 정보</a></li>
                        <li class="on"><a href="/mypage/eduPayInfo.do">결제 내역</a></li>
                    </ul>
                    <!-- //sub_tab_btn -->

                    <!-- my_pay_list_wrap -->
                    <div class="my_pay_list_wrap">
                        <div class="sub_box_tit">
                            <div class="big">결제내역</div>
                        </div>
                        <div class="my_pay_list">
                            <ul class="list_head">
                                <li>
                                    <div class="number">번호</div>
                                    <div class="subject">과정명</div>
                                    <div class="date">신청일(결제일)</div>
                                    <div class="method">결제 방법</div>
                                    <div class="state">결제 상태</div>
                                </li>
                            </ul>
                            <ul class="list_body">
                                <c:if test="${not empty paymentList}">
                                    <c:forEach var="info" items="${paymentList}" begin="0" end="${paymentList.size()}" step="1" varStatus="status">
                                        <li>
                                            <div class="number">${info.rownum}</div>
                                            <div class="subject">
                                                <a href="">${info.trainName}</a>
                                                <div class="payment">
                                                    <fmt:formatNumber value="${info.totPrice}" type="currency" maxFractionDigits="0" currencySymbol="￦ "/> 원
                                                </div>
                                            </div>
                                            <div class="date">
                                                <fmt:parseDate var="dateString" value="${fn:split(info.finalRegiDttm,' ')[0]}" pattern="yyyy-MM-dd" />
                                                <fmt:formatDate value="${dateString}" pattern="yyyy.MM.dd" />
                                            </div>
                                            <div class="method">${info.cardPurchaseName} (${info.cardNum})</div>
                                            <div class="state">${info.payStatus}</div>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty paymentList}">
                                    <li>
                                        결제내역 없음
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <!-- //my_pay_list_wrap -->

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