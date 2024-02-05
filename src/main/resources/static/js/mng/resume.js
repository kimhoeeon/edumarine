/***
 * mng/customer/member
 * 회원/신청>회원관리>전체회원목록
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

function f_customer_resume_search(){

    /* 로딩페이지 */
    loadingBarShow();

    /* DataTable Data Clear */
    let dataTbl = $('#mng_customer_resume_table').DataTable();
    dataTbl.clear();
    dataTbl.draw(false);

    /* TM 및 잠재DB 목록 데이터 조회 */
    let jsonObj;
    let condition = $('#search_box option:selected').val();
    let searchText = $('#search_text').val();

    let sex = $('#condition_sex option:selected').val();
    if(nullToEmpty(searchText) === ""){
        jsonObj = {
            sex: sex
        };
    }else{
        jsonObj = {
            sex: sex ,
            condition: condition ,
            searchText: searchText
        }
    }

    let resData = ajaxConnect('/mng/customer/resume/selectList.do', 'post', jsonObj);

    dataTbl.rows.add(resData).draw();

    /* 조회 카운트 입력 */
    document.getElementById('search_cnt').innerText = resData.length;

    /* DataTable Column tooltip Set */
    let jb = $('#mng_customer_resume_table tbody td');
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

function f_customer_resume_search_condition_init(){
    $('#search_box').val('').select2({minimumResultsForSearch: Infinity});
    $('#search_text').val('');
    $('#condition_sex').val('').select2({minimumResultsForSearch: Infinity});

    /* 재조회 */
    f_customer_resume_search();
}

function f_customer_resume_modify_init_set(seq){
    window.location.href = '/mng/customer/resume/detail.do?seq=' + seq;
}

function f_customer_resume_detail_modal_set(seq){
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