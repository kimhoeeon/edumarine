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
                    <span>Introduction</span>
                    <span>Vision</span>
                </div>
                <div class="sub_top_tit">Vision</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Vision</div>
                <ul class="tabOptSel">
                    <li><a href="/eng/introduce/greeting.do">Greeting</a></li>
                    <li><a href="/eng/introduce/history.do">History</a></li>
                    <li class="active"><a href="/eng/introduce/vision.do">Vision</a></li>
                    <li><a href="/eng/introduce/business.do">Business</a></li>
                    <li><a href="/eng/introduce/way.do">Location</a></li>
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
                        A New Leader<br>
                        in the Business Ecosystem
                    </div>
                </div>
                <div class="box">
                    <div class="small">
                        This association is the Seoul Digital Industrial Complex Business Representative Association, which houses 13,000 IT companies<br>
                        (SW, HW, manufacturing).
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //sub_bd_tit -->

    <!-- vs_img -->
    <div class="vs_img padding_b">
        <div class="inner">
            <div class="img_box radius15"><img src="<%request.getContextPath();%>/static/img/img_vision01.jpg"></div>
        </div>
    </div>
    <!-- //vs_img -->

    <!-- vs_value -->
    <div class="vs_value padding_b">
        <div class="inner">
            <div class="sub_bd_tit">
                <div class="box">
                    <div class="big">Core Value</div>
                </div>
            </div>
            <ul class="vs_value_box">
                <li>
                    <div class="box">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_vision01.png"></div>
                        <div class="txt">
                            <div class="big">Self-sustaining<br>power</div>
                            <div class="small">Strengthening corporate self-sustaining power through internal and external collaboration</div>
                        </div>
                    </div>
                    <div class="boxOn">
                        <div class="txt">
                            The association aims to contribute to corporate growth and the expansion of the competitiveness of national industrial complexes by supporting companies, universities, and government agencies to create new markets at home and abroad through mutual cooperation.
                        </div>
                    </div>
                </li>
                <li>
                    <div class="box">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_vision02.png"></div>
                        <div class="txt">
                            <div class="big">Corporate<br>growth</div>
                            <div class="small">Support sustainable growth focused on corporate expertise, creativity, and independence</div>
                        </div>
                    </div>
                    <div class="boxOn">
                        <div class="txt">
                            In particular, the association is committed to helping companies enter smart industrial complexes, smart cities, smart energy, and digital green environments.
                        </div>
                    </div>
                </li>
                <li>
                    <div class="box">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_vision03.png"></div>
                        <div class="txt">
                            <div class="big">Global<br>Infrastructure</div>
                            <div class="small">Build a global infrastructure for internal and external collaboration</div>
                        </div>
                    </div>
                    <div class="boxOn">
                        <div class="txt">
                            The association aims to contribute to overseas markets rather than domestic demand to the extent possible.
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- //vs_value -->

    <!-- vs_mission -->
    <div class="vs_mission padding_tb">
        <div class="inner">
            <div class="vs_mission_box">
                <div class="img">
                    <img src="<%request.getContextPath();%>/static/img/img_vision03_eng.png">
                </div>
                <div class="txt">
                    <div class="big">Mission</div>
                    <div class="small">
                        The Smart Industry Promotion Association aims to become a leader in the global business market through corporate growth support, industry development, and internal and external cooperation infrastructure, and to co-exist large companies, SMEs, government agencies, and universities.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //vs_mission -->


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