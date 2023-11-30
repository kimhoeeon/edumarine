/***
 * mng/board/community
 * 정보센터>게시판관리>커뮤니티
 * */

$(function(){

    // 파일 입력 변경에 대한 이벤트 핸들러 추가
    $(document).on('change', '.upload_hidden', function () {
        let fileName = $(this).val().split('\\').pop();
        let fileNameInput = $(this).parent('div').siblings('div').find('.upload_name');
        fileNameInput.val(fileName);
    });

});

function f_board_community_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_board_community_table').DataTable();
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

    let resData = ajaxConnect('/mng/board/community/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_board_community_table tbody td');
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

function f_board_community_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_board_community_search();
}

function f_board_community_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/board/community/selectSingle.do', 'post', jsonObj);

    /* 사진자료 상세보기 Modal form Set */
    //console.log(resData);

    if(resData.gbn==="1"){
        document.querySelector('#md_notice_gbn').checked = true;
    }else{
        document.querySelector('#md_notice_gbn').checked = false;
    }

    document.querySelector('#md_title').value = resData.title;
    document.querySelector('#md_writer').value = resData.writer;
    document.querySelector('#md_write_date').value = resData.writeDate;

    if(nvl(resData.hashtag,'') !== ''){
        let hashtagArr = resData.hashtag.split(',');
        let hashtagVal = '';
        for(let i=0; i<hashtagArr.length; i++){
            hashtagVal += '<span class="mr10">';
            hashtagVal += '#' + hashtagArr[i];
            hashtagVal += '</span>';
        }
        document.querySelector('#md_hashtag').innerHTML = hashtagVal;
    }else{
        document.querySelector('#md_hashtag').innerHTML = '';
    }

    document.querySelector('#md_content').innerHTML = resData.content;
    document.querySelector('#md_recommend_cnt').value = resData.recommendCnt;
    document.querySelector('#md_view_cnt').value = resData.viewCnt;

    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj2 = {
        userId: seq
    };

    $('#file_list label').remove();
    $('#file_list input').remove();

    let fileData = ajaxConnect('/file/upload/selectList.do', 'post', jsonObj2);
    if(nullToEmpty(fileData) !== ''){
        for(let i=0; i<fileData.length; i++){
            let file_list_el = document.getElementById('file_list');
            let label_el = document.createElement('label');
            label_el.classList.add('form-label');
            label_el.innerText = '첨부파일 ' + (i+1);

            file_list_el.append(label_el);

            let input_el = document.createElement('input');
            input_el.type = 'text';
            input_el.classList.add('form-control');
            input_el.classList.add('form-control-lg');
            input_el.classList.add('form-control-solid-bg');
            input_el.classList.add('mb-4');
            input_el.value = fileData[i].fileName;
            input_el.readOnly = true;

            file_list_el.append(input_el);
        }
    }
}

function f_board_community_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 커뮤니티 글을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/board/community/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '커뮤니티 글 삭제', '커뮤니티 글이 삭제되었습니다.', '');
                    f_board_community_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '커뮤니티 글 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }
}

function f_board_community_modify_init_set(seq){
    window.location.href = '/mng/board/community/detail.do?seq=' + seq;
}

function f_board_community_save(seq){
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
            let validCheck = f_board_community_valid();

            if(validCheck){
                /* File upload */

                let fileIdList = '';
                let uploadFileList = document.getElementById('uploadFileList').children;
                let uploadFileListLen = uploadFileList.length;
                for(let i=0; i<uploadFileListLen; i++){
                    let fileId = uploadFileList[i].children[1].id;
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
                let data = f_board_community_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/board/community/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '커뮤니티 글 정보 변경',
                                    text: "커뮤니티 글 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_board_community_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '커뮤니티 글 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/board/community/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '커뮤니티 글 정보 등록',
                                    text: "커뮤니티 글 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/board/community.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '커뮤니티 글 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_board_community_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    form.uploadFile = '';
    form.writerKey = 'admin';

    return JSON.stringify(form);
}

function f_board_community_valid(){
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

function f_board_community_reply_modal_set(seq){
    // TODO: 댓글 관리 호출

}