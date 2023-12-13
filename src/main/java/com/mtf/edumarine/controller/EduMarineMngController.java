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
        //System.out.println(searchDTO.toString());

        List<PressDTO> responseList = eduMarineMngService.processSelectPressList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/press/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PressDTO> mng_board_press_selectSingle(@RequestBody PressDTO pressDTO) {
        System.out.println("EduMarineMngController > mng_board_press_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<GalleryDTO> responseList = eduMarineMngService.processSelectGalleryList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/gallery/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GalleryDTO> mng_board_gallery_selectSingle(@RequestBody GalleryDTO galleryDTO) {
        System.out.println("EduMarineMngController > mng_board_gallery_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<MediaDTO> responseList = eduMarineMngService.processSelectMediaList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/media/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MediaDTO> mng_board_media_selectSingle(@RequestBody MediaDTO mediaDTO) {
        System.out.println("EduMarineMngController > mng_board_media_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateMedia(mediaDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/media/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_board_media_insert(@RequestBody MediaDTO mediaDTO) {
        System.out.println("EduMarineMngController > mng_board_media_insert");
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<NewsletterDTO> responseList = eduMarineMngService.processSelectNewsletterList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/newsletter/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<NewsletterDTO> mng_board_newsletter_selectSingle(@RequestBody NewsletterDTO newsletterDTO) {
        System.out.println("EduMarineMngController > mng_board_newsletter_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<AnnouncementDTO> responseList = eduMarineMngService.processSelectAnnouncementList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/announcement/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnnouncementDTO> mng_board_announcement_selectSingle(@RequestBody AnnouncementDTO announcementDTO) {
        System.out.println("EduMarineMngController > mng_board_announcement_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<JobDTO> responseList = eduMarineMngService.processSelectJobList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/job/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JobDTO> mng_board_job_selectSingle(@RequestBody JobDTO jobDTO) {
        System.out.println("EduMarineMngController > mng_board_job_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<CommunityDTO> responseList = eduMarineMngService.processSelectCommunityList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/board/community/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommunityDTO> mng_board_community_selectSingle(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineMngController > mng_board_community_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePopup(popupDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/popup/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_popup_insert(@RequestBody PopupDTO popupDTO) {
        System.out.println("EduMarineMngController > mng_pop_popup_insert");
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<BannerDTO> responseList = eduMarineMngService.processSelectBannerList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/banner/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BannerDTO> mng_pop_banner_selectSingle(@RequestBody BannerDTO bannerDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateBanner(bannerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/pop/banner/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_pop_banner_insert(@RequestBody BannerDTO bannerDTO) {
        System.out.println("EduMarineMngController > mng_pop_banner_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertBanner(bannerDTO);

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
        //System.out.println(searchDTO.toString());

        List<MemberDTO> responseList = eduMarineMngService.processSelectMemberList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/member/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MemberDTO> mng_customer_member_selectSingle(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/member/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_member_insert(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineMngController > mng_customer_member_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
        //System.out.println(searchDTO.toString());

        List<RegularDTO> responseList = eduMarineMngService.processSelectRegularList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RegularDTO> mng_customer_regular_selectSingle(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_selectSingle");
        //System.out.println(searchDTO.toString());

        RegularDTO response = eduMarineMngService.processSelectRegularSingle(regularDTO);

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
        }

        mv.setViewName("/mng/customer/regular/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/regular/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_update(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/regular/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_regular_insert(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineMngController > mng_customer_regular_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder.do", method = RequestMethod.GET)
    public ModelAndView mng_customer_inboarder() {
        System.out.println("EduMarineMngController > mng_customer_inboarder");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/customer/inboarder");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/inboarder/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<InboarderDTO>> mng_customer_inboarder_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_selectList");
        //System.out.println(searchDTO.toString());

        List<InboarderDTO> responseList = eduMarineMngService.processSelectInboarderList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InboarderDTO> mng_customer_inboarder_selectSingle(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_selectSingle");
        //System.out.println(searchDTO.toString());

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

        if(seq != null && !"".equals(seq)){
            InboarderDTO inboarderDTO = new InboarderDTO();
            inboarderDTO.setSeq(seq);
            InboarderDTO info = eduMarineMngService.processSelectInboarderSingle(inboarderDTO);
            mv.addObject("info", info);
        }

        mv.setViewName("/mng/customer/inboarder/detail");
        return mv;
    }

    @RequestMapping(value = "/mng/customer/inboarder/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_update(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/customer/inboarder/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_customer_inboarder_insert(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineMngController > mng_customer_inboarder_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train.do", method = RequestMethod.GET)
    public ModelAndView mng_education_train() {
        System.out.println("EduMarineMngController > mng_education_train");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mng/education/train");
        return mv;
    }

    @RequestMapping(value = "/mng/education/train/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> mng_education_train_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineMngController > mng_education_train_selectList");
        //System.out.println(searchDTO.toString());

        List<TrainDTO> responseList = eduMarineMngService.processSelectTrainList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TrainDTO> mng_education_train_selectSingle(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_selectSingle");
        //System.out.println(searchDTO.toString());

        TrainDTO response = eduMarineMngService.processSelectTrainSingle(trainDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/detail.do", method = RequestMethod.GET)
    public ModelAndView mng_education_train_detail(String seq) {
        System.out.println("EduMarineMngController > mng_education_train_detail");
        ModelAndView mv = new ModelAndView();

        if(seq != null && !"".equals(seq)){
            TrainDTO trainDTO = new TrainDTO();
            trainDTO.setSeq(seq);
            TrainDTO info = eduMarineMngService.processSelectTrainSingle(trainDTO);
            mv.addObject("info", info);
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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateTrain(trainDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/train/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_train_insert(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineMngController > mng_education_train_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processInsertTrain(trainDTO);

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
        //System.out.println(searchDTO.toString());

        List<PaymentDTO> responseList = eduMarineMngService.processSelectPaymentList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PaymentDTO> mng_education_payment_selectSingle(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_selectSingle");
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdatePayment(paymentDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/education/payment/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_education_payment_insert(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("EduMarineMngController > mng_education_payment_insert");
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

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
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineMngService.processUpdateSubscriber(subscriberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/newsletter/subscriber/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_newsletter_subscriber_insert(@RequestBody SubscriberDTO subscriberDTO) {
        System.out.println("EduMarineMngController > mng_newsletter_subscriber_insert");
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<SmsDTO> responseList = eduMarineMngService.processSelectSmsList(searchDTO);

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

    @RequestMapping(value = "/mng/smsMng/sms/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_smsMng_sms_insert(@RequestBody SmsDTO smsDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_insert");
        //System.out.println(noticeDTO.toString());

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
        //System.out.println(searchDTO.toString());

        List<TemplateDTO> responseList = eduMarineMngService.processSelectSmsTemplateList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/template/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TemplateDTO> mng_smsMng_sms_template_selectList(@RequestBody TemplateDTO templateDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_template_selectList");
        //System.out.println(searchDTO.toString());

        TemplateDTO response = eduMarineMngService.processSelectSmsTemplateSingle(templateDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/mng/smsMng/sms/template/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mng_smsMng_sms_template_save(@RequestBody TemplateDTO templateDTO) {
        System.out.println("EduMarineMngController > mng_smsMng_sms_template_save");
        //System.out.println(noticeDTO.toString());

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
                path = ResourceUtils.getFile("./usr/local/tomcat/webapps/ROOT/WEB-INF/classes/static/img/" + gbn + "/").toPath().toString();
            }else{
                /* prod */
                path = ResourceUtils.getFile("./usr/local/tomcat/webapps/upload/" + gbn + "/").toPath().toString();
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
            file_repo = ResourceUtils.getFile("./usr/local/tomcat/webapps/ROOT/WEB-INF/classes/static/img/" + path + "/").toPath().toString();
        }else {
            // 파일 업로드된 경로
            file_repo = ResourceUtils.getFile("./usr/local/tomcat/webapps/upload/" + path + "/").toPath().toString();
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
        //System.out.println(fileDTO.toString());

        SmsResponseDTO response = commService.smsSend(smsDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}