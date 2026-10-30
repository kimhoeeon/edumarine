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
    </ul>
</div>