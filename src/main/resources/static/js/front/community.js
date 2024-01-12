var pageNum = 1; // 페이지 번호 생성 시점에 따른 변수 초기화
$(function(){
    //페이지 오픈 시 default ()
    communityList(pageNum);

    $('.reply_write.replyToReply').css('display','none');

    $('.reply_info .comment').on('click', function(){
        $(this).parent().parent('.reply_box').next('.reply_write.replyToReply').css('display','block');
    })

    $('.reply_write .btn_cancel').on('click', function(){
        $(this).parent().parent().parent('.reply_write.replyToReply').css('display','none');
        $(this).parent().siblings('#replyToReplyContent').val('');
    })

});

const showPageCnt = 3; // 화면에 보일 페이지 번호 개수

/**
 * @param pageNum 출력 페이지 번호
 * */
function communityList(pageNum) {
    // 데이터 조회
    searchPosts(pageNum);

    // 페이지당 건수(10, 30, 50)가 변경되면 재조회
    /*$('#countPerPage').change(function() {
        searchPosts(1);
    });*/

    // 페이지 번호 클릭
    $(document).on('click', '.paging>ol>li>a', function() {
        if (!$(this).hasClass('this')) {
            $(this).parent().find('a.this').removeClass('this');
            $(this).addClass('this');

            searchPosts(Number($(this).text()));
        }
    });

    // 페이징 Icon(<<, <, >, >>) 클릭
    $(document).on('click', '.paging>span', function() {
        const totalCnt = parseInt($('span.number').text());
        const countPerPage = 10;
        const totalPage = Math.ceil(totalCnt / countPerPage);

        const id = $(this).attr('id');

        if (id === 'first_page') { //<<
            searchPosts(1);
        } else if (id === 'prev_page') { //<
            let arrPages = [];
            $('.paging>ol>li>a').each(function() {
                arrPages.push(Number($(this).text()));
            });
            const prevPage = Math.min(...arrPages) - 1;
            searchPosts(prevPage);
        } else if (id === 'next_page') { //>
            let arrPages = [];
            $('.paging>ol>li>a').each(function() {
                arrPages.push(Number($(this).text()));
            });
            const nextPage = Math.max(...arrPages) + 1;
            searchPosts(nextPage);
        } else if (id === 'last_page') { //>>
            searchPosts(totalPage);
        }
    });

}

/**
 * 페이지별 데이터를 조회합니다.
 * @param {int} pageNum - Page Number
 */
