package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommCodeDTO {
    String commCdNm; //공통코드명
    String commCdVal; //공통코드값
    String useYn; //사용여부
    String cdNote1; //기타1
    String cdNote2; //기타2
    String cdNote3; //기타3
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}