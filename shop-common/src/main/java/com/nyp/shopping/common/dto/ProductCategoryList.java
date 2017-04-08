package com.nyp.shopping.common.dto;

import java.util.Set;

import com.nyp.shopping.common.entity.Product;
import com.nyp.shopping.common.entity.ProductCategory;

public class ProductCategoryList {

	private String categoryName;
	private Set<ProductCategory> subCategorySet;
	private Set<Product> productSet;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<ProductCategory> getSubCategorySet() {
		return subCategorySet;
	}

	public void setSubCategorySet(Set<ProductCategory> subCategorySet) {
		this.subCategorySet = subCategorySet;
	}

	public Set<Product> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}
}
