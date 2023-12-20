/***
 * mng/customer/inboarder
 * 회원/신청>신청자목록>자가정비(선내기)
 * */

$(function(){

    // 이메일
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
    $('#year_select, #month_select').on('change', function () {
        lastDay('1'); //년과 월에 따라 마지막 일 구하기
    });

    // 초기값
    lastDay($('#birthDay').val());

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

function f_customer_inboarder_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_customer_inboarder_table').DataTable();
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

    let resData = ajaxConnect('/mng/customer/inboarder/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_customer_inboarder_table tbody td');
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

function f_customer_inboarder_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_customer_inboarder_search();
}

function f_customer_inboarder_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/customer/inboarder/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);

    document.querySelector('#md_name_ko').value = resData.nameKo;
    document.querySelector('#md_name_en').value = resData.nameEn;
    document.querySelector('#md_phone').value = resData.phone;
    document.querySelector('#md_email').value = resData.email;
    document.querySelector('#md_birth_year').value = resData.birthYear;
    document.querySelector('#md_birth_month').value = resData.birthMonth;
    document.querySelector('#md_birth_day').value = resData.birthDay;
    document.querySelector('#md_address').value = resData.address;
    document.querySelector('#md_address_detail').value = resData.addressDetail;

    $('input[type=radio][name=md_clothes_size][value=' + resData.clothesSize + ']').prop('checked',true);
    $('input[type=radio][name=md_participation_path][value=' + resData.participationPath + ']').prop('checked',true);
}

function f_customer_inboarder_remove(seq){
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
                    targetTable: 'inboarder',
                    deleteReason: result.value,
                    targetMenu: getTargetMenu('mng_customer_inboarder_table'),
                    delYn: 'Y'
                }
                f_mng_trash_remove(jsonObj);

                f_customer_inboarder_search(); // 재조회

            }else{
                alert('삭제 사유를 입력해주세요.');
            }
        });

        /*let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 신청 내역을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/customer/inboarder/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '신청 내역 삭제', '신청 내역이 삭제되었습니다.', '');
                    f_customer_inboarder_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '신청 내역 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });*/
    }
}

function f_customer_inboarder_modify_init_set(seq){
    window.location.href = '/mng/customer/inboarder/detail.do?seq=' + seq;
}

function f_customer_inboarder_save(seq){
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
            let validCheck = f_customer_inboarder_valid();

            if(validCheck){

                /* form data setting */
                let data = f_customer_inboarder_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/customer/inboarder/update.do',
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
                                        f_customer_inboarder_modify_init_set(seq); // 재조회
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
                        url: '/mng/customer/inboarder/insert.do',
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
                                        window.location.href = '/mng/customer/inboarder.do'; // 목록으로 이동
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

function f_customer_inboarder_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    //이메일
    form.email = form.email + '@' + $('#domain').val();

    return JSON.stringify(form);
}

function f_customer_inboarder_valid(){
    let nameKo = document.querySelector('#nameKo').value;
    let nameEn = document.querySelector('#nameEn').value;
    let phone = document.querySelector('#phone').value;
    let email = document.querySelector('#email').value;
    let domain = document.querySelector('#domain').value;
    let year = $('#year_select').val();
    let month = $('#month_select').val();
    let day = $('#day_select').val();
    let address = document.querySelector('#address').value;
    let addressDetail = document.querySelector('#addressDetail').value;
    let clothesSizeArr = $('input[type=radio][name=clothesSize]:checked');
    let participationPathArr = $('input[type=radio][name=participationPath]:checked');

    if(nvl(nameKo,'') === ''){ showMessage('', 'error', '[등록 정보]', '이름(국문)을 입력해 주세요.', ''); return false; }
    if(nvl(nameEn,'') === ''){ showMessage('', 'error', '[등록 정보]', '이름(영문)을 입력해 주세요.', ''); return false; }
    if(nvl(phone,'') === ''){ showMessage('', 'error', '[등록 정보]', '연락처를 입력해 주세요.', ''); return false; }
    if(nvl(email,'') === ''){ showMessage('', 'error', '[등록 정보]', '이메일을 입력해 주세요.', ''); return false; }
    if(nvl(domain,'') === ''){ showMessage('', 'error', '[등록 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }
    if(nvl(year,'') === ''){ showMessage('', 'error', '[등록 정보]', '생년월일-연도를 선택해 주세요.', ''); return false; }
    if(nvl(month,'') === ''){ showMessage('', 'error', '[등록 정보]', '생년월일-월을 선택해 주세요.', ''); return false; }
    if(nvl(day,'') === ''){ showMessage('', 'error', '[등록 정보]', '생년월일-일을 선택해 주세요.', ''); return false; }
    if(nvl(address,'') === ''){ showMessage('', 'error', '[등록 정보]', '주소를 입력해 주세요.', ''); return false; }
    if(nvl(addressDetail,'') === ''){ showMessage('', 'error', '[등록 정보]', '상세주소를 입력해 주세요.', ''); return false; }
    if(clothesSizeArr.length === 0){ showMessage('', 'error', '[등록 정보]', '작업복 사이즈를 하나 이상 선택해 주세요.', ''); return false; }
    if(participationPathArr.length === 0){ showMessage('', 'error', '[등록 정보]', '참여경로를 하나 이상 선택해 주세요.', ''); return false; }

    return true;
}