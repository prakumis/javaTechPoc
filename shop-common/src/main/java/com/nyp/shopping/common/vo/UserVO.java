/**
 * 
 */
package com.nyp.shopping.common.vo;

/**
 * @author Java Developer
 *
 */
public class UserVO extends BaseVO {

	private String emailMobile;

	private char[] password;

	/**
	 * 
	 */
	public UserVO() {
		// no-arg constructor
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getEmailMobile() {
		return emailMobile;
	}

	public void setEmailMobile(String emailMobile) {
		this.emailMobile = emailMobile;
	}

}
