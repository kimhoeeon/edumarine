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
                                        관리자 관리</h1>
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
                                        <li class="breadcrumb-item text-muted">관리자 관리</li>
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

                                <!--begin::form-->
                                <form id="dataForm" method="post" onsubmit="return false;">

                                    <!--begin::Basic info-->
                                    <div class="card mb-5 mb-xl-10">
                                        <%-- SEQ 값--%>
                                        <input type="hidden" id="seq" name="seq" value="${info.seq}">
                                        <!--begin::Card header-->
                                        <div class="card-header border-0">
                                            <!--begin::Card title-->
                                            <div class="card-title m-0">
                                                <h3 class="fw-bold m-0">관리자 기본 정보</h3>
                                            </div>
                                            <!--end::Card title-->
                                        </div>
                                        <!--end::Card header-->
                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">

                                            <!--begin::Input group-->
                                            <div class="row mb-3">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">관리자 레벨</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="gbn" value="시스템" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${info.gbn eq '시스템'}">checked</c:if> /> 시스템 관리자
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="gbn" value="일반" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${info.gbn eq '일반' or info eq null}">checked</c:if> /> 일반 관리자
                                                    </label>
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
                                                    <div class="row">
                                                        <div class="col-lg-8">
                                                            <input type="text" id="id" name="id" class="form-control form-control-lg form-control-solid-bg" placeholder="아이디" value="${info.id}" onchange="f_mng_admin_id_status_change()"/>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <button type="button" class="btn btn-primary" onclick="f_mng_admin_id_duplicate_check();">중복 검사</button>
                                                            <input type="hidden" id="idCheck" value="false">
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <span class="id_valid_result_cmnt">중복체크 버튼을 클릭해 주세요.</span>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">비밀번호</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <div class="row">
                                                        <div class="col-lg-8">
                                                            <%-- 2026-10-30 보안 조치: 저장된 비밀번호(평문·해시)를 화면에 내보내지 않는다. 수정 저장은 비밀번호를 갱신하지 않으므로 빈 값이어도 기존 동작과 같다. --%>
                                                            <input type="password" id="password" name="password" class="form-control form-control-lg form-control-solid-bg" placeholder="비밀번호" value="" autocomplete="new-password" onchange="f_mng_admin_pw_status_change()"/>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <button type="button" class="btn btn-primary" onclick="f_mng_admin_pw_check()">비밀번호 검사</button>
                                                            <input type="hidden" id="pwCheck" value="false">
                                                        </div>
                                                    </div>
                                                    <div class="row mt-2">
                                                        <span class="pw_valid_result_cmnt">비밀번호 검사 버튼을 클릭해 주세요.</span>
                                                    </div>
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
                                                    <input type="text" id="cpName" name="cpName" class="form-control form-control-lg form-control-solid-bg" placeholder="이름" value="${info.cpName}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">일반전화</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="cpTel" name="cpTel" class="form-control form-control-lg form-control-solid-bg onlyTel" placeholder="일반전화" value="${info.cpTel}" maxlength="13"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">휴대전화</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="cpPhone" name="cpPhone" class="form-control form-control-lg form-control-solid-bg onlyTel" placeholder="휴대전화" value="${info.cpPhone}" maxlength="13"/>
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
                                                        <c:set var="email1" value="${fn:split(info.cpEmail,'@')[0]}" />
                                                        <c:set var="email2" value="${fn:split(info.cpEmail,'@')[1]}" />
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
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">소속(부서)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="cpDepart" name="cpDepart" class="form-control form-control-lg form-control-solid-bg" placeholder="소속(부서)" value="${info.cpDepart}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">접속 허용/차단</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="blockYn" value="N" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${info.blockYn eq 'N' or info eq null}">checked</c:if> /> 접속 허용
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="blockYn" value="Y" class="form-check-input form-control-solid-bg me-2"
                                                               <c:if test="${info.blockYn eq 'Y'}">checked</c:if> /> 접속 차단
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">IP Address</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <div class="row">
                                                        <c:set var="ip1" value="${fn:split(info.ipAddress,'.')[0]}" />
                                                        <c:set var="ip2" value="${fn:split(info.ipAddress,'.')[1]}" />
                                                        <c:set var="ip3" value="${fn:split(info.ipAddress,'.')[2]}" />
                                                        <c:set var="ip4" value="${fn:split(info.ipAddress,'.')[3]}" />
                                                        <div class="col-lg-2 d-flex align-items-end">
                                                            <div>
                                                                <input type="text" id="ipAddress1" name="ipAddress1" class="form-control form-control-lg form-control-solid-bg onlyNum" value="${ip1}" maxlength="3"/>
                                                            </div>
                                                            <div class="ms-1 fw-bold fs-3">.</div>
                                                        </div>
                                                        <div class="col-lg-2 d-flex align-items-end">
                                                            <div>
                                                                <input type="text" id="ipAddress2" name="ipAddress2" class="form-control form-control-lg form-control-solid-bg onlyNum" value="${ip2}" maxlength="3"/>
                                                            </div>
                                                            <div class="ms-1 fw-bold fs-3">.</div>
                                                        </div>
                                                        <div class="col-lg-2 d-flex align-items-end">
                                                            <div>
                                                                <input type="text" id="ipAddress3" name="ipAddress3" class="form-control form-control-lg form-control-solid-bg onlyNum" value="${ip3}" maxlength="3"/>
                                                            </div>
                                                            <div class="ms-1 fw-bold fs-3">.</div>
                                                        </div>
                                                        <div class="col-lg-2 d-flex align-items-end">
                                                            <div>
                                                                <input type="text" id="ipAddress4" name="ipAddress4" class="form-control form-control-lg form-control-solid-bg onlyNum" value="${ip4}" maxlength="3"/>
                                                            </div>
                                                        </div>
                                                    </div>
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
                                                <h3 class="fw-bold m-0">관리자 권한 설정</h3>
                                            </div>
                                            <!--end::Card title-->
                                        </div>
                                        <!--end::Card header-->
                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                서비스 준비 중입니다.
                                            </div>
                                            <!--end::Input group-->

                                        </div>
                                        <!--end::Card body-->
                                    </div>
                                    <!--end::Basic info-->

                                </form>
                                <!--end::form-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Actions-->
                                    <div class="card-footer d-flex justify-content-between py-6 px-9">
                                        <div>
                                            <a href="/mng/adminMng/admin.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_mng_admin_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_mng_admin_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
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
    <script src="/js/mng/admin.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>