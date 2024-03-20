package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberGradeDTO {
    String seq; //순번
    String applyStatus;
    String name; //이름
    String smsYn; //SMS알림서비스동의여부
    String grade; //등급
    String afterGrade;
}