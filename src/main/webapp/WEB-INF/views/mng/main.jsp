<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="format-detection" content="telephone=no" />
    <title>경기해양레저 인력양성센터</title>

    <!-- 캐시를 바로 만료시킴. -->
    <meta http-equiv="Expires" content="-1" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.0) -->
    <meta http-equiv="Pragma" content="no-cache" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.1) -->
    <meta http-equiv="Cache-Control" content="no-cache" />

    <%-- favicon
    <link rel="shortcut icon" href="<%request.getContextPath();%>/static/img/favicon.ico" type="image/x-icon" sizes="16X16" />
    <link rel="icon" href="<%request.getContextPath();%>/static/img/favicon.ico" type="image/x-icon" sizes="16X16" />
    --%>

    <!--begin::Fonts(mandatory for all pages)-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700"/>
    <!--end::Fonts-->
    <!--begin::Vendor Stylesheets(used for this page only)-->

    <link href="<%request.getContextPath();%>/static/assets/plugins/custom/datatables/datatables.bundle.css" rel="stylesheet" type="text/css"/>
    <!--end::Vendor Stylesheets-->
    <!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
    <link href="<%request.getContextPath();%>/static/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css"/>
    <link href="<%request.getContextPath();%>/static/assets/css/style.bundle.css" rel="stylesheet" type="text/css"/>
    <!--end::Global Stylesheets Bundle-->

    <!--begin::custom Mng css-->
    <link href="<%request.getContextPath();%>/static/css/mngStyle.css" rel="stylesheet" type="text/css"/>
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

<!-- 페이지 뒤로가기 막기 -->
<script>
    history.pushState(null, null, location.href);
    window.onpopstate = function (event) {
        history.go(1);
    };
