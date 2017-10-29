/**
 * 
 */
package com.nyp.shopping.business.application.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.UserSecurityAS;
import com.nyp.shopping.business.repository.CustomerRepository;
import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author Java Developer
 *
 */
@Service
public class UserSecurityASImpl implements UserSecurityAS {

	@Inject
	private CustomerRepository customerRepository;

	/**
	 * 
	 */
	public UserSecurityASImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer getCustomerByUsername(UserVO userVO) {

		
		return customerRepository.findCustomerDetails(userVO.getEmailMobile(), userVO.getEmailMobile());
	}

}
