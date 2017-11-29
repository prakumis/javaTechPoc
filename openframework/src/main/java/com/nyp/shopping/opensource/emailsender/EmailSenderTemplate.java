/**
 * 
 */
package com.nyp.shopping.opensource.emailsender;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nyp.shopping.opensource.utils.StringUtil;

/**
 * @author Java Developer
 *
 */
public abstract class EmailSenderTemplate {

	private static final String EMAIL_CONFIG_FILE = "/email/email.properties";
	private static final String EMAIL_USERNAME_STR = "mail.username";
	private static final String EMAIL_PASS_WD_STR = "mail.password";
	private static Properties mailServerProperties;
	private static Logger logger = LoggerFactory.getLogger(EmailSenderTemplate.class);

	private String templateName;

	static {
		loadConfig();
	}

	public EmailSenderTemplate(String templateName) {
		this.templateName = templateName;
	}

	private static void loadConfig() {

		mailServerProperties = new Properties();
		InputStream is = null;

		try {
			is = EmailSenderTemplate.class.getResourceAsStream(EMAIL_CONFIG_FILE);
			mailServerProperties.load(is);
		} catch (IOException e) {

			logger.error("Exception while loading email config file {}", EMAIL_CONFIG_FILE, e);
			throw new RuntimeException(e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {

					logger.error("Exception while closing email config file {}", EMAIL_CONFIG_FILE, e);
					throw new RuntimeException(e);
				}
				is = null;
			}
		}

	}

	/**
	 * Generates new mail session. This method generated new mail session after
	 * reading mail host from properties.
	 * 
	 * @return Session mail session.
	 */
	public static Session getSession() {

		final String username = mailServerProperties.getProperty(EMAIL_USERNAME_STR);
		final String password = mailServerProperties.getProperty(EMAIL_PASS_WD_STR);
		// Get the mail session
		Session session = Session.getDefaultInstance(mailServerProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		return session;
	}

	public static Properties getMailServerProperties() {
		if (mailServerProperties == null) {
			loadConfig();
		}
		return mailServerProperties;
	}

	public final boolean processAndSendEmail(Context context) {
		EmailVO emailVO = JaxbUtils.getEmailVO(getTemplateName());
		updateEmailVO(emailVO, context);
		return sendEmail(emailVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nyp.shopping.opensource.EmailSenderTemplate#updateEmailVO(com.nyp.
	 * shopping.opensource.EmailVO)
	 */
	protected void updateEmailVO(EmailVO emailVO, Context context) {

		String messageBody = emailVO.getBody();
		Map<String, String> emailDataMap = context.getDataMap();
		for (Map.Entry<String, String> entry : emailDataMap.entrySet()) {
			messageBody = StringUtil.replaceToken(messageBody, entry.getKey(), entry.getValue());
		}
		emailVO.setBody(messageBody);
		emailVO.setTo(new String[] { emailDataMap.get("toArray") });
	}

	public boolean sendEmail(EmailVO emailVO) {
		logger.debug("Send email start ::::" + Thread.currentThread().getId());

		boolean status = true;
		try {
			Session session = getSession();
			Message message = new MimeMessage(session);
			InternetAddress[] addressTo = null;
			InternetAddress[] addressCc = null;

			message.setFrom(new InternetAddress(emailVO.getFromAddress()));

			addressTo = new InternetAddress[emailVO.getTo().length];
			int i = 0;
			for (String emailTO : emailVO.getTo()) {
				addressTo[i] = new InternetAddress(emailTO);
				i++;
			}

			if (emailVO.getCc() != null) {
				i = 0;
				addressCc = new InternetAddress[emailVO.getCc().length];
				for (String ccTO : emailVO.getCc()) {
					addressCc[i] = new InternetAddress(ccTO);
					i++;
				}
			}

			message.setRecipients(RecipientType.TO, addressTo);
			message.setRecipients(RecipientType.CC, addressCc);
			message.setText(emailVO.getBody());
			message.setSentDate(new Date());
			message.setSubject(emailVO.getSubject());

			javax.mail.Transport.send(message);
		} catch (Exception ex) {
			status = false;
			logger.debug("unable to send mails " + ex);
		}
		return status;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
