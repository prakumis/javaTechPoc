package com.nyp.shopping.business.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nyp.shopping.business.service.ProductCategoryService;
import com.nyp.shopping.common.vo.ProductCategoryVO;

@ContextConfiguration(classes = { ProductCategoryServiceImplTest.class })
@ImportResource({ "classpath*:/config/spring/shoppingApp-persistence.xml",
		"classpath*:/config/spring/shoppingApp-service.xml" })
@Configuration
@Transactional(value = TxType.NOT_SUPPORTED)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductCategoryServiceImplTest {

	@Autowired
	private ProductCategoryService productCategoryService;

	@Before
	public void prepareDB() {
		System.out.println("Before: " + productCategoryService);
	}

	@Test
	@Rollback(true)
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
		productCategoryVO.setName("Test Name for Create");
		try {
			Long id = productCategoryService.createCategory(productCategoryVO);
			Assert.assertNotNull(id);
		} catch (DataAccessException e) {
			// exception will be caught if name is not set before CREATE above
			System.out.println(e);
		}
	}

	@Test
	public void updateCategory() {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		productCategoryVO.setName("Test Name for Update");
		Long id = productCategoryService.createCategory(productCategoryVO);
		System.out.println("******** Created productCategoryVO for UPDATE: " + productCategoryVO.getId());
		String updatedName = "Updating Test";
		productCategoryVO.setId(id);
		productCategoryVO.setName(updatedName);
		ProductCategoryVO updatedProductCategoryVO = productCategoryService.updateCategory(productCategoryVO);
		Assert.assertEquals(updatedProductCategoryVO.getName(), updatedName);
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteCategory() {
		ProductCategoryVO productCategory = new ProductCategoryVO();
		productCategory.setName("Product Category for Delete");

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
