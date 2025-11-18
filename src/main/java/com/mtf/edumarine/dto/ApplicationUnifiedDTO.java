package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ApplicationUnifiedDTO {

    private String seq;
    private String trainSeq;
    private String memberSeq;
    private String applyStatus;

    // 1. RegularDTO 컬럼
    private String region;

    /**
     * 참여경로 (공통)
     */
    private String participationPath;

    private String firstApplicationField;
    private String secondApplicationField;
    private String thirdApplicationField;
    private String desiredEducationTime;
    private String experienceYn;

    /**
     * 추천인 (공통)
     */
    private String recommendPerson;

    // 2. BoarderDTO/FrpDTO 컬럼
    /**
     * 상의 (공통)
     */
    private String topClothesSize;
    private String bottomClothesSize;
    private String shoesSize;
    private String gradeGbn;
    private String schoolName;
    private String major;
    private String militaryGbn;
    private String militaryReason;
    private String disabledGbn;
    private String jobSupportGbn;
    private String techEduGbn;
    private String techEduName;
    private String applyReason;
    private String techReason;
    private String activityReason;
    private String planReason;
    private String etcReason;
    private String knowPath;
    private String knowPathReason;
    private String clothesSize;

    // 3. Inboarder/Outboarder/Sailyacht/HighSpecial/SternSpecial/Electro 컬럼
    private String rcBirthYear;
    private String rcBirthMonth;
    private String rcBirthDay;
    private String choiceDate;

    // 4. HighHorsePower/Sterndrive/Generator/Competency 컬럼
    private String trainUnderstand;
    private String trainUnderstandEtc;

    // 5. Basic/Emergency 컬럼
    private String boarderGbn;

    // 6. Famtour 컬럼
    private String applyDay;

    // 환불/취소 공통 컬럼
    private LocalDateTime cancelDttm;
    private String cancelReason;
    private String refundBankCode;
    private String refundBankName;
    private String refundBankCustomerName;
    private String refundBankNumber;

    // 기본 컬럼
    private String delYn;
    private String note;
    private String initRegiPic;
    private LocalDateTime initRegiDttm;
    private String finalRegiPic;
    private LocalDateTime finalRegiDttm;
}