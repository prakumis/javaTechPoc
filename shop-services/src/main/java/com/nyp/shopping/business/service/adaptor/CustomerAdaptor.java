/**
 * 
 */
package com.nyp.shopping.business.service.adaptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.vo.CustomerVO;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author Java Developer
 *
 */
@Component
public class CustomerAdaptor {

	/**
	 * 
	 */
	public CustomerAdaptor() {
		// no-arg constructor
	}

	public CustomerVO toCustomerVO(Customer customer) {

		CustomerVO customerVO = new CustomerVO();
		if (null != customer) {
			customerVO.setCustomerName(customer.getCustomerName());
			customerVO.setDateOfBirth(customer.getDateOfBirth());
			customerVO.setEmail(customer.getEmail());
			customerVO.setMobile(customer.getMobile());
		}
		return customerVO;
	}

	public UserVO toUserVO(Customer customer) {

		UserVO userVO = new UserVO();
		if (null != customer) {
			String username = customer.getEmail();
			if(StringUtils.isBlank(username)) {
				username = customer.getMobile();
			}
			userVO.setEmailMobile(username);
		}
		return userVO;
	}

}
