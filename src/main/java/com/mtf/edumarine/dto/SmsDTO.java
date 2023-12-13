package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsDTO {
    Integer rownum; //연번
    String seq; //순번
    String smsGroup; //그룹코드
    String phone; //수신번호
    String sender; //발신자
    String senderPhone; //발신번호
    String sendDate; //발송일
    String content; //내용
    String sendResult; //발송결과
    String templateSeq; //템플릿SEQ
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}