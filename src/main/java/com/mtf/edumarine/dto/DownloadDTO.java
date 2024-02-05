package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DownloadDTO {
    Integer rownum;
    String seq; //순번
    String downloadFileName; //다운로드파일명
    String targetMenu; //메뉴위치
    String downloadReason; //다운로드사유
    String downloadUser; //다운로드한 관리자ID
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}