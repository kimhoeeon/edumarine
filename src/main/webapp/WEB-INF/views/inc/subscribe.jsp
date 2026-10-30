<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  뉴스레터 구독 섹션 (공통)
  - 메인(main.jsp) 과 뉴스레터 목록(board/news_list.jsp) 에서 c:import 로 사용
  - 동작: /js/main.js 의 main_newsletter_subscriber_btn, 스타일: /css/style.css .subscribe_wrap
  - 2026-10-30 발주사 요청으로 뉴스레터 목록 최하단에도 동일 노출하면서 공통 파일로 분리
--%>
<!-- subscribe_wrap -->
<div class="subscribe_wrap">
    <div class="inner">
        <div class="title">
            <span>경기해양레저인력양성센터</span>의 다양한 소식을<br>
            뉴스레터로 받아보세요.
        </div>
        <div class="subscribe_bar">
            <input type="email" id="subscriber_email" class="onlyNumEng" placeholder="e-mail">
            <a href="javascript:void(0);" onclick="main_newsletter_subscriber_btn(this);" class="btn">구독하기</a>
        </div>
    </div>
</div>
<!-- //subscribe_wrap -->
