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
                    <span>인사말</span>
                </div>
                <div class="sub_top_tit">인사말</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">인사말</div>
                <ul class="tabOptSel">
                    <li class="active"><a href="/introduce/greeting.do">인사말</a></li>
                    <li><a href="/introduce/history.do">연혁</a></li>
                    <li><a href="/introduce/vision.do">비전</a></li>
                    <li><a href="/introduce/business.do">주요사업</a></li>
                    <li><a href="/introduce/way.do">오시는길</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- sub_bd_tit -->
    <div class="padding_t gt_top">
        <div class="inner">
            <div class="sub_bd_tit">
                <div class="box">
                    <div class="big">
                        안녕하세요.<br>
                        스마트산업진흥협회입니다.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //sub_bd_tit -->

    <!-- gt_ceo_img" -->
    <div class="gt_ceo_img">
        <div class="inner">
            <div class="radius15"><img src="<%request.getContextPath();%>/static/img/img_gt_ceo.jpg"></div>
        </div>
    </div>
    <!-- //gt_ceo_img" -->

    <!-- gt_intro -->
    <div class="gt_intro padding_b">
        <div class="inner">
            <div class="gt_intro_box">
                <div class="box_l">
                    스마트산업진흥협회는<br>
                    기업 성장과 글로벌 비즈니스를 위해<br>
                    협력하고 있는 자생클러스터협회입니다.
                </div>
                <div class="box_r">
                    <div class="txt_box">
                        스마트산업진흥협회는 IT, 건설, 에너지, 문화예술 등의 여러 분야를 융합하여 신규 비즈니스를 창출한다는 목표를 가지고 공공기관·기업·대학이 공동으로 주도하여 형성된 국내 최초의 상생협회입니다.<br><br>

                        2009년 12월 창립식을 가진 이후로 현재까지 목표했던 내용대로 기업들의 여러 사업을 충실히 지원하고 있으며, 중앙정부 사업에도 적극적으로 동참하여 기업들의 정부 사업 진출에 교두보 역할을 하고 있습니다.<br><br>

                        스마트산업진흥협회는 단기적으로 기업들의 매출 증대에 이바지할 수 있도록 여러 분야에서 지원 사업을 펼치고 있습니다.<br>
                        스마트 산업단지, 스마트 에너지, 스마트 공장, ITS, IBS, 보안·방범, 콘텐츠 등의 여러 분야에서 기업들이 수익을 창출할 수 있도록 지원 사업을 확대해 나가고 있습니다.<br><br>

                        또한 장기적인 측면에서도 국내·외 도시 및 산업단지 개발 사업에 기업들이 참여하여 매출이 발생할 수 있도록 관계기관과 협력하여 다각적으로 지원 사업을 펼치고 있습니다.<br>
                        특히 21세기 들어 쟁점이 되는 환경과 관련하여 디지털 그린산업 분야에서도 전문가들과 협업하여 지원 사업을 확대해 나가고 있습니다.<br><br>

                        스마트산업진흥협회는 국내뿐 아니라 해외사업에도 큰 노력을 기울여 기업들의 해외 진출과 상품 수출에도 이바지하고 있습니다.<br>
                        그동안 아시아, 유럽, 미국 등의 여러 국가에서 다양한 성과를 쌓아오고 있습니다.<br><br>

                        앞으로도 본 협회는 기업과 대학과 공공기관의 융합과 상생이라는 구도 속에 이들 모두가 성장하고 국가산업에 이바지할 수 있도록 최선을 다하여 지원하도록 할 것입니다.<br><br>

                        아무쪼록 상기와 같은 노력을 하는 본 협회에 대해 여러분들의 많은 관심과 격려를 부탁드립니다.<br><br>

                        감사합니다.
                    </div>
                    <div class="name_box">
                        <span class="txt">사단법인 스마트산업진흥협회장</span>
                        <!-- <span class="sign"><img src="<%request.getContextPath();%>/static/img/img_gt_sign.png"></span> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //gt_intro -->

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