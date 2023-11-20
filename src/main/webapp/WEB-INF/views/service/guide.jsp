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
                    <span>가입안내</span>
                </div>
                <div class="sub_top_tit">가입안내</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">가입안내</div>
                <ul class="tabOptSel">
                    <li class="active"><a href="/service/guide.do">가입안내</a></li>
                    <li><a href="/service/benefits.do">회원사혜택</a></li>
                    <li><a href="/service/faq.do">FAQ</a></li>
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
                        회원사 가입
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //sub_bd_tit -->

    <!-- reg_proc -->
    <div class="reg_proc padding_b">
        <div class="inner">
            <div class="sub_bd_tit_m">등록절차</div>
            <ul class="reg_proc_box">
                <li>
                    <div class="step">01</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess01.png"></div>
                    <div class="txt">
                        <div class="big">가입신청</div>
                        <div class="small">팩스/이메일</div>
                    </div>
                </li>
                <li>
                    <div class="step">02</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess02.png"></div>
                    <div class="txt">
                        <div class="big">접수 및 심의</div>
                    </div>
                </li>
                <li>
                    <div class="step">03</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess03.png"></div>
                    <div class="txt">
                        <div class="big">가입비 및 연회비 납부</div>
                    </div>
                </li>
                <li>
                    <div class="step">04</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess04.png"></div>
                    <div class="txt">
                        <div class="big">가입 승인</div>
                    </div>
                </li>
                <li>
                    <div class="step">05</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess05.png"></div>
                    <div class="txt">
                        <div class="big">홈페이지 등록</div>
                    </div>
                </li>
                <li>
                    <div class="step">06</div>
                    <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_regProcess06.png"></div>
                    <div class="txt">
                        <div class="big">협회 지원</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- //reg_proc -->

    <!-- reg_fee -->
    <div class="reg_fee padding_b">
        <div class="inner">
            <div class="sub_bd_tit_m">가입비 및 연회비</div>
            <div class="reg_fee_box table_box">
                <table>
                    <colgroup>
                        <col>
                        <col width="25%">
                        <col width="25%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>매출액</th>
                        <th>가입비</th>
                        <th>연회비</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>150억 미만</td>
                        <td>500,000원</td>
                        <td>500,000원</td>
                    </tr>
                    <tr>
                        <td>150억 이상 ~ 500억 미만</td>
                        <td>1,000,000원</td>
                        <td>1,000,000원</td>
                    </tr>
                    <tr>
                        <td>500억 이상 ~ 1,000억 미만</td>
                        <td>1,500,000원</td>
                        <td>1,500,000원</td>
                    </tr>
                    <tr>
                        <td>1,000억 이상</td>
                        <td>2,000,000원</td>
                        <td>2,000,000원</td>
                    </tr>
                    </tbody>
                </table>
                <div class="cmnt">* 가입비는 최초 한 번 납부하며, 연회비는 연 1회 마다 납부</div>
            </div>
        </div>
    </div>
    <!-- //reg_fee -->

    <!-- reg_guide-->
    <div class="reg_guide padding_b">
        <div class="inner">
            <div class="sub_bd_tit_m">등록안내</div>
            <div class="reg_guide_box">
                <div class="box">
                    <div class="number">01</div>
                    <div class="tit">가입 서류</div>
                    <div class="txt">
                        <p>회원가입 신청서<a href="<%request.getContextPath();%>/static/file/SIPA_협회가입신청서.hwp" class="download">신청서 다운로드</a></p>
                        <p>사업자등록증(발급 3개월 이내)</p>
                        <p>대표자 사진</p>
                        <p>회사 소개 자료(홈페이지 게시용)</p>
                    </div>
                </div>
                <div class="box">
                    <div class="number">02</div>
                    <div class="tit">서류 접수처</div>
                    <div class="txt">
                        <p>이메일 : sipa@siap.or.kr</p>
                        <p>
                            가입비/연회비 납부 계좌 :<br>
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
            <div class="sub_bd_tit_m">문의처</div>
            <div class="reg_inquiry_box">
                <div class="big">SIPA 사무국</div>
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