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
    <title>스마트산업진흥협회</title>

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

<div id="container">

    <!-- sub_top -->
    <div class="sub_top sub_top_comm">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>커뮤니티</span>
                    <span>행사게시판</span>
                </div>
                <div class="sub_top_tit">행사게시판</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">행사게시판</div>
                <ul class="tabOptSel">
                    <li><a href="/community/notice.do">공지사항</a></li>
                    <li><a href="/community/news.do">SIPA-NEWS</a></li>
                    <li class="active"><a href="/community/event.do">행사게시판</a></li>
                    <li><a href="/community/contact.do">Contact us</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- calendarr_box -->
    <script src="<%request.getContextPath();%>/static/js/calender.js"></script>
    <div class="calendar_box padding_t">
        <div class="inner">
            <div class="calBox">
                <div class="calYm">
                            <span onClick="prevCalendar();" style="cursor:pointer;">
                                <img src="<%request.getContextPath();%>/static/img/calendar_prev.png">
                            </span>
                    <span id="calYear"></span>.
                    <span id="calMonth"></span>
                    <span onClick="nextCalendar();" style="cursor:pointer;">
                                <img src="<%request.getContextPath();%>/static/img/calendar_next.png">
                            </span>
                </div>
                <div class="calTable">
                    <table class="calendar">
                        <thead>
                        <tr>
                            <td>일</td>
                            <td>월</td>
                            <td>화</td>
                            <td>수</td>
                            <td>목</td>
                            <td>금</td>
                            <td>토</td>
                        </tr>
                        </thead>

                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- //calendarr_box -->

    <!-- calendar_list -->
    <div class="calendar_list padding_b">
        <div class="inner">
            <form class="search_date">
                <select class="form-control input-sm" id="year" name="year">
                </select>
                <select class="form-control input-sm" id="month" name="month">
                </select>
                <button type="submit" id="searchButton">검색</button>
            </form>

            <div class="cal_list_box board_list_box">
                <ul class="thead">
                    <li>
                        <div class="number">번호</div>
                        <div class="tit">제목</div>
                        <div class="location">장소</div>
                        <div class="start">시작일</div>
                        <div class="end">종료일</div>
                    </li>
                </ul>
                <ul class="tbody">
                    <li>
                        <div class="number">1</div>
                        <div class="tit">제목</div>
                        <div class="location">구로롯데호텔</div>
                        <div class="start">6월 7일</div>
                        <div class="end">6월 9일</div>
                    </li>
                    <li>
                        <div class="number">2</div>
                        <div class="tit">제목</div>
                        <div class="location">구로롯데호텔</div>
                        <div class="start">6월 7일</div>
                        <div class="end">6월 9일</div>
                    </li>
                    <li>
                        <div class="number">3</div>
                        <div class="tit">제목</div>
                        <div class="location">구로롯데호텔</div>
                        <div class="start">6월 7일</div>
                        <div class="end">6월 9일</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //calendar_list -->


</div>

<c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery-1.9.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.number.min.js"></script>
<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>