package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface EduMarineService {

    void logoutCheck(HttpSession session);

    void processStatisticsAccessor();

    List<PopupDTO> processSelectPopupList(PopupDTO popupDTO);

    List<BannerDTO> processSelectBannerList(BannerDTO bannerDTO);

    FileDTO processSelectFileIdSingle(FileDTO fileReq);

    List<TrainDTO> processSelectTrainList(TrainDTO trainDTO);

    List<NoticeDTO> processSelectNoticeList(NoticeDTO noticeDTO);

    List<PressDTO> processSelectPressList(PressDTO pressDTO);

    Integer checkDuplicateId(MemberDTO memberDTO);

    ResponseDTO processInsertMember(MemberDTO memberDTO);

    ResponseDTO processCheckMemberSingle(MemberDTO memberDTO);

    String getMemberEmail(MemberDTO memberDTO);

    ResponseDTO initMemberPassword(MemberDTO memberDTO);

    List<NoticeDTO> processSelectBoardNoticeList(SearchDTO searchDTO);

    ResponseDTO processUpdateBoardNoticeViewCnt(String seq);

    NoticeDTO processSelectBoardNoticeSingle(String seq);

    List<FileDTO> processSelectFileList(String seq);

    List<PressDTO> processSelectBoardPressList(SearchDTO searchDTO);

    ResponseDTO processUpdateBoardPressViewCnt(String seq);

    PressDTO processSelectBoardPressSingle(String seq);

    List<GalleryDTO> processSelectBoardGalleryList(SearchDTO searchDTO);

    List<MediaDTO> processSelectBoardMediaList(SearchDTO searchDTO);

    List<NewsletterDTO> processSelectBoardNewsList(SearchDTO searchDTO);

    ResponseDTO processUpdateBoardNewsViewCnt(String seq);

    NewsletterDTO processSelectBoardNewsSingle(String seq);

    List<JobDTO> processSelectJobReviewList(SearchDTO searchDTO);

    MemberDTO processSelectMemberSingle(String id);

    ResponseDTO processUpdateMember(MemberDTO memberDTO);

    ResumeDTO processSelectResumeSingle(String id);

    ResponseDTO processSaveResume(ResumeDTO resumeDTO);
}
