$(document).ready(function () {

    // 숫자만 입력
    $('.onlyNum').on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'));
    });

    // 숫자랑 - 만 입력
    $('.onlyNumh').on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9-]/g, ''));
    });

    // 연락처 입력 시 자동으로 - 삽입과 숫자만 입력
    $('.onlyTel').on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
    });

    // 영문, 숫자만 입력
    $('.onlyNumEng').on("blur keyup", function () {
        let exp = /[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm;
        $(this).val($(this).val().replaceAll(exp, ''));
    });

    // 뷰포트 너비가 769px 이상일 경우
    if (window.innerWidth >= 769) {

        $(".nav .dept1 > li").on('mouseover', function () {
            $(this).children("ul").addClass('on');
        }).on('mouseleave', function () {
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

    $('.selLang .lang').on('click', function () {
        $(this).next('.list').slideToggle();
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
        $(this).parents('.popup').removeClass('on');
        $('body').removeClass('lock_scroll');
    });

    // 팝업 - 갤러리
    $(document).on("click", ".gallery_view", function() {
        let slideTitle = $(this).find('.subject').text();
        let slideImgList = $(this).find('input[type=hidden][name=slideImg]');

        let str = '';
        $.each(slideImgList , function(i) {
            let slideImgPath = slideImgList[i].value.toString().replace('/usr/local/tomcat/webapps', '/../../../..');
            str += '<li class="swiper-slide img_box">';
            str += '<img src="' + slideImgPath + '">';
            str += '</li>';
        });

        $('#popupGallery .popup_inner .popup_tit').text(slideTitle);
        $('#popupGallery .gallery_swiper .swiper_box ul').empty();
        $('#popupGallery .gallery_swiper .swiper_box ul').html(str);

        $('#popupGallery').addClass('on');
        $('body').addClass('lock_scroll');
    });

    // 팝업 - 비디오
    $(document).on("click", ".video_view", function() {
        //console.log($(this).find('img').attr('src')); // https://img.youtube.com/vi/WMaA84_cixo/mqdefault.jpg
        let youtubeTitle = $(this).find('.subject').text();
        let youtubeUrl = $(this).find('img').attr('src');
        let youtubeSeq = '';
        youtubeSeq = youtubeUrl.toString()
            .replace('https://img.youtube.com/vi/','')
            .replace('/mqdefault.jpg',''); // WMaA84_cixo
        let youtubeIframeUrl = 'https://www.youtube.com/embed/' + youtubeSeq;
        $('#popupVideo').find('.popup_tit').text(youtubeTitle);
        $('#popupVideo').find('iframe').attr('src', youtubeIframeUrl);

        $('#popupVideo').addClass('on');
        $('body').addClass('lock_scroll');
    });
    // 팝업 - 닫기 클릭 시 유튜브 영상 정지
    $('#popupVideo .popup_close').on('click', function () {
        //playVideo=재생, pauseVideo=일시정지, stopVideo=정지 
        $("iframe")[0].contentWindow.postMessage('{"event":"command","func":"stopVideo","args":""}', '*');
    });

    // 팝업 - 취창업후기
    $(document).on("click", ".job_review_view", function() {
        let slideTitle = $(this).find('.subject').text();
        let slideImgList = $(this).find('input[type=hidden][name=slideImg]');

        let str = '';
        $.each(slideImgList , function(i) {
            let slideImgPath = slideImgList[i].value.toString().replace('/usr/local/tomcat/webapps', '/../../../..');
            str += '<li class="swiper-slide img_box">';
            str += '<img src="' + slideImgPath + '">';
            str += '</li>';
        });

        $('#popupJobReview .popup_inner .popup_tit').text(slideTitle);
        $('#popupJobReview .review_swiper .swiper_box ul').empty();
        $('#popupJobReview .review_swiper .swiper_box ul').html(str);

        $('#popupJobReview').addClass('on');
        $('body').addClass('lock_scroll');
    });

     // 팝업 - 스케줄표
    $('.sked_wrap .sked_btn a').on('click', function () {
        $('#popupCalendar').addClass('on');
        $('body').addClass('lock_scroll');
    });
    $('#popupCalendar .close_btn').on('click', function () {
        $(this).parents('.popup').removeClass('on')
        $('body').removeClass('lock_scroll');
    });

    /// faq 추가 20240201
    $(document).on('click', '.board_faq .ask', function() {
        let answer = $(this).next('div');
        $(this).toggleClass('on');

        if (answer.is(':visible')) {
            answer.slideUp();
        } else {
            answer.slideDown();
        }
    });

    $('.checkbox_etc').on('change', function(){
       let checkYn = $(this).is(':checked');
       let inputTxtBox = $(this).siblings('input[type=text]');
       if(checkYn){
           inputTxtBox.prop('disabled',false);
       }else{
           inputTxtBox.prop('disabled',true);
           inputTxtBox.val('');
       }
    });

    /*$('.reply_wrap .recommend_btn').on('click', function () {
        $(this).toggleClass('on');
    });*/

});