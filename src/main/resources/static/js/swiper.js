$(document).ready(function () {
    // 메인 슬라이드
    var swiper1 = new Swiper('.main_swiper_wrap .swiper', {
        slidesPerView: 1,
        spaceBetween: 0,
        direction: getDirection(),
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
        effect : 'fade',
        loop: true,
        pagination: { 
            el: ".main_swiper_wrap .swiper-pagination",
            clickable: true, 
        },
        navigation: {
            nextEl: '.main_swiper_wrap .swiper-button-next',
            prevEl: '.main_swiper_wrap .swiper-button-prev',
        },
        on: {
            resize: function () {
                swiper1.changeDirection(getDirection());
            },
        },
    });

    // 갤러리
    var swiper2 = new Swiper('.gallery_swiper .swiper', {
        slidesPerView: 1,
        spaceBetween: 0,
        direction: getDirection(),
        watchOverflow: true,
        pagination: {
            el: ".gallery_swiper .swiper-pagination",
            clickable: true, 
        },
        navigation: {
            nextEl: '.gallery_swiper .swiper-button-next',
            prevEl: '.gallery_swiper .swiper-button-prev',
        },
        on: {
            resize: function () {
                swiper2.changeDirection(getDirection());
            },
            observerUpdate: function () {
                swiper2.slideTo(0, 0, false);
            }
        },
        observer: true,
        observeSlideChildren: true
    });

    // 취창업후기
    var swiper3 = new Swiper('.review_swiper .swiper', {
        slidesPerView: 1,
        spaceBetween: 0,
        direction: getDirection(),
        watchOverflow: true,
        pagination: {
            el: ".review_swiper .swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: '.review_swiper .swiper-button-next',
            prevEl: '.review_swiper .swiper-button-prev',
        },
        on: {
            resize: function () {
                swiper3.changeDirection(getDirection());
            },
            observerUpdate: function () {
                swiper3.slideTo(0, 0, false);
            }
        },
        observer: true,
        observeSlideChildren: true
    });


    function getDirection() {
        var windowWidth = window.innerWidth;
        var direction = window.innerWidth <= 0 ? 'vertical' : 'horizontal';

        return direction;
    }
});

