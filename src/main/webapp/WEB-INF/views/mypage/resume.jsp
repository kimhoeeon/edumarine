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

<c:if test="${status ne 'logon'}">
    <script>
        alert("로그인해 주세요.");
        location.href = '/member/login.do';
    </script>
</c:if>

<c:if test="${status eq 'logon'}">

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
                    <form id="joinForm" method="post" onsubmit="return false;">
                        <input type="hidden" name="seq" value="${info.seq}">
                        <div class="form_box">
                        <div class="form_tit">
                            <div class="big">나의 이력서</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun req"><p>성명</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" id="nameKo" name="nameKo" value="${info.nameKo}" placeholder="국문" class="w50">
                                        <input type="text" id="nameEn" name="nameEn" value="${info.nameEn}" placeholder="영문" class="w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>연락처</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <input type="text" id="phone" name="phone" value="${info.phone}" maxlength="13" placeholder="하이픈 자동 입력" class="onlyTel w50">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>이메일 주소</p></div>
                                <div class="naeyong">
                                    <div class="input form_email">
                                        <input type="text" id="email" name="email" value="${fn:split(info.email,'@')[0]}" placeholder="이메일 입력" class="email_input_1">
                                        <span>@</span>
                                        <input type="text" id="domain" name="domain" value="${fn:split(info.email,'@')[1]}" placeholder="도메인 입력" class="email_input_2">
                                        <select class="email_select">
                                            <c:set var="domain" value="${fn:split(info.email,'@')[1]}"/>
                                            <option selected>직접입력</option>
                                            <option value="daum.net" <c:if test="${domain eq 'daum.net'}">selected</c:if> >daum.net</option>
                                            <option value="nate.com" <c:if test="${domain eq 'nate.com'}">selected</c:if> >nate.com</option>
                                            <option value="hanmail.net" <c:if test="${domain eq 'hanmail.net'}">selected</c:if> >hanmail.net</option>
                                            <option value="naver.com" <c:if test="${domain eq 'naver.com'}">selected</c:if> >naver.com</option>
                                            <option value="hotmail.com" <c:if test="${domain eq 'hotmail.com'}">selected</c:if> >hotmail.com</option>
                                            <option value="yahoo.co.kr" <c:if test="${domain eq 'yahoo.co.kr'}">selected</c:if> >yahoo.co.kr</option>
                                            <option value="empal.com" <c:if test="${domain eq 'empal.com'}">selected</c:if> >empal.com</option>
                                            <option value="korea.com" <c:if test="${domain eq 'korea.com'}">selected</c:if> >korea.com</option>
                                            <option value="hanmir.com" <c:if test="${domain eq 'hanmir.com'}">selected</c:if> >hanmir.com</option>
                                            <option value="dreamwiz.com" <c:if test="${domain eq 'dreamwiz.com'}">selected</c:if> >dreamwiz.com</option>
                                            <option value="orgio.net" <c:if test="${domain eq 'orgio.net'}">selected</c:if> >orgio.net</option>
                                            <option value="korea.com" <c:if test="${domain eq 'korea.com'}">selected</c:if> >korea.com</option>
                                            <option value="hitel.net" <c:if test="${domain eq 'hitel.net'}">selected</c:if> >hitel.net</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>주민등록번호</p></div>
                                <div class="naeyong">
                                    <div class="input form_rrn">
                                        <input type="text" id="rrn_first" name="rrn_first" value="${fn:split(info.rrn,'-')[0]}" onchange="f_rrn_age_calc(this)" class="onlyNum" maxlength="6">
                                        <span>-</span>
                                        <input type="text" id="rrn_last" name="rrn_last" value="${fn:split(info.rrn,'-')[1]}" class="onlyNum" maxlength="7">
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>연령</p><p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        만
                                        <input type="text" id="age" name="age" value="${info.age}" class="onlyNum w50" placeholder="주민번호 앞자리 입력 시 자동 입력됩니다." maxlength="6" readonly>
                                        세
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>상반신 사진</p></div>
                                <div class="naeyong">
                                    <div class="input form_file">
                                        <input type="text" id="bodyPhoto" name="bodyPhoto" class="upload_name w50" value="" disabled="disabled">
                                        <input type="file" id="bodyPhotoFile" class="upload_hidden" accept=".png, .jpg, .jpeg">
                                        <label for="bodyPhotoFile">파일선택</label>
                                    </div>
                                    <div class="cmnt">PNG, JPG, JPEG 이미지, 10MB 이하로 등록해주세요.</div>
                                    <div class="img_replace_cmnt">
                                        <div class="btn">사진교체 방법</div>
                                        <div class="text">
                                            이미지를 수정(교체) 업로드 하신 후 하단의 [저장] 버튼을 눌러주세요. <br>
                                            반드시 저장하셔야, 미리보기 이미지가 수정된 이미지로 보입니다.
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <c:if test="${bodyPhotoFile ne null and not empty bodyPhotoFile}">
                                <li>
                                    <div class="gubun"><p>저장된 상반신 사진</p></div>
                                    <div class="naeyong">
                                        <ul>
                                            <li class="bodyPhotoFile_li" style="align-items: center;">
                                                <c:set var="bodyPhotoFileSrc" value="${fn:replace(bodyPhotoFile.fullFilePath, './usr/local/tomcat/webapps', '../../../../..')}" />
                                                <img src="${bodyPhotoFileSrc}" style="border: 1px solid #009ef7; max-width: 100px; margin-right: 10px;"/>
                                                <a href="/file/download.do?path=member/resume/${bodyPhotoFile.folderPath}&fileName=${bodyPhotoFile.fullFileName}">${bodyPhotoFile.fileName}</a>
                                                <input type="hidden" name="bodyPhotoUploadFile" id="${bodyPhotoFile.id}" value="${bodyPhotoFile.fullFilePath}">
                                                <button type="button" style="margin-left: 10px; width: 30px; height: 30px; line-height: 30px; padding: 0px;" onclick="f_file_remove(this,'${bodyPhotoFile.id}')">X</button>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                            </c:if>
                            <li>
                                <div class="gubun req"><p>주소</p></div>
                                <div class="naeyong">
                                    <div class="input form_address">
                                        <div class="address_box">
                                            <input type="text" id="address" name="address" value="${info.address}" placeholder="주소">
                                            <input type="button" onclick="execDaumPostcode('address','addressDetail')" value="주소 검색">
                                            <div id="map" style="width:300px;height:300px;margin-top:10px;display:none">
                                            </div>
                                        </div>
                                        <div class="address_box">
                                            <input type="text" id="addressDetail" name="addressDetail" value="${info.addressDetail}" placeholder="상세주소">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>상의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="topClothesSize" value="S" <c:if test="${info.topClothesSize eq 'S'}">checked</c:if> >S (90)</label>
                                        <label><input type="radio" name="topClothesSize" value="M" <c:if test="${info.topClothesSize eq 'M'}">checked</c:if> >M (95)</label>
                                        <label><input type="radio" name="topClothesSize" value="L" <c:if test="${info.topClothesSize eq 'L'}">checked</c:if> >L (100)</label>
                                        <label><input type="radio" name="topClothesSize" value="XL" <c:if test="${info.topClothesSize eq 'XL'}">checked</c:if> >XL (150)</label>
                                        <label><input type="radio" name="topClothesSize" value="기타" <c:if test="${info.topClothesSize eq '기타'}">checked</c:if> >기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>하의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="bottomClothesSize" value="28" <c:if test="${info.bottomClothesSize eq '28'}">checked</c:if> >28</label>
                                        <label><input type="radio" name="bottomClothesSize" value="30" <c:if test="${info.bottomClothesSize eq '30'}">checked</c:if> >30</label>
                                        <label><input type="radio" name="bottomClothesSize" value="32" <c:if test="${info.bottomClothesSize eq '32'}">checked</c:if> >32</label>
                                        <label><input type="radio" name="bottomClothesSize" value="34" <c:if test="${info.bottomClothesSize eq '34'}">checked</c:if> >34</label>
                                        <label><input type="radio" name="bottomClothesSize" value="36" <c:if test="${info.bottomClothesSize eq '36'}">checked</c:if> >36</label>
                                        <label><input type="radio" name="bottomClothesSize" value="기타" <c:if test="${info.bottomClothesSize eq '기타'}">checked</c:if> >기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>안전화 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="shoesSize" value="240" <c:if test="${info.shoesSize eq '240'}">checked</c:if> >240</label>
                                        <label><input type="radio" name="shoesSize" value="245" <c:if test="${info.shoesSize eq '245'}">checked</c:if> >245</label>
                                        <label><input type="radio" name="shoesSize" value="250" <c:if test="${info.shoesSize eq '250'}">checked</c:if> >250</label>
                                        <label><input type="radio" name="shoesSize" value="255" <c:if test="${info.shoesSize eq '255'}">checked</c:if> >255</label>
                                        <label><input type="radio" name="shoesSize" value="260" <c:if test="${info.shoesSize eq '260'}">checked</c:if> >260</label>
                                        <label><input type="radio" name="shoesSize" value="265" <c:if test="${info.shoesSize eq '265'}">checked</c:if> >265</label>
                                        <label><input type="radio" name="shoesSize" value="270" <c:if test="${info.shoesSize eq '270'}">checked</c:if> >270</label>
                                        <label><input type="radio" name="shoesSize" value="275" <c:if test="${info.shoesSize eq '275'}">checked</c:if> >275</label>
                                        <label><input type="radio" name="shoesSize" value="280" <c:if test="${info.shoesSize eq '280'}">checked</c:if> >280</label>
                                        <label><input type="radio" name="shoesSize" value="기타" <c:if test="${info.shoesSize eq '기타'}">checked</c:if> >기타</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun req"><p>참여 경로</p></div>
                                <div class="naeyong">
                                    <div class="input">
                                        <label><input type="radio" name="participationPath" value="인터넷" <c:if test="${info.participationPath eq '인터넷'}">checked</c:if> >인터넷</label>
                                        <label><input type="radio" name="participationPath" value="홈페이지" <c:if test="${info.participationPath eq '홈페이지'}">checked</c:if> >홈페이지</label>
                                        <label><input type="radio" name="participationPath" value="홍보물" <c:if test="${info.participationPath eq '홍보물'}">checked</c:if> >홍보물</label>
                                        <label><input type="radio" name="participationPath" value="지인추천" <c:if test="${info.participationPath eq '지인추천'}">checked</c:if> >지인추천</label>
                                        <label><input type="radio" name="participationPath" value="기타" <c:if test="${info.participationPath eq '기타'}">checked</c:if> >기타</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    </form>
                    <!-- //form box -->

                    <div class="form_btn_box">
                        <a href="javascript:void(0);" onclick="f_main_member_resume_submit()" class="btnSt01">저장</a>
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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/form.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>
</c:if>
</body>
</html>