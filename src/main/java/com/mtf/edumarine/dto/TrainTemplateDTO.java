package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TrainTemplateDTO {
    String gbn;
    public List<TrainTemplateInfo> data;

    @Getter
    @Setter
    @ToString
    public static class TrainTemplateInfo {
        String seq; //순번
        String major;
        String middle;
        String small;
        String value;
        String note;
    }
}