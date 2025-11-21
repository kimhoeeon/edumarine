<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="form_box">
    <div class="form_title">
        <h4>신청 정보</h4>
    </div>
    <div class="form_content">
        <dl>
            <dt>교육구분<span class="required">*</span></dt>
            <dd>
                <input type="radio" id="boarderGbn1" name="boarderGbn" value="선외기" checked>
                <label for="boarderGbn1">선외기</label>
                <input type="radio" id="boarderGbn2" name="boarderGbn" value="선내기">
                <label for="boarderGbn2">선내기</label>
                <input type="radio" id="boarderGbn3" name="boarderGbn" value="세일요트">
                <label for="boarderGbn3">세일요트</label>
            </dd>
        </dl>
        <dl>
            <dt>작업복 사이즈(남여공용)<span class="required">*</span></dt>
            <dd>
                <select name="clothesSize" id="clothesSize" title="작업복 사이즈(남여공용)">
                    <option value="">선택</option>
                    <option value="90(S)">90(S)</option>
                    <option value="95(M)">95(M)</option>
                    <option value="100(L)">100(L)</option>
                    <option value="105(XL)">105(XL)</option>
                    <option value="110(2XL)">110(2XL)</option>
                    <option value="115(3XL)">115(3XL)</option>
                    <option value="120(4XL)">120(4XL)</option>
                </select>
            </dd>
        </dl>
        <dl>
            <dt>참여경로<span class="required">*</span></dt>
            <dd>
                <select name="participationPath" id="participationPath" title="참여경로">
                    <option value="">선택</option>
                    <option value="관련업체">관련업체</option>
                    <option value="관련기관">관련기관</option>
                    <option value="인터넷">인터넷</option>
                    <option value="지인추천">지인추천</option>
                    <option value="기타">기타</option>
                </select>
            </dd>
        </dl>
        <dl>
            <dt>추천인</dt>
            <dd>
                <input type="text" name="recommendPerson" id="recommendPerson" placeholder="추천인을 입력해주세요. (선택사항)" style="width: 100%;">
            </dd>
        </dl>
    </div>
</div>