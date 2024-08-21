package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The interface GoingSool service.
 */
public interface EduMarineMngService {

    /**
     * Login check customer dto.
     *
     * @param adminDTO the admin dto
     * @param session     the session
     * @return the customer dto
     */
    AdminDTO login(AdminDTO adminDTO, HttpSession session);

    /**
     * Logout check.
     *
     * @param session the session
     */
    void logoutCheck(HttpSession session);

    ResponseDTO processUpdateFileUserId(FileDTO fileDTO);

    List<FileDTO> processSelectFileUserIdList(FileDTO fileDTO);

    FileResponseDTO processUpdateFileUseN(FileDTO fileDTO);

    InistdpayCancelResponseDTO processApplyPaymentCancelApi(InistdpayCancelRequestDTO inistdpayCancelRequestDTO);

    InistdpayCancelResponseDTO processApplyPaymentVbankCancelApi(InistdpayCancelRequestDTO inistdpayCancelRequestDTO);

    FileResponseDTO processInsertFileInfo(FileDTO fileDTO);

    List<?> uploadExcelFile(MultipartFile file);

    ResponseDTO processMailSend(MailRequestDTO mailRequestDTO);

    List<NoticeDTO> processSelectNoticeList(SearchDTO searchDTO);

    NoticeDTO processSelectNoticeSingle(NoticeDTO noticeDTO);

    ResponseDTO processDeleteNotice(NoticeDTO noticeDTO);

    ResponseDTO processUpdateNotice(NoticeDTO noticeDTO);

    ResponseDTO processInsertNotice(NoticeDTO noticeDTO);

    List<PressDTO> processSelectPressList(SearchDTO searchDTO);

    PressDTO processSelectPressSingle(PressDTO pressDTO);

    ResponseDTO processDeletePress(PressDTO pressDTO);

    ResponseDTO processUpdatePress(PressDTO pressDTO);

    ResponseDTO processInsertPress(PressDTO pressDTO);

    List<GalleryDTO> processSelectGalleryList(SearchDTO searchDTO);

    GalleryDTO processSelectGallerySingle(GalleryDTO galleryDTO);

    ResponseDTO processDeleteGallery(GalleryDTO galleryDTO);

    ResponseDTO processUpdateGallery(GalleryDTO galleryDTO);

    ResponseDTO processInsertGallery(GalleryDTO galleryDTO);

    List<MediaDTO> processSelectMediaList(SearchDTO searchDTO);

    MediaDTO processSelectMediaSingle(MediaDTO mediaDTO);

    ResponseDTO processDeleteMedia(MediaDTO mediaDTO);

    ResponseDTO processUpdateMedia(MediaDTO mediaDTO);

    ResponseDTO processInsertMedia(MediaDTO mediaDTO);

    List<NewsletterDTO> processSelectNewsletterList(SearchDTO searchDTO);

    NewsletterDTO processSelectNewsletterSingle(NewsletterDTO newsletterDTO);

    ResponseDTO processDeleteNewsletter(NewsletterDTO newsletterDTO);

    ResponseDTO processUpdateNewsletter(NewsletterDTO newsletterDTO);

    ResponseDTO processInsertNewsletter(NewsletterDTO newsletterDTO);

    List<AnnouncementDTO> processSelectAnnouncementList(SearchDTO searchDTO);

    AnnouncementDTO processSelectAnnouncementSingle(AnnouncementDTO announcementDTO);

    ResponseDTO processDeleteAnnouncement(AnnouncementDTO announcementDTO);

    ResponseDTO processUpdateAnnouncement(AnnouncementDTO announcementDTO);

    ResponseDTO processInsertAnnouncement(AnnouncementDTO announcementDTO);

    List<JobDTO> processSelectJobList(SearchDTO searchDTO);

    JobDTO processSelectJobSingle(JobDTO jobDTO);

    ResponseDTO processDeleteJob(JobDTO jobDTO);

    ResponseDTO processUpdateJob(JobDTO jobDTO);

    ResponseDTO processInsertJob(JobDTO jobDTO);

    List<CommunityDTO> processSelectCommunityList(SearchDTO searchDTO);

    CommunityDTO processSelectCommunitySingle(CommunityDTO communityDTO);

    ResponseDTO processDeleteCommunity(CommunityDTO communityDTO);

    ResponseDTO processUpdateCommunity(CommunityDTO communityDTO);

    ResponseDTO processInsertCommunity(CommunityDTO communityDTO);

    List<PopupDTO> processSelectPopupList(SearchDTO searchDTO);

    ResponseDTO processDeletePopup(PopupDTO popupDTO);

