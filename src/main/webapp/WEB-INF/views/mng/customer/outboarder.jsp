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

    <style>
        #spinner { width: 100%; height: 100%; top: 0; left: 0; opacity: .6; background: silver; position: absolute; z-index: 9999; display: none;}
        #spinner div { width: 100%; height: 100%; display: table; }
        #spinner span { display: table-cell; text-align: center; vertical-align: middle; }
        #spinner img { background: white; padding: 1em; border-radius: .7em; }
    </style>

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

    <div id="spinner">
        <div>
            <span>
                <img src="https://cdnjs.cloudflare.com/ajax/libs/galleriffic/2.0.1/css/loader.gif">
            </span>
        </div>
    </div>

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
                                <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
                                     data-kt-menu-placement="bottom-start" class="menu-item here">
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
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary" href="#"
                                                           data-bs-toggle="tab"
                                                           data-bs-target="#kt_app_header_menu_pages_member">회원 관리</a>
                                                    </li>
                                                    <!--end:Nav item-->
                                                    <!--begin:Nav item-->
                                                    <li class="nav-item mx-lg-1">
                                                        <a class="nav-link py-3 py-lg-6 text-active-primary active" href="#"
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
                                                <div class="tab-pane w-lg-200px" id="kt_app_header_menu_pages_member">
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
                                                <div class="tab-pane w-lg-200px active" id="kt_app_header_menu_pages_user">
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
                                                                           class="menu-link active">
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
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/competency.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title" style="letter-spacing: -1px;">선외기/선내기 직무역량 강화과정</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/famtourin.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">선내기 팸투어</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/famtourout.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">선외기 팸투어</span>
                                                                        </a>
                                                                        <!--end:Menu link-->
                                                                    </div>
                                                                    <!--end:Menu item-->
                                                                    <!--begin:Menu item-->
                                                                    <div class="menu-item p-0 m-0">
                                                                        <!--begin:Menu link-->
                                                                        <a href="/mng/customer/electro.do"
                                                                           class="menu-link">
                                                                            <span class="menu-title">레저선박 해양전자장비 교육</span>
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
                                                                           class="menu-link">
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
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion hover show">
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
                                            <a class="menu-link active" href="/mng/customer/outboarder.do">
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
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/competency.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title" style="letter-spacing: -1px;">선외기/선내기 직무역량 강화과정</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/famtourin.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">선내기 팸투어</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/famtourout.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">선외기 팸투어</span>
                                            </a>
                                            <!--end:Menu link-->
                                        </div>
                                        <!--end:Menu item-->
                                        <!--begin:Menu item-->
                                        <div class="menu-item">
                                            <!--begin:Menu link-->
                                            <a class="menu-link" href="/mng/customer/electro.do">
                                                <span class="menu-bullet">
                                                    <span class="bullet bullet-dot"></span>
                                                </span>
                                                <span class="menu-title">레저선박 해양전자장비 교육</span>
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
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion">
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
                                            <a class="menu-link" href="/mng/education/template.do">
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
                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_excel_export('mng_customer_outboarder_table', '자가정비_선외기')" class="btn btn-success btn-active-light-success" data-kt-export="excel" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>Export as Excel</button>
                                    <!--end::Export dropdown-->

                                    <!--begin::Export dropdown-->
                                    <button type="button" onclick="f_customer_outboarder_detail_excel_download('mng_customer_outboarder_table', '자가정비_선외기_상세')" class="btn btn-warning btn-active-light-warning" data-kt-menu-placement="bottom-end">
                                        <i class="ki-duotone ki-exit-down fs-2">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>전체 신청자 정보 상세 다운로드</button>
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
                                                    <option value="NAMEKO">이름(국문)</option>
                                                    <option value="NAMEEN">이름(영문)</option>
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
                                                <button type="button" onclick="f_customer_outboarder_search()" class="btn btn-primary me-5">Search</button>
                                                <button type="button" onclick="f_customer_outboarder_search_condition_init()" class="btn btn-secondary me-5">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i>검색조건 초기화</button>
                                            </div>
                                            <!--end:Action-->
                                            <%--<div class="ms-auto">
                                                <!--begin::등록-->
                                                <a href="javascript:void(0);" onclick="f_customer_outboarder_modify_init_set('');" class="btn btn-primary ms-auto">등록</a>
                                                <!--end::등록-->
                                            </div>--%>
                                        </div>
                                        <!--end::Card title-->
                                        <!--begin::Card toolbar-->
                                        <div class="card-toolbar flex-row-fluid gap-5">
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-175px">
                                                <!--begin::Select2-->
                                                <select id="condition_year" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 연도 -" onchange="f_customer_outboarder_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 연도 -</option>
                                                    <option value="2025" selected>2025</option>
                                                    <option value="2024">2024</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-175px">
                                                <!--begin::Select2-->
                                                <select id="condition_time" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 차시 -" onchange="f_customer_outboarder_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 차시 -</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>
                                            <%--begin::신청상태--%>
                                            <div class="w-100 mw-150px">
                                                <!--begin::Select2-->
                                                <select id="condition_apply_status" class="form-select form-select-solid" data-control="select2"
                                                        data-hide-search="true" data-allow-clear="true"
                                                        data-placeholder="- 신청상태 -" onchange="f_customer_outboarder_search()">
                                                    <option></option>
                                                    <option value="" disabled>- 신청상태 -</option>
                                                    <option value="결제대기">결제대기</option>
                                                    <option value="결제완료">결제완료</option>
                                                    <option value="수강확정">수강확정</option>
                                                    <option value="수강완료">수강완료</option>
                                                    <option value="환급대기">환급대기</option>
                                                    <option value="환급완료">환급완료</option>
                                                    <option value="취소신청">취소신청</option>
                                                    <option value="취소완료">취소완료</option>
                                                </select>
                                                <!--end::Select2-->
                                            </div>
                                            <%--end::신청상태--%>

                                            <div class="ms-auto d-flex align-items-center gap-2 gap-lg-3">

                                                <!--begin::버튼-->
                                                <%--<button type="button" id="apply_edu_change_btn" class="btn btn-dark" data-bs-target="#kt_modal_apply_edu_change">
                                                    <i class="ki-duotone ki-calendar-tick fs-3">
                                                        <span class="path1"></span>
                                                        <span class="path2"></span>
                                                        <span class="path3"></span>
                                                        <span class="path4"></span>
                                                        <span class="path5"></span>
                                                        <span class="path6"></span>
                                                    </i> 신청 교육 변경</button>--%>
                                                <!--end::버튼-->

                                                <!--begin::버튼-->
                                                <button type="button" id="apply_status_change_btn" class="btn btn-info" data-bs-target="#kt_modal_apply_status_change">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i> 신청 상태 변경</button>
                                                <!--end::버튼-->

                                                <!--begin::버튼-->
                                                <button type="button" id="apply_status_cancel_btn" class="btn btn-danger" data-bs-target="#kt_modal_apply_status_cancel">
                                                    <i class="ki-duotone ki-arrows-circle fs-3">
                                                        <i class="path1"></i>
                                                        <i class="path2"></i>
                                                    </i> 취소 승인</button>
                                                <!--end::버튼-->

                                            </div>

                                        </div>
                                        <!--end::Card toolbar-->

                                    </div>
                                    <!--end::Card header-->
                                    <!--begin::Card body-->
                                    <div class="card-body pt-0">
                                        <div class="fw-bold"><span class="mr10">검색결과</span><span id="search_cnt" style="color: #009ef7;">0</span> 개</div>
                                        <!--begin::Table-->
                                        <table class="table align-middle table-row-dashed fs-6 gy-5" id="mng_customer_outboarder_table">
                                            <thead>
                                                <tr class="text-start text-gray-400 fw-bold fs-7 text-uppercase gs-0">
                                                    <th class="w-10px pe-2">
                                                        <div class="form-check form-check-sm form-check-custom form-check-solid me-3">
                                                            <input class="form-check-input" type="checkbox" data-kt-check="true"
                                                                   data-kt-check-target="#mng_customer_outboarder_table .form-check-input" value="1"/>
                                                        </div>
                                                    </th>
                                                    <th class="text-center min-w-50px">번호</th>
                                                    <th>seq</th>
                                                    <th class="text-center min-w-50px">연도</th>
                                                    <th class="text-center min-w-50px">차시</th>
                                                    <th class="text-center min-w-75px">신청상태</th>
                                                    <th class="text-center min-w-75px">등급</th>
                                                    <th class="text-center min-w-75px">아이디</th>
                                                    <th class="text-center min-w-75px">이름</th>
                                                    <th class="text-center min-w-100px">연락처</th>
                                                    <th class="text-center min-w-125px">등록일시</th>
                                                    <th class="text-center min-w-75px">기능</th>
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_modify_history" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">신청내역 상세보기</h2>
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
                                <label class="form-label">이름</label>
                                <!--end::Label-->
                                <div class="d-flex">
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg me-2" id="md_name_ko" placeholder="이름(국문)" readonly>
                                    <!--end::Input-->
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_name_en" placeholder="이름(영문)" readonly>
                                    <!--end::Input-->
                                </div>
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">연락처</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_phone" placeholder="연락처" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">이메일</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_email" placeholder="이메일" readonly>
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">생년월일</label>
                                <!--end::Label-->
                                <div class="d-flex">
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg me-3" id="md_birth_year" placeholder="년도" readonly>
                                    <!--end::Input-->
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg me-3" id="md_birth_month" placeholder="월" readonly>
                                    <!--end::Input-->
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_birth_day" placeholder="일" readonly>
                                    <!--end::Input-->
                                </div>
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">주소</label>
                                <!--end::Label-->
                                <div>
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg mb-3" id="md_address" placeholder="주소" readonly>
                                    <!--end::Input-->
                                    <!--begin::Input-->
                                    <input type="text" class="form-control form-control-lg form-control-solid-bg" id="md_address_detail" placeholder="상세주소" readonly>
                                    <!--end::Input-->
                                </div>
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">작업복 사이즈</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_clothes_size" value="S" class="form-check-input form-control-solid-bg" disabled/> S (90)
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_clothes_size" value="M" class="form-check-input form-control-solid-bg" disabled/> M (95)
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_clothes_size" value="L" class="form-check-input form-control-solid-bg" disabled/> L (100)
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_clothes_size" value="XL" class="form-check-input form-control-solid-bg" disabled/> XL (105)
                                    </label>
                                    <label>
                                        <input type="radio" name="md_clothes_size" value="기타" class="form-check-input form-control-solid-bg" disabled/> 기타
                                    </label>
                                </div>
                                <!--end::Col-->
                                <!--end::Input-->
                            </div>
                            <!--end::Input group-->

                            <!--begin::Input group-->
                            <div class="mb-6">
                                <!--begin::Label-->
                                <label class="form-label">참여 경로</label>
                                <!--end::Label-->
                                <!--begin::Input-->
                                <!--begin::Col-->
                                <div class="d-flex align-items-center">
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="인터넷" class="form-check-input form-control-solid-bg" disabled/> 인터넷
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="홈페이지" class="form-check-input form-control-solid-bg" disabled/> 홈페이지
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="홍보물" class="form-check-input form-control-solid-bg" disabled/> 홍보물
                                    </label>
                                    <label class="me-5">
                                        <input type="radio" name="md_participation_path" value="지인추천" class="form-check-input form-control-solid-bg" disabled/> 지인추천
                                    </label>
                                    <label>
                                        <input type="radio" name="md_participation_path" value="기타" class="form-check-input form-control-solid-bg" disabled/> 기타
                                    </label>
                                </div>
                                <!--end::Col-->
                                <!--end::Input-->
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_apply_status_cancel" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">취소 승인 처리</h2>
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
                    <!--begin::form-->
                    <form id="modal_form" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">취소 승인 대상 목록</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <span class="customer_list"></span>
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">환불 구분</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <!--begin::Select2-->
                                <select id="md_cancel_gbn" class="form-select form-select-solid" data-control="select2"
                                        data-hide-search="true" data-allow-clear="true"
                                        data-placeholder="- 환불 구분 -">
                                    <option></option>
                                    <option value="" disabled>- 환불 구분 -</option>
                                    <option value="ALL">전액 환불</option>
                                    <option value="PART">조건 환불 (환불 기한에 따라 환불)</option>
                                    <option value="NOPAY">미결제환불</option>
                                </select>
                                <!--end::Select2-->
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
                            <a class="btn btn-danger" data-bs-dismiss="modal">취소</a>
                            <!--end::Cancel-->
                        </div>
                        <!--end::Col-->
                        <!--begin::Col-->
                        <div class="ms-10">
                            <!--begin::File upload-->
                            <a onclick="f_apply_cancel_btn()" class="btn btn-primary">승인</a>
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_apply_status_change" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">신청 상태 변경</h2>
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
                    <!--begin::Input group-->
                    <div>
                        * <strong>결제완료</strong> : (가상계좌일 경우) 현재 <strong class="text-danger">입금대기</strong> 상태인 경우에만 적용<br>
                        * <strong>수강확정</strong> : 현재 <strong class="text-danger">결제완료</strong> 상태인 경우에만 적용<br>
                        * <strong>수강완료</strong> : 현재 <strong class="text-danger">수강확정</strong> 상태인 경우에만 적용<br>
                        * <strong>환급대기</strong> : 현재 <strong class="text-danger">수강완료</strong> 상태인 경우에만 적용<br>
                        * <strong>환급완료</strong> : 현재 <strong class="text-danger">환급대기</strong> 상태인 경우에만 적용<br>
                    </div>
                    <!--begin::Menu separator-->
                    <div class="separator my-6"></div>
                    <!--end::Menu separator-->
                    <!--begin::form-->
                    <form id="modal_form2" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">신청 상태 변경 대상 목록</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <span class="customer_list"></span>
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-4 col-form-label required fw-semibold fs-6">변경할 신청 상태</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-8 d-flex align-items-center">
                                <!--begin::Select2-->
                                <select id="md_status_gbn" class="form-select form-select-solid" data-control="select2"
                                        data-hide-search="true" data-allow-clear="true"
                                        data-placeholder="- 신청 상태 -">
                                    <option></option>
                                    <option value="" disabled>- 신청 상태 -</option>
                                    <option value="결제완료">결제완료</option>
                                    <option value="수강확정">수강확정</option>
                                    <option value="수강완료">수강완료</option>
                                    <option value="환급대기">환급대기</option>
                                    <option value="환급완료">환급완료</option>
                                </select>
                                <!--end::Select2-->
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
                            <a class="btn btn-danger" data-bs-dismiss="modal">취소</a>
                            <!--end::Cancel-->
                        </div>
                        <!--end::Col-->
                        <!--begin::Col-->
                        <div class="ms-10">
                            <!--begin::File upload-->
                            <a onclick="f_apply_change_btn()" class="btn btn-primary">변경</a>
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

    <!--begin::Modal - 수정이력-->
    <div class="modal fade" id="kt_modal_apply_edu_change" tabindex="-1" aria-hidden="true">
        <!--begin::Modal dialog-->
        <div class="modal-dialog modal-dialog-centered mw-1000px">
            <!--begin::Modal content-->
            <div class="modal-content">
                <!--begin::Modal header-->
                <div class="modal-header" style="background-color: #1e1e2d;">
                    <!--begin::Modal title-->
                    <h2 style="color: #FFFFFF;">신청 교육 변경</h2>
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
                    <!--begin::Input group-->
                    <div class="lh-lg">
                        <c:set var="now" value="<%=new java.util.Date()%>"/>
                        <fmt:formatDate var="fmNow" value="${now}" pattern="yyyy.MM.dd"/>
                        * 오늘 날짜 기준 ( ${fmNow} ) 으로 <br/>
                        1. <strong class="text-danger">접수일정 or 교육일정에 해당되거나 교육 활성화 상태( 마감 X, 삭제 X )인 경우</strong><br/>
                        2. <strong class="text-danger">동일 교육 내 차시 변경의 경우</strong><br/>
                        에만 변경 가능합니다.<br/><br/>
                        * <strong>교육 변경 대상 목록</strong> : <strong>교육생명 / 현재 결제 상태</strong> ( 현재 결제 상태가 <strong class="text-danger">취소신청/취소완료인 경우 교육 변경 불가</strong> )<br/>
                        * <strong>변경할 교육</strong> : <strong>과정분류 / 교육명 / 차시 / 교육시작일정 / 교육종료일정</strong> 순<br/>
                    </div>
                    <!--begin::Menu separator-->
                    <div class="separator my-6"></div>
                    <!--end::Menu separator-->
                    <!--begin::form-->
                    <form id="modal_form3" method="post" onsubmit="return false;">
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-2 col-form-label required fw-semibold fs-6">교육 변경 대상 목록</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-10 d-flex align-items-center">
                                <span class="target_list"></span>
                            </div>
                            <!--end::Col-->
                        </div>
                        <!--end::Input group-->
                        <!--begin::Input group-->
                        <div class="row mb-6">
                            <!--begin::Label-->
                            <label class="col-lg-2 col-form-label required fw-semibold fs-6">변경할 교육</label>
                            <!--end::Label-->
                            <!--begin::Col-->
                            <div class="col-lg-10 d-flex align-items-center">
                                <!--begin::Select2-->
                                <select id="md_edu" class="form-select form-select-solid" data-control="select2"
                                        data-hide-search="true" data-allow-clear="true"
                                        data-placeholder="- 교육 -">
                                    <option></option>
                                    <option value="" disabled>- 교육 -</option>
                                </select>
                                <!--end::Select2-->
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
                            <a class="btn btn-danger" data-bs-dismiss="modal">취소</a>
                            <!--end::Cancel-->
                        </div>
                        <!--end::Col-->
                        <!--begin::Col-->
                        <div class="ms-10">
                            <!--begin::File upload-->
                            <a onclick="f_customer_outboarder_train_change_btn()" class="btn btn-primary">변경</a>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.5/xlsx.full.min.js"></script>
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/smsNoti.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="/js/mng/outboarder.js?ver=<%=System.currentTimeMillis()%>"></script>

    <script>

        $(document).ready(function() {

            // 차시 검색조건 Set
            $("#condition_time").children('option:not(:gt(1))').remove();

            let json = {
                gbn: '해상엔진 자가정비 (선외기)'
            }
            $.ajax({
                url: '/train/selectNextTime.do',
                method: 'post',
                data: JSON.stringify(json),
                contentType: 'application/json; charset=utf-8' //server charset 확인 필요
            })
            .done(function (data, status) {
                let results = data;
                if (nvl(results, '') !== '') {
                    $.each(results, function (i) {
                        $('#condition_time').append($('<option>', {
                            value: results[i].nextTime,
                            text: results[i].nextTime + '차'
                        }));
                    })

                    $('#condition_time').val('').select2({minimumResultsForSearch: Infinity});
                }
            }).always(function() {
                let nextTime = '${nextTime}';
                if(nvl(nextTime,'') !== ''){
                    $('#condition_time').val(nextTime).trigger('change');
                }
            });

        });

        document.addEventListener("keyup", function(event) {
            if (event.key === 'Enter') {
                f_customer_outboarder_search();
            }
        });
    </script>

    <!--end::Custom Javascript-->

    <!--end::Javascript-->

    <!--end::login check-->
</c:if>
</body>
<!--end::Body-->
</html>