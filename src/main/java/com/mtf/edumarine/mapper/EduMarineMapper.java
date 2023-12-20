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

    ResumeDTO selectResumeSingle(String id);

    Integer updateResume(ResumeDTO resumeDTO);

    String getResumeSeq();

    Integer insertResume(ResumeDTO resumeDTO);
}
