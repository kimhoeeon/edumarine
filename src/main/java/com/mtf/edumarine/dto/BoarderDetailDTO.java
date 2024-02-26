package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BoarderDetailDTO {
    Integer rownum; //연번
    String seq; //순번
    String memberSeq; //회원SEQ
    String trainSeq; //교육SEQ
    String memberStatus;
    String applyStatus; //신청상태
    String id;
    String grade;
    String nameKo; //이름
    String nameEn; //이름
    String phone; //연락처
    String email; //이메일
    String birthYear; //출생년도
    String birthMonth; //출생월
    String birthDay; //출생일
    String sex; //성별
    Integer age; //나이
    String address; //주소
    String addressDetail; //상세주소
    String topClothesSize; //상의사이즈
    String bottomClothesSize; //하의사이즈
    String shoesSize; //안전화사이즈
    String participationPath; //참여경로
    String gradeGbn; //졸업구분
    String schoolName; //학교명
    String major; //전공
    String militaryGbn; //병역
    String militaryReason; //미필사유
    String disabledGbn; //장애인
    String jobSupportGbn; //취업지원대상
    String techEduGbn; //테크니션교육경험
    String techEduName; //테크니션교육명
    String applyReason; //지원동기
    String techReason; //적합성 기술
    String activityReason; //활동사항
    String planReason; //수료후포부
    String etcReason; //기타사항
    String cancelDttm; //취소신청일시
    String cancelReason; //취소사유
    String careerPlace;
    String careerDate;
    String careerPosition;
    String careerTask;
    String careerLocation;
    String licenseName;
    String licenseDate;
    String licenseOrg;
    String refundBankCode; //환불계좌은행코드
    String refundBankName; //환불계좌은행명
    String refundBankCustomerName; //환불계좌예금주명
    String refundBankNumber; //환불계좌번호
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
}