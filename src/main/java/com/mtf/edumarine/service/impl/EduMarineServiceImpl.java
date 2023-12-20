package com.mtf.edumarine.service.impl;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.mapper.EduMarineMapper;
import com.mtf.edumarine.service.EduMarineService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class EduMarineServiceImpl implements EduMarineService {

    private static final String STR_RESULT_H = "%s - %s";

    //private final SqlSession sqlSession;

    @Setter(onMethod_ = {@Autowired})
    private EduMarineMapper eduMarineMapper;

    /*public EduMarineServiceImpl(SqlSession ss) {
        this.sqlSession = ss;
    }*/

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
    public List<TrainDTO> processSelectTrainList(TrainDTO trainDTO) {
        System.out.println("EduMarineServiceImpl > processSelectTrainList");
        return eduMarineMapper.selectTrainList(trainDTO);
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
    public ResponseDTO processCheckMemberSingle(MemberDTO memberDTO) {
        System.out.println("EduMarineServiceImpl > processCheckMemberSingle : ======");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
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

            String getSeq = eduMarineMapper.getMemberSeq();
            memberDTO.setSeq(getSeq);

            result = eduMarineMapper.insertMember(memberDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
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
            memberDTO.setPassword("aa134!@cc");
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
                //update
                System.out.println("[비밀번호 변경 프로세스 포함] 회원 정보 업데이트");
                Integer result = eduMarineMapper.updateMember(memberDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                }
            }else{
                String id = memberDTO.getId();
                String password = memberDTO.getPassword();
                MemberDTO memberInfo = eduMarineMapper.selectMemberSingle(id);
                if(!Objects.equals(password, memberInfo.getPassword())){
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
    public ResumeDTO processSelectResumeSingle(String id) {
        System.out.println("EduMarineServiceImpl > processSelectResumeSingle");
        return eduMarineMapper.selectResumeSingle(id);
    }


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

}
