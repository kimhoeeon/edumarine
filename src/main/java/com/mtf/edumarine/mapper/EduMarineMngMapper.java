package com.mtf.edumarine.mapper;

import com.mtf.edumarine.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface EduMarine Mng mapper.
 */
@Repository
public interface EduMarineMngMapper {

    AdminDTO login(AdminDTO customerDTO);

    List<NoticeDTO> selectNoticeList(SearchDTO searchDTO);

    NoticeDTO selectNoticeSingle(NoticeDTO noticeDTO);

    Integer deleteNotice(NoticeDTO noticeDTO);

    Integer updateNotice(NoticeDTO noticeDTO);

    String getNoticeSeq();

    Integer insertNotice(NoticeDTO noticeDTO);

    List<PressDTO> selectPressList(SearchDTO searchDTO);

    PressDTO selectPressSingle(PressDTO pressDTO);

    Integer deletePress(PressDTO pressDTO);

    Integer updatePress(PressDTO pressDTO);

    String getPressSeq();

    Integer insertPress(PressDTO pressDTO);

    List<FileDTO> selectFileUserIdList(FileDTO fileDTO);

    Integer updateFileUseN(FileDTO fileDTO);

    Integer updateFileUserId(FileDTO fileDTO);

    String getFileId();

    Integer insertFileInfo(FileDTO fileDTO);

    List<GalleryDTO> selectGalleryList(SearchDTO searchDTO);

    GalleryDTO selectGallerySingle(GalleryDTO galleryDTO);

    Integer deleteGallery(GalleryDTO galleryDTO);

    Integer updateGallery(GalleryDTO galleryDTO);

    String getGallerySeq();

    Integer insertGallery(GalleryDTO galleryDTO);

    List<MediaDTO> selectMediaList(SearchDTO searchDTO);

    MediaDTO selectMediaSingle(MediaDTO mediaDTO);

    Integer deleteMedia(MediaDTO mediaDTO);

    Integer updateMedia(MediaDTO mediaDTO);

    String getMediaSeq();

    Integer insertMedia(MediaDTO mediaDTO);

    List<NewsletterDTO> selectNewsletterList(SearchDTO searchDTO);

    NewsletterDTO selectNewsletterSingle(NewsletterDTO newsletterDTO);

    Integer deleteNewsletter(NewsletterDTO newsletterDTO);

    Integer updateNewsletter(NewsletterDTO newsletterDTO);

    String getNewsletterSeq();

    Integer insertNewsletter(NewsletterDTO newsletterDTO);

    List<AnnouncementDTO> selectAnnouncementList(SearchDTO searchDTO);

    AnnouncementDTO selectAnnouncementSingle(AnnouncementDTO announcementDTO);

    Integer deleteAnnouncement(AnnouncementDTO announcementDTO);

    Integer updateAnnouncement(AnnouncementDTO announcementDTO);

    String getAnnouncementSeq();

    Integer insertAnnouncement(AnnouncementDTO announcementDTO);

    List<JobDTO> selectJobList(SearchDTO searchDTO);

    JobDTO selectJobSingle(JobDTO jobDTO);

    Integer deleteJob(JobDTO jobDTO);

    Integer updateJob(JobDTO jobDTO);

    String getJobSeq();

    Integer insertJob(JobDTO jobDTO);

    List<CommunityDTO> selectCommunityList(SearchDTO searchDTO);

    CommunityDTO selectCommunitySingle(CommunityDTO communityDTO);

    Integer deleteCommunity(CommunityDTO communityDTO);

    Integer updateCommunity(CommunityDTO communityDTO);

    String getCommunitySeq();

    Integer insertCommunity(CommunityDTO communityDTO);

    List<PopupDTO> selectPopupList(SearchDTO searchDTO);

    Integer deletePopup(PopupDTO popupDTO);

    PopupDTO selectPopupSingle(PopupDTO popupDTO);

    Integer updatePopup(PopupDTO popupDTO);

    String getPopupSeq();

    Integer insertPopup(PopupDTO popupDTO);

    Integer getActivePopupCount(PopupDTO reqDTO);

    List<BannerDTO> selectBannerList(SearchDTO searchDTO);

    BannerDTO selectBannerSingle(BannerDTO bannerDTO);

