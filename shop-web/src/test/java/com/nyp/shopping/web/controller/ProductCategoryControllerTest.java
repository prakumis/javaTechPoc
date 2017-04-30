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
@ContextConfiguration(classes = { WebConfig.class })
public class ProductCategoryControllerTest {

	private static int setUpCounter=0;
	private static int testCounter=0;
	private static boolean done=false;
	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		if(!done) {
			printBeans();
		}
		System.out.println("Setup started: "+ ++setUpCounter);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("Setup Completed: "+ setUpCounter);
	}

	private void printBeans() {
		// TODO Auto-generated method stub
		String[] beanNames = ctx.getBeanDefinitionNames();
		for(String beanName: beanNames) {
			System.out.println("\n\n"+beanName+":"+ctx.getBean(beanName));
		}
		done=true;
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
		System.out.println("testFindCategoryById() started: "+ ++testCounter);
		mockMvc.perform(get("/cat/4").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.data").exists());
		System.out.println("testFindCategoryById() completed: "+testCounter);
	}

	@Test
	public void testFindCategoryById_throws404NotFound() throws Exception {
		System.out.println("testFindCategoryById() started: "+ ++testCounter);
		mockMvc.perform(get("/cat/41").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		System.out.println("testFindCategoryById_throws404NotFound() completed: "+testCounter);
	}

	/**
	 * http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.test.web.servlet.request.MockMvcRequestBuilders
	 * http://www.programcreek.com/java-api-examples/index.php?source_dir=spring-rest-black-market-master/src/test/java/org/vtsukur/spring/rest/market/AdsHttpApiTests.java
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCategory() throws Exception {
		ProductCategoryVO ad = createVO("ProductCategoryControllerTest.testCreateCategory()");
		String requestBody = saveRequestJsonString(ad);
		System.out.println("requestBody: "+requestBody);
		mockMvc.perform(post("/cat").accept("application/vnd.shop.app-v0.1+json").content(requestBody)
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	private ProductCategoryVO createVO(String pcName) { 
		ProductCategoryVO ad = new ProductCategoryVO(); 
        ad.setName(pcName);
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
		ProductCategoryVO ad = createVO("ProductCategoryControllerTest.testUpdateCategory()");
		String requestBody = saveRequestJsonString(ad);
		mockMvc.perform(put("/cat/4").accept("application/vnd.shop.app-v0.1+json").content(requestBody)
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testDeleteCategory() throws Exception {
		System.out.println("testDeleteCategory() started: "+ ++testCounter);
		mockMvc.perform(delete("/cat/30000").accept("application/vnd.shop.app-v0.1+json")
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		System.out.println("testDeleteCategory() completed: "+ testCounter);
	}
}
