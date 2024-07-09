package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestDTO {
    Integer rownum; //연번
    String seq; //seq
    String emergencyYn; //긴급여부
    String gbn; //구분
    String progressStep; //진행단계
    String hopeClosingDate; //희망마감일시
    String completeExpectDate; //완료예상일시
    String title; //제목
    String content; //내용
    String writer; //작성자
    String writeDate; //작성일시
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String fileIdList;
    String replyCnt;
}