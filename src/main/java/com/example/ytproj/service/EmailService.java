package com.example.ytproj.service;

import javax.mail.MessagingException;

public interface EmailService {
    public boolean sendMail(String msg, String sub, String to, String from) throws MessagingException;
}
