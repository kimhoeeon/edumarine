package com.mtf.edumarine.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.mapper.EduMarineMngMapper;
import com.mtf.edumarine.service.EduMarineMngService;
import com.mtf.edumarine.util.AES128;
import com.mtf.edumarine.util.SHA512;
import com.mtf.edumarine.util.StringUtil;
import lombok.Setter;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * [ 템플릿 설명 ]
 * - 해당 파일은 서비스의 비즈니스 로직을 구현하는 곳입니다.
 * - 해당 *ServiceImpl 에서는 @Service 어노테이션을 필수적으로 사용합니다.
 */
@Service
public class EduMarineMngServiceImpl implements EduMarineMngService, HttpSessionBindingListener {

    @Setter(onMethod_ = {@Autowired})
    private EduMarineMngMapper eduMarineMngMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public AdminDTO login(AdminDTO param_adminDTO, HttpSession session) {
        System.out.println("EduMarineMngServiceImpl > loginCheck : ======");
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMsg = CommConstants.RESULT_MSG_SUCCESS;
        AdminDTO db_adminDTO = eduMarineMngMapper.login(param_adminDTO);
        if(db_adminDTO != null){
            String param_ip = param_adminDTO.getIpAddress();
            String db_ip = db_adminDTO.getIpAddress();
            String db_validYn = db_adminDTO.getValidYn();
            String db_loginYn = db_adminDTO.getLoginYn();
            String db_pw = db_adminDTO.getPassword();
            String param_pw = param_adminDTO.getPassword();

            if(db_ip.equals(param_ip)){
                if(db_pw.equals(param_pw)){
                    if("Y".equals(db_validYn)){
                        System.out.println("Login Success");
                        /*if("N".equals(db_loginYn)){*/

                            // 로그인 성공
                            session.setAttribute("status", "logon");
                            session.setAttribute("id", db_adminDTO.getId());
                            session.setAttribute("gbn", db_adminDTO.getGbn());
                            session.setAttribute("note", db_adminDTO.getNote());

                            // 로그인 여부 Update
                            /*AdminDTO req_adminDTO = new AdminDTO();
                            req_adminDTO.setSeq(db_adminDTO.getSeq());
                            req_adminDTO.setLoginYn("Y");
                            eduMarineMngMapper.updateAdminMngLoginYn(req_adminDTO);*/
                            
                        /*}else{
                            resultCode = "-5";
                            resultMsg = "현재 로그인 중인 계정입니다.";
                        }//로그인 여부*/
                        
                    }else{
                        resultCode = "-4";
                        resultMsg = "비정상적인 접근으로 인해 로그인이 제한된 계정입니다.<br>해당 계정에 등록된 연락처로 문자인증을 통해<br>제한 해제 후 재시도해주세요.";
                    }//로그인 유효 여부

                }else{
                    /*if("Y".equals(db_validYn)){
                        // 로그인 유효 여부 Update
                        AdminDTO req_adminDTO = new AdminDTO();
                        req_adminDTO.setSeq(db_adminDTO.getSeq());
                        req_adminDTO.setValidYn("N");
                        eduMarineMngMapper.updateAdminMngValidYn(req_adminDTO);
                    }*/

                    resultCode = "-2";
                    resultMsg = "비밀번호를 다시 확인해주세요.";
                }//비밀번호 체크

            }else{
                resultCode = "-3";
                resultMsg = "해당 계정에 등록된 IP가 아닙니다.";
            }//IP 체크

        }else{
            db_adminDTO = new AdminDTO();
            resultCode = "-2";
            resultMsg = "등록된 계정 정보가 아닙니다.";
        }

        db_adminDTO.setResultCode(resultCode);
        db_adminDTO.setResultMsg(resultMsg);
        return db_adminDTO;
    }

