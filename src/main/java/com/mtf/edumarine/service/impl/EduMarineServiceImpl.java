package com.mtf.edumarine.service.impl;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.mapper.EduMarineMapper;
import com.mtf.edumarine.mapper.UnifiedMapper;
import com.mtf.edumarine.service.EduMarineService;
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
        System.out.println("EduMarineServiceImpl > processStatisticsAccessor : ======");

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
            System.out.println(e.getMessage() == null ? "" : e.getMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public void processUpdateTrainClosing(String todate) {
        System.out.println("EduMarineServiceImpl > processUpdateTrainClosing : ======");

        try {
            eduMarineMapper.updateTrainClosing(todate);
        }catch (Exception e){
            String eMessage = "[main] processUpdateTrainClosing Error : ";
            System.out.println(e.getMessage() == null ? "" : e.getMessage());
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PopupDTO> processSelectPopupList(PopupDTO popupDTO) {
        System.out.println("EduMarineServiceImpl > processSelectPopupList");
        return eduMarineMapper.selectPopupList(popupDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BannerDTO> processSelectBannerList(BannerDTO bannerDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBannerList");
        return eduMarineMapper.selectBannerList(bannerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ApplicationUnifiedDTO processSelectUnifiedApplicationSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectUnifiedApplicationSingle");
        return unifiedMapper.selectUnifiedApplicationSingle(seq);
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
        System.out.println("EduMarineServiceImpl > processSelectTrainList");
        return eduMarineMapper.selectTrainList(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public TrainDTO processSelectTrainSingle(String trainSeq) {
        System.out.println("EduMarineServiceImpl > processSelectTrainSingle");
        return eduMarineMapper.selectTrainSingle(trainSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainScheduleList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectTrainScheduleList");
        return eduMarineMapper.selectTrainScheduleList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainScheduleCalendarList(TrainDTO trainDTO) {
        System.out.println("EduMarineServiceImpl > processSelectTrainScheduleCalendarList");
        return eduMarineMapper.selectTrainScheduleCalendarList(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NoticeDTO> processSelectNoticeList(NoticeDTO noticeDTO) {
        System.out.println("EduMarineServiceImpl > processSelectNoticeList");
        return eduMarineMapper.selectNoticeList(noticeDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PressDTO> processSelectPressList(PressDTO pressDTO) {
        System.out.println("EduMarineServiceImpl > processSelectPressList");
        return eduMarineMapper.selectPressList(pressDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseDTO processCheckMember(MemberDTO memberDTO) {
        System.out.println("EduMarineServiceImpl > processCheckMember : ======");
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
        System.out.println("EduMarineServiceImpl > processCheckCommunity : ======");
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
        System.out.println("EduMarineServiceImpl > processCheckReply : ======");
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
        System.out.println("EduMarineServiceImpl > processCheckMemberSingle : ======");
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
        System.out.println("EduMarineServiceImpl > checkDuplicateId : ======");
        return eduMarineMapper.checkDuplicateId(memberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertMember(MemberDTO memberDTO) {
        System.out.println("EduMarineServiceImpl > processInsertMember");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertMember ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String getMemberEmail(MemberDTO memberDTO) {
        System.out.println("EduMarineServiceImpl > getMemberEmail : ======");
        return eduMarineMapper.getMemberEmail(memberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO initMemberPassword(MemberDTO memberDTO) {
        System.out.println("EduMarineServiceImpl > initMemberPassword : ======");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
            String password = "aa134!@cc";
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
        System.out.println("EduMarineServiceImpl > processSelectMemberSingle");
        return eduMarineMapper.selectMemberSingle(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public MemberDTO processSelectMemberSeqSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectMemberSeqSingle");
        return eduMarineMapper.selectMemberSeqSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NoticeDTO> processSelectBoardNoticeList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBoardNoticeList");
        return eduMarineMapper.selectBoardNoticeList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoardNoticeViewCnt(String seq) {
        System.out.println("EduMarineServiceImpl > processUpdateBoardNoticeViewCnt : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectBoardNoticeSingle");
        return eduMarineMapper.selectBoardNoticeSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PressDTO> processSelectBoardPressList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBoardPressList");
        return eduMarineMapper.selectBoardPressList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoardPressViewCnt(String seq) {
        System.out.println("EduMarineServiceImpl > processUpdateBoardPressViewCnt : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectBoardPressSingle");
        return eduMarineMapper.selectBoardPressSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<GalleryDTO> processSelectBoardGalleryList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBoardGalleryList");
        return eduMarineMapper.selectBoardGalleryList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<MediaDTO> processSelectBoardMediaList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBoardMediaList");
        return eduMarineMapper.selectBoardMediaList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NewsletterDTO> processSelectBoardNewsList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBoardNewsList");
        return eduMarineMapper.selectBoardNewsList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoardNewsViewCnt(String seq) {
        System.out.println("EduMarineServiceImpl > processUpdateBoardNewsViewCnt : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectBoardNewsSingle");
        return eduMarineMapper.selectBoardNewsSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<JobDTO> processSelectJobReviewList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectJobReviewList");
        return eduMarineMapper.selectJobReviewList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateMember(MemberDTO memberDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateMember : ======");
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
                System.out.println("[비밀번호 변경 프로세스 포함] 회원 정보 업데이트");
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
                    System.out.println("[비밀번호 변경 프로세스 미포함] 회원 정보 업데이트");
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
        System.out.println("EduMarineServiceImpl > processUpdateMemberWithdraw : ======");
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
        System.out.println("EduMarineServiceImpl > processSaveResume : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectResumeSingle");
        return eduMarineMapper.selectResumeSingle(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectRegularPreCheck(RegularDTO regularDTO) {
        System.out.println("EduMarineServiceImpl > processSelectRegularPreCheck");
        return eduMarineMapper.selectRegularPreCheck(regularDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectBoarderPreCheck(BoarderDTO boarderDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBoarderPreCheck");
        return eduMarineMapper.selectBoarderPreCheck(boarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectFrpPreCheck(FrpDTO frpDTO) {
        System.out.println("EduMarineServiceImpl > processSelectFrpPreCheck");
        return eduMarineMapper.selectFrpPreCheck(frpDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectBasicPreCheck(BasicDTO basicDTO) {
        System.out.println("EduMarineServiceImpl > processSelectBasicPreCheck");
        return eduMarineMapper.selectBasicPreCheck(basicDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectEmergencyPreCheck(EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineServiceImpl > processSelectEmergencyPreCheck");
        return eduMarineMapper.selectEmergencyPreCheck(emergencyDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectGeneratorPreCheck(GeneratorDTO generatorDTO) {
        System.out.println("EduMarineServiceImpl > processSelectGeneratorPreCheck");
        return eduMarineMapper.selectGeneratorPreCheck(generatorDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectCompetencyPreCheck(CompetencyDTO competencyDTO) {
        System.out.println("EduMarineServiceImpl > processSelectCompetencyPreCheck");
        return eduMarineMapper.selectCompetencyPreCheck(competencyDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectFamtourinPreCheck(FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineServiceImpl > processSelectFamtourinPreCheck");
        return eduMarineMapper.selectFamtourinPreCheck(famtourinDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectFamtouroutPreCheck(FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineServiceImpl > processSelectFamtouroutPreCheck");
        return eduMarineMapper.selectFamtouroutPreCheck(famtouroutDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectElectroPreCheck(ElectroDTO electroDTO) {
        System.out.println("EduMarineServiceImpl > processSelectElectroPreCheck");
        return eduMarineMapper.selectElectroPreCheck(electroDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectInboarderPreCheck(InboarderDTO inboarderDTO) {
        System.out.println("EduMarineServiceImpl > processSelectInboarderPreCheck");
        return eduMarineMapper.selectInboarderPreCheck(inboarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectOutboarderPreCheck(OutboarderDTO outboarderDTO) {
        System.out.println("EduMarineServiceImpl > processSelectOutboarderPreCheck");
        return eduMarineMapper.selectOutboarderPreCheck(outboarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectSailyachtPreCheck(SailyachtDTO sailyachtDTO) {
        System.out.println("EduMarineServiceImpl > processSelectSailyachtPreCheck");
        return eduMarineMapper.selectSailyachtPreCheck(sailyachtDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectHighHorsePowerPreCheck(HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineServiceImpl > processSelectHighHorsePowerPreCheck");
        return eduMarineMapper.selectHighHorsePowerPreCheck(highHorsePowerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectHighSelfPreCheck(HighSelfDTO highSelfDTO) {
        System.out.println("EduMarineServiceImpl > processSelectHighSelfPreCheck");
        return eduMarineMapper.selectHighSelfPreCheck(highSelfDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectHighSpecialPreCheck(HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineServiceImpl > processSelectHighSpecialPreCheck");
        return eduMarineMapper.selectHighSpecialPreCheck(highSpecialDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectSterndrivePreCheck(SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineServiceImpl > processSelectSterndrivePreCheck");
        return eduMarineMapper.selectSterndrivePreCheck(sterndriveDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectSternSpecialPreCheck(SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineServiceImpl > processSelectSternSpecialPreCheck");
        return eduMarineMapper.selectSternSpecialPreCheck(sternSpecialDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertRegular(RegularDTO regularDTO) {
        System.out.println("EduMarineServiceImpl > processInsertRegular");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertRegular ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RegularDTO processSelectPreRegularSingle(RegularDTO regularDTO) {
        System.out.println("EduMarineServiceImpl > processSelectPreRegularSingle");
        return eduMarineMapper.selectPreRegularSingle(regularDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CommunityDTO> processSelectCommunityList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectCommunityList");
        return eduMarineMapper.selectCommunityList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public CommunityDTO processSelectCommunitySingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectCommunitySingle");
        return eduMarineMapper.selectCommunitySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCommunityViewCnt(String seq) {
        System.out.println("EduMarineServiceImpl > processUpdateCommunityViewCnt : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectRecommendSingle");
        return eduMarineMapper.selectRecommendSingle(recommendReqDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRecommend(RecommendDTO recommendDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateRecommend : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectReplyList");
        return eduMarineMapper.selectReplyList(replyReqDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertReply(ReplyDTO replyDTO) {
        System.out.println("EduMarineServiceImpl > processInsertReply : ======");
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
        System.out.println("EduMarineServiceImpl > processDeleteReply : ======");
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
        System.out.println("EduMarineServiceImpl > processInsertCommunity");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMapper.getCommunitySeq();
            communityDTO.setSeq(getSeq);

            String content = communityDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            communityDTO.setContent(content);
            result = eduMarineMapper.insertCommunity(communityDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertCommunity ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteCommunity(CommunityDTO communityDTO) {
        System.out.println("EduMarineServiceImpl > processDeleteCommunity : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectPostCommunityList");
        return eduMarineMapper.selectPostCommunityList(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<ReplyDTO> processSelectPostReplyList(String id) {
        System.out.println("EduMarineServiceImpl > processSelectPostReplyList");
        return eduMarineMapper.selectPostReplyList(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCommunity(CommunityDTO communityDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateCommunity");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String content = communityDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            communityDTO.setContent(content);
            result = eduMarineMapper.updateCommunity(communityDTO);

            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateCommunity ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertPayment(PaymentDTO paymentDTO) {
        System.out.println("EduMarineServiceImpl > processInsertPayment");
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
            e.printStackTrace();
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
        System.out.println("EduMarineServiceImpl > processUpdateRegularPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateBoarderPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateFrpPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateInboarderPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateOutboarderPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateSailyachtPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateHighHorsePowerPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateHighSelfPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateHighSpecialPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateSterndrivePayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateSternSpecialPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateBasicPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateEmergencyPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateGeneratorPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateCompetencyPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateFamtourinPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateFamtouroutPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateElectroPayStatus : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectEduApplyInfoList");
        return eduMarineMapper.selectEduApplyInfoList(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EduApplyInfoDTO> processSelectEduApplyInfoCancelList(String memberSeq) {
        System.out.println("EduMarineServiceImpl > processSelectEduApplyInfoCancelList");
        return eduMarineMapper.selectEduApplyInfoCancelList(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RegularDTO processSelectRegularSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectRegularSingle");
        return eduMarineMapper.selectRegularSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRegular(RegularDTO regularDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateRegular : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectBoarderSingle");
        return eduMarineMapper.selectBoarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FrpDTO processSelectFrpSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectFrpSingle");
        return eduMarineMapper.selectFrpSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PaymentDTO> processSelectPaymentList(String memberSeq) {
        System.out.println("EduMarineServiceImpl > processSelectPaymentList");
        return eduMarineMapper.selectPaymentList(memberSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertBoarder(BoarderDTO boarderDTO) {
        System.out.println("EduMarineServiceImpl > processInsertBoarder");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertRegular ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CareerDTO> processSelectCareerList(CareerDTO careerDTO) {
        System.out.println("EduMarineServiceImpl > processSelectCareerList");
        return eduMarineMapper.selectCareerList(careerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<LicenseDTO> processSelectLicenseList(LicenseDTO licenseDTO) {
        System.out.println("EduMarineServiceImpl > processSelectLicenseList");
        return eduMarineMapper.selectLicenseList(licenseDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoarder(BoarderDTO boarderDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateBoarder : ======");
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
        System.out.println("EduMarineServiceImpl > processDeleteCareer");
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
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteLicense(LicenseDTO licenseDTO) {
        System.out.println("EduMarineServiceImpl > processDeleteLicense");
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
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFrp(FrpDTO frpDTO) {
        System.out.println("EduMarineServiceImpl > processInsertFrp");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFrp ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFrp(FrpDTO frpDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateFrp : ======");
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
        System.out.println("EduMarineServiceImpl > processInsertInboarder");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertInboarder ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public InboarderDTO processSelectInboarderSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectInboarderSingle");
        return eduMarineMapper.selectInboarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateInboarder(InboarderDTO inboarderDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateInboarder : ======");
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
        System.out.println("EduMarineServiceImpl > processInsertBasic");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertBasic ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public BasicDTO processSelectBasicSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectBasicSingle");
        return eduMarineMapper.selectBasicSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBasic(BasicDTO basicDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateBasic : ======");
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
        System.out.println("EduMarineServiceImpl > processInsertEmergency");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertEmergency ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public EmergencyDTO processSelectEmergencySingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectEmergencySingle");
        return eduMarineMapper.selectEmergencySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateEmergency(EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateEmergency : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectGeneratorSingle");
        return eduMarineMapper.selectGeneratorSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertGenerator(GeneratorDTO generatorDTO) {
        System.out.println("EduMarineServiceImpl > processInsertGenerator");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertEmergency ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateGenerator(GeneratorDTO generatorDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateGenerator : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectCompetencySingle");
        return eduMarineMapper.selectCompetencySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertCompetency(CompetencyDTO competencyDTO) {
        System.out.println("EduMarineServiceImpl > processInsertCompetency");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertCompetency ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCompetency(CompetencyDTO competencyDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateCompetency : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectFamtourinSingle");
        return eduMarineMapper.selectFamtourinSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFamtourin(FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineServiceImpl > processInsertFamtourin");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFamtourin ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourin(FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateFamtourin : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectFamtouroutSingle");
        return eduMarineMapper.selectFamtouroutSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFamtourout(FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineServiceImpl > processInsertFamtourout");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFamtourout ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourout(FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateFamtourout : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectElectroSingle");
        return eduMarineMapper.selectElectroSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertElectro(ElectroDTO electroDTO) {
        System.out.println("EduMarineServiceImpl > processInsertElectro");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertElectro ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateElectro(ElectroDTO electroDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateElectro : ======");
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
        System.out.println("EduMarineServiceImpl > processInsertOutboarder");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertOutboarder ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public OutboarderDTO processSelectOutboarderSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectOutboarderSingle");
        return eduMarineMapper.selectOutboarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateOutboarder(OutboarderDTO outboarderDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateOutboarder : ======");
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
        System.out.println("EduMarineServiceImpl > processInsertSailyacht");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSailyacht ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SailyachtDTO processSelectSailyachtSingle(String seq) {
        System.out.println("EduMarineServiceImpl > processSelectSailyachtSingle");
        return eduMarineMapper.selectSailyachtSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSailyacht(SailyachtDTO sailyachtDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateSailyacht : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectHighHorsePowerSingle");
        return eduMarineMapper.selectHighHorsePowerSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighhorsepower(HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateHighhorsepower : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectHighSelfSingle");
        return eduMarineMapper.selectHighSelfSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSelf(HighSelfDTO highselfDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateHighSelf : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectHighSpecialSingle");
        return eduMarineMapper.selectHighSpecialSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSpecial(HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateHighSelf : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectSterndriveSingle");
        return eduMarineMapper.selectSterndriveSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSterndrive(SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateSterndrive : ======");
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
        System.out.println("EduMarineServiceImpl > processSelectSternSpecialSingle");
        return eduMarineMapper.selectSternSpecialSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSternSpecial(SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineServiceImpl > processUpdateSternSpecial : ======");
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
        System.out.println("EduMarineServiceImpl > processUpdateTrainApplyCnt : ======");
        return eduMarineMapper.updateTrainApplyCnt(trainSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EmploymentDTO> processSelectEmploymentList(String gbn) {
        System.out.println("EduMarineServiceImpl > processSelectEmploymentList");
        return eduMarineMapper.selectEmploymentList(gbn);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FaqDTO> processSelectFaqList(SearchDTO searchDTO) {
        System.out.println("EduMarineServiceImpl > processSelectFaqList");
        return eduMarineMapper.selectFaqList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processUpdatePaymentVbankNoti(PaymentDTO paymentDTO) {
        System.out.println("EduMarineServiceImpl > processUpdatePaymentVbankNoti");
        return eduMarineMapper.updatePaymentVbankNoti(paymentDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PaymentDTO processSelectPaymentVbankInfo(PaymentDTO paymentDTO) {
        System.out.println("EduMarineServiceImpl > processSelectPaymentVbankInfo");
        return eduMarineMapper.selectPaymentVbankInfo(paymentDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainTemplateDTO.TrainTemplateInfo> processSelectTrainTemplateList(String major) {
        System.out.println("EduMarineServiceImpl > processSelectTrainTemplateList");
        return eduMarineMapper.selectTrainTemplateList(major);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertHighHorsePower(HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineServiceImpl > processInsertHighHorsePower");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertHighHorsePower ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertHighSelf(HighSelfDTO highselfDTO) {
        System.out.println("EduMarineServiceImpl > processInsertHighSelf");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertHighSelf ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertHighSpecial(HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineServiceImpl > processInsertHighSpecial");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertHighSpecial ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSterndrive(SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineServiceImpl > processInsertSterndrive");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSterndrive ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSternSpecial(SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineServiceImpl > processInsertSternSpecial");
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
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSternSpecial ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
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
        System.out.println("EduMarineServiceImpl > processSelectFileIdSingle");
        return eduMarineMapper.selectFileIdSingle(fileReq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FileDTO> processSelectFileList(String userId) {
        System.out.println("EduMarineServiceImpl > processSelectFileList");
        return eduMarineMapper.selectFileList(userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteFile(FileDTO fileDTO) {
        System.out.println("EduMarineServiceImpl > processDeleteFile");
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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

            // 2. 기본 상태 설정
            dto.setApplyStatus("신청완료");

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
            }
        } catch (Exception e) {
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[신청서 상태 업데이트 오류] " + e.getMessage();
        }

        response.setResultCode(resultCode);
        response.setResultMessage(resultMessage);
        return response;
    }

}