/***
 * mng/request/list
 * 개발사>요청사항 & 문의>요청사항 & 문의 관리
 * */

$(function(){
    let myModalEl = document.getElementById('modal_progress_step_status');

    if(myModalEl){

        let myModal = new bootstrap.Modal('#modal_progress_step_status', {
            focus: true
        });

        myModalEl.addEventListener('hidden.bs.modal', event => {
            // input init
            $('.target_list').empty();
            $('#md_progress_step').val('').select2({minimumResultsForSearch: Infinity});
            $('input[type=hidden][name=checkVal]').remove();
        })

        $('#progress_step_btn').on('click', function () {

            let checkbox_el = $('.request_check input[type=checkbox]:checked');
            let checkbox_len = checkbox_el.length;
            let checkbox_data_val = '';
            let checkbox_val = '';
            let checkbox_title_val = '';
            if(checkbox_len !== 0){
                let i = 0;
                $(checkbox_el).each(function() {
                    checkbox_data_val += (i+1) + '. ';
                    checkbox_title_val += $(this).data('value').toString().substring($(this).data('value').indexOf('/')+2);
                    checkbox_data_val += $(this).data('value');
                    checkbox_val += $(this).val();
                    if((i+1) !== checkbox_len){
                        checkbox_data_val += '<br>';
                        checkbox_val += ',';
                        checkbox_title_val += '^';
                    }
                    i++;
                });

                if(nvl(checkbox_val,'') !== ''){
                    let input_hidden = document.createElement('input');
                    input_hidden.type = 'hidden';
                    input_hidden.name = 'checkVal'
                    input_hidden.value = checkbox_val;

                    let input_hidden2 = document.createElement('input');
                    input_hidden2.type = 'hidden';
                    input_hidden2.name = 'checkTitleVal'
                    input_hidden2.value = checkbox_title_val;

                    $('#progressStepModalForm .target_list').html(checkbox_data_val);
                    $('#progressStepModalForm .target_list').append(input_hidden);
                    $('#progressStepModalForm .target_list').append(input_hidden2);

                    myModal.show();
                }
            }else{
                showMessage('', 'error', '[진행 단계 변경]', '진행 단계를 변경할 요청을<br/>하나 이상 선택해 주세요.', '');
                return false;
            }

        })
    }//myModalEl

    let myModalEl2 = document.getElementById('modal_complete_expect_status');

    if(myModalEl2){

        let myModal2 = new bootstrap.Modal('#modal_complete_expect_status', {
            focus: true
        });

        myModalEl2.addEventListener('hidden.bs.modal', event => {
            // input init
            $('.target_list').empty();
            $('#md_complete_expect').val('');
            $('input[type=hidden][name=checkVal]').remove();
        })

        $('#complete_expect_btn').on('click', function () {

            let checkbox_el = $('.request_check input[type=checkbox]:checked');
            let checkbox_len = checkbox_el.length;
            let checkbox_data_val = '';
            let checkbox_val = '';
            if(checkbox_len !== 0){
                let i = 0;
                $(checkbox_el).each(function() {
                    checkbox_data_val += (i+1) + '. ';
                    checkbox_data_val += $(this).data('value');
                    checkbox_val += $(this).val();
                    if((i+1) !== checkbox_len){
                        checkbox_data_val += '<br>';
                        checkbox_val += ',';
                    }
                    i++;
                });

                if(nvl(checkbox_val,'') !== ''){
                    let input_hidden = document.createElement('input');
                    input_hidden.type = 'hidden';
                    input_hidden.name = 'checkVal'
                    input_hidden.value = checkbox_val;

                    $('#completeExpectModalForm .target_list').html(checkbox_data_val);
                    $('#completeExpectModalForm .target_list').append(input_hidden);

                    myModal2.show();
                }
            }else{
                showMessage('', 'error', '[처리 예정 일시 변경]', '처리 예정 일시를 변경할 요청을<br/>하나 이상 선택해 주세요.', '');
                return false;
            }

        })
    }//myModalEl

    //작성일
    let writeDatePicker = $('#writeDate');
    if(writeDatePicker) {
        writeDatePicker.flatpickr({
            enableTime: true,
            locale: "ko",
            dateFormat: "Y-m-d H:i:S",
            minDate: "today"
        });
    }

    //처리예상일시
    let completeExpectDatePicker = $('#md_complete_expect');
    let datePickr;
    if(completeExpectDatePicker) {
        datePickr = completeExpectDatePicker.flatpickr({
            enableTime: true,
            dateFormat: "Y-m-d H:i:S",
            locale: "ko",
            minDate: "today",
            clickOpens: false
        });
    }

    $('#md_complete_expect').on('click', function(){
        datePickr.toggle();
    })

});

