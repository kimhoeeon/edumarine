/***
 * mng/education/train
 * 교육>교육관리>교육현황
 * */

$(function(){
    // 교육과정명
    $('#gbn_select').on('change', function () {
        let selectedOption = $(this).val();
        let gbn = $('#gbn');

        if (selectedOption === '직접입력') {
            gbn.prop('disabled', false).val('');
        } else {
            gbn.prop('disabled', true).val(selectedOption);
        }
    });

    /* 교육일정 (시작) */
    let trainStartDttmPicker = document.getElementById('trainStartDttm');
    if(trainStartDttmPicker) {
        trainStartDttmPicker.flatpickr({
            enableTime: false,
            dateFormat: "Y.m.d"
        });
    }

    /* 교육일정 (종료) */
    let trainEndDttmPicker = document.getElementById('trainEndDttm');
    if(trainEndDttmPicker) {
        trainEndDttmPicker.flatpickr({
            enableTime: false,
            dateFormat: "Y.m.d"
        });
    }

    /* 접수기간 (시작) */
    let applyStartDttmPicker = document.getElementById('applyStartDttm');
    if(applyStartDttmPicker) {
        applyStartDttmPicker.flatpickr({
            enableTime: false,
            dateFormat: "Y.m.d"
        });
    }

    /* 접수기간 (종료) */
    let applyEndDttmPicker = document.getElementById('applyEndDttm');
    if(applyEndDttmPicker) {
        applyEndDttmPicker.flatpickr({
            enableTime: false,
            dateFormat: "Y.m.d"
        });
    }
});

function f_education_train_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_education_train_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let time = $('#condition_time option:selected').val();
    let category = $('#condition_category option:selected').val();
    let gbn = $('#condition_gbn option:selected').val();
    if(nullToEmpty(searchText) === ''){
        jsonObj = {
            time: time,
            category: category,
            gbn: gbn,
        };
    }else{
        jsonObj = {
            time: time,
            category: category,
            gbn: gbn,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/education/train/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_education_train_table tbody td');
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

function f_education_train_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_time').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_category').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_gbn').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_education_train_search();
}

function f_education_train_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/education/train/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);

    document.querySelector('#md_gbn').value = resData.gbn;
    document.querySelector('#md_next_time').value = resData.nextTime;
    document.querySelector('#md_train_start_dttm').value = resData.trainStartDttm;
    document.querySelector('#md_train_end_dttm').value = resData.trainEndDttm;
    document.querySelector('#md_apply_start_dttm').value = resData.applyStartDttm;
    document.querySelector('#md_apply_end_dttm').value = resData.applyEndDttm;
    document.querySelector('#md_pay_sum').value = resData.paySum;
    document.querySelector('#md_train_cnt').value = resData.trainCnt;
    document.querySelector('#md_train_apply_cnt').value = resData.trainApplyCnt;
    document.querySelector('#md_train_note').innerHTML = resData.trainNote;

    $('input[type=radio][name=md_exposure_yn][value=' + resData.exposureYn + ']').prop('checked',true);
    $('input[type=radio][name=md_schedule_exposure_yn][value=' + resData.scheduleExposureYn + ']').prop('checked',true);
    $('input[type=radio][name=md_closing_yn][value=' + resData.closingYn + ']').prop('checked',true);

}

function f_education_train_modify_init_set(seq){
    window.location.href = '/mng/education/train/detail.do?seq=' + seq;
}

function f_education_train_remove(seq){
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
                        targetTable: 'train',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_education_train_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_education_train_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

        /*let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 교육을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/education/train/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '교육 삭제', '교육이 삭제되었습니다.', '');
                    f_education_train_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '교육 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });*/
    }
}

function f_education_train_save(seq){
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
            let validCheck = f_education_train_valid();

            if(validCheck){

                /* form data setting */
                let data = f_education_train_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/education/train/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '교육 정보 변경',
                                    text: "교육 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_education_train_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '교육 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/education/train/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '교육 정보 등록',
                                    text: "교육 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/education/train.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '교육 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_education_train_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    // 교육과정명
    form.gbn = $('#gbn').val();

    // 차시
    form.nextTime = $('#nextTime').val();

    // 카테고리
    let category = '전체';
    if(nvl(form.gbn,'') !== ''){
        switch (form.gbn){
            case '해상엔진 테크니션 (선내기/선외기)':
            case 'FRP 레저보트 선체 정비 테크니션':
                category = '정규과정';
                break;
            case '해상엔진 자가정비 (선외기)':
            case '해상엔진 자가정비 (선내기)':
            case '해상엔진 자가정비 (세일요트)':
                category = '단기과정';
                break;
            default:
                break;
        }
    }

    form.category = category;

    return JSON.stringify(form);
}

function f_education_train_valid(){
    let gbn = document.querySelector('#gbn').value;
    let trainStartDttm = document.querySelector('#trainStartDttm').value;
    let trainEndDttm = document.querySelector('#trainEndDttm').value;
    let applyStartDttm = document.querySelector('#applyStartDttm').value;
    let applyEndDttm = document.querySelector('#applyEndDttm').value;
    let paySum = document.querySelector('#paySum').value;
    let trainCnt = document.querySelector('#trainCnt').value;

    if(nvl(gbn,'') === ''){ showMessage('', 'error', '[등록 정보]', '교육과정명을 선택해 주세요.', ''); return false; }
    if(nvl(trainStartDttm,'') === ''){ showMessage('', 'error', '[등록 정보]', '교육일정 (시작)일을 선택해 주세요.', ''); return false; }
    if(nvl(trainEndDttm,'') === ''){ showMessage('', 'error', '[등록 정보]', '교육일정 (종료)일을 선택해 주세요.', ''); return false; }
    if(trainStartDttm > trainEndDttm){ showMessage('', 'error', '[등록 정보]', '교육일정 종료일을 시작일보다 뒤로 선택해 주세요.', ''); return false; }
    if(nvl(applyStartDttm,'') === ''){ showMessage('', 'error', '[등록 정보]', '접수기간 (시작)일을 선택해 주세요.', ''); return false; }
    if(nvl(applyEndDttm,'') === ''){ showMessage('', 'error', '[등록 정보]', '접수기간 (종료)일을 선택해 주세요.', ''); return false; }
    if(applyStartDttm > applyEndDttm){ showMessage('', 'error', '[등록 정보]', '접수기간 종료일을 시작일보다 뒤로 선택해 주세요.', ''); return false; }
    if(nvl(paySum,'') === ''){ showMessage('', 'error', '[등록 정보]', '교육비를 입력해 주세요.', ''); return false; }
    if(nvl(trainCnt,'') === ''){ showMessage('', 'error', '[등록 정보]', '총 교육인원을 입력해 주세요.', ''); return false; }

    return true;
}

function f_education_train_early_closing(seq){
    Swal.fire({
        title: '해당 교육 접수를 마감 처리하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '마감하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            let jsonObj = {
                seq: seq,
                closingYn: 'Y'
            }
            $.ajax({
                url: '/mng/education/train/earlyClosingYn.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(jsonObj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 정보 변경',
                            text: '해당 교육 접수가 마감 처리되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = '/mng/education/train.do';
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}