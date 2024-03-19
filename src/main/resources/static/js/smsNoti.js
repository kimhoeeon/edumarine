function f_sms_notify_sending(target, paramData){
    /**
     * 1 회원가입 직후 (완)
     * 2 수강신청 후 (완)
     * 3 결제 후
     * 4 취소신청 후
     * 5 취소완료 후 (완)
     * 6 가상계좌 안내 (완)
     * 7 수업 개설 2일전 교육안내 (완)
     * 8 키워드 게시물 알람 (완)
     */
    let result = '';
    switch (target){
        case '1': // 회원가입 직후 알람
            let jsonObj1 = { target: target , content: f_sms_notify_content(target, paramData) , seq: paramData.seq }
            result = ajaxConnect('/sms/send/notify/sending.do', 'post', jsonObj1);
            break;
        case '2': // 회원가입 직후 알람
            let jsonObj2 = { target: target , content: f_sms_notify_content(target, paramData) , seq : paramData.seq }
            result = ajaxConnect('/sms/send/notify/sending.do', 'post', jsonObj2);
            break;
        case '5': // 취소완료 후
            let jsonObj5 = { target: target , content: f_sms_notify_content(target, paramData) , seq : paramData.seq , trainTable: paramData.trainTable}
            result = ajaxConnect('/sms/send/notify/sending.do', 'post', jsonObj5);
            break;
        case '8': // 키워드 게시물 알람
            let jsonObj8 = { target: target , content: f_sms_notify_content(target, paramData) , keyword: paramData.keyword }
            result = ajaxConnect('/sms/send/notify/sending.do', 'post', jsonObj8);
            break;
        default:
            break;
    }
    return result;
}

function f_sms_notify_content(target, param){
    /**
     * 1 회원가입 직후 (완)
     * 2 수강신청 후 (완)
     * 3 결제 후
     * 4 취소신청 후
     * 5 취소완료 후 (완)
     * 6 가상계좌 안내 (완)
     * 7 수업 개설 2일전 교육안내 (완)
     * 8 키워드 게시물 알람 (완)
     */
    let content = '';
    switch (target){
        case '1':
            let jsonObj1 = { target: target }
            let resData1 = ajaxConnectSimple('/sms/send/notify/getContent.do', 'post', jsonObj1);
            if(nvl(resData1,'') !== ''){
                content = resData1;
            }
            break;
        case '2':
            let jsonObj2 = { target: target , trainSeq: param.trainSeq }
            let resData2 = ajaxConnectSimple('/sms/send/notify/getContent.do', 'post', jsonObj2);
            if(nvl(resData2,'') !== ''){
                content = resData2;
            }
            break;
        case '5':
            let jsonObj5 = { target: target , seq: param.seq , trainTable: param.trainTable }
            let resData5 = ajaxConnectSimple('/sms/send/notify/getContent.do', 'post', jsonObj5);
            if(nvl(resData5,'') !== ''){
                content = resData5;
            }
            break;
        case '8':
            let jsonObj8 = { target: target , keyword: param.keyword }
            let resData8 = ajaxConnectSimple('/sms/send/notify/getContent.do', 'post', jsonObj8);
            if(nvl(resData8,'') !== ''){
                content = resData8;
            }
            break;
        default:
            break;
    }


    return content;
}