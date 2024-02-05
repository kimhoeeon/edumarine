package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InistdpayCancelRequestDTO {

    String cancelGbn;

    // 전체
    String tid; //취소요청할 승인 TID
    String msg; //취소요청사유

    // 부분
    String price; //취소요청 금액
    String confirmPrice; //부분취소 후 남은 금액
    String currency; //통화 (WON, USD)
    String tax; //부가세
    String taxFree; //비과세
}