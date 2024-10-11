/***
 * mng/education/template
 * 교육>교육관리>교육안내템플릿관리
 * */

$(function () {

    //전체교육일정
    
    //마리나 선박 정비사 실무교육
    //교육내용
    $('#edu_marina_outboarder_contents,' +
        '#edu_marina_inboarder_contents,' +
        '#edu_marina_frp_contents').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //교육기간
    $('#edu_marina_outboarder_period,' +
        '#edu_marina_inboarder_period,' +
        '#edu_marina_frp_period').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //모집기간
    //수료조건
    $('#edu_marina_recruit_period,' +
        '#edu_marina_complete_condition').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //위탁교육
    //교육대상
    //교육내용
    //교육기간
    //교육장소
    $('#edu_commission_target,' +
        '#edu_commission_contents,' +
        '#edu_commission_period,' +
        '#edu_commission_place').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //해상엔진 자가정비
    //선외기
    //교육대상
    //교육내용
    //교육기간
    $('#edu_boarder_outboarder_target,' +
        '#edu_boarder_outboarder_contents,' +
        '#edu_boarder_outboarder_period').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //선내기
    //교육대상
    //교육내용
    //교육기간
    $('#edu_boarder_inboarder_target,' +
        '#edu_boarder_inboarder_contents,' +
        '#edu_boarder_inboarder_period').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //세일요트
    //교육대상
    //교육내용
    //교육기간
    $('#edu_boarder_sailyacht_target,' +
        '#edu_boarder_sailyacht_contents,' +
        '#edu_boarder_sailyacht_period').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //고마력
    //교육대상
    //교육내용
    //교육기간
    //교육장비
    $('#edu_highhorsepower_target,' +
        '#edu_highhorsepower_contents,' +
        '#edu_highhorsepower_period,' +
        '#edu_highhorsepower_stuff').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //고마력 자가정비
    //교육대상
    //교육내용
    //교육기간
    //교육장비
    $('#edu_highself_target,' +
        '#edu_highself_contents,' +
        '#edu_highself_period,' +
        '#edu_highself_stuff').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //Sterndrive
    //교육대상
    //교육내용
    //교육기간
    //교육장비
    $('#edu_sterndrive_target,' +
        '#edu_sterndrive_contents,' +
        '#edu_sterndrive_period,' +
        '#edu_sterndrive_stuff').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

    //기초정비교육
    //교육대상
    //교육내용
    //교육기간
    //교육장비
    $('#edu_basic_target,' +
        '#edu_basic_contents,' +
        '#edu_basic_period,' +
        '#edu_basic_place,' +
        '#edu_basic_stuff').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    })

    //응급조치교육
    //교육대상
    //교육내용
    //교육기간
    //교육장비
    $('#edu_emergency_target,' +
        '#edu_emergency_contents,' +
        '#edu_emergency_period,' +
        '#edu_emergency_place,' +
        '#edu_emergency_stuff').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });
    
    //발전기 정비 교육
    //교육대상
    //교육내용
    //교육기간
    //교육장비
    $('#edu_generator_target,' +
        '#edu_generator_contents,' +
        '#edu_generator_period,' +
        '#edu_generator_stuff').repeater({
        initEmpty: false,
        defaultValues: {
            'text-input': ''
        },
        isFirstItemUndeletable: true,

        show: function () {
            $(this).slideDown();
        },

        hide: function (deleteElement) {
            $(this).slideUp(deleteElement);
        }
    });

});

