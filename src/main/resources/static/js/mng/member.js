/***
 * mng/customer/member
 * 회원/신청>회원관리>전체회원목록
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

});

function f_customer_member_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_customer_member_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let grade = $('#condition_grade option:selected').val();
    let keyword = $('#condition_keyword option:selected').val();
    let sex = $('#condition_sex option:selected').val();
    if(nullToEmpty(searchText) === ''){
        jsonObj = {
            grade: grade,
            keyword: keyword,
            sex: sex
        };
    }else{
        jsonObj = {
            grade: grade ,
            keyword: keyword,
            sex: sex,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/customer/member/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_customer_member_table tbody td');
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

function f_customer_member_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_grade').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_keyword').val('').select2({minimumResultsForSearch: Infinity});
    $('#condition_sex').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_customer_member_search();
}

function f_customer_member_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/customer/member/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);
    document.querySelector('#md_grade').value = resData.grade;
    document.querySelector('#md_id').value = resData.id;
    document.querySelector('#md_name').value = resData.name;
    document.querySelector('#md_phone').value = resData.phone;
    document.querySelector('#md_email').value = resData.email;

    if(nvl(resData.keyword,'') !== ''){
        let keywordArr = resData.keyword.toString().split(',');
        for(let i=0; i<keywordArr.length; i++){
            $('input[type=checkbox][name=md_keyword][value=' + keywordArr[i] +']').prop('checked', true);
        }
    }else{
        $('input[type=checkbox][name=md_keyword]').prop('checked', false);
    }

    $('input[type=radio][name=md_sms_yn][value=' + resData.smsYn + ']').prop('checked',true);

}

function f_customer_member_remove(seq){
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
                        targetTable: 'member',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_customer_member_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_customer_member_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

        /*let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 회원을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/customer/member/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '회원 삭제', '회원이 삭제되었습니다.', '');
                    f_customer_member_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '회원 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });*/
    }
}

function f_customer_member_modify_init_set(seq){
    window.location.href = '/mng/customer/member/detail.do?seq=' + seq;
}

function f_customer_member_save(seq){
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
            let validCheck = f_customer_member_valid();

            if(validCheck){

                /* form data setting */
                let data = f_customer_member_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/customer/member/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '회원 정보 변경',
                                    text: "회원 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_customer_member_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '회원 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/customer/member/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '회원 정보 등록',
                                    text: "회원 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/customer/member.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '회원 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_customer_member_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    //이메일
    form.email = form.email + '@' + $('#domain').val();

    //관심키워드
    let keyword = '';
    let keywordArr = form.keyword;
    let keywordArrLen = keywordArr.length;
    for(let i=0; i<keywordArrLen; i++){
        keyword += keywordArr[i];
        if((i+1) !== keywordArrLen){
            keyword += ',';
        }
    }
    form.keyword = keyword;

    // 회원등급
    form.grade = '일반회원';

    // SMS 알림서비스 동의
    let smsYn = $('input[type=radio][name=smsYn]:checked').val();
    if(smsYn === '1'){
        form.smsYn = '1';

        // SMS 동의 시 일반 -> 관심 등급 업그레이드
        form.grade = '관심사용자';
    }else{
        form.smsYn = '0';
    }

    return JSON.stringify(form);
}

function f_customer_member_valid(){
    let id = document.querySelector('#id').value;
    let password = document.querySelector('#password').value;
    let name = document.querySelector('#name').value;
    let phone = document.querySelector('#phone').value;
    let email = document.querySelector('#email').value;
    let domain = document.querySelector('#domain').value;
    let keywordArr = $('input[type=checkbox][name=keyword]:checked');

    if(nvl(id,"") === ""){ showMessage('', 'error', '[등록 정보]', '아이디를 입력해 주세요.', ''); return false; }
    if(nvl(password,"") === ""){ showMessage('', 'error', '[등록 정보]', '비밀번호를 입력해 주세요.', ''); return false; }
    if(nvl(name,"") === ""){ showMessage('', 'error', '[등록 정보]', '이름을 입력해 주세요.', ''); return false; }
    if(nvl(phone,"") === ""){ showMessage('', 'error', '[등록 정보]', '연락처를 입력해 주세요.', ''); return false; }
    if(nvl(email,"") === ""){ showMessage('', 'error', '[등록 정보]', '이메일을 입력해 주세요.', ''); return false; }
    if(nvl(domain,"") === ""){ showMessage('', 'error', '[등록 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }
    if(keywordArr.length === 0){ showMessage('', 'error', '[등록 정보]', '관심 키워드를 하나 이상 선택해 주세요.', ''); return false; }

    return true;
}