package com.mtf.edumarine.service.impl;

import lombok.extern.slf4j.Slf4j;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.mapper.EduMarineMapper;
import com.mtf.edumarine.mapper.UnifiedMapper;
import com.mtf.edumarine.service.EduMarineService;
import com.mtf.edumarine.util.HtmlSanitizer;
import com.mtf.edumarine.util.PasswordUtil;
import com.mtf.edumarine.util.StringUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class EduMarineServiceImpl implements EduMarineService {

    private static final String STR_RESULT_H = "%s - %s";

    //private final SqlSession sqlSession;

    @Setter(onMethod_ = {@Autowired})
    private EduMarineMapper eduMarineMapper;

    private final UnifiedMapper unifiedMapper;

    public EduMarineServiceImpl(EduMarineMapper km, UnifiedMapper unifiedMapper) {
        this.eduMarineMapper = km;
        this.unifiedMapper = unifiedMapper; // [신규 추가]
    }

    @Override
    public void logoutCheck(HttpSession session) {
        session.invalidate(); // 세션 초기화
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public void processStatisticsAccessor() {

        try {
            String joinYear = String.valueOf(LocalDateTime.now().getYear());
            String inDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")); // yyyy-MM-dd HH:mm:ss
            StatisticsDTO reqDto = new StatisticsDTO();
            reqDto.setGbn("Accessor");
            reqDto.setJoinYear(joinYear);
            reqDto.setInDttm(inDttm);
            String seq = eduMarineMapper.checkStatisticsAccessor(reqDto);
            if(seq != null){ /* update */
                reqDto.setSeq(seq);
                eduMarineMapper.updateStatisticsAccessor(reqDto);
            }else{ /* insert */
                reqDto.setInCount("1");
                eduMarineMapper.insertStatisticsAccessor(reqDto);
            }

        }catch (Exception e){
            String eMessage = "[main] processStatisticsAccessor Error : ";
            log.error("[processStatisticsAccessor] error", e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public void processUpdateTrainClosing(String todate) {

        try {
            eduMarineMapper.updateTrainClosing(todate);
        }catch (Exception e){
            String eMessage = "[main] processUpdateTrainClosing Error : ";
            log.error("[processUpdateTrainClosing] error", e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PopupDTO> processSelectPopupList(PopupDTO popupDTO) {
        return eduMarineMapper.selectPopupList(popupDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BannerDTO> processSelectBannerList(BannerDTO bannerDTO) {
        return eduMarineMapper.selectBannerList(bannerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ApplicationUnifiedDTO processSelectApplicationUnifiedSingle(String seq) {
        return unifiedMapper.selectApplicationUnifiedSingle(seq);
    }

    @Override
    public ResponseDTO processUpdateUnifiedApplication(ApplicationUnifiedDTO dto) {
        ResponseDTO response = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            int result = unifiedMapper.updateUnifiedApplication(dto);
            if (result == 0) {
                throw new Exception("Update Failed. Seq: " + dto.getSeq());
            }
            // (필요 시) 경력/자격증 등 하위 테이블 업데이트 로직 추가 가능
        } catch (Exception e) {
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[수정 오류] " + e.getMessage();
        }

        response.setResultCode(resultCode);
        response.setResultMessage(resultMessage);
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainList(TrainDTO trainDTO) {
        return eduMarineMapper.selectTrainList(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public TrainDTO processSelectTrainSingle(String trainSeq) {
        return eduMarineMapper.selectTrainSingle(trainSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainScheduleList(SearchDTO searchDTO) {
        return eduMarineMapper.selectTrainScheduleList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainScheduleCalendarList(TrainDTO trainDTO) {
        return eduMarineMapper.selectTrainScheduleCalendarList(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NoticeDTO> processSelectNoticeList(NoticeDTO noticeDTO) {
        return eduMarineMapper.selectNoticeList(noticeDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PressDTO> processSelectPressList(PressDTO pressDTO) {
        return eduMarineMapper.selectPressList(pressDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseDTO processCheckMember(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer check = eduMarineMapper.checkMember(memberDTO);
            responseDTO.setCustomValue(String.valueOf(check));
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[login] processLoginExhibit Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseDTO processCheckCommunity(CommunityDTO communityDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            String inDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // yyyy-MM-dd
            communityDTO.setWriteDate(inDttm);
            Integer check = eduMarineMapper.checkCommunity(communityDTO);
            responseDTO.setCustomValue(String.valueOf(check));
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[Exception] processCheckCommunity Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseDTO processCheckReply(ReplyDTO replyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            String inDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // yyyy-MM-dd
            replyDTO.setWriteDate(inDttm);
            Integer check = eduMarineMapper.checkReply(replyDTO);
            responseDTO.setCustomValue(String.valueOf(check));
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[Exception] processCheckReply Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseDTO processCheckMemberSingle(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            String getSalt = eduMarineMapper.getMemberSalt(memberDTO.getId());
            //암호화
            String pw_encrypt = SHA512(memberDTO.getPassword(), getSalt);
            memberDTO.setPassword(pw_encrypt);

            Integer loginCheck = eduMarineMapper.checkMemberSingle(memberDTO);
            if(loginCheck == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "ID not found";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[login] processLoginExhibit Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer checkDuplicateId(MemberDTO memberDTO) {
        return eduMarineMapper.checkDuplicateId(memberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertMember(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String password = memberDTO.getPassword();

            //salt값 생성
            String salt = Salt();
            memberDTO.setSalt(salt);

            //암호화
            String pw_encrypt = SHA512(password, salt);
            memberDTO.setPassword(pw_encrypt);

            String getSeq = eduMarineMapper.getMemberSeq();
            memberDTO.setSeq(getSeq);

            result = eduMarineMapper.insertMember(memberDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Member Data Insert Fail]";
            }else{
                RegularDTO regularDTO = new RegularDTO();
                // where
                regularDTO.setName(memberDTO.getName());
                regularDTO.setPhone(memberDTO.getPhone());

                // set
                regularDTO.setMemberSeq(getSeq);
                regularDTO.setEmail(memberDTO.getEmail());

                Integer regularResult = eduMarineMapper.updatePreRegularInfo(regularDTO);

                /*if(regularResult == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Regular Data Update Fail]";
                }*/
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertMember ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertMember] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String getMemberEmail(MemberDTO memberDTO) {
        return eduMarineMapper.getMemberEmail(memberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO initMemberPassword(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            // 2026-10-30 보안 조치(C-5): 고정 비밀번호 대신 컨트롤러가 생성한 임시 비밀번호 사용 (없으면 여기서 생성)
            String password = memberDTO.getPassword();
            if (password == null || password.trim().isEmpty()) {
                password = PasswordUtil.generateTempPassword(10);
            }
            //salt값 생성
            String salt = Salt();
            memberDTO.setSalt(salt);

            //암호화
            String pw_encrypt = SHA512(password, salt);
            memberDTO.setPassword(pw_encrypt);

            Integer result = eduMarineMapper.initMemberPassword(memberDTO);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[Find PW] initMemberPassword Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public MemberDTO processSelectMemberSingle(String id) {
        return eduMarineMapper.selectMemberSingle(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public MemberDTO processSelectMemberSeqSingle(String seq) {
        return eduMarineMapper.selectMemberSeqSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NoticeDTO> processSelectBoardNoticeList(SearchDTO searchDTO) {
        return eduMarineMapper.selectBoardNoticeList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoardNoticeViewCnt(String seq) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBoardNoticeViewCnt(seq);

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBoardNoticeViewCnt : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public NoticeDTO processSelectBoardNoticeSingle(String seq) {
        return eduMarineMapper.selectBoardNoticeSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PressDTO> processSelectBoardPressList(SearchDTO searchDTO) {
        return eduMarineMapper.selectBoardPressList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoardPressViewCnt(String seq) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBoardPressViewCnt(seq);

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBoardPressViewCnt : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PressDTO processSelectBoardPressSingle(String seq) {
        return eduMarineMapper.selectBoardPressSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<GalleryDTO> processSelectBoardGalleryList(SearchDTO searchDTO) {
        return eduMarineMapper.selectBoardGalleryList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<MediaDTO> processSelectBoardMediaList(SearchDTO searchDTO) {
        return eduMarineMapper.selectBoardMediaList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NewsletterDTO> processSelectBoardNewsList(SearchDTO searchDTO) {
        return eduMarineMapper.selectBoardNewsList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoardNewsViewCnt(String seq) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBoardNewsViewCnt(seq);

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBoardNewsViewCnt : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public NewsletterDTO processSelectBoardNewsSingle(String seq) {
        return eduMarineMapper.selectBoardNewsSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<JobDTO> processSelectJobReviewList(SearchDTO searchDTO) {
        return eduMarineMapper.selectJobReviewList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateMember(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            if(memberDTO.getPasswordChangeYn() != null){

                String password = memberDTO.getPassword();

                //salt값 생성
                String salt = Salt();
                memberDTO.setSalt(salt);

                //암호화
                String pw_encrypt = SHA512(password, salt);
                memberDTO.setPassword(pw_encrypt);

                //update
                Integer result = eduMarineMapper.updateMember(memberDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                }
            }else{
                String id = memberDTO.getId();

                String getSalt = eduMarineMapper.getMemberSalt(id);
                //암호화
                String pw_encrypt = SHA512(memberDTO.getPassword(), getSalt);

                MemberDTO memberInfo = eduMarineMapper.selectMemberSingle(id);
                if(!Objects.equals(pw_encrypt, memberInfo.getPassword())){
                    resultCode = "99";
                    resultMessage = "기존 비밀번호와 입력하신 비밀번호가 다릅니다.<br>비밀번호 변경도 진행하시겠습니까?";
                }else{
                    //update
                    Integer result = eduMarineMapper.updateMember(memberDTO);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail]";
                    }
                }
            }
            
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[Exception] processUpdateMember Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateMemberWithdraw(MemberDTO memberDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {

            Integer result = eduMarineMapper.updateMemberWithdraw(memberDTO);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail]";
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[Exception] processUpdateMemberWithdraw Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processSaveResume(ResumeDTO resumeDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            String seq = resumeDTO.getSeq();

            if(resumeDTO.getSeq() != null && !"".equals(resumeDTO.getSeq())){
                //update
                Integer result = eduMarineMapper.updateResume(resumeDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                }
            }else{
                //insert
                seq = eduMarineMapper.getResumeSeq();
                resumeDTO.setSeq(seq);
                Integer result = eduMarineMapper.insertResume(resumeDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                }
            }

            responseDTO.setCustomValue(seq);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[Exception] processSaveResume Error : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResumeDTO processSelectResumeSingle(String memberSeq) {
        return eduMarineMapper.selectResumeSingle(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectRegularPreCheck(RegularDTO regularDTO) {
        return eduMarineMapper.selectRegularPreCheck(regularDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectBoarderPreCheck(BoarderDTO boarderDTO) {
        return eduMarineMapper.selectBoarderPreCheck(boarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectFrpPreCheck(FrpDTO frpDTO) {
        return eduMarineMapper.selectFrpPreCheck(frpDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectBasicPreCheck(BasicDTO basicDTO) {
        return eduMarineMapper.selectBasicPreCheck(basicDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectEmergencyPreCheck(EmergencyDTO emergencyDTO) {
        return eduMarineMapper.selectEmergencyPreCheck(emergencyDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectGeneratorPreCheck(GeneratorDTO generatorDTO) {
        return eduMarineMapper.selectGeneratorPreCheck(generatorDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectCompetencyPreCheck(CompetencyDTO competencyDTO) {
        return eduMarineMapper.selectCompetencyPreCheck(competencyDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectFamtourinPreCheck(FamtourinDTO famtourinDTO) {
        return eduMarineMapper.selectFamtourinPreCheck(famtourinDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectFamtouroutPreCheck(FamtouroutDTO famtouroutDTO) {
        return eduMarineMapper.selectFamtouroutPreCheck(famtouroutDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectElectroPreCheck(ElectroDTO electroDTO) {
        return eduMarineMapper.selectElectroPreCheck(electroDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectInboarderPreCheck(InboarderDTO inboarderDTO) {
        return eduMarineMapper.selectInboarderPreCheck(inboarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectOutboarderPreCheck(OutboarderDTO outboarderDTO) {
        return eduMarineMapper.selectOutboarderPreCheck(outboarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectSailyachtPreCheck(SailyachtDTO sailyachtDTO) {
        return eduMarineMapper.selectSailyachtPreCheck(sailyachtDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectHighHorsePowerPreCheck(HighHorsePowerDTO highHorsePowerDTO) {
        return eduMarineMapper.selectHighHorsePowerPreCheck(highHorsePowerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectHighSelfPreCheck(HighSelfDTO highSelfDTO) {
        return eduMarineMapper.selectHighSelfPreCheck(highSelfDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectHighSpecialPreCheck(HighSpecialDTO highSpecialDTO) {
        return eduMarineMapper.selectHighSpecialPreCheck(highSpecialDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectSterndrivePreCheck(SterndriveDTO sterndriveDTO) {
        return eduMarineMapper.selectSterndrivePreCheck(sterndriveDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectSternSpecialPreCheck(SternSpecialDTO sternSpecialDTO) {
        return eduMarineMapper.selectSternSpecialPreCheck(sternSpecialDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertRegular(RegularDTO regularDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(regularDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(regularDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getRegularSeq();
                    regularDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertRegular(regularDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertRegular ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertRegular] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RegularDTO processSelectPreRegularSingle(RegularDTO regularDTO) {
        return eduMarineMapper.selectPreRegularSingle(regularDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CommunityDTO> processSelectCommunityList(SearchDTO searchDTO) {
        return eduMarineMapper.selectCommunityList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public CommunityDTO processSelectCommunitySingle(String seq) {
        CommunityDTO info = eduMarineMapper.selectCommunitySingle(seq);
        if (info != null) {
            // 2026-10-30 보안 조치(H-4): 기존 저장분 포함 본문 정화 후 표시
            info.setContent(HtmlSanitizer.clean(info.getContent()));
        }
        return info;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCommunityViewCnt(String seq) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateCommunityViewCnt(seq);

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateCommunityViewCnt : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RecommendDTO processSelectRecommendSingle(RecommendDTO recommendReqDTO) {
        return eduMarineMapper.selectRecommendSingle(recommendReqDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRecommend(RecommendDTO recommendDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            RecommendDTO info = eduMarineMapper.selectRecommendSingle(recommendDTO);
            if(info != null){
                Integer result = eduMarineMapper.deleteRecommend(info.getSeq());
            }else{
                Integer result = eduMarineMapper.insertRecommend(recommendDTO);
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateRecommend : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<ReplyDTO> processSelectReplyList(ReplyDTO replyReqDTO) {
        return eduMarineMapper.selectReplyList(replyReqDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertReply(ReplyDTO replyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;

        try {
            String getSeq = eduMarineMapper.getReplySeq();
            replyDTO.setSeq(getSeq);

            result = eduMarineMapper.insertReply(replyDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processInsertReply : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteReply(ReplyDTO replyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {

            Integer result = eduMarineMapper.deleteReply(replyDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Delete Fail] Reply";
            }else{
                if("0".equals(replyDTO.getDepthReplyNo())){
                    result = eduMarineMapper.deleteReplyToReply(replyDTO);
                }

                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] ReplyToReply";
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processDeleteReply : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertCommunity(CommunityDTO communityDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMapper.getCommunitySeq();
            communityDTO.setSeq(getSeq);

            String content = communityDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            content = HtmlSanitizer.clean(content); // 2026-10-30 보안 조치(H-4): 스크립트·이벤트 속성 제거
            communityDTO.setContent(content);
            result = eduMarineMapper.insertCommunity(communityDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertCommunity ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertCommunity] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteCommunity(CommunityDTO communityDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {

            Integer result = eduMarineMapper.deleteCommunity(communityDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Delete Fail]";
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processDeleteCommunity : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CommunityDTO> processSelectPostCommunityList(String id) {
        return eduMarineMapper.selectPostCommunityList(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<ReplyDTO> processSelectPostReplyList(String id) {
        return eduMarineMapper.selectPostReplyList(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCommunity(CommunityDTO communityDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String content = communityDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            content = HtmlSanitizer.clean(content); // 2026-10-30 보안 조치(H-4): 스크립트·이벤트 속성 제거
            communityDTO.setContent(content);
            result = eduMarineMapper.updateCommunity(communityDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateCommunity ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processUpdateCommunity] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertPayment(PaymentDTO paymentDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMapper.getPaymentSeq();
            paymentDTO.setSeq(getSeq);

            // 신청자 SEQ 추출
            String memberSeq = paymentDTO.getMemberSeq();
            if(memberSeq != null && !"".equals(memberSeq)){
                paymentDTO.setMemberSeq(memberSeq);

                // 교육 SEQ 추출

                String trainSeq = paymentDTO.getTrainSeq();
                if(trainSeq != null && !"".equals(trainSeq)){
                    paymentDTO.setTrainSeq(trainSeq);

                    paymentDTO.setTrainName(getOriginalTrainName(trainSeq));

                    result = eduMarineMapper.insertPayment(paymentDTO);

                    responseDTO.setCustomValue(getSeq);

                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }else{
                    resultCode = "-2";
                    resultMessage = "[등록 실패] 선택하신 교육과정은 등록되지 않았거나 금일이 접수일정에 포함되지 않는 교육과정입니다.";
                }

            }else{
                resultCode = "-2";
                resultMessage = "[등록 실패] 입력하신 신청자 정보는 등록되지 않은 회원입니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertPayment ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertPayment] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    public String getOriginalTrainName(String trainSeq){

        TrainDTO trainInfo = eduMarineMapper.selectTrainSingle(trainSeq);
        String originalTrainName = "-";
        if(trainInfo != null){
            if(trainInfo.getGbnDepth() != null && !"".equals(trainInfo.getGbnDepth())){
                originalTrainName = trainInfo.getGbnDepth() + " " + trainInfo.getGbn();
            }else{
                originalTrainName = trainInfo.getGbn();
            }

        }

        return originalTrainName;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRegularPayStatus(RegularDTO regularDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateRegularPayStatus(regularDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + regularDTO.getSeq();
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateRegularPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoarderPayStatus(BoarderDTO boarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBoarderPayStatus(boarderDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + boarderDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(boarderDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(boarderDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBoarderPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFrpPayStatus(FrpDTO frpDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateFrpPayStatus(frpDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + frpDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(frpDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(frpDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateFrpPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateInboarderPayStatus(InboarderDTO inboarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateInboarderPayStatus(inboarderDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + inboarderDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(inboarderDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(inboarderDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateInboarderPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateOutboarderPayStatus(OutboarderDTO outboarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateOutboarderPayStatus(outboarderDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + outboarderDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(outboarderDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(outboarderDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateOutboarderPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSailyachtPayStatus(SailyachtDTO sailyachtDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateSailyachtPayStatus(sailyachtDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + sailyachtDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(sailyachtDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(sailyachtDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateSailyachtPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighHorsePowerPayStatus(HighHorsePowerDTO highHorsePowerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateHighHorsePowerPayStatus(highHorsePowerDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + highHorsePowerDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(highHorsePowerDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(highHorsePowerDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateHighHorsePowerPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSelfPayStatus(HighSelfDTO highSelfDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateHighSelfPayStatus(highSelfDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + highSelfDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(highSelfDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(highSelfDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateHighSelfPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSpecialPayStatus(HighSpecialDTO highSpecialDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateHighSpecialPayStatus(highSpecialDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + highSpecialDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(highSpecialDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(highSpecialDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateHighSpecialPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSterndrivePayStatus(SterndriveDTO sterndriveDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateSterndrivePayStatus(sterndriveDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + sterndriveDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(sterndriveDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(sterndriveDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateSterndrivePayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSternSpecialPayStatus(SternSpecialDTO sternSpecialDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateSternSpecialPayStatus(sternSpecialDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + sternSpecialDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(sternSpecialDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(sternSpecialDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateSternSpecialPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBasicPayStatus(BasicDTO basicDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBasicPayStatus(basicDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + basicDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(basicDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(basicDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBasicPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateEmergencyPayStatus(EmergencyDTO emergencyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateEmergencyPayStatus(emergencyDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + emergencyDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(emergencyDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(emergencyDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateEmergencyPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateGeneratorPayStatus(GeneratorDTO generatorDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateGeneratorPayStatus(generatorDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + generatorDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(generatorDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(generatorDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateGeneratorPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCompetencyPayStatus(CompetencyDTO competencyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateCompetencyPayStatus(competencyDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + competencyDTO.getSeq();
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(competencyDTO.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(competencyDTO.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateCompetencyPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourinPayStatus(FamtourinDTO famtourinDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateFamtourinPayStatus(famtourinDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + famtourinDTO.getSeq();
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateFamtourinPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtouroutPayStatus(FamtouroutDTO famtouroutDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateFamtouroutPayStatus(famtouroutDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + famtouroutDTO.getSeq();
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateFamtouroutPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateElectroPayStatus(ElectroDTO electroDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateElectroPayStatus(electroDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail] Seq : " + electroDTO.getSeq();
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateElectroPayStatus : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EduApplyInfoDTO> processSelectEduApplyInfoList(String memberSeq) {
        return eduMarineMapper.selectEduApplyInfoList(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EduApplyInfoDTO> processSelectEduApplyInfoCancelList(String memberSeq) {
        return eduMarineMapper.selectEduApplyInfoCancelList(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PaymentDTO processSelectPaymentSingle(PaymentDTO payReq) {
        return eduMarineMapper.selectPaymentSingle(payReq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RegularDTO processSelectRegularSingle(String seq) {
        return eduMarineMapper.selectRegularSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRegular(RegularDTO regularDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateRegular(regularDTO);

            responseDTO.setCustomValue(regularDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateRegular : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public BoarderDTO processSelectBoarderSingle(String seq) {
        return eduMarineMapper.selectBoarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FrpDTO processSelectFrpSingle(String seq) {
        return eduMarineMapper.selectFrpSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PaymentDTO> processSelectPaymentList(String memberSeq) {
        return eduMarineMapper.selectPaymentList(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertBoarder(BoarderDTO boarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(boarderDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(boarderDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String boarderSeq = eduMarineMapper.getBoarderSeq();
                    boarderDTO.setSeq(boarderSeq);

                    result = eduMarineMapper.insertBoarder(boarderDTO);

                    responseDTO.setCustomValue(boarderSeq);

                    /* 경력사항 insert */
                    if(!StringUtil.isEmpty(boarderDTO.getCareerList())){
                        for(int i=0; i<boarderDTO.getCareerList().size(); i++){
                            CareerDTO careerDTO = boarderDTO.getCareerList().get(i);
                            careerDTO.setBoarderSeq(boarderSeq);
                            Integer careerResult = eduMarineMapper.insertCareer(careerDTO);
                        }
                    }

                    /* 자격면허 insert */
                    if(!StringUtil.isEmpty(boarderDTO.getLicenseList())){
                        for(int i=0; i<boarderDTO.getLicenseList().size(); i++){
                            LicenseDTO licenseDTO = boarderDTO.getLicenseList().get(i);
                            licenseDTO.setBoarderSeq(boarderSeq);
                            Integer licenseResult = eduMarineMapper.insertLicense(licenseDTO);
                        }
                    }
                    
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertRegular ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertBoarder] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CareerDTO> processSelectCareerList(CareerDTO careerDTO) {
        return eduMarineMapper.selectCareerList(careerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<LicenseDTO> processSelectLicenseList(LicenseDTO licenseDTO) {
        return eduMarineMapper.selectLicenseList(licenseDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoarder(BoarderDTO boarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBoarder(boarderDTO);

            responseDTO.setCustomValue(boarderDTO.getSeq());

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail]";
            }else {

                /* career table */
                List<CareerDTO> careerList = boarderDTO.getCareerList();
                if(careerList != null){
                    for(CareerDTO request : careerList) {
                        String careerSeq = request.getSeq();
                        if (careerSeq != null & !Objects.equals(careerSeq, "")) {
                            Integer updateResult = eduMarineMapper.updateCareer(request);
                        } else {
                            Integer insertResult = eduMarineMapper.insertCareer(request);
                        }
                    }
                }

                /* license table */
                List<LicenseDTO> licenseList = boarderDTO.getLicenseList();
                if(licenseList != null){
                    for(LicenseDTO request : licenseList) {
                        String licenseSeq = request.getSeq();
                        if (licenseSeq != null & !Objects.equals(licenseSeq, "")) {
                            Integer updateResult = eduMarineMapper.updateLicense(request);
                        } else {
                            Integer insertResult = eduMarineMapper.insertLicense(request);
                        }
                    }
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBoarder : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteCareer(CareerDTO careerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(careerDTO.getSeq() != null){

                result = eduMarineMapper.deleteCareer(careerDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + careerDTO.getSeq();
                }
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteCareer ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processDeleteCareer] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteLicense(LicenseDTO licenseDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(licenseDTO.getSeq() != null){

                result = eduMarineMapper.deleteLicense(licenseDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + licenseDTO.getSeq();
                }
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteLicense ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processDeleteLicense] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFrp(FrpDTO frpDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(frpDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(frpDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String frpSeq = eduMarineMapper.getFrpSeq();
                    frpDTO.setSeq(frpSeq);

                    result = eduMarineMapper.insertFrp(frpDTO);

                    responseDTO.setCustomValue(frpSeq);

                    /* 경력사항 insert */
                    if(!StringUtil.isEmpty(frpDTO.getCareerList())){
                        for(int i=0; i<frpDTO.getCareerList().size(); i++){
                            CareerDTO careerDTO = frpDTO.getCareerList().get(i);
                            careerDTO.setBoarderSeq(frpSeq);
                            Integer careerResult = eduMarineMapper.insertCareer(careerDTO);
                        }
                    }

                    /* 자격면허 insert */
                    if(!StringUtil.isEmpty(frpDTO.getLicenseList())){
                        for(int i=0; i<frpDTO.getLicenseList().size(); i++){
                            LicenseDTO licenseDTO = frpDTO.getLicenseList().get(i);
                            licenseDTO.setBoarderSeq(frpSeq);
                            Integer licenseResult = eduMarineMapper.insertLicense(licenseDTO);
                        }
                    }

                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFrp ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertFrp] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFrp(FrpDTO frpDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateFrp(frpDTO);

            responseDTO.setCustomValue(frpDTO.getSeq());

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail]";
            }else {

                /* career table */
                List<CareerDTO> careerList = frpDTO.getCareerList();
                if(careerList != null){
                    for(CareerDTO request : careerList) {
                        String careerSeq = request.getSeq();
                        if (careerSeq != null & !Objects.equals(careerSeq, "")) {
                            Integer updateResult = eduMarineMapper.updateCareer(request);
                        } else {
                            Integer insertResult = eduMarineMapper.insertCareer(request);
                        }
                    }
                }

                /* license table */
                List<LicenseDTO> licenseList = frpDTO.getLicenseList();
                if(licenseList != null){
                    for(LicenseDTO request : licenseList) {
                        String licenseSeq = request.getSeq();
                        if (licenseSeq != null & !Objects.equals(licenseSeq, "")) {
                            Integer updateResult = eduMarineMapper.updateLicense(request);
                        } else {
                            Integer insertResult = eduMarineMapper.insertLicense(request);
                        }
                    }
                }
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateFrp : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertInboarder(InboarderDTO inboarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(inboarderDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(inboarderDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getInboarderSeq();
                    inboarderDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertInboarder(inboarderDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertInboarder ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertInboarder] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public InboarderDTO processSelectInboarderSingle(String seq) {
        return eduMarineMapper.selectInboarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateInboarder(InboarderDTO inboarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateInboarder(inboarderDTO);

            responseDTO.setCustomValue(inboarderDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateInboarder : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertBasic(BasicDTO basicDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(basicDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(basicDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getBasicSeq();
                    basicDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertBasic(basicDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertBasic ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertBasic] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public BasicDTO processSelectBasicSingle(String seq) {
        return eduMarineMapper.selectBasicSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBasic(BasicDTO basicDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateBasic(basicDTO);

            responseDTO.setCustomValue(basicDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateBasic : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertEmergency(EmergencyDTO emergencyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(emergencyDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(emergencyDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getEmergencySeq();
                    emergencyDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertEmergency(emergencyDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertEmergency ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertEmergency] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public EmergencyDTO processSelectEmergencySingle(String seq) {
        return eduMarineMapper.selectEmergencySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateEmergency(EmergencyDTO emergencyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateEmergency(emergencyDTO);

            responseDTO.setCustomValue(emergencyDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateEmergency : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public GeneratorDTO processSelectGeneratorSingle(String seq) {
        return eduMarineMapper.selectGeneratorSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertGenerator(GeneratorDTO generatorDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(generatorDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(generatorDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getGeneratorSeq();
                    generatorDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertGenerator(generatorDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertEmergency ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertGenerator] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateGenerator(GeneratorDTO generatorDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateGenerator(generatorDTO);

            responseDTO.setCustomValue(generatorDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateGenerator : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public CompetencyDTO processSelectCompetencySingle(String seq) {
        return eduMarineMapper.selectCompetencySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertCompetency(CompetencyDTO competencyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(competencyDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(competencyDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getCompetencySeq();
                    competencyDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertCompetency(competencyDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertCompetency ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertCompetency] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCompetency(CompetencyDTO competencyDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateCompetency(competencyDTO);

            responseDTO.setCustomValue(competencyDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateCompetency : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FamtourinDTO processSelectFamtourinSingle(String seq) {
        return eduMarineMapper.selectFamtourinSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFamtourin(FamtourinDTO famtourinDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(famtourinDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(famtourinDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getFamtourinSeq();
                    famtourinDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertFamtourin(famtourinDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFamtourin ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertFamtourin] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourin(FamtourinDTO famtourinDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateFamtourin(famtourinDTO);

            responseDTO.setCustomValue(famtourinDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateFamtourin : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FamtouroutDTO processSelectFamtouroutSingle(String seq) {
        return eduMarineMapper.selectFamtouroutSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFamtourout(FamtouroutDTO famtouroutDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(famtouroutDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(famtouroutDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getFamtouroutSeq();
                    famtouroutDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertFamtourout(famtouroutDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFamtourout ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertFamtourout] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourout(FamtouroutDTO famtouroutDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateFamtourout(famtouroutDTO);

            responseDTO.setCustomValue(famtouroutDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateFamtourout : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ElectroDTO processSelectElectroSingle(String seq) {
        return eduMarineMapper.selectElectroSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertElectro(ElectroDTO electroDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(electroDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(electroDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getElectroSeq();
                    electroDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertElectro(electroDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertElectro ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertElectro] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateElectro(ElectroDTO electroDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateElectro(electroDTO);

            responseDTO.setCustomValue(electroDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateElectro : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertOutboarder(OutboarderDTO outboarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(outboarderDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(outboarderDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getOutboarderSeq();
                    outboarderDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertOutboarder(outboarderDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertOutboarder ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertOutboarder] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public OutboarderDTO processSelectOutboarderSingle(String seq) {
        return eduMarineMapper.selectOutboarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateOutboarder(OutboarderDTO outboarderDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateOutboarder(outboarderDTO);

            responseDTO.setCustomValue(outboarderDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateOutboarder : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSailyacht(SailyachtDTO sailyachtDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(sailyachtDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(sailyachtDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getSailyachtSeq();
                    sailyachtDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertSailyacht(sailyachtDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSailyacht ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertSailyacht] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SailyachtDTO processSelectSailyachtSingle(String seq) {
        return eduMarineMapper.selectSailyachtSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSailyacht(SailyachtDTO sailyachtDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateSailyacht(sailyachtDTO);

            responseDTO.setCustomValue(sailyachtDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateSailyacht : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public HighHorsePowerDTO processSelectHighHorsePowerSingle(String seq) {
        return eduMarineMapper.selectHighHorsePowerSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighhorsepower(HighHorsePowerDTO highHorsePowerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateHighhorsepower(highHorsePowerDTO);

            responseDTO.setCustomValue(highHorsePowerDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateHighhorsepower : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public HighSelfDTO processSelectHighSelfSingle(String seq) {
        return eduMarineMapper.selectHighSelfSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSelf(HighSelfDTO highselfDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateHighSelf(highselfDTO);

            responseDTO.setCustomValue(highselfDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateHighSelf : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public HighSpecialDTO processSelectHighSpecialSingle(String seq) {
        return eduMarineMapper.selectHighSpecialSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSpecial(HighSpecialDTO highSpecialDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateHighSpecial(highSpecialDTO);

            responseDTO.setCustomValue(highSpecialDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateHighSpecial : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SterndriveDTO processSelectSterndriveSingle(String seq) {
        return eduMarineMapper.selectSterndriveSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSterndrive(SterndriveDTO sterndriveDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateSterndrive(sterndriveDTO);

            responseDTO.setCustomValue(sterndriveDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateSterndrive : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SternSpecialDTO processSelectSternSpecialSingle(String seq) {
        return eduMarineMapper.selectSternSpecialSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSternSpecial(SternSpecialDTO sternSpecialDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            Integer result = eduMarineMapper.updateSternSpecial(sternSpecialDTO);

            responseDTO.setCustomValue(sternSpecialDTO.getSeq());
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            String eMessage = "[ERROR] processUpdateSternSpecial : ";
            resultMessage = String.format(STR_RESULT_H, eMessage, e.getMessage() == null ? "" : e.getMessage());
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processUpdateTrainApplyCnt(String trainSeq) {
        return eduMarineMapper.updateTrainApplyCnt(trainSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EmploymentDTO> processSelectEmploymentList(String gbn) {
        return eduMarineMapper.selectEmploymentList(gbn);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FaqDTO> processSelectFaqList(SearchDTO searchDTO) {
        return eduMarineMapper.selectFaqList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processUpdatePaymentVbankNoti(PaymentDTO paymentDTO) {
        return eduMarineMapper.updatePaymentVbankNoti(paymentDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PaymentDTO processSelectPaymentVbankInfo(PaymentDTO paymentDTO) {
        return eduMarineMapper.selectPaymentVbankInfo(paymentDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainTemplateDTO.TrainTemplateInfo> processSelectTrainTemplateList(String major) {
        return eduMarineMapper.selectTrainTemplateList(major);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertHighHorsePower(HighHorsePowerDTO highHorsePowerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(highHorsePowerDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(highHorsePowerDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getHighHorsePowerSeq();
                    highHorsePowerDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertHighHorsePower(highHorsePowerDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertHighHorsePower ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertHighHorsePower] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertHighSelf(HighSelfDTO highselfDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(highselfDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(highselfDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getHighSelfSeq();
                    highselfDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertHighSelf(highselfDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertHighSelf ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertHighSelf] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertHighSpecial(HighSpecialDTO highSpecialDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(highSpecialDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(highSpecialDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getHighSpecialSeq();
                    highSpecialDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertHighSpecial(highSpecialDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertHighSpecial ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertHighSpecial] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSterndrive(SterndriveDTO sterndriveDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(sterndriveDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(sterndriveDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getSterndriveSeq();
                    sterndriveDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertSterndrive(sterndriveDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSterndrive ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertSterndrive] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSternSpecial(SternSpecialDTO sternSpecialDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(sternSpecialDTO.getId() != null){

                TrainDTO trainDTO = eduMarineMapper.selectTrainSingle(sternSpecialDTO.getTrainSeq());

                if(Objects.equals(trainDTO.getTrainCnt(), trainDTO.getTrainApplyCnt())){
                    resultCode = "99";
                    resultMessage = "수강 정원이 초과하여 신청이 불가합니다.";
                }else{
                    String getSeq = eduMarineMapper.getSternSpecialSeq();
                    sternSpecialDTO.setSeq(getSeq);

                    result = eduMarineMapper.insertSternSpecial(sternSpecialDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[재로그인 요청] 로그아웃 후 다시 로그인하여 재시도 부탁드립니다.";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSternSpecial ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processInsertSternSpecial] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    /*************************************************
     * File
     * ***********************************************/

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FileDTO processSelectFileIdSingle(FileDTO fileReq) {
        return eduMarineMapper.selectFileIdSingle(fileReq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FileDTO> processSelectFileList(String userId) {
        return eduMarineMapper.selectFileList(userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteFile(FileDTO fileDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(fileDTO.getId() != null){

                result = eduMarineMapper.deleteFile(fileDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Id : " + fileDTO.getId();
                }
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Id Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteFile ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            log.error("[processDeleteFile] 처리 중 예외", e);
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    public String Salt() {

        String salt="";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.getEncoder().encode(bytes));

        } catch (NoSuchAlgorithmException e) {
            log.error("[Salt] 처리 중 예외", e);
        }
        return salt;
    }

    public String SHA512(String password, String hash) {
        String salt = hash+password;
        String hex = null;
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-512");
            msg.update(salt.getBytes());

            hex = String.format("%128x", new BigInteger(1, msg.digest()));

        } catch (NoSuchAlgorithmException e) {
            log.error("[SHA512] 처리 중 예외", e);
        }
        return hex;
    }

    /**
     * [UNIFIED] 신규 통합 교육 신청서 제출
     */
    @Override
    public ResponseDTO processInsertUnifiedApplication(ApplicationUnifiedDTO dto) {
        ResponseDTO response = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            // (필요시) 중복 신청 체크
            // Integer count = unifiedMapper.selectUnifiedApplicationPreCheck(dto);
            // if (count > 0) { ... }

            // 1. 신규 통합 PK 채번
            String seq = unifiedMapper.getUnifiedAppSeq();
            dto.setSeq(seq);

            // 3. 통합 테이블에 INSERT
            int result = unifiedMapper.insertUnifiedApplication(dto);
            if (result > 0) {
                response.setCustomValue(seq); // 성공 시 seq를 결제 연동을 위해 반환
            } else {
                throw new Exception("Insert Unified Application failed.");
            }

        } catch (Exception e) {
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[신청서 제출 오류] " + e.getMessage();
        }

        response.setResultCode(resultCode);
        response.setResultMessage(resultMessage);
        return response;
    }

    /**
     * [UNIFIED] 신규 통합 교육 신청서 상태 업데이트 (결제/취소 공용)
     */
    @Override
    public ResponseDTO processUpdateUnifiedApplicationPayStatus(ApplicationUnifiedDTO dto) {
        ResponseDTO response = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            int result = unifiedMapper.updateUnifiedApplicationPayStatus(dto);
            if (result == 0) {
                throw new Exception("Update Unified Application Status failed. (Seq: " + dto.getSeq() + ")");
            }else{
                PaymentDTO paymentDTO = eduMarineMapper.selectPaymentTableSeq(dto.getSeq());
                if(paymentDTO != null){
                    paymentDTO.setPayStatus(dto.getApplyStatus());
                    eduMarineMapper.updatePayment(paymentDTO);
                }
            }
        } catch (Exception e) {
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[신청서 상태 업데이트 오류] " + e.getMessage();
        }

        response.setResultCode(resultCode);
        response.setResultMessage(resultMessage);
        return response;
    }

    @Override
    public Integer processUpdateTrainApplyCntMinus(String seq) {
        return eduMarineMapper.updateTrainApplyCntMinus(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectUnifiedPreCheck(ApplicationUnifiedDTO dto) {
        return unifiedMapper.selectUnifiedPreCheck(dto);
    }

}