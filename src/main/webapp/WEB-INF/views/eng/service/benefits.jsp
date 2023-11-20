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
                    <span>Member service</span>
                    <span>Membership Benefits</span>
                </div>
                <div class="sub_top_tit">Membership Benefits</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Membership Benefits</div>
                <ul class="tabOptSel">
                    <li><a href="/eng/service/guide.do">Registration Information</a></li>
                    <li class="active"><a href="/eng/service/benefits.do">Membership Benefits</a></li>
                    <li><a href="/eng/service/faq.do">FAQ</a></li>
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
                        We'll create a better <span class="blue">business ecosystem.</span>
                    </div>
                    <div class="small">We promise to make more efforts to realize the value increase of the<br>smart industry and to promote the rights and interests of member companies.</div>
                </div>
                <ul class="cont">
                    <li>
                        <div class="box">
                            <div class="number">01</div>
                            <div class="txt">
                                We provide one-stop support from smart industry participation to promotional marketing.
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="box">
                            <div class="number">02</div>
                            <div class="txt">
                                We introduce various related projects and support projects to develop member companies and strengthen their capabilities.
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="box">
                            <div class="number">03</div>
                            <div class="txt">
                                We will solve various management difficulties of member companies that arise from running the smart industry.
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
                        <div class="big">Providing data related to the smart industry</div>
                        <div class="small">It provides data related to the smart industry so that it can proceed more conveniently and efficiently.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits02.png"></div>
                    <div class="txt">
                        <div class="big">Provision of government-supported projects and bidding information</div>
                        <div class="small">We update and provide the latest government-supported projects and bidding information at home and abroad every day.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits03.png"></div>
                    <div class="txt">
                        <div class="big">Support for overseas expansion and establishment of overseas infrastructure</div>
                        <div class="small">We provide necessary information and know-how such as market selection and strategy establishment for member companies wishing to enter overseas markets.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits04.png"></div>
                    <div class="txt">
                        <div class="big">Network exchange support</div>
                        <div class="small">Through cooperative networks with about 200 member companies, business areas such as management, consulting, and MOU can be expanded.</div>
                    </div>
                </li>
                <li>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_benefits05.png"></div>
                    <div class="txt">
                        <div class="big">Providing counseling for experts such as law, tax, and labor</div>
                        <div class="small">We support consultation with experts associated with the association in the event of various disputes during the business.</div>
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