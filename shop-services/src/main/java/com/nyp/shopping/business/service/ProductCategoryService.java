package com.nyp.shopping.business.service;
/**
 * 
 */

import java.util.List;

import javax.management.ServiceNotFoundException;

import com.nyp.shopping.common.vo.ProductCategoryVO;

/**
 * @author pmis30
 *
 */
public interface ProductCategoryService {

	List<ProductCategoryVO> findAllCategories();

	List<ProductCategoryVO> getCategoryById(Long id) throws ServiceNotFoundException;

	Long createCategory(ProductCategoryVO category);

	ProductCategoryVO updateCategory(ProductCategoryVO category);

	void deleteCategory(ProductCategoryVO category);

	List<ProductCategoryVO> findTopCategories();

}
