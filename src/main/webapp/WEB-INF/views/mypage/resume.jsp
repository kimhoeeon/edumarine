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
    <title>해양레저인력양성센터</title>

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
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>마이페이지</span>
                    <span>나의 이력서</span>
                </div>
                <h2 class="sub_top_title">나의 이력서</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">마이페이지</div>
                <ul class="lnb">
                    <li><a href="/mypage/eduApplyInfo.do">교육이력조회</a></li>
                    <li class="on"><a href="/mypage/resume.do">나의 이력서</a></li>
                    <li><a href="/mypage/post.do">나의 게시글</a></li>
                    <li><a href="/mypage/modify.do">회원 정보</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_mypage">
                <div class="my_resume_wrap form_wrap">

                    <!-- form box -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">기본정보</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun req"><p>성명</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" placeholder="국문" class="w50">
                                        <input type="text" placeholder="영문" class="w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>연락처</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="tel" placeholder="하이픈(-) 없이 입력" class="onlyNum w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>이메일 주소</p></div>
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
                                <div class="gubun req"><p>주민등록번호</p></div>
                                <div class="naeyong">
                                    <div class="input form_rrn">
                                        <input type="text" class="onlyNum" maxlength="6">
                                        <span>-</span>
                                        <input type="text" class="onlyNum" maxlength="7">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>연령</p><p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" class="onlyNum w50" maxlength="6">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>상반신 사진</p></div>
                                <div class="naeyong">
                                    <div class="input form_file">
                                        <input type="text" class="upload_name w50" value="" disabled="disabled">
                                        <input type="file" id="imgFileBodyPhoto" class="upload_hidden">
                                        <label for="imgFileBodyPhoto">파일선택</label>
                                    </div>
                                    <div class="cmnt">PNG, JPG 이미지, 10MB 이하로 등록해주세요.</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>주소</p></div>
                                <div class="naeyong">
                                    <div class="input form_address">
                                        <div class="address_box">
                                            <input type="text" id="sample5_address" placeholder="주소">
                                            <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색">
                                            <div id="map" style="width:300px;height:300px;margin-top:10px;display:none">
                                            </div>
                                        </div>
                                        <div class="address_box">
                                            <input type="text" placeholder="상세주소">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>상의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_resume_shirt">S (90)</label>
                                        <label><input type="radio" name="f_resume_shirt">M (95)</label>
                                        <label><input type="radio" name="f_resume_shirt">L (100)</label>
                                        <label><input type="radio" name="f_resume_shirt">XL (150)</label>
                                        <label><input type="radio" name="f_resume_shirt">기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>하의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_resume_pants">28</label>
                                        <label><input type="radio" name="f_resume_pants">30</label>
                                        <label><input type="radio" name="f_resume_pants">32</label>
                                        <label><input type="radio" name="f_resume_pants">34</label>
                                        <label><input type="radio" name="f_resume_pants">36</label>
                                        <label><input type="radio" name="f_resume_pants">기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>안전화 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_resume_shoes">240</label>
                                        <label><input type="radio" name="f_resume_shoes">245</label>
                                        <label><input type="radio" name="f_resume_shoes">250</label>
                                        <label><input type="radio" name="f_resume_shoes">255</label>
                                        <label><input type="radio" name="f_resume_shoes">260</label>
                                        <label><input type="radio" name="f_resume_shoes">265</label>
                                        <label><input type="radio" name="f_resume_shoes">270</label>
                                        <label><input type="radio" name="f_resume_shoes">275</label>
                                        <label><input type="radio" name="f_resume_shoes">280</label>
                                        <label><input type="radio" name="f_resume_shoes">기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>참여 경로</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_resume_route">인터넷</label>
                                        <label><input type="radio" name="f_resume_route">홈페이지</label>
                                        <label><input type="radio" name="f_resume_route">홍보물</label>
                                        <label><input type="radio" name="f_resume_route">지인추천</label>
                                        <label><input type="radio" name="f_resume_route">기타</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //form box -->

                    <div class="form_btn_box">
                        <a href="javascript:void(0);" class="btnSt01">저장</a>
                        <a href="javascript:void(0);" onclick="window.print();"  class="btnSt02">출력</a>
                    </div>

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