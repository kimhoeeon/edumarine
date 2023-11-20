package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventDTO extends AbstractPagingRequestVo {
    Integer totalRecords;
    Integer rownum; //연번
    String seq; //ID
    String lang; //언어
    String title; //제목
    String writer; //작성자
    String writeDate; //작성일
    String location; //장소
    String year; //연도
    String startDate; //시작일
    String endDate; //종료일
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String prevId;
    String nextId;
}