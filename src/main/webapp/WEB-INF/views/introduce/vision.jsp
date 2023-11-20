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
    <div class="sub_top sub_top_intd">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>협회소개</span>
                    <span>비전</span>
                </div>
                <div class="sub_top_tit">비전</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">비전</div>
                <ul class="tabOptSel">
                    <li><a href="/introduce/greeting.do">인사말</a></li>
                    <li><a href="/introduce/history.do">연혁</a></li>
                    <li class="active"><a href="/introduce/vision.do">비전</a></li>
                    <li><a href="/introduce/business.do">주요사업</a></li>
                    <li><a href="/introduce/way.do">오시는길</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- sub_bd_tit -->
    <div class="padding_t">
        <div class="inner">
            <div class="sub_bd_tit">
                <div class="box">
                    <div class="big">
                        A New Leader<br>
                        in the Business Ecosystem
                    </div>
                </div>
                <div class="box">
                    <div class="small">
                        본 협회는 13,000개 IT 기업(SW, HW, 제조업)들이 입주하여 있는<br>
                        서울디지털산업단지 비즈니스 대표협회입니다.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //sub_bd_tit -->

    <!-- vs_img -->
    <div class="vs_img padding_b">
        <div class="inner">
            <div class="img_box radius15"><img src="<%request.getContextPath();%>/static/img/img_vision01.jpg"></div>
        </div>
    </div>
    <!-- //vs_img -->

    <!-- vs_value -->
    <div class="vs_value padding_b">
        <div class="inner">
            <div class="sub_bd_tit">
                <div class="box">
                    <div class="big">Core Value</div>
                </div>
            </div>
            <ul class="vs_value_box">
                <li>
                    <div class="box">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_vision01.png"></div>
                        <div class="txt">
                            <div class="big">Self-sustaining<br>power</div>
                            <div class="small">내외부 연계 및 협업을 통한 기업 자생력 강화</div>
                        </div>
                    </div>
                    <div class="boxOn">
                        <div class="txt">
                            본 협회는 기업, 대학, 정부 기관들이 상호협력을 통하여 국내외 신규시장 창출이 이뤄질 수 있도록 지원함으로써
                            기업 성장과 국가산업단지 경쟁력 확대에 이바지함을 목적으로 하고 있습니다.
                        </div>
                    </div>
                </li>
                <li>
                    <div class="box">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_vision02.png"></div>
                        <div class="txt">
                            <div class="big">Corporate<br>growth</div>
                            <div class="small">기업 전문성, 창조성, 자립성 중심의<br>지속가능한 성장 지원</div>
                        </div>
                    </div>
                    <div class="boxOn">
                        <div class="txt">
                            본 협회는 특별히 스마트 산업단지, 스마트 도시, 스마트 에너지, 디지털 그린환경 등의 분야에 기업들이 진출할 수 있도록 지원을 아끼지 않고 있습니다.
                        </div>
                    </div>
                </li>
                <li>
                    <div class="box">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_vision03.png"></div>
                        <div class="txt">
                            <div class="big">Global<br>Infrastructure</div>
                            <div class="small">내외부 협업 실행을 위한 글로벌 인프라 구축</div>
                        </div>
                    </div>
                    <div class="boxOn">
                        <div class="txt">
                            본 협회는 가능한 범위 안에서 내수보다는 해외시장 진출에 이바지함을 목표로 하고 있습니다.
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- //vs_value -->

    <!-- vs_mission -->
    <div class="vs_mission padding_tb">
        <div class="inner">
            <div class="vs_mission_box">
                <div class="img">
                    <img src="<%request.getContextPath();%>/static/img/img_vision03.png">
                </div>
                <div class="txt">
                    <div class="big">Mission</div>
                    <div class="small">
                        스마트산업진흥협회는 기업 성장 지원 및 산업 육성, 대내외 협력 인프라를 통해<br>
                        글로벌비즈니스 시장에서의 리더가 되어, 대기업, 중소기업, 정부기관,<br>
                        그리고 대학까지 연합 상생하는 것을 목표로 하고 있습니다.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //vs_mission -->


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