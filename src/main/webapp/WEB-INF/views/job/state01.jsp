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
        <div class="sub_top sub_top_job">
            <div class="inner">
                <div class="sub_top_nav">
                    <span class="home"><img src="<%request.getContextPath();%>/static/img/icon_home_mini.png" alt="홈 아이콘"></span>
                    <span>취업·창업</span>
                    <span>취창업현황</span>
                </div>
                <h2 class="sub_top_title">취창업현황</h2>
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
                    <li class="on"><a href="/job/state01.do">취창업현황</a></li>
                    <li><a href="/job/review.do">취창업성공후기</a></li>
                    <li><a href="/job/community_list.do">커뮤니티</a></li>
                </ul>
            </div>
            <!-- //sidebar -->

            <!-- content -->
            <div id="content" class="sub_state">
                <div class="state_wrap">

                    <!-- sub_tab_btn -->
                    <ul class="sub_tab_btn">
                        <li class="on"><a href="/job/state01.do">창업자 현황</a></li>
                        <li><a href="/job/state02.do">취업자 현황</a></li>
                    </ul>
                    <!-- //sub_tab_btn -->

                    <!-- table_wrap -->
                    <div class="table_wrap">
                        <div class="sub_box_tit">
                            <div class="big">주요 창업자 현황</div>
                        </div>
                        <div class="table_top">
                            <div class="left">총 26명(선박정비 관련)</div>
                            <div class="right">2016 ~ 2021년 기준</div>
                        </div>

                        <!-- table_box -->
                        <div class="table_box">
                            <table style="min-width: 700px;">
                                <colgroup>
                                    <col width="6%">
                                    <col width="14%">
                                    <col width="12%">
                                    <col width="10%">
                                    <col width="12%">
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>구분</th>
                                        <th>회사명</th>
                                        <th>대표자</th>
                                        <th>설립연도</th>
                                        <th>분야</th>
                                        <th>주소</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>청초마리나</td>
                                        <td>정*찬</td>
                                        <td>2011년</td>
                                        <td>서비스업</td>
                                        <td>강원도 속초시 철새길106</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>현마린</td>
                                        <td>김*한</td>
                                        <td>2017년</td>
                                        <td>선박정비</td>
                                        <td>경기도 가평군 설악면 사룡리 612-11 클럽티파니</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>보트샵</td>
                                        <td>나*주</td>
                                        <td>2016년</td>
                                        <td>선박정비</td>
                                        <td>경기도 김포시</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>보트타운</td>
                                        <td>김*조<br>신*철</td>
                                        <td>2018년</td>
                                        <td>선박정비</td>
                                        <td>경기도 김포시 고촌읍 전호리 368-3</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>하이크루즈</td>
                                        <td>이*철</td>
                                        <td>2006년</td>
                                        <td>서비스업</td>
                                        <td>경기도 수원시 권선구 서수원로 437, 302호</td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td>라이프마린</td>
                                        <td>이*희</td>
                                        <td>2017년</td>
                                        <td>선박정비</td>
                                        <td>경기도 안산시 단원구 도일로 60-34, 402동 1202호</td>
                                    </tr>
                                    <tr>
                                        <td>7</td>
                                        <td>하울테크</td>
                                        <td>이*민</td>
                                        <td>2020년</td>
                                        <td>선박수리<br>출장용접</td>
                                        <td>경기도 안산시 상록구 안산천동로8길 3, 102동 301호</td>
                                    </tr>
                                    <tr>
                                        <td>8</td>
                                        <td>헤스본AS<br>(마린공구)</td>
                                        <td>이*호</td>
                                        <td>2005년</td>
                                        <td>선박정비</td>
                                        <td>경기도 용인시 처인구 양지면 중부대로 2254-1, 1층</td>
                                    </tr>
                                    <tr>
                                        <td>9</td>
                                        <td>ULB systems</td>
                                        <td>유정현</td>
                                        <td>2020년</td>
                                        <td>기술서비스</td>
                                        <td>경기도 평택시 서재1길 2-37, 102호</td>
                                    </tr>
                                    <tr>
                                        <td>10</td>
                                        <td>에스마린</td>
                                        <td>조*관</td>
                                        <td>2020년</td>
                                        <td>선박정비</td>
                                        <td>경기도 화성시 서산면 앞실길 24</td>
                                    </tr>
                                    <tr>
                                        <td>11</td>
                                        <td>신원마린</td>
                                        <td>원*빈</td>
                                        <td>2017년</td>
                                        <td>선박정비</td>
                                        <td>경기도 화성시 우정읍 궁평항로 31-14</td>
                                    </tr>
                                    <tr>
                                        <td>12</td>
                                        <td>통영탑마린</td>
                                        <td>김*현</td>
                                        <td>2022년</td>
                                        <td>선박정비</td>
                                        <td>경상남도 통영시 가죽고랑3길 34-11(서호동)</td>
                                    </tr>
                                    <tr>
                                        <td>13</td>
                                        <td>고씨(GoSea)</td>
                                        <td>고*백</td>
                                        <td>2021년</td>
                                        <td>고용 알선업</td>
                                        <td>부산광역시 해운대구 세실로 35, 1203호</td>
                                    </tr>
                                    <tr>
                                        <td>14</td>
                                        <td>혼다마린코리아</td>
                                        <td>정*영</td>
                                        <td>2018년</td>
                                        <td>선박정비</td>
                                        <td>서울 구로구 디지털로34길 27</td>
                                    </tr>
                                    <tr>
                                        <td>15</td>
                                        <td>알파마린</td>
                                        <td>황*영</td>
                                        <td>2018년</td>
                                        <td>선박정비</td>
                                        <td>서울 금천구</td>
                                    </tr>
                                    <tr>
                                        <td>16</td>
                                        <td>블루마린몬스터</td>
                                        <td>임서</td>
                                        <td>2019년</td>
                                        <td>도소매업</td>
                                        <td>울산광역시 복구 중산3길 2, 104호, 105호</td>
                                    </tr>
                                    <tr>
                                        <td>17</td>
                                        <td>파로스마린</td>
                                        <td>이슬기</td>
                                        <td>2021년</td>
                                        <td>제조업</td>
                                        <td>울산광역시 북구 매곡1로 15-1, 4층 432호</td>
                                    </tr>
                                    <tr>
                                        <td>18</td>
                                        <td>오케이마린</td>
                                        <td>배*필</td>
                                        <td>2020년</td>
                                        <td>선박정비</td>
                                        <td>울산광역시 울주군 온양읍 외광리 112-7</td>
                                    </tr>
                                    <tr>
                                        <td>19</td>
                                        <td>향우선박</td>
                                        <td>조*진</td>
                                        <td>2009년</td>
                                        <td>선박정비</td>
                                        <td>인천광역시 서구 건지로95번길 36, 비층 104호</td>
                                    </tr>
                                    <tr>
                                        <td>20</td>
                                        <td>비와이마린</td>
                                        <td>최*현</td>
                                        <td>2020년</td>
                                        <td>선박기계정비</td>
                                        <td>인천광역시 중구 연안부두로 91번길 12, 1층</td>
                                    </tr>
                                    <tr>
                                        <td>21</td>
                                        <td>금일탑마린</td>
                                        <td>임재철</td>
                                        <td>2020년</td>
                                        <td>선박정비</td>
                                        <td>전라남도 완도군 금일읍 도장길 239</td>
                                    </tr>
                                    <tr>
                                        <td>22</td>
                                        <td>OK마린</td>
                                        <td>박형준</td>
                                        <td>2020년</td>
                                        <td>선박정비</td>
                                        <td>전라남도 해남군 화산면 송평로 594</td>
                                    </tr>
                                    <tr>
                                        <td>23</td>
                                        <td>남원마린</td>
                                        <td>원*선</td>
                                        <td>2019년</td>
                                        <td>선박정비</td>
                                        <td>제주특별자치도 서귀포시 남원읍 태위로 957</td>
                                    </tr>
                                    <tr>
                                        <td>24</td>
                                        <td>블루마린</td>
                                        <td>문*석</td>
                                        <td>2021년</td>
                                        <td>선박정비</td>
                                        <td>제주특별자치도 제주시 연수로1길 27, 지하1층</td>
                                    </tr>
                                    <tr>
                                        <td>25</td>
                                        <td>안면도마린</td>
                                        <td>김*기</td>
                                        <td>2017년</td>
                                        <td>선박정비</td>
                                        <td>충청남도 태안군 안면읍 밧개길 16</td>
                                    </tr>
                                    <tr>
                                        <td>26</td>
                                        <td>마린파크</td>
                                        <td>김*권</td>
                                        <td>2020년</td>
                                        <td>선박정비</td>
                                        <td>충청북도 청주시 청원구 상당로 237 1층</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- //table_box -->
                    </div>
                    <!-- //table_wrap -->

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

</body>
</html>