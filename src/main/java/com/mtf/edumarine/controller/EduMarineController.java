package com.mtf.edumarine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.mtf.edumarine.config.ExternalApiProperties;
import lombok.extern.slf4j.Slf4j;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;
import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineMngService;
import com.mtf.edumarine.service.EduMarineService;
import com.mtf.edumarine.util.PasswordUtil;
import com.mtf.edumarine.util.SignUtil;
import com.mtf.edumarine.util.SimpleRateLimiter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
@Slf4j
public class EduMarineController {

    // 필드 주입이 아닌 생성자 주입형태로 사용합니다. '생성자 주입 형태'로 사용합니다.
    private final EduMarineService eduMarineService;

    private final CommService commService;

    private final EduMarineMngService eduMarineMngService;

    /** 2026-10-30 보안 조치(2단계): 외부 서비스 키는 설정 파일(application.properties)에서 읽는다. */
    @Autowired
    private ExternalApiProperties externalApi;


    public EduMarineController(EduMarineService ks, CommService cs, EduMarineMngService ms){
        this.eduMarineService = ks;
        this.commService = cs;
        this.eduMarineMngService = ms;
    }

    //***************************************************************************
    // 2026-10-30 보안 조치 공통 헬퍼 (세션 회원 확인 / 소유자 검사)
    //***************************************************************************

    /** 세션 로그인 회원의 member seq (미로그인·회원 없음이면 null) */
    private String getSessionMemberSeq(HttpSession session) {
        if (session == null || session.getAttribute("id") == null) {
            return null;
        }
        MemberDTO member = eduMarineService.processSelectMemberSingle(session.getAttribute("id").toString());
        return member == null ? null : member.getSeq();
    }

    /** 세션 회원이 해당 신청 레코드(memberSeq)의 소유자인지 */
    private boolean isOwner(HttpSession session, String recordMemberSeq) {
        String mine = getSessionMemberSeq(session);
        return mine != null && recordMemberSeq != null && mine.equals(recordMemberSeq.trim());
    }

