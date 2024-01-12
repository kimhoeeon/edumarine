package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CareerDTO {
    String seq; //seq
    String boarderSeq;
    String memberSeq; //회원SEQ
    String trainSeq; //교육SEQ
    String careerPlace; //근무처
    String careerDate; //기간
    String careerPosition; //직위
    String careerTask; //담당업무
    String careerLocation; //소재지
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}