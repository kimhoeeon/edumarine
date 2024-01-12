/***
 * mng/file/trash
 * 파일>파일관리>임시휴지통
 * */

$(function(){

});

function f_file_trash_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_file_trash_table').DataTable();
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

    let resData = ajaxConnect('/mng/file/trash/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_file_trash_table tbody td');
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

function f_file_trash_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_file_trash_search();
}

function f_file_trash_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 데이터를 최종 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/file/trash/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '최종 삭제', '해당 데이터가 삭제되었습니다.', '');
                    f_file_trash_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '데이터 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }
}

function f_file_trash_restore(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 데이터를 복구하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '복구하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/file/trash/restore.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '최종 삭제', '해당 데이터가 복구되었습니다.', '');
                    f_file_trash_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '데이터 복구를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }
}

function f_file_trash_modify_init_set(seq){
    window.location.href = '/mng/board/notice/detail.do?seq=' + seq;
}

function f_file_trash_save(seq){
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
            let validCheck = f_file_trash_valid();

            if(validCheck){
                /* File upload */

                let fileIdList = '';
                let uploadFileList = document.getElementById('uploadFileList').children;
                let uploadFileListLen = uploadFileList.length;
                for(let i=0; i<uploadFileListLen; i++){
                    let fileId = uploadFileList[i].querySelector('input[type=hidden]').id;
                    //console.log(fileId);
                    fileIdList += fileId;
                    if((i+1) !== uploadFileListLen){
                        fileIdList += ',';
                    }
                }

                if(fileIdList !== ''){
                    let dataForm = document.getElementById('dataForm');
                    let hidden_el = document.createElement('input');
                    hidden_el.type = 'hidden';
                    hidden_el.name = 'fileIdList';
                    hidden_el.value = fileIdList;
                    dataForm.append(hidden_el);
                }

                /* form data setting */
                let data = f_file_trash_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/board/notice/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '공지사항 정보 변경',
                                    text: "공지사항 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_file_trash_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '공지사항 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/board/notice/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '공지사항 정보 등록',
                                    text: "공지사항 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/board/notice.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '공지사항 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_file_trash_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    form.uploadFile = '';

    return JSON.stringify(form);
}

function f_file_trash_valid(){
    let title = document.querySelector('#title').value;
    let writer = document.querySelector('#writer').value;
    let writeDate = document.querySelector('#writeDate').value;
    let content = document.querySelector('#quill_content').value;

    if(nvl(title,"") === ""){ showMessage('#title', 'error', '[글 등록 정보]', '제목을 입력해 주세요.', ''); return false; }
    if(nvl(writer,"") === ""){ showMessage('#writer', 'error', '[글 등록 정보]', '작성자를 입력해 주세요.', ''); return false; }
    if(nvl(writeDate,"") === ""){ showMessage('', 'error', '[글 등록 정보]', '작성일을 입력해 주세요.', ''); return false; }
    if(nvl(content,"") === ""){ showMessage('', 'error', '[글 등록 정보]', '내용을 입력해 주세요.', ''); return false; }

    return true;
}