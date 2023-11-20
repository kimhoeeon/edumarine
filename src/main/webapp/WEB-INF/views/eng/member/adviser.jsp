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
                    <span>Adviser</span>
                </div>
                <div class="sub_top_tit">Adviser</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Adviser</div>
                <ul class="tabOptSel">
                    <li><a href="/eng/member/ascdirectors.do">Board of Directors</a></li>
                    <li class="active"><a href="/eng/member/adviser.do">Adviser</a></li>
                    <li><a href="/eng/member/consultation.do">Consultant</a></li>
                    <li><a href="/eng/member/regular.do">Regular Member</a></li>
                    <li><a href="/eng/member/ascmembers.do">Associate Member</a></li>
                    <li><a href="/eng/member/partnership.do">Cooperative Organization</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- member_list -->
    <div class="member_s padding_tb">
        <div class="inner">
            <ul class="member_list guide5">
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser01.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 곽영길</div>
                            <div class="info">
                                <p><span class="cont">아주경제신문 대표이사</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser02.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 김영호</div>
                            <div class="info">
                                <p><span class="cont">전, 산업자원부 장관, 현,서울시 고문</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser03.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 김정부</div>
                            <div class="info">
                                <p><span class="cont">진웅텔레콤 회장, 전 한전 전무</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser04.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 김종규</div>
                            <div class="info">
                                <p><span class="cont">문화유산국민신탁 이사장</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser05.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 김춘호</div>
                            <div class="info">
                                <p><span class="cont">한국 뉴욕주립대학교 총장</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser06.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 남경필</div>
                            <div class="info">
                                <p><span class="cont">경기도지사</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser07.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 문국현</div>
                            <div class="info">
                                <p><span class="cont">한솔섬유 대표, 전 창조한국당 대표</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser08.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 손풍삼</div>
                            <div class="info">
                                <p><span class="cont">전,순천향대학교 총장</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser09.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 오 명</div>
                            <div class="info">
                                <p><span class="cont">전, 경제부총리</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser10.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 원희룡</div>
                            <div class="info">
                                <p><span class="cont">제주도지사</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser11.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 장세강</div>
                            <div class="info">
                                <p><span class="cont">HLT 회장, 전 동방그룹 대표</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser12.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 전홍태</div>
                            <div class="info">
                                <p><span class="cont">전,대한전자공학회장</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser13.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 최갑홍</div>
                            <div class="info">
                                <p><span class="cont">전, 한국표준협회 회장</span></p>
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/eng/member/detailB.do">
                        <div class="img thumb34 thumbBox"><img src="<%request.getContextPath();%>/static/img/img_adviser14.jpg" class="thumbImg"></div>
                        <div class="txt">
                            <div class="name">고문위원 황희융</div>
                            <div class="info">
                                <p><span class="cont">전, 서울벤처정보대학원대학교 총장</span></p>
                            </div>
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