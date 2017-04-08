/**
 * 
 */
package com.nyp.shopping.common.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pmis30
 *
 */
public class ProductCategoryVO extends BaseVO {

	private Long id;
	private String name;
	private String description;
	private Set<ProductCategoryVO> subCategories = new HashSet<>(0);
//	private Set<ProductVO> productList = new HashSet<>(0);
	private ProductCategoryVO parentCategory;
	private Integer productCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	/*public Set<ProductVO> getProductList() {
		return productList;
	}

	public void setProductList(Set<ProductVO> productList) {
		this.productList = productList;
	}*/

	public ProductCategoryVO getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ProductCategoryVO parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<ProductCategoryVO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<ProductCategoryVO> subCategories) {
		this.subCategories = subCategories;
	}

}
