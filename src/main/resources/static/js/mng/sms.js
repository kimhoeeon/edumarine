/***
 * mng/smsMng/sms
 * 교육>SMS관리>SMS발송관리
 * */

$(function(){

    let myModalEl = document.getElementById('kt_modal_template_mng');

    if(myModalEl){

        let myModal = new bootstrap.Modal('#kt_modal_template_mng', {
            focus: true
        });

        myModalEl.addEventListener('hidden.bs.modal', event => {
            // input init
            $('#template_title').val('');
            $('#template_list').val('').select2({minimumResultsForSearch: Infinity});
            $('#template_content').val('');
        });

        myModalEl.addEventListener('show.bs.modal', event => {
            f_sms_template_list_set('T');
        });

        $('#template_mng_btn').on('click', function(){
            myModal.show();
        });

    };//myModalEl

    let myModalEl2 = document.getElementById('kt_modal_sms_mng');

    if(myModalEl2){

        let myModal2 = new bootstrap.Modal('#kt_modal_sms_mng', {
            focus: true
        });

        myModalEl2.addEventListener('hidden.bs.modal', event => {
            $('.senderPhoneList:not(:first)').remove();
            $('.senderPhoneList').find('input[type=text]').val('');
            $('#sms_template_title').val('');
            $('#sms_template_list').val('').select2({minimumResultsForSearch: Infinity});
            $('#sms_template_content').val('');
        });

        myModalEl2.addEventListener('show.bs.modal', event => {
            f_sms_template_list_set('S');
            $('.senderPhoneList:first .senderPhoneDelBtn').hide();
        });

        $('#sms_mng_btn').on('click', function(){
            myModal2.show();
        });

    };//myModalEl2

    $('#template_list').on('change', function () {
        let selectedOption = $(this).val();
        let templateTitle = $('#template_title');
        let templateContent = $('#template_content');

        templateTitle.val('');
        templateContent.val('');

        let remain = document.getElementById("templateRemain");
        remain.innerText = String(90);

        if (selectedOption === '신규등록') {
            templateTitle.prop('disabled', false).val('');
        } else {
            templateTitle.prop('disabled', true).val('');
            let templateSeq = $('#template_list').val();
            if(nvl(templateSeq,'') !== '' && templateSeq !== '신규등록'){
                let resData = ajaxConnect('/mng/smsMng/sms/template/selectSingle.do', 'post', {seq:templateSeq});
                templateTitle.val(resData.title);
                templateContent.val(resData.content);

                let temp_str = resData.content;
                remain.innerText = String(90 - getByte(temp_str));
            }
        }
    });

    $('#sms_template_list').on('change', function () {
        let selectedOption = $(this).val();
        let smsTemplateTitle = $('#sms_template_title');
        let smsTemplateContent = $('#sms_template_content');

        smsTemplateTitle.val('');
        smsTemplateContent.val('');
        let remain = document.getElementById("smsRemain");
        remain.innerText = String(90);

        if (selectedOption === '미사용') {
            smsTemplateTitle.prop('disabled', true).val('');
        } else {
            smsTemplateTitle.prop('disabled', true).val('');
            let templateSeq = $('#sms_template_list').val();
            if(nvl(templateSeq,'') !== '' && templateSeq !== '미사용'){
                let resData = ajaxConnect('/mng/smsMng/sms/template/selectSingle.do', 'post', {seq:templateSeq});
                smsTemplateTitle.val(resData.title);
                smsTemplateContent.val(resData.content);

                let temp_str = resData.content;
                remain.innerText = String(90 - getByte(temp_str));
            }
        }
    });

});

function f_sms_template_list_set(gbn){

    let resData = ajaxConnect('/mng/smsMng/sms/template/selectList.do', 'post', {});

    if(resData.length > 0){
        if(gbn === 'T'){
            $('#template_list').children('option:not(:lt(3))').remove();

            for(let i=0; i<resData.length; i++) {
                $('#template_list').append('<option value=' + resData[i].seq +'>' + resData[i].title +'</option>');
            }
        }else{
            $('#sms_template_list').children('option:not(:lt(2))').remove();

            for(let i=0; i<resData.length; i++) {
                $('#sms_template_list').append('<option value=' + resData[i].seq +'>' + resData[i].title +'</option>');
            }
        }
    }
}

