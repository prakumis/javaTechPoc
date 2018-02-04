package com.nyp.shopping.business.application.impl;

import javax.persistence.EntityNotFoundException;

import com.nyp.shopping.business.repository.ProductCategoryRepository;
import com.nyp.shopping.common.entity.ProductCategory;

public class BaseASImpl {

	public void checkIfEntityExists(Long id, ProductCategoryRepository baseRepository) {

		ProductCategory productCategory = baseRepository.findOne(id);
		if (null == productCategory) {
			throw new EntityNotFoundException(String.format("Requested entity %s not found", id));
		}
	}

}