function f_train_template_marina_save(){
    console.log('f_train_template_marina_save');

    let main_json_arr = [];
    // 교육내용
    // 선외기 정비사 실무교육
    let outboarder_contents_arr = $('#edu_marina_outboarder_contents').find('input[type=text]');
    for(let i=0; i<outboarder_contents_arr.length; i++){
        let outboarder_contents = outboarder_contents_arr.eq(i).val();
        let outboarder_contents_json_obj = {
            major: 'marina',
            middle: 'outboarder',
            small: 'contents',
            value: outboarder_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(outboarder_contents_json_obj);
    }
    // 선내기 정비사 실무교육
    let inboarder_contents_arr = $('#edu_marina_inboarder_contents').find('input[type=text]');
    for(let i=0; i<inboarder_contents_arr.length; i++){
        let inboarder_contents = inboarder_contents_arr.eq(i).val();
        let inboarder_contents_json_obj = {
            major: 'marina',
            middle: 'inboarder',
            small: 'contents',
            value: inboarder_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(inboarder_contents_json_obj);
    }
    // FRP 선체 정비사 실무교육
    let frp_contents_arr = $('#edu_marina_frp_contents').find('input[type=text]');
    for(let i=0; i<frp_contents_arr.length; i++){
        let frp_contents = frp_contents_arr.eq(i).val();
        let frp_contents_json_obj = {
            major: 'marina',
            middle: 'frp',
            small: 'contents',
            value: frp_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(frp_contents_json_obj);
    }

    // 교육기간
    // 선외기 정비사 실무교육
    let outboarder_period_arr = $('#edu_marina_outboarder_period').find('input[type=text]');
    for(let i=0; i<outboarder_period_arr.length; i++){
        let outboarder_period = outboarder_period_arr.eq(i).val();
        let outboarder_contents_json_obj = {
            major: 'marina',
            middle: 'outboarder',
            small: 'period',
            value: outboarder_period,
            note: (i+1).toString()
        };
        main_json_arr.push(outboarder_contents_json_obj);
    }
    // 선내기 정비사 실무교육
    let inboarder_period_arr = $('#edu_marina_inboarder_period').find('input[type=text]');
    for(let i=0; i<inboarder_period_arr.length; i++){
        let inboarder_period = inboarder_period_arr.eq(i).val();
        let inboarder_contents_json_obj = {
            major: 'marina',
            middle: 'inboarder',
            small: 'period',
            value: inboarder_period,
            note: (i+1).toString()
        };
        main_json_arr.push(inboarder_contents_json_obj);
    }
    // FRP 선체 정비사 실무교육
    let frp_period_arr = $('#edu_marina_frp_period').find('input[type=text]');
    for(let i=0; i<frp_period_arr.length; i++){
        let frp_period = frp_period_arr.eq(i).val();
        let frp_contents_json_obj = {
            major: 'marina',
            middle: 'frp',
            small: 'period',
            value: frp_period,
            note: (i+1).toString()
        };
        main_json_arr.push(frp_contents_json_obj);
    }

    // 교육일수
    // 선외기 정비사 실무교육
    let outboarder_days = $('#edu_marina_outboarder_days').find('textarea').val();
    let outboarder_days_json_obj = {
        major: 'marina',
        middle: 'outboarder',
        small: 'days',
        value: outboarder_days.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(outboarder_days_json_obj);
    // 선내기 정비사 실무교육
    let inboarder_days = $('#edu_marina_inboarder_days').find('textarea').val();
    let inboarder_days_json_obj = {
        major: 'marina',
        middle: 'inboarder',
        small: 'days',
        value: inboarder_days.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(inboarder_days_json_obj);
    // FRP 선체 정비사 실무교육
    let frp_days = $('#edu_marina_frp_days').find('textarea').val();
    let frp_days_json_obj = {
        major: 'marina',
        middle: 'frp',
        small: 'days',
        value: frp_days.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(frp_days_json_obj);

    // 교육시간
    // 선외기 정비사 실무교육
    let outboarder_time = $('#edu_marina_outboarder_time').find('textarea').val();
    let outboarder_time_json_obj = {
        major: 'marina',
        middle: 'outboarder',
        small: 'time',
        value: outboarder_time.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(outboarder_time_json_obj);
    // 선내기 정비사 실무교육
    let inboarder_time = $('#edu_marina_inboarder_time').find('textarea').val();
    let inboarder_time_json_obj = {
        major: 'marina',
        middle: 'inboarder',
        small: 'time',
        value: inboarder_time.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(inboarder_time_json_obj);
    // FRP 선체 정비사 실무교육
    let frp_time = $('#edu_marina_frp_time').find('textarea').val();
    let frp_time_json_obj = {
        major: 'marina',
        middle: 'frp',
        small: 'time',
        value: frp_time.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(frp_time_json_obj);

    // 교육장소
    // 선외기 정비사 실무교육
    let outboarder_place = $('#edu_marina_outboarder_place').find('.item').find('input[type=text]').val();
    let outboarder_place_json_obj = {
        major: 'marina',
        middle: 'outboarder',
        small: 'place',
        value: outboarder_place
    };
    main_json_arr.push(outboarder_place_json_obj);
    let outboarder_placeDetail = $('#edu_marina_outboarder_place').find('.address').find('input[type=text]').val();
    let outboarder_placeDetail_json_obj = {
        major: 'marina',
        middle: 'outboarder',
        small: 'placeDetail',
        value: outboarder_placeDetail
    };
    main_json_arr.push(outboarder_placeDetail_json_obj);
    // 선내기 정비사 실무교육
    let inboarder_place = $('#edu_marina_inboarder_place').find('.item').find('input[type=text]').val();
    let inboarder_place_json_obj = {
        major: 'marina',
        middle: 'inboarder',
        small: 'place',
        value: inboarder_place
    };
    main_json_arr.push(inboarder_place_json_obj);
    let inboarder_placeDetail = $('#edu_marina_inboarder_place').find('.address').find('input[type=text]').val();
    let inboarder_placeDetail_json_obj = {
        major: 'marina',
        middle: 'inboarder',
        small: 'placeDetail',
        value: inboarder_placeDetail
    };
    main_json_arr.push(inboarder_placeDetail_json_obj);
    // FRP 선체 정비사 실무교육
    let frp_place = $('#edu_marina_frp_place').find('.item').find('input[type=text]').val();
    let frp_place_json_obj = {
        major: 'marina',
        middle: 'frp',
        small: 'place',
        value: frp_place
    };
    main_json_arr.push(frp_place_json_obj);
    let frp_placeDetail = $('#edu_marina_frp_place').find('.address').find('input[type=text]').val();
    let frp_placeDetail_json_obj = {
        major: 'marina',
        middle: 'frp',
        small: 'placeDetail',
        value: frp_placeDetail
    };
    main_json_arr.push(frp_placeDetail_json_obj);

    // 교육인원
    // 선외기 정비사 실무교육
    let outboarder_persons = $('#edu_marina_outboarder_persons').find('input[type=text]').val();
    let outboarder_persons_json_obj = {
        major: 'marina',
        middle: 'outboarder',
        small: 'persons',
        value: outboarder_persons
    };
    main_json_arr.push(outboarder_persons_json_obj);
    // 선내기 정비사 실무교육
    let inboarder_persons = $('#edu_marina_inboarder_persons').find('input[type=text]').val();
    let inboarder_persons_json_obj = {
        major: 'marina',
        middle: 'inboarder',
        small: 'persons',
        value: inboarder_persons
    };
    main_json_arr.push(inboarder_persons_json_obj);
    // FRP 선체 정비사 실무교육
    let frp_persons = $('#edu_marina_frp_persons').find('input[type=text]').val();
    let frp_persons_json_obj = {
        major: 'marina',
        middle: 'frp',
        small: 'persons',
        value: frp_persons
    };
    main_json_arr.push(frp_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let outboarder_pay = $('#edu_marina_outboarder_pay').find('input[type=text]').val();
    let outboarder_pay_json_obj = {
        major: 'marina',
        middle: 'outboarder',
        small: 'pay',
        value: outboarder_pay
    };
    main_json_arr.push(outboarder_pay_json_obj);
    // 선내기 정비사 실무교육
    let inboarder_pay = $('#edu_marina_inboarder_pay').find('input[type=text]').val();
    let inboarder_pay_json_obj = {
        major: 'marina',
        middle: 'inboarder',
        small: 'pay',
        value: inboarder_pay
    };
    main_json_arr.push(inboarder_pay_json_obj);
    // FRP 선체 정비사 실무교육
    let frp_pay = $('#edu_marina_frp_pay').find('input[type=text]').val();
    let frp_pay_json_obj = {
        major: 'marina',
        middle: 'frp',
        small: 'pay',
        value: frp_pay
    };
    main_json_arr.push(frp_pay_json_obj);

    // 지원자격
    let right = $('#edu_marina_right').find('.item').find('textarea').val();
    let right_json_obj = {
        major: 'marina',
        middle: 'info',
        small: 'right',
        value: right.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(right_json_obj);

    // 신청방법
    let applyMethod = $('#edu_marina_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'marina',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_marina_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'marina',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_marina_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'marina',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruit_period_arr = $('#edu_marina_recruit_period').find('input[type=text]');
    for(let i=0; i<recruit_period_arr.length; i++){
        let recruitPeriod = recruit_period_arr.eq(i).val();
        let recruitPeriod_json_obj = {
            major: 'marina',
            middle: 'info',
            small: 'recruitPeriod',
            value: recruitPeriod,
            note: (i+1).toString()
        };
        main_json_arr.push(recruitPeriod_json_obj);
    }

    // 수료조건
    let complete_condition_arr = $('#edu_marina_complete_condition').find('input[type=text]');
    for(let i=0; i<complete_condition_arr.length; i++){
        let completeCondition = complete_condition_arr.eq(i).val();
        let completeCondition_json_obj = {
            major: 'marina',
            middle: 'info',
            small: 'completeCondition',
            value: completeCondition,
            note: (i+1).toString()
        };
        main_json_arr.push(completeCondition_json_obj);
    }

    let main_json_obj = {
        gbn: 'marina',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('marina');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_commission_save(){
    console.log('f_train_template_commission_save');

    let main_json_arr = [];
    // 교육대상
    let commission_target_arr = $('#edu_commission_target').find('input[type=text]');
    for(let i=0; i<commission_target_arr.length; i++){
        let commission_target = commission_target_arr.eq(i).val();
        let commission_target_json_obj = {
            major: 'commission',
            middle: 'info',
            small: 'target',
            value: commission_target,
            note: (i+1).toString()
        };
        main_json_arr.push(commission_target_json_obj);
    }

    // 교육내용
    let commission_contents_arr = $('#edu_commission_contents').find('input[type=text]');
    for(let i=0; i<commission_contents_arr.length; i++){
        let commission_contents = commission_contents_arr.eq(i).val();
        let commission_contents_json_obj = {
            major: 'commission',
            middle: 'info',
            small: 'contents',
            value: commission_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(commission_contents_json_obj);
    }

    // 교육기간
    let commission_period_arr = $('#edu_commission_period').find('input[type=text]');
    for(let i=0; i<commission_period_arr.length; i++){
        let commission_period = commission_period_arr.eq(i).val();
        let commission_period_json_obj = {
            major: 'commission',
            middle: 'info',
            small: 'period',
            value: commission_period,
            note: (i+1).toString()
        };
        main_json_arr.push(commission_period_json_obj);
    }

    // 교육장소
    let commission_place_arr = $('#edu_commission_place').find('input[type=text]');
    for(let i=0; i<commission_place_arr.length; i++){
        let commission_place = commission_place_arr.eq(i).val();
        let commission_place_json_obj = {
            major: 'commission',
            middle: 'info',
            small: 'place',
            value: commission_place,
            note: (i+1).toString()
        };
        main_json_arr.push(commission_place_json_obj);
    }

    let main_json_obj = {
        gbn: 'commission',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('commission');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_outboarder_save(){
    console.log('f_train_template_outboarder_save');

    let main_json_arr = [];
    // 교육대상
    let outboarder_target_arr = $('#edu_boarder_outboarder_target').find('input[type=text]');
    for(let i=0; i<outboarder_target_arr.length; i++){
        let outboarder_target = outboarder_target_arr.eq(i).val();
        let outboarder_target_json_obj = {
            major: 'outboarder',
            middle: 'info',
            small: 'target',
            value: outboarder_target,
            note: (i+1).toString()
        };
        main_json_arr.push(outboarder_target_json_obj);
    }

    // 교육내용
    let outboarder_contents_arr = $('#edu_boarder_outboarder_contents').find('input[type=text]');
    for(let i=0; i<outboarder_contents_arr.length; i++){
        let outboarder_contents = outboarder_contents_arr.eq(i).val();
        let outboarder_contents_json_obj = {
            major: 'outboarder',
            middle: 'info',
            small: 'contents',
            value: outboarder_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(outboarder_contents_json_obj);
    }

    // 교육기간
    let outboarder_period_arr = $('#edu_boarder_outboarder_period').find('input[type=text]');
    for(let i=0; i<outboarder_period_arr.length; i++){
        let outboarder_period = outboarder_period_arr.eq(i).val();
        let outboarder_period_json_obj = {
            major: 'outboarder',
            middle: 'info',
            small: 'period',
            value: outboarder_period,
            note: (i+1).toString()
        };
        main_json_arr.push(outboarder_period_json_obj);
    }

    // 교육일수
    let outboarder_days = $('#edu_boarder_outboarder_days').find('input[type=text]').val();
    let outboarder_days_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'days',
        value: outboarder_days
    };
    main_json_arr.push(outboarder_days_json_obj);

    // 교육시간
    let outboarder_time = $('#edu_boarder_outboarder_time').find('input[type=text]').val();
    let outboarder_time_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'time',
        value: outboarder_time
    };
    main_json_arr.push(outboarder_time_json_obj);

    // 교육장소
    let outboarder_place = $('#edu_boarder_outboarder_place').find('.item').find('input[type=text]').val();
    let outboarder_place_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'place',
        value: outboarder_place
    };
    main_json_arr.push(outboarder_place_json_obj);
    let outboarder_placeDetail = $('#edu_boarder_outboarder_place').find('.address').find('input[type=text]').val();
    let outboarder_placeDetail_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'placeDetail',
        value: outboarder_placeDetail
    };
    main_json_arr.push(outboarder_placeDetail_json_obj);

    // 교육인원
    let outboarder_persons = $('#edu_boarder_outboarder_persons').find('input[type=text]').val();
    let outboarder_persons_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'persons',
        value: outboarder_persons
    };
    main_json_arr.push(outboarder_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let outboarder_pay = $('#edu_boarder_outboarder_pay').find('input[type=text]').val();
    let outboarder_pay_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'pay',
        value: outboarder_pay
    };
    main_json_arr.push(outboarder_pay_json_obj);

    // 신청방법
    let applyMethod = $('#edu_boarder_outboarder_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_boarder_outboarder_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_boarder_outboarder_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_boarder_outboarder_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'outboarder',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'outboarder',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('outboarder');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_inboarder_save(){
    console.log('f_train_template_inboarder_save');

    let main_json_arr = [];
    // 교육대상
    let inboarder_target_arr = $('#edu_boarder_inboarder_target').find('input[type=text]');
    for(let i=0; i<inboarder_target_arr.length; i++){
        let inboarder_target = inboarder_target_arr.eq(i).val();
        let inboarder_target_json_obj = {
            major: 'inboarder',
            middle: 'info',
            small: 'target',
            value: inboarder_target,
            note: (i+1).toString()
        };
        main_json_arr.push(inboarder_target_json_obj);
    }

    // 교육내용
    let inboarder_contents_arr = $('#edu_boarder_inboarder_contents').find('input[type=text]');
    for(let i=0; i<inboarder_contents_arr.length; i++){
        let inboarder_contents = inboarder_contents_arr.eq(i).val();
        let inboarder_contents_json_obj = {
            major: 'inboarder',
            middle: 'info',
            small: 'contents',
            value: inboarder_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(inboarder_contents_json_obj);
    }

    // 교육기간
    let inboarder_period_arr = $('#edu_boarder_inboarder_period').find('input[type=text]');
    for(let i=0; i<inboarder_period_arr.length; i++){
        let inboarder_period = inboarder_period_arr.eq(i).val();
        let inboarder_period_json_obj = {
            major: 'inboarder',
            middle: 'info',
            small: 'period',
            value: inboarder_period,
            note: (i+1).toString()
        };
        main_json_arr.push(inboarder_period_json_obj);
    }

    // 교육일수
    let inboarder_days = $('#edu_boarder_inboarder_days').find('input[type=text]').val();
    let inboarder_days_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'days',
        value: inboarder_days
    };
    main_json_arr.push(inboarder_days_json_obj);

    // 교육시간
    let inboarder_time = $('#edu_boarder_inboarder_time').find('input[type=text]').val();
    let inboarder_time_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'time',
        value: inboarder_time
    };
    main_json_arr.push(inboarder_time_json_obj);

    // 교육장소
    let inboarder_place = $('#edu_boarder_inboarder_place').find('.item').find('input[type=text]').val();
    let inboarder_place_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'place',
        value: inboarder_place
    };
    main_json_arr.push(inboarder_place_json_obj);
    let inboarder_placeDetail = $('#edu_boarder_inboarder_place').find('.address').find('input[type=text]').val();
    let inboarder_placeDetail_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'placeDetail',
        value: inboarder_placeDetail
    };
    main_json_arr.push(inboarder_placeDetail_json_obj);

    // 교육인원
    let inboarder_persons = $('#edu_boarder_inboarder_persons').find('input[type=text]').val();
    let inboarder_persons_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'persons',
        value: inboarder_persons
    };
    main_json_arr.push(inboarder_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let inboarder_pay = $('#edu_boarder_inboarder_pay').find('input[type=text]').val();
    let inboarder_pay_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'pay',
        value: inboarder_pay
    };
    main_json_arr.push(inboarder_pay_json_obj);

    // 신청방법
    let applyMethod = $('#edu_boarder_inboarder_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_boarder_inboarder_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_boarder_inboarder_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_boarder_inboarder_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'inboarder',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'inboarder',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('inboarder');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_sailyacht_save(){
    console.log('f_train_template_sailyacht_save');

    let main_json_arr = [];
    // 교육대상
    let sailyacht_target_arr = $('#edu_boarder_sailyacht_target').find('input[type=text]');
    for(let i=0; i<sailyacht_target_arr.length; i++){
        let sailyacht_target = sailyacht_target_arr.eq(i).val();
        let sailyacht_target_json_obj = {
            major: 'sailyacht',
            middle: 'info',
            small: 'target',
            value: sailyacht_target,
            note: (i+1).toString()
        };
        main_json_arr.push(sailyacht_target_json_obj);
    }

    // 교육내용
    let sailyacht_contents_arr = $('#edu_boarder_sailyacht_contents').find('input[type=text]');
    for(let i=0; i<sailyacht_contents_arr.length; i++){
        let sailyacht_contents = sailyacht_contents_arr.eq(i).val();
        let sailyacht_contents_json_obj = {
            major: 'sailyacht',
            middle: 'info',
            small: 'contents',
            value: sailyacht_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(sailyacht_contents_json_obj);
    }

    // 교육기간
    let sailyacht_period_arr = $('#edu_boarder_sailyacht_period').find('input[type=text]');
    for(let i=0; i<sailyacht_period_arr.length; i++){
        let sailyacht_period = sailyacht_period_arr.eq(i).val();
        let sailyacht_period_json_obj = {
            major: 'sailyacht',
            middle: 'info',
            small: 'period',
            value: sailyacht_period,
            note: (i+1).toString()
        };
        main_json_arr.push(sailyacht_period_json_obj);
    }

    // 교육일수
    let sailyacht_days = $('#edu_boarder_sailyacht_days').find('input[type=text]').val();
    let sailyacht_days_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'days',
        value: sailyacht_days
    };
    main_json_arr.push(sailyacht_days_json_obj);

    // 교육시간
    let sailyacht_time = $('#edu_boarder_sailyacht_time').find('input[type=text]').val();
    let sailyacht_time_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'time',
        value: sailyacht_time
    };
    main_json_arr.push(sailyacht_time_json_obj);

    // 교육장소
    let sailyacht_place = $('#edu_boarder_sailyacht_place').find('.item').find('input[type=text]').val();
    let sailyacht_place_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'place',
        value: sailyacht_place
    };
    main_json_arr.push(sailyacht_place_json_obj);
    let sailyacht_placeDetail = $('#edu_boarder_sailyacht_place').find('.address').find('input[type=text]').val();
    let sailyacht_placeDetail_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'placeDetail',
        value: sailyacht_placeDetail
    };
    main_json_arr.push(sailyacht_placeDetail_json_obj);

    // 교육인원
    let sailyacht_persons = $('#edu_boarder_sailyacht_persons').find('input[type=text]').val();
    let sailyacht_persons_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'persons',
        value: sailyacht_persons
    };
    main_json_arr.push(sailyacht_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let sailyacht_pay = $('#edu_boarder_sailyacht_pay').find('input[type=text]').val();
    let sailyacht_pay_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'pay',
        value: sailyacht_pay
    };
    main_json_arr.push(sailyacht_pay_json_obj);

    // 신청방법
    let applyMethod = $('#edu_boarder_sailyacht_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_boarder_sailyacht_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_boarder_sailyacht_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_boarder_sailyacht_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'sailyacht',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'sailyacht',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('sailyacht');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_highhorsepower_save(){
    console.log('f_train_template_highhorsepower_save');

    let main_json_arr = [];
    // 교육대상
    let highhorsepower_target_arr = $('#edu_highhorsepower_target').find('input[type=text]');
    for(let i=0; i<highhorsepower_target_arr.length; i++){
        let highhorsepower_target = highhorsepower_target_arr.eq(i).val();
        let highhorsepower_target_json_obj = {
            major: 'highhorsepower',
            middle: 'info',
            small: 'target',
            value: highhorsepower_target,
            note: (i+1).toString()
        };
        main_json_arr.push(highhorsepower_target_json_obj);
    }

    // 교육내용
    let highhorsepower_contents_arr = $('#edu_highhorsepower_contents').find('input[type=text]');
    for(let i=0; i<highhorsepower_contents_arr.length; i++){
        let highhorsepower_contents = highhorsepower_contents_arr.eq(i).val();
        let highhorsepower_contents_json_obj = {
            major: 'highhorsepower',
            middle: 'info',
            small: 'contents',
            value: highhorsepower_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(highhorsepower_contents_json_obj);
    }

    // 교육기간
    let highhorsepower_period_arr = $('#edu_highhorsepower_period').find('input[type=text]');
    for(let i=0; i<highhorsepower_period_arr.length; i++){
        let highhorsepower_period = highhorsepower_period_arr.eq(i).val();
        let highhorsepower_period_json_obj = {
            major: 'highhorsepower',
            middle: 'info',
            small: 'period',
            value: highhorsepower_period,
            note: (i+1).toString()
        };
        main_json_arr.push(highhorsepower_period_json_obj);
    }

    // 교육일수
    let highhorsepower_days = $('#edu_highhorsepower_days').find('input[type=text]').val();
    let highhorsepower_days_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'days',
        value: highhorsepower_days
    };
    main_json_arr.push(highhorsepower_days_json_obj);

    // 교육시간
    let highhorsepower_time = $('#edu_highhorsepower_time').find('input[type=text]').val();
    let highhorsepower_time_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'time',
        value: highhorsepower_time
    };
    main_json_arr.push(highhorsepower_time_json_obj);

    // 교육장소
    let highhorsepower_place = $('#edu_highhorsepower_place').find('.item').find('input[type=text]').val();
    let highhorsepower_place_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'place',
        value: highhorsepower_place
    };
    main_json_arr.push(highhorsepower_place_json_obj);
    let highhorsepower_placeDetail = $('#edu_highhorsepower_place').find('.address').find('input[type=text]').val();
    let highhorsepower_placeDetail_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'placeDetail',
        value: highhorsepower_placeDetail
    };
    main_json_arr.push(highhorsepower_placeDetail_json_obj);

    // 교육인원
    let highhorsepower_persons = $('#edu_highhorsepower_persons').find('input[type=text]').val();
    let highhorsepower_persons_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'persons',
        value: highhorsepower_persons
    };
    main_json_arr.push(highhorsepower_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let highhorsepower_pay = $('#edu_highhorsepower_pay').find('input[type=text]').val();
    let highhorsepower_pay_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'pay',
        value: highhorsepower_pay
    };
    main_json_arr.push(highhorsepower_pay_json_obj);

    // 교육장비
    let highhorsepower_stuff_arr = $('#edu_highhorsepower_stuff').find('input[type=text]');
    for(let i=0; i<highhorsepower_stuff_arr.length; i++){
        let highhorsepower_stuff = highhorsepower_stuff_arr.eq(i).val();
        let highhorsepower_stuff_json_obj = {
            major: 'highhorsepower',
            middle: 'info',
            small: 'stuff',
            value: highhorsepower_stuff,
            note: (i+1).toString()
        };
        main_json_arr.push(highhorsepower_stuff_json_obj);
    }

    // 신청방법
    let applyMethod = $('#edu_highhorsepower_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_highhorsepower_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_highhorsepower_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_highhorsepower_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'highhorsepower',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'highhorsepower',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('highhorsepower');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_highself_save(){
    console.log('f_train_template_highself_save');

    let main_json_arr = [];
    // 교육대상
    let highself_target_arr = $('#edu_highself_target').find('input[type=text]');
    for(let i=0; i<highself_target_arr.length; i++){
        let highself_target = highself_target_arr.eq(i).val();
        let highself_target_json_obj = {
            major: 'highself',
            middle: 'info',
            small: 'target',
            value: highself_target,
            note: (i+1).toString()
        };
        main_json_arr.push(highself_target_json_obj);
    }

    // 교육내용
    let highself_contents_arr = $('#edu_highself_contents').find('input[type=text]');
    for(let i=0; i<highself_contents_arr.length; i++){
        let highself_contents = highself_contents_arr.eq(i).val();
        let highself_contents_json_obj = {
            major: 'highself',
            middle: 'info',
            small: 'contents',
            value: highself_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(highself_contents_json_obj);
    }

    // 교육기간
    let highself_period_arr = $('#edu_highself_period').find('input[type=text]');
    for(let i=0; i<highself_period_arr.length; i++){
        let highself_period = highself_period_arr.eq(i).val();
        let highself_period_json_obj = {
            major: 'highself',
            middle: 'info',
            small: 'period',
            value: highself_period,
            note: (i+1).toString()
        };
        main_json_arr.push(highself_period_json_obj);
    }

    // 교육일수
    let highself_days = $('#edu_highself_days').find('input[type=text]').val();
    let highself_days_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'days',
        value: highself_days
    };
    main_json_arr.push(highself_days_json_obj);

    // 교육시간
    let highself_time = $('#edu_highself_time').find('input[type=text]').val();
    let highself_time_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'time',
        value: highself_time
    };
    main_json_arr.push(highself_time_json_obj);

    // 교육장소
    let highself_place = $('#edu_highself_place').find('.item').find('input[type=text]').val();
    let highself_place_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'place',
        value: highself_place
    };
    main_json_arr.push(highself_place_json_obj);
    let highself_placeDetail = $('#edu_highself_place').find('.address').find('input[type=text]').val();
    let highself_placeDetail_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'placeDetail',
        value: highself_placeDetail
    };
    main_json_arr.push(highself_placeDetail_json_obj);

    // 교육인원
    let highself_persons = $('#edu_highself_persons').find('input[type=text]').val();
    let highself_persons_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'persons',
        value: highself_persons
    };
    main_json_arr.push(highself_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let highself_pay = $('#edu_highself_pay').find('input[type=text]').val();
    let highself_pay_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'pay',
        value: highself_pay
    };
    main_json_arr.push(highself_pay_json_obj);

    // 교육장비
    let highself_stuff_arr = $('#edu_highself_stuff').find('input[type=text]');
    for(let i=0; i<highself_stuff_arr.length; i++){
        let highself_stuff = highself_stuff_arr.eq(i).val();
        let highself_stuff_json_obj = {
            major: 'highself',
            middle: 'info',
            small: 'stuff',
            value: highself_stuff,
            note: (i+1).toString()
        };
        main_json_arr.push(highself_stuff_json_obj);
    }

    // 신청방법
    let applyMethod = $('#edu_highself_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_highself_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_highself_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_highself_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'highself',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'highself',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('highself');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_sterndrive_save(){
    console.log('f_train_template_sterndrive_save');

    let main_json_arr = [];
    // 교육대상
    let sterndrive_target_arr = $('#edu_sterndrive_target').find('input[type=text]');
    for(let i=0; i<sterndrive_target_arr.length; i++){
        let sterndrive_target = sterndrive_target_arr.eq(i).val();
        let sterndrive_target_json_obj = {
            major: 'sterndrive',
            middle: 'info',
            small: 'target',
            value: sterndrive_target,
            note: (i+1).toString()
        };
        main_json_arr.push(sterndrive_target_json_obj);
    }

    // 교육내용
    let sterndrive_contents_arr = $('#edu_sterndrive_contents').find('input[type=text]');
    for(let i=0; i<sterndrive_contents_arr.length; i++){
        let sterndrive_contents = sterndrive_contents_arr.eq(i).val();
        let sterndrive_contents_json_obj = {
            major: 'sterndrive',
            middle: 'info',
            small: 'contents',
            value: sterndrive_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(sterndrive_contents_json_obj);
    }

    // 교육기간
    let sterndrive_period_arr = $('#edu_sterndrive_period').find('input[type=text]');
    for(let i=0; i<sterndrive_period_arr.length; i++){
        let sterndrive_period = sterndrive_period_arr.eq(i).val();
        let sterndrive_period_json_obj = {
            major: 'sterndrive',
            middle: 'info',
            small: 'period',
            value: sterndrive_period,
            note: (i+1).toString()
        };
        main_json_arr.push(sterndrive_period_json_obj);
    }

    // 교육일수
    let sterndrive_days = $('#edu_sterndrive_days').find('input[type=text]').val();
    let sterndrive_days_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'days',
        value: sterndrive_days
    };
    main_json_arr.push(sterndrive_days_json_obj);

    // 교육시간
    let sterndrive_time = $('#edu_sterndrive_time').find('input[type=text]').val();
    let sterndrive_time_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'time',
        value: sterndrive_time
    };
    main_json_arr.push(sterndrive_time_json_obj);

    // 교육장소
    let sterndrive_place = $('#edu_sterndrive_place').find('.item').find('input[type=text]').val();
    let sterndrive_place_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'place',
        value: sterndrive_place
    };
    main_json_arr.push(sterndrive_place_json_obj);
    let sterndrive_placeDetail = $('#edu_sterndrive_place').find('.address').find('input[type=text]').val();
    let sterndrive_placeDetail_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'placeDetail',
        value: sterndrive_placeDetail
    };
    main_json_arr.push(sterndrive_placeDetail_json_obj);

    // 교육인원
    let sterndrive_persons = $('#edu_sterndrive_persons').find('input[type=text]').val();
    let sterndrive_persons_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'persons',
        value: sterndrive_persons
    };
    main_json_arr.push(sterndrive_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let sterndrive_pay = $('#edu_sterndrive_pay').find('input[type=text]').val();
    let sterndrive_pay_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'pay',
        value: sterndrive_pay
    };
    main_json_arr.push(sterndrive_pay_json_obj);

    // 교육장비
    let sterndrive_stuff_arr = $('#edu_sterndrive_stuff').find('input[type=text]');
    for(let i=0; i<sterndrive_stuff_arr.length; i++){
        let sterndrive_stuff = sterndrive_stuff_arr.eq(i).val();
        let sterndrive_stuff_json_obj = {
            major: 'sterndrive',
            middle: 'info',
            small: 'stuff',
            value: sterndrive_stuff,
            note: (i+1).toString()
        };
        main_json_arr.push(sterndrive_stuff_json_obj);
    }

    // 신청방법
    let applyMethod = $('#edu_sterndrive_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_sterndrive_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_sterndrive_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_sterndrive_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'sterndrive',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'sterndrive',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('sterndrive');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_basic_save(){
    console.log('f_train_template_basic_save');

    let main_json_arr = [];
    // 교육대상
    let basic_target_arr = $('#edu_basic_target').find('input[type=text]');
    for(let i=0; i<basic_target_arr.length; i++){
        let basic_target = basic_target_arr.eq(i).val();
        let basic_target_json_obj = {
            major: 'basic',
            middle: 'info',
            small: 'target',
            value: basic_target,
            note: (i+1).toString()
        };
        main_json_arr.push(basic_target_json_obj);
    }

    // 교육내용
    let basic_contents_arr = $('#edu_basic_contents').find('input[type=text]');
    for(let i=0; i<basic_contents_arr.length; i++){
        let basic_contents = basic_contents_arr.eq(i).val();
        let basic_contents_json_obj = {
            major: 'basic',
            middle: 'info',
            small: 'contents',
            value: basic_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(basic_contents_json_obj);
    }

    // 교육기간
    let basic_period_arr = $('#edu_basic_period').find('input[type=text]');
    for(let i=0; i<basic_period_arr.length; i++){
        let basic_period = basic_period_arr.eq(i).val();
        let basic_period_json_obj = {
            major: 'basic',
            middle: 'info',
            small: 'period',
            value: basic_period,
            note: (i+1).toString()
        };
        main_json_arr.push(basic_period_json_obj);
    }

    // 교육일수
    let basic_days = $('#edu_basic_days').find('input[type=text]').val();
    let basic_days_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'days',
        value: basic_days
    };
    main_json_arr.push(basic_days_json_obj);

    // 교육시간
    let basic_time = $('#edu_basic_time').find('input[type=text]').val();
    let basic_time_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'time',
        value: basic_time
    };
    main_json_arr.push(basic_time_json_obj);

    // 교육장소
    let basic_place_arr = $('#edu_basic_place').find('input[type=text]');
    for(let i=0; i<basic_place_arr.length; i++){
        let basic_place = basic_place_arr.eq(i).val();
        let basic_place_json_obj = {
            major: 'basic',
            middle: 'info',
            small: 'place',
            value: basic_place,
            note: (i+1).toString()
        };
        main_json_arr.push(basic_place_json_obj);
    }

    // 교육인원
    let basic_persons = $('#edu_basic_persons').find('input[type=text]').val();
    let basic_persons_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'persons',
        value: basic_persons
    };
    main_json_arr.push(basic_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let basic_pay = $('#edu_basic_pay').find('input[type=text]').val();
    let basic_pay_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'pay',
        value: basic_pay
    };
    main_json_arr.push(basic_pay_json_obj);

    // 교육장비
    let basic_stuff_arr = $('#edu_basic_stuff').find('input[type=text]');
    for(let i=0; i<basic_stuff_arr.length; i++){
        let basic_stuff = basic_stuff_arr.eq(i).val();
        let basic_stuff_json_obj = {
            major: 'basic',
            middle: 'info',
            small: 'stuff',
            value: basic_stuff,
            note: (i+1).toString()
        };
        main_json_arr.push(basic_stuff_json_obj);
    }

    // 신청방법
    let applyMethod = $('#edu_basic_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_basic_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_basic_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_basic_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'basic',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'basic',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('basic');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_emergency_save(){
    console.log('f_train_template_emergency_save');

    let main_json_arr = [];
    // 교육대상
    let emergency_target_arr = $('#edu_emergency_target').find('input[type=text]');
    for(let i=0; i<emergency_target_arr.length; i++){
        let emergency_target = emergency_target_arr.eq(i).val();
        let emergency_target_json_obj = {
            major: 'emergency',
            middle: 'info',
            small: 'target',
            value: emergency_target,
            note: (i+1).toString()
        };
        main_json_arr.push(emergency_target_json_obj);
    }

    // 교육내용
    let emergency_contents_arr = $('#edu_emergency_contents').find('input[type=text]');
    for(let i=0; i<emergency_contents_arr.length; i++){
        let emergency_contents = emergency_contents_arr.eq(i).val();
        let emergency_contents_json_obj = {
            major: 'emergency',
            middle: 'info',
            small: 'contents',
            value: emergency_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(emergency_contents_json_obj);
    }

    // 교육기간
    let emergency_period_arr = $('#edu_emergency_period').find('input[type=text]');
    for(let i=0; i<emergency_period_arr.length; i++){
        let emergency_period = emergency_period_arr.eq(i).val();
        let emergency_period_json_obj = {
            major: 'emergency',
            middle: 'info',
            small: 'period',
            value: emergency_period,
            note: (i+1).toString()
        };
        main_json_arr.push(emergency_period_json_obj);
    }

    // 교육일수
    let emergency_days = $('#edu_emergency_days').find('input[type=text]').val();
    let emergency_days_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'days',
        value: emergency_days
    };
    main_json_arr.push(emergency_days_json_obj);

    // 교육시간
    let emergency_time = $('#edu_emergency_time').find('input[type=text]').val();
    let emergency_time_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'time',
        value: emergency_time
    };
    main_json_arr.push(emergency_time_json_obj);

    // 교육장소
    let emergency_place_arr = $('#edu_emergency_place').find('input[type=text]');
    for(let i=0; i<emergency_place_arr.length; i++){
        let emergency_place = emergency_place_arr.eq(i).val();
        let emergency_place_json_obj = {
            major: 'emergency',
            middle: 'info',
            small: 'place',
            value: emergency_place,
            note: (i+1).toString()
        };
        main_json_arr.push(emergency_place_json_obj);
    }

    // 교육인원
    let emergency_persons = $('#edu_emergency_persons').find('input[type=text]').val();
    let emergency_persons_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'persons',
        value: emergency_persons
    };
    main_json_arr.push(emergency_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let emergency_pay = $('#edu_emergency_pay').find('input[type=text]').val();
    let emergency_pay_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'pay',
        value: emergency_pay
    };
    main_json_arr.push(emergency_pay_json_obj);

    // 교육장비
    let emergency_stuff_arr = $('#edu_emergency_stuff').find('input[type=text]');
    for(let i=0; i<emergency_stuff_arr.length; i++){
        let emergency_stuff = emergency_stuff_arr.eq(i).val();
        let emergency_stuff_json_obj = {
            major: 'emergency',
            middle: 'info',
            small: 'stuff',
            value: emergency_stuff,
            note: (i+1).toString()
        };
        main_json_arr.push(emergency_stuff_json_obj);
    }

    // 신청방법
    let applyMethod = $('#edu_emergency_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_emergency_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_emergency_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_emergency_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'emergency',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'emergency',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('emergency');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_generator_save(){
    console.log('f_train_template_generator_save');

    let main_json_arr = [];
    // 교육대상
    let generator_target_arr = $('#edu_generator_target').find('input[type=text]');
    for(let i=0; i<generator_target_arr.length; i++){
        let generator_target = generator_target_arr.eq(i).val();
        let generator_target_json_obj = {
            major: 'generator',
            middle: 'info',
            small: 'target',
            value: generator_target,
            note: (i+1).toString()
        };
        main_json_arr.push(generator_target_json_obj);
    }

    // 교육내용
    let generator_contents_arr = $('#edu_generator_contents').find('input[type=text]');
    for(let i=0; i<generator_contents_arr.length; i++){
        let generator_contents = generator_contents_arr.eq(i).val();
        let generator_contents_json_obj = {
            major: 'generator',
            middle: 'info',
            small: 'contents',
            value: generator_contents,
            note: (i+1).toString()
        };
        main_json_arr.push(generator_contents_json_obj);
    }

    // 교육기간
    let generator_period_arr = $('#edu_generator_period').find('input[type=text]');
    for(let i=0; i<generator_period_arr.length; i++){
        let generator_period = generator_period_arr.eq(i).val();
        let generator_period_json_obj = {
            major: 'generator',
            middle: 'info',
            small: 'period',
            value: generator_period,
            note: (i+1).toString()
        };
        main_json_arr.push(generator_period_json_obj);
    }

    // 교육일수
    let generator_days = $('#edu_generator_days').find('input[type=text]').val();
    let generator_days_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'days',
        value: generator_days
    };
    main_json_arr.push(generator_days_json_obj);

    // 교육시간
    let generator_time = $('#edu_generator_time').find('input[type=text]').val();
    let generator_time_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'time',
        value: generator_time
    };
    main_json_arr.push(generator_time_json_obj);

    // 교육장소
    let generator_place = $('#edu_generator_place').find('.item').find('input[type=text]').val();
    let generator_place_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'place',
        value: generator_place
    };
    main_json_arr.push(generator_place_json_obj);
    let generator_placeDetail = $('#edu_generator_place').find('.address').find('input[type=text]').val();
    let generator_placeDetail_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'placeDetail',
        value: generator_placeDetail
    };
    main_json_arr.push(generator_placeDetail_json_obj);

    // 교육인원
    let generator_persons = $('#edu_generator_persons').find('input[type=text]').val();
    let generator_persons_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'persons',
        value: generator_persons
    };
    main_json_arr.push(generator_persons_json_obj);

    // 교육비
    // 선외기 정비사 실무교육
    let generator_pay = $('#edu_generator_pay').find('input[type=text]').val();
    let generator_pay_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'pay',
        value: generator_pay
    };
    main_json_arr.push(generator_pay_json_obj);

    // 교육장비
    let generator_stuff_arr = $('#edu_generator_stuff').find('input[type=text]');
    for(let i=0; i<generator_stuff_arr.length; i++){
        let generator_stuff = generator_stuff_arr.eq(i).val();
        let generator_stuff_json_obj = {
            major: 'generator',
            middle: 'info',
            small: 'stuff',
            value: generator_stuff,
            note: (i+1).toString()
        };
        main_json_arr.push(generator_stuff_json_obj);
    }

    // 신청방법
    let applyMethod = $('#edu_generator_apply_method').find('.item').find('textarea').val();
    let applyMethod_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'applyMethod',
        value: applyMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(applyMethod_json_obj);
    let applyMethodUrl = $('#edu_generator_apply_method').find('.cmnt').find('input[type=text]').val();

    if(nvl(applyMethodUrl, '') !== ''){
        if(!checkUrl(applyMethodUrl)){
            showMessage('', 'error', '[템플릿 등록 안내]', '신청방법 항목의 URL 주소는 http:// 나 https:// 를 포함하여 입력해 주세요.', '');
            return false;
        }
    }

    let applyMethodUrl_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'applyMethodUrl',
        value: applyMethodUrl
    };
    main_json_arr.push(applyMethodUrl_json_obj);

    // 모집방법
    let recruitMethod = $('#edu_generator_recruit_method').find('.item').find('textarea').val();
    let recruitMethod_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'recruitMethod',
        value: recruitMethod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitMethod_json_obj);

    // 모집기간
    let recruitPeriod = $('#edu_generator_recruit_period').find('.item').find('textarea').val();
    let recruitPeriod_json_obj = {
        major: 'generator',
        middle: 'info',
        small: 'recruitPeriod',
        value: recruitPeriod.replaceAll("\n", "<br/>")
    };
    main_json_arr.push(recruitPeriod_json_obj);

    let main_json_obj = {
        gbn: 'generator',
        data: main_json_arr
    }

    Swal.fire({
        title: '입력된 정보를 저장하시겠습니까?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#00a8ff',
        confirmButtonText: '저장',
        cancelButtonColor: '#A1A5B7',
        cancelButtonText: '취소'
    }).then(async (result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/mng/education/template/save.do',
                method: 'POST',
                async: false,
                data: JSON.stringify(main_json_obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    if (data.resultCode === "0") {
                        Swal.fire({
                            title: '교육 안내 템플릿 정보 저장',
                            html: '교육 안내 템플릿 정보가 저장되었습니다.',
                            icon: 'info',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '확인'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                f_train_template_init('generator');
                            }
                        });
                    } else {
                        showMessage('', 'error', '에러 발생', '교육 안내 템플릿 정보 저장을 실패하였습니다. 관리자에게 문의해주세요. ' + data.resultMessage, '');
                    }
                },
                error: function (xhr, status) {
                    alert('오류가 발생했습니다. 관리자에게 문의해주세요.\n오류명 : ' + xhr + "\n상태 : " + status);
                }
            })//ajax
        }
    });

}

function f_train_template_init(tapName){
    window.location.href = '/mng/education/template.do?tapName=' + tapName;
}

function checkUrl(strUrl) {
    let expUrl = /^http[s]?:\/\/([\S]{3,})/i;
    return expUrl.test(strUrl);
}