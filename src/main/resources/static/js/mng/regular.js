/***
 * mng/customer/regular
 * 회원/신청>신청자목록>상시사전신청
 * */

$(function(){


    /*// 이메일
    $('#email_select').on('change', function () {
        let selectedOption = $(this).val();
        let domain = $('#domain');

        if (selectedOption === '직접입력') {
            domain.prop('disabled', false).val('');
        } else {
            domain.prop('disabled', true).val(selectedOption);
        }
    });

    // 출생년월일
    $('#month_select').on('change', function () {
        $('#day_select').val('');

        lastDay(); //년과 월에 따라 마지막 일 구하기
    });*/

    let myModalEl = document.getElementById('kt_modal_apply_status_cancel');

    if(myModalEl){

        let myModal = new bootstrap.Modal('#kt_modal_apply_status_cancel', {
            focus: true
        });

        myModalEl.addEventListener('hidden.bs.modal', event => {
            // input init
            $('.customer_list').empty();
            $('#md_cancel_gbn').val('').select2({minimumResultsForSearch: Infinity});
            $('input[type=hidden][name=checkVal]').remove();
            $('input[type=hidden][name=checkStatus]').remove();
        })

        $('#apply_status_cancel_btn').on('click', function () {

            let checkbox_el = $('.train_check input[type=checkbox]:checked');
            let checkbox_len = checkbox_el.length;
            let checkbox_data_val = '';
            let checkbox_val = '';
            let checkbox_status = '';
            if(checkbox_len !== 0){
                let i = 0;
                $(checkbox_el).each(function() {
                    checkbox_data_val += (i+1) + '. ';
                    let data_val = $(this).data('value');
                    checkbox_data_val += data_val;

                    let checkbox_data_status = data_val.split(' / ')[1];
                    checkbox_status += checkbox_data_status;

                    checkbox_val += $(this).val();
                    if((i+1) !== checkbox_len){
                        checkbox_data_val += '<br>';
                        checkbox_val += ',';
                        checkbox_status += ',';
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
                    input_hidden2.name = 'checkStatus'
                    input_hidden2.value = checkbox_status;

                    $('#modal_form .customer_list').html(checkbox_data_val);
                    $('#modal_form .customer_list').append(input_hidden);
                    $('#modal_form .customer_list').append(input_hidden2);

                    myModal.show();
                }
            }else{
                showMessage('', 'error', '[취소 승인]', '취소 승인할 신청내역을 하나 이상 선택해 주세요.', '');
                return false;
            }

        })
    }//myModalEl

    let myModalEl2 = document.getElementById('kt_modal_apply_status_change');

    if(myModalEl2){

        let myModal = new bootstrap.Modal('#kt_modal_apply_status_change', {
            focus: true
        });

        myModalEl2.addEventListener('hidden.bs.modal', event => {
            // input init
            $('.customer_list').empty();
            $('#md_status_gbn').val('').select2({minimumResultsForSearch: Infinity});
            $('input[type=hidden][name=checkVal]').remove();
            $('input[type=hidden][name=checkStatus]').remove();
        })

        $('#apply_status_change_btn').on('click', function () {

            let checkbox_el = $('.train_check input[type=checkbox]:checked');
            let checkbox_len = checkbox_el.length;
            let checkbox_data_val = '';
            let checkbox_val = '';
            let checkbox_status = '';
            if(checkbox_len !== 0){
                let i = 0;
                $(checkbox_el).each(function() {
                    checkbox_data_val += (i+1) + '. ';
                    let data_val = $(this).data('value');
                    checkbox_data_val += data_val;

                    let checkbox_data_status = data_val.split(' / ')[1];
                    checkbox_status += checkbox_data_status;

                    checkbox_val += $(this).val();
                    if((i+1) !== checkbox_len){
                        checkbox_data_val += '<br>';
                        checkbox_val += ',';
                        checkbox_status += ',';
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
                    input_hidden2.name = 'checkStatus'
                    input_hidden2.value = checkbox_status;

                    $('#modal_form2 .customer_list').html(checkbox_data_val);
                    $('#modal_form2 .customer_list').append(input_hidden);
                    $('#modal_form2 .customer_list').append(input_hidden2);

                    myModal.show();
                }
            }else{
                showMessage('', 'error', '[신청 상태 변경]', '신청 상태를 변경할 신청내역을 하나 이상 선택해 주세요.', '');
                return false;
            }

        })
    }//myModalEl2

});

function lastDay(){ //년과 월에 따라 마지막 일 구하기
    let Year = $('#year_select').val();
    let Month = $('#month_select').val();
    let day=new Date(new Date(Year,Month,1)-86400000).getDate();

    let day_index_len = document.getElementById('day_select').length;
    if(day > day_index_len){
        for(let i=day_index_len; i <= day; i++){
            document.getElementById('day_select').options[i-1] = new Option(i + ' 일', i);
        }
    }
    else if(day < day_index_len){
        for(let i = day_index_len; i >= day; i--){
            document.getElementById('day_select').options[i] = null;
        }
    }else{
        document.getElementById('day_select').options[0].selected = true;
    }
}

function f_customer_regular_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_customer_regular_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let applyStatus = $('#condition_apply_status option:selected').val();
    let experienceYn = $('#condition_experience_yn option:selected').val();
    if(nullToEmpty(searchText) === ''){
        jsonObj = {
            applyStatus: applyStatus,
            experienceYn: experienceYn
        };
    }else{
        jsonObj = {
            applyStatus: applyStatus,
            experienceYn: experienceYn,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/customer/regular/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_customer_regular_table tbody td');
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

function f_customer_regular_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_apply_status').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_experience_yn').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_customer_regular_search();
}

function f_customer_regular_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    $.ajax({
        url: '/mng/customer/regular/selectSingle.do',
        method: 'post',
        async: false,
        data: JSON.stringify(jsonObj),
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    })
    .done(function (data) {
        let resData = data;
        if(nvl(resData,'') !== ''){
            $('#kt_modal_modify_history').modal('toggle');

            document.querySelector('#md_name').value = resData.name;
            document.querySelector('#md_phone').value = resData.phone;
            document.querySelector('#md_email').value = resData.email;
            document.querySelector('#md_birth_year').value = resData.birthYear;
            document.querySelector('#md_birth_month').value = resData.birthMonth;
            document.querySelector('#md_birth_day').value = resData.birthDay;
            document.querySelector('#md_region').value = resData.region;

            $('input[type=radio][name=md_participation_path][value=' + resData.participationPath + ']').prop('checked',true);

            document.querySelector('#md_first_application_field').value = resData.firstApplicationField;
            document.querySelector('#md_second_application_field').value = resData.secondApplicationField;
            document.querySelector('#md_third_application_field').value = resData.thirdApplicationField;
            document.querySelector('#md_desired_education_time').value = resData.desiredEducationTime;
            document.querySelector('#md_major').value = resData.major;

            // 교육신청내역 List

            $('.train_info_list').empty();

            let trainInfoList = resData.trainInfoList;
            if(nvl(trainInfoList,'') !== ''){
                for(let i=0; i<trainInfoList.length; i++){
                    let init_label_el = document.createElement('label');
                    init_label_el.classList.add('form-label');
                    init_label_el.innerText = '교육신청일시';

                    let init_input_el = document.createElement('input');
                    init_input_el.type = 'text';
                    init_input_el.classList.add('form-control');
                    init_input_el.classList.add('form-control-lg');
                    init_input_el.classList.add('form-control-solid-bg');
                    init_input_el.name = 'md_init_regi_dttm';
                    init_input_el.value = trainInfoList[i].initRegiDttm;
                    init_input_el.placeholder = '교육신청일시';
                    init_input_el.setAttribute('readonly','readonly');

                    let gbn_label_el = document.createElement('label');
                    gbn_label_el.classList.add('form-label');
                    gbn_label_el.innerText = '교육명';

                    let gbn_input_el = document.createElement('input');
                    gbn_input_el.type = 'text';
                    gbn_input_el.classList.add('form-control');
                    gbn_input_el.classList.add('form-control-lg');
                    gbn_input_el.classList.add('form-control-solid-bg');
                    gbn_input_el.name = 'md_gbn';
                    gbn_input_el.value = trainInfoList[i].gbn;
                    gbn_input_el.placeholder = '교육명';
                    gbn_input_el.setAttribute('readonly','readonly');

                    let time_label_el = document.createElement('label');
                    time_label_el.classList.add('form-label');
                    time_label_el.innerText = '차시';

                    let time_input_el = document.createElement('input');
                    time_input_el.type = 'text';
                    time_input_el.classList.add('form-control');
                    time_input_el.classList.add('form-control-lg');
                    time_input_el.classList.add('form-control-solid-bg');
                    time_input_el.name = 'md_next_time';
                    time_input_el.value = trainInfoList[i].nextTime;
                    time_input_el.placeholder = '차시';
                    time_input_el.setAttribute('readonly','readonly');

                    let status_label_el = document.createElement('label');
                    status_label_el.classList.add('form-label');
                    status_label_el.innerText = '상태';

                    let status_input_el = document.createElement('input');
                    status_input_el.type = 'text';
                    status_input_el.classList.add('form-control');
                    status_input_el.classList.add('form-control-lg');
                    status_input_el.classList.add('form-control-solid-bg');
                    status_input_el.name = 'md_apply_status';
                    status_input_el.value = trainInfoList[i].applyStatus;
                    status_input_el.placeholder = '상태';
                    status_input_el.setAttribute('readonly','readonly');

                    let wrap_div_el_1 = document.createElement('div');
                    wrap_div_el_1.classList.add('mb-6');
                    wrap_div_el_1.append(init_label_el);
                    wrap_div_el_1.append(init_input_el);

                    let wrap_div_el_2 = document.createElement('div');
                    wrap_div_el_2.classList.add('mb-6');
                    wrap_div_el_2.append(gbn_label_el);
                    wrap_div_el_2.append(gbn_input_el);

                    let wrap_div_el_3 = document.createElement('div');
                    wrap_div_el_3.classList.add('mb-6');
                    wrap_div_el_3.append(time_label_el);
                    wrap_div_el_3.append(time_input_el);

                    let wrap_div_el_4 = document.createElement('div');
                    wrap_div_el_4.classList.add('mb-6');
                    wrap_div_el_4.append(status_label_el);
                    wrap_div_el_4.append(status_input_el);

                    $('.train_info_list').append(wrap_div_el_1);
                    $('.train_info_list').append(wrap_div_el_2);
                    $('.train_info_list').append(wrap_div_el_3);
                    $('.train_info_list').append(wrap_div_el_4);
                }

            }else{
                let label_el = document.createElement('label');
                label_el.classList.add('form-label');
                label_el.innerText = '교육신청내역 없음';
                
                let wrap_div_el = document.createElement('div');
                wrap_div_el.classList.add('mb-6');
                wrap_div_el.append(label_el);

                $('.train_info_list').append(wrap_div_el);
            }

        }else{

            //$('#kt_modal_modify_history').find('.modal-header').find('.closeBtn').click();
            //$('#kt_modal_modify_history').modal('toggle');

            showMessage('', 'error', '[신청 정보]', '신청자 정보가 없습니다.', '');
        }
    })
    .fail(function (xhr, status, errorThrown) {
        /*$('body').html("오류가 발생했습니다.")
            .append("<br>오류명: " + errorThrown)
            .append("<br>상태: " + status);*/

        alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + errorThrown + "\n상태 : " + status);
    })

}

function f_customer_regular_remove(seq){
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
                        targetTable: 'regular',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_customer_regular_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_customer_regular_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_customer_regular_modify_init_set(seq){
    window.location.href = '/mng/customer/regular/detail.do?seq=' + seq;
}

function f_customer_regular_save(seq){
    //console.log(id + '변경내용저장 클릭');
    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '변경내용저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {

            /* form valid check */
            let validCheck = f_customer_regular_valid();

            if(validCheck){

                /* form data setting */
                let data = f_customer_regular_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/customer/regular/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '신청 내역 정보 변경',
                                    text: "신청 내역 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_customer_regular_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '신청 내역 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/customer/regular/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '신청 내역 정보 등록',
                                    text: "신청 내역 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/customer/regular.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '신청 내역 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }// id check

            }//validCheck

        }//result.isConfirmed
    })

}

