package com.nyp.shopping.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private static final long serialVersionUID = 1L;

	@Column(name = "CUSTOMER_NAME", length = 50)
	private String customerName;

	@Column(name = "PHONE", length = 15)
	private String phone;

	@Column(name = "MOBILE", length = 15)
	private String mobile;

	@Column(name = "STREET_ADDRESS", length = 200)
	private String streetAddress;

	@Column(name = "CITY_DISTRICT", length = 50)
	private String cityDistrict;

	@Column(name = "STATE", length = 50)
	private String state;

	@Column(name = "COUNTRY", length = 50)
	private String country;

	@Column(name = "ZIPCODE", length = 6)
	private Integer zipCode;

	/*
	 * This is unidirectional mapping. Customer can get its address, but address can't get customer details.
	 * So customer property is commented in address entity
	@OneToOne(fetch=FetchType.LAZY, mappedBy="customerAddress")
	private Customer customer;
	*/

	public CustomerAddress() {
		super();
	}


	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCityDistrict() {
		return this.cityDistrict;
	}

	public void setCityDistrict(String cityDistrict) {
		this.cityDistrict = cityDistrict;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
