package com.cfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: chenfuxian
 * @Date: 2022/1/14 23:45
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("450255266@qq.com");
            helper.setTo("450255266@qq.com");
            helper.setSubject("标题：发送Html内容");

            StringBuffer context = new StringBuffer();
            context.append("<p style='color:red'>");
            context.append("Hello SpringBoot Email Start SimpleWu!!");
            context.append("</p>");
            helper.setText(context.toString(),true);//设置true发送html邮件

            //带附件
            //FileSystemResource fileSystemResource=new FileSystemResource(new File("D:\2019-05-07.pdf"));
            //helper.addAttachment("邮箱附件",fileSystemResource);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