function f_customer_regular_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    //이메일
    form.email = form.email + '@' + $('#domain').val();

    return JSON.stringify(form);
}

function f_customer_regular_valid(){
    let region = document.querySelector('#region').value;
    let participationPathArr = $('input[type=radio][name=participationPath]:checked');
    let first_field_select = $('#first_field_select').val();
    let second_field_select = $('#second_field_select').val();
    let third_field_select = $('#third_field_select').val();

    if(nvl(region,'') === ''){ showMessage('', 'error', '[등록 정보]', '거주지역을 입력해 주세요.', ''); return false; }
    if(participationPathArr.length === 0){ showMessage('', 'error', '[등록 정보]', '참여경로를 하나 이상 선택해 주세요.', ''); return false; }
    if(nvl(first_field_select,'') === ''){ showMessage('', 'error', '[등록 정보]', '1순위 신청분야를 선택해 주세요.', ''); return false; }
    if(nvl(second_field_select,'') === ''){ showMessage('', 'error', '[등록 정보]', '2순위 신청분야를 선택해 주세요.', ''); return false; }
    if(nvl(third_field_select,'') === ''){ showMessage('', 'error', '[등록 정보]', '3순위 신청분야를 선택해 주세요.', ''); return false; }

    return true;
}

function f_apply_cancel_btn(){

    let idArr = $('input[type=hidden][name=checkVal]').val();
    let statusArr = $('input[type=hidden][name=checkStatus]').val();
    if (nvl(idArr,'') !== ''){

        Swal.fire({
            title: '취소 승인',
            html: '취소 승인 처리하시겠습니까 ?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                let idSplit = idArr.split(',');
                let statusSplit = statusArr.split(',');
                let jsonArr = [];
                for (let i = 0; i < idSplit.length; i++) {
                    let jsonObj = {
                        seq: idSplit[i],
                        preApplyStatus: statusSplit[i],
                        applyStatus: '취소완료'
                    }

                    jsonArr.push(jsonObj);

                } // for

                let resData = ajaxConnect('/mng/customer/regular/status/update.do', 'post', jsonArr);

                if (resData.resultCode !== "0") {
                    showMessage('', 'error', '에러 발생', '취소 승인을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                } else {
                    showMessage('', 'info', '취소 승인', '취소 승인처리가 정상 완료되었습니다.', '');

                    $('#kt_modal_apply_status_cancel').modal('hide');

                    /* 재조회 */
                    f_customer_regular_search();
                }
            }
        });
    }

}

