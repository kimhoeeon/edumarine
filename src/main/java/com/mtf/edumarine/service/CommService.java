package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.CommCodeDTO;
import com.mtf.edumarine.dto.SmsDTO;
import com.mtf.edumarine.dto.SmsNotificationDTO;
import com.mtf.edumarine.dto.SmsResponseDTO;

import java.util.List;

public interface CommService {

    SmsResponseDTO smsSend(SmsDTO smsDTO);

    SmsResponseDTO smsSend_certNum(SmsDTO smsDTO);

    String getSystemicSiteMap();

    String smsSendNotifyContent(SmsNotificationDTO smsNotificationDTO);

    String smsSendNotifySending(SmsNotificationDTO smsNotificationDTO);
}
