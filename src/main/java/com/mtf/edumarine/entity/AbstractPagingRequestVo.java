/* Copyright(C)2018 NSMALL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited by law. 
 * This file is proprietary and confidential.
 * 
 * Written by EDSK project team
 */
package com.mtf.edumarine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class of PagingRequestVo.
 *
 * @created at 2018. 11. 11 by SungOk Cho <jerred@edsk.co.kr>
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuppressWarnings("serial")
public abstract class AbstractPagingRequestVo {
    private Integer pageNum; // 현재 페이지
    private Integer rows; // 페이지 조회수
}