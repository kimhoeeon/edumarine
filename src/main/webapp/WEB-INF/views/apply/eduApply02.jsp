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
        <div class="sub_top sub_top_edu">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span><span>교육신청</span><span>해상엔진테크니션</span>
                </div>
                <h2 class="sub_top_title">해상엔진테크니션</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">

            <!-- content -->
            <div id="content" class="sub_apply">
                <div class="join_wrap form_wrap">

                    <!-- 기본정보 -->
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
                                        <input type="text" placeholder="국문" class="w50"><input type="text" placeholder="영문" class="w50">
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
                                            <option value="choi.com">choi.com</option>
                                            <option value="hitel.net">hitel.net</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>주민등록번호</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input form_idNum">
                                        <input type="text" class="onlyNum" maxlength="6">
                                        <span>-</span>
                                        <input type="text" class="onlyNum" maxlength="7">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>연령</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input"><input type="text" class="onlyNum" maxlength="2"></div>
                                    <div class="cmnt">만 나이로 입력해 주십시오.</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>상반신 사진</p>
                                </div>
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
                                <div class="gubun req">
                                    <p>집 주소</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input form_address">
                                        <div class="address_box">
                                            <input type="text" id="sample5_address" placeholder="주소">
                                            <input type="button" onclick="execDaumPostcode()" value="주소 검색">
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
                                <div class="gubun req">
                                    <p>상의 사이즈<span>(남여공용)</span></p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_shirt">S (90)</label>
                                        <label><input type="radio" name="f_edu_shirt">M (95)</label>
                                        <label><input type="radio" name="f_edu_shirt">L (100)</label>
                                        <label><input type="radio" name="f_edu_shirt">XL (150)</label>
                                        <label><input type="radio" name="f_edu_shirt">기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>하의 사이즈<span>(남여공용)</span></p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_pants">28</label>
                                        <label><input type="radio" name="f_edu_pants">30</label>
                                        <label><input type="radio" name="f_edu_pants">32</label>
                                        <label><input type="radio" name="f_edu_pants">34</label>
                                        <label><input type="radio" name="f_edu_pants">36</label>
                                        <label><input type="radio" name="f_edu_pants">기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>안전화 사이즈<span>(남여공용)</span></p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_shoes">240</label>
                                        <label><input type="radio" name="f_edu_shoes">245</label>
                                        <label><input type="radio" name="f_edu_shoes">250</label>
                                        <label><input type="radio" name="f_edu_shoes">255</label>
                                        <label><input type="radio" name="f_edu_shoes">260</label>
                                        <label><input type="radio" name="f_edu_shoes">265</label>
                                        <label><input type="radio" name="f_edu_shoes">270</label>
                                        <label><input type="radio" name="f_edu_shoes">275</label>
                                        <label><input type="radio" name="f_edu_shoes">280</label>
                                        <label><input type="radio" name="f_edu_shoes">기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>참여 경로</p>
                                </div>
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
                    <!-- //기본정보 -->

                    <!-- 최종학력 -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">최종학력</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun req">
                                    <p>졸업구분</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                    <div class="cmnt">예) 초졸, 중졸, 고졸, 대졸, 대학원졸 등</div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun req">
                                    <p>학교명</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun req">
                                    <p>전공</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                    <div class="cmnt">전공이 없을 시 '없음' 기재</div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //최종학력 -->

                    <!-- 경력사항 -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">경력사항</div>
                        </div>
                        <ul class="form_list formCareerBox">
                            <li class="form_list_tit">
                                경력사항 #<span class="formCareerNum">1</span>
                                <span class="del_btn formCareerDel">삭제</span>
                            </li>
                            <li class="w50">
                                <div class="gubun">
                                    <p>근무처</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun">
                                    <p>기간</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun">
                                    <p>직위</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun">
                                    <p>담당업무</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>소재지</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <div class="formAddBtn"><span class="formCareerAdd">추가</span></div>
                    </div>
                    <!-- //경력사항 -->

                    <!-- 자격면허 -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">자격면허</div>
                        </div>
                        <ul class="form_list formLicenseBox">
                            <li class="form_list_tit">
                                자격면허 #<span class="formLicenseNum">1</span>
                                <span class="del_btn formLicenseDel">삭제</span>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>자격면허명</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun">
                                    <p>취득일</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                            <li class="w50">
                                <div class="gubun">
                                    <p>발행기관</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text">
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <div class="formAddBtn"><span class="formLicenseAdd">추가</span></div>
                    </div>
                    <!-- //자격면허 -->

                    <!-- 상세정보 -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">상세정보</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun">
                                    <p>병역</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_moreinfo1">필</label>
                                        <label><input type="radio" name="f_edu_moreinfo1" class="check_etc">미필 <span>(사유 : <input type="text" class="check_etc_input">)</span></label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>장애인</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_moreinfo2">대상</label>
                                        <label><input type="radio" name="f_edu_moreinfo2">비대상</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>취업지원대상</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_moreinfo3">대상</label>
                                        <label><input type="radio" name="f_edu_moreinfo3">비대상</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>테크니션 교육 경험</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="f_edu_moreinfo4" class="check_etc">있음 <span>(교육명 : <input type="text" class="check_etc_input">)</span></label>
                                        <label><input type="radio" name="f_edu_moreinfo4">없음</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //상세정보 -->

                    <!-- 첨부파일 -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">첨부파일</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun req">
                                    <p>
                                        최종학교 졸업 (졸업예정)증명서
                                    </p>
                                </div>
                                <div class="naeyong">
                                    <div class="input form_file">
                                        <input type="text" class="upload_name w50" value="" disabled="disabled">
                                        <input type="file" id="imgCertificate1" class="upload_hidden">
                                        <label for="imgCertificate1">파일선택</label>
                                    </div>
                                    <div class="cmnt">PNG, JPG 이미지, 10MB 이하로 등록해주세요.</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>
                                        관련분야 경력증명서
                                    </p>
                                </div>
                                <div class="naeyong">
                                    <div class="input form_file">
                                        <input type="text" class="upload_name w50" value="" disabled="disabled">
                                        <input type="file" id="imgCertificate2" class="upload_hidden">
                                        <label for="imgCertificate2">파일선택</label>
                                    </div>
                                    <div class="cmnt">PNG, JPG 이미지, 10MB 이하로 등록해주세요.</div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //첨부파일 -->

                    <!-- 자기소개서 -->
                    <div class="form_box">
                        <div class="form_tit">
                            <div class="big">자기소개서</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun">
                                    <p>지원동기</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <textarea></textarea>
                                    </div>
                                    <div class="cmnt">해양레저분야 커리어 개발에 관심을 가지게 된 계기 및 배경을 서술하세요.</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>향후 테크니션 업무 수행시의 인성 적합성 기술</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <textarea></textarea>
                                    </div>
                                    <div class="cmnt">테크니션 업무 수행에서 필요로 하는 인성은 무엇인지 기술하세요.</div>
                                    <div class="cmnt">테크니션 업무 수행에서 본인이 가지고 있는 인성 적합성을 기술하세요.</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req">
                                    <p>자격 및 경력, 대외 활동 사항</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <textarea></textarea>
                                    </div>
                                    <div class="cmnt">해양레저 테크니션으로서의 미래를 앞당기기 위해 그간 준비해온 활동사항을 기술하세요</div>
                                    <div class="cmnt">이외에도 유관 경력 및 봉사활동 기술도 가능합니다.</div>
                                    <div class="cmnt">기계/전자 또는 엔진 등 학과/활동 사항 등</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>교육수료 후 포부 (향후 자신의 진로 등)</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <textarea></textarea>
                                    </div>
                                    <div class="cmnt">
                                        교육 수료 후, 테크니컬 기반의 해양레저 전문인력으로서의 향후 취업/창업 목표를 현실적으로 작성해주시기 바랍니다.<br>
                                        - 국내외 현실적인 작업환경 및 연봉(처우) 현황 및 산업분야 장래성 등을 본인이 생각하고 있는 대로 작성하시고, 본 산업계가 처한 장단점을 어떻게 극복/활용할 것인지를 기술해주시기 바랍니다.
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun">
                                    <p>기타</p>
                                </div>
                                <div class="naeyong">
                                    <div class="input">
                                        <textarea></textarea>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- //자기소개서 -->

                    <div class="form_btn_box">
                        <a href="javascript:void(0);" class="btnSt03 apply_cancel_edu_btn">취소</a>
                        <a href="/mypage/eduApplyInfo.do" class="btnSt01">신청하기</a>
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