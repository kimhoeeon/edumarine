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
    <title>경기해양레저 인력양성센터</title>

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
                    <span>찾아오시는 길</span>
                </div>
                <h2 class="sub_top_title">찾아오시는 길</h2>
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
                    <li><a href="/edumarine/sponsorship.do">협력 및 후원기관</a></li>
                    <li class="on"><a href="/edumarine/way.do">찾아오시는 길</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_center">
                <div class="center_wrap">

                    <!-- 해양레저산업 성장추세 -->
                    <div class="center_way">
                        <!-- tab_menu -->
                        <ul class="center_way_tab tab_menu">
                            <li class="on" data-tab="tab-1">경기테크노파크 (선외기)</li>
                            <li data-tab="tab-2">김포아라마리나 (선내기)</li>
                        </ul>
                        <!-- tab_menu -->
                        <!-- 경기테크노파크 (선외기) -->
                        <div class="center_way_map tab_content on" id="tab-1">
                            <div class="map">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d6348.317351773351!2d126.82981500000001!3d37.291373!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b6ec34390e3af%3A0x4290c03d6a503954!2z6rK96riw7YWM7YGs64W47YyM7YGs!5e0!3m2!1sko!2skr!4v1701167612534!5m2!1sko!2skr" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                            </div>
                            <ul class="info_list">
                                <li>
                                    <div class="gubun">Address</div>
                                    <div class="naeyong">경기도 안산시 상록구 해안로 705 경기테크노파크</div>
                                </li>
                                <li>
                                    <div class="gubun">Tel</div>
                                    <div class="naeyong">1811-7891</div>
                                </li>
                                <li>
                                    <div class="gubun">Fax</div>
                                    <div class="naeyong">031-999-7890</div>
                                </li>
                                <li>
                                    <div class="gubun">E-mail</div>
                                    <div class="naeyong">edu@edumarine.org</div>
                                </li>
                            </ul>
                            <!-- guide_btn_box -->
                            <div class="guide_btn_box">
                                <div class="btn_box"><a href="https://www.gtp.or.kr/antp/index/way.jsp" target="_blank">교통안내 바로가기</a></div>
                            </div>
                            <!-- //guide_btn_box -->
                        </div>
                        <!-- //경기테크노파크 (선외기) -->
                        <!-- 김포아라마리나 (선내기) -->
                        <div class="center_way_map tab_content" id="tab-2">
                            <div class="map">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d6322.198910912671!2d126.78570294609352!3d37.599818267692804!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357c9b45ed345fb1%3A0xffa07a65cced89b!2z6rK96riw64-EIOq5gO2PrOyLnCDqs6DstIzsnY0g7JWE65287Jyh66GcMTUy67KI6ri4IDIxMA!5e0!3m2!1sko!2skr!4v1701167568752!5m2!1sko!2skr" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                            </div>
                            <ul class="info_list">
                                <li>
                                    <div class="gubun">Address</div>
                                    <div class="naeyong">경기도 김포시 고촌읍 아라육로 152번길 210, 경기해양레저인력양성센터</div>
                                </li>
                                <li>
                                    <div class="gubun">Tel</div>
                                    <div class="naeyong">031-999-7841</div>
                                </li>
                                <li>
                                    <div class="gubun">Fax</div>
                                    <div class="naeyong">031-999-7890</div>
                                </li>
                                <li>
                                    <div class="gubun">E-mail</div>
                                    <div class="naeyong">ahn@waterway.or.kr</div>
                                </li>
                            </ul>
                            <!-- guide_btn_box -->
                            <div class="guide_btn_box">
                                <div class="btn_box"><a href="https://www.kweco.or.kr/www/contents.do?key=742" target="_blank">교통안내 바로가기</a></div>
                            </div>
                            <!-- //guide_btn_box -->
                        </div>
                        <!-- //김포아라마리나 (선내기) -->
                    </div>
                    <!-- //해양레저산업 성장추세 -->

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