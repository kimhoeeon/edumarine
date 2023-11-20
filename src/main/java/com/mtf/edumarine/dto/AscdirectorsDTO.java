package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AscdirectorsDTO extends AbstractPagingRequestVo {
    Integer totalRecords;
    Integer rownum; //연번
    String seq; //ID
    String lang; //언어
    String companyName; //회사명
    String companyCeo; //대표자명
    String companyHomepage; //홈페이지주소
    String companyTel; //전화번호
    String companyAddress; //회사주소
    String companyBusinessType; //주요사업
    String companyBusinessItem; //주요생산품
    String logo;
    String intro;
    String field;
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String prevId;
    String nextId;
}