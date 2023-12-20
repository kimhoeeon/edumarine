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
                    <span>뉴스레터</span>
                </div>
                <h2 class="sub_top_title">뉴스레터</h2>
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
                    <li><a href="/board/gallery.do">사진자료</a></li>
                    <li><a href="/board/media.do">영상자료</a></li>
                    <li class="on"><a href="/board/news_list.do">뉴스레터</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_board">
                <div class="board_wrap">

                    <!-- board_view -->
                    <div class="board_view_wrap">
                        <div class="subject">${newsInfo.title}</div>
                        <div class="info_box">
                            <div class="box write">
                                <div class="gubun">작성자</div>
                                <div class="naeyong">${newsInfo.writer}</div>
                            </div>
                            <div class="box date">
                                <div class="gubun">작성일</div>
                                <div class="naeyong">
                                    <c:set var="writeDate" value="${fn:replace(fn:split(newsInfo.writeDate,' ')[0],'-','.')}" />
                                    ${writeDate}
                                </div>
                            </div>
                            <div class="box views">
                                <div class="gubun">조회</div>
                                <div class="naeyong">${newsInfo.viewCnt}</div>
                            </div>
                        </div>
                        <div class="cont_box">
                            ${newsInfo.content}
                        </div>
                        <div class="file_box">
                            <c:if test="${not empty fileList}">
                                <c:forEach var="fileInfo" items="${fileList}" begin="0" end="${fileList.size()}" step="1" varStatus="status">
                                    <a href="/file/download.do?path=board/${fileInfo.folderPath}&fileName=${fileInfo.fullFileName}" class="file">${fileInfo.fileName}</a>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty fileList}">
                                첨부파일 없음
                            </c:if>
                        </div>
                        <div class="btn_box">
                            <a href="/board/news_list.do" class="btnSt01">목록으로</a>
                        </div>
                    </div>
                    <!-- //board_view -->

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