    Integer deleteBanner(BannerDTO bannerDTO);

    Integer updateBanner(BannerDTO bannerDTO);

    String getBannerSeq();

    Integer insertBanner(BannerDTO bannerDTO);

    List<MemberDTO> selectMemberList(SearchDTO searchDTO);

    MemberDTO selectMemberSingle(MemberDTO memberDTO);

    Integer deleteMember(MemberDTO memberDTO);

    Integer updateMember(MemberDTO memberDTO);

    String getMemberSeq();

    Integer insertMember(MemberDTO memberDTO);

    List<RegularDTO> selectRegularList(SearchDTO searchDTO);

    RegularDTO selectRegularSingle(RegularDTO regularDTO);

    Integer deleteRegular(RegularDTO regularDTO);

    Integer updateRegular(RegularDTO regularDTO);

    String getRegularSeq();

    Integer insertRegular(RegularDTO regularDTO);

    List<InboarderDTO> selectInboarderList(SearchDTO searchDTO);

    InboarderDTO selectInboarderSingle(InboarderDTO inboarderDTO);

    Integer deleteInboarder(InboarderDTO inboarderDTO);

    Integer updateInboarder(InboarderDTO inboarderDTO);

    String getInboarderSeq();

    Integer insertInboarder(InboarderDTO inboarderDTO);

    List<TrainDTO> selectTrainList(SearchDTO searchDTO);

    TrainDTO selectTrainSingle(TrainDTO trainDTO);

    Integer deleteTrain(TrainDTO trainDTO);

    Integer updateTrain(TrainDTO trainDTO);

    String getTrainSeq();

    Integer insertTrain(TrainDTO trainDTO);

    List<PaymentDTO> selectPaymentList(SearchDTO searchDTO);

    PaymentDTO selectPaymentSingle(PaymentDTO paymentDTO);

    Integer deletePayment(PaymentDTO paymentDTO);

    Integer updatePayment(PaymentDTO paymentDTO);

    String getPaymentSeq();

    String selectMemberSeq(MemberDTO memberDTO);

    String selectTrainSeq(TrainDTO trainDTO);

    Integer insertPayment(PaymentDTO paymentDTO);

    Integer updatePayStatus(PaymentDTO paymentInfo);

    List<SubscriberDTO> selectSubscriberList(SearchDTO searchDTO);

    SubscriberDTO selectSubscriberSingle(SubscriberDTO subscriberDTO);

    Integer deleteSubscriber(SubscriberDTO subscriberDTO);

    Integer updateSubscriber(SubscriberDTO subscriberDTO);

    String getSubscriberSeq();

    Integer insertSubscriber(SubscriberDTO subscriberDTO);

    List<SmsDTO> selectSmsList(SearchDTO searchDTO);

    List<TemplateDTO> selectSmsTemplateList();

    TemplateDTO selectSmsTemplateSingle(TemplateDTO templateDTO);

    String getSmsTemplateSeq();

    Integer insertSmsTemplate(TemplateDTO templateDTO);

    Integer updateSmsTemplate(TemplateDTO templateDTO);

    Integer deleteSmsTemplate(TemplateDTO templateDTO);

    String getSmsSeq();

    Integer insertSms(SmsDTO smsDTO);

    Integer deleteSms(SmsDTO smsDTO);

    SmsDTO selectSmsSingle(SmsDTO smsDTO);

    List<DownloadDTO> selectDownloadList(SearchDTO searchDTO);

    String getDownloadSeq();

    Integer insertDownload(DownloadDTO downloadDTO);

    List<TrashDTO> selectTrashList(SearchDTO searchDTO);

    Integer updateTargetTableTrash(TrashDTO trashDTO);

    String getTrashSeq();

    Integer insertTrash(TrashDTO trashDTO);

    Integer deleteTrash(TrashDTO trashDTO);

    Integer deleteTargetTableTrash(TrashDTO trashDTO);

    TrashDTO selectTrashSingle(TrashDTO trashDTO);

    List<EmploymentDTO> selectEmploymentList(SearchDTO searchDTO);

    EmploymentDTO selectEmploymentSingle(EmploymentDTO employmentDTO);

    Integer updateEmployment(EmploymentDTO employmentDTO);

    String getEmploymentSeq();

