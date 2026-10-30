package com.mtf.edumarine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증 실패 응답 공통 처리.
 * - 화면 요청(Accept: text/html, GET/POST 모두): 로그인 페이지로 리다이렉트 (기존 JSP 스크립트 리다이렉트와 같은 결과)
 * - API 요청(JSON/AJAX): 401 + ResponseDTO 형식 JSON
 */
final class AuthResponses {

    private AuthResponses() {
    }

    static void reject(HttpServletRequest request, HttpServletResponse response, String loginPage) throws IOException {
        // 화면 요청 판정은 메서드가 아니라 Accept 헤더로 한다. 관리자 메인은 로그인 폼 전송(POST)으로 들어오므로
        // 세션이 사라진 뒤 새로고침(POST 재전송)해도 JSON 이 아니라 로그인 페이지로 보내야 한다 (2026-10-30 배포 확인 반영).
        // AJAX(jQuery JSON, fetch, sendBeacon)는 Accept 가 */* 또는 application/json 이라 401 JSON 을 받는다.
        String accept = request.getHeader("Accept");
        boolean pageRequest = accept != null && accept.contains("text/html")
                && request.getHeader("X-Requested-With") == null;

        if (pageRequest) {
            response.sendRedirect(request.getContextPath() + loginPage);
            return;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        response.getWriter().write("{\"resultCode\":\"-9\",\"resultMessage\":\"로그인이 필요합니다.\",\"resultMsg\":\"로그인이 필요합니다.\"}");
        response.getWriter().flush();
    }
}
