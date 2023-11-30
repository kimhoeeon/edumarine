package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GalleryDTO extends AbstractPagingRequestVo {
    Integer totalRecords;
    Integer rownum; //연번
    String seq; //ID
    String lang; //언어
    String title; //제목
    String writer; //작성자
    String writeDate; //작성일
    Integer viewCnt; //조회수
    String fileIdList; //파일ID목록
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String prevId;
    String nextId;
}