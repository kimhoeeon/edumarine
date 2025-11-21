<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-language" content="ko">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no" />
    <meta name="copyright" content="경기테크노파크">
    <meta name="robots" content="all">
    <meta property="og:locale" content="ko_KR">
    <meta itemprop="inLanguage" content="ko-kr">
    <meta name="resource-type" content="website">
    <meta property="og:type" content="website">

    <meta property="og:site_name" content="경기해양레저인력양성센터">
    <title>경기해양레저 인력양성센터</title>

    <meta name="title" content="경기해양레저인력양성센터">
    <meta property="og:title" content="경기해양레저인력양성센터">
    <meta name="twitter:title" content="경기해양레저인력양성센터">
    <meta name="twitter:card" content="summary">
    <meta name="twitter:url" content="https://edumarine.org/main.do">
    <meta itemprop="name" content="경기해양레저인력양성센터">
    <meta property="nate:title" content="경기해양레저인력양성센터">
    <meta property="nate:url" content="https://edumarine.org/main.do">

    <meta property="og:url" content="https://edumarine.org/main.do">
    <meta itemprop="url" content="https://edumarine.org/main.do">
    <link rel="canonical" id="canonical" href="https://edumarine.org/main.do">

    <meta name="description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta name="twitter:description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta property="og:description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta itemprop="description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">
    <meta property="nate:description" content="경기해양레저인력양성센터는 경기도가 ‘16년도에 전국 최초로 개설한 해양레저 테크니션 전문교육기관입니다.">

    <meta property="og:keywords" content="경기해양레저인력양성센터, EDU marine, 에듀마린, 해상엔진, 해상엔진 교육, 선박엔진, 선박엔진 교육, 선외기, 선외기 교육, 선외기 정비 교육, 선내기, 선내기 교육, 선외기 정비 교육, 선체, 선체 교육, 선체 정비 교육, 해양레저, 해양레저 교육, 요트 교육, 요트정비 교육, 엔진정비 교육  편집 지켜보기">
    <meta name="keywords" content="경기해양레저인력양성센터, EDU marine, 에듀마린, 해상엔진, 해상엔진 교육, 선박엔진, 선박엔진 교육, 선외기, 선외기 교육, 선외기 정비 교육, 선내기, 선내기 교육, 선외기 정비 교육, 선체, 선체 교육, 선체 정비 교육, 해양레저, 해양레저 교육, 요트 교육, 요트정비 교육, 엔진정비 교육  편집 지켜보기">
    <meta property="twitter:keywords" content="경기해양레저인력양성센터, EDU marine, 에듀마린, 해상엔진, 해상엔진 교육, 선박엔진, 선박엔진 교육, 선외기, 선외기 교육, 선외기 정비 교육, 선내기, 선내기 교육, 선외기 정비 교육, 선체, 선체 교육, 선체 정비 교육, 해양레저, 해양레저 교육, 요트 교육, 요트정비 교육, 엔진정비 교육  편집 지켜보기">

    <meta name="image" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta name="twitter:image " content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta property="og:image" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta itemprop="image" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <meta itemprop="thumbnailUrl" content="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">
    <link rel="image_src" link="https://cdn2.micehub.com/home/2017/edua/Files/edua_20210604_122121.jpg">

    <%-- favicon --%>
    <link rel="apple-touch-icon" sizes="57x57" href="/img/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="/img/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/img/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/img/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/img/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/img/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/img/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/img/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="/img/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/img/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/img/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/img/favicon/favicon-16x16.png">
    <link rel="manifest" href="/img/favicon/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <%-- favicon --%>

    <!--begin::Fonts(mandatory for all pages)-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700"/>
    <!--end::Fonts-->
    <!--begin::Vendor Stylesheets(used for this page only)-->

    <link href="/assets/plugins/custom/datatables/datatables.bundle.css" rel="stylesheet" type="text/css"/>
    <!--end::Vendor Stylesheets-->
    <!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
    <link href="/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/style.bundle.css" rel="stylesheet" type="text/css"/>
    <!--end::Global Stylesheets Bundle-->

    <!--begin::custom Mng css-->
    <link href="/css/font.css" rel="stylesheet">
    <link href="/css/mngStyle.css" rel="stylesheet" type="text/css"/>
    <!--end::custom Mng css-->
