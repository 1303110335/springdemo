/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author xuleyan
 * @version TestEmail.java, v 0.1 2019-03-22 10:09 AM xuleyan
 */
import java.util.Properties;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.AddressException;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMessage.RecipientType;

public class TestEmail {

    public static void main(String[] args) throws AddressException, MessagingException {

        Properties properties = new Properties();

        properties.put("mail.transport.protocol", "smtp");// 连接协议

        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名

        properties.put("mail.smtp.port", 465);// 端口号

        properties.put("mail.smtp.auth", "true");

        properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用

        properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息

        //得到回话对象

        Session session = Session.getInstance(properties);

        // 获取邮件对象

        Message message = new MimeMessage(session);

        //设置发件人邮箱地址

        message.setFrom(new InternetAddress("1303110335@qq.com"));

        //设置收件人地址
        message.setRecipients(RecipientType.TO, new InternetAddress[]{new InternetAddress("1526026322@qq.com")});

        //设置邮件标题

        message.setSubject("这是第一封Java邮件");

        //设置邮件内容

        message.setText("内容为： 这是第一封java发送来的邮件。");

        //得到邮差对象

        Transport transport = session.getTransport();

        //连接自己的邮箱账户

        transport.connect("1303110335@qq.com", "yqkoymzqbqfyhbja");//密码为刚才得到的授权码

        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());

    }

}