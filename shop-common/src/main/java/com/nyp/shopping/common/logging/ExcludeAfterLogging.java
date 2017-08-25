package com.nyp.shopping.common.logging;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to exclude method return void from logging.
 * 
 * @author java developer
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludeAfterLogging {

}
