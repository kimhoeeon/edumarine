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
                                        전체 회원 목록</h1>
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
                                        <li class="breadcrumb-item text-muted">회원 관리</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">전체 회원 목록</li>
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
                                                <h3 class="fw-bold m-0">상세 정보</h3>
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
                                                    <input type="text" id="applyStatus" name="applyStatus" class="form-control form-control-lg form-control-solid-bg" placeholder="상태" value="${info.applyStatus}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">등급</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="grade" name="grade" class="form-control form-control-lg form-control-solid-bg" placeholder="등급" value="${info.grade}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">아이디</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="id" name="id" class="form-control form-control-lg form-control-solid-bg" placeholder="아이디" value="${info.id}"
                                                            <c:if test="${info.id ne null}">disabled</c:if> />
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <c:if test="${info.id ne null}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">비밀번호</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <%-- 2026-10-30 보안 조치: 회원 비밀번호 해시를 화면에 내보내지 않는다. 이 입력란은 비활성이라 저장 시 전송되지 않고, 회원 수정 SQL 도 비밀번호를 갱신하지 않는다. --%>
                                                    <input type="text" id="password" name="password" class="form-control form-control-lg form-control-solid-bg" placeholder="비밀번호" value="설정됨 (관리자 화면에서는 표시하지 않음)" disabled/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            </c:if>
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">이름</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-4">
                                                    <input type="text" id="name" name="name" class="form-control form-control-lg form-control-solid-bg" placeholder="이름" value="${info.name}"/>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="nameEn" name="nameEn" class="form-control form-control-lg form-control-solid-bg" placeholder="이름" value="${info.nameEn}"/>
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
                                                    <input type="text" id="phone" name="phone" class="form-control form-control-lg form-control-solid-bg onlyTel" placeholder="연락처" value="${info.phone}"/>
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
                                                    <c:set var="birth" value=""/>
                                                    <c:if test="${info.id ne null}">
                                                        <c:set var="birth" value="${info.birthYear}-${info.birthMonth}-${info.birthDay}"/>
                                                    </c:if>
                                                    <input type="text" id="birth" name="birth" class="form-control form-control-lg form-control-solid-bg" placeholder="생년월일 (YYYY-MM-DD)" value="${birth}"/>
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
                                                <div class="col-lg-8">
                                                    <input type="text" id="sex" name="sex" class="form-control form-control-lg form-control-solid-bg" placeholder="성별" value="${info.sex}"/>
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
                                                            <input type="text" id="address" name="address" class="form-control form-control-lg form-control-solid-bg" placeholder="주소" value="${info.address}"/>
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
                                                            <input type="text" id="addressDetail" name="addressDetail" class="form-control form-control-lg form-control-solid-bg" value="${info.addressDetail}" placeholder="상세주소"/>
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
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">이메일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <div class="input-group">
                                                        <c:set var="email1" value="${fn:split(info.email,'@')[0]}" />
                                                        <c:set var="email2" value="${fn:split(info.email,'@')[1]}" />
                                                        <input type="text" id="email" name="email" value="${email1}" class="form-control form-control-solid-bg" placeholder="이메일"/>
                                                        <span class="input-group-text">@</span>
                                                        <input type="text" id="domain" name="domain" value="${email2}" class="form-control form-control-solid-bg" placeholder="도메인"/>
                                                        <select id="email_select" class="form-select form-control-solid-bg ml10" aria-label="Select Email">
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
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">관심 키워드</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex flex-wrap">
                                                    <div class="form-check form-check-custom form-check-lg mb-3">
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_1" name="keyword" value="선내기"
                                                               <c:if test="${fn:contains(info.keyword , '선내기')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_1">
                                                            선내기
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_2" name="keyword" value="선외기"
                                                               <c:if test="${fn:contains(info.keyword , '선외기')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_2">
                                                            선외기
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_3" name="keyword" value="마리나선박"
                                                               <c:if test="${fn:contains(info.keyword , '마리나선박')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_3">
                                                            마리나선박
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_4" name="keyword" value="정비"
                                                               <c:if test="${fn:contains(info.keyword , '정비')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_4">
                                                            정비
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_5" name="keyword" value="이직/커리어"
                                                               <c:if test="${fn:contains(info.keyword , '이직/커리어')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_5">
                                                            이직/커리어
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-custom form-check-lg mb-3">
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_6" name="keyword" value="여행"
                                                               <c:if test="${fn:contains(info.keyword , '여행')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_6">
                                                            여행
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_7" name="keyword" value="해양레저이슈"
                                                               <c:if test="${fn:contains(info.keyword , '해양레저이슈')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_7">
                                                            해양레저이슈
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_8" name="keyword" value="취미"
                                                               <c:if test="${fn:contains(info.keyword , '취미')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black me-4" for="keyword_8">
                                                            취미
                                                        </label>
                                                        <input class="form-check-input form-control-solid-bg"
                                                               type="checkbox" id="keyword_9" name="keyword" value="취업"
                                                               <c:if test="${fn:contains(info.keyword , '취업')}">checked</c:if>/>
                                                        <label class="form-check-label opacity-100 text-black" for="keyword_9">
                                                            취업
                                                        </label>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-3">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">SMS 알림서비스 동의</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="smsYn" value="1" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.smsYn eq '1'}">checked</c:if>/> 동의함
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="smsYn" value="0" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.smsYn eq '0' or info eq null}">checked</c:if>/> 동의안함
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
                                    <!--begin::Actions-->
                                    <div class="card-footer d-flex justify-content-between py-6 px-9">
                                        <div>
                                            <a href="/mng/customer/member.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_customer_member_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_customer_member_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
                                        </div>
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
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/member.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>