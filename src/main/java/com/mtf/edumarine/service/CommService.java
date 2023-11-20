package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.CommCodeDTO;
import com.mtf.edumarine.dto.SmsDTO;
import com.mtf.edumarine.dto.SmsResponseDTO;

import java.util.List;

public interface CommService {

    List<CommCodeDTO> getCommCodeList(CommCodeDTO commCodeDTO);

    SmsResponseDTO smsSend(SmsDTO smsDTO);

}
