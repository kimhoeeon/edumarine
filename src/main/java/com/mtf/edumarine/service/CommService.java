package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.*;

import java.util.List;

public interface CommService {

    SmsResponseDTO smsSend(SmsDTO smsDTO);

    SmsResponseDTO smsSend_certNum(SmsDTO smsDTO);

    String getSystemicSiteMap();

    String smsSendNotifyContent(SmsNotificationDTO smsNotificationDTO);

    String smsSendNotifySending(SmsNotificationDTO smsNotificationDTO);

    void updateMemberGrade();

    ResponseDTO processUpdateFileUserId(FileDTO fileDTO);

    void processUpdateFileDeleteUseN(FileDTO fileDTO);

    List<FileDTO> processSelectFileUserIdList(FileDTO fileDTO);
}