    PopupDTO processSelectPopupSingle(PopupDTO popupDTO);

    ResponseDTO processUpdatePopup(PopupDTO popupDTO);

    ResponseDTO processInsertPopup(PopupDTO popupDTO);

    List<BannerDTO> processSelectBannerList(SearchDTO searchDTO);

    BannerDTO processSelectBannerSingle(BannerDTO bannerDTO);

    ResponseDTO processDeleteBanner(BannerDTO bannerDTO);

    ResponseDTO processUpdateBanner(BannerDTO bannerDTO);

    ResponseDTO processInsertBanner(BannerDTO bannerDTO);

    List<MemberDTO> processSelectMemberList(SearchDTO searchDTO);

    MemberDTO processSelectMemberSingle(MemberDTO memberDTO);

    ResponseDTO processDeleteMember(MemberDTO memberDTO);

    ResponseDTO processUpdateMember(MemberDTO memberDTO);

    ResponseDTO processInsertMember(MemberDTO memberDTO);

    List<RegularDTO> processSelectRegularList(SearchDTO searchDTO);

    RegularDTO processSelectRegularSingle(RegularDTO regularDTO);

    ResponseDTO processDeleteRegular(RegularDTO regularDTO);

    ResponseDTO processUpdateRegular(RegularDTO regularDTO);

    ResponseDTO processInsertRegular(RegularDTO regularDTO);

    List<InboarderDTO> processSelectInboarderList(SearchDTO searchDTO);

    InboarderDTO processSelectInboarderSingle(InboarderDTO inboarderDTO);

    ResponseDTO processDeleteInboarder(InboarderDTO inboarderDTO);

    ResponseDTO processUpdateInboarder(InboarderDTO inboarderDTO);

    ResponseDTO processInsertInboarder(InboarderDTO inboarderDTO);

    List<TrainDTO> processSelectTrainList(SearchDTO searchDTO);

    TrainDTO processSelectTrainSingle(TrainDTO trainDTO);

    ResponseDTO processDeleteTrain(TrainDTO trainDTO);

    ResponseDTO processUpdateTrain(TrainDTO trainDTO);

    ResponseDTO processInsertTrain(TrainDTO trainDTO);

    List<PaymentDTO> processSelectPaymentList(SearchDTO searchDTO);

    PaymentDTO processSelectPaymentSingle(PaymentDTO paymentDTO);

    ResponseDTO processDeletePayment(PaymentDTO paymentDTO);

    ResponseDTO processUpdatePayment(PaymentDTO paymentDTO);

    ResponseDTO processInsertPayment(PaymentDTO paymentDTO);

    ResponseDTO processUpdatePayStatus(List<PaymentDTO> paymentList);

    List<SubscriberDTO> processSelectSubscriberList(SearchDTO searchDTO);

    SubscriberDTO processSelectSubscriberSingle(SubscriberDTO subscriberDTO);

    ResponseDTO processDeleteSubscriber(SubscriberDTO subscriberDTO);

    ResponseDTO processUpdateSubscriber(SubscriberDTO subscriberDTO);

    ResponseDTO processInsertSubscriber(SubscriberDTO subscriberDTO);

    List<SmsDTO> processSelectSmsList(SearchDTO searchDTO);

    List<TemplateDTO> processSelectSmsTemplateList();

    TemplateDTO processSelectSmsTemplateSingle(TemplateDTO templateDTO);

    ResponseDTO processSaveSmsTemplate(TemplateDTO templateDTO);

    ResponseDTO processDeleteSmsTemplate(TemplateDTO templateDTO);

    ResponseDTO processInsertSms(SmsDTO smsDTO);

    ResponseDTO processDeleteSms(SmsDTO smsDTO);

    SmsDTO processSelectSmsSingle(SmsDTO smsDTO);

    List<DownloadDTO> processSelectDownloadList(SearchDTO searchDTO);

    ResponseDTO processInsertDownload(DownloadDTO downloadDTO);

    List<TrashDTO> processSelectTrashList(SearchDTO searchDTO);

    ResponseDTO processSaveTrash(TrashDTO trashDTO);

    ResponseDTO processDeleteTrash(TrashDTO trashDTO);

    ResponseDTO processRestoreTrash(TrashDTO trashDTO);

    List<EmploymentDTO> processSelectEmploymentList(SearchDTO searchDTO);

    EmploymentDTO processSelectEmploymentSingle(EmploymentDTO employmentDTO);

    ResponseDTO processUpdateEmployment(EmploymentDTO employmentDTO);

    ResponseDTO processInsertEmployment(EmploymentDTO employmentDTO);

