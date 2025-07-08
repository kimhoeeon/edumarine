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

    <!-- swiper 외부 라이브러리 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

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

    <c:import url="../header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub bnn -->
        <div class="sub_bnn contact">
            <div class="inner">
                <div class="nav">
                    <span class="home">HOME</span>
                    <span>Contact us</span>
                </div>
                <div class="tit">Contact us</div>
            </div>
        </div>
        <!-- //sub bnn -->

        <!-- sub nav -->
        <div class="sub_nav">
            <div class="inner">
                <a href="/eng/index.do" class="home"></a>
                <div class="menuBox menuBox1">
                    <div class="menuAct"></div>
                    <div class="menuSel">
                        <a href="/eng/marine/intro.do">EDU marine</a>
                        <a href="/eng/current/current.do">Current Situation</a>
                        <a href="/eng/edu/curriculum.do">Education</a>
                        <a href="/eng/contact/contact.do" class="active">Contact us</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- //sub nav -->

        <!-- section -->
        <div class="ct_sec ct_way padding_tb">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Contact us</div>
                </div>
                <!-- //sec tit -->

                <div class="tab_wrap">

                    <ul class="tab_menu">
                        <li class="on" data-tab="ctWay01">
                            Gyeonggi Techno Park<br>
                            (outboard engine)
                        </li>
                        <li data-tab="ctWay02">
                            Gimpo Ara Marina<br>
                            (inboard engine)
                        </li>
                    </ul>

                    <!-- tab cont -->
                    <div class="tab_cont on" id="ctWay01">
                        <div class="box">
                            <div class="map">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3174.158480131325!2d126.82723957628707!3d37.29137763985779!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b6ec34390e3af%3A0x4290c03d6a503954!2sGyeonggi%20Technopark!5e0!3m2!1sen!2skr!4v1733450796495!5m2!1sen!2skr" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                            </div>
                            <ul class="info">
                                <li class="w100">
                                    <div class="icon"><img src="/eng/img/icon_ct_01.png"></div>
                                    <div class="gu">Address</div>
                                    <div class="nae">705, Haean-ro, Sangnok-gu, Ansan-si, Gyeonggi-do, 15588</div>
                                </li>
                                <li>
                                    <div class="icon"><img src="/eng/img/icon_ct_02.png"></div>
                                    <div class="gu">Tel</div>
                                    <div class="nae">+82-1811-7891</div>
                                </li>
                                <li>
                                    <div class="icon"><img src="/eng/img/icon_ct_03.png"></div>
                                    <div class="gu">Fax</div>
                                    <div class="nae">+82-031-999-7880</div>
                                </li>
                                <li>
                                    <div class="icon"><img src="/eng/img/icon_ct_04.png"></div>
                                    <div class="gu">E-mail</div>
                                    <div class="nae">edu@edumarine.org</div>
                                </li>
                            </ul>
                            <a href="https://www.gtp.or.kr/index_eng.html" class="btn st1 big" target="_blank">Directions</a>
                        </div>
                    </div>
                    <!-- //tab cont -->

                    <!-- tab cont -->
                    <div class="tab_cont" id="ctWay02">
                        <div class="box">
                            <div class="map">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3161.0960444486886!2d126.78862378851612!3d37.59989855010732!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357c9b45ed345fb1%3A0xffa07a65cced89b!2s210%20Arayuk-ro%20152beon-gil%2C%20Gochon-eup%2C%20Gimpo-si%2C%20Gyeonggi-do!5e0!3m2!1sen!2skr!4v1733451152858!5m2!1sen!2skr" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                            </div>
                            <ul class="info">
                                <li class="w100">
                                    <div class="icon"><img src="/eng/img/icon_ct_01.png"></div>
                                    <div class="gu">Address</div>
                                    <div class="nae">210, Arayuk-ro 152beon-gil, Gochon-eup, Gimpo-si, Gyeonggi-do, 10135</div>
                                </li>
                                <li>
                                    <div class="icon"><img src="/eng/img/icon_ct_02.png"></div>
                                    <div class="gu">Tel</div>
                                    <div class="nae">+82-1811-7891</div>
                                </li>
                                <li>
                                    <div class="icon"><img src="/eng/img/icon_ct_03.png"></div>
                                    <div class="gu">Fax</div>
                                    <div class="nae">+82-031-999-7880</div>
                                </li>
                                <li>
                                    <div class="icon"><img src="/eng/img/icon_ct_04.png"></div>
                                    <div class="gu">E-mail</div>
                                    <div class="nae">edu@edumarine.org</div>
                                </li>
                            </ul>
                            <a href="https://www.kwateromc.co.kr/www/contents.do?key=797" class="btn" target="_blank">Directions</a>
                        </div>
                    </div>
                    <!-- //tab cont -->

                </div>

            </div>
        </div>
        <!-- //section -->



    </div>
    <!-- //container -->

    <c:import url="../footer.jsp" charEncoding="UTF-8"/>

    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

    <script src="/eng/js/jquery-3.5.1.min.js"></script>
    <script src="/eng/js/jquery-migrate-3.3.0.min.js"></script>
    <script src="/eng/js/jquery.cookie.min.js"></script>

    <script src="/eng/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>