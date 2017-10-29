/**
 * 
 */
package com.nyp.shopping.business.service;

import com.nyp.shopping.common.vo.CustomerVO;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author JavaDeveloper
 *
 */
public interface UserSecurityService {

	CustomerVO validateUserLogin(UserVO userVO);

}
