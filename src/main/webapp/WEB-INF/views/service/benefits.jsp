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
    <div class="sub_top sub_top_serv">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>회원 서비스</span>
                    <span>회원사혜택</span>
                </div>
                <div class="sub_top_tit">회원사혜택</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">회원사혜택</div>
                <ul class="tabOptSel">
                    <li><a href="/service/guide.do">가입안내</a></li>
                    <li class="active"><a href="/service/benefits.do">회원사혜택</a></li>
                    <li><a href="/service/faq.do">FAQ</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- benefit_top -->
    <div class="benefit_top padding_t">
        <div class="inner">
            <div class="bnf_top_box">
                <div class="tit">
                    <div class="big">
                        더 나은 <span class="blue">비즈니스 생태계</span>를 만들겠습니다.
                    </div>
                    <div class="small">스마트산업의 가치증대 실현과 회원사 권익 증진에 더욱 힘쓸 것을 약속드립니다.</div>
                </div>
                <ul class="cont">
                    <li>
                        <div class="box">
                            <div class="number">01</div>
                            <div class="txt">
                                스마트산업 참여부터<br>
                                홍보 마케팅까지<br>
                                원스톱으로 지원해드립니다.
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="box">
                            <div class="number">02</div>
                            <div class="txt">
                                회원사 발전과 역량 강화를 위해<br>
                                각종 연계 사업 및 지원사업을<br>
                                소개해드립니다.
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="box">
                            <div class="number">03</div>
                            <div class="txt">
                                스마트산업을 영위하며 발생하는<br>
                                회원사의 각종 경영상의 애로사항을<br>
                                해결해드립니다.
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //benefit_top -->

    <!-- benefit_list -->
    <div class="benefit_list padding_b">
        <div class="inner">
            <ul class="bnf_list_box">
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits01.png"></div>
                    <div class="txt">
                        <div class="big">스마트산업 관련 자료 제공</div>
                        <div class="small">보다 편리하고 효율적인 업무를 진행할 수 있도록 스마트산업과 관련된 자료를 제공합니다.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits02.png"></div>
                    <div class="txt">
                        <div class="big">정부지원 사업 및 입찰 정보 제공</div>
                        <div class="small">국내외 최신 정부지원 사업 및 입찰 정보를 매일 업데이트하여 제공합니다.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits03.png"></div>
                    <div class="txt">
                        <div class="big">해외진출 지원 및 해외 인프라 구축</div>
                        <div class="small">해외 진출을 희망하는 회원사를 위해 시장 선정, 전략 수립 등 필요 정보와 노하우를 지원합니다.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits04.png"></div>
                    <div class="txt">
                        <div class="big">네트워크 교류 지원</div>
                        <div class="small">약 200여 개 회원사와의 협력 네트워크를 통해 경영, 컨설팅, MOU 등 사업 영역을 확장시킬 수 있습니다.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits05.png"></div>
                    <div class="txt">
                        <div class="big">법률, 세무, 노무 등 전문가 상담 제공</div>
                        <div class="small">사업 중 각종 분쟁을 겪게 되었을 때 협회와 연계된 전문가와의 상담을 지원합니다.</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- //benefit_list -->


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