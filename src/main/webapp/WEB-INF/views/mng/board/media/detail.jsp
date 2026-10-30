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
                                        영상자료</h1>
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
                                        <li class="breadcrumb-item text-muted">정보센터</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">게시판 관리</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">영상자료</li>
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
                                            <%--<!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">언어</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <!--begin::Select2-->
                                                    <select id="lang" name="lang" class="form-select form-select-solid" data-control="select2" aria-label="- 언어 -" data-placeholder="- 언어 -" data-hide-search="true">
                                                        <option></option>
                                                        <option disabled>- 언어 -</option>
                                                        <c:if test="${info ne null}">
                                                            <option value="KO" <c:if test="${info.lang eq 'KO'}">selected</c:if> >국문</option>
                                                            <option value="EN" <c:if test="${info.lang eq 'EN'}">selected</c:if> >영문</option>
                                                        </c:if>
                                                        <c:if test="${info eq null}">
                                                            <option value="KO" selected>국문</option>
                                                            <option value="EN">영문</option>
                                                        </c:if>
                                                    </select>
                                                    <!--end::Select2-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->--%>
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">제목</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="title" name="title" class="form-control form-control-lg form-control-solid-bg" placeholder="제목" value="${info.title}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">작성자</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="writer" name="writer" class="form-control form-control-lg form-control-solid-bg" placeholder="작성자" value="${info.writer eq null ? '관리자' : info.writer}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">작성일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="writeDate" name="writeDate" placeholder="작성일" value="${info.writeDate}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">영상 URL</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="mediaUrl" name="mediaUrl" placeholder="영상 URL" value="${info.mediaUrl}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <c:if test="${info.mediaUrl ne null}">
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-2 col-form-label fw-semibold fs-6">영상 URL</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-10">
                                                        <!-- 기존 유튜브 처리 부분 -->
                                                        <c:if test="${ fn:contains(info.mediaUrl , 'youtu') }">
                                                            <iframe src='https://www.youtube.com/embed/${info.mediaKey}?autoplay=0&mute=1' frameborder='0' allowfullscreen></iframe>
                                                        </c:if>

                                                        <!-- 인스타그램 릴스 처리 부분 -->
                                                        <c:if test="${ fn:contains(info.mediaUrl , 'instagram.com') }">
                                                            <iframe src='https://www.instagram.com/reel/${info.mediaKey}/embed/' frameborder='0' scrolling='no' allowtransparency='true' allowfullscreen></iframe>
                                                        </c:if>

                                                        <!-- 그 외 (일반 링크) -->
                                                        <c:if test="${ !fn:contains(info.mediaUrl , 'youtu') && !fn:contains(info.mediaUrl , 'instagram.com') }">
                                                            <iframe src='${info.mediaUrl}' frameborder='0' allowfullscreen></iframe>
                                                        </c:if>
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                            </c:if>

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
                                            <a href="/mng/board/media.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_board_media_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_board_media_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
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
    <script src="https://cdn.jsdelivr.net/npm/flatpickr@latest/dist/l10n/ko.js"></script>
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/media.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>