package com.mtf.edumarine.controller;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineMngService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Sipa page controller.
 */
@Controller
public class EduMarineMngController {

    // 필드 주입이 아닌 생성자 주입형태로 사용합니다. '생성자 주입 형태'로 사용합니다.
    private final EduMarineMngService eduMarineMngService;

    private final CommService commService;

    /**
     * Instantiates a new Sipa controller.
     *
     * @param kms         the kms
     * @param cs
     */
    public EduMarineMngController(EduMarineMngService kms, CommService cs){
        this.eduMarineMngService = kms;
        this.commService = cs;
    }

    // Customer Folder

    /**
     * mng login model and view.
     *
     * @return the model and view
     */
    @RequestMapping(value = "/mng/index.do")
    public ModelAndView mng_index() {
        System.out.println("EduMarineMngController > mng_index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/index");
        return mv;
    }

    @RequestMapping(value = "/mng/main.do")
    public ModelAndView mng_main() {
        System.out.println("EduMarineMngController > mng_main");
        ModelAndView mv = new ModelAndView();

        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
        LocalDate now = LocalDate.now();

        // 연도, 월(문자열, 숫자), 일, 일(year 기준), 요일(문자열, 숫자)
        String fullYear = String.valueOf(now.getYear() + 1);
        /* 커밋 테스트 231011-2 */
        mv.setViewName("/mng/main");
        return mv;
    }

