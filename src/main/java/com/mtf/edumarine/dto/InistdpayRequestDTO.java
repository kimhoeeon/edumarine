package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InistdpayRequestDTO {
    private String version;
    private String gopaymethod;
    private String mid;
    private String oid;
    private String price;
    private String timestamp;
    private String useChkfake;
    private String signature;
    private String verification;
    private String mkey;
    private String currency;
    private String goodname;
    private String buyername;
    private String buyertel;
    private String buyeremail;
    private String siteDomain;
    private String returnUrl;
    private String closeUrl;
    private String acceptmethod;

    private String trainSeq;
    private String memberId;
    private String tableSeq;
    private String memberSeq;

    private String continueYn;
}