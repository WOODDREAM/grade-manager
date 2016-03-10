package com.dfire.grade.manager.utils;

import com.dfire.grade.manager.configuration.MailConfiguration;
import com.dfire.grade.manager.logger.LoggerFactory;
import com.dfire.grade.manager.logger.LoggerMarker;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
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

    /**
     * @param subject  标题
     * @param body     内容
     * @param fileName 附件名称
     * @param address  收录地址
     * @throws MessagingException 必须解决超时问题
     */
    public void sendMail(String subject, String body, String fileName, String... address) throws MessagingException, UnsupportedEncodingException {
        //需自定义annotation解决参数验证问题
        if (address.length < 1) {
            LoggerFactory.MAILFACTORY.info(LoggerMarker.MAIL_SEND, "空地址！");
            throw new MessagingException("空地址");
        }
        //属性
        Properties properties = new Properties();
        //设置发件邮箱服务器属性
        properties.setProperty(mailConfiguration.getMailSmptHost(), mailConfiguration.getFormHost());
        //是否需要授权
        properties.setProperty(mailConfiguration.getMailSmptAuth(), mailConfiguration.getMailAuth());
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfiguration.getFrom(), mailConfiguration.getMailCertificate()); //发件人邮件用户名、密码
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
            //收录人
            mimeMessage.addRecipients(Message.RecipientType.TO, addresses);
            mimeMessage.setSubject(subject);
            BodyPart messageBody = new MimeBodyPart();
            messageBody.setText(body);
            //创建多重消息
            Multipart multipart = new MimeMultipart();
            //设置文本消息部分
            multipart.addBodyPart(messageBody);
            if (null != fileName && !StringUtils.isEmptyOrWhitespaceOnly(fileName)) {
                //附件部分
                messageBody = new MimeBodyPart();
                DataSource dataSource = new FileDataSource(fileName);
                messageBody.setDataHandler(new DataHandler(dataSource));
//                sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//                String file = enc.encode(fileName.getBytes());
                //中文名编码
                messageBody.setFileName(MimeUtility.encodeText(dataSource.getName()));
            }
            multipart.addBodyPart(messageBody);

            //发送完整消息
            mimeMessage.setContent(multipart, "utf-8");
            //发送消息
            Transport.send(mimeMessage);
            LoggerFactory.MAILFACTORY.info(LoggerMarker.MAIL_SEND, "发送邮件成功！");
        } catch (MessagingException e) {
            LoggerFactory.MAILFACTORY.error(LoggerMarker.MAIL_SEND, "发送邮件失败！", e);
            throw e;
        }
    }
}
