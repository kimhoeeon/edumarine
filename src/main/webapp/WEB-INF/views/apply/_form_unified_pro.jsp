<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<div class="form_box">
    <div class="form_tit">
        <div class="big">신청정보</div>
    </div>
    <ul class="form_list">
        <li>
            <div class="gubun req"><p>작업복 사이즈(남여공용)</p></div>
            <div class="naeyong">
                <div class="input">
                    <select name="clothesSize" id="clothesSize" title="작업복 사이즈(남여공용)">
                        <option value="">선택</option>
                        <option value="S" <c:if test="${appInfo.clothesSize eq 'S'}">selected</c:if> >S(90)</option>
                        <option value="M" <c:if test="${appInfo.clothesSize eq 'M'}">selected</c:if> >M(95)</option>
                        <option value="L" <c:if test="${appInfo.clothesSize eq 'L'}">selected</c:if> >L(100)</option>
                        <option value="XL" <c:if test="${appInfo.clothesSize eq 'XL'}">selected</c:if> >XL(105)</option>
                        <option value="2XL" <c:if test="${appInfo.clothesSize eq '2XL'}">selected</c:if> >2XL(110)</option>
                        <option value="기타" <c:if test="${appInfo.clothesSize eq '기타'}">selected</c:if> >기타</option>
                    </select>
                </div>
            </div>
        </li>
        <li>
            <div class="gubun req"><p>참여경로</p></div>
            <div class="naeyong">
                <div class="input">
                    <select name="participationPath" id="participationPath" title="참여경로">
                        <option value="">선택</option>
                        <option value="인터넷" <c:if test="${appInfo.participationPath eq '인터넷'}">selected</c:if> >인터넷</option>
                        <option value="홈페이지" <c:if test="${appInfo.participationPath eq '홈페이지'}">selected</c:if> >홈페이지</option>
                        <option value="홍보물" <c:if test="${appInfo.participationPath eq '홍보물'}">selected</c:if> >홍보물</option>
                        <option value="지인추천" <c:if test="${appInfo.participationPath eq '지인추천'}">selected</c:if> >지인추천</option>
                        <option value="기타" <c:if test="${appInfo.participationPath eq '기타'}">selected</c:if> >기타</option>
                    </select>
                </div>
            </div>
        </li>
        <li>
            <div class="gubun">
                <p>추천인</p>
            </div>
            <div class="naeyong">
                <div class="input">
                    <input type="text" id="recommendPerson" name="recommendPerson" value="${appInfo.recommendPerson}" placeholder="추천인" class="w50">
                </div>
            </div>
        </li>
    </ul>
</div>