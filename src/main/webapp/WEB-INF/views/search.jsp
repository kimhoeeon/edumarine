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

    <!-- container -->
    <div id="container">

        <div class="search_wrap">
            <div class="inner">

                <!-- main_search_wrap -->
                <div class="main_search_wrap">
                    <div class="search_bar">
                        <input type="text" placeholder="원하시는 교육과정을 검색해보세요.">
                        <a href="" class="btn">검색</a>
                    </div>
                    <div class="hashtag">
                        <a href="">#선내기</a>
                        <a href="">#선외기</a>
                        <a href="">#세일요트</a>
                        <a href="">#마리나선박 정비사</a>
                    </div>
                </div>
                <!-- //main_search_wrap -->

                <!-- sked_list_wrap -->
                <div class="sked_list_wrap">
                    <div class="search_box_tit">
                        <div class="big">교육신청</div>
                        <div class="post_number">총 <span class="number">2</span>건</div>
                    </div>
                    <!-- sked_list -->
                    <ul class="sked_list">
                        <li>
                            <div class="thumb"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg"></div>
                            <div class="info">
                                <div class="subject">해양엔진테크니션(선외기 및 선내기 통합)</div>
                                <ul class="description">
                                    <li>
                                        <div class="gubun">교육일정</div>
                                        <div class="naeyong">23.11.10 ~ 23.12.03</div>
                                    </li>
                                    <li>
                                        <div class="gubun">접수기간</div>
                                        <div class="naeyong">23.11.10 ~ 23.12.03</div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육비</div>
                                        <div class="naeyong">100,000원</div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육인원(현 신청인원)</div>
                                        <div class="naeyong"><span class="color">32</span>(2)</div>
                                    </li>
                                    <li>
                                        <div class="gubun">기타</div>
                                        <div class="naeyong">수료 시, 전액 환급</div>
                                    </li>
                                </ul>
                            </div>
                            <div class="btn"><a href="">교육신청</a></div>
                        </li>
                        <li>
                            <div class="thumb"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg"></div>
                            <div class="info">
                                <div class="subject">해양엔진테크니션(선외기 및 선내기 통합)</div>
                                <ul class="description">
                                    <li>
                                        <div class="gubun">교육일정</div>
                                        <div class="naeyong">23.11.10 ~ 23.12.03</div>
                                    </li>
                                    <li>
                                        <div class="gubun">접수기간</div>
                                        <div class="naeyong">23.11.10 ~ 23.12.03</div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육비</div>
                                        <div class="naeyong">100,000원</div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육인원(현 신청인원)</div>
                                        <div class="naeyong"><span class="color">32</span>(2)</div>
                                    </li>
                                    <li>
                                        <div class="gubun">기타</div>
                                        <div class="naeyong">수료 시, 전액 환급</div>
                                    </li>
                                </ul>
                            </div>
                            <div class="btn"><a href="">교육신청</a></div>
                        </li>
                    </ul>
                    <!-- //sked_list -->
                </div>
                <!-- //sked_list_wrap -->

                <!-- search_list_wrap -->
                <div class="search_list_wrap">
                    <div class="search_box_tit">
                        <div class="big">게시글</div>
                        <div class="post_number">총 <span class="number">10</span>건</div>
                    </div>

                    <!-- search_list -->
                    <ul class="search_list">
                        <li>
                            <div class="subject"><a href="#">게시글제목</a></div>
                            <div class="date">2023.01.11</div>
                            <div class="nav">홈 > 자료실 > 공지사항</div>
                        </li>
                        <li>
                            <div class="subject"><a href="#">게시글제목</a></div>
                            <div class="date">2023.01.11</div>
                            <div class="nav">홈 > 자료실 > 공지사항</div>
                        </li>
                        <li>
                            <div class="subject"><a href="#">게시글제목</a></div>
                            <div class="date">2023.01.11</div>
                            <div class="nav">홈 > 자료실 > 공지사항</div>
                        </li>
                        <li>
                            <div class="subject"><a href="#">게시글제목</a></div>
                            <div class="date">2023.01.11</div>
                            <div class="nav">홈 > 자료실 > 공지사항</div>
                        </li>
                        <li>
                            <div class="subject"><a href="#">게시글제목</a></div>
                            <div class="date">2023.01.11</div>
                            <div class="nav">홈 > 자료실 > 공지사항</div>
                        </li>
                    </ul>
                    <!-- //search_list -->

                    <!-- paging -->
                    <div class="paging">
                        <a href="" class="prev"><img src="<%request.getContextPath();%>/static/img/btn_prev.gif"></a>
                        <ol>
                            <li><a href="" class="this">1</a></li>
                            <li><a href="" class="other">2</a></li>
                            <li><a href="" class="other">3</a></li>
                        </ol>
                        <a href="" class="next"><img src="<%request.getContextPath();%>/static/img/btn_next.gif"></a>
                    </div>
                    <!-- //paging -->
                </div>
                <!-- //search_list_wrap -->

            </div>
        </div>

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