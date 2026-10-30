<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/mng/inc/_top.jspf" %>
<%@ include file="/WEB-INF/views/mng/inc/_head.jspf" %>
</head>
<%@ include file="/WEB-INF/views/mng/inc/_body_open.jspf" %>
<c:if test="${sessionScope.get('status') eq 'logon'}">
<%@ include file="/WEB-INF/views/mng/inc/_app_open.jspf" %>
<%@ include file="/WEB-INF/views/mng/inc/_header.jspf" %>
<%@ include file="/WEB-INF/views/mng/inc/_sidebar_main_open.jspf" %>
                        <!--begin::Toolbar-->
                        <div id="kt_app_toolbar" class="app-toolbar py-3 py-lg-6">
                            <!--begin::Toolbar container-->
                            <div id="kt_app_toolbar_container" class="app-container container-full d-flex flex-stack">
                                <!--begin::Page title-->
                                <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                                    <!--begin::Title-->
                                    <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                                        해상엔진 테크니션 (선내기/선외기)</h1>
                                    <!--end::Title-->
                                    <!--begin::Breadcrumb-->
                                    <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">
                                            <a href="/mng/main.do" class="text-muted text-hover-primary">Home</a>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">회원 / 신청</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">신청자 목록</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">해상엔진 테크니션 (선내기/선외기)</li>
                                        <!--end::Item-->
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Page title-->
                                <!--begin::Actions-->
                                <div class="d-flex align-items-center gap-2 gap-lg-3">
                                    <!--begin::Filter menu-->
                                    <!--end::Filter menu-->
                                    <!--begin::Secondary button-->
                                    <!--end::Secondary button-->
                                    <!--begin::Primary button-->
                                    <!--end::Primary button-->
                                </div>
                                <!--end::Actions-->
                            </div>
                            <!--end::Toolbar container-->
                        </div>
                        <!--end::Toolbar-->
                        <!--begin::Content-->
                        <div id="kt_app_content" class="app-content flex-column-fluid">
                            <!--begin::Content container-->
                            <div id="kt_app_content_container" class="app-container container-full">

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::form-->
                                    <form id="dataForm" method="post" onsubmit="return false;">
                                        <%-- SEQ 값--%>
                                        <input type="hidden" id="userSeq" name="seq" value="${info.seq}">
                                        <!--begin::Card header-->
                                        <div class="card-header border-0">
                                            <!--begin::Card title-->
                                            <div class="card-title m-0">
                                                <h3 class="fw-bold m-0">신청 정보</h3>
                                            </div>
                                            <!--end::Card title-->
                                        </div>
                                        <!--end::Card header-->
                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">교육차시</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="nextTime" name="nextTime" class="form-control form-control-lg form-control-solid-bg" placeholder="교육차시" value="${info.nextTime} 차시" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">이름</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <!--begin::Row-->
                                                    <div class="row">
                                                        <!--begin::Col-->
                                                        <div class="col-lg-6">
                                                            <input type="text" id="nameKo" name="nameKo" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="이름(국문)" value="${memberInfo.name}" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                        <!--begin::Col-->
                                                        <div class="col-lg-6">
                                                            <input type="text" id="nameEn" name="nameEn" class="form-control form-control-lg form-control-solid-bg" placeholder="이름(영문)" value="${memberInfo.nameEn}" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                    </div>
                                                    <!--end::Row-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">연락처</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="phone" name="phone" class="form-control form-control-lg form-control-solid-bg onlyTel" placeholder="연락처" value="${memberInfo.phone}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">이메일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <div class="input-group">
                                                        <c:set var="email1" value="${fn:split(memberInfo.email,'@')[0]}" />
                                                        <c:set var="email2" value="${fn:split(memberInfo.email,'@')[1]}" />
                                                        <input type="text" id="email" name="email" value="${email1}" class="form-control form-control-solid-bg" placeholder="이메일" readonly/>
                                                        <span class="input-group-text">@</span>
                                                        <input type="text" id="domain" name="domain" value="${email2}" class="form-control form-control-solid-bg" placeholder="도메인" readonly/>
                                                        <select id="email_select" class="form-select form-control-solid-bg ms-4" aria-label="Select Email" disabled>
                                                            <option selected>직접입력</option>
                                                            <option value="daum.net" <c:if test="${email2 eq 'daum.net'}">selected</c:if> >daum.net</option>
                                                            <option value="nate.com" <c:if test="${email2 eq 'nate.com'}">selected</c:if> >nate.com</option>
                                                            <option value="hanmail.net" <c:if test="${email2 eq 'hanmail.net'}">selected</c:if> >hanmail.net</option>
                                                            <option value="naver.com" <c:if test="${email2 eq 'naver.com'}">selected</c:if> >naver.com</option>
                                                            <option value="gmail.com" <c:if test="${email2 eq 'gmail.com'}">selected</c:if> >gmail.com</option>
                                                            <option value="hotmail.com" <c:if test="${email2 eq 'hotmail.com'}">selected</c:if> >hotmail.com</option>
                                                            <option value="yahoo.co.kr" <c:if test="${email2 eq 'yahoo.co.kr'}">selected</c:if> >yahoo.co.kr</option>
                                                            <option value="empal.com" <c:if test="${email2 eq 'empal.com'}">selected</c:if> >empal.com</option>
                                                            <option value="korea.com" <c:if test="${email2 eq 'korea.com'}">selected</c:if> >korea.com</option>
                                                            <option value="hanmir.com" <c:if test="${email2 eq 'hanmir.com'}">selected</c:if> >hanmir.com</option>
                                                            <option value="dreamwiz.com" <c:if test="${email2 eq 'dreamwiz.com'}">selected</c:if> >dreamwiz.com</option>
                                                            <option value="orgio.net" <c:if test="${email2 eq 'orgio.net'}">selected</c:if> >orgio.net</option>
                                                            <option value="korea.com" <c:if test="${email2 eq 'korea.com'}">selected</c:if> >korea.com</option>
                                                            <option value="hitel.net" <c:if test="${email2 eq 'hitel.net'}">selected</c:if> >hitel.net</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">생년월일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <div class="input-group">
                                                        <input type="text" id="birthYear" name="birthYear" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="출생연도" value="${memberInfo.birthYear} 년" readonly/>
                                                        <input type="text" id="birthMonth" name="birthMonth" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="출생월" value="${memberInfo.birthMonth} 월" readonly/>
                                                        <input type="text" id="birthDay" name="birthDay" class="form-control form-control-lg form-control-solid-bg" placeholder="출생일" value="${memberInfo.birthDay} 일" readonly/>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">성별</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="sex" value="남성" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${memberInfo.sex eq '남성'}">checked</c:if> disabled/> 남성
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="sex" value="여성" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${memberInfo.sex eq '여성'}">checked</c:if> disabled/> 여성
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">주소</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <!--begin::Row-->
                                                    <div class="row">
                                                        <!--begin::Col-->
                                                        <div class="col-lg-12">
                                                            <input type="text" id="address" name="address" class="form-control form-control-lg form-control-solid-bg" placeholder="주소" value="${memberInfo.address}" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                            <%--<!--begin::Col-->
                                                            <div class="col-lg-2">
                                                                <button type="button" class="btn btn-primary" onclick="execDaumPostcode('address','addressDetail')">주소 검색</button>
                                                            </div>
                                                            <!--end::Col-->--%>
                                                    </div>
                                                    <!--end::Row-->
                                                    <!--begin::Row-->
                                                    <div class="row mt-3">
                                                        <!--begin::Col-->
                                                        <div class="col-lg-12">
                                                            <input type="text" id="addressDetail" name="addressDetail" class="form-control form-control-lg form-control-solid-bg" value="${memberInfo.addressDetail}" placeholder="상세주소" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                    </div>
                                                    <!--end::Row-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">상반신 사진</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <ul>
                                                        <c:if test="${bodyPhotoFileInfo ne null}">
                                                            <li class="mb-4">
                                                                <c:set var="fileSrc" value="${fn:replace(bodyPhotoFileInfo.fullFilePath, '/usr/local/tomcat/webapps', '/../../../..')}" />
                                                                <c:if test="${not fn:contains(fileSrc, '.ai') and not fn:contains(fileSrc, '.pdf')}">
                                                                    <img src="${fileSrc}" class="w-350px mr10" style="border: 1px solid #009ef7"/>
                                                                </c:if>
                                                                <a href="/file/download.do?path=member/boarder/${bodyPhotoFileInfo.folderPath}&fileName=${bodyPhotoFileInfo.fullFileName}">
                                                                    <c:choose>
                                                                        <c:when test="${bodyPhotoFileInfo.uuid ne null and bodyPhotoFileInfo.uuid ne ''}">
                                                                            ${bodyPhotoFileInfo.fileName}
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${bodyPhotoFileInfo.fullFileName}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </a>
                                                                <%--<input type="hidden" name="uploadFile" id="${bodyPhotoFileInfo.id}" value="${bodyPhotoFileInfo.fullFilePath}">
                                                                <button type="button" class="ml10" onclick="f_file_remove(this, '${bodyPhotoFileInfo.id}')">
                                                                    <i class="ki-duotone ki-abstract-11">
                                                                        <i class="path1"></i>
                                                                        <i class="path2"></i>
                                                                    </i>
                                                                </button>--%>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">상의 사이즈 (남여공용)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="topClothesSize" value="S" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.topClothesSize eq 'S'}">checked</c:if> disabled/> S (90)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="topClothesSize" value="M" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.topClothesSize eq 'M'}">checked</c:if> disabled/> M (95)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="topClothesSize" value="L" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.topClothesSize eq 'L'}">checked</c:if> disabled/> L (100)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="topClothesSize" value="XL" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.topClothesSize eq 'XL'}">checked</c:if> disabled/> XL (105)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="topClothesSize" value="XXL" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.topClothesSize eq 'XXL'}">checked</c:if> disabled/> XXL (110)
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="topClothesSize" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.topClothesSize eq '기타'}">checked</c:if> disabled/> 기타
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">하의 사이즈 (남여공용)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="bottomClothesSize" value="28" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.bottomClothesSize eq '28'}">checked</c:if> disabled/> 28
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="bottomClothesSize" value="30" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.bottomClothesSize eq '30'}">checked</c:if> disabled/> 30
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="bottomClothesSize" value="32" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.bottomClothesSize eq '32'}">checked</c:if> disabled/> 32
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="bottomClothesSize" value="34" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.bottomClothesSize eq '34'}">checked</c:if> disabled/> 34
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="bottomClothesSize" value="36" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.bottomClothesSize eq '36'}">checked</c:if> disabled/> 36
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="bottomClothesSize" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.bottomClothesSize eq '기타'}">checked</c:if> disabled/> 기타
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">안전화 사이즈 (남여공용)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="240" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '240'}">checked</c:if> disabled/> 240
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="245" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '245'}">checked</c:if> disabled/> 245
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="250" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '250'}">checked</c:if> disabled/> 250
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="255" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '255'}">checked</c:if> disabled/> 255
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="260" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '260'}">checked</c:if> disabled/> 260
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="265" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '265'}">checked</c:if> disabled/> 265
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="270" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '270'}">checked</c:if> disabled/> 270
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="275" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '275'}">checked</c:if> disabled/> 275
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="shoesSize" value="280" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '280'}">checked</c:if> disabled/> 280
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="shoesSize" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.shoesSize eq '기타'}">checked</c:if> disabled/> 기타
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">참여 경로</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="인터넷" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '인터넷'}">checked</c:if> disabled/> 인터넷
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="홈페이지" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '홈페이지'}">checked</c:if> disabled/> 홈페이지
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="홍보물" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '홍보물'}">checked</c:if> disabled/> 홍보물
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="지인추천" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '지인추천'}">checked</c:if> disabled/> 지인추천
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="participationPath" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '기타'}">checked</c:if> disabled/> 기타
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                        </div>
                                        <!--end::Card body-->
                                    </form>
                                    <!--end::form-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">최종 학력</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">졸업구분</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" id="gradeGbn" name="gradeGbn" class="form-control form-control-lg form-control-solid-bg" placeholder="졸업구분" value="${info.gradeGbn}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">학교명</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" id="schoolName" name="schoolName" class="form-control form-control-lg form-control-solid-bg" placeholder="학교명" value="${info.schoolName}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">전공</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" id="major" name="major" class="form-control form-control-lg form-control-solid-bg" placeholder="전공" value="${info.major}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">최종학교 졸업증명서</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <ul>
                                                    <c:if test="${gradeLicenseFileInfo ne null}">
                                                        <li class="mb-4">
                                                            <c:set var="fileSrc" value="${fn:replace(gradeLicenseFileInfo.fullFilePath, '/usr/local/tomcat/webapps', '/../../../..')}" />
                                                            <c:if test="${not fn:contains(fileSrc, '.ai') and not fn:contains(fileSrc, '.pdf')}">
                                                                <img src="${fileSrc}" class="w-350px mr10" style="border: 1px solid #009ef7"/>
                                                            </c:if>
                                                            <a href="/file/download.do?path=member/boarder/${gradeLicenseFileInfo.folderPath}&fileName=${gradeLicenseFileInfo.fullFileName}">
                                                                <c:choose>
                                                                    <c:when test="${gradeLicenseFileInfo.uuid ne null and gradeLicenseFileInfo.uuid ne ''}">
                                                                        ${gradeLicenseFileInfo.fileName}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${gradeLicenseFileInfo.fullFileName}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </a>
                                                                <%--<input type="hidden" name="uploadFile" id="${gradeLicenseFileInfo.id}" value="${gradeLicenseFileInfo.fullFilePath}">
                                                                <button type="button" class="ml10" onclick="f_file_remove(this, '${gradeLicenseFileInfo.id}')">
                                                                    <i class="ki-duotone ki-abstract-11">
                                                                        <i class="path1"></i>
                                                                        <i class="path2"></i>
                                                                    </i>
                                                                </button>--%>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">경력사항</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <c:if test="${not empty careerList}">
                                            <c:forEach var="careerInfo" items="${careerList}" begin="0" end="${careerList.size()}" step="1" varStatus="status">
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-12 col-form-label fw-semibold fs-6" style="background-color: #083274; color: #FFFFFF; font-weight: bold;">경력사항 ${status.index + 1}</label>
                                                    <!--end::Label-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">근무처</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="careerPlace" name="careerPlace" class="form-control form-control-lg form-control-solid-bg" placeholder="근무처" value="${careerInfo.careerPlace}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">기간</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="careerDate" name="careerDate" class="form-control form-control-lg form-control-solid-bg" placeholder="기간" value="${careerInfo.careerDate}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">직위</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="careerPosition" name="careerPosition" class="form-control form-control-lg form-control-solid-bg" placeholder="직위" value="${careerInfo.careerPosition}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">담당업무</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="careerTask" name="careerTask" class="form-control form-control-lg form-control-solid-bg" placeholder="담당업무" value="${careerInfo.careerTask}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">소재지</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="careerLocation" name="careerLocation" class="form-control form-control-lg form-control-solid-bg" placeholder="소재지" value="${careerInfo.careerLocation}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6 required">관련분야 경력증명서</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <ul>
                                                            <c:if test="${careerLicenseFileList ne null and not empty careerLicenseFileList}">
                                                                <c:forEach var="careerLicenseFile" items="${careerLicenseFileList}" begin="0" end="${careerLicenseFileList.size()}" step="1">
                                                                <c:set var="idx" value="${status.index + 1}"/>
                                                                <c:if test="${fn:contains(careerLicenseFile.note, 'careerLicense'.concat(idx.toString()))}">
                                                                <li class="mb-4">
                                                                    <c:set var="fileSrc" value="${fn:replace(careerLicenseFile.fullFilePath, '/usr/local/tomcat/webapps', '/../../../..')}" />
                                                                    <c:if test="${not fn:contains(fileSrc, '.ai') and not fn:contains(fileSrc, '.pdf')}">
                                                                        <img src="${fileSrc}" class="w-350px mr10" style="border: 1px solid #009ef7"/>
                                                                    </c:if>
                                                                    <a href="/file/download.do?path=member/boarder/${careerLicenseFile.folderPath}&fileName=${careerLicenseFile.fullFileName}">
                                                                        <c:choose>
                                                                            <c:when test="${careerLicenseFile.uuid ne null and careerLicenseFile.uuid ne ''}">
                                                                                ${careerLicenseFile.fileName}
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                ${careerLicenseFile.fullFileName}
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </a>
                                                                        <%--<input type="hidden" name="uploadFile" id="${careerLicenseFileInfo.id}" value="${careerLicenseFileInfo.fullFilePath}">
                                                                        <button type="button" class="ml10" onclick="f_file_remove(this, '${careerLicenseFileInfo.id}')">
                                                                            <i class="ki-duotone ki-abstract-11">
                                                                                <i class="path1"></i>
                                                                                <i class="path2"></i>
                                                                            </i>
                                                                        </button>--%>
                                                                </li>
                                                                </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </ul>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty careerList}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-12 col-form-label fw-semibold fs-6">등록된 경력사항 없음</label>
                                                <!--end::Label-->
                                            </div>
                                            <!--end::Input group-->
                                        </c:if>

                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">자격면허</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <c:if test="${not empty licenseList}">
                                            <c:forEach var="licenseInfo" items="${licenseList}" begin="0" end="${licenseList.size()}" step="1" varStatus="status">
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-12 col-form-label fw-semibold fs-6" style="background-color: #083274; color: #FFFFFF; font-weight: bold;">자격면허 ${status.index + 1}</label>
                                                    <!--end::Label-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">자격면허명</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="licenseName" name="licenseName" class="form-control form-control-lg form-control-solid-bg" placeholder="자격면허명" value="${licenseInfo.licenseName}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">취득일</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="licenseDate" name="licenseDate" class="form-control form-control-lg form-control-solid-bg" placeholder="취득일" value="${licenseInfo.licenseDate}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-4 col-form-label fw-semibold fs-6">발행기관</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-8">
                                                        <input type="text" id="licenseOrg" name="licenseOrg" class="form-control form-control-lg form-control-solid-bg" placeholder="발행기관" value="${licenseInfo.licenseOrg}" readonly/>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty licenseList}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-12 col-form-label fw-semibold fs-6">등록된 자격먼허 없음</label>
                                                <!--end::Label-->
                                            </div>
                                            <!--end::Input group-->
                                        </c:if>

                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">상세정보</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">병역</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8 d-flex align-items-center">
                                                <label class="me-5">
                                                    <input type="radio" name="militaryGbn" value="필" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.militaryGbn eq '필'}">checked</c:if> disabled/> 필
                                                </label>
                                                <label>
                                                    <input type="radio" name="militaryGbn" value="미필" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.militaryGbn eq '미필'}">checked</c:if> disabled/> 미필
                                                </label>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">병역 미필 사유</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" id="militaryReason" name="militaryReason" class="form-control form-control-lg form-control-solid-bg" placeholder="병역 미필 사유" value="${info.militaryReason}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">장애인</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8 d-flex align-items-center">
                                                <label class="me-5">
                                                    <input type="radio" name="disabledGbn" value="대상" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.disabledGbn eq '대상'}">checked</c:if> disabled/> 대상
                                                </label>
                                                <label>
                                                    <input type="radio" name="disabledGbn" value="비대상" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.disabledGbn eq '비대상'}">checked</c:if> disabled/> 비대상
                                                </label>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">취업지원대상</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8 d-flex align-items-center">
                                                <label class="me-5">
                                                    <input type="radio" name="jobSupportGbn" value="대상" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.jobSupportGbn eq '대상'}">checked</c:if> disabled/> 대상
                                                </label>
                                                <label>
                                                    <input type="radio" name="jobSupportGbn" value="비대상" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.jobSupportGbn eq '비대상'}">checked</c:if> disabled/> 비대상
                                                </label>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">테크니션 교육 경험</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8 d-flex align-items-center">
                                                <label class="me-5">
                                                    <input type="radio" name="techEduGbn" value="있음" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.techEduGbn eq '있음'}">checked</c:if> disabled/> 있음
                                                </label>
                                                <label>
                                                    <input type="radio" name="techEduGbn" value="없음" class="form-check-input form-control-solid-bg"
                                                           <c:if test="${info.techEduGbn eq '없음'}">checked</c:if> disabled/> 없음
                                                </label>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">테크니션 교육 경험명</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" id="techEduName" name="techEduName" class="form-control form-control-lg form-control-solid-bg" placeholder="테크니션 교육 경험명" value="${info.techEduName}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->

                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">자기소개서</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6">지원동기</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <textarea id="applyReason" name="applyReason" class="form-control form-control-solid-bg resize-none h-200px" placeholder="지원동기">${info.applyReason}</textarea>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6">향후 테크니션 업무 수행시의 인성 적합성 기술</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <textarea id="techReason" name="techReason" class="form-control form-control-solid-bg resize-none h-200px" placeholder="향후 테크니션 업무 수행시의 인성 적합성 기술">${info.techReason}</textarea>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6">자격 및 경력, 대외 활동 사항</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <textarea id="activityReason" name="activityReason" class="form-control form-control-solid-bg resize-none h-200px" placeholder="자격 및 경력, 대외 활동 사항">${info.activityReason}</textarea>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6">교육수료 후 포부 (향후 자신의 진로 등)</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <textarea id="planReason" name="planReason" class="form-control form-control-solid-bg resize-none h-200px" placeholder="교육수료 후 포부 (향후 자신의 진로 등)">${info.planReason}</textarea>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6">기타</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <textarea id="etcReason" name="etcReason" class="form-control form-control-solid-bg resize-none h-200px" placeholder="기타">${info.etcReason}</textarea>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->

                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">신청상태</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">상태</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" name="applyStatus" class="form-control form-control-lg form-control-solid-bg" placeholder="상태" value="${info.applyStatus}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">취소신청일시</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" name="cancelDttm" class="form-control form-control-lg form-control-solid-bg" placeholder="취소신청일시" value="${info.cancelDttm}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-4 col-form-label fw-semibold fs-6 required">취소사유</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-8">
                                                <input type="text" name="cancelReason" class="form-control form-control-lg form-control-solid-bg" placeholder="취소사유" value="${info.cancelReason}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">결제정보</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <c:if test="${paymentInfo ne null}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">결제방식</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'card')}">
                                                        <c:set var="payMethod" value="카드"/>
                                                    </c:if>
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'vbank')}">
                                                        <c:set var="payMethod" value="가상계좌"/>
                                                    </c:if>
                                                    <input type="text" name="payMethod" class="form-control form-control-lg form-control-solid-bg" placeholder="결제방식" value="${payMethod}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">결제정보</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'card')}">
                                                        <input type="text" name="payMethod" class="form-control form-control-lg form-control-solid-bg" placeholder="결제방식" value="${paymentInfo.cardPurchaseName} (${paymentInfo.cardNum})" readonly/>
                                                    </c:if>
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'vbank')}">
                                                        <input type="text" name="payMethod" class="form-control form-control-lg form-control-solid-bg" placeholder="결제방식" value="${paymentInfo.vactBankName} (${paymentInfo.vactNum})" readonly/>
                                                    </c:if>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <fmt:parseDate var="applDate" value="${paymentInfo.applDate}${paymentInfo.applTime}" pattern="yyyyMMddHHmmss" />
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">승인일자</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" name="applDate" class="form-control form-control-lg form-control-solid-bg" placeholder="승인일자" value="<fmt:formatDate value="${applDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">결제금액</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" name="totPrice" class="form-control form-control-lg form-control-solid-bg" placeholder="결제금액" value="<fmt:formatNumber value="${paymentInfo.totPrice}" type="currency" maxFractionDigits="0" currencySymbol="￦ "/> 원" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">환불계좌 은행명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" name="refundBankName" class="form-control form-control-lg form-control-solid-bg" placeholder="환불계좌 은행명" value="${info.refundBankName}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">환불계좌 예금주명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" name="refundBankCustomerName" class="form-control form-control-lg form-control-solid-bg" placeholder="환불계좌 예금주명" value="${info.refundBankCustomerName}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">환불계좌번호</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" name="refundBankNumber" class="form-control form-control-lg form-control-solid-bg" placeholder="환불계좌번호" value="${info.refundBankNumber}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                        </c:if>
                                        <c:if test="${paymentInfo eq null}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-12 col-form-label fw-semibold fs-6">결제정보 없음</label>
                                                <!--end::Label-->
                                            </div>
                                            <!--end::Input group-->
                                        </c:if>
                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Actions-->
                                    <div class="card-footer d-flex justify-content-between py-6 px-9">
                                        <div>
                                            <a href="/mng/customer/boarder.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <%--<div>
                                            <button type="button" onclick="f_customer_boarder_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_customer_boarder_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
                                        </div>--%>
                                    </div>
                                    <!--end::Actions-->
                                </div>
                                <!--end::Basic info-->

                            </div>
                            <!--end::Content container-->
                        </div>
                        <!--end::Content-->
                    </div>
                    <!--end::Content wrapper-->

<%@ include file="/WEB-INF/views/mng/inc/_footer.jspf" %>
                </div>
                <!--end:::Main-->
            </div>
            <!--end::Wrapper-->
        </div>
        <!--end::Page-->
    </div>
    <!--end::App-->

<%@ include file="/WEB-INF/views/mng/inc/_scrolltop.jspf" %>

<%@ include file="/WEB-INF/views/mng/inc/_scripts_common.jspf" %>
    <script src="/assets/js/custom/apps/ecommerce/catalog/tables.js"></script>
    <script src="/assets/js/widgets.bundle.js"></script>
    <script src="/assets/js/custom/widgets.js"></script>
    <script src="/assets/js/custom/apps/chat/chat.js"></script>
    <script src="/assets/js/custom/utilities/modals/upgrade-plan.js"></script>
    <script src="/assets/js/custom/utilities/modals/create-app.js"></script>
    <script src="/assets/js/custom/utilities/modals/users-search.js"></script>
    <!--end::Custom Javascript-->

    <!--begin::Custom Javascript(used for common page)-->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/smsNoti.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/boarder.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>