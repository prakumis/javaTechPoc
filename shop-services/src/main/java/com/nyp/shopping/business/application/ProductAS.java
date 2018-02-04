package com.nyp.shopping.business.application;
/**
 * 
 */

import java.util.List;

import com.nyp.shopping.common.entity.Product;

/**
 * @author pmis30
 *
 */
public interface ProductAS {

	List<Product> findAllProducts();

	List<Product> findAllProducts(Long catId);

	List<Product> findProductById(Long catId, Long id);

	List<Product> findProductByStatus(Long catId, Boolean status);

	Long createProduct(Product category);

	Product updateProduct(Product category);

	void updateCategory(Product product, Long newCatId);

	void updateStatus(Product category);

	void deleteProduct(Product category);

}
