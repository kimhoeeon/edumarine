package com.mtf.edumarine.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 이니시스 STDPay 서명(signature / verification / mKey) 계산.
 *
 * 2026-10-30: 샘플 라이브러리(INIpaySample_v1.3.jar)의 SignatureUtil.makeSignature 가 서명 대상 문자열
 * (결제 signKey 포함)을 System.out 으로 출력해 서버 로그에 키가 남던 것을 막기 위해 같은 계산을 출력 없이 구현했다.
 * 알고리즘은 바이트코드로 확인한 원본과 같다: 키 알파벳순 정렬(TreeMap) → "키=값" 을 "&" 로 연결(키·값 trim)
 * → SHA-256 → 소문자 hex. 라이브러리 결과와의 동일성은 비교 테스트로 확인했다(docs/QA_SWEEP_2026-10-30.md).
 */
public final class InicisSignUtil {

    private InicisSignUtil() {
    }

    /** 원본 SignatureUtil.makeSignature 와 동일 (빈 맵이면 RuntimeException) */
    public static String makeSignature(Map<String, String> signParam) {
        if (signParam == null || signParam.isEmpty()) {
            throw new RuntimeException("서명 대상 파라미터가 비어 있습니다.");
        }
        Map<String, String> sorted = new TreeMap<>(signParam);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> e : sorted.entrySet()) {
            if (!first) {
                sb.append('&');
            }
            first = false;
            sb.append(e.getKey().trim()).append('=').append(e.getValue().trim());
        }
        return sha256Hex(sb.toString());
    }

    /** 원본 SignatureUtil.hash(data, "SHA-256") 와 동일: UTF-8 바이트의 SHA-256 을 소문자 hex 로 */
    public static String sha256Hex(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 을 사용할 수 없습니다.", e);
        }
    }
}
