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

    List<TrainDTO> processSelectTrainScheduleList(SearchDTO searchDTO);

    List<TrainDTO> processSelectTrainScheduleCalendarList(TrainDTO trainDTO);

    ResponseDTO processInsertRegular(RegularDTO regularDTO);

    List<CommunityDTO> processSelectCommunityList(SearchDTO searchDTO);

    CommunityDTO processSelectCommunitySingle(String seq);

    ResponseDTO processUpdateCommunityViewCnt(String seq);

    RecommendDTO processSelectRecommendSingle(RecommendDTO recommendReqDTO);

    ResponseDTO processUpdateRecommend(RecommendDTO recommendDTO);

    List<ReplyDTO> processSelectReplyList(ReplyDTO replyReqDTO);

    ResponseDTO processInsertReply(ReplyDTO replyDTO);

    ResponseDTO processDeleteReply(ReplyDTO replyDTO);

    ResponseDTO processInsertCommunity(CommunityDTO communityDTO);

    ResponseDTO processDeleteCommunity(CommunityDTO communityDTO);

    List<CommunityDTO> processSelectPostCommunityList(String id);

    List<ReplyDTO> processSelectPostReplyList(String id);

    ResponseDTO processUpdateCommunity(CommunityDTO communityDTO);

    RegularDTO processSelectPreRegularSingle(RegularDTO regularDTO);

    MemberDTO processSelectMemberSeqSingle(String seq);

    TrainDTO processSelectTrainSingle(String trainSeq);

    ResponseDTO processInsertPayment(PaymentDTO paymentDTO);

    ResponseDTO processUpdateRegularPayStatus(RegularDTO regularDTO);

    List<EduApplyInfoDTO> processSelectEduApplyInfoList(String memberSeq);

    List<EduApplyInfoDTO> processSelectEduApplyInfoCancelList(String memberSeq);

    RegularDTO processSelectRegularSingle(String seq);

    ResponseDTO processUpdateRegular(RegularDTO regularDTO);

    List<PaymentDTO> processSelectPaymentList(String memberSeq);

    ResponseDTO processInsertBoarder(BoarderDTO boarderDTO);

    ResponseDTO processUpdateBoarderPayStatus(BoarderDTO boarderDTO);

    BoarderDTO processSelectBoarderSingle(String seq);

    List<CareerDTO> processSelectCareerList(CareerDTO careerDTO);

    List<LicenseDTO> processSelectLicenseList(LicenseDTO licenseDTO);

    ResponseDTO processUpdateBoarder(BoarderDTO boarderDTO);

    ResponseDTO processDeleteCareer(CareerDTO careerDTO);

    ResponseDTO processDeleteLicense(LicenseDTO licenseDTO);

    ResponseDTO processInsertFrp(FrpDTO frpDTO);

    ResponseDTO processUpdateFrpPayStatus(FrpDTO frpDTO);

    FrpDTO processSelectFrpSingle(String seq);

    ResponseDTO processUpdateFrp(FrpDTO frpDTO);

    ResponseDTO processInsertInboarder(InboarderDTO inboarderDTO);

    ResponseDTO processUpdateInboarderPayStatus(InboarderDTO inboarderDTO);

    InboarderDTO processSelectInboarderSingle(String seq);

    ResponseDTO processUpdateInboarder(InboarderDTO inboarderDTO);

    ResponseDTO processInsertOutboarder(OutboarderDTO outboarderDTO);

    ResponseDTO processInsertSailyacht(SailyachtDTO sailyachtDTO);

    OutboarderDTO processSelectOutboarderSingle(String seq);

    SailyachtDTO processSelectSailyachtSingle(String seq);

    ResponseDTO processUpdateOutboarder(OutboarderDTO outboarderDTO);

    ResponseDTO processUpdateSailyacht(SailyachtDTO sailyachtDTO);

    ResponseDTO processUpdateOutboarderPayStatus(OutboarderDTO outboarderDTO);

    ResponseDTO processUpdateSailyachtPayStatus(SailyachtDTO sailyachtDTO);

    List<EmploymentDTO> processSelectEmploymentList(String gbn);

    List<FaqDTO> processSelectFaqList(SearchDTO searchDTO);

    ResponseDTO processDeleteFile(FileDTO fileDTO);

    Integer processUpdateTrainApplyCnt(String trainSeq);

    ResponseDTO processUpdateMemberWithdraw(MemberDTO memberDTO);

    Integer processSelectRegularPreCheck(RegularDTO regularDTO);

    Integer processSelectBoarderPreCheck(BoarderDTO boarderDTO);

    Integer processSelectFrpPreCheck(FrpDTO frpDTO);

    Integer processSelectInboarderPreCheck(InboarderDTO inboarderDTO);

    Integer processSelectOutboarderPreCheck(OutboarderDTO outboarderDTO);

    Integer processSelectSailyachtPreCheck(SailyachtDTO sailyachtDTO);

    void processUpdateTrainClosing(String todate);

    ResponseDTO processCheckMember(MemberDTO memberDTO);

    ResponseDTO processCheckCommunity(CommunityDTO communityDTO);

    ResponseDTO processCheckReply(ReplyDTO replyDTO);
}
