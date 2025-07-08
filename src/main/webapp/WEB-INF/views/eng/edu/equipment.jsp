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
        <div class="sub_bnn edu">
            <div class="inner">
                <div class="nav">
                    <span class="home">HOME</span>
                    <span>Education</span>
                    <span>Equipment</span>
                </div>
                <div class="tit">Equipment</div>
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
                        <a href="/eng/edu/curriculum.do" class="active">Education</a>
                        <a href="/eng/contact/contact.do">Contact us</a>
                    </div>
                </div>
                <div class="menuBox menuBox2">
                    <div class="menuAct"></div>
                    <div class="menuSel">
                        <a href="/eng/edu/curriculum.do">Curriculum</a>
                        <a href="/eng/edu/equipment.do" class="active">Equipment</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- //sub nav -->

        <!-- section -->
        <div class="edu_sec edu_equ padding_tb">
            <div class="inner">
                <div class="sec_tit">
                    <div class="big">Training Equipment</div>
                </div>

                <div class="tb_wrap padding_b">
                    <div class="top_tit">
                        <div class="tit">Outboard Engines (24 units)</div>
                    </div>
                    <!-- tb box -->
                    <div class="tb_box">
                        <div class="img">
                            <img src="/eng/img/img_edu_equ_01.jpg">
                            <img src="/eng/img/img_edu_equ_02.jpg">
                            <img src="/eng/img/img_edu_equ_03.jpg">
                            <img src="/eng/img/img_edu_equ_04.jpg">
                        </div>
                        <div class="table">
                            <table>
                                <colgroup>
                                    <col>
                                    <col style="width: 22%;">
                                    <col style="width: 22%;">
                                    <col style="width: 22%;">
                                    <col style="width: 22%;">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><div class="box">Class</div></th>
                                        <th><div class="box">Key Specs.</div></th>
                                        <th><div class="box">Engine Type</div></th>
                                        <th><div class="box">Manufacturer</div></th>
                                        <th><div class="box">Model No.</div></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td rowspan="5"><div class="box bgSky">Low horse-power</div></td>
                                        <td><div class="box">15HP</div></td>
                                        <td><div class="box">mechanical</div></td>
                                        <td><div class="box">Tohatsu</div></td>
                                        <td><div class="box">M15D2</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">18HP</div></td>
                                        <td><div class="box">mechanica</div></td>
                                        <td><div class="box">Tohatsu</div></td>
                                        <td><div class="box">M18E2</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">30HP</div></td>
                                        <td><div class="box">mechanica</div></td>
                                        <td><div class="box">Tohatsu</div></td>
                                        <td><div class="box">M30H</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">40HP</div></td>
                                        <td><div class="box">mechanica</div></td>
                                        <td><div class="box">Evinrude</div></td>
                                        <td><div class="box">E40DTLSCS</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">40HP</div></td>
                                        <td><div class="box">mechanica</div></td>
                                        <td><div class="box">Yamaha</div></td>
                                        <td><div class="box">E40XWH</div></td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2"><div class="box bgSky">Mid horse-power</div></td>
                                        <td><div class="box">60HP</div></td>
                                        <td><div class="box">electric</div></td>
                                        <td><div class="box">Honda</div></td>
                                        <td><div class="box">BF60</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">60HP</div></td>
                                        <td><div class="box">electric</div></td>
                                        <td><div class="box">Yamaha</div></td>
                                        <td><div class="box">F75BET</div></td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3"><div class="box bgSky">High horse-power</div></td>
                                        <td><div class="box">200HP</div></td>
                                        <td><div class="box">electric</div></td>
                                        <td><div class="box">Honda</div></td>
                                        <td><div class="box">BF200A6</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">250HP</div></td>
                                        <td><div class="box">electric</div></td>
                                        <td><div class="box">Suzuki</div></td>
                                        <td><div class="box">DF250 APXX</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">300HP</div></td>
                                        <td><div class="box">electric</div></td>
                                        <td><div class="box">Mercury</div></td>
                                        <td><div class="box">300VER L</div></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //tb box -->
                </div>

                <div class="tb_wrap">
                    <div class="top_tit">
                        <div class="tit">Inboard Engines (6 units)</div>
                    </div>
                    <!-- tb box -->
                    <div class="tb_box">
                        <div class="img">
                            <img src="/eng/img/img_edu_equ_05.jpg">
                            <img src="/eng/img/img_edu_equ_06.jpg">
                            <img src="/eng/img/img_edu_equ_07.jpg">
                            <img src="/eng/img/img_edu_equ_08.jpg">
                        </div>
                        <div class="table">
                            <table>
                                <colgroup>
                                    <col>
                                    <col style="width: 22%;">
                                    <col style="width: 22%;">
                                    <col style="width: 22%;">
                                    <col style="width: 22%;">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><div class="box">Class</div></th>
                                        <th><div class="box">Key Specs.</div></th>
                                        <th><div class="box">Engine Type</div></th>
                                        <th><div class="box">Manufacturer</div></th>
                                        <th><div class="box">Model Name</div></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td rowspan="2"><div class="box bgSky">Electric</div></td>
                                        <td><div class="box">270HP, 6 cylinders, 3800rpm</div></td>
                                        <td><div class="box">Hyundai Seasall</div></td>
                                        <td><div class="box">s270s</div></td>
                                        <td><div class="box">-</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">370HP, 6 cylinders, 3500rpm</div></td>
                                        <td><div class="box">Volvo Penta</div></td>
                                        <td><div class="box">D6-370</div></td>
                                        <td><div class="box">-</div></td>
                                    </tr>
                                    <tr>
                                        <td rowspan="4"><div class="box bgSky">Mechanical</div></td>
                                        <td><div class="box">75HP, 4 cylinders, 3000rpm</div></td>
                                        <td><div class="box">Volvo Penta</div></td>
                                        <td><div class="box">D2-75+150S</div></td>
                                        <td><div class="box">Sail</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">700HP, 6 cylinders, 2200rpm</div></td>
                                        <td><div class="box">Yanmar</div></td>
                                        <td><div class="box">6HYM-WET</div></td>
                                        <td><div class="box">-</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">39HP, 3 cylinders, 3000rpm</div></td>
                                        <td><div class="box">Yanmar</div></td>
                                        <td><div class="box">3JH5CE+SD60-5</div></td>
                                        <td><div class="box">Sail</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box">39HP, 3 cylinders, 3000rpm</div></td>
                                        <td><div class="box">HD Hyundai Infracore</div></td>
                                        <td><div class="box">MD196TI</div></td>
                                        <td><div class="box">(former)Doosan</div></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //tb box -->
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