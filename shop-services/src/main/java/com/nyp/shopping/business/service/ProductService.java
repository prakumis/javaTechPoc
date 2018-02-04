package com.nyp.shopping.business.service;
/**
 * 
 */

import java.util.List;

import com.nyp.shopping.common.vo.ProductVO;

/**
 * @author pmis30
 *
 */
public interface ProductService {

	List<ProductVO> findAllProducts();

	List<ProductVO> findAllProducts(Long catId);

	List<ProductVO> findProductById(Long catId, Long id);

	List<ProductVO> findProductByStatus(Long catId, Boolean status);

	Long createProduct(ProductVO category);

	ProductVO updateProduct(ProductVO category);

	void updateCategory(ProductVO productVO, Long newCatId);

	void updateStatus(ProductVO category);

	void deleteProduct(ProductVO category);

}
