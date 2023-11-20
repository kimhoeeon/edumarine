/* Copyright(C)2018 NSMALL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited by law. 
 * This file is proprietary and confidential.
 * 
 * Written by EDSK project team
 */
package com.mtf.edumarine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class of AbstractPagingResponseVo.
 * <br/>
 * Paging Grid/Table 를 위한 Abstract Response Entity
 * <br/>
 * 페이징 처리를 위한 MyBatis mapping xml 파일 중 Reference.xml 과 매칭되는 컬럼이 member로 정의되어 있음<br/>
 * 해당 컬럼이 Mybastis Paging Query 에 맞춰 개발되어 있다면 AbstractPagingResponseVo를 extends 한 ResponseVo에 대해서는
 * 자동 Binding 된다.
 * <br/>
 */
@ToString(callSuper = true)
@SuppressWarnings("serial")
public abstract class AbstractPagingResponseVo {
    @JsonIgnore
    @Getter
    @Setter
    private int currentPage; // 현재 페이지
    @Getter
    @Setter
    private int totalRecords; // 페이지 조회수
    @Getter
    @Setter
    private int rownum; // 순번
}