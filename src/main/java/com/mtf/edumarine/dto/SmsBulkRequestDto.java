package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SmsBulkRequestDto {
    private List<String> phoneList;
    private String content;
    private String templateSeq;
    private String sender; // 발신번호 (예: "1811-7891")
}