package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EdmHstDTO {
    Integer rownum; //연번
    String id; //ID
    String title; //제목
    String sendCnt; //발송건수
    String sendDttm; //전송일시
    String sendResult; //전송결과
    String sendContent; //메일내용
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}