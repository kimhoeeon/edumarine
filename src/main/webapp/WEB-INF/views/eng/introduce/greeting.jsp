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
                    <span>Introduction</span>
                    <span>Greeting</span>
                </div>
                <div class="sub_top_tit">Greeting</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Greeting</div>
                <ul class="tabOptSel">
                    <li class="active"><a href="/eng/introduce/greeting.do">Greeting</a></li>
                    <li><a href="/eng/introduce/history.do">History</a></li>
                    <li><a href="/eng/introduce/vision.do">Vision</a></li>
                    <li><a href="/eng/introduce/business.do">Business</a></li>
                    <li><a href="/eng/introduce/way.do">Location</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- sub_bd_tit -->
    <div class="padding_t gt_top engSt">
        <div class="inner">
            <div class="sub_bd_tit">
                <div class="box">
                    <div class="big">
                        <p>Hello.</p>
                        This is the Smart Industry Promotion Association.
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
                    The Smart Industry Promotion Association is a self-sustaining cluster association that cooperates for corporate growth and global business.
                </div>
                <div class="box_r">
                    <div class="txt_box">
                        The Smart Industry Promotion Association is the first win-win association in Korea formed by public institutions, companies, and universities with the goal of creating new businesses by converging various fields such as IT, construction, energy, and culture and arts.<br><br>

                        Since the inauguration ceremony in December 2009, we have faithfully supported various projects of companies as we have aimed to date, and have actively participated in central government projects to serve as a bridge for companies to enter government projects.<br><br>

                        The Smart Industry Promotion Association is carrying out support projects in various fields to contribute to the increase in sales of companies in the short term. We are expanding support projects to help companies generate profits in various fields such as smart industrial complexes, smart energy, smart factories, ITS, IBS, security and crime prevention, and content.<br><br>

                        In addition, in the long term, we are working with related agencies to provide various support projects so that companies can participate in domestic and foreign urban and industrial complex development projects and generate sales.<br><br>

                        In particular, in relation to the controversial environment in the 21st century, we are expanding support projects in cooperation with experts in the digital green industry.<br><br>

                        The Smart Industry Promotion Association is making great efforts not only in Korea but also in overseas businesses, contributing to companies' overseas expansion and export of products. In the meantime, we have made various achievements in many countries such as Asia, Europe, and the United States.<br><br>

                        In the future, this association will continue to do its best to support all of them to grow and contribute to the national industry in the structure of convergence and co-prosperity between companies, universities, and public institutions.<br><br>

                        We ask for your interest and encouragement in this association, which makes the above efforts.<br><br>

                        Thank you.
                    </div>
                    <div class="name_box">
                        <span class="txt">President of the Smart Industry Promotion Association</span>
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