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

import com.nyp.shopping.business.application.ProductCategoryAS;
import com.nyp.shopping.business.repository.ProductCategoryRepository;
import com.nyp.shopping.common.entity.ProductCategory;

/**
 * @author pmis30
 *
 */
@Service
public class ProductCategoryASImpl extends BaseASImpl implements ProductCategoryAS {

	@Inject
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public List<ProductCategory> findCategoriesByParentId(Long parentId) {
		if (null == parentId) {
			return productCategoryRepository.findTopCategories();
		} else {
			return productCategoryRepository.findCategoriesByParentId(parentId);
		}
	}

	@Override
	public List<ProductCategory> findCategoriesByStatus(Boolean status) {
		return productCategoryRepository.findCategoriesByStatus(status);
	}

	@Override
	public List<ProductCategory> findCategoriesByParentIdAndStatus(Long parentId, Boolean status) {
		checkIfEntityExists(parentId);
		return productCategoryRepository.findCategoriesByParentIdAndStatus(parentId, status);
	}

	@Override
	public List<ProductCategory> getCategoryById(Long id) {
		checkIfEntityExists(id);
		List<ProductCategory> productCategoryList = new ArrayList<>();
		ProductCategory productCategory = productCategoryRepository.getOne(id);
		productCategoryList.add(productCategory);
		return productCategoryList;
	}

	private void checkIfEntityExists(Long id) {

		ProductCategory productCategory = productCategoryRepository.findOne(id);
		if (null == productCategory) {
			throw new EntityNotFoundException(String.format("Requested entity %s not found", id));
		}
	}

	@Override
	public Long createCategory(ProductCategory productCategory) {
		productCategory.setId(null);
		if(null!=productCategory.getParentCategory() && null!= productCategory.getParentCategory().getId()) {
			checkIfParentExistsAsLeaf(productCategory.getParentCategory().getId());
		}
		ProductCategory newProductCategory = productCategoryRepository.save(productCategory);
		return newProductCategory.getId();
	}

	private void checkIfParentExistsAsLeaf(Long id) {

		ProductCategory productCategory = productCategoryRepository.findOne(id, Boolean.TRUE);
		if (null == productCategory) {
			throw new EntityNotFoundException(String.format("Requested entity %s as leaf node not found", id));
		}
	}

	@Override
	public ProductCategory updateCategory(ProductCategory pc) {
		checkIfEntityExists(pc.getId());
		productCategoryRepository.update(pc.getId(),
				null == pc.getParentCategory() ? null : pc.getParentCategory().getId(), pc.getName(),
				pc.getDescription(), pc.getModifiedBy().getId(), new Date());
		return pc;
	}

	@Override
	public ProductCategory updateStatus(ProductCategory productCategory) {
		checkIfEntityExists(productCategory.getId());
		productCategoryRepository.updateStatus(productCategory.getId(), productCategory.isValid(),
				productCategory.getModifiedBy().getId(), new Date());
		return productCategory;
	}

	@Override
	public void deleteCategory(ProductCategory productCategory) {
		checkIfEntityExists(productCategory.getId());
		productCategoryRepository.delete(productCategory.getId());
	}

}
