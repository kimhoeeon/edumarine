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
        <div class="sub_top sub_top_guide">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>교육 안내</span>
                    <span>위탁교육</span>
                </div>
                <h2 class="sub_top_title">위탁교육</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">교육안내</div>
                <ul class="lnb">
                    <li><a href="/guide/guide01.do">전체 교육과정 소개</a></li>
                    <li><a href="/guide/guide02.do">해상엔진 테크니션</a></li>
                    <li><a href="/guide/guide03.do">마리나선박 정비사 실기교육</a></li>
                    <li><a href="/guide/guide04.do">FRP 정비 테크니션</a></li>
                    <li class="on"><a href="/guide/guide05.do">위탁교육</a></li>
                    <li><a href="/guide/guide06.do">해상엔진 자가정비(선외기)</a></li>
                    <li><a href="/guide/guide07.do">해상엔진 자가정비(선내기)</a></li>
                    <li><a href="/guide/guide08.do">해상엔진 자가정비(세일요트)</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_guide">
                <div class="guide_wrap">

                    <!-- guide_top -->
                    <div class="guide_top">
                        <div class="guide_top_tit">
                            <div class="big">위탁교육</div>
                            <div class="eng">FRP Leisure Boat Hull Maintenance Technology</div>
                        </div>
                        <div class="guide_top_bnr">
                            <div class="bnr_box">
                                <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/bg_guide_top_04.jpg"></div>
                                <div class="text_box">
                                    <div class="big">교육 목적</div>
                                    <div class="small">
                                        해양레저분야 종사자 직무역량 향상
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //guide_top -->

                    <!-- 과정안내 -->
                    <div class="guide_sec">
                        <!-- guide_tit_box -->
                        <div class="guide_tit_box">
                            <div class="num">01</div>
                            <div class="name">과정안내</div>
                        </div>
                        <!-- //guide_tit_box -->
                        <!-- guide_table -->
                        <div class="guide_table">
                            <div class="table_box">
                                <table>
                                    <colgroup>
                                        <col style="width: 20%;">
                                        <col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>구분</th>
                                            <th>내용</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>교육내용</td>
                                            <td>
                                                <div class="box">
                                                    <div class="number">1) 중급 이론교육</div>
                                                    <div class="number">2) 엔진 점검, 분석 능력</div>
                                                    <div class="number">3) 엔진 성능시험 등</div>
                                                    <div class="small">※ 기관별 맞춤형 프로그램 구성 가능 (별도 문의)</div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>교육대상</td>
                                            <td>
                                                <div class="box">
                                                    <div class="text">해양레저분야 종사자</div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>교육장소</td>
                                            <td>
                                                <div class="box">
                                                    <ul class="list">
                                                        <li>선외기 - 아라마리나 선내기 교육장(김포)</li>
                                                        <li>선내기 - 경기테크노파크 선외기 교육장(안산)</li>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>교육기간</td>
                                            <td>
                                                <div class="box">
                                                    <div class="text">별도문의</div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- //guide_table -->
                    </div>
                    <!-- //과정안내 -->

                    <!-- 교육문의 -->
                    <div class="guide_inquiry">
                        <!-- guide_tit_box -->
                        <div class="guide_tit_box">
                            <div class="num">02</div>
                            <div class="name">교육문의</div>
                        </div>
                        <!-- //guide_tit_box -->
                        <div class="cont_box">
                            <div class="box_1">
                                <div class="tit">지원서 허위기재 등 부정행위를 한 자는 즉시 불합격 처리</div>
                            </div>
                            <div class="box_2">
                                <div class="tit">교육문의</div>
                                <ul>
                                    <li>
                                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_guide_tel.png"></div>
                                        <div class="text">Tel. 1811-7891</div>
                                    </li>
                                    <li>
                                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_guide_talk.png"></div>
                                        <div class="text">카카오톡 [경기 해양레저인력양성센터] 검색</div>
                                    </li>
                                    <li>
                                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_guide_email.png"></div>
                                        <div class="text">E-mail. <span class="underline">edu@edumarine.org</span> 또는
                                            https://yachtmnr.or.kr
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="box_cmnt">
                                상기 교육일정 및 교육내용은 변경될 수 있습니다.<br>
                                변경시 경기해양레저인력양성센터 홈페이지 (<span class="underline">www.edumarine.org</span>)에 게시
                            </div>
                        </div>
                        <!-- guide_btn_box -->
                        <div class="guide_btn_box">
                            <div class="btn_box"><a href="">교육신청 바로가기</a></div>
                        </div>
                        <!-- //guide_btn_box -->
                    </div>
                    <!-- //교육문의 -->

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