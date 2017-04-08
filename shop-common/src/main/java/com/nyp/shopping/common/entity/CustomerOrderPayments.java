package com.nyp.shopping.common.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CustomerPaymentMethod
 *
 */
@Entity
@Table(name = "CUSTOMER_ORDER_PAYMENTS")

public class CustomerOrderPayments implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private CustomerOrder customerOrder;

	@Column(name = "AMOUNT")
	private Double amount;

	// Refer RefPaymentMethods.java
	@Column(name = "PAYMENT_METHOD", length = 10, nullable = false)
	private String paymentMethod;

	@Column(name = "CARD_NUMBER", length = 50, nullable = true)
	private String cardNumber;

	@Column(name = "OTHER_DETAILS", length = 50, nullable = true)
	private String otherDetails;

	@Embedded
	private RecordInfo recordInfo;

	public CustomerOrderPayments() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getOtherDetails() {
		return this.otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public RecordInfo getRecordInfo() {
		return recordInfo;
	}

	public void setRecordInfo(RecordInfo recordInfo) {
		this.recordInfo = recordInfo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
