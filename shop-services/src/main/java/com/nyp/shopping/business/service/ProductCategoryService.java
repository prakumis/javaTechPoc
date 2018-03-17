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

	List<ProductCategoryVO> findCategoriesByParentId(Long parentId);

	List<ProductCategoryVO> findTopCategoriesByStatus(Boolean status);

	List<ProductCategoryVO> findCategoriesByParentIdAndStatus(Long parentId, Boolean status);

	List<ProductCategoryVO> getCategoryById(Long id);

	Long createCategory(ProductCategoryVO category);

	ProductCategoryVO updateCategory(ProductCategoryVO category);

	ProductCategoryVO updateStatus(ProductCategoryVO categoryVO);

	void deleteCategory(ProductCategoryVO category);

}