function f_request_list_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_request_list_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* 검색조건 */
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let emergencyYnVal = $('#condition_emergency_yn').is(':checked');
    let emergencyYn = 'N';
    if(emergencyYnVal === true){
        emergencyYn = 'Y';
    }
    let gbn = $('#condition_gbn option:selected').val();
    let progressStep = $('#condition_progress_step option:selected').val();

    let jsonObj;
    if(nvl(searchText,'') === ''){
        jsonObj = {
            emergencyYn: emergencyYn,
            gbn: gbn,
            progressStep: progressStep
        };
    }else{
        jsonObj = {
            emergencyYn: emergencyYn,
            gbn: gbn,
            progressStep: progressStep,
            condition: condition,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/request/list/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    $('#search_cnt').text(String(resData.length));

    /* DataTable Column tooltip Set */
    let jb = $('#mng_request_list_table tbody td');
    let cnt = 0;
    jb.each(function(index, item){
        let itemText = $(item).text();
        let itemText_trim = itemText.replaceAll(' ','');
        if(itemText_trim !== '' && !itemText.match('Actions')){
            $(item).attr('data-bs-toggle', 'tooltip');
            $(item).attr('data-bs-trigger', 'hover');
            $(item).attr('data-bs-custom-class', 'tooltip-inverse');
            $(item).attr('data-bs-placement', 'top');
            $(item).attr('title', itemText);
        }
        cnt++;
    })
    jb.tooltip();
}

function f_request_list_detail_set(seq){
    window.location.href = '/mng/request/list/detail.do?seq=' + seq;
}

function f_request_list_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_emergency_yn').prop('checked', false);
    $('#condition_gbn').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_progress_step').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_request_list_search();
}

