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
        <div class="sub_bnn marine">
            <div class="inner">
                <div class="nav">
                    <span class="home">HOME</span>
                    <span>EDU marine</span>
                </div>
                <div class="tit">EDU marine</div>
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
                        <a href="/eng/marine/intro.do" class="active">EDU marine</a>
                        <a href="/eng/current/current.do">Current Situation</a>
                        <a href="/eng/edu/curriculum.do">Education</a>
                        <a href="/eng/contact/contact.do">Contact us</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- //sub nav -->

        <!-- section -->
        <div class="mar_sec mar_int padding_tb">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Introduction of EDU marine</div>
                    <div class="small">
                        Korea Marine Education Academy is Korea’s first ever marine education institute established by Gyeonggi in 2016.<br>
                        As the demand for marine leisure engineers increases,
                        the institute provides marine leisure engineering course to nurture technicians and match those talents with companies.
                    </div>
                </div>
                <!-- //sec tit -->
            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="mar_sec mar_sum padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Project Outline</div>
                </div>
                <!-- //sec tit -->

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="gu">Scope</div>
                        <div class="nae">Those who want to be hired or start one’s own business after completing marine leisure technician course.
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="gu">Details</div>
                        <div class="nae">Establishing marine leisure technician education infrastructure, training course, job seeking support, cooperation network within marine leisure industry.</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="gu">Expected to</div>
                        <div class="nae">
                            <div class="p">
                                Lead the effort to create quality youth jobs for manufacturing/service sector, by building specialized nurturing system.
                            </div>
                            <div class="p">
                                Lay the foundation of a safe marine leisure environment by providing certified technicians.
                            </div>
                        </div>
                    </div>
                    <!-- //end -->
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="mar_sec mar_pur padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Project Purpose</div>
                    <div class="small">
                        We launch ”Youth Job Creations through Gyeonggi Marine Leisure” project, to nurture skilled talents and match them with businesses,
                        as it is expected that the demand for specialized technicians grows.
                    </div>
                </div>
                <!-- //sec tit -->

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="box">
                            <div class="icon"><img src="/eng/img/icon_mar_pur_01.png"></div>
                            <div class="tit">Short-term</div>
                        </div>
                        <div class="text">
                            Job matching through engine<br>
                            technician Intro. Courses
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="box">
                            <div class="icon"><img src="/eng/img/icon_mar_pur_02.png"></div>
                            <div class="tit">Mid-term</div>
                        </div>
                        <div class="text">
                            Maintenance personnel service,<br>
                            quality enhancement
                        </div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="box">
                            <div class="icon"><img src="/eng/img/icon_mar_pur_03.png"></div>
                            <div class="tit">Long-term</div>
                        </div>
                        <div class="text">
                            Highly-skilled technicians<br>
                            who can be grafted with<br>
                            R&D system
                        </div>
                    </div>
                    <!-- //end -->
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="mar_sec mar_dir padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Project Direction</div>
                </div>
                <!-- //sec tit -->

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="gu">01</div>
                        <div class="nae">Establishing first-ever marine leisure training curriculum and infrastructure in Korea/Gyeonggi</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="gu">02</div>
                        <div class="nae">Creating quality jobs which require workers to acquire high-level, specialized license</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="gu">03</div>
                        <div class="nae">Expanding the demand base by coordinating marine leisure manufacturing and service sectors</div>
                    </div>
                    <!-- //end -->
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="mar_sec mar_gov padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Governance Structure</div>
                </div>
                <!-- //sec tit -->

                <div class="gov_box">
                    <!-- box -->
                    <div class="box gg">
                        <div class="topLogo"><img src="/eng/img/logo_gov_gg.png"></div>
                        <div class="line"></div>
                        <div class="list">
                            <div class="item manage">
                                <div class="tit">Management Body</div>
                                <div class="nae">
                                    <div class="logo"><img src="/eng/img/logo_gov_kwater.png"></div>
                                    <div class="text">
                                        <p>Hull Maintenance Training</p>
                                        <p>Inboard Engine Maintenance Training</p>
                                        <p>Outboard Engine Maintenance Training</p>
                                        <p>Organized by the Manpower</p>
                                        <p>Development Support Council</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- box -->
                    <div class="box edum">
                        <div class="topLogo"><img src="/eng/img/logo_gov_edum.png"></div>
                        <div class="line"></div>
                        <div class="list">
                            <div class="item partnes">
                                <div class="tit">Partnes</div>
                                <div class="nae">
                                    <div class="logo">
                                        <img src="/eng/img/logo_gov_partnes_01.png">
                                        <img src="/eng/img/logo_gov_partnes_02.png">
                                        <img src="/eng/img/logo_gov_partnes_03.png">
                                    </div>
                                </div>
                            </div>
                            <div class="item support">
                                <div class="tit">Support Committee</div>
                                <div class="nae">
                                    <div class="logo">
                                        <img src="/eng/img/logo_gov_support_01.png">
                                        <img src="/eng/img/logo_gov_support_02.png">
                                        <img src="/eng/img/logo_gov_support_03.png">
                                        <img src="/eng/img/logo_gov_support_04.png">
                                        <img src="/eng/img/logo_gov_support_05.png">
                                        <img src="/eng/img/logo_gov_support_06.png">
                                        <img src="/eng/img/logo_gov_support_07.png">
                                        <img src="/eng/img/logo_gov_support_08.png">
                                        <img src="/eng/img/logo_gov_support_09.png">
                                        <img src="/eng/img/logo_gov_support_10.png">
                                        <img src="/eng/img/logo_gov_support_11.png">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //end -->
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="mar_sec mar_cen padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Related Organizations</div>
                </div>
                <!-- //sec tit -->

                <!-- tab menu -->
                <ul class="tab_menu">
                    <li class="on" data-tab="marCen01">Sponsoring Organizations</li>
                    <li data-tab="marCen02">Partner Educational Institutions</li>
                    <li data-tab="marCen03">Partner Companies</li>
                </ul>
                <!-- //tab menu -->

                <!-- tab cont -->
                <div class="tab_cont on" id="marCen01">
                    <div class="box">
                        <img src="/eng/img/logo_cen_01_01.png">
                        <img src="/eng/img/logo_cen_01_02.png">
                        <img src="/eng/img/logo_cen_01_03.png">
                        <img src="/eng/img/logo_cen_01_04.png">
                        <img src="/eng/img/logo_cen_01_05.png">
                        <img src="/eng/img/logo_cen_01_06.png">
                        <img src="/eng/img/logo_cen_01_07.png">
                        <img src="/eng/img/logo_cen_01_08.png">
                    </div>
                </div>
                <!-- //tab cont -->

                <!-- tab cont -->
                <div class="tab_cont" id="marCen02">
                    <div class="box">
                        <img src="/eng/img/logo_cen_02_01.png">
                        <img src="/eng/img/logo_cen_02_02.png">
                    </div>
                </div>
                <!-- //tab cont -->

                <!-- tab cont -->
                <div class="tab_cont" id="marCen03">
                    <div class="box">
                        <img src="/eng/img/logo_cen_03_01.png">
                        <img src="/eng/img/logo_cen_03_02.png">
                        <img src="/eng/img/logo_cen_03_03.png">
                        <img src="/eng/img/logo_cen_03_04.png">
                        <img src="/eng/img/logo_cen_03_05.png">
                        <img src="/eng/img/logo_cen_03_06.png">
                        <img src="/eng/img/logo_cen_03_07.png">
                        <img src="/eng/img/logo_cen_03_08.png">
                        <img src="/eng/img/logo_cen_03_09.png">
                        <img src="/eng/img/logo_cen_03_10.png">
                        <img src="/eng/img/logo_cen_03_11.png">
                        <img src="/eng/img/logo_cen_03_12.png">
                        <img src="/eng/img/logo_cen_03_13.png">
                        <img src="/eng/img/logo_cen_03_14.png">
                    </div>
                </div>
                <!-- //tab cont -->

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