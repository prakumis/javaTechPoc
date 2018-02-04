package com.nyp.shopping.business.service;
/**
 * 
 */

import java.util.List;

import com.nyp.shopping.common.vo.ProductCategoryVO;

/**
 * @author pmis30
 *
 */
public interface ProductCategoryService {

	List<ProductCategoryVO> findAllCategories();

	List<ProductCategoryVO> getCategoryById(Long id);

	List<ProductCategoryVO> findCategoriesByParentId(Long parentId);

	List<ProductCategoryVO> findCategoriesByStatus(Boolean status);

	Long createCategory(ProductCategoryVO category);

	ProductCategoryVO updateCategory(ProductCategoryVO category);

	ProductCategoryVO updateStatus(ProductCategoryVO categoryVO);

	void deleteCategory(ProductCategoryVO category);

}
