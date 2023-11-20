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
    <title>스마트산업진흥협회</title>

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

<div id="container">

    <!-- sub_top -->
    <div class="sub_top sub_top_intd">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>협회소개</span>
                    <span>주요사업</span>
                </div>
                <div class="sub_top_tit">주요사업</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">주요사업</div>
                <ul class="tabOptSel">
                    <li><a href="/introduce/greeting.do">인사말</a></li>
                    <li><a href="/introduce/history.do">연혁</a></li>
                    <li><a href="/introduce/vision.do">비전</a></li>
                    <li class="active"><a href="/introduce/business.do">주요사업</a></li>
                    <li><a href="/introduce/way.do">오시는길</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- sub_bd_tit -->
    <div class="padding_t">
        <div class="inner">
            <div class="sub_bd_tit">
                <div class="box">
                    <div class="big">
                        스마트산업진흥협회는<br>
                        대한민국 기업성장을 위하여<br>
                        다음과 같은 사업을 수행하고 있습니다.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //sub_bd_tit -->

    <!-- biz_img -->
    <div class="biz_img">
        <div class="inner">
            <div class="img_box radius15">
                <img src="<%request.getContextPath();%>/static/img/img_business01.jpg" class="pc_img">
                <img src="<%request.getContextPath();%>/static/img/img_business01_m.jpg" class="m_img">
            </div>
        </div>
    </div>
    <!-- //biz_img -->

    <!-- biz_list -->
    <div class="biz_list padding_b">
        <div class="inner">
            <ul class="biz_list_box">
                <li>
                    <div class="number">01</div>
                    <div class="txt">SMART INDUSTRY 분야에서 기업들의 국내·외 사업을 지원한다.</div>
                </li>
                <li>
                    <div class="number">02</div>
                    <div class="txt">SMART CITY 분야에서 기업들의 국내·외 사업을 지원한다.</div>
                </li>
                <li>
                    <div class="number">03</div>
                    <div class="txt">SMART GRID 분야에서 기업들의 국내·외 사업을 지원한다.</div>
                </li>
                <li>
                    <div class="number">04</div>
                    <div class="txt">DIGITAL GREEN INDUSTRY 분야에서 기업들의 국내·외 사업을 지원한다.</div>
                </li>
                <li>
                    <div class="number">05</div>
                    <div class="txt">인공지능, IoT, 로봇 등의 첨단 분야에서 기업들의 국내·외 사업을 지원한다.</div>
                </li>
                <li>
                    <div class="number">06</div>
                    <div class="txt">기타 제조업들의 해외시장 진출을 지원한다.</div>
                </li>
            </ul>
        </div>
    </div>
    <!-- biz_list -->


</div>

<c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery-1.9.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.number.min.js"></script>
<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>