package com.mtf.edumarine.service.impl;

import com.google.gson.Gson;
import com.mtf.edumarine.constants.CommConstants;
import com.mtf.edumarine.dto.*;
import com.mtf.edumarine.mapper.CommMapper;
import com.mtf.edumarine.service.CommService;
import com.mtf.edumarine.service.EduMarineMngService;
import com.mtf.edumarine.util.StringUtil;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     */
    @Override
    public SmsResponseDTO smsSend(SmsDTO smsDTO) {
        String senderParam = CommConstants.SMS_SENDER_NUM;
        String receiverParam = smsDTO.getPhone();
        if (receiverParam.contains("-")) {
            receiverParam = receiverParam.replaceAll("-", "");
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

            if (res != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                String buffer = null;
                while ((buffer = in.readLine()) != null) {
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
     */
    @Override
    public SmsResponseDTO smsSend_certNum(SmsDTO smsDTO) {

        String certNum = sendSMS();
        String message = "[ EDU marine ] 인증번호 확인\n"
                + "[ " + certNum + " ]\n"
                + "본인 확인 인증번호를 입력해주세요!";

        String senderParam = CommConstants.SMS_SENDER_NUM;
        String receiverParam = smsDTO.getPhone();
        if (receiverParam.contains("-")) {
            receiverParam = receiverParam.replaceAll("-", "");
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
            sms.put("title", "[ EDU marine ]"); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)

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


            if (res != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                String buffer = null;
                while ((buffer = in.readLine()) != null) {
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

        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }

        System.out.println("인증번호 : " + numStr);
        return numStr.toString();
    }

    @Override
    public String smsSendNotifySending(SmsNotificationDTO smsNotificationDTO) {

        String resultCode = CommConstants.RESULT_CODE_SUCCESS;

        String content = smsNotificationDTO.getContent();
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        if (content != null && !"".equals(content)) {

            if ("1".equals(smsNotificationDTO.getTarget())) { // 회원가입 안내
                String phone = commMapper.getSmsSendingJoinList(smsNotificationDTO);

                if (phone != null && !"".equals(phone)) {
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if (responseDTO.getResult_code() != 1) {
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

            } else if ("2".equals(smsNotificationDTO.getTarget())) { // 수강신청 후
                String phone = commMapper.getSmsSendingJoinList(smsNotificationDTO);

                if (phone != null && !"".equals(phone)) {
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if (responseDTO.getResult_code() != 1) {
                        result = "실패";
                    }

                    SmsDTO smsResultDTO = new SmsDTO();
                    smsResultDTO.setSmsGroup(nowDate);
                    smsResultDTO.setPhone(phone);
                    smsResultDTO.setSender("관리자");
                    smsResultDTO.setSenderPhone(CommConstants.SMS_SENDER_NUM);
                    smsResultDTO.setContent(content);
                    smsResultDTO.setSendResult(result);
                    smsResultDTO.setTemplateSeq("T0000005");
                    eduMarineMngService.processInsertSms(smsResultDTO);
                }

            } else if ("5".equals(smsNotificationDTO.getTarget())) { // 취소완료 후
                String trainTableName = smsNotificationDTO.getTrainTable();
                String phone = "";
                switch (trainTableName){
                    case "boarder":
                        BoarderDTO boarderDTO = eduMarineMngService.processSelectBoarderSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq1 = new SmsNotificationDTO();
                        smsReq1.setSeq(boarderDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq1);
                        break;
                    case "frp":
                        FrpDTO frpDTO = eduMarineMngService.processSelectFrpSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq2 = new SmsNotificationDTO();
                        smsReq2.setSeq(frpDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq2);
                        break;
                    case "outboarder":
                        OutboarderDTO outboarderDTO = eduMarineMngService.processSelectOutboarderSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq3 = new SmsNotificationDTO();
                        smsReq3.setSeq(outboarderDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq3);
                        break;
                    case "inboarder":
                        InboarderDTO inboarderReq = new InboarderDTO();
                        inboarderReq.setSeq(smsNotificationDTO.getSeq());
                        InboarderDTO inboarderDTO = eduMarineMngService.processSelectInboarderSingle(inboarderReq);
                        SmsNotificationDTO smsReq4 = new SmsNotificationDTO();
                        smsReq4.setSeq(inboarderDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq4);
                        break;
                    case "sailyacht":
                        SailyachtDTO sailyachtDTO = eduMarineMngService.processSelectSailyachtSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq5 = new SmsNotificationDTO();
                        smsReq5.setSeq(sailyachtDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq5);
                        break;
                    case "highhorsepower":
                        HighHorsePowerDTO highHorsePowerDTO = eduMarineMngService.processSelectHighhorsepowerSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq6 = new SmsNotificationDTO();
                        smsReq6.setSeq(highHorsePowerDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq6);
                        break;
                    case "highself":
                        HighSelfDTO highSelfDTO = eduMarineMngService.processSelectHighSelfSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq7 = new SmsNotificationDTO();
                        smsReq7.setSeq(highSelfDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq7);
                        break;
                    case "highspecial":
                        HighSpecialDTO highSpecialDTO = eduMarineMngService.processSelectHighSpecialSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq8 = new SmsNotificationDTO();
                        smsReq8.setSeq(highSpecialDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq8);
                        break;
                    case "sterndrive":
                        SterndriveDTO sterndriveDTO = eduMarineMngService.processSelectSterndriveSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq9 = new SmsNotificationDTO();
                        smsReq9.setSeq(sterndriveDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq9);
                        break;
                    case "sternspecial":
                        SternSpecialDTO sternSpecialDTO = eduMarineMngService.processSelectSternSpecialSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq10 = new SmsNotificationDTO();
                        smsReq10.setSeq(sternSpecialDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq10);
                        break;
                    case "basic":
                        BasicDTO basicDTO = eduMarineMngService.processSelectBasicSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq11 = new SmsNotificationDTO();
                        smsReq11.setSeq(basicDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq11);
                        break;
                    case "emergency":
                        EmergencyDTO emergencyDTO = eduMarineMngService.processSelectEmergencySingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq12 = new SmsNotificationDTO();
                        smsReq12.setSeq(emergencyDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq12);
                        break;
                    case "generator":
                        GeneratorDTO generatorDTO = eduMarineMngService.processSelectGeneratorSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq13 = new SmsNotificationDTO();
                        smsReq13.setSeq(generatorDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq13);
                        break;
                    case "competency":
                        CompetencyDTO competencyDTO = eduMarineMngService.processSelectCompetencySingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq14 = new SmsNotificationDTO();
                        smsReq14.setSeq(competencyDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq14);
                        break;
                    case "famtourin":
                        FamtourinDTO famtourinDTO = eduMarineMngService.processSelectFamtourinSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq15 = new SmsNotificationDTO();
                        smsReq15.setSeq(famtourinDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq15);
                        break;
                    case "famtourout":
                        FamtouroutDTO famtouroutDTO = eduMarineMngService.processSelectFamtouroutSingle(smsNotificationDTO.getSeq());
                        SmsNotificationDTO smsReq16 = new SmsNotificationDTO();
                        smsReq16.setSeq(famtouroutDTO.getMemberSeq());
                        phone = commMapper.getSmsSendingJoinList(smsReq16);
                        break;
                    default:
                        break;
                }

                if (phone != null && !"".equals(phone)) {
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if (responseDTO.getResult_code() != 1) {
                        result = "실패";
                    }

                    SmsDTO smsResultDTO = new SmsDTO();
                    smsResultDTO.setSmsGroup(nowDate);
                    smsResultDTO.setPhone(phone);
                    smsResultDTO.setSender("관리자");
                    smsResultDTO.setSenderPhone(CommConstants.SMS_SENDER_NUM);
                    smsResultDTO.setContent(content);
                    smsResultDTO.setSendResult(result);
                    smsResultDTO.setTemplateSeq("T0000008");
                    eduMarineMngService.processInsertSms(smsResultDTO);
                }

            } else if ("6".equals(smsNotificationDTO.getTarget())) { // 가상계좌 안내
                String phone = commMapper.getSmsSendingJoinList(smsNotificationDTO);

                if (phone != null && !"".equals(phone)) {
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if (responseDTO.getResult_code() != 1) {
                        result = "실패";
                    }

                    SmsDTO smsResultDTO = new SmsDTO();
                    smsResultDTO.setSmsGroup(nowDate);
                    smsResultDTO.setPhone(phone);
                    smsResultDTO.setSender("관리자");
                    smsResultDTO.setSenderPhone(CommConstants.SMS_SENDER_NUM);
                    smsResultDTO.setContent(content);
                    smsResultDTO.setSendResult(result);
                    smsResultDTO.setTemplateSeq("T0000009");
                    eduMarineMngService.processInsertSms(smsResultDTO);
                }

            } else if ("7".equals(smsNotificationDTO.getTarget())) { // 수업 개설 2일전 교육안내
                List<TrainDTO> targetList = commMapper.getSmsSendingTrainNotiList();
                if(targetList != null){
                    for(TrainDTO trainDTO : targetList){
                        if (trainDTO.getPhone() != null && !"".equals(trainDTO.getPhone())) {

                            String eduDate = trainDTO.getTrainStartDttm() + '~' + trainDTO.getTrainEndDttm();
                            content = content.replace("%eduName%", trainDTO.getGbn())
                                    .replace("%eduTime%", String.valueOf(trainDTO.getNextTime()))
                                    .replace("%eduDate%", eduDate);

                            SmsDTO smsDTO = new SmsDTO();
                            smsDTO.setPhone(trainDTO.getPhone());
                            smsDTO.setContent(content);
                            SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                            String result = "성공";
                            if (responseDTO.getResult_code() != 1) {
                                result = "실패";
                            }

                            SmsDTO smsResultDTO = new SmsDTO();
                            smsResultDTO.setSmsGroup(nowDate);
                            smsResultDTO.setPhone(trainDTO.getPhone());
                            smsResultDTO.setSender("관리자");
                            smsResultDTO.setSenderPhone(CommConstants.SMS_SENDER_NUM);
                            smsResultDTO.setContent(content);
                            smsResultDTO.setSendResult(result);
                            smsResultDTO.setTemplateSeq("T0000010");
                            eduMarineMngService.processInsertSms(smsResultDTO);
                        }
                    }
                }

            } else if ("8".equals(smsNotificationDTO.getTarget())) { // 키워드 알림
                List<String> targetList = commMapper.getSmsSendingKeywordList(smsNotificationDTO);
                for (String phone : targetList) {
                    SmsDTO smsDTO = new SmsDTO();
                    smsDTO.setPhone(phone);
                    smsDTO.setContent(content);
                    SmsResponseDTO responseDTO = smsSend(smsDTO); // sms sending method

                    String result = "성공";
                    if (responseDTO.getResult_code() != 1) {
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
    public String smsSendNotifyContent(SmsNotificationDTO smsNotificationDTO) {
        String content = null;

        String target = smsNotificationDTO.getTarget();
        if (target != null && !"".equals(target)) {

            switch (target) {
                case "1": { // 회원가입 직후
                    TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                    if (templateDTO != null) {
                        content = templateDTO.getContent();
                    }
                    break;
                }
                case "2": { // 수강신청 후
                    TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                    if (templateDTO != null) {
                        TrainDTO trainReq = new TrainDTO();
                        trainReq.setSeq(smsNotificationDTO.getTrainSeq());
                        TrainDTO trainRes = eduMarineMngService.processSelectTrainSingle(trainReq);
                        content = templateDTO.getContent().replace("%eduName%", trainRes.getGbn())
                                .replace("%eduTime%", String.valueOf(trainRes.getNextTime()))
                                .replace("%eduDate%", trainRes.getTrainStartDttm() + "-" + trainRes.getTrainEndDttm())
                        ;
                    }
                    break;
                }
                case "5": { // 취소완료 후
                    TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                    if (templateDTO != null) {

                        String trainTableName = smsNotificationDTO.getTrainTable();
                        String trainSeq = "";
                        switch (trainTableName) {
                            case "boarder":
                                BoarderDTO boarderDTO = eduMarineMngService.processSelectBoarderSingle(smsNotificationDTO.getSeq());
                                trainSeq = boarderDTO.getTrainSeq();
                                break;
                            case "frp":
                                FrpDTO frpDTO = eduMarineMngService.processSelectFrpSingle(smsNotificationDTO.getSeq());
                                trainSeq = frpDTO.getTrainSeq();
                                break;
                            case "outboarder":
                                OutboarderDTO outboarderDTO = eduMarineMngService.processSelectOutboarderSingle(smsNotificationDTO.getSeq());
                                trainSeq = outboarderDTO.getTrainSeq();
                                break;
                            case "inboarder":
                                InboarderDTO inboarderReq = new InboarderDTO();
                                inboarderReq.setSeq(smsNotificationDTO.getSeq());
                                InboarderDTO inboarderDTO = eduMarineMngService.processSelectInboarderSingle(inboarderReq);
                                trainSeq = inboarderDTO.getTrainSeq();
                                break;
                            case "sailyacht":
                                SailyachtDTO sailyachtDTO = eduMarineMngService.processSelectSailyachtSingle(smsNotificationDTO.getSeq());
                                trainSeq = sailyachtDTO.getTrainSeq();
                                break;
                            case "highhorsepower":
                                HighHorsePowerDTO highHorsePowerDTO = eduMarineMngService.processSelectHighhorsepowerSingle(smsNotificationDTO.getSeq());
                                trainSeq = highHorsePowerDTO.getTrainSeq();
                                break;
                            case "highself":
                                HighSelfDTO highSelfDTO = eduMarineMngService.processSelectHighSelfSingle(smsNotificationDTO.getSeq());
                                trainSeq = highSelfDTO.getTrainSeq();
                                break;
                            case "highspecial":
                                HighSpecialDTO highSpecialDTO = eduMarineMngService.processSelectHighSpecialSingle(smsNotificationDTO.getSeq());
                                trainSeq = highSpecialDTO.getTrainSeq();
                                break;
                            case "sterndrive":
                                SterndriveDTO sterndriveDTO = eduMarineMngService.processSelectSterndriveSingle(smsNotificationDTO.getSeq());
                                trainSeq = sterndriveDTO.getTrainSeq();
                                break;
                            case "sternspecial":
                                SternSpecialDTO sternSpecialDTO = eduMarineMngService.processSelectSternSpecialSingle(smsNotificationDTO.getSeq());
                                trainSeq = sternSpecialDTO.getTrainSeq();
                                break;
                            case "basic":
                                BasicDTO basicDTO = eduMarineMngService.processSelectBasicSingle(smsNotificationDTO.getSeq());
                                trainSeq = basicDTO.getTrainSeq();
                                break;
                            case "emergency":
                                EmergencyDTO emergencyDTO = eduMarineMngService.processSelectEmergencySingle(smsNotificationDTO.getSeq());
                                trainSeq = emergencyDTO.getTrainSeq();
                                break;
                            case "generator":
                                GeneratorDTO generatorDTO = eduMarineMngService.processSelectGeneratorSingle(smsNotificationDTO.getSeq());
                                trainSeq = generatorDTO.getTrainSeq();
                                break;
                            case "competency":
                                CompetencyDTO competencyDTO = eduMarineMngService.processSelectCompetencySingle(smsNotificationDTO.getSeq());
                                trainSeq = competencyDTO.getTrainSeq();
                                break;
                            case "famtourin":
                                FamtourinDTO famtourinDTO = eduMarineMngService.processSelectFamtourinSingle(smsNotificationDTO.getSeq());
                                trainSeq = famtourinDTO.getTrainSeq();
                                break;
                            case "famtourout":
                                FamtouroutDTO famtouroutDTO = eduMarineMngService.processSelectFamtouroutSingle(smsNotificationDTO.getSeq());
                                trainSeq = famtouroutDTO.getTrainSeq();
                                break;
                            default:
                                break;
                        }

                        if (!"".equals(trainSeq)) {
                            DecimalFormat df = new DecimalFormat("###,###");

                            TrainDTO trainReq = new TrainDTO();
                            trainReq.setSeq(trainSeq);
                            TrainDTO trainRes = eduMarineMngService.processSelectTrainSingle(trainReq);
                            content = templateDTO.getContent().replace("%eduName%", trainRes.getGbn())
                                    .replace("%eduTime%", String.valueOf(trainRes.getNextTime()))
                                    .replace("%eduPrice%", df.format(trainRes.getPaySum()))
                            ;
                        }
                    }
                    break;
                }
                case "6": { // 가상계좌 안내
                    TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                    if (templateDTO != null) {
                        TrainDTO trainReq = new TrainDTO();
                        trainReq.setSeq(smsNotificationDTO.getTrainSeq());
                        TrainDTO trainRes = eduMarineMngService.processSelectTrainSingle(trainReq);
                        content = templateDTO.getContent().replace("%eduName%", trainRes.getGbn())
                                .replace("%eduTime%", String.valueOf(trainRes.getNextTime()))
                        ;

                        PaymentDTO payReq = new PaymentDTO();
                        payReq.setSeq(smsNotificationDTO.getPaymentSeq());
                        PaymentDTO payRes = eduMarineMngService.processSelectPaymentSingle(payReq);
                        content = content.replace("%vacctBank%", payRes.getVactBankName())
                                .replace("%vacctNum%", payRes.getVactNum())
                                .replace("%vacctName%", payRes.getVactName())
                        ;

                        String vactDate = payRes.getVactDate().concat(payRes.getVactTime());
                        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        // String 타입을 Date 타입으로 변환
                        Date formatDate = null;
                        try {
                            formatDate = dtFormat.parse(vactDate);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        // Date타입의 변수를 새롭게 지정한 포맷으로 변환
                        String newVactDate = newDtFormat.format(formatDate);
                        content = content.replace("%vacctLimit%", newVactDate);
                    }
                    break;
                }
                case "7": { // 수업 개설 2일전 교육안내
                    TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                    if (templateDTO != null) {
                        content = templateDTO.getContent();
                    }
                    break;
                }
                case "8": { // 키워드 알림
                    TemplateDTO templateDTO = commMapper.getTemplateContent(target);
                    if (templateDTO != null) {
                        content = templateDTO.getContent().replace("%keyword%", smsNotificationDTO.getKeyword());
                    }
                    break;
                }
            }

        }
        return content;
    }


    public String uniToKor(String uni) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < uni.length(); i++) {
            if (uni.charAt(i) == '\\' && uni.charAt(i + 1) == 'u') {
                Character c = (char) Integer.parseInt(uni.substring(i + 2, i + 6), 16);
                result.append(c);
                i += 5;
            } else {
                result.append(uni.charAt(i));
            }
        }
        return result.toString();
    }

    @Override
    public void updateMemberGrade(){
        List<MemberGradeDTO> memberGradeTargetList = commMapper.selectMemberGradeUpdateTarget();
        for(MemberGradeDTO member : memberGradeTargetList){
            String grade = member.getGrade();
            String afterGrade = member.getAfterGrade();
            if(!Objects.equals(grade, afterGrade)){
                System.out.println("[Member grade update] seq : " + member.getSeq() + " , name : " + member.getName() + " , grade : " + member.getGrade() + " -> " + member.getAfterGrade());
                commMapper.updateMemberGrade(member);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public ResponseDTO processUpdateFileUserId(FileDTO fileDTO) {
        System.out.println("CommServiceImpl > processUpdateFileUserId");
        ResponseDTO responseDTO = new ResponseDTO();
        String resultCode = CommConstants.RESULT_CODE_SUCCESS;
        String resultMessage = CommConstants.RESULT_MSG_SUCCESS;
        Integer result = 0;
        try {
            if(!StringUtil.isEmpty(fileDTO.getId())){

                result = commMapper.updateFileUserId(fileDTO);
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

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public void processUpdateFileDeleteUseN(FileDTO fileDTO) {
        System.out.println("CommServiceImpl > processUpdateFileDeleteUseN");
        try {
            if(!StringUtil.isEmpty(fileDTO.getUserId())){
                commMapper.updateFileDeleteUseN(fileDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    @Override
    public List<FileDTO> processSelectFileUserIdList(FileDTO fileDTO) {
        System.out.println("CommServiceImpl > processSelectFileUserIdList");
        return commMapper.selectFileUserIdList(fileDTO);
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