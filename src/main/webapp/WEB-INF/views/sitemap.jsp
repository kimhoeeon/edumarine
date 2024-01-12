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

    <c:import url="header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span><span>사이트맵</span>
                </div>
                <h2 class="sub_top_title">사이트맵</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">

            <!-- content -->
            <div id="content" class="sub_sitemap">
                <div class="sitemap_box">
                    <div class="title">센터소개</div>
                    <div class="list">
                        <a href="/edumarine/introduce.do">EDU marine 소개</a>
                        <a href="/edumarine/overview.do">사업개요</a>
                        <a href="/edumarine/current.do">경기도 해양레저 현황</a>
                        <a href="/edumarine/necessity.do">해양레저 인력양성의 필요성</a>
                        <a href="/edumarine/sponsorship.do">협력 및 후원기관</a>
                        <a href="/edumarine/way.do">찾아오시는 길</a>
                    </div>
                </div>
                <div class="sitemap_box">
                    <div class="title">교육안내</div>
                    <div class="list">
                        <a href="/guide/guide01.do">전체 교육과정 소개</a>
                        <a href="/guide/guide02.do">해상엔진 테크니션(선내기/선외기)</a>
                        <a href="/guide/guide03.do">마리나선박 정비사 실기교육</a>
                        <a href="/guide/guide04.do">FRP 레저보트 선체 정비 테크니션</a>
                        <a href="/guide/guide05.do">위탁교육</a>
                        <a href="/guide/guide06.do">해상엔진 자가정비(선외기)</a>
                        <a href="/guide/guide07.do">해상엔진 자가정비(선내기)</a>
                        <a href="/guide/guide08.do">해상엔진 자가정비(세일요트)</a>
                    </div>
                </div>
                <div class="sitemap_box">
                    <div class="title">교육신청</div>
                    <div class="list">
                        <a href="/apply/schedule.do">교육신청</a>
                    </div>
                </div>
                <div class="sitemap_box">
                    <div class="title">자료실</div>
                    <div class="list">
                        <a href="/board/notice_list.do">공지사항</a>
                        <a href="/board/press_list.do">보도자료</a>
                        <a href="/board/gallery.do">사진자료</a>
                        <a href="/board/media.do">영상자료</a>
                        <a href="/board/news_list.do">뉴스레터</a>
                    </div>
                </div>
                <div class="sitemap_box">
                    <div class="title">취업·창업</div>
                    <div class="list">
                        <%--<a href="/job/announcement_list.do">채용공고</a>--%>
                        <a href="/job/state01.do">취창업현황</a>
                        <a href="/job/review.do">취창업성공후기</a>
                        <a href="/job/community_list.do">커뮤니티</a>
                    </div>
                </div>
            </div>
            <!-- //content -->
        </div>
        <!-- content_wrap -->

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