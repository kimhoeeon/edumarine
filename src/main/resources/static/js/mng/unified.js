"use strict";

// 데이터테이블 인스턴스
var datatable;

var DTCustomerUnified = function () {
    var initDatatable = function () {
        datatable = $("#mng_customer_unified_table").DataTable({
            'info': false,
            'paging' : false,
            'select': false,
            'ordering': false,
            'order': [[1, 'desc']],
            'columnDefs': [
                { orderable: false, targets: 0 }, // Disable ordering on column 0 (checkbox)
                {
                    'targets': '_all',
                    'className': 'text-center'
                },
                {
                    'targets': 0,
                    'render': function (data, type, row) { return renderCheckBoxCell(data, type, row); }
                },
                {
                    'targets': 3,
                    'render': function (data, type, row) { return renderYearCell(data, type, row); }
                },
                {
                    'targets': 5,
                    'render': function (data, type, row) { return renderNextTimeCell(data, type, row); }
                },
                {
                    'targets': 6,
                    'render': function (data, type, row) { return renderApplyStatusCell(data, type, row); }
                },
                {
                    'targets': 7,
                    'render': function (data, type, row) { return renderGradeCell(data, type, row); }
                },
                {
                    'targets': 8,
                    'render': function (data, type, row) { return renderIdCell(data, type, row); }
                },
                {
                    'targets': 9,
                    'render': function (data, type, row) { return renderNameCell(data, type, row); }
                },
                {
                    'targets': 10,
                    'render': function (data, type, row) { return renderContactCell(data, type, row); }
                },
                {
                    'targets': 12,
                    'data': 'actions',
                    'render': function (data, type, row) { return renderActionsCell(data, type, row); }
                },
                { visible: false, targets: [2] }
            ],
            columns: [
                { data: '' },
                { data: 'rownum' },
                { data: 'seq'},
                { data: 'year'},
                { data: 'gbn'},
                { data: 'nextTime'},
                { data: 'applyStatus'},
                { data: 'grade'},
                { data: 'id'},
                { data: 'name'},
                { data: 'contact'},
                { data: 'initRegiDttm' },
                { data: 'actions' }
            ]
        });
    };

    function renderYearCell(data, type, row) {
        let renderHTML = '-';
        let year = row.trainStartDttm;
        if(nvl(year,'') !== ''){
            renderHTML = year.toString().substring(0, year.toString().indexOf('.'));
        }

        return renderHTML;
    }

    function renderNextTimeCell(data, type, row) {
        let renderHTML = '-';
        let nextTime = row.nextTime;
        if(nvl(nextTime,'') !== ''){
            renderHTML = nextTime + '차';
        }

        return renderHTML;
    }

    function renderGradeCell(data, type, row) {
        let renderHTML = '-';
        let grade = row.grade;
        if(nvl(grade,'') !== ''){
            renderHTML = grade;
        }

        return renderHTML;
    }

    function renderIdCell(data, type, row) {
        let renderHTML = '-';
        let id = row.id;
        if(nvl(id,'') !== ''){
            renderHTML = id;
        }

        return renderHTML;
    }

    function renderContactCell(data, type, row) {
        let renderHTML = '';
        let phone = row.phone;
        if(nvl(phone,'') !== ''){
            renderHTML += phone;
        }else{
            renderHTML += '-';
        }
        renderHTML += '</br>';
        let email = row.email;
        if(nvl(email,'') !== ''){
            renderHTML += email;
        }else{
            renderHTML += '-';
        }

        return renderHTML;
    }

    function renderCheckBoxCell(data, type, row){
        let renderHTML = '<div class="train_check form-check form-check-sm form-check-custom form-check-solid">' +
            '<input class="form-check-input" type="checkbox" value="'+ row.seq +'" data-value="' + row.nameKo  + ' / ' + row.applyStatus + '"/>' +
            '</div>';
        return renderHTML;
    }

    function renderApplyStatusCell(data, type, row) {
        let renderHTML = '';
        let payMethod = row.payMethod;
        let cancelGbn = row.cancelGbn;
        let applyStatus = row.applyStatus;
        if(applyStatus.includes('취소')){
            renderHTML += '<div class="badge badge-light-danger fw-bold">';
            renderHTML += applyStatus;
            renderHTML += '</div>';
        }else{
            renderHTML += '<div class="badge badge-light-primary fw-bold">';
            renderHTML += applyStatus;
            renderHTML += '</div>';
        }

        renderHTML += '<div>';
        if(nvl(payMethod,'') !== ''){
            payMethod = payMethod.toString().toLowerCase();
            if(payMethod.includes('card')){
                renderHTML += '( 카드 )';
            }else{
                renderHTML += '( 계좌 )';
            }
        }else{
            renderHTML += '( - )';
        }
        if(nvl(cancelGbn,'') !== ''){
            if(cancelGbn === 'ALL'){
                renderHTML += '<br>';
                renderHTML += '( 전액 )';
            }else{
                renderHTML += '<br>';
                renderHTML += '( 부분 )';
            }
        }else{
            renderHTML += '<br>';
            renderHTML += '( - )';
        }
        renderHTML += '</div>';

        return renderHTML;
    }

    function renderNameCell(data, type, row) {
        let renderHTML = '';
        let nameKo = row.nameKo;
        let nameEn = row.nameEn;
        if(nvl(nameKo,'') !== ''){
            renderHTML += nameKo;
        }else{
            renderHTML += '-';
        }
        if(nvl(nameEn,'') !== ''){
            renderHTML += '<br>' + nameEn;
        }else{
            renderHTML += '<br>-';
        }

        return renderHTML;
    }

    function renderActionsCell(data, type, row){
        //console.log(row.id);
        let seq = row.seq;
        let name = row.nameKo;
        let applyStatus = row.applyStatus;
        let nextTime = row.nextTime;
        let renderHTML = '<button type="button" onclick="KTMenu.createInstances()" class="btn btn-sm btn-light btn-flex btn-center btn-active-light-primary" data-kt-menu-trigger="click" data-kt-menu-placement="bottom-end">';
        renderHTML += 'Actions';
        renderHTML += '<i class="ki-duotone ki-down fs-5 ms-1"></i></button>';
        renderHTML += '<div id="kt_menu" class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-600 menu-state-bg-light-primary fw-semibold fs-7 w-150px py-4" data-kt-menu="true">';
        /*renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_customer_unified_detail_modal_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3" data-bs-toggle="modal" data-bs-target="#kt_modal_modify_history">상세정보</a>';
        renderHTML += '</div>';*/
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_customer_unified_modify_init_set(' + '\'' + seq + '\'' + ')" class="menu-link px-3">상세정보</a>';
        renderHTML += '</div>';
        /*renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_customer_unified_train_change_modal_set(' + '\'' + seq + '\',\'' + name + '\',\'' + applyStatus + '\',\'' + nextTime + '\')" class="menu-link px-3" data-bs-target="#kt_modal_apply_edu_change">교육변경</a>';
        renderHTML += '</div>';*/
        renderHTML += '<div class="menu-item px-3">';
        renderHTML += '<a onclick="f_customer_unified_remove(' + '\'' + seq + '\'' + ')" class="menu-link px-3">삭제</a>';
        renderHTML += '</div>';
        renderHTML += '</div>';
        return renderHTML;
    }

    return {
        init: function () {
            initDatatable();

            /* Data row clear */
            let dataTbl = $('#mng_customer_unified_table').DataTable();
            dataTbl.clear();
            dataTbl.draw(false);

            dataTbl.on('order.dt search.dt', function () {
                let i = dataTbl.rows().count();
                dataTbl.cells(null, 1, { search: 'applied', order: 'applied' })
                    .every(function (cell) {
                        this.data(i--);
                    });
            }).draw();

            /* 조회 */
            f_customer_unified_search();
        }
    };
}();

// 페이지 로드 시 실행
$(document).ready(function () {
    // 테이블 초기화
    DTCustomerUnified.init();

    // 엔터키 검색 이벤트
    $("#search_text").on("keyup", function (key) {
        if (key.keyCode == 13) {
            f_customer_unified_search();
        }
    });

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

// 검색 함수
function f_customer_unified_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_customer_unified_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let year = $('#condition_year option:selected').val();
    let applyStatus = $('#condition_apply_status option:selected').val();
    if(nvl(searchText,'') === ''){
        jsonObj = {
            year: year,
            applyStatus: applyStatus
        };
    }else{
        jsonObj = {
            year: year,
            applyStatus: applyStatus,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/customer/unified/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    dataTbl.column(6).nodes().to$().addClass('d-flex');
    dataTbl.column(6).nodes().to$().addClass('justify-content-center');

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_customer_unified_table tbody td');
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

function f_customer_unified_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_year').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_apply_status').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_customer_unified_search();
}

function f_customer_unified_remove(seq){
    //console.log('삭제버튼');
    if(nvl(seq,'') !== ''){
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
                        targetTable: 'application_unified',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_customer_unified_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_customer_unified_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_customer_unified_modify_init_set(seq){
    window.location.href = '/mng/customer/unified/detail.do?seq=' + seq;
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
                            trainTable: 'application_unified'
                        }

                        smsArr.push(smsObj);

                    } // for

                    let resData = ajaxConnect('/mng/customer/unified/status/update.do', 'post', jsonArr);

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
                        f_customer_unified_search();
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

                    let resData = ajaxConnect('/mng/customer/unified/status/change/update.do', 'post', jsonArr);

                    if (resData.resultCode !== "0") {
                        showMessage('', 'error', '에러 발생', '신청 상태 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    } else {
                        showMessage('', 'info', '신청 상태 변경', '신청 상태 변경이 정상 완료되었습니다.', '');

                        $('#kt_modal_apply_status_change').modal('hide');

                        /* 재조회 */
                        f_customer_unified_search();
                    }
                }
            });

        }else{
            showMessage('', 'error', '[ 신청 상태 변경 ]', '변경할 신청 상태를 선택해 주세요.', '');
        }
    }

}

// 엑셀 다운로드 함수
function f_excel_download(type) {
    var condition = $("#search_box").val();
    var searchText = $("#search_text").val();
    var status = $("#condition_status").val();

    var url = "/mng/customer/unified/excel/download.do?fileName=통합신청목록&condition=" + condition + "&searchText=" + searchText + "&status=" + status;
    window.location.href = url;
}