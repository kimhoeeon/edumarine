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

    <c:import url="../header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top sub_top_job">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>취업·창업</span>
                    <span>커뮤니티</span>
                </div>
                <h2 class="sub_top_title">커뮤니티</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">취업·창업</div>
                <ul class="lnb">
                    <!--<li><a href="/job/announcement_list.do">채용공고</a></li>-->
                    <li><a href="/job/state01.do">취창업현황</a></li>
                    <li><a href="/job/review.do">취창업성공후기</a></li>
                    <li class="on"><a href="/job/community_list.do">커뮤니티</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_board">
                <div class="board_wrap">


                    <!-- community_write -->
                    <div class="community_write_wrap">

                        <div class="subject"><input type="text" placeholder="제목을 입력하세요."></div>
                        <div class="cont_box">
                            여기에 에디터 삽입
                        </div>
                        <div class="tag_box">
                            <div class="gubun">키워드 선택 <span class="cmnt">(1개 이상 필수 선택)</span></div>
                            <div class="tag">
                                <label><input type="checkbox">선내기</label>
                                <label><input type="checkbox">선외기</label>
                                <label><input type="checkbox">마리나선박</label>
                                <label><input type="checkbox">정비</label>
                                <label><input type="checkbox">이직/커리어</label>
                                <label><input type="checkbox">여행</label>
                                <label><input type="checkbox">해양레저이슈</label>
                                <label><input type="checkbox">취미</label>
                                <label><input type="checkbox">취업</label>
                            </div>
                        </div>
                        <div class="btn_box">
                            <a href="/job/community_view.do" class="btnSt03">취소</a>
                            <a href="/job/community_view.do" class="btnSt01">수정</a>
                        </div>

                    </div>
                    <!-- //community_write -->

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