    @Override
    public void logoutCheck(HttpSession session) {
        System.out.println("EduMarineMngServiceImpl > logoutCheck : ======");

        // 로그인 여부 Update
        if(session.getAttribute("id") != null) {

            AdminDTO req_adminInfo = new AdminDTO();
            req_adminInfo.setId(session.getAttribute("id").toString());
            AdminDTO admin_info = eduMarineMngMapper.login(req_adminInfo);

            /*AdminDTO req_adminDTO = new AdminDTO();
            req_adminDTO.setSeq(admin_info.getSeq());
            req_adminDTO.setLoginYn("N");
            eduMarineMngMapper.updateAdminMngLoginYn(req_adminDTO);*/
        }

        session.invalidate(); // 세션 초기화
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("session 끊김");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<StatisticsDTO> processSelectStatisticsAccessorDay(StatisticsDTO statisticsDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectStatisticsAccessorDay");
        return eduMarineMngMapper.selectStatisticsAccessorDay(statisticsDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<StatisticsDTO> processSelectStatisticsAccessorMonth(StatisticsDTO statisticsDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectStatisticsAccessorMonth");
        return eduMarineMngMapper.selectStatisticsAccessorMonth(statisticsDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<StatisticsDTO> processSelectStatisticsAccessorWeek(StatisticsDTO statisticsDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectStatisticsAccessorWeek");
        return eduMarineMngMapper.selectStatisticsAccessorWeek(statisticsDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public StatisticsDTO processSelectStatisticsTrainMember(StatisticsDTO reqDto) {
        System.out.println("EduMarineMngServiceImpl > processSelectStatisticsTrainMember");
        return eduMarineMngMapper.selectStatisticsTrainMember(reqDto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<RegularDTO.TrainInfo> processSelectRegularTrainInfoList(RegularDTO info) {
        System.out.println("EduMarineMngServiceImpl > processSelectRegularTrainInfoList");
        return eduMarineMngMapper.selectRegularTrainInfoList(info);
    }

    /* Board */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NoticeDTO> processSelectNoticeList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectNoticeList");
        return eduMarineMngMapper.selectNoticeList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public NoticeDTO processSelectNoticeSingle(NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectNoticeSingle");
        return eduMarineMngMapper.selectNoticeSingle(noticeDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteNotice(NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteNotice");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(noticeDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteNotice(noticeDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + noticeDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteNotice ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateNotice(NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateNotice");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(noticeDTO.getSeq())){
                if(StringUtil.isEmpty(noticeDTO.getNoticeGbn()) || "off".equals(noticeDTO.getNoticeGbn())){
                    noticeDTO.setNoticeGbn("0");
                }else{
                    noticeDTO.setNoticeGbn("1");
                }

                String content = noticeDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                noticeDTO.setContent(content);
                result = eduMarineMngMapper.updateNotice(noticeDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + noticeDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateNotice ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertNotice(NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertNotice");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(StringUtil.isEmpty(noticeDTO.getNoticeGbn()) || "off".equals(noticeDTO.getNoticeGbn())){
                noticeDTO.setNoticeGbn("0");
            }else{
                noticeDTO.setNoticeGbn("1");
            }

            String getSeq = eduMarineMngMapper.getNoticeSeq();
            noticeDTO.setSeq(getSeq);

            String content = noticeDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            noticeDTO.setContent(content);
            result = eduMarineMngMapper.insertNotice(noticeDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertNotice ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    /* Press */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PressDTO> processSelectPressList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectPressList");
        return eduMarineMngMapper.selectPressList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PressDTO processSelectPressSingle(PressDTO pressDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectPressSingle");
        return eduMarineMngMapper.selectPressSingle(pressDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeletePress(PressDTO pressDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeletePress");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(pressDTO.getSeq() != null){
                result = eduMarineMngMapper.deletePress(pressDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + pressDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeletePress ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdatePress(PressDTO pressDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdatePress");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(pressDTO.getSeq())){
                if(StringUtil.isEmpty(pressDTO.getNoticeGbn()) || "off".equals(pressDTO.getNoticeGbn())){
                    pressDTO.setNoticeGbn("0");
                }else{
                    pressDTO.setNoticeGbn("1");
                }

                String content = pressDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                pressDTO.setContent(content);
                result = eduMarineMngMapper.updatePress(pressDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + pressDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdatePress ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertPress(PressDTO pressDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertPress");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(StringUtil.isEmpty(pressDTO.getNoticeGbn()) || "off".equals(pressDTO.getNoticeGbn())){
                pressDTO.setNoticeGbn("0");
            }else{
                pressDTO.setNoticeGbn("1");
            }

            String getSeq = eduMarineMngMapper.getPressSeq();
            pressDTO.setSeq(getSeq);

            String content = pressDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            pressDTO.setContent(content);
            result = eduMarineMngMapper.insertPress(pressDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertPress ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<GalleryDTO> processSelectGalleryList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectGalleryList");
        return eduMarineMngMapper.selectGalleryList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public GalleryDTO processSelectGallerySingle(GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectGallerySingle");
        return eduMarineMngMapper.selectGallerySingle(galleryDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteGallery(GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteGallery");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(galleryDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteGallery(galleryDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + galleryDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteGallery ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateGallery(GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateGallery");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(galleryDTO.getSeq())){

                result = eduMarineMngMapper.updateGallery(galleryDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + galleryDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateGallery ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertGallery(GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertGallery");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getGallerySeq();
            galleryDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertGallery(galleryDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertGallery ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<MediaDTO> processSelectMediaList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectMediaList");
        return eduMarineMngMapper.selectMediaList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public MediaDTO processSelectMediaSingle(MediaDTO mediaDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectMediaSingle");
        return eduMarineMngMapper.selectMediaSingle(mediaDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteMedia(MediaDTO mediaDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteMedia");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(mediaDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteMedia(mediaDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + mediaDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteMedia ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateMedia(MediaDTO mediaDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateMedia");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(mediaDTO.getSeq())){

                result = eduMarineMngMapper.updateMedia(mediaDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + mediaDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateMedia ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertMedia(MediaDTO mediaDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertMedia");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getMediaSeq();
            mediaDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertMedia(mediaDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertMedia ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<NewsletterDTO> processSelectNewsletterList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectNewsletterList");
        return eduMarineMngMapper.selectNewsletterList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public NewsletterDTO processSelectNewsletterSingle(NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectNewsletterSingle");
        return eduMarineMngMapper.selectNewsletterSingle(newsletterDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteNewsletter(NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteNewsletter");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(newsletterDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteNewsletter(newsletterDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + newsletterDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteNewsletter ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateNewsletter(NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateNewsletter");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(newsletterDTO.getSeq())){
                if(StringUtil.isEmpty(newsletterDTO.getNoticeGbn()) || "off".equals(newsletterDTO.getNoticeGbn())){
                    newsletterDTO.setNoticeGbn("0");
                }else{
                    newsletterDTO.setNoticeGbn("1");
                }

                String contentGbn = newsletterDTO.getContentGbn();
                if("1".equals(contentGbn)){
                    String content = newsletterDTO.getContent();
                    if(content != null){
                        newsletterDTO.setContent(content.replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
                    }
                }else{
                    String contentTa = newsletterDTO.getContentTa();
                    if(contentTa != null){
                        newsletterDTO.setContentTa(contentTa.replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
                    }
                }

                result = eduMarineMngMapper.updateNewsletter(newsletterDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + newsletterDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateNewsletter ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertNewsletter(NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertNewsletter");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(StringUtil.isEmpty(newsletterDTO.getNoticeGbn()) || "off".equals(newsletterDTO.getNoticeGbn())){
                newsletterDTO.setNoticeGbn("0");
            }else{
                newsletterDTO.setNoticeGbn("1");
            }

            String getSeq = eduMarineMngMapper.getNewsletterSeq();
            newsletterDTO.setSeq(getSeq);

            String contentGbn = newsletterDTO.getContentGbn();
            if("1".equals(contentGbn)){
                String content = newsletterDTO.getContent();
                if(content != null){
                    newsletterDTO.setContent(content.replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
                }
            }else{
                String contentTa = newsletterDTO.getContentTa();
                if(contentTa != null){
                    newsletterDTO.setContentTa(contentTa.replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
                }
            }

            result = eduMarineMngMapper.insertNewsletter(newsletterDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertNewsletter ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<AnnouncementDTO> processSelectAnnouncementList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectAnnouncementList");
        return eduMarineMngMapper.selectAnnouncementList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public AnnouncementDTO processSelectAnnouncementSingle(AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectAnnouncementSingle");
        return eduMarineMngMapper.selectAnnouncementSingle(announcementDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteAnnouncement(AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteAnnouncement");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(announcementDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteAnnouncement(announcementDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + announcementDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteAnnouncement ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateAnnouncement(AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateAnnouncement");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(announcementDTO.getSeq())){
                if(StringUtil.isEmpty(announcementDTO.getNoticeGbn()) || "off".equals(announcementDTO.getNoticeGbn())){
                    announcementDTO.setNoticeGbn("0");
                }else{
                    announcementDTO.setNoticeGbn("1");
                }

                String content = announcementDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                announcementDTO.setContent(content);
                result = eduMarineMngMapper.updateAnnouncement(announcementDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + announcementDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateAnnouncement ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertAnnouncement(AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertAnnouncement");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(StringUtil.isEmpty(announcementDTO.getNoticeGbn()) || "off".equals(announcementDTO.getNoticeGbn())){
                announcementDTO.setNoticeGbn("0");
            }else{
                announcementDTO.setNoticeGbn("1");
            }

            String getSeq = eduMarineMngMapper.getAnnouncementSeq();
            announcementDTO.setSeq(getSeq);

            String content = announcementDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            announcementDTO.setContent(content);
            result = eduMarineMngMapper.insertAnnouncement(announcementDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertAnnouncement ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<JobDTO> processSelectJobList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectJobList");
        return eduMarineMngMapper.selectJobList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public JobDTO processSelectJobSingle(JobDTO jobDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectJobSingle");
        return eduMarineMngMapper.selectJobSingle(jobDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteJob(JobDTO jobDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteJob");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(jobDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteJob(jobDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + jobDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteJob ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateJob(JobDTO jobDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateJob");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(jobDTO.getSeq())){

                String content = jobDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                jobDTO.setContent(content);
                result = eduMarineMngMapper.updateJob(jobDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + jobDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateJob ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertJob(JobDTO jobDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertJob");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getJobSeq();
            jobDTO.setSeq(getSeq);

            String content = jobDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            jobDTO.setContent(content);
            result = eduMarineMngMapper.insertJob(jobDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertJob ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EmploymentDTO> processSelectEmploymentList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectEmploymentList");
        return eduMarineMngMapper.selectEmploymentList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public EmploymentDTO processSelectEmploymentSingle(EmploymentDTO employmentDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectEmploymentSingle");
        return eduMarineMngMapper.selectEmploymentSingle(employmentDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateEmployment(EmploymentDTO employmentDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateEmployment");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(employmentDTO.getSeq())){

                result = eduMarineMngMapper.updateEmployment(employmentDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + employmentDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateEmployment ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertEmployment(EmploymentDTO employmentDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertEmployment");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getEmploymentSeq();
            employmentDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertEmployment(employmentDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertEmployment ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CommunityDTO> processSelectCommunityList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectCommunityList");
        return eduMarineMngMapper.selectCommunityList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public CommunityDTO processSelectCommunitySingle(CommunityDTO communityDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectCommunitySingle");
        return eduMarineMngMapper.selectCommunitySingle(communityDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteCommunity(CommunityDTO communityDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteCommunity");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(communityDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteCommunity(communityDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + communityDTO.getSeq();
                }else{
                    // TODO: 댓글 삭제 프로세스


                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteCommunity ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCommunity(CommunityDTO communityDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateCommunity");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(communityDTO.getSeq())){

                if(StringUtil.isEmpty(communityDTO.getGbn()) || "off".equals(communityDTO.getGbn())){
                    communityDTO.setGbn("0");
                }else{
                    communityDTO.setGbn("1");
                }

                String content = communityDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                communityDTO.setContent(content);
                result = eduMarineMngMapper.updateCommunity(communityDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + communityDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
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
    public ResponseDTO processInsertCommunity(CommunityDTO communityDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertCommunity");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            if(StringUtil.isEmpty(communityDTO.getGbn()) || "off".equals(communityDTO.getGbn())){
                communityDTO.setGbn("0");
            }else{
                communityDTO.setGbn("1");
            }

            String getSeq = eduMarineMngMapper.getCommunitySeq();
            communityDTO.setSeq(getSeq);

            String content = communityDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            communityDTO.setContent(content);
            result = eduMarineMngMapper.insertCommunity(communityDTO);

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
    public List<FaqDTO> processSelectFaqList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectFaqList");
        return eduMarineMngMapper.selectFaqList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FaqDTO processSelectFaqSingle(FaqDTO faqDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectFaqSingle");
        return eduMarineMngMapper.selectFaqSingle(faqDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PopupDTO> processSelectPopupList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectPopupList");
        return eduMarineMngMapper.selectPopupList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFaq(FaqDTO faqDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFaq");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(faqDTO.getSeq())){

                String answer = faqDTO.getAnswer().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                faqDTO.setAnswer(answer);
                result = eduMarineMngMapper.updateFaq(faqDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + faqDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFaq ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertFaq(FaqDTO faqDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertFaq");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getFaqSeq();
            faqDTO.setSeq(getSeq);

            String answer = faqDTO.getAnswer().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
            faqDTO.setAnswer(answer);
            result = eduMarineMngMapper.insertFaq(faqDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFaq ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PopupDTO processSelectPopupSingle(PopupDTO popupDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectPopupSingle");
        return eduMarineMngMapper.selectPopupSingle(popupDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeletePopup(PopupDTO popupDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeletePopup");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(popupDTO.getSeq() != null){
                result = eduMarineMngMapper.deletePopup(popupDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + popupDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeletePopup ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdatePopup(PopupDTO popupDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdatePopup");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(popupDTO.getSeq())){

                Boolean updFlag = false;
                if(popupDTO.getUseYn().equals("Y")){
                    /* 팝업파일정보 */
                    PopupDTO reqDTO = new PopupDTO();
                    reqDTO.setUseYn("Y");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String today = dateFormat.format(new Date());
                    reqDTO.setToday(today);

                    Integer activePopupCount = eduMarineMngMapper.getActivePopupCount(reqDTO);
                    if(activePopupCount < 4){
                        updFlag = true;
                    }else{
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "사용 가능한 팝업의 갯수는 최대 3개입니다. 팝업을 새로 추가하시려면 사용중인 팝업을 미사용으로 변경해주세요.";
                    }
                }else{
                    updFlag = true;
                }

                if(updFlag){

                    String content = popupDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                    popupDTO.setContent(content);
                    result = eduMarineMngMapper.updatePopup(popupDTO);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail] Seq : " + popupDTO.getSeq();
                    }
                    //System.out.println(result);
                }
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdatePopup ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertPopup(PopupDTO popupDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertPopup");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            Boolean istFlag = false;
            if(popupDTO.getUseYn().equals("Y")){
                /* 팝업파일정보 */
                PopupDTO reqDTO = new PopupDTO();
                reqDTO.setUseYn("Y");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String today = dateFormat.format(new Date());
                reqDTO.setToday(today);

                Integer activePopupCount = eduMarineMngMapper.getActivePopupCount(reqDTO);
                if(activePopupCount < 4){
                    istFlag = true;
                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "사용 가능한 팝업의 갯수는 최대 3개입니다. 팝업을 새로 추가하시려면 사용중인 팝업을 미사용으로 변경해주세요.";
                }
            }else{
                istFlag = true;
            }

            if(istFlag){
                String seq = eduMarineMngMapper.getPopupSeq();
                popupDTO.setSeq(seq);

                String content = popupDTO.getContent().replaceAll("&lt;","<").replaceAll("&gt;",">").trim();
                popupDTO.setContent(content);

                result = eduMarineMngMapper.insertPopup(popupDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Insert Fail]";
                }
                responseDTO.setCustomValue(seq);
                //System.out.println(result);
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertPopup ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BannerDTO> processSelectBannerList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectBannerList");
        return eduMarineMngMapper.selectBannerList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public BannerDTO processSelectBannerSingle(BannerDTO bannerDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectBannerSingle");
        return eduMarineMngMapper.selectBannerSingle(bannerDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteBanner(BannerDTO bannerDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteBanner");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(bannerDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteBanner(bannerDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + bannerDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteBanner ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBanner(BannerDTO bannerDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateBanner");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(bannerDTO.getSeq())){

                result = eduMarineMngMapper.updateBanner(bannerDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + bannerDTO.getSeq();
                }
                //System.out.println(result);
                responseDTO.setCustomValue(bannerDTO.getSeq());
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateBanner ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertBanner(BannerDTO bannerDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertBanner");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getBannerSeq();
            bannerDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertBanner(bannerDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertBanner ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<MemberDTO> processSelectMemberList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectMemberList");
        return eduMarineMngMapper.selectMemberList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public MemberDTO processSelectMemberSingle(MemberDTO memberDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectMemberSingle");
        return eduMarineMngMapper.selectMemberSingle(memberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteMember(MemberDTO memberDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteMember");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(memberDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteMember(memberDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + memberDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteMember ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateMember(MemberDTO memberDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateMember");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(memberDTO.getSeq())){

                result = eduMarineMngMapper.updateMember(memberDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + memberDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateMember ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertMember(MemberDTO memberDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertMember");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getMemberSeq();
            memberDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertMember(memberDTO);

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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<ResumeDTO> processSelectResumeList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectResumeList");
        return eduMarineMngMapper.selectResumeList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResumeDTO processSelectResumeSingle(ResumeDTO resumeDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectResumeSingle");
        return eduMarineMngMapper.selectResumeSingle(resumeDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<RegularDTO> processSelectRegularList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectRegularList");
        return eduMarineMngMapper.selectRegularList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RegularDTO processSelectRegularSingle(RegularDTO regularDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectRegularSingle");
        return eduMarineMngMapper.selectRegularSingle(regularDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteRegular(RegularDTO regularDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteRegular");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(regularDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteRegular(regularDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + regularDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteRegular ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRegular(RegularDTO regularDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateRegular");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(regularDTO.getSeq())){

                result = eduMarineMngMapper.updateRegular(regularDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + regularDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateRegular ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertRegular(RegularDTO regularDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertRegular");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getRegularSeq();
            regularDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertRegular(regularDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
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
    public ResponseDTO processUpdateRegularApplyStatus(List<RegularDTO> regularList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateRegularApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(RegularDTO info : regularList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    RegularDTO regularReq = new RegularDTO();
                    regularReq.setSeq(info.getSeq());
                    RegularDTO regularInfo = eduMarineMngMapper.selectRegularSingle(regularReq);
                    if(regularInfo != null){

                        boolean cancelApiCallYn = false;
                        if("취소신청".equals(regularInfo.getApplyStatus()) || "결제완료".equals(regularInfo.getApplyStatus())){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateRegularApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateRegularApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRegularApplyStatusChange(List<RegularDTO> regularList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateRegularApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(RegularDTO info : regularList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    RegularDTO regularReq = new RegularDTO();
                    regularReq.setSeq(info.getSeq());
                    RegularDTO regularInfo = eduMarineMngMapper.selectRegularSingle(regularReq);
                    if(regularInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(regularInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(regularInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(regularInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(regularInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(regularInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateRegularApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateRegularApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainNextTime(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainNextTime");
        return eduMarineMngMapper.selectTrainNextTime(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainActive(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainActive");
        return eduMarineMngMapper.selectTrainActive(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BoarderDTO> processSelectBoarderList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectBoarderList");
        return eduMarineMngMapper.selectBoarderList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public BoarderDTO processSelectBoarderSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectBoarderSingle");
        return eduMarineMngMapper.selectBoarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FrpDTO processSelectFrpSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectFrpSingle");
        return eduMarineMngMapper.selectFrpSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CareerDTO> processSelectCareerList(String boarderSeq) {
        System.out.println("EduMarineMngServiceImpl > processSelectCareerList");
        return eduMarineMngMapper.selectCareerList(boarderSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<LicenseDTO> processSelectLicenseList(String boarderSeq) {
        System.out.println("EduMarineMngServiceImpl > processSelectLicenseList");
        return eduMarineMngMapper.selectLicenseList(boarderSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoarderApplyStatus(List<BoarderDTO> boarderList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateBoarderApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(BoarderDTO info : boarderList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    BoarderDTO boarderInfo = eduMarineMngMapper.selectBoarderSingle(info.getSeq());
                    if(boarderInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(boarderInfo.getApplyStatus()) || "취소신청".equals(boarderInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = boarderInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(boarderInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(boarderInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(boarderInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = boarderInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(boarderInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(boarderInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(boarderInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateBoarderApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(boarderInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }
                            }else{
                                Integer result = eduMarineMngMapper.updateBoarderApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(boarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateBoarderApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(boarderInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//boarderInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateBoarderApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBoarderApplyStatusChange(List<BoarderDTO> boarderList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateBoarderApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(BoarderDTO info : boarderList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    BoarderDTO boarderInfo = eduMarineMngMapper.selectBoarderSingle(info.getSeq());
                    if(boarderInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(boarderInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(boarderInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(boarderInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(boarderInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(boarderInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateBoarderApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(boarderInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateBoarderApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FrpDTO> processSelectFrpList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectFrpList");
        return eduMarineMngMapper.selectFrpList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFrpApplyStatus(List<FrpDTO> frpList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFrpApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(FrpDTO info : frpList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    FrpDTO frpInfo = eduMarineMngMapper.selectFrpSingle(info.getSeq());
                    if(frpInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(frpInfo.getApplyStatus()) || "취소신청".equals(frpInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = frpInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(frpInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(frpInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(frpInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = frpInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(frpInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(frpInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(frpInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateFrpApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(frpInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateFrpApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(frpInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateFrpApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(frpInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//frpInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFrpApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFrpApplyStatusChange(List<FrpDTO> frpList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFrpApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(FrpDTO info : frpList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    FrpDTO frpInfo = eduMarineMngMapper.selectFrpSingle(info.getSeq());
                    if(frpInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(frpInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(frpInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(frpInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(frpInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(frpInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateFrpApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(frpInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFrpApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BasicDTO> processSelectBasicList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectBasicList");
        return eduMarineMngMapper.selectBasicList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public BasicDTO processSelectBasicSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectBasicSingle");
        return eduMarineMngMapper.selectBasicSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBasicApplyStatus(List<BasicDTO> basicList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateBasicApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(BasicDTO info : basicList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    BasicDTO basicInfo = eduMarineMngMapper.selectBasicSingle(info.getSeq());
                    if(basicInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(basicInfo.getApplyStatus()) || "취소신청".equals(basicInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = basicInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(basicInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(basicInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(basicInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = basicInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(basicInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(basicInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(basicInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateBasicApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(basicInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateBasicApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(outboarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateBasicApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(basicInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//outboarderInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateBasicApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateBasicApplyStatusChange(List<BasicDTO> basicList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateBasicApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(BasicDTO info : basicList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    BasicDTO basicInfo = eduMarineMngMapper.selectBasicSingle(info.getSeq());
                    if(basicInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(basicInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(basicInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(basicInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(basicInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(basicInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateBasicApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(basicInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateBasicApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EmergencyDTO> processSelectEmergencyList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectEmergencyList");
        return eduMarineMngMapper.selectEmergencyList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public EmergencyDTO processSelectEmergencySingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectEmergencySingle");
        return eduMarineMngMapper.selectEmergencySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateEmergencyApplyStatus(List<EmergencyDTO> emergencyList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateEmergencyApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(EmergencyDTO info : emergencyList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    EmergencyDTO emergencyInfo = eduMarineMngMapper.selectEmergencySingle(info.getSeq());
                    if(emergencyInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(emergencyInfo.getApplyStatus()) || "취소신청".equals(emergencyInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = emergencyInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(emergencyInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(emergencyInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(emergencyInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = emergencyInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(emergencyInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(emergencyInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(emergencyInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateEmergencyApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(emergencyInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateEmergencyApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(outboarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateEmergencyApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(emergencyInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//outboarderInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateEmergencyApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateEmergencyApplyStatusChange(List<EmergencyDTO> emergencyList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateEmergencyApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(EmergencyDTO info : emergencyList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    EmergencyDTO emergencyInfo = eduMarineMngMapper.selectEmergencySingle(info.getSeq());
                    if(emergencyInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(emergencyInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(emergencyInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(emergencyInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(emergencyInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(emergencyInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateEmergencyApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(emergencyInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateEmergencyApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<GeneratorDTO> processSelectGeneratorList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectGeneratorList");
        return eduMarineMngMapper.selectGeneratorList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public GeneratorDTO processSelectGeneratorSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectGeneratorSingle");
        return eduMarineMngMapper.selectGeneratorSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateGeneratorApplyStatus(List<GeneratorDTO> generatorList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateGeneratorApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(GeneratorDTO info : generatorList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    GeneratorDTO generatorInfo = eduMarineMngMapper.selectGeneratorSingle(info.getSeq());
                    if(generatorInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(generatorInfo.getApplyStatus()) || "취소신청".equals(generatorInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = generatorInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(generatorInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(generatorInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(generatorInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = generatorInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(generatorInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(generatorInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(generatorInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateGeneratorApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(generatorInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateGeneratorApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(outboarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateGeneratorApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(generatorInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//generatorInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateGeneratorApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateGeneratorApplyStatusChange(List<GeneratorDTO> generatorList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateGeneratorApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(GeneratorDTO info : generatorList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    GeneratorDTO generatorInfo = eduMarineMngMapper.selectGeneratorSingle(info.getSeq());
                    if(generatorInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(generatorInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(generatorInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(generatorInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(generatorInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(generatorInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateGeneratorApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(generatorInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateGeneratorApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CompetencyDTO> processSelectCompetencyList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectCompetencyList");
        return eduMarineMngMapper.selectCompetencyList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public CompetencyDTO processSelectCompetencySingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectCompetencySingle");
        return eduMarineMngMapper.selectCompetencySingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCompetencyApplyStatus(List<CompetencyDTO> competencyList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateCompetencyApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(CompetencyDTO info : competencyList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    CompetencyDTO competencyInfo = eduMarineMngMapper.selectCompetencySingle(info.getSeq());
                    if(competencyInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(competencyInfo.getApplyStatus()) || "취소신청".equals(competencyInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = competencyInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(competencyInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(competencyInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(competencyInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = competencyInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(competencyInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(competencyInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(competencyInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateCompetencyApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(competencyInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateCompetencyApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(outboarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateCompetencyApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(competencyInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//generatorInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateCompetencyApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateCompetencyApplyStatusChange(List<CompetencyDTO> competencyList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateCompetencyApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(CompetencyDTO info : competencyList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    CompetencyDTO competencyInfo = eduMarineMngMapper.selectCompetencySingle(info.getSeq());
                    if(competencyInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(competencyInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(competencyInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(competencyInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(competencyInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(competencyInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateCompetencyApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(competencyInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateCompetencyApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FamtourinDTO> processSelectFamtourinList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectFamtourinList");
        return eduMarineMngMapper.selectFamtourinList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FamtourinDTO processSelectFamtourinSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectFamtourinSingle");
        return eduMarineMngMapper.selectFamtourinSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourinApplyStatus(List<FamtourinDTO> famtourinList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFamtourinApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(FamtourinDTO info : famtourinList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    FamtourinDTO famtourinInfo = eduMarineMngMapper.selectFamtourinSingle(info.getSeq());
                    if(famtourinInfo != null){

                        // 결제대기 , 취소완료 건 취소승인처리
                        Integer result = eduMarineMngMapper.updateFamtourinApplyStatus(info);
                        if(result == 0){
                            resultCode = CommConstants.RESULT_CODE_FAIL;
                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                            break;
                        }else{
                            // 교육 신청인원 빼기
                            eduMarineMngMapper.updateTrainApplyCnt(famtourinInfo.getTrainSeq());
                        }

                    }//generatorInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFamtourinApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtourinApplyStatusChange(List<FamtourinDTO> famtourinList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFamtourinApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(FamtourinDTO info : famtourinList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    FamtourinDTO famtourinInfo = eduMarineMngMapper.selectFamtourinSingle(info.getSeq());
                    if(famtourinInfo != null){

                        boolean cancelApiCallYn = ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(famtourinInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(famtourinInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateFamtourinApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFamtourinApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FamtouroutDTO> processSelectFamtouroutList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectFamtouroutList");
        return eduMarineMngMapper.selectFamtouroutList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FamtouroutDTO processSelectFamtouroutSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectFamtouroutSingle");
        return eduMarineMngMapper.selectFamtouroutSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtouroutApplyStatus(List<FamtouroutDTO> famtouroutList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFamtouroutApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(FamtouroutDTO info : famtouroutList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    FamtouroutDTO famtouroutInfo = eduMarineMngMapper.selectFamtouroutSingle(info.getSeq());
                    if(famtouroutInfo != null){

                        // 결제대기 , 취소완료 건 취소승인처리
                        Integer result = eduMarineMngMapper.updateFamtouroutApplyStatus(info);
                        if(result == 0){
                            resultCode = CommConstants.RESULT_CODE_FAIL;
                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                            break;
                        }else{
                            // 교육 신청인원 빼기
                            eduMarineMngMapper.updateTrainApplyCnt(famtouroutInfo.getTrainSeq());
                        }

                    }//generatorInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFamtouroutApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFamtouroutApplyStatusChange(List<FamtouroutDTO> famtouroutList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFamtouroutApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(FamtouroutDTO info : famtouroutList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    FamtouroutDTO famtouroutInfo = eduMarineMngMapper.selectFamtouroutSingle(info.getSeq());
                    if(famtouroutInfo != null){

                        boolean cancelApiCallYn = ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(famtouroutInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(famtouroutInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateFamtouroutApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFamtouroutApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<ElectroDTO> processSelectElectroList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectElectroList");
        return eduMarineMngMapper.selectElectroList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ElectroDTO processSelectElectroSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectElectroSingle");
        return eduMarineMngMapper.selectElectroSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateElectroApplyStatus(List<ElectroDTO> electroList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateElectroApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(ElectroDTO info : electroList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    ElectroDTO electroInfo = eduMarineMngMapper.selectElectroSingle(info.getSeq());
                    if(electroInfo != null){

                        // 결제대기 , 취소완료 건 취소승인처리
                        Integer result = eduMarineMngMapper.updateElectroApplyStatus(info);
                        if(result == 0){
                            resultCode = CommConstants.RESULT_CODE_FAIL;
                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                            break;
                        }else{
                            // 교육 신청인원 빼기
                            eduMarineMngMapper.updateTrainApplyCnt(electroInfo.getTrainSeq());
                        }

                    }//generatorInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateElectroApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateElectroApplyStatusChange(List<ElectroDTO> electroList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateElectroApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(ElectroDTO info : electroList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    ElectroDTO electroInfo = eduMarineMngMapper.selectElectroSingle(info.getSeq());
                    if(electroInfo != null){

                        boolean cancelApiCallYn = ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(electroInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(electroInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateElectroApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateElectroApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<OutboarderDTO> processSelectOutboarderList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectOutboarderList");
        return eduMarineMngMapper.selectOutboarderList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public OutboarderDTO processSelectOutboarderSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectOutboarderSingle");
        return eduMarineMngMapper.selectOutboarderSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateOutboarderApplyStatus(List<OutboarderDTO> outboarderList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateOutboarderApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(OutboarderDTO info : outboarderList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    OutboarderDTO outboarderInfo = eduMarineMngMapper.selectOutboarderSingle(info.getSeq());
                    if(outboarderInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(outboarderInfo.getApplyStatus()) || "취소신청".equals(outboarderInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = outboarderInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(outboarderInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(outboarderInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(outboarderInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = outboarderInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(outboarderInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(outboarderInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(outboarderInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateOutboarderApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(outboarderInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                // 결제 안한 취소 신청건 취소승인처리
                                Integer result = eduMarineMngMapper.updateOutboarderApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(outboarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateOutboarderApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(outboarderInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//outboarderInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateOutboarderApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateOutboarderApplyStatusChange(List<OutboarderDTO> outboarderList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateOutboarderApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(OutboarderDTO info : outboarderList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    OutboarderDTO outboarderInfo = eduMarineMngMapper.selectOutboarderSingle(info.getSeq());
                    if(outboarderInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(outboarderInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(outboarderInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(outboarderInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(outboarderInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(outboarderInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateOutboarderApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(outboarderInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateOutboarderApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<InboarderDTO> processSelectInboarderList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectInboarderList");
        return eduMarineMngMapper.selectInboarderList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public InboarderDTO processSelectInboarderSingle(InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectInboarderSingle");
        return eduMarineMngMapper.selectInboarderSingle(inboarderDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteInboarder(InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteInboarder");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(inboarderDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteInboarder(inboarderDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + inboarderDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteInboarder ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateInboarder(InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateInboarder");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(inboarderDTO.getSeq())){

                result = eduMarineMngMapper.updateInboarder(inboarderDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + inboarderDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateInboarder ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertInboarder(InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertInboarder");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getInboarderSeq();
            inboarderDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertInboarder(inboarderDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
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
    public ResponseDTO processUpdateInboarderApplyStatus(List<InboarderDTO> inboarderList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateInboarderApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(InboarderDTO info : inboarderList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    InboarderDTO inboarderReq = new InboarderDTO();
                    inboarderReq.setSeq(info.getSeq());
                    InboarderDTO inboarderInfo = eduMarineMngMapper.selectInboarderSingle(inboarderReq);
                    if(inboarderInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(inboarderInfo.getApplyStatus()) || "취소신청".equals(inboarderInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = inboarderInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(inboarderInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(inboarderInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(inboarderInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = inboarderInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(inboarderInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(inboarderInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(inboarderInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateInboarderApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(inboarderInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateInboarderApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(inboarderInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateInboarderApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(inboarderInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//inboarderInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateInboarderApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateInboarderApplyStatusChange(List<InboarderDTO> inboarderList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateInboarderApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(InboarderDTO info : inboarderList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    InboarderDTO inboarderReq = new InboarderDTO();
                    inboarderReq.setSeq(info.getSeq());
                    InboarderDTO inboarderInfo = eduMarineMngMapper.selectInboarderSingle(inboarderReq);
                    if(inboarderInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(inboarderInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(inboarderInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(inboarderInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(inboarderInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(inboarderInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateInboarderApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(inboarderInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateInboarderApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SailyachtDTO> processSelectSailyachtList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSailyachtList");
        return eduMarineMngMapper.selectSailyachtList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SailyachtDTO processSelectSailyachtSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectSailyachtSingle");
        return eduMarineMngMapper.selectSailyachtSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSailyachtApplyStatus(List<SailyachtDTO> sailyachtList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSailyachtApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(SailyachtDTO info : sailyachtList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    SailyachtDTO sailyachtInfo = eduMarineMngMapper.selectSailyachtSingle(info.getSeq());
                    if(sailyachtInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(sailyachtInfo.getApplyStatus()) || "취소신청".equals(sailyachtInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = sailyachtInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(sailyachtInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(sailyachtInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(sailyachtInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = sailyachtInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(sailyachtInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(sailyachtInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(sailyachtInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateSailyachtApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(sailyachtInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateSailyachtApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(sailyachtInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateSailyachtApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(sailyachtInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//sailyachtInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateSailyachtApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSailyachtApplyStatusChange(List<SailyachtDTO> sailyachtList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSailyachtApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(SailyachtDTO info : sailyachtList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    SailyachtDTO sailyachtInfo = eduMarineMngMapper.selectSailyachtSingle(info.getSeq());
                    if(sailyachtInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(sailyachtInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(sailyachtInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(sailyachtInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(sailyachtInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(sailyachtInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateSailyachtApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(sailyachtInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateSailyachtApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<HighHorsePowerDTO> processSelectHighhorsepowerList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectHighhorsepowerList");
        return eduMarineMngMapper.selectHighhorsepowerList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public HighHorsePowerDTO processSelectHighhorsepowerSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectHighhorsepowerSingle");
        return eduMarineMngMapper.selectHighhorsepowerSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighhorsepowerApplyStatus(List<HighHorsePowerDTO> highHorsePowerList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateHighhorsepowerApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(HighHorsePowerDTO info : highHorsePowerList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    HighHorsePowerDTO highhorsepowerInfo = eduMarineMngMapper.selectHighhorsepowerSingle(info.getSeq());
                    if(highhorsepowerInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(highhorsepowerInfo.getApplyStatus()) || "취소신청".equals(highhorsepowerInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = highhorsepowerInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(highhorsepowerInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(highhorsepowerInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(highhorsepowerInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = highhorsepowerInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(highhorsepowerInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(highhorsepowerInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(highhorsepowerInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateHighhorsepowerApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(highhorsepowerInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateHighhorsepowerApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(sailyachtInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateHighhorsepowerApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(highhorsepowerInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//sailyachtInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighhorsepowerApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighhorsepowerApplyStatusChange(List<HighHorsePowerDTO> highHorsePowerList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateHighhorsepowerApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(HighHorsePowerDTO info : highHorsePowerList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    HighHorsePowerDTO highhorsepowerInfo = eduMarineMngMapper.selectHighhorsepowerSingle(info.getSeq());
                    if(highhorsepowerInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(highhorsepowerInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(highhorsepowerInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(highhorsepowerInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(highhorsepowerInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(highhorsepowerInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateHighhorsepowerApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(highhorsepowerInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighhorsepowerApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<HighSelfDTO> processSelectHighSelfList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectHighSelfList");
        return eduMarineMngMapper.selectHighSelfList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public HighSelfDTO processSelectHighSelfSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectHighSelfSingle");
        return eduMarineMngMapper.selectHighSelfSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSelfApplyStatus(List<HighSelfDTO> highSelfList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateHighSelfApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(HighSelfDTO info : highSelfList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    HighSelfDTO highSelfInfo = eduMarineMngMapper.selectHighSelfSingle(info.getSeq());
                    if(highSelfInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(highSelfInfo.getApplyStatus()) || "취소신청".equals(highSelfInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = highSelfInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(highSelfInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(highSelfInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(highSelfInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = highSelfInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(highSelfInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(highSelfInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(highSelfInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateHighSelfApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(highSelfInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateHighSelfApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(sailyachtInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateHighSelfApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(highSelfInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//sailyachtInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighSelfApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSelfApplyStatusChange(List<HighSelfDTO> highSelfList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateHighSelfApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(HighSelfDTO info : highSelfList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    HighSelfDTO highSelfInfo = eduMarineMngMapper.selectHighSelfSingle(info.getSeq());
                    if(highSelfInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(highSelfInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(highSelfInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(highSelfInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(highSelfInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(highSelfInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateHighSelfApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(highSelfInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighSelfApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<HighSpecialDTO> processSelectHighSpecialList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectHighSpecialList");
        return eduMarineMngMapper.selectHighSpecialList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public HighSpecialDTO processSelectHighSpecialSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectHighSpecialSingle");
        return eduMarineMngMapper.selectHighSpecialSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSpecialApplyStatus(List<HighSpecialDTO> highSpecialList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateHighSpecialApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(HighSpecialDTO info : highSpecialList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    HighSpecialDTO highSpecialInfo = eduMarineMngMapper.selectHighSpecialSingle(info.getSeq());
                    if(highSpecialInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(highSpecialInfo.getApplyStatus()) || "취소신청".equals(highSpecialInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = highSpecialInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(highSpecialInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(highSpecialInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(highSpecialInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = highSpecialInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(highSpecialInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(highSpecialInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(highSpecialInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateHighSpecialApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(highSpecialInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateHighSpecialApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(sailyachtInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateHighSpecialApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(highSpecialInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//sailyachtInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighSelfApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateHighSpecialApplyStatusChange(List<HighSpecialDTO> highSpecialList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateHighSpecialApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(HighSpecialDTO info : highSpecialList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    HighSpecialDTO highSpecialInfo = eduMarineMngMapper.selectHighSpecialSingle(info.getSeq());
                    if(highSpecialInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(highSpecialInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(highSpecialInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(highSpecialInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(highSpecialInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(highSpecialInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateHighSpecialApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(highSpecialInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighSpecialApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SterndriveDTO> processSelectSterndriveList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSterndriveList");
        return eduMarineMngMapper.selectSterndriveList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SterndriveDTO processSelectSterndriveSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectSterndriveSingle");
        return eduMarineMngMapper.selectSterndriveSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSterndriveApplyStatus(List<SterndriveDTO> sterndriveList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSterndriveApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(SterndriveDTO info : sterndriveList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    SterndriveDTO sterndriveInfo = eduMarineMngMapper.selectSterndriveSingle(info.getSeq());
                    if(sterndriveInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(sterndriveInfo.getApplyStatus()) || "취소신청".equals(sterndriveInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = sterndriveInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(sterndriveInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(sterndriveInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(sterndriveInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = sterndriveInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(sterndriveInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(sterndriveInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(sterndriveInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateSterndriveApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(sterndriveInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateSterndriveApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(sailyachtInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateSterndriveApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(sterndriveInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//sterndriveInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateSterndriveApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSterndriveApplyStatusChange(List<SterndriveDTO> sterndriveList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSterndriveApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(SterndriveDTO info : sterndriveList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    SterndriveDTO sterndriveInfo = eduMarineMngMapper.selectSterndriveSingle(info.getSeq());
                    if(sterndriveInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(sterndriveInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(sterndriveInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(sterndriveInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(sterndriveInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(sterndriveInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateSterndriveApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(sterndriveInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateSterndriveApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SternSpecialDTO> processSelectSternSpecialList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSternSpecialList");
        return eduMarineMngMapper.selectSternSpecialList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SternSpecialDTO processSelectSternSpecialSingle(String seq) {
        System.out.println("EduMarineMngServiceImpl > processSelectSternSpecialSingle");
        return eduMarineMngMapper.selectSternSpecialSingle(seq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSternSpecialApplyStatus(List<SternSpecialDTO> highSpecialList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSternSpecialApplyStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(SternSpecialDTO info : highSpecialList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    SternSpecialDTO sternSpecialInfo = eduMarineMngMapper.selectSternSpecialSingle(info.getSeq());
                    if(sternSpecialInfo != null){

                        boolean cancelApiCallYn = false;
                        if( "결제완료".equals(sternSpecialInfo.getApplyStatus()) || "취소신청".equals(sternSpecialInfo.getApplyStatus()) ){
                            cancelApiCallYn = true;
                        }

                        if(cancelApiCallYn){

                            // 결제 내역이 있는 신청건인지 payment table 조회
                            // where table_seq = boarder table seq
                            PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(info.getSeq());
                            if(paymentDTO != null){

                                // 교육 정보 조회
                                TrainDTO trainReqDTO = new TrainDTO();
                                trainReqDTO.setSeq(paymentDTO.getTrainSeq());
                                TrainDTO trainDTO = eduMarineMngMapper.selectTrainSingle(trainReqDTO);

                                if(trainDTO != null){

                                    if("Card".equalsIgnoreCase(paymentDTO.getPayMethod()) || "VCard".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ // 전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }

                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = sternSpecialInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }
                                    }else if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())){
                                        if("ALL".equals(info.getCancelGbn())){ //전체환불
                                            InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                            inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                            inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                            inistdpayCancelRequestDTO.setMsg("전액 환불");
                                            inistdpayCancelRequestDTO.setRefundAcctNum(sternSpecialInfo.getRefundBankNumber());
                                            inistdpayCancelRequestDTO.setRefundBankCode(sternSpecialInfo.getRefundBankCode());
                                            inistdpayCancelRequestDTO.setRefundAcctName(sternSpecialInfo.getRefundBankCustomerName());
                                            // 환불 API CALL
                                            InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                            if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                                resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                            }else{
                                                PaymentDTO updCancelPayment = new PaymentDTO();
                                                updCancelPayment.setSeq(paymentDTO.getSeq());
                                                updCancelPayment.setCancelGbn("ALL");
                                                updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                            }
                                        }else if("PART".equals(info.getCancelGbn())){ // 부분환불
                                            //String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                                            String cancelDttm = sternSpecialInfo.getCancelDttm(); // 취소신청일자 yyyy-mm-dd hh24:mi:ss
                                            String trainStartDttm = trainDTO.getTrainStartDttm(); // 교육시작일자 yyyy.MM.dd

                                            DateTimeFormatter formatter_beforeCancelDttm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                            DateTimeFormatter formatter_afterTrainStartDttm = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                                            LocalDate beforeCancelDttm = LocalDate.parse(cancelDttm, formatter_beforeCancelDttm);
                                            LocalDate afterTrainStartDttm = LocalDate.parse(trainStartDttm, formatter_afterTrainStartDttm);

                                            long diff = beforeCancelDttm.until(afterTrainStartDttm, ChronoUnit.DAYS);
                                            boolean apiFlag = true;
                                            String msg = "교육비 환불 규정에 따른 환불 : ";
                                            Integer price = paymentDTO.getPaySum();
                                            Integer confirmPrice = paymentDTO.getPaySum();
                                            if(diff > 10){
                                                msg = msg + "결제금액의 100% 환불 처리";
                                            }else if(5 <= diff){ // 교육 개설 5일 ~ 10일 50프로 금액 환불
                                                price = (int) (price * 0.5);
                                                confirmPrice = confirmPrice - price;
                                                msg = msg + "결제금액의 50% 환불 처리";
                                            }else {
                                                apiFlag = false;
                                            }

                                            if(apiFlag){

                                                InistdpayCancelRequestDTO inistdpayCancelRequestDTO = new InistdpayCancelRequestDTO();
                                                inistdpayCancelRequestDTO.setCancelGbn(info.getCancelGbn());
                                                inistdpayCancelRequestDTO.setTid(paymentDTO.getTid());
                                                inistdpayCancelRequestDTO.setMsg(msg);
                                                inistdpayCancelRequestDTO.setPrice(String.valueOf(price));
                                                inistdpayCancelRequestDTO.setConfirmPrice(String.valueOf(confirmPrice));
                                                inistdpayCancelRequestDTO.setRefundAcctNum(sternSpecialInfo.getRefundBankNumber());
                                                inistdpayCancelRequestDTO.setRefundBankCode(sternSpecialInfo.getRefundBankCode());
                                                inistdpayCancelRequestDTO.setRefundAcctName(sternSpecialInfo.getRefundBankCustomerName());
                                                // 환불 API CALL
                                                InistdpayCancelResponseDTO inistdpayCancelResponseDTO = processApplyPaymentVbankCancelApi(inistdpayCancelRequestDTO);
                                                if(!"00".equals(inistdpayCancelResponseDTO.getResultCode())){
                                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                                    resultMessage = "[INICIS ALL CANCEL FAIL] SEQ : " + info.getSeq() + " / [CODE] MSG : [" + inistdpayCancelResponseDTO.getResultCode() + "] " + inistdpayCancelResponseDTO.getResultMsg();
                                                }else{
                                                    PaymentDTO updCancelPayment = new PaymentDTO();
                                                    updCancelPayment.setSeq(paymentDTO.getSeq());
                                                    updCancelPayment.setCancelGbn("PART");
                                                    updCancelPayment.setCancelTime(inistdpayCancelResponseDTO.getCancelTime());
                                                    updCancelPayment.setCancelDate(inistdpayCancelResponseDTO.getCancelDate());
                                                    updCancelPayment.setPrtcDate(inistdpayCancelResponseDTO.getPrtcDate());
                                                    updCancelPayment.setPrtcTime(inistdpayCancelResponseDTO.getPrtcTime());
                                                    updCancelPayment.setCancelTid(inistdpayCancelResponseDTO.getTid());
                                                    updCancelPayment.setPrtcPrice(inistdpayCancelResponseDTO.getPrtcPrice());
                                                    updCancelPayment.setPrtcRemains(inistdpayCancelResponseDTO.getPrtcRemains());
                                                    eduMarineMngMapper.updatePaymentCancelResult(updCancelPayment);
                                                }
                                            }
                                        }

                                    }

                                    if(CommConstants.RESULT_CODE_SUCCESS.equals(resultCode)){

                                        System.out.println("이전 신청상태 : " + info.getPreApplyStatus());
                                        Integer result = eduMarineMngMapper.updateSternSpecialApplyStatus(info);

                                        if(result == 0){
                                            resultCode = CommConstants.RESULT_CODE_FAIL;
                                            resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                            break;
                                        }else{
                                            // payment table
                                            // set pay_status = '취소완료'
                                            PaymentDTO updPayment = new PaymentDTO();
                                            updPayment.setSeq(paymentDTO.getSeq());
                                            updPayment.setPayStatus("취소완료");
                                            updPayment.setRefundReason(sternSpecialInfo.getCancelReason());
                                            eduMarineMngMapper.updatePayment(updPayment);

                                            // 교육 신청인원 빼기
                                            eduMarineMngMapper.updateTrainApplyCnt(trainDTO.getSeq());
                                        }
                                    }else{
                                        break;
                                    }

                                }else{
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[등록된 교육 정보가 없습니다.] Train Not Exist";
                                }

                            }else{
                                Integer result = eduMarineMngMapper.updateSternSpecialApplyStatus(info);
                                if(result == 0){
                                    resultCode = CommConstants.RESULT_CODE_FAIL;
                                    resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                    break;
                                }
                            }
                        }else{

                            // 미결제취소 건 취소승인처리
                            /*if( "미결제취소".equals(sailyachtInfo.getApplyStatus()) ) {
                                info.setApplyStatus(info.getApplyStatus() + "(미결제취소)");
                            }*/

                            // 결제대기 , 취소완료 건 취소승인처리
                            Integer result = eduMarineMngMapper.updateSternSpecialApplyStatus(info);
                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(sternSpecialInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }
                        }

                    }//sailyachtInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateHighSelfApplyStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSternSpecialApplyStatusChange(List<SternSpecialDTO> sternSpecialList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSternSpecialApplyStatusChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            for(SternSpecialDTO info : sternSpecialList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    SternSpecialDTO sternSpecialInfo = eduMarineMngMapper.selectSternSpecialSingle(info.getSeq());
                    if(sternSpecialInfo != null){

                        boolean cancelApiCallYn = ("수강확정".equals(info.getApplyStatus()) && "결제완료".equals(sternSpecialInfo.getApplyStatus()))
                                || ("수강완료".equals(info.getApplyStatus()) && "수강확정".equals(sternSpecialInfo.getApplyStatus()))
                                || ("환급대기".equals(info.getApplyStatus()) && "수강완료".equals(sternSpecialInfo.getApplyStatus()))
                                || ("환급완료".equals(info.getApplyStatus()) && "환급대기".equals(sternSpecialInfo.getApplyStatus()))
                                || ("결제완료".equals(info.getApplyStatus()) && "입금대기".equals(sternSpecialInfo.getApplyStatus()))
                                ;

                        if(cancelApiCallYn){

                            Integer result = eduMarineMngMapper.updateSternSpecialApplyStatus(info);

                            if(result == 0){
                                resultCode = CommConstants.RESULT_CODE_FAIL;
                                resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                                break;
                            }else{
                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(sternSpecialInfo.getSeq());
                                if(paymentDTO != null){
                                    paymentDTO.setPayStatus(info.getApplyStatus());
                                    eduMarineMngMapper.updatePayment(paymentDTO);
                                }
                            }

                        }

                    }//regularInfo

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateSternSpecialApplyStatusChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainDTO> processSelectTrainList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainList");
        return eduMarineMngMapper.selectTrainList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public TrainDTO processSelectTrainSingle(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainSingle");
        return eduMarineMngMapper.selectTrainSingle(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteTrain(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteTrain");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(trainDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteTrain(trainDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + trainDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteTrain ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateTrain(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateTrain");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(trainDTO.getSeq())){

                result = eduMarineMngMapper.updateTrain(trainDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + trainDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateTrain ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertTrain(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertTrain");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getTrainSeq();
            trainDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertTrain(trainDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertTrain ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateTrainEarlyClosing(TrainDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateTrainEarlyClosing");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(trainDTO.getSeq())){

                result = eduMarineMngMapper.updateTrainEarlyClosingYn(trainDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + trainDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateTrainEarlyClosing ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<PaymentDTO> processSelectPaymentList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectPaymentList");
        return eduMarineMngMapper.selectPaymentList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PaymentDTO processSelectPaymentSingle(PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectPaymentSingle");
        return eduMarineMngMapper.selectPaymentSingle(paymentDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeletePayment(PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeletePayment");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(paymentDTO.getSeq() != null){
                result = eduMarineMngMapper.deletePayment(paymentDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + paymentDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeletePayment ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdatePayment(PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdatePayment");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(paymentDTO.getSeq())){

                result = eduMarineMngMapper.updatePayment(paymentDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + paymentDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdatePayment ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertPayment(PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertPayment");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getPaymentSeq();
            paymentDTO.setSeq(getSeq);

            // 신청자 SEQ 추출
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setName(paymentDTO.getMemberName());
            memberDTO.setPhone(paymentDTO.getMemberPhone());
            String memberSeq = eduMarineMngMapper.selectMemberSeq(memberDTO);
            if(memberSeq != null && !"".equals(memberSeq)){
                paymentDTO.setMemberSeq(memberSeq);

                // 교육 SEQ 추출
                TrainDTO trainDTO = new TrainDTO();
                trainDTO.setGbn(paymentDTO.getTrainName());

                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                String today = dateFormat.format(new Date());
                trainDTO.setToday(today);

                String trainSeq = eduMarineMngMapper.selectTrainSeq(trainDTO);

                if(trainSeq != null && !"".equals(trainSeq)){
                    paymentDTO.setTrainSeq(trainSeq);

                    result = eduMarineMngMapper.insertPayment(paymentDTO);

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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdatePayStatus(List<PaymentDTO> paymentList) {
        System.out.println("EduMarineMngServiceImpl > processUpdatePayStatus");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            for(PaymentDTO paymentInfo : paymentList){
                if(!StringUtil.isEmpty(paymentInfo.getSeq())){

                    result = eduMarineMngMapper.updatePayStatus(paymentInfo);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail] Seq : " + paymentInfo.getSeq();
                        break;
                    }
                    //System.out.println(result);

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdatePayStatus ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public PaymentDTO processSelectTrainPaymentInfo(PaymentDTO paymentRequestDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainPaymentInfo");
        return eduMarineMngMapper.selectTrainPaymentInfo(paymentRequestDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SubscriberDTO> processSelectSubscriberList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSubscriberList");
        return eduMarineMngMapper.selectSubscriberList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SubscriberDTO processSelectSubscriberSingle(SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSubscriberSingle");
        return eduMarineMngMapper.selectSubscriberSingle(subscriberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteSubscriber(SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteSubscriber");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(subscriberDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteSubscriber(subscriberDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + subscriberDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteSubscriber ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateSubscriber(SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateSubscriber");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(subscriberDTO.getSeq())){

                result = eduMarineMngMapper.updateSubscriber(subscriberDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + subscriberDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateSubscriber ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processCheckSubscriber(SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngServiceImpl > processCheckSubscriber");
        return eduMarineMngMapper.checkSubscriber(subscriberDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSubscriber(SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertSubscriber");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getSubscriberSeq();
            subscriberDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertSubscriber(subscriberDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSubscriber ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SmsDTO> processSelectSmsList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSmsList");
        return eduMarineMngMapper.selectSmsList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public SmsDTO processSelectSmsSingle(SmsDTO smsDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSmsSingle");
        return eduMarineMngMapper.selectSmsSingle(smsDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertSms(SmsDTO smsDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertSms");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getSmsSeq();
            smsDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertSms(smsDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertSms ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteSms(SmsDTO smsDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteSms");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(smsDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteSms(smsDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + smsDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteSms ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TemplateDTO> processSelectSmsTemplateList() {
        System.out.println("EduMarineMngServiceImpl > processSelectSmsTemplateList");
        return eduMarineMngMapper.selectSmsTemplateList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public TemplateDTO processSelectSmsTemplateSingle(TemplateDTO templateDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSmsTemplateSingle");
        return eduMarineMngMapper.selectSmsTemplateSingle(templateDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processSaveSmsTemplate(TemplateDTO templateDTO) {
        System.out.println("EduMarineMngServiceImpl > processSaveSmsTemplate");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(templateDTO.getSeq())){

                if("신규등록".equals(templateDTO.getSeq())){
                    String getSeq = eduMarineMngMapper.getSmsTemplateSeq();
                    templateDTO.setSeq(getSeq);

                    result = eduMarineMngMapper.insertSmsTemplate(templateDTO);

                    responseDTO.setCustomValue(getSeq);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }else{

                    result = eduMarineMngMapper.updateSmsTemplate(templateDTO);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail] Seq : " + templateDTO.getSeq();
                    }
                }

            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processSaveSmsTemplate ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteSmsTemplate(TemplateDTO templateDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteSmsTemplate");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(templateDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteSmsTemplate(templateDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + templateDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteSmsTemplate ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SmsSendDTO> processSelectSmsSendList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectSmsSendList");
        List<SmsSendDTO> responseList = new ArrayList<>();
        String gbn = searchDTO.getCondition();
        String boarderGbn = "";
        if(gbn.contains("기초정비교육") || gbn.contains("응급조치교육")){
            gbn = gbn.split(" ")[0];
            boarderGbn = gbn.substring(gbn.indexOf("(")+1, gbn.indexOf(")"));
        }
        switch (gbn){
            case "상시신청":
                responseList = eduMarineMngMapper.selectSmsSendRegularList();
                break;
            case "해상엔진 테크니션 (선내기/선외기)":
                responseList = eduMarineMngMapper.selectSmsSendBoarderList();
                break;
            case "FRP 레저보트 선체 정비 테크니션":
                responseList = eduMarineMngMapper.selectSmsSendFrpList();
                break;
            case "해상엔진 자가정비 (선외기)":
                responseList = eduMarineMngMapper.selectSmsSendOutboarderList();
                break;
            case "해상엔진 자가정비 (선내기)":
                responseList = eduMarineMngMapper.selectSmsSendInboarderList();
                break;
            case "해상엔진 자가정비 (세일요트)":
                responseList = eduMarineMngMapper.selectSmsSendSailyachtList();
                break;
            case "고마력 선외기 정비 중급 테크니션":
                responseList = eduMarineMngMapper.selectSmsSendHighhorsepowerList();
                break;
            case "자가정비 심화과정 (고마력 선외기)":
                responseList = eduMarineMngMapper.selectSmsSendHighSelfList();
                break;
            case "고마력 선외기 정비 중급 테크니션 (특별반)":
                responseList = eduMarineMngMapper.selectSmsSendHighSpecialList();
                break;
            case "스턴드라이브 정비 전문가과정":
                responseList = eduMarineMngMapper.selectSmsSendSterndriveList();
                break;
            case "스턴드라이브 정비 전문가과정 (특별반)":
                responseList = eduMarineMngMapper.selectSmsSendSternSpecialList();
                break;
            case "기초정비교육":
                responseList = eduMarineMngMapper.selectSmsSendBasicList(boarderGbn);
                break;
            case "응급조치교육":
                responseList = eduMarineMngMapper.selectSmsSendEmergencyList(boarderGbn);
                break;
            case "발전기 정비 교육":
                responseList = eduMarineMngMapper.selectSmsSendGeneratorList(boarderGbn);
                break;
            case "선외기/선내기 직무역량 강화과정":
                responseList = eduMarineMngMapper.selectSmsSendCompetencyList(boarderGbn);
                break;
            case "선내기 팸투어":
                responseList = eduMarineMngMapper.selectSmsSendFamtourinList(boarderGbn);
                break;
            case "선외기 팸투어":
                responseList = eduMarineMngMapper.selectSmsSendFamtouroutList(boarderGbn);
                break;
            case "레저선박 해양전자장비 교육":
                responseList = eduMarineMngMapper.selectSmsSendElectroList(boarderGbn);
                break;
            default:
                break;
        }
        return responseList;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<DownloadDTO> processSelectDownloadList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectDownloadList");
        return eduMarineMngMapper.selectDownloadList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertDownload(DownloadDTO downloadDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertDownload");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getDownloadSeq();
            downloadDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertDownload(downloadDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertDownload ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrashDTO> processSelectTrashList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrashList");
        return eduMarineMngMapper.selectTrashList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processSaveTrash(TrashDTO trashDTO) {
        System.out.println("EduMarineMngServiceImpl > processSaveTrash");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if (!StringUtil.isEmpty(trashDTO.getTargetSeq())) {

                // 대상 테이블 삭제 여부 update
                result = eduMarineMngMapper.updateTargetTableTrash(trashDTO);

                if (result == 0) {
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                } else {
                    String getSeq = eduMarineMngMapper.getTrashSeq();
                    trashDTO.setSeq(getSeq);

                    result = eduMarineMngMapper.insertTrash(trashDTO);

                    responseDTO.setCustomValue(getSeq);
                    if (result == 0) {
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Insert Fail]";
                    }
                }

            } else {
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Target Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processSaveTrash ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteTrash(TrashDTO trashDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteTrash");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(trashDTO.getSeq() != null){
                // 조회
                trashDTO = eduMarineMngMapper.selectTrashSingle(trashDTO);

                // Trash Table Delete
                result = eduMarineMngMapper.deleteTrash(trashDTO);

                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + trashDTO.getSeq();
                }else{

                    // 대상 테이블 삭제 DELETE
                    result = eduMarineMngMapper.deleteTargetTableTrash(trashDTO);

                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Delete Fail] Seq : " + trashDTO.getTargetSeq();
                    }
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteTrash ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processRestoreTrash(TrashDTO trashDTO) {
        System.out.println("EduMarineMngServiceImpl > processRestoreTrash");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if (!StringUtil.isEmpty(trashDTO.getSeq())) {

                trashDTO = eduMarineMngMapper.selectTrashSingle(trashDTO);

                // 대상 테이블 삭제 여부 - 복구 update
                trashDTO.setDelYn("N");
                result = eduMarineMngMapper.updateTargetTableTrash(trashDTO);

                if (result == 0) {
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                } else {
                    result = eduMarineMngMapper.deleteTrash(trashDTO);

                    if (result == 0) {
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Delete Fail]";
                    }

                }

            } else {
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Target Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processRestoreTrash ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processSaveTrainTemplate(TrainTemplateDTO templateInfo) {
        System.out.println("EduMarineMngServiceImpl > processSaveTrainTemplate");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {

            eduMarineMngMapper.deleteTrainTemplate(templateInfo.getGbn());

            for(TrainTemplateDTO.TrainTemplateInfo info : templateInfo.getData()){

                if(info.getValue() != null && !"".equals(info.getValue())){

                    Integer result = eduMarineMngMapper.insertTrainTemplate(info);

                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                        break;
                    }
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processSaveTrainTemplate ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<TrainTemplateDTO.TrainTemplateInfo> processSelectTrainTemplateList(String major) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainTemplateList");
        return eduMarineMngMapper.selectTrainTemplateList(major);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<AdminDTO> processSelectAdminMngList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectAdminMngList");
        return eduMarineMngMapper.selectAdminMngList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public AdminDTO processSelectAdminMngSingle(AdminDTO adminDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectAdminMngSingle");
        return eduMarineMngMapper.selectAdminMngSingle(adminDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public AdminDTO processSelectAdminMngSingleId(AdminDTO adminDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectAdminMngSingleId");
        return eduMarineMngMapper.selectAdminMngSingleId(adminDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public Integer processSelectAdminMngCheckDuplicateId(AdminDTO adminDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectAdminMngCheckDuplicateId : ======");
        return eduMarineMngMapper.selectAdminMngCheckDuplicateId(adminDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateAdminMng(AdminDTO adminDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateAdminMng");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if (!StringUtil.isEmpty(adminDTO.getSeq())) {

                // 대상 테이블 삭제 여부 update
                result = eduMarineMngMapper.updateAdminMng(adminDTO);

                if (result == 0) {
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail]";
                }

            } else {
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateAdminMng ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertAdminMng(AdminDTO adminDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertAdminMng");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String seq = eduMarineMngMapper.getAdminSeq();
            adminDTO.setSeq(seq);

            result = eduMarineMngMapper.insertAdminMng(adminDTO);

            if (result == 0) {
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Update Fail]";
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateAdminMng ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processCheckAdminMngValidYn(AdminDTO param_adminDTO) {
        System.out.println("EduMarineMngServiceImpl > processCheckAdminMngValidYn");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            AdminDTO db_adminDTO = eduMarineMngMapper.login(param_adminDTO);

            String param_cpName = param_adminDTO.getCpName();
            String db_cpName = db_adminDTO.getCpName();
            String param_cpPhone = param_adminDTO.getCpPhone();
            String db_cpPhone = db_adminDTO.getCpPhone();

            if(db_cpName.equals(param_cpName)){
                if(db_cpPhone.equals(param_cpPhone)){

                    db_adminDTO.setValidYn("Y");
                    eduMarineMngMapper.updateAdminMngValidYn(db_adminDTO);

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "계정에 등록된 담당자 휴대전화가 일치하지 않습니다.";
                }
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "계정에 등록된 담당자 성명이 일치하지 않습니다.";
            }

        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processCheckAdminMngValidYn ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    /*******************************************
     * CANCEL API
     * *****************************************/
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public InistdpayCancelResponseDTO processApplyPaymentCancelApi(InistdpayCancelRequestDTO inistdpayCancelRequestDTO) {
        System.out.println("EduMarineMngController > processApplyPaymentCancel");
        //System.out.println(memberDTO.toString());

        /* 이니시스 취소 API CALL */
        InistdpayCancelResponseDTO responseDTO = new InistdpayCancelResponseDTO();

        SHA512 sha512 = new SHA512();
        Date date_now = new Date(System.currentTimeMillis());
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");

        //step1. 요청을 위한 파라미터 설정
        String key = "oGFXQGaqFgjAhdrB";//"ItEQKi3rY7uvDS8l";
        String mid = "edumarin90";//"INIpayTest";
        String type = "refund";
        if("PART".equals(inistdpayCancelRequestDTO.getCancelGbn())){
            type = "partialRefund";
        }
        String timestamp = fourteen_format.format(date_now);
        String clientIp = "139.150.86.221";//"127.0.0.1";

        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("tid", inistdpayCancelRequestDTO.getTid());
        data1.put("msg", inistdpayCancelRequestDTO.getMsg()/*"환불 요청합니다. 사유 : "*/);
        if("PART".equals(inistdpayCancelRequestDTO.getCancelGbn())){
            data1.put("price", inistdpayCancelRequestDTO.getPrice()); //취소요청금액
            data1.put("confirmPrice", inistdpayCancelRequestDTO.getConfirmPrice()); //부분취소후남은금액
            data1.put("currency", "WON"); //통화
            //data1.put("tax", inistdpayCancelRequestDTO.getTax()); //부가세
            //data1.put("taxFree", inistdpayCancelRequestDTO.getTaxFree()); //비과세
        }

        JSONObject data = new JSONObject(data1);

        // Hash Encryption
        String plainTxt = key + mid + type + timestamp + data ;
        plainTxt = plainTxt.replaceAll("\\\\", "");
        String hashData = sha512.hash(plainTxt);

        // reqeust URL
        String apiUrl = "https://iniapi.inicis.com/v2/pg/refund";
        if("PART".equals(inistdpayCancelRequestDTO.getCancelGbn())){
            apiUrl = "https://iniapi.inicis.com/v2/pg/partialRefund";
        }

        JSONObject respJson = new JSONObject();
        respJson.put("mid", mid);
        respJson.put("type", type);
        respJson.put("timestamp",timestamp);
        respJson.put("clientIp",clientIp);
        respJson.put("data",data);
        respJson.put("hashData",hashData);

        System.out.println("REQUEST : " + respJson.toString());

        //step2. key=value 로 post 요청
        try {
            URL reqUrl = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

            if (conn != null) {
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestMethod("POST");
                conn.setDefaultUseCaches(false);
                conn.setDoOutput(true);

                if (conn.getDoOutput()) {
                    conn.getOutputStream().write(respJson.toString().getBytes(StandardCharsets.UTF_8));
                    conn.getOutputStream().flush();
                    conn.getOutputStream().close();
                }

                conn.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                //step3. 요청 결과
                String result = br.readLine();
                System.out.println("RESPONSE : " + result);
                ObjectMapper om = new ObjectMapper();
                responseDTO = om.readValue(result, InistdpayCancelResponseDTO.class);
                br.close();
            }

        }catch(Exception e ) {
            e.printStackTrace();
        }

        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public InistdpayCancelResponseDTO processApplyPaymentVbankCancelApi(InistdpayCancelRequestDTO inistdpayCancelRequestDTO) {
        System.out.println("EduMarineMngController > processApplyPaymentVbankCancelApi");
        //System.out.println(memberDTO.toString());

        /* 이니시스 취소 API CALL */
        InistdpayCancelResponseDTO responseDTO = new InistdpayCancelResponseDTO();

        SHA512 sha512 = new SHA512();
        AES128 aes128 = new AES128();
        Date date_now = new Date(System.currentTimeMillis());
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");

        //step1. 요청을 위한 파라미터 설정
        String key = "oGFXQGaqFgjAhdrB";//"ItEQKi3rY7uvDS8l";
        String iv = "kXyGHLFTLLYANZ==";
        String mid = "edumarin90";//"INIpayTest";
        String type = "refund";
        if("PART".equals(inistdpayCancelRequestDTO.getCancelGbn())){
            if(!Objects.equals(inistdpayCancelRequestDTO.getPrice(), inistdpayCancelRequestDTO.getConfirmPrice())){
                type = "partialRefund";
            }
        }
        String timestamp = fourteen_format.format(date_now);
        String clientIp = "139.150.86.221";//"127.0.0.1";

        String refundAcctNum = inistdpayCancelRequestDTO.getRefundAcctNum();

        // AES Encryption
        String enc_refundAcctNum = null;
        try {
            enc_refundAcctNum = aes128.encAES(refundAcctNum, key, iv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("tid", inistdpayCancelRequestDTO.getTid());
        data1.put("msg", inistdpayCancelRequestDTO.getMsg()/*"환불 요청합니다. 사유 : "*/);
        data1.put("refundAcctNum", enc_refundAcctNum);
        data1.put("refundBankCode", inistdpayCancelRequestDTO.getRefundBankCode());
        data1.put("refundAcctName", inistdpayCancelRequestDTO.getRefundAcctName());
        if("PART".equals(inistdpayCancelRequestDTO.getCancelGbn())){
            if(!Objects.equals(inistdpayCancelRequestDTO.getPrice(), inistdpayCancelRequestDTO.getConfirmPrice())){
                data1.put("price", inistdpayCancelRequestDTO.getPrice()); //취소요청금액
                data1.put("confirmPrice", inistdpayCancelRequestDTO.getConfirmPrice()); //부분취소후남은금액
                //data1.put("tax", inistdpayCancelRequestDTO.getTax()); //부가세
                //data1.put("taxFree", inistdpayCancelRequestDTO.getTaxFree()); //비과세
            }
        }

        JSONObject data = new JSONObject(data1);

        // Hash Encryption
        String plainTxt = key + mid + type + timestamp + data ;
        plainTxt = plainTxt.replaceAll("\\\\", "");
        String hashData = sha512.hash(plainTxt);

        // reqeust URL
        String apiUrl = "https://iniapi.inicis.com/v2/pg/refund/vacct";
        if("PART".equals(inistdpayCancelRequestDTO.getCancelGbn())){
            if(!Objects.equals(inistdpayCancelRequestDTO.getPrice(), inistdpayCancelRequestDTO.getConfirmPrice())) {
                apiUrl = "https://iniapi.inicis.com/v2/pg/partialRefund/vacct";
            }
        }

        JSONObject respJson = new JSONObject();
        respJson.put("mid", mid);
        respJson.put("type", type);
        respJson.put("timestamp",timestamp);
        respJson.put("clientIp",clientIp);
        respJson.put("data",data);
        respJson.put("hashData",hashData);

        System.out.println("REQUEST : " + respJson.toString());

        //step2. key=value 로 post 요청
        try {
            URL reqUrl = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

            if (conn != null) {
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestMethod("POST");
                conn.setDefaultUseCaches(false);
                conn.setDoOutput(true);

                if (conn.getDoOutput()) {
                    conn.getOutputStream().write(respJson.toString().getBytes(StandardCharsets.UTF_8));
                    conn.getOutputStream().flush();
                    conn.getOutputStream().close();
                }

                conn.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                //step3. 요청 결과
                String result = br.readLine();
                System.out.println("RESPONSE : " + result);
                ObjectMapper om = new ObjectMapper();
                responseDTO = om.readValue(result, InistdpayCancelResponseDTO.class);
                br.close();
            }

        }catch(Exception e ) {
            System.out.println(e.getMessage());
            responseDTO.setResultCode(CommConstants.RESULT_CODE_FAIL);
            responseDTO.setResultMsg("[Exception] processApplyPaymentVbankCancelApi : " + CommConstants.RESULT_MSG_FAIL);
        }

        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<MemberDTO> processSelectExcelMemberDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelMemberDetailList");
        return eduMarineMngMapper.selectExcelMemberDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<RegularDTO> processSelectExcelRegularDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelRegularDetailList");
        return eduMarineMngMapper.selectExcelRegularDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BoarderDetailDTO> processSelectExcelBoarderDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelBoarderDetailList");
        return eduMarineMngMapper.selectExcelBoarderDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FrpDetailDTO> processSelectExcelFrpDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelFrpDetailList");
        return eduMarineMngMapper.selectExcelFrpDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<OutboarderDetailDTO> processSelectExcelOutboarderDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelOutboarderDetailList");
        return eduMarineMngMapper.selectExcelOutboarderDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<InboarderDetailDTO> processSelectExcelInboarderDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelInboarderDetailList");
        return eduMarineMngMapper.selectExcelInboarderDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SailyachtDetailDTO> processSelectExcelSailyachtDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelSailyachtDetailList");
        return eduMarineMngMapper.selectExcelSailyachtDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<HighHorsePowerDTO> processSelectExcelHighhorsepowerDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelHighhorsepowerDetailList");
        return eduMarineMngMapper.selectExcelHighhorsepowerDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<HighSelfDTO> processSelectExcelHighSelfDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelHighSelfDetailList");
        return eduMarineMngMapper.selectExcelHighSelfDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<HighSpecialDTO> processSelectExcelHighSpecialDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelHighSpecialDetailList");
        return eduMarineMngMapper.selectExcelHighSpecialDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SterndriveDTO> processSelectExcelSterndriveDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelHighhorsepowerDetailList");
        return eduMarineMngMapper.selectExcelSterndriveDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<SternSpecialDTO> processSelectExcelSternSpecialDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelSternSpecialDetailList");
        return eduMarineMngMapper.selectExcelSternSpecialDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<BasicDetailDTO> processSelectExcelBasicDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelBasicDetailList");
        return eduMarineMngMapper.selectExcelBasicDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<EmergencyDetailDTO> processSelectExcelEmergencyDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelEmergencyDetailList");
        return eduMarineMngMapper.selectExcelEmergencyDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<GeneratorDetailDTO> processSelectExcelGeneratorDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelGeneratorDetailList");
        return eduMarineMngMapper.selectExcelGeneratorDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<CompetencyDetailDTO> processSelectExcelCompetencyDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelCompetencyDetailList");
        return eduMarineMngMapper.selectExcelCompetencyDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FamtourinDetailDTO> processSelectExcelFamtourinDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelFamtourinDetailList");
        return eduMarineMngMapper.selectExcelFamtourinDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FamtouroutDetailDTO> processSelectExcelFamtouroutDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelFamtouroutDetailList");
        return eduMarineMngMapper.selectExcelFamtouroutDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<ElectroDetailDTO> processSelectExcelElectroDetailList() {
        System.out.println("EduMarineMngServiceImpl > processSelectExcelElectroDetailList");
        return eduMarineMngMapper.selectExcelElectroDetailList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public StatisticsDTO processSelectMemberCount(StatisticsDTO statisticsDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectMemberCount");
        return eduMarineMngMapper.selectMemberCount(statisticsDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public StatisticsDTO processSelectTrainCount(StatisticsDTO trainDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectTrainCount");
        return eduMarineMngMapper.selectTrainCount(trainDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<RequestDTO> processSelectRequestList(SearchDTO searchDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectRequestList");
        return eduMarineMngMapper.selectRequestList(searchDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public RequestDTO processSelectRequestSingle(RequestDTO requestDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectRequestSingle");
        return eduMarineMngMapper.selectRequestSingle(requestDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertRequest(RequestDTO requestDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertRequest");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getRequestSeq();
            requestDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertRequest(requestDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertRequest ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRequest(RequestDTO requestDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateRequest");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(requestDTO.getSeq())){

                result = eduMarineMngMapper.updateRequest(requestDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Seq : " + requestDTO.getSeq();
                }
                responseDTO.setCustomValue(requestDTO.getSeq());
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateRequest ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteRequest(RequestDTO requestDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteRequest");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(requestDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteRequest(requestDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + requestDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteRequest ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<RequestReplyDTO> processSelectReplyList(String requestSeq) {
        System.out.println("EduMarineMngServiceImpl > processSelectReplyList");
        return eduMarineMngMapper.selectReplyList(requestSeq);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processInsertReply(RequestReplyDTO requestReplyDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertReply");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String getSeq = eduMarineMngMapper.getReplySeq();
            requestReplyDTO.setSeq(getSeq);

            result = eduMarineMngMapper.insertReply(requestReplyDTO);

            responseDTO.setCustomValue(getSeq);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertReply ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processDeleteReply(RequestReplyDTO requestReplyDTO) {
        System.out.println("EduMarineMngServiceImpl > processDeleteReply");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(requestReplyDTO.getSeq() != null){
                result = eduMarineMngMapper.deleteReply(requestReplyDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Delete Fail] Seq : " + requestReplyDTO.getSeq();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Seq Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processDeleteReply ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRequestProgressStep(List<RequestDTO> requestList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateRequestProgressStep");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            for(RequestDTO info : requestList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    result = eduMarineMngMapper.updateRequestProgressStep(info);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                        break;
                    }
                    //System.out.println(result);

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateRequestProgressStep ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateRequestCompleteExpect(List<RequestDTO> requestList) {
        System.out.println("EduMarineMngServiceImpl > processUpdateRequestCompleteExpect");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            for(RequestDTO info : requestList){
                if(!StringUtil.isEmpty(info.getSeq())){

                    result = eduMarineMngMapper.updateRequestCompleteExpect(info);
                    if(result == 0){
                        resultCode = CommConstants.RESULT_CODE_FAIL;
                        resultMessage = "[Data Update Fail] Seq : " + info.getSeq();
                        break;
                    }
                    //System.out.println(result);

                }else{
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Seq Not Found Error]";
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateRequestCompleteExpect ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateTrainChange(TrainUpdateDTO trainUpdateDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateTrainChange");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        try {
            String tableSeq = trainUpdateDTO.getSeq();
            String newTrainSeq = trainUpdateDTO.getTrainSeq();
            String newTrainName = trainUpdateDTO.getTrainName();

            if (!StringUtil.isEmpty(tableSeq)) {
                // 기존 교육신청 정보 Select
                TrainUpdateDTO preInfo = eduMarineMngMapper.selectAllTrainInfo(tableSeq);
                String preTrainName = preInfo.getTrainName();
                String preTrainSeq = preInfo.getTrainSeq();

                if(preTrainSeq != null && !"".equals(preTrainSeq)
                        && newTrainSeq != null && !"".equals(newTrainSeq)){

                    if(preTrainName.equals(newTrainName)){

                        Integer updTableResult = 0;

                        switch (newTrainName){
                            case "해상엔진 테크니션 (선내기/선외기)":
                                BoarderDTO boarderDTO = new BoarderDTO();
                                boarderDTO.setSeq(tableSeq);
                                boarderDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateBoarderTrainSeq(boarderDTO);
                                break;
                            case "FRP 레저보트 선체 정비 테크니션":
                                FrpDTO frpDTO = new FrpDTO();
                                frpDTO.setSeq(tableSeq);
                                frpDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateFrpTrainSeq(frpDTO);
                                break;
                            case "해상엔진 자가정비 (선외기)":
                                OutboarderDTO outboarderDTO = new OutboarderDTO();
                                outboarderDTO.setSeq(tableSeq);
                                outboarderDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateOutboarderTrainSeq(outboarderDTO);
                                break;
                            case "해상엔진 자가정비 (선내기)":
                                InboarderDTO inboarderDTO = new InboarderDTO();
                                inboarderDTO.setSeq(tableSeq);
                                inboarderDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateInboarderTrainSeq(inboarderDTO);
                                break;
                            case "해상엔진 자가정비 (세일요트)":
                                SailyachtDTO sailyachtDTO = new SailyachtDTO();
                                sailyachtDTO.setSeq(tableSeq);
                                sailyachtDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateSailyachtTrainSeq(sailyachtDTO);
                                break;
                            case "고마력 선외기 정비 중급 테크니션":
                                HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                highHorsePowerDTO.setSeq(tableSeq);
                                highHorsePowerDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateHighHorsePowerTrainSeq(highHorsePowerDTO);
                                break;
                            case "자가정비 심화과정 (고마력 선외기)":
                                HighSelfDTO highSelfDTO = new HighSelfDTO();
                                highSelfDTO.setSeq(tableSeq);
                                highSelfDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateHighSelfTrainSeq(highSelfDTO);
                                break;
                            case "고마력 선외기 정비 중급 테크니션 (특별반)":
                                HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                highSpecialDTO.setSeq(tableSeq);
                                highSpecialDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateHighSpecialTrainSeq(highSpecialDTO);
                                break;
                            case "스턴드라이브 정비 전문가과정":
                                SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                sterndriveDTO.setSeq(tableSeq);
                                sterndriveDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateSterndriveTrainSeq(sterndriveDTO);
                                break;
                            case "스턴드라이브 정비 전문가과정 (특별반)":
                                SternSpecialDTO sternSpecialDTO = new SternSpecialDTO();
                                sternSpecialDTO.setSeq(tableSeq);
                                sternSpecialDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateSternSpecialTrainSeq(sternSpecialDTO);
                                break;
                            case "기초정비교육":
                                BasicDTO basicDTO = new BasicDTO();
                                basicDTO.setSeq(tableSeq);
                                basicDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateBasicTrainSeq(basicDTO);
                                break;
                            case "응급조치교육":
                                EmergencyDTO emergencyDTO = new EmergencyDTO();
                                emergencyDTO.setSeq(tableSeq);
                                emergencyDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateEmergencyTrainSeq(emergencyDTO);
                                break;
                            case "발전기 정비 교육":
                                GeneratorDTO generatorDTO = new GeneratorDTO();
                                generatorDTO.setSeq(tableSeq);
                                generatorDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateGeneratorTrainSeq(generatorDTO);
                                break;
                            case "선외기/선내기 직무역량 강화과정":
                                CompetencyDTO competencyDTO = new CompetencyDTO();
                                competencyDTO.setSeq(tableSeq);
                                competencyDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateCompetencyTrainSeq(competencyDTO);
                                break;
                            case "선내기 팸투어":
                                FamtourinDTO famtourinDTO = new FamtourinDTO();
                                famtourinDTO.setSeq(tableSeq);
                                famtourinDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateFamtourinTrainSeq(famtourinDTO);
                                break;
                            case "선외기 팸투어":
                                FamtouroutDTO famtouroutDTO = new FamtouroutDTO();
                                famtouroutDTO.setSeq(tableSeq);
                                famtouroutDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateFamtouroutTrainSeq(famtouroutDTO);
                                break;
                            case "레저선박 해양전자장비 교육":
                                ElectroDTO electroDTO = new ElectroDTO();
                                electroDTO.setSeq(tableSeq);
                                electroDTO.setTrainSeq(newTrainSeq);
                                updTableResult = eduMarineMngMapper.updateElectroTrainSeq(electroDTO);
                                break;
                            default:
                                break;
                        }

                        if(updTableResult > 0){
                            TrainDTO updTrainDTO = new TrainDTO();
                            updTrainDTO.setSeq(newTrainSeq);
                            TrainDTO newTrainInfo = eduMarineMngMapper.selectTrainSingle(updTrainDTO);
                            if(newTrainInfo != null){

                                // 기존 교육신청 ApplyCnt - 1
                                eduMarineMngMapper.updateTrainApplyCnt(preTrainSeq);

                                // 변경 교육 ApplyCnt + 1
                                eduMarineMngMapper.updateTrainApplyCntAdd(newTrainInfo.getSeq());

                                PaymentDTO paymentDTO = eduMarineMngMapper.selectPaymentTableSeq(tableSeq);
                                if(paymentDTO != null){
                                    PaymentDTO updPayDTO = new PaymentDTO();
                                    updPayDTO.setTableSeq(tableSeq);
                                    updPayDTO.setTrainSeq(newTrainInfo.getSeq());
                                    updPayDTO.setTrainName(newTrainInfo.getGbn());
                                    String goodName = "[" + newTrainInfo.getSeq() + "]" + newTrainInfo.getGbn().replaceAll(" ","");
                                    updPayDTO.setGoodName(goodName);
                                    updPayDTO.setGoodsName(goodName);
                                    eduMarineMngMapper.updatePaymentTrainChange(updPayDTO);
                                }
                            }

                        }
                    }else{
                        resultCode = "-2";
                        resultMessage = "기존 신청 교육과정과 변경할 교육과정이 일치하지 않습니다. (동일 교육과정 내 차시 변경일 경우에만 가능)";
                    }
                }
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateTrainChange ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    /*******************************************
     * File
     * *****************************************/

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FileResponseDTO processInsertFileInfo(FileDTO fileDTO) {
        System.out.println("EduMarineMngServiceImpl > processInsertFileInfo");
        FileResponseDTO responseDTO = new FileResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {

            String fileId = eduMarineMngMapper.getFileId();
            fileDTO.setId(fileId);
            result = eduMarineMngMapper.insertFileInfo(fileDTO);
            if(result == 0){
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Data Insert Fail]";
            }
            responseDTO.setFileId(fileId);
            //System.out.println(result);
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processInsertFileInfo ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FileDTO> processSelectFileUserIdList(FileDTO fileDTO) {
        System.out.println("EduMarineMngServiceImpl > processSelectFileUserIdList");
        return eduMarineMngMapper.selectFileUserIdList(fileDTO);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public FileResponseDTO processUpdateFileUseN(FileDTO fileDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFileUseN");
        FileResponseDTO responseDTO = new FileResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(fileDTO.getId())){

                result = eduMarineMngMapper.updateFileUseN(fileDTO);

                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Id : " + fileDTO.getId();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Id Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFileUseN ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFileUserId(FileDTO fileDTO) {
        System.out.println("EduMarineMngServiceImpl > processUpdateFileUserId");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(fileDTO.getId())){

                result = eduMarineMngMapper.updateFileUserId(fileDTO);
                if(result == 0){
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = "[Data Update Fail] Id : " + fileDTO.getId();
                }
                //System.out.println(result);
            }else{
                resultCode = CommConstants.RESULT_CODE_FAIL;
                resultMessage = "[Id Not Found Error]";
            }
        }catch (Exception e){
            resultCode = CommConstants.RESULT_CODE_FAIL;
            resultMessage = "[processUpdateFileUserId ERROR] " + CommConstants.RESULT_MSG_FAIL + " , " + e.getMessage();
            e.printStackTrace();
        }

        responseDTO.setResultCode(resultCode);
        responseDTO.setResultMessage(resultMessage);
        return responseDTO;
    }

    /*******************************************
     * Excel File Upload Service Impl
     * *****************************************/

    public List<?> uploadExcelFile(MultipartFile excelFile){
        List<?> list = new ArrayList<>();
        try {
            OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

            // 첫번째 시트 불러오기
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
//                CreateUserVo createUserVo = new CreateUserVo();
                XSSFRow row = sheet.getRow(i);

                // 행이 존재하기 않으면 패스
                if(null == row) {
                    continue;
                }

                for(int j=0; j<row.getLastCellNum() + 1; j++){

                    // 행의 1번째 열(아이디)
                    XSSFCell cell = row.getCell(j);

                    //System.out.println(cell.getStringCellValue());
                }

                /*if(null != cell)
                    createUserVo.setUser_id(cell.getStringCellValue());
                // 행의 2번째 열(이름)
                cell = row.getCell(1);
                if(null != cell)
                    createUserVo.setUser_name(cell.getStringCellValue());

                list.add(createUserVo);*/

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*******************************************
     * Mail Send Service Impl
     * *****************************************/

    public ResponseDTO processMailSend(MailRequestDTO mailRequestDTO) {
        System.out.println("EduMarineMngServiceImpl > processMailSend");
        ResponseDTO responseDto = new ResponseDTO();
        // URL
        String url = "https://directsend.co.kr/index.php/api_v2/mail_change_word";

        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.setRequestProperty("Accept", "application/json");

            /*
             * subject  : 받을 mail 제목, 치환 문자열 사용 가능.
             *   치환 문자열 : [$NAME] - 이름 (한글 10글자/영문 30byte 처리), [$EMAIL] - 이메일, [$MOBILE] - 휴대폰,
             *     [$NOTE1] - 비고1 (한글/영문 128자 처리), [$NOTE2] - 비고2 (한글/영문 128자 처리), [$NOTE3] - 비고3 (한글/영문 128자 처리), [$NOTE4] - 비고4 (한글/영문 128자 처리), [$NOTE5] - 비고5 (한글/영문 128자 처리)
             *   템플릿 사용시 템플릿에 입력된 메일 제목이 우선적으로 적용됩니다. 빌더로 템플릿을 저장할 경우 메일 제목은 저장되지 않으므로 subject값을 입력해주시기 바랍니다.
             * body  : 받을 mail 본문, 치환 문자열 사용 가능.
             *   치환 문자열 : [$NAME] - 이름 (한글 10글자/영문 30byte 처리), [$EMAIL] - 이메일, [$MOBILE] - 휴대폰,
             *     [$NOTE1] - 비고1 (한글/영문 128자 처리), [$NOTE2] - 비고2 (한글/영문 128자 처리), [$NOTE3] - 비고3 (한글/영문 128자 처리), [$NOTE4] - 비고4 (한글/영문 128자 처리), [$NOTE5] - 비고5 (한글/영문 128자 처리)
             * template : 사이트에 등록한 발송 할 템플릿 번호
             * sender : 발송자 메일주소
             * sender_name : 발송자 이름 (35자 제한)
             * username : directsend 발급 ID
             * receiver : 발송 할 고객 수신자 정보
             *   json array. ex)
             *      [
             *          {"name": "강길동", "email":"test1@directsend.co.kr", "mobile":"", "note1":"", "note2":"", "note3":"", "note4":"", "note5":""}
             *          , {"name": "홍길동", "email":"test2@directsend.co.kr", "mobile":"수신자번호", "note1":"다이렉트 센드 2", "note2":"다이렉트센드 2", "note3":"다이렉트센드 3", "note4":"다이렉트센드 4", "note5":"다이렉트센드 5"}
             *      ]
             * address_books : 사이트에 등록한 발송 할 주소록 번호 , 로 구분함 (ex. 0,1,2)
             * duplicate_yn : 수신자 정보가 중복될 경우 중복발송을 할지에 대한 여부
             * key : directsend 발급 api key
             *
             * 각 내용이 유효하지않을 경우에는 발송이 되지 않습니다.
             * 비고 내용이 최대 길이(한글/영문 128자 처리)를 넘는 경우 최대 길이 만큼 잘려서 치환 됩니다.
             * 상업성 광고 메일이나 업체 홍보 메일을 발송하는 경우, 제목에 (광고) 문구를 표기해야 합니다.
             * 영리광고 발송 시, 명시적인 사전 동의를 받은 이에게만 광고 메일 발송이 가능합니다.
             * 수신동의 여부에 대한 분쟁이 발생하는 경우 이에 대한 입증책임은 광고성 정보 전송자에게 있습니다.
             * 수신자가 수신거부 또는 수신동의 철회 의사를 쉽게 표시할 수 있는 안내문을 명시해야 합니다.
             * 스팸 메일 발송 용도로 악용하실 경우 이용에 제한이 있을 수 있으니 이용 시 주의 부탁 드립니다.
             * 불법 스팸 메일 발송 시 예고없이 서비스 이용이 정지될 수 있으며 이용정지 시 해당 아이디의 주소록과 잔액은 소멸되며, 환불되지 않으니 서비스 이용에 주의를 부탁드립니다.
             *
             * API 연동 발송시 다량의 주소를 한번에 입력하여도 수신자에게는 1:1로 보내는 것으로 표기되며, 동일한 내용의 메일을 한건씩 발송하는 것보다 다량으로 한번에 보내는 것이 발송 효율이 더 높습니다.
             * 동일한 내용의 메일을 일부 글자만 변경하여 다수에게 발송하시는 경우 수신자 정보를 Json Array [{...}, {...}]로 구분하시어 한번에 발송하시는 것을 권장 드립니다.
             */

            // 여기서부터 수정해주시기 바랍니다.

            String subject = mailRequestDTO.getSubject();   //필수입력(템플릿 사용시 23 line 설명 참조)
            String body = mailRequestDTO.getBody().replaceAll("\"","'");		//필수입력, 템플릿 사용시 빈값을 입력 하시기 바랍니다. 예시) String body = "";
            //String sender = "business@meetingfan.com";        //필수입력(미팅팬 발송테스트용)
            String sender = "business@meetingfan.com";        //필수입력()
            String sender_name = "경기해양레저 인력양성센터";
            String username = "meetingfan";              //필수입력
            String key = "L7QNsEQIyrAzNHO";           //필수입력

            //수신자 정보 추가 - 필수 입력(주소록 미사용시), 치환문자 미사용시 치환문자 데이터를 입력하지 않고 사용할수 있습니다.
            //치환문자 미사용시 {\"email\":\"aaaa@naver.com\"} 이메일만 입력 해주시기 바랍니다.
            JSONArray jsonArray = new JSONArray();
            for(int i=0; i<mailRequestDTO.getReceiver().size(); i++){
                JsonObject jsonObject = new JsonObject();
                MailRequestDTO.Receiver receiverInfo = mailRequestDTO.getReceiver().get(i);
//                jsonObject.addProperty("name", receiverInfo.getName());
                jsonObject.addProperty("email", receiverInfo.getEmail());
//                jsonObject.addProperty("phone", receiverInfo.getPhone());
                jsonArray.add(jsonObject);
            }
            String receiver = "{\"email\":\"" + mailRequestDTO.getReceiver().get(0).getEmail() + "\"}";
            //receiver = "[" + jsonObject.toString() + "]";
            receiver = jsonArray.toJSONString();

            //템플릿을 사용하길 원하실 경우 아래 주석을 해제하신후, 사이트에 등록한 템플릿 번호를 입력해주시기 바랍니다.
            //String template = ""; //발송 할 템플릿 번호
            //주소록을 사용하길 원하실 경우 아래 주석을 해제하신 후, 사이트에 등록한 주소록 번호를 입력해주시기 바랍니다.
            //String address_books = "0,1,2";      //발송 할 주소록 번호 , 로 구분함 (ex. 0, 1, 2)

            //수신자 정보가 중복이고 내용이 다를 경우 아래 주석을 해제하시고 발송해주시기 바랍니다.
            //String duplicate_yn = "1";

            //실제 발송성공실패 여부를 받기 원하실 경우 아래 주석을 해제하신 후, 사이트에 등록한 URL 번호를 입력해주시기 바랍니다.
            //int return_url = 0;

            //open, click 등의 결과를 받기 원하실 경우 아래 주석을 해제하신 후, 사이트에 등록한 URL 번호를 입력해주시기 바랍니다.
            //등록된 도메인이 http://domain 와 같을 경우, http://domain?type=[click | open | reject]&mail_id=[MailID]&email=[Email]&sendtime=[SendTime]&mail_reserve_id=[MailReserveID] 과 같은 형식으로 request를 보내드립니다.
            //int option_return_url = 0;

            //int open = 1;	// open 결과를 받으려면 아래 주석을 해제 해주시기 바랍니다.
            //int click = 1;	// click 결과를 받으려면 아래 주석을 해제 해주시기 바랍니다.
            //int check_period = 3;	// 트래킹 기간을 지정하며 3 / 7 / 10 / 15 일을 기준으로 지정하여 발송해 주시기 바랍니다. (단, 지정을 하지 않을 경우 결과를 받을 수 없습니다.)

            // 예약발송 정보 추가
            //String mail_type = "NORMAL"; // NORMAL - 즉시발송 / ONETIME - 1회예약 / WEEKLY - 매주정기예약 / MONTHLY - 매월정기예약
            //String start_reserve_time = "2019-03-08 12:11:00";// 발송하고자 하는 시간
            //String end_reserve_time = "2019-03-08 12:11:00";// 발송이 끝나는 시간 1회 예약일 경우 start_reserve_time = end_reserve_time
            // WEEKLY | MONTHLY 일 경우에 시작 시간부터 끝나는 시간까지 발송되는 횟수 Ex) type = WEEKLY, start_reserve_time = '2017-05-17 13:00:00', end_reserve_time = '2017-05-24 13:00:00' 이면 remained_count = 2 로 되어야 합니다.
            //int remained_count = 1;
            // 예약 수정/취소 API는 소스 하단을 참고 해주시기 바랍니다.

            //필수안내문구 추가
            //String agreement_text = "본메일은 [$NOW_DATE] 기준, 회원님의 수신동의 여부를 확인한 결과 회원님께서 수신동의를 하셨기에 발송되었습니다.";
            //String deny_text = "메일 수신을 원치 않으시면 [$DENY_LINK]를 클릭하세요. \\nIf you don't want this type of information or e-mail, please click the [$EN_DENY_LINK]";
            //String sender_info_text = "사업자 등록번호:-- 소재지:ㅇㅇ시(도) ㅇㅇ구(군) ㅇㅇ동 ㅇㅇㅇ번지 TEL:-- \\nEmail: <a href='mailto:test@directsend.co.kr'>test@directsend.co.kr</a>";
            //int logo_state = 1; // logo 사용시 1 / 사용안할 시 0
            //String logo_path = "http://logoimage.com/image.png';  //사용하실 로고 이미지를 입력하시기 바랍니다.";
            //String logo_sort = "CENTER";  //로고 정렬 LEFT - 왼쪽 정렬 / CENTER - 가운데 정렬 / RIGHT - 오른쪽 정렬
            //String footer_sort = "CENTER";  //메일내용, 풋터(수신옵션) 정렬 LEFT - 왼쪽 정렬 / CENTER - 가운데 정렬 / RIGHT - 오른쪽 정렬

            // 첨부파일의 URL을 보내면 DirectSend에서 파일을 download 받아 발송처리를 진행합니다. 첨부파일은 전체 10MB 이하로 발송을 해야 하며, 파일의 구분자는 '|(shift+\)'로 사용하며 5개까지만 첨부가 가능합니다.
            //String file_url = "http://localhost:8080/static/img/mail/00c75c02-f7e6-404a-89fc-967175c43da9_23_main_bg.png|https://directsend.co.kr/test1.png";
            // 첨부파일의 이름을 지정할 수 있도록 합니다.
            // 첨부파일의 이름은 순차적(https://directsend.co.kr/test.png - image.png, https://directsend.co.kr/test1.png - image2.png) 와 같이 적용이 되며, file_name을 지정하지 않은 경우 마지막의 파일의 이름으로 메일에 보여집니다.
            //String file_name = "image.png|image2.png";

            StringBuilder fileUrlSb = new StringBuilder();
            String file_url = null;
            String file_name = null;
            String imageBaseUrl = "http://www.meetingfan.store/static/img/mail/";
            if(mailRequestDTO.getFileUrl() != null){
                if(mailRequestDTO.getFileUrl().size() > 0){
                    for(int i=0; i<mailRequestDTO.getFileUrl().size(); i++){
                        fileUrlSb.append(imageBaseUrl);
                        fileUrlSb.append(mailRequestDTO.getFileUrl().get(i).getName());
                        if((i+1) != mailRequestDTO.getFileUrl().size()){
                            fileUrlSb.append("|");
                        }
                    }
                }
                file_url = fileUrlSb.toString();

                StringBuilder fileNameSb = new StringBuilder();
                if(mailRequestDTO.getFileUrl().size() > 0){
                    for(int i=0; i<mailRequestDTO.getFileUrl().size(); i++){
                        fileNameSb.append(mailRequestDTO.getFileUrl().get(i).getName().substring(mailRequestDTO.getFileUrl().get(i).getName().indexOf('_') + 1));
                        if((i+1) != mailRequestDTO.getFileUrl().size()){
                            fileNameSb.append("|");
                        }
                    }
                }
                file_name = fileNameSb.toString();
            }

            /* 여기까지 수정해주시기 바랍니다. */

            String urlParameters = "\"subject\":\"" + subject + "\" "
                    + ", \"body\":\"" + body + "\" "
                    + ", \"sender\":\"" + sender + "\" "
                    + ", \"sender_name\":\"" + sender_name + "\" "
                    + ", \"username\":\"" + username + "\" "
                    + ", \"receiver\":" + receiver;

                    if(mailRequestDTO.getTemplate() != null && !"".equals(mailRequestDTO.getTemplate())){
                        urlParameters += ", \"template\":\"" + mailRequestDTO.getTemplate() + "\" ";		//템플릿 사용할 경우 주석 해제  //발송 할 템플릿 번호
                    }
                    //+ ", \"address_books\":\"" + address_books + "\" "	//주소록 사용할 경우 주석 해제
                    //+ ", \"duplicate_yn\":\"" + duplicate_yn + "\" "      //중복 발송을 허용할 경우 주석 해제

                    // 예약 관련 파라미터 주석 해제
                    //+ ", \"mail_type\":\"" + mail_type + "\" "
                    //+ ", \"start_reserve_time\":\"" + start_reserve_time + "\" "
                    //+ ", \"end_reserve_time\":\"" + end_reserve_time + "\" "
                    //+ ", \"remained_count\":\"" + remained_count + "\" "

                    // 필수 안내문구 관련 파라미터 주석 해제
                    //+ ", \"agreement_text\":\"" + agreement_text + "\" "
                    //+ ", \"deny_text\":\"" + deny_text + "\" "
                    //+ ", \"sender_info_text\":\"" + sender_info_text + "\" "
                    //+ ", \"logo_path\":\"" + logo_path + "\" "
                    //+ ", \"logo_state\":\"" + logo_state + "\" "
                    //+ ", \"logo_sort\":\"" + logo_sort + "\" "

                    // 메일내용, 풋터(수신옵션) 정렬 사용할 경우 주석 해제
                    //+ ", \"footer_sort\":\"" + footer_sort + "\" "

                    // 메일 발송결과를 받고 싶은 URL     return_url이 있는 경우 주석해제 바랍니다.
                    //+ ", \"return_url_yn\": " + true        //return_url 사용시 필수 입력
                    //+ ", \"return_url\":\"" + return_url + "\" "		    //return_url 사용시 필수 입력

                    // 발송 결과 측정 항목을 사용할 경우 주석 해제
                    //+ ", \"open\":\"" + open + "\" "
                    //+ ", \"click\":\"" + click + "\" "
                    //+ ", \"check_period\":\"" + check_period + "\" "
                    //+ ", \"option_return_url\":\"" + option_return_url + "\" "

                    // 첨부 파일이 있는 경우 주석 해제
                    if(file_url != null && !"".equals(file_url)) {
                        urlParameters += ", \"file_url\":\"" + file_url + "\" "
                                + ", \"file_name\":\"" + file_name + "\" ";
                    }

            urlParameters +=  ", \"key\":\"" + key + "\" ";
            urlParameters = "{"+ urlParameters  +"}";		//JSON 데이터
            System.out.println("urlParameters : " + urlParameters);

            System.setProperty("jsse.enableSNIExtension", "false");
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter (con.getOutputStream(), StandardCharsets.UTF_8);
            wr.write(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println(responseCode); // 200

            /*
             * responseCode 가 200 이 아니면 내부에서 문제가 발생한 케이스입니다.
             * directsend 관리자에게 문의해주시기 바랍니다.
             */

            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            System.out.println("mail send response : " + response.toString()); // {"status":"0"}
            JSONParser parser = new JSONParser();
            JSONObject responseObj = (JSONObject) parser.parse(response.toString());
            String mailResponseCode = "";
            if(responseObj.get("status") != null){
                mailResponseCode = String.valueOf(responseObj.get("status"));

                if("0".equals(mailResponseCode)){
                    responseDto.setResultCode(CommConstants.RESULT_CODE_SUCCESS);
                    responseDto.setResultMessage(CommConstants.RESULT_MSG_SUCCESS);
                }else{
                    responseDto.setResultCode(CommConstants.RESULT_CODE_FAIL);
                    responseDto.setResultMessage("[" + mailResponseCode + "]" + responseObj.get("msg"));
                }
            }else{
                responseDto.setResultCode(CommConstants.RESULT_CODE_FAIL);
                responseDto.setResultMessage(CommConstants.RESULT_MSG_FAIL);
            }


            /*
             * response의 실패
             * {"status":101, "msg":"UTF-8 인코딩이 아닙니다."}
             * 실패 코드번호, 내용
             */

            /*
             * response 성공
             * {"status":0}
             * 성공 코드번호 (성공코드는 다이렉트센드 DB서버에 정상수신됨을 뜻하며 발송성공(실패)의 결과는 발송완료 이후 확인 가능합니다.)
             *
             * 잘못된 이메일 주소가 포함된 경우
             * {"status":0, "msg":"유효하지 않는 이메일을 제외하고 발송 완료 하였습니다.", "msg_detail":"error email : test2@test2, test3@test"}
             * 성공 코드번호 (성공코드는 다이렉트센드 DB서버에 정상수신됨을 뜻하며 발송성공(실패)의 결과는 발송완료 이후 확인 가능합니다.), 내용, 잘못된 데이터
             *
             */

            /*
                status code
                0   : 정상발송 (성공코드는 다이렉트센드 DB서버에 정상수신됨을 뜻하며 발송성공(실패)의 결과는 발송완료 이후 확인 가능합니다.)
                100 : POST validation 실패
                101 : 회원정보가 일치하지 않음
                102 : Subject, Body 정보가 없습니다.
                103 : Sender 이메일이 유효하지 않습니다.
                104 : receiver 이메일이 유효하지 않습니다.
                105 : 본문에 포함되면 안되는 확장자가 있습니다.
                106 : body validation 실패
                107 : 받는사람이 없습니다.
                108 : 예약정보가 유효하지 않습니다.
                109 : return_url이 없습니다.
                110 : 첨부파일이 없습니다.
                111 : 첨부파일의 개수가 5개를 초과합니다.
                112 : 파일의 총Size가 10 MB를 넘어갑니다.
                113 : 첨부파일이 다운로드 되지 않았습니다.
                114 : utf-8 인코딩 에러 발생
                115 : 템플릿 validation 실패
                200 : 동일 예약시간으로는 200회 이상 API 호출을 할 수 없습니다.
                201 : 분당 300회 이상 API 호출을 할 수 없습니다.
                202 : 발송자명이 최대길이를 초과 하였습니다.
                205 : 잔액부족
                999 : Internal Error.
             */

        }catch (IOException | ParseException me){
            me.printStackTrace();
        }

        return responseDto;
    }

}