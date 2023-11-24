/***
 * mng/member/adviser
 * 정보센터>회원사관리>고문위원
 * */

$(function(){

    // 파일 입력 변경에 대한 이벤트 핸들러 추가
    $(document).on('change', '.upload_hidden', function () {
        let fileName = $(this).val().split('\\').pop();
        let fileNameInput = $(this).parent('div').siblings('div').find('.upload_name');
        fileNameInput.val(fileName);
    });

});

function f_member_adviser_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_member_adviser_table').DataTable();
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

    let resData = ajaxConnect('/mng/member/adviser/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_member_adviser_table tbody td');
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

function f_member_adviser_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_member_adviser_search();
}

function f_member_adviser_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/member/adviser/selectSingle.do', 'post', jsonObj);

    /* 공지사항 상세보기 Modal form Set */
    //console.log(resData);

    if(resData.lang === 'KO'){
        document.querySelector('#md_lang').value = '국문';
    }else{
        document.querySelector('#md_lang').value = '영문';
    }

    document.querySelector('#md_position').value = resData.position;
    document.querySelector('#md_name').value = resData.name;
    document.querySelector('#md_organization').value = resData.organization;
    document.querySelector('#md_depart').value = resData.depart;
    document.querySelector('#md_homepage').value = resData.homepage;
    document.querySelector('#md_tel').value = resData.tel;
    document.querySelector('#md_briefDescription').value = resData.briefDescription;


    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj2 = {
        userId: seq
    };

    let file_list_el = document.getElementById('file_list');
    while (file_list_el.hasChildNodes()) {
        file_list_el.removeChild(file_list_el.firstChild);
    }

    let fileData = ajaxConnect('/file/upload/selectList.do', 'post', jsonObj2);
    if(nullToEmpty(fileData) !== ''){
        for(let i=0; i<fileData.length; i++){
            let file_list_el = document.getElementById('file_list');
            let input_el = document.createElement('input');
            input_el.type = 'text';
            input_el.classList.add('form-control');
            input_el.classList.add('form-control-lg');
            input_el.classList.add('form-control-solid-bg');
            input_el.classList.add('mb-2');
            input_el.value = fileData[i].fileName;
            input_el.readOnly = true;

            let label_el = document.createElement('label');
            label_el.classList.add('form-label');
            label_el.innerText = '첨부파일';

            file_list_el.append(label_el);
            file_list_el.append(input_el);
        }
    }
}

function f_member_adviser_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 고문위원 정보를 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/member/adviser/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '고문위원 삭제', '고문위원 정보가 삭제되었습니다.', '');
                    f_member_adviser_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '고문위원 정보 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }
}

function f_member_adviser_modify_init_set(seq){
    window.location.href = '/mng/member/adviser/detail.do?seq=' + seq;
}

function f_member_adviser_save(seq){
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
            let validCheck = f_member_adviser_valid();

            if(validCheck){

                /* form data setting */
                let data = f_member_adviser_form_data_setting();

                console.log(data);

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/member/adviser/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {

                                /* file function */
                                let tableSeq = data.customValue; //tableSeq return 값
                                f_company_file_upload_call(tableSeq, 'member/adviser/' + tableSeq);

                                Swal.fire({
                                    title: '고문위원 정보 변경',
                                    text: "고문위원 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_member_adviser_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '고문위원 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/member/adviser/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '고문위원 정보 등록',
                                    text: "고문위원 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/board/press.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '고문위원 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_member_adviser_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    return JSON.stringify(form);
}

function f_member_adviser_valid(){
    let position = document.querySelector('#position').value;
    let name = document.querySelector('#name').value;

    if(nvl(position,'') === ''){ showMessage('#position', 'error', '[상세 정보]', '협회직위를 입력해 주세요.', ''); return false; }
    if(nvl(name,'') === ''){ showMessage('#name', 'error', '[상세 정보]', '성명을 입력해 주세요.', ''); return false; }

    return true;
}

function f_company_file_upload_call(id, path) {

    /* 대표이미지 */
    let representImageFile = document.getElementById('representImageFile').value;
    if (nvl(representImageFile, '') !== '') {
        f_company_file_upload(id, 'representImageFile', path);
    }

}