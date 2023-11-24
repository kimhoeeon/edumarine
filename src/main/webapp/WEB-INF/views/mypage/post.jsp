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
    <title>해양레저인력양성센터</title>

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
</head>

<body>

    <c:import url="../header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top sub_top_my">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>마이페이지</span>
                    <span>나의 게시글</span>
                </div>
                <h2 class="sub_top_title">나의 게시글</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">마이페이지</div>
                <ul class="lnb">
                    <li><a href="/mypage/eduApplyInfo.do">교육이력조회</a></li>
                    <li><a href="/mypage/resume.do">나의 이력서</a></li>
                    <li class="on"><a href="/mypage/post.do">나의 게시글</a></li>
                    <li><a href="/mypage/modify.do">회원 정보</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_mypage">

                <!-- my_post_list_wrap -->
                <div class="my_post_list_wrap">
                    <div class="sub_box_tit">
                        <div class="big">내 게시글</div>
                    </div>
                    <ul class="my_post_list">
                        <li>
                            <div class="check"><input type="checkbox" class="my_post_chk"></div>
                            <div class="number">2</div>
                            <div class="info">
                                <div class="subject"><a href="/job/community_view.do">게시물 제목</a></div>
                                <div class="description">
                                    <div>2023.06.11</div>
                                    <div><span class="color">조회</span>123</div>
                                    <div><span class="color">추천</span>2</div>
                                </div>
                            </div>
                            <div class="btn">
                                <a href="/job/community_modify.do" class="btn_modify btnSt04">수정</a>
                                <a href="" class="btn_delete btnSt04">삭제</a>
                            </div>
                        </li>
                        <li>
                            <div class="check"><input type="checkbox" class="my_post_chk"></div>
                            <div class="number">1</div>
                            <div class="info">
                                <div class="subject"><a href="/job/community_view.do">게시물 제목</a></div>
                                <div class="description">
                                    <div>2023.06.11</div>
                                    <div><span class="color">조회</span>123</div>
                                    <div><span class="color">추천</span>2</div>
                                </div>
                            </div>
                            <div class="btn">
                                <a href="/job/community_modify.do" class="btn_modify btnSt04">수정</a>
                                <a href="" class="btn_delete btnSt04">삭제</a>
                            </div>
                        </li>
                    </ul>
                    <div class="my_post_list_bot">
                        <div class="check"><label><input type="checkbox" class="my_post_chk_all">전체 선택</label></div>
                        <div class="btn"><a href="" class="btnSt04">삭제</a></div>
                    </div>
                </div>
                <!-- //my_post_list_wrap -->

                <!-- my_reply_list_wrap -->
                <div class="my_reply_list_wrap">
                    <div class="sub_box_tit">
                        <div class="big">내 댓글</div>
                    </div>
                    <ul class="my_reply_list">
                        <li>
                            <div class="check"><input type="checkbox" class="my_reply_chk"></div>
                            <div class="number">2</div>
                            <div class="info">
                                <div class="subject"><a href="/job/community_view.do">댓글 내용</a></div>
                                <div class="description">
                                    <div>2023.06.11</div>
                                    <div>게시물 제목</div>
                                </div>
                            </div>
                            <div class="btn">
                                <a href="" class="btn_delete btnSt04">삭제</a>
                            </div>
                        </li>
                        <li>
                            <div class="check"><input type="checkbox" class="my_reply_chk"></div>
                            <div class="number">1</div>
                            <div class="info">
                                <div class="subject"><a href="/job/community_view.do">댓글 내용</a></div>
                                <div class="description">
                                    <div>2023.06.11</div>
                                    <div>게시물 제목</div>
                                </div>
                            </div>
                            <div class="btn">
                                <a href="" class="btn_delete btnSt04">삭제</a>
                            </div>
                        </li>
                    </ul>
                    <div class="my_reply_list_bot">
                        <div class="check"><label><input type="checkbox" class="my_reply_chk_all">전체 선택</label></div>
                        <div class="btn"><a href="" class="btnSt04">삭제</a></div>
                    </div>
                </div>
                <!-- //my_reply_list_wrap -->

            </div>
            <!-- //content -->

        </div>
        <!-- content_wrap -->

    </div>
    <!-- //container -->

    <c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>

<script src="<%request.getContextPath();%>/static/js/jquery-3.6.0.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery-migrate-3.3.0.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>

<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/form.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>