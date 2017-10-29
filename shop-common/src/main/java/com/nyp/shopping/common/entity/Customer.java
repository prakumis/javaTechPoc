/**
 * 
 */
package com.nyp.shopping.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Java Developer
 *
 */

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "PASSWORD", length = 500)
	private String password;

	@Column(name = "CUSTOMER_NAME", length = 50)
	private String customerName;

	// TODO fetch this from enum. Not in use in phase 1
	@Column(name = "CUSTOMER_TYPE", length = 12)
	private String type;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "DATE_DOB")
	private Date dateOfBirth;

	// TODO fetch this from enum.
	@Column(name = "STATUS")
	private String status;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ADDRESS_ID")
	private CustomerAddress customerAddress;

	@Embedded
	private RecordInfo recordInfo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerOrder> orderList = new HashSet<CustomerOrder>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RecordInfo getRecordInfo() {
		return recordInfo;
	}

	public void setRecordInfo(RecordInfo recordInfo) {
		this.recordInfo = recordInfo;
	}

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */
	public Set<CustomerOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<CustomerOrder> orderList) {
		this.orderList = orderList;
	}

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
