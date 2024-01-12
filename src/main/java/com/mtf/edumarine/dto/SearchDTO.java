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
}