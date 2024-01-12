package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LicenseDTO {
    String seq; //seq
    String boarderSeq;
    String memberSeq; //회원SEQ
    String trainSeq; //교육SEQ
    String licenseName; //자격면허명
    String licenseDate; //취득일
    String licenseOrg; //발행기관
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}