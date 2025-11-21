package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MenuItem {
    // Getters and Setters...
    private String title;        // 메뉴 이름 (예: "참가자 관리")
    private String url;         // 메뉴 URL (예: "/mng/members/list.do")
    private String icon;        // 사이드바용 아이콘 클래스 (예: "ki-profile-user")
    private int pathCount; // 아이콘 path 개수를 저장할 필드 추가
    private List<MenuItem> children; // 자식 메뉴 리스트 (중첩 메뉴용)

    public MenuItem(String title, String url, String icon, int pathCount, List<MenuItem> children) {
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.pathCount = pathCount;
        this.children = children;
    }

    // 자식 메뉴가 없는 경우를 위한 생성자
    // 자식 메뉴가 없는 경우를 위한 생성자
    public MenuItem(String title, String url, String icon, int pathCount) {
        this(title, url, icon, pathCount, null);
    }

}