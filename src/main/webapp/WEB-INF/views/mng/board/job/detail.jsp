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
                                        취/창업 성공후기</h1>
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
                                        <li class="breadcrumb-item text-muted">취/창업 성공후기</li>
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
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">제목</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="title" name="title" class="form-control form-control-lg form-control-solid-bg" placeholder="제목" value="${info.title}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">작성자</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="writer" name="writer" class="form-control form-control-lg form-control-solid-bg" placeholder="작성자" value="${info.writer eq null ? '관리자' : info.writer}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">작성일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input class="form-control form-control-solid" id="writeDate" name="writeDate" placeholder="작성일" value="${info.writeDate}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6 h-375px">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">내용</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <div id="quill_editor_content" class="h-325px">${info.content}</div>
                                                    <input type="hidden" id="quill_content" name="content" value="<c:out value="${info.content}" escapeXml="true"/>">
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">첨부파일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <!--begin::Excel import-->
                                                    <a href="" class="btn btn-primary btn-active-light-primary ms-auto" data-bs-toggle="modal" data-bs-target="#kt_modal_file_upload">
                                                        <i class="ki-duotone ki-exit-up fs-2">
                                                            <span class="path1"></span>
                                                            <span class="path2"></span>
                                                        </i>첨부파일 업로드</a>
                                                    <!--end::Excel import-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">첨부파일 목록</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <ul id="uploadFileList">
                                                        <c:forEach var="file" items="${fileList}">
                                                            <li class="mb-4">
                                                                <a href="/file/download.do?path=board/${file.folderPath}&fileName=${file.fullFileName}">
                                                                    <c:choose>
                                                                        <c:when test="${file.uuid ne null and file.uuid ne ''}">
                                                                            ${file.fileName}
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${file.fullFileName}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </a>
                                                                <input type="hidden" name="uploadFile" id="${file.id}" value="${file.fullFilePath}">
                                                                <button type="button" class="ml10" onclick="f_file_remove(this, '${file.id}')">
                                                                    <i class="ki-duotone ki-abstract-11">
                                                                        <i class="path1"></i>
                                                                        <i class="path2"></i>
                                                                    </i>
                                                                </button>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
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
                                            <a href="/mng/board/job.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_board_job_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_board_job_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_file_upload" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">첨부파일 업로드</h2>
                    <!--end::Modal title-->
                    <!--begin::Close-->
                    <div class="btn btn-sm btn-icon btn-active-color-primary" onclick="modalClose('attachFile')" data-bs-dismiss="modal">
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
                    <form id="modal_file_upload_form" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">File</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8">
                                <!--begin::Row-->
                                <div class="row">
                                    <!--begin::Col-->
                                    <div class="col-lg-8 d-inline-block">
                                        <input type="text" name="attachFile" class="form-control form-control-lg form-control-solid-bg upload_name" placeholder="파일명.확장자" disabled/>
                                    </div>
                                    <!--end::Col-->
                                    <!--begin::Col-->
                                    <div class="col-lg-3 d-inline-block ms-3">
                                        <input type="file" id="attachFileInput" name="file" class="d-none upload_hidden">
                                        <label class="btn btn-primary" for="attachFileInput">파일선택</label>
                                    </div>
                                    <!--end::Col-->
                                </div>
                                <!--end::Row-->
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
                            <a onclick="modalClose('attachFile')" class="btn btn-danger" data-bs-dismiss="modal">취소</a>
                            <!--end::Cancel-->
                        </div>
                        <!--end::Col-->
                        <!--begin::Col-->
                        <div class="ms-10">
                            <!--begin::File upload-->
                            <a onclick="f_attach_file_upload('${info.seq}','modal_file_upload_form','board/job')" class="btn btn-primary">업로드</a>
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
    <script src="/assets/js/custom/apps/ecommerce/catalog/quill-editor.js"></script>
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
    <script src="/js/mng/job.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>