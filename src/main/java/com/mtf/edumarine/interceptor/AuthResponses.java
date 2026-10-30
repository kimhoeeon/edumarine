package com.mtf.edumarine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증 실패 응답 공통 처리.
 * - 화면 요청(GET + Accept: text/html): 로그인 페이지로 리다이렉트 (기존 JSP 스크립트 리다이렉트와 같은 결과)
 * - API 요청(JSON/AJAX): 401 + ResponseDTO 형식 JSON
 */
final class AuthResponses {

    private AuthResponses() {
    }

    static void reject(HttpServletRequest request, HttpServletResponse response, String loginPage) throws IOException {
        String accept = request.getHeader("Accept");
        boolean pageRequest = "GET".equalsIgnoreCase(request.getMethod())
                && accept != null && accept.contains("text/html")
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
