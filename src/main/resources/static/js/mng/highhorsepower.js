/***
 * mng/customer/highhorsepower
 * 회원/신청>신청자목록>고마력 선외기 정비 중급 테크니션
 * */

$(function(){

    // 출생년월일
    $('#year_select, #month_select').on('change', function () {
        lastDay('1'); //년과 월에 따라 마지막 일 구하기
    });

    // 초기값
    lastDay($('#birthDay').val());

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
                showMessage('', 'error', '[ 취소 승인 ]', '취소 승인할 신청내역을 하나 이상 선택해 주세요.', '');
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
                showMessage('', 'error', '[ 신청 상태 변경 ]', '신청 상태를 변경할 신청내역을 하나 이상 선택해 주세요.', '');
                return false;
            }

        })
    }//myModalEl2

});

function lastDay(val){ //년과 월에 따라 마지막 일 구하기
    let Year = $('#year_select').val();
    let Month = $('#month_select').val();
    let day=new Date(new Date(Year,Month,1)-86400000).getDate();

    if(document.getElementById('day_select')){
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
        }

        if(nvl(val,'') !== ''){
            $('select[name=birthDay]').val(val).prop('selected',true);
        }else{
            $('select[name=birthDay] option:eq(0)').before("<option value='' selected disabled>일</option>");
        }
    }

}

function f_customer_highhorsepower_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_customer_highhorsepower_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let year = $('#condition_year option:selected').val();
    let applyStatus = $('#condition_apply_status option:selected').val();
    let time = $('#condition_time option:selected').val();
    if(nvl(searchText,'') === ''){
        jsonObj = {
            year: year,
            time: time,
            applyStatus: applyStatus
        };
    }else{
        jsonObj = {
            year: year,
            time: time,
            applyStatus: applyStatus,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/customer/highhorsepower/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    dataTbl.column(5).nodes().to$().addClass('d-flex');
    dataTbl.column(5).nodes().to$().addClass('justify-content-center');

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_customer_highhorsepower_table tbody td');
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

function f_customer_highhorsepower_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_year').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_apply_status').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_time').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_customer_highhorsepower_search();
}

function f_customer_highhorsepower_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        Swal.fire({
            title: '[ 삭제 사유 ]',
            text: '사유 입력 후 삭제하기 버튼 클릭 시 데이터는 파일관리>임시휴지통 으로 이동됩니다.',
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
                        targetTable: 'highhorsepower',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_customer_highhorsepower_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_customer_highhorsepower_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_customer_highhorsepower_modify_init_set(seq){
    window.location.href = '/mng/customer/highhorsepower/detail.do?seq=' + seq;
}

function f_apply_cancel_btn(){

    let idArr = $('input[type=hidden][name=checkVal]').val();
    let statusArr = $('input[type=hidden][name=checkStatus]').val();
    if (nvl(idArr,'') !== ''){

        let md_cancel_gbn_val = $('#md_cancel_gbn').val();

        if(nvl(md_cancel_gbn_val,'') !== '') {
            Swal.fire({
                title: '[ 취소 승인 ]',
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
                    let smsArr = [];
                    for(let i=0; i<idSplit.length; i++){
                        let jsonObj = {
                            seq: idSplit[i],
                            preApplyStatus: statusSplit[i],
                            cancelGbn: md_cancel_gbn_val,
                            applyStatus: '취소완료'
                        }

                        jsonArr.push(jsonObj);

                        let smsObj = {
                            seq: idSplit[i],
                            trainTable: 'highhorsepower'
                        }

                        smsArr.push(smsObj);

                    } // for

                    let resData = ajaxConnect('/mng/customer/highhorsepower/status/update.do', 'post', jsonArr);

                    if(resData.resultCode !== "0"){
                        showMessage('', 'error', '에러 발생', '취소 승인을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    }else{

                        for (let j=0; j<smsArr.length; j++){
                            let obj = smsArr[j];
                            f_sms_notify_sending('5', obj); // 5 취소완료 후
                        }

                        showMessage('', 'info', '취소 승인', '취소 승인처리가 정상 완료되었습니다.', '');

                        $('#kt_modal_apply_status_cancel').modal('hide');

                        /* 재조회 */
                        f_customer_highhorsepower_search();
                    }
                }
            });
        }else{
            showMessage('', 'error', '[환불 구분]', '환불 구분을 선택해 주세요.', '');
        }
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

                    let resData = ajaxConnect('/mng/customer/highhorsepower/status/change/update.do', 'post', jsonArr);

                    if (resData.resultCode !== "0") {
                        showMessage('', 'error', '에러 발생', '신청 상태 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    } else {
                        showMessage('', 'info', '신청 상태 변경', '신청 상태 변경이 정상 완료되었습니다.', '');

                        $('#kt_modal_apply_status_change').modal('hide');

                        /* 재조회 */
                        f_customer_highhorsepower_search();
                    }
                }
            });

        }else{
            showMessage('', 'error', '[ 신청 상태 변경 ]', '변경할 신청 상태를 선택해 주세요.', '');
        }
    }

}

