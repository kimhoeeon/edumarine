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
                    <span>센터소개</span>
                    <span>사업개요</span>
                </div>
                <h2 class="sub_top_title">사업개요</h2>
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
                    <li class="on"><a href="/edumarine/overview.do">사업개요</a></li>
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

                    <!-- 사업목적 -->
                    <div class="center_top">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">사업목적</div>
                            <div class="small">
                                해양레저 엔지니어링 분야의 테크니션 수요 증가에 따라, 현장 실무인재를 양성하고 취업과 연계하기 위하여<br>
                                “경기해양레저 청년일자리 창출사업”을 운영합니다.
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
                    <!-- //사업목적 -->

                    <!-- 사업개요 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">사업개요</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- guide_info_1 -->
                        <ul class="guide_info_1">
                            <li>
                                <div class="gubun">사업대상</div>
                                <div class="naeyong">
                                    <div class="text">해양레저 테크니션 교육을 통한 취·창업을 희망하는 자</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">사업내용</div>
                                <div class="naeyong">
                                    <div class="text">해양레저 테크니션 교육 인프라 구축, 교육 프로그램 운영 및 취업연계, 해양레저산업 협력 네트워크 구축</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">기대효과</div>
                                <div class="naeyong">
                                    <ul class="list">
                                        <li>해양레저 장비 분야 테크니션 전문 양성 시스템을 구축하여, 국내 제조/서비스 시장 청년 고급 일자리 창출 선도</li>
                                        <li>검증된 기술인력을 시장에 공급하여 안전한 해양레저 활동 여건 마련</li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                        <!-- guide_info_1 -->
                    </div>
                    <!-- //사업개요 -->

                    <!-- 추진방향 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">추진방향</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_info_2 -->
                        <div class="center_info_2">
                            <div class="box_3">
                                <div class="box_inner">
                                    <div class="text">
                                        국내/경기도 최초 해양레저 전문<br>
                                        교육인프라 및 커리큘럼 구축
                                    </div>
                                </div>
                                <div class="box_inner">
                                    <div class="text">
                                        전문 기술 자격증 취득자 중심의<br>
                                        고급 서비스 일자리 창출 지원
                                    </div>
                                </div>
                                <div class="box_inner">
                                    <div class="text">
                                        해양레저 제조/서비스업 연계 협력회의<br>
                                        운영을 통한 수요 저변확대
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- //center_info_2 -->
                    </div>
                    <!-- //추진방향 -->

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