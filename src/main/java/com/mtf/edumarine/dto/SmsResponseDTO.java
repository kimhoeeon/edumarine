package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsResponseDTO {
    Integer result_code; //결과코드
    String message; //결과메시지
    String msg_id; //메시지고유ID
    String success_cnt; //요청성공건수
    String error_cnt; //요청실패건수
    String msg_type; //메시지타입
    String note;
}