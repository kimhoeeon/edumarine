$(document).ready(function () {

    // div,ul,li로 select 박스 기능 구현
    $(".select_label").on('blur click', function () {
        $(".option_list").toggle();
    });

    $(".option_item").on('blur click', function () {
        var selectedValue = $(this).text();
        $(".select_label").text(selectedValue);
        $(".option_list").hide();
        // 선택된 값을 콘솔에 출력
        console.log("선택된 값: " + selectedValue);
    });

    // 다른 부분을 클릭하면 옵션 목록을 닫음
    $(document).on('click' , function (event) {
        var target = $(event.target);
        if (!target.closest('.select_box').length) {
            $(".option_list").hide();
        }
    });


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

    // input 이메일
    $('.form_box .email_select').on('change', function () {
        var selectedOption = $(this).val();
        var emailInput2 = $('.form_box .email_input_2');

        if (selectedOption === '직접입력') {
            emailInput2.prop('disabled', false).val('');
        } else {
            emailInput2.prop('disabled', true).val(selectedOption);
        }
    });

    // input 생년월일
    var now = new Date();
    var year = now.getFullYear();
    var mon = (now.getMonth() + 1) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
    var day = (now.getDate()) > 9 ? '' + (now.getDate()) : '0' + (now.getDate());
    //년도 selectbox만들기               
    for (var i = 1940; i <= year; i++) {
        $('#birth-year').append('<option value="' + i + '">' + i + '</option>');
    }

    // 월별 selectbox 만들기            
    for (var i = 1; i <= 12; i++) {
        var mm = i > 9 ? i : "0" + i;
        $('#birth-month').append('<option value="' + mm + '">' + mm + '</option>');
    }

    // 일별 selectbox 만들기
    for (var i = 1; i <= 31; i++) {
        var dd = i > 9 ? i : "0" + i;
        $('#birth-day').append('<option value="' + dd + '">' + dd + '</option>');
    }

    // input file 폼 디자인
    var fileTarget = $('.form_file .upload_hidden');

    fileTarget.on('change', function () { // 값이 변경되면
        if (window.FileReader) { // modern browser
            var filename = $(this)[0].files[0].name;
        } else { // old IE
            var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
        }

        // 추출한 파일명 삽입
        $(this).siblings('.upload_name').val(filename);
    });


    // 교육신청 페이지 작성 취소
    $('.apply_cancel_edu_btn').on('click', function () {
        alert("교육신청이 취소되었습니다.");
        window.location = "/index.do";
    });


    // 마이페이지 교육취소 팝업 - 오픈
    $('.form_cancel_edu_btn').on('click', function () {
        $('#popupCancelEdu').addClass('on');
    });

    // 마이페이지 교육취소 팝업 - 10자 이상 입력 시 alert창 노출
    $("#popupCancelEdu .btn_next").on("click", function () {
        var inputValue = $(".cancel_edu_reason").val().trim();

        if (inputValue.length <= 10) {
            $("#popupCancelEdu .cmnt_box").css("display", "block");
        } else {
            $("#popupCancelEdu .cmnt_box").css("display", "none");
            $("#popupCancelEdu .box_1").css("display", "none");
            $("#popupCancelEdu .box_2").css("display", "block");
        }
    });

    // 마이페이지 교육취소 팝업 - 취소버튼 클릭 시, input 값 초기화 및 닫힘
    $("#popupCancelEdu .btn_prev").on("click", function () {
        $(".cancel_edu_reason").val("");
        $("#popupCancelEdu .cmnt_box").css("display", "none");
        $("#popupCancelEdu .box_1").css("display", "block");
        $("#popupCancelEdu .box_2").css("display", "none");
        $('#popupCancelEdu').removeClass('on');
    });
    $("#popupCancelEdu .btn_confirm").on("click", function () {
        window.location = "/mypage/eduApplyInfo.do";
    });



    // 탈퇴하기 팝업 - 오픈
    $('.form_delete_id_btn').on('click', function () {
        $('#popupDeleteId').addClass('on');
    });

    // 탈퇴하기 팝업 - 10자 이상 입력 시 alert창 노출
    $("#popupDeleteId .btn_confirm").on("click", function () {
        var inputValue = $(".delete_id_reason").val().trim();

        if (inputValue.length <= 10) {
            $("#popupDeleteId .cmnt_box").css("display", "block");
        } else {
            $("#popupDeleteId .cmnt_box").css("display", "none");
            alert("이용해 주셔서 감사합니다.");
            window.location = "/index.do";
        }
    });

    // 탈퇴하기 팝업 - 취소버튼 클릭 시, input 값 초기화 및 닫힘
    $("#popupDeleteId .btn_cancel").on("click", function () {
        $(".delete_id_reason").val("");
        $("#popupDeleteId .cmnt_box").css("display", "none");
        $('#popupDeleteId').removeClass('on');
    });


    // 개인정보수집 동의
    $('.f_privcy_chk').on('click', function () {
        var totalCheckboxes = $('.f_privcy_chk').length;
        var checkedCheckboxes = $('.f_privcy_chk:checked').length;

        if (checkedCheckboxes === totalCheckboxes) {
            $('.f_privcy_chk_all').prop('checked', true);
        } else {
            $('.f_privcy_chk_all').prop('checked', false);
        }
    });

    $('.f_privcy_chk_all').on('click', function () {
        var checked = $('.f_privcy_chk_all').is(':checked');

        if (checked) {
            $('.f_privcy_chk').prop('checked', true);
        } else {
            $('.f_privcy_chk').prop('checked', false);
        }
    });



    // 마이페이지 - 나의 게시글 전체체크
    $('.my_post_chk').on('click', function () {
        var totalCheckboxes = $('.my_post_chk').length;
        var checkedCheckboxes = $('.my_post_chk:checked').length;

        if (checkedCheckboxes === totalCheckboxes) {
            $('.my_post_chk_all').prop('checked', true);
        } else {
            $('.my_post_chk_all').prop('checked', false);
        }
    });

    $('.my_post_chk_all').on('click', function () {
        var checked = $('.my_post_chk_all').is(':checked');

        if (checked) {
            $('.my_post_chk').prop('checked', true);
        } else {
            $('.my_post_chk').prop('checked', false);
        }
    });

    // 마이페이지 - 나의 댓글 전체체크
    $('.my_reply_chk').on('click', function () {
        var totalCheckboxes = $('.my_reply_chk').length;
        var checkedCheckboxes = $('.my_reply_chk:checked').length;

        if (checkedCheckboxes === totalCheckboxes) {
            $('.my_reply_chk_all').prop('checked', true);
        } else {
            $('.my_reply_chk_all').prop('checked', false);
        }
    });

    $('.my_reply_chk_all').on('click', function () {
        var checked = $('.my_reply_chk_all').is(':checked');

        if (checked) {
            $('.my_reply_chk').prop('checked', true);
        } else {
            $('.my_reply_chk').prop('checked', false);
        }
    });

    // 사진교체방법 텍스트
    $('.form_box .img_replace_cmnt .btn').on('mouseover', function () {
        $(this).siblings('.text').show();
    }).on('mouseout' ,function () {
        $(this).siblings('.text').hide();
    });

    // 선택 시 input[type="text"] 활성화
    $('input[type="radio"]').on('change', function () {
        // 모든 .check_etc_input을 비활성화하고 초기화
        $('.check_etc_input').prop('disabled', true).val('');

        // .check_etc 클래스를 가진 라디오 버튼 중 선택된 것을 찾음
        $('.check_etc:checked').each(function () {
            // 선택된 .check_etc의 하위 요소인 .check_etc_input을 활성화
            $(this).closest('label').find('.check_etc_input').prop('disabled', false);
        });
    });

    // 페이지 로드시 초기 설정
    $('input[type="radio"]').on('change', function () {});


    ///////////////// 경력사항 추가 /////////////////
    let formCareerCount = 1;

    // .formCareerBox를 추가하는 이벤트 핸들러 추가
    $('.formCareerAdd').on('click', function () {
        let newformCareerBox = $('.formCareerBox:first').clone();
        formCareerCount++;
        newformCareerBox.find('.formCareerNum').text(formCareerCount);
        newformCareerBox.find('input[type="text"]').val('');

        // 복제된 .formCareerBox 내의 삭제 버튼 보이기
        newformCareerBox.find('.formCareerDel').show();

        newformCareerBox.find('.formCareerDel').click(deleteformCareerBox);
        $('.formCareerBox:last').after(newformCareerBox);
        updateformCareerNum();
    });

    // .formCareerBox를 삭제하는 이벤트 핸들러
    function deleteformCareerBox() {
        $(this).closest('.formCareerBox').remove();
        formCareerCount--; // 개수를 감소시킴
        updateformCareerNum();
    }


    // 각 .formCareerBox의 .formCareerNum 번호 업데이트
    function updateformCareerNum() {
        $('.formCareerBox').each(function (index) {
            $(this).find('.formCareerNum').text(index + 1);
        });
    }

    // 첫 번째 .formCareerBox 내의 삭제 버튼 숨기기
    $('.formCareerBox:first .formCareerDel').hide();

    // 첫 번째 .formCareerBox의 삭제 버튼에 대한 초기 이벤트 핸들러 추가
    $('.formCareerDel').on('click', function(){
        deleteformCareerBox();
    });

    ///////////////// 자격면허 추가 /////////////////
    let formLicenseCount = 1;

    // .formLicenseBox를 추가하는 이벤트 핸들러 추가
    $('.formLicenseAdd').on('click', function () {
        let newformLicenseBox = $('.formLicenseBox:first').clone();
        formLicenseCount++;
        newformLicenseBox.find('.formLicenseNum').text(formLicenseCount);
        newformLicenseBox.find('input[type="text"]').val('');

        // 복제된 .formLicenseBox 내의 삭제 버튼 보이기
        newformLicenseBox.find('.formLicenseDel').show();

        newformLicenseBox.find('.formLicenseDel').click(deleteformLicenseBox);
        $('.formLicenseBox:last').after(newformLicenseBox);
        updateformLicenseNum();
    });

    // .formLicenseBox를 삭제하는 이벤트 핸들러
    function deleteformLicenseBox() {
        $(this).closest('.formLicenseBox').remove();
        formLicenseCount--; // 개수를 감소시킴
        updateformLicenseNum();
    }


    // 각 .formLicenseBox의 .formLicenseNum 번호 업데이트
    function updateformLicenseNum() {
        $('.formLicenseBox').each(function (index) {
            $(this).find('.formLicenseNum').text(index + 1);
        });
    }

    // 첫 번째 .formLicenseBox 내의 삭제 버튼 숨기기
    $('.formLicenseBox:first .formLicenseDel').hide();

    // 첫 번째 .formLicenseBox의 삭제 버튼에 대한 초기 이벤트 핸들러 추가
    $('.formLicenseDel').on('click', function (){
        deleteformLicenseBox();
    });


});


