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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;


/**
 * Entity implementation class for Entity: PRODUCT
 *
 */
@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "PRODUCT")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="Product")
public class Product extends AbstractCommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private ProductCategory category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID", nullable = true)
	private Supplier supplier;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductItem> productItemList = new HashSet<>(0);

	@Column(name = "NAME", length=50, nullable=false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	public Product() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<ProductItem> getProductItemList() {
		return productItemList;
	}

	public void setProductItemList(Set<ProductItem> productItemList) {
		this.productItemList = productItemList;
	}

}
