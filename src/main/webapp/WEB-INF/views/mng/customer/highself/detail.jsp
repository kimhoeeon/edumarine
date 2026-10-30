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
                                        자가정비 심화과정 (고마력 선외기)</h1>
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
                                        <li class="breadcrumb-item text-muted">자가정비 심화과정 (고마력 선외기)</li>
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
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">작업복 사이즈 (남여공용)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="S" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'S'}">checked</c:if> disabled/> S (90)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="M" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'M'}">checked</c:if> disabled/> M (95)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="L" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'L'}">checked</c:if> disabled/> L (100)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="XL" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'XL'}">checked</c:if> disabled/> XL (105)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="XXL" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'XXL'}">checked</c:if> disabled/> XXL (110)
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="clothesSize" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq '기타'}">checked</c:if> disabled/> 기타
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
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">교육 이해</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex flex-column">
                                                    <label class="mb-3">
                                                        <input type="checkbox" name="trainUnderstand" value="1" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${fn:contains(info.trainUnderstand, '1')}">checked</c:if> disabled/> 고마력 선외기를 장착한 보트를 운항하시는 분
                                                    </label>
                                                    <label class="mb-3">
                                                        <input type="checkbox" name="trainUnderstand" value="2" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${fn:contains(info.trainUnderstand, '2')}">checked</c:if> disabled/> 고마력 선외기를 장착한 보트를 운항할 계획이신 분
                                                    </label>
                                                    <label class="mb-3">
                                                        <input type="checkbox" name="trainUnderstand" value="3" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${fn:contains(info.trainUnderstand, '3')}">checked</c:if> disabled/> 고마력 선외기 엔진에 대하여 관심이 있으신 분
                                                    </label>
                                                    <label class="d-flex align-items-center">
                                                        <input type="checkbox" name="trainUnderstand" value="4" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${fn:contains(info.trainUnderstand, '4')}">checked</c:if> disabled/> 기타 <input type="text" id="trainUnderstandEtc" name="trainUnderstandEtc" class="form-control form-control-lg form-control-solid-bg ms-2 w-75" value="${info.trainUnderstandEtc}" placeholder="직접 입력" readonly>
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">추천인</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="recommendPerson" name="recommendPerson" class="form-control form-control-lg form-control-solid-bg" placeholder="추천인" value="${info.recommendPerson}"/>
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
                                            <a href="/mng/customer/highself.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
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
    <script src="/js/mng/highself.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>