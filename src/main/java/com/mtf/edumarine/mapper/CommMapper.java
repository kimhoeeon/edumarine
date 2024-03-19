package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.SmsNotificationDTO;
import com.mtf.edumarine.dto.TemplateDTO;
import com.mtf.edumarine.dto.TrainDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommMapper {
    TemplateDTO getTemplateContent(String target);

    String getSmsSendingJoinList(SmsNotificationDTO smsNotificationDTO);

    List<String> getSmsSendingKeywordList(SmsNotificationDTO smsNotificationDTO);

    List<TrainDTO> getSmsSendingTrainNotiList();
}
