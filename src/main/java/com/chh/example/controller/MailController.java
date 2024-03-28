package com.chh.example.controller;

import com.chh.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送简单邮件
     */
    @GetMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail(
                "to@xxx.com",
                "标题",
                "这是内容请关注我");
    }

    /**
     * 发送复杂邮件（文本+图片+附件）
     */
    @GetMapping("/sendMimeMail")
    public ResponseEntity<String> sendMimeMail() {
        return mailService.sendMimeMail(
                "to@xxx.com",
                "标题",
                "<h3>这是内容</h3>" +
                        "<br>请关注我<br>" +
                        "<img src='cid:logo'>");
    }

    /**
     * 发送模板邮件
     */
    @GetMapping("/sendTemplateMail")
    public ResponseEntity<String> sendTemplateMail() {
        Context context = new Context();
        context.setVariable("username", "xxx用户");
        context.setVariable("message", "这是额外的消息");
        return mailService.sendTemplateMail(
                "to@xxx.com",
                "标题",
                context);
    }

}
