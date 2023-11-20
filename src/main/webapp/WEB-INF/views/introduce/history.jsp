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
    <title>스마트산업진흥협회</title>

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

<div id="container">

    <!-- sub_top -->
    <div class="sub_top sub_top_intd">
        <div class="inner">
            <div class="sub_top_txt">
                <div class="sub_top_nav">
                    <span><img src="<%request.getContextPath();%>/static/img/icon_home.png"></span>
                    <span>협회소개</span>
                    <span>연혁</span>
                </div>
                <div class="sub_top_tit">연혁</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">연혁</div>
                <ul class="tabOptSel">
                    <li><a href="/introduce/greeting.do">인사말</a></li>
                    <li class="active"><a href="/introduce/history.do">연혁</a></li>
                    <li><a href="/introduce/vision.do">비전</a></li>
                    <li><a href="/introduce/business.do">주요사업</a></li>
                    <li><a href="/introduce/way.do">오시는길</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //sub_top -->

    <!-- history -->
    <div class="history padding_tb">
        <div class="inner">
            <div class="hist_area">
                <div class="hist_box">
                    <!-- 2023 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2023</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">07</p>
                                <p>영국 리버풀 Oxygen Summit(우크라이나 재건사업 콘퍼런스) 참여</p>
                                <p>우크라이나 정부 & SIPA 우크라이나 재건사업 콘퍼런스 개최 협의</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>전동칫솔 100세트 한국교회봉산단 후원</p>
                                <p>필리핀무역투자진흥국 SIPA 회원사 수출사업 추진 협의</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Asian Square & 사우다란따 & SIPA 인도네시아 수출사업 MOU</p>
                                <p>JOY LOW GROUP PLCC 미국진출 기업인 이주 지원 협의</p>
                                <p>제이콥시스템 & JETT FORCE 필리핀 전기총 수출사업 MOU</p>
                                <p>시스원 & JETT FORCE 필리핀 보안사업 MOU </p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>법무법인 금성 & SIPA 인도네시아 수출사업 MOU</p>
                                <p>미국 MK LAW 미국진출 기업 법률 자문 및 계약 연결 협의</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>PLDT CLARKTELL & PLDT & 시스원 방화벽 사업 MOU</p>
                                <p>Clark Investors and Locators Association (CILA) & SIPA MOU</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>제14차 정기총회 개최</p>
                                <p>필리핀 정보통신부 장관 필리핀 5개 공항 출입통제시스템 구축 협의</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2023 -->

                    <!-- 2022 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2022</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">11</p>
                                <p>필리핀 루손 경제사절단 투자유치설명회 참여</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>LTE 통신장비 회원사 PLDT 1차 협력사 결정</p>
                                <p>제4회 재생에너지의날 기념식 참여</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>PLDT 구매팀 증강현실 2차 시연 결정</p>
                                <p>필리핀 PLDT SMART 방문 및 증강현실 시연</p>
                                <p>필리핀 JETT FORCE 방문 및 공동사업 추진 협의</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>SK에코플랜트 산단공 구조고도화사업 추진 컨소시엄 협의</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>제42회 황금촬영상영화제 본 협회 지원기관 협의</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>서울디지털운동장 인수개발 컨소시엄 구성</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>대한항공 왕산마리나 부지 인수개발 컨소시엄 구성</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>필리핀 대기업 PLDT 방문 및 휴대용 통신장비 시연회</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>제13차 정기총회 개최</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>인도네시아, 일본, 중국 가정용 전자제품 수출 추진</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2022 -->

                    <!-- 2021 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2021</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>중국 왕푸징그룹 가정용품 분야 수출기업 추천 공식 제안</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>필리핀 세부시 유캐스트 LTE 통신장비 매입 결정</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>필리핀 콘셉트시 스마트시티 구축사업 MOU</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>서울시 강동구청 태양광 구축사업 추진 협의</p>
                                <p>미국 오세올라카운티주 네오시티 조성사업 컨퍼런스 참여</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>서울시 강동구청, 서대문구청 스마트폴 구축 제의</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>서울시 종로구청 스마트폴 광장시장 구축 협의</p>
                                <p>부천산업진흥원 부천시 스마트시티 사업협력 간담회</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>필리핀투자청 정보통신장비 ODA 추진 간담회</p>
                                <p>수출입은행 필리핀 EDCF 추진 간담회</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>전국 스마트폴 구축사업 추진 컨소시엄 구성</p>
                                <p>서울시 강동구청 AI 구축사업 추진 컨소시엄 구성</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>제12차 정기총회 개최</p>
                                <p>수도권 개발사업 추진 협의체 구성</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2021 -->

                    <!-- 2020 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2020</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>에디오피아 교육부 차관 ICT사업 추진 협의</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>NIPA ‘ICT 규제 샌드박스 제도 개선’ 위원회 참여</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>필리핀 대기업 PLDT 통신장비 공급사업 추진</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>과학기술커뮤니티협의회(KASC) 위원회 참여</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>미얀마 양곤대학교 EDCF 추진 협의</p>
                                <p>폴란드 정부 정보화&amp;철도현대화사업 국내기업 참여 추진</p>
                                <p>충청남도 서천군청 간담회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2020 -->

                    <!-- 2019 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2019</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">11</p>
                                <p>충북대학교 창업 심사위원회 참여</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>UAE 아부다비 사업설명회 참여</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>부천산업진흥원 부천시 스마트시티 사업협력 간담회</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>서울특별시의정회 공동사업 추진 협의</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>삼성C&amp;P 투자유치 설명회</p>
                                <p>프랑스 에어버스 3D익스피리언스 초청 간담회</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>중국 칭화대학교 EMBA 초청 간담회</p>
                                <p>중국 기업가협회 및 국제개발연구소 공동사업 추진 협의</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>인도 뉴델리 사업진출 세미나 참여</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>중국 IGE 방문 및 수소전기차 안전장비 수출 상담회</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>제10차 정기총회 개최</p>
                                <p>중국 공업정보화부 CCID, 아시아링크그룹 방문</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>마리나투자클럽 투자유치설명회 심사위원회 참여</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2019 -->

                    <!-- 2018 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2018</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">11</p>
                                <p>중국 북경시 기업 간담회</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>서울테크노파크 공동사업 추진 간담회</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>경기중소기업연합회 광명지부 창립식 참여</p>
                                <p>필리핀 JETT FORCE&amp;STG 업무협약식</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>국가사이버안전연합회&amp;IIPC 업무협약식</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>GNMP 연합행사 참여</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>KOTRA 필리핀 투자유치설명회 마닐라 행사 참여</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>필리핀, 탄자니아 EDCF 추진 간담회</p>
                                <p>인도&amp;탄자니아 Electric Power Global Energy Networking Event</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>충청북도 금산군청 추진사업 참여 간담회</p>
                                <p>제9차 정기총회 개최</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>충청남도 서천군청 투자유치과 간담회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2018 -->

                    <!-- 2017 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2017</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">10</p>
                                <p>태국 수출기업 상담회</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>한국발명진흥회(KIPA) 위원회 참여</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>필리핀 BCDA, 고든 상원위원실, PLDT, JETT FORCE 방문</p>
                                <p>CLARK WATER, CLARK INTERNATIONAL AIRPORT 방문</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>충청북도 금산군청 추진사업 참여 간담회</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>한성대학교 &amp; 인도 뉴델리 업무협약식 참여</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>농림수산식품부 시범사업 추진 간담회</p>
                                <p>광명의료복합클러스터 개발사업 컨소시엄 구성</p>
                                <p>링컨협회 창립총회 참여</p>
                                <p>산업통상자원부 스마트산업단지 추진 간담회</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>단국대학교 산학협력 투자설명회 공동 개최</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>제8차 정기총회 개최</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>단국대학교 경기지역 산학협력 간담회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2017 -->

                    <!-- 2016 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2016</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">11</p>
                                <p>유한대학교 창업 강연회</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>상생포럼 창립식 참여</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>국가사이버안전연합회 준비위원회 발족</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>인천시 남구 스마트빌딩 추진 간담회</p>
                                <p>세계화인기업가협회 업무협약식(MOU 체결)</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>한국산업단지공단 대구경북지사 스마트산업단지 추진 간담회</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>전라북도 문화체육관광부 신규사업 추진 간담회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2016 -->

                    <!-- 2015 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2015</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">11</p>
                                <p>2015 채용예정자과정 개강</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>베트남 꽝아이 성 정부 관계자 참여 세미나 (베트남 Quang Ngai)</p>
                                <p>2015 씨윗컨퍼런스 참여</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>세계화인기업가협회 초청 간담회 참여</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>미래창조과학부 제2판교테크노밸리 공공부지 조성방안 위원회 참여</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>제6차 정기총회</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>중국 천진빈해고신기술산업개발구 사업교류 방문</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2015 -->

                    <!-- 2014 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2014</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>국민대학교 링크사업단 페스티발 참여</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>중국 장사국가고신산업개발구 사업교류 방문</p>
                                <p>2014 채용예정자과정 개강</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>제11회 미국 뉴욕 씨윗컨퍼런스 참여</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>천진시 중환그룹 회장단 방문(국내 대기업 계열사 인수 협의)</p>
                                <p>중국 칭화대학교 EMBA과정 19개 기업 방문</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>중국 천진빈해고신기술산업개발구 사업교류 방문</p>
                                <p>중국 중환그룹 무선충전기 투자지원 사업계약</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>한국산업단지공단 대경권 중국진출 설명회</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>중국 천진빈해고신산업개발구 사업교류 협약(MOU)</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>중국 운남성 농업IT 시장조사단 파견</p>
                                <p>제5차 정기총회 (제3기 회장 :㈜미성포리테크김종달 회장)</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>중국 천진빈해고신산업개발구 당서기 일행 방문</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2014 -->

                    <!-- 2013 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2013</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>중국과학기술부 초청 중국스마트시티 시장조사단 파견</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>채용예정자과정 개강</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>제10회 미국 뉴욕 씨윗컨퍼런스 참여</p>
                                <p>산업통상자원부 스마트그리드 확산사업 예타사업자 협회 컨소시엄 선정</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>중국 스마트시티 포럼 한국 기타대표 협약식 참여</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>제4차 정기총회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2013 -->

                    <!-- 2012 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2012</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>한국유에코시티협회 -&gt;한국유에코산업협회 협회명칭 변경</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>씨윗국제콘퍼런스 및 엑스포(CEWIT2012) 행사지원</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>2012 한국전자전 (KES) 서울디지털산업단지 부스 공동주관</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>미국 MBDA 행사 참여</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>제2차 ETRI IT+제조 광역기술장터 주최</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>제3차 정기총회(제2기 회장 : YPP㈜ 백종만 회장)</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2012 -->

                    <!-- 2011 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2011</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">11</p>
                                <p>지식경제부 K-MEG 채용예정자과정 위탁수행</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>서울디지털산업단지 정부 R&amp;D사업 기술수요조사 설명회</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>제1차 ETRI IT+제조 광역기술장터 행사지원</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>지식경제부 K-MEG 실증사업 서울디지털산업단지 추진</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>제2차 정기총회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2011 -->

                    <!-- 2010 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2010</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>2010 하반기 임시총회</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>중국 연길 IT투자 박람회 참여</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>제1차 정기총회(제1기 회장 :YPP㈜ 백종만 회장)</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>서울시 구로세무서 비영리법인 고유번호증 인가</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>지식경제부 사단법인 설립허가</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2010 -->

                    <!-- 2009 -->
                    <div class="box">
                        <div class="year">
                            <div class="txt">2009</div><div class="line"><span></span></div>
                        </div>
                        <ul class="list">
                            <li>
                                <p class="month">12</p>
                                <p>한국유에코시티협회 창립총회</p>
                            </li>
                        </ul>
                    </div>
                    <!-- //2010 -->
                </div>

            </div>
        </div>
    </div>
    <!-- //history -->

</div>

<c:import url="../footer.jsp" charEncoding="UTF-8"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.5/dist/sweetalert2.all.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery-1.9.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.cookie.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/jquery.number.min.js"></script>
<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
<script src="<%request.getContextPath();%>/static/js/script.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="<%request.getContextPath();%>/static/js/swiper.js"></script>
<script src="<%request.getContextPath();%>/static/js/main.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>