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

                                            <div class="row mb-6">
                                                <label class="col-lg-2 col-form-label required fw-semibold fs-6">신청서 양식</label>
                                                <div class="col-lg-10">
                                                    <c:choose>
                                                        <%--신규 통합 시스템 (UNIFIED)--%>
                                                        <c:when test="${info.applicationSystemType == 'UNIFIED'}">
                                                            <select class="form-select form-select-solid" name="formType" data-control="select2" data-hide-search="true" data-placeholder="신청서 양식을 선택하세요">
                                                                <option></option>
                                                                <option value="BASIC_FORM" <c:if test="${info.formType == 'BASIC_FORM'}">selected</c:if>>기본형 (작업복사이즈, 참여경로)</option>
                                                                <option value="PRO_FORM" <c:if test="${info.formType == 'PRO_FORM'}">selected</c:if>>기본형2 (작업복사이즈, 참여경로, 추천인)</option>
                                                                <option value="BASE_FORM" <c:if test="${info.formType == 'BASE_FORM'}">selected</c:if>>기본형3 (참여경로)</option>
                                                            </select>
                                                            <input type="hidden" name="applicationSystemType" value="UNIFIED"/>
                                                        </c:when>

                                                        <%--기존 시스템 (LEGACY) - 기본값--%>
                                                        <c:otherwise>
                                                            <input type="text" class="form-control form-control-solid" value="기존 교육 양식 (LEGACY)" readonly />
                                                            <input type="hidden" name="applicationSystemType" value="LEGACY"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>

                                            <c:if test="${info.applicationSystemType == 'UNIFIED'}">
                                                <div class="row mb-6">
                                                    <label class="col-lg-2 col-form-label fw-semibold fs-6">썸네일 이미지</label>
                                                    <div class="col-lg-10">
                                                        <div class="d-flex align-items-center">
                                                            <div class="me-5">
                                                                <div class="symbol symbol-100px symbol-2by3">
                                                                    <c:choose>
                                                                        <c:when test="${not empty info.thumbFileId}">
                                                                            <img id="thumbPreview" src="/file/download.do?fileName=${info.thumbFileName}&path=${info.thumbFilePath}"
                                                                                 alt="썸네일" style="width: 150px; height: 100px; object-fit: cover; border: 1px solid #ddd; border-radius: 4px;">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <img id="thumbPreview" src="/assets/media/svg/files/blank-image.svg"
                                                                                 alt="없음" style="width: 150px; height: 100px; object-fit: cover; border: 1px solid #ddd; border-radius: 4px; background: #f5f8fa;">
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>

                                                            <div class="flex-grow-1">
                                                                <div class="input-group">
                                                                    <input type="file" class="form-control" id="thumbFileObj" accept="image/*" />
                                                                    <input type="hidden" id="thumbFileId" name="thumbFileId" value="${info.thumbFileId}"/>
                                                                </div>
                                                                <div class="form-text text-muted mt-2">
                                                                    ※ 권장 사이즈: <strong>1920 x 1080px</strong> (비율 16:9) / jpg, png 파일
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">교육과정명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">

                                                    <div class="d-flex align-items-center">
                                                        <select id="gbn_select" class="form-select form-select-solid me-2">
                                                            <option selected>- 교육과정명 선택 -</option>
                                                            <c:choose>
                                                                <%--신규 통합 시스템 (UNIFIED)--%>
                                                                <c:when test="${info.applicationSystemType == 'UNIFIED'}">
                                                                    <option value="direct">직접입력</option>
                                                                </c:when>

                                                                <%--기존 시스템 (LEGACY) - 기본값--%>
                                                                <c:otherwise>
                                                                    <option value="상시신청" <c:if test="${info.gbn eq '상시신청'}">selected</c:if> >상시신청</option>
                                                                    <option value="FRP 레저보트 선체 정비 테크니션" <c:if test="${info.gbn eq 'FRP 레저보트 선체 정비 테크니션'}">selected</c:if> >FRP 레저보트 선체 정비 테크니션</option>
                                                                    <option value="선외기 기초정비실습 과정" <c:if test="${info.gbn eq '선외기 기초정비실습 과정'}">selected</c:if> >선외기 기초정비실습 과정</option>
                                                                    <option value="선내기 기초정비실습 과정" <c:if test="${info.gbn eq '선내기 기초정비실습 과정'}">selected</c:if> >선내기 기초정비실습 과정</option>
                                                                    <option value="세일요트 기초정비실습 과정" <c:if test="${info.gbn eq '세일요트 기초정비실습 과정'}">selected</c:if> >세일요트 기초정비실습 과정</option>
                                                                    <option value="마리나선박 선외기 정비사 실무과정" <c:if test="${info.gbn eq '마리나선박 선외기 정비사 실무과정'}">selected</c:if> >마리나선박 선외기 정비사 실무과정</option>
                                                                    <option value="마리나선박 선내기 정비사 실무과정" <c:if test="${info.gbn eq '마리나선박 선내기 정비사 실무과정'}">selected</c:if> >마리나선박 선내기 정비사 실무과정</option>
                                                                    <option value="고마력 선외기 정비 중급 테크니션" <c:if test="${info.gbn eq '고마력 선외기 정비 중급 테크니션'}">selected</c:if> >고마력 선외기 정비 중급 테크니션</option>
                                                                    <option value="자가정비 심화과정 (고마력 선외기)" <c:if test="${info.gbn eq '자가정비 심화과정 (고마력 선외기)'}">selected</c:if> >자가정비 심화과정 (고마력 선외기)</option>
                                                                    <option value="고마력 선외기 정비 중급 테크니션 (특별반)" <c:if test="${info.gbn eq '고마력 선외기 정비 중급 테크니션 (특별반)'}">selected</c:if> >고마력 선외기 정비 중급 테크니션 (특별반)</option>
                                                                    <option value="스턴드라이브 정비 전문가과정" <c:if test="${info.gbn eq '스턴드라이브 정비 전문가과정'}">selected</c:if> >스턴드라이브 정비 전문가과정</option>
                                                                    <option value="스턴드라이브 정비 전문가과정 (특별반)" <c:if test="${info.gbn eq '스턴드라이브 정비 전문가과정 (특별반)'}">selected</c:if> >스턴드라이브 정비 전문가과정 (특별반)</option>
                                                                    <option value="기초정비교육" <c:if test="${info.gbn eq '기초정비교육'}">selected</c:if> >기초정비교육</option>
                                                                    <option value="응급조치교육" <c:if test="${info.gbn eq '응급조치교육'}">selected</c:if> >응급조치교육</option>
                                                                    <option value="발전기 정비 교육" <c:if test="${info.gbn eq '발전기 정비 교육'}">selected</c:if> >발전기 정비 교육</option>
                                                                    <option value="선외기/선내기 직무역량 강화과정" <c:if test="${info.gbn eq '선외기/선내기 직무역량 강화과정'}">selected</c:if> >선외기/선내기 직무역량 강화과정</option>
                                                                    <option value="선내기 팸투어" <c:if test="${info.gbn eq '선내기 팸투어'}">selected</c:if> >선내기 팸투어</option>
                                                                    <option value="선외기 팸투어" <c:if test="${info.gbn eq '선외기 팸투어'}">selected</c:if> >선외기 팸투어</option>
                                                                    <option value="레저선박 해양전자장비 교육" <c:if test="${info.gbn eq '레저선박 해양전자장비 교육'}">selected</c:if> >레저선박 해양전자장비 교육</option>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </select>
                                                        <input type="text" name="gbn" id="gbn" class="form-control form-control-solid"
                                                               value="${info.gbn}" readonly placeholder="직접입력 선택 시 작성 가능"/>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6 d-none">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">세부교육명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <div class="d-flex align-items-center">
                                                        <select id="gbn_depth_select" class="form-select form-select-solid me-2" <c:if test="${info.gbnDepth ne null}">disabled</c:if> >
                                                            <option value="" selected>- 세부교육명 선택 -</option>
                                                            <option value="선외기" <c:if test="${info.gbnDepth eq '선외기'}">selected</c:if> >선외기</option>
                                                            <option value="선내기" <c:if test="${info.gbnDepth eq '선내기'}">selected</c:if> >선내기</option>
                                                            <option value="세일요트" <c:if test="${info.gbnDepth eq '세일요트'}">selected</c:if> >세일요트</option>
                                                        </select>
                                                        <input type="text" id="gbnDepth" name="gbnDepth" value="${info.gbnDepth}" class="form-control form-control-solid" placeholder="세부교육명" readonly/>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">차시</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid onlyNum" id="nextTime" name="nextTime" maxlength="2" placeholder="차시" value="${info.nextTime}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">접수기간 (시작)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="applyStartDttm" name="applyStartDttm" placeholder="접수시작일" value="${info.applyStartDttm}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">접수기간 (종료)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="applyEndDttm" name="applyEndDttm" placeholder="접수종료일" value="${info.applyEndDttm}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">교육일정 (시작)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="trainStartDttm" name="trainStartDttm" placeholder="교육시작일" value="${info.trainStartDttm}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">교육일정 (종료)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input class="form-control form-control-solid" id="trainEndDttm" name="trainEndDttm" placeholder="교육종료일" value="${info.trainEndDttm}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">교육비</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="paySum" name="paySum" class="form-control form-control-lg form-control-solid-bg onlyNum" placeholder="교육비" value="${info.paySum}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">총 교육인원</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="trainCnt" name="trainCnt" class="form-control form-control-lg form-control-solid-bg onlyNum" placeholder="교육인원" value="${info.trainCnt}"/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">교육신청인원<br>(최초 등록시 '0', 수정불가항목)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="trainApplyCnt" name="trainApplyCnt" class="form-control form-control-lg form-control-solid-bg onlyNum" placeholder="교육신청인원"
                                                           value="${info.trainApplyCnt eq null ? 0 : info.trainApplyCnt}" disabled/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">비고</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <textarea id="trainNote" name="trainNote" class="form-control form-control-solid-bg resize-none h-200px" placeholder="비고">${info.trainNote}</textarea>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">홈페이지 노출 여부</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="exposureYn" value="Y" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.exposureYn eq 'Y'}">checked</c:if>/> 노출
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="exposureYn" value="N" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.exposureYn eq 'N' or info.exposureYn eq null}">checked</c:if>/> 미노출
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">연간 일정표 등록 여부</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="scheduleExposureYn" value="Y" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.scheduleExposureYn eq 'Y'}">checked</c:if>/> 등록
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="scheduleExposureYn" value="N" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.scheduleExposureYn eq 'N' or info.scheduleExposureYn eq null}">checked</c:if>/> 미등록
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">교육 상태</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex align-items-center">
                                                    <input type="hidden" id="preClosingYn" value="${info.closingYn}">
                                                    <label class="me-5">
                                                        <input type="radio" name="closingYn" value="N" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.closingYn eq 'N' or info.scheduleExposureYn eq null}">checked</c:if>/> 신청오픈
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="closingYn" value="Y" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.closingYn eq 'Y'}">checked</c:if>/> 조기마감
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
                                            <a href="/mng/education/train.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_education_train_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_education_train_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
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
    <script src="/js/smsNoti.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/train.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->
    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>