    List<BoarderDTO> processSelectBoarderList(SearchDTO searchDTO);

    ResponseDTO processUpdateBoarderApplyStatus(List<BoarderDTO> boarderList);

    List<FrpDTO> processSelectFrpList(SearchDTO searchDTO);

    ResponseDTO processUpdateFrpApplyStatus(List<FrpDTO> frpList);

    List<OutboarderDTO> processSelectOutboarderList(SearchDTO searchDTO);

    ResponseDTO processUpdateOutboarderApplyStatus(List<OutboarderDTO> outboarderList);

    List<SailyachtDTO> processSelectSailyachtList(SearchDTO searchDTO);

    ResponseDTO processUpdateSailyachtApplyStatus(List<SailyachtDTO> sailyachtList);

    ResponseDTO processUpdateInboarderApplyStatus(List<InboarderDTO> inboarderList);

    ResponseDTO processUpdateRegularApplyStatus(List<RegularDTO> regularList);

    ResponseDTO processUpdateRegularApplyStatusChange(List<RegularDTO> regularList);

    ResponseDTO processUpdateBoarderApplyStatusChange(List<BoarderDTO> boarderList);

    ResponseDTO processUpdateFrpApplyStatusChange(List<FrpDTO> frpList);

    ResponseDTO processUpdateOutboarderApplyStatusChange(List<OutboarderDTO> outboarderList);

    ResponseDTO processUpdateInboarderApplyStatusChange(List<InboarderDTO> inboarderList);

    ResponseDTO processUpdateSailyachtApplyStatusChange(List<SailyachtDTO> sailyachtList);

    List<AdminDTO> processSelectAdminMngList(SearchDTO searchDTO);

    AdminDTO processSelectAdminMngSingle(AdminDTO adminDTO);

    Integer processSelectAdminMngCheckDuplicateId(AdminDTO adminDTO);

    ResponseDTO processUpdateAdminMng(AdminDTO adminDTO);

    ResponseDTO processInsertAdminMng(AdminDTO adminDTO);

    ResponseDTO processCheckAdminMngValidYn(AdminDTO adminDTO);

    AdminDTO processSelectAdminMngSingleId(AdminDTO adminDTO);

    ResponseDTO processUpdateTrainEarlyClosing(TrainDTO trainDTO);

    BoarderDTO processSelectBoarderSingle(String seq);

    List<CareerDTO> processSelectCareerList(String boarderSeq);

    List<LicenseDTO> processSelectLicenseList(String boarderSeq);

    FrpDTO processSelectFrpSingle(String seq);

    OutboarderDTO processSelectOutboarderSingle(String seq);

    SailyachtDTO processSelectSailyachtSingle(String seq);

    List<ResumeDTO> processSelectResumeList(SearchDTO searchDTO);

    ResumeDTO processSelectResumeSingle(ResumeDTO resumeDTO);

    List<SmsSendDTO> processSelectSmsSendList(SearchDTO searchDTO);

    List<FaqDTO> processSelectFaqList(SearchDTO searchDTO);

    FaqDTO processSelectFaqSingle(FaqDTO faqDTO);

    ResponseDTO processUpdateFaq(FaqDTO faqDTO);

    ResponseDTO processInsertFaq(FaqDTO faqDTO);

    PaymentDTO processSelectTrainPaymentInfo(PaymentDTO paymentRequestDTO);

    Integer processCheckSubscriber(SubscriberDTO subscriberDTO);

    List<TrainDTO> processSelectTrainNextTime(TrainDTO trainDTO);

    List<MemberDTO> processSelectExcelMemberDetailList();

    List<RegularDTO> processSelectExcelRegularDetailList();

    List<BoarderDetailDTO> processSelectExcelBoarderDetailList();

    List<FrpDetailDTO> processSelectExcelFrpDetailList();

    List<OutboarderDetailDTO> processSelectExcelOutboarderDetailList();

    List<InboarderDetailDTO> processSelectExcelInboarderDetailList();

    List<SailyachtDetailDTO> processSelectExcelSailyachtDetailList();

    StatisticsDTO processSelectMemberCount(StatisticsDTO statisticsDTO);

    StatisticsDTO processSelectTrainCount(StatisticsDTO trainDTO);

    List<StatisticsDTO> processSelectStatisticsAccessorDay(StatisticsDTO reqDto);

    List<StatisticsDTO> processSelectStatisticsAccessorMonth(StatisticsDTO reqDto);

    List<StatisticsDTO> processSelectStatisticsAccessorWeek(StatisticsDTO reqDto);

    StatisticsDTO processSelectStatisticsTrainMember(StatisticsDTO reqDto);

