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
    <div class="sub_top sub_top_comm">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>Community</span>
                    <span>Contact us</span>
                </div>
                <div class="sub_top_tit">Contact us</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Contact us</div>
                <ul class="tabOptSel">
                    <li><a href="/eng/community/notice.do">Notice</a></li>
                    <li><a href="/eng/community/news.do">SIPA-NEWS</a></li>
                    <li><a href="/eng/community/event.do">Event schedule</a></li>
                    <li class="active"><a href="/eng/community/contact.do">Contact us</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- contact_top -->
    <div class="contact_top padding_tb">
        <div class="inner">
            <div class="ct_top_box">
                <div class="tit">Contact Us</div>
                <div class="txt">
                    We look forward to your participation in the successful business of our partnership, regardless
                    of size.<br>
                    If you leave any questions and suggestions about the partnership, we will contact you after
                    reviewing it.
                </div>
                <div class="email">sipa@sipa.or.kr</div>
            </div>
        </div>
    </div>
    <!-- //contact_top -->

    <!-- contact_info -->
    <div class="contact_top padding_b">
        <div class="inner">
            <div class="ct_info_box">
                <div class="box">
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_contact_adress.png"></div>
                    <div class="txt">
                        <div class="big">ADDRESS</div>
                        <div class="small">
                            (08378) 1103, 55, Digital-ro 34-gil,<br>
                            Guro-gu, Seoul, Republic of Korea
                        </div>
                    </div>
                </div>
                <div class="box">
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_contact_tel.png"></div>
                    <div class="txt">
                        <div class="big">TEL/FAX</div>
                        <div class="small">
                            TEL: 02-867-3637<br>
                            FAX: 02-867-3639
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //contact_info -->

    <!-- contact_input -->
    <div class="contact_input padding_b">
        <div class="inner">

            <div class="ct_input_box">
                <div class="ct_input_tit">
                    <div class="tit">01. Basic Information</div>
                    <div class="cmnt"><img src="<%request.getContextPath();%>/static/img/icon_contact_star.png">Required input</div>
                </div>
                <ul>
                    <li class="w50">
                        <div class="gubun req">Company Name</div>
                        <div class="input">
                            <input type="text" placeholder="Company Name">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">Country</div>
                        <div class="input">
                            <script src="<%request.getContextPath();%>/static/js/country.js"></script>
                            <select name="country" style="width: 100%;"></select>
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">Industry</div>
                        <div class="input">
                            <input type="text" placeholder="Industry">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">Contact Name</div>
                        <div class="input">
                            <input type="text" placeholder="Contact Name">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">Position</div>
                        <div class="input">
                            <input type="text" placeholder="Position">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">Contact</div>
                        <div class="input">
                            <input type="tel" placeholder="Please enter numbers only." class="onlyTel">
                        </div>
                    </li>
                    <li class="w100">
                        <div class="gubun req">Email Address</div>
                        <div class="input">
                            <input type="email" placeholder="Email Address">
                        </div>
                    </li>
                </ul>
            </div>

            <div class="ct_input_box">
                <div class="ct_input_tit">
                    <div class="tit">02. Details of inquiry</div>
                </div>
                <ul>
                    <li class="w100">
                        <div class="gubun req">Content of inquiry</div>
                        <div class="input">
                            <textarea placeholder="Content of inquiry"></textarea>
                        </div>
                    </li>
                    <li class="w100">
                        <div class="gubun">Item</div>
                        <div class="input">
                            <label><input type="radio" name="visitRoute">Internet search</label>
                            <label><input type="radio" name="visitRoute">Media</label>
                            <label><input type="radio" name="visitRoute">Introduction of acquaintances</label>
                            <label><input type="radio" name="visitRoute">Existing member companies</label>
                            <label><input type="radio" name="visitRoute">Other</label>
                        </div>
                    </li>
                    <li class="w100">
                        <div class="input privacy">
                            <label><input type="checkbox" name="visitRoute"><span class="txt_bold">Consent to
                                        Collection and Use of Personal Information</span></label>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="ct_input_btn">
                <a href="" class="btnSt01">Register</a>
            </div>

        </div>
    </div>
    <!-- //contact_input -->


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