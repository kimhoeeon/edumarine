package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConsultationDTO extends AbstractPagingRequestVo {
    Integer totalRecords;
    Integer rownum; //연번
    String seq; //ID
    String gbn; // 고문/자문 구분
    String lang; //언어
    String position; //협회직위
    String name; //성명
    String organization; //소속기관
    String depart; //직위
    String homepage; //홈페이지
    String tel; //전화번호
    String briefDescription; //약력사항
    String representImage; //대표이미지
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String prevId;
    String nextId;
}