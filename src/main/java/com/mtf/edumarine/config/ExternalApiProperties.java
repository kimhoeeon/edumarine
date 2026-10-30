package com.mtf.edumarine.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 외부 서비스 자격증명 (이니시스·DirectSend·알리고).
 *
 * 2026-10-30 보안 조치(2단계): 소스 코드에 글자로 박혀 있던 키를 설정 파일(application.properties, git 제외)로 옮겼다.
 * 설정 항목이 없으면 빈 문자열로 뜨고 기동 로그에 경고만 남긴다(기동 실패 없음). 값은 로그에 남기지 않는다.
 *
 *  inicis.mid           이니시스 상점 ID (PC·모바일 결제 요청, 취소 API 공통)
 *  inicis.sign-key      이니시스 웹 결제 signKey
 *  inicis.cancel-key    이니시스 취소 API(INIAPI) key
 *  inicis.cancel-iv     이니시스 취소 API AES iv
 *  directsend.username  DirectSend 메일 발송 계정
 *  directsend.key       DirectSend API key
 *  aligo.user-id        알리고 SMS 계정
 *  aligo.key            알리고 SMS 인증키
 */
@Slf4j
@Getter
@Component
public class ExternalApiProperties {

    @Value("${inicis.mid:}")
    private String inicisMid;

    @Value("${inicis.sign-key:}")
    private String inicisSignKey;

    @Value("${inicis.cancel-key:}")
    private String inicisCancelKey;

    @Value("${inicis.cancel-iv:}")
    private String inicisCancelIv;

    @Value("${directsend.username:}")
    private String directsendUsername;

    @Value("${directsend.key:}")
    private String directsendKey;

    @Value("${aligo.user-id:}")
    private String aligoUserId;

    @Value("${aligo.key:}")
    private String aligoKey;

    @PostConstruct
    void checkConfigured() {
        List<String> missing = new ArrayList<>();
        if (isBlank(inicisMid)) missing.add("inicis.mid");
        if (isBlank(inicisSignKey)) missing.add("inicis.sign-key");
        if (isBlank(inicisCancelKey)) missing.add("inicis.cancel-key");
        if (isBlank(inicisCancelIv)) missing.add("inicis.cancel-iv");
        if (isBlank(directsendUsername)) missing.add("directsend.username");
        if (isBlank(directsendKey)) missing.add("directsend.key");
        if (isBlank(aligoUserId)) missing.add("aligo.user-id");
        if (isBlank(aligoKey)) missing.add("aligo.key");
        if (missing.isEmpty()) {
            log.info("[external api config] 외부 서비스 설정 8개 항목 모두 설정됨");
        } else {
            log.warn("[external api config] 설정 파일에 값이 없는 항목: {} (결제·메일·문자 기능이 동작하지 않음)", missing);
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
