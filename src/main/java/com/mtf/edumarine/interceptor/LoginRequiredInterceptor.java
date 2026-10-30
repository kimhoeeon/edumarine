package com.mtf.edumarine.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 회원 또는 관리자 로그인이 있어야 하는 API 검사 (파일 업로드·삭제, 회원 조회 등).
 * 2026-10-30 보안 점검 조치로 추가.
 */
public class LoginRequiredInterceptor implements HandlerInterceptor {

    public static final String LOGIN_PAGE = "/member/login.do";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isLoggedIn(request.getSession(false))) {
            return true;
        }
        AuthResponses.reject(request, response, LOGIN_PAGE);
        return false;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session != null && "logon".equals(session.getAttribute("status")) && session.getAttribute("id") != null;
    }
}
