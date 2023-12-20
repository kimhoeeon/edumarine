package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainDTO {
    Integer rownum; //연번
    String seq; //순번
    String gbn; //구분
    String trainStartDttm; //교육시작일시
    String trainEndDttm; //교육종료일시
    String applyStartDttm; //접수시작일시
    String applyEndDttm; //접수종료일시
    Integer paySum; //교육비
    Integer trainCnt; //교육인원
    Integer trainApplyCnt; //교육신청인원
    Integer nextTime; //차시
    String trainNote; //교육비고
    String exposureYn; //홈페이지 노출여부
    String scheduleExposureYn; // 연간일정표 등록여부
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String today;
}