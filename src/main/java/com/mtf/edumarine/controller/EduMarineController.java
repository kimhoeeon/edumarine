package com.mtf.edumarine.controller;

import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.ResponseDTO;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

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
        mv.setViewName("main");
        return mv;
    }

    //***************************************************************************
    // community Folder
    //***************************************************************************

    @RequestMapping(value = "/community/contact.do", method = RequestMethod.GET)
    public ModelAndView community_contact() {
        System.out.println("EduMarineController > community_contact");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/community/contact");
        return mv;
    }

    @RequestMapping(value = "/community/event.do", method = RequestMethod.GET)
    public ModelAndView community_event() {
        System.out.println("EduMarineController > community_event");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/community/event");
        return mv;
    }

    @RequestMapping(value = "/community/gallery.do", method = RequestMethod.GET)
    public ModelAndView community_gallery() {
        System.out.println("EduMarineController > community_gallery");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/community/gallery");
        return mv;
    }

    @RequestMapping(value = "/community/notice.do", method = RequestMethod.GET)
    public ModelAndView community_notice() {
        System.out.println("EduMarineController > community_notice");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/community/notice");
        return mv;
    }

    @RequestMapping(value = "/community/notice/detail.do", method = RequestMethod.GET)
    public ModelAndView community_notice_detail() {
        System.out.println("EduMarineController > community_notice_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("community/notice/detail");
        return mv;
    }

    @RequestMapping(value = "/community/news.do", method = RequestMethod.GET)
    public ModelAndView community_news() {
        System.out.println("EduMarineController > community_news");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/community/news");
        return mv;
    }

    @RequestMapping(value = "/community/news/detail.do", method = RequestMethod.GET)
    public ModelAndView community_news_detail() {
        System.out.println("EduMarineController > community_news_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/community/news/detail");
        return mv;
    }

    //***************************************************************************
    // introduce Folder
    //***************************************************************************

    @RequestMapping(value = "/introduce/business.do", method = RequestMethod.GET)
    public ModelAndView introduce_business() {
        System.out.println("EduMarineController > introduce_business");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/introduce/business");
        return mv;
    }

    @RequestMapping(value = "/introduce/vision.do", method = RequestMethod.GET)
    public ModelAndView introduce_vision() {
        System.out.println("EduMarineController > introduce_vision");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/introduce/vision");
        return mv;
    }

    @RequestMapping(value = "/introduce/history.do", method = RequestMethod.GET)
    public ModelAndView introduce_history() {
        System.out.println("EduMarineController > introduce_history");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/introduce/history");
        return mv;
    }

    @RequestMapping(value = "/introduce/way.do", method = RequestMethod.GET)
    public ModelAndView introduce_way() {
        System.out.println("EduMarineController > introduce_way");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/introduce/way");
        return mv;
    }

    @RequestMapping(value = "/introduce/greeting.do", method = RequestMethod.GET)
    public ModelAndView introduce_greeting() {
        System.out.println("EduMarineController > introduce_greeting");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/introduce/greeting");
        return mv;
    }

    //***************************************************************************
    // member Folder
    //***************************************************************************

    @RequestMapping(value = "/member/ascdirectors.do", method = RequestMethod.GET)
    public ModelAndView member_ascdirectors() {
        System.out.println("EduMarineController > member_ascdirectors");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/ascdirectors");
        return mv;
    }

    @RequestMapping(value = "/member/detailA.do", method = RequestMethod.GET)
    public ModelAndView member_detailA() {
        System.out.println("EduMarineController > member_detailA");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/detailA");
        return mv;
    }

    @RequestMapping(value = "/member/detailB.do", method = RequestMethod.GET)
    public ModelAndView member_detailB() {
        System.out.println("EduMarineController > member_detailB");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/detailB");
        return mv;
    }

    @RequestMapping(value = "/member/detailC.do", method = RequestMethod.GET)
    public ModelAndView member_detailC() {
        System.out.println("EduMarineController > member_detailC");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/detailC");
        return mv;
    }

    @RequestMapping(value = "/member/adviser.do", method = RequestMethod.GET)
    public ModelAndView member_adviser() {
        System.out.println("EduMarineController > member_adviser");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/adviser");
        return mv;
    }

    @RequestMapping(value = "/member/consultation.do", method = RequestMethod.GET)
    public ModelAndView member_consultation() {
        System.out.println("EduMarineController > member_consultation");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/consultation");
        return mv;
    }

    @RequestMapping(value = "/member/regular.do", method = RequestMethod.GET)
    public ModelAndView member_regular() {
        System.out.println("EduMarineController > member_regular");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/regular");
        return mv;
    }

    @RequestMapping(value = "/member/ascmembers.do", method = RequestMethod.GET)
    public ModelAndView member_ascmembers() {
        System.out.println("EduMarineController > member_ascmembers");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/ascmembers");
        return mv;
    }

    @RequestMapping(value = "/member/partnership.do", method = RequestMethod.GET)
    public ModelAndView member_partnership() {
        System.out.println("EduMarineController > member_partnership");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/partnership");
        return mv;
    }

    //***************************************************************************
    // service Folder
    //***************************************************************************

    @RequestMapping(value = "/service/benefits.do", method = RequestMethod.GET)
    public ModelAndView service_benefits() {
        System.out.println("EduMarineController > service_benefits");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/service/benefits");
        return mv;
    }

    @RequestMapping(value = "/service/faq.do", method = RequestMethod.GET)
    public ModelAndView service_faq() {
        System.out.println("EduMarineController > service_faq");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/service/faq");
        return mv;
    }

    @RequestMapping(value = "/service/guide.do", method = RequestMethod.GET)
    public ModelAndView service_guide() {
        System.out.println("EduMarineController > service_guide");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/service/guide");
        return mv;
    }

    //***************************************************************************
    // support Folder
    //***************************************************************************

    @RequestMapping(value = "/support/bidanm.do", method = RequestMethod.GET)
    public ModelAndView support_bidanm() {
        System.out.println("EduMarineController > support_bidanm");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/support/bidanm");
        return mv;
    }

    @RequestMapping(value = "/support/bidanm/detail.do", method = RequestMethod.GET)
    public ModelAndView support_bidanm_detail() {
        System.out.println("EduMarineController > support_bidanm_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/support/bidanm/detail");
        return mv;
    }

    @RequestMapping(value = "/support/bizanm.do", method = RequestMethod.GET)
    public ModelAndView support_bizanm() {
        System.out.println("EduMarineController > support_bizanm");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/support/bizanm");
        return mv;
    }

    @RequestMapping(value = "/support/bizanm/detail.do", method = RequestMethod.GET)
    public ModelAndView support_bizanm_detail() {
        System.out.println("EduMarineController > support_bizanm_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/support/bizanm/detail");
        return mv;
    }

    @RequestMapping(value = "/support/issue.do", method = RequestMethod.GET)
    public ModelAndView support_issue() {
        System.out.println("EduMarineController > support_issue");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/support/issue");
        return mv;
    }

    @RequestMapping(value = "/support/issue/detail.do", method = RequestMethod.GET)
    public ModelAndView support_issue_detail() {
        System.out.println("EduMarineController > support_issue_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/support/issue/detail");
        return mv;
    }

    //***************************************************************************
    // eng Folder
    //***************************************************************************

    @RequestMapping(value = "/eng/index.do", method = RequestMethod.GET)
    public ModelAndView eng_index() {
        System.out.println("EduMarineController > eng_index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/index");
        return mv;
    }

    @RequestMapping(value = "/eng/main.do", method = RequestMethod.GET)
    public ModelAndView eng_main() {
        System.out.println("EduMarineController > eng_main");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/main");
        return mv;
    }

    //***************************************************************************
    // community Folder
    //***************************************************************************

    @RequestMapping(value = "/eng/community/contact.do", method = RequestMethod.GET)
    public ModelAndView eng_community_contact() {
        System.out.println("EduMarineController > eng_community_contact");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/contact");
        return mv;
    }

    @RequestMapping(value = "/eng/community/event.do", method = RequestMethod.GET)
    public ModelAndView eng_community_event() {
        System.out.println("EduMarineController > eng_community_event");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/event");
        return mv;
    }

    @RequestMapping(value = "/eng/community/gallery.do", method = RequestMethod.GET)
    public ModelAndView eng_community_gallery() {
        System.out.println("EduMarineController > eng_community_gallery");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/gallery");
        return mv;
    }

    @RequestMapping(value = "/eng/community/notice.do", method = RequestMethod.GET)
    public ModelAndView eng_community_notice() {
        System.out.println("EduMarineController > eng_community_notice");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/notice");
        return mv;
    }

    @RequestMapping(value = "/eng/community/notice/detail.do", method = RequestMethod.GET)
    public ModelAndView eng_community_notice_detail() {
        System.out.println("EduMarineController > eng_community_notice_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/notice/detail");
        return mv;
    }

    @RequestMapping(value = "/eng/community/news.do", method = RequestMethod.GET)
    public ModelAndView eng_community_news() {
        System.out.println("EduMarineController > eng_community_news");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/news");
        return mv;
    }

    @RequestMapping(value = "/eng/community/news/detail.do", method = RequestMethod.GET)
    public ModelAndView eng_community_news_detail() {
        System.out.println("EduMarineController > eng_community_news_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/community/news/detail");
        return mv;
    }

    //***************************************************************************
    // introduce Folder
    //***************************************************************************

    @RequestMapping(value = "/eng/introduce/business.do", method = RequestMethod.GET)
    public ModelAndView eng_introduce_business() {
        System.out.println("EduMarineController > eng_introduce_business");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/introduce/business");
        return mv;
    }

    @RequestMapping(value = "/eng/introduce/vision.do", method = RequestMethod.GET)
    public ModelAndView eng_introduce_vision() {
        System.out.println("EduMarineController > eng_introduce_vision");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/introduce/vision");
        return mv;
    }

    @RequestMapping(value = "/eng/introduce/history.do", method = RequestMethod.GET)
    public ModelAndView eng_introduce_history() {
        System.out.println("EduMarineController > eng_introduce_history");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/introduce/history");
        return mv;
    }

    @RequestMapping(value = "/eng/introduce/way.do", method = RequestMethod.GET)
    public ModelAndView eng_introduce_way() {
        System.out.println("EduMarineController > eng_introduce_way");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/introduce/way");
        return mv;
    }

    @RequestMapping(value = "/eng/introduce/greeting.do", method = RequestMethod.GET)
    public ModelAndView eng_introduce_greeting() {
        System.out.println("EduMarineController > eng_introduce_greeting");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/introduce/greeting");
        return mv;
    }

    //***************************************************************************
    // member Folder
    //***************************************************************************

    @RequestMapping(value = "/eng/member/ascdirectors.do", method = RequestMethod.GET)
    public ModelAndView eng_member_ascdirectors() {
        System.out.println("EduMarineController > eng_member_ascdirectors");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/ascdirectors");
        return mv;
    }

    @RequestMapping(value = "/eng/member/detailA.do", method = RequestMethod.GET)
    public ModelAndView eng_member_detailA() {
        System.out.println("EduMarineController > eng_member_detailA");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/detailA");
        return mv;
    }

    @RequestMapping(value = "/eng/member/detailB.do", method = RequestMethod.GET)
    public ModelAndView eng_member_detailB() {
        System.out.println("EduMarineController > eng_member_detailB");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/detailB");
        return mv;
    }

    @RequestMapping(value = "/eng/member/detailC.do", method = RequestMethod.GET)
    public ModelAndView eng_member_detailC() {
        System.out.println("EduMarineController > eng_member_detailC");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/detailC");
        return mv;
    }

    @RequestMapping(value = "/eng/member/adviser.do", method = RequestMethod.GET)
    public ModelAndView eng_member_adviser() {
        System.out.println("EduMarineController > eng_member_adviser");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/adviser");
        return mv;
    }

    @RequestMapping(value = "/eng/member/consultation.do", method = RequestMethod.GET)
    public ModelAndView eng_member_consultation() {
        System.out.println("EduMarineController > eng_member_consultation");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/consultation");
        return mv;
    }

    @RequestMapping(value = "/eng/member/regular.do", method = RequestMethod.GET)
    public ModelAndView eng_member_regular() {
        System.out.println("EduMarineController > eng_member_regular");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/regular");
        return mv;
    }

    @RequestMapping(value = "/eng/member/ascmembers.do", method = RequestMethod.GET)
    public ModelAndView eng_member_ascmembers() {
        System.out.println("EduMarineController > eng_member_ascmembers");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/ascmembers");
        return mv;
    }

    @RequestMapping(value = "/eng/member/partnership.do", method = RequestMethod.GET)
    public ModelAndView eng_member_partnership() {
        System.out.println("EduMarineController > eng_member_partnership");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/member/partnership");
        return mv;
    }

    //***************************************************************************
    // service Folder
    //***************************************************************************

    @RequestMapping(value = "/eng/service/benefits.do", method = RequestMethod.GET)
    public ModelAndView eng_service_benefits() {
        System.out.println("EduMarineController > eng_service_benefits");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/service/benefits");
        return mv;
    }

    @RequestMapping(value = "/eng/service/faq.do", method = RequestMethod.GET)
    public ModelAndView eng_service_faq() {
        System.out.println("EduMarineController > eng_service_faq");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/service/faq");
        return mv;
    }

    @RequestMapping(value = "/eng/service/guide.do", method = RequestMethod.GET)
    public ModelAndView eng_service_guide() {
        System.out.println("EduMarineController > eng_service_guide");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/service/guide");
        return mv;
    }

    //***************************************************************************
    // support Folder
    //***************************************************************************

    @RequestMapping(value = "/eng/support/bidanm.do", method = RequestMethod.GET)
    public ModelAndView eng_support_bidanm() {
        System.out.println("EduMarineController > eng_support_bidanm");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/support/bidanm");
        return mv;
    }

    @RequestMapping(value = "/eng/support/bidanm/detail.do", method = RequestMethod.GET)
    public ModelAndView eng_support_bidanm_detail() {
        System.out.println("EduMarineController > eng_support_bidanm_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/support/bidanm/detail");
        return mv;
    }

    @RequestMapping(value = "/eng/support/bizanm.do", method = RequestMethod.GET)
    public ModelAndView eng_support_bizanm() {
        System.out.println("EduMarineController > eng_support_bizanm");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/support/bizanm");
        return mv;
    }

    @RequestMapping(value = "/eng/support/bizanm/detail.do", method = RequestMethod.GET)
    public ModelAndView eng_support_bizanm_detail() {
        System.out.println("EduMarineController > eng_support_bizanm_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/support/bizanm/detail");
        return mv;
    }

    @RequestMapping(value = "/eng/support/issue.do", method = RequestMethod.GET)
    public ModelAndView eng_support_issue() {
        System.out.println("EduMarineController > eng_support_issue");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/support/issue");
        return mv;
    }

    @RequestMapping(value = "/eng/support/issue/detail.do", method = RequestMethod.GET)
    public ModelAndView eng_support_issue_detail() {
        System.out.println("EduMarineController > eng_support_issue_detail");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/eng/support/issue/detail");
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
                    String path = ResourceUtils.getFile("./tomcat/webapps/upload/" + uploadFilePath).toPath().toString();

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
