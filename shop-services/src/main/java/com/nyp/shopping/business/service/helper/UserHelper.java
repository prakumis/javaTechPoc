/**
 * 
 */
package com.nyp.shopping.business.service.helper;

import org.springframework.stereotype.Component;

import com.nyp.shopping.common.entity.Customer;
import com.nyp.shopping.common.entity.UserStatus;
import com.nyp.shopping.common.exception.AuthenticationException;
import com.nyp.shopping.common.utils.EncryptionUtils;
import com.nyp.shopping.common.vo.UserVO;

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
	}

	public void validateUserStatus(Customer userInfoFromDB) {

		logger.debug("Entered validateUserStatus()");
		if (!userInfoFromDB.getStatus().equals(UserStatus.ACTIVE.toString())) {
			// User is Inactive

		}
	}

}
