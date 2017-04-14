package com.nyp.shopping.business.application.impl;

import java.util.List;

import javax.management.ServiceNotFoundException;
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

@ContextConfiguration ( classes = { ProductCategoryASImplTest.class } )
@ImportResource ( { "classpath*:/config/spring/shoppingApp-persistence.xml",
        "classpath*:/config/spring/shoppingApp-service.xml"} )
@Configuration
@Transactional (value=TxType.NOT_SUPPORTED)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductCategoryASImplTest {

    @Autowired
    private ProductCategoryAS productCategoryAS;

    @Before
    public void prepareDB() {
    	System.out.println("Before: "+productCategoryAS);
    }

    @Test
    @Rollback ( true )
	public void findAllCategories() {
    	List<ProductCategory> productCategoryList = productCategoryAS.findAllCategories();
    	Assert.assertNotNull(productCategoryList);
    	Assert.assertTrue(productCategoryList.size()>0);
	}

    @Test
    @Rollback ( true )
	public void findTopCategories() {
    	List<ProductCategory> productCategoryList = productCategoryAS.findTopCategories();
    	Assert.assertNotNull(productCategoryList);
    	Assert.assertTrue(productCategoryList.size()>0);
	}

    @Test
	public void getCategoryById() throws ServiceNotFoundException {
		List<ProductCategory> productCategoryList = productCategoryAS.getCategoryById(1L);
		Assert.assertNotNull(productCategoryList);
	}

    @Test(expected=EntityNotFoundException.class)
	public void getCategoryById_shouldThrowEntityNotFoundException() throws ServiceNotFoundException {
		List<ProductCategory> productCategoryList = productCategoryAS.getCategoryById(1111L);
		Assert.assertNotNull(productCategoryList);
	}

    @Test
	public void createCategory() {
    	ProductCategory productCategory= new ProductCategory();
    	productCategory.setName("Test");
		Long id = productCategoryAS.createCategory(productCategory);
		Assert.assertNotNull(id);
	}

}
