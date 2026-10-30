package com.mtf.edumarine.util;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 메모리 기반 단순 횟수 제한기 (단일 인스턴스 운영 전제).
 * - 로그인 실패 횟수 제한
 * - 공개 SMS 알림 API 호출 횟수 제한
 *
 * 창(window) 안에서 max 회를 넘으면 창이 끝날 때까지 거부한다. 재시작하면 초기화된다.
 * 2026-10-30 보안 점검 조치로 추가.
 */
public final class SimpleRateLimiter {

    private static final class Bucket {
        long windowStart;
        int count;
    }

    private static final Map<String, Bucket> BUCKETS = new ConcurrentHashMap<>();
    private static final int CLEANUP_THRESHOLD = 20000;

    private SimpleRateLimiter() {
    }

    /**
     * 호출을 1회 기록하고 허용 여부를 돌려준다.
     *
     * @param key      구분 키 (예: "sms:1:V0000001", "login:member:abc")
     * @param max      창 안에서 허용되는 최대 횟수
     * @param windowMs 창 길이(밀리초)
     * @return 허용이면 true, 한도 초과면 false
     */
    public static boolean tryAcquire(String key, int max, long windowMs) {
        if (key == null) {
            return true;
        }
        long now = System.currentTimeMillis();
        cleanupIfNeeded(now, windowMs);
        Bucket b = BUCKETS.computeIfAbsent(key, k -> new Bucket());
        synchronized (b) {
            if (now - b.windowStart > windowMs) {
                b.windowStart = now;
                b.count = 0;
            }
            b.count++;
            return b.count <= max;
        }
    }

    /**
     * 실패를 1회 기록한다 (로그인 실패 등). tryAcquire 와 같은 창 규칙을 쓴다.
     */
    public static void recordFailure(String key, long windowMs) {
        if (key == null) {
            return;
        }
        long now = System.currentTimeMillis();
        cleanupIfNeeded(now, windowMs);
        Bucket b = BUCKETS.computeIfAbsent(key, k -> new Bucket());
        synchronized (b) {
            if (now - b.windowStart > windowMs) {
                b.windowStart = now;
                b.count = 0;
            }
            b.count++;
        }
    }

    /**
     * 창 안의 실패 횟수가 max 이상이면 true (차단 상태).
     */
    public static boolean isBlocked(String key, int max, long windowMs) {
        if (key == null) {
            return false;
        }
        Bucket b = BUCKETS.get(key);
        if (b == null) {
            return false;
        }
        long now = System.currentTimeMillis();
        synchronized (b) {
            if (now - b.windowStart > windowMs) {
                return false;
            }
            return b.count >= max;
        }
    }

    /**
     * 키의 기록을 지운다 (로그인 성공 시 등).
     */
    public static void reset(String key) {
        if (key != null) {
            BUCKETS.remove(key);
        }
    }

    private static void cleanupIfNeeded(long now, long windowMs) {
        if (BUCKETS.size() < CLEANUP_THRESHOLD) {
            return;
        }
        Iterator<Map.Entry<String, Bucket>> it = BUCKETS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Bucket> e = it.next();
            Bucket b = e.getValue();
            synchronized (b) {
                if (now - b.windowStart > windowMs) {
                    it.remove();
                }
            }
        }
    }
}
