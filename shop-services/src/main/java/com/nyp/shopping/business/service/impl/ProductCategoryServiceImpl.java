/**
 * 
 */
package com.nyp.shopping.business.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.nyp.shopping.business.application.ProductCategoryAS;
import com.nyp.shopping.business.service.ProductCategoryService;
import com.nyp.shopping.business.service.adaptor.ProductCategoryAdaptor;
import com.nyp.shopping.common.entity.ProductCategory;
import com.nyp.shopping.common.vo.ProductCategoryVO;

/**
 * @author pmis30
 *
 */
@Repository
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Inject
	private ProductCategoryAS productCategoryAS;

	@Inject
	private ProductCategoryAdaptor productCategoryAdaptor;

	@Override
	public List<ProductCategoryVO> findAllCategories() {
		List<ProductCategory> productCategoryList = productCategoryAS.findAllCategories();
		return productCategoryAdaptor.toVO(productCategoryList);
	}

	@Override
	public List<ProductCategoryVO> findTopCategories() {
		List<ProductCategory> productCategoryList = productCategoryAS.findTopCategories();
		return productCategoryAdaptor.toVO(productCategoryList);
	}

	@Override
	public List<ProductCategoryVO> getCategoryById(Long id) {
		List<ProductCategory> productCategoryList = productCategoryAS.getCategoryById(id);
		return productCategoryAdaptor.toVO(productCategoryList);
	}

	@Override
	public Long createCategory(ProductCategoryVO productCategoryVO) {
		productCategoryVO.setId(null);
		ProductCategory productCategory = productCategoryAdaptor.fromVO(productCategoryVO);
		return productCategoryAS.createCategory(productCategory);
	}

	@Override
	public ProductCategoryVO updateCategory(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = productCategoryAdaptor.fromVO(productCategoryVO);
		productCategory = productCategoryAS.updateCategory(productCategory);
		return productCategoryAdaptor.toVO(productCategory);
	}

	@Override
	public void deleteCategory(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(productCategoryVO.getId());
		productCategoryAS.deleteCategory(productCategory);
	}

}