</head>
<!--end::Head-->
<!--begin::Body-->
<body id="kt_app_body" data-kt-app-layout="dark-sidebar" data-kt-app-header-fixed="true"
      data-kt-app-sidebar-enabled="true" data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
      data-kt-app-sidebar-push-header="true" data-kt-app-sidebar-push-toolbar="true"
      data-kt-app-sidebar-push-footer="true" data-kt-app-toolbar-enabled="true" class="app-default">
<!--begin::Theme mode setup on page load-->
<script>var defaultThemeMode = "light";
var themeMode;
if (document.documentElement) {
    if (document.documentElement.hasAttribute("data-bs-theme-mode")) {
        themeMode = document.documentElement.getAttribute("data-bs-theme-mode");
    } else {
        if (localStorage.getItem("data-bs-theme") !== null) {
            themeMode = localStorage.getItem("data-bs-theme");
        } else {
            themeMode = defaultThemeMode;
        }
    }
    if (themeMode === "system") {
        themeMode = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light";
    }
    document.documentElement.setAttribute("data-bs-theme", themeMode);
}</script>
<!--end::Theme mode setup on page load-->

<!--begin::login check-->
<c:if test="${status ne 'logon'}">
    <script>
        alert("로그인해 주세요.");
        location.href = '/mng/index.do';
    </script>
</c:if>

