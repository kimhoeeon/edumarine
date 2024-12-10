$(document).ready(function () {

    // header 이벤트
    function setNavEvents() {
        // 이전에 설정된 이벤트를 모두 제거
        $('#header .menu > li').off('mouseover mouseleave click');

        if (window.innerWidth >= 899) {
            // .submenu를 자식으로 가진 .nav .menu > li 요소에만 이벤트
            $('#header .menu > li').has('.submenu').on('mouseover', function () {
                $(this).children(".submenu").addClass('on');
                $('.submenu_bg').addClass('on');
            }).on('mouseleave', function () {
                $(this).children(".submenu").removeClass('on');
                $('.submenu_bg').removeClass('on');
            });

            // PC 화면에서 초기화 작업 (899 이상에서는 .arr 제거 및 .hd_top 복원)
            $('.menu > li').removeClass('arr');
            if ($('.m_top .inner').length) {
                $('.m_top .inner').appendTo('.hd_top .inner');
            }
        } else {
            // 899 이하일 때
            $('#header .menu > li').on('click', function () {
                var $submenu = $(this).children('.submenu');
                $('.submenu').not($submenu).slideUp();
                $submenu.slideToggle();
            });

            // .submenu가 있는 .menu > li에 .arr 클래스 추가
            $('#header .menu > li').has('.submenu').addClass('arr');

            // .hd_top .inner의 내용을 .m_top으로 이동
            if ($('.hd_top .inner').length) {
                $('.hd_top .inner').appendTo('.m_top');
            }
        }
    }

    // 초기 화면 크기와 이벤트 설정
    $(window).on('resize', function () {
        setNavEvents();
    }).trigger('resize');


    $('.hamberg').on('click', function () {
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
        $('.tab_cont').removeClass('on');

        $(this).addClass('on');
        $("#" + tab_id).addClass('on');
    });

    // 푸터 관련기관 사이트
    $('.otherSite .btn').on('click', function () {
        $('.otherSite .option').slideToggle();
    });


    // 메뉴 클릭 이벤트
    $(".sub_nav .menuBox").on('click', function () {
        var selectedMenu = $(this).find(".menuSel");
        if (selectedMenu.is(':visible')) {
            selectedMenu.slideUp();
        } else {
            $(".menuSel").slideUp();
            selectedMenu.slideDown();
        }

        // .menuSel .active 클래스의 텍스트를 .menuAct에 삽입
        var activeText = selectedMenu.find("a.active").text();
        $(this).find(".menuAct").text(activeText);
    });

    // 페이지 로드 시 초기화
    $(".menuBox").each(function () {
        var activeText = $(this).find(".menuSel a.active").text();
        $(this).find(".menuAct").text(activeText);
    });



});