    Integer insertEmployment(EmploymentDTO employmentDTO);

    List<BoarderDTO> selectBoarderList(SearchDTO searchDTO);

    Integer updateBoarderApplyStatus(BoarderDTO info);

    List<FrpDTO> selectFrpList(SearchDTO searchDTO);

    Integer updateFrpApplyStatus(FrpDTO info);

    List<OutboarderDTO> selectOutboarderList(SearchDTO searchDTO);

    Integer updateOutboarderApplyStatus(OutboarderDTO info);

    List<SailyachtDTO> selectSailyachtList(SearchDTO searchDTO);

    Integer updateSailyachtApplyStatus(SailyachtDTO info);

    Integer updateInboarderApplyStatus(InboarderDTO info);

    BoarderDTO selectBoarderSingle(String seq);

    PaymentDTO selectPaymentTableSeq(String seq);

    Integer updatePaymentCancelResult(PaymentDTO updCancelPayment);

    FrpDTO selectFrpSingle(String seq);

    OutboarderDTO selectOutboarderSingle(String seq);

    SailyachtDTO selectSailyachtSingle(String seq);

    Integer updateRegularApplyStatus(RegularDTO info);

    List<AdminDTO> selectAdminMngList(SearchDTO searchDTO);

    AdminDTO selectAdminMngSingle(AdminDTO adminDTO);

    Integer selectAdminMngCheckDuplicateId(AdminDTO adminDTO);

    Integer updateAdminMng(AdminDTO adminDTO);

    String getAdminSeq();

    Integer insertAdminMng(AdminDTO adminDTO);

    void updateAdminMngLoginYn(AdminDTO adminDTO);

    void updateAdminMngValidYn(AdminDTO reqAdminDTO);

    AdminDTO selectAdminMngSingleId(AdminDTO adminDTO);

    Integer updateTrainEarlyClosingYn(TrainDTO trainDTO);

    List<CareerDTO> selectCareerList(String boarderSeq);

    List<LicenseDTO> selectLicenseList(String boarderSeq);

    List<FileDTO> selectFileList(String userId);

    List<ResumeDTO> selectResumeList(SearchDTO searchDTO);

    ResumeDTO selectResumeSingle(ResumeDTO resumeDTO);

    List<SmsSendDTO> selectSmsSendList(SearchDTO searchDTO);

    List<SmsSendDTO> selectSmsSendRegularList();

    List<SmsSendDTO> selectSmsSendBoarderList();

    List<SmsSendDTO> selectSmsSendFrpList();

    List<SmsSendDTO> selectSmsSendOutboarderList();

    List<SmsSendDTO> selectSmsSendInboarderList();

    List<SmsSendDTO> selectSmsSendSailyachtList();

    List<FaqDTO> selectFaqList(SearchDTO searchDTO);

    FaqDTO selectFaqSingle(FaqDTO faqDTO);

    Integer updateFaq(FaqDTO faqDTO);

    String getFaqSeq();

    Integer insertFaq(FaqDTO faqDTO);

    void updateTrainApplyCnt(String seq);

    PaymentDTO selectTrainPaymentInfo(PaymentDTO paymentRequestDTO);

    Integer checkSubscriber(SubscriberDTO subscriberDTO);

    List<TrainDTO> selectTrainNextTime(TrainDTO trainDTO);

    List<MemberDTO> selectExcelMemberDetailList();

    List<RegularDTO> selectExcelRegularDetailList();

    List<BoarderDetailDTO> selectExcelBoarderDetailList();

    List<FrpDetailDTO> selectExcelFrpDetailList();

    List<OutboarderDetailDTO> selectExcelOutboarderDetailList();

    List<InboarderDetailDTO> selectExcelInboarderDetailList();

    List<SailyachtDetailDTO> selectExcelSailyachtDetailList();

    StatisticsDTO selectMemberCount(StatisticsDTO statisticsDTO);

    StatisticsDTO selectTrainCount(StatisticsDTO trainDTO);

    List<StatisticsDTO> selectStatisticsAccessorDay(StatisticsDTO statisticsDTO);

    List<StatisticsDTO> selectStatisticsAccessorMonth(StatisticsDTO statisticsDTO);

    List<StatisticsDTO> selectStatisticsAccessorWeek(StatisticsDTO statisticsDTO);