</script>
<!-- 페이지 뒤로가기 막기 -->

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
                        <img alt="Logo" src="<%request.getContextPath();%>/static/assets/media/logos/default-small.svg" class="h-30px"/>
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
                                                    <a class="nav-link py-3 py-lg-6 active text-active-primary" href="#"
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
                                                                    <a href="/mng/popup/popup.do"
                                                                       class="menu-link">
                                                                        <span class="menu-title">해상 엔진 테크니션</span>
                                                                    </a>
                                                                    <!--end:Menu link-->
                                                                </div>
                                                                <!--end:Menu item-->
                                                                <!--begin:Menu item-->
                                                                <div class="menu-item p-0 m-0">
                                                                    <!--begin:Menu link-->
                                                                    <a href="/mng/popup/popup.do"
                                                                       class="menu-link">
                                                                        <span class="menu-title">FRP 정비 테크니션</span>
                                                                    </a>
                                                                    <!--end:Menu link-->
                                                                </div>
                                                                <!--end:Menu item-->
                                                                <!--begin:Menu item-->
                                                                <div class="menu-item p-0 m-0">
                                                                    <!--begin:Menu link-->
                                                                    <a href="/mng/popup/popup.do"
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
                                                                    <a href="/mng/popup/popup.do"
                                                                       class="menu-link">
                                                                        <span class="menu-title">자가정비 (세일요트)</span>
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
                                                    <a class="nav-link py-3 py-lg-6 active text-active-primary" href="#"
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
                                            <div class="tab-pane active w-lg-125px" id="kt_app_header_menu_pages_education">
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
                                                    <a class="nav-link py-3 py-lg-6 active text-active-primary" href="#"
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
                                            <div class="tab-pane active w-lg-400px" id="kt_app_header_menu_pages_board">
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
                                                                    <a href="/mng/board/press.do"
                                                                       class="menu-link">
                                                                        <span class="menu-title">/mng/board/press.do</span>
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
                        </div>
                        <!--end::Menu-->
                    </div>
                    <!--end::Menu wrapper-->
                    <!--begin::Navbar-->
                    <div class="app-navbar flex-shrink-0">
                        <!--begin::Search-->
                        <%--<div class="app-navbar-item align-items-stretch ms-1 ms-md-3">
                            <!--begin::Search-->
                            <div id="kt_header_search" class="header-search d-flex align-items-stretch"
                                 data-kt-search-keypress="true" data-kt-search-min-length="2"
                                 data-kt-search-enter="enter" data-kt-search-layout="menu" data-kt-menu-trigger="auto"
                                 data-kt-menu-overflow="false" data-kt-menu-permanent="true"
                                 data-kt-menu-placement="bottom-end">
                                <!--begin::Search toggle-->
                                <div class="d-flex align-items-center" data-kt-search-element="toggle"
                                     id="kt_header_search_toggle">
                                    <div class="btn btn-icon btn-custom btn-icon-muted btn-active-light btn-active-color-primary w-30px h-30px w-md-40px h-md-40px">
                                        <i class="ki-duotone ki-magnifier fs-2 fs-lg-1">
                                            <span class="path1"></span>
                                            <span class="path2"></span>
                                        </i>
                                    </div>
                                </div>
                                <!--end::Search toggle-->
                                <!--begin::Menu-->
                                <div data-kt-search-element="content"
                                     class="menu menu-sub menu-sub-dropdown p-7 w-325px w-md-375px">
                                    <!--begin::Wrapper-->
                                    <div data-kt-search-element="wrapper">
                                        <!--begin::Form-->
                                        <form data-kt-search-element="form" class="w-100 position-relative mb-3" autocomplete="off">
                                            <!--begin::Icon-->
                                            <i class="ki-duotone ki-magnifier fs-2 text-gray-500 position-absolute top-50 translate-middle-y ms-0">
                                                <span class="path1"></span>
                                                <span class="path2"></span>
                                            </i>
                                            <!--end::Icon-->
                                            <!--begin::Input-->
                                            <input type="text"
                                                   class="search-input form-control form-control-flush ps-10"
                                                   name="search" value="" placeholder="Search..."
                                                   data-kt-search-element="input"/>
                                            <!--end::Input-->
                                            <!--begin::Spinner-->
                                            <span class="search-spinner position-absolute top-50 end-0 translate-middle-y lh-0 d-none me-1"
                                                  data-kt-search-element="spinner">
                                                <span class="spinner-border h-15px w-15px align-middle text-gray-400"></span>
                                            </span>
                                            <!--end::Spinner-->
                                            <!--begin::Reset-->
                                            <span class="search-reset btn btn-flush btn-active-color-primary position-absolute top-50 end-0 translate-middle-y lh-0 d-none"
                                                  data-kt-search-element="clear">
                                                <i class="ki-duotone ki-cross fs-2 fs-lg-1 me-0">
                                                    <span class="path1"></span>
                                                    <span class="path2"></span>
                                                </i>
                                            </span>
                                            <!--end::Reset-->
                                        </form>
                                        <!--end::Form-->
                                    </div>
                                    <!--end::Wrapper-->
                                </div>
                                <!--end::Menu-->
                            </div>
                            <!--end::Search-->
                        </div>--%>
                        <!--end::Search-->
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
                                                <span class="badge badge-light-success fw-bold fs-8 px-2 py-1 ms-2">admin</span>
                                            </div>
                                            <a href="/mng/main.do" class="fw-semibold text-muted text-hover-primary fs-7">EduMarine 관리자</a>
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
                        <img alt="Logo" src="<%request.getContextPath();%>/static/img/mng_logo.png<%--/static/assets/media/logos/default-dark.svg--%>"
                             class="h-80px app-sidebar-logo-default"/>
                        <img alt="Logo" src="<%request.getContextPath();%>/static/img/mng_logo.png<%--assets/media/logos/default-small.svg--%>"
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
                                        <a class="menu-link" href="/mng/popup/popup.do">
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
                                        <a class="menu-link" href="/mng/popup/popup.do">
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
                                        <a class="menu-link" href="/mng/popup/popup.do">
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
                                        <a class="menu-link" href="/mng/popup/popup.do">
                                            <span class="menu-bullet">
                                                <span class="bullet bullet-dot"></span>
                                            </span>
                                            <span class="menu-title">자가정비 (세일요트)</span>
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

                        </div>
                        <!--end::Menu-->
                    </div>
                    <!--end::Menu wrapper-->
                </div>
                <!--end::sidebar menu-->
                <!--begin::Footer-->
                <div class="app-sidebar-footer flex-column-auto pt-2 pb-6 px-6" id="kt_app_sidebar_footer">
                    <a href="javascript:alert('준비 중입니다.');"
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
                        <div id="kt_app_toolbar_container" class="app-container container-xxl d-flex flex-stack">
                            <!--begin::Page title-->
                            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                                <!--begin::Title-->
                                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                                    관리자 메인 페이지</h1>
                                <!--end::Title-->
                                <!--begin::Breadcrumb-->
                                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                                    <!--begin::Item-->
                                    <li class="breadcrumb-item text-muted">
                                        <a href="/mng/main.do"
                                           class="text-muted text-hover-primary">Home</a>
                                    </li>
                                    <!--end::Item-->
                                </ul>
                                <!--end::Breadcrumb-->
                            </div>
                            <!--end::Page title-->
                        </div>
                        <!--end::Toolbar container-->
                    </div>
                    <!--end::Toolbar-->

                    <!--begin::Content-->
                    <%--<div id="kt_app_content" class="app-content flex-column-fluid">
                        <!--begin::Content container-->
                        <div id="kt_app_content_container" class="app-container container-xxl">
                            <!--begin::Stats-->
                            <div class="row g-6 g-xl-9">
                                <div class="col-lg-12 col-xxl-12 mb-xl-8">
                                    <!--begin::Card-->
                                    <div class="card h-100">
                                        <!--begin::Card body-->
                                        <div class="card-body p-9">
                                            <h3 class="card-title align-items-start flex-column">
                                                <span class="card-label fw-bold fs-3 mb-1">안내사항</span>
                                                <span class="text-muted fw-semibold fs-7">Information</span>
                                            </h3>
                                            <div class="mt-5 mb-2">※ 현황 통계는 <span class="text-primary fw-bold">'2024'년 데이터</span>만 나타냅니다.</div>
                                            <div class="mb-2">※ 통계(<span class="text-primary fw-bold">'전체'</span>) 항목은 {(참가기업수-참가취소)+(참관객수-참관취소)} 의 값입니다.</div>
                                            <div class="mb-2">※ 그래프(<span class="text-primary fw-bold">'홈페이지 방문 현황'</span>) 항목은 메인페이지 접속 시에 +1 되어 도출된 값입니다.</div>
                                            <div class="mb-2">※ 그래프(<span class="text-primary fw-bold">'참가 신청 현황'</span>) 항목은 참가기업(취소제외), 참관객(취소제외) 수 현황입니다.</div>
                                            <div>※ 그래프(<span class="text-primary fw-bold">'부스별 신청업체 수'</span>) 항목은 부스별 신청업체 수를 백분율로 표현한 그래프입니다.</div>
                                        </div>
                                        <!--end::Card body-->
                                    </div>
                                    <!--end::Card-->
                                </div>
                            </div>
                            <!--end::Stats-->

                            <!--begin::Stats-->
                            <div class="row g-6 g-xl-9">
                                <div class="col-lg-3 col-xxl-3 mb-xl-8">
                                    <!--begin::Card-->
                                    <div class="card h-100">
                                        <!--begin::Card body-->
                                        <div class="card-body p-9">
                                            <!--begin::Heading-->
                                            <div class="fs-2x fw-bold">참가기업 <span class="fs-3 text-danger">( 취소 )</span></div>
                                            <div class="fs-7 fw-semibold text-gray-500 mb-7">* 참가 신청 기업 수 ( 취소 신청 기업 수 )</div>
                                            <!--end::Heading-->
                                            <div class="separator"></div>
                                            <!--begin::Wrapper-->
                                            <div class="d-flex flex-wrap justify-content-center mt-5">
                                                <!--begin::Chart-->
                                                <div class="fs-3x d-flex align-items-start">
                                                    <div class="lh-sm fw-bolder">
                                                        ${companyStat.inCount.split(',')[0] eq null ? 0 : companyStat.inCount.split(',')[0]}
                                                            <span class="fs-3 text-danger">( ${companyStat.inCount.split(',')[1] eq null ? 0 : companyStat.inCount.split(',')[1]} )</span>
                                                    </div>
                                                </div>
                                                <!--end::Chart-->
                                            </div>
                                            <!--end::Wrapper-->
                                        </div>
                                        <!--end::Card body-->
                                    </div>
                                    <!--end::Card-->
                                </div>
                                <div class="col-lg-3 col-xxl-3 mb-xl-8">
                                    <!--begin::Card-->
                                    <div class="card h-100">
                                        <!--begin::Card body-->
                                        <div class="card-body p-9">
                                            <!--begin::Heading-->
                                            <div class="fs-2x fw-bold">참관객 <span class="fs-3 text-danger">( 취소 )</span></div>
                                            <div class="fs-7 fw-semibold text-gray-500 mb-7">* 참관객 수 ( 취소 참관객 수 )</div>
                                            <!--end::Heading-->
                                            <div class="separator"></div>
                                            <!--begin::Wrapper-->
                                            <div class="d-flex flex-wrap justify-content-center mt-5">
                                                <!--begin::Chart-->
                                                <div class="fs-3x d-flex align-items-start">
                                                    <div class="lh-sm fw-bolder">
                                                            ${visitorStat.inCount.split(',')[0] eq null ? 0 : visitorStat.inCount.split(',')[0]}
                                                        <span class="fs-3 text-danger">( ${visitorStat.inCount.split(',')[1] eq null ? 0 : visitorStat.inCount.split(',')[1]} )</span>
                                                    </div>
                                                </div>
                                                <!--end::Chart-->
                                            </div>
                                            <!--end::Wrapper-->
                                        </div>
                                        <!--end::Card body-->
                                    </div>
                                    <!--end::Card-->
                                </div>
                                <div class="col-lg-6 col-xxl-6 mb-xl-8">
                                    <!--begin::Card-->
                                    <div class="card h-100">
                                        <!--begin::Card body-->
                                        <div class="card-body p-9">
                                            <!--begin::Heading-->
                                            <div class="fs-2x fw-bold">전체 <span class="fs-3">( 참가기업 + 참관객 ) <span class="text-danger">( 취소 )</span></span>
                                                <div class="fs-4 fw-semibold text-gray-400 mb-7 d-inline-block float-end">
                                                    <c:set var="now" value="<%=new java.util.Date()%>" />
                                                    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
                                                </div>
                                            </div>
                                            <div class="fs-7 fw-semibold text-gray-500 mb-7">* 참가 기업 및 참관객 신청 수 ( 취소 신청 기업 및 취소 참관객 수 )</div>
                                            <!--end::Heading-->
                                            <div class="separator"></div>
                                            <!--begin::Wrapper-->
                                            <div class="d-flex flex-wrap justify-content-center mt-5">
                                                <!--begin::Chart-->
                                                <div class="fs-3x d-flex align-items-start">
                                                    <div class="lh-sm fw-bolder">
                                                        ${(companyStat.inCount.split(',')[0] eq null ? 0 : companyStat.inCount.split(',')[0]) + visitorStat.inCount}
                                                            <span class="fs-3 text-danger">( ${ (companyStat.inCount.split(',')[1] eq null ? 0 : companyStat.inCount.split(',')[1]) +
                                                                    (visitorStat.inCount.split(',')[1] eq null ? 0 : visitorStat.inCount.split(',')[1])} )</span>
                                                    </div>
                                                </div>
                                                <!--end::Chart-->
                                            </div>
                                            <!--end::Wrapper-->
                                        </div>
                                        <!--end::Card body-->
                                    </div>
                                    <!--end::Card-->
                                </div>
                            </div>
                            <!--end::Stats-->

                            <!--begin::Row-->
                            <div class="row g-5 g-xl-8">

                                <div class="col-xl-6">
                                    <!--begin::Charts Widget 3-->
                                    <div class="card card-xl-stretch">
                                        <!--begin::Header-->
                                        <div class="card-header border-0 pt-5">

                                            <h3 class="card-title align-items-start flex-column">
                                                <span class="card-label fw-bold fs-3 mb-2">홈페이지 방문 현황</span>
                                                <span class="fs-7 fw-semibold text-gray-500">* 사이트 방문자 수</span>
                                            </h3>

                                            <!--begin::Toolbar-->
                                            <div class="homepage-visit card-toolbar" data-kt-buttons="true">
                                                <a class="btn btn-sm btn-color-muted btn-active btn-active-primary px-4 active"
                                                   id="kt_charts_widget_3_day_btn">Day</a>
                                                <a class="btn btn-sm btn-color-muted btn-active btn-active-primary px-4 me-1"
                                                   id="kt_charts_widget_3_week_btn">Week</a>
                                                <a class="btn btn-sm btn-color-muted btn-active btn-active-primary px-4 me-1"
                                                   id="kt_charts_widget_3_month_btn">Month</a>
                                            </div>
                                            <!--end::Toolbar-->
                                        </div>
                                        <!--end::Header-->
                                        <!--begin::Body-->
                                        <div class="card-body">
                                            <!--begin::Chart-->
                                            <div id="kt_charts_widget_3_chart" style="height: 350px"></div>
                                            <!--end::Chart-->
                                        </div>
                                        <!--end::Body-->
                                    </div>
                                    <!--end::Charts Widget 3-->
                                </div>


                                <div class="col-xl-6">
                                    <!--begin::Charts Widget 4-->
                                    <div class="card card-xl-stretch">
                                        <!--begin::Header-->
                                        <div class="card-header border-0 pt-5">
                                            <h3 class="card-title align-items-start flex-column">
                                                <span class="card-label fw-bold fs-3 mb-2">참가 신청 현황</span>
                                                <span class="fs-7 fw-semibold text-gray-500">* 초록(참가기업) / 노랑(참관객)</span>
                                            </h3>
                                            <!--begin::Toolbar-->
                                            <div class="participant-stat card-toolbar" data-kt-buttons="true">
                                                <a class="btn btn-sm btn-color-muted btn-active btn-active-primary px-4 active"
                                                   id="kt_charts_widget_4_day_btn">Day</a>
                                                <a class="btn btn-sm btn-color-muted btn-active btn-active-primary px-4 me-1"
                                                   id="kt_charts_widget_4_week_btn">Week</a>
                                                <a class="btn btn-sm btn-color-muted btn-active btn-active-primary px-4 me-1"
                                                   id="kt_charts_widget_4_month_btn">Month</a>
                                            </div>
                                            <!--end::Toolbar-->
                                        </div>
                                        <!--end::Header-->
                                        <!--begin::Body-->
                                        <div class="card-body">
                                            <!--begin::Chart-->
                                            <div id="kt_charts_widget_4_chart" style="height: 350px"></div>
                                            <!--end::Chart-->
                                        </div>
                                        <!--end::Body-->
                                    </div>
                                    <!--end::Charts Widget 4-->
                                </div>


                                <div class="col-xl-6">
                                    <!--begin::Charts Widget 4-->
                                    <div class="card card-xl-stretch">
                                        <!--begin::Header-->
                                        <div class="card-header border-0 pt-5">
                                            <h3 class="card-title align-items-start flex-column">
                                                <span class="card-label fw-bold fs-3 mb-2">부스별 신청업체 수</span>
                                                <span class="fs-7 fw-semibold text-gray-500">* 부스 신청 유형별 현황 백분율</span>
                                            </h3>
                                        </div>
                                        <!--end::Header-->
                                        <!--begin::Body-->
                                        <div class="card-body d-flex justify-content-center align-items-center pt-0">
                                            <!--begin::Chart-->
                                            <div id="kt_project_list_chart1"></div>
                                            <!--end::Chart-->
                                        </div>
                                        <!--end::Body-->
                                    </div>
                                    <!--end::Charts Widget 4-->
                                </div>

                                <div class="col-xl-6">
                                    <!--begin::Charts Widget 4-->
                                    <div class="card card-xl-stretch">
                                        <!--begin::Header-->
                                        <div class="card-header border-0 pt-5">
                                            <h3 class="card-title align-items-start flex-column">
                                                <span class="card-label fw-bold fs-3 mb-2">참가분야별 신청업체 수</span>
                                                <span class="fs-7 fw-semibold text-gray-500">* 참가분야별 신청 현황 그래프</span>
                                            </h3>
                                        </div>
                                        <!--end::Header-->
                                        <!--begin::Body-->
                                        <div class="card-body d-flex justify-content-end align-items-center pt-0">
                                            <!--begin::Chart-->
                                            <div id="kt_project_list_chart2"></div>
                                            <!--end::Chart-->
                                        </div>
                                        <!--end::Body-->
                                    </div>
                                    <!--end::Charts Widget 4-->
                                </div>

                            </div>
                            <!--end::Row-->
                        </div>
                        <!--end::Content container-->
                    </div>--%>
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

