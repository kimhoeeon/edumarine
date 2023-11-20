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
                    <span>Contact us</span>
                </div>
                <div class="sub_top_tit">Contact us</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">Contact us</div>
                <ul class="tabOptSel">
                    <li><a href="/community/notice.do">공지사항</a></li>
                    <li><a href="/community/news.do">SIPA-NEWS</a></li>
                    <li><a href="/community/event.do">행사게시판</a></li>
                    <li class="active"><a href="/community/contact.do">Contact us</a></li>
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
                    규모에 관계없이 당사와의 파트너십을 통해 성공적인 비즈니스를 할 수 있는 귀사의 참여를 기다리고 있습니다.<br>
                    파트너십에 대한 문의 및 제안 내용을 남겨주시면 검토 후 연락 드리겠습니다.
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
                        <div class="big">주소</div>
                        <div class="small">
                            (08378) 서울특별시 구로구 디지털로 34길 55, 1103호<br>
                            (구로동, 코오롱싸이언스밸리2차)
                        </div>
                    </div>
                </div>
                <div class="box">
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_contact_tel.png"></div>
                    <div class="txt">
                        <div class="big">전화/팩스</div>
                        <div class="small">
                            전화: 02-867-3637<br>
                            팩스: 02-867-3639
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
                    <div class="tit">01. 기본정보</div>
                    <div class="cmnt"><img src="<%request.getContextPath();%>/static/img/icon_contact_star.png">필수입력</div>
                </div>
                <ul>
                    <li class="w50">
                        <div class="gubun req">회사명</div>
                        <div class="input">
                            <input type="text" placeholder="회사명">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">지역</div>
                        <div class="input sido">
                            <script src="<%request.getContextPath();%>/static/js/sido.js"></script>
                            <select name="sido"></select>
                            <select name="gugun"></select>
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">업종</div>
                        <div class="input">
                            <input type="text" placeholder="업종">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">담당자명</div>
                        <div class="input">
                            <input type="text" placeholder="담당자명">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">직위</div>
                        <div class="input">
                            <input type="text" placeholder="직위">
                        </div>
                    </li>
                    <li class="w50">
                        <div class="gubun req">연락처</div>
                        <div class="input">
                            <input type="tel" placeholder="숫자만 입력해주세요." class="onlyTel">
                        </div>
                    </li>
                    <li class="w100">
                        <div class="gubun req">이메일 주소</div>
                        <div class="input">
                            <input type="email" placeholder="이메일 주소">
                        </div>
                    </li>
                </ul>
            </div>

            <div class="ct_input_box">
                <div class="ct_input_tit">
                    <div class="tit">02. 상세문의내용</div>
                </div>
                <ul>
                    <li class="w100">
                        <div class="gubun req">문의내용</div>
                        <div class="input">
                            <textarea placeholder="문의내용"></textarea>
                        </div>
                    </li>
                    <li class="w100">
                        <div class="gubun">방문경로</div>
                        <div class="input">
                            <label><input type="radio" name="visitRoute">인터넷 검색</label>
                            <label><input type="radio" name="visitRoute">언론매체</label>
                            <label><input type="radio" name="visitRoute">지인소개</label>
                            <label><input type="radio" name="visitRoute">기존 회원사</label>
                            <label><input type="radio" name="visitRoute">기타</label>
                        </div>
                    </li>
                    <li class="w100">
                        <div class="input privacy">
                            <label><input type="checkbox" name="visitRoute"><span class="txt_bold">개인정보수집이용 동의</span></label>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="ct_input_btn">
                <a href="" class="btnSt01">문의하기</a>
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