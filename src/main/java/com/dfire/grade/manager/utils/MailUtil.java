package com.dfire.grade.manager.utils;

import com.dfire.grade.manager.configuration.MailConfiguration;
import com.dfire.grade.manager.logger.LoggerFactory;
import com.dfire.grade.manager.logger.LoggerMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * User:huangtao
 * Date:2016-03-09
 * description：
 */
@Component
public class MailUtil {
    @Autowired
    private MailConfiguration mailConfiguration;

    public void sendMail(String subject, String body, String... address) throws MessagingException {
        if (address.length < 1) {
            throw new MessagingException("空地址");
        }
        Properties properties = new Properties();
        properties.setProperty(mailConfiguration.getMailSmptHost(), mailConfiguration.getFormHost());
        properties.setProperty(mailConfiguration.getMailSmptAuth(), mailConfiguration.getMailAuth());
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        properties.put("mail.smtp.socketFactory.fallback", "false");
//        properties.put("mail.smtp.starttls.enable", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfiguration.getFrom(), mailConfiguration.getMailPop3Token()); //发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象。
            MimeMessage mimeMessage = new MimeMessage(session);
            //set 发送主机
            mimeMessage.setFrom(mailConfiguration.getFrom());
            InternetAddress[] addresses = new InternetAddress[address.length];
            for (int i = 0; i < address.length; i++) {
                InternetAddress internetAddress = new InternetAddress(address[i]);
                addresses[i] = internetAddress;
            }
            mimeMessage.addRecipients(Message.RecipientType.TO, addresses);
            mimeMessage.setSubject(subject);
            BodyPart messageBody = new MimeBodyPart();
            messageBody.setText(body);
            //创建多重消息
            Multipart multipart = new MimeMultipart();
            //设置文本消息部分
            multipart.addBodyPart(messageBody);
            //附件部分
            messageBody = new MimeBodyPart();
            String fileName = "sd";
            DataSource dataSource = new FileDataSource(fileName);
            messageBody.setDataHandler(new DataHandler(dataSource));
            messageBody.setFileName(fileName);
            multipart.addBodyPart(messageBody);

            //发送完整消息
            mimeMessage.setContent(multipart);
            //发送消息
            Transport.send(mimeMessage);
            LoggerFactory.MAILFACTORY.info(LoggerMarker.MAIL_SEND, "发送邮件成功！");
        } catch (MessagingException e) {
            LoggerFactory.MAILFACTORY.error(LoggerMarker.MAIL_SEND, "发送邮件失败！", e);
            throw e;
        }
    }
}
