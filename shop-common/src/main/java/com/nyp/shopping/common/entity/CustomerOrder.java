package com.nyp.shopping.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "CUSTOMER_ORDER")

public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "AMOUNT_ITEMS_SUM")
	private Double amountItemsSum;

	@Column(name = "AMOUNT_FINAL_BILLING")
	private Double amountFinalBilling;

	@Column(name = "AMOUNT_PAID")
	private Double amountPaid;

	@Column(name = "OTHER_DETAILS", length = 100)
	private String orderOtherDetails;

	// NOT_PAID, PART_PAID, FULL_PAID
	@Column(name = "ORDER_STATUS", length = 100)
	private String orderStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DELIVERY_ADDRESS_ID")
	private CustomerOrderAddress deliveryAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BILLING_ADDRESS_ID")
	private CustomerOrderAddress billingAddress;

	/** CustomerOrder is considered as the owner side while Product is on OTHER side of ManyToMany relationship	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
	private Set<CustomerOrderItem> customerOrderItemsList = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
	private Set<CustomerOrderPayments> paymentList = new HashSet<>(0);

	public CustomerOrder() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getAmountItemsSum() {
		return amountItemsSum;
	}

	public void setAmountItemsSum(Double amountItemsSum) {
		this.amountItemsSum = amountItemsSum;
	}

	public Double getAmountFinalBilling() {
		return amountFinalBilling;
	}

	public void setAmountFinalBilling(Double amountFinalBilling) {
		this.amountFinalBilling = amountFinalBilling;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getOrderOtherDetails() {
		return orderOtherDetails;
	}

	public void setOrderOtherDetails(String orderOtherDetails) {
		this.orderOtherDetails = orderOtherDetails;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public CustomerOrderAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(CustomerOrderAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Set<CustomerOrderItem> getCustomerOrderItemsList() {
		return customerOrderItemsList;
	}

	public void setCustomerOrderItemsList(Set<CustomerOrderItem> customerOrderItemsList) {
		this.customerOrderItemsList = customerOrderItemsList;
	}

	public Set<CustomerOrderPayments> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(Set<CustomerOrderPayments> paymentList) {
		this.paymentList = paymentList;
	}

	public synchronized CustomerOrderAddress getBillingAddress() {
		return billingAddress;
	}

	public synchronized void setBillingAddress(CustomerOrderAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

}
