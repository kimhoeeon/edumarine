package com.mtf.edumarine.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 결제 복귀(merchantData / P_NOTI) 서명 유틸.
 *
 * 결제 요청 시 서버가 "신청번호|회원ID|발급시각" 에 HMAC 서명을 붙여 이니시스에 넘기고,
 * 결제 복귀 시 세션이 끊겨 있으면 서명이 맞는 경우에만 세션을 복구한다.
 * 키는 기동 시 무작위 생성(메모리)이라 재기동 전후에 걸친 결제는 세션 복구만 생략된다(결제 처리는 그대로).
 *
 * 2026-10-30 보안 점검 조치(C-4)로 추가.
 */
public final class SignUtil {

    private static final byte[] KEY = newKey();
    private static final long MAX_AGE_MS = 6L * 60 * 60 * 1000; // 6시간

    private SignUtil() {
    }

    private static byte[] newKey() {
        byte[] k = new byte[32];
        new SecureRandom().nextBytes(k);
        return k;
    }

    /** 현재 시각(ms) 문자열 */
    public static String now() {
        return String.valueOf(System.currentTimeMillis());
    }

    /** 결제 복귀용 서명 생성 */
    public static String signMerchant(String tableSeq, String userId, String ts) {
        return hmacHex(nvl(tableSeq) + "|" + nvl(userId) + "|" + nvl(ts));
    }

    /** 결제 복귀용 서명 검증 (유효 시간 포함) */
    public static boolean verifyMerchant(String tableSeq, String userId, String ts, String sign) {
        if (userId == null || userId.trim().isEmpty() || ts == null || sign == null) {
            return false;
        }
        long issued;
        try {
            issued = Long.parseLong(ts.trim());
        } catch (NumberFormatException e) {
            return false;
        }
        long age = System.currentTimeMillis() - issued;
        if (age < 0 || age > MAX_AGE_MS) {
            return false;
        }
        String expected = signMerchant(tableSeq, userId, ts.trim());
        return MessageDigest.isEqual(expected.getBytes(StandardCharsets.US_ASCII),
                sign.trim().toLowerCase().getBytes(StandardCharsets.US_ASCII));
    }

    /** 서명 토큰처럼 보이는 문자열인지 (64자리 16진수) */
    public static boolean looksLikeSign(String s) {
        return s != null && s.trim().matches("[0-9a-fA-F]{64}");
    }

    /** 발급시각 토큰처럼 보이는 문자열인지 (13자리 숫자) */
    public static boolean looksLikeTs(String s) {
        return s != null && s.trim().matches("[0-9]{13}");
    }

    private static String hmacHex(String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(KEY, "HmacSHA256"));
            byte[] out = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(out.length * 2);
            for (byte b : out) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalStateException("HMAC not available", e);
        }
    }

    private static String nvl(String s) {
        return s == null ? "" : s.trim();
    }
}
