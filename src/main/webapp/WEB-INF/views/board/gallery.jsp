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
        <div class="sub_top sub_top_board">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>자료실</span>
                    <span>사진자료</span>
                </div>
                <h2 class="sub_top_title">사진자료</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">자료실</div>
                <ul class="lnb">
                    <li><a href="/board/notice_list.do">공지사항</a></li>
                    <li><a href="/board/press_list.do">보도자료</a></li>
                    <li class="on"><a href="/board/gallery.do">사진자료</a></li>
                    <li><a href="/board/media.do">영상자료</a></li>
                    <li><a href="/board/news_list.do">뉴스레터</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_board">
                <div class="board_wrap">

                    <!-- board_top -->
                    <div class="board_top">
                        <div class="post_number">총 <span class="number"></span>개의 게시글이 있습니다.</div>
                        <div class="board_search">
                            <select id="search_box">
                                <option value="title" selected>제목</option>
                                <%--<option value="content">내용</option>
                                <option value="all">내용+제목</option>--%>
                            </select>
                            <div class="search_bar">
                                <input type="text" id="search_text" placeholder="검색어 입력">
                                <a href="javascript:void(0);" onclick="galleryList(1);" class="search_btn"></a>
                            </div>
                        </div>
                    </div>
                    <!-- //board_top -->

                    <!-- board_list -->
                    <div class="gallery_list_wrap">

                        <ul class="gallery_list">
                            <%--<li class="gallery_view">
                                <div class="thumb"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg"></div>
                                <div class="subject">게시물 제목</div>
                                <div class="date">2023.03.04</div>
                            </li>--%>
                        </ul>

                        <!-- paging -->
                        <div class="paging">
                            <span class="first" id="first_page"><a><img src="<%request.getContextPath();%>/static/img/btn_first.gif" style="cursor: pointer"></a></span>
                            <span class="prev" id="prev_page"><a><img src="<%request.getContextPath();%>/static/img/btn_prev.gif" style="cursor: pointer"></a></span>
                            <ol>
                                <%--<li>
                                  <a class="this">1</a>
                                </li>
                                <li>
                                  <a class="other">2</a>
                                </li>--%>
                            </ol>
                            <span class="next" id="next_page"><a><img src="<%request.getContextPath();%>/static/img/btn_next.gif" style="cursor: pointer"></a></span>
                            <span class="last" id="last_page"><a><img src="<%request.getContextPath();%>/static/img/btn_last.gif" style="cursor: pointer"></a></span>
                        </div>
                        <!-- //paging -->
                    </div>
                    <!-- //board_list -->


                    <!-- gallery view -->
                    <div class="popup" id="popupGallery">
                        <div class="popup_inner">
                            <span class="popup_close"><img src="<%request.getContextPath();%>/static/img/icon_close_white.png" alt="닫기 아이콘"></span>
                            <div class="popup_tit">게시글 제목</div>
                            <div class="popup_box gallery_swiper">
                                <div class="swiper_box">
                                    <div class="swiper">
                                        <ul class="swiper-wrapper">
                                            <%--<li class="swiper-slide img_box">
                                                <img src="<%request.getContextPath();%>/static/img/img_sample.jpg">
                                            </li>--%>
                                        </ul>
                                    </div>
                                    <div class="swiper-button-prev"></div>
                                    <div class="swiper-button-next"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //gallery view -->


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

<script src="<%request.getContextPath();%>/static/js/front/gallery.js?ver=<%=System.currentTimeMillis()%>"></script>

    <script>
        document.addEventListener("keyup", function(event) {
            if (event.key === 'Enter') {
                galleryList(1);
            }
        });
    </script>

</body>
</html>