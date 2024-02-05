package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDTO extends AbstractPagingRequestVo {
    String condition; //검색조건
    String searchText; //검색단어
    String lang; //언어
    String gbn; //구분
    String useYn; //사용여부
    String year; //년도
    String category; //카테고리
    String applyStatus; //신청상태
    String grade; //등급
    Integer time; //차시
    String sendYn; //수신허용여부
    String result; //결과
    String blockYn;
    String sex;
    String keyword;
    String exposureYn;
    String experienceYn;
    String jobSupportYn;
}