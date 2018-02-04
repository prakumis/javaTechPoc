package com.nyp.shopping.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;


/**
 * Entity implementation class for Entity: RefProductCategory
 *
 */
@Entity
@Table(name = "PRODUCT_CATEGORY")
@DynamicUpdate
@SelectBeforeUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.nyp.shopping.common.entity.ProductCategory")
public class ProductCategory extends AbstractCommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 50, nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	// THIS FIELD IS BY DEFAULT EMBEDDED
	//private RecordInfo recordInfo;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCategory")
	private Set<ProductCategory> subCategories = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Product> productList = new HashSet<>(0);

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_CATEGORY_ID")
	private ProductCategory parentCategory;

	@Transient
	private Integer productCount;

	public ProductCategory() {
		super();
	}

	public ProductCategory(Long catId) {
		this.setId(catId);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ProductCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<ProductCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public Set<Product> getProductList() {
		return productList;
	}

	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}

	public ProductCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((parentCategory == null) ? 0 : parentCategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (parentCategory == null) {
			if (other.parentCategory != null)
				return false;
		} else if (!parentCategory.equals(other.parentCategory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Id: %s, Name: %s, Description: %s", this.getId(), this.getName(), this.getDescription());
	}
}
