package com.nyp.shopping.opensource.emailsender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mail-template")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailVO {

	/**
	 * Email Address that appears in TO list of mail.
	 */
	private String[] to;

	/**
	 * Email Address that appears in CC list of mail.
	 */
	private String[] cc;

	/**
	 * Email Address that appears in BCC list of mail.
	 */
	private String[] bcc;

	/**
	 * Email address of the sender.
	 */
	private String fromAddress;

	/**
	 * Name of the sender.
	 */
	private String fromName;

	/**
	 * Mail message text.
	 */
	private String body;

	/**
	 * Subject of the mail.
	 */
	private String subject;

	/**
	 * Type of mail.
	 */
	private String mailType;

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
