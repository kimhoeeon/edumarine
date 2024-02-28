package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SplineStatisticsDTO {
    private List<StatisticsDTO> data;

    private List<Integer> series;
    private List<String> labels;
}