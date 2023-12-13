package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.*;
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
}
