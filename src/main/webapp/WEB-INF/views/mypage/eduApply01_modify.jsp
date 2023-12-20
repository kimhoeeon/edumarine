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
    <title>경기해양레저 인력양성센터</title>

    <!-- 캐시를 바로 만료시킴. -->
    <meta http-equiv="Expires" content="-1" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.0) -->
    <meta http-equiv="Pragma" content="no-cache" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.1) -->
    <meta http-equiv="Cache-Control" content="no-cache" />

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

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top sub_top_my">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span><span>교육신청</span><span>상시접수</span>
                </div>
                <h2 class="sub_top_title">상시접수</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">

            <!-- content -->
            <div id="content" class="sub_apply">
                <div class="join_wrap form_wrap">

                    <!-- form box -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">기본정보</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun req">
                                    <p>이름</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" placeholder="이름 입력" class="w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>연락처</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="tel" placeholder="하이픈(-) 없이 입력" class="onlyNum w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>이메일 주소</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input form_email">
                                        <input type="email" placeholder="이메일 입력" class="email_input_1">
                                        <span>@</span>
                                        <input type="email" class="email_input_2">
                                        <select class="email_select">
                                            <option selected>직접입력</option>
                                            <option value="daum.net">daum.net</option>
                                            <option value="nate.com">nate.com</option>
                                            <option value="hanmail.net">hanmail.net</option>
                                            <option value="naver.com">naver.com</option>
                                            <option value="hotmail.com">hotmail.com</option>
                                            <option value="yahoo.co.kr">yahoo.co.kr</option>
                                            <option value="empal.com">empal.com</option>
                                            <option value="korea.com">korea.com</option>
                                            <option value="hanmir.com">hanmir.com</option>
                                            <option value="dreamwiz.com">dreamwiz.com</option>
                                            <option value="orgio.net">orgio.net</option>
                                            <option value="korea.com">korea.com</option>
                                            <option value="hitel.net">hitel.net</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>생년월일</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input form_birth">
                                        <select class="box" id="birth-year">
                                            <option disabled selected>출생 연도</option>
                                        </select>
                                        <select class="box" id="birth-month">
                                            <option disabled selected>월</option>
                                        </select>
                                        <select class="box" id="birth-day">
                                            <option disabled selected>일</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>거주지역</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" placeholder="시군구까지 입력" class="w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>참여 경로</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_route">인터넷</label>
                                        <label><input type="radio" name="f_edu_route">홈페이지</label>
                                        <label><input type="radio" name="f_edu_route">홍보물</label>
                                        <label><input type="radio" name="f_edu_route">지인추천</label>
                                        <label><input type="radio" name="f_edu_route">기타</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //form box -->

                    <!-- form box -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">신청분야</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun req">
                                    <p>1순위 신청분야</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <select>
                                            <option>교육 선택</option>
                                            <option>세일보트 엔진 테크니션</option>
                                            <option>해상엔진 테크니션(선외기 및 선내기 통합)</option>
                                            <option>FRP 레저보트 선체 정비 테크니션</option>
                                            <option>자가정비 선외기</option>
                                            <option>자가정비 선내기</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>2순위 신청분야</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <select>
                                            <option>교육 선택</option>
                                            <option>세일보트 엔진 테크니션</option>
                                            <option>해상엔진 테크니션(선외기 및 선내기 통합)</option>
                                            <option>FRP 레저보트 선체 정비 테크니션</option>
                                            <option>자가정비 선외기</option>
                                            <option>자가정비 선내기</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>희망 교육 시기</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" placeholder="" class="w100">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>전공</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" placeholder="" class="w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>경험유무</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_exp">있음</label>
                                        <label><input type="radio" name="f_edu_exp">없음</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //form box -->

                    <div class="form_btn_box">
                        <a href="javascript:void(0);" class="btnSt03 form_cancel_edu_btn">신청취소</a>
                        <a href="/mypage/eduApplyInfo.do" class="btnSt01">수정하기</a>
                    </div>

                    <!-- form_notice -->
                    <div class="form_notice_box">
                        <div class="tit_box">교육비 환불 규정</div>
                        <div class="text_box">
                            <div class="box">
                                <div class="tit">교육신청자 최소 인원 미달로 폐강시 100% 환불</div>
                                <div class="cont">
                                    <div class="text1">교육 환불 규정(정상가 기준)</div>
                                    <div class="text2">- 교육 개설 10 일 전 100%</div>
                                    <div class="text2">- 교육 개설 5 일 전 50%</div>
                                    <div class="text2">- 교육 개설 4 일전 ~ 교육당일 환불 및 취소 불가</div>
                                </div>
                            </div>
                            <div class="box">
                                <div class="cont">
                                    <div class="text1 bold">
                                        위 환불 규정에 의거하여, 해당 교육일 기준 환불수수료를 공제한 교육비가 반환됩니다.<br>
                                        (신용카드 결제 시, 카드 취소 처리 및 계좌 입금 시, 납부 계좌로 환불)
                                    </div>
                                    <div class="text1 bold">
                                        해당 교육일 기준 환불 소요기간 : 14일~20일<br>
                                        * 소비자 보호원 학원법 환불 규정적용
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //form_notice -->

                    <!-- popupCancelEdu -->
                    <div class="popup" id="popupCancelEdu">
                        <div class="popup_inner popup_form">
                            <div class="popup_box popup_form">
                                <div class="box_1">
                                    <div class="tit_box">교육 취소</div>
                                    <div class="text_box">[교육명]을 신청하셨습니다.<br>정말로 취소하시겠습니까?</div>
                                    <div class="cmnt_box"><span style="color: #C00000">10자 이상 입력해 주세요!</span></div>
                                    <div class="input_box"><input type="text" placeholder="10자 이상 입력" class="cancel_edu_reason">
                                    </div>
                                    <div class="btn_box">
                                        <a href="javascript:void(0);" class="btnSt03 btn_prev">이전</a>
                                        <a href="javascript:void(0);" class="btnSt04 btn_next">확인</a>
                                    </div>
                                </div>
                                <div class="box_2">
                                    <div class="text_box">
                                        취소 신청이 접수되었습니다.<br>
                                        담당자 승인 후 5일 내로, 취소 여부가 확정되며<br>
                                        취소 확정 여부는 추후 마이페이지를 통해<br>
                                        확인하실 수 있습니다.
                                    </div>
                                    <div class="btn_box">
                                        <a href="javascript:void(0);" class="btnSt04 btn_confirm">확인</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- //popupCancelEdu -->

                </div>
            </div>
            <!-- //content -->
        </div>
        <!-- content_wrap -->

    </div>
    <!-- //container -->

    <c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

<script src="<%request.getContextPath();%>/static/js/jquery-3.6.0.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery-migrate-3.3.0.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>

<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/form.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>