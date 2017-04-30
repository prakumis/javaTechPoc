package com.nyp.shopping.business.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nyp.shopping.business.AbstractCommonTest;
import com.nyp.shopping.business.TestMetadataConfig;
import com.nyp.shopping.business.service.ProductCategoryService;
import com.nyp.shopping.common.vo.ProductCategoryVO;

@TestMetadataConfig
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductCategoryServiceImplTest extends AbstractCommonTest {

    static final String STATEMENT_SEPARATOR = ";;";
    static final String INIT_DB_FILE_NAME = "productNutrition.sql";

	@Autowired
	private ProductCategoryService productCategoryService;

	@Before
	public void prepareDB() {
		System.out.println("Before: " + productCategoryService);
	}

	@Test
	public void findAllCategories() {
		List<ProductCategoryVO> productCategoryList = productCategoryService.findAllCategories();
		Assert.assertNotNull(productCategoryList);
		Assert.assertFalse(productCategoryList.isEmpty());
	}

	@Test
	public void findTopCategories() {
		List<ProductCategoryVO> productCategoryList = productCategoryService.findTopCategories();
		Assert.assertNotNull(productCategoryList);
		Assert.assertFalse(productCategoryList.isEmpty());
	}

	@Test
	public void getCategoryById() {
		List<ProductCategoryVO> productCategoryList = productCategoryService.getCategoryById(1L);
		Assert.assertNotNull(productCategoryList);
		Assert.assertFalse(productCategoryList.isEmpty());
	}

	/**
	 * Also test following scenario: 1) validation error for missing mandatory
	 * fields 2) validation error for data size exceeding the column length 3) A
	 * created objects to match all the fields given as input in CREATE method
	 * 
	 */
	@Test
	public void createCategory() {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		productCategoryVO.setId(null);
		productCategoryVO.setName("ProductCategoryServiceImplTest.createCategory()");
		productCategoryVO.setDescription("Some Description");
		Long id = productCategoryService.createCategory(productCategoryVO);
		Assert.assertNotNull(id);
	}

	@Test
	public void updateCategory() {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		productCategoryVO.setName("ProductCategoryServiceImplTest.updateCategory_1()");
		Long id = productCategoryService.createCategory(productCategoryVO);
		System.out.println("******** Created productCategoryVO for UPDATE: " + productCategoryVO.getId());
		String updatedName = "ProductCategoryServiceImplTest.updateCategory()";
		productCategoryVO.setId(id);
		productCategoryVO.setName(updatedName);
		ProductCategoryVO updatedProductCategoryVO = productCategoryService.updateCategory(productCategoryVO);
		Assert.assertEquals(updatedProductCategoryVO.getName(), updatedName);
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteCategory() {
		ProductCategoryVO productCategory = new ProductCategoryVO();
		productCategory.setName("ProductCategoryServiceImplTest.deleteCategory()");

		Long id = productCategoryService.createCategory(productCategory);
		List<ProductCategoryVO> createdProductCategory = productCategoryService.getCategoryById(id);
		Assert.assertFalse(createdProductCategory.isEmpty());
		System.out.println("******** Created productCategory for DELETE: " + createdProductCategory.get(0).getId());
		productCategory.setId(id);
		productCategoryService.deleteCategory(productCategory);
		System.out.println("productCategory: " + productCategory);
		Assert.assertNotNull(productCategory.getId());
		productCategoryService.getCategoryById(productCategory.getId());
	}

}
