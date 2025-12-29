<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="form_box">
    <div class="form_tit">
        <div class="big">신청정보</div>
    </div>
    <ul class="form_list">
        <li>
            <div class="gubun req"><p>상의 사이즈(남여공용)</p></div>
            <div class="naeyong">
                <div class="input">
                    <select name="topClothesSize" id="topClothesSize" title="상의 사이즈(남여공용)">
                        <option value="">선택</option>
                    </select>
                </div>
            </div>
        </li>
        <li>
            <div class="gubun req"><p>하의 사이즈(남여공용)</p></div>
            <div class="naeyong">
                <div class="input">
                    <input type="text" name="bottomClothesSize" id="bottomClothesSize" placeholder="숫자만 입력 (예: 30)" style="width: 100%;">
                </div>
            </div>
        </li>
        <li>
            <div class="gubun req"><p>안전화 사이즈(mm)</p></div>
            <div class="naeyong">
                <div class="input">
                    <input type="text" name="shoesSize" id="shoesSize" placeholder="숫자만 입력 (예: 260)" style="width: 100%;">
                </div>
            </div>
        </li>
        <li>
            <div class="gubun req"><p>참여경로</p></div>
            <div class="naeyong">
                <div class="input">
                    <select name="participationPath" id="participationPath" title="참여경로">
                    </select>
                </div>
            </div>
        </li>

        <li>
            <div class="gubun req"><p>졸업구분</p></div>
            <div class="naeyong">
                <div class="input">
                    <select name="gradeGbn" id="gradeGbn" title="졸업구분">
                    </select>
                </div>
            </div>
        </li>
        <li>
            <div class="gubun req"><p>학교명</p></div>
            <div class="naeyong">
                <div class="input">
                    <input type="text" name="schoolName" id="schoolName" placeholder="학교명" style="width: 100%;">
                </div>
            </div>
        </li>
    </ul>
</div>