function f_customer_highhorsepower_detail_excel_download(tableId , name){

    let dataCount = $('#' + tableId).DataTable().rows().count();
    if(dataCount > 0){

        Swal.fire({
            title: '[ 전체 신청자 정보 상세 다운로드 ]',
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
                                    url: '/mng/customer/highhorsepower/excel/download.do?fileName=' + fileName,
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
        showMessage('', 'info', '[ 전체 신청자 정보 상세 다운로드 ]', '엑셀로 추출할 데이터가 없습니다.', '');
    }
}

function f_customer_highhorsepower_train_change_modal_set(seq, name, applyStatus, nextTime){

    if(!applyStatus.includes('취소')){
        // 교육 Set
        $("#md_edu").children('option:not(:gt(1))').remove();

        let jsonObj = { gbn : '고마력 선외기 정비 중급 테크니션' };
        $.ajax({
            url: '/train/active.do',
            method: 'post',
            data: JSON.stringify(jsonObj),
            async: false,
            contentType: 'application/json; charset=utf-8', //server charset 확인 필요
        })
            .done(function (data, status) {
                let results = data;
                if (nvl(results, '') !== '') {
                    $.each(results, function (i) {
                        $('#md_edu').append($('<option>', {
                            value: results[i].seq,
                            text: results[i].category + ' / ' + results[i].gbn + ' / ' + results[i].nextTime + '차 / ' + results[i].trainStartDttm + ' / ' + results[i].trainEndDttm
                        }));
                    })

                    $('#md_edu').val('').select2({minimumResultsForSearch: Infinity});
                }
            });


        let input_hidden = document.createElement('input');
        input_hidden.type = 'hidden';
        input_hidden.name = 'checkVal'
        input_hidden.value = seq;

        let input_hidden2 = document.createElement('input');
        input_hidden2.type = 'hidden';
        input_hidden2.name = 'checkStatus'
        input_hidden2.value = applyStatus;

        if(nvl(nextTime, '') === ''){ nextTime = '-'; }
        let modal_target_list = $('#modal_form3 .target_list');
        modal_target_list.html(nextTime + '차 / ' + name + ' / ' + applyStatus);
        modal_target_list.append(input_hidden);
        modal_target_list.append(input_hidden2);

        $('#kt_modal_apply_edu_change').modal('show');
    }else{
        showMessage('', 'error', '[ 신청 교육 변경 ]', '교육 변경 불가한 내역입니다. (취소신청/취소완료)', '');
        return false;
    }

}

function f_customer_highhorsepower_train_change_btn(){

    let seq = $('input[type=hidden][name=checkVal]').val();
    if (nvl(seq,'') !== ''){

        let md_edu_val = $('#md_edu').val();
        let md_edu_text = $('#md_edu option:selected').text();
        if(nvl(md_edu_val,'') !== '') {
            Swal.fire({
                title: '[ 신청 교육 변경 ]',
                html: '신청자의 교육을 변경하시겠습니까 ?',
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {

                    let jsonObj = {
                        seq: seq,
                        trainSeq: md_edu_val,
                        trainName: md_edu_text.split(' / ')[1].trim() // 단기과정 / 해상엔진 자가정비 (선외기) / 3차 / 2024.08.24 / 2024.08.25
                    }

                    let resData = ajaxConnect('/mng/customer/train/change/update.do', 'post', jsonObj);

                    if (resData.resultCode === "-1") {
                        showMessage('', 'error', '에러 발생', '신청 교육 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    } else if(resData.resultCode === "-2"){
                        showMessage('', 'info', '[ 신청 교육 변경 ]', resData.resultMessage, '');
                    }else {
                        Swal.fire({
                            title: '[ 신청 교육 변경 ]',
                            html: '신청 교육 변경이 정상 완료되었습니다.',
                            allowOutsideClick: false,
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then(async (result) => {
                            if (result.isConfirmed) {
                                $('#kt_modal_apply_edu_change').modal('hide');

                                /* 재조회 */
                                f_customer_highhorsepower_search();
                            }
                        });
                    }
                }
            });

        }else{
            showMessage('', 'error', '[ 신청 교육 변경 ]', '변경할 신청 교육을 선택해 주세요.', '');
        }
    }

}