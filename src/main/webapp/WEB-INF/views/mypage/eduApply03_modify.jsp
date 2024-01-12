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
        <div class="sub_top sub_top_edu">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span><span>교육신청</span><span>FRP 레저보트 선체 정비 테크니션</span>
                </div>
                <h2 class="sub_top_title">FRP 레저보트 선체 정비 테크니션</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">

            <!-- content -->
            <div id="content" class="sub_apply">
                <div class="join_wrap form_wrap">

                    <!-- form box -->
                    <form id="joinForm" method="post" onsubmit="return false;">
                        <input type="hidden" name="seq" value="${info.seq}">
                        <input type="hidden" name="memberSeq" value="${info.memberSeq}">
                        <input type="hidden" name="trainSeq" value="${info.trainSeq}">

                        <!-- 기본정보 -->
                        <div class="form_box">
                            <div class="form_tit">
                                <div class="big">기본정보</div>
                                <div class="small">이름, 연락처, 이메일 주소는 <span style="font-weight: bold;">'마이페이지>회원정보'</span> 에서 수정 가능합니다.</div>
                            </div>
                            <ul class="form_list">
                                <li>
                                    <div class="gubun req">
                                        <p>이름</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="text" id="nameKo" name="nameKo" value="${info.nameKo}" placeholder="이름 입력" class="w50" readonly>
                                            <input type="text" id="nameEn" name="nameEn" value="${info.nameEn}" placeholder="영문" class="w50">
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req">
                                        <p>연락처</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="text" id="phone" name="phone" value="${info.phone}" maxlength="13" placeholder="하이픈 자동 입력" class="onlyTel w50" readonly>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req"><p>이메일 주소</p></div>
                                    <div class="naeyong">
                                        <div class="input form_email">
                                            <input type="text" id="email" name="email" value="${fn:split(info.email,'@')[0]}" placeholder="이메일 입력" class="email_input_1" readonly>
                                            <span>@</span>
                                            <input type="text" id="domain" name="domain" value="${fn:split(info.email,'@')[1]}" placeholder="도메인 입력" class="email_input_2" readonly>
                                            <select class="email_select">
                                                <c:set var="domain" value="${fn:split(info.email,'@')[1]}"/>
                                                <option selected disabled>직접입력</option>
                                                <option value="daum.net" <c:if test="${domain eq 'daum.net'}">selected</c:if> disabled>daum.net</option>
                                                <option value="nate.com" <c:if test="${domain eq 'nate.com'}">selected</c:if> disabled>nate.com</option>
                                                <option value="hanmail.net" <c:if test="${domain eq 'hanmail.net'}">selected</c:if> disabled>hanmail.net</option>
                                                <option value="naver.com" <c:if test="${domain eq 'naver.com'}">selected</c:if> disabled>naver.com</option>
                                                <option value="gmail.com" <c:if test="${domain eq 'gmail.com'}">selected</c:if> disabled>gmail.com</option>
                                                <option value="hotmail.com" <c:if test="${domain eq 'hotmail.com'}">selected</c:if> disabled>hotmail.com</option>
                                                <option value="yahoo.co.kr" <c:if test="${domain eq 'yahoo.co.kr'}">selected</c:if> disabled>yahoo.co.kr</option>
                                                <option value="empal.com" <c:if test="${domain eq 'empal.com'}">selected</c:if> disabled>empal.com</option>
                                                <option value="korea.com" <c:if test="${domain eq 'korea.com'}">selected</c:if> disabled>korea.com</option>
                                                <option value="hanmir.com" <c:if test="${domain eq 'hanmir.com'}">selected</c:if> disabled>hanmir.com</option>
                                                <option value="dreamwiz.com" <c:if test="${domain eq 'dreamwiz.com'}">selected</c:if> disabled>dreamwiz.com</option>
                                                <option value="orgio.net" <c:if test="${domain eq 'orgio.net'}">selected</c:if> disabled>orgio.net</option>
                                                <option value="korea.com" <c:if test="${domain eq 'korea.com'}">selected</c:if> disabled>korea.com</option>
                                                <option value="hitel.net" <c:if test="${domain eq 'hitel.net'}">selected</c:if> disabled>hitel.net</option>
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
                                            <input type="text" id="rrnFirst" name="rrnFirst" value="${fn:split(info.rrn,'-')[0]}" onchange="f_rrn_age_calc(this)" class="onlyNum" maxlength="6" placeholder="앞 6자리">
                                            <span>-</span>
                                            <input type="text" id="rrnLast" name="rrnLast" value="${fn:split(info.rrn,'-')[1]}" class="onlyNum" maxlength="7" placeholder="뒤 7자리">
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun">
                                        <p>연령</p>
                                    </div>
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
                                                교체할 이미지를 업로드 하신 후 하단의 [수정하기] 버튼을 눌러주세요.<br>
                                                이전 등록하신 이미지가 업로드하신 이미지로 교체됩니다.
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <c:if test="${bodyPhotoFile ne null and not empty bodyPhotoFile}">
                                    <li>
                                        <div class="gubun"><p>저장된 상반신 사진</p></div>
                                        <div class="naeyong">
                                            <ul>
                                                <li class="bodyPhotoFile_li" style="display: flex; align-items: center;">
                                                    <c:set var="bodyPhotoFileSrc" value="${fn:replace(bodyPhotoFile.fullFilePath, './usr/local/tomcat/webapps', '../../../../..')}" />
                                                    <img src="${bodyPhotoFileSrc}" style="border: 1px solid #009ef7; max-width: 100px; margin-right: 10px;"/>
                                                    <a href="/file/download.do?path=member/frp/${bodyPhotoFile.folderPath}&fileName=${bodyPhotoFile.fullFileName}">${bodyPhotoFile.fileName}</a>
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
                                    <div class="gubun req">
                                        <p>상의 사이즈<span>(남여공용)</span></p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <label><input type="radio" name="topClothesSize" value="S" <c:if test="${info.topClothesSize eq 'S'}">checked</c:if> >S (90)</label>
                                            <label><input type="radio" name="topClothesSize" value="M" <c:if test="${info.topClothesSize eq 'M'}">checked</c:if> >M (95)</label>
                                            <label><input type="radio" name="topClothesSize" value="L" <c:if test="${info.topClothesSize eq 'L'}">checked</c:if> >L (100)</label>
                                            <label><input type="radio" name="topClothesSize" value="XL" <c:if test="${info.topClothesSize eq 'XL'}">checked</c:if> >XL (105)</label>
                                            <label><input type="radio" name="topClothesSize" value="XXL" <c:if test="${info.topClothesSize eq 'XXL'}">checked</c:if> >XXL (110)</label>
                                            <label><input type="radio" name="topClothesSize" value="기타" <c:if test="${info.topClothesSize eq '기타'}">checked</c:if> >기타</label>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req">
                                        <p>하의 사이즈<span>(남여공용)</span></p>
                                    </div>
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
                                    <div class="gubun req">
                                        <p>안전화 사이즈<span>(남여공용)</span></p>
                                    </div>
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
                                    <div class="gubun req">
                                        <p>참여 경로</p>
                                    </div>
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
                                            <input type="text" id="gradeGbn" name="gradeGbn" value="${info.gradeGbn}" placeholder="졸업구분">
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
                                            <input type="text" id="schoolName" name="schoolName" value="${info.schoolName}" placeholder="학교명">
                                        </div>
                                    </div>
                                </li>
                                <li class="w50">
                                    <div class="gubun req">
                                        <p>전공</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <input type="text" id="major" name="major" value="${info.major}" placeholder="전공">
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
                            <c:if test="${empty careerList}">
                                <ul class="form_list formCareerBox">
                                    <li class="form_list_tit">
                                        <input type="hidden" name="careerSeq" value="">
                                        경력사항 #<span class="formCareerNum">1</span>
                                        <span class="del_btn formCareerDel">삭제</span>
                                    </li>
                                    <li class="w50">
                                        <div class="gubun">
                                            <p>근무처</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="careerPlace" placeholder="근무처">
                                            </div>
                                        </div>
                                    </li>
                                    <li class="w50">
                                        <div class="gubun">
                                            <p>기간</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="careerDate" placeholder="년.월.일 ~ 년.월.일">
                                            </div>
                                        </div>
                                    </li>
                                    <li class="w50">
                                        <div class="gubun">
                                            <p>직위</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="careerPosition" placeholder="직위">
                                            </div>
                                        </div>
                                    </li>
                                    <li class="w50">
                                        <div class="gubun">
                                            <p>담당업무</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="careerTask" placeholder="담당업무">
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="gubun">
                                            <p>소재지</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="careerLocation" placeholder="소재지">
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </c:if>

                            <c:if test="${not empty careerList}">
                                <c:forEach var="career" items="${careerList}" begin="0" end="${careerList.size()}" step="1" varStatus="status">
                                    <ul class="form_list formCareerBox">
                                        <li class="form_list_tit">
                                            <input type="hidden" name="careerSeq" value="${career.seq}">
                                            경력사항 #<span class="formCareerNum">${status.index + 1}</span>
                                            <span class="del_btn formCareerDel">삭제</span>
                                        </li>
                                        <li class="w50">
                                            <div class="gubun">
                                                <p>근무처</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="careerPlace" value="${career.careerPlace}" placeholder="근무처">
                                                </div>
                                            </div>
                                        </li>
                                        <li class="w50">
                                            <div class="gubun">
                                                <p>기간</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="careerDate" value="${career.careerDate}" placeholder="년.월.일 ~ 년.월.일">
                                                </div>
                                            </div>
                                        </li>
                                        <li class="w50">
                                            <div class="gubun">
                                                <p>직위</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="careerPosition" value="${career.careerPosition}" placeholder="직위">
                                                </div>
                                            </div>
                                        </li>
                                        <li class="w50">
                                            <div class="gubun">
                                                <p>담당업무</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="careerTask" value="${career.careerTask}" placeholder="담당업무">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="gubun">
                                                <p>소재지</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="careerLocation" value="${career.careerLocation}" placeholder="소재지">
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </c:if>

                            <div class="formAddBtn"><span class="formCareerAdd">추가</span></div>
                        </div>
                        <!-- //경력사항 -->

                        <!-- 자격면허 -->
                        <div class="form_box">
                            <div class="form_tit">
                                <div class="big">자격면허</div>
                            </div>
                            <c:if test="${empty licenseList}">
                                <ul class="form_list formLicenseBox">
                                    <li class="form_list_tit">
                                        <input type="hidden" name="licenseSeq" value="">
                                        자격면허 #<span class="formLicenseNum">1</span>
                                        <span class="del_btn formLicenseDel">삭제</span>
                                    </li>
                                    <li>
                                        <div class="gubun">
                                            <p>자격면허명</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="licenseName" placeholder="자격면허명">
                                            </div>
                                        </div>
                                    </li>
                                    <li class="w50">
                                        <div class="gubun">
                                            <p>취득일</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="licenseDate" placeholder="취득일">
                                            </div>
                                        </div>
                                    </li>
                                    <li class="w50">
                                        <div class="gubun">
                                            <p>발행기관</p>
                                        </div>
                                        <div class="naeyong">
                                            <div class="input">
                                                <input type="text" name="licenseOrg" placeholder="발행기관">
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </c:if>

                            <c:if test="${not empty licenseList}">
                                <c:forEach var="license" items="${licenseList}" begin="0" end="${licenseList.size()}" step="1" varStatus="status">
                                    <ul class="form_list formLicenseBox">
                                        <li class="form_list_tit">
                                            <input type="hidden" name="licenseSeq" value="${license.seq}">
                                            자격면허 #<span class="formLicenseNum">${status.index + 1}</span>
                                            <span class="del_btn formLicenseDel">삭제</span>
                                        </li>
                                        <li>
                                            <div class="gubun">
                                                <p>자격면허명</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="licenseName" value="${license.licenseName}" placeholder="자격면허명">
                                                </div>
                                            </div>
                                        </li>
                                        <li class="w50">
                                            <div class="gubun">
                                                <p>취득일</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="licenseDate" value="${license.licenseDate}" placeholder="취득일">
                                                </div>
                                            </div>
                                        </li>
                                        <li class="w50">
                                            <div class="gubun">
                                                <p>발행기관</p>
                                            </div>
                                            <div class="naeyong">
                                                <div class="input">
                                                    <input type="text" name="licenseOrg" value="${license.licenseOrg}" placeholder="발행기관">
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </c:if>

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
                                    <div class="gubun req">
                                        <p>병역</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <label><input type="radio" name="militaryGbn" value="필" <c:if test="${info.militaryGbn eq '필'}">checked</c:if> >필</label>
                                            <label><input type="radio" name="militaryGbn" value="미필" class="check_etc" <c:if test="${info.militaryGbn eq '미필'}">checked</c:if> >미필
                                                <span>(사유 :
                                                    <input type="text" id="militaryReason" name="militaryReason" class="check_etc_input"
                                                           <c:if test="${info.militaryGbn eq '미필'}">value="${info.militaryReason}"</c:if>
                                                           <c:if test="${info.militaryGbn eq '필'}">disabled="disabled"</c:if>
                                                    >)</span>
                                            </label>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req">
                                        <p>장애인</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <label><input type="radio" name="disabledGbn" value="대상" <c:if test="${info.disabledGbn eq '대상'}">checked</c:if> >대상</label>
                                            <label><input type="radio" name="disabledGbn" value="비대상" <c:if test="${info.disabledGbn eq '비대상'}">checked</c:if> >비대상</label>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun req">
                                        <p>취업지원대상</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <label><input type="radio" name="jobSupportGbn" value="대상" <c:if test="${info.jobSupportGbn eq '대상'}">checked</c:if> >대상</label>
                                            <label><input type="radio" name="jobSupportGbn" value="비대상" <c:if test="${info.jobSupportGbn eq '비대상'}">checked</c:if> >비대상</label>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="gubun">
                                        <p>본 사업을 알게 된 경로</p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input">
                                            <label><input type="radio" name="knowPath" value="홍보" <c:if test="${info.knowPath eq '홍보'}">checked</c:if> >홍보</label>
                                            <label><input type="radio" name="knowPath" value="추천" <c:if test="${info.knowPath eq '추천'}">checked</c:if> >추천</label>
                                            <label><input type="radio" name="knowPath" value="검색" <c:if test="${info.knowPath eq '검색'}">checked</c:if> >검색</label>
                                            <label><input type="radio" name="knowPath" value="기타"  <c:if test="${info.knowPath eq '기타'}">checked</c:if> class="check_etc">기타
                                                <span>
                                                    <input type="text" id="knowPathReason" name="knowPathReason" class="check_etc_input"
                                                           <c:if test="${info.knowPath eq '기타'}">value="${info.knowPathReason}"</c:if>
                                                           <c:if test="${info.knowPath ne '기타'}">disabled="disabled"</c:if>
                                                    ></span>
                                            </label>
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
                                            <input type="text" id="gradeLicense" name="gradeLicense" class="upload_name w50" value="" disabled="disabled">
                                            <input type="file" id="gradeLicenseFile" class="upload_hidden" accept=".png, .jpg, .jpeg">
                                            <label for="gradeLicenseFile">파일선택</label>
                                        </div>
                                        <div class="cmnt">PNG, JPG 이미지, 10MB 이하로 등록해주세요.</div>
                                    </div>
                                </li>
                                <c:if test="${gradeLicenseFile ne null and not empty gradeLicenseFile}">
                                    <li>
                                        <div class="gubun"><p>저장된 최종학교 졸업 (졸업예정)증명서</p></div>
                                        <div class="naeyong">
                                            <ul>
                                                <li class="gradeLicenseFile_li" style="display: flex; align-items: center;">
                                                    <c:set var="gradeLicenseFileSrc" value="${fn:replace(gradeLicenseFile.fullFilePath, './usr/local/tomcat/webapps', '../../../../..')}" />
                                                    <img src="${gradeLicenseFileSrc}" style="border: 1px solid #009ef7; max-width: 100px; margin-right: 10px;"/>
                                                    <a href="/file/download.do?path=member/frp/${gradeLicenseFile.folderPath}&fileName=${gradeLicenseFile.fullFileName}">${gradeLicenseFile.fileName}</a>
                                                    <input type="hidden" name="bodyPhotoUploadFile" id="${gradeLicenseFile.id}" value="${gradeLicenseFile.fullFilePath}">
                                                    <button type="button" style="margin-left: 10px; width: 30px; height: 30px; line-height: 30px; padding: 0px;" onclick="f_file_remove(this,'${gradeLicenseFile.id}')">X</button>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </c:if>
                                <li>
                                    <div class="gubun">
                                        <p>
                                            관련분야 경력증명서
                                        </p>
                                    </div>
                                    <div class="naeyong">
                                        <div class="input form_file">
                                            <input type="text" id="careerLicense" name="careerLicense" class="upload_name w50" value="" disabled="disabled">
                                            <input type="file" id="careerLicenseFile" class="upload_hidden" accept=".png, .jpg, .jpeg">
                                            <label for="careerLicenseFile">파일선택</label>
                                        </div>
                                        <div class="cmnt">PNG, JPG 이미지, 10MB 이하로 등록해주세요.</div>
                                    </div>
                                </li>
                                <c:if test="${careerLicenseFile ne null and not empty careerLicenseFile}">
                                    <li>
                                        <div class="gubun"><p>저장된 관련분야 경력증명서</p></div>
                                        <div class="naeyong">
                                            <ul>
                                                <li class="careerLicenseFile_li" style="display: flex; align-items: center;">
                                                    <c:set var="careerLicenseFileSrc" value="${fn:replace(careerLicenseFile.fullFilePath, './usr/local/tomcat/webapps', '../../../../..')}" />
                                                    <img src="${careerLicenseFileSrc}" style="border: 1px solid #009ef7; max-width: 100px; margin-right: 10px;"/>
                                                    <a href="/file/download.do?path=member/frp/${careerLicenseFile.folderPath}&fileName=${careerLicenseFile.fullFileName}">${careerLicenseFile.fileName}</a>
                                                    <input type="hidden" name="bodyPhotoUploadFile" id="${careerLicenseFile.id}" value="${careerLicenseFile.fullFilePath}">
                                                    <button type="button" style="margin-left: 10px; width: 30px; height: 30px; line-height: 30px; padding: 0px;" onclick="f_file_remove(this,'${careerLicenseFile.id}')">X</button>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </c:if>
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
                                            <textarea id="applyReason" name="applyReason" placeholder="지원동기">${info.applyReason}</textarea>
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
                                            <textarea id="techReason" name="techReason" placeholder="향후 테크니션 업무 수행시의 인성 적합성 기술">${info.techReason}</textarea>
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
                                            <textarea id="activityReason" name="activityReason" placeholder="자격 및 경력, 대외 활동 사항">${info.activityReason}</textarea>
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
                                            <textarea id="planReason" name="planReason" placeholder="교육수료 후 포부 (향후 자신의 진로 등)">${info.planReason}</textarea>
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
                                            <textarea id="etcReason" name="etcReason" placeholder="기타 사항을 입력해 주세요.">${info.etcReason}</textarea>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- //자기소개서 -->

                    </form>
                    <!-- form box -->

                    <div class="form_btn_box">
                        <a href="javascript:void(0);" value="${info.seq}" class="btnSt03 form_cancel_edu_btn">신청취소</a>
                        <a href="javascript:void(0);" onclick="f_main_apply_eduApply03_modify_submit('${info.seq}');" class="btnSt01">수정하기</a>
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
                                    <div class="text_box">[ FRP 레저보트 선체 정비 테크니션 ]을 신청하셨습니다.<br>정말로 취소하시겠습니까?</div>
                                    <div class="cmnt_box"><span style="color: #C00000">취소 사유를 10자 이상 입력해 주세요!</span></div>
                                    <div class="input_box"><input type="text" placeholder="취소 사유 10자 이상 입력" class="cancel_edu_reason">
                                    </div>
                                    <div class="btn_box">
                                        <a href="javascript:void(0);" class="btnSt03 btn_prev">이전</a>
                                        <a href="javascript:void(0);" class="btnSt04 btn_next" onclick="f_edu_apply_cancel_btn('${info.seq}','FRP 레저보트 선체 정비 테크니션')">확인</a>
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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/form.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

</c:if>
</body>
</html>