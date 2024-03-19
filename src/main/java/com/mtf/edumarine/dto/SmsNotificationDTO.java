package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsNotificationDTO {
    String target;
    String seq; //memberSeq
    String content; //내용
    String trainSeq;
    String paymentSeq;
    String trainTable;

    //치환문자
    String eduName; //교육명
    String eduTime; //교육차시
    String eduDate; //교육일시
    String eduPrice; //교육비
    String vacctBank; //가상계좌은행
    String vacctNum; //가상계좌번호
    String vacctName; //가상계좌주
    String vacctLimit; //가상계좌입금기한
    String keyword; //키워드

}