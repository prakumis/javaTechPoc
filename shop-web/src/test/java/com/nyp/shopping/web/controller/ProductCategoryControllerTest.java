/**
 * 
 */
package com.nyp.shopping.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nyp.shopping.common.vo.ProductCategoryVO;
import com.nyp.shopping.web.config.WebConfig;

/**
 * 
 * CRUD Operations:
 * 
 * 
 * @author pmis30
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
// @ContextConfiguration({"classpath:config/spring/shoppingApp-servlet-test.xml"})
@ContextConfiguration(classes = { WebConfig.class })
public class ProductCategoryControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		System.out.println("Setup started");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("Setup Completed");
	}

	@Test
	public void testMethod() throws Exception {
		mockMvc.perform(get("/cat/test").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testFindAllCategories() throws Exception {
		mockMvc.perform(get("/cat").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testFindTopCategories() throws Exception {
		mockMvc.perform(get("/cat").accept("application/vnd.shop.app-v0.2+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testFindCategoryById() throws Exception {
		mockMvc.perform(get("/cat/4").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.data").exists());
	}

	/**
	 * http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.test.web.servlet.request.MockMvcRequestBuilders
	 * http://www.programcreek.com/java-api-examples/index.php?source_dir=spring-rest-black-market-master/src/test/java/org/vtsukur/spring/rest/market/AdsHttpApiTests.java
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCategory() throws Exception {
		ProductCategoryVO ad = createVO();
		String requestBody = saveRequestJsonString(ad);
		System.out.println("requestBody: "+requestBody);
		mockMvc.perform(post("/cat").accept("application/vnd.shop.app-v0.1+json").content(requestBody)
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	private ProductCategoryVO createVO() { 
		ProductCategoryVO ad = new ProductCategoryVO(); 
        ad.setName("need it now!"); 
        ad.setDescription("description referenceUser"); 
        return ad; 
    } 
 
    private static String saveRequestJsonString(ProductCategoryVO ad) { 
        return "{\n" + 
                "  \"name\": \"" + ad.getName() + "\",\n" + 
                "  \"description\": \"" + ad.getDescription() + "\"\n" + 
                "}"; 
    } 

	@Test
	public void testUpdateCategory() throws Exception {
		ProductCategoryVO ad = createVO();
		String requestBody = saveRequestJsonString(ad);
		mockMvc.perform(put("/cat/4").accept("application/vnd.shop.app-v0.1+json").content(requestBody)
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testDeleteCategory() throws Exception {
		mockMvc.perform(delete("/cat/30000").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}
}
