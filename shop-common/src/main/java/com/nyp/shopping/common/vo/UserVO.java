/**
 * 
 */
package com.nyp.shopping.common.vo;

/**
 * @author Java Developer
 *
 */
public class UserVO extends BaseVO {

	private Long userID;
	private String email;
	private Boolean globalAdmin;
	private String userName;
	private String firstName;
	private String lastName;
	private String phone;
	private String status;
	private Boolean isCustomer;
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

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getGlobalAdmin() {
		return globalAdmin;
	}

	public void setGlobalAdmin(Boolean globalAdmin) {
		this.globalAdmin = globalAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

}
