package com.mtf.edumarine.controller;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EduMarineController {

    // 필드 주입이 아닌 생성자 주입형태로 사용합니다. '생성자 주입 형태'로 사용합니다.
    private final EduMarineService eduMarineService;

    private final CommService commService;

    public EduMarineController(EduMarineService ks, CommService cs){
        this.eduMarineService = ks;
        this.commService = cs;
    }

    //***************************************************************************
    // Home
    //***************************************************************************

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() {
        System.out.println("EduMarineController > home : ======");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session, ModelAndView mv) {
        System.out.println("EduMarineController > logout");
        eduMarineService.logoutCheck(session);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public ModelAndView main() {
        System.out.println("EduMarineController > main");
        ModelAndView mv = new ModelAndView();

        /* 방문자 수 카운트 */
        eduMarineService.processStatisticsAccessor();

        /* 팝업파일정보 */
        PopupDTO popupDTO = new PopupDTO();
        popupDTO.setUseYn("Y");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = dateFormat.format(new Date());
        popupDTO.setToday(today);
        List<PopupDTO> popupList = eduMarineService.processSelectPopupList(popupDTO);
        mv.addObject("popupList", popupList);

        /* 배너 */
        BannerDTO bannerDTO = new BannerDTO();
        List<BannerDTO> bannerList = eduMarineService.processSelectBannerList(bannerDTO);
        List<FileDTO> bannerFileList = new ArrayList<>();
        if(bannerList != null){
            for(int i=0; i<bannerList.size(); i++){
                String fileIdList = bannerList.get(i).getFileIdList();
                if(fileIdList != null){
                    String[] fileIdSplit = bannerList.get(i).getFileIdList().split(",");
                    for(int j=0; j<fileIdSplit.length; j++){
                        FileDTO fileReq = new FileDTO();
                        fileReq.setId(fileIdSplit[j]);
                        FileDTO fileDTO = eduMarineService.processSelectFileIdSingle(fileReq);
                        bannerFileList.add(fileDTO);
                    }
                }
            }
        }
        mv.addObject("bannerList", bannerFileList);

        /* 교육과정 */
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setGbn("해상엔진 테크니션");
        List<TrainDTO> trainList = eduMarineService.processSelectTrainList(trainDTO);
        mv.addObject("trainList", trainList);

        /* 공지사항 */
        NoticeDTO noticeDTO = new NoticeDTO();
        List<NoticeDTO> noticeList = eduMarineService.processSelectNoticeList(noticeDTO);
        mv.addObject("noticeList", noticeList);

        /* 보도자료 */
        PressDTO pressDTO = new PressDTO();
        List<PressDTO> pressList = eduMarineService.processSelectPressList(pressDTO);
        mv.addObject("pressList", pressList);

        mv.setViewName("main");
        return mv;
    }

    @RequestMapping(value = "/sitemap.do", method = RequestMethod.GET)
    public ModelAndView sitemap() {
        System.out.println("EduMarineController > sitemap");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sitemap");
        return mv;
    }

    @RequestMapping(value = "/search.do", method = RequestMethod.GET)
    public ModelAndView search() {
        System.out.println("EduMarineController > search");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search");
        return mv;
    }

    //***************************************************************************
    // member Folder
    //***************************************************************************

    @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
    public ModelAndView member_login() {
        System.out.println("EduMarineController > member_login");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/login");
        return mv;
    }

    @RequestMapping(value = "/member/login/submit.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_login_submit(@RequestBody MemberDTO memberDTO, HttpSession session) {
        System.out.println("EduMarineController > member_login_submit");
        //System.out.println(searchDTO.toString());
        ResponseDTO response = eduMarineService.processCheckMemberSingle(memberDTO);
        if(response.getResultCode().equals("0")){
            session.setAttribute("status", "logon");
            session.setAttribute("id", memberDTO.getId());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/findpw.do", method = RequestMethod.GET)
    public ModelAndView member_findpw() {
        System.out.println("EduMarineController > member_findpw");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/findpw");
        return mv;
    }

    @RequestMapping(value = "/member/getEmail.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> member_getEmail(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > member_getEmail");
        String result = eduMarineService.getMemberEmail(memberDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/initPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_initPassword(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > member_initPassword");
        ResponseDTO response = eduMarineService.initMemberPassword(memberDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/join.do", method = RequestMethod.GET)
    public ModelAndView member_join() {
        System.out.println("EduMarineController > member_join");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/join");
        return mv;
    }

    @RequestMapping(value = "/member/join/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_join_insert(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > member_join_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/complete.do", method = RequestMethod.GET)
    public ModelAndView member_complete() {
        System.out.println("EduMarineController > member_complete");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/complete");
        return mv;
    }

    @RequestMapping(value = "/checkDuplicateId.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> checkDuplicateId(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > checkDuplicateId");
        //System.out.println(searchDTO.toString());
        Integer result = eduMarineService.checkDuplicateId(memberDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //***************************************************************************
    // apply Folder
    //***************************************************************************

    @RequestMapping(value = "/apply/schedule.do", method = RequestMethod.GET)
    public ModelAndView apply_schedule() {
        System.out.println("EduMarineController > apply_schedule");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/schedule");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply01.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply01() {
        System.out.println("EduMarineController > apply_eduApply01");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/eduApply01");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply02.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply02() {
        System.out.println("EduMarineController > apply_eduApply02");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/eduApply02");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply03.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply03() {
        System.out.println("EduMarineController > apply_eduApply03");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/eduApply03");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply04.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply04() {
        System.out.println("EduMarineController > apply_eduApply04");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/eduApply04");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply05.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply05() {
        System.out.println("EduMarineController > apply_eduApply05");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/eduApply05");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply06.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply06() {
        System.out.println("EduMarineController > apply_eduApply06");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/eduApply06");
        return mv;
    }

    //***************************************************************************
    // board Folder
    //***************************************************************************

    @RequestMapping(value = "/board/notice_list.do", method = RequestMethod.GET)
    public ModelAndView board_notice_list() {
        System.out.println("EduMarineController > board_notice_list");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/notice_list");
        return mv;
    }

    @RequestMapping(value = "/board/notice/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<NoticeDTO>> board_notice_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > board_notice_selectList");

        List<NoticeDTO> responseList = eduMarineService.processSelectBoardNoticeList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/notice_view.do", method = RequestMethod.GET)
    public ModelAndView board_notice_view(String seq) {
        System.out.println("EduMarineController > board_notice_view");
        ModelAndView mv = new ModelAndView();

        /* 조회 카운트 Update */
        eduMarineService.processUpdateBoardNoticeViewCnt(seq);

        /* 데이터 조회 후 Set */
        NoticeDTO noticeInfo = eduMarineService.processSelectBoardNoticeSingle(seq);

        if(noticeInfo != null){

            mv.addObject("noticeInfo", noticeInfo);

            /* 첨부파일 정보 Set */
            List<FileDTO> fileList = eduMarineService.processSelectFileList(noticeInfo.getSeq());
            if(fileList != null && !fileList.isEmpty()){
                mv.addObject("fileList", fileList);
            }
        }

        mv.setViewName("/board/notice_view");
        return mv;
    }

    @RequestMapping(value = "/board/press_list.do", method = RequestMethod.GET)
    public ModelAndView board_press_list() {
        System.out.println("EduMarineController > board_press_list");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/press_list");
        return mv;
    }

    @RequestMapping(value = "/board/press/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<PressDTO>> board_press_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > board_press_selectList");

        List<PressDTO> responseList = eduMarineService.processSelectBoardPressList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/press_view.do", method = RequestMethod.GET)
    public ModelAndView board_press_view(String seq) {
        System.out.println("EduMarineController > board_press_view");
        ModelAndView mv = new ModelAndView();

        /* 조회 카운트 Update */
        eduMarineService.processUpdateBoardPressViewCnt(seq);

        /* 데이터 조회 후 Set */
        PressDTO pressInfo = eduMarineService.processSelectBoardPressSingle(seq);

        if(pressInfo != null){

            mv.addObject("pressInfo", pressInfo);

            /* 첨부파일 정보 Set */
            List<FileDTO> fileList = eduMarineService.processSelectFileList(pressInfo.getSeq());
            if(fileList != null && !fileList.isEmpty()){
                mv.addObject("fileList", fileList);
            }
        }

        mv.setViewName("/board/press_view");
        return mv;
    }

    @RequestMapping(value = "/board/gallery.do", method = RequestMethod.GET)
    public ModelAndView board_gallery() {
        System.out.println("EduMarineController > board_gallery");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/gallery");
        return mv;
    }

    @RequestMapping(value = "/board/gallery/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<GalleryDTO>> board_gallery_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > board_gallery_selectList");

        List<GalleryDTO> responseList = eduMarineService.processSelectBoardGalleryList(searchDTO);

        for(GalleryDTO response : responseList){
            List<String> fullFilePathList = new ArrayList<>();
            List<FileDTO> fileList = eduMarineService.processSelectFileList(response.getSeq());
            for(FileDTO file : fileList){
                fullFilePathList.add(file.getFullFilePath());
            }
            response.setFullFilePathList(fullFilePathList);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/media.do", method = RequestMethod.GET)
    public ModelAndView board_media() {
        System.out.println("EduMarineController > board_media");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/media");
        return mv;
    }

    @RequestMapping(value = "/board/media/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<MediaDTO>> board_media_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > board_media_selectList");

        List<MediaDTO> responseList = eduMarineService.processSelectBoardMediaList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/news_list.do", method = RequestMethod.GET)
    public ModelAndView board_news_list() {
        System.out.println("EduMarineController > board_news_list");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/news_list");
        return mv;
    }

    @RequestMapping(value = "/board/news/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<NewsletterDTO>> board_news_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > board_news_selectList");

        List<NewsletterDTO> responseList = eduMarineService.processSelectBoardNewsList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/news_view.do", method = RequestMethod.GET)
    public ModelAndView board_news_view(String seq) {
        System.out.println("EduMarineController > board_news_view");
        ModelAndView mv = new ModelAndView();

        /* 조회 카운트 Update */
        eduMarineService.processUpdateBoardNewsViewCnt(seq);

        /* 데이터 조회 후 Set */
        NewsletterDTO newsInfo = eduMarineService.processSelectBoardNewsSingle(seq);

        if(newsInfo != null){

            mv.addObject("newsInfo", newsInfo);

            /* 첨부파일 정보 Set */
            List<FileDTO> fileList = eduMarineService.processSelectFileList(newsInfo.getSeq());
            if(fileList != null && !fileList.isEmpty()){
                mv.addObject("fileList", fileList);
            }
        }
        mv.setViewName("/board/news_view");
        return mv;
    }

    //***************************************************************************
    // job Folder
    //***************************************************************************

    @RequestMapping(value = "/job/announcement_list.do", method = RequestMethod.GET)
    public ModelAndView job_announcement_list() {
        System.out.println("EduMarineController > job_announcement_list");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/announcement_list");
        return mv;
    }

    @RequestMapping(value = "/job/announcement_view.do", method = RequestMethod.GET)
    public ModelAndView job_announcement_view() {
        System.out.println("EduMarineController > job_announcement_view");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/announcement_view");
        return mv;
    }

    @RequestMapping(value = "/job/state01.do", method = RequestMethod.GET)
    public ModelAndView job_state01() {
        System.out.println("EduMarineController > job_state01");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/state01");
        return mv;
    }

    @RequestMapping(value = "/job/state02.do", method = RequestMethod.GET)
    public ModelAndView job_state02() {
        System.out.println("EduMarineController > job_state02");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/state02");
        return mv;
    }

    @RequestMapping(value = "/job/review.do", method = RequestMethod.GET)
    public ModelAndView job_review() {
        System.out.println("EduMarineController > job_review");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/review");
        return mv;
    }

    @RequestMapping(value = "/job/review/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<JobDTO>> job_review_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > job_review_selectList");

        List<JobDTO> responseList = eduMarineService.processSelectJobReviewList(searchDTO);

        for(JobDTO response : responseList){
            List<String> fullFilePathList = new ArrayList<>();
            List<FileDTO> fileList = eduMarineService.processSelectFileList(response.getSeq());
            for(FileDTO file : fileList){
                fullFilePathList.add(file.getFullFilePath());
            }
            response.setFullFilePathList(fullFilePathList);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_list.do", method = RequestMethod.GET)
    public ModelAndView job_community_list() {
        System.out.println("EduMarineController > job_community_list");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/community_list");
        return mv;
    }

    @RequestMapping(value = "/job/community_view.do", method = RequestMethod.GET)
    public ModelAndView job_community_view() {
        System.out.println("EduMarineController > job_community_view");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/community_view");
        return mv;
    }

    @RequestMapping(value = "/job/community_write.do", method = RequestMethod.GET)
    public ModelAndView job_community_write() {
        System.out.println("EduMarineController > job_community_write");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/community_write");
        return mv;
    }

    @RequestMapping(value = "/job/community_modify.do", method = RequestMethod.GET)
    public ModelAndView job_community_modify() {
        System.out.println("EduMarineController > job_community_modify");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/community_modify");
        return mv;
    }

    //***************************************************************************
    // mypage Folder
    //***************************************************************************

    @RequestMapping(value = "/mypage/eduApplyInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mypage_eduApplyInfo(HttpSession session) {
        System.out.println("EduMarineController > mypage_eduApplyInfo");
        ModelAndView mv = new ModelAndView();
        String id = String.valueOf(session.getAttribute("id"));
        mv.setViewName("/mypage/eduApplyInfo");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduPayInfo.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduPayInfo() {
        System.out.println("EduMarineController > mypage_eduPayInfo");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mypage/eduPayInfo");
        return mv;
    }

    @RequestMapping(value = "/mypage/resume.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView mypage_resume(HttpSession session) {
        System.out.println("EduMarineController > mypage_resume");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            String id = session.getAttribute("id").toString();
            ResumeDTO info = eduMarineService.processSelectResumeSingle(id);
            mv.addObject("info", info);

            if(info != null){
                /* 첨부파일 정보 Set */
                List<FileDTO> bodyPhotoFile = eduMarineService.processSelectFileList(info.getSeq());
                if(bodyPhotoFile != null  && !bodyPhotoFile.isEmpty()){
                    for (FileDTO fileDTO : bodyPhotoFile) {
                        if ("bodyPhoto".equals(fileDTO.getNote())) {
                            mv.addObject("bodyPhotoFile", fileDTO);
                        }
                    }
                }
            }
        }

        mv.setViewName("/mypage/resume");
        return mv;
    }

    @RequestMapping(value = "/mypage/resume/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_resume_save(@RequestBody ResumeDTO resumeDTO) {
        System.out.println("EduMarineController > mypage_resume_save");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processSaveResume(resumeDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/post.do", method = RequestMethod.GET)
    public ModelAndView mypage_post() {
        System.out.println("EduMarineController > mypage_post");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mypage/post");
        return mv;
    }

    @RequestMapping(value = "/mypage/modify.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView mypage_modify(HttpSession session) {
        System.out.println("EduMarineController > mypage_modify");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);
        }

        mv.setViewName("/mypage/modify");
        return mv;
    }

    @RequestMapping(value = "/member/modify/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_modify_update(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > member_modify_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply01_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply01_modify() {
        System.out.println("EduMarineController > mypage_eduApply01_modify");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mypage/eduApply01_modify");
        return mv;
    }

    //***************************************************************************
    // edumarine Folder
    //***************************************************************************

    @RequestMapping(value = "/edumarine/introduce.do", method = RequestMethod.GET)
    public ModelAndView edumarine_introduce() {
        System.out.println("EduMarineController > edumarine_introduce");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/introduce");
        return mv;
    }

    @RequestMapping(value = "/edumarine/overview.do", method = RequestMethod.GET)
    public ModelAndView edumarine_overview() {
        System.out.println("EduMarineController > edumarine_overview");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/overview");
        return mv;
    }

    @RequestMapping(value = "/edumarine/current.do", method = RequestMethod.GET)
    public ModelAndView edumarine_current() {
        System.out.println("EduMarineController > edumarine_current");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/current");
        return mv;
    }

    @RequestMapping(value = "/edumarine/necessity.do", method = RequestMethod.GET)
    public ModelAndView edumarine_necessity() {
        System.out.println("EduMarineController > edumarine_necessity");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/necessity");
        return mv;
    }

    @RequestMapping(value = "/edumarine/sponsorship.do", method = RequestMethod.GET)
    public ModelAndView edumarine_sponsorship() {
        System.out.println("EduMarineController > edumarine_sponsorship");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/sponsorship");
        return mv;
    }

    @RequestMapping(value = "/edumarine/way.do", method = RequestMethod.GET)
    public ModelAndView edumarine_way() {
        System.out.println("EduMarineController > edumarine_way");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/way");
        return mv;
    }

    //***************************************************************************
    // guide Folder
    //***************************************************************************

    @RequestMapping(value = "/guide/guide01.do", method = RequestMethod.GET)
    public ModelAndView guide_guide01() {
        System.out.println("EduMarineController > guide_guide01");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide01");
        return mv;
    }

    @RequestMapping(value = "/guide/guide02.do", method = RequestMethod.GET)
    public ModelAndView guide_guide02() {
        System.out.println("EduMarineController > guide_guide02");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide02");
        return mv;
    }

    @RequestMapping(value = "/guide/guide03.do", method = RequestMethod.GET)
    public ModelAndView guide_guide03() {
        System.out.println("EduMarineController > guide_guide03");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide03");
        return mv;
    }

    @RequestMapping(value = "/guide/guide04.do", method = RequestMethod.GET)
    public ModelAndView guide_guide04() {
        System.out.println("EduMarineController > guide_guide04");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide04");
        return mv;
    }

    @RequestMapping(value = "/guide/guide05.do", method = RequestMethod.GET)
    public ModelAndView guide_guide05() {
        System.out.println("EduMarineController > guide_guide05");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide05");
        return mv;
    }

    @RequestMapping(value = "/guide/guide06.do", method = RequestMethod.GET)
    public ModelAndView guide_guide06() {
        System.out.println("EduMarineController > guide_guide06");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide06");
        return mv;
    }

    @RequestMapping(value = "/guide/guide07.do", method = RequestMethod.GET)
    public ModelAndView guide_guide07() {
        System.out.println("EduMarineController > guide_guide07");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide07");
        return mv;
    }

    @RequestMapping(value = "/guide/guide08.do", method = RequestMethod.GET)
    public ModelAndView guide_guide08() {
        System.out.println("EduMarineController > guide_guide08");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide08");
        return mv;
    }

    //***************************************************************************
    // Common
    //***************************************************************************

    /**
     * Upload file response entity.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity uploadFile(MultipartFile[] uploadFiles, String uploadFilePath) {

        //System.out.println(uploadFilePath); // exhibitor/{id}
        System.out.println(uploadFiles.toString());

        JSONObject obj = new JSONObject();
        ResponseDTO response = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        if(uploadFiles != null) {
            for (MultipartFile multipartFile : uploadFiles) {
                try {
                    /* Prod */
                    String path = ResourceUtils.getFile("./usr/local/tomcat/webapps/upload/" + uploadFilePath).toPath().toString();

                    /* Local */
                    //String path = ResourceUtils.getFile("C:/Users/slhge/project/Kibs/upload/" + uploadFilePath).toPath().toString();

                    File folder = new File(path);

                    // 해당 디렉토리가 없을경우 디렉토리를 생성
                    if (!folder.exists()) {
                        Files.createDirectories(folder.toPath());
                    }

                    String fileName = generateFileName(multipartFile);
                    File tmp = new File(path + "/" + fileName);
                    multipartFile.transferTo(tmp);
                } catch (Exception e) {
                    String eMessage = "[UPLOAD] Error : ";
                    resultCode = CommConstants.RESULT_CODE_FAIL;
                    resultMessage = String.format("%s - %s", eMessage, e.getMessage() == null ? "" : e.getMessage());
                }
            }
        }

        response.setResultCode(resultCode);
        response.setResultMessage(resultMessage);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    private String generateFileName(MultipartFile multipartFile) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return new SimpleDateFormat("yyyyMMdd").format(date)+"_"+multipartFile.getOriginalFilename();
    }

    public HashMap<String, Object> convertMap(HttpServletRequest request) {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        String key;
        Enumeration<?> en = request.getParameterNames();
        while ( en.hasMoreElements()){
            key = (String) en.nextElement();
            if (request.getParameterValues(key).length > 1) {
                hm.put(key, request.getParameterValues(key));
            } else {
                hm.put(key, request.getParameter(key));
            }
        }
        return hm;
    }

    @RequestMapping(value="/sitemap.xml", produces= {"application/xml"})
    @ResponseBody
    public ResponseEntity<String> sitemap (HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(commService.getSystemicSiteMap());
    }

    /**
     * Robots string.
     *
     * @return the string
     */
    /* robots.txt */
    @RequestMapping(value = "/robots.txt")
    @ResponseBody
    public String robots() {
        return "User-agent: *\nAllow: /\n";
    }

}
