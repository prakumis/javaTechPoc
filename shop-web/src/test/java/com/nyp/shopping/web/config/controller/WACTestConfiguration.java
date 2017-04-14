/**
 * 
 */
package com.nyp.shopping.web.config.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nyp.shopping.web.config.WebConfig;
import com.nyp.shopping.web.listener.LogbackConfigListener;

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
//@ContextConfiguration({ "classpath:config/spring/shoppingApp-servlet-test.xml"})
@ContextConfiguration(classes = { WebConfig.class })
public class WACTestConfiguration {

	public static LogbackConfigListener logbackConfigListener=new LogbackConfigListener();
	static {
		System.out.println("Static started");
		System.setProperty("catalina.home", "D:/Application/apache-tomcat-9.0.0.M9_shop");
		logbackConfigListener.contextInitialized(null);
		System.out.println("Static Completed");
	}

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
	public void testFirstMethod() throws Exception {
		System.out.println("\n\n\nFirst Method: "+mockMvc);
		mockMvc.perform(get("/cat/test").accept("application/vnd.shop.app-v0.1+json").contentType("application/vnd.shop.app-v0.1+json"));
		System.out.println("First Method Completed");
	}
}
