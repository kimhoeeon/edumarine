<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
                                     data-kt-menu-placement="bottom-start" class="menu-item here">
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
                                                                           class="menu-link active">
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
                                <div data-kt-menu-trigger="click" class="menu-item menu-accordion hover show">
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
                                            <a class="menu-link active" href="/mng/request/list.do">
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
</body>
<!--end::Body-->
</html>