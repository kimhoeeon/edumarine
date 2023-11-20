/* Copyright(C)2018 NSMALL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited by law.
 * This file is proprietary and confidential.
 *
 * Written by EDSK project team
 */
package com.mtf.edumarine.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * 공통 String Util
 * org.apache.commons.lang.StringUtils 상속후 필요 메소드 추가
 * 자세한 기타 자세한 스펙은 org.apache.commons.lang.StringUtils 참조
 */
public class StringUtil {

    public static final String EMPTY = "";

    /**
     * 문자열 좌측의 공백을 제거하는 메소드
     *
     * @param str 대상 문자열
     * @return trimed string with white space removed from the front.
     */
    public static String ltrim(String str) {
        int len = str.length();
        int idx = 0;
        while ((idx < len) && (str.charAt(idx) <= ' ')) {
            idx++;
        }
        return str.substring(idx, len);
    }

    /**
     * 문자열 우측의 공백을 제거하는 메소드
     *
     * @param str 대상 문자열
     * @return trimed string with white space removed from the end.
     */
    public static String rtrim(String str) {
        int len = str.length();
        while ((0 < len) && (str.charAt(len - 1) <= ' ')) {
            len--;
        }
        return str.substring(0, len);
    }

    public static String defaultString(final Object str) {
        return str == null ? EMPTY : str.toString();
    }

    public static String defaultString(final Object str, final String defaultStr) {
        return str == null ? defaultStr : str.toString();
    }

    /**
     * String을 int형으로
     *
     * @param value
     * @return
     */
    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    /**
     * Object를 int형으로
     * Object가 null이면 defaultValue return
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static int parseInt(String value, int defaultValue) {
        String valueStr = defaultString(value, String.valueOf(defaultValue));
        return Integer.parseInt(valueStr);
    }

    /**
     * String 앞 또는 뒤를 특정문자로 지정한 길이만큼 채워주는 함수    <BR>
     * (예) pad("1234","0", 6, 1) --> "123400"   <BR>
     *
     * @param src    Source string
     * @param pad    pad string
     * @param totLen total length
     * @param mode   앞/뒤 구분 (-1:front, 1:back)
     * @return String
     */
    public static String pad(String src, String pad, int totLen, int mode) {
        String paddedString = "";
        if (src == null)
            return "";
        int srcLen = src.length();
        if ((totLen < 1) || (srcLen >= totLen))
            return src;
        for (int i = 0; i < (totLen - srcLen); i++) {
            paddedString += pad;
        }
        if (mode == -1)
            paddedString += src; // front padding
        else
            paddedString = src + paddedString; //back padding
        return paddedString;
    }

    /**
     * str을 원하는 byte만큼 잘라 리턴한다.
     *
     * @param str
     * @param byteLength
     * @param sizePerLetter (UTF-8 : 3 , EUC-KR : 2)
     * @return string cut
     */
    public static String subStringBytes(String str, int byteLength, int sizePerLetter) {
        int retLength = 0;
        int tempSize = 0;
        int asc;
        if (str == null || "".equals(str) || "null".equals(str)) {
            str = "";
        }

        int length = str.length();

        for (int i = 1; i <= length; i++) {
            asc = (int) str.charAt(i - 1);
            if (asc > 127) {
                if (byteLength >= tempSize + sizePerLetter) {
                    tempSize += sizePerLetter;
                    retLength++;
                }
            } else {
                if (byteLength > tempSize) {
                    tempSize++;
                    retLength++;
                }
            }
        }

        return str.substring(0, retLength);
    }

    public static String removeCRLF(String parameter) {
        return parameter.replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * String 형태의 날짜의 포맷을 변경 필요한 부분은 추가.
     *
     * @param date yyyy-MM-dd 날짜
     * @param type 변환할타입 dd-MMM-yyyy
     * @return String changeDate
     */
    public static String dateFormatChange(String date, String type) {

        String changeDate = "";

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        if ("01".equals(type)) {
            try {
                changeDate = sdf.format(formatter.parse(date));
            } catch (ParseException e) {
                changeDate = date;
            }
        } else if ("02".equals(type)) {
            try {
                changeDate = sdf.format(formatter.parse(date)) + " 00:00:00";
            } catch (ParseException e) {
                changeDate = date;
            }
        } else if ("03".equals(type)) {
            try {
                changeDate = sdf.format(formatter.parse(date)) + " 23:59:59";
            } catch (ParseException e) {
                changeDate = date;
            }
        } else {
            changeDate = date;
        }

        return changeDate;
    }

    public static String cutKorean(String inputString, int koreanByte, int maxByte) {
        byte[] inputByte = inputString.getBytes();
        int cutByte = 0;
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (isIncludeKorean(inputString.substring(i, i + 1))) {
                if (cutByte + koreanByte > maxByte) {
                    break;
                }
                cutByte += koreanByte;
            } else {
                if (cutByte + 1 > maxByte) {
                    break;
                }
                cutByte += 1;
            }
        }
        return new String(inputByte, 0, cutByte);
    }

    private static boolean isIncludeKorean(String input) {
        boolean returnFlag = false;
        for (int i = 0; i < input.length(); i++) {
            if (Character.getType(input.charAt(i)) == Character.OTHER_LETTER) {
                returnFlag = true;
            }
        }
        return returnFlag;
    }

    public static String nvl(Object oValue) {

        String stNew = "";
        if (oValue != null) {
            stNew = nvl(oValue.toString());
        }
        return stNew;
    }

    public static String nvl(Object oValue, String stNew) {

        if (oValue != null) {
            String str = nvl(oValue.toString());
            stNew = nvl(str, stNew);
        }
        return stNew;
    }

    public static String nvl(String stValue) {

        String stNew = nvl(stValue, "");
        return stNew;
    }

    public static String nvl(String stValue, String stNew) {

        String stResult = null;

        if (isEmpty(stValue) == true) {
            stResult = stNew;
        } else {
            stResult = stValue.trim();
        }

        return stResult;
    }

    /**
     * NULL값을 공백값으로 치환
     *
     * @param lvalue
     * @return String
     */
    public static String defaultString(Long lvalue) {

        return defaultString(lvalue, EMPTY);
    }

    /**
     * NULL값을 default 문자열로 치환
     *
     * @param lvalue
     * @param defaultString
     * @return
     */
    public static String defaultString(Long lvalue, String defaultString) {

        if (lvalue == null) {
            return defaultString;
        } else {
            return String.valueOf(lvalue);
        }
    }

    public static boolean equalsOr(String org, String... compares) {
        return equalsOr(org, Arrays.asList(compares));
    }

    /**
     * 같은 문자열이 있는지 체크
     *
     * @param org(기준문자열)
     * @return
     */
    public static boolean equalsOr(String org, List<String> commpares) {
        if (org == null) {
            return false;
        }
        if (commpares == null) {
            return false;
        }

        for (String compare : commpares) {
            if (compare == null) {
                continue;
            }

            if (org.equals(compare)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     * @since 2.0
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Empty checks
    //-----------------------------------------------------------------------

    /**
     * <p>Checks if a CharSequence is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the CharSequence.
     * That functionality is available in isBlank().</p>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

}