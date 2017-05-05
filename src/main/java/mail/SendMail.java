package mail;

import java.security.GeneralSecurityException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendMail {

	public static void main(String[] args) throws GeneralSecurityException {
		// 收件人电子邮箱
		String to = "1135431732@Qqq.com";

		// 发件人电子邮箱
		String from = "xpillx@163.com";

		// 指定发送邮件的主机为 localhost
		String host = "smtp.163.com";

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.transport.protocol", "SMTP");
		properties.put("mail.smtp.auth", "true");
		final String smtpPort = "465";
		properties.setProperty("mail.smtp.port", smtpPort);
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", smtpPort);

		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xpillx@163.com", "qwe987654321"); // 发件人邮件用户名、密码gflycogkybwmbaca
			}
		});

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: 头部头字段
			message.setSubject("This is the Subject Line!");

			// 设置消息体
			message.setText("This is actual message");

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
