/**
 * 
 */
package com.nyp.shopping.common.vo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author Java Developer
 *
 */
public class ProductCategoryVO extends BaseVO {

	private Long id;

	@NotNull(message = "error.NotNull.name")
	@Length(min=5, max=50, message="error.Length.name")
	private String name;

	//TODO check why this @Size is not working (Ref: https://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html/chapter-message-interpolation.html#section-interpolation-with-message-expressions)
	//@Size(min = 2, max = 14, message = "The license plate '${validatedValue}' must be between {min} and {max} characters long")
//	@NotBlank(message = "Task description must not be blank!")
	@NotNull(message = "NotNull.Description")
	@Length(min=5, max=50, message="The length should be between 5 and 50 characters long")
	private String description;

	private Set<ProductCategoryVO> subCategories = new HashSet<>(0);
//	private Set<ProductVO> productList = new HashSet<>(0);
	private ProductCategoryVO parentCategory;
	private Long parentCategoryId;
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

	public synchronized Long getParentCategoryId() {
		return parentCategoryId;
	}

	public synchronized void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

}