    List<RegularDTO.TrainInfo> processSelectRegularTrainInfoList(RegularDTO info);

    ResponseDTO processSaveTrainTemplate(TrainTemplateDTO templateInfo);

    List<TrainTemplateDTO.TrainTemplateInfo> processSelectTrainTemplateList(String major);

    List<RequestDTO> processSelectRequestList(SearchDTO searchDTO);

    RequestDTO processSelectRequestSingle(RequestDTO requestDTO);

    ResponseDTO processInsertRequest(RequestDTO requestDTO);

    ResponseDTO processUpdateRequest(RequestDTO requestDTO);

    ResponseDTO processDeleteRequest(RequestDTO requestDTO);

    List<RequestReplyDTO> processSelectReplyList(String requestSeq);

    ResponseDTO processInsertReply(RequestReplyDTO requestReplyDTO);

    ResponseDTO processDeleteReply(RequestReplyDTO requestReplyDTO);

    ResponseDTO processUpdateRequestProgressStep(List<RequestDTO> requestList);

    ResponseDTO processUpdateRequestCompleteExpect(List<RequestDTO> requestList);

    List<TrainDTO> processSelectTrainActive(TrainDTO trainDTO);

    ResponseDTO processUpdateTrainChange(TrainUpdateDTO trainUpdateDTO);

    List<HighHorsePowerDTO> processSelectHighhorsepowerList(SearchDTO searchDTO);

    HighHorsePowerDTO processSelectHighhorsepowerSingle(String seq);

    ResponseDTO processUpdateHighhorsepowerApplyStatus(List<HighHorsePowerDTO> highHorsePowerList);

    ResponseDTO processUpdateHighhorsepowerApplyStatusChange(List<HighHorsePowerDTO> highHorsePowerList);

    List<HighHorsePowerDTO> processSelectExcelHighhorsepowerDetailList();

    List<SterndriveDTO> processSelectSterndriveList(SearchDTO searchDTO);

    SterndriveDTO processSelectSterndriveSingle(String seq);

    ResponseDTO processUpdateSterndriveApplyStatus(List<SterndriveDTO> sterndriveList);

    ResponseDTO processUpdateSterndriveApplyStatusChange(List<SterndriveDTO> sterndriveList);

    List<SterndriveDTO> processSelectExcelSterndriveDetailList();

    List<HighSelfDTO> processSelectHighSelfList(SearchDTO searchDTO);

    ResponseDTO processUpdateHighSelfApplyStatus(List<HighSelfDTO> highSelfList);

    ResponseDTO processUpdateHighSelfApplyStatusChange(List<HighSelfDTO> highSelfList);

    HighSelfDTO processSelectHighSelfSingle(String seq);

    List<HighSelfDTO> processSelectExcelHighSelfDetailList();

    List<HighSpecialDTO> processSelectHighSpecialList(SearchDTO searchDTO);

    ResponseDTO processUpdateHighSpecialApplyStatus(List<HighSpecialDTO> highSpecialList);

    ResponseDTO processUpdateHighSpecialApplyStatusChange(List<HighSpecialDTO> highSpecialList);

    HighSpecialDTO processSelectHighSpecialSingle(String seq);

    List<HighSpecialDTO> processSelectExcelHighSpecialDetailList();

    List<SternSpecialDTO> processSelectSternSpecialList(SearchDTO searchDTO);

    ResponseDTO processUpdateSternSpecialApplyStatus(List<SternSpecialDTO> sternspecialList);

    ResponseDTO processUpdateSternSpecialApplyStatusChange(List<SternSpecialDTO> sternspecialList);

    SternSpecialDTO processSelectSternSpecialSingle(String seq);

    List<SternSpecialDTO> processSelectExcelSternSpecialDetailList();

    List<BasicDTO> processSelectBasicList(SearchDTO searchDTO);

    ResponseDTO processUpdateBasicApplyStatus(List<BasicDTO> basicList);

    ResponseDTO processUpdateBasicApplyStatusChange(List<BasicDTO> basicList);

    BasicDTO processSelectBasicSingle(String seq);

    List<BasicDetailDTO> processSelectExcelBasicDetailList();

    List<EmergencyDTO> processSelectEmergencyList(SearchDTO searchDTO);

    ResponseDTO processUpdateEmergencyApplyStatus(List<EmergencyDTO> emergencyList);

    ResponseDTO processUpdateEmergencyApplyStatusChange(List<EmergencyDTO> emergencyList);

    EmergencyDTO processSelectEmergencySingle(String seq);

    List<EmergencyDetailDTO> processSelectExcelEmergencyDetailList();
}