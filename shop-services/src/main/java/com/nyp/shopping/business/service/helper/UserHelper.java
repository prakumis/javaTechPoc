/**
 * 
 */
package com.nyp.shopping.business.service.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nyp.shopping.business.service.helper.email.UserRegistrationEmailSender;
import com.nyp.shopping.common.constants.ApplicationConstants;
import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.entity.UserStatus;
import com.nyp.shopping.common.exception.AuthenticationException;
import com.nyp.shopping.common.utils.EncryptionUtils;
import com.nyp.shopping.common.vo.UserVO;
import com.nyp.shopping.opensource.emailsender.Context;
import com.nyp.shopping.opensource.emailsender.EmailSenderTemplate;

/**
 * @author Java Developer
 *
 */
@Component
public class UserHelper extends BaseHelper {

	public void validateUserName(Customer customer) {

		if (null == customer) {
			// TODO The below message should be as per the requested locale
			throw new AuthenticationException("Username or Password doesn't match");
		}
	}

	public void validatePasswordAgainstDB(Customer customer, UserVO userVO) {

		logger.debug("Entered validatePasswordAgainstDB()");
		String encryptedPasswordFromDB = customer.getPassword();
		String encryptedPasswordFromRequest = EncryptionUtils
				.encrypt(ArrayUtils.charArrayToByteArray(userVO.getPassword()), "saltFromProperties");
		if (!encryptedPasswordFromDB.equals(encryptedPasswordFromRequest)) {
			// Invalid Password
			throw new AuthenticationException("Username or Password doesn't match");
		}
		
		logger.debug("Exited validatePasswordAgainstDB()");
		//testSendEmail(customer);
	}

	private void testSendEmail(Customer customer) {

		logger.debug("Entered testSendEmail()");
		Map<String, String> emailDataMap = new HashMap<>();
		Context context = new Context(emailDataMap);
		emailDataMap.put("@firstName@", customer.getCustomerName());
		emailDataMap.put("@userName@", customer.getEmail());
		emailDataMap.put("@password@", customer.getMobile());
		emailDataMap.put("toArray", customer.getEmail());

		EmailSenderTemplate emailSender = new UserRegistrationEmailSender(
				ApplicationConstants.EMAIL_TEMPLATE_PATH + ApplicationConstants.EMAIL_TEMPLATE_AUTHENTICATION_FAILURE);
		emailSender.processAndSendEmail(context);
	}

	public void validateUserStatus(Customer userInfoFromDB) {

		logger.debug("Entered validateUserStatus()");
		if (!userInfoFromDB.getStatus().equals(UserStatus.ACTIVE.toString())) {
			// User is Inactive

		}
	}

}
