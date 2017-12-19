/**
 * 
 */
package com.nyp.shopping.business.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * @author hello
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource({
	  "classpath:/config/spring/shoppingApp-service-main.xml",
	  "classpath:/config/spring/shoppingApp-persistence.xml",
	  "classpath:/config/spring/appContext-dataSource-main.xml"
	})
public class ApplicationConfig {

}
