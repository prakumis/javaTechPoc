/**
 * 
 */
package com.nyp.shopping.business.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nyp.shopping.business.application.ProductAS;
import com.nyp.shopping.business.service.ProductService;
import com.nyp.shopping.business.service.adaptor.ProductAdaptor;
import com.nyp.shopping.common.entity.Product;
import com.nyp.shopping.common.entity.ProductCategory;
import com.nyp.shopping.common.entity.User;
import com.nyp.shopping.common.vo.ProductVO;

/**
 * @author pmis30
 *
 */
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackForClassName = "Exception", isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	static {
		// Following line is printed twice,
		// 1) execution of service project - jar
		// 2) execution of web project - war
		// means this class and its project is loaded twice
		System.out.println("\n\n\n\n\n**********************ProductServiceImpl.Static block initialized");
	}

	@Inject
	private ProductAS productAS;

	@Inject
	private ProductAdaptor productAdaptor;

	@Override
	public List<ProductVO> findAllProducts() {
		List<Product> productList = productAS.findAllProducts();
		return productAdaptor.toVO(productList);
	}

	@Override
	public List<ProductVO> findAllProducts(Long id) {
		List<Product> productList = productAS.findAllProducts(id);
		return productAdaptor.toVO(productList);
	}

	@Override
	public List<ProductVO> findProductById(Long catId, Long id) {
		List<Product> productList = productAS.findProductById(catId, id);
		return productAdaptor.toVO(productList);
	}

	@Override
	public List<ProductVO> findProductByStatus(Long catId, Boolean status) {
		List<Product> productList = productAS.findProductByStatus(catId, status);
		return productAdaptor.toVO(productList);
	}

	@Override
	public Long createProduct(ProductVO productVO) {
		productVO.setId(null);
		Product product = productAdaptor.fromVO(productVO, true);
		return productAS.createProduct(product);
	}

	@Override
	public ProductVO updateProduct(ProductVO productVO) {
		Product product = productAdaptor.fromVO(productVO, false);
		product = productAS.updateProduct(product);
		return productAdaptor.toVO(product);
	}

	@Override
	public void updateCategory(ProductVO productVO, Long newCatId) {

		Product product = productAdaptor.fromVO(productVO, false);
		productAS.updateCategory(product, newCatId);
	}

	@Override
	public void updateStatus(ProductVO productVO) {
		Product product = productAdaptor.fromVO(productVO, false);
		productAS.updateStatus(product);
	}

	@Override
	public void deleteProduct(ProductVO productVO) {
		Product product = new Product();
		product.setId(productVO.getId());
		product.setCategory(new ProductCategory(productVO.getCatId()));
		product.setModifiedBy(new User(productVO.getLoggedInUserId()));
		productAS.deleteProduct(product);
	}

}
