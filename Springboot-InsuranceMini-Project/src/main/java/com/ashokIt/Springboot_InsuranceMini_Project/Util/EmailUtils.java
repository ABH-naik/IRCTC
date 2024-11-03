package com.ashokIt.Springboot_InsuranceMini_Project.Util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject,String body,String to)
    {
        try{
            MimeMessage mimeMessage=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
            helper.setSubject(subject);
            helper.setText(body,true);
            helper.setTo(to);
            mailSender.send(mimeMessage);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;

    }

}
