package com.samsung.sodam.api.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender sender;

    final AuthService authService;

    public void sendEmail(String email, String title, String subTitle, String code) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, email);    // 수신자 설정
        message.setSubject(title); // 메일 제목 설정


        String text="";
        text+= "<div style='margin:100px;'>";
        text+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        text+= "<h3 style='color:blue;'>"+subTitle+"</h3>";
        text+= "<div style='font-size:130%'>";
        text+= " : <strong>";
        text+= code+"</strong><div><br/> ";
        text+= "</div>";
        message.setText(text, "utf-8", "html"); // 메일 내용 설정

        //message.setFrom(senderEmail); // 발신자 설정

        sender.send(message);

    }

}
