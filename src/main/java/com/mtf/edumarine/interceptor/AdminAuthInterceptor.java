package com.mtf.edumarine.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 관리자 영역(/mng/** 및 관리자 전용 API) 서버 측 로그인 검사.
 *
 * 기존에는 JSP 의 스크립트 리다이렉트만 있어 JSON API·엑셀 다운로드가 로그인 없이 응답됐다.
 * 관리자 세션 판정: status=logon 이고 (gbn 이 있거나 adminYn=Y). 회원 로그인 세션에는 gbn 이 없다.
 *
 * 2026-10-30 보안 점검 조치(C-1)로 추가. 로그인 방식·화면은 바꾸지 않는다.
 */
public class AdminAuthInterceptor implements HandlerInterceptor {

    public static final String LOGIN_PAGE = "/mng/index.do";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isAdminSession(request.getSession(false))) {
            return true;
        }
        AuthResponses.reject(request, response, LOGIN_PAGE);
        return false;
    }

    public static boolean isAdminSession(HttpSession session) {
        if (session == null) {
            return false;
        }
        Object status = session.getAttribute("status");
        if (!"logon".equals(status)) {
            return false;
        }
        return session.getAttribute("gbn") != null || "Y".equals(session.getAttribute("adminYn"));
    }
}