<!--begin::Javascript-->

<script>var hostUrl = "<%request.getContextPath();%>/static/assets/";</script>
<!--begin::Global Javascript Bundle(mandatory for all pages)-->
<script src="<%request.getContextPath();%>/static/assets/plugins/global/plugins.bundle.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/scripts.bundle.js"></script>
<!--end::Global Javascript Bundle-->
<!--begin::Vendors Javascript(used for this page only)-->
<script src="<%request.getContextPath();%>/static/assets/plugins/custom/datatables/datatables.bundle.js"></script>
<!--end::Vendors Javascript-->
<!--begin::Custom Javascript(used for this page only)-->
<script src="<%request.getContextPath();%>/static/assets/js/widgets.bundle.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/custom/widgets.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/custom/apps/chat/chat.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/custom/apps/projects/list/list.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/custom/utilities/modals/upgrade-plan.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/custom/utilities/modals/create-app.js"></script>
<script src="<%request.getContextPath();%>/static/assets/js/custom/utilities/modals/users-search.js"></script>
<!--end::Custom Javascript-->

<!--begin::Custom Javascript(used for common page)-->
<script src="<%request.getContextPath();%>/static/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>
<!--end::Custom Javascript-->

<!--end::Javascript-->

<!--end::login check-->
</c:if>
</body>
<!--end::Body-->
</html>