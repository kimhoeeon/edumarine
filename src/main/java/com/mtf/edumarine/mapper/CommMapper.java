package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommMapper {
    TemplateDTO getTemplateContent(String target);

    String getSmsSendingJoinList(SmsNotificationDTO smsNotificationDTO);

    List<String> getSmsSendingKeywordList(SmsNotificationDTO smsNotificationDTO);

    List<TrainDTO> getSmsSendingTrainNotiList();

    List<MemberGradeDTO> selectMemberGradeUpdateTarget();

    void updateMemberGrade(MemberGradeDTO member);

    Integer updateFileUserId(FileDTO fileDTO);

    void updateFileDeleteUseN(FileDTO fileDTO);

    List<FileDTO> selectFileUserIdList(FileDTO fileDTO);
}
