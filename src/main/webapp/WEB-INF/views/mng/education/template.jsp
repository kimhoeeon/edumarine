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
                                        교육 안내 템플릿 관리</h1>
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
                                        <li class="breadcrumb-item text-muted">교육 안내 템플릿 관리</li>
                                        <!--end::Item-->
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Page title-->
                                <!--begin::Actions-->
                                <div class="d-flex align-items-center gap-2 gap-lg-3">
                                    <!--begin::Export dropdown-->
                                    <%--<button type="button" onclick="f_excel_export('mng_education_train_table', '교육현황')" class="btn btn-success btn-active-light-success" data-kt-export="excel" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>Export as Excel</button>--%>
                                    <!--end::Export dropdown-->
                                </div>
                                <!--end::Actions-->

                                <!--begin::Hide default export buttons-->
                                <%--<div id="kt_datatable_excel_hidden_buttons" class="d-none"></div>--%>
                                <!--end::Hide default export buttons-->
                            </div>
                            <!--end::Toolbar container-->
                        </div>
                        <!--end::Toolbar-->
                        <!--begin::Content-->
                        <div id="kt_app_content" class="app-content flex-column-fluid">
                            <!--begin::Content container-->
                            <div id="kt_app_content_container" class="app-container container-full">
                                <div class="card card-custom">
                                    <div class="card-header">
                                        <div class="card-title">
                                            <h3 class="card-label">교육 안내 템플릿</h3>
                                        </div>
                                        <div class="card-toolbar">
                                            <ul class="nav nav-light-success nav-bold nav-pills">
                                                <%--<li class="nav-item">
                                                    <a class="nav-link active" id="all" data-toggle="tab" href="#kt_tab_pane_4_0">
                                                        <span class="nav-text">전체 교육 일정</span>
                                                    </a>
                                                </li>--%>
                                                <li class="nav-item">
                                                    <a class="nav-link active" id="marina" data-toggle="tab" href="#kt_tab_pane_4_1">
                                                        <span class="nav-text">마리나선박 정비사 실무과정</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="commission" data-toggle="tab" href="#kt_tab_pane_4_2">
                                                        <span class="nav-text">위탁교육</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item dropdown">
                                                    <a class="nav-link dropdown-toggle" id="boarder" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                                        <span class="nav-text">기초정비실습 과정</span>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
                                                        <a class="dropdown-item" id="outboarder" data-toggle="tab" href="#kt_tab_pane_4_3">선외기</a>
                                                        <a class="dropdown-item" id="inboarder" data-toggle="tab" href="#kt_tab_pane_4_4">선내기</a>
                                                        <a class="dropdown-item" id="sailyacht" data-toggle="tab" href="#kt_tab_pane_4_5">세일요트</a>
                                                        <%--<div class="dropdown-divider"></div>
                                                        <a class="dropdown-item" data-toggle="tab" href="#kt_tab_pane_4_3">Separated link</a>--%>
                                                    </div>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="highhorsepower" data-toggle="tab" href="#kt_tab_pane_4_6">
                                                        <span class="nav-text">고마력 선외기 정비</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="highself" data-toggle="tab" href="#kt_tab_pane_4_8">
                                                        <span class="nav-text">자가정비 심화과정 (고마력 선외기)</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="sterndrive" data-toggle="tab" href="#kt_tab_pane_4_7">
                                                        <span class="nav-text">스턴드라이브 정비</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="basic" data-toggle="tab" href="#kt_tab_pane_4_10">
                                                        <span class="nav-text">기초정비교육</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="emergency" data-toggle="tab" href="#kt_tab_pane_4_11">
                                                        <span class="nav-text">응급조치교육</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="generator" data-toggle="tab" href="#kt_tab_pane_4_9">
                                                        <span class="nav-text">발전기 정비 교육</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content">
                                            <%--<div class="tab-pane fade show active" id="kt_tab_pane_4_0" role="tabpanel" aria-labelledby="kt_tab_pane_4_0">

                                                <!-- 전체 교육 일정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>과정명</th>
                                                                <th>일정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">정규</td>
                                                                <td id="edu_all_order_title_1">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="과정명"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_all_order_contents_1">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            &lt;%&ndash;<c:if test="${m_contentsList eq null or empty m_contentsList}">&ndash;%&gt;
                                                                                <div class="mb-2 edu_all_order_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육일정"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            &lt;%&ndash;</c:if>&ndash;%&gt;
                                                                            &lt;%&ndash;<c:forEach var="info" items="${m_contentsList}" begin="0" end="${m_contentsList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'inboarder'}">
                                                                                    <div class="mb-2 edu_all_order_contents_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육일정"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>&ndash;%&gt;
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">정규</td>
                                                                <td id="edu_all_order_title_2">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="과정명"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_all_order_contents_2">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                                &lt;%&ndash;<c:if test="${m_contentsList eq null or empty m_contentsList}">&ndash;%&gt;
                                                                            <div class="mb-2 edu_all_order_contents_detail" data-repeater-item>
                                                                                <div class="input-group">
                                                                                    <input type="text" class="form-control" name="text-input" placeholder="교육일정"/>
                                                                                    <div class="input-group-append">
                                                                                        <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                            <i class="ki-duotone ki-minus"></i>
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                                &lt;%&ndash;</c:if>&ndash;%&gt;
                                                                                &lt;%&ndash;<c:forEach var="info" items="${m_contentsList}" begin="0" end="${m_contentsList.size()}" step="1" varStatus="status">
                                                                                    <c:if test="${info.middle eq 'inboarder'}">
                                                                                        <div class="mb-2 edu_all_order_contents_detail" data-repeater-item>
                                                                                            <div class="input-group">
                                                                                                <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육일정"/>
                                                                                                <div class="input-group-append">
                                                                                                    <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                        <i class="ki-duotone ki-minus"></i>
                                                                                                    </a>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </c:if>
                                                                                </c:forEach>&ndash;%&gt;
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //전체 교육 일정 -->

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_marina_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('all');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //마리나 선박 정비사 실기교육 -->
                                            </div>--%>
                                            <div class="tab-pane fade show active" id="kt_tab_pane_4_1" role="tabpanel" aria-labelledby="kt_tab_pane_4_1">

                                                <!-- 마리나 선박 정비사 실기교육 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>선외기 정비사 실무교육</th>
                                                                <th>선내기 정비사 실무교육</th>
                                                                <th>FRP 선체 정비사 실무교육</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_marina_outboarder_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${m_contentsList eq null or empty m_contentsList}">
                                                                                <div class="mb-2 edu_marina_outboarder_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${m_contentsList}" begin="0" end="${m_contentsList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'outboarder'}">
                                                                                    <div class="mb-2 edu_marina_outboarder_contents_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${m_contentsList eq null or empty m_contentsList}">
                                                                                <div class="mb-2 edu_marina_inboarder_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${m_contentsList}" begin="0" end="${m_contentsList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'inboarder'}">
                                                                                    <div class="mb-2 edu_marina_inboarder_contents_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${m_contentsList eq null or empty m_contentsList}">
                                                                                <div class="mb-2 edu_marina_frp_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${m_contentsList}" begin="0" end="${m_contentsList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'frp'}">
                                                                                    <div class="mb-2 edu_marina_frp_contents_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_marina_outboarder_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${m_periodList eq null or empty m_periodList}">
                                                                                <div class="mb-2 edu_marina_outboarder_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${m_periodList}" begin="0" end="${m_periodList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'outboarder'}">
                                                                                    <div class="mb-2 edu_marina_outboarder_period_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${m_periodList eq null or empty m_periodList}">
                                                                                <div class="mb-2 edu_marina_inboarder_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${m_periodList}" begin="0" end="${m_periodList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'inboarder'}">
                                                                                    <div class="mb-2 edu_marina_inboarder_period_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${m_periodList eq null or empty m_periodList}">
                                                                                <div class="mb-2 edu_marina_frp_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${m_periodList}" begin="0" end="${m_periodList.size()}" step="1" varStatus="status">
                                                                                <c:if test="${info.middle eq 'frp'}">
                                                                                    <div class="mb-2 edu_marina_frp_period_detail" data-repeater-item>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                            <div class="input-group-append">
                                                                                                <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                    <i class="ki-duotone ki-minus"></i>
                                                                                                </a>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_marina_outboarder_days">
                                                                    <div class="item">
                                                                        <textarea class="form-control" placeholder="교육일수"></textarea>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_days">
                                                                    <div class="item">
                                                                        <textarea class="form-control" placeholder="교육일수"></textarea>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_days">
                                                                    <div class="item">
                                                                        <textarea class="form-control" placeholder="교육일수"></textarea>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_marina_outboarder_time">
                                                                    <div class="item">
                                                                        <textarea class="form-control" placeholder="교육일수"></textarea>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_time">
                                                                    <div class="item">
                                                                        <textarea class="form-control" placeholder="교육일수"></textarea>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_time">
                                                                    <div class="item">
                                                                        <textarea class="form-control" placeholder="교육일수"></textarea>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_marina_outboarder_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_marina_outboarder_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_marina_outboarder_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_inboarder_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                                <td id="edu_marina_frp_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //마리나 선박 정비사 실기교육 -->

                                                <!-- 마리나 선박 정비사 실기교육 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_marina_right">
                                                            <div class="gubun">지원자격</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="지원자격"></textarea>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_marina_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_marina_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_marina_recruit_period">
                                                            <div class="gubun">모집기간</div>
                                                            <div class="naeyong">
                                                                <div class="form-group mb-3">
                                                                    <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                        <i class="ki-duotone ki-plus fs-3"></i>
                                                                        Add
                                                                    </a>
                                                                </div>
                                                                <div class="item list form-group row">
                                                                    <div data-repeater-list="kt_docs_repeater_basic">
                                                                        <c:if test="${m_recruitPeriodList eq null or empty m_recruitPeriodList}">
                                                                            <div class="mb-2 edu_marina_recruit_period_detail" data-repeater-item>
                                                                                <div class="input-group">
                                                                                    <input type="text" class="form-control" name="text-input" placeholder="모집기간"/>
                                                                                    <div class="input-group-append">
                                                                                        <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                            <i class="ki-duotone ki-minus"></i>
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:forEach var="info" items="${m_recruitPeriodList}" begin="0" end="${m_recruitPeriodList.size()}" step="1" varStatus="status">
                                                                            <div class="mb-2 edu_marina_recruit_period_detail" data-repeater-item>
                                                                                <div class="input-group">
                                                                                    <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="모집기간"/>
                                                                                    <div class="input-group-append">
                                                                                        <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                            <i class="ki-duotone ki-minus"></i>
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                                <div class="img">
                                                                    <img src="/img/img_guide_marina.png" class="pc" style="width: 100%;">
                                                                    <img src="/img/img_guide_marina_m.png" class="m">
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_marina_complete_condition">
                                                            <div class="gubun">수료조건</div>
                                                            <div class="naeyong">
                                                                <div class="form-group mb-3">
                                                                    <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                        <i class="ki-duotone ki-plus fs-3"></i>
                                                                        Add
                                                                    </a>
                                                                </div>
                                                                <div class="item list form-group row">
                                                                    <div data-repeater-list="kt_docs_repeater_basic">
                                                                        <c:if test="${m_completeConditionList eq null or empty m_completeConditionList}">
                                                                            <div class="mb-2 edu_marina_complete_condition_detail" data-repeater-item>
                                                                                <div class="input-group">
                                                                                    <input type="text" class="form-control" name="text-input" placeholder="수료조건"/>
                                                                                    <div class="input-group-append">
                                                                                        <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                            <i class="ki-duotone ki-minus"></i>
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:forEach var="info" items="${m_completeConditionList}" begin="0" end="${m_completeConditionList.size()}" step="1" varStatus="status">
                                                                            <div class="mb-2 edu_marina_complete_condition_detail" data-repeater-item>
                                                                                <div class="input-group">
                                                                                    <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="수료조건"/>
                                                                                    <div class="input-group-append">
                                                                                        <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                            <i class="ki-duotone ki-minus"></i>
                                                                                        </a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_marina_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('marina');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //마리나 선박 정비사 실기교육 -->
                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_2" role="tabpanel" aria-labelledby="kt_tab_pane_4_2">

                                                <!-- 위탁교육 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>위탁교육</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_commission_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${c_targetList eq null or empty c_targetList}">
                                                                                <div class="mb-2 edu_commission_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${c_targetList}" begin="0" end="${c_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_commission_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_commission_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${c_contentsList eq null or empty c_contentsList}">
                                                                                <div class="mb-2 edu_commission_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${c_contentsList}" begin="0" end="${c_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_commission_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>※ 기관별 맞춤형 프로그램 구성 가능 (별도 문의)</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_commission_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${c_periodList eq null or empty c_periodList}">
                                                                                <div class="mb-2 edu_commission_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${c_periodList}" begin="0" end="${c_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_commission_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>※ 기관별 맞춤형 프로그램 구성 가능 (별도 문의)</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_commission_place">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${c_placeList eq null or empty c_placeList}">
                                                                                <div class="mb-2 edu_commission_place_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장소"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${c_placeList}" begin="0" end="${c_placeList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_commission_place_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장소"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //위탁교육 -->

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_commission_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('commission');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //위탁교육 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_3" role="tabpanel" aria-labelledby="kt_tab_pane_4_3">

                                                <!-- 선외기 기초정비실습 과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>선외기 기초정비실습 과정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_boarder_outboarder_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${o_targetList eq null or empty o_targetList}">
                                                                                <div class="mb-2 edu_boarder_outboarder_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${o_targetList}" begin="0" end="${o_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_outboarder_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_boarder_outboarder_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${o_contentsList eq null or empty o_contentsList}">
                                                                                <div class="mb-2 edu_boarder_outboarder_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${o_contentsList}" begin="0" end="${o_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_outboarder_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_boarder_outboarder_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${o_periodList eq null or empty o_periodList}">
                                                                                <div class="mb-2 edu_boarder_outboarder_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${o_periodList}" begin="0" end="${o_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_outboarder_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_boarder_outboarder_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_boarder_outboarder_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_boarder_outboarder_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_boarder_outboarder_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_boarder_outboarder_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>※ 분리교육 진행 시 교육비 별도 공지 예정</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //선외기 기초정비실습 과정 -->

                                                <!-- 선외기 기초정비실습 과정 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_boarder_outboarder_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_boarder_outboarder_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_boarder_outboarder_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_outboarder_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('outboarder');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //선외기 기초정비실습 과정 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_4" role="tabpanel" aria-labelledby="kt_tab_pane_4_4">

                                                <!-- 선내기 기초정비실습 과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>선내기 기초정비실습 과정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_boarder_inboarder_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${i_targetList eq null or empty i_targetList}">
                                                                                <div class="mb-2 edu_boarder_inboarder_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${i_targetList}" begin="0" end="${i_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_inboarder_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_boarder_inboarder_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${i_contentsList eq null or empty i_contentsList}">
                                                                                <div class="mb-2 edu_boarder_inboarder_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${i_contentsList}" begin="0" end="${i_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_inboarder_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_boarder_inboarder_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${i_periodList eq null or empty i_periodList}">
                                                                                <div class="mb-2 edu_boarder_inboarder_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${i_periodList}" begin="0" end="${i_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_inboarder_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_boarder_inboarder_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_boarder_inboarder_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_boarder_inboarder_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_boarder_inboarder_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_boarder_inboarder_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>※ 분리교육 진행 시 교육비 별도 공지 예정</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //선내기 기초정비실습 과정 -->

                                                <!-- 선내기 기초정비실습 과정 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_boarder_inboarder_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_boarder_inboarder_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_boarder_inboarder_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_inboarder_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('inboarder');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //선내기 기초정비실습 과정 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_5" role="tabpanel" aria-labelledby="kt_tab_pane_4_5">

                                                <!-- 세일요트 기초정비실습 과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>세일요트 기초정비실습 과정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_boarder_sailyacht_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${s_targetList eq null or empty s_targetList}">
                                                                                <div class="mb-2 edu_boarder_sailyacht_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${s_targetList}" begin="0" end="${s_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_sailyacht_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_boarder_sailyacht_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${s_contentsList eq null or empty s_contentsList}">
                                                                                <div class="mb-2 edu_boarder_sailyacht_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${s_contentsList}" begin="0" end="${s_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_sailyacht_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_boarder_sailyacht_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${s_periodList eq null or empty s_periodList}">
                                                                                <div class="mb-2 edu_boarder_sailyacht_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${s_periodList}" begin="0" end="${s_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_boarder_sailyacht_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_boarder_sailyacht_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_boarder_sailyacht_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_boarder_sailyacht_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_boarder_sailyacht_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_boarder_sailyacht_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>※ 분리교육 진행 시 교육비 별도 공지 예정</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //세일요트 기초정비실습 과정 -->

                                                <!-- 세일요트 기초정비실습 과정 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_boarder_sailyacht_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_boarder_sailyacht_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_boarder_sailyacht_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_sailyacht_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('sailyacht');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //세일요트 기초정비실습 과정 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_6" role="tabpanel" aria-labelledby="kt_tab_pane_4_6">

                                                <!-- 고마력 선외기 정비 중급 테크니션 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>고마력 선외기 정비 중급 테크니션</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_highhorsepower_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${h_targetList eq null or empty h_targetList}">
                                                                                <div class="mb-2 edu_highhorsepower_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${h_targetList}" begin="0" end="${h_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highhorsepower_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_highhorsepower_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${h_contentsList eq null or empty h_contentsList}">
                                                                                <div class="mb-2 edu_highhorsepower_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${h_contentsList}" begin="0" end="${h_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highhorsepower_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_highhorsepower_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${h_periodList eq null or empty h_periodList}">
                                                                                <div class="mb-2 edu_highhorsepower_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${h_periodList}" begin="0" end="${h_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highhorsepower_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_highhorsepower_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_highhorsepower_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_highhorsepower_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_highhorsepower_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_highhorsepower_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장비 -->
                                                            <tr>
                                                                <td class="gubun">교육장비</td>
                                                                <td id="edu_highhorsepower_stuff">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${h_stuffList eq null or empty h_stuffList}">
                                                                                <div class="mb-2 edu_highhorsepower_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${h_stuffList}" begin="0" end="${h_stuffList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highhorsepower_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //고마력 선외기 정비 중급 테크니션 -->

                                                <!-- 고마력 선외기 정비 중급 테크니션 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_highhorsepower_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_highhorsepower_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_highhorsepower_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_highhorsepower_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('highhorsepower');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //고마력 선외기 정비 중급 테크니션 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_8" role="tabpanel" aria-labelledby="kt_tab_pane_4_8">

                                                <!-- 자가정비 심화과정 (고마력 선외기) -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>자가정비 심화과정 (고마력 선외기)</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_highself_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${e_targetList eq null or empty e_targetList}">
                                                                                <div class="mb-2 edu_highsele_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${e_targetList}" begin="0" end="${e_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highself_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_highself_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${e_contentsList eq null or empty e_contentsList}">
                                                                                <div class="mb-2 edu_highself_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${e_contentsList}" begin="0" end="${e_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highself_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_highself_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${e_periodList eq null or empty e_periodList}">
                                                                                <div class="mb-2 edu_highself_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${e_periodList}" begin="0" end="${e_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highself_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_highself_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_highself_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_highself_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_highself_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_highself_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장비 -->
                                                            <tr>
                                                                <td class="gubun">교육장비</td>
                                                                <td id="edu_highself_stuff">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${e_stuffList eq null or empty e_stuffList}">
                                                                                <div class="mb-2 edu_highself_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${e_stuffList}" begin="0" end="${e_stuffList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_highself_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //자가정비 심화과정 (고마력 선외기) -->

                                                <!-- 자가정비 심화과정 (고마력 선외기) -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_highself_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_highself_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_highself_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_highself_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('highself');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //자가정비 심화과정 (고마력 선외기) -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_7" role="tabpanel" aria-labelledby="kt_tab_pane_4_7">

                                                <!-- 스턴드라이브 정비 전문가과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>스턴드라이브(Sterndrive) 정비 전문가과정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_sterndrive_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${t_targetList eq null or empty t_targetList}">
                                                                                <div class="mb-2 edu_sterndrive_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${t_targetList}" begin="0" end="${t_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_sterndrive_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_sterndrive_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${t_contentsList eq null or empty t_contentsList}">
                                                                                <div class="mb-2 edu_sterndrive_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${t_contentsList}" begin="0" end="${t_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_sterndrive_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_sterndrive_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${t_periodList eq null or empty t_periodList}">
                                                                                <div class="mb-2 edu_sterndrive_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${t_periodList}" begin="0" end="${t_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_sterndrive_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_sterndrive_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_sterndrive_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_sterndrive_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_sterndrive_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_sterndrive_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장비 -->
                                                            <tr>
                                                                <td class="gubun">교육장비</td>
                                                                <td id="edu_sterndrive_stuff">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${t_stuffList eq null or empty t_stuffList}">
                                                                                <div class="mb-2 edu_sterndrive_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${t_stuffList}" begin="0" end="${t_stuffList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_sterndrive_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //고마력 선외기 정비 중급 테크니션 -->

                                                <!-- 고마력 선외기 정비 중급 테크니션 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_sterndrive_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_sterndrive_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_sterndrive_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_sterndrive_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('sterndrive');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //스턴드라이브 정비 전문가과정 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_10" role="tabpanel" aria-labelledby="kt_tab_pane_4_10">

                                                <!-- 기초정비교육 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>해상엔진 기초정비교육과정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_basic_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${b_targetList eq null or empty b_targetList}">
                                                                                <div class="mb-2 edu_basic_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${b_targetList}" begin="0" end="${b_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_basic_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_basic_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${b_contentsList eq null or empty b_contentsList}">
                                                                                <div class="mb-2 edu_basic_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${b_contentsList}" begin="0" end="${b_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_basic_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장비 -->
                                                            <tr>
                                                                <td class="gubun">교육장비</td>
                                                                <td id="edu_basic_stuff">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${b_stuffList eq null or empty b_stuffList}">
                                                                                <div class="mb-2 edu_basic_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${b_stuffList}" begin="0" end="${b_stuffList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_basic_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_basic_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${b_periodList eq null or empty b_periodList}">
                                                                                <div class="mb-2 edu_basic_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${b_periodList}" begin="0" end="${b_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_basic_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_basic_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_basic_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_basic_place">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${b_placeList eq null or empty b_placeList}">
                                                                                <div class="mb-2 edu_basic_place_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장소"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${b_placeList}" begin="0" end="${b_placeList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_basic_place_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장소"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_basic_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원(4명)에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_basic_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //기초정비교육 -->

                                                <!-- 기초정비교육 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_basic_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_basic_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_basic_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(15명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_basic_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('basic');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //기초정비교육 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_11" role="tabpanel" aria-labelledby="kt_tab_pane_4_11">

                                                <!-- 기초정비교육 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>해상엔진 응급조치교육과정</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_emergency_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${y_targetList eq null or empty y_targetList}">
                                                                                <div class="mb-2 edu_emergency_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${y_targetList}" begin="0" end="${y_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_emergency_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_emergency_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${y_contentsList eq null or empty y_contentsList}">
                                                                                <div class="mb-2 edu_emergency_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${y_contentsList}" begin="0" end="${y_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_emergency_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장비 -->
                                                            <tr>
                                                                <td class="gubun">교육장비</td>
                                                                <td id="edu_emergency_stuff">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${y_stuffList eq null or empty y_stuffList}">
                                                                                <div class="mb-2 edu_emergency_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${y_stuffList}" begin="0" end="${y_stuffList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_emergency_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_emergency_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${y_periodList eq null or empty y_periodList}">
                                                                                <div class="mb-2 edu_emergency_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${y_periodList}" begin="0" end="${y_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_emergency_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_emergency_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_emergency_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_emergency_place">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${y_placeList eq null or empty y_placeList}">
                                                                                <div class="mb-2 edu_emergency_place_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장소"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${y_placeList}" begin="0" end="${y_placeList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_emergency_place_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장소"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_emergency_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원(4명)에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_emergency_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //기초정비교육 -->

                                                <!-- 기초정비교육 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_emergency_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_emergency_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_emergency_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(15명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_emergency_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('emergency');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //응급조치교육 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_9" role="tabpanel" aria-labelledby="kt_tab_pane_4_9">

                                                <!-- 발전기 정비 교육 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>발전기 정비 교육</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <!-- 교육대상 -->
                                                            <tr>
                                                                <td class="gubun">교육대상</td>
                                                                <td id="edu_generator_target">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kg_docs_repeater_basic">
                                                                            <c:if test="${g_targetList eq null or empty g_targetList}">
                                                                                <div class="mb-2 edu_generator_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${g_targetList}" begin="0" end="${g_targetList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_generator_target_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육대상"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육내용 -->
                                                            <tr>
                                                                <td class="gubun">교육내용</td>
                                                                <td id="edu_generator_contents">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${g_contentsList eq null or empty g_contentsList}">
                                                                                <div class="mb-2 edu_generator_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${g_contentsList}" begin="0" end="${g_contentsList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_generator_contents_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육내용"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육기간 -->
                                                            <tr>
                                                                <td class="gubun">교육기간</td>
                                                                <td id="edu_generator_period">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${g_periodList eq null or empty g_periodList}">
                                                                                <div class="mb-2 edu_generator_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${g_periodList}" begin="0" end="${g_periodList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_generator_period_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육기간"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육일수 -->
                                                            <tr>
                                                                <td class="gubun">교육일수</td>
                                                                <td id="edu_generator_days">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육일수"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육시간 -->
                                                            <tr>
                                                                <td class="gubun">교육시간</td>
                                                                <td id="edu_generator_time">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육시간"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장소 -->
                                                            <tr>
                                                                <td class="gubun">교육장소</td>
                                                                <td id="edu_generator_place">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="장소명"/>
                                                                    </div>
                                                                    <div class="address">
                                                                        <input type="text" class="form-control" placeholder="상세주소"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육인원 -->
                                                            <tr>
                                                                <td class="gubun">교육인원</td>
                                                                <td id="edu_generator_persons">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육인원"/>
                                                                    </div>
                                                                    <div class="cmnt">
                                                                        <p>· 주1) 교육 신청 현황에 따라 조정 가능합니다.</p>
                                                                        <p>· 주2) 교육신청자가 최소 인원에 미달하는 경우에는 해당 차수의 교육과정이 취소될 수 있습니다.</p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육비 -->
                                                            <tr>
                                                                <td class="gubun">교육비</td>
                                                                <td id="edu_generator_pay">
                                                                    <div class="item">
                                                                        <input type="text" class="form-control" placeholder="교육비"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <!-- 교육장비 -->
                                                            <tr>
                                                                <td class="gubun">교육장비</td>
                                                                <td id="edu_generator_stuff">
                                                                    <div class="form-group mb-3">
                                                                        <a href="javascript:;" class="btn btn-light-warning" data-repeater-create>
                                                                            <i class="ki-duotone ki-plus fs-3"></i>
                                                                            Add
                                                                        </a>
                                                                    </div>
                                                                    <div class="item list form-group row">
                                                                        <div data-repeater-list="kt_docs_repeater_basic">
                                                                            <c:if test="${g_stuffList eq null or empty g_stuffList}">
                                                                                <div class="mb-2 edu_generator_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:forEach var="info" items="${g_stuffList}" begin="0" end="${g_stuffList.size()}" step="1" varStatus="status">
                                                                                <div class="mb-2 edu_generator_stuff_detail" data-repeater-item>
                                                                                    <div class="input-group">
                                                                                        <input type="text" class="form-control" name="text-input" value="${info.value}" placeholder="교육장비"/>
                                                                                        <div class="input-group-append">
                                                                                            <a href="javascript:;" class="btn font-weight-bold btn-light-danger btn-icon" data-repeater-delete>
                                                                                                <i class="ki-duotone ki-minus"></i>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <!-- //고마력 선외기 정비 중급 테크니션 -->

                                                <!-- 고마력 선외기 정비 중급 테크니션 -->
                                                <div class="guide_tp_02">
                                                    <ul>
                                                        <li id="edu_generator_apply_method">
                                                            <div class="gubun">신청방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="신청방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <input type="text" class="form-control" placeholder="URL입력"/>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_generator_recruit_method">
                                                            <div class="gubun">모집방법</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집방법"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 신청취소 등 교육인원 축소 대비 대기인원 접수</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li id="edu_generator_recruit_period">
                                                            <div class="gubun">모집마감</div>
                                                            <div class="naeyong">
                                                                <div class="item">
                                                                    <textarea class="form-control" placeholder="모집기간"></textarea>
                                                                </div>
                                                                <div class="cmnt">
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당 과정 폐강</p>
                                                                    <p>※ 마감일 기준 교육정원(16명)에게 개별적으로 교육확정 문자 알림 예정</p>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>

                                                <div class="card-footer d-flex justify-content-between">
                                                    <div></div>
                                                    <div>
                                                        <button type="button" onclick="f_train_template_generator_save();" class="btn font-weight-bold btn-primary mr-2">저장</button>
                                                        <button type="button" onclick="f_train_template_init('generator');" class="btn font-weight-bold btn-secondary">취소</button>
                                                    </div>
                                                </div>

                                                <!-- //스턴드라이브 정비 전문가과정 -->

                                            </div>

                                        </div>
                                    </div>
                                </div>
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
    <script src="/js/jquery.repeater.js"></script>
    <script src="/assets/js/custom/formrepeater.bundle.js"></script>
    <!--end::Custom Javascript-->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    <!--begin::Custom Javascript(used for common page)-->
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/template.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <script>
        $(function(){
            // 탭네임
            let tapName = '${tapName}';
            if(nvl(tapName,'') !== ''){
                $('#' + tapName).tab('show');

                if(tapName === 'outboarder' || tapName === 'inboarder' || tapName === 'sailyacht'){
                    $('#boarder').addClass('active');
                }else{
                    $('#boarder').removeClass('active');
                }
            }

            /********************
             * 마리나
             * ******************/
            // 교육일수
            <c:forEach var="info" items="${m_daysList}" begin="0" end="${m_daysList.size()}" step="1" varStatus="status">
                <c:if test="${info.middle eq 'outboarder'}">
                    let outboarder_days = '${info.value}'.replaceAll('<br/>', '\r\n');
                    $('#edu_marina_outboarder_days').find('textarea').val(outboarder_days);
                </c:if>
                <c:if test="${info.middle eq 'inboarder'}">
                    let inboarder_days = '${info.value}'.replaceAll('<br/>', '\r\n');
                    $('#edu_marina_inboarder_days').find('textarea').val(inboarder_days);
                </c:if>
                <c:if test="${info.middle eq 'frp'}">
                    let frp_days = '${info.value}'.replaceAll('<br/>', '\r\n');
                    $('#edu_marina_frp_days').find('textarea').val(frp_days);
                </c:if>
            </c:forEach>

            // 교육시간
            <c:forEach var="info" items="${m_timeList}" begin="0" end="${m_timeList.size()}" step="1" varStatus="status">
                <c:if test="${info.middle eq 'outboarder'}">
                    let outboarder_time = '${info.value}'.replaceAll('<br/>', '\r\n');
                    $('#edu_marina_outboarder_time').find('textarea').val(outboarder_time);
                </c:if>
                <c:if test="${info.middle eq 'inboarder'}">
                    let inboarder_time = '${info.value}'.replaceAll('<br/>', '\r\n');
                    $('#edu_marina_inboarder_time').find('textarea').val(inboarder_time);
                </c:if>
                <c:if test="${info.middle eq 'frp'}">
                    let frp_time = '${info.value}'.replaceAll('<br/>', '\r\n');
                    $('#edu_marina_frp_time').find('textarea').val(frp_time);
                </c:if>
            </c:forEach>

            // 교육장소 명
            <c:forEach var="info" items="${m_placeList}" begin="0" end="${m_placeList.size()}" step="1" varStatus="status">
                <c:if test="${info.middle eq 'outboarder'}">
                    $('#edu_marina_outboarder_place').find('.item').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'inboarder'}">
                    $('#edu_marina_inboarder_place').find('.item').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'frp'}">
                    $('#edu_marina_frp_place').find('.item').find('input[type=text]').val('${info.value}');
                </c:if>
            </c:forEach>

            // 교육장소 내용
            <c:forEach var="info" items="${m_placeDetailList}" begin="0" end="${m_placeDetailList.size()}" step="1" varStatus="status">
                <c:if test="${info.middle eq 'outboarder'}">
                    $('#edu_marina_outboarder_place').find('.address').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'inboarder'}">
                    $('#edu_marina_inboarder_place').find('.address').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'frp'}">
                    $('#edu_marina_frp_place').find('.address').find('input[type=text]').val('${info.value}');
                </c:if>
            </c:forEach>

            // 교육인원
            <c:forEach var="info" items="${m_personsList}" begin="0" end="${m_personsList.size()}" step="1" varStatus="status">
                <c:if test="${info.middle eq 'outboarder'}">
                    $('#edu_marina_outboarder_persons').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'inboarder'}">
                    $('#edu_marina_inboarder_persons').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'frp'}">
                    $('#edu_marina_frp_persons').find('input[type=text]').val('${info.value}');
                </c:if>
            </c:forEach>

            // 교육비
            <c:forEach var="info" items="${m_payList}" begin="0" end="${m_payList.size()}" step="1" varStatus="status">
                <c:if test="${info.middle eq 'outboarder'}">
                    $('#edu_marina_outboarder_pay').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'inboarder'}">
                    $('#edu_marina_inboarder_pay').find('input[type=text]').val('${info.value}');
                </c:if>
                <c:if test="${info.middle eq 'frp'}">
                    $('#edu_marina_frp_pay').find('input[type=text]').val('${info.value}');
                </c:if>
            </c:forEach>

            // 지원자격
            let right = '${m_right.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_marina_right').find('.item').find('textarea').val(right);

            // 신청방법
            let applyMethod = '${m_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_marina_apply_method').find('.item').find('textarea').val(applyMethod);
            $('#edu_marina_apply_method').find('.cmnt').find('input[type=text]').val('${m_applyMethodUrl.value}');

            // 모집방법
            let recruitMethod = '${m_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_marina_recruit_method').find('.item').find('textarea').val(recruitMethod);

            /********************
             * 선외기
             * ******************/
            // 교육일수
            $('#edu_boarder_outboarder_days').find('input[type=text]').val('${o_days.value}');

            // 교육시간
            $('#edu_boarder_outboarder_time').find('input[type=text]').val('${o_time.value}');

            // 교육장소
            $('#edu_boarder_outboarder_place').find('.item').find('input[type=text]').val('${o_place.value}');
            $('#edu_boarder_outboarder_place').find('.address').find('input[type=text]').val('${o_placeDetail.value}');

            // 교육인원
            $('#edu_boarder_outboarder_persons').find('input[type=text]').val('${o_persons.value}');

            // 교육비
            $('#edu_boarder_outboarder_pay').find('input[type=text]').val('${o_pay.value}');

            // 신청방법
            let o_applyMethod = '${o_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_outboarder_apply_method').find('.item').find('textarea').val(o_applyMethod);
            $('#edu_boarder_outboarder_apply_method').find('.cmnt').find('input[type=text]').val('${o_applyMethodUrl.value}');

            // 모집방법
            let o_recruitMethod = '${o_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_outboarder_recruit_method').find('.item').find('textarea').val(o_recruitMethod);

            // 모집기간
            let o_recruitPeriod = '${o_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_outboarder_recruit_period').find('.item').find('textarea').val(o_recruitPeriod);

            /********************
             * 선내기
             * ******************/
            // 교육일수
            $('#edu_boarder_inboarder_days').find('input[type=text]').val('${i_days.value}');

            // 교육시간
            $('#edu_boarder_inboarder_time').find('input[type=text]').val('${i_time.value}');

            // 교육장소
            $('#edu_boarder_inboarder_place').find('.item').find('input[type=text]').val('${i_place.value}');
            $('#edu_boarder_inboarder_place').find('.address').find('input[type=text]').val('${i_placeDetail.value}');

            // 교육인원
            $('#edu_boarder_inboarder_persons').find('input[type=text]').val('${i_persons.value}');

            // 교육비
            $('#edu_boarder_inboarder_pay').find('input[type=text]').val('${i_pay.value}');

            // 신청방법
            let i_applyMethod = '${i_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_inboarder_apply_method').find('.item').find('textarea').val(i_applyMethod);
            $('#edu_boarder_inboarder_apply_method').find('.cmnt').find('input[type=text]').val('${i_applyMethodUrl.value}');

            // 모집방법
            let i_recruitMethod = '${i_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_inboarder_recruit_method').find('.item').find('textarea').val(i_recruitMethod);

            // 모집기간
            let i_recruitPeriod = '${i_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_inboarder_recruit_period').find('.item').find('textarea').val(i_recruitPeriod);

            /********************
             * 세일요트
             * ******************/
            // 교육일수
            $('#edu_boarder_sailyacht_days').find('input[type=text]').val('${s_days.value}');

            // 교육시간
            $('#edu_boarder_sailyacht_time').find('input[type=text]').val('${s_time.value}');

            // 교육장소
            $('#edu_boarder_sailyacht_place').find('.item').find('input[type=text]').val('${s_place.value}');
            $('#edu_boarder_sailyacht_place').find('.address').find('input[type=text]').val('${s_placeDetail.value}');

            // 교육인원
            $('#edu_boarder_sailyacht_persons').find('input[type=text]').val('${s_persons.value}');

            // 교육비
            $('#edu_boarder_sailyacht_pay').find('input[type=text]').val('${s_pay.value}');

            // 신청방법
            let s_applyMethod = '${s_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_sailyacht_apply_method').find('.item').find('textarea').val(s_applyMethod);
            $('#edu_boarder_sailyacht_apply_method').find('.cmnt').find('input[type=text]').val('${s_applyMethodUrl.value}');

            // 모집방법
            let s_recruitMethod = '${s_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_sailyacht_recruit_method').find('.item').find('textarea').val(s_recruitMethod);

            // 모집기간
            let s_recruitPeriod = '${s_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_boarder_sailyacht_recruit_period').find('.item').find('textarea').val(s_recruitPeriod);

            /********************
             * 고마력
             * ******************/
            // 교육일수
            $('#edu_highhorsepower_days').find('input[type=text]').val('${h_days.value}');

            // 교육시간
            $('#edu_highhorsepower_time').find('input[type=text]').val('${h_time.value}');

            // 교육장소
            $('#edu_highhorsepower_place').find('.item').find('input[type=text]').val('${h_place.value}');
            $('#edu_highhorsepower_place').find('.address').find('input[type=text]').val('${h_placeDetail.value}');

            // 교육인원
            $('#edu_highhorsepower_persons').find('input[type=text]').val('${h_persons.value}');

            // 교육비
            $('#edu_highhorsepower_pay').find('input[type=text]').val('${h_pay.value}');

            // 신청방법
            let h_applyMethod = '${h_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_highhorsepower_apply_method').find('.item').find('textarea').val(h_applyMethod);
            $('#edu_highhorsepower_apply_method').find('.cmnt').find('input[type=text]').val('${h_applyMethodUrl.value}');

            // 모집방법
            let h_recruitMethod = '${h_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_highhorsepower_recruit_method').find('.item').find('textarea').val(h_recruitMethod);

            // 모집기간
            let h_recruitPeriod = '${h_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_highhorsepower_recruit_period').find('.item').find('textarea').val(h_recruitPeriod);

            /********************
             * 고마력 자가정비
             * ******************/
            // 교육일수
            $('#edu_highself_days').find('input[type=text]').val('${e_days.value}');

            // 교육시간
            $('#edu_highself_time').find('input[type=text]').val('${e_time.value}');

            // 교육장소
            $('#edu_highself_place').find('.item').find('input[type=text]').val('${e_place.value}');
            $('#edu_highself_place').find('.address').find('input[type=text]').val('${e_placeDetail.value}');

            // 교육인원
            $('#edu_highself_persons').find('input[type=text]').val('${e_persons.value}');

            // 교육비
            $('#edu_highself_pay').find('input[type=text]').val('${e_pay.value}');

            // 신청방법
            let e_applyMethod = '${e_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_highself_apply_method').find('.item').find('textarea').val(e_applyMethod);
            $('#edu_highself_apply_method').find('.cmnt').find('input[type=text]').val('${e_applyMethodUrl.value}');

            // 모집방법
            let e_recruitMethod = '${e_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_highself_recruit_method').find('.item').find('textarea').val(e_recruitMethod);

            // 모집기간
            let e_recruitPeriod = '${e_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_highself_recruit_period').find('.item').find('textarea').val(e_recruitPeriod);
            
            /********************
             * Sterndrive
             * ******************/
            // 교육일수
            $('#edu_sterndrive_days').find('input[type=text]').val('${t_days.value}');

            // 교육시간
            $('#edu_sterndrive_time').find('input[type=text]').val('${t_time.value}');

            // 교육장소
            $('#edu_sterndrive_place').find('.item').find('input[type=text]').val('${t_place.value}');
            $('#edu_sterndrive_place').find('.address').find('input[type=text]').val('${t_placeDetail.value}');

            // 교육인원
            $('#edu_sterndrive_persons').find('input[type=text]').val('${t_persons.value}');

            // 교육비
            $('#edu_sterndrive_pay').find('input[type=text]').val('${t_pay.value}');

            // 신청방법
            let t_applyMethod = '${t_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_sterndrive_apply_method').find('.item').find('textarea').val(t_applyMethod);
            $('#edu_sterndrive_apply_method').find('.cmnt').find('input[type=text]').val('${t_applyMethodUrl.value}');

            // 모집방법
            let t_recruitMethod = '${t_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_sterndrive_recruit_method').find('.item').find('textarea').val(t_recruitMethod);

            // 모집기간
            let t_recruitPeriod = '${t_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_sterndrive_recruit_period').find('.item').find('textarea').val(t_recruitPeriod);

            /********************
             * Basic
             * ******************/
            // 교육일수
            $('#edu_basic_days').find('input[type=text]').val('${b_days.value}');

            // 교육시간
            $('#edu_basic_time').find('input[type=text]').val('${b_time.value}');

            // 교육인원
            $('#edu_basic_persons').find('input[type=text]').val('${b_persons.value}');

            // 교육비
            $('#edu_basic_pay').find('input[type=text]').val('${b_pay.value}');

            // 신청방법
            let b_applyMethod = '${b_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_basic_apply_method').find('.item').find('textarea').val(b_applyMethod);
            $('#edu_basic_apply_method').find('.cmnt').find('input[type=text]').val('${b_applyMethodUrl.value}');

            // 모집방법
            let b_recruitMethod = '${b_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_basic_recruit_method').find('.item').find('textarea').val(b_recruitMethod);

            // 모집기간
            let b_recruitPeriod = '${b_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_basic_recruit_period').find('.item').find('textarea').val(b_recruitPeriod);

            /********************
             * Emergency
             * ******************/
            // 교육일수
            $('#edu_emergency_days').find('input[type=text]').val('${y_days.value}');

            // 교육시간
            $('#edu_emergency_time').find('input[type=text]').val('${y_time.value}');

            // 교육인원
            $('#edu_emergency_persons').find('input[type=text]').val('${y_persons.value}');

            // 교육비
            $('#edu_emergency_pay').find('input[type=text]').val('${y_pay.value}');

            // 신청방법
            let y_applyMethod = '${y_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_emergency_apply_method').find('.item').find('textarea').val(y_applyMethod);
            $('#edu_emergency_apply_method').find('.cmnt').find('input[type=text]').val('${y_applyMethodUrl.value}');

            // 모집방법
            let y_recruitMethod = '${y_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_emergency_recruit_method').find('.item').find('textarea').val(y_recruitMethod);

            // 모집기간
            let y_recruitPeriod = '${y_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_emergency_recruit_period').find('.item').find('textarea').val(y_recruitPeriod);
            
            /********************
             * 발전기 정비 교육
             * ******************/
            // 교육일수
            $('#edu_generator_days').find('input[type=text]').val('${g_days.value}');

            // 교육시간
            $('#edu_generator_time').find('input[type=text]').val('${g_time.value}');

            // 교육장소
            $('#edu_generator_place').find('.item').find('input[type=text]').val('${g_place.value}');
            $('#edu_generator_place').find('.address').find('input[type=text]').val('${g_placeDetail.value}');

            // 교육인원
            $('#edu_generator_persons').find('input[type=text]').val('${g_persons.value}');

            // 교육비
            $('#edu_generator_pay').find('input[type=text]').val('${g_pay.value}');

            // 신청방법
            let g_applyMethod = '${g_applyMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_generator_apply_method').find('.item').find('textarea').val(g_applyMethod);
            $('#edu_generator_apply_method').find('.cmnt').find('input[type=text]').val('${g_applyMethodUrl.value}');

            // 모집방법
            let g_recruitMethod = '${g_recruitMethod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_generator_recruit_method').find('.item').find('textarea').val(g_recruitMethod);

            // 모집기간
            let g_recruitPeriod = '${g_recruitPeriod.value}'.replaceAll('<br/>', '\r\n');
            $('#edu_generator_recruit_period').find('.item').find('textarea').val(g_recruitPeriod);

        })
    </script>
    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>