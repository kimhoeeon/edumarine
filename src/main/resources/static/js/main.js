var transferYear = new Date().getFullYear() + 1;

$(function(){

    $('#id').on('blur keyup', function(event){

        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            let inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^a-zA-Z0-9]/gi, ''));
        }

    });

    $('#passwordCheck').on('blur keyup', function(event){
        let pw = $('#password').val();
        let pwCheck = $('#passwordCheck').val();
        if(pw !== '' && pwCheck !== ''){
            if(pw !== pwCheck){
                $(this).siblings('.pw_check_valid_result_cmnt').html('비밀번호가 일치하지 않습니다.');
                $(this).siblings('.pw_check_valid_result_cmnt').css('color', '#AD1D1D');
                $('#pwConfirmCheck').val('false');
            }else{
                $(this).siblings('.pw_check_valid_result_cmnt').html('비밀번호가 일치합니다.');
                $(this).siblings('.pw_check_valid_result_cmnt').css('color', '#1D5CAD');
                $('#pwConfirmCheck').val('true');
            }
        }
    });

    // 파일 입력 변경에 대한 이벤트 핸들러 추가
    $('.upload_hidden').on('change', function () {

        let fileName = $(this).val();
        if(nvl(fileName,'') !== ''){
            fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();

            let acceptArr = $(this).attr('accept').toString().replaceAll('.','').split(', ');
            if(!acceptArr.includes(fileName)){
                let alertMsg = '파일 첨부는 ' + $(this).attr('accept').toString() + ' 파일만 가능합니다.';
                alert(alertMsg);
                $(this).val(''); //업로드한 파일 제거
                let fileNameInput = $(this).siblings('.upload_name');
                fileNameInput.val('');
                return;
            }

            if (this.files && this.files[0]) {
                let maxSize = 10 * 1024 * 1024; //* 10MB 사이즈 제한
                let file = this.files[0];
                if (file.size > maxSize) {
                    alert("파일 첨부는 10MB 이내 파일만 가능합니다.");
                    $(this).val(''); //업로드한 파일 제거
                    let fileNameInput = $(this).siblings('.upload_name');
                    fileNameInput.val('');
                } else {
                    let fileName = $(this).val().split('\\').pop();
                    let fileNameInput = $(this).siblings('.upload_name');
                    fileNameInput.val(fileName);
                }
            }
        }else{
            $(this).val(''); //업로드한 파일 제거
            let fileNameInput = $(this).siblings('.upload_name');
            fileNameInput.val('');
        }
    });

})
function home(){
    window.location.href = '/';
}

/**
 * 메인
 * 뉴스레터 구독 Function
 * */
function main_newsletter_subscriber_btn(el){

    let email = $(el).siblings('input[type=email]').val();

    let regex = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");
    if(!regex.test(email)){ showMessage('', 'error', '[뉴스레터 구독]', '올바른 이메일 주소를 입력해 주세요.', ''); return false; }

    Swal.fire({
        title: '[뉴스레터 구독]',
        html: '입력된 이메일로 뉴스레터를 받아보시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '구독하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            let jsonObj = { email: email, sendYn: 'Y' };

            let resData = ajaxConnect('/mng/newsletter/subscriber/insert.do', 'post', jsonObj);
            //console.log(i , resData);
            if (resData.resultCode === "0") {
                Swal.fire({
                    title: '[뉴스레터 구독]',
                    html: '입력하신 이메일로 뉴스레터가 발송됩니다.<br>구독해주셔서 감사합니다.',
                    icon: 'info',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '확인'
                }).then(async (result) => {
                    if (result.isConfirmed) {
                        $(el).siblings('input[type=email]').val('');
                    }
                });
            }

        } // isConfiremd
    }) //Swal
}

/**
 * 회원가입 Function
 * */
