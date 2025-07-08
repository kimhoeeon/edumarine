package com.mtf.edumarine.controller;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

        /* 교육 접수/일정 마감 체크 */
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
        String todate = dateFormat1.format(new Date());
        eduMarineService.processUpdateTrainClosing(todate);

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
        TrainDTO engineDTO = new TrainDTO();
        engineDTO.setGbn("해상엔진 테크니션");
        List<TrainDTO> engineList = eduMarineService.processSelectTrainList(engineDTO);
        mv.addObject("engineList", engineList);

        TrainDTO frpDTO = new TrainDTO();
        frpDTO.setGbn("FRP");
        List<TrainDTO> frpList = eduMarineService.processSelectTrainList(frpDTO);
        mv.addObject("frpList", frpList);

        TrainDTO basicDTO = new TrainDTO();
        basicDTO.setGbn("기초정비교육");
        List<TrainDTO> basicList = eduMarineService.processSelectTrainList(basicDTO);
        mv.addObject("basicList", basicList);

        TrainDTO emergencyDTO = new TrainDTO();
        emergencyDTO.setGbn("응급조치교육");
        List<TrainDTO> emergencyList = eduMarineService.processSelectTrainList(emergencyDTO);
        mv.addObject("emergencyList", emergencyList);

        TrainDTO marinaInDTO = new TrainDTO();
        marinaInDTO.setGbn("마리나 선박 선내기");
        List<TrainDTO> marinaInList = eduMarineService.processSelectTrainList(marinaInDTO);

        TrainDTO marinaOutDTO = new TrainDTO();
        marinaOutDTO.setGbn("마리나 선박 선외기");
        List<TrainDTO> marinaOutList = eduMarineService.processSelectTrainList(marinaOutDTO);

        marinaInList.addAll(marinaOutList);

        mv.addObject("marinaList", marinaInList);

        TrainDTO highHorsePowerDTO = new TrainDTO();
        highHorsePowerDTO.setGbn("고마력 선외기 정비 중급 테크니션");
        List<TrainDTO> highHorsePowerList = eduMarineService.processSelectTrainList(highHorsePowerDTO);
        mv.addObject("highHorsePowerList", highHorsePowerList);

        TrainDTO highSelfDTO = new TrainDTO();
        highSelfDTO.setGbn("자가정비 심화과정 (고마력 선외기)");
        List<TrainDTO> highSelfList = eduMarineService.processSelectTrainList(highSelfDTO);
        mv.addObject("highSelfList", highSelfList);

        TrainDTO highSpecialDTO = new TrainDTO();
        highSpecialDTO.setGbn("고마력 선외기 정비 중급 테크니션 (특별반)");
        List<TrainDTO> highSpecialList = eduMarineService.processSelectTrainList(highSpecialDTO);
        mv.addObject("highSpecialList", highSpecialList);

        TrainDTO sterndriveDTO = new TrainDTO();
        sterndriveDTO.setGbn("스턴드라이브");
        List<TrainDTO> sterndriveList = eduMarineService.processSelectTrainList(sterndriveDTO);
        mv.addObject("sterndriveList", sterndriveList);

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
    public ResponseEntity<ResponseDTO> member_login_submit(@RequestBody MemberDTO memberDTO, HttpSession session/*, HttpServletResponse httpResponse*/) {
        System.out.println("EduMarineController > member_login_submit");
        //System.out.println(searchDTO.toString());
        ResponseDTO response = eduMarineService.processCheckMemberSingle(memberDTO);
        if(response.getResultCode().equals("0")){
            session.setAttribute("status", "logon");
            session.setAttribute("id", memberDTO.getId());
            /*httpResponse.setHeader("Set-Cookie", "id=" + memberDTO.getId() + ";   Secure; SameSite=None");*/
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

    @RequestMapping(value = "/member/join/member/check.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_join_member_check(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > member_join_member_check");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processCheckMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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

    @RequestMapping(value = "/apply/schedule.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView apply_schedule(String searchText) {
        System.out.println("EduMarineController > apply_schedule");
        ModelAndView mv = new ModelAndView();
        if(searchText != null && !"".equals(searchText)){
            String trainName = "";
            switch (searchText){
                case "EDU01":
                    trainName = "해상엔진 테크니션 (선내기/선외기)";
                    break;
                case "EDU04":
                    trainName = "FRP 레저보트 선체 정비 테크니션";
                    break;
                case "EDU06":
                    trainName = "선외기";
                    break;
                case "EDU07":
                    trainName = "선내기";
                    break;
                case "EDU08":
                    trainName = "세일요트";
                    break;
                case "EDU09":
                    trainName = "마리나 선박";
                    break;
                case "EDU10":
                    trainName = "고마력 선외기 정비 중급 테크니션";
                    break;
                case "EDU12":
                    trainName = "자가정비 심화과정 (고마력 선외기)";
                    break;
                case "EDU13":
                    trainName = "고마력 선외기 정비 중급 테크니션 (특별반)";
                    break;
                case "EDU11":
                    trainName = "스턴드라이브";
                    break;
                case "EDU14":
                    trainName = "기초정비교육";
                    break;
                case "EDU15":
                    trainName = "응급조치교육";
                    break;
                case "EDU16":
                    trainName = "발전기 정비 교육";
                    break;
                case "EDU17":
                    trainName = "선외기/선내기 직무역량 강화과정";
                    break;
                case "EDU18":
                    trainName = "선내기 팸투어";
                    break;
                case "EDU19":
                    trainName = "선외기 팸투어";
                    break;
                default:
                    trainName = searchText;
                    break;
            }
            mv.addObject("searchText", trainName);
        }

        mv.setViewName("/apply/schedule");
        return mv;
    }

    @RequestMapping(value = "/apply/schedule/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> apply_schedule_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > apply_schedule_selectList");
        //System.out.println(searchDTO.toString());

        List<TrainDTO> responseList = eduMarineService.processSelectTrainScheduleList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/schedule/calendar/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> apply_schedule_calendar_selectList(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineController > apply_schedule_calendar_selectList");
        List<TrainDTO> responseList = eduMarineService.processSelectTrainScheduleCalendarList(trainDTO);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply01(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply01");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply01");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply01/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply01_preCheck(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineController > apply_eduApply01_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectRegularPreCheck(regularDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply02/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply02_preCheck(@RequestBody BoarderDTO boarderDTO) {
        System.out.println("EduMarineController > apply_eduApply02_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectBoarderPreCheck(boarderDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply03/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply03_preCheck(@RequestBody FrpDTO frpDTO) {
        System.out.println("EduMarineController > apply_eduApply03_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectFrpPreCheck(frpDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply04/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply04_preCheck(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineController > apply_eduApply04_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectInboarderPreCheck(inboarderDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply05/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply05_preCheck(@RequestBody OutboarderDTO outboarderDTO) {
        System.out.println("EduMarineController > apply_eduApply05_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectOutboarderPreCheck(outboarderDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply06/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply06_preCheck(@RequestBody SailyachtDTO sailyachtDTO) {
        System.out.println("EduMarineController > apply_eduApply06_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectSailyachtPreCheck(sailyachtDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply01_insert(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineController > apply_eduApply01_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply01_update_status(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineController > apply_eduApply01_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateRegularPayStatus(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply02/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply02_update_status(@RequestBody BoarderDTO boarderDTO) {
        System.out.println("EduMarineController > apply_eduApply02_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBoarderPayStatus(boarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply03/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply03_update_status(@RequestBody FrpDTO frpDTO) {
        System.out.println("EduMarineController > apply_eduApply03_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateFrpPayStatus(frpDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply04/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply04_update_status(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineController > apply_eduApply04_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply05/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply05_update_status(@RequestBody OutboarderDTO outboarderDTO) {
        System.out.println("EduMarineController > apply_eduApply05_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply06/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply06_update_status(@RequestBody SailyachtDTO sailyachtDTO) {
        System.out.println("EduMarineController > apply_eduApply06_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply07/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply07_update_status(@RequestBody HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineController > apply_eduApply07_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateHighHorsePowerPayStatus(highHorsePowerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply08/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply08_update_status(@RequestBody SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineController > apply_eduApply08_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateSterndrivePayStatus(sterndriveDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply09/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply09_update_status(@RequestBody HighSelfDTO highSelfDTO) {
        System.out.println("EduMarineController > apply_eduApply09_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSelfPayStatus(highSelfDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply10/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply10_update_status(@RequestBody HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineController > apply_eduApply10_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply11/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply11_update_status(@RequestBody SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineController > apply_eduApply11_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply12/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply12_update_status(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply12_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBasicPayStatus(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply13/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply13_update_status(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply13_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBasicPayStatus(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply14/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply14_update_status(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply14_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBasicPayStatus(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply15/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply15_update_status(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply15_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply16/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply16_update_status(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply16_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply17/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply17_update_status(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply17_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply18/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply18_update_status(@RequestBody GeneratorDTO generatorDTO) {
        System.out.println("EduMarineController > apply_eduApply18_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateGeneratorPayStatus(generatorDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply19/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply19_update_status(@RequestBody CompetencyDTO competencyDTO) {
        System.out.println("EduMarineController > apply_eduApply19_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateCompetencyPayStatus(competencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply20/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply20_update_status(@RequestBody FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineController > apply_eduApply20_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtourinPayStatus(famtourinDTO);

        if("결제완료".equals(famtourinDTO.getApplyStatus())) {
            eduMarineService.processUpdateTrainApplyCnt(famtourinDTO.getTrainSeq());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply21/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply21_update_status(@RequestBody FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineController > apply_eduApply21_update_status");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtouroutPayStatus(famtouroutDTO);

        if("결제완료".equals(famtouroutDTO.getApplyStatus())) {
            eduMarineService.processUpdateTrainApplyCnt(famtouroutDTO.getTrainSeq());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01/pre/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RegularDTO> apply_eduApply01_pre_selectSingle(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineController > apply_eduApply01_pre_selectSingle");
        //System.out.println(searchDTO.toString());

        RegularDTO responseDTO = eduMarineService.processSelectPreRegularSingle(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply02.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply02(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply02");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);
        }

        mv.setViewName("/apply/eduApply02");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply02/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply02_insert(@RequestBody BoarderDTO boarderDTO) {
        System.out.println("EduMarineController > apply_eduApply02_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertBoarder(boarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply03.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply03(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply03");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);
        }

        mv.setViewName("/apply/eduApply03");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply03/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply03_insert(@RequestBody FrpDTO frpDTO) {
        System.out.println("EduMarineController > apply_eduApply03_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertFrp(frpDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply04.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply04(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply04");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply04");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply04/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply04_insert(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineController > apply_eduApply04_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply04_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply04_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply04_modify");
        ModelAndView mv = new ModelAndView();

        InboarderDTO info = eduMarineService.processSelectInboarderSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply04_modify");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply05.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply05(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply05");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply05");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply05/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply05_insert(@RequestBody OutboarderDTO outboarderDTO) {
        System.out.println("EduMarineController > apply_eduApply05_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertOutboarder(outboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply05_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply05_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply05_modify");
        ModelAndView mv = new ModelAndView();

        OutboarderDTO info = eduMarineService.processSelectOutboarderSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply05_modify");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply06.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply06(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply06");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply06");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply06_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply06_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply06_modify");
        ModelAndView mv = new ModelAndView();

        SailyachtDTO info = eduMarineService.processSelectSailyachtSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply06_modify");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply06/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply06_insert(@RequestBody SailyachtDTO sailyachtDTO) {
        System.out.println("EduMarineController > apply_eduApply06_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertSailyacht(sailyachtDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply07.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply07(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply07");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply07");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply07/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply07_preCheck(@RequestBody HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineController > apply_eduApply07_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectHighHorsePowerPreCheck(highHorsePowerDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply07/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply07_insert(@RequestBody HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineController > apply_eduApply07_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertHighHorsePower(highHorsePowerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply07_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply07_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply07_modify");
        ModelAndView mv = new ModelAndView();

        HighHorsePowerDTO info = eduMarineService.processSelectHighHorsePowerSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply07_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply07/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply07_update(@RequestBody HighHorsePowerDTO highHorsePowerDTO) {
        System.out.println("EduMarineController > mypage_eduApply07_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateHighhorsepower(highHorsePowerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply08.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply08(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply08");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply08");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply08/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply08_preCheck(@RequestBody SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineController > apply_eduApply08_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectSterndrivePreCheck(sterndriveDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply08/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply08_insert(@RequestBody SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineController > apply_eduApply08_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertSterndrive(sterndriveDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply08_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply08_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply08_modify");
        ModelAndView mv = new ModelAndView();

        SterndriveDTO info = eduMarineService.processSelectSterndriveSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply08_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply08/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply08_update(@RequestBody SterndriveDTO sterndriveDTO) {
        System.out.println("EduMarineController > mypage_eduApply08_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateSterndrive(sterndriveDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply09.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply09(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply09");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply09");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply09/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply09_preCheck(@RequestBody HighSelfDTO highselfDTO) {
        System.out.println("EduMarineController > apply_eduApply09_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectHighSelfPreCheck(highselfDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply09/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply09_insert(@RequestBody HighSelfDTO highselfDTO) {
        System.out.println("EduMarineController > apply_eduApply09_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertHighSelf(highselfDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply09_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply09_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply09_modify");
        ModelAndView mv = new ModelAndView();

        HighSelfDTO info = eduMarineService.processSelectHighSelfSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply09_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply09/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply09_update(@RequestBody HighSelfDTO highselfDTO) {
        System.out.println("EduMarineController > mypage_eduApply09_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSelf(highselfDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply10.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply10(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply10");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply10");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply10/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply10_preCheck(@RequestBody HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineController > apply_eduApply10_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectHighSpecialPreCheck(highSpecialDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply10/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply10_insert(@RequestBody HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineController > apply_eduApply10_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertHighSpecial(highSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply10_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply10_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply10_modify");
        ModelAndView mv = new ModelAndView();

        HighSpecialDTO info = eduMarineService.processSelectHighSpecialSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply10_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply10/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply10_update(@RequestBody HighSpecialDTO highSpecialDTO) {
        System.out.println("EduMarineController > mypage_eduApply10_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSpecial(highSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply11.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply11(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply11");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply11");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply11/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply11_preCheck(@RequestBody SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineController > apply_eduApply11_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectSternSpecialPreCheck(sternSpecialDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply11/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply11_insert(@RequestBody SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineController > apply_eduApply11_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertSternSpecial(sternSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply11_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply11_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply11_modify");
        ModelAndView mv = new ModelAndView();

        SternSpecialDTO info = eduMarineService.processSelectSternSpecialSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply11_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply11/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply11_update(@RequestBody SternSpecialDTO sternSpecialDTO) {
        System.out.println("EduMarineController > mypage_eduApply11_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateSternSpecial(sternSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply12.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply12(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply12");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply12");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply12/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply12_preCheck(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply12_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectBasicPreCheck(basicDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply12/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply12_insert(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply12_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply12_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply12_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply12_modify");
        ModelAndView mv = new ModelAndView();

        BasicDTO info = eduMarineService.processSelectBasicSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply12_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply12/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply12_update(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > mypage_eduApply12_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply13.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply13(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply13");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply13");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply13/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply13_preCheck(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply13_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectBasicPreCheck(basicDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply13/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply13_insert(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply13_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply13_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply13_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply13_modify");
        ModelAndView mv = new ModelAndView();

        BasicDTO info = eduMarineService.processSelectBasicSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply13_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply13/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply13_update(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > mypage_eduApply13_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply14.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply14(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply14");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply14");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply14/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply14_preCheck(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply14_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectBasicPreCheck(basicDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply14/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply14_insert(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > apply_eduApply14_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply14_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply14_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply14_modify");
        ModelAndView mv = new ModelAndView();

        BasicDTO info = eduMarineService.processSelectBasicSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply14_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply14/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply14_update(@RequestBody BasicDTO basicDTO) {
        System.out.println("EduMarineController > mypage_eduApply14_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply15.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply15(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply15");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply15");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply15/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply15_preCheck(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply15_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectEmergencyPreCheck(emergencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply15/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply15_insert(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply15_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply15_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply15_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply15_modify");
        ModelAndView mv = new ModelAndView();

        EmergencyDTO info = eduMarineService.processSelectEmergencySingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply15_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply15/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply15_update(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > mypage_eduApply15_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply16.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply16(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply16");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply16");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply16/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply16_preCheck(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply16_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectEmergencyPreCheck(emergencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply16/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply16_insert(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply16_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply16_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply16_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply16_modify");
        ModelAndView mv = new ModelAndView();

        EmergencyDTO info = eduMarineService.processSelectEmergencySingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply16_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply16/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply16_update(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > mypage_eduApply16_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply17.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply17(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply17");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply17");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply17/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply17_preCheck(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply17_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectEmergencyPreCheck(emergencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply17/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply17_insert(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > apply_eduApply17_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply17_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply17_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply17_modify");
        ModelAndView mv = new ModelAndView();

        EmergencyDTO info = eduMarineService.processSelectEmergencySingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply17_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply17/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply17_update(@RequestBody EmergencyDTO emergencyDTO) {
        System.out.println("EduMarineController > mypage_eduApply17_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply18.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply18(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply18");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply18");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply18/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply18_preCheck(@RequestBody GeneratorDTO generatorDTO) {
        System.out.println("EduMarineController > apply_eduApply18_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectGeneratorPreCheck(generatorDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply18/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply18_insert(@RequestBody GeneratorDTO generatorDTO) {
        System.out.println("EduMarineController > apply_eduApply18_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertGenerator(generatorDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply18_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply18_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply18_modify");
        ModelAndView mv = new ModelAndView();

        GeneratorDTO info = eduMarineService.processSelectGeneratorSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply18_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply18/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply18_update(@RequestBody GeneratorDTO generatorDTO) {
        System.out.println("EduMarineController > mypage_eduApply18_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateGenerator(generatorDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply19.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply19(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply19");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply19");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply19/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply19_preCheck(@RequestBody CompetencyDTO competencyDTO) {
        System.out.println("EduMarineController > apply_eduApply19_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectCompetencyPreCheck(competencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply19/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply19_insert(@RequestBody CompetencyDTO competencyDTO) {
        System.out.println("EduMarineController > apply_eduApply19_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertCompetency(competencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply19_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply19_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply19_modify");
        ModelAndView mv = new ModelAndView();

        CompetencyDTO info = eduMarineService.processSelectCompetencySingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply19_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply19/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply19_update(@RequestBody CompetencyDTO competencyDTO) {
        System.out.println("EduMarineController > mypage_eduApply19_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateCompetency(competencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply20.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply20(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply20");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply20");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply20/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply20_preCheck(@RequestBody FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineController > apply_eduApply20_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectFamtourinPreCheck(famtourinDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply20/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply20_insert(@RequestBody FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineController > apply_eduApply20_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertFamtourin(famtourinDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply20_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply20_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply20_modify");
        ModelAndView mv = new ModelAndView();

        FamtourinDTO info = eduMarineService.processSelectFamtourinSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply20_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply20/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply20_update(@RequestBody FamtourinDTO famtourinDTO) {
        System.out.println("EduMarineController > mypage_eduApply20_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtourin(famtourinDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply21.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply21(String seq, HttpSession session) {
        System.out.println("EduMarineController > apply_eduApply21");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply21");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply21/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply21_preCheck(@RequestBody FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineController > apply_eduApply21_preCheck");
        //System.out.println(noticeDTO.toString());

        Integer result = eduMarineService.processSelectFamtouroutPreCheck(famtouroutDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply21/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply21_insert(@RequestBody FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineController > apply_eduApply21_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertFamtourout(famtouroutDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply21_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply21_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply21_modify");
        ModelAndView mv = new ModelAndView();

        FamtouroutDTO info = eduMarineService.processSelectFamtouroutSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply21_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply21/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply21_update(@RequestBody FamtouroutDTO famtouroutDTO) {
        System.out.println("EduMarineController > mypage_eduApply21_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtourout(famtouroutDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/apply/faq.do", method = RequestMethod.GET)
    public ModelAndView apply_faq() {
        System.out.println("EduMarineController > apply_faq");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/faq");
        return mv;
    }

    @RequestMapping(value = "/apply/faq/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FaqDTO>> apply_faq_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > apply_faq_selectList");

        List<FaqDTO> responseList = eduMarineService.processSelectFaqList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
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

        String gbn = "창업처";
        List<EmploymentDTO> employmentList = eduMarineService.processSelectEmploymentList(gbn);
        mv.addObject("employmentList", employmentList);
        mv.setViewName("/job/state01");
        return mv;
    }

    @RequestMapping(value = "/job/state02.do", method = RequestMethod.GET)
    public ModelAndView job_state02() {
        System.out.println("EduMarineController > job_state02");
        ModelAndView mv = new ModelAndView();

        String gbn = "취업처";
        List<EmploymentDTO> employmentList = eduMarineService.processSelectEmploymentList(gbn);
        mv.addObject("employmentList", employmentList);
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

    @RequestMapping(value = "/job/community/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CommunityDTO>> job_community_selectList(@RequestBody SearchDTO searchDTO) {
        System.out.println("EduMarineController > job_community_selectList");

        List<CommunityDTO> responseList = eduMarineService.processSelectCommunityList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_view.do", method = RequestMethod.GET)
    public ModelAndView job_community_view(String seq, HttpSession session) {
        System.out.println("EduMarineController > job_community_view");
        ModelAndView mv = new ModelAndView();

        /* 조회 카운트 Update */
        eduMarineService.processUpdateCommunityViewCnt(seq);

        /* 데이터 조회 후 Set */
        CommunityDTO info = eduMarineService.processSelectCommunitySingle(seq);

        if(info != null){

            mv.addObject("info", info);

            /* 추천 정보 */
            String id = String.valueOf(session.getAttribute("id"));
            mv.addObject("id", id);

            RecommendDTO recommendReqDTO = new RecommendDTO();
            recommendReqDTO.setCommunitySeq(seq);
            recommendReqDTO.setMemberId(id);
            RecommendDTO recommendInfo = eduMarineService.processSelectRecommendSingle(recommendReqDTO);
            mv.addObject("recommendInfo", recommendInfo);

            /* 댓글 정보 */
            ReplyDTO replyReqDTO = new ReplyDTO();
            replyReqDTO.setCommunitySeq(seq);
            List<ReplyDTO> replyList = eduMarineService.processSelectReplyList(replyReqDTO);
            mv.addObject("replyList", replyList);

            /* 첨부파일 정보 Set */
            List<FileDTO> fileList = eduMarineService.processSelectFileList(info.getSeq());
            if(fileList != null && !fileList.isEmpty()){
                mv.addObject("fileList", fileList);
            }
        }

        mv.setViewName("/job/community_view");
        return mv;
    }

    @RequestMapping(value = "/job/community/write/check.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_check(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineController > job_community_write_check");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processCheckCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/reply/check.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_reply_check(@RequestBody ReplyDTO replyDTO) {
        System.out.println("EduMarineController > job_community_reply_check");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processCheckReply(replyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/reply/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_reply_insert(@RequestBody ReplyDTO replyDTO) {
        System.out.println("EduMarineController > job_community_reply_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertReply(replyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/reply/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_reply_delete(@RequestBody ReplyDTO replyDTO) {
        System.out.println("EduMarineController > job_community_reply_delete");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processDeleteReply(replyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/recommend/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_recommend_update(@RequestBody RecommendDTO recommendDTO) {
        System.out.println("EduMarineController > job_community_recommend_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateRecommend(recommendDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_write.do", method = RequestMethod.GET)
    public ModelAndView job_community_write(HttpSession session) {
        System.out.println("EduMarineController > job_community_write");
        ModelAndView mv = new ModelAndView();
        String id = String.valueOf(session.getAttribute("id"));
        mv.addObject("id", id);
        mv.setViewName("/job/community_write");
        return mv;
    }

    @RequestMapping(value = "/job/community/write/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_insert(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineController > job_community_write_insert");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processInsertCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_modify.do", method = RequestMethod.GET)
    public ModelAndView job_community_modify(String seq) {
        System.out.println("EduMarineController > job_community_modify");
        ModelAndView mv = new ModelAndView();
        CommunityDTO info = eduMarineService.processSelectCommunitySingle(seq);
        mv.addObject("info", info);
        mv.setViewName("/job/community_modify");
        return mv;
    }

    @RequestMapping(value = "/job/community/write/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_update(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineController > job_community_write_update");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/write/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_delete(@RequestBody CommunityDTO communityDTO) {
        System.out.println("EduMarineController > job_community_write_delete");
        //System.out.println(noticeDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processDeleteCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //***************************************************************************
    // mypage Folder
    //***************************************************************************

    @RequestMapping(value = "/train/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TrainDTO> mypage_resume_save(@RequestBody TrainDTO trainDTO) {
        System.out.println("EduMarineController > mypage_resume_save");
        //System.out.println(memberDTO.toString());

        TrainDTO responseDTO = eduMarineService.processSelectTrainSingle(trainDTO.getSeq());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApplyInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mypage_eduApplyInfo(HttpServletRequest request, HttpSession session) {
        System.out.println("EduMarineController > mypage_eduApplyInfo");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null) {

            String id = session.getAttribute("id").toString();

            MemberDTO memberDTO = eduMarineService.processSelectMemberSingle(id);

            if(memberDTO != null){
                String memberSeq = memberDTO.getSeq();

                mv.addObject("memberInfo", memberDTO);

                // 교육신청내역
                List<EduApplyInfoDTO> eduApplyInfoList = eduMarineService.processSelectEduApplyInfoList(memberSeq);
                mv.addObject("eduApplyInfoList", eduApplyInfoList);

                // 교육취소내역
                List<EduApplyInfoDTO> eduApplyInfoCancelList = eduMarineService.processSelectEduApplyInfoCancelList(memberSeq);
                mv.addObject("eduApplyInfoCancelList", eduApplyInfoCancelList);
            }
        }

        // 이니시스 결제 Response
        String pc_resultCode = request.getParameter("resultCode");
        String mo_resultCode = request.getParameter("P_STATUS");

        System.out.println("PC : " + pc_resultCode + " / MOBILE : " + mo_resultCode);

        if(pc_resultCode != null && !"null".equals(pc_resultCode)){
            System.out.println("이니시스 결제 Response pc_resultCode : " + pc_resultCode);
            if(!"V801".equals(pc_resultCode)) { // 결제 도중 취소 코드 V801
                System.out.println("request Session Id : " + request.getSession().getAttribute("id"));
                System.out.println("Session Id : " + session.getAttribute("id"));
                if (session.getAttribute("id") != null) {
                    String id = session.getAttribute("id").toString();
                    MemberDTO memberInfo = eduMarineService.processSelectMemberSingle(id);

                    InistdpayResponseDTO inistdpayResponseDTO = getInistdpayResponseDTO("PC", memberInfo.getSeq(), request);
                    mv.addObject("payResInfo", inistdpayResponseDTO);
                } else {
                    System.out.println("Session getAttribute is null , PC");
                }
            }
        }else if(mo_resultCode != null && !"null".equals(mo_resultCode)){
            System.out.println("이니시스 결제 Response mo_resultCode : " + mo_resultCode);
            if(!"01".equals(mo_resultCode)) { // 결제 도중 취소 코드 01
                System.out.println("request Session Id : " + request.getSession().getAttribute("id"));
                System.out.println("Session Id : " + session.getAttribute("id"));
                if (session.getAttribute("id") != null) {
                    String id = session.getAttribute("id").toString();
                    MemberDTO memberInfo = eduMarineService.processSelectMemberSingle(id);

                    InistdpayResponseDTO inistdpayResponseDTO = getInistdpayResponseDTO("MOBILE", memberInfo.getSeq(), request);
                    mv.addObject("payResInfo", inistdpayResponseDTO);
                } else {
                    System.out.println("Session getAttribute is null , MOBILE");
                }
            }
        }

        mv.setViewName("/mypage/eduApplyInfo");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduPayInfo.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduPayInfo(HttpSession session) {
        System.out.println("EduMarineController > mypage_eduPayInfo");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null) {

            String id = session.getAttribute("id").toString();

            MemberDTO memberDTO = eduMarineService.processSelectMemberSingle(id);

            if(memberDTO != null){
                String memberSeq = memberDTO.getSeq();

                // 결제내역
                List<PaymentDTO> paymentList = eduMarineService.processSelectPaymentList(memberSeq);
                mv.addObject("paymentList", paymentList);
            }
        }

        mv.setViewName("/mypage/eduPayInfo");
        return mv;
    }

    @RequestMapping(value = "/mypage/resume.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView mypage_resume(HttpSession session) {
        System.out.println("EduMarineController > mypage_resume");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

            if(info != null){
                ResumeDTO resumeInfo = eduMarineService.processSelectResumeSingle(info.getSeq());
                mv.addObject("resumeInfo", resumeInfo);

                if(resumeInfo != null){
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
    public ModelAndView mypage_post(HttpSession session) {
        System.out.println("EduMarineController > mypage_post");
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            String id = session.getAttribute("id").toString();

            /* 내 게시글 */
            List<CommunityDTO> communityList = eduMarineService.processSelectPostCommunityList(id);
            mv.addObject("communityList", communityList);

            /* 내 댓글 */
            List<ReplyDTO> replyList = eduMarineService.processSelectPostReplyList(id);
            mv.addObject("replyList", replyList);
        }

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

    @RequestMapping(value = "/member/modify/withdraw.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_modify_withdraw(@RequestBody MemberDTO memberDTO, HttpSession session) {
        System.out.println("EduMarineController > member_modify_withdraw");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateMemberWithdraw(memberDTO);

        if(CommConstants.RESULT_CODE_SUCCESS.equals(responseDTO.getResultCode())){
            System.out.println("session invalidate");
            session.removeAttribute("status");
            session.invalidate();
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/seq/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MemberDTO> member_seq_selectSingle(@RequestBody MemberDTO memberDTO) {
        System.out.println("EduMarineController > job_community_selectList");

        String seq = memberDTO.getSeq();
        MemberDTO responseInfo = eduMarineService.processSelectMemberSeqSingle(seq);

        return new ResponseEntity<>(responseInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply01_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply01_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply01_modify");
        ModelAndView mv = new ModelAndView();

        RegularDTO info = eduMarineService.processSelectRegularSingle(seq);

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply01_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply01/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply01_update(@RequestBody RegularDTO regularDTO) {
        System.out.println("EduMarineController > mypage_eduApply01_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply02_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply02_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply02_modify");
        ModelAndView mv = new ModelAndView();

        BoarderDTO info = eduMarineService.processSelectBoarderSingle(seq);

        if(info != null){

            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);

            /* 경력사항 */
            CareerDTO careerDTO = new CareerDTO();
            careerDTO.setBoarderSeq(info.getSeq());
            List<CareerDTO> careerList = eduMarineService.processSelectCareerList(careerDTO);
            mv.addObject("careerList", careerList);

            /* 자격면허 */
            LicenseDTO licenseDTO = new LicenseDTO();
            licenseDTO.setBoarderSeq(info.getSeq());
            List<LicenseDTO> licenseList = eduMarineService.processSelectLicenseList(licenseDTO);
            mv.addObject("licenseList", licenseList);

            /* 첨부파일 정보 Set */
            List<FileDTO> careerLicenseList = new ArrayList<>();
            List<FileDTO> fileList = eduMarineService.processSelectFileList(info.getMemberSeq());
            if(fileList != null  && !fileList.isEmpty()){
                for (FileDTO fileDTO : fileList) {
                    String fileNote = fileDTO.getNote().replaceAll("[0-9]", "");
                    if ("bodyPhoto".equals(fileNote)) {
                        mv.addObject("bodyPhotoFile", fileDTO);
                    }else if ("gradeLicense".equals(fileNote)) {
                        mv.addObject("gradeLicenseFile", fileDTO);
                    }else if ("careerLicense".equals(fileNote)) {
                        careerLicenseList.add(fileDTO);
                    }
                }

                mv.addObject("careerLicenseFileList", careerLicenseList);
            }

        }

        mv.setViewName("/mypage/eduApply02_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply02/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply02_update(@RequestBody BoarderDTO boarderDTO) {
        System.out.println("EduMarineController > mypage_eduApply02_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateBoarder(boarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply02/career/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply02_career_delete(@RequestBody CareerDTO careerDTO) {
        System.out.println("EduMarineController > mypage_eduApply02_career_delete");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processDeleteCareer(careerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply02/license/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply02_license_delete(@RequestBody LicenseDTO licenseDTO) {
        System.out.println("EduMarineController > mypage_eduApply02_license_delete");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processDeleteLicense(licenseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply03_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply03_modify(String seq, String modYn) {
        System.out.println("EduMarineController > mypage_eduApply03_modify");
        ModelAndView mv = new ModelAndView();

        FrpDTO info = eduMarineService.processSelectFrpSingle(seq);

        if(info != null){

            mv.addObject("info", info);

            if(modYn == null || "".equals(modYn)){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);

            /* 경력사항 */
            CareerDTO careerDTO = new CareerDTO();
            careerDTO.setBoarderSeq(info.getSeq());
            List<CareerDTO> careerList = eduMarineService.processSelectCareerList(careerDTO);
            mv.addObject("careerList", careerList);

            /* 자격면허 */
            LicenseDTO licenseDTO = new LicenseDTO();
            licenseDTO.setBoarderSeq(info.getSeq());
            List<LicenseDTO> licenseList = eduMarineService.processSelectLicenseList(licenseDTO);
            mv.addObject("licenseList", licenseList);

            /* 첨부파일 정보 Set */
            List<FileDTO> careerLicenseList = new ArrayList<>();
            List<FileDTO> fileList = eduMarineService.processSelectFileList(info.getMemberSeq());
            if(fileList != null  && !fileList.isEmpty()){
                for (FileDTO fileDTO : fileList) {
                    String fileNote = fileDTO.getNote().replaceAll("[0-9]", "");
                    if ("bodyPhoto".equals(fileNote)) {
                        mv.addObject("bodyPhotoFile", fileDTO);
                    }else if ("gradeLicense".equals(fileNote)) {
                        mv.addObject("gradeLicenseFile", fileDTO);
                    }else if ("careerLicense".equals(fileNote)) {
                        careerLicenseList.add(fileDTO);
                    }
                }

                mv.addObject("careerLicenseFileList", careerLicenseList);
            }
        }

        mv.setViewName("/mypage/eduApply03_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply03/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply03_update(@RequestBody FrpDTO frpDTO) {
        System.out.println("EduMarineController > mypage_eduApply03_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateFrp(frpDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply04/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply04_update(@RequestBody InboarderDTO inboarderDTO) {
        System.out.println("EduMarineController > mypage_eduApply04_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply05/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply05_update(@RequestBody OutboarderDTO outboarderDTO) {
        System.out.println("EduMarineController > mypage_eduApply05_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateOutboarder(outboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply06/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply06_update(@RequestBody SailyachtDTO sailyachtDTO) {
        System.out.println("EduMarineController > mypage_eduApply06_update");
        //System.out.println(memberDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processUpdateSailyacht(sailyachtDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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

        // 마리나
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateMarinaInfoList = eduMarineService.processSelectTrainTemplateList("marina");
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

        //위탁교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateCommissionInfoList = eduMarineService.processSelectTrainTemplateList("commission");
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

        mv.setViewName("/guide/guide05");
        return mv;
    }

    @RequestMapping(value = "/guide/guide06.do", method = RequestMethod.GET)
    public ModelAndView guide_guide06() {
        System.out.println("EduMarineController > guide_guide06");
        ModelAndView mv = new ModelAndView();

        //선외기
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateOutboarderInfoList = eduMarineService.processSelectTrainTemplateList("outboarder");
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

        mv.setViewName("/guide/guide06");
        return mv;
    }

    @RequestMapping(value = "/guide/guide07.do", method = RequestMethod.GET)
    public ModelAndView guide_guide07() {
        System.out.println("EduMarineController > guide_guide07");
        ModelAndView mv = new ModelAndView();

        //선내기
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateInboarderInfoList = eduMarineService.processSelectTrainTemplateList("inboarder");
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

        mv.setViewName("/guide/guide07");
        return mv;
    }

    @RequestMapping(value = "/guide/guide08.do", method = RequestMethod.GET)
    public ModelAndView guide_guide08() {
        System.out.println("EduMarineController > guide_guide08");
        ModelAndView mv = new ModelAndView();

        //세일요트
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateSailyachtInfoList = eduMarineService.processSelectTrainTemplateList("sailyacht");
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

        mv.setViewName("/guide/guide08");
        return mv;
    }

    @RequestMapping(value = "/guide/guide09.do", method = RequestMethod.GET)
    public ModelAndView guide_guide09() {
        System.out.println("EduMarineController > guide_guide09");
        ModelAndView mv = new ModelAndView();

        //고마력
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateHighHorsePowerInfoList = eduMarineService.processSelectTrainTemplateList("highhorsepower");
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

        mv.setViewName("/guide/guide09");
        return mv;
    }

    @RequestMapping(value = "/guide/guide10.do", method = RequestMethod.GET)
    public ModelAndView guide_guide10() {
        System.out.println("EduMarineController > guide_guide10");
        ModelAndView mv = new ModelAndView();

        //sterndrive
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateSterndriveInfoList = eduMarineService.processSelectTrainTemplateList("sterndrive");
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

        } //고마력

        mv.setViewName("/guide/guide10");
        return mv;
    }

    @RequestMapping(value = "/guide/guide11.do", method = RequestMethod.GET)
    public ModelAndView guide_guide11() {
        System.out.println("EduMarineController > guide_guide11");
        ModelAndView mv = new ModelAndView();

        //고마력 선외기 자가정비
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateHighselfInfoList = eduMarineService.processSelectTrainTemplateList("highself");
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

        mv.setViewName("/guide/guide11");
        return mv;
    }

    @RequestMapping(value = "/guide/guide12.do", method = RequestMethod.GET)
    public ModelAndView guide_guide12() {
        System.out.println("EduMarineController > guide_guide12");
        ModelAndView mv = new ModelAndView();

        //기초정비교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateBasicInfoList = eduMarineService.processSelectTrainTemplateList("basic");
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

        mv.setViewName("/guide/guide12");
        return mv;
    }

    @RequestMapping(value = "/guide/guide13.do", method = RequestMethod.GET)
    public ModelAndView guide_guide13() {
        System.out.println("EduMarineController > guide_guide13");
        ModelAndView mv = new ModelAndView();

        //응급조치교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateEmergencyInfoList = eduMarineService.processSelectTrainTemplateList("emergency");
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
        
        mv.setViewName("/guide/guide13");
        return mv;
    }

    @RequestMapping(value = "/guide/guide14.do", method = RequestMethod.GET)
    public ModelAndView guide_guide14() {
        System.out.println("EduMarineController > guide_guide14");
        ModelAndView mv = new ModelAndView();

        //발전기 정비 교육
        List<TrainTemplateDTO.TrainTemplateInfo> trainTemplateGeneratorInfoList = eduMarineService.processSelectTrainTemplateList("generator");
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
        
        mv.setViewName("/guide/guide14");
        return mv;
    }

    //***************************************************************************
    // ENG
    //***************************************************************************

    @RequestMapping(value = "/eng/index.do", method = RequestMethod.GET)
    public ModelAndView eng_index() {
        System.out.println("EduMarineController > eng_index");

        /* 방문자 수 카운트 */
        eduMarineService.processStatisticsAccessor();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/index");
        return mv;
    }

    @RequestMapping(value = "/eng/marine/intro.do", method = RequestMethod.GET)
    public ModelAndView eng_marine_intro() {
        System.out.println("EduMarineController > eng_marine_intro");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/marine/intro");
        return mv;
    }

    @RequestMapping(value = "/eng/current/current.do", method = RequestMethod.GET)
    public ModelAndView eng_current_current() {
        System.out.println("EduMarineController > eng_current_current");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/current/current");
        return mv;
    }

    @RequestMapping(value = "/eng/edu/curriculum.do", method = RequestMethod.GET)
    public ModelAndView eng_edu_curriculum() {
        System.out.println("EduMarineController > eng_edu_curriculum");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/edu/curriculum");
        return mv;
    }

    @RequestMapping(value = "/eng/edu/equipment.do", method = RequestMethod.GET)
    public ModelAndView eng_edu_equipment() {
        System.out.println("EduMarineController > eng_edu_equipment");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/edu/equipment");
        return mv;
    }

    @RequestMapping(value = "/eng/contact/contact.do", method = RequestMethod.GET)
    public ModelAndView eng_contact_contact() {
        System.out.println("EduMarineController > eng_contact_contact");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/contact/contact");
        return mv;
    }

    //***************************************************************************
    // Common
    //***************************************************************************

    private InistdpayResponseDTO getInistdpayResponseDTO(String deviceGbn, String memberSeq, HttpServletRequest request) {
        InistdpayResponseDTO inistdpayResponseDTO = new InistdpayResponseDTO();

        if(deviceGbn.equals("PC")) {
            Map<String, String> resultMap = new HashMap<String, String>();
            String merchantData = "";
            try {

                //#############################
                // 인증결과 파라미터 일괄 수신
                //#############################
                request.setCharacterEncoding("UTF-8");

                Map<String, String> paramMap = new Hashtable<String, String>();

                Enumeration elems = request.getParameterNames();

                String temp = "";

                while (elems.hasMoreElements()) {
                    temp = (String) elems.nextElement();
                    paramMap.put(temp, request.getParameter(temp));
                }

                //##############################
                // 인증성공 resultCode=0000 확인
                // IDC센터 확인 [idc_name=fc,ks,stg]
                // idc_name 으로 수신 받은 값 기준 properties 에 설정된 승인URL과 authURL 이 같은지 비교
                // 승인URL은  https://manual.inicis.com 참조
                //##############################

                if ("0000".equals(paramMap.get("resultCode")) && paramMap.get("authUrl").equals(ResourceBundle.getBundle("idc_name").getString(paramMap.get("idc_name")))) {

                    System.out.println("####인증성공/승인요청####");

                    //############################################
                    // 1.전문 필드 값 설정(***가맹점 개발수정***)
                    //############################################

                    String mid = paramMap.get("mid");
                    String timestamp = SignatureUtil.getTimestamp();
                    String charset = "UTF-8";
                    String format = "JSON";
                    String authToken = paramMap.get("authToken");
                    String authUrl = paramMap.get("authUrl");
                    String netCancel = paramMap.get("netCancelUrl");
                    merchantData = paramMap.get("merchantData");

                    //#####################
                    // 2.signature 생성
                    //#####################
                    Map<String, String> signParam = new HashMap<String, String>();

                    signParam.put("authToken", authToken);        // 필수
                    signParam.put("timestamp", timestamp);        // 필수

                    // signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
                    String signature = SignatureUtil.makeSignature(signParam);

                    signParam.put("signKey", "elVJSjZPYWhaelBCVjlCSFYwbzdkQT09");        // 필수

                    // signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
                    String verification = SignatureUtil.makeSignature(signParam);

                    //#####################
                    // 3.API 요청 전문 생성
                    //#####################
                    Map<String, String> authMap = new Hashtable<String, String>();

                    authMap.put("mid", mid);            // 필수
                    authMap.put("authToken", authToken);    // 필수
                    authMap.put("signature", signature);    // 필수
                    authMap.put("verification", verification);    // 필수
                    authMap.put("timestamp", timestamp);    // 필수
                    authMap.put("charset", charset);        // default=UTF-8
                    authMap.put("format", format);

                    HttpUtil httpUtil = new HttpUtil();

                    try {
                        //#####################
                        // 4.API 통신 시작
                        //#####################

                        String authResultString = "";

                        authResultString = httpUtil.processHTTP(authMap, authUrl);

                        //############################################################
                        //6.API 통신결과 처리(***가맹점 개발수정***)
                        //############################################################

                        String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ", "").replace("\n", "").replace("}", "").replace("{", "");

                        resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱

                        // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패
                        //throw new Exception("강제 망취소 요청 Exception ");

                    } catch (Exception ex) {

                        //####################################
                        // 실패시 처리(***가맹점 개발수정***)
                        //####################################

                        //---- db 저장 실패시 등 예외처리----//
                        System.out.println(ex.getMessage());

                        //#####################
                        // 망취소 API
                        //#####################
                        String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);    // 망취소 요청 API url(고정, 임의 세팅 금지)

                        System.out.println("## 망취소 API 결과 ##");

                        // 망취소 결과 확인
                        System.out.println("<p>" + netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</p>");
                    }

                } else {

                    resultMap.put("resultCode", paramMap.get("resultCode"));
                    resultMap.put("resultMsg", paramMap.get("resultMsg"));
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            inistdpayResponseDTO = (InistdpayResponseDTO) convertMapToObject(resultMap, inistdpayResponseDTO);
            System.out.println("PC Inisis Payment Result : " + inistdpayResponseDTO.toString());

            if ("0000".equals(inistdpayResponseDTO.getResultCode())) {
                // 결제내역 테이블 Insert process

                String goodName = inistdpayResponseDTO.getGoodName(); // [T0000003]상시신청
                String trainSeq = goodName.substring(goodName.indexOf("[") + 1, goodName.indexOf("]")); // T0000003
                String[] gbnArr = goodName.split("]"); // 상시신청

                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setMemberSeq(memberSeq);
                paymentDTO.setMemberName(inistdpayResponseDTO.getBuyerName());
                paymentDTO.setMemberPhone(inistdpayResponseDTO.getBuyerTel());
                paymentDTO.setTableSeq(merchantData);
                paymentDTO.setTrainSeq(trainSeq);
                paymentDTO.setTrainName(gbnArr[1]);
                paymentDTO.setBuyerName(inistdpayResponseDTO.getBuyerName());
                paymentDTO.setBuyerTel(inistdpayResponseDTO.getBuyerTel());
                paymentDTO.setBuyerEmail(inistdpayResponseDTO.getBuyerEmail());
                paymentDTO.setCustEmail(inistdpayResponseDTO.getCustEmail());
                paymentDTO.setPaySum(Integer.valueOf(inistdpayResponseDTO.getTotPrice()));
                paymentDTO.setTotPrice(inistdpayResponseDTO.getTotPrice());
                paymentDTO.setGoodName(inistdpayResponseDTO.getGoodName());
                paymentDTO.setGoodsName(inistdpayResponseDTO.getGoodsName());
                paymentDTO.setApplDate(inistdpayResponseDTO.getApplDate());
                paymentDTO.setApplTime(inistdpayResponseDTO.getApplTime());
                paymentDTO.setMoid(inistdpayResponseDTO.getMOID());
                paymentDTO.setMid(inistdpayResponseDTO.getMid());
                paymentDTO.setTid(inistdpayResponseDTO.getTid());
                paymentDTO.setApplNum(inistdpayResponseDTO.getApplNum());
                paymentDTO.setAuthSignature(inistdpayResponseDTO.getAuthSignature());
                paymentDTO.setEventCode(inistdpayResponseDTO.getEventCode());
                paymentDTO.setPayMethod(inistdpayResponseDTO.getPayMethod());
                paymentDTO.setCurrency(inistdpayResponseDTO.getCurrency());
                paymentDTO.setPFnNm(inistdpayResponseDTO.getP_FN_NM());
                paymentDTO.setCardNum(inistdpayResponseDTO.getCARD_Num());
                paymentDTO.setCardCode(inistdpayResponseDTO.getCARD_Code());
                paymentDTO.setCardCorpFlag(inistdpayResponseDTO.getCARD_CorpFlag());
                paymentDTO.setCardMemberNum(inistdpayResponseDTO.getCARD_MemberNum());
                paymentDTO.setCardApplPrice(inistdpayResponseDTO.getCARD_ApplPrice());
                paymentDTO.setCardPoint(inistdpayResponseDTO.getCARD_Point());
                paymentDTO.setCardQuota(inistdpayResponseDTO.getCARD_Quota());
                paymentDTO.setCardPurchaseCode(inistdpayResponseDTO.getCARD_PurchaseCode());
                paymentDTO.setCardPrtcCode(inistdpayResponseDTO.getCARD_PrtcCode());
                paymentDTO.setCardCheckFlag(inistdpayResponseDTO.getCARD_CheckFlag());
                paymentDTO.setCardBankCode(inistdpayResponseDTO.getCARD_BankCode());
                paymentDTO.setCardTerminalNum(inistdpayResponseDTO.getCARD_TerminalNum());
                paymentDTO.setCardUsePoint(inistdpayResponseDTO.getCARD_UsePoint());
                paymentDTO.setCardInterest(inistdpayResponseDTO.getCARD_Interest());
                paymentDTO.setCardSrcCode(inistdpayResponseDTO.getCARD_SrcCode());
                paymentDTO.setCardGwcode(inistdpayResponseDTO.getCARD_GWCode());
                paymentDTO.setCardPurchaseName(inistdpayResponseDTO.getCARD_PurchaseName());
                paymentDTO.setPayDevice(inistdpayResponseDTO.getPayDevice());
                paymentDTO.setVactDate(inistdpayResponseDTO.getVACT_Date());
                paymentDTO.setVactTime(inistdpayResponseDTO.getVACT_Time());
                paymentDTO.setVactName(inistdpayResponseDTO.getVACT_Name());
                paymentDTO.setVactInputName(inistdpayResponseDTO.getVACT_InputName());
                paymentDTO.setVactBankCode(inistdpayResponseDTO.getVACT_BankCode());
                paymentDTO.setVactBankName(inistdpayResponseDTO.getVactBankName());
                paymentDTO.setVactNum(inistdpayResponseDTO.getVACT_Num());
                paymentDTO.setResultCode(inistdpayResponseDTO.getResultCode());
                paymentDTO.setResultMsg(inistdpayResponseDTO.getResultMsg());

                if ("0000".equals(inistdpayResponseDTO.getResultCode())) {
                    String payStatus = "결제완료";
                    if ("vbank".equalsIgnoreCase(inistdpayResponseDTO.getPayMethod())) {
                        payStatus = "입금대기";
                    }
                    paymentDTO.setPayStatus(payStatus);
                }

                ResponseDTO paymentResDto = eduMarineService.processInsertPayment(paymentDTO);

                if ("0".equals(paymentResDto.getResultCode())) {
                    if (paymentDTO.getTableSeq() != null && !"".equals(paymentDTO.getTableSeq())) {

                        if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())) {
                            // 가상계좌 입금안내 SMS Noti
                            SmsNotificationDTO smsNotiReq = new SmsNotificationDTO();
                            smsNotiReq.setTarget("6"); // note
                            smsNotiReq.setPaymentSeq(paymentResDto.getCustomValue());
                            smsNotiReq.setTrainSeq(trainSeq);
                            String smsNotiContent = commService.smsSendNotifyContent(smsNotiReq);

                            SmsNotificationDTO smsSendReq = new SmsNotificationDTO();
                            smsSendReq.setTarget("6");
                            smsSendReq.setSeq(memberSeq);
                            smsSendReq.setContent(smsNotiContent);
                            commService.smsSendNotifySending(smsSendReq);
                        }

                        // 상시신청
                        // 해상엔진 테크니션 (선내기/선외기)
                        // FRP 레저보트 선체 정비 테크니션
                        // 해상엔진 자가정비 (선외기)
                        // 해상엔진 자가정비 (선내기)
                        // 해상엔진 자가정비 (세일요트)
                        // 고마력 선외기 정비 중급 테크니션
                        // 스턴드라이브 정비 전문가과정
                        // 선외기 기초정비교육
                        // 선내기 기초정비교육
                        // 세일요트 기초정비교육
                        // 선외기 응급조치교육
                        // 선내기 응급조치교육
                        // 세일요트 응급조치교육
                        // 발전기 정비 교육
                        // 선외기/선내기 직무역량 강화과정
                        if (paymentDTO.getTrainName().contains("상시")) {

                            // regular table
                            RegularDTO regularDTO = new RegularDTO();
                            regularDTO.setSeq(paymentDTO.getTableSeq());
                            regularDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateRegularPayStatus(regularDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(선내기/")) {

                            // boarder table
                            BoarderDTO boarderDTO = new BoarderDTO();
                            boarderDTO.setSeq(paymentDTO.getTableSeq());
                            boarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateBoarderPayStatus(boarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("FRP")) {

                            // frp table
                            FrpDTO frpDTO = new FrpDTO();
                            frpDTO.setSeq(paymentDTO.getTableSeq());
                            frpDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateFrpPayStatus(frpDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(선외기)")) {

                            // outboarder table
                            OutboarderDTO outboarderDTO = new OutboarderDTO();
                            outboarderDTO.setSeq(paymentDTO.getTableSeq());
                            outboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(선내기)")) {

                            // inboarder table
                            InboarderDTO inboarderDTO = new InboarderDTO();
                            inboarderDTO.setSeq(paymentDTO.getTableSeq());
                            inboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(세일요트)")) {

                            // sailyacht table
                            SailyachtDTO sailyachtDTO = new SailyachtDTO();
                            sailyachtDTO.setSeq(paymentDTO.getTableSeq());
                            sailyachtDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("고마력 선외기 정비 중급 테크니션")) {

                            if (paymentDTO.getTrainName().contains("(특별반)")) {

                                // highspecial table
                                HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                highSpecialDTO.setSeq(paymentDTO.getTableSeq());
                                highSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }else{

                                // highhorsepower table
                                HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                highHorsePowerDTO.setSeq(paymentDTO.getTableSeq());
                                highHorsePowerDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateHighHorsePowerPayStatus(highHorsePowerDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("자가정비 심화과정 (고마력 선외기)")) {

                            // highself table
                            HighSelfDTO highSelfDTO = new HighSelfDTO();
                            highSelfDTO.setSeq(paymentDTO.getTableSeq());
                            highSelfDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateHighSelfPayStatus(highSelfDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("스턴드라이브")) {

                            if (paymentDTO.getTrainName().contains("(특별반)")) {

                                // sternspecial table
                                SternSpecialDTO sternSpecialDTO = new SternSpecialDTO();
                                sternSpecialDTO.setSeq(paymentDTO.getTableSeq());
                                sternSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }else{

                                // Sterndrive table
                                SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                sterndriveDTO.setSeq(paymentDTO.getTableSeq());
                                sterndriveDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateSterndrivePayStatus(sterndriveDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }

                        } else if (paymentDTO.getTrainName().contains("기초정비교육")) {

                            // Basic table
                            BasicDTO basicDTO = new BasicDTO();
                            basicDTO.setSeq(paymentDTO.getTableSeq());
                            basicDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateBasicPayStatus(basicDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("응급조치교육")) {

                            // Basic table
                            EmergencyDTO emergencyDTO = new EmergencyDTO();
                            emergencyDTO.setSeq(paymentDTO.getTableSeq());
                            emergencyDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("발전기")) {

                            // Generator table
                            GeneratorDTO generatorDTO = new GeneratorDTO();
                            generatorDTO.setSeq(paymentDTO.getTableSeq());
                            generatorDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateGeneratorPayStatus(generatorDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("직무역량")) {

                            // Competency table
                            CompetencyDTO competencyDTO = new CompetencyDTO();
                            competencyDTO.setSeq(paymentDTO.getTableSeq());
                            competencyDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateCompetencyPayStatus(competencyDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        }
                    }
                }
            }
        }else if(deviceGbn.equals("MOBILE")){
            HashMap<String, String> map = new HashMap<String, String>();
            try {
                /////////////////////////////////////////////////////////////////////////////
                ///// 1. 변수 초기화 및 POST 인증값 받음                                 ////
                /////////////////////////////////////////////////////////////////////////////
                request.setCharacterEncoding("UTF-8");

                String P_STATUS = request.getParameter("P_STATUS");            // 인증 상태
                String P_RMESG1 = request.getParameter("P_RMESG1");            // 인증 결과 메시지
                String P_TID = request.getParameter("P_TID");                // 인증 거래번호
                String P_REQ_URL = request.getParameter("P_REQ_URL");        // 결제요청 URL
                String P_NOTI = request.getParameter("P_NOTI");                // 기타주문정보

                ////////////////////////////////////////////////////////////////////////////
                // 인증성공 P_STATUS=00 확인
                // IDC센터 확인 [idc_name=fc,ks,stg]
                // idc_name 으로 수신 받은 값 기준 properties 에 설정된 승인URL과 P_REQ_URL 이 같은지 비교
                // 승인URL은  https://manual.inicis.com 참조
                ////////////////////////////////////////////////////////////////////////////

                if (P_STATUS.equals("00") && P_REQ_URL.equals(ResourceBundle.getBundle("idc_name_m").getString(request.getParameter("idc_name")))) {

                    /////////////////////////////////////////////////////////////////////////////
                    ///// 2. 상점 아이디 설정 :                                              ////
                    /////    결제요청 페이지에서 사용한 MID값과 동일하게 세팅해야 함...      ////
                    /////////////////////////////////////////////////////////////////////////////

                    String P_MID = P_TID.substring(10, 20);

                    /////////////////////////////////////////////////////////////////////////////
                    //// 3. 승인요청 :                                                      ////
                    //// 	승인요청 API url (P_REQ_URL) 리스트 는 properties 에 세팅하여 사용합니다.
                    //// 	idc_name 으로 수신 받은 센터 네임을 properties 에서 확인하여 승인요청하시면 됩니다.
                    /////////////////////////////////////////////////////////////////////////////

                    P_REQ_URL = P_REQ_URL + "?P_TID=" + P_TID + "&P_MID=" + P_MID;

                    try {
                        URL reqUrl = new URL(P_REQ_URL);
                        HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();

                        if (conn != null) {
                            conn.setRequestMethod("POST");
                            conn.setDefaultUseCaches(false);
                            conn.setDoOutput(true);

                            if (conn.getDoOutput()) {
                                conn.getOutputStream().flush();
                                conn.getOutputStream().close();
                            }

                            conn.connect();

                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                            String[] values = new String(br.readLine()).split("&");

                            //System.out.println("mobile pay result ========================");
                            for (String value : values) {

                                // 승인결과를 파싱값 잘라 hashmap에 저장
                                int i = value.indexOf("=");
                                String key1 = value.substring(0, i);
                                String value1 = value.substring(i + 1);
                                map.put(key1, value1);

                                //System.out.println(key1 + " / " + value1);
                            }

                            br.close();
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {

                    map.put("P_STATUS", P_STATUS);
                    map.put("P_RMESG1", P_RMESG1);

                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            inistdpayResponseDTO = (InistdpayResponseDTO) convertMapToObject(map, inistdpayResponseDTO);
            System.out.println("MOBILE Inisis Payment Result : " + inistdpayResponseDTO.toString());

            if ("00".equals(inistdpayResponseDTO.getP_STATUS())) {
                // 결제내역 테이블 Insert process

                String[] P_NOTI_ARR = inistdpayResponseDTO.getP_NOTI().split(","); //T0000030,O0000261,해상엔진 자가정비 (선외기)
                String goodName = "[" + P_NOTI_ARR[0] + "]" + P_NOTI_ARR[2]; // [T0000030]해상엔진 자가정비 (선외기)
                String trainSeq = P_NOTI_ARR[0]; // T0000030
                String tableSeq = P_NOTI_ARR[1]; // O0000261
                String trainName = P_NOTI_ARR[2]; // 해상엔진 자가정비 (선외기)

                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setMemberSeq(memberSeq);
                paymentDTO.setMemberName(inistdpayResponseDTO.getP_UNAME());
                paymentDTO.setTableSeq(tableSeq);
                paymentDTO.setTrainSeq(trainSeq);
                paymentDTO.setTrainName(trainName);
                paymentDTO.setBuyerName(inistdpayResponseDTO.getP_UNAME());
                paymentDTO.setPaySum(Integer.valueOf(inistdpayResponseDTO.getP_AMT()));
                paymentDTO.setTotPrice(inistdpayResponseDTO.getP_AMT());
                paymentDTO.setGoodName(goodName);
                paymentDTO.setGoodsName(goodName);
                paymentDTO.setApplDate(inistdpayResponseDTO.getP_AUTH_DT().substring(0,8));
                paymentDTO.setApplTime(inistdpayResponseDTO.getP_AUTH_DT().substring(8));
                paymentDTO.setMoid(inistdpayResponseDTO.getP_OID());
                paymentDTO.setMid(inistdpayResponseDTO.getP_MID());
                paymentDTO.setTid(inistdpayResponseDTO.getP_TID());
                paymentDTO.setApplNum(inistdpayResponseDTO.getP_AUTH_NO());
                paymentDTO.setPayMethod(inistdpayResponseDTO.getP_TYPE());
                paymentDTO.setPFnNm(inistdpayResponseDTO.getP_FN_NM());
                paymentDTO.setCardNum(inistdpayResponseDTO.getP_CARD_NUM());
                paymentDTO.setCardCode(inistdpayResponseDTO.getP_FN_CD1());
                paymentDTO.setCardCorpFlag(inistdpayResponseDTO.getCARD_CorpFlag());
                paymentDTO.setCardApplPrice(inistdpayResponseDTO.getP_CARD_APPLPRICE());
                paymentDTO.setCardPurchaseCode(inistdpayResponseDTO.getP_CARD_PURCHASE_CODE());
                paymentDTO.setCardPrtcCode(inistdpayResponseDTO.getP_CARD_PRTC_CODE());
                paymentDTO.setCardCheckFlag(inistdpayResponseDTO.getP_CARD_CHECKFLAG());
                paymentDTO.setCardBankCode(inistdpayResponseDTO.getP_CARD_ISSUER_CODE());
                paymentDTO.setCardInterest(inistdpayResponseDTO.getP_CARD_INTEREST());
                paymentDTO.setPayDevice("MOBILE");
                paymentDTO.setVactDate(inistdpayResponseDTO.getP_VACT_DATE());
                paymentDTO.setVactTime(inistdpayResponseDTO.getP_VACT_TIME());
                paymentDTO.setVactName(inistdpayResponseDTO.getP_VACT_NAME());
//                paymentDTO.setVactInputName(inistdpayResponseDTO.getVACT_InputName());
                paymentDTO.setVactBankCode(inistdpayResponseDTO.getP_VACT_BANK_CODE());
                paymentDTO.setVactBankName(inistdpayResponseDTO.getP_FN_NM());
                paymentDTO.setVactNum(inistdpayResponseDTO.getP_VACT_NUM());
                paymentDTO.setResultCode(inistdpayResponseDTO.getP_STATUS());
                paymentDTO.setResultMsg(inistdpayResponseDTO.getP_RMESG1());

                if ("00".equals(inistdpayResponseDTO.getP_STATUS())) {
                    String payStatus = "결제완료";
                    if ("vbank".equalsIgnoreCase(inistdpayResponseDTO.getP_TYPE())) {
                        payStatus = "입금대기";
                    }
                    paymentDTO.setPayStatus(payStatus);
                }

                ResponseDTO paymentResDto = eduMarineService.processInsertPayment(paymentDTO);

                if ("0".equals(paymentResDto.getResultCode())) {
                    if (paymentDTO.getTableSeq() != null && !"".equals(paymentDTO.getTableSeq())) {

                        if ("vbank".equalsIgnoreCase(paymentDTO.getPayMethod())) {
                            // 가상계좌 입금안내 SMS Noti
                            SmsNotificationDTO smsNotiReq = new SmsNotificationDTO();
                            smsNotiReq.setTarget("6"); // note
                            smsNotiReq.setPaymentSeq(paymentResDto.getCustomValue());
                            smsNotiReq.setTrainSeq(trainSeq);
                            String smsNotiContent = commService.smsSendNotifyContent(smsNotiReq);

                            SmsNotificationDTO smsSendReq = new SmsNotificationDTO();
                            smsSendReq.setTarget("6");
                            smsSendReq.setSeq(memberSeq);
                            smsSendReq.setContent(smsNotiContent);
                            commService.smsSendNotifySending(smsSendReq);
                        }

                        // 상시신청
                        // 해상엔진 테크니션 (선내기/선외기)
                        // FRP 레저보트 선체 정비 테크니션
                        // 해상엔진 자가정비 (선외기)
                        // 해상엔진 자가정비 (선내기)
                        // 해상엔진 자가정비 (세일요트)
                        // 고마력 선외기 정비 중급 테크니션
                        // 스턴드라이브 정비 전문가과정
                        // 선외기 기초정비교육
                        // 선내기 기초정비교육
                        // 세일요트 기초정비교육
                        // 선외기 응급조치교육
                        // 선내기 응급조치교육
                        // 세일요트 응급조치교육
                        // 발전기 정비 교육
                        // 선외기/선내기 직무역량 강화과정
                        if (paymentDTO.getTrainName().contains("상시")) {

                            // regular table
                            RegularDTO regularDTO = new RegularDTO();
                            regularDTO.setSeq(paymentDTO.getTableSeq());
                            regularDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateRegularPayStatus(regularDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(선내기/")) {

                            // boarder table
                            BoarderDTO boarderDTO = new BoarderDTO();
                            boarderDTO.setSeq(paymentDTO.getTableSeq());
                            boarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateBoarderPayStatus(boarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("FRP")) {

                            // frp table
                            FrpDTO frpDTO = new FrpDTO();
                            frpDTO.setSeq(paymentDTO.getTableSeq());
                            frpDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateFrpPayStatus(frpDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(선외기)")) {

                            // outboarder table
                            OutboarderDTO outboarderDTO = new OutboarderDTO();
                            outboarderDTO.setSeq(paymentDTO.getTableSeq());
                            outboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(선내기)")) {

                            // inboarder table
                            InboarderDTO inboarderDTO = new InboarderDTO();
                            inboarderDTO.setSeq(paymentDTO.getTableSeq());
                            inboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("(세일요트)")) {

                            // sailyacht table
                            SailyachtDTO sailyachtDTO = new SailyachtDTO();
                            sailyachtDTO.setSeq(paymentDTO.getTableSeq());
                            sailyachtDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("고마력 선외기 정비 중급 테크니션")) {

                            if (paymentDTO.getTrainName().contains("(특별반)")) {

                                // highspecial table
                                HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                highSpecialDTO.setSeq(paymentDTO.getTableSeq());
                                highSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }else{

                                // highhorsepower table
                                HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                highHorsePowerDTO.setSeq(paymentDTO.getTableSeq());
                                highHorsePowerDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateHighHorsePowerPayStatus(highHorsePowerDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }

                        }else if (paymentDTO.getTrainName().contains("자가정비 심화과정 (고마력 선외기)")) {

                            // highself table
                            HighSelfDTO highSelfDTO = new HighSelfDTO();
                            highSelfDTO.setSeq(paymentDTO.getTableSeq());
                            highSelfDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateHighSelfPayStatus(highSelfDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("스턴드라이브")) {

                            if (paymentDTO.getTrainName().contains("(특별반)")) {

                                // sternspecial table
                                SternSpecialDTO sternSpecialDTO = new SternSpecialDTO();
                                sternSpecialDTO.setSeq(paymentDTO.getTableSeq());
                                sternSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }else{

                                // Sterndrive table
                                SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                sterndriveDTO.setSeq(paymentDTO.getTableSeq());
                                sterndriveDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateSterndrivePayStatus(sterndriveDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            }

                        } else if (paymentDTO.getTrainName().contains("기초정비교육")) {

                            // Basic table
                            BasicDTO basicDTO = new BasicDTO();
                            basicDTO.setSeq(paymentDTO.getTableSeq());
                            basicDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateBasicPayStatus(basicDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("응급조치교육")) {

                            // Basic table
                            EmergencyDTO emergencyDTO = new EmergencyDTO();
                            emergencyDTO.setSeq(paymentDTO.getTableSeq());
                            emergencyDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("발전기")) {

                            // Generator table
                            GeneratorDTO generatorDTO = new GeneratorDTO();
                            generatorDTO.setSeq(paymentDTO.getTableSeq());
                            generatorDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateGeneratorPayStatus(generatorDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (paymentDTO.getTrainName().contains("직무역량")) {

                            // Competency table
                            CompetencyDTO competencyDTO = new CompetencyDTO();
                            competencyDTO.setSeq(paymentDTO.getTableSeq());
                            competencyDTO.setApplyStatus(paymentDTO.getPayStatus());
                            ResponseDTO result = eduMarineService.processUpdateCompetencyPayStatus(competencyDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        }
                    }
                }
            }

        }

        return inistdpayResponseDTO;
    }

    @RequestMapping(value = "/apply/payment.do", method = RequestMethod.POST)
    public ModelAndView apply_payment(InistdpayRequestDTO inistdpayRequestDTO) throws Exception {
        System.out.println("EduMarineController > apply_payment");
        ModelAndView mv = new ModelAndView();
        TrainDTO trainDTO = eduMarineService.processSelectTrainSingle(inistdpayRequestDTO.getTrainSeq());

        //String mid					= "INIpayTest";		                    // 상점아이디
        //String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey

        String mid = "edumarin90";
        String signKey = "elVJSjZPYWhaelBCVjlCSFYwbzdkQT09";

        // 실제 Key
        // 웹결제 signkey
        // TmlKeFBiSjlpVUhkMldsMlJDWWdKQT09

        String mKey = SignatureUtil.hash(signKey, "SHA-256");

        String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
        String orderNumber			= mid + "_" + SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
        String price				= String.valueOf(trainDTO.getPaySum());								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)

        String use_chkfake			= "Y";									// verification 검증 여부 ('Y' , 'N')

        Map<String, String> signParam = new HashMap<String, String>();

        signParam.put("oid", orderNumber);
        signParam.put("price", price);
        signParam.put("timestamp", timestamp);

        String signature = SignatureUtil.makeSignature(signParam);			// signature 대상: oid, price, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        signParam.put("signKey", signKey);

        String verification = SignatureUtil.makeSignature(signParam);		// verification 대상 : oid, price, signkey, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        /* 개발 */
        /*String siteDomain = "http://localhost:8080";*/
        /* 운영 */
        String siteDomain = "https://edumarine.org";

        String gbn = trainDTO.getGbn();
        if(trainDTO.getGbnDepth() != null && !"".equals(trainDTO.getGbnDepth())){
            gbn = trainDTO.getGbnDepth() + " " + gbn;
        }

        inistdpayRequestDTO.setMid(mid);
        inistdpayRequestDTO.setOid(orderNumber);
        inistdpayRequestDTO.setPrice(String.valueOf(trainDTO.getPaySum()));
        inistdpayRequestDTO.setTimestamp(timestamp);
        inistdpayRequestDTO.setUseChkfake(use_chkfake);
        inistdpayRequestDTO.setSignature(signature);
        inistdpayRequestDTO.setVerification(verification);
        inistdpayRequestDTO.setMkey(mKey);
        inistdpayRequestDTO.setGoodname(gbn);
        inistdpayRequestDTO.setSiteDomain(siteDomain);
        mv.addObject("payInfo", inistdpayRequestDTO);

        System.out.println("apply_payment : " + inistdpayRequestDTO.toString());
        mv.setViewName("/apply/payment");
        return mv;
    }

    @RequestMapping(value = "/apply/mobile/payment.do", method = RequestMethod.POST)
    public ModelAndView apply_mobile_payment(InistdpayRequestDTO inistdpayRequestDTO) throws Exception {
        System.out.println("EduMarineController > apply_mobile_payment");
        ModelAndView mv = new ModelAndView();
        TrainDTO trainDTO = eduMarineService.processSelectTrainSingle(inistdpayRequestDTO.getTrainSeq());

        //String mid					= "INIpayTest";		                    // 상점아이디
        //String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey

        String mid = "edumarin90";
        String signKey = "elVJSjZPYWhaelBCVjlCSFYwbzdkQT09";

        // 실제 Key
        // 웹결제 signkey
        // TmlKeFBiSjlpVUhkMldsMlJDWWdKQT09

        String mKey = SignatureUtil.hash(signKey, "SHA-256");

        String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
        String orderNumber			= mid + "_" + SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
        String price				= String.valueOf(trainDTO.getPaySum());								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)

        String use_chkfake			= "Y";									// verification 검증 여부 ('Y' , 'N')

        Map<String, String> signParam = new HashMap<String, String>();

        signParam.put("oid", orderNumber);
        signParam.put("price", price);
        signParam.put("timestamp", timestamp);

        String signature = SignatureUtil.makeSignature(signParam);			// signature 대상: oid, price, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        signParam.put("signKey", signKey);

        String verification = SignatureUtil.makeSignature(signParam);		// verification 대상 : oid, price, signkey, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        /* 개발 */
        /*String siteDomain = "http://localhost:8080";*/
        /* 운영 */
        String siteDomain = "https://edumarine.org";

        String gbn = trainDTO.getGbn();
        if(trainDTO.getGbnDepth() != null && !"".equals(trainDTO.getGbnDepth())){
            gbn = trainDTO.getGbnDepth() + " " + gbn;
        }

        inistdpayRequestDTO.setMid(mid);
        inistdpayRequestDTO.setOid(orderNumber);
        inistdpayRequestDTO.setPrice(String.valueOf(trainDTO.getPaySum()));
        inistdpayRequestDTO.setTimestamp(timestamp);
        inistdpayRequestDTO.setUseChkfake(use_chkfake);
        inistdpayRequestDTO.setSignature(signature);
        inistdpayRequestDTO.setVerification(verification);
        inistdpayRequestDTO.setMkey(mKey);
        inistdpayRequestDTO.setGoodname(gbn);
        inistdpayRequestDTO.setSiteDomain(siteDomain);
        mv.addObject("payInfo", inistdpayRequestDTO);

        System.out.println("apply_mobile_payment : " + inistdpayRequestDTO.toString());
        mv.setViewName("/apply/payment_m");
        return mv;
    }



    private static Object convertMapToObject(Map<String,String> map,Object obj){
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;

        for (String s : map.keySet()) {
            keyAttribute = s;
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (methodString.equals(method.getName())) {
                    try {
                        method.invoke(obj, map.get(keyAttribute));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }

    @RequestMapping(value = "/pc/payment/vbank/vacct/noti.do", method = RequestMethod.POST)
    @ResponseBody
    public String payment_vbank_vacct_noti(HttpServletRequest request) {
        System.out.println("EduMarineMngController > payment_vbank_vacct_noti");
        String response = "";
        /*******************************************************************************
         * FILE NAME : vacctinput.jsp
         * DATE : 2009.07
         * 이니시스 가상계좌 입금내역 처리demon으로 넘어오는 파라메터를 control 하는 부분 입니다.
         * [수신정보] 자세한 내용은 메뉴얼 참조
         * 변수명           한글명
         * no_tid           거래번호
         * no_oid           주문번호
         * cd_bank          거래발생 기관코드
         * cd_deal          취급기관코드
         * dt_trans         거래일자
         * tm_trans         거래시각
         * no_vacct         계좌번호
         * amt_input        입금금액
         * amt_check        미결제타점권금액
         * flg_close        마감구분
         * type_msg         거래구분
         * nm_inputbank     입금은행명
         * nm_input         입금자명
         * dt_inputstd      입금기준일자
         * dt_calculstd     정산기준일자
         * dt_transbase     거래기준일자
         * cl_trans         거래구분코드 "1100"
         * cl_close         마감전후 구분,  0:마감점, 1마감후
         * cl_kor           한글구분코드, 2:KSC5601
         *
         * (가상계좌채번시 현금영수증 자동발급신청시에만 전달)
         * dt_cshr          현금영수증 발급일자
         * tm_cshr          현금영수증 발급시간
         * no_cshr_appl     현금영수증 발급번호
         * no_cshr_tid      현금영수증 발급TID
         *******************************************************************************/

        /***********************************************************************************
         * 이니시스가 전달하는 가상계좌이체의 결과를 수신하여 DB 처리 하는 부분 입니다.
         * 필요한 파라메터에 대한 DB 작업을 수행하십시오.
         ***********************************************************************************/

        //PG에서 보냈는지 IP로 체크

        try {
            request.setCharacterEncoding("UTF-8");
            String REMOTE_IP = request.getRemoteAddr();
            String PG_IP = REMOTE_IP.substring(0, 10);
            if (PG_IP.equals("203.238.37") || PG_IP.equals("39.115.212") || PG_IP.equals("183.109.71")) {

                //String file_path = "/home/was/INIpayJAVA/vacct";

                String id_merchant = request.getParameter("id_merchant");
                String no_tid = request.getParameter("no_tid");
                String no_oid = request.getParameter("no_oid");
                String no_vacct = request.getParameter("no_vacct");
                String amt_input = request.getParameter("amt_input");
                String nm_inputbank = request.getParameter("nm_inputbank");
                String nm_input = request.getParameter("nm_input");

                if(no_tid == null){
                    String p_status = request.getParameter("P_STATUS");
                    if("02".equals(p_status)){
                        no_oid = request.getParameter("P_OID");
                        no_vacct = request.getParameter("P_RMESG1");
                        no_vacct = no_vacct.substring(no_vacct.indexOf('=')+1,no_vacct.indexOf('|'));
                        amt_input = request.getParameter("P_AMT");
                    }
                }

                // 매뉴얼을 보시고 추가하실 파라메터가 있으시면 아래와 같은 방법으로 추가하여 사용하시기 바랍니다.

                // String value = reqeust.getParameter("전문의 필드명");

                try {
                    /*writeLog(file_path);*/

                    //System.out.println("************************************************");
                    //System.out.println("PageCall time : " + getTime());
                    //System.out.println("ID_MERCHANT : " + id_merchant);
                    //System.out.println("NO_TID : " + no_tid);
                    //System.out.println("NO_OID : " + no_oid);
                    //System.out.println("NO_VACCT : " + no_vacct);
                    //System.out.println("AMT_INPUT : " + amt_input);
                    //System.out.println("NM_INPUTBANK : " + nm_inputbank);
                    //System.out.println("NM_INPUT : " + nm_input);
                    //System.out.println("************************************************");

                    /*
                    ************************************************
                    PageCall time : [18:32:08]
                    ID_MERCHANT : edumarin90
                    NO_TID : ININPGVBNKedumarin9020240306182322164144
                    NO_OID : edumarin90_1709716699089
                    NO_VACCT : 27489058118161
                    AMT_INPUT : 2000
                    NM_INPUTBANK : ��������
                    NM_INPUT : ��ȸ��
                    ************************************************
                    */

                    Boolean successFlag = true;

                    PaymentDTO paymentDTO = new PaymentDTO();
                    paymentDTO.setMoid(no_oid);
                    paymentDTO.setVactNum(no_vacct);
                    paymentDTO.setTotPrice(amt_input);
                    paymentDTO.setPayStatus("결제완료");
                    Integer updResult = eduMarineService.processUpdatePaymentVbankNoti(paymentDTO);
                    if(updResult > 0){
                        PaymentDTO payInfo = eduMarineService.processSelectPaymentVbankInfo(paymentDTO);

                        String tableSeq = payInfo.getTableSeq();
                        String trainSeq = payInfo.getTrainSeq();
                        String trainName = payInfo.getTrainName();
                        String applyStatus = "결제완료";

                        if (trainName.contains("상시")) {

                            // regular table
                            RegularDTO regularDTO = new RegularDTO();
                            regularDTO.setSeq(tableSeq);
                            regularDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateRegularPayStatus(regularDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("(선내기/")) {

                            // boarder table
                            BoarderDTO boarderDTO = new BoarderDTO();
                            boarderDTO.setSeq(tableSeq);
                            boarderDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateBoarderPayStatus(boarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("FRP")) {

                            // frp table
                            FrpDTO frpDTO = new FrpDTO();
                            frpDTO.setSeq(tableSeq);
                            frpDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateFrpPayStatus(frpDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("(선외기)")) {

                            // outboarder table
                            OutboarderDTO outboarderDTO = new OutboarderDTO();
                            outboarderDTO.setSeq(tableSeq);
                            outboarderDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("(선내기)")) {

                            // inboarder table
                            InboarderDTO inboarderDTO = new InboarderDTO();
                            inboarderDTO.setSeq(tableSeq);
                            inboarderDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("(세일요트)")) {

                            // sailyacht table
                            SailyachtDTO sailyachtDTO = new SailyachtDTO();
                            sailyachtDTO.setSeq(tableSeq);
                            sailyachtDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("고마력 선외기 정비 중급 테크니션")) {

                            if (trainName.contains("(특별반)")) {

                                // highspecial table
                                HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                highSpecialDTO.setSeq(tableSeq);
                                highSpecialDTO.setApplyStatus(applyStatus);
                                ResponseDTO result = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }else{
                                    successFlag = false;
                                }

                            }else{

                                // highhorsepower table
                                HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                highHorsePowerDTO.setSeq(tableSeq);
                                highHorsePowerDTO.setApplyStatus(applyStatus);
                                ResponseDTO result = eduMarineService.processUpdateHighHorsePowerPayStatus(highHorsePowerDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }else{
                                    successFlag = false;
                                }

                            }

                        }else if (trainName.contains("자가정비 심화과정 (고마력 선외기)")) {

                            // highself table
                            HighSelfDTO highSelfDTO = new HighSelfDTO();
                            highSelfDTO.setSeq(tableSeq);
                            highSelfDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateHighSelfPayStatus(highSelfDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            }else{
                                successFlag = false;
                            }

                        } else if (trainName.contains("스턴드라이브")) {

                            if (trainName.contains("(특별반)")) {

                                // sternspecial table
                                SternSpecialDTO sternSpecialDTO = new SternSpecialDTO();
                                sternSpecialDTO.setSeq(tableSeq);
                                sternSpecialDTO.setApplyStatus(applyStatus);
                                ResponseDTO result = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }else{
                                    successFlag = false;
                                }

                            }else{

                                // Sterndrive table
                                SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                sterndriveDTO.setSeq(tableSeq);
                                sterndriveDTO.setApplyStatus(applyStatus);
                                ResponseDTO result = eduMarineService.processUpdateSterndrivePayStatus(sterndriveDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }else{
                                    successFlag = false;
                                }

                            }

                        } else if (trainName.contains("기초정비교육")) {

                            // basic table
                            BasicDTO basicDTO = new BasicDTO();
                            basicDTO.setSeq(tableSeq);
                            basicDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateBasicPayStatus(basicDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (trainName.contains("응급조치교육")) {

                            // basic table
                            EmergencyDTO emergencyDTO = new EmergencyDTO();
                            emergencyDTO.setSeq(tableSeq);
                            emergencyDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else if (trainName.contains("발전기")) {

                            // Generator table
                            GeneratorDTO generatorDTO = new GeneratorDTO();
                            generatorDTO.setSeq(tableSeq);
                            generatorDTO.setApplyStatus(applyStatus);
                            ResponseDTO result = eduMarineService.processUpdateGeneratorPayStatus(generatorDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        }

                    }


                    //***********************************************************************************
                    //
                    //	위에서 상점 데이터베이스에 등록 성공유무에 따라서 성공시에는 "OK"를 이니시스로
                    //	리턴하셔야합니다. 아래 조건에 데이터베이스 성공시 받는 FLAG 변수를 넣으세요
                    //	(주의) OK를 리턴하지 않으시면 이니시스 지불 서버는 "OK"를 수신할때까지 계속 재전송을 시도합니다
                    //	기타 다른 형태의 out.println(response.write)는 하지 않으시기 바랍니다
                    //  System.out.println("====================" + successFlag + "====================");
                    if (successFlag)
                    {
                        System.out.print("OK"); // 절대로 지우지 마세요
                        response = "OK";
                    }

                } catch (Exception ex) {
                    System.out.print(ex.getMessage());
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return response;
    }

    /*private String getDate() {
        Calendar calendar = Calendar.getInstance();

        StringBuffer times = new StringBuffer();
        times.append(Integer.toString(calendar.get(Calendar.YEAR)));
        if ((calendar.get(Calendar.MONTH) + 1) < 10) {
            times.append("0");
        }
        times.append(Integer.toString(calendar.get(Calendar.MONTH) + 1));
        if ((calendar.get(Calendar.DATE)) < 10) {
            times.append("0");
        }
        times.append(Integer.toString(calendar.get(Calendar.DATE)));

        return times.toString();
    }

    private String getTime() {
        Calendar calendar = Calendar.getInstance();

        StringBuffer times = new StringBuffer();

        times.append("[");
        if ((calendar.get(Calendar.HOUR_OF_DAY)) < 10) {
            times.append("0");
        }
        times.append(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)));
        times.append(":");
        if ((calendar.get(Calendar.MINUTE)) < 10) {
            times.append("0");
        }
        times.append(Integer.toString(calendar.get(Calendar.MINUTE)));
        times.append(":");
        if ((calendar.get(Calendar.SECOND)) < 10) {
            times.append("0");
        }
        times.append(Integer.toString(calendar.get(Calendar.SECOND)));
        times.append("]");

        return times.toString();
    }

    private void writeLog(String file_path) throws Exception {

        File file = new File(file_path);
        file.createNewFile();

        FileWriter file2 = new FileWriter(file_path + "/vacctinput_" + getDate() + ".log", true);


        file2.write("\n************************************************\n");
        file2.write("PageCall time : " + getTime());
        file2.write("\nID_MERCHANT : " + id_merchant);
        file2.write("\nNO_TID : " + no_tid);
        file2.write("\nNO_OID : " + no_oid);
        file2.write("\nNO_VACCT : " + no_vacct);
        file2.write("\nAMT_INPUT : " + amt_input);
        file2.write("\nNM_INPUTBANK : " + nm_inputbank);
        file2.write("\nNM_INPUT : " + nm_input);
        file2.write("\n************************************************\n");

        file2.close();

    }*/

    @RequestMapping(value = "/payment/INIstdpay_pc_req.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView payment_INIstdpay_pc_req(@RequestBody InistdpayRequestDTO inistdpayRequestDTO) {
        System.out.println("EduMarineController > payment_INIstdpay_pc_req");
        ModelAndView mv = new ModelAndView();
        System.out.println(inistdpayRequestDTO.toString());
        mv.setViewName("/payment/INIstdpay_pc_req");
        return mv;
    }

    @RequestMapping(value = "/payment/close.do", method = RequestMethod.GET)
    public ModelAndView payment_close(String trainSeq , String trainUrl) {
        System.out.println("EduMarineController > payment_close");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/payment/close");
        return mv;
    }

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
                    String path = ResourceUtils.getFile("/usr/local/tomcat/webapps/upload/" + uploadFilePath).toPath().toString();

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

    @RequestMapping(value = "/file/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> file_delete(@RequestBody FileDTO fileDTO) {
        System.out.println("KibsController > file_delete");
        //System.out.println(fileDTO.toString());

        ResponseDTO responseDTO = eduMarineService.processDeleteFile(fileDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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