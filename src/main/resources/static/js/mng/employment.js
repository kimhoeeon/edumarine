/***
 * mng/board/employment
 * 정보센터>게시판관리>취/창업 현황
 * */

$(function(){

});

function f_board_employment_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_board_employment_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let gbn = $('#condition_gbn option:selected').val();
    if(nullToEmpty(searchText) === ""){
        jsonObj = {
            gbn: gbn
        };
    }else{
        jsonObj = {
            gbn: gbn,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/board/employment/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_board_employment_table tbody td');
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

function f_board_employment_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_gbn').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_board_employment_search();
}

function f_board_employment_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/board/employment/selectSingle.do', 'post', jsonObj);

    /* 상세보기 Modal form Set */
    //console.log(resData);

    document.querySelector('#md_gbn').value = resData.gbn;
    document.querySelector('#md_employ_name').value = resData.employName;
    document.querySelector('#md_employ_ceo').value = resData.employCeo;
    document.querySelector('#md_employ_year').value = resData.employYear;
    document.querySelector('#md_employ_field').value = resData.employField;
    document.querySelector('#md_employ_address').value = resData.employAddress;
    document.querySelector('#md_employ_address_detail').value = resData.employAddressDetail;

}

function f_board_employment_remove(seq){
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
                if(result.value){
                    let jsonObj = {
                        targetSeq: seq,
                        targetTable: 'employment',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_board_employment_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_board_employment_search(); // 재조회

                }else{
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_board_employment_modify_init_set(seq){
    window.location.href = '/mng/board/employment/detail.do?seq=' + seq;
}

function f_board_employment_save(seq){
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
            let validCheck = f_board_employment_valid();

            if(validCheck){

                /* form data setting */
                let data = f_board_employment_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/board/employment/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '취/창업 현황 정보 변경',
                                    text: "취/창업 현황 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_board_employment_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '취/창업 현황 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/board/employment/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '취/창업 현황 정보 등록',
                                    text: "취/창업 현황 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/board/employment.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '취/창업 현황 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_board_employment_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    form.employCeo = maskingName(form.employCeo);

    return JSON.stringify(form);
}

// 문자열 검색해서 중간 글자 *로 만들기
// 4글자면 마지막 글자만
function maskingName(strName) {
    if (strName.length > 2) {
        const originName = strName.split('');
        originName.forEach(function (splitName, i) {
            if (i === 0 || i === originName.length - 1) return;
            originName[i] = '*';
        });
        const joinName = originName.join();
        return joinName.replace(/,/g, '');
    } else {
        const pattern = /.$/;
        return strName.replace(pattern, '*');
    }
}

function f_board_employment_valid(){
    let gbn = $('#gbn').val();
    let employName = $('#employName').val();

    if(nvl(gbn,'') === ''){ showMessage('', 'error', '[등록 정보]', '취/창업 구분을 선택해 주세요.', ''); return false; }
    if(nvl(employName,'') === ''){ showMessage('', 'error', '[등록 정보]', '회사명을 입력해 주세요.', ''); return false; }

    return true;
}