<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="header">

    <!-- header menu -->
    <div class="hd_top">
        <div class="hd_top_inner">
            <h1 class="hd_logo">
                <a href="/">
                    <img src="<%request.getContextPath();%>/static/img/logo.png">
                </a>
            </h1>
            <div class="hd_top_right">
                <a href="/" class="language kor">KOR</a>
                <a href="/eng/index.do" class="language eng">ENG</a>
                <a href="#a" class="side_menu m_menu">
                    <span>메뉴</span>
                </a>
            </div>
        </div>
    </div>

    <div class="hd_nav">
        <div class="hd_nav_inner">
            <div class="nav">
                <ul class="dept1">
                    <li>
                        <a href="#a" class="side_menu pc_menu">
                            <span>메뉴</span>
                        </a>
                    </li>
                    <li>
                        <a href="/introduce/greeting.do"><span>협회소개</span></a>
                        <ul class="dept2">
                            <li><a href="/introduce/greeting.do"><span>인사말</span></a></li>
                            <li><a href="/introduce/history.do"><span>연혁</span></a></li>
                            <li><a href="/introduce/vision.do"><span>비전</span></a></li>
                            <li><a href="/introduce/business.do"><span>주요사업</span></a></li>
                            <li><a href="/introduce/way.do"><span>오시는길</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/member/ascdirectors.do"><span>회원사</span></a>
                        <ul class="dept2">
                            <li><a href="/member/ascdirectors.do"><span>협회이사</span></a></li>
                            <li><a href="/member/adviser.do"><span>고문위원</span></a></li>
                            <li><a href="/member/consultation.do"><span>자문위원</span></a></li>
                            <li><a href="/member/regular.do"><span>정회원</span></a></li>
                            <li><a href="/member/ascmembers.do"><span>준회원</span></a></li>
                            <li><a href="/member/partnership.do"><span>협력기관</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/service/guide.do"><span>회원서비스</span></a>
                        <ul class="dept2">
                            <li><a href="/service/guide.do"><span>가입 안내</span></a></li>
                            <li><a href="/service/benefits.do"><span>회원사 혜택</span></a></li>
                            <li><a href="/service/faq.do"><span>FAQ</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/support/bizanm.do"><span>기업지원</span></a>
                        <ul class="dept2">
                            <li><a href="/support/bizanm.do"><span>사업공고</span></a></li>
                            <li><a href="/support/bidanm.do"><span>입찰공고</span></a></li>
                            <li><a href="/support/issue.do"><span>Issue&Trend</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/community/notice.do"><span>커뮤니티</span></a>
                        <ul class="dept2">
                            <li><a href="/community/notice.do"><span>공지사항</span></a></li>
                            <li><a href="/community/news.do"><span>SIPA-NEWS</span></a></li>
                            <li><a href="/community/event.do"><span>행사 게시판</span></a></li>
                            <li><a href="/community/contact.do"><span>Contact us</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- header menu -->

    <!-- site map & mobile -->
    <div class="hd_site_map">
        <div class="side_top_box">
            <div class="side_top_txt">전체메뉴</div>
            <a href="" class="language kor">KOR</a>
            <a href="" class="language eng">ENG</a>
            <a href="#a" class="side_menu_close">
                <span>닫기</span>
            </a>
        </div>
        <div class="site_map_box">
            <div class="site_map_nav">
                <ul class="dept1">
                    <li>
                        <a href="/introduce/greeting.do"><span>협회소개</span></a>
                        <ul class="dept2">
                            <li><a href="/introduce/greeting.do"><span>인사말</span></a></li>
                            <li><a href="/introduce/history.do"><span>연혁</span></a></li>
                            <li><a href="/introduce/vision.do"><span>비전</span></a></li>
                            <li><a href="/introduce/business.do"><span>주요사업</span></a></li>
                            <li><a href="/introduce/way.do"><span>오시는길</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/member/ascdirectors.do"><span>회원사</span></a>
                        <ul class="dept2">
                            <li><a href="/member/ascdirectors.do"><span>협회이사</span></a></li>
                            <li><a href="/member/adviser.do"><span>고문위원</span></a></li>
                            <li><a href="/member/consultation.do"><span>자문위원</span></a></li>
                            <li><a href="/member/regular.do"><span>정회원</span></a></li>
                            <li><a href="/member/ascmembers.do"><span>준회원</span></a></li>
                            <li><a href="/member/partnership.do"><span>협력기관</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/service/guide.do"><span>회원서비스</span></a>
                        <ul class="dept2">
                            <li><a href="/service/guide.do"><span>가입 안내</span></a></li>
                            <li><a href="/service/benefits.do"><span>회원사 혜택</span></a></li>
                            <li><a href="/service/faq.do"><span>FAQ</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/support/bizanm.do"><span>기업지원</span></a>
                        <ul class="dept2">
                            <li><a href="/support/bizanm.do"><span>사업공고</span></a></li>
                            <li><a href="/support/bidanm.do"><span>입찰공고</span></a></li>
                            <li><a href="/support/issue.do"><span>Issue&Trend</span></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/community/notice.do"><span>커뮤니티</span></a>
                        <ul class="dept2">
                            <li><a href="/community/notice.do"><span>공지사항</span></a></li>
                            <li><a href="/community/news.do"><span>SIPA-NEWS</span></a></li>
                            <li><a href="/community/event.do"><span>행사 게시판</span></a></li>
                            <li><a href="/community/contact.do"><span>Contact us</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //site map & mobile -->
    <div class="side_bg"></div>

</div>