<c:if test="${status eq 'logon'}">

    <!--begin::App-->
    <div class="d-flex flex-column flex-root app-root" id="kt_app_root">
        <!--begin::Page-->
        <div class="app-page flex-column flex-column-fluid" id="kt_app_page">
            <!--begin::Header-->
            <div id="kt_app_header" class="app-header">
                <!--begin::Header container-->
                <div class="app-container container-fluid d-flex align-items-stretch justify-content-between"
                     id="kt_app_header_container">
                    <!--begin::Sidebar mobile toggle-->
                    <div class="d-flex align-items-center d-lg-none ms-n3 me-1 me-md-2" title="Show sidebar menu">
                        <div class="btn btn-icon btn-active-color-primary w-35px h-35px" id="kt_app_sidebar_mobile_toggle">
                            <i class="ki-duotone ki-abstract-14 fs-2 fs-md-1">
                                <span class="path1"></span>
                                <span class="path2"></span>
                            </i>
                        </div>
                    </div>
                    <!--end::Sidebar mobile toggle-->
                    <!--begin::Mobile logo-->
                    <div class="d-flex align-items-center flex-grow-1 flex-lg-grow-0">
                        <a href="/mng/main.do" class="d-lg-none">
                            <img alt="Logo" src="/img/mng_main_logo.png<%--/assets/media/logos/default-small.svg--%>" class="h-30px"/>
                        </a>
                    </div>
                    <!--end::Mobile logo-->
                    <!--begin::Header wrapper-->
                    <div class="d-flex align-items-stretch justify-content-between flex-lg-grow-1"
                         id="kt_app_header_wrapper">
                        <!--begin::Menu wrapper-->
                        <div class="app-header-menu app-header-mobile-drawer align-items-stretch" data-kt-drawer="true"
                             data-kt-drawer-name="app-header-menu" data-kt-drawer-activate="{default: true, lg: false}"
                             data-kt-drawer-overlay="true" data-kt-drawer-width="250px" data-kt-drawer-direction="end"
                             data-kt-drawer-toggle="#kt_app_header_menu_toggle" data-kt-swapper="true"
                             data-kt-swapper-mode="{default: 'append', lg: 'prepend'}"
                             data-kt-swapper-parent="{default: '#kt_app_body', lg: '#kt_app_header_wrapper'}">
                            <!--begin::Menu-->
                            <div class="menu menu-rounded menu-column menu-lg-row my-5 my-lg-0 align-items-stretch fw-semibold px-2 px-lg-0"
                                 id="kt_app_header_menu" data-kt-menu="true">

                                <!--begin:Menu item-->
                                <my:headerMenu items="${menuItems}" />
                                <!--end:Menu item-->

                            </div>
                            <!--end::Menu-->
                        </div>
                        <!--end::Menu wrapper-->
                        <!--begin::Navbar-->
                        <div class="app-navbar flex-shrink-0">

                            <!--begin::admin mng menu-->
                            <c:if test="${sessionScope.gbn eq '슈퍼'}">
                                <div class="app-navbar-item align-items-stretch ms-md-3">
                                    <!--begin::admin-->
                                    <div class="d-flex align-items-stretch">
                                        <!--begin::admin mng-->
                                        <div class="d-flex align-items-center" id="kt_header_admin_menu">
                                            <a href="/mng/adminMng/admin.do" class="btn btn-dark btn-active-light-dark">관리자 관리</a>
                                        </div>
                                        <!--end::admin mng-->
                                    </div>
                                    <!--end::admin-->
                                </div>
                            </c:if>
                            <!--end::admin mng menu-->

                            <!--begin::User menu-->
                            <div class="app-navbar-item ms-1 ms-md-3" id="kt_header_user_menu_toggle">
                                <!--begin::Menu wrapper-->
                                <div class="cursor-pointer symbol symbol-30px symbol-md-40px"
                                     data-kt-menu-trigger="{default: 'click', lg: 'hover'}" data-kt-menu-attach="parent"
                                     data-kt-menu-placement="bottom-end">
                                    <i class="ki-solid ki-user-square fs-2qx"></i>
                                </div>
                                <!--begin::User account menu-->
                                <div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg menu-state-color fw-semibold py-4 fs-6 w-275px"
                                     data-kt-menu="true">
                                    <!--begin::Menu item-->
                                    <div class="menu-item px-3">
                                        <div class="menu-content d-flex align-items-center px-3">
                                            <!--begin::Avatar-->
                                            <div class="symbol symbol-50px me-5">
                                                <i class="ki-solid ki-user-square fs-3x"></i>
                                            </div>
                                            <!--end::Avatar-->
                                            <!--begin::Username-->
                                            <div class="d-flex flex-column">
                                                <div class="fw-bold d-flex align-items-center fs-5">${sessionScope.id}
                                                    <span class="badge badge-light-success fw-bold fs-8 px-2 py-1 ms-2">Admin</span>
                                                </div>
                                                <a href="/mng/main.do" class="fw-semibold text-muted text-hover-primary fs-7">${sessionScope.gbn} 관리자</a>
                                            </div>
                                            <!--end::Username-->
                                        </div>
                                    </div>
                                    <!--end::Menu item-->
                                    <!--begin::Menu separator-->
                                    <div class="separator my-2"></div>
                                    <!--end::Menu separator-->
                                    <!--begin::Menu item-->
                                    <div class="menu-item px-5">
                                        <a href="javascript:logout();"
                                           class="menu-link px-5">Sign Out</a>
                                    </div>
                                    <!--end::Menu item-->
                                </div>
                                <!--end::User account menu-->
                                <!--end::Menu wrapper-->
                            </div>
                            <!--end::User menu-->
                            <!--begin::Header menu toggle-->
                            <div class="app-navbar-item d-lg-none ms-2 me-n2" title="Show header menu">
                                <div class="btn btn-flex btn-icon btn-active-color-primary w-30px h-30px"
                                     id="kt_app_header_menu_toggle">
                                    <i class="ki-duotone ki-element-4 fs-1">
                                        <span class="path1"></span>
                                        <span class="path2"></span>
                                    </i>
                                </div>
                            </div>
                            <!--end::Header menu toggle-->
                        </div>
                        <!--end::Navbar-->
                    </div>
                    <!--end::Header wrapper-->
                </div>
                <!--end::Header container-->
            </div>
            <!--end::Header-->
            <!--begin::Wrapper-->
            <div class="app-wrapper flex-column flex-row-fluid" id="kt_app_wrapper">
                <!--begin::Sidebar-->
                <div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true"
                     data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}"
                     data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start"
                     data-kt-drawer-toggle="#kt_app_sidebar_mobile_toggle">
                    <!--begin::Logo-->
                    <div class="app-sidebar-logo pe-4 justify-content-center h-90px" id="kt_app_sidebar_logo">
                        <!--begin::Logo image-->
                        <a href="/mng/main.do">
                            <img alt="Logo" src="/img/mng_logo.png<%--/static/assets/media/logos/default-dark.svg--%>"
                                 class="h-80px app-sidebar-logo-default"/>
                            <img alt="Logo" src="/img/mng_logo.png<%--assets/media/logos/default-small.svg--%>"
                                 class="h-80px app-sidebar-logo-minimize"/>
                        </a>
                        <!--end::Logo image-->
                        <!--begin::Sidebar toggle-->
                        <div id="kt_app_sidebar_toggle"
                             class="app-sidebar-toggle btn btn-icon btn-shadow btn-sm btn-color-muted btn-active-color-primary body-bg h-30px w-30px position-absolute top-50 start-100 translate-middle rotate"
                             data-kt-toggle="true" data-kt-toggle-state="active" data-kt-toggle-target="body"
                             data-kt-toggle-name="app-sidebar-minimize">
                            <i class="ki-duotone ki-double-left fs-2 rotate-180">
                                <span class="path1"></span>
                                <span class="path2"></span>
                            </i>
                        </div>
                        <!--end::Sidebar toggle-->
                    </div>
                    <!--end::Logo-->
                    <!--begin::sidebar menu-->
                    <div class="app-sidebar-menu overflow-hidden flex-column-fluid">
                        <!--begin::Menu wrapper-->
                        <div id="kt_app_sidebar_menu_wrapper" class="app-sidebar-wrapper hover-scroll-overlay-y my-5"
                             data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto"
                             data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer"
                             data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px"
                             data-kt-scroll-save-state="true">

                            <!--begin:Menu item-->
                            <my:sidebarMenu items="${menuItems}" />
                            <!--end:Menu item-->

                        </div>
                        <!--end::Menu wrapper-->
                    </div>
                    <!--end::sidebar menu-->
                    <!--begin::Footer-->
                    <div class="app-sidebar-footer flex-column-auto pt-2 pb-6 px-6" id="kt_app_sidebar_footer">
                        <a href="javascript:f_google_analytics_page();"
                           class="btn btn-flex flex-center btn-primary overflow-hidden text-nowrap px-0 h-40px w-100"
                           data-bs-toggle="tooltip" data-bs-trigger="hover" data-bs-dismiss-="click"
                           title="방문자 데이터 보기"<%-- target="_blank"--%>>
                            <span class="btn-label">방문자 데이터 보기</span>
                        </a>
                    </div>
                    <!--end::Footer-->
                </div>
                <!--end::Sidebar-->
                <!--begin::Main-->
                <div class="app-main flex-column flex-row-fluid" id="kt_app_main">
                    <!--begin::Content wrapper-->
                    <div class="d-flex flex-column flex-column-fluid">
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
                                        <input type="hidden" id="applicationSystemType" name="applicationSystemType" value="${info.applicationSystemType}" />
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
                                                <div class="col-lg-10 fv-row">
                                                    <c:choose>
                                                        <%--신규 통합 시스템 (UNIFIED)--%>
                                                        <c:when test="${info.applicationSystemType == 'UNIFIED'}">
                                                            <select class="form-select form-select-solid" name="formType" data-control="select2" data-hide-search="true" data-placeholder="신청서 양식을 선택하세요">
                                                                <option></option>
                                                                <option value="BASIC_FORM" <c:if test="${info.formType == 'BASIC_FORM'}">selected</c:if>>기본형 (의류사이즈, 참여경로)</option>
                                                                <option value="PRO_FORM" <c:if test="${info.formType == 'PRO_FORM'}">selected</c:if>>전문가형 (기본형 + 학력/경력/자격증)</option>
                                                                <option value="FAMTOUR_FORM" <c:if test="${info.formType == 'FAMTOUR_FORM'}">selected</c:if>>팸투어형 (신청일 선택)</option>
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
                                                                                 alt="없음" style="width: 150px; height: 100px; object-fit: contain; border: 1px solid #ddd; border-radius: 4px; background: #f5f8fa;">
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
                                                                    ※ 권장 사이즈: <strong>1920 x 1080px</strong> (비율 3:2) / jpg, png 파일
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
                                                    <div class="input-group">
                                                        <input type="text" id="gbn" name="gbn" value="${info.gbn}" class="form-control form-control-solid-bg" placeholder="교육과정명" readonly/>
                                                        <select id="gbn_select" class="form-select form-control-solid-bg ms-4" <c:if test="${info.gbn ne null}">disabled</c:if> >
                                                            <option selected>- 교육과정명 선택 -</option>
                                                            <option value="상시신청" <c:if test="${info.gbn eq '상시신청'}">selected</c:if> >상시신청</option>
                                                            <%--<option value="해상엔진 테크니션 (선내기/선외기)" <c:if test="${info.gbn eq '해상엔진 테크니션 (선내기/선외기)'}">selected</c:if> >해상엔진 테크니션 (선내기/선외기)</option>--%>
                                                            <option value="FRP 레저보트 선체 정비 테크니션" <c:if test="${info.gbn eq 'FRP 레저보트 선체 정비 테크니션'}">selected</c:if> >FRP 레저보트 선체 정비 테크니션</option>
                                                            <option value="해상엔진 자가정비 (선외기)" <c:if test="${info.gbn eq '해상엔진 자가정비 (선외기)'}">selected</c:if> >해상엔진 자가정비 (선외기)</option>
                                                            <option value="해상엔진 자가정비 (선내기)" <c:if test="${info.gbn eq '해상엔진 자가정비 (선내기)'}">selected</c:if> >해상엔진 자가정비 (선내기)</option>
                                                            <option value="해상엔진 자가정비 (세일요트)" <c:if test="${info.gbn eq '해상엔진 자가정비 (세일요트)'}">selected</c:if> >해상엔진 자가정비 (세일요트)</option>
                                                            <option value="마리나 선박 선외기 정비사 실무과정" <c:if test="${info.gbn eq '마리나 선박 선외기 정비사 실무과정'}">selected</c:if> >마리나 선박 선외기 정비사 실무과정</option>
                                                            <option value="마리나 선박 선내기 정비사 실무과정" <c:if test="${info.gbn eq '마리나 선박 선내기 정비사 실무과정'}">selected</c:if> >마리나 선박 선내기 정비사 실무과정</option>
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
                                                        </select>
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
                                                    <div class="input-group">
                                                        <input type="text" id="gbnDepth" name="gbnDepth" value="${info.gbnDepth}" class="form-control form-control-solid-bg" placeholder="세부교육명" readonly/>
                                                        <select id="gbn_depth_select" class="form-select form-control-solid-bg ms-4" <c:if test="${info.gbnDepth ne null}">disabled</c:if> >
                                                            <option value="" selected>- 세부교육명 선택 -</option>
                                                            <option value="선외기" <c:if test="${info.gbnDepth eq '선외기'}">selected</c:if> >선외기</option>
                                                            <option value="선내기" <c:if test="${info.gbnDepth eq '선내기'}">selected</c:if> >선내기</option>
                                                            <option value="세일요트" <c:if test="${info.gbnDepth eq '세일요트'}">selected</c:if> >세일요트</option>
                                                        </select>
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
                                                    <input class="form-control form-control-solid onlyNum" id="nextTime" name="nextTime" maxlength="2" placeholder="차시" value="${info.nextTime}" <c:if test="${info ne null}">readonly</c:if>/>
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

                    <!--begin::Footer-->
                    <div id="kt_app_footer" class="app-footer">
                        <!--begin::Footer container-->
                        <div class="app-container container-fluid d-flex flex-column flex-md-row flex-center flex-md-stack py-3">
                            <!--begin::Copyright-->
                            <div class="text-dark order-2 order-md-1"></div>
                            <!--end::Copyright-->
                            <!--begin::Menu-->
                            <ul class="menu menu-gray-600 menu-hover-primary fw-semibold order-1"></ul>
                            <!--end::Menu-->
                        </div>
                        <!--end::Footer container-->
                    </div>
                    <!--end::Footer-->
                </div>
                <!--end:::Main-->
            </div>
            <!--end::Wrapper-->
        </div>
        <!--end::Page-->
    </div>
    <!--end::App-->

    <!--begin::Scrolltop-->
    <div id="kt_scrolltop" class="scrolltop" data-kt-scrolltop="true">
        <i class="ki-duotone ki-arrow-up">
            <span class="path1"></span>
            <span class="path2"></span>
        </i>
    </div>
    <!--end::Scrolltop-->

    <!--begin::Javascript-->

    <script>var hostUrl = "/assets/";</script>
    <!--begin::Global Javascript Bundle(mandatory for all pages)-->
    <script src="/assets/plugins/global/plugins.bundle.js"></script>
    <script src="/assets/js/scripts.bundle.js"></script>
    <!--end::Global Javascript Bundle-->
    <!--begin::Vendors Javascript(used for this page only)-->
    <script src="/assets/plugins/custom/datatables/datatables.bundle.js"></script>
    <!--end::Vendors Javascript-->
    <!--begin::Custom Javascript(used for this page only)-->
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
    <script>
        $(function(){
            $('#gbn_select').trigger('change');
        })
    </script>

    <!--end::login check-->
</c:if>
</body>
<!--end::Body-->
</html>