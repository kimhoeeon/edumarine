<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/mng/inc/_top.jspf" %>
<%@ include file="/WEB-INF/views/mng/inc/_head.jspf" %>

    <style>
        #spinner { width: 100%; height: 100%; top: 0; left: 0; opacity: .6; background: silver; position: absolute; z-index: 9999; display: none;}
        #spinner div { width: 100%; height: 100%; display: table; }
        #spinner span { display: table-cell; text-align: center; vertical-align: middle; }
        #spinner img { background: white; padding: 1em; border-radius: .7em; }
    </style>

</head>
<%@ include file="/WEB-INF/views/mng/inc/_body_open_loader.jspf" %>
<c:if test="${sessionScope.get('status') eq 'logon'}">

    <div id="spinner">
        <div>
            <span>
                <img src="https://cdnjs.cloudflare.com/ajax/libs/galleriffic/2.0.1/css/loader.gif">
            </span>
        </div>
    </div>

    <!--begin::Page loading(append to body)-->
    <div class="page-loader flex-column bg-dark bg-opacity-25">
        <span class="spinner-border text-primary" role="status"></span>
        <span class="text-gray-800 fs-6 fw-semibold mt-5">Loading...</span>
    </div>
    <!--end::Page loading-->

    <!--begin::App-->
    <div class="d-flex flex-column flex-root app-root" id="kt_app_root">
        <!--begin::Page-->
        <div class="app-page flex-column flex-column-fluid" id="kt_app_page">
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
                                        상시 사전 신청</h1>
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
                                        <li class="breadcrumb-item text-muted">상시 사전 신청</li>
                                        <!--end::Item-->
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Page title-->
                                <!--begin::Actions-->
                                <div class="d-flex align-items-center gap-2 gap-lg-3">
                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_excel_export('mng_customer_regular_table', '상시사전신청')" class="btn btn-success btn-active-light-success" data-kt-export="excel" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>Export as Excel</button>
                                    <!--end::Export dropdown-->

                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_customer_regular_detail_excel_download('mng_customer_regular_table', '상시사전신청_상세')" class="btn btn-warning btn-active-light-warning" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>전체 신청자 정보 상세 다운로드</button>
                                    <!--end::Export dropdown-->
                                </div>
                                <!--end::Actions-->

                                <!--begin::Hide default export buttons-->
                                <div id="kt_datatable_excel_hidden_buttons" class="d-none"></div>
                                <!--end::Hide default export buttons-->
                            </div>
                            <!--end::Toolbar container-->
                        </div>
                        <!--end::Toolbar-->
                        <!--begin::Content-->
                        <div id="kt_app_content" class="app-content flex-column-fluid">
                            <!--begin::Content container-->
                            <div id="kt_app_content_container" class="app-container container-full">
                                <!--begin::Products-->
                                <div class="card card-flush">
                                    <!--begin::Card header-->
                                    <div class="card-header align-items-center py-5 gap-2">
                                        <!--begin::Card title-->
                                        <div class="card-title w-100">
                                            <%--begin::검색구분--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="search_box" class="form-select form-select-solid" data-control="select2"
                                                        aria-label="- 검색조건 -" data-placeholder="- 검색조건 -"
                                                        data-allow-clear="true" data-hide-search="true">
                                                    <option></option>
                                                    <option disabled>- 검색조건 -</option>
                                                    <option value="" selected>전체</option>
                                                    <option value="NAME">이름</option>
                                                    <option value="ID">아이디</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::검색구분--%>
                                            <!--begin::Search-->
                                            <div class="d-flex align-items-center position-relative my-1 ml15 mr15">
                                                <i class="ki-duotone ki-magnifier fs-3 position-absolute ms-4">
                                                    <span class="path1"></span>
                                                    <span class="path2"></span>
                                                </i>
                                                <input type="text" id="search_text" name="search_text" value="" class="form-control form-control-solid w-250px ps-12" placeholder="검색어 입력"/>
                                            </div>
                                            <!--end::Search-->
                                            <!--begin:Action-->
                                            <div class="d-flex align-items-center">
                                                <button type="button" onclick="f_customer_regular_search()" class="btn btn-primary me-5">Search</button>
                                                <button type="button" onclick="f_customer_regular_search_condition_init()" class="btn btn-secondary me-5">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i>검색조건 초기화</button>
                                            </div>
                                            <!--end:Action-->
                                            <%--<div class="ms-auto">
                                                <!--begin::등록-->
                                                <a href="javascript:void(0);" onclick="f_customer_regular_modify_init_set('');" class="btn btn-primary ms-auto">등록</a>
                                                <!--end::등록-->
                                            </div>--%>
                                        </div>
                                        <!--end::Card title-->
                                        <!--begin::Card toolbar-->
                                        <div class="card-toolbar flex-row-fluid gap-5">
                                            <%--begin::필터--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_apply_status" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 신청상태 -" onchange="f_customer_regular_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 신청상태 -</option>
                                                    <option value="결제대기">결제대기</option>
                                                    <option value="결제완료">결제완료</option>
                                                    <option value="수강확정">수강확정</option>
                                                    <option value="수강완료">수강완료</option>
                                                    <option value="환급대기">환급대기</option>
                                                    <option value="환급완료">환급완료</option>
                                                    <option value="취소신청">취소신청</option>
                                                    <option value="취소완료">취소완료</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::필터--%>
                                            <%--begin::필터--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_experience_yn" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 경험유무 -" onchange="f_customer_regular_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 경험유무 -</option>
                                                    <option value="1">있음</option>
                                                    <option value="0">없음</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::필터--%>

                                            <div class="ms-auto d-flex align-items-center gap-2 gap-lg-3">

                                                <!--begin::버튼-->
                                                <button type="button" id="apply_status_change_btn" class="btn btn-info" data-bs-target="#kt_modal_apply_status_change">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i> 신청 상태 변경</button>
                                                <!--end::버튼-->

                                                <!--begin::버튼-->
                                                <button type="button" id="apply_status_cancel_btn" class="btn btn-danger" data-bs-target="#kt_modal_apply_status_cancel">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i> 취소 승인</button>
                                                <!--end::버튼-->
                                            </div>

                                        </div>
                                        <!--end::Card toolbar-->

                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body pt-0">
                                        <div class="fw-bold"><span class="mr10">검색결과</span><span id="search_cnt" style="color: #009ef7;">0</span> 개</div>
                                        <!--begin::Table-->
                                        <table class="table align-middle table-row-dashed fs-6 gy-5" id="mng_customer_regular_table">
                                            <thead>
                                                <tr class="text-start text-gray-400 fw-bold fs-7 text-uppercase gs-0">
                                                    <th class="w-10px pe-2">
                                                        <div class="form-check form-check-sm form-check-custom form-check-solid me-3">
                                                            <input class="form-check-input" type="checkbox" data-kt-check="true"
                                                                   data-kt-check-target="#mng_customer_regular_table .form-check-input" value="1"/>
                                                        </div>
                                                    </th>
                                                    <th class="text-center min-w-50px">번호</th>
                                                    <th>seq</th>
                                                    <th class="text-center min-w-75px">신청상태</th>
                                                    <th class="text-center min-w-75px">등급</th>
                                                    <th class="text-center min-w-250px">교육분야</th>
                                                    <th class="text-center min-w-50px">아이디</th>
                                                    <th class="text-center min-w-50px">이름</th>
                                                    <th class="text-center min-w-100px">연락처</th>
                                                    <th class="text-center min-w-50px">연령대</th>
                                                    <th class="text-center min-w-50px">신청이력</th>
                                                    <th class="text-center min-w-125px">등록일시</th>
                                                    <th class="text-center min-w-75px">기능</th>
                                                </tr>
                                            </thead>
                                            <tbody class="fw-semibold text-gray-600">
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td class="text-start"></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!--end::Table-->
                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Products-->
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_modify_history" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">신청내역 상세보기</h2>
                    <!--end::Modal title-->
                    <!--begin::Close-->
                    <div class="btn btn-sm btn-icon btn-active-color-primary closeBtn" data-bs-dismiss="modal">
                        <i class="ki-duotone ki-cross fs-1">
                            <span class="path1"></span>
                            <span class="path2"></span>
                        </i>
                    </div>
                    <!--end::Close-->
                </div>
                <!--end::Modal header-->
                <!--begin::Modal body-->
                <div class="modal-body py-lg-10 px-lg-10">
                    <div class="card card-flush py-4">

                        <!--begin::Card body-->
                        <div class="card-body pt-0">

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">이름</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_name" placeholder="이름" readonly>
                                <!--end::Input-->
                                <!--begin::Description-->
                                <%--<div class="text-muted fs-7">Set a meta tag title. Recommended to be simple and precise keywords.</div>--%>
                                <!--end::Description-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">연락처</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_phone" placeholder="연락처" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">이메일</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_email" placeholder="이메일" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">생년월일</label>
                                <!--end::Label-->
                                <div class="d-flex">
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg me-3" id="md_birth_year" placeholder="년도" readonly>
                                    <!--end::Input-->
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg me-3" id="md_birth_month" placeholder="월" readonly>
                                    <!--end::Input-->
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_birth_day" placeholder="일" readonly>
                                    <!--end::Input-->
                                </div>
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">거주지역</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_region" placeholder="거주지역" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">참여 경로</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="인터넷" class="form-check-input form-control-solid-bg" disabled/> 인터넷
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="홈페이지" class="form-check-input form-control-solid-bg" disabled/> 홈페이지
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="홍보물" class="form-check-input form-control-solid-bg" disabled/> 홍보물
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="지인추천" class="form-check-input form-control-solid-bg" disabled/> 지인추천
                                    </label>
                                    <label>
                                        <input type="radio" name="md_participation_path" value="기타" class="form-check-input form-control-solid-bg" disabled/> 기타
                                    </label>
                                </div>
                                <!--end::Col-->
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">1순위 신청분야</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_first_application_field" placeholder="1순위 신청분야" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">2순위 신청분야</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_second_application_field" placeholder="2순위 신청분야" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">3순위 신청분야</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_third_application_field" placeholder="3순위 신청분야" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">희망 교육 시기</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_desired_education_time" placeholder="희망 교육 시기" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">전공</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_major" placeholder="전공" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Menu separator-->
                            <div class="separator my-2"></div>
                            <!--end::Menu separator-->

                            <div class="my-6"><h3>교육신청내역</h3></div>

                            <div class="train_info_list"></div>

                        </div>
                        <!--end::Card header-->
                    </div>
                </div>
                <!--end::Modal body-->
            </div>
            <!--end::Modal content-->
        </div>
        <!--end::Modal dialog-->
    </div>
    <!--end::Modal - 수정이력-->

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_apply_status_cancel" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">취소 승인 처리</h2>
                    <!--end::Modal title-->
                    <!--begin::Close-->
                    <div class="btn btn-sm btn-icon btn-active-color-primary" data-bs-dismiss="modal">
                        <i class="ki-duotone ki-cross fs-1">
                            <span class="path1"></span>
                            <span class="path2"></span>
                        </i>
                    </div>
                    <!--end::Close-->
                </div>
                <!--end::Modal header-->
                <!--begin::Modal body-->
                <div class="modal-body py-lg-10 px-lg-10">
                    <!--begin::form-->
                    <form id="modal_form" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">취소 승인 대상 목록</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <span class="customer_list"></span>
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                    </form>
                    <!--end::form-->
                    <!--begin::Menu separator-->
                    <div class="separator my-6"></div>
                    <!--end::Menu separator-->
                    <!--begin::Col-->
                    <div class="col-lg-12 d-flex justify-content-center">
                        <!--begin::Col-->
                        <div>
                            <!--begin::Cancel-->
                            <a class="btn btn-danger" data-bs-dismiss="modal">취소</a>
                            <!--end::Cancel-->
                        </div>
                        <!--end::Col-->
                        <!--begin::Col-->
                        <div class="ms-10">
                            <!--begin::File upload-->
                            <a onclick="f_apply_cancel_btn()" class="btn btn-primary">승인</a>
                            <!--end::File upload-->
                        </div>
                        <!--end::Col-->
                    </div>
                    <!--end::Col-->
                </div>
                <!--end::Modal body-->
            </div>
            <!--end::Modal content-->
        </div>
        <!--end::Modal dialog-->
    </div>
    <!--end::Modal - 수정이력-->

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_apply_status_change" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">신청 상태 변경</h2>
                    <!--end::Modal title-->
                    <!--begin::Close-->
                    <div class="btn btn-sm btn-icon btn-active-color-primary" data-bs-dismiss="modal">
                        <i class="ki-duotone ki-cross fs-1">
                            <span class="path1"></span>
                            <span class="path2"></span>
                        </i>
                    </div>
                    <!--end::Close-->
                </div>
                <!--end::Modal header-->
                <!--begin::Modal body-->
                <div class="modal-body py-lg-10 px-lg-10">
                    <!--begin::Input group-->
                    <div>
                        * <strong>결제완료</strong> : (가상계좌일 경우) 현재 <strong class="text-danger">입금대기</strong> 상태인 경우에만 적용<br>
                        * <strong>수강확정</strong> : 현재 <strong class="text-danger">결제완료</strong> 상태인 경우에만 적용<br>
                        * <strong>수강완료</strong> : 현재 <strong class="text-danger">수강확정</strong> 상태인 경우에만 적용<br>
                        * <strong>환급대기</strong> : 현재 <strong class="text-danger">수강완료</strong> 상태인 경우에만 적용<br>
                        * <strong>환급완료</strong> : 현재 <strong class="text-danger">환급대기</strong> 상태인 경우에만 적용<br>
                    </div>
                    <!--begin::Menu separator-->
                    <div class="separator my-6"></div>
                    <!--end::Menu separator-->
                    <!--begin::form-->
                    <form id="modal_form2" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">신청 상태 변경 대상 목록</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <span class="customer_list"></span>
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">변경할 신청 상태</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <!--begin::Select2-->
                                <select id="md_status_gbn" class="form-select form-select-solid" data-control="select2"
                                        data-hide-search="true" data-allow-clear="true"
                                        data-placeholder="- 신청 상태 -">
                                    <option></option>
                                    <option value="" disabled>- 신청 상태 -</option>
                                    <option value="결제완료">결제완료</option>
                                    <option value="수강확정">수강확정</option>
                                    <option value="수강완료">수강완료</option>
                                    <option value="환급대기">환급대기</option>
                                    <option value="환급완료">환급완료</option>
                                </select>
                                <!--end::Select2-->
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                    </form>
                    <!--end::form-->
                    <!--begin::Menu separator-->
                    <div class="separator my-6"></div>
                    <!--end::Menu separator-->
                    <!--begin::Col-->
                    <div class="col-lg-12 d-flex justify-content-center">
                        <!--begin::Col-->
                        <div>
                            <!--begin::Cancel-->
                            <a class="btn btn-danger" data-bs-dismiss="modal">취소</a>
                            <!--end::Cancel-->
                        </div>
                        <!--end::Col-->
                        <!--begin::Col-->
                        <div class="ms-10">
                            <!--begin::File upload-->
                            <a onclick="f_apply_change_btn()" class="btn btn-primary">변경</a>
                            <!--end::File upload-->
                        </div>
                        <!--end::Col-->
                    </div>
                    <!--end::Col-->
                </div>
                <!--end::Modal body-->
            </div>
            <!--end::Modal content-->
        </div>
        <!--end::Modal dialog-->
    </div>
    <!--end::Modal - 수정이력-->

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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.5/xlsx.full.min.js"></script>
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/regular.js?ver=<%=System.currentTimeMillis()%>"></script>

    <script>
        document.addEventListener("keyup", function(event) {
            if (event.key === 'Enter') {
                f_customer_regular_search();
            }
        });
    </script>

    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>