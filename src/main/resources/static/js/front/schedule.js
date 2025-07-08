var pageNum = 1; // 페이지 번호 생성 시점에 따른 변수 초기화
var category = '전체';
$(function(){
    //페이지 오픈 시 default ()
    scheduleList(pageNum, '전체');

    $('.option_list').on('click','li',function(e){
        category = $(this).data("value");
        scheduleList(pageNum, category);
    })

});

const showPageCnt = 3; // 화면에 보일 페이지 번호 개수

/**
 * @param pageNum 출력 페이지 번호
 * @param categoryValue
 * */
function scheduleList(pageNum, categoryValue) {
    if(categoryValue !== ''){
        // 데이터 조회
        searchPosts(pageNum, categoryValue);
    }else{
        searchPosts(pageNum, $('.select_label').text());
    }

    // 페이지당 건수(10, 30, 50)가 변경되면 재조회
    /*$('#countPerPage').change(function() {
        searchPosts(1);
    });*/

    // 페이지 번호 클릭
    $(document).on('click', '.paging>ol>li>a', function() {
        if (!$(this).hasClass('this')) {
            $(this).parent().find('a.this').removeClass('this');
            $(this).addClass('this');

            searchPosts(Number($(this).text()), category);
        }
    });

    // 페이징 Icon(<<, <, >, >>) 클릭
    $(document).on('click', '.paging>span', function() {
        const totalCnt = parseInt($('span.number').text());
        const countPerPage = 5;
        const totalPage = Math.ceil(totalCnt / countPerPage);

        const id = $(this).attr('id');

        if (id === 'first_page') { //<<
            searchPosts(1, category);
        } else if (id === 'prev_page') { //<
            let arrPages = [];
            $('.paging>ol>li>a').each(function() {
                arrPages.push(Number($(this).text()));
            });
            const prevPage = Math.min(...arrPages) - 1;
            searchPosts(prevPage, category);
        } else if (id === 'next_page') { //>
            let arrPages = [];
            $('.paging>ol>li>a').each(function() {
                arrPages.push(Number($(this).text()));
            });
            const nextPage = Math.max(...arrPages) + 1;
            searchPosts(nextPage, category);
        } else if (id === 'last_page') { //>>
            searchPosts(totalPage, category);
        }
    });

}

/**
 * 페이지별 데이터를 조회합니다.
 * @param {int} pageNum - Page Number
 * @param categoryValue
 */
