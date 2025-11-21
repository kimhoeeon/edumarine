package com.mtf.edumarine.service;

import com.mtf.edumarine.dto.MenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    public List<MenuItem> getMenuList() {
        List<MenuItem> menuList = new ArrayList<>();

        // [헤더] 회원 / 신청
        menuList.add(new MenuItem("회원 / 신청", null, null, 0));

        // 1. 회원관리 (Icon: ki-address-book, paths: 3)
        List<MenuItem> memberChildren = new ArrayList<>();
        memberChildren.add(new MenuItem("전체 회원 목록", "/mng/customer/member.do", null, 0));
        memberChildren.add(new MenuItem("나의 이력서 목록", "/mng/customer/resume.do", null, 0));
        menuList.add(new MenuItem("회원관리", null, "ki-address-book", 3, memberChildren));

        // 2. 신청자 목록 (Icon: ki-people, paths: 5)
        List<MenuItem> applicantChildren = new ArrayList<>();
        applicantChildren.add(new MenuItem("상시 사전 신청", "/mng/customer/regular.do", null, 0));
        applicantChildren.add(new MenuItem("해상 엔진 테크니션", "/mng/customer/boarder.do", null, 0));
        applicantChildren.add(new MenuItem("FRP 정비 테크니션", "/mng/customer/frp.do", null, 0));
        applicantChildren.add(new MenuItem("기초정비교육", "/mng/customer/basic.do", null, 0));
        applicantChildren.add(new MenuItem("응급조치교육", "/mng/customer/emergency.do", null, 0));
        applicantChildren.add(new MenuItem("자가정비 (선외기)", "/mng/customer/outboarder.do", null, 0));
        applicantChildren.add(new MenuItem("자가정비 (선내기)", "/mng/customer/inboarder.do", null, 0));
        applicantChildren.add(new MenuItem("자가정비 (세일요트)", "/mng/customer/sailyacht.do", null, 0));
        applicantChildren.add(new MenuItem("고마력 선외기 정비", "/mng/customer/highhorsepower.do", null, 0));
        applicantChildren.add(new MenuItem("자가정비 심화과정 (고마력)", "/mng/customer/highself.do", null, 0));
        applicantChildren.add(new MenuItem("고마력 선외기 정비 (특별반)", "/mng/customer/highspecial.do", null, 0));
        applicantChildren.add(new MenuItem("스턴드라이브 정비", "/mng/customer/sterndrive.do", null, 0));
        applicantChildren.add(new MenuItem("스턴드라이브 정비 (특별반)", "/mng/customer/sternspecial.do", null, 0));
        applicantChildren.add(new MenuItem("발전기 정비 교육", "/mng/customer/generator.do", null, 0));
        applicantChildren.add(new MenuItem("선외기/선내기 직무역량 강화과정", "/mng/customer/competency.do", null, 0));
        applicantChildren.add(new MenuItem("선내기 팸투어", "/mng/customer/famtourin.do", null, 0));
        applicantChildren.add(new MenuItem("선외기 팸투어", "/mng/customer/famtourout.do", null, 0));
        applicantChildren.add(new MenuItem("레저선박 해양전자장비 교육", "/mng/customer/electro.do", null, 0));
        menuList.add(new MenuItem("신청자 목록", null, "ki-people", 5, applicantChildren));

        // [헤더] 교육
        menuList.add(new MenuItem("교육", null, null, 0));

        // 3. 교육 관리 (Icon: ki-book-open, paths: 4)
        List<MenuItem> eduChildren = new ArrayList<>();
        eduChildren.add(new MenuItem("교육 현황", "/mng/education/train.do", null, 0));
        eduChildren.add(new MenuItem("교육 안내 템플릿 관리", "/mng/education/template.do", null, 0));
        eduChildren.add(new MenuItem("결제/환불 현황", "/mng/education/payment.do", null, 0));
        menuList.add(new MenuItem("교육 관리", null, "ki-book-open", 4, eduChildren));

        // [헤더] 정보센터
        menuList.add(new MenuItem("정보센터", null, null, 0));

        // 4. 게시판 관리 (Icon: ki-element-8, paths: 2)
        List<MenuItem> boardChildren = new ArrayList<>();
        boardChildren.add(new MenuItem("공지사항", "/mng/board/notice.do", null, 0));
        boardChildren.add(new MenuItem("보도자료", "/mng/board/press.do", null, 0));
        boardChildren.add(new MenuItem("사진자료", "/mng/board/gallery.do", null, 0));
        boardChildren.add(new MenuItem("영상자료", "/mng/board/media.do", null, 0));
        boardChildren.add(new MenuItem("뉴스레터", "/mng/board/newsletter.do", null, 0));
        // boardChildren.add(new MenuItem("채용공고", "/mng/board/announcement.do", null, 0)); // JSP 주석
        boardChildren.add(new MenuItem("취/창업 현황", "/mng/board/employment.do", null, 0));
        boardChildren.add(new MenuItem("취/창업 성공후기", "/mng/board/job.do", null, 0));
        boardChildren.add(new MenuItem("커뮤니티", "/mng/board/community.do", null, 0));
        boardChildren.add(new MenuItem("FAQ", "/mng/board/faq.do", null, 0));
        menuList.add(new MenuItem("게시판 관리", null, "ki-element-8", 2, boardChildren));

        // 5. 팝업/배너 관리 (Icon: ki-messages, paths: 5)
        List<MenuItem> popupChildren = new ArrayList<>();
        popupChildren.add(new MenuItem("팝업 관리", "/mng/pop/popup.do", null, 0));
        popupChildren.add(new MenuItem("배너 관리", "/mng/pop/banner.do", null, 0));
        menuList.add(new MenuItem("팝업/배너 관리", null, "ki-messages", 5, popupChildren));

        // 6. 뉴스레터 관리 (Icon: ki-directbox-default, paths: 4)
        List<MenuItem> newsletterChildren = new ArrayList<>();
        newsletterChildren.add(new MenuItem("뉴스레터 구독자 관리", "/mng/newsletter/subscriber.do", null, 0));
        menuList.add(new MenuItem("뉴스레터 관리", null, "ki-directbox-default", 4, newsletterChildren));

        // 7. SMS 관리 (Icon: ki-sms, paths: 2)
        List<MenuItem> smsChildren = new ArrayList<>();
        smsChildren.add(new MenuItem("SMS 발송 관리", "/mng/smsMng/sms.do", null, 0));
        menuList.add(new MenuItem("SMS 관리", null, "ki-sms", 2, smsChildren));

        // [헤더] 파일
        menuList.add(new MenuItem("파일", null, null, 0));

        // 8. 파일 관리 (Icon: ki-folder, paths: 2)
        List<MenuItem> fileChildren = new ArrayList<>();
        fileChildren.add(new MenuItem("다운로드 내역", "/mng/file/download.do", null, 0));
        fileChildren.add(new MenuItem("임시 휴지통", "/mng/file/trash.do", null, 0));
        menuList.add(new MenuItem("파일 관리", null, "ki-folder", 2, fileChildren));

        // [헤더] 개발사
        menuList.add(new MenuItem("개발사", null, null, 0));

        // 9. 요청사항 & 문의 (Icon: ki-notification-on, paths: 5)
        List<MenuItem> requestChildren = new ArrayList<>();
        requestChildren.add(new MenuItem("요청사항 & 문의 관리", "/mng/request/list.do", null, 0));
        menuList.add(new MenuItem("요청사항 & 문의", null, "ki-notification-on", 5, requestChildren));

        return menuList;
    }

}
