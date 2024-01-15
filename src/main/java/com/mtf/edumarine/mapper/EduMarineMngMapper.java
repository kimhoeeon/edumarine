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
}
