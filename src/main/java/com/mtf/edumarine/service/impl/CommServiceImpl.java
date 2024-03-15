package com.mtf.edumarine.service.impl;

import com.google.gson.Gson;
import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.SmsDTO;
import com.mtf.edumarine.dto.SmsNotificationDTO;
import com.mtf.edumarine.dto.SmsResponseDTO;
import com.mtf.edumarine.dto.TemplateDTO;
import com.mtf.edumarine.mapper.CommMapper;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineMngService;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CommServiceImpl implements CommService {

    @Setter(onMethod_ = {@Autowired})
    private CommMapper commMapper;

    private final EduMarineMngService eduMarineMngService;

    @Autowired
    public CommServiceImpl(EduMarineMngService eduMarineMngService) {
        this.eduMarineMngService = eduMarineMngService;
    }

    /**
     * 단건 전송용
     * */
    @Override
    public SmsResponseDTO smsSend(SmsDTO smsDTO) {
        String senderParam = CommConstants.SMS_SENDER_NUM;
        String receiverParam = smsDTO.getPhone();
        if(receiverParam.contains("-")){
            receiverParam = receiverParam.replaceAll("-","");
        }

        String result = "";
        try {
            final String encodingType = "UTF8";
            final String boundary = "____boundary____";

            /**************** 문자전송하기 예제 ******************/
            /* "result_code":결과코드,"message":결과문구, */
            /* "msg_id":메세지ID,"error_cnt":에러갯수,"success_cnt":성공갯수 */
            /* 동일내용 > 전송용 입니다.
            /******************** 인증정보 ********************/
            String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL

            Map<String, String> sms = new HashMap<String, String>();

            sms.put("user_id", "meetingfan"); // SMS 아이디
            sms.put("key", "ddefu9nx1etgljr1p1z1n9h7ri5u8mf0"); //인증키

            /******************** 인증정보 ********************/

            /******************** 전송정보 ********************/
            sms.put("msg", smsDTO.getContent()); // 메세지 내용
            sms.put("receiver", receiverParam); // 수신번호
            sms.put("destination", ""/*smsDTO.getReceiver()+"|"+smsDTO.getCustomerName()*/); // 수신인 %고객명% 치환
            sms.put("sender", senderParam); // 발신번호
            sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
            sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
            sms.put("testmode_yn", ""); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
            sms.put("title", "경기해양레저인력양성센터"); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)

            String image = "";
            //image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치

            /******************** 전송정보 ********************/

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setBoundary(boundary);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName(encodingType));

            for (Iterator<String> i = sms.keySet().iterator(); i.hasNext(); ) {
                String key = i.next();
                builder.addTextBody(key, sms.get(key)
                        , ContentType.create("Multipart/related", encodingType));
            }

            File imageFile = new File(image);
            if (image != null && image.length() > 0 && imageFile.exists()) {

                builder.addPart("image",
                        new FileBody(imageFile, ContentType.create("application/octet-stream"),
                                URLEncoder.encode(imageFile.getName(), encodingType)));
            }

            HttpEntity entity = builder.build();

            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(sms_url);
            post.setEntity(entity);

            HttpResponse res = client.execute(post);

            if(res != null){
                BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                String buffer = null;
                while((buffer = in.readLine())!=null){
                    result += buffer;
                }
                in.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Gson gson = new Gson();
        SmsResponseDTO responseDTO = gson.fromJson(uniToKor(result), SmsResponseDTO.class);
        System.out.println("Msg Send Response : " + responseDTO.toString());
        //{"result_code":"1","message":"success","msg_id":"583009869","success_cnt":1,"error_cnt":0,"msg_type":"SMS"}

        return responseDTO;
    }

    /**
     * 단건 전송용
     * */
    @Override
    public SmsResponseDTO smsSend_certNum(SmsDTO smsDTO) {

        String certNum = sendSMS();
        String message = "[EDU marine] 인증번호 확인\n"
                + "[ " + certNum + " ]\n"
                + "본인 확인 인증번호를 입력해주세요!";

        String senderParam = CommConstants.SMS_SENDER_NUM;
        String receiverParam = smsDTO.getPhone();
        if(receiverParam.contains("-")){
            receiverParam = receiverParam.replaceAll("-","");
        }

        String result = "";
        try {
            final String encodingType = "UTF8";
            final String boundary = "____boundary____";

            /**************** 문자전송하기 예제 ******************/
            /* "result_code":결과코드,"message":결과문구, */
            /* "msg_id":메세지ID,"error_cnt":에러갯수,"success_cnt":성공갯수 */
            /* 동일내용 > 전송용 입니다.
            /******************** 인증정보 ********************/
            String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL

            Map<String, String> sms = new HashMap<String, String>();

            sms.put("user_id", "meetingfan"); // SMS 아이디
            sms.put("key", "ddefu9nx1etgljr1p1z1n9h7ri5u8mf0"); //인증키

            /******************** 인증정보 ********************/

            /******************** 전송정보 ********************/
            sms.put("msg", message); // 메세지 내용
            sms.put("receiver", receiverParam); // 수신번호
            sms.put("destination", ""/*smsDTO.getReceiver()+"|"+smsDTO.getCustomerName()*/); // 수신인 %고객명% 치환
            sms.put("sender", senderParam); // 발신번호
            sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
            sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
            sms.put("testmode_yn", ""); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
            sms.put("title", "[EDU marine]"); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)

            String image = "";
            //image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치

            /******************** 전송정보 ********************/

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setBoundary(boundary);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName(encodingType));

            for (Iterator<String> i = sms.keySet().iterator(); i.hasNext(); ) {
                String key = i.next();
                builder.addTextBody(key, sms.get(key)
                        , ContentType.create("Multipart/related", encodingType));
            }

            File imageFile = new File(image);
            if (image != null && image.length() > 0 && imageFile.exists()) {

                builder.addPart("image",
                        new FileBody(imageFile, ContentType.create("application/octet-stream"),
                                URLEncoder.encode(imageFile.getName(), encodingType)));
            }

            HttpEntity entity = builder.build();

            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(sms_url);
            post.setEntity(entity);

            HttpResponse res = client.execute(post);


            if(res != null){
                BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                String buffer = null;
                while((buffer = in.readLine())!=null){
                    result += buffer;
                }
                in.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Gson gson = new Gson();
        SmsResponseDTO responseDTO = gson.fromJson(uniToKor(result), SmsResponseDTO.class);
        responseDTO.setNote(certNum);
        System.out.println("Msg Send Response : " + responseDTO.toString());
        //{"result_code":"1","message":"success","msg_id":"583009869","success_cnt":1,"error_cnt":0,"msg_type":"SMS"}

        return responseDTO;
    }

    public String sendSMS() {

        Random rand  = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i=0; i<6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }

        System.out.println("인증번호 : " + numStr);
        return numStr.toString();
    }

    @Override
    public String smsSendNotifySending(SmsNotificationDTO smsNotificationDTO){

        String resultCode = CommConstants.RESULT_CODE_SUCCESS;

        String content = smsNotificationDTO.getContent();
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        if(content != null && !"".equals(content)){

            if ("1".equals(smsNotificationDTO.getTarget())) { // 회원가입 안내
                String phone = commMapper.getSmsSendingJoinList(smsNotificationDTO);

                if(phone != null && !"".equals(phone)){
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if(responseDTO.getResult_code() != 1){
                        result = "실패";
                    }

                    SmsDTO smsResultDTO = new SmsDTO();
                    smsResultDTO.setSmsGroup(nowDate);
                    smsResultDTO.setPhone(phone);
                    smsResultDTO.setSender("관리자");
                    smsResultDTO.setSenderPhone(CommConstants.SMS_SENDER_NUM);
                    smsResultDTO.setContent(content);
                    smsResultDTO.setSendResult(result);
                    smsResultDTO.setTemplateSeq("T0000004");
                    eduMarineMngService.processInsertSms(smsResultDTO);
                }

            }else if ("8".equals(smsNotificationDTO.getTarget())) { // 키워드 알림
                List<String> targetList = commMapper.getSmsSendingKeywordList(smsNotificationDTO);
                for(String phone : targetList){
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if(responseDTO.getResult_code() != 1){
                        result = "실패";
                    }

                    SmsDTO smsResultDTO = new SmsDTO();
                    smsResultDTO.setSmsGroup(nowDate);
                    smsResultDTO.setPhone(phone);
                    smsResultDTO.setSender("관리자");
                    smsResultDTO.setSenderPhone(CommConstants.SMS_SENDER_NUM);
                    smsResultDTO.setContent(content);
                    smsResultDTO.setSendResult(result);
                    smsResultDTO.setTemplateSeq("T0000011");
                    eduMarineMngService.processInsertSms(smsResultDTO);
                }
            }
        }

        return resultCode;
    }

    @Override
    public String smsSendNotifyContent(SmsNotificationDTO smsNotificationDTO){
        String content = null;

        String target = smsNotificationDTO.getTarget();
        if(target != null && !"".equals(target)){

            if("1".equals(target)) { // 회원가입 직후
                TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                if(templateDTO != null){
                    content = templateDTO.getContent();
                }
            }else if("8".equals(target)){ // 키워드 알림
                TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                if(templateDTO != null){
                    content = templateDTO.getContent().replace("%keyword%", smsNotificationDTO.getKeyword());
                }
            }

        }
        return content;
    }

    public String uniToKor(String uni){
        StringBuilder result = new StringBuilder();

        for(int i=0; i<uni.length(); i++){
            if(uni.charAt(i) == '\\' &&  uni.charAt(i+1) == 'u'){
                Character c = (char)Integer.parseInt(uni.substring(i+2, i+6), 16);
                result.append(c);
                i+=5;
            }else{
                result.append(uni.charAt(i));
            }
        }
        return result.toString();
    }

    @Override
    public String getSystemicSiteMap() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n" +
                "  <!-- edumarine 페이지들 -->\n" +
                "  <url>\n" +
                "    <loc>https://edumarine.org/main.do</loc>\n" +
                "    <changefreq>daily</changefreq>\n" +
                "    <priority>0.9</priority>\n" +
                "  </url>\n" +
                "</urlset>";
    }

}
