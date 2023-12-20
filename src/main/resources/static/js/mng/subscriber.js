/***
 * mng/newsletter/subscriber.js
 * 정보센터>뉴스레터관리>뉴스레터구독자관리
 * */

$(function(){

});

function f_newsletter_subscriber_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_newsletter_subscriber_table').DataTable();
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
            condition: condition,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/newsletter/subscriber/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_newsletter_subscriber_table tbody td');
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

function f_search_condition_sel_change(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_newsletter_subscriber_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    let lang = $('#lang option:selected').val(); //참가신청언어
    let useYnVal = $('#condition_use_yn').is(':checked');
    let useYn = '';
    if(useYnVal === true){
        useYn = 'Y';
    }

    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let jsonObj = {
        lang: lang,
        useYn: useYn,
        condition: condition,
        searchText: searchText
    }

    //console.log(jsonObj);

    /* 목록 데이터 조회 */
    let resData = ajaxConnect('/mng/newsletter/subscriber/selectList.do', 'post', jsonObj);
    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_newsletter_subscriber_table tbody td');
    let cnt = 0;
    jb.each(function(index, item){
        let itemText = $(item).text();
        let itemText_trim = itemText.replaceAll(' ','');
        if(itemText_trim !== ''){
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

function f_newsletter_subscriber_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');

    /* 재조회 */
    f_newsletter_subscriber_search();
}

function f_newsletter_subscriber_modify_init_set(id){
    window.location.href = '/mng/newsletter/subscriber/detail.do?seq=' + id;
}

function f_newsletter_subscriber_remove(seq){
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
            if (result.value) {

                let jsonObj = {
                    targetSeq: seq,
                    targetTable: 'subscriber',
                    deleteReason: result.value,
                    targetMenu: getTargetMenu('mng_newsletter_subscriber_table'),
                    delYn: 'Y'
                }
                f_mng_trash_remove(jsonObj);

                f_newsletter_subscriber_search(); // 재조회

            }else{
                alert('삭제 사유를 입력해주세요.');
            }
        });

        /*let jsonObj = {
            seq: rowId
        }
        Swal.fire({
            title: '선택한 구독자 정보를 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {

                let resData = ajaxConnect('/mng/newsletter/subscriber/delete.do', 'post', jsonObj);

                if (resData.resultCode === "0") {
                    showMessage('', 'info', '구독자 정보 삭제', '구독자 정보가 삭제되었습니다.', '');
                    f_newsletter_subscriber_search(); // 삭제 성공 후 재조회 수행
                } else {
                    showMessage('', 'error', '에러 발생', '구독자 정보 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                }
            }
        });*/
    }
}

function f_newsletter_subscriber_save(seq){
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
            let validCheck = f_newsletter_subscriber_valid();

            if(validCheck){

                /* form data setting */
                let data = f_newsletter_subscriber_form_data_setting();

                let url = '/mng/newsletter/subscriber/update.do'; /* Modify */
                if(nvl(seq, '') === '') { /* Insert */
                    url = '/mng/newsletter/subscriber/insert.do'
                }

                $.ajax({
                    url: url,
                    method: 'POST',
                    async: false,
                    data: data,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    success: function (data) {
                        if (data.resultCode === "0") {

                            Swal.fire({
                                title: '구독자 상세 정보',
                                text: "구독자 상세 정보가 저장되었습니다.",
                                icon: 'info',
                                confirmButtonColor: '#3085d6',
                                confirmButtonText: '확인'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    if(nvl(seq, '') === '') {
                                        window.location.href = '/mng/newsletter/subscriber.do'; // 목록으로 이동
                                    }else{
                                        f_newsletter_subscriber_modify_init_set(seq);
                                    }
                                }
                            });
                        } else {
                            showMessage('', 'error', '구독자 상세 정보', data.resultMessage, '');
                        }
                    },
                    error: function (xhr, status) {
                        alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                    }
                })//ajax

            }//validCheck

        }//result.isConfirmed
    })

}

function f_newsletter_subscriber_valid(){
    let email = $('#email').val();

    if(nvl(email,'') === ''){ showMessage('', 'error', '[구독자 상세 정보]', '이메일을 입력해 주세요.', ''); return false; }

    let regex = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");
    if(!regex.test(email)){ showMessage('', 'error', '[구독자 상세 정보]', '올바른 이메일 주소를 입력해 주세요.', ''); return false; }

    return true;
}

function f_newsletter_subscriber_form_data_setting(){

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    return JSON.stringify(form);
}