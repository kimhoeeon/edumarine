$(document).ready(function () {

    // 뷰포트 너비가 769px 이상일 경우
    if (window.innerWidth >= 769) {

        $(".nav .dept1 > li").on('mouseover', function () {
            $(this).children("ul").addClass('on');
        });

        $(".nav .dept1 > li").on('mouseleave', function () {
            $(this).children("ul").removeClass('on');
        });

    } else {
        $(".nav .dept1 > li").on('click', function () {
            $(".nav .dept1 > li").not(this).removeClass('on').children("ul").slideUp();
            $(this).toggleClass('on').children("ul").slideToggle();
        });
    }

    $('.m_menu').on('click', function () {
        $(this).toggleClass('on');
        $('.aside_bg, #header .nav').toggleClass('on');
        $('body').toggleClass('lock_scroll')
    });


    // tab
    $('.tab_menu li').on('click', function () {
        var tab_id = $(this).attr('data-tab');

        $('.tab_menu li').removeClass('on');
        $('.tab_content').removeClass('on');

        $(this).addClass('on');
        $("#" + tab_id).addClass('on');
    });

    // 푸터 관련기관 사이트
    $('.footer_other_site .btn').on('click', function () {
        $('.footer_other_site .option_box').slideToggle();
    });


    // 서브페이지 사이드바 모바일
    $('#sidebar .lnb li.on').on('click', function () {
        $('#sidebar .lnb li').not(this).slideToggle();
    });


    // 팝업 - 댣기
    $('.popup_close').on('click', function () {
        $(this).parents('.popup').removeClass('on')
        $('body').removeClass('lock_scroll');
    });
    // 팝업 - 갤러리
    $('.gallery_view').on('click', function () {
        $('#popupGallery').addClass('on');
        $('body').addClass('lock_scroll');
    });
    // 팝업 - 비디오
    $('.video_view').on('click', function () {
        $('#popupVideo').addClass('on');
        $('body').addClass('lock_scroll');
    });
    // 팝업 - 닫기 클릭 시 유튜브 영상 정지
    $('#popupVideo .popup_close').on('click', function () {
        //playVideo=재생, pauseVideo=일시정지, stopVideo=정지 
        $("iframe")[0].contentWindow.postMessage('{"event":"command","func":"stopVideo","args":""}', '*');
    });

    // 팝업 - 취창업후기
    $('.job_review_view').on('click', function () {
        $('#popupJobReview').addClass('on');
        $('body').addClass('lock_scroll');
    });

     // 팝업 - 스케줄표
    $('.sked_wrap .sked_btn').on('click', function () {
        $('#popupCalendar').addClass('on');
        $('body').addClass('lock_scroll');
    });
    $('#popupCalendar .close_btn').on('click', function () {
        $(this).parents('.popup').removeClass('on')
        $('body').removeClass('lock_scroll');
    });


    $('.reply_wrap .recommend_btn').on('click', function () {
        $(this).toggleClass('on');
    });

});


