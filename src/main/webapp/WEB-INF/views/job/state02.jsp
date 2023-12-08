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
                        <li><a href="/job/state01.do">창업자 현황</a></li>
                        <li class="on"><a href="/job/state02.do">취업자 현황</a></li>
                    </ul>
                    <!-- //sub_tab_btn -->

                    <!-- table_wrap -->
                    <div class="table_wrap">
                        <div class="sub_box_tit">
                            <div class="big">주요 취업자 현황</div>
                        </div>
                        <div class="table_top">
                            <div class="left">총 58명(관련분야 종사자)</div>
                            <div class="right">2016 ~ 2021년 기준</div>
                        </div>

                        <!-- table_box -->
                        <div class="table_box">
                            <table style="min-width: 450px;">
                                <colgroup>
                                    <col width="8%">
                                    <col width="40%">
                                    <col width="20%">
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>구분</th>
                                        <th>회사명</th>
                                        <th>분야</th>
                                        <th>주소</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>화창상사</td>
                                        <td>선박정비</td>
                                        <td>경기도 과천시</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>선우엔지니어링</td>
                                        <td>선박정비</td>
                                        <td>부산시 강서구</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>한성마린기계</td>
                                        <td>선박정비</td>
                                        <td>제주시 한림읍</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>쌍용디젤</td>
                                        <td>선박정비</td>
                                        <td>서울시</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>신대원마린</td>
                                        <td>선박정비</td>
                                        <td>충청북도 청원군</td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td>한진기공</td>
                                        <td>선박정비</td>
                                        <td>서울시 용산구</td>
                                    </tr>
                                    <tr>
                                        <td>7</td>
                                        <td>부산선외기</td>
                                        <td>선박정비</td>
                                        <td>경남 창원시</td>
                                    </tr>
                                    <tr>
                                        <td>8</td>
                                        <td>동진마린레저</td>
                                        <td>선박정비</td>
                                        <td>전남 목포시</td>
                                    </tr>
                                    <tr>
                                        <td>9</td>
                                        <td>볼보벤타 군산점</td>
                                        <td>선박정비</td>
                                        <td>전북 군산시</td>
                                    </tr>
                                    <tr>
                                        <td>10</td>
                                        <td>평택당진항</td>
                                        <td>시설관리</td>
                                        <td>경기도 평택, 충남 당진</td>
                                    </tr>
                                    <tr>
                                        <td>11</td>
                                        <td>웨스트마린</td>
                                        <td>선박정비</td>
                                        <td>경기도 안산시</td>
                                    </tr>
                                    <tr>
                                        <td>12</td>
                                        <td>왕산마리나</td>
                                        <td>선박정비</td>
                                        <td>인천시 중구</td>
                                    </tr>
                                    <tr>
                                        <td>13</td>
                                        <td>엘지엠</td>
                                        <td>선박정비</td>
                                        <td>경기도 수원시</td>
                                    </tr>
                                    <tr>
                                        <td>14</td>
                                        <td>해광선박</td>
                                        <td>선박정비</td>
                                        <td>부산시 영도구</td>
                                    </tr>
                                    <tr>
                                        <td>15</td>
                                        <td>이레마린</td>
                                        <td>선박정비</td>
                                        <td>경기도 화성시</td>
                                    </tr>
                                    <tr>
                                        <td>16</td>
                                        <td>충주호 관광선</td>
                                        <td>기관장</td>
                                        <td>충북 충주시</td>
                                    </tr>
                                    <tr>
                                        <td>17</td>
                                        <td>스카니아</td>
                                        <td>선박정비</td>
                                        <td>서울시 강남구</td>
                                    </tr>
                                    <tr>
                                        <td>18</td>
                                        <td>보트맥스</td>
                                        <td>선박정비</td>
                                        <td>경기도 김포시</td>
                                    </tr>
                                    <tr>
                                        <td>19</td>
                                        <td>한국해양컨텐츠개발</td>
                                        <td>선박정비</td>
                                        <td>부산시 영도구</td>
                                    </tr>
                                    <tr>
                                        <td>20</td>
                                        <td>부산 KCPM</td>
                                        <td>선박정비, 운항</td>
                                        <td>부산시 남구</td>
                                    </tr>
                                    <tr>
                                        <td>21</td>
                                        <td>한엔진테크</td>
                                        <td>선박정비</td>
                                        <td>충남 보령시</td>
                                    </tr>
                                    <tr>
                                        <td>22</td>
                                        <td>안산시요트협회</td>
                                        <td>운항</td>
                                        <td>경기도 안산시</td>
                                    </tr>
                                    <tr>
                                        <td>23</td>
                                        <td>현대상공모터스</td>
                                        <td>선박정비</td>
                                        <td>경기도 하남시</td>
                                    </tr>
                                    <tr>
                                        <td>24</td>
                                        <td>군산 볼보펜타</td>
                                        <td>선박정비</td>
                                        <td>전북 군산시</td>
                                    </tr>
                                    <tr>
                                        <td>25</td>
                                        <td>영진기계</td>
                                        <td>선박정비</td>
                                        <td>경남 양산시</td>
                                    </tr>
                                    <tr>
                                        <td>26</td>
                                        <td>보트코리아</td>
                                        <td>선박정비</td>
                                        <td>경기도 남양주시</td>
                                    </tr>
                                    <tr>
                                        <td>27</td>
                                        <td>성진마린</td>
                                        <td>선박정비</td>
                                        <td>경기도 화성시</td>
                                    </tr>
                                    <tr>
                                        <td>28</td>
                                        <td>제주전기기계</td>
                                        <td>선박정비</td>
                                        <td>제주시</td>
                                    </tr>
                                    <tr>
                                        <td>29</td>
                                        <td>마린월드</td>
                                        <td>선박정비</td>
                                        <td>경기도 안성시</td>
                                    </tr>
                                    <tr>
                                        <td>30</td>
                                        <td>블루마린다이브리조트</td>
                                        <td>관광업</td>
                                        <td>강원도 양양군</td>
                                    </tr>
                                    <tr>
                                        <td>31</td>
                                        <td>(주) 유엘티</td>
                                        <td>제조업</td>
                                        <td>경기도 안산시</td>
                                    </tr>
                                    <tr>
                                        <td>32</td>
                                        <td>미상</td>
                                        <td>선박정비</td>
                                        <td>광주시 광산구</td>
                                    </tr>
                                    <tr>
                                        <td>33</td>
                                        <td>코스탈파워</td>
                                        <td>제조업</td>
                                        <td>서울시 구로구</td>
                                    </tr>
                                    <tr>
                                        <td>34</td>
                                        <td>스피드마린</td>
                                        <td>선박정비</td>
                                        <td>경남 창원시</td>
                                    </tr>
                                    <tr>
                                        <td>35</td>
                                        <td>성우디젤</td>
                                        <td>선박정비</td>
                                        <td>인천시 중구</td>
                                    </tr>
                                    <tr>
                                        <td>36</td>
                                        <td>여울마린</td>
                                        <td>선박정비</td>
                                        <td>부산 영도구</td>
                                    </tr>
                                    <tr>
                                        <td>37</td>
                                        <td>명진기계</td>
                                        <td>선박정비</td>
                                        <td>전남 여수시</td>
                                    </tr>
                                    <tr>
                                        <td>38</td>
                                        <td>현대상선</td>
                                        <td>운항</td>
                                        <td>인천시 중구</td>
                                    </tr>
                                    <tr>
                                        <td>39</td>
                                        <td>에즈금융서비스</td>
                                        <td>금융업</td>
                                        <td>서울시 영등포구</td>
                                    </tr>
                                    <tr>
                                        <td>40</td>
                                        <td>서울보트</td>
                                        <td>선박정비</td>
                                        <td>경기도 화성시</td>
                                    </tr>
                                    <tr>
                                        <td>41</td>
                                        <td>수자원환견산업진흥㈜</td>
                                        <td>교육지원</td>
                                        <td>경기도 김포시</td>
                                    </tr>
                                    <tr>
                                        <td>42</td>
                                        <td>비엔와이코리아</td>
                                        <td>선박정비</td>
                                        <td>인천시 남동구</td>
                                    </tr>
                                    <tr>
                                        <td>43</td>
                                        <td>백송기업</td>
                                        <td>운송, 정비</td>
                                        <td>인천시 동구</td>
                                    </tr>
                                    <tr>
                                        <td>44</td>
                                        <td>현대요트</td>
                                        <td>판매업</td>
                                        <td>울산시 울주군</td>
                                    </tr>
                                    <tr>
                                        <td>45</td>
                                        <td>에스에이치마린</td>
                                        <td>선박정비</td>
                                        <td>인천시 연수구</td>
                                    </tr>
                                    <tr>
                                        <td>46</td>
                                        <td>삼흥선외기</td>
                                        <td>선박정비</td>
                                        <td>충남 서천군</td>
                                    </tr>
                                    <tr>
                                        <td>47</td>
                                        <td>화인세라텍</td>
                                        <td>제조업</td>
                                        <td>경기도 화성시</td>
                                    </tr>
                                    <tr>
                                        <td>48</td>
                                        <td>두원이엔지</td>
                                        <td>제조업</td>
                                        <td>충남 당진시</td>
                                    </tr>
                                    <tr>
                                        <td>49</td>
                                        <td>현재씨즈올</td>
                                        <td>제조업</td>
                                        <td>경기도 화성시</td>
                                    </tr>
                                    <tr>
                                        <td>50</td>
                                        <td>인하공업전문대학 산업기술연구소</td>
                                        <td>연구, 리서치</td>
                                        <td>인천시 미추홀구</td>
                                    </tr>
                                    <tr>
                                        <td>51</td>
                                        <td>르노삼성 서비스센터</td>
                                        <td>자동차정비</td>
                                        <td>경기도 수원시</td>
                                    </tr>
                                    <tr>
                                        <td>52</td>
                                        <td>썸밑엔지니어링</td>
                                        <td>선박정비</td>
                                        <td>인천시 중구</td>
                                    </tr>
                                    <tr>
                                        <td>53</td>
                                        <td>마린랜드</td>
                                        <td>선박정비</td>
                                        <td>충남 천안시</td>
                                    </tr>
                                    <tr>
                                        <td>54</td>
                                        <td>스타보트</td>
                                        <td>선박정비</td>
                                        <td>경기도 화성시</td>
                                    </tr>
                                    <tr>
                                        <td>55</td>
                                        <td>에스텍마린</td>
                                        <td>선박정비</td>
                                        <td>경기도 구리시</td>
                                    </tr>
                                    <tr>
                                        <td>56</td>
                                        <td>삼덕FRP조선</td>
                                        <td>선박정비</td>
                                        <td>경남 창원시</td>
                                    </tr>
                                    <tr>
                                        <td>57</td>
                                        <td>케이엔지니어링</td>
                                        <td>선박정비</td>
                                        <td>서울시 송파구</td>
                                    </tr>
                                    <tr>
                                        <td>58</td>
                                        <td>(주)에스엔에스엔진</td>
                                        <td>내연기관 제조</td>
                                        <td>전남 여수시</td>
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