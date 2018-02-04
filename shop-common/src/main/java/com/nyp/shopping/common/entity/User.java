package com.nyp.shopping.common.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.nyp.shopping.common.entity.ProductCategory;

import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "USER")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends AbstractCommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "EMAIL", length = 50)
	private String email;
	@Column(name = "PASSWORD", length = 500)
	private String password;
	@Column(name = "USERNAME", length = 50)
	private String userName;
	@Column(name = "FIRST_NAME", length = 50)
	private String firstName;
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	@Column(name = "PHONE", length = 50)
	private String phone;
	@Column(name = "STATUS", length = 50)
	private String status;

	// bi-directional many-to-one association to UserGroup
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<UserGroup> usergroups;

	// bi-directional many-to-one association to UserGroup
	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
	private List<ProductCategory> createdProductCategories;

	// bi-directional many-to-one association to UserGroup
	@OneToMany(mappedBy = "modifiedBy", fetch = FetchType.LAZY)
	private List<ProductCategory> updatedProductCategories;

	public User() {
		// no argument constructor
	}

	public User(Long id) {
		this.setId(id);
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
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

	public List<UserGroup> getUsergroups() {
		return this.usergroups;
	}

	public void setUsergroups(List<UserGroup> usergroups) {
		this.usergroups = usergroups;
	}

	public UserGroup addUsergroup(UserGroup usergroup) {
		getUsergroups().add(usergroup);
		usergroup.setUser(this);

		return usergroup;
	}

	public UserGroup removeUsergroup(UserGroup usergroup) {
		getUsergroups().remove(usergroup);
		usergroup.setUser(null);

		return usergroup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public synchronized List<ProductCategory> getCreatedProductCategories() {
		return createdProductCategories;
	}

	public synchronized void setCreatedProductCategories(List<ProductCategory> createdProductCategories) {
		this.createdProductCategories = createdProductCategories;
	}

	public synchronized List<ProductCategory> getUpdatedProductCategories() {
		return updatedProductCategories;
	}

	public synchronized void setUpdatedProductCategories(List<ProductCategory> updatedProductCategories) {
		this.updatedProductCategories = updatedProductCategories;
	}

}