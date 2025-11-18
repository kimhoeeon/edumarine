package com.mtf.edumarine.controller;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineMngService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        String fullYear = String.valueOf(now.getYear());

        /* 전체 회원수 */
        StatisticsDTO memberStatDTO = new StatisticsDTO();
        StatisticsDTO memberStat = eduMarineMngService.processSelectMemberCount(memberStatDTO);
        mv.addObject("memberStat", memberStat);

        /* 교육신청자 수 */
        StatisticsDTO trainDTO = new StatisticsDTO();
        trainDTO.setGbn("ALL");
        StatisticsDTO trainStat = eduMarineMngService.processSelectTrainCount(trainDTO);
        mv.addObject("trainStat", trainStat);

        /* 교육신청 취소 수 */
        StatisticsDTO trainCancelDTO = new StatisticsDTO();
        trainCancelDTO.setGbn("CANCEL");
        StatisticsDTO trainCancelStat = eduMarineMngService.processSelectTrainCount(trainCancelDTO);
        mv.addObject("trainCancelStat", trainCancelStat);

        mv.setViewName("/mng/main");
        return mv;
    }

    @RequestMapping(value = "/mng/main/statistics/accessor/day.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<StatisticsDTO>> mng_main_statistics_accessor_day() {
        System.out.println("EduMarineMngController > mng_main_statistics_accessor_day");
        //System.System.out.println(searchDTO.toString());

        String transferYear = String.valueOf(LocalDateTime.now().getYear());
        StatisticsDTO reqDto = new StatisticsDTO();
        reqDto.setGbn("Accessor");
        List<StatisticsDTO> responseList = eduMarineMngService.processSelectStatisticsAccessorDay(reqDto);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/main/statistics/accessor/month.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<StatisticsDTO>> mng_main_statistics_accessor_month() {
        System.out.println("EduMarineMngController > mng_main_statistics_accessor_month");
        //System.System.out.println(searchDTO.toString());

        String transferYear = String.valueOf(LocalDateTime.now().getYear());
        StatisticsDTO reqDto = new StatisticsDTO();
        reqDto.setGbn("Accessor");
        List<StatisticsDTO> responseList = eduMarineMngService.processSelectStatisticsAccessorMonth(reqDto);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/main/statistics/accessor/week.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<StatisticsDTO>> mng_main_statistics_accessor_week() {
        System.out.println("EduMarineMngController > mng_main_statistics_accessor_week");
        //System.System.out.println(searchDTO.toString());

        String transferYear = String.valueOf(LocalDateTime.now().getYear());
        StatisticsDTO reqDto = new StatisticsDTO();
        reqDto.setGbn("Accessor");
        List<StatisticsDTO> responseList = eduMarineMngService.processSelectStatisticsAccessorWeek(reqDto);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/main/statistics/train/member.do", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SplineStatisticsDTO> mng_main_statistics_company_booth() {
        System.out.println("EduMarineMngController > mng_main_statistics_company_booth");
        //System.System.out.println(searchDTO.toString());

        String transferYear = String.valueOf(LocalDateTime.now().getYear()); //2024
        StatisticsDTO reqDto = new StatisticsDTO();
        StatisticsDTO info = eduMarineMngService.processSelectStatisticsTrainMember(reqDto);
        String[] statSplit = info.getInCount().split(",");

        SplineStatisticsDTO result = new SplineStatisticsDTO();
        List<Integer> series = new ArrayList<>();
        series.add(Integer.parseInt(statSplit[0])); //regular
        series.add(Integer.parseInt(statSplit[1])); //boarder
        series.add(Integer.parseInt(statSplit[2])); //frp
        series.add(Integer.parseInt(statSplit[3])); //outboarder
        series.add(Integer.parseInt(statSplit[4])); //inboarder
        series.add(Integer.parseInt(statSplit[5])); //sailyacht
        series.add(Integer.parseInt(statSplit[6])); //highhorsepower
        series.add(Integer.parseInt(statSplit[7])); //highSelf
        series.add(Integer.parseInt(statSplit[8])); //highSpecial
        series.add(Integer.parseInt(statSplit[9])); //sterndrive
        series.add(Integer.parseInt(statSplit[10])); //sternspecial
        series.add(Integer.parseInt(statSplit[11])); //basic
        series.add(Integer.parseInt(statSplit[12])); //emergency
        series.add(Integer.parseInt(statSplit[13])); //generator
        series.add(Integer.parseInt(statSplit[14])); //competency
        series.add(Integer.parseInt(statSplit[15])); //famtourin
        series.add(Integer.parseInt(statSplit[16])); //famtourout
        result.setSeries(series);

        List<String> labels = new ArrayList<>();
        labels.add("상시신청");
        labels.add("해상엔진 테크니션 (선내기/선외기)");
        labels.add("FRP 레저보트 선체 정비 테크니션");
        labels.add("해상엔진 자가정비 (선외기)");
        labels.add("해상엔진 자가정비 (선내기)");
        labels.add("해상엔진 자가정비 (세일요트)");
        labels.add("고마력 선외기 정비 중급 테크니션");
        labels.add("자가정비 심화과정 (고마력 선외기)");
        labels.add("고마력 선외기 정비 중급 테크니션 (특별반)");
        labels.add("스턴드라이브 정비 전문가과정");
        labels.add("스턴드라이브 정비 전문가과정 (특별반)");
        labels.add("기초정비교육");
        labels.add("응급조치교육");
        labels.add("발전기 정비 교육");
        labels.add("선외기/선내기 직무역량 강화과정");
        labels.add("선내기 팸투어");
        labels.add("선외기 팸투어");
        labels.add("레저선박 해양전자장비 교육");
        result.setLabels(labels);

        return new ResponseEntity<>(result, HttpStatus.OK);
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

    @RequestMapping(value = "/mng/logoutCheck.do", method = RequestMethod.POST)
    public ModelAndView logoutCheck(HttpSession session, ModelAndView mv) {
        System.out.println("EduMarineMngController > logoutCheck");
        eduMarineMngService.logoutCheck(session);
        mv.setViewName("/mng/index");
        return mv;
    }

    boolean isClose = false;
    @RequestMapping(value = "/mng/session/kill/gbn.do", method = RequestMethod.POST)
    @ResponseBody
    public void mng_session_kill_gbn(boolean isClose, HttpSession session) throws Exception {
        System.out.println("EduMarineMngController > mng_session_kill_gbn");
        this.isClose = isClose;

        if ( !isClose ) {
            System.out.println("Refresh Site");
            return ;
        }

        Thread.sleep(2000);
        if ( this.isClose ) {
            System.out.println("Log Out");
            eduMarineMngService.logoutCheck(session);
        }

    }


    //***************************************************************************
    // board Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/adminMng/admin.do", method = RequestMethod.GET)
    public ModelAndView mng_adminMng_admin() {
        System.out.println("EduMarineMngController > mng_adminMng_admin");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/adminMng/admin");
        return mv;
    }

    @RequestMapping(value = "/mng/adminMng/admin/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_adminMng_admin_detail(String seq) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setSeq(seq);
            AdminDTO info = eduMarineMngService.processSelectAdminMngSingle(adminDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/adminMng/admin/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/adminMng/admin/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<AdminDTO>> mng_adminMng_admin_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_selectList");
        //System.System.out.println(searchDTO.toString());

        List<AdminDTO> responseList = eduMarineMngService.processSelectAdminMngList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/adminMng/admin/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AdminDTO> mng_adminMng_admin_selectSingle(@RequestBody AdminDTO adminDTO) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_selectSingle");
        //System.System.out.println(searchDTO.toString());

        AdminDTO response = eduMarineMngService.processSelectAdminMngSingleId(adminDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/adminMng/admin/checkDuplicateId.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> mng_adminMng_admin_checkDuplicateId(@RequestBody AdminDTO adminDTO) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_checkDuplicateId");
        //System.System.out.println(searchDTO.toString());
        Integer result = eduMarineMngService.processSelectAdminMngCheckDuplicateId(adminDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/adminMng/admin/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_adminMng_admin_update(@RequestBody AdminDTO adminDTO) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateAdminMng(adminDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/adminMng/admin/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_adminMng_admin_insert(@RequestBody AdminDTO adminDTO, HttpSession session) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_insert");
        //System.System.out.println(noticeDTO.toString());

        String regiPic = session.getAttribute("id").toString();
        adminDTO.setInitRegiPic(regiPic);
        adminDTO.setFinalRegiPic(regiPic);
        ResponseDTO responseDTO = eduMarineMngService.processInsertAdminMng(adminDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/adminMng/admin/updateValidYn.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_adminMng_admin_checkValidYn(@RequestBody AdminDTO adminDTO) {
        System.out.println("EduMarineMngController > mng_adminMng_admin_checkValidYn");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processCheckAdminMngValidYn(adminDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
        //System.System.out.println(searchDTO.toString());

        List<NoticeDTO> responseList = eduMarineMngService.processSelectNoticeList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/notice/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<NoticeDTO> mng_board_notice_selectSingle(@RequestBody NoticeDTO noticeDTO) {
        System.out.println("EduMarineMngController > mng_board_notice_selectSingle");
        //System.System.out.println(searchDTO.toString());

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
        //System.System.out.println(noticeDTO.toString());

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
        //System.System.out.println(noticeDTO.toString());

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

    @RequestMapping(value = "/mng/board/press.do", method = RequestMethod.GET)
    public ModelAndView mng_board_press() {
        System.out.println("EduMarineMngController > mng_board_press");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/press");
        return mv;
    }

    @RequestMapping(value = "/mng/board/press/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<PressDTO>> mng_board_press_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_press_selectList");
        //System.System.out.println(searchDTO.toString());

        List<PressDTO> responseList = eduMarineMngService.processSelectPressList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/press/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PressDTO> mng_board_press_selectSingle(@RequestBody PressDTO pressDTO) {
        System.out.println("EduMarineMngController > mng_board_press_selectSingle");
        //System.System.out.println(searchDTO.toString());

        PressDTO response = eduMarineMngService.processSelectPressSingle(pressDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/press/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_press_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_press_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            PressDTO pressDTO = new PressDTO();
            pressDTO.setSeq(seq);
            PressDTO info = eduMarineMngService.processSelectPressSingle(pressDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/press/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/press/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_press_delete(@RequestBody PressDTO pressDTO) {
        System.out.println("EduMarineMngController > mng_board_press_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeletePress(pressDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/press/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_press_update(@RequestBody PressDTO pressDTO) {
        System.out.println("EduMarineMngController > mng_board_press_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePress(pressDTO);

        String fileIdList = pressDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(pressDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/press/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_press_insert(@RequestBody PressDTO pressDTO) {
        System.out.println("EduMarineMngController > mng_board_press_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertPress(pressDTO);

        String fileIdList = pressDTO.getFileIdList();
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

    @RequestMapping(value = "/mng/board/gallery.do", method = RequestMethod.GET)
    public ModelAndView mng_board_gallery() {
        System.out.println("EduMarineMngController > mng_board_gallery");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/gallery");
        return mv;
    }

    @RequestMapping(value = "/mng/board/gallery/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<GalleryDTO>> mng_board_gallery_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_gallery_selectList");
        //System.System.out.println(searchDTO.toString());

        List<GalleryDTO> responseList = eduMarineMngService.processSelectGalleryList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/gallery/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GalleryDTO> mng_board_gallery_selectSingle(@RequestBody GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngController > mng_board_gallery_selectSingle");
        //System.System.out.println(searchDTO.toString());

        GalleryDTO response = eduMarineMngService.processSelectGallerySingle(galleryDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/gallery/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_gallery_delete(@RequestBody GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngController > mng_board_gallery_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteGallery(galleryDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/gallery/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_gallery_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_gallery_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            GalleryDTO galleryDTO = new GalleryDTO();
            galleryDTO.setSeq(seq);
            GalleryDTO info = eduMarineMngService.processSelectGallerySingle(galleryDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/gallery/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/gallery/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_gallery_update(@RequestBody GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngController > mng_board_gallery_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateGallery(galleryDTO);

        String fileIdList = galleryDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(galleryDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/gallery/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_gallery_insert(@RequestBody GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngController > mng_board_gallery_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertGallery(galleryDTO);

        String fileIdList = galleryDTO.getFileIdList();
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

    @RequestMapping(value = "/mng/board/media.do", method = RequestMethod.GET)
    public ModelAndView mng_board_media() {
        System.out.println("EduMarineMngController > mng_board_media");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/media");
        return mv;
    }

    @RequestMapping(value = "/mng/board/media/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<MediaDTO>> mng_board_media_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_media_selectList");
        //System.System.out.println(searchDTO.toString());

        List<MediaDTO> responseList = eduMarineMngService.processSelectMediaList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/media/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MediaDTO> mng_board_media_selectSingle(@RequestBody MediaDTO mediaDTO) {
        System.out.println("EduMarineMngController > mng_board_media_selectSingle");
        //System.System.out.println(searchDTO.toString());

        MediaDTO response = eduMarineMngService.processSelectMediaSingle(mediaDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/media/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_media_delete(@RequestBody MediaDTO mediaDTO) {
        System.out.println("EduMarineMngController > mng_board_media_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteMedia(mediaDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/media/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_media_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_media_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setSeq(seq);
            MediaDTO info = eduMarineMngService.processSelectMediaSingle(mediaDTO);
            mv.addObject("info", info);

        }

        mv.setViewName("/mng/board/media/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/media/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_media_update(@RequestBody MediaDTO mediaDTO) {
        System.out.println("EduMarineMngController > mng_board_media_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateMedia(mediaDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/media/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_media_insert(@RequestBody MediaDTO mediaDTO) {
        System.out.println("EduMarineMngController > mng_board_media_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertMedia(mediaDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/newsletter.do", method = RequestMethod.GET)
    public ModelAndView mng_board_newsletter() {
        System.out.println("EduMarineMngController > mng_board_newsletter");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/newsletter");
        return mv;
    }

    @RequestMapping(value = "/mng/board/newsletter/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<NewsletterDTO>> mng_board_newsletter_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_newsletter_selectList");
        //System.System.out.println(searchDTO.toString());

        List<NewsletterDTO> responseList = eduMarineMngService.processSelectNewsletterList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/newsletter/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<NewsletterDTO> mng_board_newsletter_selectSingle(@RequestBody NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngController > mng_board_newsletter_selectSingle");
        //System.System.out.println(searchDTO.toString());

        NewsletterDTO response = eduMarineMngService.processSelectNewsletterSingle(newsletterDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/newsletter/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_newsletter_delete(@RequestBody NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngController > mng_board_newsletter_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteNewsletter(newsletterDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/newsletter/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_newsletter_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_newsletter_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            NewsletterDTO newsletterDTO = new NewsletterDTO();
            newsletterDTO.setSeq(seq);
            NewsletterDTO info = eduMarineMngService.processSelectNewsletterSingle(newsletterDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/newsletter/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/newsletter/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_newsletter_update(@RequestBody NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngController > mng_center_board_newsletter_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateNewsletter(newsletterDTO);

        String fileIdList = newsletterDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(newsletterDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/newsletter/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_newsletter_insert(@RequestBody NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngController > mng_center_board_newsletter_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertNewsletter(newsletterDTO);

        String fileIdList = newsletterDTO.getFileIdList();
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

    @RequestMapping(value = "/mng/board/announcement.do", method = RequestMethod.GET)
    public ModelAndView mng_board_announcement() {
        System.out.println("EduMarineMngController > mng_board_announcement");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/announcement");
        return mv;
    }

    @RequestMapping(value = "/mng/board/announcement/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<AnnouncementDTO>> mng_board_announcement_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_announcement_selectList");
        //System.System.out.println(searchDTO.toString());

        List<AnnouncementDTO> responseList = eduMarineMngService.processSelectAnnouncementList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/announcement/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnnouncementDTO> mng_board_announcement_selectSingle(@RequestBody AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngController > mng_board_announcement_selectSingle");
        //System.System.out.println(searchDTO.toString());

        AnnouncementDTO response = eduMarineMngService.processSelectAnnouncementSingle(announcementDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/announcement/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_announcement_delete(@RequestBody AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngController > mng_board_announcement_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteAnnouncement(announcementDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/announcement/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_announcement_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_announcement_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            AnnouncementDTO announcementDTO = new AnnouncementDTO();
            announcementDTO.setSeq(seq);
            AnnouncementDTO info = eduMarineMngService.processSelectAnnouncementSingle(announcementDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/announcement/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/announcement/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_announcement_update(@RequestBody AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngController > mng_center_board_announcement_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateAnnouncement(announcementDTO);

        String fileIdList = announcementDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(announcementDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/announcement/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_announcement_insert(@RequestBody AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngController > mng_center_board_announcement_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertAnnouncement(announcementDTO);

        String fileIdList = announcementDTO.getFileIdList();
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

    @RequestMapping(value = "/mng/board/job.do", method = RequestMethod.GET)
    public ModelAndView mng_board_job() {
        System.out.println("EduMarineMngController > mng_board_job");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/job");
        return mv;
    }

    @RequestMapping(value = "/mng/board/job/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<JobDTO>> mng_board_job_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_job_selectList");
        //System.System.out.println(searchDTO.toString());

        List<JobDTO> responseList = eduMarineMngService.processSelectJobList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/job/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JobDTO> mng_board_job_selectSingle(@RequestBody JobDTO jobDTO) {
        System.out.println("EduMarineMngController > mng_board_job_selectSingle");
        //System.System.out.println(searchDTO.toString());

        JobDTO response = eduMarineMngService.processSelectJobSingle(jobDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/job/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_job_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_job_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            JobDTO jobDTO = new JobDTO();
            jobDTO.setSeq(seq);
            JobDTO info = eduMarineMngService.processSelectJobSingle(jobDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/job/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/job/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_job_delete(@RequestBody JobDTO jobDTO) {
        System.out.println("EduMarineMngController > mng_board_job_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteJob(jobDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/job/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_job_update(@RequestBody JobDTO jobDTO) {
        System.out.println("EduMarineMngController > mng_board_job_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateJob(jobDTO);

        String fileIdList = jobDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(jobDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/job/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_job_insert(@RequestBody JobDTO jobDTO) {
        System.out.println("EduMarineMngController > mng_center_board_job_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertJob(jobDTO);

        String fileIdList = jobDTO.getFileIdList();
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

    @RequestMapping(value = "/mng/board/employment.do", method = RequestMethod.GET)
    public ModelAndView mng_board_employment() {
        System.out.println("EduMarineMngController > mng_board_employment");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/employment");
        return mv;
    }

    @RequestMapping(value = "/mng/board/employment/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<EmploymentDTO>> mng_board_employment_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_employment_selectList");
        //System.System.out.println(searchDTO.toString());

        List<EmploymentDTO> responseList = eduMarineMngService.processSelectEmploymentList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/employment/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<EmploymentDTO> mng_board_employment_selectSingle(@RequestBody EmploymentDTO employmentDTO) {
        System.out.println("EduMarineMngController > mng_board_employment_selectSingle");
        //System.System.out.println(searchDTO.toString());

        EmploymentDTO response = eduMarineMngService.processSelectEmploymentSingle(employmentDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/employment/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_employment_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_employment_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            EmploymentDTO employmentDTO = new EmploymentDTO();
            employmentDTO.setSeq(seq);
            EmploymentDTO info = eduMarineMngService.processSelectEmploymentSingle(employmentDTO);
            mv.addObject("info", info);

        }

        mv.setViewName("/mng/board/employment/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/employment/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_employment_update(@RequestBody EmploymentDTO employmentDTO) {
        System.out.println("EduMarineMngController > mng_board_employment_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateEmployment(employmentDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/employment/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_employment_insert(@RequestBody EmploymentDTO employmentDTO) {
        System.out.println("EduMarineMngController > mng_center_board_employment_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertEmployment(employmentDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/community.do", method = RequestMethod.GET)
    public ModelAndView mng_board_community() {
        System.out.println("EduMarineMngController > mng_board_community");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/board/community");
        return mv;
    }

    @RequestMapping(value = "/mng/board/community/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CommunityDTO>> mng_board_community_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_board_community_selectList");
        //System.System.out.println(searchDTO.toString());

        List<CommunityDTO> responseList = eduMarineMngService.processSelectCommunityList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/community/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommunityDTO> mng_board_community_selectSingle(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineMngController > mng_board_community_selectSingle");
        //System.System.out.println(searchDTO.toString());

        CommunityDTO response = eduMarineMngService.processSelectCommunitySingle(communityDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/community/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_community_delete(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineMngController > mng_board_community_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/community/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_board_community_detail(String seq) {
        System.out.println("EduMarineMngController > mng_board_community_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setSeq(seq);
            CommunityDTO info = eduMarineMngService.processSelectCommunitySingle(communityDTO);
            mv.addObject("info", info);

            // TODO: 댓글

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/board/community/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/community/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_community_update(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineMngController > mng_board_community_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateCommunity(communityDTO);

        // TODO: 댓글 업데이트 추가

        String fileIdList = communityDTO.getFileIdList();
        if(fileIdList != null && !"".equals(fileIdList)){
            String[] fileIdSplit = fileIdList.split(",");
            for(int i=0; i<fileIdSplit.length; i++){
                if(!"".equals(fileIdSplit[i])){
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileIdSplit[i]);
                    fileDTO.setUserId(communityDTO.getSeq());
                    ResponseDTO fileResponse = eduMarineMngService.processUpdateFileUserId(fileDTO);
                }
            }
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/community/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_community_insert(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineMngController > mng_center_board_community_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertCommunity(communityDTO);

        String fileIdList = communityDTO.getFileIdList();
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
        //System.System.out.println(searchDTO.toString());

        List<FaqDTO> responseList = eduMarineMngService.processSelectFaqList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<FaqDTO> mng_board_faq_selectSingle(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_board_faq_selectSingle");
        //System.System.out.println(searchDTO.toString());

        FaqDTO response = eduMarineMngService.processSelectFaqSingle(faqDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
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
        }

        mv.setViewName("/mng/board/faq/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/board/faq/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_faq_update(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_board_faq_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFaq(faqDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/faq/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_center_board_faq_insert(@RequestBody FaqDTO faqDTO) {
        System.out.println("EduMarineMngController > mng_center_board_faq_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertFaq(faqDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/popup.do", method = RequestMethod.GET)
    public ModelAndView mng_pop_popup() {
        System.out.println("EduMarineMngController > mng_pop_popup");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/pop/popup");
        return mv;
    }

    @RequestMapping(value = "/mng/pop/popup/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<PopupDTO>> mng_pop_popup_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_pop_popup_selectList");
        //System.System.out.println(searchDTO.toString());

        List<PopupDTO> responseList = eduMarineMngService.processSelectPopupList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/popup/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_pop_popup_detail(String seq) {
        System.out.println("EduMarineMngController > mng_pop_popup_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            PopupDTO popupDTO = new PopupDTO();
            popupDTO.setSeq(seq);
            PopupDTO info = eduMarineMngService.processSelectPopupSingle(popupDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/pop/popup/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/pop/popup/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_popup_delete(@RequestBody PopupDTO popupDTO) {
        System.out.println("EduMarineMngController > mng_pop_popup_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeletePopup(popupDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/popup/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_popup_update(@RequestBody PopupDTO popupDTO) {
        System.out.println("EduMarineMngController > mng_pop_popup_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePopup(popupDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/popup/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_popup_insert(@RequestBody PopupDTO popupDTO) {
        System.out.println("EduMarineMngController > mng_pop_popup_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertPopup(popupDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/banner.do", method = RequestMethod.GET)
    public ModelAndView mng_pop_banner() {
        System.out.println("EduMarineMngController > mng_pop_banner");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/pop/banner");
        return mv;
    }

    @RequestMapping(value = "/mng/pop/banner/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<BannerDTO>> mng_pop_banner_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_selectList");
        //System.System.out.println(searchDTO.toString());

        List<BannerDTO> responseList = eduMarineMngService.processSelectBannerList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/banner/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BannerDTO> mng_pop_banner_selectSingle(@RequestBody BannerDTO bannerDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_selectSingle");
        //System.System.out.println(searchDTO.toString());

        BannerDTO response = eduMarineMngService.processSelectBannerSingle(bannerDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/banner/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_pop_banner_detail(String seq) {
        System.out.println("EduMarineMngController > mng_pop_banner_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            BannerDTO bannerDTO = new BannerDTO();
            bannerDTO.setSeq(seq);
            BannerDTO info = eduMarineMngService.processSelectBannerSingle(bannerDTO);
            mv.addObject("info", info);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setUserId(info.getSeq());
            List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
            mv.addObject("fileList", fileList);
        }

        mv.setViewName("/mng/pop/banner/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/pop/banner/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_banner_delete(@RequestBody BannerDTO bannerDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteBanner(bannerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/banner/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_banner_update(@RequestBody BannerDTO bannerDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateBanner(bannerDTO);

        String fileIdList = bannerDTO.getFileIdList();
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

    @RequestMapping(value = "/mng/pop/banner/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_banner_insert(@RequestBody BannerDTO bannerDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertBanner(bannerDTO);

        String fileIdList = bannerDTO.getFileIdList();
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
    // customer Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/customer/member.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_member() {
        System.out.println("EduMarineMngController > mng_customer_member");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/customer/member");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/member/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<MemberDTO>> mng_customer_member_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_selectList");
        //System.System.out.println(searchDTO.toString());

        List<MemberDTO> responseList = eduMarineMngService.processSelectMemberList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/member/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MemberDTO> mng_customer_member_selectSingle(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_selectSingle");
        //System.System.out.println(searchDTO.toString());

        MemberDTO response = eduMarineMngService.processSelectMemberSingle(memberDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/member/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_member_delete(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/member/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_member_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_member_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setSeq(seq);
            MemberDTO info = eduMarineMngService.processSelectMemberSingle(memberDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/customer/member/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/member/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_member_update(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/member/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_member_insert(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/resume.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_resume() {
        System.out.println("EduMarineMngController > mng_customer_resume");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/customer/resume");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/resume/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<ResumeDTO>> mng_customer_resume_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_resume_selectList");
        //System.System.out.println(searchDTO.toString());

        List<ResumeDTO> responseList = eduMarineMngService.processSelectResumeList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/resume/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_resume_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_resume_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setSeq(seq);
            ResumeDTO info = eduMarineMngService.processSelectResumeSingle(resumeDTO);
            mv.addObject("info", info);

            if(info != null){
                /* 첨부파일 정보 Set */
                FileDTO fileDTO = new FileDTO();
                fileDTO.setUserId(info.getMemberSeq());
                List<FileDTO> bodyPhotoFile = eduMarineMngService.processSelectFileUserIdList(fileDTO);
                if(bodyPhotoFile != null  && !bodyPhotoFile.isEmpty()){
                    for (FileDTO fileInfo : bodyPhotoFile) {
                        if ("bodyPhoto".equals(fileInfo.getNote())) {
                            mv.addObject("bodyPhotoFile", fileInfo);
                        }
                    }
                }
            }
        }

        mv.setViewName("/mng/customer/resume/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/resume/detail/multi.do", method = RequestMethod.POST)
    public ModelAndView mng_customer_resume_detail_multi(String seqList) {
        System.out.println("EduMarineMngController > mng_customer_resume_detail_multi");
        ModelAndView mv = new ModelAndView();

        if(seqList != null && !"".equals(seqList)){

            List<ResumeDTO> resumeList = new ArrayList<>();
            String[] seqSplitArr = seqList.split(",");
            for(int i=0; i<seqSplitArr.length; i++){
                String seq = seqSplitArr[i];

                ResumeDTO resumeDTO = new ResumeDTO();
                resumeDTO.setSeq(seq);
                ResumeDTO info = eduMarineMngService.processSelectResumeSingle(resumeDTO);

                if(info != null){
                    /* 첨부파일 정보 Set */
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setUserId(info.getMemberSeq());
                    List<FileDTO> bodyPhotoFile = eduMarineMngService.processSelectFileUserIdList(fileDTO);
                    if(bodyPhotoFile != null  && !bodyPhotoFile.isEmpty()){
                        for (FileDTO fileInfo : bodyPhotoFile) {
                            if ("bodyPhoto".equals(fileInfo.getNote())) {
                                info.setBodyPhotoFileSrc(fileInfo.getFullFilePath());
                            }
                        }
                    }
                }

                resumeList.add(info);
            }

            mv.addObject("resumeList", resumeList);

        }

        mv.setViewName("/mng/customer/resume/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/regular.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_regular() {
        System.out.println("EduMarineMngController > mng_customer_regular");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/customer/regular");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/regular/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<RegularDTO>> mng_customer_regular_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_selectList");
        //System.System.out.println(searchDTO.toString());

        List<RegularDTO> responseList = eduMarineMngService.processSelectRegularList(searchDTO);
        for(int i=0; i<responseList.size(); i++){
            RegularDTO info = responseList.get(i);
            if(info.getMemberSeq() != null){
                List<RegularDTO.TrainInfo> trainInfoList = eduMarineMngService.processSelectRegularTrainInfoList(info);
                if(trainInfoList != null){
                    info.setTrainInfoList(trainInfoList);
                }
                responseList.set(i, info);
            }
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RegularDTO> mng_customer_regular_selectSingle(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_selectSingle");
        //System.System.out.println(searchDTO.toString());

        RegularDTO response = eduMarineMngService.processSelectRegularSingle(regularDTO);
        if(response.getMemberSeq() != null){
            List<RegularDTO.TrainInfo> trainInfoList = eduMarineMngService.processSelectRegularTrainInfoList(response);
            if(trainInfoList != null){
                response.setTrainInfoList(trainInfoList);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_delete(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_regular_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_regular_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            RegularDTO regularDTO = new RegularDTO();
            regularDTO.setSeq(seq);
            RegularDTO info = eduMarineMngService.processSelectRegularSingle(regularDTO);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);
            }
        }

        mv.setViewName("/mng/customer/regular/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/regular/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_update(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_insert(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_status_update(@RequestBody List<RegularDTO> regularList) {
        System.out.println("EduMarineMngController > mng_customer_regular_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRegularApplyStatus(regularList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_status_change_update(@RequestBody List<RegularDTO> regularList) {
        System.out.println("EduMarineMngController > mng_customer_regular_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRegularApplyStatusChange(regularList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/boarder.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_boarder(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_boarder");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/boarder");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/boarder/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_boarder_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_boarder_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            BoarderDTO info = eduMarineMngService.processSelectBoarderSingle(seq);

            if(info != null){
                mv.addObject("info", info);

                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                String boarderSeq = info.getSeq();

                List<CareerDTO> careerList = eduMarineMngService.processSelectCareerList(boarderSeq);
                mv.addObject("careerList", careerList);

                List<LicenseDTO> licenseList = eduMarineMngService.processSelectLicenseList(boarderSeq);
                mv.addObject("licenseList", licenseList);

                /* 첨부파일 정보 Set */
                if(memberInfo != null){

                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setUserId(memberInfo.getSeq());
                    List<FileDTO> careerLicenseList = new ArrayList<>();
                    List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
                    if(fileList != null && !fileList.isEmpty()){
                        for(FileDTO fileInfo : fileList){
                            String fileNote = fileInfo.getNote().replaceAll("[0-9]", "");
                            if("bodyPhoto".equals(fileNote)){
                                mv.addObject("bodyPhotoFileInfo", fileInfo);
                            }else if("gradeLicense".equals(fileNote)){
                                mv.addObject("gradeLicenseFileInfo", fileInfo);
                            }else if("careerLicense".equals(fileNote)){
                                careerLicenseList.add(fileInfo);
                            }
                        }
                        mv.addObject("careerLicenseFileList", careerLicenseList);
                    }

                }

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/boarder/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/boarder/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<BoarderDTO>> mng_customer_boarder_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_boarder_selectList");
        //System.System.out.println(searchDTO.toString());

        List<BoarderDTO> responseList = eduMarineMngService.processSelectBoarderList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/boarder/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_boarder_status_update(@RequestBody List<BoarderDTO> boarderList) {
        System.out.println("EduMarineMngController > mng_customer_boarder_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateBoarderApplyStatus(boarderList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/boarder/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_boarder_status_change_update(@RequestBody List<BoarderDTO> boarderList) {
        System.out.println("EduMarineMngController > mng_customer_boarder_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateBoarderApplyStatusChange(boarderList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/frp.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_frp(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_frp");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/frp");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/frp/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FrpDTO>> mng_customer_frp_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_frp_selectList");
        //System.System.out.println(searchDTO.toString());

        List<FrpDTO> responseList = eduMarineMngService.processSelectFrpList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/frp/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_frp_status_update(@RequestBody List<FrpDTO> frpList) {
        System.out.println("EduMarineMngController > mng_customer_frp_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFrpApplyStatus(frpList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/frp/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_frp_status_change_update(@RequestBody List<FrpDTO> frpList) {
        System.out.println("EduMarineMngController > mng_customer_frp_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFrpApplyStatusChange(frpList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/frp/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_frp_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_frp_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            FrpDTO info = eduMarineMngService.processSelectFrpSingle(seq);
            mv.addObject("info", info);

            if(info != null){

                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                String frpSeq = info.getSeq();

                List<CareerDTO> careerList = eduMarineMngService.processSelectCareerList(frpSeq);
                mv.addObject("careerList", careerList);

                List<LicenseDTO> licenseList = eduMarineMngService.processSelectLicenseList(frpSeq);
                mv.addObject("licenseList", licenseList);

                /* 첨부파일 정보 Set */
                if(memberInfo != null) {
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setUserId(memberInfo.getSeq());
                    List<FileDTO> careerLicenseList = new ArrayList<>();
                    List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);
                    if (fileList != null && !fileList.isEmpty()) {
                        for (FileDTO fileInfo : fileList) {
                            String fileNote = fileInfo.getNote().replaceAll("[0-9]", "");
                            if ("bodyPhoto".equals(fileNote)) {
                                mv.addObject("bodyPhotoFileInfo", fileInfo);
                            } else if ("gradeLicense".equals(fileNote)) {
                                mv.addObject("gradeLicenseFileInfo", fileInfo);
                            } else if ("careerLicense".equals(fileNote)) {
                                careerLicenseList.add(fileInfo);
                            }
                        }
                        mv.addObject("careerLicenseFileList", careerLicenseList);
                    }
                }

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/frp/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/basic.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_basic(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_basic");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/basic");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/basic/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<BasicDTO>> mng_customer_basic_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_basic_selectList");
        //System.System.out.println(searchDTO.toString());

        List<BasicDTO> responseList = eduMarineMngService.processSelectBasicList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/basic/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_basic_status_update(@RequestBody List<BasicDTO> basicList) {
        System.out.println("EduMarineMngController > mng_customer_basic_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateBasicApplyStatus(basicList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/basic/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_basic_status_change_update(@RequestBody List<BasicDTO> basicList) {
        System.out.println("EduMarineMngController > mng_customer_basic_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateBasicApplyStatusChange(basicList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/basic/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_basic_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_basic_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            BasicDTO info = eduMarineMngService.processSelectBasicSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/basic/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/emergency.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_emergency(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_emergency");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/emergency");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/emergency/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<EmergencyDTO>> mng_customer_emergency_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_emergency_selectList");
        //System.System.out.println(searchDTO.toString());

        List<EmergencyDTO> responseList = eduMarineMngService.processSelectEmergencyList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/emergency/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_emergency_status_update(@RequestBody List<EmergencyDTO> emergencyList) {
        System.out.println("EduMarineMngController > mng_customer_emergency_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateEmergencyApplyStatus(emergencyList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/emergency/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_emergency_status_change_update(@RequestBody List<EmergencyDTO> emergencyList) {
        System.out.println("EduMarineMngController > mng_customer_emergency_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateEmergencyApplyStatusChange(emergencyList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/emergency/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_emergency_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_emergency_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            EmergencyDTO info = eduMarineMngService.processSelectEmergencySingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/emergency/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/outboarder.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_outboarder(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_outboarder");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/outboarder");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/outboarder/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<OutboarderDTO>> mng_customer_outboarder_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_outboarder_selectList");
        //System.System.out.println(searchDTO.toString());

        List<OutboarderDTO> responseList = eduMarineMngService.processSelectOutboarderList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/outboarder/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_outboarder_status_update(@RequestBody List<OutboarderDTO> outboarderList) {
        System.out.println("EduMarineMngController > mng_customer_outboarder_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateOutboarderApplyStatus(outboarderList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/outboarder/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_outboarder_status_change_update(@RequestBody List<OutboarderDTO> outboarderList) {
        System.out.println("EduMarineMngController > mng_customer_outboarder_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateOutboarderApplyStatusChange(outboarderList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/outboarder/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_outboarder_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_outboarder_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !seq.isEmpty()){
            OutboarderDTO info = eduMarineMngService.processSelectOutboarderSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/outboarder/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/inboarder.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_inboarder(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_inboarder");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/inboarder");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/inboarder/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<InboarderDTO>> mng_customer_inboarder_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_selectList");
        //System.System.out.println(searchDTO.toString());

        List<InboarderDTO> responseList = eduMarineMngService.processSelectInboarderList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InboarderDTO> mng_customer_inboarder_selectSingle(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_selectSingle");
        //System.System.out.println(searchDTO.toString());

        InboarderDTO response = eduMarineMngService.processSelectInboarderSingle(inboarderDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_delete(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_inboarder_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !seq.isEmpty()){
            InboarderDTO inboarderDTO = new InboarderDTO();
            inboarderDTO.setSeq(seq);
            InboarderDTO info = eduMarineMngService.processSelectInboarderSingle(inboarderDTO);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/inboarder/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/inboarder/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_update(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_insert(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_status_update(@RequestBody List<InboarderDTO> inboarderList) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateInboarderApplyStatus(inboarderList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_status_change_update(@RequestBody List<InboarderDTO> inboarderList) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateInboarderApplyStatusChange(inboarderList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sailyacht.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_sailyacht(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_sailyacht");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/sailyacht");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/sailyacht/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SailyachtDTO>> mng_customer_sailyacht_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_sailyacht_selectList");
        //System.System.out.println(searchDTO.toString());

        List<SailyachtDTO> responseList = eduMarineMngService.processSelectSailyachtList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sailyacht/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_sailyacht_status_update(@RequestBody List<SailyachtDTO> sailyachtList) {
        System.out.println("EduMarineMngController > mng_customer_sailyacht_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSailyachtApplyStatus(sailyachtList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sailyacht/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_sailyacht_status_change_update(@RequestBody List<SailyachtDTO> sailyachtList) {
        System.out.println("EduMarineMngController > mng_customer_sailyacht_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSailyachtApplyStatusChange(sailyachtList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sailyacht/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_sailyacht_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_sailyacht_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !seq.isEmpty()){
            SailyachtDTO info = eduMarineMngService.processSelectSailyachtSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/sailyacht/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/highhorsepower.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_highhorsepower(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_highhorsepower");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/highhorsepower");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/highhorsepower/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<HighHorsePowerDTO>> mng_customer_highhorsepower_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_sailyacht_selectList");
        //System.System.out.println(searchDTO.toString());

        List<HighHorsePowerDTO> responseList = eduMarineMngService.processSelectHighhorsepowerList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highhorsepower/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_highhorsepower_status_update(@RequestBody List<HighHorsePowerDTO> highHorsePowerList) {
        System.out.println("EduMarineMngController > mng_customer_highhorsepower_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateHighhorsepowerApplyStatus(highHorsePowerList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highhorsepower/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_highhorsepower_status_change_update(@RequestBody List<HighHorsePowerDTO> highHorsePowerList) {
        System.out.println("EduMarineMngController > mng_customer_highhorsepower_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateHighhorsepowerApplyStatusChange(highHorsePowerList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highhorsepower/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_highhorsepower_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_highhorsepower_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            HighHorsePowerDTO info = eduMarineMngService.processSelectHighhorsepowerSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/highhorsepower/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/highself.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_highself(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_highself");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/highself");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/highself/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<HighSelfDTO>> mng_customer_highself_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_highself_selectList");
        //System.System.out.println(searchDTO.toString());

        List<HighSelfDTO> responseList = eduMarineMngService.processSelectHighSelfList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highself/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_highself_status_update(@RequestBody List<HighSelfDTO> highSelfList) {
        System.out.println("EduMarineMngController > mng_customer_highself_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateHighSelfApplyStatus(highSelfList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highself/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_highself_status_change_update(@RequestBody List<HighSelfDTO> highSelfList) {
        System.out.println("EduMarineMngController > mng_customer_highself_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateHighSelfApplyStatusChange(highSelfList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highself/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_highself_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_highself_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            HighSelfDTO info = eduMarineMngService.processSelectHighSelfSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/highself/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/highspecial.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_highspecial(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_highspecial");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/highspecial");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/highspecial/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<HighSpecialDTO>> mng_customer_highspecial_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_highspecial_selectList");
        //System.System.out.println(searchDTO.toString());

        List<HighSpecialDTO> responseList = eduMarineMngService.processSelectHighSpecialList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highspecial/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_highspecial_status_update(@RequestBody List<HighSpecialDTO> highSpecialList) {
        System.out.println("EduMarineMngController > mng_customer_highspecial_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateHighSpecialApplyStatus(highSpecialList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highspecial/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_highspecial_status_change_update(@RequestBody List<HighSpecialDTO> highSpecialList) {
        System.out.println("EduMarineMngController > mng_customer_highspecial_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateHighSpecialApplyStatusChange(highSpecialList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/highspecial/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_highspecial_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_highspecial_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !seq.isEmpty()){
            HighSpecialDTO info = eduMarineMngService.processSelectHighSpecialSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/highspecial/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/sterndrive.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_sterndrive(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_sterndrive");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/sterndrive");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/sterndrive/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SterndriveDTO>> mng_customer_sterndrive_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_sterndrive_selectList");
        //System.System.out.println(searchDTO.toString());

        List<SterndriveDTO> responseList = eduMarineMngService.processSelectSterndriveList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sterndrive/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_sterndrive_status_update(@RequestBody List<SterndriveDTO> sterndriveList) {
        System.out.println("EduMarineMngController > mng_customer_sterndrive_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSterndriveApplyStatus(sterndriveList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sterndrive/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_sterndrive_status_change_update(@RequestBody List<SterndriveDTO> sterndriveList) {
        System.out.println("EduMarineMngController > mng_customer_sterndrive_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSterndriveApplyStatusChange(sterndriveList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sterndrive/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_sterndrive_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_sterndrive_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            SterndriveDTO info = eduMarineMngService.processSelectSterndriveSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/sterndrive/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/sternspecial.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_sternspecial(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_sternspecial");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/sternspecial");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/sternspecial/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SternSpecialDTO>> mng_customer_sternspecial_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_sternspecial_selectList");
        //System.System.out.println(searchDTO.toString());

        List<SternSpecialDTO> responseList = eduMarineMngService.processSelectSternSpecialList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sternspecial/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_sternspecial_status_update(@RequestBody List<SternSpecialDTO> sternspecialList) {
        System.out.println("EduMarineMngController > mng_customer_sternspecial_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSternSpecialApplyStatus(sternspecialList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sternspecial/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_sternspecial_status_change_update(@RequestBody List<SternSpecialDTO> sternspecialList) {
        System.out.println("EduMarineMngController > mng_customer_sternspecial_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSternSpecialApplyStatusChange(sternspecialList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/sternspecial/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_sternspecial_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_sternspecial_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !seq.isEmpty()){
            SternSpecialDTO info = eduMarineMngService.processSelectSternSpecialSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/sternspecial/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/generator.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_generator(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_generator");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/generator");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/generator/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<GeneratorDTO>> mng_customer_generator_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_generator_selectList");
        //System.System.out.println(searchDTO.toString());

        List<GeneratorDTO> responseList = eduMarineMngService.processSelectGeneratorList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/generator/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_generator_status_update(@RequestBody List<GeneratorDTO> generatorList) {
        System.out.println("EduMarineMngController > mng_customer_generator_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateGeneratorApplyStatus(generatorList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/generator/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_generator_status_change_update(@RequestBody List<GeneratorDTO> generatorList) {
        System.out.println("EduMarineMngController > mng_customer_generator_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateGeneratorApplyStatusChange(generatorList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/generator/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_generator_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_generator_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !seq.isEmpty()){
            GeneratorDTO info = eduMarineMngService.processSelectGeneratorSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/generator/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/competency.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_competency(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_competency");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/competency");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/competency/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CompetencyDTO>> mng_customer_competency_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_competency_selectList");
        //System.System.out.println(searchDTO.toString());

        List<CompetencyDTO> responseList = eduMarineMngService.processSelectCompetencyList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/competency/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_competency_status_update(@RequestBody List<CompetencyDTO> competencyList) {
        System.out.println("EduMarineMngController > mng_customer_competency_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateCompetencyApplyStatus(competencyList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/competency/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_competency_status_change_update(@RequestBody List<CompetencyDTO> competencyList) {
        System.out.println("EduMarineMngController > mng_customer_competency_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateCompetencyApplyStatusChange(competencyList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/competency/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_competency_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_competency_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            CompetencyDTO info = eduMarineMngService.processSelectCompetencySingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/competency/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/famtourin.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_famtourin(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_famtourin");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/famtourin");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/famtourin/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FamtourinDTO>> mng_customer_famtourin_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_famtourin_selectList");
        //System.System.out.println(searchDTO.toString());

        List<FamtourinDTO> responseList = eduMarineMngService.processSelectFamtourinList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/famtourin/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_famtourin_status_update(@RequestBody List<FamtourinDTO> famtourinList) {
        System.out.println("EduMarineMngController > mng_customer_famtourin_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFamtourinApplyStatus(famtourinList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/famtourin/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_famtourin_status_change_update(@RequestBody List<FamtourinDTO> famtourinList) {
        System.out.println("EduMarineMngController > mng_customer_famtourin_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFamtourinApplyStatusChange(famtourinList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/famtourin/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_famtourin_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_famtourin_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            FamtourinDTO info = eduMarineMngService.processSelectFamtourinSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);
            }
        }

        mv.setViewName("/mng/customer/famtourin/detail");
        return mv;
    }
    
    @RequestMapping(value = "/mng/customer/famtourout.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_famtourout(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_famtourout");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/famtourout");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/famtourout/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FamtouroutDTO>> mng_customer_famtourout_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_famtourout_selectList");
        //System.System.out.println(searchDTO.toString());
        List<FamtouroutDTO> responseList = eduMarineMngService.processSelectFamtouroutList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/famtourout/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_famtourout_status_update(@RequestBody List<FamtouroutDTO> famtouroutList) {
        System.out.println("EduMarineMngController > mng_customer_famtourout_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFamtouroutApplyStatus(famtouroutList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/famtourout/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_famtourout_status_change_update(@RequestBody List<FamtouroutDTO> famtouroutList) {
        System.out.println("EduMarineMngController > mng_customer_famtourout_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateFamtouroutApplyStatusChange(famtouroutList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/famtourout/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_famtourout_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_famtourout_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            FamtouroutDTO info = eduMarineMngService.processSelectFamtouroutSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/famtourout/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/electro.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_electro(String nextTime) {
        System.out.println("EduMarineMngController > mng_customer_electro");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/customer/electro");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/electro/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<ElectroDTO>> mng_customer_electro_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_electro_selectList");
        //System.System.out.println(searchDTO.toString());
        List<ElectroDTO> responseList = eduMarineMngService.processSelectElectroList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/electro/status/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_electro_status_update(@RequestBody List<ElectroDTO> electroList) {
        System.out.println("EduMarineMngController > mng_customer_electro_status_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateElectroApplyStatus(electroList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/electro/status/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_electro_status_change_update(@RequestBody List<ElectroDTO> electroList) {
        System.out.println("EduMarineMngController > mng_customer_electro_status_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateElectroApplyStatusChange(electroList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/electro/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_electro_detail(String seq) {
        System.out.println("EduMarineMngController > mng_customer_electro_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            ElectroDTO info = eduMarineMngService.processSelectElectroSingle(seq);
            mv.addObject("info", info);

            if(info != null){
                MemberDTO reqMemberDTO = new MemberDTO();
                reqMemberDTO.setSeq(info.getMemberSeq());
                MemberDTO memberInfo = eduMarineMngService.processSelectMemberSingle(reqMemberDTO);
                mv.addObject("memberInfo", memberInfo);

                /* 결제 정보 */
                PaymentDTO paymentRequestDTO = new PaymentDTO();
                paymentRequestDTO.setMemberSeq(info.getMemberSeq());
                paymentRequestDTO.setTrainSeq(info.getTrainSeq());
                paymentRequestDTO.setTableSeq(info.getSeq());
                PaymentDTO paymentInfo = eduMarineMngService.processSelectTrainPaymentInfo(paymentRequestDTO);
                mv.addObject("paymentInfo", paymentInfo);
            }
        }

        mv.setViewName("/mng/customer/electro/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/education/train.do", method = RequestMethod.GET)
    public ModelAndView mng_education_train(String nextTime) {
        System.out.println("EduMarineMngController > mng_education_train");
        ModelAndView mv = new ModelAndView();
        mv.addObject("nextTime", nextTime);
        mv.setViewName("/mng/education/train");
        return mv;
    }

    @RequestMapping(value = "/mng/education/train/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> mng_education_train_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_education_train_selectList");
        //System.System.out.println(searchDTO.toString());

        List<TrainDTO> responseList = eduMarineMngService.processSelectTrainList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TrainDTO> mng_education_train_selectSingle(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_selectSingle");
        //System.System.out.println(searchDTO.toString());

        TrainDTO response = eduMarineMngService.processSelectTrainSingle(trainDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_education_train_detail(String seq) {
        System.out.println("EduMarineMngController > mng_education_train_detail");
        ModelAndView mv = new ModelAndView();

        if (seq != null && !seq.isEmpty()) {
            TrainDTO trainDTO = new TrainDTO();
            trainDTO.setSeq(seq);
            TrainDTO info = eduMarineMngService.processSelectTrainSingle(trainDTO);
            mv.addObject("info", info);
        } else {
            // --- [신규 추가] ---
            // 신규 교육 생성 시 기본값 설정
            TrainDTO newTrain = new TrainDTO();
            newTrain.setApplicationSystemType("UNIFIED"); // 스위치를 '신규'로
            newTrain.setExposureYn("Y");
            newTrain.setScheduleExposureYn("Y");
            mv.addObject("info", newTrain);
            // --- [여기까지] ---
        }

        mv.setViewName("/mng/education/train/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/education/train/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_train_delete(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteTrain(trainDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_train_update(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateTrain(trainDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_train_insert(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertTrain(trainDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/earlyClosingYn.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_train_earlyClosing(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_earlyClosing");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateTrainEarlyClosing(trainDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/template.do", method = RequestMethod.GET)
    public ModelAndView mng_education_train_template(String tapName) {
        System.out.println("EduMarineMngController > mng_education_train_template");
        ModelAndView mv = new ModelAndView();
        mv.addObject("tapName", tapName);

        //전체 교육 일정
        /*List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateAllInfoList = eduMarineMngService.processSelectTrainTemplateList("all");
        if(trainTemplateAllInfoList != null){

        }*/


        // 마리나
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateMarinaInfoList = eduMarineMngService.processSelectTrainTemplateList("marina");
        if(trainTemplateMarinaInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> daysList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> timeList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> placeList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> placeDetailList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> personsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> payList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> recruitPeriodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> completeConditionList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateMarinaInfoList){
                if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    daysList.add(info);
                }else if(info.getSmall().equals("time")){
                    timeList.add(info);
                }else if(info.getSmall().equals("place")){
                    placeList.add(info);
                }else if(info.getSmall().equals("placeDetail")){
                    placeDetailList.add(info);
                }else if(info.getSmall().equals("persons")){
                    personsList.add(info);
                }else if(info.getSmall().equals("pay")){
                    payList.add(info);
                }else if(info.getSmall().equals("right")){
                    mv.addObject("m_right", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("m_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("m_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("m_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    recruitPeriodList.add(info);
                }else if(info.getSmall().equals("completeCondition")){
                    completeConditionList.add(info);
                }

            } //for

            mv.addObject("m_contentsList", contentsList);
            mv.addObject("m_periodList", periodList);
            mv.addObject("m_daysList", daysList);
            mv.addObject("m_timeList", timeList);
            mv.addObject("m_placeList", placeList);
            mv.addObject("m_placeDetailList", placeDetailList);
            mv.addObject("m_personsList", personsList);
            mv.addObject("m_payList", payList);
            mv.addObject("m_recruitPeriodList", recruitPeriodList);
            mv.addObject("m_completeConditionList", completeConditionList);

        } //마리나

        //위탁교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateCommissionInfoList = eduMarineMngService.processSelectTrainTemplateList("commission");
        if(trainTemplateCommissionInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> placeList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateCommissionInfoList) {
                if (info.getSmall().equals("target")) {
                    targetList.add(info);
                } else if (info.getSmall().equals("contents")) {
                    contentsList.add(info);
                } else if (info.getSmall().equals("period")) {
                    periodList.add(info);
                } else if (info.getSmall().equals("place")) {
                    placeList.add(info);
                }
            }

            mv.addObject("c_targetList", targetList);
            mv.addObject("c_contentsList", contentsList);
            mv.addObject("c_periodList", periodList);
            mv.addObject("c_placeList", placeList);
            
        } //위탁교육

        //선외기
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateOutboarderInfoList = eduMarineMngService.processSelectTrainTemplateList("outboarder");
        if(trainTemplateOutboarderInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateOutboarderInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("o_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("o_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("o_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("o_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("o_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("o_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("o_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("o_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("o_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("o_recruitPeriod", info);
                }

            } //for

            mv.addObject("o_targetList", targetList);
            mv.addObject("o_contentsList", contentsList);
            mv.addObject("o_periodList", periodList);

        } //선외기

        //선내기
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateInboarderInfoList = eduMarineMngService.processSelectTrainTemplateList("inboarder");
        if(trainTemplateInboarderInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateInboarderInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("i_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("i_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("i_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("i_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("i_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("i_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("i_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("i_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("i_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("i_recruitPeriod", info);
                }

            } //for

            mv.addObject("i_targetList", targetList);
            mv.addObject("i_contentsList", contentsList);
            mv.addObject("i_periodList", periodList);

        } //선내기

        //세일요트
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateSailyachtInfoList = eduMarineMngService.processSelectTrainTemplateList("sailyacht");
        if(trainTemplateSailyachtInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateSailyachtInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("s_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("s_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("s_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("s_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("s_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("s_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("s_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("s_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("s_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("s_recruitPeriod", info);
                }

            } //for

            mv.addObject("s_targetList", targetList);
            mv.addObject("s_contentsList", contentsList);
            mv.addObject("s_periodList", periodList);

        } //세일요트

        //고마력
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateHighHorsePowerInfoList = eduMarineMngService.processSelectTrainTemplateList("highhorsepower");
        if(trainTemplateHighHorsePowerInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> stuffList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateHighHorsePowerInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("h_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("h_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("h_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("h_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("h_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("h_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("h_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("h_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("h_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("h_recruitPeriod", info);
                }else if(info.getSmall().equals("stuff")){
                    stuffList.add(info);
                }

            } //for

            mv.addObject("h_targetList", targetList);
            mv.addObject("h_contentsList", contentsList);
            mv.addObject("h_periodList", periodList);
            mv.addObject("h_stuffList", stuffList);

        } //고마력

        //고마력 자가정비
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateHighselfInfoList = eduMarineMngService.processSelectTrainTemplateList("highself");
        if(trainTemplateHighselfInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> stuffList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateHighselfInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("e_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("e_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("e_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("e_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("e_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("e_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("e_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("e_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("e_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("e_recruitPeriod", info);
                }else if(info.getSmall().equals("stuff")){
                    stuffList.add(info);
                }

            } //for

            mv.addObject("e_targetList", targetList);
            mv.addObject("e_contentsList", contentsList);
            mv.addObject("e_periodList", periodList);
            mv.addObject("e_stuffList", stuffList);

        } //고마력

        //Sterndrive
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateSterndriveInfoList = eduMarineMngService.processSelectTrainTemplateList("sterndrive");
        if(trainTemplateSterndriveInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> stuffList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateSterndriveInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("t_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("t_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("t_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("t_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("t_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("t_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("t_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("t_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("t_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("t_recruitPeriod", info);
                }else if(info.getSmall().equals("stuff")){
                    stuffList.add(info);
                }

            } //for

            mv.addObject("t_targetList", targetList);
            mv.addObject("t_contentsList", contentsList);
            mv.addObject("t_periodList", periodList);
            mv.addObject("t_stuffList", stuffList);

        } //Sterndrive

        //기초정비교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateBasicInfoList = eduMarineMngService.processSelectTrainTemplateList("basic");
        if(trainTemplateBasicInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> placeList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> stuffList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateBasicInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("place")){
                    placeList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("b_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("b_time", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("b_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("b_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("b_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("b_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("b_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("b_recruitPeriod", info);
                }else if(info.getSmall().equals("stuff")){
                    stuffList.add(info);
                }

            } //for

            mv.addObject("b_targetList", targetList);
            mv.addObject("b_contentsList", contentsList);
            mv.addObject("b_periodList", periodList);
            mv.addObject("b_placeList", placeList);
            mv.addObject("b_stuffList", stuffList);

        } //기초정비교육

        //응급조치교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateEmergencyInfoList = eduMarineMngService.processSelectTrainTemplateList("emergency");
        if(trainTemplateEmergencyInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> placeList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> stuffList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateEmergencyInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("place")){
                    placeList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("y_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("y_time", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("y_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("y_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("y_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("y_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("y_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("y_recruitPeriod", info);
                }else if(info.getSmall().equals("stuff")){
                    stuffList.add(info);
                }

            } //for

            mv.addObject("y_targetList", targetList);
            mv.addObject("y_contentsList", contentsList);
            mv.addObject("y_periodList", periodList);
            mv.addObject("y_placeList", placeList);
            mv.addObject("y_stuffList", stuffList);

        } //응급조치교육
        
        //발전기 정비 교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateGeneratorInfoList = eduMarineMngService.processSelectTrainTemplateList("generator");
        if(trainTemplateGeneratorInfoList != null){
            List<TrainTemplateDTO.TrainTemplateInfo> targetList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> contentsList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> periodList = new ArrayList<>();
            List<TrainTemplateDTO.TrainTemplateInfo> stuffList = new ArrayList<>();

            for(TrainTemplateDTO.TrainTemplateInfo info: trainTemplateGeneratorInfoList){
                if(info.getSmall().equals("target")){
                    targetList.add(info);
                }else if(info.getSmall().equals("contents")){
                    contentsList.add(info);
                }else if(info.getSmall().equals("period")){
                    periodList.add(info);
                }else if(info.getSmall().equals("days")){
                    mv.addObject("g_days", info);
                }else if(info.getSmall().equals("time")){
                    mv.addObject("g_time", info);
                }else if(info.getSmall().equals("place")){
                    mv.addObject("g_place", info);
                }else if(info.getSmall().equals("placeDetail")){
                    mv.addObject("g_placeDetail", info);
                }else if(info.getSmall().equals("persons")){
                    mv.addObject("g_persons", info);
                }else if(info.getSmall().equals("pay")){
                    mv.addObject("g_pay", info);
                }else if(info.getSmall().equals("applyMethod")){
                    mv.addObject("g_applyMethod", info);
                }else if(info.getSmall().equals("applyMethodUrl")){
                    mv.addObject("g_applyMethodUrl", info);
                }else if(info.getSmall().equals("recruitMethod")){
                    mv.addObject("g_recruitMethod", info);
                }else if(info.getSmall().equals("recruitPeriod")){
                    mv.addObject("g_recruitPeriod", info);
                }else if(info.getSmall().equals("stuff")){
                    stuffList.add(info);
                }

            } //for

            mv.addObject("g_targetList", targetList);
            mv.addObject("g_contentsList", contentsList);
            mv.addObject("g_periodList", periodList);
            mv.addObject("g_stuffList", stuffList);

        } //발전기 정비 교육

        mv.setViewName("/mng/education/template");
        return mv;
    }

    @RequestMapping(value = "/mng/education/template/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_train_template_save(@RequestBody TrainTemplateDTO templateInfo) {
        System.out.println("EduMarineMngController > mng_education_train_template_save");

        ResponseDTO responseDTO = eduMarineMngService.processSaveTrainTemplate(templateInfo);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment.do", method = RequestMethod.GET)
    public ModelAndView mng_education_payment() {
        System.out.println("EduMarineMngController > mng_education_payment");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/education/payment");
        return mv;
    }

    @RequestMapping(value = "/mng/education/payment/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<PaymentDTO>> mng_education_payment_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_selectList");
        //System.System.out.println(searchDTO.toString());

        List<PaymentDTO> responseList = eduMarineMngService.processSelectPaymentList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PaymentDTO> mng_education_payment_selectSingle(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_selectSingle");
        //System.System.out.println(searchDTO.toString());

        PaymentDTO response = eduMarineMngService.processSelectPaymentSingle(paymentDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_education_payment_detail(String seq) {
        System.out.println("EduMarineMngController > mng_education_payment_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setSeq(seq);
            PaymentDTO info = eduMarineMngService.processSelectPaymentSingle(paymentDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/education/payment/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/education/payment/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_payment_delete(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeletePayment(paymentDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_payment_update(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePayment(paymentDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_payment_insert(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertPayment(paymentDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/updatePayStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_payment_updatePayStatus(@RequestBody List<PaymentDTO> paymentList) {
        System.out.println("EduMarineMngController > mng_education_payment_updatePayStatus");

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePayStatus(paymentList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/newsletter/subscriber.do", method = RequestMethod.GET)
    public ModelAndView mng_newsletter_subscriber() {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/newsletter/subscriber");
        return mv;
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SubscriberDTO>> mng_newsletter_subscriber_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber_selectList");
        //System.System.out.println(searchDTO.toString());

        List<SubscriberDTO> responseList = eduMarineMngService.processSelectSubscriberList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_newsletter_subscriber_detail(String seq) {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            SubscriberDTO subscriberDTO = new SubscriberDTO();
            subscriberDTO.setSeq(seq);
            SubscriberDTO info = eduMarineMngService.processSelectSubscriberSingle(subscriberDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/newsletter/subscriber/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_newsletter_subscriber_delete(@RequestBody SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteSubscriber(subscriberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_newsletter_subscriber_update(@RequestBody SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSubscriber(subscriberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/checkDuplicate.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> member_seq_selectSingle(@RequestBody SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineController > job_community_selectList");

        Integer responseInfo = eduMarineMngService.processCheckSubscriber(subscriberDTO);

        return new ResponseEntity<>(responseInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_newsletter_subscriber_insert(@RequestBody SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertSubscriber(subscriberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms.do", method = RequestMethod.GET)
    public ModelAndView mng_smsMng_sms() {
        System.out.println("EduMarineMngController > mng_smsMng_sms");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/smsMng/sms");
        return mv;
    }

    @RequestMapping(value = "/mng/smsMng/sms/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SmsDTO>> mng_smsMng_sms_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_selectList");
        //System.System.out.println(searchDTO.toString());

        List<SmsDTO> responseList = eduMarineMngService.processSelectSmsList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/send/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<SmsSendDTO>> mng_smsMng_sms_send_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_selectList");
        //System.System.out.println(searchDTO.toString());

        List<SmsSendDTO> responseList = eduMarineMngService.processSelectSmsSendList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_smsMng_sms_detail(String seq) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            SmsDTO smsDTO = new SmsDTO();
            smsDTO.setSeq(seq);
            SmsDTO info = eduMarineMngService.processSelectSmsSingle(smsDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/smsMng/sms/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/smsMng/sms/send.do", method = RequestMethod.GET)
    public ModelAndView mng_smsMng_sms_send() {
        System.out.println("EduMarineMngController > mng_smsMng_sms_send");
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/mng/smsMng/sms/send");
        return mv;
    }

    @RequestMapping(value = "/mng/smsMng/sms/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_smsMng_sms_insert(@RequestBody SmsDTO smsDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertSms(smsDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_smsMng_sms_delete(@RequestBody SmsDTO smsDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteSms(smsDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/template/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TemplateDTO>> mng_smsMng_sms_template_selectList() {
        System.out.println("EduMarineMngController > mng_smsMng_sms_template_selectList");
        //System.System.out.println(searchDTO.toString());

        List<TemplateDTO> responseList = eduMarineMngService.processSelectSmsTemplateList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/template/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TemplateDTO> mng_smsMng_sms_template_selectList(@RequestBody TemplateDTO templateDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_template_selectList");
        //System.System.out.println(searchDTO.toString());

        TemplateDTO response = eduMarineMngService.processSelectSmsTemplateSingle(templateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/template/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_smsMng_sms_template_save(@RequestBody TemplateDTO templateDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_template_save");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processSaveSmsTemplate(templateDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/template/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_smsMng_sms_template_delete(@RequestBody TemplateDTO templateDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_template_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteSmsTemplate(templateDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/mng/smsMng/sms/sendBulk.do")
    @ResponseBody
    public Map<String, Object> sendBulkSms(@RequestBody SmsBulkRequestDto requestDto) {

        Map<String, Object> response = new HashMap<>();

        try {
            // 세션에서 현재 관리자 ID 가져오기 (로그용)
            // (세션 키 이름이 "id"가 아니면 실제 키로 변경해주세요)
            String adminId = "관리자"; // 세션이 없을 경우 대비

            String smsGroup = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

            for (String phone : requestDto.getPhoneList()) {

                // 1. DTO 준비 (발송용)
                SmsDTO smsSendDto = new SmsDTO();
                smsSendDto.setSender(requestDto.getSender());
                smsSendDto.setPhone(phone);
                smsSendDto.setContent(requestDto.getContent());
                // (SmsDTO에 필요한 다른 값이 있다면 requestDto에서 추가로 매핑)

                String sendResult = "성공";

                try {
                    /**************************************************************
                     * 1. 실제 SMS 발송 (기존 /sms/send.do의 서비스 호출)
                     **************************************************************/
                    SmsResponseDTO apiResponse = commService.smsSend(smsSendDto);

                    // (주의) SmsResponseDTO의 실제 성공/실패 확인 로직으로 변경해야 합니다.
                    //       sms.js가 'result_code != 1'을 체크하므로, 여기서는 1이 아니면 실패로 간주합니다.
                    if (apiResponse == null || apiResponse.getResult_code() != 1) {
                        sendResult = "실패" + (apiResponse != null ? " [" + apiResponse.getMessage() + "]" : " [응답없음]");
                    }

                } catch (Exception e) {
                    // e.printStackTrace(); // SLF4J 로그 사용 권장
                    sendResult = "실패 [발송 예외: " + e.getMessage() + "]";
                }

                try {
                    /**************************************************************
                     * 2. 발송 결과 DB에 저장 (기존 /mng/smsMng/sms/insert.do의 서비스 호출)
                     **************************************************************/
                    SmsDTO logSmsDto = new SmsDTO();
                    logSmsDto.setSmsGroup(smsGroup);
                    logSmsDto.setPhone(phone);
                    logSmsDto.setSender(adminId); // 보낸 관리자 ID
                    logSmsDto.setSenderPhone(requestDto.getSender()); // 발신번호
                    logSmsDto.setContent(requestDto.getContent());
                    logSmsDto.setSendResult(sendResult);
                    logSmsDto.setTemplateSeq(requestDto.getTemplateSeq());

                    eduMarineMngService.processInsertSms(logSmsDto);

                } catch (Exception dbE) {
                    // DB 저장 실패 로그 (치명적)
                    // dbE.printStackTrace(); // SLF4J 로그 사용 권장
                    System.err.println("FATAL: Failed to log SMS for " + phone + ": " + dbE.getMessage());
                }

                try {
                    // [권장] SMS API 제공업체의 초당 전송량(Rate Limit)을 준수하기 위해
                    // 루프 사이에 반드시 지연(Sleep)을 줍니다. (예: 0.1초)
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // 인터럽트 발생 시 스레드 종료
                    break;
                }
            } // end for

            System.out.println("SMS 대량 발송 작업 완료 (그룹 ID: " + smsGroup + ")");

            // 클라이언트에게는 작업이 등록되었음을 즉시 알립니다.
            response.put("resultCode", "0");
            response.put("message", "서버에 발송 작업이 등록되었습니다.");
            response.put("result_code", 1); // sms.js의 성공 조건

        } catch (Exception e) {
            // e.printStackTrace(); // 실제 운영에서는 SLF4J 로그를 남겨야 합니다.
            System.err.println("sendBulkSms Error: " + e.getMessage());
            response.put("resultCode", "-1");
            response.put("message", "발송 작업 등록에 실패했습니다: " + e.getMessage());
            response.put("result_code", -1); // sms.js의 실패 조건
        }

        return response;
    }

    @RequestMapping(value = "/mng/file/download.do", method = RequestMethod.GET)
    public ModelAndView mng_file_download() {
        System.out.println("EduMarineMngController > mng_file_download");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/file/download");
        return mv;
    }

    @RequestMapping(value = "/mng/file/download/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<DownloadDTO>> mng_file_download_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_file_download_selectList");
        //System.System.out.println(searchDTO.toString());

        List<DownloadDTO> responseList = eduMarineMngService.processSelectDownloadList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/file/download/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_file_download_insert(@RequestBody DownloadDTO downloadDTO, HttpSession session) {
        System.out.println("EduMarineMngController > mng_file_download_insert");
        //System.System.out.println(noticeDTO.toString());

        downloadDTO.setDownloadUser(String.valueOf(session.getAttribute("id")));
        ResponseDTO responseDTO = eduMarineMngService.processInsertDownload(downloadDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/file/trash.do", method = RequestMethod.GET)
    public ModelAndView mng_file_trash() {
        System.out.println("EduMarineMngController > mng_file_trash");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/file/trash");
        return mv;
    }

    @RequestMapping(value = "/mng/file/trash/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrashDTO>> mng_file_trash_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_file_trash_selectList");
        //System.System.out.println(searchDTO.toString());

        List<TrashDTO> responseList = eduMarineMngService.processSelectTrashList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/file/trash/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_file_trash_save(@RequestBody TrashDTO trashDTO) {
        System.out.println("EduMarineMngController > mng_file_trash_save");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processSaveTrash(trashDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/file/trash/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_file_trash_delete(@RequestBody TrashDTO trashDTO) {
        System.out.println("EduMarineMngController > mng_file_trash_delete");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processDeleteTrash(trashDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/file/trash/restore.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_file_trash_restore(@RequestBody TrashDTO trashDTO) {
        System.out.println("EduMarineMngController > mng_file_trash_restore");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processRestoreTrash(trashDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //***************************************************************************
    // request Folder
    //***************************************************************************

    @RequestMapping(value = "/mng/request/list.do", method = RequestMethod.GET)
    public ModelAndView mng_request_list() {
        System.out.println("EduMarineMngController > mng_request_list");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/request/list");
        return mv;
    }

    @RequestMapping(value = "/mng/request/list/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<RequestDTO>> mng_request_list_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_request_list_selectList");
        //System.System.out.println(searchDTO.toString());

        List<RequestDTO> responseList = eduMarineMngService.processSelectRequestList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_request_list_detail(String seq) {
        System.out.println("EduMarineMngController > mng_request_list_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setSeq(seq);
            RequestDTO info = eduMarineMngService.processSelectRequestSingle(requestDTO);
            if(info != null){
                mv.addObject("info", info);

                List<RequestReplyDTO> replyList = eduMarineMngService.processSelectReplyList(seq);
                mv.addObject("replyList", replyList);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setUserId(info.getSeq());
                List<FileDTO> fileList = commService.processSelectFileUserIdList(fileDTO);
                mv.addObject("fileList", fileList);
            }
        }

        mv.setViewName("/mng/request/list/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/request/list/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_insert(@RequestBody RequestDTO requestDTO) {
        System.out.println("EduMarineMngController > mng_request_list_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertRequest(requestDTO);

        updateFileParentSeq(requestDTO.getSeq(), requestDTO.getFileIdList());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_update(@RequestBody RequestDTO requestDTO) {
        System.out.println("EduMarineMngController > mng_request_list_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRequest(requestDTO);

        updateFileParentSeq(requestDTO.getSeq(), requestDTO.getFileIdList());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_delete(@RequestBody RequestDTO requestDTO) {
        System.out.println("EduMarineMngController > mng_request_list_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteRequest(requestDTO);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUserId(requestDTO.getSeq());
        commService.processUpdateFileDeleteUseN(fileDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/reply/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_reply_insert(@RequestBody RequestReplyDTO requestReplyDTO) {
        System.out.println("EduMarineMngController > mng_request_list_reply_insert");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertReply(requestReplyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/reply/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_reply_delete(@RequestBody RequestReplyDTO requestReplyDTO) {
        System.out.println("EduMarineMngController > mng_request_list_reply_delete");

        ResponseDTO responseDTO = eduMarineMngService.processDeleteReply(requestReplyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/progress/step/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_progress_step_update(@RequestBody List<RequestDTO> requestList) {
        System.out.println("EduMarineMngController > mng_request_list_progress_step_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRequestProgressStep(requestList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/request/list/complete/expect/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_request_list_complete_expect_update(@RequestBody List<RequestDTO> requestList) {
        System.out.println("EduMarineMngController > mng_request_list_complete_expect_update");
        //System.System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRequestCompleteExpect(requestList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private void updateFileParentSeq(String parentSeq, String fileSeqList){

        if(fileSeqList != null && !"".equals(fileSeqList)){
            String[] fileSeqSplit = fileSeqList.split(",");
            for (String fileSeq : fileSeqSplit) {
                if (!"".equals(fileSeq)) {
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setId(fileSeq);
                    fileDTO.setUserId(parentSeq);
                    commService.processUpdateFileUserId(fileDTO);
                }
            }
        }

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
        //System.System.out.println(newsletterDTO.toString());

        List<FileDTO> fileList = eduMarineMngService.processSelectFileUserIdList(fileDTO);

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

    @RequestMapping(value = "/file/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> file_upload(HttpServletRequest uploadFile) throws UnsupportedEncodingException {
        System.out.println("EduMarineMngController > file_upload");
        String gbn = uploadFile.getParameter("gbn");
        uploadFile.setCharacterEncoding("UTF-8");
        JSONObject response = new JSONObject();

        int size = 1024 * 1024 * 100; // 100M
        String file = "";
        String oriFile = "";

        JSONObject obj = new JSONObject();

        try {
            String path = "";
            if("mail".equals(gbn)){
                path = ResourceUtils.getFile("/usr/local/tomcat/webapps/ROOT/WEB-INF/classes/static/img/" + gbn + "/").toPath().toString();
            }else{
                /* prod */
                path = ResourceUtils.getFile("/usr/local/tomcat/webapps/upload/" + gbn + "/").toPath().toString();
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

            file = appendSuffixName(uploadPath.getPath() , file, 1);

            // 업로드된 파일 객체 생성
            File oldFile = new File(uploadPath.getPath()  + File.separator + oriFile);

            // 실제 저장될 파일 객체 생성
            File newFile = new File(uploadPath.getPath() + File.separator + file);

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
            //response.put("uuid",uuid);
            response.put("fileName", file);

            System.out.println("[full file path] : " + uploadPath + File.separator + file);
            /*System.System.out.println("[uploadPath.getPath()2] : " + uploadPath.getPath());
            System.System.out.println("[uuid] : " + uuid);
            System.System.out.println("[fileName] : " + oriFile);
            System.System.out.println("[file] : " + file);*/

        } catch (Exception e) {
            System.out.println("[upload file save error] : " + e.getMessage());
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static String appendSuffixName(String path, String orgFileName, int seq) {
        String retFileName = "";
        // 파일이 존재하는지 확인한다.
        if (new File(path + File.separator + orgFileName).exists()) {
            int plusSeq = 1;

            String seqStr = "_" + seq;
            String firstFileName = orgFileName.substring(0,
                    orgFileName.lastIndexOf("."));
            String extName = orgFileName
                    .substring(orgFileName.lastIndexOf("."));

            // 만약 파일명에 _숫자가 들어간경우라면..
            if (orgFileName.lastIndexOf("_") != -1
                    && !firstFileName.endsWith("_")) {
                String numStr = orgFileName.substring(
                        orgFileName.lastIndexOf("_") + 1,
                        orgFileName.lastIndexOf(extName));
                try {
                    plusSeq = Integer.parseInt(numStr);
                    plusSeq = plusSeq + 1;

                    retFileName = firstFileName.substring(0,
                            firstFileName.lastIndexOf("_"))
                            + "_" + plusSeq + extName;
                } catch (NumberFormatException e) {
                    retFileName = firstFileName + seqStr + extName;
                    return appendSuffixName(path , retFileName, ++plusSeq);
                }

            } else {
                retFileName = firstFileName + seqStr + extName;
            }
            // 재귀
            return appendSuffixName(path , retFileName, ++plusSeq);
        } else {
            return orgFileName;
        }
    }

    @ResponseBody
    @GetMapping(value = "/board/uploadFileGet")
    public ResponseEntity<byte[]> board_uploadFileGet(@RequestParam("fileName") String fileName) {
        System.out.println("EduMarineMngController > board_uploadFileGet");
        //System.System.out.println("fileName : " + fileName);

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
            //System.System.out.println("Files.content-type : " + Files.probeContentType(file.toPath()));
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
            file_repo = ResourceUtils.getFile("/usr/local/tomcat/webapps/ROOT/WEB-INF/classes/static/img/" + path + "/").toPath().toString();
        }else {
            // 파일 업로드된 경로
            file_repo = ResourceUtils.getFile("/usr/local/tomcat/webapps/upload/" + path + "/").toPath().toString();
        }
        // 서버에 실제 저장된 파일명
        //String filename = "20140819151221.zip" ;
        String fileName = request.getParameter("fileName");

        OutputStream out = response.getOutputStream();
        String downFile = file_repo + "/" + fileName;
        File f = new File(downFile);
        response.setHeader("Cache-Control", "no-cache");
        // 한글 파일명 처리
        /*fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);*/
        response.addHeader("Content-disposition","attachment; fileName=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20") + ";");

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

    @RequestMapping(value = "/mng/customer/member/excel/download.do", method = RequestMethod.GET)
    public void customer_member_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_member_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "등급", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "관심키워드", "SMS수신동의여부", "등록일",
                    "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Member");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,15));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("회원정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<MemberDTO> memberDetailList = eduMarineMngService.processSelectExcelMemberDetailList();

            int cellCnt = 0;
            int listCount = memberDetailList.size();

            //데이터 부분 생성
            for(MemberDTO info : memberDetailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getName().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getGrade());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getName());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 관심키워드
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getKeyword());

                // SMS수신동의여부
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String smsYn = "동의";
                if("0".equals(info.getSmsYn())){
                    smsYn = "미동의";
                }
                cell.setCellValue(smsYn);

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachement; filename=\""+ java.net.URLEncoder.encode(fileName, "UTF-8") + "\";charset=\"UTF-8\"");

            /* 기존 코드 */
            workbook.write(res.getOutputStream());
            res.getOutputStream().close();
            workbook.dispose();

            /* 변경코드 (암호화)
            try (POIFSFileSystem fs = new POIFSFileSystem()) {
                EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
                Encryptor enc = info.getEncryptor();
                enc.confirmPassword("123");
                try (OutputStream os = enc.getDataStream(fs)) {
                    workbook.write(os);
                } catch (Exception e) {
                    System.out.println("OutputStream error while encrypting excel : " + e.getMessage());
                }

                fs.writeFilesystem(res.getOutputStream());

                res.getOutputStream().close();
                workbook.dispose();
            } catch (Exception e) {
                System.out.println("POIFSFileSystem error while encrypting excel : " + e.getMessage());
            }*/

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/regular/excel/download.do", method = RequestMethod.GET)
    public void customer_regular_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_regular_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "회원상태", "등급", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "거주지역",
                    "참여경로", "1순위 신청분야", "2순위 신청분야", "3순위 신청분야", "희망교육시기",
                    "전공", "경험유무", "추천인", "결제상태", "등록일",
                    "수정일", "교육신청일시", "교육명", "차시", "상태"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.LIME.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Regular");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,24));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("상시신청 신청자정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<RegularDTO> regularDetailList = eduMarineMngService.processSelectExcelRegularDetailList();

            for(int i=0; i<regularDetailList.size(); i++) {
                RegularDTO info = regularDetailList.get(i);
                if (info.getMemberSeq() != null) {
                    List<RegularDTO.TrainInfo> trainInfoList = eduMarineMngService.processSelectRegularTrainInfoList(info);
                    if (!trainInfoList.isEmpty()) {
                        info.setTrainInfoList(trainInfoList);
                    }
                    regularDetailList.set(i, info);
                }
            }

            int cellCnt = 0;
            int listCount = regularDetailList.size();

            //데이터 부분 생성
            for(RegularDTO info : regularDetailList) {

                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getName().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getMemberStatus() != null && !"".equals(info.getMemberStatus())){
                    cell.setCellValue(info.getMemberStatus());
                }else{
                    cell.setCellValue("-");
                }

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getGrade() != null && !"".equals(info.getGrade())){
                    cell.setCellValue(info.getGrade());
                }else{
                    cell.setCellValue("-");
                }

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getId() != null && !"".equals(info.getId())){
                    cell.setCellValue(info.getId());
                }else{
                    cell.setCellValue("-");
                }

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getName());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getNameEn() != null && !"".equals(info.getNameEn())){
                    cell.setCellValue(info.getNameEn());
                }else{
                    cell.setCellValue("-");
                }

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getBirthYear() != null && !"".equals(info.getBirthYear())){
                    cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());
                }else{
                    cell.setCellValue("-");
                }

                // 거주지역
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRegion());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 1순위 신청분야
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFirstApplicationField());

                // 2순위 신청분야
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSecondApplicationField());

                // 3순위 신청분야
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getThirdApplicationField());

                // 희망교육시기
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getDesiredEducationTime());

                // 전공
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMajor());

                // 경험유무
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String experienceYn = "있음";
                if("0".equals(info.getExperienceYn())){
                    experienceYn = "없음";
                }
                cell.setCellValue(experienceYn);

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 결제상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

                if(info.getTrainInfoList() != null && !info.getTrainInfoList().isEmpty()){
                    for(int i=0; i<info.getTrainInfoList().size(); i++){
                        RegularDTO.TrainInfo trainInfo = info.getTrainInfoList().get(i);
                        for(int j=0; j<4; j++){
                            cell = row.createCell(cellCnt++);
                            cell.setCellStyle(bodyStyle);
                            if(j == 0){
                                cell.setCellValue(trainInfo.getInitRegiDttm());
                            }else if(j == 1){
                                cell.setCellValue(trainInfo.getGbn());
                            }else if(j == 2){
                                cell.setCellValue(trainInfo.getNextTime());
                            }else {
                                cell.setCellValue(trainInfo.getApplyStatus());
                            }
                        }
                    }
                }else{
                    // 교육데이터 없는 애들은 공백 4칸 만들기
                    for(int i=0; i<4; i++){
                        cell = row.createCell(cellCnt++);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue("");
                    }
                }

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/boarder/excel/download.do", method = RequestMethod.GET)
    public void customer_boarder_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_boarder_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "상의사이즈", "하의사이즈", "안전화사이즈",
                    "참여경로", "졸업구분", "학교명", "전공",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "병역", "미필사유",
                    "장애인", "취업지원대상", "테크니션교육경험", "교육명",
                    "지원동기", "향후 테크니션 업무 수행시의 인성 적합성 기술", "자격 및 경력, 대외 활동 사항",
                    "교육수료 후 포부 (향후 자신의 진로 등)", "기타",
                    "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000,
                    5000, 5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000,
                    5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Boarder");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,18));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본/신청정보");

            // 경력사항
            sheet.addMergedRegion(new CellRangeAddress(0,0,19,23));
            SXSSFCell mergeCell2 = row.createCell(19);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("경력사항1");

            sheet.addMergedRegion(new CellRangeAddress(0,0,24,28));
            SXSSFCell mergeCell2_2 = row.createCell(24);
            mergeCell2_2.setCellStyle(headerStyle_light_green);
            mergeCell2_2.setCellValue("경력사항2");

            sheet.addMergedRegion(new CellRangeAddress(0,0,29,33));
            SXSSFCell mergeCell2_3 = row.createCell(29);
            mergeCell2_3.setCellStyle(headerStyle_light_green);
            mergeCell2_3.setCellValue("경력사항3");

            sheet.addMergedRegion(new CellRangeAddress(0,0,34,38));
            SXSSFCell mergeCell2_4 = row.createCell(34);
            mergeCell2_4.setCellStyle(headerStyle_light_green);
            mergeCell2_4.setCellValue("경력사항4");

            sheet.addMergedRegion(new CellRangeAddress(0,0,39,43));
            SXSSFCell mergeCell2_5 = row.createCell(39);
            mergeCell2_5.setCellStyle(headerStyle_light_green);
            mergeCell2_5.setCellValue("경력사항5");

            sheet.addMergedRegion(new CellRangeAddress(0,0,44,48));
            SXSSFCell mergeCell2_6 = row.createCell(44);
            mergeCell2_6.setCellStyle(headerStyle_light_green);
            mergeCell2_6.setCellValue("경력사항6");

            sheet.addMergedRegion(new CellRangeAddress(0,0,49,53));
            SXSSFCell mergeCell2_7 = row.createCell(49);
            mergeCell2_7.setCellStyle(headerStyle_light_green);
            mergeCell2_7.setCellValue("경력사항7");

            sheet.addMergedRegion(new CellRangeAddress(0,0,54,58));
            SXSSFCell mergeCell2_8 = row.createCell(54);
            mergeCell2_8.setCellStyle(headerStyle_light_green);
            mergeCell2_8.setCellValue("경력사항8");

            sheet.addMergedRegion(new CellRangeAddress(0,0,59,63));
            SXSSFCell mergeCell2_9 = row.createCell(59);
            mergeCell2_9.setCellStyle(headerStyle_light_green);
            mergeCell2_9.setCellValue("경력사항9");

            sheet.addMergedRegion(new CellRangeAddress(0,0,64,68));
            SXSSFCell mergeCell2_10 = row.createCell(64);
            mergeCell2_10.setCellStyle(headerStyle_light_green);
            mergeCell2_10.setCellValue("경력사항10");

            sheet.addMergedRegion(new CellRangeAddress(0,0,69,73));
            SXSSFCell mergeCell2_11 = row.createCell(69);
            mergeCell2_11.setCellStyle(headerStyle_light_green);
            mergeCell2_11.setCellValue("경력사항11");

            sheet.addMergedRegion(new CellRangeAddress(0,0,74,78));
            SXSSFCell mergeCell2_12 = row.createCell(74);
            mergeCell2_12.setCellStyle(headerStyle_light_green);
            mergeCell2_12.setCellValue("경력사항12");

            sheet.addMergedRegion(new CellRangeAddress(0,0,79,83));
            SXSSFCell mergeCell2_13 = row.createCell(79);
            mergeCell2_13.setCellStyle(headerStyle_light_green);
            mergeCell2_13.setCellValue("경력사항13");

            sheet.addMergedRegion(new CellRangeAddress(0,0,84,88));
            SXSSFCell mergeCell2_14 = row.createCell(84);
            mergeCell2_14.setCellStyle(headerStyle_light_green);
            mergeCell2_14.setCellValue("경력사항14");

            sheet.addMergedRegion(new CellRangeAddress(0,0,89,93));
            SXSSFCell mergeCell2_15 = row.createCell(89);
            mergeCell2_15.setCellStyle(headerStyle_light_green);
            mergeCell2_15.setCellValue("경력사항15");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,94,96));
            SXSSFCell mergeCell3 = row.createCell(94);
            mergeCell3.setCellStyle(headerStyle_light_orange);
            mergeCell3.setCellValue("자격면허1");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,97,99));
            SXSSFCell mergeCell3_2 = row.createCell(97);
            mergeCell3_2.setCellStyle(headerStyle_light_orange);
            mergeCell3_2.setCellValue("자격면허2");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,100,102));
            SXSSFCell mergeCell3_3 = row.createCell(100);
            mergeCell3_3.setCellStyle(headerStyle_light_orange);
            mergeCell3_3.setCellValue("자격면허3");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,103,105));
            SXSSFCell mergeCell3_4 = row.createCell(103);
            mergeCell3_4.setCellStyle(headerStyle_light_orange);
            mergeCell3_4.setCellValue("자격면허4");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,106,108));
            SXSSFCell mergeCell3_5 = row.createCell(106);
            mergeCell3_5.setCellStyle(headerStyle_light_orange);
            mergeCell3_5.setCellValue("자격면허5");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,109,111));
            SXSSFCell mergeCell3_6 = row.createCell(109);
            mergeCell3_6.setCellStyle(headerStyle_light_orange);
            mergeCell3_6.setCellValue("자격면허6");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,112,114));
            SXSSFCell mergeCell3_7 = row.createCell(112);
            mergeCell3_7.setCellStyle(headerStyle_light_orange);
            mergeCell3_7.setCellValue("자격면허7");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,115,117));
            SXSSFCell mergeCell3_8 = row.createCell(115);
            mergeCell3_8.setCellStyle(headerStyle_light_orange);
            mergeCell3_8.setCellValue("자격면허8");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,118,120));
            SXSSFCell mergeCell3_9 = row.createCell(118);
            mergeCell3_9.setCellStyle(headerStyle_light_orange);
            mergeCell3_9.setCellValue("자격면허9");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,121,123));
            SXSSFCell mergeCell3_10 = row.createCell(121);
            mergeCell3_10.setCellStyle(headerStyle_light_orange);
            mergeCell3_10.setCellValue("자격면허10");

            // 상세정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,124,129));
            SXSSFCell mergeCell4 = row.createCell(124);
            mergeCell4.setCellStyle(headerStyle);
            mergeCell4.setCellValue("상세정보");

            // 자기소개서
            sheet.addMergedRegion(new CellRangeAddress(0,0,130,134));
            SXSSFCell mergeCell5 = row.createCell(130);
            mergeCell5.setCellStyle(headerStyle);
            mergeCell5.setCellValue("자기소개서");

            // 상세정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,135,136));
            SXSSFCell mergeCell6 = row.createCell(135);
            mergeCell6.setCellStyle(headerStyle);
            mergeCell6.setCellValue("상세정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<19){
                    cell.setCellStyle(headerStyle);
                }else if(i<94){
                    cell.setCellStyle(headerStyle_light_green);
                }else if(i<124){
                    cell.setCellStyle(headerStyle_light_orange);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<BoarderDetailDTO> detailList = eduMarineMngService.processSelectExcelBoarderDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(BoarderDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 상의사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getTopClothesSize());

                // 하의사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBottomClothesSize());

                // 안전화사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getShoesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 졸업구분
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getGradeGbn());

                // 학교명
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSchoolName());

                // 전공
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMajor());

                for(int i=0; i<15; i++) {
                    // 근무처
                    String[] careerPlaceSplit = new String[15];
                    if (info.getCareerPlace() != null) {
                        if (info.getCareerPlace().contains("^")) {
                            careerPlaceSplit = info.getCareerPlace().split("\\^");
                        } else {
                            careerPlaceSplit[0] = info.getCareerPlace().replaceAll("\\^", "");
                        }
                    } else {
                        careerPlaceSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerPlaceSplit, i));

                    // 기간
                    String[] careerDateSplit = new String[15];
                    if (info.getCareerDate() != null) {
                        if (info.getCareerDate().contains("^")) {
                            careerDateSplit = info.getCareerDate().split("\\^");
                        } else {
                            careerDateSplit[0] = info.getCareerDate().replaceAll("\\^", "");
                        }
                    } else {
                        careerDateSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerDateSplit, i));

                    // 직위
                    String[] careerPositionSplit = new String[15];
                    if (info.getCareerPosition() != null) {
                        if (info.getCareerPosition().contains("^")) {
                            careerPositionSplit = info.getCareerPosition().split("\\^");
                        } else {
                            careerPositionSplit[0] = info.getCareerPosition().replaceAll("\\^", "");
                        }
                    } else {
                        careerPositionSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerPositionSplit, i));

                    // 담당업무
                    String[] careerTaskSplit = new String[15];
                    if (info.getCareerTask() != null) {
                        if (info.getCareerTask().contains("^")) {
                            careerTaskSplit = info.getCareerTask().split("\\^");
                        } else {
                            careerTaskSplit[0] = info.getCareerTask().replaceAll("\\^", "");
                        }
                    } else {
                        careerTaskSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerTaskSplit, i));

                    // 소재지
                    String[] careerLocationSplit = new String[15];
                    if (info.getCareerLocation() != null) {
                        if (info.getCareerLocation().contains("^")) {
                            careerLocationSplit = info.getCareerLocation().split("\\^");
                        } else {
                            careerLocationSplit[0] = info.getCareerLocation().replaceAll("\\^", "");
                        }
                    } else {
                        careerLocationSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerLocationSplit, i));
                }

                for(int i=0; i<10; i++) {
                    // 자격면허명
                    String[] licenseNameSplit = new String[10];
                    if (info.getLicenseName() != null) {
                        if (info.getLicenseName().contains("^")) {
                            licenseNameSplit = info.getLicenseName().split("\\^");
                        } else {
                            licenseNameSplit[0] = info.getLicenseName().replaceAll("\\^", "");
                        }
                    } else {
                        licenseNameSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(licenseNameSplit, i));

                    // 취득일
                    String[] licenseDateSplit = new String[10];
                    if (info.getLicenseDate() != null) {
                        if (info.getLicenseDate().contains("^")) {
                            licenseDateSplit = info.getLicenseDate().split("\\^");
                        } else {
                            licenseDateSplit[0] = info.getLicenseDate().replaceAll("\\^", "");
                        }
                    } else {
                        licenseDateSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(licenseDateSplit, i));

                    // 발행기관
                    String[] licenseOrgSplit = new String[10];
                    if (info.getLicenseOrg() != null) {
                        if (info.getLicenseOrg().contains("^")) {
                            licenseOrgSplit = info.getLicenseOrg().split("\\^");
                        } else {
                            licenseOrgSplit[0] = info.getLicenseOrg().replaceAll("\\^", "");
                        }
                    } else {
                        licenseOrgSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(licenseOrgSplit, i));
                }

                // 병역
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMilitaryGbn());

                // 병역(사유)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMilitaryReason());

                // 장애인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getDisabledGbn());

                // 취업지원대상
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getJobSupportGbn());

                // 테크니션교육경험
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getTechEduGbn());

                // 테크니션교육경험(교육명)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getTechEduName());

                // 지원동기
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyReason());

                // 적합성 기술
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getTechReason());

                // 활동사항
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getActivityReason());

                // 포부
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPlanReason());

                // 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEtcReason());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/frp/excel/download.do", method = RequestMethod.GET)
    public void customer_frp_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_frp_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "상의사이즈", "하의사이즈", "안전화사이즈",
                    "참여경로", "졸업구분", "학교명", "전공",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "근무처", "기간", "직위", "담당업무", "소재지",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "자격면허명", "취득일", "발행기관",
                    "병역", "미필사유",
                    "장애인", "취업지원대상", "알게된 경로", "기타 경로",
                    "지원동기", "향후 테크니션 업무 수행시의 인성 적합성 기술", "자격 및 경력, 대외 활동 사항",
                    "교육수료 후 포부 (향후 자신의 진로 등)", "기타",
                    "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000,
                    5000, 5000, 5000, 5000,
                    5000, 5000, 5000,
                    5000, 5000,
                    5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("FRP");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,18));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본/신청정보");

            // 경력사항
            sheet.addMergedRegion(new CellRangeAddress(0,0,19,23));
            SXSSFCell mergeCell2 = row.createCell(19);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("경력사항1");

            sheet.addMergedRegion(new CellRangeAddress(0,0,24,28));
            SXSSFCell mergeCell2_2 = row.createCell(24);
            mergeCell2_2.setCellStyle(headerStyle_light_green);
            mergeCell2_2.setCellValue("경력사항2");

            sheet.addMergedRegion(new CellRangeAddress(0,0,29,33));
            SXSSFCell mergeCell2_3 = row.createCell(29);
            mergeCell2_3.setCellStyle(headerStyle_light_green);
            mergeCell2_3.setCellValue("경력사항3");

            sheet.addMergedRegion(new CellRangeAddress(0,0,34,38));
            SXSSFCell mergeCell2_4 = row.createCell(34);
            mergeCell2_4.setCellStyle(headerStyle_light_green);
            mergeCell2_4.setCellValue("경력사항4");

            sheet.addMergedRegion(new CellRangeAddress(0,0,39,43));
            SXSSFCell mergeCell2_5 = row.createCell(39);
            mergeCell2_5.setCellStyle(headerStyle_light_green);
            mergeCell2_5.setCellValue("경력사항5");

            sheet.addMergedRegion(new CellRangeAddress(0,0,44,48));
            SXSSFCell mergeCell2_6 = row.createCell(44);
            mergeCell2_6.setCellStyle(headerStyle_light_green);
            mergeCell2_6.setCellValue("경력사항6");

            sheet.addMergedRegion(new CellRangeAddress(0,0,49,53));
            SXSSFCell mergeCell2_7 = row.createCell(49);
            mergeCell2_7.setCellStyle(headerStyle_light_green);
            mergeCell2_7.setCellValue("경력사항7");

            sheet.addMergedRegion(new CellRangeAddress(0,0,54,58));
            SXSSFCell mergeCell2_8 = row.createCell(54);
            mergeCell2_8.setCellStyle(headerStyle_light_green);
            mergeCell2_8.setCellValue("경력사항8");

            sheet.addMergedRegion(new CellRangeAddress(0,0,59,63));
            SXSSFCell mergeCell2_9 = row.createCell(59);
            mergeCell2_9.setCellStyle(headerStyle_light_green);
            mergeCell2_9.setCellValue("경력사항9");

            sheet.addMergedRegion(new CellRangeAddress(0,0,64,68));
            SXSSFCell mergeCell2_10 = row.createCell(64);
            mergeCell2_10.setCellStyle(headerStyle_light_green);
            mergeCell2_10.setCellValue("경력사항10");

            sheet.addMergedRegion(new CellRangeAddress(0,0,69,73));
            SXSSFCell mergeCell2_11 = row.createCell(69);
            mergeCell2_11.setCellStyle(headerStyle_light_green);
            mergeCell2_11.setCellValue("경력사항11");

            sheet.addMergedRegion(new CellRangeAddress(0,0,74,78));
            SXSSFCell mergeCell2_12 = row.createCell(74);
            mergeCell2_12.setCellStyle(headerStyle_light_green);
            mergeCell2_12.setCellValue("경력사항12");

            sheet.addMergedRegion(new CellRangeAddress(0,0,79,83));
            SXSSFCell mergeCell2_13 = row.createCell(79);
            mergeCell2_13.setCellStyle(headerStyle_light_green);
            mergeCell2_13.setCellValue("경력사항13");

            sheet.addMergedRegion(new CellRangeAddress(0,0,84,88));
            SXSSFCell mergeCell2_14 = row.createCell(84);
            mergeCell2_14.setCellStyle(headerStyle_light_green);
            mergeCell2_14.setCellValue("경력사항14");

            sheet.addMergedRegion(new CellRangeAddress(0,0,89,93));
            SXSSFCell mergeCell2_15 = row.createCell(89);
            mergeCell2_15.setCellStyle(headerStyle_light_green);
            mergeCell2_15.setCellValue("경력사항15");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,94,96));
            SXSSFCell mergeCell3 = row.createCell(94);
            mergeCell3.setCellStyle(headerStyle_light_orange);
            mergeCell3.setCellValue("자격면허1");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,97,99));
            SXSSFCell mergeCell3_2 = row.createCell(97);
            mergeCell3_2.setCellStyle(headerStyle_light_orange);
            mergeCell3_2.setCellValue("자격면허2");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,100,102));
            SXSSFCell mergeCell3_3 = row.createCell(100);
            mergeCell3_3.setCellStyle(headerStyle_light_orange);
            mergeCell3_3.setCellValue("자격면허3");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,103,105));
            SXSSFCell mergeCell3_4 = row.createCell(103);
            mergeCell3_4.setCellStyle(headerStyle_light_orange);
            mergeCell3_4.setCellValue("자격면허4");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,106,108));
            SXSSFCell mergeCell3_5 = row.createCell(106);
            mergeCell3_5.setCellStyle(headerStyle_light_orange);
            mergeCell3_5.setCellValue("자격면허5");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,109,111));
            SXSSFCell mergeCell3_6 = row.createCell(109);
            mergeCell3_6.setCellStyle(headerStyle_light_orange);
            mergeCell3_6.setCellValue("자격면허6");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,112,114));
            SXSSFCell mergeCell3_7 = row.createCell(112);
            mergeCell3_7.setCellStyle(headerStyle_light_orange);
            mergeCell3_7.setCellValue("자격면허7");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,115,117));
            SXSSFCell mergeCell3_8 = row.createCell(115);
            mergeCell3_8.setCellStyle(headerStyle_light_orange);
            mergeCell3_8.setCellValue("자격면허8");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,118,120));
            SXSSFCell mergeCell3_9 = row.createCell(118);
            mergeCell3_9.setCellStyle(headerStyle_light_orange);
            mergeCell3_9.setCellValue("자격면허9");

            // 자격면허
            sheet.addMergedRegion(new CellRangeAddress(0,0,121,123));
            SXSSFCell mergeCell3_10 = row.createCell(121);
            mergeCell3_10.setCellStyle(headerStyle_light_orange);
            mergeCell3_10.setCellValue("자격면허10");

            // 상세정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,124,129));
            SXSSFCell mergeCell4 = row.createCell(124);
            mergeCell4.setCellStyle(headerStyle);
            mergeCell4.setCellValue("상세정보");

            // 자기소개서
            sheet.addMergedRegion(new CellRangeAddress(0,0,130,134));
            SXSSFCell mergeCell5 = row.createCell(130);
            mergeCell5.setCellStyle(headerStyle);
            mergeCell5.setCellValue("자기소개서");

            // 상세정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,135,136));
            SXSSFCell mergeCell6 = row.createCell(135);
            mergeCell6.setCellStyle(headerStyle);
            mergeCell6.setCellValue("상세정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<19){
                    cell.setCellStyle(headerStyle);
                }else if(i<94){
                    cell.setCellStyle(headerStyle_light_green);
                }else if(i<124){
                    cell.setCellStyle(headerStyle_light_orange);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<FrpDetailDTO> detailList = eduMarineMngService.processSelectExcelFrpDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(FrpDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 상의사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getTopClothesSize());

                // 하의사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBottomClothesSize());

                // 안전화사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getShoesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 졸업구분
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getGradeGbn());

                // 학교명
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSchoolName());

                // 전공
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMajor());

                for(int i=0; i<15; i++) {
                    // 근무처
                    String[] careerPlaceSplit = new String[15];
                    if (info.getCareerPlace() != null) {
                        if (info.getCareerPlace().contains("^")) {
                            careerPlaceSplit = info.getCareerPlace().split("\\^");
                        } else {
                            careerPlaceSplit[0] = info.getCareerPlace().replaceAll("\\^", "");
                        }
                    } else {
                        careerPlaceSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerPlaceSplit, i));

                    // 기간
                    String[] careerDateSplit = new String[15];
                    if (info.getCareerDate() != null) {
                        if (info.getCareerDate().contains("^")) {
                            careerDateSplit = info.getCareerDate().split("\\^");
                        } else {
                            careerDateSplit[0] = info.getCareerDate().replaceAll("\\^", "");
                        }
                    } else {
                        careerDateSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerDateSplit, i));

                    // 직위
                    String[] careerPositionSplit = new String[15];
                    if (info.getCareerPosition() != null) {
                        if (info.getCareerPosition().contains("^")) {
                            careerPositionSplit = info.getCareerPosition().split("\\^");
                        } else {
                            careerPositionSplit[0] = info.getCareerPosition().replaceAll("\\^", "");
                        }
                    } else {
                        careerPositionSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerPositionSplit, i));

                    // 담당업무
                    String[] careerTaskSplit = new String[15];
                    if (info.getCareerTask() != null) {
                        if (info.getCareerTask().contains("^")) {
                            careerTaskSplit = info.getCareerTask().split("\\^");
                        } else {
                            careerTaskSplit[0] = info.getCareerTask().replaceAll("\\^", "");
                        }
                    } else {
                        careerTaskSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerTaskSplit, i));

                    // 소재지
                    String[] careerLocationSplit = new String[15];
                    if (info.getCareerLocation() != null) {
                        if (info.getCareerLocation().contains("^")) {
                            careerLocationSplit = info.getCareerLocation().split("\\^");
                        } else {
                            careerLocationSplit[0] = info.getCareerLocation().replaceAll("\\^", "");
                        }
                    } else {
                        careerLocationSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(careerLocationSplit, i));
                }

                for(int i=0; i<10; i++) {
                    // 자격면허명
                    String[] licenseNameSplit = new String[10];
                    if (info.getLicenseName() != null) {
                        if (info.getLicenseName().contains("^")) {
                            licenseNameSplit = info.getLicenseName().split("\\^");
                        } else {
                            licenseNameSplit[0] = info.getLicenseName().replaceAll("\\^", "");
                        }
                    } else {
                        licenseNameSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(licenseNameSplit, i));

                    // 취득일
                    String[] licenseDateSplit = new String[10];
                    if (info.getLicenseDate() != null) {
                        if (info.getLicenseDate().contains("^")) {
                            licenseDateSplit = info.getLicenseDate().split("\\^");
                        } else {
                            licenseDateSplit[0] = info.getLicenseDate().replaceAll("\\^", "");
                        }
                    } else {
                        licenseDateSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(licenseDateSplit, i));

                    // 발행기관
                    String[] licenseOrgSplit = new String[10];
                    if (info.getLicenseOrg() != null) {
                        if (info.getLicenseOrg().contains("^")) {
                            licenseOrgSplit = info.getLicenseOrg().split("\\^");
                        } else {
                            licenseOrgSplit[0] = info.getLicenseOrg().replaceAll("\\^", "");
                        }
                    } else {
                        licenseOrgSplit[0] = "";
                    }
                    cell = row.createCell(cellCnt++);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(convertValue(licenseOrgSplit, i));
                }

                // 병역
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMilitaryGbn());

                // 병역(사유)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMilitaryReason());

                // 장애인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getDisabledGbn());

                // 취업지원대상
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getJobSupportGbn());

                // 알게된 경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getKnowPath());

                // 기타 경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getKnowPathReason());

                // 지원동기
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyReason());

                // 적합성 기술
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getTechReason());

                // 활동사항
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getActivityReason());

                // 포부
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPlanReason());

                // 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEtcReason());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/basic/excel/download.do", method = RequestMethod.GET)
    public void customer_basic_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_basic_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "교육구분", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "추천인",
                    "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("basic");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,13,15));
            SXSSFCell mergeCell2 = row.createCell(13);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<13){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<BasicDetailDTO> detailList = eduMarineMngService.processSelectExcelBasicDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(BasicDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 교육구분
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBoarderGbn());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/emergency/excel/download.do", method = RequestMethod.GET)
    public void customer_emergency_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_emergency_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "교육구분", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "추천인",
                    "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("emergency");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,13,15));
            SXSSFCell mergeCell2 = row.createCell(13);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<13){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<EmergencyDetailDTO> detailList = eduMarineMngService.processSelectExcelEmergencyDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(EmergencyDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 교육구분
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBoarderGbn());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/outboarder/excel/download.do", method = RequestMethod.GET)
    public void customer_outboarder_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_outboarder_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "추천인",
                    "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Outboarder");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,15));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<OutboarderDetailDTO> detailList = eduMarineMngService.processSelectExcelOutboarderDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(OutboarderDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

                //줄 높이 계산
                for (String s : remark) {
                    if (!s.isEmpty()) {
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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getRcBirthYear() != null && !info.getRcBirthYear().isEmpty()){
                    cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());
                }else{
                    cell.setCellValue("");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/inboarder/excel/download.do", method = RequestMethod.GET)
    public void customer_inboarder_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_inboarder_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "추천인",
                    "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Inboarder");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,15));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<InboarderDetailDTO> detailList = eduMarineMngService.processSelectExcelInboarderDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(InboarderDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

                //줄 높이 계산
                for (String s : remark) {
                    if (!s.isEmpty()) {
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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getRcBirthYear() != null && !info.getRcBirthYear().isEmpty()){
                    cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());
                }else{
                    cell.setCellValue("");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/sailyacht/excel/download.do", method = RequestMethod.GET)
    public void customer_sailyacht_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_sailyacht_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "추천인",
                    "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Sailyacht");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,15));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<SailyachtDetailDTO> detailList = eduMarineMngService.processSelectExcelSailyachtDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(SailyachtDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

                //줄 높이 계산
                for (String s : remark) {
                    if (!s.isEmpty()) {
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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getRcBirthYear() != null && !info.getRcBirthYear().isEmpty()){
                    cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());
                }else{
                    cell.setCellValue("");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/highhorsepower/excel/download.do", method = RequestMethod.GET)
    public void customer_highhorsepower_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_highhorsepower_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "교육이해",
                    "교육이해 기타", "추천인", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Highhorsepower");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,16));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,17,18));
            SXSSFCell mergeCell2_2 = row.createCell(17);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<17){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<HighHorsePowerDTO> detailList = eduMarineMngService.processSelectExcelHighhorsepowerDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(HighHorsePowerDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 교육이해
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String trainUnderstand = "";
                if(info.getTrainUnderstand() != null && !"".equals(info.getTrainUnderstand())){
                    switch (info.getTrainUnderstand()){
                        case "1":
                            trainUnderstand = "해상엔진테크니션양성과정 및 마리나선박선외기/선내기정비사 실무과정 수료생";
                            break;
                        case "2":
                            trainUnderstand = "선외기 정비 분야에 경력이 있으신 분";
                            break;
                        case "3":
                            trainUnderstand = "경력이 없으나 선외기 정비 기술 습득을 원하시는 분";
                            break;
                        case "4":
                            trainUnderstand = "기타";
                            break;
                        default:
                            break;
                    }
                }
                cell.setCellValue(trainUnderstand);

                // 교육이해 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getTrainUnderstandEtc() != null && !"".equals(info.getTrainUnderstandEtc())){
                    cell.setCellValue(info.getTrainUnderstandEtc());
                }else{
                    cell.setCellValue("-");
                }

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/highself/excel/download.do", method = RequestMethod.GET)
    public void customer_highself_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_highself_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "교육이해",
                    "교육이해 기타", "추천인", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Highself");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,16));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,17,18));
            SXSSFCell mergeCell2_2 = row.createCell(17);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<17){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<HighSelfDTO> detailList = eduMarineMngService.processSelectExcelHighSelfDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(HighSelfDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 교육이해
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String trainUnderstand = "";
                if(info.getTrainUnderstand() != null && !"".equals(info.getTrainUnderstand())){
                    switch (info.getTrainUnderstand()){
                        case "1":
                            trainUnderstand = "고마력 선외기를 장착한 보트를 운항하시는 분";
                            break;
                        case "2":
                            trainUnderstand = "고마력 선외기를 장착한 보트를 운항할 계획이신 분";
                            break;
                        case "3":
                            trainUnderstand = "고마력 선외기 엔진에 대하여 관심이 있으신 분";
                            break;
                        case "4":
                            trainUnderstand = "기타";
                            break;
                        default:
                            break;
                    }
                }
                cell.setCellValue(trainUnderstand);

                // 교육이해 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getTrainUnderstandEtc() != null && !"".equals(info.getTrainUnderstandEtc())){
                    cell.setCellValue(info.getTrainUnderstandEtc());
                }else{
                    cell.setCellValue("-");
                }

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/highspecial/excel/download.do", method = RequestMethod.GET)
    public void customer_highspecial_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_highspecial_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "추천인", "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Highspecial");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,13));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<HighSpecialDTO> detailList = eduMarineMngService.processSelectExcelHighSpecialDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(HighSpecialDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

                //줄 높이 계산
                for (String s : remark) {
                    if (!s.isEmpty()) {
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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getRcBirthYear() != null && !info.getRcBirthYear().isEmpty()){
                    cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());
                }else{
                    cell.setCellValue("");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());
            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/sterndrive/excel/download.do", method = RequestMethod.GET)
    public void customer_sterndrive_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_sterndrive_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "교육이해",
                    "교육이해기타", "추천인", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Sterndrive");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,16));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,17,18));
            SXSSFCell mergeCell2_2 = row.createCell(17);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<17){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<SterndriveDTO> detailList = eduMarineMngService.processSelectExcelSterndriveDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(SterndriveDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 교육이해
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String trainUnderstand = "";
                if(info.getTrainUnderstand() != null && !"".equals(info.getTrainUnderstand())){
                    switch (info.getTrainUnderstand()){
                        case "1":
                            trainUnderstand = "해상엔진테크니션양성과정 및 마리나선박선외기/선내기정비사 실무과정 수료생";
                            break;
                        case "2":
                            trainUnderstand = "스턴드라이브 정비 분야에 경력이 있으신 분";
                            break;
                        case "3":
                            trainUnderstand = "경력이 없으나 스턴드라이브 정비 기술 습득을 원하시는 분";
                            break;
                        case "4":
                            trainUnderstand = "기타";
                            break;
                        default:
                            break;
                    }
                }
                cell.setCellValue(trainUnderstand);

                // 교육이해 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getTrainUnderstandEtc() != null && !"".equals(info.getTrainUnderstandEtc())){
                    cell.setCellValue(info.getTrainUnderstandEtc());
                }else{
                    cell.setCellValue("-");
                }

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/sternspecial/excel/download.do", method = RequestMethod.GET)
    public void customer_sternspecial_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_sternspecial_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "추천인", "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Sternspecial");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,13));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<SternSpecialDTO> detailList = eduMarineMngService.processSelectExcelSternSpecialDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(SternSpecialDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

                //줄 높이 계산
                for (String s : remark) {
                    if (!s.isEmpty()) {
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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getRcBirthYear() != null && !info.getRcBirthYear().isEmpty()){
                    cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());
                }else{
                    cell.setCellValue("");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());
            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/generator/excel/download.do", method = RequestMethod.GET)
    public void customer_generator_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_generator_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "교육이해",
                    "교육이해기타", "추천인", "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Generator");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,17));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,18,19));
            SXSSFCell mergeCell2_2 = row.createCell(18);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<18){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<GeneratorDetailDTO> detailList = eduMarineMngService.processSelectExcelGeneratorDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(GeneratorDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

                //줄 높이 계산
                for (String s : remark) {
                    if (!s.isEmpty()) {
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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 교육이해
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String trainUnderstand = "";
                if(info.getTrainUnderstand() != null && !"".equals(info.getTrainUnderstand())){
                    switch (info.getTrainUnderstand()){
                        case "1":
                            trainUnderstand = "선외기·선내기 정비사 실무과정 수료";
                            break;
                        case "2":
                            trainUnderstand = "엔진에 대한 지식이 있음";
                            break;
                        case "3":
                            trainUnderstand = "전기부분에 대한 기초지식이 있음";
                            break;
                        case "4":
                            trainUnderstand = "기타";
                            break;
                        default:
                            break;
                    }
                }
                cell.setCellValue(trainUnderstand);

                // 교육이해 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getTrainUnderstandEtc() != null && !"".equals(info.getTrainUnderstandEtc())){
                    cell.setCellValue(info.getTrainUnderstandEtc());
                }else{
                    cell.setCellValue("-");
                }

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getRcBirthYear() != null && !info.getRcBirthYear().isEmpty()){
                    cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());
                }else{
                    cell.setCellValue("");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/competency/excel/download.do", method = RequestMethod.GET)
    public void customer_competency_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_competency_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "작업복사이즈(남여공용)", "참여경로", "교육이해",
                    "교육이해기타", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Competency");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,15));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<CompetencyDetailDTO> detailList = eduMarineMngService.processSelectExcelCompetencyDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(CompetencyDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 작업복사이즈
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getClothesSize());

                // 참여경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 교육이해
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                String trainUnderstand = "";
                if(info.getTrainUnderstand() != null && !"".equals(info.getTrainUnderstand())){
                    switch (info.getTrainUnderstand()){
                        case "1":
                            trainUnderstand = "선외기·선내기 정비사 실무과정 수료";
                            break;
                        case "2":
                            trainUnderstand = "엔진에 대한 지식이 있음";
                            break;
                        case "3":
                            trainUnderstand = "전기부분에 대한 기초지식이 있음";
                            break;
                        case "4":
                            trainUnderstand = "기타";
                            break;
                        default:
                            break;
                    }
                }
                cell.setCellValue(trainUnderstand);

                // 교육이해 기타
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                if(info.getTrainUnderstandEtc() != null && !"".equals(info.getTrainUnderstandEtc())){
                    cell.setCellValue(info.getTrainUnderstandEtc());
                }else{
                    cell.setCellValue("-");
                }

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/famtourin/excel/download.do", method = RequestMethod.GET)
    public void customer_famtourin_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_famtourin_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "신청일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Famtourin");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,12));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,13,14));
            SXSSFCell mergeCell2_2 = row.createCell(13);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<13){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<FamtourinDetailDTO> detailList = eduMarineMngService.processSelectExcelFamtourinDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(FamtourinDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 신청일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyDay());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/famtourout/excel/download.do", method = RequestMethod.GET)
    public void customer_famtourout_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_famtourout_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "신청일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Famtourout");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,12));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,13,14));
            SXSSFCell mergeCell2_2 = row.createCell(13);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<13){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<FamtouroutDetailDTO> detailList = eduMarineMngService.processSelectExcelFamtouroutDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(FamtouroutDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 신청일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyDay());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping(value = "/mng/customer/electro/excel/download.do", method = RequestMethod.GET)
    public void customer_electro_detail_excel_download(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("EduMarineMngController > customer_electro_detail_excel_download");
        String fileName = req.getParameter("fileName");

        // Workbook 생성
        try(SXSSFWorkbook workbook = new SXSSFWorkbook()){ // Excel 2007 이상

            /* 엑셀 그리기 */
            final String[] colNames_ex = {
                    /* 회원정보 */
                    "No", "상태", "결제상태", "아이디", "성명(국문)",
                    "성명(영문)", "연락처", "이메일", "생년월일", "성별",
                    "주소", "상세주소", "날짜 선택", "참여 경로", "추천인",
                    "추천인 생년월일", "등록일", "수정일"
            };

            // 헤더 사이즈
            final int[] colWidths_ex = {
                    3000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000, 5000, 5000,
                    5000, 5000, 5000
            };

            workbook.setCompressTempFiles(true);

            // *** Style--------------------------------------------------
            //Font
            Font fontHeader = workbook.createFont();
            fontHeader.setFontName("맑은 고딕");	//글씨체
            fontHeader.setFontHeight((short)(9 * 20));	//사이즈
            fontHeader.setBold(true);	//볼드(굵게)
            Font font9 = workbook.createFont();
            font9.setFontName("맑은 고딕");	//글씨체
            font9.setFontHeight((short)(9 * 20));	//사이즈

            // 엑셀 헤더 셋팅 default
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFont(fontHeader);
            headerStyle.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_GREEN)
            CellStyle headerStyle_light_green = workbook.createCellStyle();
            headerStyle_light_green.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_green.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_green.setBorderRight(BorderStyle.THIN);
            headerStyle_light_green.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_green.setBorderTop(BorderStyle.THIN);
            headerStyle_light_green.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_green.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle_light_green.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_green.setFont(fontHeader);
            headerStyle_light_green.setWrapText(true); //개행
            // 엑셀 헤더 셋팅 (LIGHT_ORANGE)
            CellStyle headerStyle_light_orange = workbook.createCellStyle();
            headerStyle_light_orange.setAlignment(HorizontalAlignment.CENTER);
            headerStyle_light_orange.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle_light_orange.setBorderRight(BorderStyle.THIN);
            headerStyle_light_orange.setBorderLeft(BorderStyle.THIN);
            headerStyle_light_orange.setBorderTop(BorderStyle.THIN);
            headerStyle_light_orange.setBorderBottom(BorderStyle.THIN);
            headerStyle_light_orange.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
            headerStyle_light_orange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle_light_orange.setFont(fontHeader);
            headerStyle_light_orange.setWrapText(true); //개행
            // 엑셀 바디 셋팅 default
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setAlignment(HorizontalAlignment.CENTER);
            bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            bodyStyle.setBorderRight(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setFont(font9);
            bodyStyle.setWrapText(true); //개행

            //rows
            int rowCnt = 0;

            // *** Sheet-------------------------------------------------
            // Sheet 생성
            SXSSFSheet sheet = workbook.createSheet("Electro");

            SXSSFCell cell = null;
            SXSSFRow row = sheet.createRow(rowCnt++);

            // 헤더 정보 구성
            // 기본/신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
            SXSSFCell mergeCell = row.createCell(0);
            mergeCell.setCellStyle(headerStyle);
            mergeCell.setCellValue("기본정보");

            // 신청정보
            sheet.addMergedRegion(new CellRangeAddress(0,0,12,15));
            SXSSFCell mergeCell2 = row.createCell(12);
            mergeCell2.setCellStyle(headerStyle_light_green);
            mergeCell2.setCellValue("신청정보");

            sheet.addMergedRegion(new CellRangeAddress(0,0,16,17));
            SXSSFCell mergeCell2_2 = row.createCell(16);
            mergeCell2_2.setCellStyle(headerStyle);
            mergeCell2_2.setCellValue("기본정보");

            row = sheet.createRow(rowCnt++);
            for (int i = 0; i < colNames_ex.length; i++) {
                cell = row.createCell(i);
                if(i<12){
                    cell.setCellStyle(headerStyle);
                }else if(i<16){
                    cell.setCellStyle(headerStyle_light_green);
                }else{
                    cell.setCellStyle(headerStyle);
                }

                cell.setCellValue(colNames_ex[i]);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(colWidths_ex[i]) + 1024));	//column width 지정
            }

            // 데이터 조회
            List<ElectroDetailDTO> detailList = eduMarineMngService.processSelectExcelElectroDetailList();

            int cellCnt = 0;
            int listCount = detailList.size();

            //데이터 부분 생성
            for(ElectroDetailDTO info : detailList) {
                cellCnt = 0;
                row = sheet.createRow(rowCnt++);

                int nCount = 0;
                String[] remark = info.getNameKo().split("\\^");

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

                // 상태
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getMemberStatus());

                // 등급
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getApplyStatus());

                // 아이디
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getId());

                // 성명(국문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameKo());

                // 성명(영문)
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getNameEn());

                // 연락처
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getPhone());

                // 이메일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getEmail());

                // 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getBirthYear() + "-" + info.getBirthMonth() + "-" + info.getBirthDay());

                // 성별
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getSex());

                // 주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddress());

                // 상세주소
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getAddressDetail());

                // 날짜 선택
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getChoiceDate());

                // 참여 경로
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getParticipationPath());

                // 추천인
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRecommendPerson());

                // 추천인 생년월일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getRcBirthYear() + "-" + info.getRcBirthMonth() + "-" + info.getRcBirthDay());

                // 등록일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getInitRegiDttm());

                // 수정일
                cell = row.createCell(cellCnt++);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(info.getFinalRegiDttm());

            }

            //너비를 자동으로 다시 설정
            for (int i = 0; i < colNames_ex.length; i++) {
                sheet.trackColumnForAutoSizing(i);
                sheet.setColumnWidth(i, Math.min(255*256, sheet.getColumnWidth(i) + 1024));
            }

            // excel 파일 저장
            res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 엑셀 파일명 설정
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(res.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private String convertValue(String[] splitVal, int index){
        int length = splitVal.length;
        String returnVal = "";
        if(index < length){
            returnVal = splitVal[index] == null ? "" : splitVal[index];
        }
        return returnVal;
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

    /*********************** mail/sms send ***********************/

    @RequestMapping(value = "/mail/send.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mail_send(@RequestBody MailRequestDTO mailRequestDTO) {
        System.out.println("EduMarineMngController > mail_send");
        System.out.println(mailRequestDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processMailSend(mailRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/sms/send.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SmsResponseDTO> processSmsSend(@RequestBody SmsDTO smsDTO) {
        System.out.println("EduMarineMngController > processSmsSend");
        //System.System.out.println(fileDTO.toString());

        SmsResponseDTO response = commService.smsSend(smsDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/sms/send/certNum.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SmsResponseDTO> processSmsSend_certNum(@RequestBody SmsDTO smsDTO) {
        System.out.println("EduMarineMngController > processSmsSend_certNum");
        //System.System.out.println(fileDTO.toString());

        SmsResponseDTO response = commService.smsSend_certNum(smsDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/sms/send/notify/getContent.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> sms_send_notify_getContent(@RequestBody SmsNotificationDTO smsNotificationDTO) {
        System.out.println("EduMarineMngController > sms_send_notify_getContent");
        //System.System.out.println(fileDTO.toString());

        String response = commService.smsSendNotifyContent(smsNotificationDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/sms/send/notify/sending.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> sms_send_notify_sending(@RequestBody SmsNotificationDTO smsNotificationDTO) {
        System.out.println("EduMarineMngController > sms_send_notify_sending");
        //System.System.out.println(fileDTO.toString());
        String result = commService.smsSendNotifySending(smsNotificationDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 14 * * ?", zone = "Asia/Seoul") /* 초 분 시 일 월 요일 */
    @GetMapping("/sms/send/notify/train.do")
    public void sms_send_notify_train() {
        System.out.println("EduMarineMngController > sms_send_notify_train");
        /*commService.insertData();*/
        // 수업 개설일 2일 전 SMS Noti 발송
        // 매 14시에 대상 조회 후 발송

        String targetNum = "7";
        SmsNotificationDTO smsNotiReq = new SmsNotificationDTO();
        smsNotiReq.setTarget(targetNum); // note
        String smsNotiContent = commService.smsSendNotifyContent(smsNotiReq);

        SmsNotificationDTO smsNotificationDTO = new SmsNotificationDTO();
        smsNotificationDTO.setTarget(targetNum);
        smsNotificationDTO.setContent(smsNotiContent);
        commService.smsSendNotifySending(smsNotificationDTO);
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul") /* 초 분 시 일 월 요일 */
    @GetMapping("/member/grade/update.do")
    public void member_grade_update() {
        System.out.println("EduMarineMngController > member_grade_update");
        commService.updateMemberGrade();
    }

    @RequestMapping(value = "/train/selectNextTime.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> mng_education_train_selectNextTime(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_selectNextTime");
        //System.System.out.println(searchDTO.toString());

        List<TrainDTO> responseList = eduMarineMngService.processSelectTrainNextTime(trainDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/train/active.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> mng_education_train_active(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_active");
        //System.System.out.println(searchDTO.toString());

        List<TrainDTO> responseList = eduMarineMngService.processSelectTrainActive(trainDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/train/change/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_train_change_update(@RequestBody TrainUpdateDTO trainUpdateDTO) {
        System.out.println("EduMarineMngController > mng_customer_train_change_update");

        ResponseDTO responseDTO = eduMarineMngService.processUpdateTrainChange(trainUpdateDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}