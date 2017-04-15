package com.nyp.shopping.business.application.impl;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nyp.shopping.business.application.ProductCategoryAS;
import com.nyp.shopping.common.entity.ProductCategory;

@ContextConfiguration(classes = { ProductCategoryASImplTest.class })
@ImportResource({ "classpath*:/config/spring/shoppingApp-persistence.xml",
		"classpath*:/config/spring/shoppingApp-service.xml" })
@Configuration
@Transactional(value = TxType.NOT_SUPPORTED)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductCategoryASImplTest {

	@Autowired
	private ProductCategoryAS productCategoryAS;

	@Before
	public void prepareDB() {
		System.out.println("Before: " + productCategoryAS);
	}

	@Test
	@Rollback(true)
	public void findAllCategories() {
		List<ProductCategory> productCategoryList = productCategoryAS.findAllCategories();
		Assert.assertNotNull(productCategoryList);
		Assert.assertTrue(productCategoryList.size() > 0);
	}

	@Test
	@Rollback(true)
	public void findTopCategories() {
		List<ProductCategory> productCategoryList = productCategoryAS.findTopCategories();
		Assert.assertNotNull(productCategoryList);
		Assert.assertTrue(productCategoryList.size() > 0);
	}

	@Test
	public void getCategoryById() {
		List<ProductCategory> productCategoryList = productCategoryAS.getCategoryById(1L);
		System.out.println("************* " + productCategoryList.size());
		Assert.assertNotNull(productCategoryList.size());
	}

	@Test(expected = EntityNotFoundException.class)
	public void getCategoryById_shouldThrowEntityNotFoundException() {
		productCategoryAS.getCategoryById(1111L);
	}

	@Test
	public void createCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName("Test");
		Long id = productCategoryAS.createCategory(productCategory);
		System.out.println("*****************Created successfully ProductCategory: " + id);
		Assert.assertNotNull(id);
	}

	@Test
	public void updateCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName("Product Category for Update");
		productCategoryAS.createCategory(productCategory);
		System.out.println("******** Created productCategory for UPDATE: " + productCategory.getId());
		String updatedName = "Updating Test";
		productCategory.setName(updatedName);
		ProductCategory updatedProductCategory = productCategoryAS.updateCategory(productCategory);
		Assert.assertEquals(updatedProductCategory.getName(), updatedName);
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName("Product Category for Delete");
		productCategoryAS.createCategory(productCategory);
		List<ProductCategory> createdProductCategory = productCategoryAS.getCategoryById(productCategory.getId());
		Assert.assertFalse(createdProductCategory.isEmpty());
		System.out.println("******** Created productCategory for DELETE: " + createdProductCategory.get(0).getId());
		productCategoryAS.deleteCategory(productCategory);
		System.out.println("productCategory: " + productCategory);
		Assert.assertNotNull(productCategory.getId());
		List<ProductCategory> deletedProductCategory = productCategoryAS.getCategoryById(productCategory.getId());
		Assert.assertFalse(deletedProductCategory.isEmpty());
	}

}