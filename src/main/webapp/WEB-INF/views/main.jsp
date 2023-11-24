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

    <c:import url="header.jsp" charEncoding="UTF-8"/>

    <!-- floating -->
    <div class="floating">
        <a href="<%request.getContextPath();%>/static/file/edumarine_brochure.pdf" target="_blank">
            <p><span class="bold">Brochure</span><br>Download</p>
            <img src="<%request.getContextPath();%>/static/img/icon_download_yellow.png">
        </a>
    </div>
    <!-- //floating -->

    <!-- container -->
    <div id="container">

        <!-- main_top -->
        <div class="main_top">
            <div class="inner">
                <!-- main_search_wrap -->
                <div class="main_search_wrap">
                    <div class="search_bar">
                        <input type="text" placeholder="원하시는 교육과정을 검색해보세요.">
                        <a href="/search.do" class="btn">검색</a>
                    </div>
                    <div class="hashtag">
                        <a href="">#선내기</a>
                        <a href="">#선외기</a>
                        <a href="">#세일요트</a>
                        <a href="">#마리나선박 정비사</a>
                    </div>
                </div>
                <!-- //main_search_wrap -->
                <!-- main_swiper_wrap -->
                <div class="main_swiper_wrap">
                    <div class="swiper_box">
                        <div class="swiper">
                            <ul class="swiper-wrapper">
                                <li class="swiper-slide img_box"><img src="<%request.getContextPath();%>/static/img/img_main_slide_01.png" alt="메인 슬라이드"></li>
                                <li class="swiper-slide img_box"><img src="<%request.getContextPath();%>/static/img/img_main_slide_01.png" alt="메인 슬라이드"></li>
                                <li class="swiper-slide img_box"><img src="<%request.getContextPath();%>/static/img/img_main_slide_01.png" alt="메인 슬라이드"></li>
                            </ul>
                        </div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
                <!-- //main_swiper_wrap -->
                <!-- main_menu_wrap & main_video_wrap -->
                <div class="main_top_bot">
                    <ul class="main_menu_wrap">
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_01.png" alt="교육과정 아이콘"></div>
                                <div class="text">교육과정</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_02.png" alt="교육일정 아이콘"></div>
                                <div class="text">교육일정</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_03.png" alt="교육신청 아이콘"></div>
                                <div class="text">교육신청</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_04.png" alt="수료증발급 아이콘"></div>
                                <div class="text">수료증발급</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_05.png" alt="센터소개 아이콘"></div>
                                <div class="text">센터소개</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_06.png" alt="오시는길 아이콘"></div>
                                <div class="text">오시는길</div>
                            </a>
                        </li>
                    </ul>
                    <div class="main_video_wrap">
                        <div class="embed-container">
                            <iframe src='https://www.youtube.com/embed/i1OVSZ1npzs?autoplay=1&mute=1' frameborder='0'
                                    allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
                <!-- //main_menu_wrap & main_video_wrap -->
            </div>
        </div>
        <!-- //main_top -->

        <!-- main_edu -->
        <div class="main_edu">
            <div class="inner">
                <!-- body_tit -->
                <h3 class="body_tit">
                    <div class="big">교육 과정</div>
                </h3>
                <!-- //body_tit -->
                <!-- tab_menu -->
                <ul class="main_edu_tab tab_menu">
                    <li class="on" data-tab="tab-1">해상엔진 테크니션</li>
                    <li data-tab="tab-2">선외기 자가정비과정</li>
                    <li data-tab="tab-3">선내기 자가정비과정</li>
                    <li data-tab="tab-4">세일요트 자가정비과정</li>
                    <li data-tab="tab-5">마리나선박 정비사 실기교육</li>
                </ul>
                <!-- tab_menu -->
                <!-- list -->
                <div class="main_edu_list tab_content on" id="tab-1">
                    <ul class="list_head">
                        <li>
                            <div class="name">과정명</div>
                            <div class="chasi">차시</div>
                            <div class="peopleRecruit">인원</div>
                            <div class="peopleApp">신청인원</div>
                            <div class="periodApp">신청기간</div>
                            <div class="periodTng">교육기간</div>
                        </li>
                    </ul>
                    <ul class="list_body">
                        <li>
                            <div class="name"><a href="">해상엔진테크니션 (선외기 및 선내기 통합)</a></div>
                            <div class="chasi">8</div>
                            <div class="peopleRecruit">21</div>
                            <div class="peopleApp">1234</div>
                            <div class="periodApp">23.10.10 ~ 23.11.03</div>
                            <div class="periodTng">23.11.10 ~ 23.12.03</div>
                        </li>
                        <li>
                            <div class="name"><a href="">해상엔진테크니션 (선외기 및 선내기 통합)</a></div>
                            <div class="chasi">8</div>
                            <div class="peopleRecruit">21</div>
                            <div class="peopleApp">1234</div>
                            <div class="periodApp">23.10.10 ~ 23.11.03</div>
                            <div class="periodTng">23.11.10 ~ 23.12.03</div>
                        </li>
                        <li>
                            <div class="name"><a href="">해상엔진테크니션 (선외기 및 선내기 통합)</a></div>
                            <div class="chasi">8</div>
                            <div class="peopleRecruit">21</div>
                            <div class="peopleApp">1234</div>
                            <div class="periodApp">23.10.10 ~ 23.11.03</div>
                            <div class="periodTng">23.11.10 ~ 23.12.03</div>
                        </li>
                        <li>
                            <div class="name"><a href="">해상엔진테크니션 (선외기 및 선내기 통합)</a></div>
                            <div class="chasi">8</div>
                            <div class="peopleRecruit">21</div>
                            <div class="peopleApp">1234</div>
                            <div class="periodApp">23.10.10 ~ 23.11.03</div>
                            <div class="periodTng">23.11.10 ~ 23.12.03</div>
                        </li>
                        <li>
                            <div class="name"><a href="">해상엔진테크니션 (선외기 및 선내기 통합)</a></div>
                            <div class="chasi">8</div>
                            <div class="peopleRecruit">21</div>
                            <div class="peopleApp">1234</div>
                            <div class="periodApp">23.10.10 ~ 23.11.03</div>
                            <div class="periodTng">23.11.10 ~ 23.12.03</div>
                        </li>
                    </ul>
                </div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-2">두번째탭내용</div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-3">세번째탭내용</div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-4">네번째탭내용</div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-5">다섯번째탭내용</div>
                <!-- //list -->
            </div>
        </div>
        <!-- //main_edu -->


        <!-- main_board -->
        <div class="main_board">
            <div class="inner">
                <!-- body_tit -->
                <h3 class="body_tit">
                    <div class="big">센터 소식</div>
                </h3>
                <!-- //body_tit -->
                <!-- main_board_wrap -->
                <div class="main_board_box">
                    <!-- notice -->
                    <div class="main_board_list">
                        <div class="title">
                            <h4 class="text">공지사항</h4>
                            <a href="" class="more">더보기</a>
                        </div>
                        <ul class="list">
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                        </ul>
                    </div>
                    <!-- //notice -->
                    <!-- press -->
                    <div class="main_board_list">
                        <div class="title">
                            <h4 class="text">보도자료</h4>
                            <a href="" class="more">더보기</a>
                        </div>
                        <ul class="list">
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                            <li>
                                <div class="subject"><a href="">마리나 선박 정비사 신규과정 안내</a></div>
                                <div class="date">2023.10.16</div>
                            </li>
                        </ul>
                    </div>
                    <!-- //press -->
                </div>
                <!-- //main_board_wrap -->
            </div>
        </div>
        <!-- //main_board -->

        <!-- subscribe_wrap -->
        <div class="subscribe_wrap">
            <div class="inner">
                <div class="title">
                    <span>경기해양레저인력양성센터</span>의 다양한 소식을<br>
                    뉴스레터로 받아보세요.
                </div>
                <div class="subscribe_bar">
                    <input type="email" placeholder="e-mail">
                    <a href="" class="btn">구독하기</a>
                </div>
            </div>
        </div>
        <!-- //subscribe_wrap -->

    </div>
    <!-- //container -->

    <c:import url="footer.jsp" charEncoding="UTF-8"/>

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