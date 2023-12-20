/***
 * mngMain.js
 * */

$(function(){

    // 숫자만 입력
    $('.onlyNum').on("blur keyup", function () {
        $(this).val($(this).val().replaceAll(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'));
    });

    // 연락처 입력 시 자동으로 - 삽입과 숫자만 입력
    $('.onlyTel').on("blur keyup", function () {
        $(this).val($(this).val().replaceAll(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
    });

    // 영문, 숫자만 입력
    $('.onlyNumEng').on("blur keyup", function () {
        let exp = /[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm;
        $(this).val($(this).val().replaceAll(exp, ''));
    });

    // 숫자, 소수점 입력
    $('.onlyNumDec').on("blur keyup", function () {
        var sanitizedValue = $(this).val().replace(/[^0-9.]/g, ''); // 숫자와 소수점 이외의 문자 제거
        var decimalParts = sanitizedValue.split("."); // 소수점을 기준으로 분할

        // 소수점이 하나 이상일 경우 마지막 소수점만 남기고 제거
        if (decimalParts.length > 2) {
            decimalParts.pop(); // 마지막 요소(소수점 이후 내용) 제거
            sanitizedValue = decimalParts.join(".");
        }

        $(this).val(sanitizedValue); // 처리된 값을 다시 입력 필드에 설정
    });

    // 파일 입력 변경에 대한 이벤트 핸들러 추가
    $(document).on('change', '.upload_hidden', function () {
        let fileName = $(this).val().split('\\').pop();
        let fileNameInput = $(this).parent('div').siblings('div').find('.upload_name');

        let _lastDot = fileName.lastIndexOf('.');
        let realFileName = fileName.substring(0, _lastDot).toLowerCase();

        // 파일명에 특수문자 체크
        let pattern =   /[\{\}\/?,.;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
        if(pattern.test(String(realFileName)) ){
            //alert("파일명에 허용된 특수문자는 '-', '_', '(', ')', '[', ']', '.' 입니다.");
            fileNameInput.val('');
            alert('파일명에 허용되지 않는 특수문자가 포함되어 있습니다.\n허용된 특수문자는 - _ ( ) [ ] 입니다.');
        }else{
            fileNameInput.val(realFileName);
        }
    });

    let customDatepicker = document.getElementById("kt_td_picker_custom_icons");
    if(customDatepicker){
        new tempusDominus.TempusDominus(customDatepicker, {
            display: {
                icons: {
                    time: "ki-outline ki-time fs-1",
                    date: "ki-outline ki-calendar fs-1",
                    up: "ki-outline ki-up fs-1",
                    down: "ki-outline ki-down fs-1",
                    previous: "ki-outline ki-left fs-1",
                    next: "ki-outline ki-right fs-1",
                    today: "ki-outline ki-check fs-1",
                    clear: "ki-outline ki-trash fs-1",
                    close: "ki-outline ki-cross fs-1",
                },
                buttons: {
                    today: true,
                    clear: true,
                    close: true,
                },
            }
        });
    }

    /* 작성일 */
    let writeDatePicker = document.getElementById('writeDate');
    if(writeDatePicker) {
        writeDatePicker.flatpickr({
            enableTime: true,
            dateFormat: "Y-m-d H:i:S",
            defaultDate: new Date()
        });
    }

    const exportButtons = document.querySelectorAll('button[data-kt-export]');
    exportButtons.forEach(exportButton => {
        exportButton.addEventListener('click', e => {
            e.preventDefault();

            // Get clicked export value
            const exportValue = e.target.getAttribute('data-kt-export');
            const target = document.querySelector('.dt-buttons .buttons-' + exportValue);

            // Trigger click event on hidden datatable export buttons
            if(target){
                target.click();
            }
        });
    });

});


function loginFormSubmit() {
    let form = document.getElementById("login_form");
    let id = document.getElementById("adminId");
    let password = document.getElementById("adminPw");

    if (id.value.trim() === "" || password.value.trim() === "") {
        showMessage('', 'info', '입력 정보 확인', '아이디와 비밀번호를 입력해 주세요.', '');
        return false;
    }

    let jsonObj = {
        id: id.value,
        password: password.value
    };

    $.ajax({
        url: '/mng/login.do',
        method: 'post',
        data: JSON.stringify(jsonObj),
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    })
        .done(function (data) {
            /*if (data !== "") {*/
                form.submit(); // /mng/main.do
            /*} else {
                showMessage('', 'info', '로그인 실패', '관리자 아이디와 비밀번호를 확인해주세요.', '');
            }*/
        })
        .fail(function (xhr, status, errorThrown) {
            /*$('body').html("오류가 발생했습니다.")
                .append("<br>오류명: " + errorThrown)
                .append("<br>상태: " + status);*/
            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + errorThrown + "\n상태 : " + status);
        })

}

function logout() {
    if(confirm('로그아웃 하시겠습니까?')){
        $.ajax({
            url: '/mng/logoutCheck.do',
            method: 'post',
            contentType: 'application/json; charset=utf-8' //server charset 확인 필요
        })
        .done(function (data) {
            if (data !== "") {
                window.location.href = '/mng/index.do';
            } else {
                showMessage('', 'info', '로그아웃 실패', '관리자에게 문의해주세요.', '');
            }
        })
        .fail(function (xhr, status, errorThrown) {
            /*$('body').html("오류가 발생했습니다.")
                .append("<br>오류명: " + errorThrown)
                .append("<br>상태: " + status);*/
            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + errorThrown + "\n상태 : " + status);
        })
    }

}

function replaceText(value){
    if(nullToEmpty(value) !== ''){
        value = value.toString().replaceAll('\r','');
        value = value.toString().replaceAll('\n','');
    }
    return value;
}

// 엑셀업로드
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
            $(selector).focus();
        });
}

function ajaxConnect(url, method, jsonStr) {
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
        })
    return result;
}

