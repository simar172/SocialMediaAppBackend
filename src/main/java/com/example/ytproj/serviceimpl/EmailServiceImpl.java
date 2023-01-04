package com.example.ytproj.serviceimpl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.example.ytproj.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    public boolean sendMail(String msg, String sub, String to, String from) throws MessagingException {
        // TODO Auto-generated method stub
        String host = "smtp.gmail.com";
        boolean f = false;
        Properties p = System.getProperties();
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.port", "465");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.ssl.enable", "true");
        Session ses = Session.getInstance(p, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication("simarp804@gmail.com", "raqpwopwxalyyhxv");
            }

        });
        ses.setDebug(true);
        MimeMessage misg = new MimeMessage(ses);
        misg.setFrom(from);
        misg.setSubject(sub);

        misg.setContent(msg, "text/html");
        misg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        Transport.send(misg);
        System.out.println("Send");
        f = true;
        return f;
    }

}
