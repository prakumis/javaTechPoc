/**
 * 
 */
package com.nyp.shopping.business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hello
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages= {"com.nyp.shopping.business", "com.nyp.shopping.common"})
public class ApplicationConfig {

}
