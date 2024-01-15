package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EmploymentDTO extends AbstractPagingRequestVo {
    Integer totalRecords;
    Integer rownum; //연번
    String seq; //ID
    String gbn; //구분
    String employName; //회사명
    String employCeo; //대표자
    String employYear; //설립연도
    String employField; //분야
    String employAddress; //주소
    String employAddressDetail; //상세주소
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}