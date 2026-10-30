package com.mtf.edumarine.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

/**
 * 관리자 엑셀 다운로드 공통 조각 (2026-10-30 정리).
 *
 * 19개 엑셀 핸들러가 각자 복제하던 스타일 생성·셀 쓰기·열 너비 조정을 모았다.
 * 생성되는 글꼴(맑은 고딕 9pt)·테두리(얇은 선)·배경색(헤더 CORNFLOWER_BLUE)·정렬(가운데)·줄바꿈은 기존 코드와 같다.
 * 셀 쓰기 helper 는 기존의 createCell → setCellStyle → setCellValue 세 줄과 같은 호출을 한다.
 */
public final class ExcelExportUtil {

    private ExcelExportUtil() {
    }

    /** 헤더 셀 스타일: 맑은 고딕 9pt 굵게, 가운데 정렬, 얇은 테두리, 연파랑(CORNFLOWER_BLUE) 배경, 줄바꿈 */
    public static CellStyle headerStyle(Workbook workbook) {
        return headerStyle(workbook, IndexedColors.CORNFLOWER_BLUE);
    }

    /** 배경색만 다른 헤더 셀 스타일 (기존 headerStyle_light_green / headerStyle_light_orange 와 동일 구성) */
    public static CellStyle headerStyle(Workbook workbook, IndexedColors fillColor) {
        Font fontHeader = workbook.createFont();
        fontHeader.setFontName("맑은 고딕");
        fontHeader.setFontHeight((short) (9 * 20));
        fontHeader.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(fillColor.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(fontHeader);
        headerStyle.setWrapText(true);
        return headerStyle;
    }

    /** 본문 셀 스타일: 맑은 고딕 9pt, 가운데 정렬, 얇은 테두리, 줄바꿈 */
    public static CellStyle bodyStyle(Workbook workbook) {
        Font font9 = workbook.createFont();
        font9.setFontName("맑은 고딕");
        font9.setFontHeight((short) (9 * 20));

        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setFont(font9);
        bodyStyle.setWrapText(true);
        return bodyStyle;
    }

    /** 문자열 셀: createCell → setCellStyle → setCellValue(String) */
    public static SXSSFCell cell(SXSSFRow row, int index, CellStyle style, String value) {
        SXSSFCell cell = row.createCell(index);
        cell.setCellStyle(style);
        cell.setCellValue(value);
        return cell;
    }

    /** 숫자 셀: createCell → setCellStyle → setCellValue(double) (int·long 인자는 기존처럼 double 로 넓혀 저장) */
    public static SXSSFCell cell(SXSSFRow row, int index, CellStyle style, double value) {
        SXSSFCell cell = row.createCell(index);
        cell.setCellStyle(style);
        cell.setCellValue(value);
        return cell;
    }

    /** 열 너비를 현재 너비 + 1024 (최대 255*256) 로 다시 설정 — 기존 "너비를 자동으로 다시 설정" 루프와 동일 */
    public static void autoWidth(SXSSFSheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.trackColumnForAutoSizing(i);
            sheet.setColumnWidth(i, Math.min(255 * 256, sheet.getColumnWidth(i) + 1024));
        }
    }
}
