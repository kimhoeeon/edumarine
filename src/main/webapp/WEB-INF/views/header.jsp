<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- header -->
<div id="header">
    <!-- header_top -->
    <div class="header_top">
        <div class="inner">
            <a href="/member/login.do">로그인</a>
            <a href="/member/join.do">회원가입</a>
            <a href="/sitemap.do" class="sitemap"><img src="<%request.getContextPath();%>/static/img/icon_menu_white.png" alt="메뉴 아이콘"></a>
        </div>
    </div>
    <!-- //header_top -->
    <!-- header_bottom -->
    <div class="header_bot">
        <div class="inner">
            <h1><a href="/" class="logo"><img src="<%request.getContextPath();%>/static/img/logo.png" alt="로고"></a></h1>
            <a href="#a" class="m_menu">
                <span>메뉴</span>
            </a>
            <div class="nav">
                <div class="mobile_top">
                    <a href="/member/login.do">로그인</a>
                    <a href="/member/join.do">회원가입</a>
                </div>
                <ul class="dept1">
                    <li>
                        <a href="">센터소개</a>
                        <ul class="dept2">
                            <li><a href="">EDU marine 소개</a></li>
                            <li><a href="">사업개요</a></li>
                            <li><a href="">경기도 해양레저 현황</a></li>
                            <li><a href="">해양레저 인력양성의 필요성</a></li>
                            <li><a href="">협력 및 후원기관</a></li>
                            <li><a href="">찾아오시는 길</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="">교육안내</a>
                        <ul class="dept2">
                            <li><a href="">전체 교육과정 소개</a></li>
                            <li><a href="">해상엔진테크니션 (선내기·선외기)</a></li>
                            <li><a href="">마리나선박 정비사 실기교육</a></li>
                            <li><a href="">FRP 레저보트 선체 정비 테크니션</a></li>
                            <li><a href="">위탁교육</a></li>
                            <li><a href="">해상엔진 자가정비 (선외기)</a></li>
                            <li><a href="">해상엔진 자가정비 (선내기)</a></li>
                            <li><a href="">해상엔진 자가정비 (세일요트)</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="">교육신청</a>
                        <ul class="dept2">
                            <li><a href="">교육일정</a></li>
                            <li><a href="/apply/schedule.do">교육신청</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/board/notice_list.do">자료실</a>
                        <ul class="dept2">
                            <li><a href="/board/notice_list.do">공지사항</a></li>
                            <li><a href="/board/press_list.do">보도자료</a></li>
                            <li><a href="/board/gallery.do">사진자료</a></li>
                            <li><a href="/board/gallery.do">영상자료</a></li>
                            <li><a href="/board/news_list.do">뉴스레터</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/job/announcement_list.do">취업/창업</a>
                        <ul class="dept2">
                            <li><a href="/job/announcement_list.do">채용공고</a></li>
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