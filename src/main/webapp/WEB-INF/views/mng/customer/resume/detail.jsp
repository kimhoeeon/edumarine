<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">

<head>
    <title>경기해양레저 인력양성센터</title>

    <link href="/css/reset.css" rel="stylesheet">
    <link href="/css/font.css" rel="stylesheet">
    <link href="/css/style.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
    <link href="/css/responsive.css" rel="stylesheet">

    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="/js/jquery-migrate-3.3.0.js"></script>
    <script src="/js/jquery.cookie.min.js"></script>
    <script src="/js/mngMain.js?ver=<%=System.currentTimeMillis()%>"></script>

    <style>
        body {
            margin: 0;
            padding: 0;
        }

        * {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        .page {
            width: 21cm;
            min-height: 29cm;
            padding: 1cm 2cm;
            margin: 0 auto;
            background: #eee;
        }

        .print_area {
            border: 2px red solid;
            background: #fff;
            height: 277mm;
        }

        @page {
            size: A4;
            margin: 0;
        }

        @media print {
            html, body {
                width: 210mm;
                height: 290mm;
                margin: 0 !important;
                padding: 0 !important;
                /*overflow: hidden;*/
            }
            .page {
                margin: 0;
                border: initial;
                width: initial;
                min-height: initial;
                box-shadow: initial;
                background: initial;
            }
        }



        /*div { border: 1px solid black;}*/
    </style>
</head>
<body>
<c:if test="${empty resumeList}">


    <div class="print_btn_area" style="width: 21cm; height: 45px; margin: 15px auto;">
        <button onclick="f_resume_detail_print()" style="float: right; width: 100px;">출력</button>
    </div>

    <!-- print -->
    <div class="page">
        <div class="print_area">
            <!-- content -->
            <div id="content">
                <div class="form_wrap">

                    <div class="form_box" style="margin-bottom: unset">
                        <div class="form_tit">
                            <div class="big">나의 이력서</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun"><p>성명</p></div>
                                <div class="naeyong">
                                    <div>(국) ${info.nameKo}<br>(영) ${info.nameEn}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>연락처</p></div>
                                <div class="naeyong">
                                    <div>${info.phone}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>이메일 주소</p></div>
                                <div class="naeyong">
                                    <div>${info.email}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>생년월일</p></div>
                                <div class="naeyong">
                                    <div>${info.birthYear}년 ${info.birthMonth}월 ${info.birthDay}일</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>성별</p></div>
                                <div class="naeyong">
                                    <div>${info.sex}</div>
                                </div>
                            </li>
                            <%--<li>
                                <div class="gubun"><p>연령</p></div>
                                <div class="naeyong">
                                    <div>만 ${info.age} 세</div>
                                </div>
                            </li>--%>
                            <li>
                                <div class="gubun"><p>상반신 사진</p></div>
                                <div class="naeyong">
                                    <c:set var="bodyPhotoFileSrc" value="${fn:replace(bodyPhotoFile.fullFilePath, '/usr/local/tomcat/webapps', '/../../../..')}" />
                                    <img src="${bodyPhotoFileSrc}" style="max-height: 150px;"/>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>주소</p></div>
                                <div class="naeyong">
                                    <div>${info.address} , ${info.addressDetail}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>상의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div>${info.topClothesSize}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>하의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div>${info.bottomClothesSize}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>안전화 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div>${info.shoesSize}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>참여 경로</p></div>
                                <div class="naeyong">
                                    <div>${info.participationPath}</div>
                                </div>
                            </li>
                            <li style="height: 130px;">
                                <div class="gubun"><p>비고</p></div>
                                <div class="naeyong">
                                </div>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
            <!-- //content -->
        </div>
    </div>
    <!-- //print -->

</c:if>
<c:if test="${not empty resumeList}">
    <c:forEach var="info" items="${resumeList}" begin="0" end="${resumeList.size()}" step="1" varStatus="status">

    <c:if test="${status.index eq 0}">
        <div class="print_btn_area" style="width: 21cm; height: 45px; margin: 15px auto;">
            <button onclick="f_multi_resume_detail_print()" style="float: right; width: 150px;">전체 출력</button>
        </div>
    </c:if>

    <!-- print -->
    <div class="page">
        <div class="print_area">
            <!-- content -->
            <div id="content">
                <div class="form_wrap">

                    <div class="form_box" style="margin-bottom: unset">
                        <div class="form_tit">
                            <div class="big">나의 이력서</div>
                        </div>
                        <ul class="form_list">
                            <li>
                                <div class="gubun"><p>성명</p></div>
                                <div class="naeyong">
                                    <div>(국) ${info.nameKo}<br>(영) ${info.nameEn}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>연락처</p></div>
                                <div class="naeyong">
                                    <div>${info.phone}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>이메일 주소</p></div>
                                <div class="naeyong">
                                    <div>${info.email}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>생년월일</p></div>
                                <div class="naeyong">
                                    <div>${info.birthYear}년 ${info.birthMonth}월 ${info.birthDay}일</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>성별</p></div>
                                <div class="naeyong">
                                    <div>${info.sex}</div>
                                </div>
                            </li>
                            <%--<li>
                                <div class="gubun"><p>연령</p></div>
                                <div class="naeyong">
                                    <div>만 ${info.age} 세</div>
                                </div>
                            </li>--%>
                            <li>
                                <div class="gubun"><p>상반신 사진</p></div>
                                <div class="naeyong">
                                    <c:set var="bodyPhotoFileSrc" value="${fn:replace(info.bodyPhotoFileSrc, '/usr/local/tomcat/webapps', '/../../../..')}" />
                                    <img src="${bodyPhotoFileSrc}" style="max-height: 150px;"/>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>주소</p></div>
                                <div class="naeyong">
                                    <div>${info.address} , ${info.addressDetail}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>상의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div>${info.topClothesSize}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>하의 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div>${info.bottomClothesSize}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>안전화 사이즈<span>(남여공용)</span></p></div>
                                <div class="naeyong">
                                    <div>${info.shoesSize}</div>
                                </div>
                            </li>
                            <li>
                                <div class="gubun"><p>참여 경로</p></div>
                                <div class="naeyong">
                                    <div>${info.participationPath}</div>
                                </div>
                            </li>
                            <li style="height: 130px;">
                                <div class="gubun"><p>비고</p></div>
                                <div class="naeyong">
                                </div>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
            <!-- //content -->
        </div>

        <%--<c:if test="${(status.index+1) ne resumeList.size()}">
            <div style="page-break-before:always;"></div><br style="height:0; line-height:0">
        </c:if>--%>
    </div>
    <!-- //print -->

    </c:forEach>
</c:if>
</body>
</html>