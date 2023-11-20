package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDTO {
    String id; //ID
    String userId; //유저아이디
    String fullFilePath; //전체경로+파일명
    String fullPath; //전체경로
    String folderPath; //폴더경로
    String fullFileName; //uuid_파일명
    String uuid; //uuid
    String fileName; //파일명
    String fileYn; //파일사용여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}