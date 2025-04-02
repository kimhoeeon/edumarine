<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-language" content="ko">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no" />
    <meta name="copyright" content="경기테크노파크">
    <meta name="robots" content="all">
    <meta property="og:locale" content="ko_KR">
    <meta itemprop="inLanguage" content="ko-kr">
    <meta name="resource-type" content="website">
    <meta property="og:type" content="website">

    <meta property="og:site_name" content="경기해양레저인력양성센터">
    <title>경기해양레저 인력양성센터</title>

    <meta name="title" content="경기해양레저인력양성센터">
    <meta property="og:title" content="경기해양레저인력양성센터">
    <meta name="twitter:title" content="경기해양레저인력양성센터">
    <meta name="twitter:card" content="summary">
    <meta name="twitter:url" content="https://edumarine.org/main.do">
    <meta itemprop="name" content="경기해양레저인력양성센터">
    <meta property="nate:title" content="경기해양레저인력양성센터">
    <meta property="nate:url" content="https://edumarine.org/main.do">

    <meta property="og:url" content="https://edumarine.org/main.do">
    <meta itemprop="url" content="https://edumarine.org/main.do">
    <link rel="canonical" id="canonical" href="https://edumarine.org/main.do">

    <meta name="description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta name="twitter:description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta property="og:description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta itemprop="description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta property="nate:description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">

    <meta property="og:keywords" content="경기해양레저인력양성센터, EDU marine, 에듀마린, 해상엔진, 해상엔진 교육, 선박엔진, 선박엔진 교육, 선외기, 선외기 교육, 선외기 정비 교육, 선내기, 선내기 교육, 선외기 정비 교육, 선체, 선체 교육, 선체 정비 교육, 해양레저, 해양레저 교육, 요트 교육, 요트정비 교육, 엔진정비 교육  편집 지켜보기">
    <meta name="keywords" content="경기해양레저인력양성센터, EDU marine, 에듀마린, 해상엔진, 해상엔진 교육, 선박엔진, 선박엔진 교육, 선외기, 선외기 교육, 선외기 정비 교육, 선내기, 선내기 교육, 선외기 정비 교육, 선체, 선체 교육, 선체 정비 교육, 해양레저, 해양레저 교육, 요트 교육, 요트정비 교육, 엔진정비 교육  편집 지켜보기">
    <meta property="twitter:keywords" content="경기해양레저인력양성센터, EDU marine, 에듀마린, 해상엔진, 해상엔진 교육, 선박엔진, 선박엔진 교육, 선외기, 선외기 교육, 선외기 정비 교육, 선내기, 선내기 교육, 선외기 정비 교육, 선체, 선체 교육, 선체 정비 교육, 해양레저, 해양레저 교육, 요트 교육, 요트정비 교육, 엔진정비 교육  편집 지켜보기">

    <meta name="image" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta name="twitter:image " content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta property="og:image" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta itemprop="image" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta itemprop="thumbnailUrl" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <link rel="image_src" link="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">

    <!-- 캐시를 바로 만료시킴. -->
    <meta http-equiv="Expires" content="-1" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.0) -->
    <meta http-equiv="Pragma" content="no-cache" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.1) -->
    <meta http-equiv="Cache-Control" content="no-cache" />

    <%-- favicon --%>
    <link rel="apple-touch-icon" sizes="57x57" href="/img/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="/img/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/img/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/img/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/img/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/img/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/img/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/img/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="/img/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/img/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/img/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/img/favicon/favicon-16x16.png">
    <link rel="manifest" href="/img/favicon/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <%-- favicon --%>

    <!-- swiper 외부 라이브러리 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

    <%-- sweetalert CDN --%>
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.min.css" rel="stylesheet">

    <link rel="stylesheet" href="/css/payment/style.css">
    <link rel="stylesheet" href="/css/payment/bootstrap.min.css">

    <link href="/css/reset.css" rel="stylesheet">
    <link href="/css/font.css" rel="stylesheet">
    <link href="/css/style.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="/css/responsive.css" rel="stylesheet">

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
        <div class="sub_top sub_top_edu">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="/img/icon_home_mini.png" alt="홈 아이콘"></span><span>교육신청</span><span>결제</span>
                </div>
                <h2 class="sub_top_title">결제페이지</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">

            <!-- content -->
            <div id="content" class="sub_apply">
                <div class="join_wrap form_wrap"></div>
            </div>
            <!-- //content -->
        </div>
        <!-- content_wrap -->

    </div>
    <!-- //container -->
    <c:set var="now" value="<%=new java.util.Date(new java.util.Date().getTime() + 60*60*24*1000*1)%>" />
    <c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyyMMdd235959"/></c:set>

    <form name="SendPayForm_id" id="SendPayForm_id" method="post">
        <input type="hidden" name="version" value="1.0">
        <input type="hidden" name="gopaymethod" value="Card:vbank"><%--Card:vbank--%>
        <input type="hidden" name="mid" value="${payInfo.mid}">
        <input type="hidden" name="oid" value="${payInfo.oid}">
        <input type="hidden" name="price" value="${payInfo.price}">
        <input type="hidden" name="timestamp" value="${payInfo.timestamp}">
        <input type="hidden" name="use_chkfake" value="${payInfo.useChkfake}">
        <input type="hidden" name="signature" value="${payInfo.signature}">
        <input type="hidden" name="verification" value="${payInfo.verification}">
        <input type="hidden" name="mKey" value="${payInfo.mkey}">
        <input type="hidden" name="currency" value="WON">
        <input type="hidden" name="goodname" value="[${payInfo.trainSeq}]${payInfo.goodname}">
        <input type="hidden" name="buyername" value="${payInfo.buyername}">
        <input type="hidden" name="buyertel" value="${payInfo.buyertel}">
        <input type="hidden" name="buyeremail" value="${payInfo.buyeremail}">
        <input type="hidden" name="returnUrl" value="${payInfo.siteDomain}/mypage/eduApplyInfo.do">
        <input type="hidden" name="closeUrl" value=""><%--https://${payInfo.siteDomain}/mypage/eduApplyInfo.do--%>
        <input type="hidden" name="acceptmethod" value="HPP(1):below1000:centerCd(Y):SKIN(#083274):vbank(${sysDate})">
        <input type="hidden" name="merchantData" value="${payInfo.tableSeq}">
        <%--<input type="hidden" name="payViewType" value="popup">
        <input type="hidden" name="popupUrl" value="${payInfo.siteDomain}/apply/payment.do">--%>
    </form>

    <c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/jquery-migrate-3.3.0.js"></script>
<script src="/js/jquery.cookie.min.js"></script>

<script src="/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="/js/swiper.js"></script>
<script src="/js/form.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

    <%-- 개발 --%>
    <!--테스트 JS--><%--<script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>--%>

    <%-- 운영 --%>
    <!--운영 JS>--> <script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
    <script type="text/javascript">
        $(function(){
            INIStdPay.pay('SendPayForm_id');
        })
    </script>
</c:if>
</body>
</html>