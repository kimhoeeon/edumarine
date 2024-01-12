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
</head>

<body>

    <c:import url="../header.jsp" charEncoding="UTF-8"/>

    <!-- container -->
    <div id="container">

        <!-- sub_top -->
        <div class="sub_top sub_top_job">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>취업·창업</span>
                    <span>커뮤니티</span>
                </div>
                <h2 class="sub_top_title">커뮤니티</h2>
            </div>
        </div>
        <!-- //sub_top -->

        <!-- content_wrap -->
        <div id="content_wrap">
            <!-- sidebar -->
            <div id="sidebar">
                <div class="title">취업·창업</div>
                <ul class="lnb">
                    <!--<li><a href="/job/announcement_list.do">채용공고</a></li>-->
                    <li><a href="/job/state01.do">취창업현황</a></li>
                    <li><a href="/job/review.do">취창업성공후기</a></li>
                    <li class="on"><a href="/job/community_list.do">커뮤니티</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_board">
                <div class="board_wrap">

                    <!-- board_view -->
                    <div class="board_view_wrap">
                        <div class="subject">${info.title}</div>
                        <div class="info_box">
                            <div class="box write">
                                <div class="gubun">작성자</div>
                                <div class="naeyong">${info.writer}</div>
                            </div>
                            <div class="box date">
                                <div class="gubun">작성일</div>
                                <div class="naeyong">
                                    <c:set var="writeDate" value="${fn:replace(fn:split(info.writeDate,' ')[0],'-','.')}" />
                                    ${writeDate}
                                </div>
                            </div>
                            <div class="box views">
                                <div class="gubun">조회</div>
                                <div class="naeyong">${info.viewCnt}</div>
                            </div>
                        </div>
                        <div class="cont_box">
                            ${info.content}
                        </div>
                        <div class="tag_box">
                            <c:forTokens items="${info.hashtag}" delims="," var="item">
                                <p>#${item}</p>
                            </c:forTokens>
                        </div>
                        <div class="file_box">
                            <c:if test="${not empty fileList}">
                                <c:forEach var="fileInfo" items="${fileList}" begin="0" end="${fileList.size()}" step="1" varStatus="status">
                                    <a href="/file/download.do?path=board/${fileInfo.folderPath}&fileName=${fileInfo.fullFileName}" class="file">${fileInfo.fileName}</a>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty fileList}">
                                첨부파일 없음
                            </c:if>
                        </div>
                        <!-- 댓글 wrap -->
                        <div class="reply_wrap">
                            <!-- 댓글 상단 -->
                            <div class="reply_top">
                                <div class="repley_number">댓글 ${replyList.size()}
                                    <c:if test="${id eq null}">
                                        <span style="font-size: 1.4rem; color: #777; margin-left: 1.2rem;">댓글을 작성하시려면 로그인 해주세요.</span>
                                    </c:if>
                                </div>
                                <div href="javascript:void(0);" onclick="f_main_community_recommend_btn('${id}', '${info.seq}', this)"
                                     class="recommend_btn <c:if test="${not empty recommendInfo}">on</c:if>">추천 ${info.recommendCnt}</div>
                            </div>
                            <!-- //댓글 상단 -->
                            <!-- 댓글 리스트 -->
                            <div class="reply_list">
                                <c:if test="${not empty replyList}">
                                    <c:forEach var="reply" items="${replyList}" begin="0" end="${replyList.size()}" step="1" varStatus="status">
                                        <c:if test="${reply.depthReplyNo eq '0'}">
                                            <div class="reply_box">
                                                <div class="user_id">${reply.writer} <c:if test="${id eq info.writerKey}"><span class="icon_writer"></span></c:if></div>
                                                <div class="reply_cont"><c:out value="${reply.content}" escapeXml="true"/></div>
                                                <div class="reply_info">
                                                    <div class="date">
                                                        <c:set var="writeDate" value="${fn:replace(reply.writeDate,'-','.')}" />
                                                        ${writeDate}
                                                    </div>
                                                    <c:if test="${id ne null}">
                                                        <a href="javascript:void(0);" data-value="${reply.seq}" class="comment">답글작성</a>
                                                        <c:if test="${id eq reply.writer}">
                                                            <a href="javascript:void(0);" onclick="f_main_community_reply_remove('C','${reply.depthReplyNo}','${info.seq}', '${reply.seq}')" class="delete">삭제</a>
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="reply_write replyToReply">
                                                <div class="reply_write_inner">
                                                    <div class="user_id"><c:out value="${id}"/></div>
                                                    <textarea id="replyToReplyContent" placeholder="댓글을 남겨보세요"></textarea>
                                                    <div class="btn">
                                                        <a href="javascript:void(0);" class="btn_cancel">취소</a>
                                                        <a href="javascript:void(0);" onclick="f_main_community_reply_add('${info.seq}', '1', '${reply.seq}', '${id}', this);" class="btn_reg">등록</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:forEach var="reReply" items="${replyList}" begin="0" end="${replyList.size()}" step="1" varStatus="status">
                                            <c:if test="${reply.seq eq reReply.moReplySeq}">
                                                <div class="reply_box replyToReply">
                                                    <div class="user_id">${reReply.writer} <c:if test="${id eq info.writerKey}"><span class="icon_writer"></span></c:if></div>
                                                    <div class="reply_cont"><c:out value="${reReply.content}" escapeXml="true"/></div>
                                                    <div class="reply_info">
                                                        <div class="date">
                                                            <c:set var="writeDate" value="${fn:replace(reReply.writeDate,'-','.')}" />
                                                            ${writeDate}
                                                        </div>
                                                        <c:if test="${id ne null}">
                                                            <c:if test="${id eq reReply.writer}">
                                                                <a href="javascript:void(0);" onclick="f_main_community_reply_remove('C','${reReply.depthReplyNo}','${info.seq}', '${reReply.seq}')" class="delete">삭제</a>
                                                            </c:if>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${id ne null}">
                                    <!-- 댓글 작성 -->
                                    <div class="reply_write">
                                        <div class="reply_write_inner">
                                            <div class="user_id">
                                                <c:out value="${id}"/>
                                            </div>
                                            <textarea id="replyContent" placeholder="댓글을 남겨보세요"></textarea>
                                            <div class="btn">
                                                <a href="javascript:void(0);" onclick="f_main_community_reply_add('${info.seq}', '0', '', '${id}', this);" class="btn_reg">등록</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- //댓글 작성 -->
                                </c:if>

                                <%--<!-- 댓글 -->
                                <div class="reply_box">
                                    <div class="user_id">나는야댓글쓴이</div>
                                    <div class="reply_cont">내용내용내용 댓글 내용</div>
                                    <div class="reply_info">
                                        <div class="date">2023.11.03</div>
                                        <a href="#" class="comment">답글작성</a>
                                        <a href="#" class="delete">삭제</a>
                                    </div>
                                </div>
                                <!-- //댓글 -->
                                <!-- 답글(게시글 작성자) -->
                                <div class="reply_box replyToReply">
                                    <div class="user_id">나는야작성자 <span class="icon_writer"></span></div>
                                    <div class="reply_cont">내용내용내용 댓글 내용</div>
                                    <div class="reply_info">
                                        <div class="date">2023.11.03</div>
                                        <a href="#" class="comment">답글작성</a>
                                    </div>
                                </div>
                                <!-- //답글(게시글 작성자) -->
                                <!-- 답글 작성 -->
                                <div class="reply_write replyToReply">
                                    <div class="reply_write_inner">
                                        <div class="user_id">나는야댓글쓴이</div>
                                        <textarea placeholder="댓글을 남겨보세요"></textarea>
                                        <div class="btn"><a href="#" class="btn_cancel">취소</a><a href="#" class="btn_reg">등록</a></div>
                                    </div>
                                </div>
                                <!-- //답글 작성 -->--%>

                            </div>
                            <!-- //댓글 리스트 -->
                        </div>
                        <!-- //댓글 -->
                    </div>
                    <!-- //board_view -->

                    <div class="community_btn_box">
                        <div class="left">
                            <c:if test="${id eq info.writerKey}">
                                <a href="javascript:void(0);" onclick="f_login_check_page_move('${id}','${info.seq}','/job/community_modify.do')" class="btnSt03">수정</a>
                                <a href="javascript:void(0);" onclick="f_main_community_remove('${id}','${info.seq}')" class="btnSt03">삭제</a>
                            </c:if>
                        </div>
                        <div class="right"><a href="/job/community_list.do" class="btnSt01">목록으로</a></div>
                    </div>

                </div>
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

<script src="<%request.getContextPath();%>/static/js/front/community.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>