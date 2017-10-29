/**
 * 
 */
package com.nyp.shopping.business.application;

import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author Java Developer
 *
 */
public interface UserSecurityAS {

	Customer getCustomerByUsername(UserVO userVO);

}
