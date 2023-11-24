<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="format-detection" content="telephone=no" />
    <title>해양레저인력양성센터</title>

    <link href="<%request.getContextPath();%>/static/css/reset.css" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/mngStyle.css" rel="stylesheet">

    <%-- favicon
    <link rel="shortcut icon" href="<%request.getContextPath();%>/static/img/favicon.ico" type="image/x-icon" sizes="16X16" />
    <link rel="icon" href="<%request.getContextPath();%>/static/img/favicon.ico" type="image/x-icon" sizes="16X16" />
    --%>
</head>

<body>
<%-- 페이지 뒤로가기 막기 --%>
<script>
    history.pushState(null, null, location.href);
    window.onpopstate = function (event) {
        history.go(1);
    };
</script>

    <div id="main_container">
        <div id="login">
            <form id="login_form" name="login_form" action="/mng/main.do" method="post" onsubmit="return false;">
                <div>
                    <p class="brand-logo">
                        <img src="<%request.getContextPath();%>/static/img/logo.png" class="mngLogo">
                        <br><br>
                        <span id="logo_txt">[ 관리자 시스템 ]</span>
                    </p>
                    <p class="mb20 mt30">
                        <input type="text" name="adminId" id="adminId" class="inp" placeholder="아이디" autocomplete="off">
                    </p>
                    <p class="mb40">
                        <input type="password" name="adminPw" id="adminPw" class="inp" placeholder="비밀번호" autocomplete="off">
                    </p>
                </div>
            </form>

            <p class="mt30 mb40">
                <button id="login_submit_btn" class="btn-login" onclick="loginFormSubmit()">로그인</button>
            </p>
        </div>
    </div>

<script>
    document.addEventListener("keyup", function(event) {
        if (event.key === 'Enter') {
            loginFormSubmit();
        }
    });
</script>

<script type="text/javascript">

    function getIP(json) {
        let myIp = json.ip;
        let ipArr = ['118.36.143.89'];
        let pass = false;
        for(let i=0; i<ipArr.length; i++){
            if(myIp === ipArr[i]){
                pass = true;
                break;
            }
        }

        if(!pass){
            alert('EDU marine 관리자 시스템에 접근 권한이 없는 IP입니다.\n메인페이지로 이동합니다.');
            window.location.href = '/';
        }
    }

    document.addEventListener("keyup", function(event) {
        if (event.key === 'Enter') {
            loginFormSubmit();
        }
    });
</script>

<c:import url="../inc/commonMngJS.jsp" charEncoding="UTF-8"/>
<script type="text/javascript" src="https://api64.ipify.org?format=jsonp&callback=getIP"></script>

</body>

</html>