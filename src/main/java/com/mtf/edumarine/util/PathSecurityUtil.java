package com.mtf.edumarine.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 파일 업로드/다운로드 경로 보호 유틸.
 * - 업로드 루트 밖으로 벗어나는 경로(../ 등) 차단
 * - 서버에서 실행되거나 브라우저에서 스크립트로 해석될 수 있는 확장자 차단
 *
 * 2026-10-30 보안 점검 조치(C-2, C-3)로 추가. 기존 업로드 경로·파일명 규칙은 그대로 둔다.
 */
public final class PathSecurityUtil {

    /** 운영 서버 업로드 루트 (기존 코드에 하드코딩된 값과 동일) */
    public static final String UPLOAD_ROOT = "/usr/local/tomcat/webapps/upload";

    /** 메일 이미지 저장 경로 (gbn=mail 업로드 전용, 기존 코드와 동일) */
    public static final String MAIL_IMG_ROOT = "/usr/local/tomcat/webapps/ROOT/WEB-INF/classes/static/img/mail";

    /** 저장을 허용하지 않는 확장자 (서버 실행 파일, 스크립트, HTML 계열) */
    private static final Set<String> BLOCKED_EXT = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            "jsp", "jspx", "jspf", "jsw", "jsv", "jar", "war", "ear", "class",
            "sh", "bash", "exe", "bat", "cmd", "com", "scr", "msi", "dll", "ps1",
            "php", "php3", "php4", "php5", "phtml", "phar",
            "asp", "aspx", "asa", "asax", "cer", "cgi", "pl", "py", "rb",
            "vbs", "vbe", "js", "jse", "wsf", "wsh", "hta",
            "html", "htm", "xhtml", "shtml", "svg", "mht", "mhtml"
    )));

    private PathSecurityUtil() {
    }

    /**
     * 업로드 하위 폴더명(gbn 파라미터 등)으로 쓸 수 있는 값인지 검사.
     * 영문/숫자/_/-/ 와 중간 슬래시만 허용하고 상위 이동(..)·절대 경로·백슬래시는 거부한다.
     */
    public static boolean isSafeSegment(String segment) {
        if (segment == null || segment.trim().isEmpty()) {
            return false;
        }
        String s = segment.trim();
        if (s.startsWith("/") || s.contains("..") || s.contains("\\") || s.indexOf('\0') >= 0) {
            return false;
        }
        return s.matches("[A-Za-z0-9_\\-]+(/[A-Za-z0-9_\\-]+)*/?");
    }

    /**
     * 파일명(경로 없이)으로 쓸 수 있는 값인지 검사. 경로 구분자·널문자·차단 확장자를 거부한다.
     */
    public static boolean isSafeFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        String name = fileName.trim();
        if (name.contains("/") || name.contains("\\") || name.contains("..") || name.indexOf('\0') >= 0) {
            return false;
        }
        return !isBlockedExtension(name);
    }

    /**
     * 차단 확장자 여부. 마지막 점 뒤의 확장자만 본다(Tomcat 의 *.jsp 매핑 기준).
     */
    public static boolean isBlockedExtension(String fileName) {
        if (fileName == null) {
            return false;
        }
        String name = fileName.trim();
        int dot = name.lastIndexOf('.');
        if (dot < 0 || dot == name.length() - 1) {
            return false;
        }
        String ext = name.substring(dot + 1).trim().toLowerCase(Locale.ROOT);
        return BLOCKED_EXT.contains(ext);
    }

    /**
     * file 이 root 디렉터리 안(하위 포함)에 있는지 정규화된 경로로 검사한다.
     */
    public static boolean isUnder(File file, File root) {
        if (file == null || root == null) {
            return false;
        }
        try {
            String rootPath = root.getCanonicalPath();
            String filePath = file.getCanonicalPath();
            if (filePath.equals(rootPath)) {
                return false; // 루트 자체는 파일이 아님
            }
            if (!rootPath.endsWith(File.separator)) {
                rootPath = rootPath + File.separator;
            }
            return filePath.startsWith(rootPath);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * root 아래의 상대 경로(relative)를 정규화해 반환한다. root 밖이면 null.
     */
    public static File resolveUnder(File root, String relative) {
        if (root == null || relative == null) {
            return null;
        }
        if (relative.indexOf('\0') >= 0) {
            return null;
        }
        File target = new File(root, relative);
        return isUnder(target, root) ? target : null;
    }

    /**
     * 절대 경로 문자열이 허용된 루트들 중 하나의 안에 있으면 그 File 을, 아니면 null 을 반환한다.
     */
    public static File resolveAbsoluteUnderAny(String absolutePath, File... roots) {
        if (absolutePath == null || absolutePath.trim().isEmpty() || absolutePath.indexOf('\0') >= 0) {
            return null;
        }
        File target = new File(absolutePath.trim());
        for (File root : roots) {
            if (isUnder(target, root)) {
                return target;
            }
        }
        return null;
    }
}