function f_id_duplicate_check(el){
    // ID
    let id = document.querySelector('#id').value;

    if(nvl(id,'') !== ''){
        if(id.length < 5 || id.length > 12){
            $(el).siblings('.id_valid_result_cmnt').css('color', '#AD1D1D');
            $(el).siblings('.id_valid_result_cmnt').html('5 ~ 12자리 이내로 입력해 주세요.');
            $('#idCheck').val('false');
            return;
        }

        // ID 중복체크
        let jsonStr = { id : id };
        let checkDuplicateId = ajaxConnect('/checkDuplicateId.do', 'post', jsonStr);
        if(checkDuplicateId !== 0){
            $(el).siblings('.id_valid_result_cmnt').css('color', '#AD1D1D');
            $(el).siblings('.id_valid_result_cmnt').html('사용할 수 없는 아이디입니다. 해당 아이디로 이미 가입된 회원이 존재합니다.');
            $('#idCheck').val('false');
        }else{
            $(el).siblings('.id_valid_result_cmnt').css('color', '#1D5CAD');
            $(el).siblings('.id_valid_result_cmnt').html('사용 가능한 아이디입니다.');
            $('#idCheck').val('true');
        }
    }
}

function f_pw_status_change(el){
    $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
    $('.pw_valid_result_cmnt').text('비밀번호 검사 버튼을 클릭해 주세요.');

    $('#passwordCheck').val('');

    $('#pwCheck').val('false');
    $('#pwConfirmCheck').val('false');
}

