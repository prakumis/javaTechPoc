/**
 * 
 */
package com.nyp.shopping.business.application.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.ProductCategoryAS;
import com.nyp.shopping.business.repository.ProductCategoryRepository;
import com.nyp.shopping.common.entity.ProductCategory;

/**
 * @author pmis30
 *
 */
@Service
public class ProductCategoryASImpl implements ProductCategoryAS {

	@Inject
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public List<ProductCategory> findAllCategories() {
		return productCategoryRepository.findAll();
	}

	@Override
	public List<ProductCategory> findTopCategories() {
		return productCategoryRepository.findTopCategories();
	}

	@Override
	public List<ProductCategory> getCategoryById(Long id) {
		checkIfEntityExists(id);
		List<ProductCategory> productCategoryList = new ArrayList<>();
		productCategoryList.add(productCategoryRepository.findOne(id));
		return productCategoryList;
	}

	private void checkIfEntityExists(Long id) {
		if(!productCategoryRepository.exists(id)) {
			throw new EntityNotFoundException(
					String.format("Requested entity %s not found", id));
		}
	}

	@Override
	public Long createCategory(ProductCategory productCategory) {
		productCategory.setId(null);
		ProductCategory newProductCategory = productCategoryRepository.save(productCategory);
		return newProductCategory.getId();
	}

	@Override
	public ProductCategory updateCategory(ProductCategory productCategory) {
		checkIfEntityExists(productCategory.getId());
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public void deleteCategory(ProductCategory productCategory) {
		checkIfEntityExists(productCategory.getId());
		productCategoryRepository.delete(productCategory.getId());
	}

}
