package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegularDTO {
    Integer rownum; //연번
    String seq; //순번
    String name; //이름
    String phone; //연락처
    String email; //이메일
    String birthYear; //출생년도
    String birthMonth; //출생월
    String birthDay; //출생일
    String region; //거주지역
    String participationPath; //참여경로
    String firstApplicationField; //1순위 신청분야
    String secondApplicationField; //2순위 신청분야
    String desiredEducationTime; //희망교육시기
    String major; //전공
    String experienceYn; //경험유무
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}