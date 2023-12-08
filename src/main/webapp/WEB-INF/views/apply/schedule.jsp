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
        <div class="sub_top sub_top_edu">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>교육신청</span>
                </div>
                <h2 class="sub_top_title">교육신청</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">

            <!-- content -->
            <div id="content" class="sub_skde">
                <div class="sked_wrap">

                    <!-- sked_top -->
                    <div class="sked_top">
                        <div class="sked_btn"><a href="#">연간 교육 일정표</a></div>
                        <ul class="sked_search_wrap">
                            <li>
                                <div class="gubun">교육연도</div>
                                <div class="input"><input type="text" value="2023" readonly></div>
                            </li>
                            <li>
                                <div class="gubun">과정</div>
                                <div class="input">
                                    <div class="select_box">
                                        <div class="select_label">전체</div>
                                        <ul class="option_list">
                                            <li class="option_item">전체</li>
                                            <li class="option_item">정규과정</li>
                                            <li class="option_item">단기과정</li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">교육 과정명</div>
                                <div class="input"><input type="text" placeholder="교육 과정명을 입력해주세요."></div>
                                <a href="#" class="search_btn"></a>
                            </li>
                        </ul>
                    </div>
                    <!-- //sked_top -->

                    <!-- sked_list_none -->
                    <%--<div class="sked_list_none">
                        <div class="edu_none">
                            <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_edu_no.png"></div>
                            <div class="text">해당 교육과정은 개설 예정입니다</div>
                        </div>
                    </div>--%>
                    <!-- //sked_list_none -->

                    <!-- sked_list_wrap -->
                    <div class="sked_list_wrap">
                        <div class="post_number">총 <span class="number">5</span>개의 게시글이 있습니다.</div>
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
                                <div class="btn"><a href="/apply/eduApply01.do">교육신청</a></div>
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
                                <div class="btn"><a href="/apply/eduApply01.do">교육신청</a></div>
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
                                <div class="btn"><a href="/apply/eduApply01.do">교육신청</a></div>
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
                                <div class="btn"><a href="/apply/eduApply01.do">교육신청</a></div>
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
                                <div class="btn"><a href="/apply/eduApply01.do">교육신청</a></div>
                            </li>
                        </ul>
                        <!-- //sked_list -->

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
                    <!-- //sked_list_wrap -->


                    <!-- popup calendar -->
                    <div class="popup" id="popupCalendar">
                        <div class="popup_inner">
                            <div class="popup_box">

                                <script src="<%request.getContextPath();%>/static/js/calendar.js"></script>
                                <!-- calendar_box -->
                                <div class="calendar_box">
                                    <div class="calBox">
                                        <div class="calYm">
                                            <span onClick="prevCalendar();" style="cursor:pointer;">
                                                <img src="<%request.getContextPath();%>/static/img/icon_calendar_prev.png">
                                            </span>
                                            <span id="calYear"></span>
                                            <span id="calMonth"></span>
                                            <span onClick="nextCalendar();" style="cursor:pointer;">
                                                <img src="<%request.getContextPath();%>/static/img/icon_calendar_next.png">
                                            </span>
                                        </div>
                                        <div class="calRadio">
                                            <label><input type="radio" name="calendar_edu_cc" value="전체"
                                                    checked>전체</label>
                                            <label><input type="radio" name="calendar_edu_cc" value="정규">정규과정</label>
                                            <label><input type="radio" name="calendar_edu_cc" value="단기">단기과정</label>
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

                                    <div class="calList">
                                        <div class="choiceCalDay"></div>
                                        <ul class="choiceSkedList"></ul>
                                    </div>
                                </div>
                                <!-- //calendar_box -->

                                <a href="#" class="close_btn btnSt01">닫기</a>

                            </div>
                        </div>
                    </div>
                    <!-- //popup calendar -->



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