function f_apply_change_btn(){

    let idArr = $('input[type=hidden][name=checkVal]').val();
    let statusArr = $('input[type=hidden][name=checkStatus]').val();
    if (nvl(idArr,'') !== ''){

        let md_status_gbn_val = $('#md_status_gbn').val();

        if(nvl(md_status_gbn_val,'') !== '') {
            Swal.fire({
                title: '신청 상태 변경',
                html: '신청 상태를 변경하시겠습니까 ?',
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    let idSplit = idArr.split(',');
                    let statusSplit = statusArr.split(',');
                    let jsonArr = [];
                    for (let i = 0; i < idSplit.length; i++) {
                        let jsonObj = {
                            seq: idSplit[i],
                            preApplyStatus: statusSplit[i],
                            applyStatus: md_status_gbn_val
                        }

                        jsonArr.push(jsonObj);

                    } // for

                    let resData = ajaxConnect('/mng/customer/regular/status/change/update.do', 'post', jsonArr);

                    if (resData.resultCode !== "0") {
                        showMessage('', 'error', '에러 발생', '신청 상태 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    } else {
                        showMessage('', 'info', '신청 상태 변경', '신청 상태 변경이 정상 완료되었습니다.', '');

                        $('#kt_modal_apply_status_change').modal('hide');

                        /* 재조회 */
                        f_customer_regular_search();
                    }
                }
            });

        }else{
            showMessage('', 'error', '[신청 상태 변경]', '변경할 신청 상태를 선택해 주세요.', '');
        }
    }

}

