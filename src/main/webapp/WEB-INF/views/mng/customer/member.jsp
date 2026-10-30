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
                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_excel_export('mng_customer_member_table', '전체회원목록')" class="btn btn-success btn-active-light-success" data-kt-export="excel" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>Export as Excel</button>
                                    <!--end::Export dropdown-->

                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_customer_member_detail_excel_download('mng_customer_member_table', '전체회원목록_상세')" class="btn btn-warning btn-active-light-warning" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>전체 회원 상세 다운로드</button>
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
                                                <button type="button" onclick="f_customer_member_search()" class="btn btn-primary me-5">Search</button>
                                                <button type="button" onclick="f_customer_member_search_condition_init()" class="btn btn-secondary me-5">
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
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_grade" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 등급 -" onchange="f_customer_member_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 등급 -</option>
                                                    <option value="일반회원">일반회원</option>
                                                    <option value="관심사용자">관심사용자</option>
                                                    <option value="교육생(단기)">교육생(단기)</option>
                                                    <option value="교육생(정규)">교육생(정규)</option>
                                                    <%--<option value="수료생">수료생</option>--%>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>

                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-200px">
                                                <!--begin::Select2-->
                                                <select id="condition_keyword" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 관심키워드 -" onchange="f_customer_member_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 관심키워드 -</option>
                                                    <option value="선내기">선내기</option>
                                                    <option value="선외기">선외기</option>
                                                    <option value="마리나선박">마리나선박</option>
                                                    <option value="정비">정비</option>
                                                    <option value="이직/커리어">이직/커리어</option>
                                                    <option value="여행">여행</option>
                                                    <option value="해양레저이슈">해양레저이슈</option>
                                                    <option value="취미">취미</option>
                                                    <option value="취업">취업</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>

                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_sex" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 성별 -" onchange="f_customer_member_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 성별 -</option>
                                                    <option value="남성">남성</option>
                                                    <option value="여성">여성</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>

                                            <div class="ms-auto d-flex align-items-center gap-2 gap-lg-3">

                                                <%-- 2026-10-30: 관리자 회원 등록 기능 제거(사용자 결정). 회원 가입은 공개 화면에서만 한다. --%>

                                            </div>

                                        </div>
                                        <!--end::Card toolbar-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body pt-0">
                                        <div class="fw-bold"><span class="mr10">검색결과</span><span id="search_cnt" style="color: #009ef7;">0</span> 개</div>
                                        <!--begin::Table-->
                                        <table class="table align-middle table-row-dashed fs-6 gy-5" id="mng_customer_member_table">
                                            <thead>
                                                <tr class="text-start text-gray-400 fw-bold fs-7 text-uppercase gs-0">
                                                    <th class="text-center min-w-50px">번호</th>
                                                    <th>seq</th>
                                                    <th class="text-center min-w-50px">상태</th>
                                                    <th class="text-center min-w-75px">등급</th>
                                                    <th class="text-center min-w-100px">아이디</th>
                                                    <th class="text-center min-w-100px">이름</th>
                                                    <th class="text-center min-w-50px">성별</th>
                                                    <th class="text-center min-w-100px">연락처</th>
                                                    <th class="text-center min-w-75px">SMS알림동의</th>
                                                    <th class="text-center min-w-150px">등록일시</th>
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
                    <h2 style="color: #FFFFFF;">회원 상세보기</h2>
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
                                <label class="form-label">회원등급</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg mb-2" id="md_grade" placeholder="회원등급" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">아이디</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg mb-2" id="md_id" placeholder="아이디" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">이름</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg mb-2" id="md_name" placeholder="이름" readonly>
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
                                <input type="text" class="form-control form-control-lg form-control-solid-bg mb-2" id="md_phone" placeholder="연락처" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">이메일</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg mb-2" id="md_email" placeholder="이메일" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">관심키워드</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <!--begin::Col-->
                                <div class="d-flex flex-wrap">
                                    <div class="form-check form-check-custom form-check-lg mb-3">
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_1" name="md_keyword" value="선내기" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_1">
                                            선내기
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_2" name="md_keyword" value="선외기" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_2">
                                            선외기
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_3" name="md_keyword" value="마리나선박" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_3">
                                            마리나선박
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_4" name="md_keyword" value="정비" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_4">
                                            정비
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_5" name="md_keyword" value="이직/커리어" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_5">
                                            이직/커리어
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_6" name="md_keyword" value="여행" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_6">
                                            여행
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_7" name="md_keyword" value="해양레저이슈" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_7">
                                            해양레저이슈
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_8" name="md_keyword" value="취미" disabled/>
                                        <label class="form-check-label opacity-100 text-black me-4" for="md_keyword_8">
                                            취미
                                        </label>
                                        <input class="form-check-input form-control-solid-bg"
                                               type="checkbox" id="md_keyword_9" name="md_keyword" value="취업" disabled/>
                                        <label class="form-check-label opacity-100 text-black" for="md_keyword_9">
                                            취업
                                        </label>
                                    </div>
                                </div>
                                <!--end::Col-->
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div>
                                <!--begin::Label-->
                                <label class="form-label">SMS 알림서비스 동의</label>
                                <!--end::Label-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_sms_yn" value="1" class="form-check-input form-control-solid-bg" disabled> 동의함
                                    </label>
                                    <label>
                                        <input type="radio" name="md_sms_yn" value="0" class="form-check-input form-control-solid-bg" disabled> 동의안함
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
    <script src="/js/mng/member.js?ver=<%=System.currentTimeMillis()%>"></script>

    <script>
        document.addEventListener("keyup", function(event) {
            if (event.key === 'Enter') {
                f_customer_member_search();
            }
        });
    </script>

    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>