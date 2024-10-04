/***
 * mng/board/faq
 * 정보센터>게시판관리>FAQ
 * */

$(function(){

});

function f_board_faq_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_board_faq_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let exposureYn = $('#condition_exposure_yn option:selected').val();
    if(nullToEmpty(searchText) === ""){
        jsonObj = {
            exposureYn: exposureYn
        };
    }else{
        jsonObj = {
            exposureYn: exposureYn ,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/board/faq/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_board_faq_table tbody td');
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

function f_board_faq_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_exposure_yn').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_board_faq_search();
}

function f_board_faq_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/board/faq/selectSingle.do', 'post', jsonObj);

    if(resData.exposureYn === 'Y'){
        document.querySelector('#md_exposure_yn').checked = true;
    }else{
        document.querySelector('#md_exposure_yn').checked = false;
    }
    document.querySelector('#md_writer').value = resData.writer;
    document.querySelector('#md_write_date').value = resData.writeDate;
    document.querySelector('#md_question').value = resData.question;
    document.querySelector('#md_answer').innerHTML = resData.answer;

}

function f_board_faq_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ''){

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
                        targetTable: 'faq',
                        deleteReason: result.value,
                        targetMenu: getTargetMenu('mng_board_faq_table'),
                        delYn: 'Y'
                    }
                    f_mng_trash_remove(jsonObj);

                    f_board_faq_search(); // 재조회

                } else {
                    alert('삭제 사유를 입력해주세요.');
                }
            }
        });

    }
}

function f_board_faq_modify_init_set(seq){
    window.location.href = '/mng/board/faq/detail.do?seq=' + seq;
}

function f_board_faq_save(seq){
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
            let validCheck = f_board_faq_valid();

            if(validCheck){

                /* form data setting */
                let data = f_board_faq_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/board/faq/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === '0') {
                                Swal.fire({
                                    title: 'FAQ 글 정보 변경',
                                    text: "FAQ 글 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_board_faq_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', 'FAQ 글 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/board/faq/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === '0') {
                                Swal.fire({
                                    title: 'FAQ 글 정보 등록',
                                    text: "FAQ 글 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/board/faq.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', 'FAQ 글 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_board_faq_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    if(nvl(form.exposureYn,'') === ''){
        form.exposureYn = 'N';
    }

    return JSON.stringify(form);
}

function f_board_faq_valid(){
    let writer = $('#writer').val();
    if(nvl(writer,'') === ''){ showMessage('', 'error', '[FAQ 정보]', '작성자를 입력해 주세요.', ''); return false; }

    let writeDate = $('#writeDate').val();
    if(nvl(writeDate,'') === ''){ showMessage('', 'error', '[FAQ 정보]', '작성일을 입력해 주세요.', ''); return false; }

    let question = $('#question').val();
    if(nvl(question,'') === ''){ showMessage('', 'error', '[FAQ 정보]', '질문을 입력해 주세요.', ''); return false; }

    let answer = $('#quill_content').val();
    if(nvl(answer,'') === ''){ showMessage('', 'error', '[FAQ 정보]', '답변을 입력해 주세요.', ''); return false; }

    return true;
}