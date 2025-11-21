package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDTO {
    String resultCode;
    String resultMessage;
    String customValue;
    String customValue2;
}