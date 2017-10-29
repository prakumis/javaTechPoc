/**
 * 
 */
package com.nyp.shopping.business.service.helper.email;

import com.nyp.shopping.opensource.emailsender.Context;
import com.nyp.shopping.opensource.emailsender.EmailSenderTemplate;
import com.nyp.shopping.opensource.emailsender.EmailVO;

/**
 * @author Java Developer
 *
 */
public class UserRegistrationEmailSender extends EmailSenderTemplate {

	public UserRegistrationEmailSender(String templateName) {
		super(templateName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nyp.shopping.opensource.EmailSenderTemplate#updateEmailVO(com.nyp.
	 * shopping.opensource.EmailVO)
	 */
	@Override
	protected void updateEmailVO(EmailVO emailVO, Context context) {

		super.updateEmailVO(emailVO, context);
	}

}
