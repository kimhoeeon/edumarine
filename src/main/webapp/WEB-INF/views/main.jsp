<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="format-detection" content="telephone=no" />
    <title>경기해양레저 인력양성센터</title>

    <!-- 캐시를 바로 만료시킴. -->
    <meta http-equiv="Expires" content="-1" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.0) -->
    <meta http-equiv="Pragma" content="no-cache" />

    <!-- 페이지 로드시마다 페이지를 캐싱하지 않음. (HTTP 1.1) -->
    <meta http-equiv="Cache-Control" content="no-cache" />

    <!-- swiper 외부 라이브러리 -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <%-- sweetalert CDN --%>
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.min.css" rel="stylesheet">

    <link href="<%request.getContextPath();%>/static/css/reset.css" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/font.css" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/style.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="<%request.getContextPath();%>/static/css/responsive.css" rel="stylesheet">

    <style>
        .mainLayerPopup { display:none; position:fixed; width:auto; min-height:100px; top:74px; left:0px; padding:0px;  border:3px solid #242527; background:#fff; overflow:hidden; z-index:9999; }
        .mainLayerPopup .popupBox { text-align: center; padding:35px 0 0; min-height:150px; height:100%}
        .mainLayerPopup .popupBox img { display: block; margin: 0 0;}
        .mainLayerPopup .popupClose {text-align:right; background:#111; padding:10px;}
        .mainLayerPopup .popupClose input {vertical-align:middle;}
        .mainLayerPopup .popupClose label {color:#fff; font-size:14px; vertical-align:middle;  margin-left:3px;}
        .mainLayerPopup .popupClose a{color:#fff; width:15px; display:inline-block; vertical-align:middle; margin-left:10px; }
        .mainLayerPopup .popupClose input[type="checkbox"] {border:1px solid #ccc; background:#fff; width: 20px; height: 20px; margin-right:5px; margin-bottom:5px; vertical-align: middle; border-radius: 0;}
    </style>
</head>

<body>

    <c:import url="header.jsp" charEncoding="UTF-8"/>

    <!-- floating -->
    <div class="floating">
        <a href="<%request.getContextPath();%>/static/file/edumarine_brochure.pdf" target="_blank">
            <p><span class="bold">Brochure</span><br>Download</p>
            <img src="<%request.getContextPath();%>/static/img/icon_download_yellow.png">
        </a>
    </div>
    <!-- //floating -->

    <!-- container -->
    <div id="container">

        <!-- main_top -->
        <div class="main_top">
            <div class="inner">
                <!-- main_search_wrap -->
                <div class="main_search_wrap">
                    <div class="search_bar">
                        <input type="text" placeholder="원하시는 교육과정을 검색해보세요.">
                        <a href="/search.do" class="btn">검색</a>
                    </div>
                    <div class="hashtag">
                        <a href="">#선내기</a>
                        <a href="">#선외기</a>
                        <a href="">#세일요트</a>
                        <a href="">#마리나선박 정비사</a>
                    </div>
                </div>
                <!-- //main_search_wrap -->
                <!-- main_swiper_wrap -->
                <div class="main_swiper_wrap">
                    <div class="swiper_box">
                        <div class="swiper">
                            <ul class="swiper-wrapper">
                                <c:forEach var="mainBanner" items="${bannerList}" begin="0" end="${bannerList.size()}" step="1" varStatus="status">
                                    <c:set var="bannerFilePathSrc" value="${fn:replace(mainBanner.fullFilePath, './usr/local/tomcat/webapps', '../../../../..')}" />
                                <li class="swiper-slide img_box">
                                    <img src="${bannerFilePathSrc}" alt="메인 슬라이드_${status.index+1}">
                                </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
                <!-- //main_swiper_wrap -->
                <!-- main_menu_wrap & main_video_wrap -->
                <div class="main_top_bot">
                    <ul class="main_menu_wrap">
                        <li>
                            <a href="/edumarine/introduce.do">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_05.png" alt="센터소개 아이콘"></div>
                                <div class="text">센터소개</div>
                            </a>
                        </li>
                        <li>
                            <a href="/guide/guide01.do">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_01.png" alt="교육과정 아이콘"></div>
                                <div class="text">교육과정</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_02.png" alt="교육일정 아이콘"></div>
                                <div class="text">교육일정</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_04.png" alt="수료증발급 아이콘"></div>
                                <div class="text">수료증발급</div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_07.png" alt="커뮤니티 아이콘"></div>
                                <div class="text">커뮤니티</div>
                            </a>
                        </li>
                        <li>
                            <a href="/edumarine/way.do">
                                <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_menu_06.png" alt="오시는길 아이콘"></div>
                                <div class="text">오시는길</div>
                            </a>
                        </li>
                    </ul>
                    <div class="main_video_wrap">
                        <div class="embed-container">
                            <iframe src='https://www.youtube.com/embed/i1OVSZ1npzs?autoplay=1&mute=1' frameborder='0'
                                    allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
                <!-- //main_menu_wrap & main_video_wrap -->
            </div>
        </div>
        <!-- //main_top -->

        <!-- main_edu -->
        <div class="main_edu">
            <div class="inner">
                <!-- body_tit -->
                <h3 class="body_tit">
                    <div class="big">교육 과정</div>
                </h3>
                <!-- //body_tit -->
                <!-- tab_menu -->
                <ul class="main_edu_tab tab_menu">
                    <li class="on" data-tab="tab-1">해상엔진 테크니션</li>
                    <li data-tab="tab-2">선외기 자가정비과정</li>
                    <li data-tab="tab-3">선내기 자가정비과정</li>
                    <li data-tab="tab-4">세일요트 자가정비과정</li>
                    <li data-tab="tab-5">마리나선박 정비사 실기교육</li>
                </ul>
                <!-- tab_menu -->
                <!-- list -->
                <div class="main_edu_list tab_content on" id="tab-1">
                    <ul class="list_head">
                        <li>
                            <div class="name">과정명</div>
                            <div class="chasi">차시</div>
                            <div class="peopleRecruit">인원</div>
                            <div class="peopleApp">신청인원</div>
                            <div class="periodApp">신청기간</div>
                            <div class="periodTng">교육기간</div>
                        </li>
                    </ul>
                    <ul class="list_body">
                        <c:forEach var="mainTrain" items="${trainList}" begin="0" end="${trainList.size()}" step="1" varStatus="status">
                        <li>
                            <div class="name"><a href="">${mainTrain.gbn}</a></div>
                            <div class="chasi">${mainTrain.nextTime}</div>
                            <div class="peopleRecruit">${mainTrain.trainCnt}</div>
                            <div class="peopleApp">${mainTrain.trainApplyCnt}</div>
                            <div class="periodApp">${fn:substring(mainTrain.applyStartDttm,2, mainTrain.applyStartDttm.length())} ~ ${fn:substring(mainTrain.applyEndDttm,2, mainTrain.applyEndDttm.length())}</div>
                            <div class="periodTng">${fn:substring(mainTrain.trainStartDttm,2, mainTrain.trainStartDttm.length())} ~ ${fn:substring(mainTrain.trainEndDttm,2, mainTrain.trainEndDttm.length())}</div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-2">
                    <div class="edu_none">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_edu_no.png"></div>
                        <div class="text">해당 교육과정은 개설 예정입니다</div>
                    </div>
                </div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-3">
                    <div class="edu_none">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_edu_no.png"></div>
                        <div class="text">해당 교육과정은 개설 예정입니다</div>
                    </div>
                </div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-4">
                    <div class="edu_none">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_edu_no.png"></div>
                        <div class="text">해당 교육과정은 개설 예정입니다</div>
                    </div>
                </div>
                <!-- //list -->
                <!-- list -->
                <div class="main_edu_list tab_content" id="tab-5">
                    <div class="edu_none">
                        <div class="icon"><img src="<%request.getContextPath();%>/static/img/icon_main_edu_no.png"></div>
                        <div class="text">해당 교육과정은 개설 예정입니다</div>
                    </div>
                </div>
                <!-- //list -->
            </div>
        </div>
        <!-- //main_edu -->


        <!-- main_board -->
        <div class="main_board">
            <div class="inner">
                <!-- body_tit -->
                <h3 class="body_tit">
                    <div class="big">센터 소식</div>
                </h3>
                <!-- //body_tit -->
                <!-- main_board_wrap -->
                <div class="main_board_box">
                    <!-- notice -->
                    <div class="main_board_list">
                        <div class="title">
                            <h4 class="text">공지사항</h4>
                            <a href="/board/notice_list.do" class="more">더보기</a>
                        </div>
                        <ul class="list">
                            <c:forEach var="mainNotice" items="${noticeList}" begin="0" end="${noticeList.size()}" step="1" varStatus="status">
                            <li>
                                <div class="subject"><a href="/board/notice_view.do?seq=${mainNotice.seq}">${mainNotice.title}</a></div>
                                <div class="date">${fn:split(mainNotice.writeDate,' ')[0]}</div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- //notice -->
                    <!-- press -->
                    <div class="main_board_list">
                        <div class="title">
                            <h4 class="text">보도자료</h4>
                            <a href="/board/press_list.do" class="more">더보기</a>
                        </div>
                        <ul class="list">
                            <c:forEach var="mainPress" items="${pressList}" begin="0" end="${pressList.size()}" step="1" varStatus="status">
                            <li>
                                <div class="subject"><a href="/board/press_view.do?seq=${mainPress.seq}">${mainPress.title}</a></div>
                                <div class="date">${fn:split(mainPress.writeDate,' ')[0]}</div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- //press -->
                </div>
                <!-- //main_board_wrap -->
            </div>
        </div>
        <!-- //main_board -->

        <!-- subscribe_wrap -->
        <div class="subscribe_wrap">
            <div class="inner">
                <div class="title">
                    <span>경기해양레저인력양성센터</span>의 다양한 소식을<br>
                    뉴스레터로 받아보세요.
                </div>
                <div class="subscribe_bar">
                    <input type="email" id="subscriber_email" placeholder="e-mail">
                    <a href="javascript:void(0);" onclick="main_newsletter_subscriber_btn(this);" class="btn">구독하기</a>
                </div>
            </div>
        </div>
        <!-- //subscribe_wrap -->

    </div>
    <!-- //container -->

    <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

    <script src="<%request.getContextPath();%>/static/js/jquery-3.6.0.min.js"></script>
    <script src="<%request.getContextPath();%>/static/js/jquery-migrate-3.3.0.js"></script>
    <script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>

    <script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
    <script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
    <script src="<%request.getContextPath();%>/static/js/form.js"></script>
    <script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

    <c:import url="footer.jsp" charEncoding="UTF-8"/>

    <%-- 뒷배경 적용 시 주석 해제 <div id="mainLayerPopup" style="display: block;"></div>--%>
    <c:set var="imgLeftPosition" value="0"/>
    <c:forEach var="popup" items="${popupList}" begin="0" end="${popupList.size()}" step="1" varStatus="status">
        <c:if test="${status.index eq 0}">
            <c:set var="imgLeftPosition" value="${popup.leftPx}"/>
        </c:if>
        <c:if test="${status.index > 0}">
            <c:set var="imgLeftPosition" value="${imgLeftPosition + popup.leftPx + popupList.get(status.index-1).widthPx}"/>
        </c:if>
        <div class="mainLayerPopup" id="id_popup_${popup.seq}"
             style="position:fixed; top:${popup.topPx}px; left:${imgLeftPosition}px; width:${popup.widthPx}px; display: none;">
            <div class="popupBox" style="padding:0;">
                    ${fn:replace(fn:replace(popup.content,'&lt;','<'),'&gt;','>')}
            </div>
            <div class="popupClose">
                <input type="checkbox" id="id_today_${popup.seq}" onclick="CloseMainPopup('${popup.seq}')">
                <label for="id_today_${popup.seq}"> 오늘 하루 그만보기 </label>
                <a href="javascript:CloseMainPopup('${popup.seq}');" style="margin-left:20px">
                    <img src="<%request.getContextPath();%>/static/img/close_w.png">
                </a>
            </div>
        </div>
    </c:forEach>

    <script>

        let cookie_first_name = 'popup_';

        function ShowMainPopup(pop_id) {
            let id_popup_name	= "id_popup_" + pop_id;
            let cookie_name		= cookie_first_name + pop_id;

            if( getStorage(cookie_name)){
                return;
            }
            let popupEl = $('#'+id_popup_name);
            let popupSize = popupEl.css('width').replace('px','');
            let popupLeft = parseInt('${imgLeftPosition}');

            let windowWidth = window.innerWidth;
            if(windowWidth >= 900 && windowWidth <= 1024){
                popupEl.css('width', (popupSize * 0.9) + 'px');
                popupEl.css('left', (popupLeft * 0.9) + 'px');
            }else if(windowWidth >= 769 && windowWidth <= 899) {
                popupEl.css('width', (popupSize * 0.7) + 'px');
                popupEl.css('left', (popupLeft * 0.7) + 'px');
            }else if(windowWidth >= 481 && windowWidth <= 768) {
                popupEl.css('width', (popupSize * 0.6) + 'px');
                popupEl.css('left', (popupLeft * 0.1) + 'px');
            }else if(windowWidth <= 480){
                popupEl.css('width', (popupSize * 0.5) + 'px');
                popupEl.css('left', '10px');
            }

            popupEl.show();
        }

        function CloseMainPopup(pop_id) {
            let id_popup_name	= "id_popup_" + pop_id;
            let id_today_name	= 'id_today_' + pop_id;
            let cookie_name		= cookie_first_name + pop_id;

            if( $(":input:checkbox[id='" + id_today_name + "']:checked").length > 0 ){
                setStorage(cookie_name, 1);
            }

            $('#'+id_popup_name).hide();
        }

        function setStorage(name, exp){
            // 만료 시간 구하기(exp를 ms단위로 변경)
            let date = new Date();
            date = date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000);

            // 로컬 스토리지에 저장하기
            // (값을 따로 저장하지 않고 만료 시간을 저장)
            localStorage.setItem(name, String(date));
        }

        function getStorage(name){
            let now = new Date();
            now = now.setTime(now.getTime());
            // 현재 시각과 스토리지에 저장된 시각을 각각 비교하여
            // 시간이 남아 있으면 true, 아니면 false 리턴
            return parseInt(localStorage.getItem(name)) > now
        }

        $(function(){
            <c:forEach var="popup" items="${popupList}" begin="0" end="${popupList.size()}" step="1">
            ShowMainPopup('${popup.seq}');
            </c:forEach>
        });
    </script>

</body>
</html>