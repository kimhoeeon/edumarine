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
    String tableSeq;
    String trainSeq; //강의SEQ
    String trainName; //과정명
    Integer paySum; //교육비
    String payStatus; //결제상태
    String cardQuota; //카드 할부 기간
    String cardCorpFlag; //카드구분 ["0":개인카드, "1":법인카드, "9":구분불가]
    String buyerTel; //구매자 휴대폰번호
    String applDate; //승인일자(YYYYMMDD)
    String buyerEmail; //구매자 이메일
    String resultCode; //결과코드
    String mid; //상점아이디
    String cardUsePoint; //포인트사용금액
    String cardNum; //신용카드번호
    String authSignature; //인증key
    String tid; //거래번호
    String eventCode; //이벤트 코드, 카드 할부 및 행사 적용 코드
    String goodName; //상품명
    String totPrice; //결제금액
    String payMethod; //지불수단
    String cardMemberNum; //
    String moid; //주문번호
    String cardPoint; //카드포인트
    String currency; //통화코드
    String cardPurchaseCode; //카드 구매 코드
    String cardPrtcCode; //부분취소 가능여부 ["1":가능 , "0":불가능]
    String applTime; //승인시간(hh24miss)
    String goodsName; //상품명
    String cardCheckFlag; //카드종류 ["0":신용카드, "1":체크카드, "2":기프트카드]
    String cardCode; //카드사 코드
    String cardBankCode; //카드발급사(은행) 코드
    String cardTerminalNum;
    String pFnNm; // 카드 종류
    String buyerName; //구매자 명
    String applNum; //승인번호
    String resultMsg; //결과메시지
    String cardInterest; //상점부담 무이자 할부여부 ["1":상점부담 무이자]
    String cardSrcCode; //간편(앱)결제구분
    String cardApplPrice; //신용카드 승인금액
    String cardGwcode;
    String custEmail; //최종 이메일주소
    String cardPurchaseName;
    String payDevice; // 결제장치
    String refundReason; //환불사유
    String cancelGbn; //취소구분(전체/부분)
    String cancelDate; //[전체]취소일자
    String cancelTime; //[전체]취소시간
    String prtcDate; //[부분]취소일자
    String prtcTime; //[부분]취소시간
    String cancelTid; //[부분]취소거래번호
    String prtcPrice; //[부분]취소금액
    String prtcRemains; //[부분]취소후남은금액
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}