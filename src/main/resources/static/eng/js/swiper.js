$(document).ready(function () {
    // 메인 슬라이드
    var swiper = new Swiper('.swiperMainTop', {
        slidesPerView: 1,
        spaceBetween: 0,
        direction: getDirection(),
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
        effect : 'fade',
        loop: true,
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

