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
                    <span>Curriculum</span>
                </div>
                <div class="tit">Curriculum</div>
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
                        <a href="/eng/edu/curriculum.do" class="active">Curriculum</a>
                        <a href="/eng/edu/equipment.do">Equipment</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- //sub nav -->

        <!-- section -->
        <div class="edu_sec edu_cur padding_tb">
            <div class="inner">
                <div class="sec_tit">
                    <div class="big">Korea EduMarine Academy Program Overview</div>
                </div>

                <div class="tb_wrap padding_b">
                    <div class="top_tit">
                        <div class="number">01</div>
                        <div class="tit">Regular Course</div>
                    </div>
                    <!-- tb box -->
                    <div class="tb_box">
                        <div class="tit">[Basic]</div>
                        <div class="table">
                            <table>
                                <colgroup>
                                    <col>
                                    <col style="width: 28%;">
                                    <col style="width: 28%;">
                                    <col style="width: 28%;">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><div class="box">Course Name</div></th>
                                        <th><div class="box">Boat Engineer<br>(Outboard engine vessel maintenance)</div></th>
                                        <th><div class="box">Boat Engineer<br>(Inboard engine vessel maintenance)</div></th>
                                        <th><div class="box">Boat Engineer<br>(FRP Hull maintenance)</div></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><div class="box bgSky center">Training Target</div></td>
                                        <td colspan="3">
                                            <div class="box center">Among those who completed the theory course provided by Marina Professional Training Institutions designated by the Ministry Oceans and Fisheries, anyone that wants to acquire the certificate</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><div class="box bgSky center">Purpose of Training</div></td>
                                        <td colspan="2">
                                            <div class="box">
                                                <div class="p">Acquiring skills to maintain marine engine</div>
                                                <div class="p">Equipping engineers with the ability to use technical literatures</div>
                                                <div class="p">Those who completed the course earn the qualification to take the exam for marina boat engineer certificate</div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Acquiring skills to maintain FRP Hull</div>
                                                <div class="p">Equipping engineers with the ability to use technical literatures</div>
                                                <div class="p">Those who completed the course earn the qualification to take the exam for marina boat engineer certificate</div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><div class="box bgSky center">Training Contents</div></td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Understanding of the marine engine</div>
                                                <div class="p">Understanding of outboard engine system</div>
                                                <div class="p">Two-stroke outboard engine Practice</div>
                                                <div class="p">Four-stroke outboard engine Practice</div>
                                                <div class="p">Outboard engine service and maintenance</div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Understanding of the marine engine</div>
                                                <div class="p">Diesel engine system</div>
                                                <div class="p">Inboard engine maintenance practice</div>
                                                <div class="p">Inboard engine service and maintenance</div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Understanding of FRP hull</div>
                                                <div class="p">Hull management and maintenance</div>
                                                <div class="p">FRP lamination and the basics of engineering</div>
                                                <div class="p">Base coating to repair hull damage</div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //tb box -->
                    <!-- tb box -->
                    <div class="tb_box">
                        <div class="tit">[Advanced]</div>
                        <div class="table">
                            <table>
                                <colgroup>
                                    <col>
                                    <col style="width: 28%;">
                                    <col style="width: 28%;">
                                    <col style="width: 28%;">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><div class="box">Course Name</div></th>
                                        <th><div class="box">High-horsepower outboard maintenance</div></th>
                                        <th><div class="box">Sterndrive maintenance</div></th>
                                        <th><div class="box">Sail engine repair and maintenance</div></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><div class="box bgSky center">Training Target</div></td>
                                        <td colspan="3">
                                            <div class="box">
                                                <div class="p">Those who completed the marine engine technician training</div>
                                                <div class="p">Those who completed practice course for marina boat (inboard/outboard engine)</div>
                                                <div class="p">Anyone who has experiences related to engine repair</div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><div class="box bgSky center">Purpose of Training</div></td>
                                        <td><div class="box center">Nurturing intermediate level technician for outboard engine</div></td>
                                        <td><div class="box center">Sterndrive repair and maintenance</div></td>
                                        <td><div class="box center">Sail engine repair and maintenance</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box bgSky center">Training Contents</div></td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Understanding of high-horsepower outboard engine system</div>
                                                <div class="p">High-horsepower outboard engine disassembly and assembly practice</div>
                                                <div class="p">Understanding of maintenance and fault diagnosis for high-horsepower outboard engine</div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Understanding of sterndrive</div>
                                                <div class="p">Method to maintain and inspect sterndrive</div>
                                                <div class="p">Fault of sterndrive and corrective action</div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="box">
                                                <div class="p">Understanding of sail engine</div>
                                                <div class="p">Method to maintain and inspect sail engine</div>
                                                <div class="p">Fault of sail engine and corrective action</div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //tb box -->
                </div>

                <div class="tb_wrap padding_b">
                    <div class="top_tit">
                        <div class="number">02</div>
                        <div class="tit">Intensive Course</div>
                    </div>
                    <!-- tb box -->
                    <div class="tb_box">
                        <div class="table">
                            <table>
                                <colgroup>
                                    <col>
                                    <col style="width: 28%;">
                                    <col style="width: 28%;">
                                    <col style="width: 28%;">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><div class="box">Course Name</div></th>
                                        <th><div class="box">Emergency response for marine engine </div></th>
                                        <th><div class="box">Basic maintenance of marine engine</div></th>
                                        <th><div class="box">Advanced self-repair</div></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><div class="box bgSky center">Training Target</div></td>
                                        <td colspan="3">
                                            <div class="box">
                                                <div class="p">Those who completed the marine engine technician training</div>
                                                <div class="p">Those who completed practicum course for marina boat (inboard/outboard engine)</div>
                                                <div class="p">Anyone who has experiences related to engine repair</div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><div class="box bgSky center">Purpose of Training</div></td>
                                        <td><div class="box center">Nurturing intermediate level technician for outboard engine</div></td>
                                        <td><div class="box center">Sterndrive repair and maintenance</div></td>
                                        <td><div class="box center">Sail engine repair and maintenance</div></td>
                                    </tr>
                                    <tr>
                                        <td><div class="box bgSky center">Training Contents</div></td>
                                        <td colspan="3">
                                            <div class="boxCol">
                                                <div class="box">
                                                    <div class="p">Understanding of high-horsepower outboard engine system</div>
                                                    <div class="p">High-horsepower outboard engine disassembly and assembly practice</div>
                                                    <div class="p">Understanding of maintenance and fault diagnosis for high-horsepower outboard engine</div>
                                                </div>
                                                <div class="box">
                                                    <div class="p">Understanding of sail engine</div>
                                                    <div class="p">Method to maintain and inspect sail engine</div>
                                                    <div class="p">Fault of sail engine and corrective action</div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //tb box -->
                </div>

                <div class="img_box"><img src="/eng/img/img_edu_cur_01.jpg"></div>

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