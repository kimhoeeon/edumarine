package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDTO {
    Integer rownum;
    String seq;
    String loginYn; //로그인여부
    String validYn; //로그인유효여부
    String gbn; //권한구분
    String blockYn; //접속차단여부
    String accessMenu; //접근가능메뉴
    String ipAddress; //아이피주소
    String id; //관리자ID
    String password; //비밀번호
    String cpName; //담당자명
    String cpTel; //담당자전화
    String cpPhone; //담당자휴대전화
    String cpEmail; //담당자이메일
    String cpDepart; //담당자소속
    String lastAccessDttm; //마지막 접속 일시
    String delYn;
    String note; //비고
    String initRegiPic; //최초 등록 담당자
    String initRegiDttm; //최초 등록 일시
    String finalRegiPic; //최종 변경 담당자
    String finalRegiDttm; //최종 변경 일시

    String resultCode;
    String resultMsg;
}