function f_customer_regular_detail_excel_download(tableId , name){

    let dataTbl = $('#' + tableId).DataTable();
    let dataCount = dataTbl.rows().count();
    if(dataCount > 0){

        Swal.fire({
            title: '[엑셀 다운로드]',
            html: '엑셀 다운로드 사유 입력 후 다운로드 가능합니다.<br>파일 > 파일관리 > 다운로드내역',
            input: 'text',
            inputPlaceholder: '엑셀 다운로드 사유를 입력해주세요.',
            width: '70em',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '다운로드',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                if (result.value) {

                    let downloadFileName = name + '_목록_excel_' + getCurrentDate();

                    let jsonObj = {
                        downloadFileName: downloadFileName,
                        targetMenu: getTargetMenu(tableId),
                        downloadReason: result.value
                    }
                    $.ajax({
                        url: '/mng/file/download/insert.do',
                        method: 'post',
                        async: false,
                        data: JSON.stringify(jsonObj),
                        contentType: 'application/json; charset=utf-8',
                        success: function (res) {
                            if (res.resultCode === '0') {

                                let fileName = downloadFileName + '.xlsx';

                                $.ajax({
                                    url: '/mng/customer/regular/excel/download.do?fileName=' + fileName,
                                    method: 'get',
                                    /*async: false,*/
                                    xhrFields: {
                                        responseType: 'blob'
                                    },
                                    contentType: 'application/json; charset=utf-8', //server charset 확인 필요
                                    beforeSend : function(request){
                                        // Performed before calling Ajax
                                        $('#spinner').show();
                                    },
                                    success: function (blob) {
                                        let link = document.createElement('a');
                                        link.href = window.URL.createObjectURL(blob);
                                        link.download = fileName;
                                        link.click();

                                        $('#spinner').hide();
                                    },
                                    error: function() {
                                        // Do when ajax call fail
                                        alert('오류가 발생했습니다. 관리자에게 문의해주세요.');
                                        $('#spinner').hide();
                                    }
                                })

                            }else{
                                showMessage('', 'error', '에러 발생', '엑셀 다운로드 내역 저장에 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                            }
                        }
                    })
                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        })
    }else{
        showMessage('', 'info', 'Export as Excel', '엑셀로 추출할 데이터가 없습니다.', '');
    }
}