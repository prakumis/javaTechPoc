package com.nyp.shopping.common.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ProductVO extends BaseVO {

	private Long id;
	private Long catId;

	@NotNull(message = "error.NotNull.name")
	@Length(min=5, max=50, message="error.Length.name")
	private String name;

	@NotNull(message = "NotNull.Description")
	@Length(min=5, max=50, message="The length should be between 5 and 50 characters long")
	private String description;

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

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

}
