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
                    <span>Introduction</span>
                    <span>History</span>
                </div>
                <div class="sub_top_tit">History</div>
            </div>
            <div class="sub_top_tab">
                <div class="tabOptAct">History</div>
                <ul class="tabOptSel">
                    <li><a href="/eng/introduce/greeting.do">Greeting</a></li>
                    <li class="active"><a href="/eng/introduce/history.do">History</a></li>
                    <li><a href="/eng/introduce/vision.do">Vision</a></li>
                    <li><a href="/eng/introduce/business.do">Business</a></li>
                    <li><a href="/eng/introduce/way.do">Location</a></li>
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
                                <p>Liverpool, England, participated in the Oxygen Summit (Ukraine Reconstruction Project Conference)</p>
                                <p>Ukrainian government & SIPA to hold conference on reconstruction projects in Ukraine</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>100 sets of electric toothbrushes sponsored by the Korean Church Bongsan Group</p>
                                <p>Philippine Trade and Investment Promotion Agency SIPA member company's export business promotion consultation</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Asian Square & Saudi Arabia & SIPA Indonesia Export Business MOU</p>
                                <p>JOY LOW GROUP PLCC Consultation on Migrant Support for Companies Entering the U.S</p>
                                <p>JACOB SYSTEM & JETT FORCE Philippines Electricity General Export Business MOU</p>
                                <p>SYSONE & JETT FORCE Philippines Security Business MOU</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Kumsung & SIPA Indonesia Export Business MOU</p>
                                <p>Consultation on legal advice and contract connection for companies entering the U.S. in MK LAW, U.S</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>PLDT CLARKTELL & PLDT & SYSONE Firewall Business MOU</p>
                                <p>Clark Investors and Locators Association (CILA) & SIPA MOU</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>The 14th Annual General Meeting</p>
                                <p>Philippine Minister of Information and Communication Consultation on the Establishment of Access Control System at 5 airports in the Philippines</p>
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
                                <p>Philippine Luzon Economic Mission Participates in Investment Attraction Briefing</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>LTE communication equipment member company PLDT 1st partner company decided</p>
                                <p>Participation in the 4th Renewable Energy Day Ceremony</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>The PLDT purchasing team decided on the second demonstration of augmented reality</p>
                                <p>Visit to PLDT SMART in the Philippines and demonstrate augmented reality</p>
                                <p>Visit to JETT FORCE in the Philippines and consultation on joint projects</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>Consultation on consortium to promote SK Eco Plant Industrial Complex Structural Advancement Project</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>The 42nd Golden Film Festival, the main association support organization, was consulted</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Seoul Digital Stadium Acquisition and Development Consortium Formed</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Korean Air's Wangsan Marina Site Acquisition and Development Consortium Formed</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Philippine conglomerate PLDT visits and demonstrates portable communication equipment</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>The 13th Annual General Meeting</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>Indonesia, Japan push to export home electronics to China</p>
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
                                <p>China's Wangfujing Group officially proposes to recommend exporters in the household goods sector</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>Cebu City, Philippines, decided to purchase Ucast LTE communication equipment</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>MOU for smart city construction project in concept city of Philippines</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>Seoul Gangdong-gu Office Consultation on Solar Energy Construction Project</p>
                                <p>Participated in the Neo City Construction Project Conference in Oseola County, USA</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Seoul's Gangdong-gu Office, Seodaemun-gu Office, Proposed Smart Pole Construction</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Seoul Jongno-gu Office Smart Pole Plaza Market Construction Consultation</p>
                                <p>Bucheon Industrial Promotion Agency Bucheon Smart City Business Cooperation Meeting</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Philippine Investment Agency ODA Promotion Meeting</p>
                                <p>Export-Import Bank of Korea EDCF Promotion Meeting in the Philippines</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>Forming a consortium to promote the nation's smart pole construction project</p>
                                <p>The Gangdong-gu Office in Seoul formed a consortium to promote AI construction projects</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>The 12th Annual General Meeting</p>
                                <p>Organization of Consultative Body for the Promotion of Development Projects in Seoul Metropolitan Area</p>
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
                                <p>Deputy Minister of Education of Ethiopia Consultation on ICT Project Promotion</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>NIPA Participates in the 'Improvement of ICT Regulatory Sandbox System' Committee</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>Philippine conglomerate PLDT telecommunication equipment supply business promoted</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Participation in the Science and Technology Community Council (KASC) Committee</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>Consultation on the Promotion of EDCF at Yangon University in Myanmar</p>
                                <p>The Polish government promotes participation in informatization and railway modernization projects by domestic companies</p>
                                <p>Meeting of Seocheon-gun Office in Chungcheongnam-do</p>
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
                                <p>Participated in Chungbuk National University Start-up Review Committee</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>UAE Participates in Abu Dhabi Business Briefing</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>Bucheon Industrial Promotion Agency Bucheon Smart City Business Cooperation Meeting</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>Consultation on the Promotion of Joint Projects of the Seoul Metropolitan Council</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Samsung C&P Investment Attraction Briefing Session</p>
                                <p>French Airbus 3D Experience Invitation Meeting</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>EMBA Invitation Meeting at Tsinghua University, China</p>
                                <p>China Entrepreneurs Association and International Development Institute Joint Project Promotion Agreement</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Participated in a seminar on business advancement in New Delhi, India</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>Visit to China's IGE and Consultation on Export of Hydrogen Electric Vehicle Safety Equipment</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>The 10th Annual General Meeting</p>
                                <p>China's Ministry of Industry and Information Technology CCID visits Asia Link Group</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>Marina Investment Club Participates in the Investment Attraction Briefing Review Committee</p>
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
                                <p>Business Conference in Beijing, China</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>Seoul Techno Park Joint Project Promotion Meeting</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>Participating in the opening ceremony of the Gwangmyeong branch of the Gyeonggi Small and Medium Business Association</p>
                                <p>Philippines JETT FORCE & STG Business Agreement Ceremony</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>National Cyber Security Association & IIPC Business Agreement Ceremony</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Participation in the GNMP Joint Event</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>KOTRA participated in Manila event at the Philippines investment attraction briefing</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>Philippines, Tanzania EDCF Promotion Meeting</p>
                                <p>India, Tanzania Electric Power Global Energy Networking Event</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>Meeting on the Promotion of Geumsan-gun Office in Chungcheongbuk-do</p>
                                <p>The 9th Annual General Meeting</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>Meeting on Investment Attraction of Seocheon-gun Office, Chungcheongnam-do</p>
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
                                <p>Consultation Meeting on Export Companies in Thailand</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>Participation in the Korea Invention Promotion Association (KIPA) Committee</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>Philippine BCDA, Gordon visit Senate office, PLDT, JETT FORCE</p>
                                <p>Visit CLARK WATER, CLARK INTERNATIONAL AIRPORT</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Meeting on the Promotion of Geumsan-gun Office in Chungcheongbuk-do</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Hansung University & India Participate in Business Agreement Ceremony</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Meeting on the Promotion of Pilot Projects by the Ministry of Agriculture, Forestry and Fisheries</p>
                                <p>Gwangmyeong Medical Complex Cluster Development Project Consortium Formed</p>
                                <p>Participation in the Lincoln Association's inaugural meeting</p>
                                <p>Ministry of Trade, Industry and Energy Smart Industrial Complex Promotion Meeting</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>Dankook University Co-hosted Industry-Academic Cooperation Investment Briefing</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>The 8th Annual General Meeting</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>Dankook University Industry-Academic Cooperation Meeting in Gyeonggi Province</p>
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
                                <p>Yuhan University Start-up Lecture</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>Participation in the inaugural ceremony of the Win-Win Forum</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>National Cyber Security Association Preparatory Committee Launches</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>Smart Building Promotion Meeting in Nam-gu, Incheon</p>
                                <p>Global Fine Entrepreneurs Association Business Agreement Ceremony (MOU)</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Korea Industrial Complex Corporation Daegu-Gyeongbuk Branch Smart Industrial Complex Promotion Meeting</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Meeting on the Promotion of New Projects by the Ministry of Culture, Sports and Tourism of Jeollabuk-do</p>
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
                                <p>Opening of the 2015 recruitment program</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>Seminar on Participation of Government Officials in Quang Ai Province, Vietnam (Quang Ngai, Vietnam)</p>
                                <p>Participated in the 2015 CEWIT Conference</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Participation in the Invitational Meeting of the World Chinese Entrepreneurs Association</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Participation of the 2nd Pangyo Techno Valley Public Site Development Plan Committee of the Ministry of Science, ICT and Future Planning</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>The 6th Annual General Meeting</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>China's Chen-Jin-bin to visit business exchanges in the New Technology Industry Development Zone</p>
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
                                <p>Participation in the Kookmin University Link Project Festival</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>Visit to Business Exchange in China's New Industrial Development Zone in Jangsa Province</p>
                                <p>Opening of the 2014 recruitment program</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>Participated in the 11th New York CEWIT Conference</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>Chairman of Chen-Jin City Junghwan Group visits (consultation to acquire affiliates of large domestic companies)</p>
                                <p>Visit to 19 companies in EMBA courses at Tsinghua University in China</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>China's Chen-Jin-bin to visit business exchanges in the New Technology Industry Development Zone</p>
                                <p>China Zhonghwan Group Contracts for Investment Support for Wireless Chargers</p>
                            </li>
                            <li>
                                <p class="month">04</p>
                                <p>Korea Industrial Complex Corporation's China Expansion Briefing Session</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>China's Chen-Jin-bin New Industrial Development Zone Business Exchange Agreement (MOU)</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>China's Unnam Agricultural IT Market Research Group Dispatched</p>
                                <p>5th Regular General Meeting (3rd Chairman: Miseong Poritech Chairman Kim Jong-dal)</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>Chen-Jin-bin, China's new industrial development district's party secretary, visited</p>
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
                                <p>Invited by China's Ministry of Science and Technology to send a market research team to China's Smart City</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>Course opening for prospective recruiters</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>Participated in the 10th New York CEWIT Conference</p>
                                <p>The Ministry of Trade, Industry and Energy selected a consortium of preliminary business operators for the spread of smart grids</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>China Smart City Forum Participates in the Agreement Ceremony for Other Representatives of Korea</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>The 4th Annual General Meeting</p>
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
                                <p>Korea UECO City Association -> Change the name of the Korea UECO Industry Association Association</p>
                            </li>
                            <li>
                                <p class="month">11</p>
                                <p>Support for CEWIT International Conference and Expo (CEWIT2012) events</p>
                            </li>
                            <li>
                                <p class="month">10</p>
                                <p>2012 Korea Electronics Show(KES) Seoul Digital Industrial Complex Booth Co-hosted</p>
                            </li>
                            <li>
                                <p class="month">09</p>
                                <p>Participation in the MBDA event in the United States</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Hosted by the 2nd ETRI IT+ Manufacturing Wide Area Technology Market</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>The 3rd Regular General Meeting (2nd Chairman: Chairman Baek Jong-man of YPP Co., Ltd.)</p>
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
                                <p>The Ministry of Knowledge Economy commissioned the K-MEG recruitment program</p>
                            </li>
                            <li>
                                <p class="month">07</p>
                                <p>Seoul Digital Industrial Complex Government R&D Project Technology Demand Survey Briefing Session</p>
                            </li>
                            <li>
                                <p class="month">06</p>
                                <p>Support for the 1st ETRI IT+ Manufacturing Wide-area Technology Market Event</p>
                            </li>
                            <li>
                                <p class="month">05</p>
                                <p>Ministry of Knowledge Economy Promotes Seoul Digital Industrial Complex for K-MEG Demonstration Project</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>The second regular general meeting</p>
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
                                <p>The second half of 2010 extraordinary second half of 2010</p>
                            </li>
                            <li>
                                <p class="month">08</p>
                                <p>Participation in the IT Investment Fair in Yanji, China</p>
                            </li>
                            <li>
                                <p class="month">03</p>
                                <p>1st Regular General Meeting (1st Chairman: Chairman Baek Jong-man of YPP Co., Ltd.)</p>
                            </li>
                            <li>
                                <p class="month">02</p>
                                <p>Authorization of a non-profit corporation's unique number card by Guro Tax Office in Seoul</p>
                            </li>
                            <li>
                                <p class="month">01</p>
                                <p>Permission to establish a corporation under the Ministry of Knowledge Economy</p>
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
                                <p>Founding General Meeting of the Korea UEco-City Association</p>
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