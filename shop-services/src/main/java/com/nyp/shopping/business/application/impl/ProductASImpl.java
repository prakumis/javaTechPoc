/**
 * 
 */
package com.nyp.shopping.business.application.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.ProductAS;
import com.nyp.shopping.business.repository.ProductCategoryRepository;
import com.nyp.shopping.business.repository.ProductRepository;
import com.nyp.shopping.common.entity.Product;

/**
 * @author pmis30
 *
 */
@Service
public class ProductASImpl extends BaseASImpl implements ProductAS {

	@Inject
	private ProductRepository productRepository;

	@Inject
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAllProducts(Long catId) {
		//checkIfEntityExists(catId);
		List<Product> productList = new ArrayList<>();
		productList.addAll(productRepository.findProductsByCatId(catId));
		return productList;
	}

	@Override
	public List<Product> findProductById(Long catId, Long id) {
		//checkIfEntityExists(catId);
		List<Product> productList = new ArrayList<>();
		productList.addAll(productRepository.findProductByCatIdAndId(catId, id));
		return productList;
	}

	@Override
	public List<Product> findProductByStatus(Long catId, Boolean status) {
		List<Product> productList = new ArrayList<>();
		productList.addAll(productRepository.findProductByCatIdAndStatus(catId, status));
		return productList;
	}

	private void checkIfEntityExists(Long id, Long catId, boolean statusUpdateQuery) {

		Product product;
		if(statusUpdateQuery) {
			product = productRepository.findOne(catId, id);
		} else {
			product = productRepository.findOne(catId, id, true);
		}
		if(null == product) {
			throw new EntityNotFoundException(
					String.format("Requested entity/parent %s/%s not found", id, catId));
		}
	}

	@Override
	public Long createProduct(Product product) {
		product.setId(null);
		Product newProduct = productRepository.save(product);
		return newProduct.getId();
	}

	@Override
	public Product updateProduct(Product product) {
		checkIfEntityExists(product.getId(), product.getCategory().getId(), false);
		productRepository.update(product.getId(), product.getCategory().getId(), product.getName(),
				product.getDescription(), product.getModifiedBy().getId(), new Date());
		return product;
	}

	@Override
	public void updateCategory(Product product, Long newCatId) {

		checkIfEntityExists(product.getId(), product.getCategory().getId(), true);
		checkIfEntityExists(newCatId, productCategoryRepository);
		productRepository.updateCategory(product.getId(), newCatId, product.getModifiedBy().getId(), new Date());
	}

	@Override
	public void updateStatus(Product product) {
		checkIfEntityExists(product.getId(), product.getCategory().getId(), true);
		productRepository.updateStatus(product.getId(), product.isValid(), product.getModifiedBy().getId(), new Date());
	}

	@Override
	public void deleteProduct(Product product) {
		checkIfEntityExists(product.getId(), product.getCategory().getId(), false);
		productRepository.updateStatus(product.getId(), product.isValid(), product.getModifiedBy().getId(), new Date());
	}

}
