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
                                        통합 교육 신청 목록</h1>
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
                                        <li class="breadcrumb-item text-muted">통합 교육 신청 목록</li>
                                        <!--end::Item-->
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Page title-->
                                <!--begin::Actions-->
                                <div class="d-flex align-items-center gap-2 gap-lg-3">
                                    <button type="button" onclick="f_excel_download('unified_detail')" class="btn btn-success btn-active-light-success" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>신청자 정보 상세 다운로드</button>
                                </div>
                                <!--end::Actions-->

                            </div>
                            <!--end::Toolbar container-->
                        </div>
                        <!--end::Toolbar-->
                        <!--begin::Content-->
                        <div id="kt_app_content" class="app-content flex-column-fluid">
                            <div id="kt_app_content_container" class="app-container container-full">
                                <div class="card card-flush">
                                    <div class="card-header align-items-center py-5 gap-2">
                                        <div class="card-title w-100">
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="search_box" class="form-select form-select-solid" data-control="select2"
                                                        aria-label="- 검색조건 -" data-placeholder="- 검색조건 -"
                                                        data-allow-clear="true" data-hide-search="true">
                                                    <option></option>
                                                    <option disabled>- 검색조건 -</option>
                                                    <option value="" selected>전체</option>
                                                    <option value="NAME">이름</option>
                                                    <option value="PHONE">연락처</option>
                                                    <option value="TRAIN">교육명</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <div class="d-flex align-items-center position-relative my-1 ml15 mr15">
                                                <i class="ki-duotone ki-magnifier fs-3 position-absolute ms-4">
                                                    <span class="path1"></span>
                                                    <span class="path2"></span>
                                                </i>
                                                <input type="text" id="search_text" name="search_text" value="" class="form-control form-control-solid w-250px ps-12" placeholder="검색어 입력"/>
                                            </div>
                                            <!--begin:Action-->
                                            <div class="d-flex align-items-center">
                                                <button type="button" onclick="f_customer_unified_search()" class="btn btn-primary me-5">Search</button>
                                                <button type="button" onclick="f_customer_unified_search_condition_init()" class="btn btn-secondary me-5">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i>검색조건 초기화</button>
                                            </div>
                                            <!--end:Action-->
                                        </div>
                                        <div class="card-toolbar flex-row-fluid gap-5">
                                            <div class="w-100 mw-275px">
                                                <select id="condition_train_seq" class="form-select form-select-solid" data-control="select2" data-hide-search="true" onchange="f_customer_unified_search()">
                                                    <option value="">전체 교육명</option>
                                                    <c:forEach var="train" items="${trainList}">
                                                        <option value="${train.gbn}">${train.gbn}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="w-100 mw-150px">
                                                <select id="condition_next_time" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 차시 -" onchange="f_customer_unified_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 차시 -</option>
                                                    <c:forEach var="time" items="${timeList}">
                                                        <option value="${time}">${time}차</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_year" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 연도 -" onchange="f_customer_unified_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 연도 -</option>
                                                    <option value="2026" selected>2026</option>
                                                    <option value="2025">2025</option>
                                                    <option value="2024">2024</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>
                                            <div class="w-100 mw-150px">
                                                <select id="condition_apply_status" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 신청상태 -" onchange="f_customer_unified_search()">
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
                                            </div>

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
                                    </div>
                                    <div class="card-body pt-0">
                                        <div class="fw-bold"><span class="mr10">검색결과</span><span id="search_cnt" style="color: #009ef7;">0</span> 개</div>
                                        <table class="table align-middle table-row-dashed fs-6 gy-5" id="mng_customer_unified_table">
                                            <thead>
                                                <tr class="text-start text-gray-400 fw-bold fs-7 text-uppercase gs-0">
                                                    <th class="w-10px pe-2">
                                                        <div class="form-check form-check-sm form-check-custom form-check-solid me-3">
                                                            <input class="form-check-input" type="checkbox" data-kt-check="true"
                                                                   data-kt-check-target="#mng_customer_unified_table .form-check-input" value="1"/>
                                                        </div>
                                                    </th>
                                                    <th class="text-center min-w-50px">번호</th>
                                                    <th>seq</th>
                                                    <th class="text-center min-w-50px">연도</th>
                                                    <th class="text-center min-w-100px">교육명</th>
                                                    <th class="text-center min-w-50px">차시</th>
                                                    <th class="text-center min-w-75px">신청상태</th>
                                                    <th class="text-center min-w-75px">등급</th>
                                                    <th class="text-center min-w-75px">아이디</th>
                                                    <th class="text-center min-w-75px">이름</th>
                                                    <th class="text-center min-w-125px">연락처</th>
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
                                    </div>
                                </div>
                            </div>
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
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">환불 구분</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <!--begin::Select2-->
                                <select id="md_cancel_gbn" class="form-select form-select-solid" data-control="select2"
                                        data-hide-search="true" data-allow-clear="true"
                                        data-placeholder="- 환불 구분 -">
                                    <option></option>
                                    <option value="" disabled>- 환불 구분 -</option>
                                    <option value="ALL">전액 환불 (환불 기한 상관없이)</option>
                                    <option value="PART">조건 환불 (환불 기한에 따라 환불)</option>
                                    <option value="NOPAY">미결제취소승인</option>
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_apply_edu_change" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">신청 교육 변경</h2>
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
                    <div class="lh-lg">
                        <c:set var="now" value="<%=new java.util.Date()%>"/>
                        <fmt:formatDate var="fmNow" value="${now}" pattern="yyyy.MM.dd"/>
                        * 오늘 날짜 기준 ( ${fmNow} ) 으로 <br/>
                        1. <strong class="text-danger">접수일정 or 교육일정에 해당되거나 교육 활성화 상태( 마감 X, 삭제 X )인 경우</strong><br/>
                        2. <strong class="text-danger">동일 교육 내 차시 변경의 경우</strong><br/>
                        에만 변경 가능합니다.<br/><br/>
                        * <strong>교육 변경 대상 목록</strong> : <strong>교육생명 / 현재 결제 상태</strong> ( 현재 결제 상태가 <strong class="text-danger">취소신청/취소완료인 경우 교육 변경 불가</strong> )<br/>
                        * <strong>변경할 교육</strong> : <strong>과정분류 / 교육명 / 차시 / 교육시작일정 / 교육종료일정</strong> 순<br/>
                    </div>
                    <!--begin::Menu separator-->
                    <div class="separator my-6"></div>
                    <!--end::Menu separator-->
                    <!--begin::form-->
                    <form id="modal_form3" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-2 col-form-label required fw-semibold fs-6">교육 변경 대상 목록</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-10 d-flex align-items-center">
                                <span class="target_list"></span>
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-2 col-form-label required fw-semibold fs-6">변경할 교육</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-10 d-flex align-items-center">
                                <!--begin::Select2-->
                                <select id="md_edu" class="form-select form-select-solid" data-control="select2"
                                        data-hide-search="true" data-allow-clear="true"
                                        data-placeholder="- 교육 -">
                                    <option></option>
                                    <option value="" disabled>- 교육 -</option>
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
                            <a onclick="f_customer_boarder_train_change_btn()" class="btn btn-primary">변경</a>
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
    <script src="/js/smsNoti.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/unified.js?ver=<%=System.currentTimeMillis()%>"></script>

    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>