    StatisticsDTO selectStatisticsTrainMember(StatisticsDTO reqDto);

    List<RegularDTO.TrainInfo> selectRegularTrainInfoList(RegularDTO info);

    void deleteTrainTemplate(String gbn);

    Integer insertTrainTemplate(TrainTemplateDTO.TrainTemplateInfo info);

    List<TrainTemplateDTO.TrainTemplateInfo> selectTrainTemplateList(String major);

    List<RequestDTO> selectRequestList(SearchDTO searchDTO);

    RequestDTO selectRequestSingle(RequestDTO requestDTO);

    String getRequestSeq();

    Integer insertRequest(RequestDTO requestDTO);

    Integer updateRequest(RequestDTO requestDTO);

    Integer deleteRequest(RequestDTO requestDTO);

    List<RequestReplyDTO> selectReplyList(String requestSeq);

    String getReplySeq();

    Integer insertReply(RequestReplyDTO requestReplyDTO);

    Integer deleteReply(RequestReplyDTO requestReplyDTO);

    Integer updateRequestProgressStep(RequestDTO info);

    Integer updateRequestCompleteExpect(RequestDTO info);

    List<TrainDTO> selectTrainActive(TrainDTO trainDTO);

    Integer updateBoarderTrainSeq(BoarderDTO boarderDTO);

    Integer updateFrpTrainSeq(FrpDTO frpDTO);

    Integer updateOutboarderTrainSeq(OutboarderDTO updDTO);

    Integer updateInboarderTrainSeq(InboarderDTO inboarderDTO);

    Integer updateSailyachtTrainSeq(SailyachtDTO sailyachtDTO);

    Integer updateHighHorsePowerTrainSeq(HighHorsePowerDTO highHorsePowerDTO);

    void updateTrainApplyCntAdd(String newTrainSeq);

    void updatePaymentTrainChange(PaymentDTO updPayDTO);

    TrainUpdateDTO selectAllTrainInfo(String tableSeq);

    List<SmsSendDTO> selectSmsSendHighhorsepowerList();

    List<SmsSendDTO> selectSmsSendSterndriveList();

    Integer updateSterndriveTrainSeq(SterndriveDTO sterndriveDTO);

    List<HighHorsePowerDTO> selectHighhorsepowerList(SearchDTO searchDTO);

    HighHorsePowerDTO selectHighhorsepowerSingle(String seq);

    Integer updateHighhorsepowerApplyStatus(HighHorsePowerDTO info);

    List<HighHorsePowerDTO> selectExcelHighhorsepowerDetailList();

    List<SterndriveDTO> selectSterndriveList(SearchDTO searchDTO);

    SterndriveDTO selectSterndriveSingle(String seq);

    Integer updateSterndriveApplyStatus(SterndriveDTO info);

    List<SterndriveDTO> selectExcelSterndriveDetailList();

    List<SmsSendDTO> selectSmsSendHighSelfList();

    List<SmsSendDTO> selectSmsSendHighSpecialList();

    Integer updateHighSelfTrainSeq(HighSelfDTO highSelfDTO);

    Integer updateHighSpecialTrainSeq(HighSpecialDTO highSpecialDTO);

    List<HighSelfDTO> selectHighSelfList(SearchDTO searchDTO);

    HighSelfDTO selectHighSelfSingle(String seq);

    Integer updateHighSelfApplyStatus(HighSelfDTO info);

    List<HighSelfDTO> selectExcelHighSelfDetailList();

    List<HighSpecialDTO> selectHighSpecialList(SearchDTO searchDTO);

    HighSpecialDTO selectHighSpecialSingle(String seq);

    Integer updateHighSpecialApplyStatus(HighSpecialDTO info);

    List<HighSpecialDTO> selectExcelHighSpecialDetailList();

    List<SternSpecialDTO> selectSternSpecialList(SearchDTO searchDTO);

    SternSpecialDTO selectSternSpecialSingle(String seq);

    Integer updateSternSpecialApplyStatus(SternSpecialDTO info);

    List<SternSpecialDTO> selectExcelSternSpecialDetailList();

    List<SmsSendDTO> selectSmsSendSternSpecialList();

    Integer updateSternSpecialTrainSeq(SternSpecialDTO sternSpecialDTO);
}