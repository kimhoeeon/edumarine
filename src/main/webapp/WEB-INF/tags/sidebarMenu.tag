<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="items" type="java.util.List" required="true" %>

<%-- 현재 페이지 URL 확인 --%>
<c:set var="currentURI" value="${pageContext.request.requestURI}" />

<div class="menu menu-column menu-rounded menu-sub-indention px-3" id="#kt_app_sidebar_menu"
     data-kt-menu="true" data-kt-menu-expand="false">

    <c:forEach var="item" items="${items}">

        <%-- 1. 헤더(구분선)인 경우 --%>
        <c:if test="${empty item.url and empty item.icon}">
            <div class="menu-item pt-5">
                <div class="menu-content">
                    <span class="menu-heading fw-bold text-uppercase fs-7">${item.title}</span>
                </div>
            </div>
        </c:if>

        <%-- 2. 하위 메뉴가 있는 경우 (아코디언) --%>
        <c:if test="${not empty item.children}">
            <%-- 활성화 로직: 하위 메뉴 중 하나라도 현재 URL과 일치하면 상위 메뉴도 펼침 --%>
            <c:set var="isActive" value="false" />
            <c:forEach var="child" items="${item.children}">
                <c:if test="${fn:contains(currentURI, child.url)}">
                    <c:set var="isActive" value="true" />
                </c:if>
            </c:forEach>

            <div data-kt-menu-trigger="click" class="menu-item menu-accordion ${isActive ? 'here show' : ''}">
                <span class="menu-link">
                    <span class="menu-icon">
                        <i class="ki-duotone ${item.icon} fs-2">
                            <c:forEach begin="1" end="${item.pathCount}" var="i">
                                <span class="path${i}"></span>
                            </c:forEach>
                        </i>
                    </span>
                    <span class="menu-title">${item.title}</span>
                    <span class="menu-arrow"></span>
                </span>
                <div class="menu-sub menu-sub-accordion">
                    <c:forEach var="child" items="${item.children}">
                        <div class="menu-item">
                            <a class="menu-link ${fn:contains(currentURI, child.url) ? 'active' : ''}" href="${child.url}">
                                <span class="menu-bullet">
                                    <span class="bullet bullet-dot"></span>
                                </span>
                                <span class="menu-title">${child.title}</span>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <%-- 3. 하위 메뉴가 없는 일반 링크 --%>
        <c:if test="${not empty item.url and empty item.children}">
            <div class="menu-item">
                <a class="menu-link ${fn:contains(currentURI, item.url) ? 'active' : ''}" href="${item.url}">
                    <span class="menu-icon">
                        <i class="ki-duotone ${item.icon} fs-2">
                            <c:forEach begin="1" end="${item.pathCount}" var="i">
                                <span class="path${i}"></span>
                            </c:forEach>
                        </i>
                    </span>
                    <span class="menu-title">${item.title}</span>
                </a>
            </div>
        </c:if>

    </c:forEach>
</div>