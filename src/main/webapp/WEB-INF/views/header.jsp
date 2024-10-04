<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- header -->
<div id="header">
    <!-- header_top -->
    <div class="header_top">
        <div class="inner">
            <c:if test="${empty status}">
                <a href="/member/login.do">로그인</a>
                <a href="/member/join.do">회원가입</a>
            </c:if>
            <c:if test="${not empty status}">
                <a href="/logout.do" onclick="sessionStorage.clear();">로그아웃</a>
                <a href="javascript:void(0);" onclick="f_page_move('/mypage/eduApplyInfo.do',{ id: '${id}' })" class="mypage">마이페이지</a>
            </c:if>
            <a href="/sitemap.do" class="sitemap"><img src="/img/icon_menu_white.png" alt="메뉴 아이콘"></a>
        </div>
    </div>
    <!-- //header_top -->
    <!-- header_bottom -->
    <div class="header_bot">
        <div class="inner">
            <h1><a href="/" class="logo"><img src="/img/logo.png" alt="로고"></a></h1>
            <a href="#a" class="m_menu">
                <span>메뉴</span>
            </a>
            <div class="nav">
                <div class="mobile_top">
                    <c:if test="${empty status}">
                        <a href="/member/login.do">로그인</a>
                        <a href="/member/join.do">회원가입</a>
                    </c:if>
                    <c:if test="${not empty status}">
                        <a href="/logout.do" onclick="sessionStorage.clear();">로그아웃</a>
                        <a href="javascript:void(0);" onclick="f_page_move('/mypage/eduApplyInfo.do',{ id: '${id}' })" class="mypage">마이페이지</a>
                    </c:if>
                </div>
                <ul class="dept1">
                    <li>
                        <a href="/edumarine/introduce.do">센터소개</a>
                        <ul class="dept2">
                            <li><a href="/edumarine/introduce.do">EDU marine 소개</a></li>
                            <li><a href="/edumarine/overview.do">사업개요</a></li>
                            <li><a href="/edumarine/current.do">경기도 해양레저 현황</a></li>
                            <li><a href="/edumarine/necessity.do">해양레저 인력양성의 필요성</a></li>
                            <li><a href="/edumarine/sponsorship.do">협력 및 후원기관</a></li>
                            <li><a href="/edumarine/way.do">찾아오시는 길</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/guide/guide01.do">교육안내</a>
                        <ul class="dept2">
                            <li><a href="/guide/guide01.do">전체 교육과정 소개</a></li>
                            <%--<li><a href="/guide/guide02.do">해상엔진 테크니션 (선내기/선외기)</a></li>--%>
                            <li><a href="/guide/guide03.do">마리나 선박 정비사 실무교육</a></li>
                            <%--<li><a href="/guide/guide04.do">FRP 레저보트 선체 정비 테크니션</a></li>--%>
                            <li><a href="/guide/guide05.do">위탁교육</a></li>
                            <li><a href="/guide/guide12.do">해상엔진 기초정비교육</a></li>
                            <li><a href="/guide/guide13.do">해상엔진 응급조치교육</a></li>
                            <%--<li><a href="/guide/guide06.do">해상엔진 자가정비 (선외기)</a></li>
                            <li><a href="/guide/guide07.do">해상엔진 자가정비 (선내기)</a></li>
                            <li><a href="/guide/guide08.do">해상엔진 자가정비 (세일요트)</a></li>--%>
                            <li><a href="/guide/guide11.do">자가정비 심화과정 (고마력 선외기)</a></li>
                            <li><a href="/guide/guide14.do">발전기 정비 교육</a></li>
                            <li><a href="/guide/guide09.do">고마력 선외기 정비 중급 테크니션</a></li>
                            <li><a href="/guide/guide10.do">스턴드라이브 정비 전문가과정</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/apply/schedule.do">교육신청</a>
                        <ul class="dept2">
                            <li><a href="/apply/schedule.do">교육신청</a></li>
                            <li><a href="/apply/faq.do">FAQ</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/board/notice_list.do">자료실</a>
                        <ul class="dept2">
                            <li><a href="/board/notice_list.do">공지사항</a></li>
                            <li><a href="/board/press_list.do">보도자료</a></li>
                            <li><a href="/board/gallery.do">사진자료</a></li>
                            <li><a href="/board/media.do">영상자료</a></li>
                            <li><a href="/board/news_list.do">뉴스레터</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/job/state01.do">취업/창업</a>
                        <ul class="dept2">
                            <!--<li><a href="/job/announcement_list.do">채용공고</a></li>-->
                            <li><a href="/job/state01.do">취창업현황</a></li>
                            <li><a href="/job/review.do">취업성공후기</a></li>
                            <li><a href="/job/community_list.do">커뮤니티</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //header_bottom -->
    <div class="aside_bg"></div>
</div>
<!-- //header -->