    /**
     * loginCheck model and view.
     *
     * @return the model and view
     */
    @RequestMapping(value = "/mng/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AdminDTO> login(@RequestBody AdminDTO adminDTO, HttpSession session) {
        System.out.println("EduMarineMngController > login");
        AdminDTO result = eduMarineMngService.login(adminDTO, session);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/mng/logoutCheck.do")
    public ModelAndView logoutCheck(HttpSession session, ModelAndView mv) {
        System.out.println("EduMarineMngController > logoutCheck");
        eduMarineMngService.logoutCheck(session);
        mv.setViewName("/mng/index");
        return mv;
    }

    //***************************************************************************
    // board Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/board/notice.do", method = RequestMethod.GET)
    public ModelAndView mng_board_notice() {
        System.out.println("EduMarineMngController > mng_exhibitor_company");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/notice");
        return mv;
    }

    @RequestMapping(value = "/mng/board/notice/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<NoticeDTO>> mng_board_notice_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_center_board_notice_selectList");
        //System.out.println(searchDTO.toString());

        List<NoticeDTO> responseList = eduMarineMngService.processSelectNoticeList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/notice/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<NoticeDTO> mng_board_notice_selectSingle(@RequestBody NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngController > mng_board_notice_selectSingle");
        //System.out.println(searchDTO.toString());

        NoticeDTO response = eduMarineMngService.processSelectNoticeSingle(noticeDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/notice/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_notice_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_notice_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setSeq(seq);
            NoticeDTO info = eduMarineMngService.processSelectNoticeSingle(noticeDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/notice/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/notice/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_notice_delete(@RequestBody NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngController > mng_board_notice_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteNotice(noticeDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/notice/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_notice_update(@RequestBody NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngController > mng_center_board_notice_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateNotice(noticeDTO);

        String fileIdList = noticeDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(noticeDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/notice/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_notice_insert(@RequestBody NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngController > mng_center_board_notice_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertNotice(noticeDTO);

        String fileIdList = noticeDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(responseDTO.getCustomValue());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/sipaNews.do", method = RequestMethod.GET)
    public ModelAndView mng_board_sipaNews() {
        System.out.println("EduMarineMngController > mng_board_sipaNews");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/sipaNews");
        return mv;
    }

    @RequestMapping(value = "/mng/board/sipaNews/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SipaNewsDTO>> mng_board_sipaNews_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_sipaNews_selectList");
        //System.out.println(searchDTO.toString());

        List<SipaNewsDTO> responseList = eduMarineMngService.processSelectSipaNewsList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/sipaNews/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_sipaNews_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_sipaNews_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            SipaNewsDTO sipaNewsDTO = new SipaNewsDTO();
            sipaNewsDTO.setSeq(seq);
            SipaNewsDTO info = eduMarineMngService.processSelectSipaNewsSingle(sipaNewsDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/sipaNews/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/sipaNews/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SipaNewsDTO> mng_board_notice_selectSingle(@RequestBody SipaNewsDTO sipaNewsDTO) {
        System.out.println("EduMarineMngController > mng_board_notice_selectSingle");
        //System.out.println(searchDTO.toString());

        SipaNewsDTO response = eduMarineMngService.processSelectSipaNewsSingle(sipaNewsDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/sipaNews/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_sipaNews_delete(@RequestBody SipaNewsDTO sipaNewsDTO) {
        System.out.println("EduMarineMngController > mng_board_sipaNews_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteSipaNews(sipaNewsDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/sipaNews/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_sipaNews_update(@RequestBody SipaNewsDTO sipaNewsDTO) {
        System.out.println("EduMarineMngController > mng_center_board_sipaNews_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSipaNews(sipaNewsDTO);

        String fileIdList = sipaNewsDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(sipaNewsDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/sipaNews/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_sipaNews_insert(@RequestBody SipaNewsDTO sipaNewsDTO) {
        System.out.println("EduMarineMngController > mng_center_board_sipaNews_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertSipaNews(sipaNewsDTO);

        String fileIdList = sipaNewsDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(responseDTO.getCustomValue());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/event.do", method = RequestMethod.GET)
    public ModelAndView mng_board_event() {
        System.out.println("EduMarineMngController > mng_board_event");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/event");
        return mv;
    }

    @RequestMapping(value = "/mng/board/event/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<EventDTO>> mng_board_event_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_event_selectList");
        //System.out.println(searchDTO.toString());

        List<EventDTO> responseList = eduMarineMngService.processSelectEventList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/event/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<EventDTO> mng_board_event_selectSingle(@RequestBody EventDTO eventDTO) {
        System.out.println("EduMarineMngController > mng_board_event_selectSingle");
        //System.out.println(searchDTO.toString());

        EventDTO response = eduMarineMngService.processSelectEventSingle(eventDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/event/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_event_delete(@RequestBody EventDTO eventDTO) {
        System.out.println("EduMarineMngController > mng_board_event_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteEvent(eventDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/event/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_event_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_event_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            EventDTO eventDTO = new EventDTO();
            eventDTO.setSeq(seq);
            EventDTO info = eduMarineMngService.processSelectEventSingle(eventDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/board/event/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/event/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_event_update(@RequestBody EventDTO eventDTO) {
        System.out.println("EduMarineMngController > mng_center_board_event_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateEvent(eventDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/event/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_event_insert(@RequestBody EventDTO eventDTO) {
        System.out.println("EduMarineMngController > mng_center_board_event_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertEvent(eventDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq.do", method = RequestMethod.GET)
    public ModelAndView mng_board_faq() {
        System.out.println("EduMarineMngController > mng_board_faq");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/faq");
        return mv;
    }

    @RequestMapping(value = "/mng/board/faq/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FaqDTO>> mng_board_faq_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_faq_selectList");
        //System.out.println(searchDTO.toString());

        List<FaqDTO> responseList = eduMarineMngService.processSelectFaqList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FaqDTO> mng_board_faq_selectSingle(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_board_faq_selectSingle");
        //System.out.println(searchDTO.toString());

        FaqDTO response = eduMarineMngService.processSelectFaqSingle(faqDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_faq_delete(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_board_faq_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteFaq(faqDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_faq_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_faq_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            FaqDTO faqDTO = new FaqDTO();
            faqDTO.setSeq(seq);
            FaqDTO info = eduMarineMngService.processSelectFaqSingle(faqDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/faq/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/faq/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_faq_update(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_center_board_faq_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFaq(faqDTO);

        String fileIdList = faqDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(faqDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_faq_insert(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_center_board_faq_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertFaq(faqDTO);

        String fileIdList = faqDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(responseDTO.getCustomValue());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //***************************************************************************
    // popup Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/popup/popup.do", method = RequestMethod.GET)
    public ModelAndView mng_popup_popup() {
        System.out.println("EduMarineMngController > mng_popup_popup");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/popup/popup");
        return mv;
    }

    //***************************************************************************
    // company Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/company/businessNotice.do", method = RequestMethod.GET)
    public ModelAndView mng_company_businessNotice() {
        System.out.println("EduMarineMngController > mng_company_businessNotice");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/company/businessNotice");
        return mv;
    }

    @RequestMapping(value = "/mng/company/tenderNotice.do", method = RequestMethod.GET)
    public ModelAndView mng_company_tenderNotice() {
        System.out.println("EduMarineMngController > mng_company_tenderNotice");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/company/tenderNotice");
        return mv;
    }

    @RequestMapping(value = "/mng/company/issue.do", method = RequestMethod.GET)
    public ModelAndView mng_company_issue() {
        System.out.println("EduMarineMngController > mng_company_issue");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/company/issue");
        return mv;
    }

    //***************************************************************************
    // member Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/member/ascdirectors.do", method = RequestMethod.GET)
    public ModelAndView mng_member_ascdirectors() {
        System.out.println("EduMarineMngController > mng_member_ascdirectors");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/member/ascdirectors");
        return mv;
    }

    @RequestMapping(value = "/mng/member/ascdirectors/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<AscdirectorsDTO>> mng_member_ascdirectors_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_selectList");
        //System.out.println(searchDTO.toString());

        List<AscdirectorsDTO> responseList = eduMarineMngService.processSelectAscdirectorsList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/ascdirectors/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AscdirectorsDTO> mng_member_ascdirectors_selectSingle(@RequestBody AscdirectorsDTO ascdirectorsDTO) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_selectSingle");
        //System.out.println(searchDTO.toString());

        AscdirectorsDTO response = eduMarineMngService.processSelectAscdirectorsSingle(ascdirectorsDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/ascdirectors/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_ascdirectors_delete(@RequestBody AscdirectorsDTO ascdirectorsDTO) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteAscdirectors(ascdirectorsDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/ascdirectors/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_member_ascdirectors_detail(String seq) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            AscdirectorsDTO ascdirectorsDTO = new AscdirectorsDTO();
            ascdirectorsDTO.setSeq(seq);
            AscdirectorsDTO info = eduMarineMngService.processSelectAscdirectorsSingle(ascdirectorsDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/member/ascdirectors/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/member/ascdirectors/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_ascdirectors_update(@RequestBody AscdirectorsDTO ascdirectorsDTO) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateAscdirectors(ascdirectorsDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/ascdirectors/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_ascdirectors_insert(@RequestBody AscdirectorsDTO ascdirectorsDTO) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertAscdirectors(ascdirectorsDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/adviser.do", method = RequestMethod.GET)
    public ModelAndView mng_member_adviser() {
        System.out.println("EduMarineMngController > mng_member_adviser");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/member/adviser");
        return mv;
    }

    @RequestMapping(value = "/mng/member/adviser/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<AdviserDTO>> mng_member_adviser_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_member_adviser_selectList");
        //System.out.println(searchDTO.toString());

        List<AdviserDTO> responseList = eduMarineMngService.processSelectAdviserList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/adviser/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AdviserDTO> mng_member_adviser_selectSingle(@RequestBody AdviserDTO adviserDTO) {
        System.out.println("EduMarineMngController > mng_member_adviser_selectSingle");
        //System.out.println(searchDTO.toString());

        AdviserDTO response = eduMarineMngService.processSelectAdviserSingle(adviserDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/adviser/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_adviser_delete(@RequestBody AdviserDTO adviserDTO) {
        System.out.println("EduMarineMngController > mng_member_adviser_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteAdviser(adviserDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/adviser/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_member_adviser_detail(String seq) {
        System.out.println("EduMarineMngController > mng_member_adviser_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            AdviserDTO adviserDTO = new AdviserDTO();
            adviserDTO.setSeq(seq);
            AdviserDTO info = eduMarineMngService.processSelectAdviserSingle(adviserDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/member/adviser/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/member/adviser/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_adviser_update(@RequestBody AdviserDTO adviserDTO) {
        System.out.println("EduMarineMngController > mng_member_adviser_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateAdviser(adviserDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/adviser/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_adviser_insert(@RequestBody AdviserDTO adviserDTO) {
        System.out.println("EduMarineMngController > mng_member_adviser_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertAdviser(adviserDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/consultation.do", method = RequestMethod.GET)
    public ModelAndView mng_member_consultation() {
        System.out.println("EduMarineMngController > mng_member_consultation");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/member/consultation");
        return mv;
    }

    @RequestMapping(value = "/mng/member/consultation/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<ConsultationDTO>> mng_member_consultation_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_member_consultation_selectList");
        //System.out.println(searchDTO.toString());

        List<ConsultationDTO> responseList = eduMarineMngService.processSelectConsultationList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/consultation/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ConsultationDTO> mng_member_consultation_selectSingle(@RequestBody ConsultationDTO consultationDTO) {
        System.out.println("EduMarineMngController > mng_member_consultation_selectSingle");
        //System.out.println(searchDTO.toString());

        ConsultationDTO response = eduMarineMngService.processSelectConsultationSingle(consultationDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/consultation/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_consultation_delete(@RequestBody ConsultationDTO consultationDTO) {
        System.out.println("EduMarineMngController > mng_member_consultation_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteConsultation(consultationDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/consultation/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_member_consultation_detail(String seq) {
        System.out.println("EduMarineMngController > mng_member_consultation_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            ConsultationDTO consultationDTO = new ConsultationDTO();
            consultationDTO.setSeq(seq);
            ConsultationDTO info = eduMarineMngService.processSelectConsultationSingle(consultationDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/member/consultation/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/member/consultation/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_consultation_update(@RequestBody ConsultationDTO consultationDTO) {
        System.out.println("EduMarineMngController > mng_member_consultation_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateConsultation(consultationDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/consultation/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_consultation_insert(@RequestBody ConsultationDTO consultationDTO) {
        System.out.println("EduMarineMngController > mng_member_consultation_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertConsultation(consultationDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/company.do", method = RequestMethod.GET)
    public ModelAndView mng_member_company() {
        System.out.println("EduMarineMngController > mng_member_company");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/member/company");
        return mv;
    }

    @RequestMapping(value = "/mng/member/company/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CompanyDTO>> mng_member_company_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_member_company_selectList");
        //System.out.println(searchDTO.toString());

        List<CompanyDTO> responseList = eduMarineMngService.processSelectCompanyList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/company/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CompanyDTO> mng_member_company_selectSingle(@RequestBody CompanyDTO companyDTO) {
        System.out.println("EduMarineMngController > mng_member_company_selectSingle");
        //System.out.println(searchDTO.toString());

        CompanyDTO response = eduMarineMngService.processSelectCompanySingle(companyDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/company/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_company_delete(@RequestBody CompanyDTO companyDTO) {
        System.out.println("EduMarineMngController > mng_member_company_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteCompany(companyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/company/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_member_company_detail(String seq) {
        System.out.println("EduMarineMngController > mng_member_company_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setSeq(seq);
            CompanyDTO info = eduMarineMngService.processSelectCompanySingle(companyDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/member/company/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/member/company/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_company_update(@RequestBody CompanyDTO companyDTO) {
        System.out.println("EduMarineMngController > mng_member_company_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateCompany(companyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/company/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_company_insert(@RequestBody CompanyDTO companyDTO) {
        System.out.println("EduMarineMngController > mng_member_company_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertCompany(companyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/partnership.do", method = RequestMethod.GET)
    public ModelAndView mng_member_partnership() {
        System.out.println("EduMarineMngController > mng_member_partnership");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/member/partnership");
        return mv;
    }

    @RequestMapping(value = "/mng/member/partnership/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<PartnershipDTO>> mng_member_partnership_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_member_partnership_selectList");
        //System.out.println(searchDTO.toString());

        List<PartnershipDTO> responseList = eduMarineMngService.processSelectPartnershipList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/partnership/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PartnershipDTO> mng_member_partnership_selectSingle(@RequestBody PartnershipDTO partnershipDTO) {
        System.out.println("EduMarineMngController > mng_member_partnership_selectSingle");
        //System.out.println(searchDTO.toString());

        PartnershipDTO response = eduMarineMngService.processSelectPartnershipSingle(partnershipDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/partnership/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_partnership_delete(@RequestBody PartnershipDTO partnershipDTO) {
        System.out.println("EduMarineMngController > mng_member_partnership_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeletePartnership(partnershipDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/partnership/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_member_partnership_detail(String seq) {
        System.out.println("EduMarineMngController > mng_member_partnership_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            PartnershipDTO partnershipDTO = new PartnershipDTO();
            partnershipDTO.setSeq(seq);
            PartnershipDTO info = eduMarineMngService.processSelectPartnershipSingle(partnershipDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/member/partnership/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/member/partnership/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_partnership_update(@RequestBody PartnershipDTO partnershipDTO) {
        System.out.println("EduMarineMngController > mng_member_ascdirectors_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePartnership(partnershipDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/member/partnership/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_member_partnership_insert(@RequestBody PartnershipDTO partnershipDTO) {
        System.out.println("EduMarineMngController > mng_member_partnership_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertPartnership(partnershipDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*********************** file upload ***********************/

    @RequestMapping(value = "/file/upload/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FileResponseDTO> file_upload_save(@RequestBody FileDTO fileDTO) {
        System.out.println("EduMarineMngController > file_upload_save");
        System.out.println(fileDTO.toString());

        FileResponseDTO responseDTO = eduMarineMngService.processInsertFileInfo(fileDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/file/upload/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FileResponseDTO> file_upload_update(@RequestBody FileDTO fileDTO) {
        System.out.println("EduMarineMngController > file_upload_save");
        System.out.println(fileDTO.toString());

        FileResponseDTO responseDTO = eduMarineMngService.processUpdateFileUseN(fileDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/file/upload/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FileDTO>> file_upload_selectList(@RequestBody FileDTO fileDTO) {
        System.out.println("EduMarineMngController > file_upload_selectList");
        //System.out.println(newsletterDTO.toString());

        List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

    @RequestMapping(value = "/file/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> file_upload(HttpServletRequest uploadFile) {
        System.out.println("EduMarineMngController > file_upload");
        String gbn = uploadFile.getParameter("gbn");

        JSONObject response = new JSONObject();

        int size = 1024 * 1024 * 100; // 100M
        String file = "";
        String oriFile = "";

        JSONObject obj = new JSONObject();

        try {
            String path = "";
            if("mail".equals(gbn)){
                path = ResourceUtils.getFile("./tomcat/webapps/ROOT/WEB-INF/classes/static/img/" + gbn + "/").toPath().toString();
            }else{
                /* prod */
                path = ResourceUtils.getFile("./tomcat/webapps/upload/" + gbn + "/").toPath().toString();
            }

            /* 날짜로 폴더 setting
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String formatDate = sdt.format(date);

            String datePath = formatDate.replace("-", File.separator);*/

            /* dir 없다면 create */
            /*File uploadPath = new File(path + "/" + datePath);*/
            File uploadPath = new File(path);

            if (!uploadPath.exists()) {
                try {
                    Files.createDirectories(uploadPath.toPath());
                } catch (IOException e) {
                    System.out.println("[mkdir error] : " + e.getMessage());
                }
            }

            /* 파일명 중복 방지 - UUID setting */
            String uuid = UUID.randomUUID().toString();

            /* 폴더에 파일 업로드 */
            MultipartRequest multi = new MultipartRequest(uploadFile, uploadPath.getPath()+"/", size,
                    "UTF-8", new DefaultFileRenamePolicy());
            Enumeration files = multi.getFileNames();
            String str = (String)files.nextElement();

            file = multi.getFilesystemName(str);
            oriFile = multi.getOriginalFileName(str);
            // 파일명에서 공백 제거
            oriFile = oriFile.replaceAll("(\r\n|\r|\n|\n\r|\\p{Z}|\\t)", "").replaceAll("%20", "").replaceAll("\\s+", "");
            // 파일명에서 특수문자 제거
            oriFile = oriFile.replaceAll("[:\\\\/*?|<>\\[\\]]", "_");

            // 업로드된 파일 객체 생성
            File oldFile = new File(uploadPath.getPath() + "/" + file);

            // 실제 저장될 파일 객체 생성
            File newFile = new File(uploadPath.getPath() + File.separator + uuid + "_" + oriFile);

            // 파일명 rename
            if(!oldFile.renameTo(newFile)){
                int read = 0;
                byte[] buf = new byte[1024];
                FileInputStream fin = null;
                FileOutputStream fout = null;
                //rename이 되지 않을경우 강제로 파일을 복사하고 기존파일은 삭제
                buf = new byte[1024];
                fin = new FileInputStream(oldFile);
                fout = new FileOutputStream(newFile);
                read = 0;
                while((read=fin.read(buf,0,buf.length))!=-1){
                    fout.write(buf, 0, read);
                }

                fin.close();
                fout.close();
                oldFile.delete();
            }

            response.put("uploadPath",uploadPath.getPath());
            response.put("uuid",uuid);
            response.put("fileName", oriFile);

            System.out.println("[full file path] : " + uploadPath + File.separator + uuid + "_" + oriFile);
            /*System.out.println("[uploadPath.getPath()2] : " + uploadPath.getPath());
            System.out.println("[uuid] : " + uuid);
            System.out.println("[fileName] : " + oriFile);
            System.out.println("[file] : " + file);*/

        } catch (Exception e) {
            System.out.println("[upload file save error] : " + e.getMessage());
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/board/uploadFileGet")
    public ResponseEntity<byte[]> board_uploadFileGet(@RequestParam("fileName") String fileName) {
        System.out.println("EduMarineMngController > board_uploadFileGet");
        //System.out.println("fileName : " + fileName);

        //String replaceFileName = fileName.replace("/",File.separator);

        File file = new File(fileName);

        ResponseEntity<byte[]> result = null;

        try {

            HttpHeaders header = new HttpHeaders();

        /*
        Files.probeContentType() 해당 파일의 Content 타입을 인식(image, text/plain ...)
        없으면 null 반환

        file.toPath() -> file 객체를 Path객체로 변환

        */
            //System.out.println("Files.content-type : " + Files.probeContentType(file.toPath()));
            header.add("Content-type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /*********************** file download ***********************/

    @RequestMapping(value = "/file/download.do", method = RequestMethod.GET)
    public void board_downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("EduMarineMngController > board_downloadFile");
        String path = request.getParameter("path");
        path = path.replaceAll("\\\\", "/");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String file_repo = "";
        if("mail".equals(path)){
            file_repo = ResourceUtils.getFile("./tomcat/webapps/ROOT/WEB-INF/classes/static/img/" + path + "/").toPath().toString();
        }else {
            // 파일 업로드된 경로
            file_repo = ResourceUtils.getFile("./tomcat/webapps/upload/" + path + "/").toPath().toString();
        }
        // 서버에 실제 저장된 파일명
        //String filename = "20140819151221.zip" ;
        String fileName = request.getParameter("fileName");

        OutputStream out = response.getOutputStream();
        String downFile = file_repo + "/" + fileName;
        File f = new File(downFile);
        response.setHeader("Cache-Control", "no-cache");
        // 한글 파일명 처리
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.addHeader("Content-disposition","attachment; fileName=" + fileName);

        FileInputStream in = new FileInputStream(f);
        byte[] buffer = new byte[1024*8];
        while(true){
            int count = in.read(buffer);
            if(count == -1){
                break;
            }
            out.write(buffer,0,count);
        }
        in.close();
        out.close();
    }

    @RequestMapping(value = "/mng/directory/download.do", method = RequestMethod.GET)
    public void directory_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > directory_download");
        String fileName = req.getParameter("fileName");
        String transferYear = req.getParameter("transferYear");

        // Workbook 생성
        try(XSSFWorkbook workbook = new XSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    "No",
                    "승인여부", "입금여부",
                    "회사명(국문)", "회사명(영문)",
                    "주소", "상세주소",
                    "대표자명", "전화",
                    "홈페이지", "FAX", "사업자등록번호",
                    "블로그", "페이스북", "인스타그램", "기타",
                    "회사소개(국문)", "회사소개(영문)",
                    "KIBS 참가목적(국문)", "KIBS 참가목적(영문)",
                    "성명", "직위", "부서", "전화번호", "휴대전화", "이메일",
                    "전시품목명", "브랜드명", "실물보트수"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000
            };

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈
            // 엑셀 헤더 셋팅 (참가업체정보)
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            // 엑셀 바디 셋팅 (참가업체정보)
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            // 엑셀 헤더 셋팅 (담당자정보)
            CellStyle headerStyle_ch = workbook.createCellStyle();
            headerStyle_ch.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_ch.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_ch.setBorderRight(BorderStyle.THIN);
            headerStyle_ch.setBorderLeft(BorderStyle.THIN);
            headerStyle_ch.setBorderTop(BorderStyle.THIN);
            headerStyle_ch.setBorderBottom(BorderStyle.THIN);
            headerStyle_ch.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_ch.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_ch.setFont(fontHeader);
            // 엑셀 바디 셋팅 (담당자정보)
            CellStyle bodyStyle_ch = workbook.createCellStyle();
            bodyStyle_ch.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle_ch.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle_ch.setBorderRight(BorderStyle.THIN);
            bodyStyle_ch.setBorderLeft(BorderStyle.THIN);
            bodyStyle_ch.setBorderTop(BorderStyle.THIN);
            bodyStyle_ch.setBorderBottom(BorderStyle.THIN);
            bodyStyle_ch.setFont(font9);
            bodyStyle_ch.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (전시정보)
            CellStyle headerStyle_di = workbook.createCellStyle();
            headerStyle_di.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_di.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_di.setBorderRight(BorderStyle.THIN);
            headerStyle_di.setBorderLeft(BorderStyle.THIN);
            headerStyle_di.setBorderTop(BorderStyle.THIN);
            headerStyle_di.setBorderBottom(BorderStyle.THIN);
            headerStyle_di.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.index);
            headerStyle_di.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_di.setFont(fontHeader);
            // 엑셀 바디 셋팅 (전시정보)
            CellStyle bodyStyle_di = workbook.createCellStyle();
            bodyStyle_di.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle_di.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle_di.setBorderRight(BorderStyle.THIN);
            bodyStyle_di.setBorderLeft(BorderStyle.THIN);
            bodyStyle_di.setBorderTop(BorderStyle.THIN);
            bodyStyle_di.setBorderBottom(BorderStyle.THIN);
            bodyStyle_di.setFont(font9);
            bodyStyle_di.setWrapText(true); //개행

            // 데이터 조회
            /*DirectoryDTO directoryDTO = new DirectoryDTO();
            directoryDTO.setTransferYear(transferYear);
            List<DirectoryDTO> directoryList = kibsMngService.processSelectDirectoryList(directoryDTO);*/

            //rows
            int rowCnt = 0;
            int cellCnt = 0;
            int listCount = 0;/*directoryList.size();*/

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            XSSFSheet sheet = workbook.createSheet("Directory");

            XSSFCell cell = null;
            XSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 참가업체정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,19));
            XSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("참가업체정보");

            // 담당자정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,20,25));
            XSSFCell mergeCell2 = row.createCell(20);
            mergeCell2.setCellStyle(headerStyle_ch);
            mergeCell2.setCellValue("담당자정보");

            // 전시정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,26,28));
            XSSFCell mergeCell3 = row.createCell(26);
            mergeCell3.setCellStyle(headerStyle_di);
            mergeCell3.setCellValue("전시정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i < 20) {
                    cell.setCellStyle(headerStyle);
                }else if(i<26){
                    cell.setCellStyle(headerStyle_ch);
                }else{
                    cell.setCellStyle(headerStyle_di);
                }
                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, colWidths_ex[i]);	//column width 지정
            }

            //데이터 부분 생성
            /*for(DirectoryDTO info : directoryList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getChargePersonName().split("\\^,");

                //줄 높이 계산
                for (String s : remark) {
                    if (s.length() > 0) {
                        nCount++;
                    }
                }

                //줄 높이 설정
                if (nCount > 1){
                    row.setHeightInPoints((nCount * sheet.getDefaultRowHeightInPoints()));
                }

                // 넘버링
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(listCount--);
                // 승인여부
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApprovalStatus());
                // 입금여부
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPrcYn());
                // 회사명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyNameKo());
                // 회사명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyNameEn());
                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyAddress());
                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyAddressDetail());
                // 대표자
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyCeo());
                // 전화
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyTel());
                // 홈페이지
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyHomepage());
                // FAX
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyFax());
                // 사업자등록번호
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyLicenseNum());
                // 블로그
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSnsBlog());
                // 페이스북
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSnsFacebook());
                // 인스타그램
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSnsInstagram());
                // 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSnsEtc());
                // 회사소개(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyIntroKo());
                // 회사소개(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyIntroEn());
                // KIBS 참가목적(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyPurposeKo());
                // KIBS 참가목적(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getCompanyPurposeEn());
                // 담당자명
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_ch);
                cell.setCellValue(info.getChargePersonName().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 담당자직위
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_ch);
                cell.setCellValue(info.getChargePersonPosition().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 담당자부서
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_ch);
                cell.setCellValue(info.getChargePersonDepart().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 담당자전화번호
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_ch);
                cell.setCellValue(info.getChargePersonTel().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 담당자휴대전화
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_ch);
                cell.setCellValue(info.getChargePersonPhone().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 담당자이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_ch);
                cell.setCellValue(info.getChargePersonEmail().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 전시품목
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_di);
                cell.setCellValue(info.getDisplayItem().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 브랜드명
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_di);
                cell.setCellValue(info.getDisplayBrand().replaceAll("\\^,","\n").replaceAll("\\^",""));
                // 전시품목보트수
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle_di);
                cell.setCellValue(info.getDisplayBoatCnt().replaceAll("\\^,","\n").replaceAll("\\^",""));
            }*/

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1024);
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /*********************** excel upload ***********************/

    @RequestMapping(value = "/mng/excelUpload.do" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_excelUpload(MultipartHttpServletRequest request) {
        System.out.println("EduMarineMngController > mng_excelUpload");
        ResponseDTO responseDto = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;

        try {
//            JSONObject jsonObject = new JSONObject();

            MultipartFile file = null;
            Iterator<String> iterator = request.getFileNames();
            if(iterator.hasNext()) {
                file = request.getFile(iterator.next());
            }

            System.out.println(Objects.requireNonNull(file).getOriginalFilename());
            System.out.println(file.getName());

            List<?> list = eduMarineMngService.uploadExcelFile(file);
            if(list !=null) {
//                jsonObject.put("rs", "0000");
            }else {
//                jsonObject.put("rs", "9999");
            }

        } catch (Exception e) {
            System.out.println("[EXCEL UPLOAD ERROR] : " + e.getMessage());
        }

        responseDto.setResultCode(resultCode);
        responseDto.setResultMessage(resultMessage);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /*********************** mail send ***********************/

    @RequestMapping(value = "/mail/send.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mail_send(@RequestBody MailRequestDTO mailRequestDTO) {
        System.out.println("EduMarineMngController > mail_send");
        System.out.println(mailRequestDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processMailSend(mailRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}