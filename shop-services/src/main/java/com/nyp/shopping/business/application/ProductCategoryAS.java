package com.nyp.shopping.business.application;
/**
 * 
 */

import java.util.List;

import javax.management.ServiceNotFoundException;

import com.nyp.shopping.common.entity.ProductCategory;

/**
 * @author pmis30
 *
 */
public interface ProductCategoryAS {

	List<ProductCategory> findAllCategories();

	List<ProductCategory> getCategoryById(Long id) throws ServiceNotFoundException;

	Long createCategory(ProductCategory category);

	ProductCategory updateCategory(ProductCategory category);

	void deleteCategory(ProductCategory category);

	List<ProductCategory> findTopCategories();

}