function searchPosts(pageNum) {
    const countPerPage = 10; // 페이지당 노출 개수
    const start = (pageNum - 1) * countPerPage;
    let flag = true;

    /* 검색조건 */
    let searchText = $('#search_text').val();
    let condition = $('#search_box option:selected').val();

    let jsonObj = {
        pageNum: start,
        rows: countPerPage,
        condition: condition,
        searchText: searchText
    };

    $.ajax({
        url: '/job/community/selectList.do',
        method: 'post',
        data: JSON.stringify(jsonObj),
        contentType: 'application/json; charset=utf-8' //server charset 확인 필요
    })
    .done(function (data, status){
        // console.log(status);
        // console.log(data);
        let results = data;
        let str = '';
        $.each(results , function(i){
            let rownum = results[i].rownum;
            let seq = results[i].seq;
            let gbn = results[i].gbn;
            let title = results[i].title;
            let writer = results[i].writer;
            let writerKey = results[i].writerKey;
            let writeDate = results[i].writeDate;
            writeDate = writeDate.split(' ')[0].replaceAll('-','.');
            let viewCnt = results[i].viewCnt;
            let recommendCnt = results[i].recommendCnt;

            if(gbn === '1'){
                str += '<li class="important">';
                    str += '<div class="number">';
                        str += '중요';
                    str += '</div>';
            }else{
                str += '<li>';
                    str += '<div class="number">';
                    str += rownum;
                    str += '</div>';
            }
                str += '<div class="info">';
                    str += '<div class="subject">';
                        str += '<a href="javascript:void(0);" onclick="location.href=\'/job/community_view.do?seq=' + seq + '\'">';
                            str += title;
                        str += '</a>';
                    str += '</div>';
                    str += '<div class="description">';
                        str += '<div class="left">';
                            str += '<div class="write">';
                                str += writer;
                            str += '</div>';
                            str += '<div class="date">';
                                str += writeDate;
                            str += '</div>';
                        str += '</div>';
                        str += '<div class="right">';
                            str += '<div class="views">';
                                str += '<span class="gubun">';
                                    str += '조회';
                                str += '</span>';
                                str += viewCnt;
                            str += '</div>';
                            str += '<div class="recommend">';
                                str += '<span class="gubun">';
                                    str += '추천';
                                str += '</span>';
                                str += recommendCnt;
                            str += '</div>';
                        str += '</div>';
                    str += '</div>';
                str += '</div>';
            str += '</li>';

            if(results.length === (i+1)){ // each 문이 모두 실행되면 아래 페이징 정보 세팅 실행
                flag = false;
            }
        });

        $('.community_list_wrap .community_list').empty();
        $('.community_list_wrap .community_list').html(str);

        if(nvl(results,"") !== "") {
            // 맨 처음에만 total 값 세팅
            if (pageNum === 1) {
                $('span.number').text(results[0].totalRecords || 0);
            }
        }else{ // 데이터 없는 경우 ( 해당 권역에 양조장 없을 경우 )
            $('span.number').text(0);
            $('.paging ol').empty(); // 페이징 번호 없애기
            let emptyStr = '';
            emptyStr += '<li>';
            emptyStr += '<div>';
                emptyStr += '해당 조건으로 검색된 게시물이 없습니다.';
            emptyStr += '</div>';
            emptyStr += '</li>';
            $('.community_list_wrap .community_list').html(emptyStr);
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

/**
 * 페이징 정보를 세팅합니다.
 * @param {int} pageNum - Page Number
 */
function setPaging(pageNum) {
    const totalCnt = parseInt($('span.number').text());
    const countPerPage = 10;

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
}

function f_main_community_recommend_btn(id, seq, el){
    if(nvl(id,'') !== ''){
        /*let btnOnYn = $(el).hasClass('on');
        if(!btnOnYn) {
            /!* 추천 누름 *!/
        }else{
            /!* 추천 취소 *!/
        }*/

        let jsonObj = { memberId: id, communitySeq: seq };

        let resData = ajaxConnect('/job/community/recommend/update.do', 'post', jsonObj);
        //console.log(i , resData);
        if (resData.resultCode === "0") {
            $(el).toggleClass('on');
        }
    }else{
        Swal.fire({
            title: '[로그인 필요]',
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

function f_main_community_reply_add(communitySeq, depth, moReplySeq, writer, el){
    if(nvl(writer,'') !== ''){
        let moReplySeqVal = null;
        let content = $(el).parent().prev('textarea').val();
        if(depth === '1'){
            moReplySeqVal = moReplySeq;
        }

        let jsonObj = {
            communitySeq: communitySeq,
            depthReplyNo: depth,
            moReplySeq: moReplySeqVal,
            writer: writer,
            content: content
        };

        let resData = ajaxConnect('/job/community/reply/insert.do', 'post', jsonObj);
        //console.log(i , resData);
        if (resData.resultCode === "0") {
            window.location.href = '/job/community_view.do?seq=' + communitySeq;
        }

    }else{
        Swal.fire({
            title: '[로그인 필요]',
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

function f_main_community_reply_remove(pageGbn, depthReplyNo, communitySeq, replySeq){
    Swal.fire({
        title: '[댓글 삭제]',
        html: '댓글을 삭제하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '삭제하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            let jsonObj = {
                seq: replySeq,
                depthReplyNo: depthReplyNo,
                moReplySeq: replySeq
            };
            let resData = ajaxConnect('/job/community/reply/delete.do', 'post', jsonObj);
            if (resData.resultCode === "0") {
                if(pageGbn === "C"){
                    window.location.href = '/job/community_view.do?seq=' + communitySeq;
                }else{
                    window.location.href = '/mypage/post.do';
                }
            }
        }
    });
}

function f_main_community_reply_all_remove(id){
    if(nvl(id,'') !== '') {
        let replyCheckArr = $('input[type=checkbox][name=replyCheck]:checked');
        if (replyCheckArr.length > 0) {
            let flag = false;
            for (let i = 0; i < replyCheckArr.length; i++) {
                let jsonObj = {
                    seq: replyCheckArr.eq(i).val()
                };
                let resData = ajaxConnect('/job/community/reply/delete.do', 'post', jsonObj);
                if ((i + 1) === replyCheckArr.length) {
                    flag = true;
                }
            }

            if (flag) {
                window.location.href = '/mypage/post.do';
            }
        } else {
            showMessage('', 'error', '[커뮤니티]', '삭제할 댓글을 선택해주세요.', '');
        }
    }else{
        Swal.fire({
            title: '[로그인 필요]',
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

function f_main_community_add(id){
    let title = $('#title').val();
    if(nvl(title,'') === ''){ showMessage('', 'error', '[글 등록 정보]', '제목을 입력해 주세요.', ''); return false; }

    let content = $('#quill_content').val().replaceAll(/<[^>]*>?/g, '');
    if(nvl(content,'') === ''){ showMessage('', 'error', '[글 등록 정보]', '내용을 입력해 주세요.', ''); return false; }

    let hashtagCheckbox = $('input[type=checkbox][name=hashtag]:checked');
    if(hashtagCheckbox.length === 0){ showMessage('', 'error', '[글 등록 정보]', '키워드를 1개 이상 선택해 주세요.', ''); return false; }

    let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

    //해시태그
    let hashtagArr = hashtagCheckbox;
    let hashtagArrLen = hashtagArr.length;
    let hashtag = '';
    for(let i=0; i<hashtagArrLen; i++){
        hashtag += hashtagArr.eq(i).val();
        if((i+1) !== hashtagArrLen){
            hashtag += ',';
        }
    }
    form.hashtag = hashtag;

    form.writer = id;
    form.writerKey = id;

    Swal.fire({
        title: '[커뮤니티]',
        html: '커뮤니티 글을 등록하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '등록하기',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/job/community/write/insert.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(form),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        window.location.href = '/job/community_list.do'; // 목록으로 이동
                    }else {
                        showMessage('', 'error', '에러 발생', '커뮤니티 글 등록을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });
}

function f_main_community_modify(id, communitySeq){
    if(nvl(id,'') !== ''){
        let title = $('#title').val();
        if(nvl(title,'') === ''){ showMessage('', 'error', '[게시글 정보]', '제목을 입력해 주세요.', ''); return false; }

        let content = $('#quill_content').val().replaceAll(/<[^>]*>?/g, '');
        if(nvl(content,'') === ''){ showMessage('', 'error', '[게시글 정보]', '내용을 입력해 주세요.', ''); return false; }

        let hashtagCheckbox = $('input[type=checkbox][name=hashtag]:checked');
        if(hashtagCheckbox.length === 0){ showMessage('', 'error', '[게시글 정보]', '키워드를 1개 이상 선택해 주세요.', ''); return false; }

        let form = JSON.parse(JSON.stringify($('#dataForm').serializeObject()));

        //해시태그
        let hashtagArr = hashtagCheckbox;
        let hashtagArrLen = hashtagArr.length;
        let hashtag = '';
        for(let i=0; i<hashtagArrLen; i++){
            hashtag += hashtagArr.eq(i).val();
            if((i+1) !== hashtagArrLen){
                hashtag += ',';
            }
        }
        form.hashtag = hashtag;

        form.writer = id;
        form.writerKey = id;

        Swal.fire({
            title: '[커뮤니티]',
            html: '커뮤니티 글을 수정하시겠습니까?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#00a8ff',
            confirmButtonText: '수정하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then(async (result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/job/community/write/update.do',
                    method: 'POST',
                    async: false,
                    data: JSON.stringify(form),
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    success: function (data) {
                        if (data.resultCode === "0") {
                            window.location.href = '/job/community_view.do?seq=' + communitySeq; // 목록으로 이동
                        }else {
                            showMessage('', 'error', '에러 발생', '커뮤니티 글 수정을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                        }
                    },
                    error: function (xhr, status) {
                        alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                    }
                })//ajax
            }
        });
    }else{
        Swal.fire({
            title: '[로그인 필요]',
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

function f_main_community_remove(id, communitySeq){
    if(nvl(id,'') !== ''){
        Swal.fire({
            title: '[커뮤니티]',
            html: '커뮤니티 글을 삭제하시겠습니까?',
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#00a8ff',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then(async (result) => {
            if (result.isConfirmed) {
                let jsonObj = {
                    seq: communitySeq
                };
                let resData = ajaxConnect('/job/community/write/delete.do', 'post', jsonObj);
                if (resData.resultCode === "0") {
                    window.location.href = '/job/community_list.do';
                }
            }
        });
    }else{
        Swal.fire({
            title: '[로그인 필요]',
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

function f_main_community_all_remove(id){
    if(nvl(id,'') !== '') {
        let postCheckArr = $('input[type=checkbox][name=postCheck]:checked');
        if (postCheckArr.length > 0) {
            let flag = false;
            for (let i = 0; i < postCheckArr.length; i++) {
                let jsonObj = {
                    seq: postCheckArr.eq(i).val()
                };
                let resData = ajaxConnect('/job/community/write/delete.do', 'post', jsonObj);
                if ((i + 1) === postCheckArr.length) {
                    flag = true;
                }
            }

            if (flag) {
                window.location.href = '/mypage/post.do';
            }
        } else {
            showMessage('', 'error', '[커뮤니티]', '삭제할 게시글을 선택해주세요.', '');
        }
    }else{
        Swal.fire({
            title: '[로그인 필요]',
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