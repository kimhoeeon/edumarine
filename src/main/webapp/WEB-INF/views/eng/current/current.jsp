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
        <div class="sub_bnn current">
            <div class="inner">
                <div class="nav">
                    <span class="home">HOME</span>
                    <span>Current Situation</span>
                </div>
                <div class="tit">Current Situation</div>
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
                        <a href="/eng/current/current.do" class="active">Current Situation</a>
                        <a href="/eng/edu/curriculum.do">Education</a>
                        <a href="/eng/contact/contact.do">Contact us</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- //sub nav -->

        <!-- section -->
        <div class="cur_sec cur_int padding_tb">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Connecting Relevant Industries of Gyeonggi</div>
                    <div class="small">
                        Gyeonggi-do accounts for 1/5 of Korea’s GDP and is a growth engine of Korea with its population over 12mil.<br>
                        The province has strong auto-industry, as well as IT and fabric industry.<br>
                        Furthermore, it holds a great potential for the marine leisure business along with its 252km long coastal areas.<br>
                        The marine leisure industry is a highly value-added industry where tourism, sports<br class="etP">
                        and manufacturing industries can be combined,<br>
                        <span class="bold colorMain">which establishes Gyeonggi as the centerpiece of Northeast Asia’s marine leisure hub.</span>
                    </div>
                </div>
                <!-- //sec tit -->

                <div class="cont_box">
                    <div class="box">
                        <div class="tit">Korea(‘11)</div>
                        <div class="list">
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_01.png"></div>
                                <div class="text">
                                    Game scale<br>
                                    <span class="line">world’s <span class="big">11th</span></span>
                                </div>
                            </div>
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_02.png"></div>
                                <div class="text">
                                    Shipbuilding<br>
                                    <span class="line">world’s <span class="big">1st</span></span>
                                </div>
                            </div>
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_03.png"></div>
                                <div class="text">
                                    Major producers of<br>
                                    semi-conductors and <span class="big line">TFT-LCD</span>
                                </div>
                            </div>
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_04.png"></div>
                                <div class="text">
                                    Auto vehicle<br>
                                    <span class="line">world’s <span class="big">4th</span></span>
                                </div>
                            </div>
                            <!-- //end -->
                        </div>
                    </div>
                    <div class="box">
                        <div class="tit">Gyeonggi-do(’11)</div>
                        <div class="list">
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_05.png"></div>
                                <div class="text">
                                    <span class="line">Population <span class="big">11.24mil</span></span><br>
                                    (highest density in Korea)
                                </div>
                            </div>
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_06.png"></div>
                                <div class="text">
                                    <span class="line">Regional Production at<br> <span class="big">243tril KRW</span></span><br>
                                    (Korea’s 2nd)
                                </div>
                            </div>
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_07.png"></div>
                                <div class="text">
                                    <span class="line">Export at <span class="big">87.5bil USD</span></span><br>
                                    (Korea’s 2nd)
                                </div>
                            </div>
                            <!-- item -->
                            <div class="item">
                                <div class="icon"><img src="/eng/img/icon_cur_int_08.png"></div>
                                <div class="text">
                                    <span class="line"><span class="big">54,213</span> factories</span><br>
                                    (Korea’s largest)
                                </div>
                            </div>
                            <!-- //end -->
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="cur_sec cur_fac padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Gyeonggi Marina’s Facility</div>
                    <div class="small">
                        Gyeonggi Province is promoting large scale marina projects at Jeongok, Gimpo, Jebu, Helgot, Bang-a-meori.<br>
                        Those marinas are equipped with land/water moorages,<br>
                        <span class="bold colorMain">which prevents vessels from natural disasters such as tidal difference, typhoon</span>, etc.<br>
                        Gyeonggi bay will become the hub of the marine leisure tourism industry in the northeast<br>
                        Asia based on those five marinas which will be utilized for<br>
                        identifying new navigation route and marine tourism infrastructure
                    </div>
                </div>
                <!-- //sec tit -->

                <div class="img_box"><img src="/eng/img/img_cur_fac_top.jpg"></div>

                <div class="tab_wrap">

                    <ul class="tab_menu">
                        <li class="on" data-tab="curFac01">Jeongok Marina</li>
                        <li data-tab="curFac02">Gimpo Ara Marina</li>
                        <li data-tab="curFac03">Jebu Marina</li>
                        <li data-tab="curFac04">Bang-A Meori Marina</li>
                        <li data-tab="curFac05">Helgot Marina</li>
                    </ul>

                    <div class="cont_box">
                        <!-- tab cont -->
                        <div class="tab_cont on" id="curFac01">
                            <div class="box">
                                <div class="img"><img src="/eng/img/img_cur_fac_01.jpg"></div>
                                <div class="text">Capacity of 200 boats</div>
                            </div>
                        </div>
                        <!-- //tab cont -->
                        <!-- tab cont -->
                        <div class="tab_cont" id="curFac02">
                            <div class="box">
                                <div class="img"><img src="/eng/img/img_cur_fac_02.jpg"></div>
                                <div class="text">Capacity of 194 boats</div>
                            </div>
                        </div>
                        <!-- //tab cont -->
                        <!-- tab cont -->
                        <div class="tab_cont" id="curFac03">
                            <div class="box">
                                <div class="img"><img src="/eng/img/img_cur_fac_03.jpg"></div>
                                <div class="text">Capacity of 300 boats</div>
                            </div>
                        </div>
                        <!-- //tab cont -->
                        <!-- tab cont -->
                        <div class="tab_cont" id="curFac04">
                            <div class="box">
                                <div class="img"><img src="/eng/img/img_cur_fac_04.jpg"></div>
                                <div class="text">Capacity of 305 boats</div>
                            </div>
                        </div>
                        <!-- //tab cont -->
                        <!-- tab cont -->
                        <div class="tab_cont" id="curFac05">
                            <div class="box">
                                <div class="img"><img src="/eng/img/img_cur_fac_05.jpg"></div>
                                <div class="text">Capacity of 300 boats</div>
                            </div>
                        </div>
                        <!-- //tab cont -->
                    </div>

                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="cur_sec cur_gro padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Growth trend of the marine leisure industry</div>
                    <div class="small">It is necessary to nurture fit-talent in line with the growth of the marine leisure industry</div>
                </div>
                <!-- //sec tit -->

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="tit">Curriculum for marine leisure industry</div>
                        <div class="text">More jobs creation by nurturing talents demanded by site</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="tit">Preparation for future boom of Gyeonggi Bay and the industry</div>
                        <div class="text">Accommodation of increasing number of experts in the field.</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="tit">Quality talents, Safer marine leisure</div>
                        <div class="text">Credible talents who passed certified curriculum</div>
                    </div>
                    <!-- //end -->
                </div>

                <div class="graph_box">
                    <div class="top">Increase of marine-leisure population</div>
                    <div class="box">
                        <div class="tit">Registered vessels status </div>
                        <div class="graph"><img src="/eng/img/img_cur_gro_graph_01.png"></div>
                    </div>
                    <div class="box">
                        <div class="tit">Registered license holders status</div>
                        <div class="graph"><img src="/eng/img/img_cur_gro_graph_02.png"></div>
                    </div>
                    <div class="sc">Source: Korea Coast Guard</div>
                </div>

            </div>
        </div>
        <!-- //section -->

        <!-- section -->
        <div class="cur_sec cur_div padding_b">
            <div class="inner">
                <!-- sec tit -->
                <div class="sec_tit">
                    <div class="big">Diverse career paths as marine-leisure expert</div>
                    <div class="small">Marine-leisure engine fabrication and repair, Hull maintenance, Sales industry, Education, Marine leisure industry, etc.</div>
                </div>
                <!-- //sec tit -->

                <div class="list">
                    <!-- item -->
                    <div class="item">
                        <div class="icon"><img src="/eng/img/icon_cur_div_01.png"></div>
                        <div class="text">Engine repair shop<br>/maintenance</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="icon"><img src="/eng/img/icon_cur_div_02.png"></div>
                        <div class="text">Boat seller</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="icon"><img src="/eng/img/icon_cur_div_03.png"></div>
                        <div class="text">Boat maker</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="icon"><img src="/eng/img/icon_cur_div_04.png"></div>
                        <div class="text">Self-employment</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="icon"><img src="/eng/img/icon_cur_div_05.png"></div>
                        <div class="text">Marina</div>
                    </div>
                    <!-- item -->
                    <div class="item">
                        <div class="icon"><img src="/eng/img/icon_cur_div_06.png"></div>
                        <div class="text">Outboard engine vessel import<br>/agent</div>
                    </div>
                    <!-- //end -->
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