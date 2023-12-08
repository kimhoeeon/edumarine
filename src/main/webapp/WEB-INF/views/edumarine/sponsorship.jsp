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
    <title>해양레저인력양성센터</title>

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
        <div class="sub_top sub_top_guide">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>센터소개</span>
                    <span>협력 및 후원기관</span>
                </div>
                <h2 class="sub_top_title">협력 및 후원기관</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">센터소개</div>
                <ul class="lnb">
                    <li><a href="/edumarine/introduce.do">EDU marine 소개</a></li>
                    <li><a href="/edumarine/overview.do">사업개요</a></li>
                    <li><a href="/edumarine/current.do">경기도 해양레저 현황</a></li>
                    <li><a href="/edumarine/necessity.do">해양레저 인력양성의 필요성</a></li>
                    <li class="on"><a href="/edumarine/sponsorship.do">협력 및 후원기관</a></li>
                    <li><a href="/edumarine/way.do">찾아오시는 길</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_center">
                <div class="center_wrap">

                    <!-- 협력 교육기관 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">협력 교육기관</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_img -->
                        <div class="center_info_img">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_02.png" class="img_pc">
                                <img src="<%request.getContextPath();%>/static/img/img_center_02_m.png" class="img_m">
                            </div>
                        </div>
                        <!-- //center_info_img -->
                    </div>
                    <!-- //협력 교육기관 -->

                    <!-- 협력 기업 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">협력 기업</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_img -->
                        <div class="center_info_img">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_03.png" class="img_pc">
                                <img src="<%request.getContextPath();%>/static/img/img_center_03_m.png" class="img_m">
                            </div>
                        </div>
                        <!-- //center_info_img -->
                    </div>
                    <!-- //협력 기업 -->

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