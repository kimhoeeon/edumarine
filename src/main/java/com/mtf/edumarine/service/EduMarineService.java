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

    Integer processUpdatePaymentVbankNoti(PaymentDTO paymentDTO);

    PaymentDTO processSelectPaymentVbankInfo(PaymentDTO paymentDTO);

    List<TrainTemplateDTO.TrainTemplateInfo> processSelectTrainTemplateList(String major);

    Integer processSelectHighHorsePowerPreCheck(HighHorsePowerDTO highHorsePowerDTO);

    ResponseDTO processInsertHighHorsePower(HighHorsePowerDTO highHorsePowerDTO);

    ResponseDTO processUpdateHighHorsePowerPayStatus(HighHorsePowerDTO highHorsePowerDTO);

    Integer processSelectSterndrivePreCheck(SterndriveDTO sterndriveDTO);

    ResponseDTO processInsertSterndrive(SterndriveDTO sterndriveDTO);

    ResponseDTO processUpdateSterndrivePayStatus(SterndriveDTO sterndriveDTO);

    HighHorsePowerDTO processSelectHighHorsePowerSingle(String seq);

    ResponseDTO processUpdateHighhorsepower(HighHorsePowerDTO highHorsePowerDTO);

    SterndriveDTO processSelectSterndriveSingle(String seq);

    ResponseDTO processUpdateSterndrive(SterndriveDTO sterndriveDTO);

    Integer processSelectHighSelfPreCheck(HighSelfDTO highselfDTO);

    ResponseDTO processInsertHighSelf(HighSelfDTO highselfDTO);

    HighSelfDTO processSelectHighSelfSingle(String seq);

    ResponseDTO processUpdateHighSelf(HighSelfDTO highselfDTO);

    Integer processSelectHighSpecialPreCheck(HighSpecialDTO highSpecialDTO);

    ResponseDTO processInsertHighSpecial(HighSpecialDTO highSpecialDTO);

    HighSpecialDTO processSelectHighSpecialSingle(String seq);

    ResponseDTO processUpdateHighSpecial(HighSpecialDTO highSpecialDTO);

    ResponseDTO processUpdateHighSelfPayStatus(HighSelfDTO highSelfDTO);

    ResponseDTO processUpdateHighSpecialPayStatus(HighSpecialDTO highSpecialDTO);

    Integer processSelectSternSpecialPreCheck(SternSpecialDTO sternSpecialDTO);

    ResponseDTO processInsertSternSpecial(SternSpecialDTO sternSpecialDTO);

    SternSpecialDTO processSelectSternSpecialSingle(String seq);

    ResponseDTO processUpdateSternSpecial(SternSpecialDTO sternSpecialDTO);

    ResponseDTO processUpdateSternSpecialPayStatus(SternSpecialDTO sternSpecialDTO);

    Integer processSelectBasicPreCheck(BasicDTO basicDTO);

    ResponseDTO processInsertBasic(BasicDTO basicDTO);

    BasicDTO processSelectBasicSingle(String seq);

    ResponseDTO processUpdateBasic(BasicDTO basicDTO);

    ResponseDTO processUpdateBasicPayStatus(BasicDTO basicDTO);

    Integer processSelectEmergencyPreCheck(EmergencyDTO emergencyDTO);

    ResponseDTO processInsertEmergency(EmergencyDTO emergencyDTO);

    EmergencyDTO processSelectEmergencySingle(String seq);

    ResponseDTO processUpdateEmergency(EmergencyDTO emergencyDTO);

    ResponseDTO processUpdateEmergencyPayStatus(EmergencyDTO emergencyDTO);

    Integer processSelectGeneratorPreCheck(GeneratorDTO generatorDTO);

    ResponseDTO processInsertGenerator(GeneratorDTO generatorDTO);

    GeneratorDTO processSelectGeneratorSingle(String seq);

    ResponseDTO processUpdateGenerator(GeneratorDTO generatorDTO);

    ResponseDTO processUpdateGeneratorPayStatus(GeneratorDTO generatorDTO);

    Integer processSelectCompetencyPreCheck(CompetencyDTO competencyDTO);

    ResponseDTO processInsertCompetency(CompetencyDTO competencyDTO);

    CompetencyDTO processSelectCompetencySingle(String seq);

    ResponseDTO processUpdateCompetency(CompetencyDTO competencyDTO);

    ResponseDTO processUpdateCompetencyPayStatus(CompetencyDTO competencyDTO);

    Integer processSelectFamtourinPreCheck(FamtourinDTO famtourinDTO);

    ResponseDTO processInsertFamtourin(FamtourinDTO famtourinDTO);

    FamtourinDTO processSelectFamtourinSingle(String seq);

    ResponseDTO processUpdateFamtourin(FamtourinDTO famtourinDTO);

    ResponseDTO processUpdateFamtourinPayStatus(FamtourinDTO famtourinDTO);

    Integer processSelectFamtouroutPreCheck(FamtouroutDTO famtouroutDTO);

    ResponseDTO processInsertFamtourout(FamtouroutDTO famtouroutDTO);

    FamtouroutDTO processSelectFamtouroutSingle(String seq);

    ResponseDTO processUpdateFamtourout(FamtouroutDTO famtouroutDTO);

    ResponseDTO processUpdateFamtouroutPayStatus(FamtouroutDTO famtouroutDTO);

    Integer processSelectElectroPreCheck(ElectroDTO electroDTO);

    ElectroDTO processSelectElectroSingle(String seq);

    ResponseDTO processInsertElectro(ElectroDTO electroDTO);

    ResponseDTO processUpdateElectro(ElectroDTO electroDTO);

    ResponseDTO processUpdateElectroPayStatus(ElectroDTO electroDTO);

    /**
     * [UNIFIED] 신규 통합 교육 신청서 제출
     * @param dto 통합 신청 DTO
     * @return ResponseDTO (customValue에 app_seq 반환)
     */
    ResponseDTO processInsertUnifiedApplication(ApplicationUnifiedDTO dto);

    /**
     * [UNIFIED] 신규 통합 교육 신청서 상태 업데이트 (결제/취소 공용)
     * @param dto 통합 신청 DTO (seq, applyStatus, cancelReason 등)
     * @return ResponseDTO
     */
    ResponseDTO processUpdateUnifiedApplicationPayStatus(ApplicationUnifiedDTO dto);

    ApplicationUnifiedDTO processSelectUnifiedApplicationSingle(String seq);

    ResponseDTO processUpdateUnifiedApplication(ApplicationUnifiedDTO dto);
}