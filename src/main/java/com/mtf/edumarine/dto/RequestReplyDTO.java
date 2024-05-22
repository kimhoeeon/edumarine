package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestReplyDTO {
    Integer rownum;
    String seq; //ID
    String requestSeq; //게시물SEQ
    String depthReplyNo; // 댓글 : 0 , 대댓글 : 1
    String moReplySeq; // 모댓글SEQ
    String content; //내용
    String writer; //작성자
    String writeDate; //작성일
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}