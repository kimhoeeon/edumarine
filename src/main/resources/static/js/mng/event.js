/***
 * mng/board/event
 * 정보센터>게시판관리>행사 게시판
 * */

$(function(){

    /* 행사 시작일 */
    let startDatePicker = document.getElementById('startDate');
    if(startDatePicker) {
        startDatePicker.flatpickr({
            enableTime: false,
            dateFormat: "Y-m-d",
            defaultDate: new Date()
        });
    }

    /* 행사 종료일 */
    let endDatePicker = document.getElementById('endDate');
    if(endDatePicker) {
        endDatePicker.flatpickr({
            enableTime: false,
            dateFormat: "Y-m-d"
        });
    }

});

function f_board_event_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_board_event_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();
    if(nvl(searchText,'') === ''){
        jsonObj = {
            condition: condition
        };
    }else{
        jsonObj = {
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/board/event/selectList.do', 'post', jsonObj);
    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_board_event_table tbody td');
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

function f_board_event_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_board_event_search();
}

function f_board_event_detail_modal_set(seq){
    /* TM 및 잠재DB 목록 상세 조회 */
    let jsonObj = {
        seq: seq
    };

    let resData = ajaxConnect('/mng/board/event/selectSingle.do', 'post', jsonObj);

    /* 공지사항 상세보기 Modal form Set */
    //console.log(resData);

    if(resData.lang === "KO"){
        document.querySelector('#md_lang').value = '국문';
    }else{
        document.querySelector('#md_lang').value = '영문';
    }

    document.querySelector('#md_title').value = resData.title;
    document.querySelector('#md_writer').value = resData.writer;
    document.querySelector('#md_write_date').value = resData.writeDate;
    document.querySelector('#md_location').value = resData.location;
    document.querySelector('#md_start_date').value = resData.startDate;
    document.querySelector('#md_end_date').value = resData.endDate;

}

function f_board_event_remove(seq){
    //console.log('삭제버튼');
    if(nullToEmpty(seq) !== ""){
        let jsonObj = {
            seq: seq
        }
        Swal.fire({
            title: '선택한 행사를 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/board/event/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '행사 삭제', '행사가 삭제되었습니다.', '');
                    f_board_event_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '행사 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });
    }
}

function f_board_event_modify_init_set(seq){
    window.location.href = '/mng/board/event/detail.do?seq=' + seq;
}

function f_board_event_save(seq){
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
            let validCheck = f_board_event_valid();

            if(validCheck){

                /* form data setting */
                let data = f_board_event_form_data_setting();

                /* Modify */
                if(nvl(seq, '') !== ''){
                    $.ajax({
                        url: '/mng/board/event/update.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === "0") {
                                Swal.fire({
                                    title: '행사 정보 변경',
                                    text: "행사 정보가 변경되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        f_board_event_modify_init_set(seq); // 재조회
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '행사 정보 변경을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                            }
                        },
                        error: function (xhr, status) {
                            alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                        }
                    })//ajax
                }else { /* Insert */
                    $.ajax({
                        url: '/mng/board/event/insert.do',
                        method: 'POST',
                        async: false,
                        data: data,
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        success: function (data) {
                            if (data.resultCode === '0') {
                                Swal.fire({
                                    title: '행사 정보 등록',
                                    text: "행사 정보가 등록되었습니다.",
                                    icon: 'info',
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonText: '확인'
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        window.location.href = '/mng/board/event.do'; // 목록으로 이동
                                    }
                                });
                            } else {
                                showMessage('', 'error', '에러 발생', '행사 정보 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
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

function f_board_event_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    let writer = form.writer;
    if(nvl(writer,'') === ''){
        form.writer = 'SIPA';
    }

    return JSON.stringify(form);
}

function f_board_event_valid(){
    let title = document.querySelector('#title').value;
    let location = document.querySelector('#location').value;
    let startDate = document.querySelector('#startDate').value;
    let endDate = document.querySelector('#endDate').value;

    if(nvl(title,'') === ''){ showMessage('#title', 'error', '[행사 정보]', '행사명을 입력해 주세요.', ''); return false; }
    if(nvl(location,'') === ''){ showMessage('', 'error', '[행사 정보]', '장소를 입력해 주세요.', ''); return false; }
    if(nvl(startDate,'') === ''){ showMessage('', 'error', '[행사 정보]', '행사 시작일을 입력해 주세요.', ''); return false; }
    if(nvl(endDate,'') === ''){ showMessage('', 'error', '[행사 정보]', '행사 종료일을 입력해 주세요.', ''); return false; }

    return true;
}