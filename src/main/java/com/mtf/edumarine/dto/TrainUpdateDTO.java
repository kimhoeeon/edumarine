package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainUpdateDTO {
    String seq; //순번
    String trainSeq; //교육SEQ

    String trainName;
}