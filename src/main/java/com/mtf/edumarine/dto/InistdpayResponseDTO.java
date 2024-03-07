package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InistdpayResponseDTO {
    ////////////////////////////////////////////////
    //PC
    ////////////////////////////////////////////////
    private String CARD_Quota;
    private String CARD_ClEvent;
    private String CARD_CorpFlag;
    private String buyerTel;
    private String parentEmail;
    private String applDate;
    private String buyerEmail;
    private String OrgPrice;
    private String p_Sub;
    private String resultCode;
    private String mid;
    private String CARD_UsePoint;
    private String CARD_Num;
    private String authSignature;
    private String tid;
    private String EventCode;
    private String goodName;
    private String TotPrice;
    private String payMethod;
    private String CARD_MemberNum;
    private String MOID;
    private String CARD_Point;
    private String currency;
    private String CARD_PurchaseCode;
    private String CARD_PrtcCode;
    private String applTime;
    private String goodsName;
    private String CARD_CheckFlag;
    private String FlgNotiSendChk;
    private String CARD_Code;
    private String CARD_BankCode;
    private String CARD_TerminalNum;
    private String P_FN_NM;
    private String buyerName;
    private String p_SubCnt;
    private String applNum;
    private String resultMsg;
    private String CARD_Interest;
    private String CARD_SrcCode;
    private String CARD_ApplPrice;
    private String CARD_GWCode;
    private String custEmail;
    private String CARD_Expire;
    private String CARD_PurchaseName;
    private String CARD_PRTC_CODE;
    private String payDevice;
    private String merchantData;

    // 가상계좌
    private String VACT_Date;
    private String VACT_Name;
    private String VACT_InputName;
    private String VACT_Time;
    private String VACT_BankCode;
    private String vactBankName;
    private String VACT_Num;

    ////////////////////////////////////////////////
    //MOBILE
    ////////////////////////////////////////////////
    private String P_STATUS;
    private String P_RMESG1;
    private String P_TID;
    private String P_AMT;
    private String idc_name;
    private String P_REQ_URL;
    private String P_NOTI;
    private String P_OID;
    private String P_TYPE;
    private String P_AUTH_DT;
    private String P_MID;
    private String P_UNAME;
    private String P_MNAME;
    private String P_NOTEURL;
    private String P_NEXT_URL;

    private String P_AUTH_NO;
    private String P_CARD_NUM;
    private String P_CARD_INTEREST;
    private String P_RMESG2;
    private String P_FN_CD1;
    private String P_CARD_CHECKFLAG;
    private String P_CARD_PRTC_CODE;
    private String P_CARD_ISSUER_CODE;
    private String P_ISP_CARDCODE;
    private String P_SRC_CODE;
    private String P_CARD_MEMBER_NUM;
    private String P_CARD_PURCHASE_CODE;
    private String P_CARD_USEPOINT;
    private String P_COUPONFLAG;
    private String P_COUPON_DISCOUNT;
    private String P_CARD_APPLPRICE;
    private String P_CARD_COUPON_PRICE;
    private String NAVERPOINT_UseFreePoint;
    private String NAVERPOINT_CSHRApplYN;
    private String NAVERPOINT_CSHRApplAmt;
    private String PCO_OrderNo;
    private String CARD_EmpPrtnCode;
    private String CARD_NomlMobPrtnCode;

    private String P_VACT_NUM;
    private String P_VACT_BANK_CODE;
    private String P_VACT_NAME;
    private String P_VACT_DATE;
    private String P_VACT_TIME;
    private String P_HPP_NUM;
    private String P_HPP_CORP;
}