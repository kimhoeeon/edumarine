package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InistdpayCancelResponseDTO {
    
    // 전체
    String resultCode; //결과코드 "00":성공, 이외 실패
    String resultMsg; //결과메세지
    String cancelDate; //취소일자 [YYYYMMDD]
    String cancelTime; //취소시간 [hhmmss]
    String cshrCancelNum; //현금영수증 취소승인번호 현금영수증 발행건에 한함
    String detailResultCode; //취소실패 응답시 상세코드
    String receiptInfo; //특정 가맹점 전용 응답필드

    // 부분
    String prtcDate; //취소일자 [YYYYMMDD]
    String prtcTime; //취소시간 [hhmmss]
    String tid; //부분취소 거래번호
    String prtcTid; //원 승인 거래번호
    String prtcPrice; //부분취소금액
    String prtcRemains; //부분취소 후 남은금액
    String prtcCnt; //부분취소 요청 횟수
    String prtcType; //부분취소 구분 ["0":재승인방식, "1":부분취소]
    String pointAmount; //부분취소 시 취소된 포인트 금액
    String discountAmount; //부분취소 시 취소된 할인 금액
    String creditAmount; //부분취소 시 취소된 여신 금액
    String cashReceiptAmount; //부분취소 후 남은 금액에 대한 현금영수증 발행 대상 금액

}