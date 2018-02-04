package com.nyp.shopping.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * Entity implementation class for Entity: PRODUCT
 *
 */
@Entity(name = "PRODUCT_ITEM")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="ProductItem")
public class ProductItem extends AbstractCommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	@Column(name = "MRP_AMOUNT")
	private Double mrpAmount;

	@Column(name = "COLOUR")
	private String colour;

	@Column(name = "SIZE")
	private String packagingSize;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productItem")
	private Set<CustomerOrderItem> customerOrderItemList = new HashSet<>(0);

	public ProductItem() {
		super();
	}

	public String getColour() {
		return this.colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getPackagingSize() {
		return this.packagingSize;
	}

	public void setPackagingSize(String packagingSize) {
		this.packagingSize = packagingSize;
	}

	public Set<CustomerOrderItem> getCustomerOrderItemList() {
		return customerOrderItemList;
	}

	public void setCustomerOrderItemList(Set<CustomerOrderItem> customerOrderItemList) {
		this.customerOrderItemList = customerOrderItemList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getMrpAmount() {
		return mrpAmount;
	}

	public void setMrpAmount(Double mrpAmount) {
		this.mrpAmount = mrpAmount;
	}

}
