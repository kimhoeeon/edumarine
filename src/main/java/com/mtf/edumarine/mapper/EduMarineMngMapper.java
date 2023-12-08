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
}