function searchPosts(pageNum, categoryValue) {
    const countPerPage = 5; // 페이지당 노출 개수
    const start = (pageNum - 1) * countPerPage;
    let flag = true;

    /* 검색조건 */
    let searchText = $('#search_text').val();
    let condition = $('#search_box option:selected').val();
    let year = $('#trainYear').val();

    let jsonObj = {
        pageNum: start,
        rows: countPerPage,
        category: categoryValue,
        year: year,
        condition: condition,
        searchText: searchText
    };

    $.ajax({
        url: '/apply/schedule/selectList.do',
        method: 'post',
        data: JSON.stringify(jsonObj),
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    })
    .done(function (data, status){
        // console.log(status);
        // console.log(data);
        let results = data;
        let str = '';
        let today = getCurrentDate('N').replaceAll('-','.'); // YYYY.MM.DD
        $.each(results , function(i){
            let seq = results[i].seq;
            let gbn = results[i].gbn;
            let gbnDepth = results[i].gbnDepth;
            if(nvl(gbnDepth,'') !== ''){
                gbn = gbnDepth + ' ' + gbn;
            }
            let nextTime = results[i].nextTime;

            let trainStartDttm = results[i].trainStartDttm;
            let trainEndDttm = results[i].trainEndDttm;
            let applyStartDttm = results[i].applyStartDttm;
            let applyEndDttm = results[i].applyEndDttm;
            let trainNote = results[i].trainNote;

            let paySum = results[i].paySum;
            let trainCnt = results[i].trainCnt;
            let trainApplyCnt = results[i].trainApplyCnt;
            let closingYn = results[i].closingYn;

            // 마감여부체크
            let trainBtnText = '교육신청';
            if(closingYn === 'N'){
                if(applyStartDttm > today){
                    trainBtnText = '접수예정';
                    if(trainStartDttm > today){
                        trainBtnText = '교육예정';
                    }
                }else{
                    if(applyEndDttm < today){
                        trainBtnText = '접수마감';
                        if(trainEndDttm < today){
                            trainBtnText = '교육마감';
                        }
                    }else{
                        if(trainCnt === trainApplyCnt){
                            trainBtnText = '접수마감';
                        }
                    }
                }
            }else{
                trainBtnText = '교육마감';
            }

            applyStartDttm = applyStartDttm.toString().substring(2);
            applyEndDttm = applyEndDttm.toString().substring(2);
            trainStartDttm = trainStartDttm.toString().substring(2);
            trainEndDttm = trainEndDttm.toString().substring(2);

            let thumbnailImage = '/img/sample_img.jpg';
            let applyPath = '';

            str += '<li>';
                str += '<div class="thumb">';
                    switch (gbn){
                        case '상시신청':
                            thumbnailImage = '/img/thumbnail_always.jpg';
                            applyPath = '/apply/eduApply01.do';
                            break;
                        case '해상엔진 테크니션 (선내기/선외기)':
                            thumbnailImage = '/img/thumbnail_engine.jpg';
                            applyPath = '/apply/eduApply02.do';
                            break;
                        case 'FRP 레저보트 선체 정비 테크니션':
                            thumbnailImage = '/img/thumbnail_frp.png';
                            /*applyPath = '/apply/eduApply03.do';*/
                            applyPath = 'https://yachtmnr.or.kr/common/greeting.do';
                            break;
                        case '해상엔진 자가정비 (선외기)':
                            thumbnailImage = '/img/thumbnail_outboarder.jpg';
                            applyPath = '/apply/eduApply05.do';
                            break;
                        case '해상엔진 자가정비 (선내기)':
                            thumbnailImage = '/img/thumbnail_inboarder.jpg';
                            applyPath = '/apply/eduApply04.do';
                            break;
                        case '해상엔진 자가정비 (세일요트)':
                            thumbnailImage = '/img/thumbnail_sale.jpg';
                            applyPath = '/apply/eduApply06.do';
                            break;
                        case '고마력 선외기 정비 중급 테크니션':
                            thumbnailImage = '/img/thumbnail_highhorsepower_re.jpg';
                            applyPath = '/apply/eduApply07.do';
                            break;
                        case '자가정비 심화과정 (고마력 선외기)':
                            thumbnailImage = '/img/thumbnail_highself_re.jpg';
                            applyPath = '/apply/eduApply09.do';
                            break;
                        case '고마력 선외기 정비 중급 테크니션 (특별반)':
                            thumbnailImage = '/img/thumbnail_highspecial_re.jpg';
                            applyPath = '/apply/eduApply10.do';
                            break;
                        case '스턴드라이브 정비 전문가과정':
                            thumbnailImage = '/img/thumbnail_sterndrive_re.jpg';
                            applyPath = '/apply/eduApply08.do';
                            break;
                        case '스턴드라이브 정비 전문가과정 (특별반)':
                            thumbnailImage = '/img/thumbnail_sternspecial.png';
                            applyPath = '/apply/eduApply11.do';
                            break;
                        case '마리나 선박 선외기 정비사 실무과정':
                            thumbnailImage = '/img/thumbnail_marina_out.jpg';
                            applyPath = 'https://yachtmnr.or.kr/common/greeting.do';
                            break;
                        case '마리나 선박 선내기 정비사 실무과정':
                            thumbnailImage = '/img/thumbnail_marina_in.jpg';
                            applyPath = 'https://yachtmnr.or.kr/common/greeting.do';
                            break;
                        case '선외기 기초정비교육':
                            thumbnailImage = '/img/thumbnail_outboarder_basic.png';
                            applyPath = '/apply/eduApply12.do';
                            break;
                        case '선내기 기초정비교육':
                            thumbnailImage = '/img/thumbnail_inboarder_basic.png';
                            applyPath = '/apply/eduApply13.do';
                            break;
                        case '세일요트 기초정비교육':
                            thumbnailImage = '/img/thumbnail_sailyacht_basic.png';
                            applyPath = '/apply/eduApply14.do';
                            break;
                        case '선외기 응급조치교육':
                            thumbnailImage = '/img/thumbnail_outboarder_emergency.png';
                            applyPath = '/apply/eduApply15.do';
                            break;
                        case '선내기 응급조치교육':
                            thumbnailImage = '/img/thumbnail_inboarder_emergency.png';
                            applyPath = '/apply/eduApply16.do';
                            break;
                        case '세일요트 응급조치교육':
                            thumbnailImage = '/img/thumbnail_sailyacht_emergency.png';
                            applyPath = '/apply/eduApply17.do';
                            break;
                        case '발전기 정비 교육':
                            thumbnailImage = '/img/thumbnail_generator.png';
                            applyPath = '/apply/eduApply18.do';
                            break;
                        case '선외기/선내기 직무역량 강화과정':
                            thumbnailImage = '/img/thumbnail_competency.png';
                            applyPath = '/apply/eduApply19.do';
                            break;
                        case '선내기 팸투어':
                            thumbnailImage = '/img/thumbnail_famtourin.png';
                            applyPath = '/apply/eduApply20.do';
                            break;
                        case '선외기 팸투어':
                            thumbnailImage = '/img/thumbnail_famtourout.png';
                            applyPath = '/apply/eduApply21.do';
                            break;
                        default:
                            break;
                    }
                    str += '<img src="' + thumbnailImage + '">';
                str += '</div>';
                str += '<div class="info">';
                    str += '<div class="subject">';
                        str += gbn;
                    str += '</div>';
                    str += '<ul class="description">';
                        str += '<li>';
                            str += '<div class="gubun">';
                                str += '차시';
                            str += '</div>';
                            str += '<div class="naeyong">';
                                str += nextTime;
                            str += '</div>';
                        str += '</li>';
                        str += '<li>';
                            str += '<div class="gubun">';
                                str += '교육일정';
                            str += '</div>';
                            str += '<div class="naeyong">';
                                str += trainStartDttm + ' ~ ' + trainEndDttm;
                            str += '</div>';
                        str += '</li>';
                        str += '<li>';
                            str += '<div class="gubun">';
                                str += '접수기간';
                            str += '</div>';
                            str += '<div class="naeyong">';
                                str += applyStartDttm + ' ~ ' + applyEndDttm;
                            str += '</div>';
                        str += '</li>';
                        str += '<li>';
                            str += '<div class="gubun">';
                                str += '교육비';
                            str += '</div>';
                            str += '<div class="naeyong">';
                                str += Number(paySum).toLocaleString() + '원';
                            str += '</div>';
                        str += '</li>';
                        str += '<li>';
                            str += '<div class="gubun">';
                                str += '교육인원';
                            if(!applyPath.includes('yachtmnr')) {
                                str += '(현 신청인원)';
                            }
                            str += '</div>';
                            str += '<div class="naeyong">';
                                str += '<span class="color">';
                                    str += trainCnt;
                                str += '</span>';
                                if(!applyPath.includes('yachtmnr')) {
                                    str += '(' + trainApplyCnt + ')';
                                }
                            str += '</div>';
                        str += '</li>';

                        str += '<li>';
                            str += '<div class="gubun">';
                                str += '기타';
                            str += '</div>';
                            str += '<div class="naeyong">';
                                str += trainNote;
                            str += '</div>';
                        str += '</li>';
                    str += '</ul>';
                str += '</div>';
                if(trainBtnText === '교육신청'){
                    str += '<div class="btn">';
                        if(applyPath.includes('yachtmnr')){
                            str += '<a href="' + applyPath + '" target="_blank">';
                            str += trainBtnText;
                            str += '</a>';
                        }else{
                            str += '<a href="javascript:void(0);" onclick="f_apply_page_move(' + '\'' + seq + '\'' + ',' + '\'' + applyPath + '\'' + ')">';
                            str += trainBtnText;
                            str += '</a>';
                        }
                    str += '</div>';
                }else{
                    str += '<div class="btn">';
                        str += '<a href="javascript:void(0);" onclick="alert(' + '\'' + trainBtnText + ' 인 교육입니다.'+ '\')" style="background-color: #555; cursor: unset">';
                            str += trainBtnText;
                        str += '</a>';
                    str += '</div>';
                }
            str += '</li>';

            if(results.length === (i+1)){ // each 문이 모두 실행되면 아래 페이징 정보 세팅 실행
                flag = false;
            }
        });

        $('.sked_list_wrap .sked_list').empty();
        $('.sked_list_wrap .sked_list').html(str);

        if(nvl(results,"") !== "") {
            // 맨 처음에만 total 값 세팅
            if (pageNum === 1) {
                $('span.number').text(results[0].totalRecords || 0);
            }
        }else{ // 데이터 없는 경우
            $('span.number').text(0);
            $('.paging ol').empty(); // 페이징 번호 없애기
            let emptyStr = '';
            emptyStr += '<li>';
                emptyStr += '<div class="thumb">';
                emptyStr += '</div>';
                emptyStr += '<div class="info">';
                    emptyStr += '<div class="subject">';
                        emptyStr += '해당 조건으로 검색된 교육이 없습니다.';
                    emptyStr += '</div>';
                emptyStr += '</div>';
            emptyStr += '</li>';
            $('.sked_list_wrap .sked_list').html(emptyStr);
        }
    })
    .fail(function(xhr, status, errorThrown) {
        $('body').html("오류가 발생했습니다.")
            .append("<br>오류명: " + errorThrown)
            .append("<br>상태: " + status);
    })
    .always(function() {
        if(!flag){ // flag = false 이면 아래 페이징 정보 세팅 실행
            // 페이징 정보 세팅
            setPaging(pageNum);
        }
    });
}

