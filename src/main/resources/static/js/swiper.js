$(document).ready(function () {
    // 메인 슬라이드
    var swiper = new Swiper('.main_swiper_wrap .swiper', {
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
                swiper.changeDirection(getDirection());
            },
        },
    });

    // 갤러리
    var swiper = new Swiper('.gallery_swiper .swiper', {
        slidesPerView: 1,
        spaceBetween: 0,
        direction: getDirection(),
        loop: true,
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
                swiper.changeDirection(getDirection());
            },
        },
    });


    function getDirection() {
        var windowWidth = window.innerWidth;
        var direction = window.innerWidth <= 0 ? 'vertical' : 'horizontal';

        return direction;
    }
});

