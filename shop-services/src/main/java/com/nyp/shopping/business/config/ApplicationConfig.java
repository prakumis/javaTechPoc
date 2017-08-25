/**
 * 
 */
package com.nyp.shopping.business.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hello
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationConfig {

}
