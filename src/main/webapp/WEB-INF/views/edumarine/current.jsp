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
                    <span>경기도 해양레저 현황</span>
                </div>
                <h2 class="sub_top_title">경기도 해양레저 현황</h2>
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
                    <li class="on"><a href="/edumarine/current.do">경기도 해양레저 현황</a></li>
                    <li><a href="/edumarine/necessity.do">해양레저 인력양성의 필요성</a></li>
                    <li><a href="/edumarine/sponsorship.do">협력 및 후원기관</a></li>
                    <li><a href="/edumarine/way.do">찾아오시는 길</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_center">
                <div class="center_wrap">

                    <!-- 전곡 해양산단 -->
                    <div class="center_top">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">전곡 해양산단</div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_current_1 -->
                        <div class="center_current_1">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_04.jpg">
                            </div>
                        </div>
                        <!-- //center_current_1 -->
                        <!-- center_current_2 -->
                        <div class="center_current_2">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_05.jpg">
                            </div>
                            <div class="text_box">
                                <ul class="list">
                                    <li>위 치 : 경기도 화성시 서신면 전곡리 일원</li>
                                    <li>사업면적 : 162만㎡</li>
                                    <li>총사업비 : 5,370억원</li>
                                    <li>사업기간 : 2008~2013년</li>
                                    <li>사업내용 : 제조시설 산업단지 조성</li>
                                    <li>시설계획 : 국내 업체 / 해외 업체 / 교육, 판매, 보수 시설</li>
                                </ul>
                            </div>
                        </div>
                        <!-- //center_current_2 -->
                        <!-- center_current_1 -->
                        <div class="center_current_1">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_06.jpg">
                            </div>
                        </div>
                        <!-- //center_current_1 -->
                    </div>
                    <!-- //전곡 해양산단 -->

                    <!-- 경기만의 마리나 시설 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">경기만의 마리나 시설</div>
                            <div class="small">
                                경기도는 전곡, 김포 마리나와 함께 제부, 홀곶, 방아머리 등 대규모 마리나 3곳 개발을 추진하고 있습니다.<br>
                                이 마리나들은 바다와 육지 계류 시설을 함께 갖추고 있어 조수간만의 차, 태풍 등<br>
                                자연재해로부터 최대한 보호되는 안전한 요·보트 계류가 가능합니다.<br>
                                이 다섯 마리나를 이용한 항해코스개발과 해양관광인프라로 활용될 마리나를 거점으로<br>
                                경기만은 동북아의 해양레저관광 산업의 허브로 성장하게 될 것입니다.
                            </div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_current_1 -->
                        <div class="center_current_1">
                            <div class="img_box">
                                <img src="<%request.getContextPath();%>/static/img/img_center_07.jpg">
                            </div>
                        </div>
                        <!-- //center_current_1 -->
                    </div>
                    <!-- //경기만의 마리나 시설 -->

                    <!-- 경기도 관련산업 연계 -->
                    <div class="center_sec">
                        <!-- center_tit_box -->
                        <div class="center_tit_box">
                            <div class="big">경기도 관련산업 연계</div>
                            <div class="small">
                                경기도는 한국 전체 GDP의 1/5을 차지하고 1,200만명이 넘는 인구와 함께 한국 경제성장의 엔진입니다.<br>
                                경기도는 강력한 자동차 산업과 IT, 섬유 산업을 기반으로 하고 있으며,<br>
                                252킬로미터의 해안지대와 더불어 해양레저 비즈니스 가능성을 갖추고 있습니다.<br>
                                해양레저산업은 관광, 스포츠, 제조 산업과 결합된 부가가치가 매우 높은 산업으로<br>
                                <span class="point">경기도는 동북아 해양레저산업의 허브</span>로 나아가고 있습니다.
                            </div>
                        </div>
                        <!-- //center_tit_box -->
                        <!-- center_current_3 -->
                        <div class="center_current_3">
                            <div class="box">
                                <div class="tit">한국(’11)</div>
                                <ul class="list">
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_01.png"></div>
                                        <div class="text">
                                            경기규모<br>
                                            세계 11위
                                        </div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_02.png"></div>
                                        <div class="text">
                                            조선업계<br>
                                            세계 1위
                                        </div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_03.png"></div>
                                        <div class="text">
                                            반도체와<br>
                                            TFT-LCD 주역 생산 업체
                                        </div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_04.png"></div>
                                        <div class="text">
                                            세계 4위<br>
                                            자동차 생산국
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="box">
                                <div class="tit">경기도(’11)</div>
                                <ul class="list">
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_05.png"></div>
                                        <div class="text">
                                            천이백이십사만 인구<br>
                                            (국내 최대 인구 밀집도)
                                        </div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_06.png"></div>
                                        <div class="text">
                                            243조원 지역 내<br>
                                            총 생산(국내2위)
                                        </div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_07.png"></div>
                                        <div class="text">
                                            수출 실적 875억불<br>
                                            (국내2위)
                                        </div>
                                    </li>
                                    <li>
                                        <div class="img"><img src="<%request.getContextPath();%>/static/img/img_center_medal_08.png"></div>
                                        <div class="text">
                                            54,213개의 공장<br>
                                            (국내1위)
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- //center_current_3 -->
                    </div>
                    <!-- //경기도 관련산업 연계 -->

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