package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsSendDTO {
    Integer totalRecords;
    Integer rownum; //연번
    String tableSeq; //테이블SEQ
    String trainSeq; //교육SEQ
    String memberSeq; //회원SEQ
    String id;
    String name;
    String phone;
    String applyStatus;
}