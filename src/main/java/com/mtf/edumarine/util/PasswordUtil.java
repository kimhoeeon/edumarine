package com.mtf.edumarine.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 관리자 비밀번호 해시 유틸 (JDK 내장 PBKDF2-HMAC-SHA256, 추가 라이브러리 없음).
 *
 * 저장 형식: $pbkdf2$<반복횟수>$<salt base64>$<hash base64>
 * - 기존 DB 의 평문 비밀번호는 로그인 성공 시 자동으로 이 형식으로 바뀐다 (관리자 재설정 불필요).
 * - matches() 는 저장값이 해시 형식이 아니면 기존 방식(평문 비교)으로 동작해 로그인이 끊기지 않는다.
 *
 * 2026-10-30 보안 점검 조치(C-7)로 추가.
 */
public final class PasswordUtil {

    private static final String PREFIX = "$pbkdf2$";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH_BITS = 256;
    private static final int SALT_BYTES = 16;
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordUtil() {
    }

    /** 저장값이 이 유틸의 해시 형식인지 */
    public static boolean isHashed(String stored) {
        return stored != null && stored.startsWith(PREFIX);
    }

    /** 평문을 해시 문자열로 */
    public static String hash(String raw) {
        if (raw == null) {
            raw = "";
        }
        byte[] salt = new byte[SALT_BYTES];
        RANDOM.nextBytes(salt);
        byte[] dk = pbkdf2(raw.toCharArray(), salt, ITERATIONS);
        return PREFIX + ITERATIONS + "$"
                + Base64.getEncoder().encodeToString(salt) + "$"
                + Base64.getEncoder().encodeToString(dk);
    }

    /**
     * 입력 평문이 저장값과 일치하는지.
     * 저장값이 해시 형식이면 PBKDF2 검증, 아니면 기존처럼 평문 동일 비교(이관 전 계정).
     */
    public static boolean matches(String raw, String stored) {
        if (raw == null || stored == null) {
            return false;
        }
        if (!isHashed(stored)) {
            return constantTimeEquals(raw.getBytes(), stored.getBytes());
        }
        try {
            String[] parts = stored.substring(PREFIX.length()).split("\\$");
            if (parts.length != 3) {
                return false;
            }
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expected = Base64.getDecoder().decode(parts[2]);
            byte[] actual = pbkdf2(raw.toCharArray(), salt, iterations);
            return constantTimeEquals(expected, actual);
        } catch (Exception e) {
            return false;
        }
    }

    /** 임시 비밀번호 생성 (영문 대소문자+숫자+특수문자, 혼동 글자 제외) */
    public static String generateTempPassword(int length) {
        final String letters = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
        final String digits = "23456789";
        final String specials = "!@#$%^*";
        final String all = letters + digits + specials;
        if (length < 8) {
            length = 8;
        }
        StringBuilder sb = new StringBuilder(length);
        // 각 종류 최소 1자 보장
        sb.append(letters.charAt(RANDOM.nextInt(letters.length())));
        sb.append(digits.charAt(RANDOM.nextInt(digits.length())));
        sb.append(specials.charAt(RANDOM.nextInt(specials.length())));
        while (sb.length() < length) {
            sb.append(all.charAt(RANDOM.nextInt(all.length())));
        }
        // 섞기
        char[] arr = sb.toString().toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
        return new String(arr);
    }

    private static byte[] pbkdf2(char[] raw, byte[] salt, int iterations) {
        try {
            PBEKeySpec spec = new PBEKeySpec(raw, salt, iterations, KEY_LENGTH_BITS);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return f.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("PBKDF2 not available", e);
        }
    }

    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        return MessageDigest.isEqual(a, b);
    }
}
