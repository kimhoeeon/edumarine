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
                                        자가정비 (선외기)</h1>
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
                                        <li class="breadcrumb-item text-muted">자가정비 (선외기)</li>
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
                                                <h3 class="fw-bold m-0">신청 정보</h3>
                                            </div>
                                            <!--end::Card title-->
                                        </div>
                                        <!--end::Card header-->
                                        <!--begin::Card body-->
                                        <div class="card-body border-top p-9">

                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">교육차시</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="nextTime" name="nextTime" class="form-control form-control-lg form-control-solid-bg" placeholder="교육차시" value="${info.nextTime} 차시" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">이름</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <!--begin::Row-->
                                                    <div class="row">
                                                        <!--begin::Col-->
                                                        <div class="col-lg-6">
                                                            <input type="text" id="nameKo" name="nameKo" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="이름(국문)" value="${memberInfo.name}" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                        <!--begin::Col-->
                                                        <div class="col-lg-6">
                                                            <input type="text" id="nameEn" name="nameEn" class="form-control form-control-lg form-control-solid-bg" placeholder="이름(영문)" value="${memberInfo.nameEn}" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                    </div>
                                                    <!--end::Row-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">연락처</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="phone" name="phone" class="form-control form-control-lg form-control-solid-bg onlyTel" placeholder="연락처" value="${memberInfo.phone}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">이메일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <div class="input-group">
                                                        <c:set var="email1" value="${fn:split(memberInfo.email,'@')[0]}" />
                                                        <c:set var="email2" value="${fn:split(memberInfo.email,'@')[1]}" />
                                                        <input type="text" id="email" name="email" value="${email1}" class="form-control form-control-solid-bg" placeholder="이메일" readonly/>
                                                        <span class="input-group-text">@</span>
                                                        <input type="text" id="domain" name="domain" value="${email2}" class="form-control form-control-solid-bg" placeholder="도메인" readonly/>
                                                        <select id="email_select" class="form-select form-control-solid-bg ms-4" aria-label="Select Email" disabled>
                                                            <option selected>직접입력</option>
                                                            <option value="daum.net" <c:if test="${email2 eq 'daum.net'}">selected</c:if> >daum.net</option>
                                                            <option value="nate.com" <c:if test="${email2 eq 'nate.com'}">selected</c:if> >nate.com</option>
                                                            <option value="hanmail.net" <c:if test="${email2 eq 'hanmail.net'}">selected</c:if> >hanmail.net</option>
                                                            <option value="naver.com" <c:if test="${email2 eq 'naver.com'}">selected</c:if> >naver.com</option>
                                                            <option value="gmail.com" <c:if test="${email2 eq 'gmail.com'}">selected</c:if> >gmail.com</option>
                                                            <option value="hotmail.com" <c:if test="${email2 eq 'hotmail.com'}">selected</c:if> >hotmail.com</option>
                                                            <option value="yahoo.co.kr" <c:if test="${email2 eq 'yahoo.co.kr'}">selected</c:if> >yahoo.co.kr</option>
                                                            <option value="empal.com" <c:if test="${email2 eq 'empal.com'}">selected</c:if> >empal.com</option>
                                                            <option value="korea.com" <c:if test="${email2 eq 'korea.com'}">selected</c:if> >korea.com</option>
                                                            <option value="hanmir.com" <c:if test="${email2 eq 'hanmir.com'}">selected</c:if> >hanmir.com</option>
                                                            <option value="dreamwiz.com" <c:if test="${email2 eq 'dreamwiz.com'}">selected</c:if> >dreamwiz.com</option>
                                                            <option value="orgio.net" <c:if test="${email2 eq 'orgio.net'}">selected</c:if> >orgio.net</option>
                                                            <option value="korea.com" <c:if test="${email2 eq 'korea.com'}">selected</c:if> >korea.com</option>
                                                            <option value="hitel.net" <c:if test="${email2 eq 'hitel.net'}">selected</c:if> >hitel.net</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">생년월일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <div class="input-group">
                                                        <input type="text" id="birthYear" name="birthYear" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="출생연도" value="${memberInfo.birthYear} 년" readonly/>
                                                        <input type="text" id="birthMonth" name="birthMonth" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="출생월" value="${memberInfo.birthMonth} 월" readonly/>
                                                        <input type="text" id="birthDay" name="birthDay" class="form-control form-control-lg form-control-solid-bg" placeholder="출생일" value="${memberInfo.birthDay} 일" readonly/>
                                                    </div>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">주소</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <!--begin::Row-->
                                                    <div class="row">
                                                        <!--begin::Col-->
                                                        <div class="col-lg-12">
                                                            <input type="text" id="address" name="address" class="form-control form-control-lg form-control-solid-bg" placeholder="주소" value="${memberInfo.address}" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                        <%--<!--begin::Col-->
                                                        <div class="col-lg-2">
                                                            <button type="button" class="btn btn-primary" onclick="execDaumPostcode('address','addressDetail')">주소 검색</button>
                                                        </div>
                                                        <!--end::Col-->--%>
                                                    </div>
                                                    <!--end::Row-->
                                                    <!--begin::Row-->
                                                    <div class="row mt-3">
                                                        <!--begin::Col-->
                                                        <div class="col-lg-12">
                                                            <input type="text" id="addressDetail" name="addressDetail" class="form-control form-control-lg form-control-solid-bg" value="${memberInfo.addressDetail}" placeholder="상세주소" readonly/>
                                                        </div>
                                                        <!--end::Col-->
                                                    </div>
                                                    <!--end::Row-->
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">작업복 사이즈 (남여공용)</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="S" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'S'}">checked</c:if> disabled/> S (90)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="M" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'M'}">checked</c:if> disabled/> M (95)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="L" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'L'}">checked</c:if> disabled/> L (100)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="XL" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'XL'}">checked</c:if> disabled/> XL (105)
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="clothesSize" value="XXL" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq 'XXL'}">checked</c:if> disabled/> XXL (110)
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="clothesSize" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.clothesSize eq '기타'}">checked</c:if> disabled/> 기타
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">참여 경로</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10 d-flex align-items-center">
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="인터넷" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '인터넷'}">checked</c:if> disabled/> 인터넷
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="홈페이지" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '홈페이지'}">checked</c:if> disabled/> 홈페이지
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="홍보물" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '홍보물'}">checked</c:if> disabled/> 홍보물
                                                    </label>
                                                    <label class="me-5">
                                                        <input type="radio" name="participationPath" value="지인추천" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '지인추천'}">checked</c:if> disabled/> 지인추천
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="participationPath" value="기타" class="form-check-input form-control-solid-bg"
                                                               <c:if test="${info.participationPath eq '기타'}">checked</c:if> disabled/> 기타
                                                    </label>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">추천인</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" id="recommendPerson" name="recommendPerson" class="form-control form-control-lg form-control-solid-bg" placeholder="추천인" value="${info.recommendPerson}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6">추천인 생년월일</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <div class="input-group">
                                                        <input type="text" id="rcBirthYear" name="rcBirthYear" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="출생연도" value="${info.rcBirthYear} 년" readonly/>
                                                        <input type="text" id="rcBirthMonth" name="rcBirthMonth" class="form-control form-control-lg form-control-solid-bg me-4" placeholder="출생월" value="${info.rcBirthMonth} 월" readonly/>
                                                        <input type="text" id="rcBirthDay" name="rcBirthDay" class="form-control form-control-lg form-control-solid-bg" placeholder="출생일" value="${info.rcBirthDay} 일" readonly/>
                                                    </div>
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
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">신청상태</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-2 col-form-label fw-semibold fs-6 required">상태</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-10">
                                                <input type="text" name="applyStatus" class="form-control form-control-lg form-control-solid-bg" placeholder="상태" value="${info.applyStatus}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-2 col-form-label fw-semibold fs-6 required">취소신청일시</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-10">
                                                <input type="text" name="cancelDttm" class="form-control form-control-lg form-control-solid-bg" placeholder="취소신청일시" value="${info.cancelDttm}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                        <!--begin::Input group-->
                                        <div class="row mb-6">
                                            <!--begin::Label-->
                                            <label class="col-lg-2 col-form-label fw-semibold fs-6 required">취소사유</label>
                                            <!--end::Label-->
                                            <!--begin::Col-->
                                            <div class="col-lg-10">
                                                <input type="text" name="cancelReason" class="form-control form-control-lg form-control-solid-bg" placeholder="취소사유" value="${info.cancelReason}" readonly/>
                                            </div>
                                            <!--end::Col-->
                                        </div>
                                        <!--end::Input group-->
                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Card header-->
                                    <div class="card-header border-0">
                                        <!--begin::Card title-->
                                        <div class="card-title m-0">
                                            <h3 class="fw-bold m-0">결제정보</h3>
                                        </div>
                                        <!--end::Card title-->
                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body border-top p-9">

                                        <c:if test="${paymentInfo ne null}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">결제방식</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'card')}">
                                                        <c:set var="payMethod" value="카드"/>
                                                    </c:if>
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'vbank')}">
                                                        <c:set var="payMethod" value="가상계좌"/>
                                                    </c:if>
                                                    <input type="text" name="payMethod" class="form-control form-control-lg form-control-solid-bg" placeholder="결제방식" value="${payMethod}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">결제정보</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'card')}">
                                                        <input type="text" name="payMethod" class="form-control form-control-lg form-control-solid-bg" placeholder="결제방식" value="${paymentInfo.cardPurchaseName} (${paymentInfo.cardNum})" readonly/>
                                                    </c:if>
                                                    <c:if test="${fn:contains(fn:toLowerCase(paymentInfo.payMethod), 'vbank')}">
                                                        <input type="text" name="payMethod" class="form-control form-control-lg form-control-solid-bg" placeholder="결제방식" value="${paymentInfo.vactBankName} (${paymentInfo.vactNum})" readonly/>
                                                    </c:if>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <fmt:parseDate var="applDate" value="${paymentInfo.applDate}${paymentInfo.applTime}" pattern="yyyyMMddHHmmss" />
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">승인일자</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" name="applDate" class="form-control form-control-lg form-control-solid-bg" placeholder="승인일자" value="<fmt:formatDate value="${applDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">결제금액</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" name="totPrice" class="form-control form-control-lg form-control-solid-bg" placeholder="결제금액" value="<fmt:formatNumber value="${paymentInfo.totPrice}" type="currency" maxFractionDigits="0" currencySymbol="￦ "/> 원" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">환불계좌 은행명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" name="refundBankName" class="form-control form-control-lg form-control-solid-bg" placeholder="환불계좌 은행명" value="${info.refundBankName}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">환불계좌 예금주명</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" name="refundBankCustomerName" class="form-control form-control-lg form-control-solid-bg" placeholder="환불계좌 예금주명" value="${info.refundBankCustomerName}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-2 col-form-label fw-semibold fs-6 required">환불계좌번호</label>
                                                <!--end::Label-->
                                                <!--begin::Col-->
                                                <div class="col-lg-10">
                                                    <input type="text" name="refundBankNumber" class="form-control form-control-lg form-control-solid-bg" placeholder="환불계좌번호" value="${info.refundBankNumber}" readonly/>
                                                </div>
                                                <!--end::Col-->
                                            </div>
                                            <!--end::Input group-->
                                        </c:if>
                                        <c:if test="${paymentInfo eq null}">
                                            <!--begin::Input group-->
                                            <div class="row mb-6">
                                                <!--begin::Label-->
                                                <label class="col-lg-12 col-form-label fw-semibold fs-6">결제정보 없음</label>
                                                <!--end::Label-->
                                            </div>
                                            <!--end::Input group-->
                                        </c:if>
                                    </div>
                                    <!--end::Card body-->
                                </div>
                                <!--end::Basic info-->

                                <!--begin::Basic info-->
                                <div class="card mb-5 mb-xl-10">
                                    <!--begin::Actions-->
                                    <div class="card-footer d-flex justify-content-between py-6 px-9">
                                        <div>
                                            <a href="/mng/customer/outboarder.do" class="btn btn-info btn-active-light-info" id="kt_list_btn">목록</a>
                                        </div>
                                        <%--<div>
                                            <button type="button" onclick="f_customer_boarder_modify_init_set('${info.seq}')" class="btn btn-danger btn-active-light-danger me-2">변경내용취소</button>
                                            <button type="button" onclick="f_customer_boarder_save('${info.seq}')" class="btn btn-primary btn-active-light-primary" id="kt_save_submit">변경내용저장</button>
                                        </div>--%>
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
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/smsNoti.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/outboarder.js?ver=<%=System.currentTimeMillis()%>"></script>
    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
</body>
<!--end::Body-->
</html>