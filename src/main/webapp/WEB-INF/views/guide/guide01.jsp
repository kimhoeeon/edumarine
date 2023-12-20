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
                    <span>교육 안내</span>
                    <span>전체 교육과정 소개</span>
                </div>
                <h2 class="sub_top_title">전체 교육과정 소개</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">교육안내</div>
                <ul class="lnb">
                    <li class="on"><a href="/guide/guide01.do">전체 교육과정 소개</a></li>
                    <li><a href="/guide/guide02.do">해상엔진 테크니션</a></li>
                    <li><a href="/guide/guide03.do">마리나선박 정비사 실기교육</a></li>
                    <li><a href="/guide/guide04.do">FRP 레저보트 선체 정비 테크니션</a></li>
                    <li><a href="/guide/guide05.do">위탁교육</a></li>
                    <li><a href="/guide/guide06.do">해상엔진 자가정비(선외기)</a></li>
                    <li><a href="/guide/guide07.do">해상엔진 자가정비(선내기)</a></li>
                    <li><a href="/guide/guide08.do">해상엔진 자가정비(세일요트)</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_guide">
                <div class="guide_wrap">

                    <!-- guide_all -->
                    <div class="guide_all">
                        <!-- 정규과정 -->
                        <div class="box">
                            <div class="guide_tit_box">
                                <div class="num">01</div>
                                <div class="name">정규 과정</div>
                            </div>
                            <div class="cont_list">
                                <ul>
                                    <li class="list_top">
                                        <div class="gubun">교육명</div>
                                        <div class="naeyong">
                                            <div class="item">해상엔진 테크니션 <span class="small">(선내기 및 선외기 통합과정)</span></div>
                                            <div class="item">FRP 레저보트 선체정비 테크니션</div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육대상</div>
                                        <div class="naeyong">
                                            <div class="item">해양레저분야 취/창업 희망자 <span class="small">(졸업 예정자 가능)</span></div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육목적</div>
                                        <div class="naeyong">
                                            <div class="item">해양레저분야 테크니션 실무 역량 습득 및 취업기회 획득</div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- //정규과정 -->

                        <!-- 단기과정 -->
                        <div class="box">
                            <div class="guide_tit_box">
                                <div class="num">02</div>
                                <div class="name">단기 과정</div>
                            </div>
                            <div class="cont_list">
                                <ul>
                                    <li class="list_top">
                                        <div class="gubun">교육명</div>
                                        <div class="naeyong">
                                            <div class="item">해상엔진 자가정비반</div>
                                            <div class="item">위탁교육반</div>
                                            <div class="item">해상엔진 전문화 과정</div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육대상</div>
                                        <div class="naeyong">
                                            <div class="item list">
                                                <p>레저 선박 소유자 등</p>
                                            </div>
                                            <div class="item list">
                                                <p>해양레저 관련업 종사자 등</p>
                                            </div>
                                            <div class="item list">
                                                <p>레저 선박 소유자</p>
                                                <p>해양레저 관련업 종사자 등</p>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="gubun">교육목적</div>
                                        <div class="naeyong">
                                            <div class="item list">
                                                <p>기초 이론교육</p>
                                                <p>엔진 기초 유지관리</p>
                                                <p>응급조치 방법</p>
                                            </div>
                                            <div class="item list">
                                                <p>중급 이론교육</p>
                                                <p>엔진 점검, 진단, 분석 능력</p>
                                                <p>엔진 성능시험</p>
                                            </div>
                                            <div class="item list">
                                                <p>중급 이론교육</p>
                                                <p>엔진 점검, 진단, 분석 능력</p>
                                                <p>엔진 설치 탈착</p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- //단기과정 -->
                    </div>
                    <!-- //guide_all -->

                    <!-- guide_all_inquiry -->
                    <div class="guide_all_inquiry">
                        <div class="tit_box"><p>교육문의</p></div>
                        <ul class="cont_box">
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
                                <div class="text">E-mail. edu@edumarine.org 또는 https://yachtmnr.or.kr</div>
                            </li>
                        </ul>
                    </div>
                    <!-- //guide_all_inquiry -->

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