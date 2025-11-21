<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="items" type="java.util.List" required="true" %>

<c:set var="currentURI" value="${pageContext.request.requestURI}" />

<c:forEach var="item" items="${items}">
    <%-- 헤더 메뉴는 주로 하위 메뉴가 있는 항목만 표시하거나, 링크형태로 표시 --%>
    <c:if test="${not empty item.children or not empty item.url}">

        <c:set var="isHeaderActive" value="false" />
        <c:if test="${not empty item.url and fn:contains(currentURI, item.url)}">
            <c:set var="isHeaderActive" value="true" />
        </c:if>
        <c:forEach var="child" items="${item.children}">
            <c:if test="${fn:contains(currentURI, child.url)}">
                <c:set var="isHeaderActive" value="true" />
            </c:if>
        </c:forEach>

        <div data-kt-menu-trigger="{default: 'click', lg: 'hover'}" data-kt-menu-placement="bottom-start"
             class="menu-item ${isHeaderActive ? 'here show' : ''}">

            <span class="menu-link">
                <span class="menu-title">${item.title}</span>
                <span class="menu-arrow d-lg-none"></span>
            </span>

                <%-- 하위 메뉴 드롭다운 --%>
            <c:if test="${not empty item.children}">
                <div class="menu-sub menu-sub-lg-down-accordion menu-sub-lg-dropdown p-0">
                    <div class="menu-active-bg px-4 px-lg-0">
                        <div class="tab-content py-4 py-lg-8 px-lg-7">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <c:forEach var="child" items="${item.children}">
                                                <div class="menu-item p-0 m-0">
                                                    <a href="${child.url}" class="menu-link ${fn:contains(currentURI, child.url) ? 'active' : ''}">
                                                        <span class="menu-title">${child.title}</span>
                                                    </a>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>
</c:forEach>