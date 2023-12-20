/***
 * mng/education/payment
 * 교육>교육관리>결제/환불현황
 * */

$(function(){

    let myModalEl = document.getElementById('kt_modal_pay_status');

    if(myModalEl){

        let myModal = new bootstrap.Modal('#kt_modal_pay_status', {
            focus: true
        });

        myModalEl.addEventListener('hidden.bs.modal', event => {
            // input init
            $('.payment_list').empty();
            $('#md_pay_status').val('').select2({minimumResultsForSearch: Infinity});
            $('#refund_reason').val('');
            $('input[type=hidden][name=checkVal]').remove();
        })

        // input
        $('#md_pay_status_sel').on('change', function () {
            let selectedOption = $(this).val();
            let refundReasonInput = $('#refund_reason');

            if (selectedOption === '환불신청') {
                refundReasonInput.parent('div').parent('div').removeClass('d-none');
            } else {
                refundReasonInput.parent('div').parent('div').addClass('d-none');
            }

            refundReasonInput.val('');
        });

        $('#pay_status_btn').on('click', function () {

            let checkbox_el = $('.pay_check input[type=checkbox]:checked');
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

                    $('#modal_form .payment_list').html(checkbox_data_val);
                    $('#modal_form .payment_list').append(input_hidden);

                    myModal.show();
                }
            }else{
                showMessage('', 'error', '[결제 상태 변경]', '결제 상태를 변경할 내역을<br>하나 이상 선택해 주세요.', '');
                return false;
            }

        })
    }//myModalEl

});

function f_education_payment_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_education_payment_table').DataTable();
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

    let resData = ajaxConnect('/mng/education/payment/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_education_payment_table tbody td');
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

function f_education_payment_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_education_payment_search();
}

function f_education_payment_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/education/payment/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);

    document.querySelector('#md_member_name').value = resData.memberName;
    document.querySelector('#md_member_phone').value = resData.memberPhone;
    document.querySelector('#md_train_name').value = resData.trainName;
    document.querySelector('#md_pay_sum').value = resData.paySum;
    document.querySelector('#md_pay_status').value = resData.payStatus;
    document.querySelector('#md_refund_reason').innerHTML = resData.refundReason;

}

function f_education_payment_modify_init_set(seq){
    window.location.href = '/mng/education/payment/detail.do?seq=' + seq;
}

function f_education_payment_remove(seq){
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
            if (result.value) {

                let jsonObj = {
                    targetSeq: seq,
                    targetTable: 'payment',
                    deleteReason: result.value,
                    targetMenu: getTargetMenu('mng_education_payment_table'),
                    delYn: 'Y'
                }
                f_mng_trash_remove(jsonObj);

                f_education_payment_search(); // 재조회

            }else{
                alert('삭제 사유를 입력해주세요.');
            }
        });

        /*let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 결제 내역을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/education/payment/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '결제 내역 삭제', '결제 내역이 삭제되었습니다.', '');
                    f_education_payment_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '결제 내역 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });*/
    }
}

function f_education_payment_save(seq){
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
            let validCheck = f_education_payment_valid();

            if(validCheck){

                /* form data setting */
                let data = f_education_payment_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/education/payment/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '결제 내역 정보 변경',
                                    text: "결제 내역 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_education_payment_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '결제 내역 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/education/payment/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '결제 내역 정보 등록',
                                    text: "결제 내역 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/education/payment.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                if (data.resultCode === "-1") {
                                    showMessage('', 'error', '에러 발생', '결제 내역 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                                }else{
                                    showMessage('', 'info', '[등록 정보]', data.resultMessage, '');
                                }
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

function f_education_payment_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    // 교육과정명
    form.trainName = $('#trainName').val();

    return JSON.stringify(form);
}

function f_education_payment_valid(){
    let memberName = $('#memberName').val();
    let memberPhone = $('#memberPhone').val();
    let trainName = $('#trainName').val();
    let paySum = $('#paySum').val();
    let payStatus = $('#payStatus').val();

    if(nvl(memberName,'') === ''){ showMessage('', 'error', '[등록 정보]', '신청자 성명을 입력해 주세요.', ''); return false; }
    if(nvl(memberPhone,'') === ''){ showMessage('', 'error', '[등록 정보]', '신청자 연락처를 입력해 주세요.', ''); return false; }
    if(nvl(trainName,'') === ''){ showMessage('', 'error', '[등록 정보]', '교육과정명을 선택해 주세요.', ''); return false; }
    if(nvl(paySum,'') === ''){ showMessage('', 'error', '[등록 정보]', '교육비를 입력해 주세요.', ''); return false; }
    if(nvl(payStatus,'') === ''){ showMessage('', 'error', '[등록 정보]', '결제상태를 선택해 주세요.', ''); return false; }


    return true;
}

function f_pay_status_btn_yn(){

    let idArr = $('input[type=hidden][name=checkVal]').val();
    if (nvl(idArr,'') !== ''){

        let md_pay_status_val = $('#md_pay_status_sel').val();
        if(nvl(md_pay_status_val,'') !== ''){

            Swal.fire({
                title: '결제 상태 변경',
                html: '결제 상태를 변경하시겠습니까 ?<br>[ ' + md_pay_status_val + ' ]',
                icon: 'info',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    let idSplit = idArr.split(',');
                    let jsonArr = [];
                    for(let i=0; i<idSplit.length; i++){
                        let jsonObj = {
                            seq: idSplit[i],
                            payStatus: md_pay_status_val,
                            refundReason: $('#refund_reason').val()
                        }

                        jsonArr.push(jsonObj);

                    } // for

                    let resData = ajaxConnect('/mng/education/payment/updatePayStatus.do', 'post', jsonArr);

                    if(resData.resultCode !== "0"){
                        showMessage('', 'error', '에러 발생', '결제 상태 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                        return false;
                    }else{
                        showMessage('', 'info', '결제 상태 변경', '결제 상태 변경이 정상 완료되었습니다.', '');

                        $('#kt_modal_pay_status').modal('hide');

                        /* 재조회 */
                        f_education_payment_search();
                    }
                }
            });
        }else{
            showMessage('', 'error', '[결제 상태 변경]', '변경할 결제 상태를 선택해 주세요.', '');
            return false;
        }
    }

}