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
            $('#smsType').text('단문 (SMS)');
            $('#templateRemain').text('0');
        });

        myModalEl.addEventListener('show.bs.modal', event => {
            f_sms_template_list_set('T');
        });

        $('#template_mng_btn').on('click', function(){
            myModal.show();
        });

    };//myModalEl

    $('#template_list').on('change', function () {
        let selectedOption = $(this).val();
        let templateTitle = $('#template_title');
        let templateContent = $('#template_content');

        templateTitle.val('');
        templateContent.val('');

        let remain = document.getElementById("templateRemain");
        remain.innerText = String(0);

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
                remain.innerText = String(getByte(temp_str));
            }
        }
    });

    $('.sms_template_list').on('change', function () {
        let selectedOption = $(this).val();
        let smsTemplateTitle = $('.sms_template_title:visible');
        let smsTemplateContent = $('.sms_template_content:visible');

        smsTemplateTitle.val('');
        smsTemplateContent.val('');
        $('.smsType:visible').text('단문 (SMS)');
        let remain = $('.smsRemain:visible');
        remain.text(String(0));

        if (selectedOption === '미사용') {
            smsTemplateTitle.prop('disabled', true).val('');
        } else {
            smsTemplateTitle.prop('disabled', true).val($(this).text());
            let templateSeq = $('.sms_template_list:visible').val();
            if(nvl(templateSeq,'') !== '' && templateSeq !== '미사용'){
                let resData = ajaxConnect('/mng/smsMng/sms/template/selectSingle.do', 'post', {seq:templateSeq});
                smsTemplateTitle.val(resData.title);
                smsTemplateContent.val(resData.content);

                let temp_str = resData.content;
                remain.text(String(getByte(temp_str)));
            }
        }
    });

    $('#sms_send_form_train').hide();
    $('#sms_send_form_member').hide();
    $('#sms_send_form_excel').hide();
    $('#sms_gbn_content_select').on('change',function(){
        let selectOptionVal = $(this).val();
        switch (selectOptionVal){
            case 'TRAIN':
                $('#sms_send_form_train').show();
                $('#sms_send_form_member').hide();
                $('#sms_send_form_excel').hide();
                f_sms_template_list_set('');
                break;
            case 'MEMBER':
                $('#sms_send_form_train').hide();
                $('#sms_send_form_member').show();
                $('#sms_send_form_excel').hide();
                f_sms_template_list_set('');
                break;
            case 'EXCEL':
                $('#sms_send_form_train').hide();
                $('#sms_send_form_member').hide();
                $('#sms_send_form_excel').show();
                f_sms_template_list_set('');
                break;
            default:
                break;
        }

        f_sms_form_init('all');
    });

    $(document).on('change', '.sms_send_tr_all_check', function () {
        $('#sms_phone_train_list').html('');
        if($(this).is(':checked')){
            let dataTbl = $('#sms_send_form_train_table').DataTable();
            let checkCnt = 0;
            dataTbl.rows().every(function (rowIdx, tableLoop, rowLoop) {
                let node = this.node();
                let $check = $(node).find('.sms_send_tr_check');
                if($check.is(':checked')){
                    let data = this.data();
                    // data.phone (phone 컬럼명 확인 필요)
                    let phone = data.phone; // data.phone이 아닐 경우 data 객체 확인 후 수정
                    let input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'sendPhone';
                    input.value = phone;
                    $('#sms_phone_train_list').append(input);
                    checkCnt++;
                }
            });
            $('.select_cnt').text(checkCnt);
        }else{
            $('.select_cnt').text('0');
        }
    });

    $(document).on('change', '.sms_send_tr_check', function () {
        let selectCnt = 0;
        $('#sms_phone_train_list').html('');

        let dataTbl = $('#sms_send_form_train_table').DataTable();
        dataTbl.rows().every(function (rowIdx, tableLoop, rowLoop) {
            let node = this.node();
            let $check = $(node).find('.sms_send_tr_check');

            if($check.is(':checked')){
                selectCnt++;
                let data = this.data();
                // data.phone (phone 컬럼명 확인 필요)
                let phone = data.phone; // data.phone이 아닐 경우 data 객체 확인 후 수정
                let input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'sendPhone';
                input.value = phone;
                $('#sms_phone_train_list').append(input);
            }
        });

        let checkboxCnt = $('.sms_send_tr_check').length;
        if(selectCnt === checkboxCnt){
            $('.sms_send_tr_all_check').prop('checked',true);
        }else{
            $('.sms_send_tr_all_check').prop('checked',false);
        }
        $('.select_cnt').text(selectCnt);
    });

    $(document).on('change', '.sms_send_ex_all_check', function () {
        $('#sms_phone_excel_list').html('');
        if($(this).is(':checked')){
            let dataTbl = $('#sms_send_form_excel_table').DataTable();
            let checkCnt = 0;
            // dataTable의 모든 행을 순회
            dataTbl.rows().every(function (rowIdx, tableLoop, rowLoop) {
                let node = this.node();
                // [수정] 비활성화되지 않은 체크박스만 대상으로 함
                let $check = $(node).find('.sms_send_ex_check:enabled');

                if($check.is(':checked')){ // '전체선택'으로 인해 체크된 상태
                    let data = this.data();
                    if(data && data.phone){ // 'phone_send'에 유효한 값이 있는지 확인
                        let input = document.createElement('input');
                        input.type = 'hidden';
                        input.name = 'sendPhone';
                        input.value = data.phone; // data 객체에서 'phone_send' 값 사용
                        $('#sms_phone_excel_list').append(input);
                        checkCnt++;
                    }
                }
            });
            $('.select_cnt').text(checkCnt);
        }else{
            $('.select_cnt').text('0');
        }
    });

    $(document).on('change', '.sms_send_ex_check', function () {
        let selectCnt = 0;
        $('#sms_phone_excel_list').html('');

        let dataTbl = $('#sms_send_form_excel_table').DataTable();
        // dataTable의 모든 행을 순회하며 체크된 항목 찾기
        dataTbl.rows().every(function (rowIdx, tableLoop, rowLoop) {
            let node = this.node();
            // [수정] 비활성화되지 않은 체크박스만 대상으로 함
            let $check = $(node).find('.sms_send_ex_check:enabled');

            if($check.is(':checked')){
                selectCnt++;
                let data = this.data(); // 현재 행의 데이터 객체
                if(data && data.phone){ // 'phone_send'에 유효한 값이 있는지 확인
                    let input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'sendPhone';
                    input.value = data.phone; // data 객체에서 'phone_send' 값 사용
                    $('#sms_phone_excel_list').append(input);
                }
            }
        });

        // [수정] 전체 체크박스 개수는 비활성화된 것을 제외하고 계산
        let checkboxCnt = $('.sms_send_ex_check:enabled').length;
        if(selectCnt === checkboxCnt && checkboxCnt > 0){
            $('.sms_send_ex_all_check').prop('checked',true);
        }else{
            $('.sms_send_ex_all_check').prop('checked',false);
        }
        $('.select_cnt').text(selectCnt);
    });

    let toolTipTxt = '';
    toolTipTxt += '교육명 : %eduName%';
    toolTipTxt += '<br>';
    toolTipTxt += '교육차시 : %eduTime%';
    toolTipTxt += '<br>';
    toolTipTxt += '교육일시 : %eduDate%';
    toolTipTxt += '<br>';
    toolTipTxt += '교육비 : %eduPrice%';
    toolTipTxt += '<br>';
    toolTipTxt += '가상계좌은행 : %bank%';
    toolTipTxt += '<br>';
    toolTipTxt += '가상계좌번호 : %vacctNum%';
    toolTipTxt += '<br>';
    toolTipTxt += '가상계좌주 : %vacctName%';
    toolTipTxt += '<br>';
    toolTipTxt += '가상계좌입금기한 : %vacctLimit%';
    toolTipTxt += '<br>';
    toolTipTxt += '키워드 : %keyword%';

    let options = {
        html: true,
        container: 'body',
        trigger: 'click',
        content: function () {
            return toolTipTxt;
        }

    };
    $('.replaceTooltip').popover(options);

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
            let newOption = new Option('미사용', '미사용', false, false);
            $('.sms_template_list:visible').append(newOption);

            for(let i=0; i<resData.length; i++) {
                $('.sms_template_list:visible').append('<option value=' + resData[i].seq + '>' + resData[i].title +'</option>').trigger('change');
            }

            $('.sms_template_list:visible').val('').select2({minimumResultsForSearch: Infinity});
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

    let result = $('#condition_result option:selected').val();
    if(nullToEmpty(searchText) === ""){
        jsonObj = {
            result: result
        };
    }else{
        jsonObj = {
            result: result ,
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
    $('#condition_result').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_smsMng_sms_search();
}

function f_smsMng_sms_modify_init_set(seq){
    window.location.href = '/mng/smsMng/sms/detail.do?seq=' + seq;
}

function f_smsMng_sms_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        Swal.fire({
            title: "[삭제 사유]",
            text: "사유 입력 후 삭제하기 버튼 클릭 시 데이터는 파일관리>임시휴지통 으로 이동됩니다.",
            input: 'text',
            inputPlaceholder: '삭제 사유를 입력해주세요.',
            width: '70em',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                if (result.value) {

                    let jsonObj = {
                        targetSeq: seq,
                        targetTable: 'sms',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_smsMng_sms_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_smsMng_sms_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_smsMng_sms_send(gbn){
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
            let validCheck = false;
            if(gbn === 'train') {
                validCheck = f_smsMng_sms_train_valid();
            }else if(gbn === 'member') {
                validCheck = f_smsMng_sms_member_valid();
            }else if(gbn === 'excel'){
                validCheck = f_smsMng_sms_excel_valid();
            }

            if(validCheck) {

                // [수정] gbn === 'excel' 인 경우, 서버에서 처리하도록 로직 변경
                if(gbn === 'excel') {
                    let senderPhoneList = [];
                    // 숨겨진 input에서 전화번호 목록을 배열로 가져옴
                    $('#sms_phone_excel_list').find('input[type=hidden]').each(function() {
                        senderPhoneList.push($(this).val());
                    });

                    let contentVal = $('#sms_template_excel_content').val();
                    let templateSeq = $('.sms_template_list option:selected').val();

                    // 발송 버튼 비활성화 (중복 클릭 방지)
                    let sendButton = $(event.target); // 확인 필요. 이 이벤트가 아닐 수 있음.
                    sendButton.prop('disabled', true);

                    Swal.fire({
                        title: 'SMS 발송 요청 중...',
                        text: '대량 발송 작업이 서버에 등록 중입니다. 잠시만 기다려주세요.',
                        allowOutsideClick: false,
                        didOpen: () => {
                            Swal.showLoading();
                        }
                    });

                    // [신규] 서버 일괄 처리 API 호출
                    $.ajax({
                        url: '/mng/smsMng/sms/sendBulk.do', // [주의] 신규로 만들어야 하는 서버 API 주소
                        type: 'POST',
                        contentType: 'application/json', // JSON으로 전송
                        data: JSON.stringify({
                            phoneList: senderPhoneList, // 전화번호 리스트
                            content: contentVal,          // 문자 내용
                            templateSeq: templateSeq,     // 템플릿 번호
                            sender: '1811-7891'           // 하드코딩된 발신번호 (서버에서 처리하는 것이 더 좋음)
                        }),
                        success: function(resData) {
                            if (resData.result_code === 1 || resData.resultCode === "0") { // 서버 응답에 따라 성공 조건 확인
                                Swal.fire({
                                    title: '[SMS 발송 요청 완료]',
                                    html: '서버에 발송 작업이 등록되었습니다.<br>발송결과는 SMS 발송 관리 페이지에서 확인하실 수 있습니다.',
                                    icon: 'success',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/smsMng/sms.do';
                                    }
                                });
                            } else {
                                Swal.fire(
                                    '발송 요청 실패',
                                    '서버에 발송 작업을 등록하지 못했습니다: ' + resData.message,
                                    'error'
                                );
                            }
                        },
                        error: function(xhr, status, error) {
                            Swal.fire(
                                '네트워크 오류',
                                '서버와 통신 중 오류가 발생했습니다: ' + error,
                                'error'
                            );
                        },
                        complete: function() {
                            sendButton.prop('disabled', false); // 발송 버튼 활성화
                        }
                    });

                } else {
                    // gbn이 'train' 또는 'member'인 경우 (기존 로직 유지 - 단, 이 경우도 건수가 많다면 서버 처리 방식 권장)
                    let senderPhoneList = '';
                    let contentVal = '';
                    if(gbn === 'train') {
                        senderPhoneList = $('#sms_phone_train_list').find('input[type=hidden]');
                        contentVal = $('#sms_template_train_content').val();
                    }else if(gbn === 'member') {
                        senderPhoneList = $('.senderPhoneList').find('input[type=text]');
                        contentVal = $('#sms_template_member_content').val();
                    }

                    for (let i = 0; i < senderPhoneList.length; i++) {
                        let phone = senderPhoneList.eq(i).val();
                        let content = contentVal;

                        let jsonObj = {
                            sender: '1811-7891', //해양레저인력양성센터
                            phone: phone,
                            content: content
                        }

                        let sendResult = '성공';
                        let resData = ajaxConnect('/sms/send.do', 'post', jsonObj);
                        if (resData.result_code !== 1) {
                            sendResult = '실패' + ' [' + resData.message +']';
                        }

                        let jsonObj2 = {
                            smsGroup: getCurrentDate().substring(0, getCurrentDate().length-2),
                            phone: phone,
                            sender: '관리자',
                            senderPhone: '1811-7891', //해양레저인력양성센터
                            content: content,
                            sendResult: sendResult,
                            templateSeq: $('.sms_template_list option:selected').val(),
                        }

                        let resData2 = ajaxConnect('/mng/smsMng/sms/insert.do', 'post', jsonObj2);

                        if((i+1) === senderPhoneList.length){
                            Swal.fire({
                                title: '[SMS 발송완료]',
                                html: '발송결과는 SMS 관리>SMS 발송 관리 페이지에서 확인하실 수 있습니다.',
                                icon: 'info',
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: '확인'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    window.location.href = '/mng/smsMng/sms.do';
                                }
                            });
                        }
                    } // end for
                } // end else (gbn !== 'excel')

                /*let senderPhoneList = '';
                let contentVal = '';
                if(gbn === 'train') {
                    senderPhoneList = $('#sms_phone_train_list').find('input[type=hidden]');
                    contentVal = $('#sms_template_train_content').val();
                }else if(gbn === 'member') {
                    senderPhoneList = $('.senderPhoneList').find('input[type=text]');
                    contentVal = $('#sms_template_member_content').val();
                }else if(gbn === 'excel'){
                    senderPhoneList = $('#sms_phone_excel_list').find('input[type=hidden]');
                    contentVal = $('#sms_template_excel_content').val();
                }

                for (let i = 0; i < senderPhoneList.length; i++) {
                    let phone = senderPhoneList.eq(i).val();
                    let content = contentVal;

                    let jsonObj = {
                        sender: '1811-7891', //해양레저인력양성센터
                        phone: phone,
                        content: content
                    }

                    let sendResult = '성공';
                    let resData = ajaxConnect('/sms/send.do', 'post', jsonObj);
                    if (resData.result_code !== 1) {
                        sendResult = '실패' + ' [' + resData.message +']';
                    }

                    let jsonObj2 = {
                        smsGroup: getCurrentDate().substring(0, getCurrentDate().length-2),
                        phone: phone,
                        sender: '관리자',
                        senderPhone: '1811-7891', //해양레저인력양성센터
                        content: content,
                        sendResult: sendResult,
                        templateSeq: $('.sms_template_list option:selected').val(),
                    }

                    let resData2 = ajaxConnect('/mng/smsMng/sms/insert.do', 'post', jsonObj2);

                    if((i+1) === senderPhoneList.length){
                        Swal.fire({
                            title: '[SMS 발송완료]',
                            html: '발송결과는 SMS 관리>SMS 발송 관리 페이지에서 확인하실 수 있습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = '/mng/smsMng/sms.do';
                            }
                        });
                    }
                }*/
            }

        }
    });

}

