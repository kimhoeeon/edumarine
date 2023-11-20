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
                    <span>커뮤니티</span>
                    <span>SIPA-NEWS</span>
                </div>
                <div class="sub_top_tit">SIPA-NEWS</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">SIPA-NEWS</div>
                <ul class="tabOptSel">
                    <li><a href="/community/notice.do">공지사항</a></li>
                    <li class="active"><a href="/community/news.do">SIPA-NEWS</a></li>
                    <li><a href="/community/event.do">행사게시판</a></li>
                    <li><a href="/community/contact.do">Contact us</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- board_gallery -->
    <div class="board_gallery padding_tb">
        <div class="inner">
            <form class="search_box">
                <select>
                    <option>제목</option>
                    <option>내용</option>
                    <option>제목+내용</option>
                </select>
                <div class="input">
                    <input type="text" placeholder="검색어를 입력해주세요.">
                    <button type="submit"><img src="<%request.getContextPath();%>/static/img/icon_search.png"></button>
                </div>
            </form>

            <div class="board_gallery_box">
                <ul>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                    <li class="viewGallery">
                        <div class="thumbBox thumb75"><img src="<%request.getContextPath();%>/static/img/img_sample.jpg" class="thumbImg"></div>
                        <div class="txt_box">
                            <div class="tit">SIPA-NEWS 제목 노출</div>
                            <div class="date">2023.08.04</div>
                        </div>
                    </li>
                </ul>
            </div>

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
    <!-- //board_gallery -->

    <!-- popGallery -->
    <div class="popGallery popup">
        <div class="popup_inner">
            <div class="popup_wrap">
                <div class="pop_tit">
                    <div class="tit">게시물 제목 노출</div>
                    <a class="close" href="#close"><img src="<%request.getContextPath();%>/static/img/icon_close_w.png"></a>
                </div>
                <div class="pop_box">
                    <div class="pop_cont">
                        <!-- 슬라이드박스 -->
                        <div class="swiper_box">
                            <div class="swiper swiper_gallery">
                                <ul class="swiper-wrapper">
                                    <li class="swiper-slide">
                                        <div class="thumb75 thumbBox"><img class="thumbImg" src="<%request.getContextPath();%>/static/img/img_sample.jpg"></div>
                                        <div class="txt_box">사진설명노출</div>
                                    </li>
                                    <li class="swiper-slide">
                                        <div class="thumb75 thumbBox"><img class="thumbImg" src="<%request.getContextPath();%>/static/img/img_sample.jpg"></div>
                                        <div class="txt_box">사진설명노출</div>
                                    </li>
                                    <li class="swiper-slide">
                                        <div class="thumb75 thumbBox"><img class="thumbImg" src="<%request.getContextPath();%>/static/img/img_sample.jpg"></div>
                                        <div class="txt_box">사진설명노출</div>
                                    </li>
                                </ul>
                            </div>
                            <div class="swiper-button-prev swiper_gallery_prev"></div>
                            <div class="swiper-button-next swiper_gallery_next"></div>
                        </div>
                        <!-- //슬라이드박스 -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //popGallery -->

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