function f_mng_trash_remove(jsonObj){

    let resData = ajaxConnect('/mng/file/trash/save.do', 'post', jsonObj);

    if (resData.resultCode === "0") {
        showMessage('', 'info', '삭제', '해당 데이터가 임시휴지통으로 이동되었습니다.', '');
    } else {
        showMessage('', 'error', '에러 발생', '삭제 처리를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
    }
}

function getCurrentDate() {
    let date = new Date(); // Data 객체 생성
    let year = date.getFullYear().toString(); // 년도 구하기

    let month = date.getMonth() + 1; // 월 구하기
    month = month < 10 ? '0' + month.toString() : month.toString(); // 10월 미만 0 추가

    let day = date.getDate(); // 날짜 구하기
    day = day < 10 ? '0' + day.toString() : day.toString(); // 10일 미만 0 추가

    let hour = date.getHours(); // 시간 구하기
    hour = hour < 10 ? '0' + hour.toString() : hour.toString(); // 10시 미만 0 추가

    let minites = date.getMinutes(); // 분 구하기
    minites = minites < 10 ? '0' + minites.toString() : minites.toString(); // 10분 미만 0 추가

    let seconds = date.getSeconds(); // 초 구하기
    seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString(); // 10초 미만 0 추가

    return year + month + day + hour + minites + seconds; // yyyymmddhhmmss 형식으로 리턴
}

function nullToEmpty(nullStr){
    let convertStr;
    if(nullStr === null || nullStr === 'null' || nullStr === undefined || nullStr === 'undefined' || Object.keys(nullStr).length === 0){
        convertStr = '';
    }else{
        convertStr = nullStr;
    }
    return convertStr;
}

/**
 * 문자열이 빈 문자열인지 체크하여 기본 문자열로 리턴한다.
 * @param str			: 체크할 문자열
 * @param defaultStr	: string 비어있을경우 리턴할 기본 문자열
 */
function nvl(str, defaultStr){


    if(typeof str === "undefined" || typeof str === undefined || str === null || str === "" || str === "null" || Object.keys(str).length === 0){
        str = defaultStr ;
    }

    return str ;
}

function loadingBarShow(){
    const loadingEl = document.createElement("div");
    document.body.prepend(loadingEl);
    loadingEl.classList.add("page-loader");
    loadingEl.classList.add("flex-column");
    loadingEl.classList.add("bg-dark");
    loadingEl.classList.add("bg-opacity-25");
    loadingEl.innerHTML = '<span class="spinner-border text-primary" role="status"></span><span class="text-gray-800 fs-6 fw-semibold mt-5">Loading...</span>';

    // Show page loading
    KTApp.showPageLoading();

    // Hide after 3 seconds
    setTimeout(function() {
        KTApp.hidePageLoading();
        loadingEl.remove();
    }, 1500);
}

function f_excel_export(tableId , name){
    let dataTbl = $('#' + tableId).DataTable();
    let dataCount = dataTbl.rows().count();
    if(dataCount > 0){

        let downloadFileName = name + '_목록_excel_' + getCurrentDate();

        let jsonObj = {
            downloadFileName: downloadFileName,
            targetMenu: getTargetMenu(tableId)
        }
        let resData = ajaxConnect('/mng/file/download/insert.do', 'post', jsonObj);

        if (resData.resultCode === "0") {
            let buttons = new $.fn.dataTable.Buttons(dataTbl, {
                buttons:[
                    {
                        extend: 'excelHtml5',
                        title: downloadFileName,
                        autoFilter: true,
                        text: 'Export as Excel',
                        className: 'btn btn-success btn-active-light-success'
                    }
                ]
            }).container().appendTo($('#kt_datatable_excel_hidden_buttons'));
        }else{
            showMessage('', 'error', '에러 발생', '엑셀 다운로드 내역 저장에 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
        }
    }else{
        showMessage('', 'info', 'Export as Excel', '엑셀로 추출할 데이터가 없습니다.', '');
    }
}

function getTargetMenu(tableId){
    let targetMenu = '';
    let menu = tableId.toString().split('_');
    let menuTop = menu[1];
    switch (menuTop){
        case 'customer':
            targetMenu += '회원/신청';
            break;
        case 'education':
            targetMenu += '교육';
            break;
        case 'board':
        case 'pop':
        case 'newsletter':
        case 'smsMng':
            targetMenu += '정보센터';
            break;
        case 'file':
            targetMenu += '파일';
            break;
        default:
            break;
    }

    targetMenu += '_';

    let menuMid = menu[2];
    switch (menuMid){
        case 'member':
        case 'regular':
        case 'inboarder':
            targetMenu += '회원관리';
            break;
        case 'train':
        case 'payment':
            targetMenu += '교육관리';
            break;
        case 'notice':
        case 'press':
        case 'gallery':
        case 'media':
        case 'newsletter':
        case 'job':
        case 'community':
            targetMenu += '게시판관리';
            break;
        case 'popup':
        case 'banner':
            targetMenu += '팝업/배너관리';
            break;
        case 'subscriber':
            targetMenu += '뉴스레터관리';
            break;
        case 'sms':
            targetMenu += 'SMS관리';
            break;
        case 'download':
            targetMenu += '파일관리';
            break;
        default:
            break;
    }

    targetMenu += '_';

    let menuBot = menu[3];
    switch (menuMid){
        case 'member':
            targetMenu += '전체회원목록';
            break;
        case 'regular':
            targetMenu += '상시사전신청';
            break;
        case 'inboarder':
            targetMenu += '자가정비(선내기)';
            break;
        case 'train':
            targetMenu += '교육현황';
            break;
        case 'payment':
            targetMenu += '결제/환불현황';
            break;
        case 'notice':
            targetMenu += '공지사항';
            break;
        case 'press':
            targetMenu += '보도자료';
            break;
        case 'gallery':
            targetMenu += '사진자료';
            break;
        case 'media':
            targetMenu += '영상자료';
            break;
        case 'newsletter':
            targetMenu += '뉴스레터';
            break;
        case 'job':
            targetMenu += '취창업성공후기';
            break;
        case 'community':
            targetMenu += '커뮤니티';
            break;
        case 'popup':
            targetMenu += '팝업관리';
            break;
        case 'banner':
            targetMenu += '배너관리';
            break;
        case 'subscriber':
            targetMenu += '뉴스레터구독자관리';
            break;
        case 'sms':
            targetMenu += 'SMS발송관리';
            break;
        case 'download':
            targetMenu += '다운로드내역';
            break;
        case 'trash':
            targetMenu += '임시휴지통';
            break;
        default:
            break;
    }

    //mng_customer_member_table // 회원/신청_회원관리_전체회원목록

    //mng_customer_regular_table // 회원/신청_회원관리_상시사전신청
    //mng_customer_inboarder_table // 회원/신청_회원관리_자가정비(선내기)

    //mng_education_train_table // 교육_교육관리_교육현황
    //mng_education_payment_table 교육_교육관리_결제/환불현황

    //mng_board_notice_table // 정보센터_게시판관리_공지사항
    //mng_board_press_table // 정보센터_게시판관리_보도자료
    //mng_board_gallery_table // 정보센터_게시판관리_사진자료
    //mng_board_media_table // 정보센터_게시판관리_영상자료
    //mng_board_newsletter_table // 정보센터_게시판관리_뉴스레터
    //mng_board_job_table // 정보센터_게시판관리_취창업성공후기
    //mng_board_community_table // 정보센터_게시판관리_커뮤니티

    //mng_pop_popup_table // 정보센터_팝업/배너관리_팝업관리
    //mng_pop_banner_table // 정보센터_팝업/배너관리_배너관리

    //mng_newsletter_subscriber_table // 정보센터_뉴스레터관리_뉴스레터구독자관리

    //mng_smsMng_sms_table // 정보센터_SMS관리_SMS발송관리

    //mng_file_download_table // 파일_파일관리_다운로드내역

    return targetMenu;
}

function f_mng_temporary_trash_save(){

}

function f_mng_uploadFile(formId, path) {
    /* 파일 업로드 */
    let fileForm = document.getElementById(formId);
    let formData = new FormData(fileForm);

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

function modalClose(name){
    document.querySelector('input[type=text][name=' + name + ']').value = '';
    document.querySelector('input[type=file][name=file]').value = null;
}

async function f_company_file_upload(userId, elementId, path) {
    let uploadFileResponse = '';
    uploadFileResponse = await f_company_uploadFile(elementId, path);
    if (nvl(uploadFileResponse, '') !== '') {
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
            "userId": userId,
            "fullFilePath": fullFilePath.replaceAll(' ','').replaceAll('%20',''),
            "fullPath": fullPath.replaceAll(' ','').replaceAll('%20',''),
            "folderPath": folderPath.replaceAll(' ','').replaceAll('%20',''),
            "fullFileName": fullFileName.replaceAll(' ','').replaceAll('%20',''),
            "uuid": uuid,
            "fileName": fileName.replaceAll(' ','').replaceAll('%20',''),
            "fileYn": 'Y',
            "note": note
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

function f_company_uploadFile(elementId, path) {
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

async function f_attach_file_upload(userId, formId, path) {
    let file = $('#attachFileInput').val();
    if(nvl(file,'') !== ''){
        let uploadFileResponse = '';
        uploadFileResponse = await f_mng_uploadFile(formId, path);
        if (nvl(uploadFileResponse, "") !== '') {
            Swal.fire({
                title: '파일 업로드',
                text: "파일 업로드 성공",
                icon: 'info',
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인'
            }).then((result) => {
                if (result.isConfirmed) {
                    let fullFilePath = uploadFileResponse.replaceAll('\\','/');
                    // ./tomcat/webapps/upload/center/board/notice/b3eb661d-34de-4fd0-bc74-17db9fffc1bd_KIBS_TV_목록_excel_20230817151752.xlsx

                    let fullPath = fullFilePath.substring(0, fullFilePath.lastIndexOf('/')+1);
                    // ./tomcat/webapps/upload/center/board/notice/

                    let pureFileNameSplit = fullFilePath.split('/');
                    let fullFileName = pureFileNameSplit[pureFileNameSplit.length - 1];
                    // b3eb661d-34de-4fd0-bc74-17db9fffc1bd_KIBS_TV_목록_excel_20230817151752.xlsx

                    let uuid = fullFileName.substring(0, fullFileName.indexOf('_'));
                    // b3eb661d-34de-4fd0-bc74-17db9fffc1bd

                    let fileName = fullFileName.substring(fullFileName.indexOf('_')+1);
                    // KIBS_TV_목록_excel_20230817151752.xlsx

                    let folderPath = pureFileNameSplit[pureFileNameSplit.length - 2];
                    // notice

                    let jsonObj = {
                        userId: userId,
                        fullFilePath: fullFilePath,
                        fullPath: fullPath,
                        folderPath: folderPath,
                        fullFileName: fullFileName,
                        uuid: uuid,
                        fileName: fileName,
                        fileYn: 'Y'
                    };
                    let resData = ajaxConnect('/file/upload/save.do', 'post', jsonObj);
                    if (resData.resultCode === "0") {
                        let ul_el = document.getElementById('uploadFileList');
                        let li_el = document.createElement('li');
                        let a_el = document.createElement('a');

                        if(folderPath === 'gallery' || folderPath === 'banner'){
                            let img_el = document.createElement('img');
                            img_el.src = fullFilePath.replace('./usr/local/tomcat/webapps','../../../../..');
                            img_el.classList.add('w-350px','mr10');
                            img_el.style.border = '1px solid #009ef7';
                            li_el.append(img_el);
                        }

                        /*a_el.href = 'javascript:f_file_download(' + '\'' + pureFileName + '\'' + ',' + '\'' + pureFilePath + '\'' +')';*/
                        a_el.href = '/file/download.do?path=' + path + '&fileName=' + fullFileName;
                        a_el.text = fileName;
                        li_el.append(a_el);

                        let hidden_el = document.createElement('input');
                        hidden_el.type = 'hidden';
                        hidden_el.name = 'uploadFile';
                        hidden_el.id = resData.fileId;
                        hidden_el.value = fullFilePath;
                        li_el.append(hidden_el);

                        let button_el = document.createElement('button');
                        button_el.type = 'button';
                        button_el.className = 'ml10';
                        button_el.onclick = function(){ f_file_remove(this, resData.fileId) }
                        button_el.innerHTML = '<i class="ki-duotone ki-abstract-11">\n' +
                            '<i class="path1"></i>\n' +
                            '<i class="path2"></i>\n' +
                            '</i>';
                        li_el.append(button_el);

                        ul_el.append(li_el);

                        /* modal 창 닫기 */
                        f_upload_modal_close('kt_modal_file_upload','attachFile');
                    }
                }
            });
        }
    }else{
        alert('첨부된 파일이 없습니다.');
    }
}

function f_upload_modal_close(target_modal_id, target_modal_form_init_id){
    /* modal close */
    // open : className="app-default modal-open" style="overflow: hidden; padding-right: 17px;">
    // close : className="app-default" style="">
    let body_el = document.querySelector('body');
    body_el.classList.remove('modal-open');
    body_el.style.removeProperty('overflow');
    body_el.style.removeProperty('padding-right');

    // open : <div className="modal fade show" id="kt_modal_excel_upload" tabIndex="-1" style="display: block;" aria-modal="true" role="dialog">
    // close : <div className="modal fade" id="kt_modal_excel_upload" tabIndex="-1" style="display: none;" aria-hidden="true">
    let modal_el = document.getElementById(target_modal_id);
    modal_el.classList.remove('show');
    modal_el.style.display = 'none';
    modal_el.removeAttribute('aria-modal');
    modal_el.removeAttribute('role');
    modal_el.setAttribute('aria-hidden','true');

    let modal_backdrop_el = document.querySelector('.modal-backdrop');
    modal_backdrop_el.remove();

    /* modal close */
    modalClose(target_modal_form_init_id);
}

function f_file_remove(el, fileId){
    let jsonObj = {
        id: fileId
    }

    let resData = ajaxConnect('/file/upload/update.do', 'post', jsonObj);
    if(resData.resultCode === "0"){
        $(el).parent('li').remove();
    }
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

            if(nullToEmpty(address) !== '' && nullToEmpty(addressDetail) !== ''){
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