function f_smsMng_sms_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_smsMng_sms_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();
    if(nullToEmpty(searchText) === ""){
        jsonObj = {
            condition: condition
        };
    }else{
        jsonObj = {
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/smsMng/sms/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_smsMng_sms_table tbody td');
    let cnt = 0;
    jb.each(function(index, item){
        let itemText = $(item).text();
        let itemText_trim = itemText.replaceAll(' ','');
        if(itemText_trim !== '' && !itemText.match('Actions')){
            $(item).attr('data-bs-toggle', 'tooltip');
            $(item).attr('data-bs-trigger', 'hover');
            $(item).attr('data-bs-custom-class', 'tooltip-inverse');
            $(item).attr('data-bs-placement', 'top');
            $(item).attr('title',itemText);
        }
        cnt++;
    })
    jb.tooltip();
}

function f_smsMng_sms_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_smsMng_sms_search();
}

function f_smsMng_sms_modify_init_set(seq){
    window.location.href = '/mng/smsMng/sms/detail.do?seq=' + seq;
}

function f_smsMng_sms_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 SMS 발송 내역을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/smsMng/sms/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', 'SMS 발송 내역 삭제', 'SMS 발송 내역이 삭제되었습니다.', '');
                    f_smsMng_sms_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', 'SMS 발송 내역 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }
}

function f_smsMng_sms_send(){
    Swal.fire({
        title: '입력된 정보로 SMS를 발송하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '발송',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            /* form valid check */
            let validCheck = f_smsMng_sms_valid();

            if(validCheck) {
                let senderPhoneList = $('.senderPhoneList').find('input[type=text]');
                let sendResultTxt = '';
                for (let i = 0; i < senderPhoneList.length; i++) {
                    let phone = senderPhoneList.eq(i).val();
                    let content = $('#sms_template_content').val();

                    let jsonObj = {
                        sender: '070-8949-8065', //미팅팬번호
                        phone: phone,
                        content: content
                    }

                    let sendResult = '성공';
                    let resData = ajaxConnect('/sms/send.do', 'post', jsonObj);
                    if (resData.result_code === 1) {
                        sendResultTxt += phone + ' [ 성공 ]<br>';
                    } else {
                        sendResultTxt += phone + ' [ 실패 ]<br>';
                        sendResult = '실패';
                    }

                    let jsonObj2 = {
                        smsGroup: getCurrentDate().substring(0, getCurrentDate().length-2),
                        phone: phone,
                        sender: '관리자',
                        senderPhone: '070-8949-8065', //미팅팬번호
                        content: content,
                        sendResult: sendResult,
                        templateSeq: $('#sms_template_list option:selected').val(),
                    }

                    let resData2 = ajaxConnect('/mng/smsMng/sms/insert.do', 'post', jsonObj2);

                    if(resData2.resultCode === "0"){
                        Swal.fire({
                            title: '[SMS 발송 결과]',
                            html: sendResultTxt,
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                $('#kt_modal_sms_mng').modal('hide');

                                f_smsMng_sms_search();
                            }
                        });
                    }else{
                        showMessage('', 'error', '에러 발생', 'SMS 발송을 실패하였습니다. 관리자에게 문의해주세요. ' + resData2.resultMessage, '');
                    }
                }
            }
        }
    });

}

function f_smsMng_sms_valid(){
    let senderPhoneList = $('.senderPhoneList').find('input[type=text]');
    for (let i = 0; i < senderPhoneList.length; i++) {
        if(nvl(senderPhoneList.eq(i).val(),'') === '') { showMessage('', 'error', '[SMS 발송 정보]', '연락처가 입력되지 않은 항목이 있습니다.', ''); return false; }
    }

    let content = $('#sms_template_content').val();
    if(nvl(content,'') === '') { showMessage('', 'error', '[SMS 발송 정보]', 'SMS 발송 내용을 입력해 주세요.', ''); return false; }

    return true;
}

function f_sms_template_add_btn(){

    /* form valid check */
    let validCheck = f_sms_template_add_valid();

    if(validCheck) {

        Swal.fire({
            title: '[등록 정보]',
            html: '작성하신 템플릿 정보를 저장하시겠습니까 ?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                let templateSeq = $('#template_list').val();
                let templateTitle = $('#template_title').val();
                let templateContent = $('#template_content').val();

                let jsonObj = {
                    seq: templateSeq,
                    title: templateTitle,
                    content: templateContent
                }

                let resData = ajaxConnect('/mng/smsMng/sms/template/save.do', 'post', jsonObj);

                if (resData.resultCode !== "0") {
                    showMessage('', 'error', '에러 발생', '템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    return false;
                } else {
                    showMessage('', 'info', '[등록 정보]', '템플릿 정보 저장이 정상 완료되었습니다.', '');

                    $('#kt_modal_template_mng').modal('hide');

                    /* 재조회 */
                    f_smsMng_sms_search();
                }
            }
        });
    }
}

