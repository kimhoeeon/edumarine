<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<div class="form_box">
    <div class="form_tit">
        <div class="big">신청정보</div>
    </div>
    <ul class="form_list">
        <%-- 교육명에 '팸투어'가 포함되지 않은 경우에만 사이즈 선택 노출 --%>
        <c:if test="${not fn:contains(trainInfo.gbn, '팸투어')}">
            <li>
                <div class="gubun req"><p>작업복 사이즈(남여공용)</p></div>
                <div class="naeyong">
                    <div class="input">
                        <select name="clothesSize" id="clothesSize" title="작업복 사이즈(남여공용)">
                            <option value="">선택</option>
                            <option value="S">S(90)</option>
                            <option value="M">M(95)</option>
                            <option value="L">L(100)</option>
                            <option value="XL">XL(105)</option>
                            <option value="2XL">2XL(110)</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                </div>
            </li>
        </c:if>

        <li>
            <div class="gubun req"><p>참여경로</p></div>
            <div class="naeyong">
                <div class="input">
                    <select name="participationPath" id="participationPath" title="참여경로">
                        <option value="">선택</option>
                        <option value="인터넷">인터넷</option>
                        <option value="홈페이지">홈페이지</option>
                        <option value="홍보물">홍보물</option>
                        <option value="지인추천">지인추천</option>
                        <option value="기타">기타</option>
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
                    <input type="text" id="recommendPerson" name="recommendPerson" value="" placeholder="추천인" class="w50">
                </div>
            </div>
        </li>
    </ul>
</div>