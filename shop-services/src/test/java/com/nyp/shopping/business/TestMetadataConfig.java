package com.nyp.shopping.business;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Good Read:
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html
 * 
 * @author pmis30
 *
 */
//@ActiveProfiles("testMysql")
//@Profile("testMysql")
//@ActiveProfiles("testHsql")
//@Profile("testHsql")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = { TestMetadataConfig.class })
@ImportResource({ "classpath*:config/spring/shoppingApp-persistence.xml", "classpath*:config/spring/shoppingApp-service.xml" })
//@ImportResource({ "classpath*:/config/spring/shoppingApp-service-test.xml" })
@Configuration
@Transactional(value = TxType.NOT_SUPPORTED)
@RunWith(SpringJUnit4ClassRunner.class)
public @interface TestMetadataConfig {
}
