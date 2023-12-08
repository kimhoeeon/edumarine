package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BannerDTO extends AbstractPagingRequestVo {
    Integer rownum; //연번
    String seq; //ID
    String title; //제목
    Integer viewSeq; //노출순서
    String writer; //작성자
    String writeDate; //작성일자
    String fileIdList; //파일ID목록
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}