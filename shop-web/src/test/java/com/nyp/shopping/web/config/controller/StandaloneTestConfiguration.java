/**
 * 
 */
package com.nyp.shopping.web.config.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nyp.shopping.web.controller.ProductCategoryController;

/**
 * 
 * CRUD Operations:
 * 
 * 
 * @author pmis30
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StandaloneTestConfiguration {

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductCategoryController()).build();
	}

	@Test
	public void testFirstMethod() throws Exception {
		System.out.println("First Method stated: " + mockMvc);
		mockMvc.perform(get("/cat/test").accept("application/vnd.shop.app-v0.1+json").contentType("application/vnd.shop.app-v0.1+json"));
		System.out.println("First Method Completed");
	}
}
