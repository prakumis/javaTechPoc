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
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackForClassName = "Exception", isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class ProductCategoryServiceImpl extends BaseServiceImpl implements ProductCategoryService {

	static {
		// Following line is printed twice,
		// 1) execution of service project - jar
		// 2) execution of web project - war
		// means this class and its project is loaded twice
		System.out.println("\n\n\n\n\n********************************Static block initialized");
	}
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
	public List<ProductCategoryVO> findCategoriesByParentId(Long parentId) {
		List<ProductCategory> productCategoryList = productCategoryAS.findCategoriesByParentId(parentId);
		return productCategoryAdaptor.toVO(productCategoryList);
	}

	@Override
	public List<ProductCategoryVO> getCategoryById(Long id) {
		List<ProductCategory> productCategoryList = productCategoryAS.getCategoryById(id);
		return productCategoryAdaptor.toVO(productCategoryList);
	}

	@Override
	public List<ProductCategoryVO> findCategoriesByStatus(Boolean status) {
		List<ProductCategory> productCategoryList = productCategoryAS.findCategoriesByStatus(status);
		return productCategoryAdaptor.toVO(productCategoryList);
	}

	@Override
	public Long createCategory(ProductCategoryVO productCategoryVO) {
		productCategoryVO.setId(null);
		ProductCategory productCategory = productCategoryAdaptor.fromVO(productCategoryVO, true);
		return productCategoryAS.createCategory(productCategory);
	}

	@Override
	public ProductCategoryVO updateCategory(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = productCategoryAdaptor.fromVO(productCategoryVO, false);
		productCategory = productCategoryAS.updateCategory(productCategory);
		return productCategoryAdaptor.toVO(productCategory);
	}

	@Override
	public ProductCategoryVO updateStatus(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = productCategoryAdaptor.fromVO(productCategoryVO, false);
		productCategory = productCategoryAS.updateStatus(productCategory);
		return productCategoryAdaptor.toVO(productCategory);
	}

	@Override
	public void deleteCategory(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(productCategoryVO.getId());
		productCategoryAS.deleteCategory(productCategory);
	}

}
