<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <!-- Google tag (gtag.js) -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-368L9FRD5B"></script>
    <script>   window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());
    gtag('config', 'G-368L9FRD5B'); </script>

    <meta name="google-site-verification" content="nOdgBX2kjgySRAVZjwMl-AmYg53q0GxLBOV_qEtG7jk" />

    <meta name="naver-site-verification" content="8027433e606d42e31ef9e566afbd709c593e33bc" />

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
    <title>EDU marine</title>

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

    <!-- swiper 외부 라이브러리 -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <%-- sweetalert CDN --%>
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.min.css" rel="stylesheet">

    <link href="/eng/css/reset.css" rel="stylesheet">
    <link href="/eng/css/font.css" rel="stylesheet">
    <link href="/eng/css/base.css" rel="stylesheet">
    <link href="/eng/css/style.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="/eng/css/responsive.css" rel="stylesheet">

    <%-- favicon --%>
    <link rel="apple-touch-icon" sizes="57x57" href="/eng/img/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="/eng/img/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/eng/img/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/eng/img/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/eng/img/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/eng/img/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/eng/img/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/eng/img/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="/eng/img/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/eng/img/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/eng/img/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/eng/img/favicon/favicon-16x16.png">
    <link rel="manifest" href="/eng/img/favicon/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <%-- favicon --%>

</head>

<body>

    <c:import url="header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- section -->
        <div class="main_top">
            <div class="inner">
                <div class="title">
                    The Future Leader of<br>
                    the Marine Leisure Industry, <span class="colorGr">EduMarine</span>
                </div>
                <div class="cont_box">
                    <div class="list">
                        <div class="item">
                            <div class="icon"><img src="/eng/img/icon_main_top_01.png"></div>
                            <div class="text">General Course</div>
                        </div>
                        <div class="item">
                            <div class="icon"><img src="/eng/img/icon_main_top_02.png"></div>
                            <div class="text">Advanced Course</div>
                        </div>
                        <div class="item">
                            <div class="icon"><img src="/eng/img/icon_main_top_03.png"></div>
                            <div class="text">Short-term Course</div>
                        </div>
                    </div>
                    <a href="/eng/edu/curriculum.do" class="more">READ MORE</a>
                </div>
            </div>
            <div class="bg">
                <div class="swiper_box">
                    <div class="swiper swiperMainTop">
                        <ul class="swiper-wrapper">
                            <li class="swiper-slide "><img src="/eng/img/bg_main_top_01.webp"></li>
                            <li class="swiper-slide "><img src="/eng/img/bg_main_top_02.webp"></li>
                            <li class="swiper-slide "><img src="/eng/img/bg_main_top_03.webp"></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- //section -->

        <!-- seciton -->
        <div class="main_intro padding_tb">
            <div class="inner">
                <div class="main_tit">
                    <div class="head">Introduction</div>
                    <div class="big">Gyeonggi Marine Leisure Talent Training Center</div>
                    <div class="small">
                        <span class="colorMain">EduMarine</span>, established in 2016 as the first institution of its kind in Korea,<br>
                        <span class="colorMain">is dedicated to cultivating skilled professionals for the marine leisure industry.</span><br>
                        We are committed to meeting the growing demand for technical talent in the field of marine leisure engineering.
                    </div>
                </div>
            </div>
        </div>
        <!-- //seciton -->

        <!-- section -->
        <div class="main_do padding_tb">
            <div class="inner">
                <div class="main_tit">
                    <div class="head">Why are we doing</div>
                    <div class="big">The Need for Marine Leisure Workforce Development</div>
                    <div class="small">
                        <span class="colorMain">In line with the growth trend of the marine leisure industry,</span><br>
                        it is essential to develop a workforce suited to the needs of the industry.
                    </div>
                </div>

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="number">01</div>
                        <div class="box">
                            <div class="tit">The Need for Educational Programs Related to the Marine Leisure Industry in Korea</div>
                            <div class="text">Meeting the demand for specialized professionals needed in the field to create job opportunities</div>
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="number">02</div>
                        <div class="box">
                            <div class="tit">Preparing for the revitalization of the marine leisure industry</div>
                            <div class="text">Responding to the growing demand for professional manpower in the marine leisure industry</div>
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="number">03</div>
                        <div class="box">
                            <div class="tit">Supporting safe marine leisure through the supply of high-quality manpower</div>
                            <div class="text">Training reliable personnel who have completed a verified curriculum</div>
                        </div>
                    </div>
                    <!-- //end -->
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="main_cur padding_b">
            <div class="inner">
                <div class="main_tit">
                    <div class="head">Current status of the domestic industry</div>
                    <div class="big">Current status of Marine leisure in <span class="colorGr">Gyeonggi Province</span></div>
                    <div class="small">
                        To expand the base of marine leisure and foster and develop the marine industry as a next-generation growth engine,<br>
                        <span class="colorMain">we are creating a complex industrial park equipped with infrastructure</span> for boat and<br>
                        yacht manufacturing, repair, sales, and research and development.
                    </div>
                </div>

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="bg"><img src="/eng/img/bg_main_cur_01.jpg"></div>
                        <div class="cont">
                            <div class="tit">JMIP</div>
                            <div class="text">
                                Center of the<br>
                                marine leisure industry
                            </div>
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="bg"><img src="/eng/img/bg_main_cur_02.jpg"></div>
                        <div class="cont">
                            <div class="tit">Leisure / Tourism</div>
                            <div class="text">
                                World Yacht Racing<br>
                                Competition /<br>
                                Yacht Academy
                            </div>
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="bg"><img src="/eng/img/bg_main_cur_03.jpg"></div>
                        <div class="cont">
                            <div class="tit">Basic Facilities</div>
                            <div class="text">
                                Educational Facilities /<br>
                                Mooring / Resort
                            </div>
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="bg"><img src="/eng/img/bg_main_cur_04.jpg"></div>
                        <div class="cont">
                            <div class="tit">Production Facilities</div>
                            <div class="text">
                                Design / Repair /<br>
                                Produce / R&D
                            </div>
                        </div>
                    </div>
                    <!-- //end -->
                </div>

            </div>
        </div>
        <!-- //section -->

    </div>
    <!-- //container -->

    <c:import url="footer.jsp" charEncoding="UTF-8"/>

    <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

    <script src="/eng/js/jquery-3.5.1.min.js"></script>
    <script src="/eng/js/jquery-migrate-3.3.0.min.js"></script>
    <script src="/eng/js/jquery.cookie.min.js"></script>

    <script src="/eng/js/swiper.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/eng/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>