function f_request_list_remove(seq){
    if(nvl(seq,'') !== ''){
        Swal.fire({
            title: '[ 요청사항 & 문의 ]',
            html: '해당 요청사항 & 문의를 정말로 삭제하시겠습니까?<br/>삭제한 정보는 복구할 수 없습니다.',
            allowOutsideClick: false,
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/request/list/delete.do', 'post', { seq: seq });

                if (resData.resultCode === "0") {

                    Swal.fire({
                        icon: 'info',
                        title: '[ 요청사항 & 문의 ]',
                        html: '<span style="font-size: 1.2em;">요청사항 & 문의가 삭제되었습니다.</span>',
                        allowOutsideClick: false,
                        confirmButtonColor: '#00a8ff',
                        confirmButtonText: '확인'
                    }).then(async (result) => {
                        if (result.isConfirmed) {
                            f_request_list_search(); // 재조회
                        }
                    })

                } else {
                    showMessage('', 'error', '에러 발생', '요청사항 & 문의 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }

            }
        });

    }
}

function f_progress_step_change_modal_btn(){
    let idArr = $('input[type=hidden][name=checkVal]').val();
    if (nvl(idArr,'') !== ''){

        let md_progress_step_val = $('#md_progress_step').val();
        if(nvl(md_progress_step_val,'') !== ''){

            Swal.fire({
                title: '[ 진행 단계 변경 ]',
                html: '진행 단계를 변경하시겠습니까 ?<br>[ ' + md_progress_step_val + ' ]',
                icon: 'info',
                showCancelButton: true,
                allowOutsideClick: false,
                confirmButtonColor: '#3085d6',
                confirmButtonText: '변경',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    let idSplit = idArr.split(',');
                    let jsonArr = [];
                    for(let i=0; i<idSplit.length; i++){
                        let jsonObj = {
                            seq: idSplit[i],
                            progressStep: md_progress_step_val
                        }

                        jsonArr.push(jsonObj);

                    } // for

                    let resData = ajaxConnect('/mng/request/list/progress/step/update.do', 'post', jsonArr);

                    if(resData.resultCode !== "0"){
                        showMessage('', 'error', '에러 발생', '진행 단계 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                        return false;
                    }else{

                        if(md_progress_step_val === '완료'){

                            let titleArr = $('input[type=hidden][name=checkTitleVal]').val();
                            if (nvl(titleArr,'') !== ''){

                                let titleList = titleArr.split('^');
                                for(let i=0; i<titleList.length; i++){
                                    let title = titleList[i];
                                    let subject = '[ 경기해양레저 인력양성센터 ] - 요청사항 & 문의 ( ' + title + ' ) 요청이 처리 완료되었습니다.';
                                    let body = '';
                                    body += '<p><br/></p>';
                                    body += '<p>';
                                        body += '--------------------------------------------------------------------------------------';
                                    body += '</p>';
                                    body += '<p><br/></p>';
                                    body += '<p>';
                                        body += '제목 : ' + title;
                                    body += '</p>';
                                    body += '<p><br/></p>';
                                    body += '<p>';
                                        body += '--------------------------------------------------------------------------------------';
                                    body += '</p>';
                                    body += '<p><br/></p>';
                                    body += '<p>';
                                        body += '요청하신 요청사항 & 문의 게시물의 처리 내용 확인 부탁드립니다.';
                                    body += '</p>';
                                    body += '<p>';
                                        body += '이후 추가 문의나 요청사항이 있으시면 해당 게시판 요청 신규 등록 및 댓글을 이용하여 주시기 바랍니다.';
                                    body += '</p>';
                                    body += '<p>';
                                        body += '감사합니다.';
                                    body += '</p>';
                                    body += '</p>';
                                    body += '<p><br/></p>';
                                    body += '<p>';
                                        body += '<a href="https://edumarine.org/mng/index.do" target="_blank">';
                                            body += '관리자 페이지 바로가기';
                                        body += '</a>';
                                    body += '</p>';

                                    body = body.replaceAll('"','\\\\"');

                                    // 담당자 메일 send
                                    let mailJson = {
                                        subject: subject,
                                        body: body,
                                        note: '개발사'
                                    };
                                    let resData1 = ajaxConnect('/mail/send.do', 'post', requestMakeMailFormat(mailJson));
                                    if (resData1.resultCode !== "0") {
                                        showMessage('', 'error', '에러 발생', '요청사항 & 문의 접수 담당자 메일 전송에 실패하였습니다. 관리자에게 문의해주세요. ' + resData1.resultMessage, '');
                                    }
                                }
                            }
                        }

                        Swal.fire({
                            icon: 'info',
                            title: '[ 진행 단계 변경 ]',
                            html: '<span style="font-size: 1.2em;">진행 단계 변경이 정상 완료되었습니다.</span>',
                            allowOutsideClick: false,
                            confirmButtonColor: '#00a8ff',
                            confirmButtonText: '확인'
                        }).then(async (result) => {
                            if (result.isConfirmed) {
                                $('#modal_progress_step_status').modal('hide');

                                /* 재조회 */
                                f_request_list_search();
                            }
                        })

                    }
                }
            });
        }else{
            showMessage('', 'error', '[ 진행 단계 변경 ]', '변경할 진행 단계를 선택해 주세요.', '');
            return false;
        }
    }
}

function f_complete_expect_change_modal_btn(){
    let idArr = $('input[type=hidden][name=checkVal]').val();
    if (nvl(idArr,'') !== ''){

        let md_complete_expect_val = $('#md_complete_expect').val();
        if(nvl(md_complete_expect_val,'') !== ''){

            Swal.fire({
                title: '[ 처리 예정 일시 변경 ]',
                html: '처리 예정 일시를 변경하시겠습니까 ?<br>[ ' + md_complete_expect_val + ' ]',
                icon: 'info',
                showCancelButton: true,
                allowOutsideClick: false,
                allowEnterKey: true,
                stopKeydownPropagation: false,
                confirmButtonColor: '#3085d6',
                confirmButtonText: '변경',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    let idSplit = idArr.split(',');
                    let jsonArr = [];
                    for(let i=0; i<idSplit.length; i++){
                        let jsonObj = {
                            seq: idSplit[i],
                            completeExpectDate: md_complete_expect_val
                        }

                        jsonArr.push(jsonObj);

                    } // for

                    let resData = ajaxConnect('/mng/request/list/complete/expect/update.do', 'post', jsonArr);

                    if(resData.resultCode !== "0"){
                        showMessage('', 'error', '에러 발생', '처리 예정 일시 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                        return false;
                    }else{

                        Swal.fire({
                            icon: 'info',
                            title: '[ 처리 예정 일시 변경 ]',
                            html: '<span style="font-size: 1.2em;">처리 예정 일시 변경이 정상 완료되었습니다.</span>',
                            allowOutsideClick: false,
                            confirmButtonColor: '#00a8ff',
                            confirmButtonText: '확인'
                        }).then(async (result) => {
                            if (result.isConfirmed) {
                                $('#modal_complete_expect_status').modal('hide');

                                /* 재조회 */
                                f_request_list_search();
                            }
                        })

                    }
                }
            });
        }else{
            showMessage('', 'error', '[ 처리 예정 일시 변경 ]', '변경할 처리 예정 일시를 선택해 주세요.', '');
            return false;
        }
    }
}

function f_request_list_save(seq){
    //console.log(id + '변경내용저장 클릭');
    /* form valid check */
    let validCheck = f_request_list_valid();

    if(validCheck){

        Swal.fire({
            title: '[ 요청사항 & 문의 ]',
            text: '작성된 정보로 문의하시겠습니까?',
            icon: 'info',
            allowOutsideClick: false,
            showCancelButton: true,
            confirmButtonColor: '#00a8ff',
            confirmButtonText: '문의',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then(async (result) => {
            if (result.isConfirmed) {

                /* File upload */
                let fileIdList = '';
                let uploadFileList = document.getElementById('uploadFileList').children;
                let uploadFileListLen = uploadFileList.length;
                for(let i=0; i<uploadFileListLen; i++){
                    let fileId = uploadFileList[i].querySelector('input[type=hidden]').id;
                    //console.log(fileId);
                    fileIdList += fileId;
                    if((i+1) !== uploadFileListLen){
                        fileIdList += ',';
                    }
                }

                if(fileIdList !== ''){
                    let dataForm = document.getElementById('dataForm');
                    let hidden_el = document.createElement('input');
                    hidden_el.type = 'hidden';
                    hidden_el.name = 'fileIdList';
                    hidden_el.value = fileIdList;
                    dataForm.append(hidden_el);
                }

                /* Modify */
                let processGbn = 'I';
                let url = '/mng/request/list/insert.do';
                if(nvl(seq, '') !== '') {
                    processGbn = 'U';
                    url = '/mng/request/list/update.do';
                }

                /* form data setting */
                let form = f_request_list_form_data_setting(processGbn);

                $.ajax({
                    url: url,
                    method: 'POST',
                    async: false,
                    data: JSON.stringify(form),
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    success: function (data) {
                        if (data.resultCode === "0") {
                            Swal.fire({
                                title: '[ 요청사항 & 문의 ]',
                                text: '작성된 정보가 저장되었습니다.',
                                icon: 'info',
                                allowOutsideClick: false,
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: '확인'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    if(processGbn === 'U'){
                                        f_request_list_detail_set(seq); // 재조회
                                    }else{

                                        // [ 경기해양레저 인력양성센터 ] - (오류) 요청사항 문의드립니다.
                                        let subject = '[ 경기해양레저 인력양성센터 ] - 요청사항 & 문의 (' + form.gbn + ') 관련 새 글이 등록되었습니다.';
                                        let body = '';
                                        body += '<p>';
                                            body += '경기해양레저 인력양성센터 관리자 페이지에 요청사항 & 문의가 등록되었습니다.';
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '--------------------------------------------------------------------------------------';
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '긴급여부 : ' + form.emergencyYn;
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '요청구분 : ' + form.gbn;
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '제목 : ' + form.title;
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '내용 : ' + form.content.toString().replaceAll('"','\'').replaceAll('/board/uploadFileGet?fileName=/usr/local/tomcat/webapps','https://edumarine.org/');
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '첨부파일 : ';
                                        if(uploadFileListLen > 0){
                                            body += '있음';
                                        }else{
                                            body += '없음';
                                        }
                                        body += '</p>';
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '--------------------------------------------------------------------------------------';
                                        body += '</p>';
                                        body += '<p><br/></p>';
                                        body += '<p>';
                                            body += '<a href="https://edumarine.org/mng/index.do" target="_blank">';
                                                body += '관리자 페이지 바로가기';
                                            body += '</a>';
                                        body += '</p>';

                                        body = body.replaceAll('"','\\\\"');

                                        // 담당자 메일 send
                                        let mailJson = {
                                            subject: subject,
                                            body: body,
                                            note: ''
                                        };
                                        let resData1 = ajaxConnect('/mail/send.do', 'post', requestMakeMailFormat(mailJson));
                                        if (resData1.resultCode === "0") {
                                            window.location.href = '/mng/request/list.do';
                                        }else{
                                            showMessage('', 'error', '에러 발생', '요청사항 & 문의 접수 담당자 메일 전송에 실패하였습니다. 관리자에게 문의해주세요. ' + resData1.resultMessage, '');
                                        }
                                    }
                                }
                            });
                        } else {
                            showMessage('', 'error', '에러 발생', '요청사항 & 문의 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                        }
                    },
                    error: function (xhr, status) {
                        alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                    }
                })//ajax

            }//result.isConfirmed
        })

    }//validCheck

}

function requestMakeMailFormat(data){
    let returnJsonObj;
    let receiverArr = [];

    let note = data.note;

    let cpEmail;
    if(nvl(note,'') === '개발사') {
        cpEmail = 'jove0904@kwateromc.co.kr';
    }else{
        // kyj@meetingfan.com
        // khe@meetingfan.com
        // cmn@meetingfan.com
        let subject = data.subject;
        let gbn = subject.substring(subject.indexOf('(')+1, subject.indexOf(')'));
        switch (gbn) {
            case '유지보수':
            case '단순 문의':
            case '기타':
                cpEmail = 'cmn@meetingfan.com';
                break;
            case '기능 추가 문의':
            case '뉴스레터':
                cpEmail = 'kyj@meetingfan.com';
                break;
            case '오류':
                cpEmail = 'khe@meetingfan.com';
                break;
            default:
                cpEmail = 'kyj@meetingfan.com';
                break;
        }
    }

    let receiverObj = {
        email: cpEmail //받는이 메일주소,
    }
    
    receiverArr.push(receiverObj);

    returnJsonObj = {
        subject: data.subject, //제목
        body: data.body, //본문
        receiver: receiverArr
    }

    return returnJsonObj;
}

function f_request_list_form_data_setting(processGbn){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    form.uploadFile = '';

    form.emergencyYn = nvl(form.emergencyYn,'N');

    if(processGbn === 'I') {
        form.progressStep = '처리대기';
    }

    return form;
}

function f_request_list_valid(){

    let gbn = $('#gbn').val();
    let title = $('#title').val();
    let content = document.querySelector('#quill_content').value;

    if(nvl(gbn,'') === ''){ showMessage('', 'error', '[ 입력 정보 ]', '요청구분 항목을 선택해 주세요.', ''); return false; }
    if(nvl(title,'') === ''){ showMessage('', 'error', '[ 입력 정보 ]', '제목 항목을 입력해 주세요.', ''); return false; }
    if(nvl(content,'') === ''){ showMessage('', 'error', '[ 입력 정보 ]', '내용 항목을 입력해 주세요.', ''); return false; }

    return true;
}

function f_request_list_reply_save(requestSeq){

    if(nvl(requestSeq,'') !== ''){

        let content = $('#replyForm textarea[name=content]').val();
        if (nvl(content, '') === '') {

            showMessage('', 'error', '[ 댓글 입력 ]', '댓글 내용을 입력해 주세요.', '');

        } else {

            Swal.fire({
                title: '[ 댓글 ]',
                text: '작성된 내용으로 댓글을 등록하시겠습니까?',
                icon: 'info',
                allowOutsideClick: false,
                showCancelButton: true,
                confirmButtonColor: '#00a8ff',
                confirmButtonText: '등록',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then(async (result) => {
                if (result.isConfirmed) {

                    /* form data setting */
                    let form = JSON.parse(JSON.stringify($('#replyForm').serializeObject()));
                    form.depthReplyNo = '0';

                    let data = JSON.stringify(form);

                    /* Modify */
                    let url = '/mng/request/list/reply/insert.do';

                    $.ajax({
                        url: url,
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {

                                f_request_list_reply_sms_send(form);

                                f_request_list_detail_set(requestSeq); // 재조회

                            } else {
                                showMessage('', 'error', '에러 발생', '댓글 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax

                }//result.isConfirmed
            })

        }//validCheck
    }else{
        showMessage('', 'error', '[ 댓글 입력 ]', '아직 등록되지 않은 글에는 댓글을 저장할 수 없습니다.', '');
    }

}

function f_request_list_reply_sms_send(form){
    let gbn = $('#gbn').val();
    let title = $('#title').val();
    let content = $('#replyContent').val();
    let note = form.note;

    // [ 경기해양레저 인력양성센터 ] - (오류) 요청사항 문의드립니다.
    let subject = '[ 경기해양레저 인력양성센터 ] - 요청사항 & 문의 (' + gbn + ') ' + title +  ' 에 새 댓글이 등록되었습니다.';
    let body = '';
    body += '<p>';
        body += '요청사항 & 문의 게시물에 대한 새 댓글 등록 알림 메일입니다.';
    body += '</p>';
    body += '<p><br/></p>';
    body += '<p>';
        body += '--------------------------------------------------------------------------------------';
    body += '</p>';
    body += '<p><br/></p>';
    body += '<p>';
        body += '* 요청구분 : ' + gbn;
    body += '</p>';
    body += '<p><br/></p>';
    body += '<p>';
        body += '* 제목 : ' + title;
    body += '</p>';
    body += '<p><br/></p>';
    body += '<p>';
        body += '* 내용 : ' + content;
    body += '</p>';
    body += '<p><br/></p>';
    body += '<p>';
        body += '--------------------------------------------------------------------------------------';
    body += '</p>';
    body += '<p><br/></p>';
    body += '<p>';
        body += '<a href="https://edumarine.org/mng/index.do" target="_blank">';
            body += '관리자 페이지 바로가기';
        body += '</a>';
    body += '</p>';

    body = body.replaceAll('"','\\\\"');

    // 담당자 메일 send
    let mailJson = {
        subject: subject,
        body: body,
        note: note
    };
    let resData1 = ajaxConnect('/mail/send.do', 'post', requestMakeMailFormat(mailJson));
    if (resData1.resultCode !== "0") {
        showMessage('', 'error', '에러 발생', '요청사항 & 문의 접수 담당자 메일 전송에 실패하였습니다. 관리자에게 문의해주세요. ' + resData1.resultMessage, '');
    }
}

function f_request_list_reply_remove(seq, requestSeq){
    if(nvl(seq,'') !== ''){
        Swal.fire({
            title: '[ 댓글 ]',
            html: '해당 댓글을 정말로 삭제하시겠습니까?<br/>삭제한 정보는 복구할 수 없습니다.',
            allowOutsideClick: false,
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/request/list/reply/delete.do', 'post', { seq: seq });

                if (resData.resultCode === "0") {

                    Swal.fire({
                        icon: 'info',
                        title: '[ 댓글 ]',
                        html: '댓글이 삭제되었습니다.',
                        allowOutsideClick: false,
                        confirmButtonColor: '#00a8ff',
                        confirmButtonText: '확인'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            f_request_list_detail_set(requestSeq);
                        }
                    });

                } else {
                    showMessage('', 'error', '에러 발생', '댓글 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }

            }
        });

    }
}