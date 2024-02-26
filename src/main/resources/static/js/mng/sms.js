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

    $('.sms_template_list').on('change', function () {
        let selectedOption = $(this).val();
        let smsTemplateTitle = $('.sms_template_title');
        let smsTemplateContent = $('.sms_template_content');

        smsTemplateTitle.val('');
        smsTemplateContent.val('');
        let remain = $('.smsRemain:visible');
        remain.text(String(90));

        if (selectedOption === '미사용') {
            smsTemplateTitle.prop('disabled', true).val('');
        } else {
            smsTemplateTitle.prop('disabled', true).val('');
            let templateSeq = $('.sms_template_list').val();
            if(nvl(templateSeq,'') !== '' && templateSeq !== '미사용'){
                let resData = ajaxConnect('/mng/smsMng/sms/template/selectSingle.do', 'post', {seq:templateSeq});
                smsTemplateTitle.val(resData.title);
                smsTemplateContent.val(resData.content);

                let temp_str = resData.content;
                remain.innerText = String(90 - getByte(temp_str));
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
                break;
            case 'MEMBER':
                $('#sms_send_form_train').hide();
                $('#sms_send_form_member').show();
                $('#sms_send_form_excel').hide();
                break;
            case 'EXCEL':
                $('#sms_send_form_train').hide();
                $('#sms_send_form_member').hide();
                $('#sms_send_form_excel').show();
                break;
            default:
                break;
        }

        f_sms_form_init('all');
    });

    $(document).on('change', '.sms_send_tr_all_check', function () {
        $('#sms_phone_train_list').html('');
        if($(this).is(':checked')){
            $('.select_cnt').text($('.sms_send_tr_check').length);
            $('.sms_send_tr_check').each(function(){
                let input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'sendPhone';
                input.value = $(this).val();
                $('#sms_phone_train_list').append(input);
            });
        }else{
            $('.select_cnt').text('0');
        }
    })

    $(document).on('change', '.sms_send_tr_check', function () {
        let selectCnt = 0;
        $('#sms_phone_train_list').html('');
        $('.sms_send_tr_check').each(function(){
            if($(this).is(':checked')){
                selectCnt++;

                let input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'sendPhone';
                input.value = $(this).val();
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
    })

    $(document).on('change', '.sms_send_ex_all_check', function () {
        $('#sms_phone_excel_list').html('');
        if($(this).is(':checked')){
            $('.select_cnt').text($('.sms_send_ex_check').length);
            $('.sms_send_ex_check').each(function(){
                let input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'sendPhone';
                input.value = $(this).val();
                $('#sms_phone_excel_list').append(input);
            });
        }else{
            $('.select_cnt').text('0');
        }
    })

    $(document).on('change', '.sms_send_ex_check', function () {
        let selectCnt = 0;
        $('#sms_phone_excel_list').html('');
        $('.sms_send_ex_check').each(function(){
            if($(this).is(':checked')){
                selectCnt++;

                let input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'sendPhone';
                input.value = $(this).val();
                $('#sms_phone_excel_list').append(input);
            }
        });

        let checkboxCnt = $('.sms_send_ex_check').length;
        if(selectCnt === checkboxCnt){
            $('.sms_send_ex_all_check').prop('checked',true);
        }else{
            $('.sms_send_ex_all_check').prop('checked',false);
        }
        $('.select_cnt').text(selectCnt);
    })

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
            $('.sms_template_list').children('option:not(:lt(2))').remove();

            for(let i=0; i<resData.length; i++) {
                $('.sms_template_list').append('<option value=' + resData[i].seq +'>' + resData[i].title +'</option>');
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
                let senderPhoneList = '';
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
                        sendResult = '실패';
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
                }
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

function smsByteChk(el){
    let temp_str = $(el).val();
    let remain = $(el).parent().siblings('label').find('span');

    remain.text(String(90 - getByte(temp_str)));
    //남은 바이트수를 표시 하기
    if(remain.text() < 0) {
        alert("SMS 내용은 " + 90 + " Byte 를 초과할 수 없습니다.");

        while(remain.text() < 0) {
            temp_str = temp_str.substring(0, temp_str.length-1);
            $(el).text(temp_str);
            remain.text(String(90 - getByte(temp_str)));
        }

        $(el).focus();
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

function makeExcelJsonFormat(data){
    let parseData = JSON.parse(JSON.stringify(data));

    return {
        rownum: replaceText(parseData[0]), //연번
        name: replaceText(parseData[1]), //이름
        phone: replaceText(parseData[2]), //연락처
        grade: replaceText(parseData[3]), //등급
        trainName: replaceText(parseData[4]) //교육명
    };
}

function excelUpload(){
    /* 테이블 데이터 지우기 */
    let dataTbl = $('#sms_send_form_excel_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    let input = document.querySelector('#excel_file');
    let reader = new FileReader();
    reader.onload = function() {
        let fdata = reader.result;
        let read_buffer = XLSX.read(fdata, {type : 'binary'});
        read_buffer.SheetNames.forEach(function(sheetName) {
            let rowdata =XLSX.utils.sheet_to_json(read_buffer.Sheets[sheetName],{defval:''}); // Excel 입력 데이터
            //console.log('excel all : ' + JSON.stringify(rowdata));
            //console.log(rowdata[100]==null); //true

            if(rowdata.length > 2){
                for(let i=2; i<rowdata.length; i++){

                    let dataArr = [];

                    JSON.parse(JSON.stringify(rowdata[i]), (key, value) => {
                        dataArr.push(value);
                    });

                    let dataRow = makeExcelJsonFormat(dataArr);
                    dataTbl.row.add(dataRow).draw();
                }

                let div = document.createElement('div');
                div.classList.add('col-lg-12');
                div.classList.add('d-flex');
                div.classList.add('align-items-center');
                dataTbl.columns().every(function (e) {
                    if(e === 4 || e === 5){
                        let column = this;

                        // Create select element
                        let select = document.createElement('select');
                        if( e === 4 ){
                            select.id = 'select_grade';
                            select.classList.add('form-select');
                            select.classList.add('form-select-solid');
                            select.classList.add('me-3');
                            /*select.setAttribute('data-control','select2');
                            select.setAttribute('data-hide-search','true');
                            select.setAttribute('data-allow-clear','true');
                            select.setAttribute('data-placeholder','- 등급 -');*/
                            select.add(new Option('등급','',true, true));
                        }else if( e === 5 ){
                            select.id = 'select_train';
                            select.classList.add('form-select');
                            select.classList.add('form-select-solid');
                            /*select.setAttribute('data-control','select2');
                            select.setAttribute('data-hide-search','true');
                            select.setAttribute('data-allow-clear','true');
                            select.setAttribute('data-placeholder','- 교육명 -');*/
                            select.add(new Option('교육명','',true, true));
                        }

                        div.append(select);
                        $('#sms_send_form_excel_table').before(div);

                        if( e === 4 ){
                            //document.getElementById('select_grade').options[0].disabled = true;
                            // Apply listener for user change in value

                            $('#select_grade').on('change', function () {
                                let val = DataTable.util.escapeRegex(this.value);
                                console.log(val);
                                dataTbl.columns(4).search(val).draw();
                            });
                        }else if( e === 5 ){
                            //document.getElementById('select_train').options[0].disabled = true;
                            $('#select_train').on('change', function () {
                                let val = DataTable.util.escapeRegex(this.value);
                                console.log(val);
                                dataTbl.columns(5).search(val).draw();
                            });
                        }

                        // Add list of options
                        column.data().unique().sort().each(function (d, j) {
                            /*if(j===0){
                                select.add(new Option());
                            }*/
                            select.add(new Option(d));
                        });
                    }
                });

            }else{
                alert('첨부된 엑셀파일에 데이터가 없습니다.');
            }
        });
        /* 조회 카운트 입력 */
        $('span.search_cnt:visible').text(dataTbl.data().length);

        /* modal close fn */
        f_modal_close('kt_modal_excel_upload');

        let jb = $('#sms_send_form_excel_table tbody td');
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
    modal_backdrop_el.remove();

    /* modal close */
    modalClose('excel');

}

function f_sms_form_init(gbn){
    $('input[type=text]').val('');
    $('textarea').val('');
    $('input[type=checkbox]').prop('checked',false);
    $('span.smsRemain:visible').text('90');
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
    }else if(gbn === 'train'){
        $('#sms_phone_train_list').html('');
    }

}