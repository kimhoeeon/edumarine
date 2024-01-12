package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecommendDTO {
    String seq; //순번
    String memberId; //회원ID
    String communitySeq; //커뮤니티SEQ
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}