    private ResponseEntity<ResponseDTO> forbidden() {
        ResponseDTO res = new ResponseDTO();
        res.setResultCode(CommConstants.RESULT_CODE_FAIL);
        res.setResultMessage("본인의 신청 정보만 처리할 수 있습니다.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 신청 상태 변경(/apply/eduApplyNN/update/status.do) 공통 검증.
     * - 로그인 회원 본인 신청만 변경 가능
     * - '결제완료' 는 교육비가 1원 이상으로 확인되는 유료 교육이면 거부 (0원·미입력·교육 미확인은 허용, 유료는 결제 승인 시 서버가 설정)
     * @return 통과면 null, 아니면 실패 응답
     */
    private ResponseEntity<ResponseDTO> guardStatusUpdate(HttpSession session, String recordMemberSeq, String recordTrainSeq,
                                                          String applyStatus, String requestTrainSeq) {
        if (!isOwner(session, recordMemberSeq)) {
            return forbidden();
        }
        if ("결제완료".equals(applyStatus)) {
            String trainSeq = (requestTrainSeq != null && !requestTrainSeq.isEmpty()) ? requestTrainSeq : recordTrainSeq;
            TrainDTO train = (trainSeq == null || trainSeq.isEmpty()) ? null : eduMarineService.processSelectTrainSingle(trainSeq);
            if (train != null && train.getPaySum() != null && train.getPaySum() > 0) {
                ResponseDTO res = new ResponseDTO();
                res.setResultCode(CommConstants.RESULT_CODE_FAIL);
                res.setResultMessage("유료 교육은 결제 완료 후 상태가 변경됩니다.");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        return null;
    }

    /** 이메일 마스킹 (앞 3자 유지) */
    private String maskEmail(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }
        int at = email.indexOf('@');
        if (at <= 0) {
            return "***";
        }
        String local = email.substring(0, at);
        int keep = Math.min(3, local.length());
        return local.substring(0, keep) + "***" + email.substring(at);
    }

    /** 아이디 마스킹 (앞 2자·뒤 2자 유지, 화면의 maskingName 과 같은 규칙) */
    private String maskId(String id) {
        if (id == null) {
            return null;
        }
        if (id.length() <= 4) {
            return id.isEmpty() ? id : id.charAt(0) + "***";
        }
        StringBuilder sb = new StringBuilder(id);
        for (int i = 2; i < id.length() - 2; i++) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    private String escapeHtml(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }

    private String buildInitPasswordMailBody(String id, String tempPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div style='font-family:sans-serif; line-height:1.7;'>");
        sb.append("<p>안녕하세요. 경기해양레저 인력양성센터입니다.</p>");
        sb.append("<p>요청하신 아이디 [ ").append(escapeHtml(id)).append(" ] 의 비밀번호가 아래 임시 비밀번호로 초기화되었습니다.</p>");
        sb.append("<p style='font-size:18px; font-weight:bold;'>임시 비밀번호 : ").append(escapeHtml(tempPassword)).append("</p>");
        sb.append("<p>로그인 후 마이페이지에서 반드시 비밀번호를 변경해 주세요.</p>");
        sb.append("<p>본인이 요청하지 않았다면 센터(Tel. 1811-7891)로 문의해 주세요.</p>");
        sb.append("</div>");
        return sb.toString();
    }

    //***************************************************************************
    // Home
    //***************************************************************************

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session, ModelAndView mv) {
        eduMarineService.logoutCheck(session);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public ModelAndView main() {
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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sitemap");
        return mv;
    }

    @RequestMapping(value = "/search.do", method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search");
        return mv;
    }

    //***************************************************************************
    // member Folder
    //***************************************************************************

    @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
    public ModelAndView member_login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/login");
        return mv;
    }

    @RequestMapping(value = "/member/login/submit.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_login_submit(@RequestBody MemberDTO memberDTO, HttpSession session, HttpServletRequest request) {
        // 2026-10-30 보안 조치(M-1): 실패 횟수 제한(같은 ID 10분 20회), 성공 시 세션 ID 교체. 로그인 방식은 그대로.
        String limitKey = "login:member:" + (memberDTO.getId() == null ? "" : memberDTO.getId().trim());
        if (SimpleRateLimiter.isBlocked(limitKey, 20, 10 * 60 * 1000L)) {
            ResponseDTO blocked = new ResponseDTO();
            blocked.setResultCode(CommConstants.RESULT_CODE_FAIL);
            blocked.setResultMessage("로그인 시도가 너무 많습니다. 10분 후 다시 시도해주세요.");
            return new ResponseEntity<>(blocked, HttpStatus.OK);
        }
        ResponseDTO response = eduMarineService.processCheckMemberSingle(memberDTO);
        if(response.getResultCode().equals("0")){
            SimpleRateLimiter.reset(limitKey);
            try {
                request.changeSessionId(); // 세션 고정 방지 (속성은 유지됨)
            } catch (Exception e) {
                // 세션 ID 교체 실패는 로그인에 영향 없음
            }
            session.setAttribute("status", "logon");
            session.setAttribute("id", memberDTO.getId());
        } else {
            SimpleRateLimiter.recordFailure(limitKey, 10 * 60 * 1000L);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/findpw.do", method = RequestMethod.GET)
    public ModelAndView member_findpw() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/findpw");
        return mv;
    }

    @RequestMapping(value = "/member/getEmail.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> member_getEmail(@RequestBody MemberDTO memberDTO) {
        // 2026-10-30 보안 조치(C-5): 아이디만으로 이메일 전체가 노출되지 않도록 마스킹해 돌려준다 (화면 안내 문구용)
        String result = eduMarineService.getMemberEmail(memberDTO);
        return new ResponseEntity<>(maskEmail(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/member/initPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_initPassword(@RequestBody MemberDTO memberDTO, HttpServletRequest request) {
        // 2026-10-30 보안 조치(C-5): 고정 비밀번호 대신 무작위 임시 비밀번호를 서버가 만들고 메일도 서버가 보낸다.
        //  - 메일 발송에 성공한 뒤에만 DB 비밀번호를 바꾼다 (메일 실패 시 기존 비밀번호 유지)
        //  - 같은 ID 10분 6회, 같은 IP 10분 20회로 제한
        ResponseDTO response = new ResponseDTO();
        String id = memberDTO.getId() == null ? "" : memberDTO.getId().trim();
        if (id.isEmpty()
                || !SimpleRateLimiter.tryAcquire("pwinit:" + id, 6, 10 * 60 * 1000L)
                || !SimpleRateLimiter.tryAcquire("pwinit:ip:" + request.getRemoteAddr(), 20, 10 * 60 * 1000L)) {
            response.setResultCode(CommConstants.RESULT_CODE_FAIL);
            response.setResultMessage("요청이 너무 많습니다. 잠시 후 다시 시도해주세요.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        memberDTO.setId(id);
        String email = eduMarineService.getMemberEmail(memberDTO);
        if (email == null || email.trim().isEmpty()) {
            response.setResultCode("-2");
            response.setResultMessage("등록된 이메일이 없습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        String tempPassword = PasswordUtil.generateTempPassword(10);

        MailRequestDTO mail = new MailRequestDTO();
        mail.setSubject("[ 경기해양레저 인력양성센터 ] 비밀번호 초기화 요청");
        mail.setBody(buildInitPasswordMailBody(id, tempPassword));
        mail.setTemplate("");
        MailRequestDTO.Receiver receiver = new MailRequestDTO.Receiver();
        receiver.setEmail(email.trim());
        mail.setReceiver(Collections.singletonList(receiver));
        ResponseDTO mailRes = eduMarineMngService.processMailSend(mail);
        if (mailRes == null || !CommConstants.RESULT_CODE_SUCCESS.equals(mailRes.getResultCode())) {
            response.setResultCode("-2");
            response.setResultMessage("메일 전송에 실패하였습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        memberDTO.setPassword(tempPassword);
        response = eduMarineService.initMemberPassword(memberDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/join.do", method = RequestMethod.GET)
    public ModelAndView member_join() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/join");
        return mv;
    }

    @RequestMapping(value = "/member/join/member/check.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_join_member_check(@RequestBody MemberDTO memberDTO) {

        ResponseDTO responseDTO = eduMarineService.processCheckMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/join/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_join_insert(@RequestBody MemberDTO memberDTO) {

        ResponseDTO responseDTO = eduMarineService.processInsertMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/complete.do", method = RequestMethod.GET)
    public ModelAndView member_complete() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/complete");
        return mv;
    }

    @RequestMapping(value = "/checkDuplicateId.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> checkDuplicateId(@RequestBody MemberDTO memberDTO) {
        Integer result = eduMarineService.checkDuplicateId(memberDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //***************************************************************************
    // apply Folder
    //***************************************************************************

    @RequestMapping(value = "/apply/schedule.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView apply_schedule(String searchText) {
        ModelAndView mv = new ModelAndView();
        if(searchText != null && !searchText.isEmpty()){
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
                case "EDU20":
                    trainName = "레저선박 해양전자장비 교육";
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

        List<TrainDTO> responseList = eduMarineService.processSelectTrainScheduleList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/schedule/calendar/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<TrainDTO>> apply_schedule_calendar_selectList(@RequestBody TrainDTO trainDTO) {
        List<TrainDTO> responseList = eduMarineService.processSelectTrainScheduleCalendarList(trainDTO);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply01(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectRegularPreCheck(regularDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply02/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply02_preCheck(@RequestBody BoarderDTO boarderDTO) {

        Integer result = eduMarineService.processSelectBoarderPreCheck(boarderDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply03/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply03_preCheck(@RequestBody FrpDTO frpDTO) {

        Integer result = eduMarineService.processSelectFrpPreCheck(frpDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply04/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply04_preCheck(@RequestBody InboarderDTO inboarderDTO) {

        Integer result = eduMarineService.processSelectInboarderPreCheck(inboarderDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply05/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply05_preCheck(@RequestBody OutboarderDTO outboarderDTO) {

        Integer result = eduMarineService.processSelectOutboarderPreCheck(outboarderDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply06/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply06_preCheck(@RequestBody SailyachtDTO sailyachtDTO) {

        Integer result = eduMarineService.processSelectSailyachtPreCheck(sailyachtDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply01_insert(@RequestBody RegularDTO regularDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            regularDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply01_update_status(@RequestBody RegularDTO regularDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        RegularDTO existing = eduMarineService.processSelectRegularSingle(regularDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                regularDTO.getApplyStatus(), regularDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateRegularPayStatus(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply02/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply02_update_status(@RequestBody BoarderDTO boarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        BoarderDTO existing = eduMarineService.processSelectBoarderSingle(boarderDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                boarderDTO.getApplyStatus(), boarderDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBoarderPayStatus(boarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply03/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply03_update_status(@RequestBody FrpDTO frpDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        FrpDTO existing = eduMarineService.processSelectFrpSingle(frpDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                frpDTO.getApplyStatus(), frpDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateFrpPayStatus(frpDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply04/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply04_update_status(@RequestBody InboarderDTO inboarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        InboarderDTO existing = eduMarineService.processSelectInboarderSingle(inboarderDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                inboarderDTO.getApplyStatus(), inboarderDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply05/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply05_update_status(@RequestBody OutboarderDTO outboarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        OutboarderDTO existing = eduMarineService.processSelectOutboarderSingle(outboarderDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                outboarderDTO.getApplyStatus(), outboarderDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply06/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply06_update_status(@RequestBody SailyachtDTO sailyachtDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        SailyachtDTO existing = eduMarineService.processSelectSailyachtSingle(sailyachtDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                sailyachtDTO.getApplyStatus(), sailyachtDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply07/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply07_update_status(@RequestBody HighHorsePowerDTO highHorsePowerDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        HighHorsePowerDTO existing = eduMarineService.processSelectHighHorsePowerSingle(highHorsePowerDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                highHorsePowerDTO.getApplyStatus(), highHorsePowerDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateHighHorsePowerPayStatus(highHorsePowerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply08/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply08_update_status(@RequestBody SterndriveDTO sterndriveDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        SterndriveDTO existing = eduMarineService.processSelectSterndriveSingle(sterndriveDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                sterndriveDTO.getApplyStatus(), sterndriveDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateSterndrivePayStatus(sterndriveDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply09/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply09_update_status(@RequestBody HighSelfDTO highSelfDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        HighSelfDTO existing = eduMarineService.processSelectHighSelfSingle(highSelfDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                highSelfDTO.getApplyStatus(), highSelfDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSelfPayStatus(highSelfDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply10/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply10_update_status(@RequestBody HighSpecialDTO highSpecialDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        HighSpecialDTO existing = eduMarineService.processSelectHighSpecialSingle(highSpecialDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                highSpecialDTO.getApplyStatus(), highSpecialDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply11/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply11_update_status(@RequestBody SternSpecialDTO sternSpecialDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        SternSpecialDTO existing = eduMarineService.processSelectSternSpecialSingle(sternSpecialDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                sternSpecialDTO.getApplyStatus(), sternSpecialDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply12/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply12_update_status(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        BasicDTO existing = eduMarineService.processSelectBasicSingle(basicDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                basicDTO.getApplyStatus(), basicDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBasicPayStatus(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply13/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply13_update_status(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        BasicDTO existing = eduMarineService.processSelectBasicSingle(basicDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                basicDTO.getApplyStatus(), basicDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBasicPayStatus(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply14/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply14_update_status(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        BasicDTO existing = eduMarineService.processSelectBasicSingle(basicDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                basicDTO.getApplyStatus(), basicDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBasicPayStatus(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply15/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply15_update_status(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        EmergencyDTO existing = eduMarineService.processSelectEmergencySingle(emergencyDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                emergencyDTO.getApplyStatus(), emergencyDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply16/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply16_update_status(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        EmergencyDTO existing = eduMarineService.processSelectEmergencySingle(emergencyDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                emergencyDTO.getApplyStatus(), emergencyDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply17/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply17_update_status(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        EmergencyDTO existing = eduMarineService.processSelectEmergencySingle(emergencyDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                emergencyDTO.getApplyStatus(), emergencyDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergencyPayStatus(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply18/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply18_update_status(@RequestBody GeneratorDTO generatorDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        GeneratorDTO existing = eduMarineService.processSelectGeneratorSingle(generatorDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                generatorDTO.getApplyStatus(), generatorDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateGeneratorPayStatus(generatorDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply19/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply19_update_status(@RequestBody CompetencyDTO competencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        CompetencyDTO existing = eduMarineService.processSelectCompetencySingle(competencyDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                competencyDTO.getApplyStatus(), competencyDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateCompetencyPayStatus(competencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply20/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply20_update_status(@RequestBody FamtourinDTO famtourinDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        FamtourinDTO existing = eduMarineService.processSelectFamtourinSingle(famtourinDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                famtourinDTO.getApplyStatus(), famtourinDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtourinPayStatus(famtourinDTO);

        if("결제완료".equals(famtourinDTO.getApplyStatus())) {
            eduMarineService.processUpdateTrainApplyCnt(famtourinDTO.getTrainSeq());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply21/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply21_update_status(@RequestBody FamtouroutDTO famtouroutDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        FamtouroutDTO existing = eduMarineService.processSelectFamtouroutSingle(famtouroutDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                famtouroutDTO.getApplyStatus(), famtouroutDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtouroutPayStatus(famtouroutDTO);

        if("결제완료".equals(famtouroutDTO.getApplyStatus())) {
            eduMarineService.processUpdateTrainApplyCnt(famtouroutDTO.getTrainSeq());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply22/update/status.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply22_update_status(@RequestBody ElectroDTO electroDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 본인 신청만 변경, '결제완료' 는 유료(교육비 1원 이상) 교육이면 거부
        ElectroDTO existing = eduMarineService.processSelectElectroSingle(electroDTO.getSeq());
        ResponseEntity<ResponseDTO> guard = guardStatusUpdate(session,
                existing == null ? null : existing.getMemberSeq(),
                existing == null ? null : existing.getTrainSeq(),
                electroDTO.getApplyStatus(), electroDTO.getTrainSeq());
        if (guard != null) {
            return guard;
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateElectroPayStatus(electroDTO);

        if("결제완료".equals(electroDTO.getApplyStatus())) {
            eduMarineService.processUpdateTrainApplyCnt(electroDTO.getTrainSeq());
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply01/pre/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RegularDTO> apply_eduApply01_pre_selectSingle(@RequestBody RegularDTO regularDTO) {

        RegularDTO responseDTO = eduMarineService.processSelectPreRegularSingle(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply02.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply02(String seq, HttpSession session) {
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
    public ResponseEntity<ResponseDTO> apply_eduApply02_insert(@RequestBody BoarderDTO boarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            boarderDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertBoarder(boarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply03.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply03(String seq, HttpSession session) {
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
    public ResponseEntity<ResponseDTO> apply_eduApply03_insert(@RequestBody FrpDTO frpDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            frpDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertFrp(frpDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply04.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply04(String seq, HttpSession session) {
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
    public ResponseEntity<ResponseDTO> apply_eduApply04_insert(@RequestBody InboarderDTO inboarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            inboarderDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply04_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply04_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        InboarderDTO info = eduMarineService.processSelectInboarderSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
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
    public ResponseEntity<ResponseDTO> apply_eduApply05_insert(@RequestBody OutboarderDTO outboarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            outboarderDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertOutboarder(outboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply05_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply05_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        OutboarderDTO info = eduMarineService.processSelectOutboarderSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
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
    public ModelAndView mypage_eduApply06_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        SailyachtDTO info = eduMarineService.processSelectSailyachtSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
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
    public ResponseEntity<ResponseDTO> apply_eduApply06_insert(@RequestBody SailyachtDTO sailyachtDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            sailyachtDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertSailyacht(sailyachtDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply07.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply07(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectHighHorsePowerPreCheck(highHorsePowerDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply07/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply07_insert(@RequestBody HighHorsePowerDTO highHorsePowerDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            highHorsePowerDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertHighHorsePower(highHorsePowerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply07_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply07_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        HighHorsePowerDTO info = eduMarineService.processSelectHighHorsePowerSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply07_update(@RequestBody HighHorsePowerDTO highHorsePowerDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        HighHorsePowerDTO existing = eduMarineService.processSelectHighHorsePowerSingle(highHorsePowerDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateHighhorsepower(highHorsePowerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply08.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply08(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectSterndrivePreCheck(sterndriveDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply08/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply08_insert(@RequestBody SterndriveDTO sterndriveDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            sterndriveDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertSterndrive(sterndriveDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply08_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply08_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        SterndriveDTO info = eduMarineService.processSelectSterndriveSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply08_update(@RequestBody SterndriveDTO sterndriveDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        SterndriveDTO existing = eduMarineService.processSelectSterndriveSingle(sterndriveDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateSterndrive(sterndriveDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply09.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply09(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectHighSelfPreCheck(highselfDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply09/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply09_insert(@RequestBody HighSelfDTO highselfDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            highselfDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertHighSelf(highselfDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply09_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply09_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        HighSelfDTO info = eduMarineService.processSelectHighSelfSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply09_update(@RequestBody HighSelfDTO highselfDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        HighSelfDTO existing = eduMarineService.processSelectHighSelfSingle(highselfDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSelf(highselfDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply10.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply10(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectHighSpecialPreCheck(highSpecialDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply10/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply10_insert(@RequestBody HighSpecialDTO highSpecialDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            highSpecialDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertHighSpecial(highSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply10_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply10_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        HighSpecialDTO info = eduMarineService.processSelectHighSpecialSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
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
    public ResponseEntity<ResponseDTO> mypage_eduApply10_update(@RequestBody HighSpecialDTO highSpecialDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        HighSpecialDTO existing = eduMarineService.processSelectHighSpecialSingle(highSpecialDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateHighSpecial(highSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply11.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply11(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectSternSpecialPreCheck(sternSpecialDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply11/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply11_insert(@RequestBody SternSpecialDTO sternSpecialDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            sternSpecialDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertSternSpecial(sternSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply11_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply11_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        SternSpecialDTO info = eduMarineService.processSelectSternSpecialSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
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
    public ResponseEntity<ResponseDTO> mypage_eduApply11_update(@RequestBody SternSpecialDTO sternSpecialDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        SternSpecialDTO existing = eduMarineService.processSelectSternSpecialSingle(sternSpecialDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateSternSpecial(sternSpecialDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply12.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply12(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectBasicPreCheck(basicDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply12/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply12_insert(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            basicDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply12_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply12_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        BasicDTO info = eduMarineService.processSelectBasicSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply12_update(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        BasicDTO existing = eduMarineService.processSelectBasicSingle(basicDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply13.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply13(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectBasicPreCheck(basicDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply13/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply13_insert(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            basicDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply13_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply13_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        BasicDTO info = eduMarineService.processSelectBasicSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply13_update(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        BasicDTO existing = eduMarineService.processSelectBasicSingle(basicDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply14.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply14(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectBasicPreCheck(basicDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply14/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply14_insert(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            basicDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply14_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply14_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        BasicDTO info = eduMarineService.processSelectBasicSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply14_update(@RequestBody BasicDTO basicDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        BasicDTO existing = eduMarineService.processSelectBasicSingle(basicDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBasic(basicDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply15.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply15(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectEmergencyPreCheck(emergencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply15/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply15_insert(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            emergencyDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply15_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply15_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        EmergencyDTO info = eduMarineService.processSelectEmergencySingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply15_update(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        EmergencyDTO existing = eduMarineService.processSelectEmergencySingle(emergencyDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply16.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply16(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectEmergencyPreCheck(emergencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply16/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply16_insert(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            emergencyDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply16_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply16_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        EmergencyDTO info = eduMarineService.processSelectEmergencySingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply16_update(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        EmergencyDTO existing = eduMarineService.processSelectEmergencySingle(emergencyDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply17.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply17(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectEmergencyPreCheck(emergencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply17/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply17_insert(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            emergencyDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply17_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply17_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        EmergencyDTO info = eduMarineService.processSelectEmergencySingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply17_update(@RequestBody EmergencyDTO emergencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        EmergencyDTO existing = eduMarineService.processSelectEmergencySingle(emergencyDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateEmergency(emergencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply18.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply18(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectGeneratorPreCheck(generatorDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply18/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply18_insert(@RequestBody GeneratorDTO generatorDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            generatorDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertGenerator(generatorDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply18_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply18_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        GeneratorDTO info = eduMarineService.processSelectGeneratorSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
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
    public ResponseEntity<ResponseDTO> mypage_eduApply18_update(@RequestBody GeneratorDTO generatorDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        GeneratorDTO existing = eduMarineService.processSelectGeneratorSingle(generatorDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateGenerator(generatorDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply19.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply19(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectCompetencyPreCheck(competencyDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply19/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply19_insert(@RequestBody CompetencyDTO competencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            competencyDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertCompetency(competencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply19_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply19_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        CompetencyDTO info = eduMarineService.processSelectCompetencySingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply19_update(@RequestBody CompetencyDTO competencyDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        CompetencyDTO existing = eduMarineService.processSelectCompetencySingle(competencyDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateCompetency(competencyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply20.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply20(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectFamtourinPreCheck(famtourinDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply20/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply20_insert(@RequestBody FamtourinDTO famtourinDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            famtourinDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertFamtourin(famtourinDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply20_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply20_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        FamtourinDTO info = eduMarineService.processSelectFamtourinSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply20_update(@RequestBody FamtourinDTO famtourinDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        FamtourinDTO existing = eduMarineService.processSelectFamtourinSingle(famtourinDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtourin(famtourinDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply21.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply21(String seq, HttpSession session) {
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

        Integer result = eduMarineService.processSelectFamtouroutPreCheck(famtouroutDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply21/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply21_insert(@RequestBody FamtouroutDTO famtouroutDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            famtouroutDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertFamtourout(famtouroutDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply21_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply21_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        FamtouroutDTO info = eduMarineService.processSelectFamtouroutSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply21_update(@RequestBody FamtouroutDTO famtouroutDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        FamtouroutDTO existing = eduMarineService.processSelectFamtouroutSingle(famtouroutDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateFamtourout(famtouroutDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply22.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApply22(String seq, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("id") != null){
            mv.addObject("seq", seq);

            String id = session.getAttribute("id").toString();
            MemberDTO info = eduMarineService.processSelectMemberSingle(id);
            mv.addObject("info", info);

        }

        mv.setViewName("/apply/eduApply22");
        return mv;
    }

    @RequestMapping(value = "/apply/eduApply22/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApply22_preCheck(@RequestBody ElectroDTO electroDTO) {

        Integer result = eduMarineService.processSelectElectroPreCheck(electroDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApply22/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApply22_insert(@RequestBody ElectroDTO electroDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 상태면 회원번호는 세션 기준으로 고정 (타인 명의 신청 차단)
        String sessionMemberSeq = getSessionMemberSeq(session);
        if (sessionMemberSeq != null) {
            electroDTO.setMemberSeq(sessionMemberSeq);
        }

        ResponseDTO responseDTO = eduMarineService.processInsertElectro(electroDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply22_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply22_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        ElectroDTO info = eduMarineService.processSelectElectroSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            if(modYn == null || modYn.isEmpty()){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);
        }

        mv.setViewName("/mypage/eduApply22_modify");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduApply22/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply22_update(@RequestBody ElectroDTO electroDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        ElectroDTO existing = eduMarineService.processSelectElectroSingle(electroDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateElectro(electroDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApplyUnified.do", method = RequestMethod.GET)
    public ModelAndView apply_eduApplyUnified(String seq, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 1. 세션에서 ID 확인
        Object sessionId = session.getAttribute("id");

        // [CASE 1] 로그인을 안 한 경우
        if (sessionId == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        // 2. DB에서 회원 정보 조회
        String id = sessionId.toString();
        MemberDTO info = eduMarineService.processSelectMemberSingle(id);

        // [CASE 2] 세션 ID는 있지만, DB에 회원 정보가 없는 경우 (정보 불일치/삭제됨)
        if (info == null) {
            log.warn("세션 ID[{}]에 해당하는 회원 정보가 없어 강제 로그아웃 처리", id);
            session.invalidate(); // ★ 핵심: 세션 정보 삭제
            mv.setViewName("redirect:/member/login.do"); // 로그인 페이지로 이동
            return mv;
        }

        try {
            // 회원 신청 정보 조회
            ApplicationUnifiedDTO appInfo = eduMarineService.processSelectApplicationUnifiedSingle(info.getSeq());
            mv.addObject("appInfo", appInfo);

            // 교육 과정 정보 조회
            if (seq != null && !seq.isEmpty()) {
                TrainDTO trainInfo = eduMarineService.processSelectTrainSingle(seq);
                mv.addObject("trainInfo", trainInfo);
                mv.addObject("seq", seq);
            }

            mv.addObject("info", info);
            mv.setViewName("/apply/eduApplyUnified"); // 이동할 JSP 페이지

        } catch (Exception e) {
            log.error("[apply_eduApplyUnified] 처리 중 예외", e);
        }

        return mv;
    }

    /**
     * [신규] 통합 교육 신청서 저장
     */
    @RequestMapping(value = "/apply/eduApplyUnified/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> apply_eduApplyUnified_insert(
            @RequestBody ApplicationUnifiedDTO dto,
            HttpServletRequest request,
            HttpSession session) {

        ResponseDTO response = new ResponseDTO();
        try {
            // 세션 정보 바인딩 (2026-10-30 보안 조치(H-1): 세션 회원번호로 고정. 기존 코드의 "seq" 속성은 어디서도 넣지 않아 항상 null 이었음)
            String memberSeq = getSessionMemberSeq(session);
            if (memberSeq != null && !memberSeq.isEmpty()) dto.setMemberSeq(memberSeq);

            // ★ 교육비 0원(무료) 검증 및 상태 자동 지정 로직 추가
            TrainDTO trainDTO = eduMarineService.processSelectTrainSingle(dto.getTrainSeq());
            if (trainDTO != null && trainDTO.getPaySum() != null && trainDTO.getPaySum() == 0) {
                dto.setApplyStatus("결제완료"); // 무료 교육이므로 즉시 결제완료(신청완료 대상) 처리
            } else {
                dto.setApplyStatus("결제대기"); // 기본 상태
            }

            // 서비스 호출
            eduMarineService.processInsertUnifiedApplication(dto);

            // ★ 무료 교육일 경우 즉시 교육의 신청 인원(train_apply_cnt) 증가 처리
            if ("결제완료".equals(dto.getApplyStatus())) {
                eduMarineService.processUpdateTrainApplyCnt(dto.getTrainSeq());
            }

            response.setResultCode("0");
            response.setResultMessage("저장되었습니다.");
            response.setCustomValue(dto.getSeq()); // 생성된 신청 번호 반환

        } catch (Exception e) {
            log.error("[apply_eduApplyUnified_insert] 처리 중 예외", e);
            response.setResultCode("-1");
            response.setResultMessage("오류 발생: " + e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    
    @RequestMapping(value = "/apply/faq.do", method = RequestMethod.GET)
    public ModelAndView apply_faq() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/apply/faq");
        return mv;
    }

    @RequestMapping(value = "/apply/faq/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<FaqDTO>> apply_faq_selectList(@RequestBody SearchDTO searchDTO) {

        List<FaqDTO> responseList = eduMarineService.processSelectFaqList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    //***************************************************************************
    // board Folder
    //***************************************************************************

    @RequestMapping(value = "/board/notice_list.do", method = RequestMethod.GET)
    public ModelAndView board_notice_list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/notice_list");
        return mv;
    }

    @RequestMapping(value = "/board/notice/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<NoticeDTO>> board_notice_selectList(@RequestBody SearchDTO searchDTO) {

        List<NoticeDTO> responseList = eduMarineService.processSelectBoardNoticeList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/notice_view.do", method = RequestMethod.GET)
    public ModelAndView board_notice_view(String seq) {
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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/press_list");
        return mv;
    }

    @RequestMapping(value = "/board/press/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<PressDTO>> board_press_selectList(@RequestBody SearchDTO searchDTO) {

        List<PressDTO> responseList = eduMarineService.processSelectBoardPressList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/press_view.do", method = RequestMethod.GET)
    public ModelAndView board_press_view(String seq) {
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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/gallery");
        return mv;
    }

    @RequestMapping(value = "/board/gallery/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<GalleryDTO>> board_gallery_selectList(@RequestBody SearchDTO searchDTO) {

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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/media");
        return mv;
    }

    @RequestMapping(value = "/board/media/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<MediaDTO>> board_media_selectList(@RequestBody SearchDTO searchDTO) {

        List<MediaDTO> responseList = eduMarineService.processSelectBoardMediaList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/news_list.do", method = RequestMethod.GET)
    public ModelAndView board_news_list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/news_list");
        return mv;
    }

    @RequestMapping(value = "/board/news/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<NewsletterDTO>> board_news_selectList(@RequestBody SearchDTO searchDTO) {

        List<NewsletterDTO> responseList = eduMarineService.processSelectBoardNewsList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/news_view.do", method = RequestMethod.GET)
    public ModelAndView board_news_view(String seq) {
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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/announcement_list");
        return mv;
    }

    @RequestMapping(value = "/job/announcement_view.do", method = RequestMethod.GET)
    public ModelAndView job_announcement_view() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/announcement_view");
        return mv;
    }

    @RequestMapping(value = "/job/state01.do", method = RequestMethod.GET)
    public ModelAndView job_state01() {
        ModelAndView mv = new ModelAndView();

        String gbn = "창업자";
        List<EmploymentDTO> employmentList = eduMarineService.processSelectEmploymentList(gbn);
        mv.addObject("employmentList", employmentList);
        mv.setViewName("/job/state01");
        return mv;
    }

    @RequestMapping(value = "/job/state02.do", method = RequestMethod.GET)
    public ModelAndView job_state02() {
        ModelAndView mv = new ModelAndView();

        String gbn = "취업자";
        List<EmploymentDTO> employmentList = eduMarineService.processSelectEmploymentList(gbn);
        mv.addObject("employmentList", employmentList);
        mv.setViewName("/job/state02");
        return mv;
    }

    @RequestMapping(value = "/job/review.do", method = RequestMethod.GET)
    public ModelAndView job_review() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/review");
        return mv;
    }

    @RequestMapping(value = "/job/review/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<JobDTO>> job_review_selectList(@RequestBody SearchDTO searchDTO) {

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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/job/community_list");
        return mv;
    }

    @RequestMapping(value = "/job/community/selectList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<CommunityDTO>> job_community_selectList(@RequestBody SearchDTO searchDTO) {

        List<CommunityDTO> responseList = eduMarineService.processSelectCommunityList(searchDTO);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_view.do", method = RequestMethod.GET)
    public ModelAndView job_community_view(String seq, HttpSession session) {
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

        ResponseDTO responseDTO = eduMarineService.processCheckCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/reply/check.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_reply_check(@RequestBody ReplyDTO replyDTO) {

        ResponseDTO responseDTO = eduMarineService.processCheckReply(replyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/reply/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_reply_insert(@RequestBody ReplyDTO replyDTO) {

        ResponseDTO responseDTO = eduMarineService.processInsertReply(replyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/reply/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_reply_delete(@RequestBody ReplyDTO replyDTO) {

        ResponseDTO responseDTO = eduMarineService.processDeleteReply(replyDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/recommend/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_recommend_update(@RequestBody RecommendDTO recommendDTO) {

        ResponseDTO responseDTO = eduMarineService.processUpdateRecommend(recommendDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_write.do", method = RequestMethod.GET)
    public ModelAndView job_community_write(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String id = String.valueOf(session.getAttribute("id"));
        mv.addObject("id", id);
        mv.setViewName("/job/community_write");
        return mv;
    }

    @RequestMapping(value = "/job/community/write/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_insert(@RequestBody CommunityDTO communityDTO) {

        ResponseDTO responseDTO = eduMarineService.processInsertCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community_modify.do", method = RequestMethod.GET)
    public ModelAndView job_community_modify(String seq) {
        ModelAndView mv = new ModelAndView();
        CommunityDTO info = eduMarineService.processSelectCommunitySingle(seq);
        mv.addObject("info", info);
        mv.setViewName("/job/community_modify");
        return mv;
    }

    @RequestMapping(value = "/job/community/write/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_update(@RequestBody CommunityDTO communityDTO) {

        ResponseDTO responseDTO = eduMarineService.processUpdateCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/job/community/write/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> job_community_write_delete(@RequestBody CommunityDTO communityDTO) {

        ResponseDTO responseDTO = eduMarineService.processDeleteCommunity(communityDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //***************************************************************************
    // mypage Folder
    //***************************************************************************

    @RequestMapping(value = "/train/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TrainDTO> mypage_resume_save(@RequestBody TrainDTO trainDTO) {

        TrainDTO responseDTO = eduMarineService.processSelectTrainSingle(trainDTO.getSeq());

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApplyInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mypage_eduApplyInfo(HttpServletRequest request, HttpSession session) {
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


        // --- 1. PC 결제 콜백 처리 ---
        if (pc_resultCode != null && !"null".equals(pc_resultCode)) {
            log.info("이니시스 PC 결제 응답 resultCode={}", pc_resultCode);
            if (!"V801".equals(pc_resultCode)) { // 결제 도중 취소 코드 V801 아닐 때

                String userId = null;
                String userIdForPay = null; // 결제 처리에만 쓰는 ID (세션 복구와 분리)

                // 1-1. 세션에서 ID 추출 시도
                if (session.getAttribute("id") != null) {
                    userId = session.getAttribute("id").toString();
                }
                // 1-2. 세션 유실 시 merchantData에서 역추출하여 세션 복구
                //      2026-10-30 보안 조치(C-4): 결제 요청 시 서버가 붙인 서명(발급시각·HMAC)이 맞을 때만 세션을 복구한다.
                //      서명이 없거나 틀리면 결제 처리는 기존대로 진행하되 로그인 세션은 만들지 않는다.
                else {
                    String merchantData = request.getParameter("merchantData");
                    if (merchantData != null && merchantData.contains("||")) {
                        String[] parts = merchantData.split("\\|\\|");
                        if (parts.length >= 2) {
                            userIdForPay = parts[1];
                            if (parts.length >= 4 && SignUtil.verifyMerchant(parts[0], parts[1], parts[2], parts[3])) {
                                userId = parts[1]; // 사용자 ID 추출
                                session.setAttribute("id", userId);
                                session.setAttribute("status", "logon");
                                log.info("PC 결제: 세션 복구 완료 id={}", userId);
                            } else {
                                log.warn("PC 결제: merchantData 서명 불일치 - 세션 복구 생략");
                            }
                        }
                    }
                }

                // 1-3. 결제 데이터 Insert 진행
                if (userId == null && userIdForPay != null) {
                    userId = userIdForPay;
                }
                if (userId != null) {
                    MemberDTO memberInfo = eduMarineService.processSelectMemberSingle(userId);
                    if (memberInfo != null) {
                        InistdpayResponseDTO inistdpayResponseDTO = getInistdpayResponseDTO("PC", memberInfo.getSeq(), request);
                        mv.addObject("payResInfo", inistdpayResponseDTO);
                    } else {
                        log.warn("PC 결제 에러: 해당 회원이 존재하지 않습니다.");
                    }
                } else {
                    log.error("PC 결제 치명적 에러: 세션 및 merchantData 모두 ID 없음");
                }
            }
        }
        // --- 2. 모바일 결제 콜백 처리 ---
        else if (mo_resultCode != null && !"null".equals(mo_resultCode)) {
            log.info("이니시스 모바일 결제 응답 P_STATUS={}", mo_resultCode);
            if (!"01".equals(mo_resultCode)) { // 결제 도중 취소 코드 01 아닐 때

                String userId = null;
                String userIdForPay = null; // 결제 처리에만 쓰는 ID (세션 복구와 분리)

                // 2-1. 세션에서 ID 추출 시도
                if (session.getAttribute("id") != null) {
                    userId = session.getAttribute("id").toString();
                }
                // 2-2. 세션 유실 시 P_NOTI에서 역추출하여 세션 복구
                //      2026-10-30 보안 조치(C-4): P_NOTI 끝에 붙인 발급시각·서명이 맞을 때만 세션을 복구한다.
                else {
                    String pNoti = request.getParameter("P_NOTI");
                    if (pNoti != null) {
                        String[] parts = null;
                        if (pNoti.contains("_UNIFIED_")) { // 통합 폼 방식
                            parts = pNoti.split("_");
                        } else if (pNoti.contains(",")) { // 기존 폼 방식
                            parts = pNoti.split(",");
                        }
                        if (parts != null && parts.length >= 4) {
                            int last = parts.length - 1;
                            boolean signed = parts.length >= 6 && SignUtil.looksLikeSign(parts[last]) && SignUtil.looksLikeTs(parts[last - 1]);
                            String candidate = signed ? parts[last - 2] : parts[last];
                            userIdForPay = candidate;
                            if (signed && SignUtil.verifyMerchant(parts[1], candidate, parts[last - 1], parts[last])) {
                                userId = candidate;
                                session.setAttribute("id", userId);
                                session.setAttribute("status", "logon");
                                log.info("모바일 결제: 세션 복구 완료 id={}", userId);
                            } else {
                                log.warn("모바일 결제: P_NOTI 서명 불일치 - 세션 복구 생략");
                            }
                        }
                    }
                }

                // 2-3. 결제 데이터 Insert 진행
                if (userId == null && userIdForPay != null) {
                    userId = userIdForPay;
                }
                if (userId != null) {
                    MemberDTO memberInfo = eduMarineService.processSelectMemberSingle(userId);
                    if (memberInfo != null) {
                        InistdpayResponseDTO inistdpayResponseDTO = getInistdpayResponseDTO("MOBILE", memberInfo.getSeq(), request);
                        mv.addObject("payResInfo", inistdpayResponseDTO);
                    } else {
                        log.warn("모바일 결제 에러: 해당 회원이 존재하지 않습니다.");
                    }
                } else {
                    log.error("모바일 결제 치명적 에러: 세션 및 P_NOTI 모두 ID 없음");
                }
            }
        }

        mv.setViewName("/mypage/eduApplyInfo");
        return mv;
    }

    @RequestMapping(value = "/mypage/eduPayInfo.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduPayInfo(HttpSession session) {
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

        ResponseDTO responseDTO = eduMarineService.processSaveResume(resumeDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/post.do", method = RequestMethod.GET)
    public ModelAndView mypage_post(HttpSession session) {
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

        ResponseDTO responseDTO = eduMarineService.processUpdateMember(memberDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/modify/withdraw.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> member_modify_withdraw(@RequestBody MemberDTO memberDTO, HttpSession session) {

        ResponseDTO responseDTO = eduMarineService.processUpdateMemberWithdraw(memberDTO);

        if(CommConstants.RESULT_CODE_SUCCESS.equals(responseDTO.getResultCode())){
            session.removeAttribute("status");
            session.invalidate();
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/member/seq/selectSingle.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MemberDTO> member_seq_selectSingle(@RequestBody MemberDTO memberDTO) {

        String seq = memberDTO.getSeq();
        MemberDTO responseInfo = eduMarineService.processSelectMemberSeqSingle(seq);

        // 2026-10-30 보안 조치(H-1): 비밀번호·연락처 등 개인정보는 돌려주지 않고, 화면 안내에 쓰는 마스킹 아이디만 반환
        MemberDTO safe = null;
        if (responseInfo != null) {
            safe = new MemberDTO();
            safe.setSeq(responseInfo.getSeq());
            safe.setId(maskId(responseInfo.getId()));
        }

        return new ResponseEntity<>(safe, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply01_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply01_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        RegularDTO info = eduMarineService.processSelectRegularSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply01_update(@RequestBody RegularDTO regularDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        RegularDTO existing = eduMarineService.processSelectRegularSingle(regularDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateRegular(regularDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply02_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply02_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        BoarderDTO info = eduMarineService.processSelectBoarderSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply02_update(@RequestBody BoarderDTO boarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        BoarderDTO existing = eduMarineService.processSelectBoarderSingle(boarderDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateBoarder(boarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply02/career/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply02_career_delete(@RequestBody CareerDTO careerDTO) {

        ResponseDTO responseDTO = eduMarineService.processDeleteCareer(careerDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply02/license/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply02_license_delete(@RequestBody LicenseDTO licenseDTO) {

        ResponseDTO responseDTO = eduMarineService.processDeleteLicense(licenseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply03_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApply03_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 열람 (미로그인 → 로그인, 타인 신청 → 내 신청 목록)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        FrpDTO info = eduMarineService.processSelectFrpSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) {
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

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
    public ResponseEntity<ResponseDTO> mypage_eduApply03_update(@RequestBody FrpDTO frpDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        FrpDTO existing = eduMarineService.processSelectFrpSingle(frpDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateFrp(frpDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply04/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply04_update(@RequestBody InboarderDTO inboarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        InboarderDTO existing = eduMarineService.processSelectInboarderSingle(inboarderDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateInboarder(inboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply05/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply05_update(@RequestBody OutboarderDTO outboarderDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        OutboarderDTO existing = eduMarineService.processSelectOutboarderSingle(outboarderDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateOutboarder(outboarderDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/mypage/eduApply06/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApply06_update(@RequestBody SailyachtDTO sailyachtDTO, HttpSession session) {

        // 2026-10-30 보안 조치(H-1): 로그인 회원 본인 신청만 수정
        SailyachtDTO existing = eduMarineService.processSelectSailyachtSingle(sailyachtDTO.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }

        ResponseDTO responseDTO = eduMarineService.processUpdateSailyacht(sailyachtDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // -----------------------------------------------------------------------
    // [신규] 마이페이지 - 통합 신청 관리 (상세 / 수정 / 취소)
    // -----------------------------------------------------------------------

    // 1. 상세 페이지 이동
    @RequestMapping(value = "/mypage/eduApplyUnified_detail.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApplyUnified_detail(String auSeq, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 2026-10-30 보안 조치(H-1): 로그인 필수
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        // 신청 정보 조회 (Service에 해당 메서드가 있다고 가정. 없다면 추가 필요)
        // ApplicationUnifiedDTO info = eduMarineService.processSelectApplicationUnifiedSingle(seq);
        // ※ 주의: Service에 processSelectApplicationUnifiedSingle가 없다면 MngService 로직을 참고하여 추가해주세요.
        // 여기서는 임시로 eduMarineMngService와 동일한 로직을 사용한다고 가정합니다.

        // (실제로는 EduMarineService에 UnifiedMapper.selectUnifiedApplicationSingle 호출 메서드를 만들어야 함)
        // 아래는 예시 코드입니다. Service에 메서드를 추가한 후 주석을 해제하세요.
        ApplicationUnifiedDTO info = eduMarineService.processSelectApplicationUnifiedSingle(auSeq);
        if (info != null && !isOwner(session, info.getMemberSeq())) { // 타인 신청은 내 신청 목록으로
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if(info != null){
            mv.addObject("info", info);

            // 교육 정보 (Form Type 확인용)
            TrainDTO trainInfo = eduMarineService.processSelectTrainSingle(info.getTrainSeq());
            mv.addObject("trainInfo", trainInfo);

            // 회원 정보
            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);

            // 결제 정보
            PaymentDTO payReq = new PaymentDTO();
            payReq.setTableSeq(auSeq);
            payReq.setMemberSeq(memberInfo.getSeq());
            payReq.setTrainSeq(trainInfo.getSeq());
            PaymentDTO payInfo = eduMarineService.processSelectPaymentSingle(payReq);
            mv.addObject("payInfo", payInfo);
        }

        mv.setViewName("/mypage/eduApplyUnified_modify");
        return mv;
    }

    // 2. 수정 페이지 이동
    @RequestMapping(value = "/mypage/eduApplyUnified_modify.do", method = RequestMethod.GET)
    public ModelAndView mypage_eduApplyUnified_modify(String seq, String modYn, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 1. 로그인 세션 체크 (필수)
        if (session.getAttribute("id") == null) {
            mv.setViewName("redirect:/member/login.do");
            return mv;
        }

        // 2. 통합 신청 정보 조회
        ApplicationUnifiedDTO info = eduMarineService.processSelectApplicationUnifiedSingle(seq);
        if (info != null && !isOwner(session, info.getMemberSeq())) { // 2026-10-30 보안 조치(H-1): 타인 신청은 내 신청 목록으로
            mv.setViewName("redirect:/mypage/eduApplyInfo.do");
            return mv;
        }

        if (info != null) {
            mv.addObject("info", info);

            // 3. 교육 과정 정보 조회 (Form Type 확인용: BASIC_FORM / PRO_FORM 등)
            TrainDTO trainInfo = eduMarineService.processSelectTrainSingle(info.getTrainSeq());
            mv.addObject("trainInfo", trainInfo);

            // 4. 회원 정보 조회 (기본정보 표시용)
            MemberDTO memberInfo = eduMarineService.processSelectMemberSeqSingle(info.getMemberSeq());
            mv.addObject("memberInfo", memberInfo);

            // 5. 수정 가능 여부 체크 (결제완료/신청완료 상태만 수정 가능하도록)
            // 마감 여부(closingYn)나 교육 시작일 체크 로직이 필요하다면 Service에서 처리 후 modYn 반환
            /*String modYn = "Y";
            if (!"신청완료".equals(info.getApplyStatus()) && !"결제완료".equals(info.getApplyStatus()) && !"입금대기".equals(info.getApplyStatus())) {
                modYn = "N"; // 이미 수강확정되거나 취소된 경우 수정 불가 처리
            }*/
            if(modYn == null || modYn.isEmpty()){
                modYn = "Y";
            }
            mv.addObject("modYn", modYn);

            // 6. (PRO_FORM용) 경력/자격증/파일 리스트 조회
            if ("PRO_FORM".equals(trainInfo.getFormType())) {
                // Repeater 데이터 조회 (Service 내부에 구현 필요)
                // mv.addObject("careerList", eduMarineService.selectCareerList(seq));
                // mv.addObject("licenseList", eduMarineService.selectLicenseList(seq));
                // mv.addObject("bodyPhotoFile", ...);
            }
        }

        mv.setViewName("/mypage/eduApplyUnified_modify");
        return mv;
    }

    // 3. 정보 수정 실행 (POST)
    @RequestMapping(value = "/mypage/eduApplyUnified/update.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApplyUnified_update(@RequestBody ApplicationUnifiedDTO dto, HttpSession session) {
        // 2026-10-30 보안 조치(H-1): 본인 신청만 수정
        ApplicationUnifiedDTO existing = eduMarineService.processSelectApplicationUnifiedSingle(dto.getSeq());
        if (existing == null || !isOwner(session, existing.getMemberSeq())) {
            return forbidden();
        }
        ResponseDTO response = eduMarineService.processUpdateUnifiedApplication(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 4. 신청 취소 실행 (POST)
    @RequestMapping(value = "/mypage/eduApplyUnified/cancel.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> mypage_eduApplyUnified_cancel(@RequestBody ApplicationUnifiedDTO dto, HttpSession session) {

        // 1. 기존 신청 정보 및 교육 과정 정보(수강료) 조회
        ApplicationUnifiedDTO appInfo = eduMarineService.processSelectApplicationUnifiedSingle(dto.getSeq());

        // 2026-10-30 보안 조치(H-1): 본인 신청만 취소
        if (appInfo == null || !isOwner(session, appInfo.getMemberSeq())) {
            return forbidden();
        }
        TrainDTO trainInfo = null;
        if(appInfo != null) {
            trainInfo = eduMarineService.processSelectTrainSingle(appInfo.getTrainSeq());
        }

        // 2. 무료 교육(0원) 여부에 따라 상태값 자동 분기
        boolean isFree = false;
        if (trainInfo != null && trainInfo.getPaySum() != null && trainInfo.getPaySum() == 0) {
            isFree = true;
        }

        if (isFree) {
            dto.setApplyStatus("취소완료"); // 무료는 환불이 필요 없으므로 즉시 취소 완료 처리
        } else {
            dto.setApplyStatus("취소신청"); // 유료는 환불(PG사 취소) 처리를 위해 관리자 승인 대기
        }

        // 3. 상태 업데이트 (취소 사유 등 포함)
        ResponseDTO response = eduMarineService.processUpdateUnifiedApplicationPayStatus(dto);

        // 4. 즉시 취소 완료된 무료 교육의 경우, 수강 정원(인원) 1명 즉시 복구 처리
        if (CommConstants.RESULT_CODE_SUCCESS.equals(response.getResultCode()) && isFree) {
            if (appInfo != null) {
                eduMarineService.processUpdateTrainApplyCntMinus(appInfo.getTrainSeq());
            }
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply/eduApplyUnified/preCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> apply_eduApplyUnified_preCheck(@RequestBody ApplicationUnifiedDTO dto) {

        Integer result = eduMarineService.processSelectUnifiedPreCheck(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //***************************************************************************
    // edumarine Folder
    //***************************************************************************

    @RequestMapping(value = "/edumarine/introduce.do", method = RequestMethod.GET)
    public ModelAndView edumarine_introduce() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/introduce");
        return mv;
    }

    @RequestMapping(value = "/edumarine/overview.do", method = RequestMethod.GET)
    public ModelAndView edumarine_overview() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/overview");
        return mv;
    }

    @RequestMapping(value = "/edumarine/current.do", method = RequestMethod.GET)
    public ModelAndView edumarine_current() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/current");
        return mv;
    }

    @RequestMapping(value = "/edumarine/necessity.do", method = RequestMethod.GET)
    public ModelAndView edumarine_necessity() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/necessity");
        return mv;
    }

    @RequestMapping(value = "/edumarine/sponsorship.do", method = RequestMethod.GET)
    public ModelAndView edumarine_sponsorship() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/sponsorship");
        return mv;
    }

    @RequestMapping(value = "/edumarine/way.do", method = RequestMethod.GET)
    public ModelAndView edumarine_way() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/edumarine/way");
        return mv;
    }

    //***************************************************************************
    // guide Folder
    //***************************************************************************

    @RequestMapping(value = "/guide/guide01.do", method = RequestMethod.GET)
    public ModelAndView guide_guide01() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide01");
        return mv;
    }

    @RequestMapping(value = "/guide/guide02.do", method = RequestMethod.GET)
    public ModelAndView guide_guide02() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide02");
        return mv;
    }

    @RequestMapping(value = "/guide/guide03.do", method = RequestMethod.GET)
    public ModelAndView guide_guide03() {
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
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/guide/guide04");
        return mv;
    }

    @RequestMapping(value = "/guide/guide05.do", method = RequestMethod.GET)
    public ModelAndView guide_guide05() {
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

        /* 방문자 수 카운트 */
        eduMarineService.processStatisticsAccessor();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/index");
        return mv;
    }

    @RequestMapping(value = "/eng/marine/intro.do", method = RequestMethod.GET)
    public ModelAndView eng_marine_intro() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/marine/intro");
        return mv;
    }

    @RequestMapping(value = "/eng/current/current.do", method = RequestMethod.GET)
    public ModelAndView eng_current_current() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/current/current");
        return mv;
    }

    @RequestMapping(value = "/eng/edu/curriculum.do", method = RequestMethod.GET)
    public ModelAndView eng_edu_curriculum() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/edu/curriculum");
        return mv;
    }

    @RequestMapping(value = "/eng/edu/equipment.do", method = RequestMethod.GET)
    public ModelAndView eng_edu_equipment() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/edu/equipment");
        return mv;
    }

    @RequestMapping(value = "/eng/contact/contact.do", method = RequestMethod.GET)
    public ModelAndView eng_contact_contact() {
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
            String tableSeq = "";
            String systemType = "L";


            try {

                Map<String, String> paramMap = new Hashtable<String, String>();

                //#############################
                // 인증결과 파라미터 일괄 수신
                //#############################
                request.setCharacterEncoding("UTF-8");

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

                    log.info("이니시스 PC 인증 성공, 승인 요청 시작");

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

                    //#####################
                    // 2.signature 생성
                    //#####################
                    Map<String, String> signParam = new HashMap<String, String>();

                    signParam.put("authToken", authToken);        // 필수
                    signParam.put("timestamp", timestamp);        // 필수

                    // signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
                    String signature = SignatureUtil.makeSignature(signParam);

                    signParam.put("signKey", externalApi.getInicisSignKey());        // 필수 (2026-10-30: 설정 파일 inicis.sign-key)

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

                        //String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace("\n", "").replace("}", "").replace("{", "");
                        //resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱

                        // 변경할 코드 (안전한 JSON 파싱)
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObj = (JSONObject) parser.parse(authResultString);

                        for (Object key : jsonObj.keySet()) {
                            resultMap.put(((String) key).trim(), String.valueOf(jsonObj.get(key)).trim());
                        }

                        String reqMerchantData = request.getParameter("merchantData");
                        if (reqMerchantData != null) {
                            resultMap.put("merchantData", reqMerchantData);
                        }

                        // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패
                        //throw new Exception("강제 망취소 요청 Exception ");

                    } catch (Exception ex) {

                        //####################################
                        // 실패시 처리(***가맹점 개발수정***)
                        //####################################

                        //---- db 저장 실패시 등 예외처리----//
                        log.error("[getInistdpayResponseDTO] error", ex);

                        //#####################
                        // 망취소 API
                        //#####################
                        String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);    // 망취소 요청 API url(고정, 임의 세팅 금지)


                        // 망취소 결과 확인
                        log.warn("이니시스 망취소 API 결과: {}", netcancelResultString);
                    }

                } else {

                    resultMap.put("resultCode", paramMap.get("resultCode"));
                    resultMap.put("resultMsg", paramMap.get("resultMsg"));
                }

            } catch (Exception e) {
                log.error("[getInistdpayResponseDTO] error", e);
            }

            inistdpayResponseDTO = (InistdpayResponseDTO) convertMapToObject(resultMap, inistdpayResponseDTO);
            log.info("이니시스 PC 승인 결과 resultCode={} resultMsg={} tid={}", inistdpayResponseDTO.getResultCode(), inistdpayResponseDTO.getResultMsg(), inistdpayResponseDTO.getTid());

            if ("0000".equals(inistdpayResponseDTO.getResultCode())) {
                // 결제내역 테이블 Insert process

                // ★ 1. tableSeq 추출 (Null 방어 및 세션 복구용 데이터 분리 추가)
                String merchantData = inistdpayResponseDTO.getMerchantData();
                if (merchantData != null && !merchantData.isEmpty()) {

                    // 세션 복구를 위해 '||' 로 이어붙인 아이디가 있다면 앞부분(tableSeq)만 추출
                    if (merchantData.contains("||")) {
                        merchantData = merchantData.split("\\|\\|")[0];
                    }

                    if (merchantData.contains(",")) {
                        String[] md = merchantData.split(",");
                        tableSeq = md[0];
                        if (md.length > 1) {
                            systemType = md[1];
                        }
                    } else {
                        tableSeq = merchantData;
                    }
                }

                // 완벽한 분기를 위해 tableSeq가 'AU'로 시작하면 강제로 UNIFIED 시스템 타입으로 지정
                if (tableSeq != null && tableSeq.startsWith("AU")) {
                    systemType = "UNIFIED";
                }

                //  2. goodName에서 trainSeq, trainName 추출 (IndexOutOfBoundsException 완벽 방어)
                String goodName = inistdpayResponseDTO.getGoodName(); // 예: [T0000148]세일요트 기초정비실습 과정
                String trainSeq = "";
                String trainName = "";

                if (goodName != null && goodName.contains("[") && goodName.contains("]")) {
                    trainSeq = goodName.substring(goodName.indexOf("[") + 1, goodName.indexOf("]"));
                    trainName = goodName.substring(goodName.indexOf("]") + 1).trim();
                } else if (goodName != null) {
                    trainName = goodName; // 괄호 형식이 아닐 경우 원본 그대로 저장
                }

                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setMemberSeq(memberSeq);
                paymentDTO.setMemberName(inistdpayResponseDTO.getBuyerName());
                paymentDTO.setMemberPhone(inistdpayResponseDTO.getBuyerTel());
                paymentDTO.setTableSeq(tableSeq);
                paymentDTO.setTrainSeq(trainSeq);
                paymentDTO.setTrainName(trainName);
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

                        // --- 시스템 타입 분기 (PC) ▼▼▼ ---
                        if ("UNIFIED".equals(systemType)) {

                            // [신규 UNIFIED 로직]
                            ApplicationUnifiedDTO unifiedDTO = new ApplicationUnifiedDTO();
                            unifiedDTO.setSeq(tableSeq);
                            unifiedDTO.setApplyStatus(paymentDTO.getPayStatus());

                            ResponseDTO result = eduMarineService.processUpdateUnifiedApplicationPayStatus(unifiedDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else {
                            // 상시신청
                            // 해상엔진 테크니션 (선내기/선외기)
                            // FRP 레저보트 선체 정비 테크니션
                            // 선외기 기초정비실습 과정
                            // 선내기 기초정비실습 과정
                            // 세일요트 기초정비실습 과정
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
                                regularDTO.setSeq(tableSeq);
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
                                boarderDTO.setSeq(tableSeq);
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
                                frpDTO.setSeq(tableSeq);
                                frpDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateFrpPayStatus(frpDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            } else if (paymentDTO.getTrainName().contains("기초정비실습")) {

                                if (paymentDTO.getTrainName().contains("선외기")) {

                                    // outboarder table
                                    OutboarderDTO outboarderDTO = new OutboarderDTO();
                                    outboarderDTO.setSeq(tableSeq);
                                    outboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else if (paymentDTO.getTrainName().contains("선내기")) {

                                    // inboarder table
                                    InboarderDTO inboarderDTO = new InboarderDTO();
                                    inboarderDTO.setSeq(tableSeq);
                                    inboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else if (paymentDTO.getTrainName().contains("세일요트")) {

                                    // sailyacht table
                                    SailyachtDTO sailyachtDTO = new SailyachtDTO();
                                    sailyachtDTO.setSeq(tableSeq);
                                    sailyachtDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                }

                            } else if (paymentDTO.getTrainName().contains("고마력 선외기 정비 중급 테크니션")) {

                                if (paymentDTO.getTrainName().contains("(특별반)")) {

                                    // highspecial table
                                    HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                    highSpecialDTO.setSeq(tableSeq);
                                    highSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else {

                                    // highhorsepower table
                                    HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                    highHorsePowerDTO.setSeq(tableSeq);
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
                                highSelfDTO.setSeq(tableSeq);
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
                                    sternSpecialDTO.setSeq(tableSeq);
                                    sternSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else {

                                    // Sterndrive table
                                    SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                    sterndriveDTO.setSeq(tableSeq);
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
                                basicDTO.setSeq(tableSeq);
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
                                emergencyDTO.setSeq(tableSeq);
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
                                generatorDTO.setSeq(tableSeq);
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
                                competencyDTO.setSeq(tableSeq);
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

                            for (String value : values) {

                                // 승인결과를 파싱값 잘라 hashmap에 저장
                                int i = value.indexOf("=");
                                String key1 = value.substring(0, i);
                                String value1 = value.substring(i + 1);
                                map.put(key1, value1);

                            }

                            br.close();
                        }

                    } catch (Exception ex) {
                        log.error("[getInistdpayResponseDTO] error", ex);
                    }

                } else {

                    map.put("P_STATUS", P_STATUS);
                    map.put("P_RMESG1", P_RMESG1);

                }
            }catch (Exception e){
                log.error("[getInistdpayResponseDTO] error", e);
            }

            inistdpayResponseDTO = (InistdpayResponseDTO) convertMapToObject(map, inistdpayResponseDTO);
            log.info("이니시스 모바일 승인 결과 P_STATUS={} P_RMESG1={} P_TID={}", inistdpayResponseDTO.getP_STATUS(), inistdpayResponseDTO.getP_RMESG1(), inistdpayResponseDTO.getP_TID());

            if ("00".equals(inistdpayResponseDTO.getP_STATUS())) {
                // 결제내역 테이블 Insert process

                // --- [신규 추가] P_NOTI 파싱 (Mobile) ---
                // P_NOTI 형식: trainSeq|tableSeq|systemType|goodName
                String pNoti = inistdpayResponseDTO.getP_NOTI();
                String trainSeq = "";
                String tableSeq = "";
                String systemType = "L";
                String trainName = "";
                String goodName = "";

                if (pNoti != null) {
                    String[] P_NOTI_ARR = pNoti.split("_");
                    if (pNoti.contains("_")) {
                        if(P_NOTI_ARR.length >= 4) {
                            trainSeq = P_NOTI_ARR[0];
                            tableSeq = P_NOTI_ARR[1];
                            systemType = P_NOTI_ARR[2];
                            trainName = P_NOTI_ARR[3];
                            goodName = "[" + trainSeq + "]" + trainName;
                        } else if(P_NOTI_ARR.length == 3) {
                            trainSeq = P_NOTI_ARR[0];
                            tableSeq = P_NOTI_ARR[1];
                            systemType = P_NOTI_ARR[2];
                            trainName = "";
                            goodName = "[" + trainSeq + "]";
                        }
                    } else {
                        // Legacy Logic Fallback (Original format with comma)
                        String[] legacyArr = pNoti.split(",");
                        if(legacyArr.length >= 3) {
                            trainSeq = legacyArr[0];
                            tableSeq = legacyArr[1];
                            trainName = legacyArr[2];
                            goodName = "[" + trainSeq + "]" + trainName;
                        }
                    }
                }

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
                //paymentDTO.setVactInputName(inistdpayResponseDTO.getVACT_InputName());
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

                        // --- ▼▼▼ [신규 추가] 시스템 타입 분기 (MOBILE) ▼▼▼ ---
                        if ("UNIFIED".equals(systemType)) {

                            // [신규 UNIFIED 로직]
                            ApplicationUnifiedDTO unifiedDTO = new ApplicationUnifiedDTO();
                            unifiedDTO.setSeq(tableSeq);
                            unifiedDTO.setApplyStatus(paymentDTO.getPayStatus());

                            ResponseDTO result = eduMarineService.processUpdateUnifiedApplicationPayStatus(unifiedDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                    eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                }
                            }

                        } else {
                            // 상시신청
                            // 해상엔진 테크니션 (선내기/선외기)
                            // FRP 레저보트 선체 정비 테크니션
                            // 선외기 기초정비실습 과정
                            // 선내기 기초정비실습 과정
                            // 세일요트 기초정비실습 과정
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
                                regularDTO.setSeq(tableSeq);
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
                                boarderDTO.setSeq(tableSeq);
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
                                frpDTO.setSeq(tableSeq);
                                frpDTO.setApplyStatus(paymentDTO.getPayStatus());
                                ResponseDTO result = eduMarineService.processUpdateFrpPayStatus(frpDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    }
                                }

                            } else if (paymentDTO.getTrainName().contains("기초정비실습")) {

                                if (paymentDTO.getTrainName().contains("선외기")) {

                                    // outboarder table
                                    OutboarderDTO outboarderDTO = new OutboarderDTO();
                                    outboarderDTO.setSeq(tableSeq);
                                    outboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else if (paymentDTO.getTrainName().contains("선내기")) {

                                    // inboarder table
                                    InboarderDTO inboarderDTO = new InboarderDTO();
                                    inboarderDTO.setSeq(tableSeq);
                                    inboarderDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else if (paymentDTO.getTrainName().contains("세일요트")) {

                                    // sailyacht table
                                    SailyachtDTO sailyachtDTO = new SailyachtDTO();
                                    sailyachtDTO.setSeq(tableSeq);
                                    sailyachtDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                }

                            } else if (paymentDTO.getTrainName().contains("고마력 선외기 정비 중급 테크니션")) {

                                if (paymentDTO.getTrainName().contains("(특별반)")) {

                                    // highspecial table
                                    HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                    highSpecialDTO.setSeq(tableSeq);
                                    highSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else {

                                    // highhorsepower table
                                    HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                    highHorsePowerDTO.setSeq(tableSeq);
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
                                highSelfDTO.setSeq(tableSeq);
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
                                    sternSpecialDTO.setSeq(tableSeq);
                                    sternSpecialDTO.setApplyStatus(paymentDTO.getPayStatus());
                                    ResponseDTO result = eduMarineService.processUpdateSternSpecialPayStatus(sternSpecialDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        if ("결제완료".equals(paymentDTO.getPayStatus())) {
                                            Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                        }
                                    }

                                } else {

                                    // Sterndrive table
                                    SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                    sterndriveDTO.setSeq(tableSeq);
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
                                basicDTO.setSeq(tableSeq);
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
                                emergencyDTO.setSeq(tableSeq);
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
                                generatorDTO.setSeq(tableSeq);
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
                                competencyDTO.setSeq(tableSeq);
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

        }

        return inistdpayResponseDTO;
    }

    @RequestMapping(value = "/apply/payment.do", method = RequestMethod.POST)
    public ModelAndView apply_payment(InistdpayRequestDTO inistdpayRequestDTO, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        TrainDTO trainDTO = eduMarineService.processSelectTrainSingle(inistdpayRequestDTO.getTrainSeq());

        // 2026-10-30 보안 조치(2단계): 상점 ID·signKey 는 설정 파일(inicis.*)에서 읽는다.
        String mid = externalApi.getInicisMid();
        String signKey = externalApi.getInicisSignKey();

        String mKey = SignatureUtil.hash(signKey, "SHA-256");

        String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
        String oid = inistdpayRequestDTO.getOid();
        String orderNumber			= "";	// 가맹점 주문번호(가맹점에서 직접 설정)
        if (oid != null && !oid.isEmpty()) {
            // 화면에서 보낸 값: timestamp_seq_systemType
            // 최종 oid: mid_timestamp_seq_systemType
            orderNumber = SignatureUtil.getTimestamp() + "_" + oid;
        } else {
            // 없으면 기존 방식대로 생성
            orderNumber = SignatureUtil.getTimestamp(); // 기본값
        }

        String price				= String.valueOf(trainDTO.getPaySum());								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)

        String use_chkfake			= "Y";									// verification 검증 여부 ('Y' , 'N')

        Map<String, String> signParam = new HashMap<String, String>();

        signParam.put("oid", orderNumber);
        signParam.put("price", price);
        signParam.put("timestamp", timestamp);

        String signature = SignatureUtil.makeSignature(signParam);			// signature 대상: oid, price, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        signParam.put("signKey", signKey);

        String verification = SignatureUtil.makeSignature(signParam);		// verification 대상 : oid, price, signkey, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        // ---------------------------------------------------------
        // 접속한 도메인을 동적으로 가져오도록 변경
        // ---------------------------------------------------------
        String serverName = request.getServerName(); // 사용자가 접속한 도메인 (www.edumarine.org 등)
        String siteDomain = "";

        if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
            // 로컬 개발 환경인 경우
            siteDomain = "http://" + serverName + ":" + request.getServerPort();
        } else {
            // 실제 운영 환경인 경우 (무조건 https 프로토콜 적용)
            siteDomain = "https://" + serverName;
        }

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
        inistdpayRequestDTO.setGoodname("[" + trainDTO.getSeq() + "] " + gbn);
        inistdpayRequestDTO.setSiteDomain(siteDomain);
        // 2026-10-30 보안 조치(C-4): 결제 복귀 세션 복구용 서명 (merchantData / P_NOTI 끝에 붙임)
        String mdTs = SignUtil.now();
        Object sessionLoginId = request.getSession().getAttribute("id");
        mv.addObject("mdTs", mdTs);
        mv.addObject("mdSign", SignUtil.signMerchant(inistdpayRequestDTO.getTableSeq(), sessionLoginId == null ? "" : sessionLoginId.toString(), mdTs));

        mv.addObject("payInfo", inistdpayRequestDTO);

        log.info("결제 요청(PC) trainSeq={} tableSeq={} oid={}", inistdpayRequestDTO.getTrainSeq(), inistdpayRequestDTO.getTableSeq(), inistdpayRequestDTO.getOid());
        mv.setViewName("/apply/payment");
        return mv;
    }

    @RequestMapping(value = "/apply/mobile/payment.do", method = RequestMethod.POST)
    public ModelAndView apply_mobile_payment(InistdpayRequestDTO inistdpayRequestDTO, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        TrainDTO trainDTO = eduMarineService.processSelectTrainSingle(inistdpayRequestDTO.getTrainSeq());

        // 2026-10-30 보안 조치(2단계): 상점 ID·signKey 는 설정 파일(inicis.*)에서 읽는다.
        String mid = externalApi.getInicisMid();
        String signKey = externalApi.getInicisSignKey();

        String mKey = SignatureUtil.hash(signKey, "SHA-256");

        String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
        String oid = inistdpayRequestDTO.getOid();
        String orderNumber			= "";	// 가맹점 주문번호(가맹점에서 직접 설정)
        if (oid != null && !oid.isEmpty()) {
            // 화면에서 보낸 값: timestamp_seq_systemType
            // 최종 oid: mid_timestamp_seq_systemType
            orderNumber = SignatureUtil.getTimestamp() + "_" + oid;
        } else {
            // 없으면 기존 방식대로 생성
            orderNumber = SignatureUtil.getTimestamp(); // 기본값
        }
        String price				= String.valueOf(trainDTO.getPaySum());								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)

        String use_chkfake			= "Y";									// verification 검증 여부 ('Y' , 'N')

        Map<String, String> signParam = new HashMap<String, String>();

        signParam.put("oid", orderNumber);
        signParam.put("price", price);
        signParam.put("timestamp", timestamp);

        String signature = SignatureUtil.makeSignature(signParam);			// signature 대상: oid, price, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        signParam.put("signKey", signKey);

        String verification = SignatureUtil.makeSignature(signParam);		// verification 대상 : oid, price, signkey, timestamp (알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)

        // ---------------------------------------------------------
        // 동적 도메인 할당
        // ---------------------------------------------------------
        String serverName = request.getServerName(); // 사용자가 접속한 도메인 (www.edumarine.org 등)
        String siteDomain = "";

        if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
            // 로컬 개발 환경인 경우
            siteDomain = "http://" + serverName + ":" + request.getServerPort();
        } else {
            // 실제 운영 환경인 경우 (무조건 https 프로토콜 적용)
            siteDomain = "https://" + serverName;
        }

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
        inistdpayRequestDTO.setGoodname("[" + trainDTO.getSeq() + "] " + gbn);
        inistdpayRequestDTO.setSiteDomain(siteDomain);
        // 2026-10-30 보안 조치(C-4): 결제 복귀 세션 복구용 서명 (merchantData / P_NOTI 끝에 붙임)
        String mdTs = SignUtil.now();
        Object sessionLoginId = request.getSession().getAttribute("id");
        mv.addObject("mdTs", mdTs);
        mv.addObject("mdSign", SignUtil.signMerchant(inistdpayRequestDTO.getTableSeq(), sessionLoginId == null ? "" : sessionLoginId.toString(), mdTs));

        mv.addObject("payInfo", inistdpayRequestDTO);

        log.info("결제 요청(모바일) trainSeq={} tableSeq={} oid={}", inistdpayRequestDTO.getTrainSeq(), inistdpayRequestDTO.getTableSeq(), inistdpayRequestDTO.getOid());
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
                        log.error("[convertMapToObject] 처리 중 예외", e);
                    }
                }
            }
        }
        return obj;
    }

    @RequestMapping(value = "/pc/payment/vbank/vacct/noti.do", method = RequestMethod.POST)
    @ResponseBody
    public String payment_vbank_vacct_noti(HttpServletRequest request) {
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


                    Boolean successFlag = true;

                    PaymentDTO paymentDTO = new PaymentDTO();
                    paymentDTO.setMoid(no_oid);
                    paymentDTO.setVactNum(no_vacct);
                    paymentDTO.setTotPrice(amt_input);
                    paymentDTO.setPayStatus("결제완료");
                    Integer updResult = eduMarineService.processUpdatePaymentVbankNoti(paymentDTO);

                    if(updResult > 0) {
                        PaymentDTO payInfo = eduMarineService.processSelectPaymentVbankInfo(paymentDTO);

                        // --- merchantData 파싱 ▼▼▼ ---
                        // payInfo.tableSeq에는 "AU000001,UNIFIED" 또는 "F000001"이 들어있음
                        String tableSeq = payInfo.getTableSeq();
                        String systemType = "LEGACY"; // 기본값

                        if (tableSeq != null && tableSeq.startsWith("AU")) {
                            systemType = "UNIFIED";
                        }
                        // --- merchantData 파싱 ▲▲▲ ---

                        String trainSeq = payInfo.getTrainSeq();
                        String trainName = payInfo.getTrainName();
                        String applyStatus = "결제완료";

                        // --- 시스템 타입 분기 ▼▼▼ ---
                        if ("UNIFIED".equals(systemType)) {

                            // [신규 로직]
                            ApplicationUnifiedDTO unifiedDTO = new ApplicationUnifiedDTO();
                            unifiedDTO.setSeq(tableSeq);
                            unifiedDTO.setApplyStatus(applyStatus);

                            ResponseDTO result = eduMarineService.processUpdateUnifiedApplicationPayStatus(unifiedDTO);

                            if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                            } else {
                                successFlag = false;
                            }

                        } else {

                            if (trainName.contains("상시")) {

                                // regular table
                                RegularDTO regularDTO = new RegularDTO();
                                regularDTO.setSeq(tableSeq);
                                regularDTO.setApplyStatus(applyStatus);
                                ResponseDTO result = eduMarineService.processUpdateRegularPayStatus(regularDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                } else {
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
                                } else {
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
                                } else {
                                    successFlag = false;
                                }

                            } else  if (trainName.contains("기초정비실습")) {

                                if (trainName.contains("선외기")) {

                                    // outboarder table
                                    OutboarderDTO outboarderDTO = new OutboarderDTO();
                                    outboarderDTO.setSeq(tableSeq);
                                    outboarderDTO.setApplyStatus(applyStatus);
                                    ResponseDTO result = eduMarineService.processUpdateOutboarderPayStatus(outboarderDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    } else {
                                        successFlag = false;
                                    }

                                } else if (trainName.contains("선내기")) {

                                    // inboarder table
                                    InboarderDTO inboarderDTO = new InboarderDTO();
                                    inboarderDTO.setSeq(tableSeq);
                                    inboarderDTO.setApplyStatus(applyStatus);
                                    ResponseDTO result = eduMarineService.processUpdateInboarderPayStatus(inboarderDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    } else {
                                        successFlag = false;
                                    }

                                } else if (trainName.contains("세일요트")) {

                                    // sailyacht table
                                    SailyachtDTO sailyachtDTO = new SailyachtDTO();
                                    sailyachtDTO.setSeq(tableSeq);
                                    sailyachtDTO.setApplyStatus(applyStatus);
                                    ResponseDTO result = eduMarineService.processUpdateSailyachtPayStatus(sailyachtDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    } else {
                                        successFlag = false;
                                    }

                                }

                            }else if (trainName.contains("고마력 선외기 정비 중급 테크니션")) {

                                if (trainName.contains("(특별반)")) {

                                    // highspecial table
                                    HighSpecialDTO highSpecialDTO = new HighSpecialDTO();
                                    highSpecialDTO.setSeq(tableSeq);
                                    highSpecialDTO.setApplyStatus(applyStatus);
                                    ResponseDTO result = eduMarineService.processUpdateHighSpecialPayStatus(highSpecialDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    } else {
                                        successFlag = false;
                                    }

                                } else {

                                    // highhorsepower table
                                    HighHorsePowerDTO highHorsePowerDTO = new HighHorsePowerDTO();
                                    highHorsePowerDTO.setSeq(tableSeq);
                                    highHorsePowerDTO.setApplyStatus(applyStatus);
                                    ResponseDTO result = eduMarineService.processUpdateHighHorsePowerPayStatus(highHorsePowerDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    } else {
                                        successFlag = false;
                                    }

                                }

                            } else if (trainName.contains("자가정비 심화과정 (고마력 선외기)")) {

                                // highself table
                                HighSelfDTO highSelfDTO = new HighSelfDTO();
                                highSelfDTO.setSeq(tableSeq);
                                highSelfDTO.setApplyStatus(applyStatus);
                                ResponseDTO result = eduMarineService.processUpdateHighSelfPayStatus(highSelfDTO);

                                if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                    Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                } else {
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
                                    } else {
                                        successFlag = false;
                                    }

                                } else {

                                    // Sterndrive table
                                    SterndriveDTO sterndriveDTO = new SterndriveDTO();
                                    sterndriveDTO.setSeq(tableSeq);
                                    sterndriveDTO.setApplyStatus(applyStatus);
                                    ResponseDTO result = eduMarineService.processUpdateSterndrivePayStatus(sterndriveDTO);

                                    if (CommConstants.RESULT_CODE_SUCCESS.equals(result.getResultCode())) {
                                        Integer updTrain = eduMarineService.processUpdateTrainApplyCnt(trainSeq);
                                    } else {
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
                    }

                    //***********************************************************************************
                    //
                    //	위에서 상점 데이터베이스에 등록 성공유무에 따라서 성공시에는 "OK"를 이니시스로
                    //	리턴하셔야합니다. 아래 조건에 데이터베이스 성공시 받는 FLAG 변수를 넣으세요
                    //	(주의) OK를 리턴하지 않으시면 이니시스 지불 서버는 "OK"를 수신할때까지 계속 재전송을 시도합니다
                    //	기타 다른 형태의 out.println(response.write)는 하지 않으시기 바랍니다
                    if (successFlag)
                    {
                        log.info("가상계좌 입금통보 처리 완료, OK 응답"); // 이니시스에는 아래 response 변수로 OK 를 돌려준다
                        response = "OK";
                    }

                } catch (Exception ex) {
                    log.error("[payment_vbank_vacct_noti] error", ex);
                }

            }
        }catch (Exception e){
            log.error("[payment_vbank_vacct_noti] error", e);
        }

        return response;
    }

    @RequestMapping(value = "/payment/close.do", method = RequestMethod.GET)
    public ModelAndView payment_close(String trainSeq , String trainUrl) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/payment/close");
        return mv;
    }

    @RequestMapping(value = "/file/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDTO> file_delete(@RequestBody FileDTO fileDTO) {

        ResponseDTO responseDTO = eduMarineService.processDeleteFile(fileDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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