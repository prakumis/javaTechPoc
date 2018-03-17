/**
 * 
 */
package com.nyp.shopping.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nyp.shopping.common.vo.ProductCategoryVO;
import com.nyp.shopping.web.config.TestMetadataWebConfig;

/**
 * 
 * CRUD Operations:
 * 
 * 
 * @author pmis30
 *
 */
@TestMetadataWebConfig
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductCategoryControllerTest extends BaseControllerTest {

	private static int setUpCounter = 0;
	private static int testCounter = 0;
	private static boolean done = true;

	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		if (!done) {
			printBeans();
		}
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Setup started: " + ++setUpCounter);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("Setup Completed: " + setUpCounter);
	}

	private void printBeans() {
		// TODO Auto-generated method stub
		String[] beanNames = ctx.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println("\n\n" + beanName + ":" + ctx.getBean(beanName));
		}
		done = true;
	}

	//@Test
	public void testFindTopCategories1() throws Exception {
		mockMvc.perform(get("/cat").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindTopCategories2() throws Exception {
		mockMvc.perform(get("/cat/").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindTopCategories3() throws Exception {
		mockMvc.perform(get("/cat/parent").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindTopCategories4() throws Exception {
		mockMvc.perform(get("/cat/parent/").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindCategoryByParentId() throws Exception {
		mockMvc.perform(get("/cat/parent/4").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindCategoryByParentId_InvalidParent400() throws Exception {
		mockMvc.perform(get("/cat/parent/InvalidParent").accept(SUPPORTED_CONTENT_TYPE))
				.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.data").doesNotExist());
	}

	//@Test
	public void testFindTopCategoryByStatusDefault() throws Exception {
		mockMvc.perform(get("/cat/status").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindTopCategoryByStatus0() throws Exception {
		mockMvc.perform(get("/cat/status/0").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindTopCategoryByStatus1() throws Exception {
		mockMvc.perform(get("/cat/status/1").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindTopCategoryByStatus_InvalidStatus400() throws Exception {
		mockMvc.perform(get("/cat/status/5").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").doesNotExist());
	}

	//@Test
	public void testFindCategoryByParentIdAndStatusDefault() throws Exception {
		mockMvc.perform(get("/cat/parent/4/status").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindCategoryByParentIdAndStatus0() throws Exception {
		mockMvc.perform(get("/cat/parent/4/status/0").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindCategoryByParentIdAndStatus1() throws Exception {
		mockMvc.perform(get("/cat/parent/4/status/1").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindCategoryByParentIdAndStatus_InvalidParentId() throws Exception {
		mockMvc.perform(get("/cat/parent/-1/status/InvalidStatus").accept(SUPPORTED_CONTENT_TYPE))
				.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.data").doesNotExist());
	}

	//@Test
	public void testFindCategoryByParentIdAndStatus_InvalidStatus() throws Exception {
		mockMvc.perform(get("/cat/parent/4/status/InvalidStatus").accept(SUPPORTED_CONTENT_TYPE))
				.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.data").doesNotExist());
	}

	//@Test
	public void testFindCategoryById() throws Exception {
		mockMvc.perform(get("/cat/4").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.data").exists());
	}

	//@Test
	public void testFindCategoryById_EntityNotFound404() throws Exception {
		mockMvc.perform(get("/cat/412345").accept(SUPPORTED_CONTENT_TYPE)).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$.errorCode").exists());
	}
	/**
	 * http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.test.web.servlet.request.MockMvcRequestBuilders
	 * http://www.programcreek.com/java-api-examples/index.php?source_dir=spring-rest-black-market-master/src/test/java/org/vtsukur/spring/rest/market/AdsHttpApiTests.java
	 * 
	 * @throws Exception
	 */
	//@Test
	public void testCreateTopLevelCategory() throws Exception {
		ProductCategoryVO ad = createVO("Ctrl.testCreateTopLevelCategory");
		String requestBody = saveRequestJsonString(ad);
		System.out.println("requestBody: " + requestBody);
		mockMvc.perform(post("/cat").content(requestBody).contentType("application/vnd.shop.app-v0.1+json"))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCreateCategoryWithParent() throws Exception {
		ProductCategoryVO ad = createVO("Ctrl.testCreateCategoryWithParent");
		ad.setParentCategoryId(11l);
		String requestBody = saveRequestJsonString(ad);
		System.out.println("requestBody: " + requestBody);
		mockMvc.perform(post("/cat").content(requestBody).contentType("application/vnd.shop.app-v0.1+json"))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCreateCategoryWithParent_ParentIdNotFound404() throws Exception {
		ProductCategoryVO ad = createVO("Ctrl.testCreateCategoryWithParent");
		ad.setParentCategoryId(412345l);
		String requestBody = saveRequestJsonString(ad);
		System.out.println("requestBody: " + requestBody);
		mockMvc.perform(post("/cat").content(requestBody).contentType("application/vnd.shop.app-v0.1+json"))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCreateCategoryWithParent_ParentNotLeafNode() throws Exception {
		ProductCategoryVO ad = createVO("Ctrl.testCreateCategoryWithParent");
		ad.setParentCategoryId(34l);
		String requestBody = saveRequestJsonString(ad);
		System.out.println("requestBody: " + requestBody);
		mockMvc.perform(post("/cat").content(requestBody).contentType("application/vnd.shop.app-v0.1+json"))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}
	//============== Completed Till Here

	//@Test
	public void testUpdateCategory() throws Exception {
		ProductCategoryVO ad = createVO("CtrlUpdateCategory");
		String requestBody = saveRequestJsonString(ad);
		mockMvc.perform(put("/cat/4").param("uid", "1").accept(SUPPORTED_CONTENT_TYPE)
				.content(requestBody).contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	//@Test
	public void testUpdateStatus() throws Exception {
		ProductCategoryVO ad = createVO("ProductCategoryControllerTest.testUpdateCategory()");
		String requestBody = saveRequestJsonString(ad);
		mockMvc.perform(patch("/cat/4/status/true").param("uid", "1").accept(SUPPORTED_CONTENT_TYPE)
				.content(requestBody)).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	//@Test
	public void testDeleteCategory() throws Exception {
		System.out.println("testDeleteCategory() started: " + ++testCounter);
		mockMvc.perform(delete("/cat/30000").accept(SUPPORTED_CONTENT_TYPE)
				.contentType("application/vnd.shop.app-v0.1+json")).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		System.out.println("testDeleteCategory() completed: " + testCounter);
	}
}

/*

"1) Invalid parent category id
2) Parent Category is NOT leaf node
3) All mandatory field is Null or Empty
        Name
        Description field 
        Is leaf category
        Active status
4) All String fields with chars more than given size
        Name
        description
5) All boolean fields with invalid values
        Active status
        Is leaf node
"
*/