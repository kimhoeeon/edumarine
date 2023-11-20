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
                    <span>Registration Information</span>
                </div>
                <div class="sub_top_tit">Registration Information</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Registration Information</div>
                <ul class="tabOptSel">
                    <li class="active"><a href="/eng/service/guide.do">Registration Information</a></li>
                    <li><a href="/eng/service/benefits.do">Membership Benefits</a></li>
                    <li><a href="/eng/service/faq.do">FAQ</a></li>
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
                        Member company registration information
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //sub_bd_tit -->

    <!-- reg_proc -->
    <div class="reg_proc padding_b">
        <div class="inner">
            <div class="sub_bd_tit_m">Registration procedure</div>
            <ul class="reg_proc_box">
                <li>
                    <div class="step">01</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess01.png"></div>
                    <div class="txt">
                        <div class="big">Application for subscription</div>
                        <div class="small">fax/email</div>
                    </div>
                </li>
                <li>
                    <div class="step">02</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess02.png"></div>
                    <div class="txt">
                        <div class="big">Reception and deliberation</div>
                    </div>
                </li>
                <li>
                    <div class="step">03</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess03.png"></div>
                    <div class="txt">
                        <div class="big">Payment of subscription fees and annual fees</div>
                    </div>
                </li>
                <li>
                    <div class="step">04</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess04.png"></div>
                    <div class="txt">
                        <div class="big">Approval of subscription</div>
                    </div>
                </li>
                <li>
                    <div class="step">05</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess05.png"></div>
                    <div class="txt">
                        <div class="big">Registration on the website</div>
                    </div>
                </li>
                <li>
                    <div class="step">06</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess06.png"></div>
                    <div class="txt">
                        <div class="big">Support for the association</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- //reg_proc -->

    <!-- reg_fee -->
    <div class="reg_fee padding_b">
        <div class="inner">
            <div class="sub_bd_tit_m">Subscription and Annual Fees</div>
            <div class="reg_fee_box table_box">
                <table>
                    <colgroup>
                        <col>
                        <col width="25%">
                        <col width="25%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>Sales (annual)</th>
                        <th>Subscription fee</th>
                        <th>Annual fee</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Less than 15 billion won</td>
                        <td>500,000won</td>
                        <td>500,000won</td>
                    </tr>
                    <tr>
                        <td>More than 15 billion won to less than 50 billion won</td>
                        <td>1,000,000won</td>
                        <td>1,000,000won</td>
                    </tr>
                    <tr>
                        <td>More than 50 billion won to less than 100 billion won</td>
                        <td>1,500,000won</td>
                        <td>1,500,000won</td>
                    </tr>
                    <tr>
                        <td>More than 100 billion won</td>
                        <td>2,000,000won</td>
                        <td>2,000,000won</td>
                    </tr>
                    </tbody>
                </table>
                <div class="cmnt">* The subscription fee shall be paid once for the first time, and the annual fee shall be paid once a year</div>
            </div>
        </div>
    </div>
    <!-- //reg_fee -->

    <!-- reg_guide-->
    <div class="reg_guide padding_b engSt">
        <div class="inner">
            <div class="sub_bd_tit_m">Registration Information</div>
            <div class="reg_guide_box">
                <div class="box">
                    <div class="number">01</div>
                    <div class="tit">Registration document</div>
                    <div class="txt">
                        <p>An application for membership<a href="<%request.getContextPath();%>/static/file/SIPA_협회가입신청서.hwp" class="download">Download Application</a></p>
                        <p>Business license (within 3 months of issuance)</p>
                        <p>A photograph of a representative</p>
                        <p>Company Introduction (for posting on the homepage)</p>
                    </div>
                </div>
                <div class="box">
                    <div class="number">02</div>
                    <div class="tit">Filing desk</div>
                    <div class="txt">
                        <p>E-mail: sipa@sipa.or.kr </p>
                        <p>
                            Subscription/Annual fee account :<br>
                            IBK기업은행 / 078-133031-04-010  / (사)스마트산업진흥협회
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //reg_guide -->

    <!-- reg_inquiry-->
    <div class="reg_inquiry padding_b">
        <div class="inner">
            <div class="sub_bd_tit_m">Contact information</div>
            <div class="reg_inquiry_box">
                <div class="big">SIPA Executive office</div>
                <div class="small">
                    <p><span class="txt_bold">Tel</span>02-867-3637</p>
                    <p><span class="txt_bold">E-mail</span>sipa@sipa.or.kr</p>
                </div>
            </div>
        </div>
    </div>
    <!-- //reg_inquiry -->

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