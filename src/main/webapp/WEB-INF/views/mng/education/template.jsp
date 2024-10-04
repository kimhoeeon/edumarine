<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

    <!-- 캐시를 바로 만료시킴. -->
    <meta http-equiv="Expires" content="-1" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.0) -->
    <meta http-equiv="Pragma" content="no-cache" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.1) -->
    <meta http-equiv="Cache-Control" content="no-cache" />

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
      data-kt-app-sidebar-push-footer="true" data-kt-app-toolbar-enabled="true"
      data-kt-app-page-loading-enabled="true" data-kt-app-page-loading="on"
      class="app-default">
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
                            <img alt="Logo" src="/assets/media/logos/default-small.svg" class="h-30px"/>
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
                                <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
                                     data-kt-menu-placement="bottom-start" class="menu-item">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-title">회원 / 신청</span>
                                        <span class="menu-arrow d-lg-none"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-lg-down-accordion menu-sub-lg-dropdown p-0">
                                        <!--begin:Pages menu-->
                                        <div class="menu-active-bg px-4 px-lg-0">
                                            <!--begin:Tabs nav-->
                                            <div class="d-flex w-100 overflow-auto">
                                                <ul class="nav nav-stretch nav-line-tabs fw-bold fs-6 p-0 p-lg-10 flex-nowrap flex-grow-1">
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary active" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_member">회원 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_user">신청자 목록</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                </ul>
                                            </div>
                                            <!--end:Tabs nav-->
                                            <!--begin:Tab content-->
                                            <div class="tab-content py-4 py-lg-8 px-lg-7">
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane active w-lg-200px" id="kt_app_header_menu_pages_member">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/member.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">전체 회원 목록</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/resume.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">나의 이력서 목록</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane w-lg-200px" id="kt_app_header_menu_pages_user">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/regular.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">상시 사전 신청</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/boarder.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">해상 엔진 테크니션</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/frp.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">FRP 정비 테크니션</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/basic.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">기초정비교육</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/emergency.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">응급조치교육</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/outboarder.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">자가정비 (선외기)</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/inboarder.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">자가정비 (선내기)</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/sailyacht.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">자가정비 (세일요트)</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/highhorsepower.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">고마력 선외기 정비</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/highself.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">자가정비 심화과정 (고마력)</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/highspecial.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">고마력 선외기 정비 (특별반)</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/sterndrive.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">스턴드라이브 정비</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/sternspecial.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">스턴드라이브 정비 (특별반)</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/generator.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">발전기 정비 교육</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                            </div>
                                            <!--end:Tab content-->
                                        </div>
                                        <!--end:Pages menu-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
                                     data-kt-menu-placement="bottom-start" class="menu-item here">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-title">교육</span>
                                        <span class="menu-arrow d-lg-none"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-lg-down-accordion menu-sub-lg-dropdown p-0">
                                        <!--begin:Pages menu-->
                                        <div class="menu-active-bg px-4 px-lg-0">
                                            <!--begin:Tabs nav-->
                                            <div class="d-flex w-100 overflow-auto">
                                                <ul class="nav nav-stretch nav-line-tabs fw-bold fs-6 p-0 p-lg-10 flex-nowrap flex-grow-1">
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary active" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_education">교육 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                </ul>
                                            </div>
                                            <!--end:Tabs nav-->
                                            <!--begin:Tab content-->
                                            <div class="tab-content py-4 py-lg-8 px-lg-7">
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane active w-lg-175px" id="kt_app_header_menu_pages_education">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/education/train.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">교육 현황</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/education/template.do"
                                                                           class="menu-link active">
                                                                            <span class="menu-title">교육 안내 템플릿 관리</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/education/payment.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">결제/환불 현황</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                            </div>
                                            <!--end:Tab content-->
                                        </div>
                                        <!--end:Pages menu-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
                                     data-kt-menu-placement="bottom-start" class="menu-item">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-title">정보센터</span>
                                        <span class="menu-arrow d-lg-none"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-lg-down-accordion menu-sub-lg-dropdown p-0">
                                        <!--begin:Pages menu-->
                                        <div class="menu-active-bg px-4 px-lg-0">
                                            <!--begin:Tabs nav-->
                                            <div class="d-flex w-100 overflow-auto">
                                                <ul class="nav nav-stretch nav-line-tabs fw-bold fs-6 p-0 p-lg-10 flex-nowrap flex-grow-1">
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary active" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_board">게시판 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_pop">팝업/배너 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_news">뉴스레터 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_sms">SMS 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                </ul>
                                            </div>
                                            <!--end:Tabs nav-->
                                            <!--begin:Tab content-->
                                            <div class="tab-content py-4 py-lg-8 px-lg-7">
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane w-lg-400px active" id="kt_app_header_menu_pages_board">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/notice.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">공지사항</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/press.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">보도자료</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/gallery.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">사진자료</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/media.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">영상자료</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/newsletter.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">뉴스레터</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <%--<div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/announcement.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">채용공고</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>--%>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/employment.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">취/창업 현황</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/job.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">취/창업 성공후기</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/community.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">커뮤니티</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/board/faq.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">FAQ</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane w-lg-400px" id="kt_app_header_menu_pages_pop">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/pop/popup.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">팝업 관리</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/pop/banner.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">배너 관리</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane w-lg-400px" id="kt_app_header_menu_pages_news">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/newsletter/subscriber.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">뉴스레터 구독자 관리</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane w-lg-400px" id="kt_app_header_menu_pages_sms">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/smsMng/sms.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">SMS 발송 관리</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                            </div>
                                            <!--end:Tab content-->
                                        </div>
                                        <!--end:Pages menu-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
                                     data-kt-menu-placement="bottom-start" class="menu-item">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-title">파일</span>
                                        <span class="menu-arrow d-lg-none"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-lg-down-accordion menu-sub-lg-dropdown p-0">
                                        <!--begin:Pages menu-->
                                        <div class="menu-active-bg px-4 px-lg-0">
                                            <!--begin:Tabs nav-->
                                            <div class="d-flex w-100 overflow-auto">
                                                <ul class="nav nav-stretch nav-line-tabs fw-bold fs-6 p-0 p-lg-10 flex-nowrap flex-grow-1">
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 active text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_file">파일 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                </ul>
                                            </div>
                                            <!--end:Tabs nav-->
                                            <!--begin:Tab content-->
                                            <div class="tab-content py-4 py-lg-8 px-lg-7">
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane active w-lg-150px" id="kt_app_header_menu_pages_file">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/file/download.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">다운로드 내역</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/file/trash.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">임시 휴지통</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                            </div>
                                            <!--end:Tab content-->
                                        </div>
                                        <!--end:Pages menu-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
                                     data-kt-menu-placement="bottom-start" class="menu-item">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                <span class="menu-title">개발사</span>
                                <span class="menu-arrow d-lg-none"></span>
                            </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-lg-down-accordion menu-sub-lg-dropdown p-0">
                                        <!--begin:Pages menu-->
                                        <div class="menu-active-bg px-4 px-lg-0">
                                            <!--begin:Tabs nav-->
                                            <div class="d-flex w-100 overflow-auto">
                                                <ul class="nav nav-stretch nav-line-tabs fw-bold fs-6 p-0 p-lg-10 flex-nowrap flex-grow-1">
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 active text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_request">요청사항 & 문의</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                </ul>
                                            </div>
                                            <!--end:Tabs nav-->
                                            <!--begin:Tab content-->
                                            <div class="tab-content py-4 py-lg-8 px-lg-7">
                                                <!--begin:Tab pane-->
                                                <div class="tab-pane active w-lg-175px" id="kt_app_header_menu_pages_request">
                                                    <!--begin:Row-->
                                                    <div class="row">
                                                        <!--begin:Col-->
                                                        <div class="col-lg-12 mb-6 mb-lg-0">
                                                            <!--begin:Row-->
                                                            <div class="row">
                                                                <!--begin:Col-->
                                                                <div class="col-lg-12">
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/request/list.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">요청사항 & 문의 관리</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                </div>
                                                                <!--end:Col-->
                                                            </div>
                                                            <!--end:Row-->
                                                        </div>
                                                        <!--end:Col-->
                                                    </div>
                                                    <!--end:Row-->
                                                </div>
                                                <!--end:Tab pane-->
                                            </div>
                                            <!--end:Tab content-->
                                        </div>
                                        <!--end:Pages menu-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
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
                            <!--begin::Menu-->
                            <div class="menu menu-column menu-rounded menu-sub-indention px-3" id="#kt_app_sidebar_menu"
                                 data-kt-menu="true" data-kt-menu-expand="false">
                                <!--begin:Menu item-->
                                <div class="menu-item pt-5">
                                    <!--begin:Menu content-->
                                    <div class="menu-content">
                                        <span class="menu-heading fw-bold text-uppercase fs-7">회원 / 신청</span>
                                    </div>
                                    <!--end:Menu content-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-address-book fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                                <span class="path3"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">회원관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/member.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">전체 회원 목록</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/resume.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">나의 이력서 목록</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-people fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                                <span class="path3"></span>
                                                <span class="path4"></span>
                                                <span class="path5"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">신청자 목록</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/regular.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">상시 사전 신청</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/boarder.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">해상 엔진 테크니션</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/frp.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">FRP 정비 테크니션</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/basic.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">기초정비교육</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/emergency.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">응급조치교육</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/outboarder.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">자가정비 (선외기)</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/inboarder.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">자가정비 (선내기)</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/sailyacht.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">자가정비 (세일요트)</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/highhorsepower.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">고마력 선외기 정비</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/highself.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">자가정비 심화과정 (고마력)</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/highspecial.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">고마력 선외기 정비 (특별반)</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/sterndrive.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">스턴드라이브 정비</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/sternspecial.do">
												<span class="menu-bullet">
													<span class="bullet bullet-dot"></span>
												</span>
                                                <span class="menu-title">스턴드라이브 정비 (특별반)</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/generator.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">발전기 정비 교육</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div class="menu-item pt-5">
                                    <!--begin:Menu content-->
                                    <div class="menu-content">
                                        <span class="menu-heading fw-bold text-uppercase fs-7">교육</span>
                                    </div>
                                    <!--end:Menu content-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion hover show">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-book-open fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                                <span class="path3"></span>
                                                <span class="path4"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">교육 관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/education/train.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">교육 현황</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link active" href="/mng/education/template.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">교육 안내 템플릿 관리</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/education/payment.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">결제/환불 현황</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div class="menu-item pt-5">
                                    <!--begin:Menu content-->
                                    <div class="menu-content">
                                        <span class="menu-heading fw-bold text-uppercase fs-7">정보센터</span>
                                    </div>
                                    <!--end:Menu content-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-element-8 fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">게시판 관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/notice.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">공지사항</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/press.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">보도자료</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/gallery.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">사진자료</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/media.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">영상자료</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/newsletter.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">뉴스레터</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <%--<div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/announcement.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">채용공고</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>--%>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/employment.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">취/창업 현황</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/job.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">취/창업 성공후기</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/community.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">커뮤니티</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/board/faq.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">FAQ</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-messages fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                                <span class="path3"></span>
                                                <span class="path4"></span>
                                                <span class="path5"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">팝업/배너 관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/pop/popup.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">팝업 관리</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/pop/banner.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">배너 관리</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-directbox-default fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                                <span class="path3"></span>
                                                <span class="path4"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">뉴스레터 관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/newsletter/subscriber.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">뉴스레터 구독자 관리</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-sms fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">SMS 관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/smsMng/sms.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">SMS 발송 관리</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div class="menu-item pt-5">
                                    <!--begin:Menu content-->
                                    <div class="menu-content">
                                        <span class="menu-heading fw-bold text-uppercase fs-7">파일</span>
                                    </div>
                                    <!--end:Menu content-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                        <span class="menu-icon">
                                            <i class="ki-duotone ki-folder fs-2">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                            </i>
                                        </span>
                                        <span class="menu-title">파일 관리</span>
                                        <span class="menu-arrow"></span>
                                    </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/file/download.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">다운로드 내역</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/file/trash.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">임시 휴지통</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div class="menu-item pt-5">
                                    <!--begin:Menu content-->
                                    <div class="menu-content">
                                        <span class="menu-heading fw-bold text-uppercase fs-7">개발사</span>
                                    </div>
                                    <!--end:Menu content-->
                                </div>
                                <!--end:Menu item-->
                                <!--begin:Menu item-->
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
                                    <!--begin:Menu link-->
                                    <span class="menu-link">
                                <span class="menu-icon">
                                    <i class="ki-duotone ki-notification-on fs-2">
                                        <span class="path1"></span>
                                        <span class="path2"></span>
                                        <span class="path3"></span>
                                        <span class="path4"></span>
                                        <span class="path5"></span>
                                    </i>
                                </span>
                                <span class="menu-title">요청사항 & 문의</span>
                                <span class="menu-arrow"></span>
                            </span>
                                    <!--end:Menu link-->
                                    <!--begin:Menu sub-->
                                    <div class="menu-sub menu-sub-accordion">
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/request/list.do">
                                        <span class="menu-bullet">
                                            <span class="bullet bullet-dot"></span>
                                        </span>
                                                <span class="menu-title">요청사항 & 문의 관리</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                    </div>
                                    <!--end:Menu sub-->
                                </div>
                                <!--end:Menu item-->
                            </div>
                            <!--end::Menu-->
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
                                                <li class="nav-item">
                                                    <a class="nav-link active" id="marina" data-toggle="tab" href="#kt_tab_pane_4_1">
                                                        <span class="nav-text">마리나 선박 정비사 실무교육</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="commission" data-toggle="tab" href="#kt_tab_pane_4_2">
                                                        <span class="nav-text">위탁교육</span>
                                                    </a>
                                                </li>
                                                <li class="nav-item dropdown">
                                                    <a class="nav-link dropdown-toggle" id="boarder" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                                        <span class="nav-text">해상엔진 자가정비</span>
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
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content">
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

                                                <!-- 선외기 자가정비과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>선외기 자가정비과정</th>
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
                                                <!-- //선외기 자가정비과정 -->

                                                <!-- 선외기 자가정비과정 -->
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
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당과정 폐강</p>
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

                                                <!-- //선외기 자가정비과정 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_4" role="tabpanel" aria-labelledby="kt_tab_pane_4_4">

                                                <!-- 선내기 자가정비과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>선내기 자가정비과정</th>
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
                                                <!-- //선내기 자가정비과정 -->

                                                <!-- 선내기 자가정비과정 -->
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
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당과정 폐강</p>
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

                                                <!-- //선내기 자가정비과정 -->

                                            </div>
                                            <div class="tab-pane fade" id="kt_tab_pane_4_5" role="tabpanel" aria-labelledby="kt_tab_pane_4_5">

                                                <!-- 세일요트 자가정비과정 -->
                                                <div class="guide_tp_01">
                                                    <div class="mobile_cmnt">표를 좌우로 움직여 확인해 주세요.</div>
                                                    <div class="table">
                                                        <table>
                                                            <thead>
                                                            <!-- 과정명 -->
                                                            <tr>
                                                                <th class="gubun">구분</th>
                                                                <th>세일요트 자가정비과정</th>
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
                                                <!-- //세일요트 자가정비과정 -->

                                                <!-- 세일요트 자가정비과정 -->
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
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당과정 폐강</p>
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

                                                <!-- //세일요트 자가정비과정 -->

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
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당과정 폐강</p>
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
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당과정 폐강</p>
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
                                                                    <p>※ 마감일 기준 교육신청 최소정원(4명) 미달 시 해당과정 폐강</p>
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

                                        </div>
                                    </div>
                                </div>
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

        })
    </script>
    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
</body>
<!--end::Body-->
</html>