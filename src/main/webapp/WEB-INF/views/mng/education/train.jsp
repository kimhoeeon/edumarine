<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/mng/inc/_top.jspf" %>
<%@ include file="/WEB-INF/views/mng/inc/_head.jspf" %>
</head>
<%@ include file="/WEB-INF/views/mng/inc/_body_open_loader.jspf" %>
<c:if test="${sessionScope.get('status') eq 'logon'}">

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
                                        교육 현황</h1>
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
                                        <li class="breadcrumb-item text-muted">교육</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">교육 관리</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">교육 현황</li>
                                        <!--end::Item-->
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Page title-->
                                <!--begin::Actions-->
                                <div class="d-flex align-items-center gap-2 gap-lg-3">
                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_excel_export('mng_education_train_table', '교육현황')" class="btn btn-success btn-active-light-success" data-kt-export="excel" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>Export as Excel</button>
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
                                                    <option value="">전체</option>
                                                    <option value="NAME" selected>교육과정명</option>
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
                                                <button type="button" onclick="f_education_train_search()" class="btn btn-primary me-5">Search</button>
                                                <button type="button" onclick="f_education_train_search_condition_init()" class="btn btn-secondary me-5">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i>검색조건 초기화</button>
                                            </div>
                                            <!--end:Action-->
                                        </div>
                                        <!--end::Card title-->
                                        <!--begin::Card toolbar-->
                                        <div class="card-toolbar flex-row-fluid gap-5">
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-175px">
                                                <!--begin::Select2-->
                                                <select id="condition_time" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 차시 -" onchange="f_education_train_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 차시 -</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_category" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 분류 -" onchange="f_education_train_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 분류 -</option>
                                                    <option value="전체">전체</option>
                                                    <option value="정규과정">정규과정</option>
                                                    <option value="단기과정">단기과정</option>
                                                    <option value="심화과정">심화과정</option>
                                                    <option value="팸투어">팸투어</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-325px">
                                                <!--begin::Select2-->
                                                <select id="condition_gbn" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 교육과정 -" onchange="f_education_train_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 교육과정 -</option>
                                                    <option value="상시신청">상시신청</option>
                                                    <option value="해상엔진 테크니션 (선내기/선외기)">해상엔진 테크니션 (선내기/선외기)</option>
                                                    <option value="FRP 레저보트 선체 정비 테크니션">FRP 레저보트 선체 정비 테크니션</option>
                                                    <option value="선외기 기초정비실습 과정">선외기 기초정비실습 과정</option>
                                                    <option value="선내기 기초정비실습 과정">선내기 기초정비실습 과정</option>
                                                    <option value="세일요트 기초정비실습 과정">세일요트 기초정비실습 과정</option>
                                                    <option value="고마력 선외기 정비 중급 테크니션">고마력 선외기 정비 중급 테크니션</option>
                                                    <option value="자가정비 심화과정 (고마력 선외기)">자가정비 심화과정 (고마력 선외기)</option>
                                                    <option value="고마력 선외기 정비 중급 테크니션 (특별반)">고마력 선외기 정비 중급 테크니션 (특별반)</option>
                                                    <option value="스턴드라이브 정비 전문가과정">스턴드라이브 정비 전문가과정</option>
                                                    <option value="스턴드라이브 정비 전문가과정 (특별반)">스턴드라이브 정비 전문가과정 (특별반)</option>
                                                    <option value="기초정비교육">기초정비교육</option>
                                                    <option value="응급조치교육">응급조치교육</option>
                                                    <option value="발전기 정비 교육">발전기 정비 교육</option>
                                                    <option value="선외기/선내기 직무역량 강화과정">선외기/선내기 직무역량 강화과정</option>
                                                    <option value="선내기 팸투어">선내기 팸투어</option>
                                                    <option value="선외기 팸투어">선외기 팸투어</option>
                                                    <option value="레저선박 해양전자장비 교육">레저선박 해양전자장비 교육</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-175px d-none">
                                                <!--begin::Select2-->
                                                <select id="condition_gbn_depth" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 세부교육명 -" onchange="f_education_train_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 세부교육명 -</option>
                                                    <option value="선외기">선외기</option>
                                                    <option value="선내기">선내기</option>
                                                    <option value="세일요트">세일요트</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>

                                            <div class="ms-auto d-flex align-items-center gap-2 gap-lg-3">

                                                <!--begin::등록-->
                                                <a href="javascript:void(0);" onclick="f_education_train_modify_init_set('');" class="btn btn-primary ms-auto">교육 등록</a>
                                                <!--end::등록-->

                                                <c:if test="${sessionScope.get('note') eq '개발사'}">
                                                    <a href="<c:url value="/mng/education/train/detail.do?type=UNIFIED"/>" class="btn btn-success ms-2" title="신규 통합 교육 등록 (관리자용)">
                                                        (신규) 통합 등록
                                                    </a>
                                                </c:if>

                                            </div>

                                        </div>
                                        <!--end::Card toolbar-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body pt-0">
                                        <div class="fw-bold"><span class="mr10">검색결과</span><span id="search_cnt" style="color: #009ef7;">0</span> 개</div>
                                        <!--begin::Table-->
                                        <table class="table align-middle table-row-dashed fs-6 gy-5" id="mng_education_train_table">
                                            <thead>
                                                <tr class="text-start text-gray-400 fw-bold fs-7 text-uppercase gs-0">
                                                    <th class="text-center min-w-50px">번호</th>
                                                    <th>seq</th>
                                                    <th class="text-center min-w-50px w-75px">분류</th>
                                                    <th class="text-center min-w-40px w-50px">차시</th>
                                                    <th class="text-center min-w-200px">교육과정명</th>
                                                    <th class="text-center min-w-150px">교육일정</th>
                                                    <th class="text-center min-w-150px">접수일정</th>
                                                    <th class="text-center min-w-75px">교육비</th>
                                                    <th class="text-center min-w-75px">교육인원(현 인원)</th>
                                                    <th class="text-center min-w-75px">상태</th>
                                                    <th class="text-center min-w-150px w-175px">등록일시</th>
                                                    <th class="text-center min-w-100px">기능</th>
                                                </tr>
                                            </thead>
                                            <tbody class="fw-semibold text-gray-600">
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
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
                    <h2 style="color: #FFFFFF;">교육 상세보기</h2>
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
                    <div class="card card-flush py-4">

                        <!--begin::Card body-->
                        <div class="card-body pt-0">
                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">교육과정명</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_gbn" placeholder="교육과정명" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">세부교육명</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_gbn_depth" placeholder="세부교육명" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">차시</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_next_time" placeholder="차시" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">교육일정 (시작)</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_train_start_dttm" placeholder="교육일정 (시작)" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">교육일정 (종료)</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_train_end_dttm" placeholder="교육일정 (종료)" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">접수일정 (시작)</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_apply_start_dttm" placeholder="접수일정 (시작)" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">접수일정 (종료)</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_apply_end_dttm" placeholder="접수일정 (종료)" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">교육비</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_pay_sum" placeholder="교육비" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">총 교육인원</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_train_cnt" placeholder="총 교육인원" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">교육신청인원</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_train_apply_cnt" placeholder="교육신청인원" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">비고</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <div id="md_train_note" class="form-control form-control-solid-bg resize-none h-150px overflow-y-auto" readonly></div>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">홈페이지 노출여부</label>
                                <!--end::Label-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_exposure_yn" value="Y" class="form-check-input form-control-solid-bg" disabled> 노출
                                    </label>
                                    <label>
                                        <input type="radio" name="md_exposure_yn" value="N" class="form-check-input form-control-solid-bg" disabled> 미노출
                                    </label>
                                </div>
                                <!--end::Col-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">연간 일정표 등록 여부</label>
                                <!--end::Label-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_schedule_exposure_yn" value="Y" class="form-check-input form-control-solid-bg" disabled> 등록
                                    </label>
                                    <label>
                                        <input type="radio" name="md_schedule_exposure_yn" value="N" class="form-check-input form-control-solid-bg" disabled> 미등록
                                    </label>
                                </div>
                                <!--end::Col-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div>
                                <!--begin::Label-->
                                <label class="form-label">교육 상태</label>
                                <!--end::Label-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_closing_yn" value="N" class="form-check-input form-control-solid-bg" disabled> 신청오픈
                                    </label>
                                    <label>
                                        <input type="radio" name="md_closing_yn" value="Y" class="form-check-input form-control-solid-bg" disabled> 신청마감
                                    </label>
                                </div>
                                <!--end::Col-->
                            </div>
                            <!--end::Input group-->

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
    <script src="/js/mng/train.js?ver=<%=System.currentTimeMillis()%>"></script>

    <script>

        $(document).ready(function() {

            // 차시 검색조건 Set
            $("#condition_time").children('option:not(:gt(1))').remove();

            let json = {}
            $.ajax({
                url: '/train/selectNextTime.do',
                method: 'post',
                data: JSON.stringify(json),
                contentType: 'application/json; charset=utf-8' //server charset 확인 필요
            })
                .done(function (data, status) {
                    let results = data;
                    if (nvl(results, '') !== '') {
                        $.each(results, function (i) {
                            $('#condition_time').append($('<option>', {
                                value: results[i].nextTime,
                                text: results[i].nextTime + '차'
                            }));
                        })

                        $('#condition_time').val('').select2({minimumResultsForSearch: Infinity});
                    }
                }).always(function() {
                let nextTime = '${nextTime}';
                if(nvl(nextTime,'') !== ''){
                    $('#condition_time').val(nextTime).trigger('change');
                }
            });

        });

        document.addEventListener("keyup", function(event) {
            if (event.key === 'Enter') {
                f_education_train_search();
            }
        });
    </script>

    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>