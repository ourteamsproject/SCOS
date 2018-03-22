package es.source.code.activity;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Cristo on 2017/10/24.
 */

public class JavaMail {
    public static void main(String[] args) throws Exception {

        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.host","smtp.163.com");
        properties.setProperty("mail.smtp.auth", "true");
        Session session=Session.getDefaultInstance(properties);




        try {
            MimeMessage message=createMimeMessage(session, "fjz1994624@163.com", "1171788630@qq.com");
            Transport transport = session.getTransport();
            transport.connect("fjz1994624@163.com",  "1171788630@qq.com");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "某宝网", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject("打折钜惠", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent("XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();



        return message;

    }

    }
