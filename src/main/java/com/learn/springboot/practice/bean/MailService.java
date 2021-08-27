package com.learn.springboot.practice.bean;

public interface MailService {
    /**
     * 发送邮件
     * @param message
     */
    void sendEmail(String message);
}
