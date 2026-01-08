/***
 * mng/education/train
 * 교육>교육관리>교육현황
 * */

$(function () {

    // [신규] 초기화 함수 실행 (수정 모드 진입 시 로직 처리)
    f_init_gbn_select();

    // 교육과정명
    $('#gbn_select').on('change', function () {
        let selectedOption = $(this).val();
        let gbnInput = $('#gbn');

        if (selectedOption === 'direct') {
            // [CASE 1] 직접입력 선택: 입력창 활성화, 값 초기화, 포커스
            gbnInput.prop('readonly', false).val('').focus();
        } else if (nvl(selectedOption, '') !== '') {
            // [CASE 2] 목록 선택: 입력창에 값 주입 후 비활성화
            gbnInput.val(selectedOption).prop('readonly', true);
        } else {
            // [CASE 3] 선택 안함: 초기화
            gbnInput.val('').prop('readonly', true);
        }

        // Depth(세부교육명) 노출 로직 (기존 유지)
        f_control_gbn_depth(selectedOption);
    });

    // [신규] 페이지 로드 시 SelectBox 및 Input 상태 초기화 함수
    function f_init_gbn_select() {
        let dbValue = $('#gbn').val(); // DB에서 불러온 값 (수정 시 값 있음, 신규 시 빈값)

        if (nvl(dbValue, '') === '') {
            // [신규 등록] 아무것도 안 함 (기본 상태 유지)
            return;
        }

        // SelectBox 내에 DB값과 일치하는 옵션이 있는지 확인
        let existOption = $("#gbn_select option[value='" + dbValue + "']").length > 0;

        if (existOption) {
            // [수정 - 목록 일치] 해당 옵션 선택 & 입력창 Readonly
            $('#gbn_select').val(dbValue).trigger('change.select2'); // select2 UI 갱신
            $('#gbn').prop('readonly', true);
            // depth 노출 체크
            f_control_gbn_depth(dbValue);
        } else {
            // [수정 - 목록 불일치] '직접입력' 선택 & 입력창 활성화 & 값 유지
            $('#gbn_select').val('direct').trigger('change.select2');
            $('#gbn').prop('readonly', false);
            // 주의: trigger('change')를 하면 위 이벤트 리스너 때문에 val('')이 실행될 수 있음.
            // 따라서 값을 다시 한번 넣어줍니다.
            $('#gbn').val(dbValue);
        }
    }

    // [공통] Depth 노출 제어 함수 (중복 제거)
    function f_control_gbn_depth(val) {
        let gbnDepth = $('#gbnDepth');
        if(val === '기초정비교육' || val === '응급조치교육'){
            gbnDepth.parent().parent().parent().removeClass('d-none');
        } else {
            gbnDepth.parent().parent().parent().addClass('d-none');
            $('#gbnDepth').val('');
            $('#gbn_depth_select').val('');
        }
    }

    // 세부교육명
    $('#gbn_depth_select').on('change', function () {
        let selectedOption = $(this).val();
        let gbnDepth = $('#gbnDepth');

        if (nvl(selectedOption,'') !== '') {
            gbnDepth.prop('disabled', true).val(selectedOption);
        } else {
            gbnDepth.prop('disabled', true).val('');
        }
    });

    /* 교육일정 (시작) */
    let trainStartDttmPicker = document.getElementById('trainStartDttm');
    if (trainStartDttmPicker) {
        trainStartDttmPicker.flatpickr({
            enableTime: false,
            locale: "ko",
            dateFormat: "Y.m.d"
        });
    }

    /* 교육일정 (종료) */
    let trainEndDttmPicker = document.getElementById('trainEndDttm');
    if (trainEndDttmPicker) {
        trainEndDttmPicker.flatpickr({
            enableTime: false,
            locale: "ko",
            dateFormat: "Y.m.d"
        });
    }

    /* 접수기간 (시작) */
    let applyStartDttmPicker = document.getElementById('applyStartDttm');
    if (applyStartDttmPicker) {
        applyStartDttmPicker.flatpickr({
            enableTime: false,
            locale: "ko",
            dateFormat: "Y.m.d"
        });
    }

    /* 접수기간 (종료) */
    let applyEndDttmPicker = document.getElementById('applyEndDttm');
    if (applyEndDttmPicker) {
        applyEndDttmPicker.flatpickr({
            enableTime: false,
            locale: "ko",
            dateFormat: "Y.m.d"
        });
    }

    $('#thumbFileObj').on('change', function(e) {
        var file = e.target.files[0];
        if (!file) return;

        // 1. 이미지 파일 검증
        if (!file.type.match('image.*')) {
            Swal.fire('경고', '이미지 파일만 등록 가능합니다.', 'warning');
            $(this).val(''); // 초기화
            $('#thumbPreview').attr('src', '/assets/media/svg/files/blank-image.svg');
            return;
        }

        // 2. FileReader로 미리보기 출력
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#thumbPreview').attr('src', e.target.result);
            $('#thumbPreview').css({'object-fit': 'cover'});
        }
        reader.readAsDataURL(file);
    });

});

