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
        /*console.log("선택된 값: " + selectedValue);*/
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
        $('#rc-birth-year').append('<option value="' + i + '">' + i + '</option>');
    }

    // 월별 selectbox 만들기            
    for (var i = 1; i <= 12; i++) {
        var mm = i > 9 ? i : "0" + i;
        $('#birth-month').append('<option value="' + mm + '">' + mm + '</option>');
        $('#rc-birth-month').append('<option value="' + mm + '">' + mm + '</option>');
    }

    // 일별 selectbox 만들기
    for (var i = 1; i <= 31; i++) {
        var dd = i > 9 ? i : "0" + i;
        $('#birth-day').append('<option value="' + dd + '">' + dd + '</option>');
        $('#rc-birth-day').append('<option value="' + dd + '">' + dd + '</option>');
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
    $(document).on("click",".form_cancel_edu_btn", function(event){
        let changeYn = $(this).siblings('input[type=hidden]').val();
        if(nvl(changeYn,'') !== '' && changeYn === 'Y'){
            $('#popupCancelEdu').addClass('on');

            let trainName = $(this).parent().siblings('.subject').find('a').text();
            if(nvl(trainName,'') !== ''){
                $('#popupCancelEdu').find('.train_name').text(trainName);

                let seq = $(this).attr('value');

                if(trainName.toString().includes('팸투어') || trainName.toString().includes('전자장비')){
                    $("#popupCancelEdu .refund_account_box").css("display", "none");

                    $('#popupCancelEdu').find('.edu_cancel_btn').attr('onclick',"f_edu_apply_cancel_btn('" + seq + "','" + trainName + "','" + '' + "','" + '' + "')");
                }else{
                    let payStatus = $(this).data('status');
                    let payMethod = $(this).data('value');
                    payMethod = payMethod.toLowerCase();
                    if(payMethod.includes('card')){
                        $("#popupCancelEdu .refund_account_box").css("display", "none");
                    }else{
                        if(payStatus === '입금대기'){
                            $("#popupCancelEdu .refund_account_box").css("display", "none");
                        }else{
                            $("#popupCancelEdu .refund_account_box").css("display", "block");
                        }
                    }

                    $('#popupCancelEdu').find('.edu_cancel_btn').attr('onclick',"f_edu_apply_cancel_btn('" + seq + "','" + trainName + "','" + payMethod + "','" + payStatus + "')");
                }
            }
        }else{
            Swal.fire({
                title: '[ 교육 신청 정보 ]',
                html: '죄송합니다.<br> 교육 4일 전 ~ 교육 시작일 이후 취소는 불가합니다.',
                icon: 'info',
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인'
            });
        }

    });

    // 각 신청 페이지 신청 취소 버튼
    $(document).on("click",".form_apply_cancel_edu_btn", function(event){
        let changeYn = $(this).siblings('input[type=hidden][name=chg_changeYn]').val();
        let seq = $(this).siblings('input[type=hidden][name=chg_seq]').val();
        let trainName = $(this).siblings('input[type=hidden][name=chg_trainName]').val();
        let payMethod = $(this).siblings('input[type=hidden][name=chg_payMethod]').val();
        let applyStatus = $(this).siblings('input[type=hidden][name=chg_applyStatus]').val();
        if(changeYn === 'Y'){
            $('#popupCancelEdu').addClass('on');

            if(nvl(trainName,'') !== ''){

                $('#popupCancelEdu').find('.train_name').text(trainName);

                if(trainName.toString().includes('팸투어') || trainName.toString().includes('전자장비')){

                    $("#popupCancelEdu .refund_account_box").css("display", "none");

                    $('#popupCancelEdu').find('.edu_cancel_btn').attr('onclick',"f_edu_apply_cancel_btn('" + seq + "','" + trainName + "','" + '' + "','" + '' + "')");

                }else{
                    payMethod = payMethod.toLowerCase();
                    if(payMethod.includes('card')){
                        $("#popupCancelEdu .refund_account_box").css("display", "none");
                    }else{
                        if(applyStatus === '입금대기'){
                            $("#popupCancelEdu .refund_account_box").css("display", "none");
                        }else{
                            $("#popupCancelEdu .refund_account_box").css("display", "block");
                        }
                    }

                    $('#popupCancelEdu').find('.edu_cancel_btn').attr('onclick',"f_edu_apply_cancel_btn('" + seq + "','" + trainName + "','" + payMethod + "','" + applyStatus + "')");
                }
            }
        }else{
            Swal.fire({
                title: '[ 교육 신청 정보 ]',
                html: '죄송합니다.<br> 교육 4일 전 ~ 교육 시작일 이후 취소는 불가합니다.',
                icon: 'info',
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인'
            });
        }

    });

    // 마이페이지 교육취소 팝업 - 10자 이상 입력 시 alert창 노출
    /*$("#popupCancelEdu .btn_next").on("click", function () {
        var inputValue = $(".cancel_edu_reason").val().trim();

        if (inputValue.length <= 10) {
            $("#popupCancelEdu .cmnt_box").css("display", "block");
        } else {
            $("#popupCancelEdu .cmnt_box").css("display", "none");
            $("#popupCancelEdu .box_1").css("display", "none");
            $("#popupCancelEdu .box_2").css("display", "block");


        }
    });*/

    // 마이페이지 교육취소 팝업 - 취소버튼 클릭 시, input 값 초기화 및 닫힘
    $("#popupCancelEdu .btn_prev").on("click", function () {
        $(".cancel_edu_reason").val("");
        $("#popupCancelEdu .cmnt_box").css("display", "none");
        $("#popupCancelEdu .box_1").css("display", "block");
        $("#popupCancelEdu .box_2").css("display", "none");
        $("#popupCancelEdu .refund_account_box").css("display", "none");
        $('#popupCancelEdu #cancel_edu_bank_select').val("");
        $('#popupCancelEdu .refund_account_box input[type=text]').val("");
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
            $('#popupDeleteId .cmnt_box').css('display', 'none');
            $('#popupDeleteId').removeClass('on');

            Swal.fire({
                title: '[회원 정보]',
                html: '정말로 회원 탈퇴를 진행하시겠습니까?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#00a8ff',
                confirmButtonText: '탈퇴하기',
                cancelButtonColor: '#A1A5B7',
                cancelButtonText: '취소'
            }).then(async (result) => {
                if (result.isConfirmed) {
                    f_main_member_withdraw();
                }
            });
            /*alert("이용해 주셔서 감사합니다.");
            window.location = "/index.do";*/
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
        let flag = $(this).hasClass('check_etc');
        if(flag){
            $(this).next('span').find('.check_etc_input').prop('disabled', false);
        }else{
            $(this).parent().siblings('label').find('.check_etc_input').prop('disabled', true).val('');
        }
    });

    // 마이페이지 교육취소 팝업 - 취소버튼 클릭 시, input 값 초기화 및 닫힘
    $("#popupPaySel .btn_prev").on("click", function () {
        $('#popupPaySel #pay_select').val("");
        $('#popupPaySel').removeClass('on');
        $('#popupPaySel').find('input[type=hidden]').val('');
    });

    // 페이지 로드시 초기 설정
    //$('input[type="radio"]').on('change', function () {});


    ///////////////// 경력사항 추가 /////////////////
    let formCareerCount = $('.formCareerBox:last .formCareerNum').text();

    // .formCareerBox를 추가하는 이벤트 핸들러 추가
    $('.formCareerAdd').on('click', function () {
        let newformCareerBox = $('.formCareerBox:first').clone(true);
        formCareerCount++;
        newformCareerBox.find('.formCareerNum').text(formCareerCount);
        newformCareerBox.find('input[type="text"]').val('');
        newformCareerBox.find('input[type="hidden"]').val('');

        // 복제된 .formCareerBox 내의 삭제 버튼 보이기
        newformCareerBox.find('.formCareerDel').show();

        // 파일 입력 초기화 및 비활성화 속성 제거
        let fileInput = newformCareerBox.find('.upload_hidden');
        let fileNameInput = newformCareerBox.find('.upload_name');
        fileInput.val('').attr('id', 'careerLicenseFile' + formCareerCount);
        fileNameInput.val('').attr('disabled', true).attr('id', 'careerLicense' + formCareerCount).attr('name', 'careerLicense');
        newformCareerBox.find('label').attr('for', 'careerLicenseFile' + formCareerCount);

        // 복제된 .onlineInfoBox 는 제품 기존 값 목록 제거
        newformCareerBox.find('.preFileList').remove();

        newformCareerBox.find('.formCareerDel').on('click', function () {
            deleteformCareerBox(this);
        });
        $('.formCareerBox:last').after(newformCareerBox);
        updateformCareerNum();
    });

    // .formCareerBox를 삭제하는 이벤트 핸들러
    function deleteformCareerBox(el) {
        Swal.fire({
            title: '[경력사항 정보]',
            html: '해당 경력사항 정보를 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                let seq = $(el).siblings('input[type=hidden]').val();
                if(nvl(seq,'') !== ''){
                    let jsonObj = {
                        seq: seq
                    };
                    let resData = ajaxConnect('/mypage/eduApply02/career/delete.do','post',jsonObj);
                    if(resData.resultCode !== "0"){
                        showMessage('', 'error', '에러 발생', '경력사항 정보 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    }

                    let fileList = $(el).parent().parent().find('.preFileList').find('.naeyong ul .careerLicenseFile_li').find('input[type=hidden][name=careerLicenseUploadFile]');
                    if(nvl(fileList, '') !== ''){
                        for(let i=0; i<fileList.length; i++){
                            let file_id = fileList[i].id;
                            if(nvl(file_id,'') !== ''){
                                let file_jsonObj = {
                                    id: file_id
                                };
                                let resData = ajaxConnect('/file/delete.do','post',file_jsonObj);
                                if(resData.resultCode !== '0'){
                                    showMessage('', 'error', '에러 발생', '파일 정보 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                                }
                            }
                        }
                    }
                }

                $(el).closest('.formCareerBox').remove();
                formCareerCount--; // 개수를 감소시킴
                updateformCareerNum();
            }//isConfirmed
        }); //swal
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
        deleteformCareerBox(this);
    });

    ///////////////// 자격면허 추가 /////////////////
    let formLicenseCount = $('.formLicenseBox:last .formLicenseNum').text();

    // .formLicenseBox를 추가하는 이벤트 핸들러 추가
    $('.formLicenseAdd').on('click', function () {
        let newformLicenseBox = $('.formLicenseBox:first').clone(true);
        formLicenseCount++;
        newformLicenseBox.find('.formLicenseNum').text(formLicenseCount);
        newformLicenseBox.find('input[type="text"]').val('');
        newformLicenseBox.find('input[type="hidden"]').val('');

        // 복제된 .formLicenseBox 내의 삭제 버튼 보이기
        newformLicenseBox.find('.formLicenseDel').show();

        newformLicenseBox.find('.formLicenseDel').on('click', function () {
            deleteformLicenseBox(this);
        });
        $('.formLicenseBox:last').after(newformLicenseBox);
        updateformLicenseNum();
    });

    // .formLicenseBox를 삭제하는 이벤트 핸들러
    function deleteformLicenseBox(el) {
        Swal.fire({
            title: '[자격면허 정보]',
            html: '해당 자격면허 정보를 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: '삭제하기',
            cancelButtonColor: '#A1A5B7',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                let seq = $(el).siblings('input[type=hidden]').val();
                if(nvl(seq,'') !== ''){
                    let jsonObj = {
                        seq: seq
                    };
                    let resData = ajaxConnect('/mypage/eduApply02/license/delete.do','post',jsonObj);
                    if(resData.resultCode !== "0"){
                        showMessage('', 'error', '에러 발생', '자격면허 정보 삭제를 실패하였습니다. 관리자에게 문의해주세요. ' + resData.resultMessage, '');
                    }
                }

                $(el).closest('.formLicenseBox').remove();
                formLicenseCount--; // 개수를 감소시킴
                updateformLicenseNum();
            }//isConfirmed
        }); //swal
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
        deleteformLicenseBox(this);
    });


});