function f_smsMng_sms_train_valid(){
    let senderPhoneList = $('#sms_phone_train_list').find('input[type=hidden]');
    if(senderPhoneList.length === 0) { showMessage('', 'error', '[SMS 발송 정보]', 'SMS 발송 대상을 선택해 주세요.', ''); return false; }

    let content = $('#sms_template_train_content').val();
    if(nvl(content,'') === '') { showMessage('', 'error', '[SMS 발송 정보]', 'SMS 발송 내용을 입력해 주세요.', ''); return false; }

    return true;
}

function f_smsMng_sms_member_valid(){
    let senderPhoneList = $('.senderPhoneList').find('input[type=text]');
    for (let i = 0; i < senderPhoneList.length; i++) {
        if(nvl(senderPhoneList.eq(i).val(),'') === '') { showMessage('', 'error', '[SMS 발송 정보]', '연락처가 입력되지 않은 항목이 있습니다.', ''); return false; }
    }

    let content = $('#sms_template_member_content').val();
    if(nvl(content,'') === '') { showMessage('', 'error', '[SMS 발송 정보]', 'SMS 발송 내용을 입력해 주세요.', ''); return false; }

    return true;
}

function f_smsMng_sms_excel_valid(){
    let senderPhoneList = $('#sms_phone_excel_list').find('input[type=hidden]');
    if(senderPhoneList.length === 0) { showMessage('', 'error', '[SMS 발송 정보]', 'SMS 발송 대상을 선택해 주세요.', ''); return false; }

    let content = $('#sms_template_excel_content').val();
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

function f_train_gbn_sms_yn_target_list(el){

    f_sms_form_init('train');

    let selOptVal = $(el).val();

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#sms_send_form_train_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj = {
        condition: selOptVal
    }

    let resData = ajaxConnect('/mng/smsMng/sms/send/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    $('span.search_cnt:visible').text(resData.length);

    /* DataTable Column tooltip Set */
    let jb = $('#sms_send_form_train_table tbody td');
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
    let remain = $('#templateRemain:visible');
    let smsType = $('#smsType:visible');

    remain.text(String(getByte(temp_str)));
    if(Number.parseInt(remain.text()) <= 90){
        smsType.text('단문 (SMS)');
    }else if(Number.parseInt(remain.text()) > 90){
        smsType.text('장문 (LMS)');
    }
}

function smsByteChk(el){
    let temp_str = $(el).val();
    let remain = $(el).parent().siblings('label').find('span');
    let smsType = $('.smsType:visible');

    remain.text(String(getByte(temp_str)));
    //남은 바이트수를 표시 하기
    if(Number.parseInt(remain.text()) <= 90){
        smsType.text('단문 (SMS)');
    }else if(Number.parseInt(remain.text()) > 90){
        smsType.text('장문 (LMS)');
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

function excelUpload(){
    /* 테이블 데이터 지우기 */
    let dataTbl = $('#sms_send_form_excel_table').DataTable();
    dataTbl.clear();

    // [신규] 기존에 생성된 필터 select box 제거 (중복 생성 방지)
    let existingFilters = $('#sms_send_form_excel_table').prev('div.col-lg-12.d-flex');
    if (existingFilters.length > 0) {
        existingFilters.remove();
    }

    dataTbl.draw(false);

    let input = document.querySelector('#excel_file');
    let reader = new FileReader();
    reader.onload = function() {
        let fdata = reader.result;
        let read_buffer = XLSX.read(fdata, {type : 'binary'});
        read_buffer.SheetNames.forEach(function(sheetName) {

            let sheet = read_buffer.Sheets[sheetName];
            let range = XLSX.utils.decode_range(sheet['!ref']);

            // [수정] 3번째 행(인덱스 2)을 헤더로, 4번째 행(인덱스 3)을 데이터 시작으로 설정
            range.s.r = 2; // 0-indexed start row (Header)

            let new_range_str = XLSX.utils.encode_range(range);

            let rowdata = XLSX.utils.sheet_to_json(sheet, {
                defval:'',
                range: new_range_str // 3번째 행부터 시작
            });

            let validRowCount = 0;
            let invalidRowCount = 0;

            if(rowdata.length > 0){
                for(let i=0; i<rowdata.length; i++){
                    let row = rowdata[i];

                    let rawPhone = String(row['연락처'] || '');
                    let name = String(row['이름'] || '');

                    // [신규] 유효성 검사 및 포맷팅 로직
                    let isValid = isValidPhoneNumber(rawPhone);
                    let displayPhone = '';  // 테이블에 표시될 값
                    let sendablePhone = null; // 실제 발송에 사용될 값 (유효할 때만)

                    if (isValid) {
                        // 유효한 경우: 하이픈 포맷 적용
                        displayPhone = formatPhoneNumber(rawPhone);
                        // 발송용 데이터는 하이픈 제거
                        sendablePhone = displayPhone.replace(/-/g, '');
                        validRowCount++;
                    } else {
                        // 유효하지 않은 경우: 오류 메시지 표시
                        // 텍스트가 비어있으면(공란) 오류로 표시하지 않고 그냥 비워둠
                        if (rawPhone.trim() !== '') {
                            displayPhone = `<span class="text-danger" data-bs-toggle="tooltip" title="유효하지 않은 번호">[오류] ${rawPhone}</span>`;
                            invalidRowCount++;
                        } else {
                            displayPhone = ''; // 공란은 그냥 비워둠
                        }
                        sendablePhone = null; // 발송 불가
                    }

                    let dataRow = {
                        rownum: String(row['연번'] || (i + 1)),
                        name: name,
                        phone: displayPhone, // [수정] '연락처' 컬럼에 표시될 값
                        grade: String(row['등급'] || ''),
                        trainName: String(row['교육명'] || ''),
                        phone_send: sendablePhone // [신규] 발송 시 사용할 숨겨진 데이터
                    };

                    let addedRow = dataTbl.row.add(dataRow);

                    // [신규] 유효하지 않은 번호(오류 또는 공란)는 체크박스 비활성화
                    if (!isValid) {
                        let rowNode = addedRow.node();
                        $(rowNode).find('input.sms_send_ex_check').prop('disabled', true);
                    }
                } // end for

                dataTbl.draw(); // 데이터 추가 후 테이블 다시 그리기

                if(invalidRowCount > 0) {
                    alert(`총 ${rowdata.length}건의 데이터 중 ${invalidRowCount}건의 유효하지 않은 연락처 정보를 확인했습니다. 해당 항목은 발송 대상에서 제외됩니다.`);
                }

                // ... (필터 생성 로직 - 원본 코드 유지) ...
                let div = document.createElement('div');
                div.classList.add('col-lg-12');
                div.classList.add('d-flex');
                div.classList.add('align-items-center');
                dataTbl.columns().every(function (e) {
                    // 등급: 4, 교육명: 5
                    if(e === 4 || e === 5){
                        let column = this;
                        let select = document.createElement('select');
                        if( e === 4 ){
                            select.id = 'select_grade';
                            select.classList.add('form-select');
                            select.classList.add('form-select-solid');
                            select.classList.add('me-3');
                            select.add(new Option('등급','',true, true));
                        }else if( e === 5 ){
                            select.id = 'select_train';
                            select.classList.add('form-select');
                            select.classList.add('form-select-solid');
                            select.add(new Option('교육명','',true, true));
                        }

                        div.append(select);
                        $('#sms_send_form_excel_table').before(div);

                        if( e === 4 ){
                            $('#select_grade').on('change', function () {
                                let val = DataTable.util.escapeRegex(this.value);
                                dataTbl.columns(4).search(val).draw();
                            });
                        }else if( e === 5 ){
                            $('#select_train').on('change', function () {
                                let val = DataTable.util.escapeRegex(this.value);
                                dataTbl.columns(5).search(val).draw();
                            });
                        }

                        column.data().unique().sort().each(function (d, j) {
                            select.add(new Option(d));
                        });
                    }
                });

            }else{
                alert('첨부된 엑셀파일에 헤더 행(3행)을 제외한 데이터가 없습니다.');
            }
        });
        /* 조회 카운트 입력 */
        // [수정] 유효한 건수(validRowCount)가 아닌, 테이블에 표시된 총 건수
        $('span.search_cnt:visible').text(dataTbl.data().length);

        /* modal close fn */
        f_modal_close('kt_modal_excel_upload');

        let jb = $('#sms_send_form_excel_table tbody td');
        let cnt = 0;
        jb.each(function(index, item){
            let itemText = $(item).text();
            let itemText_trim = itemText.replaceAll(' ','');
            if(itemText_trim !== '' && !itemText.match('Actions')){
                // [수정] 오류 SPAN이 HTML로 들어가므로, text()가 아닌 html()로 title 생성
                let titleText = $(item).html();
                $(item).attr('data-bs-toggle', 'tooltip');
                $(item).attr('data-bs-trigger', 'hover');
                $(item).attr('data-bs-custom-class', 'tooltip-inverse');
                $(item).attr('data-bs-placement', 'top');
                $(item).attr('title', titleText);

                // [신규] 오류 메시지 SPAN에 개별 툴팁 적용
                $(item).find('[data-bs-toggle="tooltip"]').tooltip();
            }
            cnt++;
        })
        jb.tooltip();
    }
    reader.readAsBinaryString(input.files[0]);
}

function f_modal_close(target_modal_id){
    /* modal close */
    // open : className="app-default modal-open" style="overflow: hidden; padding-right: 17px;">
    // close : className="app-default" style="">
    let body_el = document.querySelector('body');
    body_el.classList.remove('modal-open');
    body_el.style.removeProperty('overflow');
    body_el.style.removeProperty('padding-right');

    // open : <div className="modal fade show" id="kt_modal_excel_upload" tabIndex="-1" style="display: block;" aria-modal="true" role="dialog">
    // close : <div className="modal fade" id="kt_modal_excel_upload" tabIndex="-1" style="display: none;" aria-hidden="true">
    let modal_el = document.getElementById(target_modal_id);
    modal_el.classList.remove('show');
    modal_el.style.display = 'none';
    modal_el.removeAttribute('aria-modal');
    modal_el.removeAttribute('role');
    modal_el.setAttribute('aria-hidden','true');

    let modal_backdrop_el = document.querySelector('.modal-backdrop');
    if (modal_backdrop_el) {
        modal_backdrop_el.remove();
    }

    /* modal close */
    modalClose('excel');

}

function f_sms_form_init(gbn){
    $('input[type=text]').val('');
    $('textarea').val('');
    $('input[type=checkbox]').prop('checked',false);
    $('span.smsRemain:visible').text('0');
    $('span.smsType:visible').text('단문 (SMS)');
    $('span.search_cnt:visible').text('0');
    $('.select_cnt').text('0');
    $('.sms_template_title').val('');
    $('.sms_template_list').val('').select2({minimumResultsForSearch: Infinity});
    $('.sms_template_content').val('');
    if(gbn === 'all'){
        $('.sms_send_tr_all_check').prop('checked',false);
        $('.sms_send_tr_check').prop('checked',false);
        $('.sms_send_ex_all_check').prop('checked',false);
        $('.sms_send_ex_check').prop('checked',false);

        $('#sms_phone_train_list').html('');
        $('#sms_phone_excel_list').html('');
        $('#form_train_gbn').val('').select2({minimumResultsForSearch: Infinity});

        $('.senderPhoneList').each(function(index, item){
            if(index > 0){
                $(this).remove();
            }
        })

        /* DataTable Data Clear */
        let dataTbl = $('#sms_send_form_train_table').DataTable();
        dataTbl.clear();
        dataTbl.draw(false);
        /* DataTable Data Clear */
        let dataTbl2 = $('#sms_send_form_excel_table').DataTable();
        dataTbl2.clear();
        dataTbl2.draw(false);

        // 엑셀 테이블 상단 필터 제거
        let existingFilters = $('#sms_send_form_excel_table').prev('div.col-lg-12.d-flex');
        if (existingFilters.length > 0) {
            existingFilters.remove();
        }

    }else if(gbn === 'train'){
        $('#sms_phone_train_list').html('');
    }

}

// [신규] 헬퍼 함수 - 유효한 한국 휴대폰 번호인지 (하이픈 무관)
function isValidPhoneNumber(phoneStr) {
    if (!phoneStr) return false;
    // 모든 공백 및 하이픈 제거
    let cleanPhone = String(phoneStr).replace(/[-\s]/g, '');
    // 정규식: 010, 011, 016, 017, 018, 019로 시작하고 10자리 또는 11자리
    return /^(010|01[16-9])\d{7,8}$/.test(cleanPhone);
}

// [신규] 헬퍼 함수 - 유효한 번호를 하이픈 포맷으로 변경
function formatPhoneNumber(phoneStr) {
    if (!phoneStr) return "";
    let cleanPhone = String(phoneStr).replace(/\D/g, ''); // 숫자 외 모두 제거

    if (cleanPhone.length === 10) {
        return cleanPhone.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
    } else if (cleanPhone.length === 11) {
        return cleanPhone.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    } else {
        return phoneStr; // 포맷팅 실패 시 원본 반환
    }
}