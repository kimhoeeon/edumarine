package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrashDTO {
    Integer rownum;
    String seq; //순번
    String targetSeq; //대상SEQ
    String targetTable; //대상테이블명
    String targetMenu; //대상메뉴위치
    String deleteReason; //삭제사유
    String delYn; //삭제여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}