function f_education_train_search() {

    let gbn = $('#condition_gbn option:selected').val();

    if(gbn === '기초정비교육' || gbn === '응급조치교육'){
        //show
        $('#condition_gbn_depth').parent().removeClass('d-none');
    }else{
        //hide
        $('#condition_gbn_depth').parent().addClass('d-none');
        $('#condition_gbn_depth').val('').select2({minimumResultsForSearch: Infinity});
    }

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
    let gbnDepth = $('#condition_gbn_depth option:selected').val();
    if (nullToEmpty(searchText) === '') {
        jsonObj = {
            time: time,
            category: category,
            gbn: gbn,
            gbnDepth: gbnDepth
        };
    } else {
        jsonObj = {
            time: time,
            category: category,
            gbn: gbn,
            gbnDepth: gbnDepth,
            condition: condition,
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
    jb.each(function (index, item) {
        let itemText = $(item).text();
        let itemText_trim = itemText.replaceAll(' ', '');
        if (itemText_trim !== '' && !itemText.match('Actions')) {
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

function f_education_train_search_condition_init() {
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_time').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_category').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_gbn').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_gbn_depth').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_education_train_search();
}

function f_education_train_detail_modal_set(seq) {
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/education/train/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);

    document.querySelector('#md_gbn').value = resData.gbn;
    document.querySelector('#md_gbn_depth').value = nvl(resData.gbnDepth,'-');
    document.querySelector('#md_next_time').value = resData.nextTime;
    document.querySelector('#md_train_start_dttm').value = resData.trainStartDttm;
    document.querySelector('#md_train_end_dttm').value = resData.trainEndDttm;
    document.querySelector('#md_apply_start_dttm').value = resData.applyStartDttm;
    document.querySelector('#md_apply_end_dttm').value = resData.applyEndDttm;
    document.querySelector('#md_pay_sum').value = resData.paySum;
    document.querySelector('#md_train_cnt').value = resData.trainCnt;
    document.querySelector('#md_train_apply_cnt').value = resData.trainApplyCnt;
    document.querySelector('#md_train_note').innerHTML = resData.trainNote;

    $('input[type=radio][name=md_exposure_yn][value=' + resData.exposureYn + ']').prop('checked', true);
    $('input[type=radio][name=md_schedule_exposure_yn][value=' + resData.scheduleExposureYn + ']').prop('checked', true);
    $('input[type=radio][name=md_closing_yn][value=' + resData.closingYn + ']').prop('checked', true);

}

function f_education_train_modify_init_set(seq) {
    window.location.href = '/mng/education/train/detail.do?seq=' + seq;
}

function f_education_train_remove(seq) {
    //console.log('삭제버튼');
    if (nullToEmpty(seq) !== '') {
        Swal.fire({
            title: '[교육 삭제]',
            html: '삭제 시, 교육을 신청한 모든 신청자의 결제 및 신청서 수정이 불가하게 됩니다.<br>그래도 삭제하시겠습니까?',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
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
            }
        });

    }

}

function f_education_train_save(seq) {
    // 1. 유효성 검사
    if (!f_education_train_valid()) return;

    // ★ 핵심: 초기 진입 시 신규 등록(빈값)이었는지 여부를 저장
    let isNewRegistration = (nvl(seq, '') === '');

    Swal.fire({
        title: '저장',
        text: "입력된 정보를 저장하시겠습니까?",
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            var fileInput = $('#thumbFileObj')[0];

            // 2. 썸네일 파일이 있는 경우: 업로드 먼저 수행
            if (fileInput && fileInput.files.length > 0) {
                var formData = new FormData();
                formData.append("thumbFile", fileInput.files[0]);
                formData.append("seq", seq); // 현재 seq 전달 (신규면 빈값)

                $.ajax({
                    url: '/mng/education/train/uploadThumb.do',
                    type: 'POST',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function(res) {
                        if (res.resultCode === "0") {
                            // 업로드 성공!
                            // 1) 파일 ID 바인딩
                            $('#thumbFileId').val(res.customValue);

                            // 2) 신규 등록이었다면, 미리 발급받은 교육 ID(seq)를 폼에 바인딩
                            //    (이래야 insertTrain 호출 시 이 ID를 사용하여 저장함)
                            if (isNewRegistration && res.customValue2) {
                                $('input[name=seq]').val(res.customValue2);
                                // 주의: 여기서 seq 변수를 업데이트하지만, '신규 등록'이라는 사실(isNewRegistration)은 변하지 않음
                                seq = res.customValue2;
                            }

                            // 3. 실제 교육 정보 저장 요청
                            fn_actual_save_process(seq, isNewRegistration);

                        } else {
                            Swal.fire('오류', '이미지 업로드 실패: ' + res.resultMessage, 'error');
                        }
                    },
                    error: function() {
                        Swal.fire('오류', '서버 통신 오류 (파일 업로드)', 'error');
                    }
                });
            } else {
                // 파일 없으면 바로 저장
                fn_actual_save_process(seq, isNewRegistration);
            }
        }
    });
}

// [신규] 실제 DB 저장/수정 처리 함수 (기존 로직 분리)
function fn_actual_save_process(seq, isInsertFlag) {
    /* form data setting (thumbFileId가 포함됨) */
    let resData = f_education_train_form_data_setting();
    resData.gbn = $('#gbn').val();

    // 신규 등록인데 seq가 있다면(파일 업로드로 생성됨), resData에도 반영
    let currentSeq = $('input[name=seq]').val();
    if(currentSeq) {
        resData.seq = currentSeq;
        seq = currentSeq; // 변수도 동기화
    }

    // ★ URL 결정 로직 변경
    // 기존: seq가 있으면 무조건 Update (오류 원인)
    // 수정: isInsertFlag가 true면(최초 버튼 클릭 시 빈값이었으면) 무조건 Insert 수행
    let url = '';
    if (isInsertFlag === true) {
        url = '/mng/education/train/insert.do';
    } else {
        url = (nvl(seq, '') === '') ? '/mng/education/train/insert.do' : '/mng/education/train/update.do';
    }

    // 조기마감 체크 등 기존 로직...
    let preClosingYn = $('#preClosingYn').val();
    if (preClosingYn === 'N' && resData.closingYn === 'Y') {
        Swal.fire({
            title: '교육 정보 변경',
            html: '교육 조기마감 시, 교육을 신청하였으나 결제하지 않은 모든 신청자의 결제가 불가하게 되며,<br>이들의 신청 내역이 취소됩니다.<br>그래도 조기 마감하시겠습니까?',
            icon: 'warning',
            allowOutsideClick: false,
            showCancelButton: true,
            confirmButtonColor: '#00a8ff',
            confirmButtonText: '변경',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((res) => {
            if(res.isConfirmed) sendData(url, resData);
        });
    } else {
        sendData(url, resData);
    }
}

// [신규] AJAX 호출 함수 (중복 제거)
function sendData(url, data) {
    $.ajax({
        url: url,
        method: 'POST',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (res) {
            if (res.resultCode === "0") {
                Swal.fire('성공', '저장되었습니다.', 'success').then(() => {
                    location.href = '/mng/education/train.do';
                });
            } else {
                Swal.fire('실패', res.resultMessage, 'error');
            }
        }
    });
}

function f_education_train_form_data_setting() {

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    // 교육과정명
    form.gbn = $('#gbn').val();

    // 세부교육과정명
    form.gbnDepth = $('#gbnDepth').val();

    // 차시
    form.nextTime = nvl($('#nextTime').val(),'1');

    // 카테고리
    let category = '전체';
    if (nvl(form.gbn, '') !== '') {
        switch (form.gbn) {
            case '해상엔진 테크니션 (선내기/선외기)':
            case 'FRP 레저보트 선체 정비 테크니션':
            case '마리나 선박 선외기 정비사 실무과정':
            case '마리나 선박 선내기 정비사 실무과정':
                category = '정규과정';
                break;
            case '선외기 기초정비실습 과정':
            case '선내기 기초정비실습 과정':
            case '세일요트 기초정비실습 과정':
            case '자가정비 심화과정 (고마력 선외기)':
            case '기초정비교육':
            case '응급조치교육':
            case '선외기/선내기 직무역량 강화과정':
                category = '단기과정';
                break;
            case '고마력 선외기 정비 중급 테크니션':
            case '고마력 선외기 정비 중급 테크니션 (특별반)':
            case '스턴드라이브 정비 전문가과정':
            case '스턴드라이브 정비 전문가과정 (특별반)':
            case '발전기 정비 교육':
                category = '심화과정';
                break;
            case '선내기 팸투어':
            case '선외기 팸투어':
                category = '팸투어';
                break;
            case '레저선박 해양전자장비 교육':
                category = '협업';
                break;
            default:
                break;
        }
    }

    form.category = category;

    return form;
}

function f_education_train_valid() {
    let gbn = document.querySelector('#gbn').value;
    let trainStartDttm = document.querySelector('#trainStartDttm').value;
    let trainEndDttm = document.querySelector('#trainEndDttm').value;
    let applyStartDttm = document.querySelector('#applyStartDttm').value;
    let applyEndDttm = document.querySelector('#applyEndDttm').value;
    let paySum = document.querySelector('#paySum').value;
    let trainCnt = document.querySelector('#trainCnt').value;

    if (nvl(gbn, '- 교육과정명 선택 -') === '- 교육과정명 선택 -') {
        showMessage('', 'error', '[ 등록 정보 ]', '교육과정명을 선택해 주세요.', '');
        return false;
    }else{
        if(gbn === '기초정비교육' || gbn === '응급조치교육'){
            let gbnDepth = document.querySelector('#gbnDepth').value;
            if (nvl(gbnDepth, '- 세부교육명 선택 -') === '- 세부교육명 선택 -') {
                showMessage('', 'error', '[ 등록 정보 ]', '세부교육명을 선택해 주세요.', '');
                return false;
            }
        }
    }
    if (nvl(trainStartDttm, '') === '') {
        showMessage('', 'error', '[ 등록 정보 ]', '교육일정 (시작)일을 선택해 주세요.', '');
        return false;
    }
    if (nvl(trainEndDttm, '') === '') {
        showMessage('', 'error', '[ 등록 정보 ]', '교육일정 (종료)일을 선택해 주세요.', '');
        return false;
    }
    if (trainStartDttm > trainEndDttm) {
        showMessage('', 'error', '[ 등록 정보 ]', '교육일정 종료일을 시작일보다 뒤로 선택해 주세요.', '');
        return false;
    }
    if (nvl(applyStartDttm, '') === '') {
        showMessage('', 'error', '[ 등록 정보 ]', '접수기간 (시작)일을 선택해 주세요.', '');
        return false;
    }
    if (nvl(applyEndDttm, '') === '') {
        showMessage('', 'error', '[ 등록 정보 ]', '접수기간 (종료)일을 선택해 주세요.', '');
        return false;
    }
    if (applyStartDttm > applyEndDttm) {
        showMessage('', 'error', '[ 등록 정보 ]', '접수기간 종료일을 시작일보다 뒤로 선택해 주세요.', '');
        return false;
    }

    if(!gbn.toString().includes('마리나')){
        if (nvl(paySum, '') === '') {
            showMessage('', 'error', '[ 등록 정보 ]', '교육비를 입력해 주세요.', '');
            return false;
        }
        if (nvl(trainCnt, '') === '') {
            showMessage('', 'error', '[ 등록 정보 ]', '총 교육인원을 입력해 주세요.', '');
            return false;
        }
    }

    return true;
}

function f_education_train_early_closing(seq) {
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

function f_education_train_apply_list(gbn, nextTime, trainApplyCnt, applicationSystemType){
    //console.log(gbn, nextTime, trainApplyCnt);

    if(trainApplyCnt === '0'){
        showMessage('', 'info', '[ 신청명단보기 ]', '현재 신청자가 없는 교육입니다.', '');
        return;
    }

    let link = '';
    if(applicationSystemType === 'UNIFIED'){
        link = '/mng/customer/unified.do';
    }else{
        switch (gbn){
            case '상시신청':
                link = '/mng/customer/regular.do'
                break;
            case '해상엔진 테크니션 (선내기/선외기)':
                link = '/mng/customer/boarder.do';
                break;
            case 'FRP 레저보트 선체 정비 테크니션':
                link = '/mng/customer/frp.do';
                break;
            case '선외기 기초정비실습 과정':
                link = '/mng/customer/outboarder.do';
                break;
            case '선내기 기초정비실습 과정':
                link = '/mng/customer/inboarder.do';
                break;
            case '세일요트 기초정비실습 과정':
                link = '/mng/customer/sailyacht.do';
                break;
            case '고마력 선외기 정비 중급 테크니션':
                link = '/mng/customer/highhorsepower.do';
                break;
            case '자가정비 심화과정 (고마력 선외기)':
                link = '/mng/customer/highself.do';
                break;
            case '고마력 선외기 정비 중급 테크니션 (특별반)':
                link = '/mng/customer/highspecial.do';
                break;
            case '스턴드라이브 정비 전문가과정':
                link = '/mng/customer/sterndrive.do';
                break;
            case '스턴드라이브 정비 전문가과정 (특별반)':
                link = '/mng/customer/sternspecial.do';
                break;
            case '기초정비교육':
                link = '/mng/customer/basic.do';
                break;
            case '응급조치교육':
                link = '/mng/customer/emergency.do';
                break;
            case '발전기 정비 교육':
                link = '/mng/customer/generator.do';
                break;
            case '선외기/선내기 직무역량 강화과정':
                link = '/mng/customer/competency.do';
                break;
            case '선내기 팸투어':
                link = '/mng/customer/famtourin.do';
                break;
            case '선외기 팸투어':
                link = '/mng/customer/famtourout.do';
                break;
            case '레저선박 해양전자장비 교육':
                link = '/mng/customer/electro.do';
                break;
            default:
                break;
        }
    }

    if(nvl(link,'') !== ''){
        if(gbn === '상시신청' || applicationSystemType === 'UNIFIED'){
            window.location.href = link;
        }else{
            window.location.href = link + '?nextTime=' + nextTime;
        }
    }else{
        showMessage('', 'info', '[ 신청명단보기 ]', '신청 명단 페이지가 존재하지 않습니다.', '');
    }
}