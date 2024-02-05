/***
 * mng/adminMng/admin
 * 관리자 관리
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

function f_mng_admin_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_mng_admin_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let blockYnVal = $('#condition_block_yn').is(':checked');
    let blockYn = '';
    if(blockYnVal === true){
        blockYn = 'Y';
    }
    if(nullToEmpty(searchText) === ""){
        jsonObj = {
            blockYn: blockYn
        };
    }else{
        jsonObj = {
            blockYn: blockYn ,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/adminMng/admin/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_mng_admin_table tbody td');
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

function f_mng_admin_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_block_yn').prop('checked',false);

    /* 재조회 */
    f_mng_admin_search();
}

function f_mng_admin_detail_modal_set(seq){
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

function f_mng_admin_remove(seq){
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
                        targetTable: 'admin',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_mng_admin_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_mng_admin_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_mng_admin_modify_init_set(seq){
    window.location.href = '/mng/adminMng/admin/detail.do?seq=' + seq;
}

function f_mng_admin_save(seq){
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
            let validCheck = f_mng_admin_valid(seq);

            if(validCheck){

                /* form data setting */
                let data = f_mng_admin_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/adminMng/admin/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '관리자 정보 변경',
                                    text: '관리자 정보가 변경되었습니다.',
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_mng_admin_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '관리자 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/adminMng/admin/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '관리자 정보 등록',
                                    text: "관리자 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/adminMng/admin.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '관리자 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_mng_admin_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    //이메일
    form.cpEmail = form.email + '@' + $('#domain').val();

    //Ip
    form.ipAddress = form.ipAddress1 + '.' + form.ipAddress2 + '.' + form.ipAddress3 + '.' + form.ipAddress4;

    return JSON.stringify(form);
}

function f_mng_admin_valid(seq){
    let gbnArr = $('input[type=radio][name=gbn]:checked');
    if(gbnArr.length === 0){ showMessage('', 'error', '[등록 정보]', '관리자 레벨을 선택해 주세요.', ''); return false; }

    if(nvl(seq,'') === ''){
        let idCheck = $('#idCheck').val();
        let pwCheck = $('#pwCheck').val();
        if(idCheck === 'false'){ showMessage('', 'error', '[등록 정보]', '아이디 중복체크해 주세요.', ''); return false; }
        if(pwCheck === 'false'){ showMessage('', 'error', '[등록 정보]', '비밀번호를 검사해 주세요.', ''); return false; }
    }

    let id = $('#id').val();
    if(nvl(id,'') === ''){ showMessage('', 'error', '[등록 정보]', '아이디를 입력해 주세요.', ''); return false; }

    let password = $('#password').val();
    if(nvl(password,'') === ''){ showMessage('', 'error', '[등록 정보]', '비밀번호를 입력해 주세요.', ''); return false; }
    
    let cpName = $('#cpName').val();
    if(nvl(cpName,'') === ''){ showMessage('', 'error', '[등록 정보]', '이름을 입력해 주세요.', ''); return false; }

    let cpPhone = $('#cpPhone').val();
    if(nvl(cpPhone,'') === ''){ showMessage('', 'error', '[등록 정보]', '휴대전화를 입력해 주세요.', ''); return false; }

    let email = $('#email').val();
    let domain = $('#domain').val();
    if(nvl(email,'') === ''){ showMessage('', 'error', '[등록 정보]', '이메일을 입력해 주세요.', ''); return false; }
    if(nvl(domain,'') === ''){ showMessage('', 'error', '[등록 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }

    let cpDepart = $('#cpDepart').val();
    if(nvl(cpDepart,'') === ''){ showMessage('', 'error', '[등록 정보]', '소속(부서)를 입력해 주세요.', ''); return false; }

    let blockYnRadio = $('input[type=radio][name=blockYn]:checked');
    if(blockYnRadio.length === 0){ showMessage('', 'error', '[등록 정보]', '접속 허용/차단 여부를 선택해 주세요.', ''); return false; }

    let ipAddress1 = $('#ipAddress1').val();
    let ipAddress2 = $('#ipAddress2').val();
    let ipAddress3 = $('#ipAddress3').val();
    let ipAddress4 = $('#ipAddress4').val();

    if(nvl(ipAddress1,'') === '' || nvl(ipAddress2,'') === '' || nvl(ipAddress3,'') === '' || nvl(ipAddress4,'') === ''){
        showMessage('', 'error', '[등록 정보]', '올바른 Ip 주소를 입력해 주세요.', ''); return false;
    }

    return true;
}

function f_mng_admin_id_duplicate_check(){
    // ID
    let id = document.querySelector('#id').value;

    if(nvl(id,'') !== ''){
        if(id.length < 5 || id.length > 12){
            $('.id_valid_result_cmnt').css('color', '#AD1D1D');
            $('.id_valid_result_cmnt').html('5 ~ 12자리 이내로 입력해 주세요.');
            $('#idCheck').val('false');
            return;
        }

        // ID 중복체크
        let jsonStr = { id : id };
        let checkDuplicateId = ajaxConnect('/mng/adminMng/admin/checkDuplicateId.do', 'post', jsonStr);
        if(checkDuplicateId !== 0){
            $('.id_valid_result_cmnt').css('color', '#AD1D1D');
            $('.id_valid_result_cmnt').html('사용할 수 없는 아이디입니다. 해당 아이디로 이미 가입된 회원이 존재합니다.');
            $('#idCheck').val('false');
        }else{
            $('.id_valid_result_cmnt').css('color', '#1D5CAD');
            $('.id_valid_result_cmnt').html('사용 가능한 아이디입니다.');
            $('#idCheck').val('true');
        }
    }
}

function f_mng_admin_id_status_change(){
    $('.id_valid_result_cmnt').css('color', '#AD1D1D');
    $('.id_valid_result_cmnt').text('중복체크 버튼을 클릭해 주세요.');

    $('#idCheck').val('false');
}

function f_mng_admin_pw_status_change(){
    $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
    $('.pw_valid_result_cmnt').text('비밀번호 검사 버튼을 클릭해 주세요.');

    $('#passwordCheck').val('');

    $('#pwCheck').val('false');
}

function f_mng_admin_pw_check(){
    let pw = $("#password").val();
    let number = pw.search(/[0-9]/g);
    let english = pw.search(/[a-z]/ig);
    let space = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{9,16}$/;

    if (pw.length < 8 && pw.length > 17) {
        $('.pw_valid_result_cmnt').html('8자리 이상, 16자리 이내로 입력해주세요.');
        $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if (pw.search(/\s/) !== -1) {
        $('.pw_valid_result_cmnt').html('비밀번호는 공백 없이 입력해주세요.');
        $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if (number < 0 || english < 0 || space < 0) {
        $('.pw_valid_result_cmnt').html('영문, 숫자, 특수문자를 혼합하여 입력해주세요.');
        $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if ((number < 0 && english < 0) || (english < 0 && space < 0) || (space < 0 && number < 0)) {
        $('.pw_valid_result_cmnt').html('영문, 숫자, 특수문자를 혼합하여 입력해주세요.');
        $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if (/(\w)\1\1\1/.test(pw)) {
        $('.pw_valid_result_cmnt').html('같은 문자를 4번 이상 사용하실 수 없습니다.');
        $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    }

    if (false === reg.test(pw)) {
        $('.pw_valid_result_cmnt').html('비밀번호는 9~16자리 이어야 하며, 숫자/영문/특수문자를 모두 포함해야 합니다.');
        $('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else {
        $('.pw_valid_result_cmnt').html('비밀번호가 정상적으로 입력되었습니다.');
        $('.pw_valid_result_cmnt').css('color', '#1D5CAD');
        $('#pwCheck').val('true');
    }
}

function f_mng_admin_block_yn_btn(){
    //$('input[type=checkbox][name=blockYn]:checked').eq(0).val();
}

function getIP(json) {
    let myIp = json.ip;
    let ipArr = [
        '211.208.232.85',
        '58.239.97.142',
        '175.210.117.1', /* 수자원 */
        '118.36.143.89' /* 미팅팬 */
    ];
    let pass = false;
    for(let i=0; i<ipArr.length; i++){
        if(myIp === ipArr[i]){
            pass = true;
            break;
        }
    }

    if(!pass){
        alert('EDU marine 관리자 시스템에 접근 권한이 없는 IP입니다.\n메인페이지로 이동합니다.');
        window.location.href = '/';
    }
}