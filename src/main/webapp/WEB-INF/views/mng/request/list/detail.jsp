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
                                        요청사항 & 문의 관리</h1>
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
                                        <li class="breadcrumb-item text-muted">개발사</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">요청사항 & 문의</li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item">
                                            <span class="bullet bg-gray-400 w-5px h-2px"></span>
                                        </li>
                                        <!--end::Item-->
                                        <!--begin::Item-->
                                        <li class="breadcrumb-item text-muted">요청사항 & 문의 관리</li>
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
                                        <%-- hidden SEQ --%>
                                        <input type="hidden" id="seq" name="seq" value="${info.seq}">
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
                                            <h3 class="card-title align-items-start flex-column">
                                                <span class="card-label fw-semibold fs-3 mb-1">유지보수 작업일 안내</span>
                                                <span class="text-muted fw-semibold fs-7">Information</span>
                                            </h3>
                                            <div class="mt-5 mb-2">▷ 금요일 ~ 화요일 요청 : <span class="text-primary fw-bold">수요일</span></div>
                                            <div class="mb-2">▷ 수요일 ~ 목요일 요청 : <span class="text-primary fw-bold">금요일</span></div>
                                            <div>※ 에러발생 및 긴급처리가 필요하신 경우, 아래 <span class="text-primary fw-bold">긴급여부 항목에 체크하여 등록</span>해 주세요.</div>
                                        </div>
                                        <!--end::Card body-->

                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">작성자</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="writer" name="writer" class="form-control form-control-lg form-control-solid-bg" placeholder="작성자" value="${sessionScope.id}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">작성일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <c:set var="now" value="<%=new java.util.Date()%>"/>
                                                    <fmt:formatDate var="fmNow" value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                    <c:set var="sysdate" value="${info.writeDate eq null ? fmNow : info.writeDate}"/>
                                                    <input class="form-control form-control-solid" id="writeDate" name="writeDate" placeholder="작성일" value="${sysdate}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">처리예정일시</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="completeExpectDate" name="completeExpectDate" placeholder="처리예정일시" value="${info.completeExpectDate eq null ? '-':info.completeExpectDate}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">긴급여부</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex flex-wrap align-items-center">
                                                    <input type="checkbox" id="emergencyYn" name="emergencyYn" class="form-check-input form-control-solid-bg" value="Y" <c:if test="${info.emergencyYn eq 'Y'}">checked</c:if> />
                                                    <label class="form-check-label opacity-100 text-black ms-4" for="emergencyYn">긴급 요청일 경우 체크</label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">요청구분</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <!--begin::Select2-->
                                                    <select id="gbn" name="gbn" class="form-select form-select-solid" data-control="select2" aria-label="- 요청구분 -" data-placeholder="- 요청구분 -" data-hide-search="true">
                                                        <option></option>
                                                        <option value="" disabled>- 요청구분 -</option>
                                                        <option value="유지보수" <c:if test="${info.gbn eq '유지보수'}">selected</c:if> >유지보수</option>
                                                        <option value="오류" <c:if test="${info.gbn eq '오류'}">selected</c:if> >오류</option>
                                                        <option value="단순 문의" <c:if test="${info.gbn eq '단순 문의'}">selected</c:if> >단순 문의</option>
                                                        <option value="기능 추가 문의" <c:if test="${info.gbn eq '기능 추가 문의'}">selected</c:if> >기능 추가 문의</option>
                                                        <option value="뉴스레터" <c:if test="${info.gbn eq '뉴스레터'}">selected</c:if> >뉴스레터</option>
                                                        <option value="기타" <c:if test="${info.gbn eq '기타'}">selected</c:if> >기타</option>
                                                    </select>
                                                    <!--end::Select2-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <c:if test="${info ne null}">
                                                <!--begin::Input group-->
                                                <div class="row mb-6">
                                                    <!--begin::Label-->
                                                    <label class="col-lg-2 col-form-label fw-semibold fs-6 required">진행단계</label>
                                                    <!--end::Label-->
                                                    <!--begin::Col-->
                                                    <div class="col-lg-10">
                                                        <!--begin::Select2-->
                                                        <select id="progressStep" name="progressStep" class="form-select form-select-solid" data-control="select2" aria-label="- 진행단계 -" data-placeholder="- 진행단계 -" data-hide-search="true">
                                                            <option></option>
                                                            <option value="" disabled>- 진행단계 -</option>
                                                            <option value="처리대기" <c:if test="${info.progressStep eq '처리대기'}">selected</c:if> >처리대기</option>
                                                            <option value="진행중" <c:if test="${info.progressStep eq '진행중'}">selected</c:if> >진행중</option>
                                                            <option value="완료" <c:if test="${info.progressStep eq '완료'}">selected</c:if> >완료</option>
                                                            <option value="논의필요" <c:if test="${info.progressStep eq '논의필요'}">selected</c:if> >논의필요</option>
                                                            <option value="처리불가" <c:if test="${info.progressStep eq '처리불가'}">selected</c:if> >처리불가</option>
                                                        </select>
                                                        <!--end::Select2-->
                                                    </div>
                                                    <!--end::Col-->
                                                </div>
                                                <!--end::Input group-->
                                            </c:if>
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">제목</label>
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
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">내용</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <div id="quill_editor_content" class="h-325px">${info.content}</div>
                                                    <input type="hidden" id="quill_content" name="content" value="<c:out value="${info.content}" escapeXml="true"/>">
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">첨부파일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
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
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6"></label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    ※ 최대 10MB까지 업로드 가능합니다. 대용량 파일의 경우 ( kyj@meetingfan.com ) 으로 보내주세요.
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">첨부파일 목록</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <ul id="uploadFileList">
                                                        <c:forEach var="file" items="${fileList}">
                                                            <li class="mb-4">
                                                                <a href="/file/download.do?path=${file.folderPath}&fileName=${file.fullFileName}">
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

                                <c:if test="${info ne null}">
                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">

                                        <!--begin::Card header-->
                                        <div class="card-header border-0">
                                            <!--begin::Card title-->
                                            <div class="card-title m-0">
                                                <h3 class="fw-bold m-0">댓글 ( ${replyList.size()} )</h3>
                                            </div>
                                            <!--end::Card title-->
                                        </div>
                                        <!--end::Card header-->

                                        <c:if test="${not empty replyList}">
                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">
                                            <c:forEach var="reply" items="${replyList}" begin="0" end="${replyList.size()}" step="1" varStatus="status">
                                            <!--begin::Input group-->
                                            <div class="row mb-2"
                                                    <c:choose>
                                                        <c:when test="${reply.note eq '개발사'}">
                                                            style="background-color: aliceblue; padding: 15px;"
                                                        </c:when>
                                                        <c:otherwise>
                                                            style="background-color: #e8f7ee; padding: 15px;"
                                                        </c:otherwise>
                                                    </c:choose>
                                                 >
                                                <input type="hidden" name="replySeq" value="${reply.seq}">
                                                <!--begin::Label-->
                                                <label class="col-lg-2"
                                                        <c:choose>
                                                            <c:when test="${reply.note eq '개발사'}">
                                                                style="border-right: 1px solid aliceblue;"
                                                            </c:when>
                                                            <c:otherwise>
                                                                style="border-right: 1px solid #e8f7ee;"
                                                            </c:otherwise>
                                                        </c:choose>
                                                >
                                                    <c:choose>
                                                        <c:when test="${reply.note eq '개발사'}">
                                                            <div class="badge badge-primary fw-bold d-flex align-items-center justify-content-center flex-wrap fs-4 h-35px mb-1" style="line-height: inherit;">
                                                                개발사
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="badge badge-success fw-bold d-flex align-items-center justify-content-center flex-wrap fs-4 h-35px mb-1" style="line-height: inherit;">
                                                                관리자
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <span class="mt-2 text-gray-600 fs-8">${reply.initRegiDttm}</span>
                                                </label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <div class="row">
                                                        <div class="col-lg-10">
                                                            <% pageContext.setAttribute("CRLF", "\r\n"); %>
                                                            <% pageContext.setAttribute("LF", "\n"); %>
                                                            ${fn:replace(fn:replace(fn:escapeXml(reply.content), CRLF, '<br/>'), LF, '<br/>')}
                                                        </div>
                                                        <div class="col-lg-2 text-end">
                                                            <a href="javascript:void(0);" onclick="f_request_list_reply_remove('${reply.seq}', '${reply.requestSeq}')">
                                                                <i class="ki-duotone ki-trash fs-2">
                                                                    <span class="path1"></span>
                                                                    <span class="path2"></span>
                                                                    <span class="path3"></span>
                                                                    <span class="path4"></span>
                                                                    <span class="path5"></span>
                                                                </i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            </c:forEach>
                                        </div>
                                        <!--end::Card body-->
                                        </c:if>

                                    <!--begin::form-->
                                    <form id="replyForm" method="post" onsubmit="return false;">
                                        <%-- hidden SEQ --%>
                                        <input type="hidden" name="requestSeq" value="${info.seq}">
                                        <input type="hidden" name="writer" value="${sessionScope.id}">
                                        <input type="hidden" name="note" value="${sessionScope.note}">
                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2">
                                                    <c:choose>
                                                        <c:when test="${sessionScope.note eq '개발사'}">
                                                            <div class="badge badge-primary fw-bold d-flex align-items-center justify-content-center flex-wrap fs-4 h-35px mb-1" style="line-height: inherit;">
                                                                개발사
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="badge badge-success fw-bold d-flex align-items-center justify-content-center flex-wrap fs-4 h-35px mb-1" style="line-height: inherit;">
                                                                관리자
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <textarea id="replyContent" name="content" class="form-control form-control-solid-bg resize-none h-100px" placeholder="댓글 내용 작성"></textarea>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6"></label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex justify-content-end">
                                                    <button type="button" onclick="f_request_list_reply_save('${info.seq}')" class="btn btn-primary btn-active-light-primary">댓글등록</button>
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
                                </c:if>

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Actions-->
                                    <div class="card-footer d-flex justify-content-between py-6 px-9">
                                        <div>
                                            <a href="/mng/request/list.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_request_list_detail_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">내용초기화</button>
                                            <button type="button" onclick="f_request_list_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">등록</button>
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
                    <form id="modal_file_upload_form" method="post">
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
                                        <input type="file" id="attachFileInput" name="file" class="d-none upload_hidden" accept=".png, .jpg, .jpeg, .pdf, .ppt, .pptx, .xls, .xlsx, .hwp, .docx, .zip">
                                        <label class="btn btn-primary" for="attachFileInput">파일선택</label>
                                    </div>
                                    <!--end::Col-->
                                </div>
                                <!--end::Row-->
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                        <!--begin::Input group-->
                        <div class="row">
                            <!--begin::Label-->
                            <label class="col-lg-12 col-form-label fw-semibold fs-6">
                                ※ 최대 10MB까지 업로드 가능합니다. 대용량 파일의 경우 ( kyj@meetingfan.com ) 으로 보내주세요.
                            </label>
                            <!--end::Label-->
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
                            <a onclick="f_attach_file_upload('${info.seq}','modal_file_upload_form','request')" class="btn btn-primary">업로드</a>
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
    <script src="/js/mng/list.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>