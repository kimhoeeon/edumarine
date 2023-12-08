/***
 * mng/customer/regular
 * 회원/신청>신청자목록>상시사전신청
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
    $('#month_select').on('change', function () {
        $('#day_select').val('');

        lastDay(); //년과 월에 따라 마지막 일 구하기
    });

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

    /* 재조회 */
    f_customer_regular_search();
}

function f_customer_regular_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/customer/regular/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);

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
    document.querySelector('#md_desired_education_time').value = resData.desiredEducationTime;
    document.querySelector('#md_major').value = resData.major;

    $('input[type=radio][name=md_experience_yn][value=' + resData.experienceYn + ']').prop('checked',true);
}

function f_customer_regular_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
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

                let resData = ajaxConnect('/mng/customer/regular/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '신청 내역 삭제', '신청 내역이 삭제되었습니다.', '');
                    f_customer_regular_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '신청 내역 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
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
    let name = document.querySelector('#name').value;
    let phone = document.querySelector('#phone').value;
    let email = document.querySelector('#email').value;
    let domain = document.querySelector('#domain').value;
    let year = $('#year_select').val();
    let month = $('#month_select').val();
    let day = $('#day_select').val();
    let region = document.querySelector('#region').value;
    let participationPathArr = $('input[type=radio][name=participationPath]:checked');
    let first_field_select = $('#first_field_select').val();
    let second_field_select = $('#second_field_select').val();

    if(nvl(name,'') === ''){ showMessage('', 'error', '[등록 정보]', '이름을 입력해 주세요.', ''); return false; }
    if(nvl(phone,'') === ''){ showMessage('', 'error', '[등록 정보]', '연락처를 입력해 주세요.', ''); return false; }
    if(nvl(email,'') === ''){ showMessage('', 'error', '[등록 정보]', '이메일을 입력해 주세요.', ''); return false; }
    if(nvl(domain,'') === ''){ showMessage('', 'error', '[등록 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }
    if(nvl(year,'') === ''){ showMessage('', 'error', '[등록 정보]', '생년월일-연도를 선택해 주세요.', ''); return false; }
    if(nvl(month,'') === ''){ showMessage('', 'error', '[등록 정보]', '생년월일-월을 선택해 주세요.', ''); return false; }
    if(nvl(day,'') === ''){ showMessage('', 'error', '[등록 정보]', '생년월일-일을 선택해 주세요.', ''); return false; }
    if(nvl(region,'') === ''){ showMessage('', 'error', '[등록 정보]', '거주지역을 입력해 주세요.', ''); return false; }
    if(participationPathArr.length === 0){ showMessage('', 'error', '[등록 정보]', '참여경로를 하나 이상 선택해 주세요.', ''); return false; }
    if(nvl(first_field_select,'') === ''){ showMessage('', 'error', '[등록 정보]', '1순위 신청분야를 선택해 주세요.', ''); return false; }
    if(nvl(second_field_select,'') === ''){ showMessage('', 'error', '[등록 정보]', '2순위 신청분야를 선택해 주세요.', ''); return false; }

    return true;
}