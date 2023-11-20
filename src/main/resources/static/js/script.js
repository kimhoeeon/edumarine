

$(document).ready(function () {
    // dept1 메뉴 아이템에 대한 마우스 오버
    $('.nav .dept1 > li').mouseover(function () {
        $(this).find('.dept2').addClass('on');
    }).mouseout(function () {
        $(this).find('.dept2').removeClass('on');
    });

    // 모바일 슬라이드 메뉴
    $('.side_menu, .side_menu_close').on('click', function () {
        $('.hd_site_map').toggleClass('on');
        $('.side_bg').toggleClass('on');
    });


    // 모바일 메뉴
    function handleDept1Click() {
        // Check viewport width
        var viewportWidth = $(window).width();
        if (viewportWidth <= 1024) {
            // Check if any other .dept2 is visible
            var otherDept2 = $('.site_map_nav .dept1 > li').not($(this)).find('.dept2:visible');

            // Slide up the previously exposed .dept2 and expose the corresponding child element
            otherDept2.slideUp();
            $(this).find('.dept2').slideToggle();
        }
    }

    // Handle .dept1 click on page load
    $('.site_map_nav .dept1 > li').on('click', handleDept1Click);

    // Handle .dept1 click on window resize
    $(window).resize(function () {
        $('.site_map_nav .dept1 > li').off('click', handleDept1Click);
        $('.site_map_nav .dept1 > li').on('click', handleDept1Click);
    });

    $(".sub_top_tab").click(function () {
        if ($(window).width() <= 768) {
            var selectedMenu = $(this).find(".tabOptSel");
            if (selectedMenu.is(':visible')) {
                selectedMenu.slideUp();
            } else {
                $(".tabOptSel").slideUp();
                selectedMenu.slideDown();
            }
        }
    });


    $('.sp_cate_icon li, .sp_cate_list li').on('click', function () {
        // Remove "on" class from all other li elements
        $('li').not(this).removeClass('on');

        // Toggle "on" class on the clicked li element
        $(this).toggleClass('on');
    });

    //자주묻는 질문
    $(".board_faq .ask").click(function () {
        var answer = $(this).next("div");
        $(this).toggleClass('on');

        if (answer.is(":visible")) {
            answer.slideUp();
        } else {
            answer.slideDown();
        }
    });

    // 갤러리
    $('.viewGallery').on('click', function () {
        $('.popGallery').toggleClass('on');
        $('body').toggleClass('lock_scroll')
    });

    // 팝업닫기    
    $('.popup .close').on('click', function () {
        $('.popup').removeClass('on');
        $('body').removeClass('lock_scroll')
    });

    // 연락처 입력 시 자동으로 - 삽입과 숫자만 입력
    $('.onlyTel').on("keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
    });



});