function f_pw_check(el){
    let pw = $("#password").val();
    let number = pw.search(/[0-9]/g);
    let english = pw.search(/[a-z]/ig);
    let space = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{9,16}$/;

    if (pw.length < 8 && pw.length > 17) {
        $(el).siblings('.pw_valid_result_cmnt').html('8자리 이상, 16자리 이내로 입력해주세요.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if (pw.search(/\s/) !== -1) {
        $(el).siblings('.pw_valid_result_cmnt').html('비밀번호는 공백 없이 입력해주세요.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if (number < 0 || english < 0 || space < 0) {
        $(el).siblings('.pw_valid_result_cmnt').html('영문, 숫자, 특수문자를 혼합하여 입력해주세요.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if ((number < 0 && english < 0) || (english < 0 && space < 0) || (space < 0 && number < 0)) {
        $(el).siblings('.pw_valid_result_cmnt').html('영문, 숫자, 특수문자를 혼합하여 입력해주세요.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else if (/(\w)\1\1\1/.test(pw)) {
        $(el).siblings('.pw_valid_result_cmnt').html('같은 문자를 4번 이상 사용하실 수 없습니다.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    }

    if (false === reg.test(pw)) {
        $(el).siblings('.pw_valid_result_cmnt').html('비밀번호는 9~16자리 이어야 하며, 숫자/영문/특수문자를 모두 포함해야 합니다.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#AD1D1D');
        $('#pwCheck').val('false');
        return false;
    } else {
        $(el).siblings('.pw_valid_result_cmnt').html('비밀번호가 정상적으로 입력되었습니다.');
        $(el).siblings('.pw_valid_result_cmnt').css('color', '#1D5CAD');
        $('#pwCheck').val('true');
    }
}

function f_main_member_join(){
    
    //Valid Check
    let idCheck = $('#idCheck').val();
    let pwCheck = $('#pwCheck').val();
    let pwConfirmCheck = $('#pwConfirmCheck').val();
    if(idCheck === 'false'){ showMessage('', 'error', '[아이디 중복체크]', '아이디 중복체크해 주세요.', ''); return false; }
    if(pwCheck === 'false'){ showMessage('', 'error', '[비밀번호 검사]', '비밀번호를 검사해 주세요.', ''); return false; }
    if(pwConfirmCheck === 'false'){ showMessage('', 'error', '[비밀번호 확인]', '비밀번호 항목과 비밀번호 확인 항목이 일치하는지 확인해 주세요.', ''); return false; }

    // 개인정보수집 동의 여부
    let privcy_chk = $('#f_privcy_essential').is(':checked');
    if(!privcy_chk){ showMessage('', 'error', '[개인정보수집 동의]', '개인정보수집 동의 항목에 체크해 주세요.', ''); return false; }

    let id = $('#id').val();
    if(nvl(id,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '아이디를 입력해 주세요.', ''); return false; }

    let password = $('#password').val();
    if(nvl(password,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '비밀번호를 입력해 주세요.', ''); return false; }

    let passwordCheck = $('#passwordCheck').val();
    if(nvl(passwordCheck,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '비밀번호 확인을 입력해 주세요.', ''); return false; }

    let name = $('#name').val();
    if(nvl(name,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '이름을 입력해 주세요.', ''); return false; }

    let phone = $('#phone').val();
    if(nvl(phone,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '연락처를 입력해 주세요.', ''); return false; }

    let email = $('#email').val();
    if(nvl(email,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '이메일을 입력해 주세요.', ''); return false; }

    let domain = $('#domain').val();
    if(nvl(domain,'') === ''){ showMessage('', 'error', '[회원가입 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }

    let keywordCheckbox = $('input[type=checkbox][name=keyword]:checked');
    if(keywordCheckbox.length === 0){ showMessage('', 'error', '[회원가입 정보]', '관심 키워드를 하나 이상 선택해 주세요.', ''); return false; }

    // form
    let form = JSON.parse(JSON.stringify($('#joinForm').serializeObject()));

    //이메일
    form.email = email + '@' + domain;

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
    let smsYn = $('#smsYn').is(':checked');
    if(smsYn){
        form.smsYn = '1';

        // SMS 동의 시 일반 -> 관심 등급 업그레이드
        form.grade = '관심사용자';
    }else{
        form.smsYn = '0';
    }

    Swal.fire({
        title: '[회원가입]',
        html: '입력된 정보로 회원가입을 하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '제출하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {

            $.ajax({
                url: '/member/join/insert.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(form),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        window.location.href = '/member/complete.do';
                    } else {
                        showMessage('', 'error', '에러 발생', '회원가입을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function loginFormSubmit() {
    let form = document.getElementById('login_form');
    let id = $('#login_id').val();
    let password = $('#login_password').val();

    if (nvl(id,'') === '' || nvl(password,'') === '') {
        showMessage('', 'info', '입력 정보 확인', '아이디와 비밀번호를 입력해 주세요.', '');
        return false;
    }

    let jsonObj = {
        id: id,
        password: password
    };

    $.ajax({
        url: '/member/login/submit.do',
        method: 'post',
        data: JSON.stringify(jsonObj),
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    }).done(function (data) {
        if (data.resultCode === "0") {
            let hiddenField_id = document.createElement('input');
            hiddenField_id.type = 'hidden';
            hiddenField_id.name = 'id';
            hiddenField_id.value = id;
            let hiddenField_pw = document.createElement('input');
            hiddenField_pw.type = 'hidden';
            hiddenField_pw.name = 'password';
            hiddenField_pw.value = password;

            form.appendChild(hiddenField_id); //아이디
            form.appendChild(hiddenField_pw); //비밀번호

            document.body.appendChild(form);

            sessionStorage.setItem('id', id);

            form.submit(); // / 메인페이지로 이동
        } else {
            showMessage('', 'info', '로그인 실패', '아이디와 비밀번호를 확인해주세요.', '');
        }
    }).fail(function (xhr, status, errorThrown) {
        alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + errorThrown + "\n상태 : " + status);
    })

}

function f_pw_init(){
    let id = $('#init_id').val();

    if(nvl(id,'') === ''){
        showMessage('#init_id', 'error', '[회원 정보]', '아이디를 입력해주세요.', '');
        return false;
    }

    // ID 체크
    let jsonStr = { id : id };
    let checkDuplicateId = ajaxConnect('/checkDuplicateId.do', 'post', jsonStr);
    if(checkDuplicateId !== 0){
        Swal.fire({
            title: '[회원 정보]',
            html: '해당 아이디 [ ' + id + ' ] 의<br>비밀번호 초기화를 요청하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            confirmButtonText: '요청하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let email = ajaxConnectSimple('/member/getEmail.do', 'post', jsonStr);
                if(nvl(email,'') !== ''){
                    let jsonObj = {
                        subject: '[경기해양레저 인력양성센터] 비밀번호 초기화 요청', //제목
                        body: "", //본문
                        template: "12", //템플릿 번호
                        receiver: [{"email": email}]
                    }

                    let resData = ajaxConnect('/mail/send.do', 'post', jsonObj);
                    //console.log(i , resData);
                    if (resData.resultCode === "0") {
                        /* 비밀번호 초기화 */
                        let res = ajaxConnect('/member/initPassword.do', 'post', jsonStr);
                        if(res.resultCode !== "0"){
                            showMessage('', 'error', '[회원 정보]', '비밀번호 초기화에 실패하였습니다. 관리자에게 문의해주세요.', '');
                            return false;
                        }else{
                            Swal.fire({
                                title: '회원 정보',
                                html: '해당 ID의 비밀번호가 초기화되었습니다.<br>초기화 정보는 [ ' + email + ' ] 로 전송되었습니다.<br>로그인하신 후 비밀번호를 변경하여 이용해주세요.',
                                icon: 'info',
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: '확인'
                            });
                            return false;
                        }
                    }else{
                        Swal.fire({
                            title: '회원 정보',
                            html: '해당 ID에 등록된 Email 주소로 메일 전송이 실패하였습니다.<br>경기해양레저 인력양성센터로 문의 바랍니다.<br>Tel. 1811-7891',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        });
                        return false;
                    }
                }else{
                    Swal.fire({
                        title: '회원 정보',
                        html: '해당 ID에 등록된 Email 주소가 없습니다.<br>경기해양레저 인력양성센터로 문의 바랍니다.<br>Tel. 1811-7891',
                        icon: 'info',
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: '확인'
                    });
                    return false;
                }
            }
        })
    }else{
        showMessage('', 'error', '[회원 정보]', '해당 아이디로 가입된 회원 정보가 없습니다.', '');
        return false;
    }
}

function f_main_member_modify(){

    //Valid Check
    let pwCheck = $('#pwCheck').val();
    let pwConfirmCheck = $('#pwConfirmCheck').val();
    if(pwCheck === 'false'){ showMessage('', 'error', '[비밀번호 검사]', '비밀번호를 검사해 주세요.', ''); return false; }
    if(pwConfirmCheck === 'false'){ showMessage('', 'error', '[비밀번호 확인]', '비밀번호 항목과 비밀번호 확인 항목이 일치하는지 확인해 주세요.', ''); return false; }

    // 개인정보수집 동의 여부
    let privcy_chk = $('#f_privcy_essential').is(':checked');
    if(!privcy_chk){ showMessage('', 'error', '[개인정보수집 동의]', '개인정보수집 동의 항목에 체크해 주세요.', ''); return false; }

    let password = $('#password').val();
    if(nvl(password,'') === ''){ showMessage('', 'error', '[회원 정보]', '비밀번호를 입력해 주세요.', ''); return false; }

    let passwordCheck = $('#passwordCheck').val();
    if(nvl(passwordCheck,'') === ''){ showMessage('', 'error', '[회원 정보]', '비밀번호 확인을 입력해 주세요.', ''); return false; }

    let name = $('#name').val();
    if(nvl(name,'') === ''){ showMessage('', 'error', '[회원 정보]', '이름을 입력해 주세요.', ''); return false; }

    let phone = $('#phone').val();
    if(nvl(phone,'') === ''){ showMessage('', 'error', '[회원 정보]', '연락처를 입력해 주세요.', ''); return false; }

    let email = $('#email').val();
    if(nvl(email,'') === ''){ showMessage('', 'error', '[회원 정보]', '이메일을 입력해 주세요.', ''); return false; }

    let domain = $('#domain').val();
    if(nvl(domain,'') === ''){ showMessage('', 'error', '[회원 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }

    let keywordCheckbox = $('input[type=checkbox][name=keyword]:checked');
    if(keywordCheckbox.length === 0){ showMessage('', 'error', '[회원 정보]', '관심 키워드를 하나 이상 선택해 주세요.', ''); return false; }

    // form
    let form = JSON.parse(JSON.stringify($('#joinForm').serializeObject()));

    //이메일
    form.email = email + '@' + domain;

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
    let smsYn = $('#smsYn').is(':checked');
    if(smsYn){
        form.smsYn = '1';

        // SMS 동의 시 일반 -> 관심 등급 업그레이드
        form.grade = '관심사용자';
    }else{
        form.smsYn = '0';
    }

    Swal.fire({
        title: '[회원 정보]',
        html: '입력된 정보로 회원 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {

            $.ajax({
                url: '/member/modify/update.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(form),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {

                    if (data.resultCode === "0") {

                        Swal.fire({
                            title: '[회원 정보]',
                            html: '회원 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then(async (result) => {
                            if (result.isConfirmed) {
                                window.location.href = '/mypage/modify.do';

                                // 비밀번호 체크 초기화
                                $('.pw_valid_result_cmnt').text('');
                                $('#pwCheck').val('true');
                                $('#passwordCheck').val('');
                                $('#pwConfirmCheck').val('false');
                            }
                        });

                    } else if(data.resultCode === "99"){

                        Swal.fire({
                            title: '[회원 정보]',
                            html: data.resultMessage,
                            icon: 'info',
                            showCancelButton: true,
                            confirmButtonColor: '#00a8ff',
                            confirmButtonText: '저장하기',
                            cancelButtonColor: '#A1A5B7',
                            cancelButtonText: '취소'
                        }).then(async (result) => {
                            if (result.isConfirmed) {

                                form.passwordChangeYn = 'Y';

                                $.ajax({
                                    url: '/member/modify/update.do',
                                    method: 'POST',
                                    async: false,
                                    data: JSON.stringify(form),
                                    dataType: 'json',
                                    contentType: 'application/json; charset=utf-8',
                                    success: function (data) {
                                        if (data.resultCode === "0") {
                                            Swal.fire({
                                                title: '[회원 정보]',
                                                html: '회원 정보가 저장되었습니다.',
                                                icon: 'info',
                                                confirmButtonColor: '#3085d6',
                                                confirmButtonText: '확인'
                                            }).then(async (result) => {
                                                if (result.isConfirmed) {
                                                    window.location.href = '/mypage/modify.do';

                                                    // 비밀번호 체크 초기화
                                                    $('.pw_valid_result_cmnt').text('');
                                                    $('#pwCheck').val('true');
                                                    $('#passwordCheck').val('');
                                                    $('#pwConfirmCheck').val('false');
                                                }
                                            });
                                        }else{
                                            showMessage('', 'error', '에러 발생', '회원 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                                        }
                                    }
                                })
                            }
                        })

                    }else{
                        showMessage('', 'error', '에러 발생', '회원 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    })

}

function f_rrn_age_calc(el){
    let rrn_first = $(el).val();

    if(rrn_first.length === 6){
        let rrn_year = rrn_first.substring(0,2); // 92
        let year = '20';
        if(rrn_year.indexOf('9',0) > -1){
            year = '19';
        }
        year += rrn_year;

        let rrn_month = rrn_first.substring(2,4); // 08
        let rrn_day = rrn_month.substring(4); // 08

        // 생년월일을 입력합니다.
        let birth = rrn_year + '-' + rrn_month + '-' + rrn_day;
        let birthDate = new Date(birth);
        // 한국식 나이를 계산합니다.
        let koreanAge = calculateKoreanAge(birthDate);

        $('#age').val(koreanAge);
    }else{
        $('#age').val('');
    }
}

function calculateKoreanAge(birthDate) {
    let currentYear = new Date().getFullYear();  // 현재 연도를 가져옵니다.
    let birthYear = birthDate.getFullYear();  // 생년을 가져옵니다.
    return currentYear - birthYear + 1;
}

function f_main_member_resume_submit(){

    let nameKo = $('#nameKo').val();
    if(nvl(nameKo,'') === ''){ showMessage('', 'error', '[이력서 정보]', '성명(국문)을 입력해 주세요.', ''); return false; }

    let nameEn = $('#nameEn').val();
    if(nvl(nameEn,'') === ''){ showMessage('', 'error', '[이력서 정보]', '성명(영문)을 입력해 주세요.', ''); return false; }

    let phone = $('#phone').val();
    if(nvl(phone,'') === ''){ showMessage('', 'error', '[이력서 정보]', '연락처를 입력해 주세요.', ''); return false; }

    let email = $('#email').val();
    if(nvl(email,'') === ''){ showMessage('', 'error', '[이력서 정보]', '이메일을 입력해 주세요.', ''); return false; }

    let domain = $('#domain').val();
    if(nvl(domain,'') === ''){ showMessage('', 'error', '[이력서 정보]', '이메일 도메인을 입력해 주세요.', ''); return false; }

    let rrn_first = $('#rrn_first').val();
    if(nvl(rrn_first,'') === '' || rrn_first.length !== 6){ showMessage('', 'error', '[이력서 정보]', '주민등록번호 앞자리를 입력해 주세요.', ''); return false; }

    let rrn_last = $('#rrn_last').val();
    if(nvl(rrn_last,'') === '' || rrn_last.length !== 7){ showMessage('', 'error', '[이력서 정보]', '주민등록번호 뒷자리를 입력해 주세요.', ''); return false; }

    let bodyPhotoFile_li = $('.bodyPhotoFile_li').length;
    if(bodyPhotoFile_li === 0){
        let bodyPhoto = $('#bodyPhoto').val();
        if (nvl(bodyPhoto,'') === ''){ showMessage('', 'error', '[이력서 정보]', '상반신 사진을 첨부해주세요.', ''); return false; }
    }

    let address = $('#address').val();
    if(nvl(address,'') === ''){ showMessage('', 'error', '[이력서 정보]', '주소를 입력해 주세요.', ''); return false; }

    let addressDetail = $('#addressDetail').val();
    if(nvl(addressDetail,'') === ''){ showMessage('', 'error', '[이력서 정보]', '상세 주소를 입력해 주세요.', ''); return false; }

    let topClothesSizeLen = $('input[type=radio][name=topClothesSize]:checked').length;
    if(topClothesSizeLen === 0){ showMessage('', 'error', '[이력서 정보]', '상의 사이즈를 선택해 주세요.', ''); return false; }

    let bottomClothesSizeLen = $('input[type=radio][name=bottomClothesSize]:checked').length;
    if(bottomClothesSizeLen === 0){ showMessage('', 'error', '[이력서 정보]', '하의 사이즈를 선택해 주세요.', ''); return false; }

    let shoesSizeLen = $('input[type=radio][name=shoesSize]:checked').length;
    if(shoesSizeLen === 0){ showMessage('', 'error', '[이력서 정보]', '안전화 사이즈를 선택해 주세요.', ''); return false; }

    let participationPathLen = $('input[type=radio][name=participationPath]:checked').length;
    if(participationPathLen === 0){ showMessage('', 'error', '[이력서 정보]', '참여 경로를 선택해 주세요.', ''); return false; }

    // form
    let form = JSON.parse(JSON.stringify($('#joinForm').serializeObject()));

    //이메일
    form.email = email + '@' + domain;

    //주민등록번호
    form.rrn = rrn_first + '-' + rrn_last;

    form.id = sessionStorage.getItem('id');

    Swal.fire({
        title: '[이력서 정보]',
        html: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mypage/resume/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(form),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {

                        let seq = data.customValue;

                        /* 파일 업로드 */
                        f_main_resume_file_upload_call(seq, 'member/resume/' + seq);

                        let timerInterval;
                        Swal.fire({
                            title: "[이력서 정보]",
                            html: "입력하신 정보를 저장 중입니다.<br><b></b> milliseconds.<br>현재 화면을 유지해주세요.",
                            timer: 3000,
                            timerProgressBar: true,
                            didOpen: () => {
                                Swal.showLoading();
                                const timer = Swal.getPopup().querySelector("b");
                                timerInterval = setInterval(() => {
                                    timer.textContent = `${Swal.getTimerLeft()}`;
                                }, 1000);
                            },
                            willClose: () => {
                                clearInterval(timerInterval);
                            }
                        }).then((result) => {
                            /* Read more about handling dismissals below */
                            if (result.dismiss === Swal.DismissReason.timer) {
                                Swal.fire({
                                    title: '[이력서 정보]',
                                    html: '이력서 정보가 저장되었습니다.',
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mypage/resume.do';
                                    }
                                })
                            }
                        });

                    } else {
                        showMessage('', 'error', '에러 발생', '이력서 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

/**************************************************************
 * COMMON
 * ************************************************************/

function ajaxConnect(url, method, jsonStr){
    let result;
    $.ajax({
        url: url,
        method: method,
        async: false,
        data: JSON.stringify(jsonStr),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    })
        .done(function (data) {
            result = data;
        })
        .fail(function (xhr, status, errorThrown) {
            /*$('body').html("오류가 발생했습니다.")
                .append("<br>오류명: " + errorThrown)
                .append("<br>상태: " + status);*/

            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + errorThrown + "\n상태 : " + status);
            result = "fail";
        })
    return result;
}

function ajaxConnectSimple(url, method, jsonStr){
    let result = '';
    $.ajax({
        url: url,
        method: method,
        async: false,
        data: JSON.stringify(jsonStr),
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    })
    .done(function (data) {
        result = data;
    })
    .fail(function (xhr, status, errorThrown) {
        alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + errorThrown + "\n상태 : " + status);
    })
    return result;
}

function showMessage(selector, icon, title, msg, confirmButtonColor) {
    if (typeof icon == "undefined" || title == null) icon = 'info';
    if (typeof title == "undefined" || title == null) title = '';
    if (typeof confirmButtonColor == "undefined" || confirmButtonColor == null || confirmButtonColor === '') confirmButtonColor = '#00a8ff';

    Swal.fire({
        icon: icon,
        title: title,
        html: msg,
        confirmButtonColor: confirmButtonColor
    })
        .then(() => {
            if( selector && selector !== '' ){
                setTimeout(function() { $(selector).focus(); }, 200);
            }
        });
}

function execDaumPostcode(address, addressDetail) {
    let width = 500; //팝업의 너비
    let height = 600; //팝업의 높이
    new daum.Postcode({
        width: width, //생성자에 크기 값을 명시적으로 지정해야 합니다.
        height: height,
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                // document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                // document.getElementById("sample6_extraAddress").value = '';
            }

            if(nvl(address,"") !== "" && nvl(addressDetail,"") !== ""){
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById(address).value = '(' + data.zonecode + ') ' + addr;

                // 우편번호 클릭시 초기화
                document.getElementById(addressDetail).value = '';

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById(addressDetail).focus();
            }else{
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address').value = '(' + data.zonecode + ') ' + addr;

                // 우편번호 클릭시 초기화
                document.getElementById('address_detail').value = '';

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address_detail').focus();
            }
        }
    }).open({
        left: (window.screen.width / 2) - (width / 2),
        top: (window.screen.height / 2) - (height / 2),
        popupTitle: '우편번호 검색 팝업', //팝업창 타이틀 설정 (영문,한글,숫자 모두 가능)
        popupKey: 'popup1' //팝업창 Key값 설정 (영문+숫자 추천)
    });
}

function f_main_resume_file_upload_call(id, path) {

    /* 상반신 사진 */
    let bodyPhotoFile = document.getElementById('bodyPhotoFile').value;
    if (nvl(bodyPhotoFile, '') !== '') {
        f_main_file_upload(id, 'bodyPhotoFile', path);
    }

}

function f_gift_file_upload_call(id, path) {

    /* 경품사진 */
    let giftPhotoFileList = document.getElementsByName('giftPhotoFile');
    let photoIdx = parseInt($('div.form_chuga_list').length);
    for(let i=0; i<giftPhotoFileList.length; i++){
        let giftPhotoFile = giftPhotoFileList[i].value;
        if (nvl(giftPhotoFile, '') !== '') {
            f_main_file_upload(id, 'giftPhotoFile' + photoIdx, path);
        }
    }

    /* 회사로고 */
    let giftCompanyLogoFileList = document.getElementsByName('giftCompanyLogoFile');
    let logoIdx = parseInt($('div.form_chuga_list').length);
    for(let i=0; i<giftCompanyLogoFileList.length; i++){
        let giftCompanyLogoFile = giftCompanyLogoFileList[i].value;
        if (nvl(giftCompanyLogoFile, '') !== '') {
            f_main_file_upload(id, 'giftCompanyLogoFile' + logoIdx, path);
        }
    }

}

function f_web_file_upload_call(id, path) {

    /* 배너 이미지 */
    let webbannerImageFile = document.getElementById('webbannerImageFile').value;
    if (nvl(webbannerImageFile, '') !== '') {
        f_main_file_upload(id, 'webbannerImageFile', path);
    }

    /* 로고 이미지 */
    let webbannerLogoImageFile = document.getElementById('webbannerLogoImageFile').value;
    if (nvl(webbannerLogoImageFile, '') !== '') {
        //console.log('로고 파일 업로드');
        f_main_file_upload(id, 'webbannerLogoImageFile', path);
    }

}

async function f_main_file_upload(userId, elementId, path) {
    let uploadFileResponse = '';
    uploadFileResponse = await f_main_server_upload(elementId, path);
    if (nvl(uploadFileResponse, "") !== '') {
        let fullFilePath = uploadFileResponse.replaceAll('\\', '/');
        // ./tomcat/webapps/upload/center/board/notice/b3eb661d-34de-4fd0-bc74-17db9fffc1bd_KIBS_TV_목록_excel_20230817151752.xlsx

        let fullPath = fullFilePath.substring(0, fullFilePath.lastIndexOf('/') + 1);
        // ./tomcat/webapps/upload/center/board/notice/

        let pureFileNameSplit = fullFilePath.split('/');
        let fullFileName = pureFileNameSplit[pureFileNameSplit.length - 1];
        // b3eb661d-34de-4fd0-bc74-17db9fffc1bd_KIBS_TV_목록_excel_20230817151752.xlsx

        let uuid = fullFileName.substring(0, fullFileName.indexOf('_'));
        // b3eb661d-34de-4fd0-bc74-17db9fffc1bd

        let fileName = fullFileName.substring(fullFileName.indexOf('_') + 1);
        // KIBS_TV_목록_excel_20230817151752.xlsx

        let folderPath = pureFileNameSplit[pureFileNameSplit.length - 2];
        // notice

        let note = elementId.replace('File', '');

        let jsonObj = {
            userId: userId,
            fullFilePath: fullFilePath.replaceAll(' ','').replaceAll('%20',''),
            fullPath: fullPath.replaceAll(' ','').replaceAll('%20',''),
            folderPath: folderPath.replaceAll(' ','').replaceAll('%20',''),
            fullFileName: fullFileName.replaceAll(' ','').replaceAll('%20',''),
            uuid: uuid,
            fileName: fileName.replaceAll(' ','').replaceAll('%20',''),
            fileYn: 'Y',
            note: note
        };
        let resData = ajaxConnect('/file/upload/save.do', 'post', jsonObj);
        if (resData.resultCode === "0") {
            /*let parents_el = document.querySelector('#' + note);
            let fileId_el = document.createElement('input');
            fileId_el.type = 'hidden';
            fileId_el.id = note+'1';
            fileId_el.name = note+'1';
            fileId_el.value = resData.fileId;

            parents_el.appendChild(fileId_el);*/
        }
    }
}

function f_main_server_upload(elementId, path) {
    /* 파일 업로드 */
    let file = document.querySelector('#' + elementId);
    let formData = new FormData();
    formData.append('uploadFile',file.files[0]);

    return new Promise((resolve, reject) => {
        fetch('/file/upload.do?gbn=' + path, {
            method: 'post',
            body: formData
        })
            .then(function (response) {
                return response.json();
            })
            .then(res => {
                if( typeof res.uploadPath !== undefined){
                    resolve(res.uploadPath + '\\' + res.uuid + '_' + res.fileName);
                }
            })

    });
}

function f_file_remove(el, fileId){
    let jsonObj = {
        id: fileId
    }

    let resData = ajaxConnect('/file/upload/update.do', 'post', jsonObj);
    if(resData.resultCode === "0"){
        $(el).parent().remove();
    }
}

function makeJsonFormat(data){
    let returnJsonObj;
    let receiverArr = [];

    $.each(data , function(i){
        let receiverObj = {
            email: data[i].chargePersonEmail //받는이 메일주소
        }
        receiverArr.push(receiverObj);
    });

    returnJsonObj = {
        subject: '[SIPA 스마트산업진흥협회] 참가업체 접수 완료', //제목
        body: '',//본문
        template: '6', //템플릿 번호
        receiver: receiverArr
    }

    return returnJsonObj;
}

/**
 * 문자열이 빈 문자열인지 체크하여 기본 문자열로 리턴한다.
 * @param str			: 체크할 문자열
 * @param defaultStr	: string 비어있을경우 리턴할 기본 문자열
 */
function nvl(str, defaultStr){

    if(str === "" || str === null || str === undefined || (typeof str === "object" && !Object.keys(str).length) ){
        str = defaultStr ;
    }

    return str ;
}

function f_page_move(url, param){
    let form = document.createElement('form');
    form.setAttribute('method', 'post'); //POST 메서드 적용
    form.setAttribute('action', url);

    let keys = Object.keys(param); //키를 가져옵니다. 이때, keys 는 반복가능한 객체가 됩니다.
    for (let i=0; i<keys.length; i++) {
        let key = keys[i];
        let hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden'); //값 입력
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', param[key]);
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();
}

/*
@author https://github.com/macek/jquery-serialize-object
*/
$.fn.serializeObject = function () {
    "use strict";
    var result = {};
    var extend = function (i, element) {
        var node = result[element.name];
        if ("undefined" !== typeof node && node !== null) {
            if ($.isArray(node)) {
                node.push(element.value);
            } else {
                result[element.name] = [node, element.value];
            }
        } else {
            result[element.name] = element.value;
        }
    };

    $.each(this.serializeArray(), extend);
    return result;
};