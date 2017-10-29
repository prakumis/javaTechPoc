/**
 * 
 */
package com.nyp.shopping.business.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.UserSecurityAS;
import com.nyp.shopping.business.service.UserSecurityService;
import com.nyp.shopping.business.service.adaptor.CustomerAdaptor;
import com.nyp.shopping.business.service.helper.UserHelper;
import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.vo.CustomerVO;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author pmis30
 *
 */
@Service
public class UserSecurityServiceImpl extends BaseServiceImpl implements UserSecurityService {

	@Inject
	private UserSecurityAS userSecurityAS;

	@Inject
	private CustomerAdaptor customerAdaptor;

	@Inject
	private UserHelper userHelper;

	/**
	 * 
	 */
	public UserSecurityServiceImpl() {
	}

	@Override
	public CustomerVO validateUserLogin(UserVO userVO) {

		Customer customer = userSecurityAS.getCustomerByUsername(userVO);
		userHelper.validateUserName(customer);
		userHelper.validatePasswordAgainstDB(customer, userVO);
		userHelper.validateUserStatus(customer);
		return customerAdaptor.toCustomerVO(customer);
	}

}
