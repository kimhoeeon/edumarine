package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EduMarineMapper {

    String checkStatisticsAccessor(StatisticsDTO statisticsDTO);

    Integer updateStatisticsAccessor(StatisticsDTO reqDto);

    Integer insertStatisticsAccessor(StatisticsDTO reqDto);

    List<PopupDTO> selectPopupList(PopupDTO popupDTO);

    List<BannerDTO> selectBannerList(BannerDTO bannerDTO);

    FileDTO selectFileIdSingle(FileDTO fileReq);

    List<TrainDTO> selectTrainList(TrainDTO trainDTO);

    List<NoticeDTO> selectNoticeList(NoticeDTO noticeDTO);

    List<PressDTO> selectPressList(PressDTO pressDTO);

    Integer checkDuplicateId(MemberDTO memberDTO);

    String getMemberSeq();

    Integer insertMember(MemberDTO memberDTO);

    Integer checkMemberSingle(MemberDTO memberDTO);

    String getMemberEmail(MemberDTO memberDTO);

    Integer initMemberPassword(MemberDTO memberDTO);

    List<NoticeDTO> selectBoardNoticeList(SearchDTO searchDTO);

    Integer updateBoardNoticeViewCnt(String seq);

    NoticeDTO selectBoardNoticeSingle(String seq);

    List<FileDTO> selectFileList(String seq);

    List<PressDTO> selectBoardPressList(SearchDTO searchDTO);

    Integer updateBoardPressViewCnt(String seq);

    PressDTO selectBoardPressSingle(String seq);

    List<GalleryDTO> selectBoardGalleryList(SearchDTO searchDTO);

    List<MediaDTO> selectBoardMediaList(SearchDTO searchDTO);

    List<NewsletterDTO> selectBoardNewsList(SearchDTO searchDTO);

    Integer updateBoardNewsViewCnt(String seq);

    NewsletterDTO selectBoardNewsSingle(String seq);

    List<JobDTO> selectJobReviewList(SearchDTO searchDTO);

    MemberDTO selectMemberSingle(String id);

    Integer updateMember(MemberDTO memberDTO);

    ResumeDTO selectResumeSingle(String memberSeq);

    Integer updateResume(ResumeDTO resumeDTO);

    String getResumeSeq();

    Integer insertResume(ResumeDTO resumeDTO);

    List<TrainDTO> selectTrainScheduleList(SearchDTO searchDTO);

    List<TrainDTO> selectTrainScheduleCalendarList(TrainDTO trainDTO);

    String getRegularSeq();

    Integer insertRegular(RegularDTO regularDTO);

    TrainDTO selectTrainSingle(String trainSeq);

    Integer updateTrainApplyCnt(String trainSeq);

    List<CommunityDTO> selectCommunityList(SearchDTO searchDTO);

    CommunityDTO selectCommunitySingle(String seq);

    Integer updateCommunityViewCnt(String seq);

    RecommendDTO selectRecommendSingle(RecommendDTO recommendReqDTO);

    Integer deleteRecommend(String seq);

    Integer insertRecommend(RecommendDTO recommendDTO);

    List<ReplyDTO> selectReplyList(ReplyDTO replyReqDTO);

    String getReplySeq();

    Integer insertReply(ReplyDTO replyDTO);

    Integer deleteReply(ReplyDTO replyDTO);

    String getCommunitySeq();

    Integer insertCommunity(CommunityDTO communityDTO);

    Integer deleteCommunity(CommunityDTO communityDTO);

    List<CommunityDTO> selectPostCommunityList(String id);

    List<ReplyDTO> selectPostReplyList(String id);

    Integer updateCommunity(CommunityDTO communityDTO);

    Integer deleteReplyToReply(ReplyDTO replyDTO);

    RegularDTO selectPreRegularSingle(RegularDTO regularDTO);

    MemberDTO selectMemberSeqSingle(String seq);

    Integer updatePreRegularInfo(RegularDTO regularDTO);

    String getPaymentSeq();

    String selectMemberSeq(MemberDTO memberDTO);

    String selectTrainSeq(TrainDTO trainDTO);

    Integer insertPayment(PaymentDTO paymentDTO);

    Integer updateRegularPayStatus(RegularDTO regularDTO);

    List<EduApplyInfoDTO> selectEduApplyInfoList(String memberSeq);

    List<EduApplyInfoDTO> selectEduApplyInfoCancelList(String memberSeq);

    RegularDTO selectRegularSingle(String seq);

    Integer updateRegular(RegularDTO regularDTO);

    List<PaymentDTO> selectPaymentList(String memberSeq);

    String getBoarderSeq();

    Integer insertBoarder(BoarderDTO boarderDTO);

    Integer insertCareer(CareerDTO careerDTO);

    Integer insertLicense(LicenseDTO licenseDTO);

    Integer updateBoarderPayStatus(BoarderDTO boarderDTO);

    BoarderDTO selectBoarderSingle(String seq);

    List<CareerDTO> selectCareerList(CareerDTO careerDTO);

    List<LicenseDTO> selectLicenseList(LicenseDTO licenseDTO);

    Integer updateBoarder(BoarderDTO boarderDTO);

    Integer updateCareer(CareerDTO request);

    Integer updateLicense(LicenseDTO request);

    Integer deleteCareer(CareerDTO careerDTO);

    Integer deleteLicense(LicenseDTO licenseDTO);

    String getFrpSeq();

    Integer insertFrp(FrpDTO frpDTO);

    Integer updateFrpPayStatus(FrpDTO frpDTO);

    FrpDTO selectFrpSingle(String seq);

    Integer updateFrp(FrpDTO frpDTO);

    String getInboarderSeq();

    Integer insertInboarder(InboarderDTO inboarderDTO);

    Integer updateInboarderPayStatus(InboarderDTO inboarderDTO);

    InboarderDTO selectInboarderSingle(String seq);

    Integer updateInboarder(InboarderDTO inboarderDTO);

    String getOutboarderSeq();

    Integer insertOutboarder(OutboarderDTO outboarderDTO);

    String getSailyachtSeq();

    Integer insertSailyacht(SailyachtDTO sailyachtDTO);

    OutboarderDTO selectOutboarderSingle(String seq);

    SailyachtDTO selectSailyachtSingle(String seq);

    Integer updateOutboarder(OutboarderDTO outboarderDTO);

    Integer updateSailyacht(SailyachtDTO sailyachtDTO);

    Integer updateOutboarderPayStatus(OutboarderDTO outboarderDTO);

    Integer updateSailyachtPayStatus(SailyachtDTO sailyachtDTO);

    List<EmploymentDTO> selectEmploymentList(String gbn);

    List<FaqDTO> selectFaqList(SearchDTO searchDTO);

    Integer deleteFile(FileDTO fileDTO);

    String getMemberSalt(String id);

    Integer updateMemberWithdraw(MemberDTO memberDTO);

    Integer selectRegularPreCheck(RegularDTO regularDTO);

    Integer selectBoarderPreCheck(BoarderDTO boarderDTO);

    Integer selectFrpPreCheck(FrpDTO frpDTO);

    Integer selectInboarderPreCheck(InboarderDTO inboarderDTO);

    Integer selectOutboarderPreCheck(OutboarderDTO outboarderDTO);

    Integer selectSailyachtPreCheck(SailyachtDTO sailyachtDTO);

    void updateTrainClosing(String todate);

    Integer checkMember(MemberDTO memberDTO);

    Integer checkCommunity(CommunityDTO communityDTO);

    Integer checkReply(ReplyDTO replyDTO);

    Integer updatePaymentVbankNoti(PaymentDTO paymentDTO);

    PaymentDTO selectPaymentVbankInfo(PaymentDTO paymentDTO);

    PaymentDTO selectPaymentTableSeq(String seq);

    void updatePayment(PaymentDTO paymentDTO);
}
