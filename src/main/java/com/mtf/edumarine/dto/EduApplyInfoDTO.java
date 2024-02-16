package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EduApplyInfoDTO {
    Integer rownum; //연번
    String trainName;
    String trainSeq;
    String payStatus;
    String payMethod;
    String seq;
    String finalRegiDttm; //최종 변경 일시
    String trainStartDttm;
    String trainEndDttm;
    String changeYn;
}