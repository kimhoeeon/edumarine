<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="form_box">
    <div class="form_title">
        <h4>신청 정보</h4>
    </div>
    <div class="form_content">
        <dl>
            <dt>상의 사이즈(남여공용)<span class="required">*</span></dt>
            <dd>
                <select name="topClothesSize" id="topClothesSize" title="상의 사이즈(남여공용)">
                    <option value="">선택</option>
                </select>
            </dd>
        </dl>
        <dl>
            <dt>하의 사이즈(남여공용)<span class="required">*</span></dt>
            <dd>
                <input type="text" name="bottomClothesSize" id="bottomClothesSize" placeholder="숫자만 입력 (예: 30)" style="width: 100%;">
            </dd>
        </dl>
        <dl>
            <dt>안전화 사이즈(mm)<span class="required">*</span></dt>
            <dd>
                <input type="text" name="shoesSize" id="shoesSize" placeholder="숫자만 입력 (예: 260)" style="width: 100%;">
            </dd>
        </dl>
        <dl>
            <dt>참여경로<span class="required">*</span></dt>
            <dd>
                <select name="participationPath" id="participationPath" title="참여경로">
                </select>
            </dd>
        </dl>

        <dl>
            <dt>졸업구분<span class="required">*</span></dt>
            <dd>
                <select name="gradeGbn" id="gradeGbn" title="졸업구분">
                </select>
            </dd>
        </dl>
        <dl>
            <dt>학교명<span class="required">*</span></dt>
            <dd>
                <input type="text" name="schoolName" id="schoolName" placeholder="학교명" style="width: 100%;">
            </dd>
        </dl>
    </div>
</div>