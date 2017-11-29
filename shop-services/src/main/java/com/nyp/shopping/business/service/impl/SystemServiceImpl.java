package com.nyp.shopping.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.UserSecurityAS;
import com.nyp.shopping.business.service.SystemService;
import com.nyp.shopping.business.service.helper.email.UserRegistrationEmailSender;
import com.nyp.shopping.common.constants.ApplicationConstants;
import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.vo.UserVO;
import com.nyp.shopping.opensource.emailsender.Context;
import com.nyp.shopping.opensource.emailsender.EmailSenderTemplate;

@Service
public class SystemServiceImpl extends BaseServiceImpl implements SystemService {

	@Inject
	UserSecurityAS userSecurityAS;

	@Override
	public Map<String, String> getSystemStatus() {

		Map<String, String> systemStatusMap = new HashMap<>();

		updateDatabaseStatus(systemStatusMap);
		updateEmailStatus(systemStatusMap);
		return systemStatusMap;
	}

	private void updateEmailStatus(Map<String, String> systemStatusMap) {

		Customer customer = new Customer();
		customer.setCustomerName("System Status Check");
		customer.setEmail("someEmail@gmail.com");
		customer.setMobile("09876543210");
		if(testSendEmail(customer)) {
			systemStatusMap.put(ApplicationConstants.SYSTEM_EMAIL_STATUS, ApplicationConstants.SYSTEM_STATUS_RUNNING);
		} else {
			systemStatusMap.put(ApplicationConstants.SYSTEM_EMAIL_STATUS, ApplicationConstants.SYSTEM_STATUS_STOPPED);
		}
	}

	private boolean testSendEmail(Customer customer) {

		logger.debug("Entered testSendEmail()");
		Map<String, String> emailDataMap = new HashMap<>();
		Context context = new Context(emailDataMap);
		emailDataMap.put("@firstName@", customer.getCustomerName());
		emailDataMap.put("@userName@", customer.getEmail());
		emailDataMap.put("@password@", customer.getMobile());
		emailDataMap.put("toArray", customer.getEmail());

		EmailSenderTemplate emailSender = new UserRegistrationEmailSender(
				ApplicationConstants.EMAIL_TEMPLATE_PATH + ApplicationConstants.EMAIL_TEMPLATE_AUTHENTICATION_FAILURE);
		//return emailSender.processAndSendEmail(context);
		return false;
	}

	private void updateDatabaseStatus(Map<String, String> systemStatusMap) {

		UserVO userVO = new UserVO();
		userVO.setEmailMobile("abc@xyz.com");
		Customer customer = null;
		try {
			customer = userSecurityAS.getCustomerByUsername(userVO);
		} catch (Exception ex) {
			/**
			 * This exception should not be logged or handled here.
			 */
		}
		if (null == customer) {
			systemStatusMap.put(ApplicationConstants.SYSTEM_DB_STATUS, ApplicationConstants.SYSTEM_STATUS_STOPPED);
		} else {
			systemStatusMap.put(ApplicationConstants.SYSTEM_DB_STATUS, ApplicationConstants.SYSTEM_STATUS_RUNNING);
		}
	}

}
