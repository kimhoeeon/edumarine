package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SailyachtDTO {
    Integer rownum; //연번
    String seq; //순번
    String grade;
    String id;
    String memberSeq;
    String trainSeq;
    String nameKo; //이름
    String nameEn; //이름
    String phone; //연락처
    String email; //이메일
    String birthYear; //출생년도
    String birthMonth; //출생월
    String birthDay; //출생일
    String address; //주소
    String addressDetail; //상세주소
    String clothesSize; //작업복사이즈
    String participationPath; //참여경로
    String payYn; //결제여부
    Integer paySum; //결제금액
    String applyStatus;
    String cancelDttm; //취소신청일시
    String cancelReason;
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String preApplyStatus;
    String cancelGbn;
}