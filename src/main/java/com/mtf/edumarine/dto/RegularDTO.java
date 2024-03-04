package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RegularDTO {
    Integer rownum; //연번
    String id;
    String seq; //순번
    String memberSeq; //회원SEQ
    String trainSeq; //교육SEQ
    String grade;
    String name; //이름
    String nameEn; //이름
    String phone; //연락처
    String email; //이메일
    String birthYear; //출생년도
    String birthMonth; //출생월
    String birthDay; //출생일
    String ageGroup; //연령대
    String region; //거주지역
    String participationPath; //참여경로
    String firstApplicationField; //1순위 신청분야
    String secondApplicationField; //2순위 신청분야
    String thirdApplicationField; //2순위 신청분야
    String desiredEducationTime; //희망교육시기
    String major; //전공
    String experienceYn; //경험유무
    String applyStatus; //신청상태
    String cancelDttm; //취소신청일시
    String cancelReason; //취소사유
    String refundBankCode; //환불계좌은행코드
    String refundBankName; //환불계좌은행명
    String refundBankCustomerName; //환불계좌예금주명
    String refundBankNumber; //환불계좌번호
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String memberStatus;

    public List<TrainInfo> trainInfoList;

    @Getter
    @Setter
    @ToString
    public static class TrainInfo {
        private String initRegidttm;
        private String gbn;
        private String nextTime;
        private String applyStatus;
    }
}