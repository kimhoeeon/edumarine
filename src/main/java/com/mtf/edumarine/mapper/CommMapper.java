package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /** 2026-10-30 결함 수정: 신청번호로 회원번호 조회 (table 은 CommServiceImpl 의 고정 목록 값만 전달) */
    String selectMemberSeqByApplySeq(Map<String, Object> param);
}
