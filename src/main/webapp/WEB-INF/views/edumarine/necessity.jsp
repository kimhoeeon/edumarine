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
                    <span>센터소개</span>
                    <span>해양레저 인력양성의 필요성</span>
                </div>
                <h2 class="sub_top_title">해양레저 인력양성의 필요성</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">센터소개</div>
                <ul class="lnb">
                    <li><a href="/edumarine/introduce.do">EDU marine 소개</a></li>
                    <li><a href="/edumarine/overview.do">사업개요</a></li>
                    <li><a href="/edumarine/current.do">경기도 해양레저 현황</a></li>
                    <li class="on"><a href="/edumarine/necessity.do">해양레저 인력양성의 필요성</a></li>
                    <li><a href="/edumarine/sponsorship.do">협력 및 후원기관</a></li>
                    <li><a href="/edumarine/way.do">찾아오시는 길</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_center">
                <div class="center_wrap">

                    <!-- 해양레저산업 성장추세 -->
                    <div class="center_top">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">해양레저산업 성장추세</div>
                            <div class="small">해양레저산업 성장추세에 맞춰 산업현장에 적합한 인력 양성 필요</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- enter_info_4 -->
                        <div class="center_info_4">
                            <div class="box">
                                <div class="box_inner">
                                    <div class="tit">
                                        국내 해양레저산업 관련<br>
                                        교육과정 필요
                                    </div>
                                    <div class="text">
                                        산업현장에서 필요로 하는 전문 양성인력으로
                                        일자리 창출에 부응
                                    </div>
                                </div>
                                <div class="box_inner">
                                    <div class="tit">
                                        향후 경기만 등<br>
                                        해양레저산업 활성화를 대비
                                    </div>
                                    <div class="text">
                                        점차 증가하는 해양레저산업<br>
                                        전문인력 수용에 대응
                                    </div>
                                </div>
                                <div class="box_inner">
                                    <div class="tit">
                                        양질 인력 공급으로<br>
                                        안전한 해양레저 지원
                                    </div>
                                    <div class="text">
                                        검증된 커리큘럼을 이수한<br>
                                        믿을 수 있는 인력배출
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- //enter_info_4 -->
                    </div>
                    <!-- //해양레저산업 성장추세 -->

                    <!-- 해양레저 전문 커리어 진로 다양성 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">해양레저 전문 커리어 진로 다양성</div>
                            <div class="small"><div class="small">해양레저 엔진 제작 및 정비, 선체정비, 판매관련 기업, 교육기관, 해양레저 산업체 등</div></div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- guide_info_4 -->
                        <div class="guide_info_4">
                            <div class="line"></div>
                            <ul class="info_list">
                                <li>
                                    <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/img_guide_01.png"></div>
                                    <div class="text_box">엔진수리점/<br>유지보수</div>
                                </li>
                                <li>
                                    <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/img_guide_02.png"></div>
                                    <div class="text_box">보트 판매사</div>
                                </li>
                                <li>
                                    <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/img_guide_03.png"></div>
                                    <div class="text_box">보트 제조사</div>
                                </li>
                                <li>
                                    <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/img_guide_04.png"></div>
                                    <div class="text_box">창업</div>
                                </li>
                                <li>
                                    <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/img_guide_05.png"></div>
                                    <div class="text_box">마리나</div>
                                </li>
                                <li>
                                    <div class="bg_box"><img src="<%request.getContextPath();%>/static/img/img_guide_06.png"></div>
                                    <div class="text_box">선외기<br>수입사/대리점</div>
                                </li>
                            </ul>
                        </div>
                        <!-- guide_info_4 -->
                    </div>
                    <!-- //해양레저 전문 커리어 진로 다양성 -->

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