$(document).ready(function () {

    //main slide
    var swiper = new Swiper('.swiper_main_slide', {
        slidesPerView: 1,
        spaceBetween: 20,
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
        autoHeight: true,
        loop: true,
        pagination: {
            el: ".main_pagination",
            clickable: true,
        },
        on: {
            resize: function () {
                swiper.changeDirection(getDirection());
            },

        },

    });

    //main company
    var swiper = new Swiper('.swiper_main_company', {
        slidesPerView: 2,
        spaceBetween: 20,
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
        autoHeight: true,
        loop: true,
        breakpoints: {
            575: { // 575px ~ 991px
                slidesPerView: 3,
            },

            768: { // 768px ~ 
                slidesPerView: 4,
                spaceBetween: 20,
            },

            1024: {  // 1024px ~ 
                slidesPerView: 5,
                spaceBetween: 20,
            },
        },
        on: {
            resize: function () {
                swiper.changeDirection(getDirection());
            },

        },

    });


    // edumarine-news
    var swiper = new Swiper('.swiper_gallery', {
        slidesPerView: 1,
        spaceBetween: 20,
        direction: getDirection(),
        loop: true,
        navigation: {
            nextEl: '.swiper_gallery_next',
            prevEl: '.swiper_gallery_prev',
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