function f_sms_template_add_valid(){
    let templateSeq = $('#template_list').val();
    let templateTitle = $('#template_title').val();
    let templateContent = $('#template_content').val();

    if(nvl(templateSeq,'') !== ''){
        if(templateSeq === '신규등록'){
            if(nvl(templateTitle,'') === ''){
                showMessage('', 'error', '[등록 정보]', '템플릿명을 입력해 주세요.', ''); return false;
            }
            if(nvl(templateContent,'') === ''){
                showMessage('', 'error', '[등록 정보]', '템플릿내용을 입력해 주세요.', ''); return false;
            }
        }else{
            if(nvl(templateContent,'') === ''){
                showMessage('', 'error', '[등록 정보]', '템플릿내용을 입력해 주세요.', ''); return false;
            }
        }
    }else{
        if(nvl(templateTitle,'') === ''){
            showMessage('', 'error', '[등록 정보]', '템플릿명을 입력해 주세요.', ''); return false;
        }
        if(nvl(templateContent,'') === ''){
            showMessage('', 'error', '[등록 정보]', '템플릿내용을 입력해 주세요.', ''); return false;
        }
    }

    return true;
}

function f_sms_template_delete_btn(){
    let seq = $('#template_list option:selected').val();

    if(nvl(seq, '') !== '' && seq !== '신규등록'){
        Swal.fire({
            title: '선택한 템플릿 정보를 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/smsMng/sms/template/delete.do', 'post', {seq: seq});

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '템플릿 정보 삭제', '템플릿 정보가 삭제되었습니다.', '');

                    $('#kt_modal_template_mng').modal('hide');

                } else {
                    showMessage('', 'error', '에러 발생', '템플릿 정보 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }else{
        showMessage('', 'info', '템플릿 정보 삭제', '등록된 템플릿 정보만 삭제 가능합니다.', '');
    }
}

function f_sms_phone_add_btn(){
    let senderPhoneListBox = $('.senderPhoneList:first').clone(true, true);
    senderPhoneListBox.find('input[type="text"]').val('');
    senderPhoneListBox.find('.senderPhoneDelBtn').show();
    $('.senderPhoneList:last').after(senderPhoneListBox);
}

function f_sms_phone_remove_btn(el){
    let senderPhoneList = $('.senderPhoneList');
    if(senderPhoneList.length > 1){
        $(el).closest('.senderPhoneList').remove();
    }
}

function templateByteChk(content){
    let temp_str = content.value;
    let remain = document.getElementById("templateRemain");

    remain.innerText = String(90 - getByte(temp_str));
    //남은 바이트수를 표시 하기
    if(remain.innerText < 0) {
        alert("템플릿 내용은 " + 90 + " Byte 를 초과할 수 없습니다.");

        while(remain.innerText < 0) {
            temp_str = temp_str.substring(0, temp_str.length-1);
            content.value = temp_str;
            remain.innerText = String(90 - getByte(temp_str));
        }

        content.focus();
    }

}

function smsByteChk(content){
    let temp_str = content.value;
    let remain = document.getElementById("smsRemain");

    remain.innerText = String(90 - getByte(temp_str));
    //남은 바이트수를 표시 하기
    if(remain.innerText < 0) {
        alert("SMS 내용은 " + 90 + " Byte 를 초과할 수 없습니다.");

        while(remain.innerText < 0) {
            temp_str = temp_str.substring(0, temp_str.length-1);
            content.value = temp_str;
            remain.innerText = String(90 - getByte(temp_str));
        }

        content.focus();
    }

}

function getByte(str){
    let resultSize = 0;
    if(str == null) {
        return 0;
    }

    for(let i=0; i<str.length; i++) {
        let c = escape(str.charAt(i));
        if(c.length === 1)//기본 아스키코드
        {
            resultSize ++;
        }
        else if(c.indexOf("%u") !== -1)//한글 혹은 기타
        {
            resultSize += 2;
        }
        else
        {
            resultSize ++;
        }
    }

    return resultSize;
}