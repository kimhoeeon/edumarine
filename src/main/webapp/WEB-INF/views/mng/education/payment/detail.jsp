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
                                        결제/환불 현황</h1>
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
                                        <li class="breadcrumb-item text-muted">결제/환불 현황</li>
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
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">신청자 성명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input class="form-control form-control-solid" id="memberName" name="memberName" placeholder="신청자 성명" value="${info.memberName}" <c:if test="${info ne null}">readonly</c:if>/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">신청자 연락처</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input class="form-control form-control-solid onlyTel" id="memberPhone" name="memberPhone" placeholder="신청자 연락처" value="${info.memberPhone}" <c:if test="${info ne null}">readonly</c:if>/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">교육과정명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <select id="trainName" name="trainName" class="form-select form-control-solid-bg" <c:if test="${info ne null}">disabled</c:if>>
                                                        <option value="해상엔진 테크니션 (선내기/선외기)" <c:if test="${info.trainName eq '해상엔진 테크니션 (선내기/선외기)'}">selected</c:if> >해상엔진 테크니션 (선내기/선외기)</option>
                                                        <option value="마리나선박 정비사 실무과정" <c:if test="${info.trainName eq '마리나선박 정비사 실무과정'}">selected</c:if> >마리나선박 정비사 실무과정</option>
                                                        <option value="FRP 레저보트 선체 정비 테크니션" <c:if test="${info.trainName eq 'FRP 레저보트 선체 정비 테크니션'}">selected</c:if> >FRP 레저보트 선체 정비 테크니션</option>
                                                        <option value="위탁교육" <c:if test="${info.trainName eq '위탁교육'}">selected</c:if> >위탁교육</option>
                                                        <option value="선외기 기초정비실습 과정" <c:if test="${info.trainName eq '선외기 기초정비실습 과정'}">selected</c:if> >선외기 기초정비실습 과정</option>
                                                        <option value="선내기 기초정비실습 과정" <c:if test="${info.trainName eq '선내기 기초정비실습 과정'}">selected</c:if> >선내기 기초정비실습 과정</option>
                                                        <option value="세일요트 기초정비실습 과정" <c:if test="${info.trainName eq '세일요트 기초정비실습 과정'}">selected</c:if> >세일요트 기초정비실습 과정</option>
                                                        <option value="고마력 선외기 정비 중급 테크니션" <c:if test="${info.trainName eq '고마력 선외기 정비 중급 테크니션'}">selected</c:if> >고마력 선외기 정비 중급 테크니션</option>
                                                        <option value="자가정비 심화과정 (고마력 선외기)" <c:if test="${info.trainName eq '자가정비 심화과정 (고마력 선외기)'}">selected</c:if> >자가정비 심화과정 (고마력 선외기)</option>
                                                        <option value="고마력 선외기 정비 중급 테크니션 (특별반)" <c:if test="${info.trainName eq '고마력 선외기 정비 중급 테크니션 (특별반)'}">selected</c:if> >자가정비 심화과정 (고마력 선외기)</option>
                                                        <option value="스턴드라이브 정비 전문가과정" <c:if test="${info.trainName eq '스턴드라이브 정비 전문가과정'}">selected</c:if> >스턴드라이브 정비 전문가과정</option>
                                                        <option value="스턴드라이브 정비 전문가과정 (특별반)" <c:if test="${info.trainName eq '스턴드라이브 정비 전문가과정 (특별반)'}">selected</c:if> >스턴드라이브 정비 전문가과정 (특별반)</option>
                                                        <option value="발전기 정비 교육" <c:if test="${info.trainName eq '발전기 정비 교육'}">selected</c:if> >발전기 정비 교육</option>
                                                        <option value="선외기/선내기 직무역량 강화과정" <c:if test="${info.trainName eq '선외기/선내기 직무역량 강화과정'}">selected</c:if> >선외기/선내기 직무역량 강화과정</option>
                                                    </select>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">교육비</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <input type="text" id="paySum" name="paySum" class="form-control form-control-lg form-control-solid-bg onlyNum" placeholder="교육비" value="${info.paySum}" <c:if test="${info ne null}">readonly</c:if>/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6 required">결제상태</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <select id="payStatus" name="payStatus" class="form-select form-control-solid-bg">
                                                        <option value="결제대기" <c:if test="${info.payStatus eq '결제대기'}">selected</c:if> >결제대기</option>
                                                        <option value="결제완료" <c:if test="${info.payStatus eq '결제완료'}">selected</c:if> >결제완료</option>
                                                        <option value="취소신청" <c:if test="${info.payStatus eq '취소신청'}">selected</c:if> >취소신청</option>
                                                        <option value="취소완료" <c:if test="${info.payStatus eq '취소완료'}">selected</c:if> >취소완료</option>
                                                    </select>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-4 col-form-label fw-semibold fs-6">환불사유</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-8">
                                                    <textarea id="refundReason" name="refundReason" class="form-control form-control-solid-bg resize-none h-200px" placeholder="환불사유">${info.refundReason}</textarea>
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
                                            <a href="/mng/education/payment.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <div>
                                            <button type="button" onclick="f_education_payment_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_education_payment_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
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
    <script src="/js/mng/payment.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
<%@ include file="/WEB-INF/views/mng/inc/_bottom.jspf" %>