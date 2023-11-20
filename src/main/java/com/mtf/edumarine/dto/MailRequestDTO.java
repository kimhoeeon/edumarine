package com.mtf.edumarine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MailRequestDTO {
    private String id; // Table Id
    private String subject; //제목
    private String body; //본문
    private String sender; //발신메일주소
    private String senderName; //발신자이름
    private String template; //템플릿
    private List<Receiver> receiver;
    private List<FileUrl> fileUrl;

    @Getter
    @Setter
    @ToString
    public static class Receiver {
        private String name;
        private String email;
        private String phone;
        private String note1;
    }

    @Getter
    @Setter
    @ToString
    public static class FileUrl {
        private String name;
    }

}