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

    <%-- container --%>
    <div id="container">

        <%-- sub_top --%>
        <div class="sub_top sub_top_guide">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>센터소개</span>
                    <span>EDU marine 소개</span>
                </div>
                <h2 class="sub_top_title">EDU marine 소개</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">센터소개</div>
                <ul class="lnb">
                    <li class="on"><a href="/edumarine/introduce.do">EDU marine 소개</a></li>
                    <li><a href="/edumarine/overview.do">사업개요</a></li>
                    <li><a href="/edumarine/current.do">경기도 해양레저 현황</a></li>
                    <li><a href="/edumarine/necessity.do">해양레저 인력양성의 필요성</a></li>
                    <li><a href="/edumarine/sponsorship.do">협력 및 후원기관</a></li>
                    <li><a href="/edumarine/way.do">찾아오시는 길</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_center">
                <div class="center_wrap">

                    <!-- edu marine 소개 -->
                    <div class="center_top">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">EDU marine 소개</div>
                            <div class="small">
                                경기해양레저인력양성센터(Korea Marine Education Academy)는 경기도가 16년도에 전국최초로<br>
                                해양레저 엔지니어링 교육을 개설하여 해양레저 엔지니어링 분야의 테크니션 수요 증가에 따라,<br>
                                현장 실무에 필요한 전문인력을 양성하고 취업 연계를 지원하는 교육기관입니다.
                            </div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_1 -->
                        <div class="center_info_1">
                            <ul class="cont_box">
                                <li class="box">
                                    <div class="text">
                                        <div class="big">단기</div>
                                        <div class="small">
                                            해양 엔진 테크니션<br>
                                            입문 교육을 통한<br>
                                            고용 진출
                                        </div>
                                    </div>
                                </li>
                                <li class="icon"></li>
                                <li class="box">
                                    <div class="text">
                                        <div class="big">중기</div>
                                        <div class="small">
                                            유지보수 인력 서비스<br>
                                            품질 선진화
                                        </div>
                                    </div>
                                </li>
                                <li class="icon"></li>
                                <li class="box">
                                    <div class="text">
                                        <div class="big">장기</div>
                                        <div class="small">
                                            R&D 접목이 가능한<br>
                                            고급 테크니션 양성
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- //center_info_1 -->
                    </div>
                    <!-- //edu marine 소개 -->

                    <!-- 추친 체계 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">추친 체계</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_2 -->
                        <div class="center_info_2">
                            <div class="box_1">
                                <div class="box_inner"><img src="<%request.getContextPath();%>/static/img/logo_center_01.png"></div>
                            </div>
                            <div class="box_2">
                                <div class="box_inner">
                                    <div class="text">해외 전문 교육기관</div>
                                    <div class="logo">
                                        <img src="<%request.getContextPath();%>/static/img/logo_center_02.png">
                                        <img src="<%request.getContextPath();%>/static/img/logo_center_03.png">
                                    </div>
                                </div>
                                <div class="box_inner">
                                    <div class="logo">
                                        <img src="<%request.getContextPath();%>/static/img/logo_center_04.png">
                                    </div>
                                </div>
                                <div class="box_inner">
                                    <div class="text">
                                        국내 해양레저기업 협회 단체<br>
                                        (요트/보트협회 등)
                                    </div>
                                </div>
                            </div>
                            <div class="box_3">
                                <div class="box_inner">
                                    <div class="logo">
                                        <img src="<%request.getContextPath();%>/static/img/logo_center_05.png">
                                    </div>
                                    <ul class="list">
                                        <li>선외기 엔진 정비 교육 운영</li>
                                        <li>인력양성지원협의회 주관</li>
                                        <li>경기해양레저산업 육성</li>
                                    </ul>
                                </div>
                                <div class="box_inner">
                                    <div class="logo">
                                        <img src="<%request.getContextPath();%>/static/img/logo_center_06.png">
                                    </div>
                                    <ul class="list">
                                        <li>선내기 엔진 정비 교육 운영</li>
                                        <li>선체 유지보수 교육 운영</li>
                                        <li>요트면허인증 기관</li>
                                    </ul>
                                </div>
                                <div class="box_inner">
                                    <div class="logo">
                                        <img src="<%request.getContextPath();%>/static/img/logo_center_07.png">
                                    </div>
                                    <ul class="list">
                                        <li>경기국제보트쇼 운영</li>
                                        <li>해양레저산업 판로개척지원</li>
                                        <li>국제해양레저 협력 지원</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- //center_info_2 -->
                    </div>
                    <!-- //추친 체계 -->

                    <!-- 기대효과 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">기대효과</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_3 -->
                        <div class="center_info_3">
                            <div class="box">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_center_check.png"></div>
                                <div class="text">해양레저 장비 분야 테크니션 전문 양성 시스템을 구축하여, 국내 제조/서비스 시장 청년 고급 일자리 창출 선도</div>
                            </div>
                            <div class="box">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_center_check.png"></div>
                                <div class="text">검증된 기술인력을 시장에 공급하여 안전한 해양레저 활동 여건 마련</div>
                            </div>
                        </div>
                        <!-- //center_info_3 -->
                    </div>
                    <!-- //기대효과 -->

                    <!-- 후원기관 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">후원기관</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_img -->
                        <div class="center_info_img">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_01.png" class="img_pc">
                                <img src="<%request.getContextPath();%>/static/img/img_center_01_m.png" class="img_m">
                            </div>
                        </div>
                        <!-- //center_info_img -->
                    </div>
                    <!-- //후원기관 -->

                    <!-- 협력 교육기관 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">협력 교육기관</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_img -->
                        <div class="center_info_img">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_02.png" class="img_pc">
                                <img src="<%request.getContextPath();%>/static/img/img_center_02_m.png" class="img_m">
                            </div>
                        </div>
                        <!-- //center_info_img -->
                    </div>
                    <!-- //협력 교육기관 -->

                    <!-- 협력 기업 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">협력 기업</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_img -->
                        <div class="center_info_img">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_03.png" class="img_pc">
                                <img src="<%request.getContextPath();%>/static/img/img_center_03_m.png" class="img_m">
                            </div>
                        </div>
                        <!-- //center_info_img -->
                    </div>
                    <!-- //협력 기업 -->

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