package com.mtf.edumarine.dto;

import com.mtf.edumarine.entity.AbstractPagingRequestVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    Integer rownum; //연번
    String seq; //순번
    String id; //ID
    String password; //비밀번호
    String grade; //등급
    String name; //이름
    String nameEn; //이름(영문)
    String phone; //연락처
    String email; //이메일
    String keyword; //관심키워드
    String smsYn; //SMS알림서비스동의여부
    String delYn; //임시휴지통여부
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시
    
    String passwordChangeYn; //비밀번호변경여부
}