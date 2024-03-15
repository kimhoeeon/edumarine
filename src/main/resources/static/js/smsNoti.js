function f_sms_notify_sending(target, paramData){
    /**
     * 1 회원가입 직후
     * 2 수강신청 후
     * 3 결제 후
     * 4 취소신청 후
     * 5 취소완료 후
     * 6 가상계좌 안내
     * 7 수업 개설 2일전 교육안내
     * 8 키워드 게시물 알람 (공지사항, 교육신청)
     */
    let result = '';
    switch (target){
        case '1': // 회원가입 직후 알람
            let jsonObj1 = { target: target , content: f_sms_notify_content(target, paramData) , seq: paramData.seq }
            result = ajaxConnect('/sms/send/notify/sending.do', 'post', jsonObj1);
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
     * 1 회원가입 직후
     * 2 수강신청 후
     * 3 결제 후
     * 4 취소신청 후
     * 5 취소완료 후
     * 6 가상계좌 안내
     * 7 수업 개설 2일전 교육안내
     * 8 키워드 게시물 알람
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