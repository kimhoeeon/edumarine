package com.mtf.edumarine.config;

import com.mtf.edumarine.interceptor.AdminAuthInterceptor;
import com.mtf.edumarine.interceptor.LoginRequiredInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 서버 측 로그인 검사 인터셉터 등록.
 *
 * - 관리자 세션 필요: /mng/** (로그인 화면·로그인 처리·로그아웃 제외) 와 관리자 전용 API
 * - 회원 또는 관리자 세션 필요: 파일 업로드/삭제, 경력·자격 삭제
 *
 * 공개 페이지·결제 복귀(/mypage/eduApplyInfo.do)·회원가입 직후 SMS 알림(/sms/send/notify/*)·
 * 상시신청 사전정보 조회(/member/seq/selectSingle.do)는 기존 흐름(비로그인 호출)을 지키기 위해
 * 여기서 막지 않고 각 핸들러에서 검증·축소한다.
 *
 * 2026-10-30 보안 점검 조치(C-1, H-2, H-3)로 추가.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AdminAuthInterceptor())
                .addPathPatterns(
                        "/mng/**",
                        "/file/upload/selectList.do",
                        "/train/selectNextTime.do",
                        "/train/active.do",
                        "/mail/send.do",
                        "/sms/send.do",
                        "/sms/send/certNum.do",
                        "/sms/send/notify/train.do",
                        "/member/grade/update.do"
                )
                .excludePathPatterns(
                        "/mng/index.do",
                        "/mng/login.do",
                        "/mng/logoutCheck.do",
                        "/mng/session/kill/gbn.do"
                );

        registry.addInterceptor(new LoginRequiredInterceptor())
                .addPathPatterns(
                        "/file/upload.do",
                        "/file/upload/save.do",
                        "/file/upload/update.do",
                        "/file/delete.do",
                        "/mypage/eduApply02/career/delete.do",
                        "/mypage/eduApply02/license/delete.do"
                );
    }
}
