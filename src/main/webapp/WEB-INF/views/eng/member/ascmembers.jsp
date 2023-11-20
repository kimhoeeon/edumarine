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
    <div class="sub_top sub_top_member">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>Member</span>
                    <span>Associate Member</span>
                </div>
                <div class="sub_top_tit">Associate Member</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Associate Member</div>
                <ul class="tabOptSel">
                    <li><a href="/eng/member/ascdirectors.do">Board of Directors</a></li>
                    <li><a href="/eng/member/adviser.do">Adviser</a></li>
                    <li><a href="/eng/member/consultation.do">Consultant</a></li>
                    <li><a href="/eng/member/regular.do">Regular Member</a></li>
                    <li class="active"><a href="/eng/member/ascmembers.do">Associate Member</a></li>
                    <li><a href="/eng/member/partnership.do">Cooperative Organization</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- member_list -->
    <div class="member_s padding_tb">
        <div class="inner">
            <ul class="member_list guide4">
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers01.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">건아정보기술(주)</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers02.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)국제비엠에스</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers03.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)그리드씨앤티</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers04.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)김앤에이엘</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers05.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">나노미래(주)</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers06.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)나일소프트</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers07.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)네오비씨아이앤씨</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers08.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)네패스</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers09.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)넥솔위즈빌</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers10.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">넷코비나(주)</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers11.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)다산지앤지</div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailC.do">
                        <div class="img thumb169 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_ascmembers12.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">(주)닥터소프트</div>
                        </div>
                    </a>
                </li>
            </ul>

            <div class="paging">
                <a href="" class="prev"><img src="<%request.getContextPath();%>/static/img/btn_prev.gif"></a>
                <ol>
                    <li><a href="" class="this">1</a></li>
                    <li><a href="" class="other">2</a></li>
                    <li><a href="" class="other">3</a></li>
                </ol>
                <a href="" class="next"><img src="<%request.getContextPath();%>/static/img/btn_next.gif"></a>
            </div>
        </div>
    </div>
    <!-- //member_list -->


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