function f_apply_page_move(trainSeq, endpoint){
    let id = sessionStorage.getItem('id');
    if(nvl(id,'') !== ''){
        Swal.fire({
            title: '[ 교육 신청 ]',
            html: '신청 페이지로 이동하시겠습니까?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#00a8ff',
            confirmButtonText: '이동하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then(async (result) => {
            if (result.isConfirmed) {
                window.location.href = endpoint + '?seq=' + trainSeq;
            }
        });
    }else{
        Swal.fire({
            title: '[ 교육 신청 ]',
            html: '로그인이 필요합니다.<br> 로그인 페이지로 이동하시겠습니까?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#00a8ff',
            confirmButtonText: '이동하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then(async (result) => {
            if (result.isConfirmed) {
                window.location.href = '/member/login.do';
            }
        });
    }
}

/**
 * 페이징 정보를 세팅합니다.
 * @param {int} pageNum - Page Number
 */
function setPaging(pageNum) {
    const totalCnt = parseInt($('span.number').text());
    const countPerPage = 5;

    const currentPage = pageNum;
    const totalPage = Math.ceil(totalCnt / countPerPage);

    showAllIcon();

    if (currentPage <= showPageCnt) {
        $('#first_page').hide();
        $('#prev_page').hide();
    }
    if (
        totalPage <= showPageCnt ||
        Math.ceil(currentPage / showPageCnt) * showPageCnt + 1 > totalPage
    ) {
        $('#next_page').hide();
        $('#last_page').hide();
    }

    let start = Math.floor((currentPage - 1) / showPageCnt) * showPageCnt + 1;
    let sPagesHtml = '';
    for (const end = start + showPageCnt; start < end && start <= totalPage; start++) {
        sPagesHtml += '<li><a class="' + (start === currentPage ? 'this' : 'other') + '" style="cursor: pointer">' + start + '</a></li>';
    }
    $('.paging ol').html(sPagesHtml);
}

/**
 * Icon(<<, <, >, >>) All Show
 */
function showAllIcon() {
    $('#first_page').show();
    $('#prev_page').show();
    $('#next_page').show();
    $('#last_page').show();
};