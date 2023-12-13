package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentDTO {
    Integer rownum; //연번
    String seq; //순번
    String memberSeq; //회원SEQ
    String memberName; //신청자명
    String memberPhone; //신청자연락처
    String trainSeq; //강의SEQ
    String trainName; //과정명
    Integer paySum; //교육비
    String payStatus; //결제상태
    String refundReason; //환불사유
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}