package com.nyp.shopping.common.logging;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to exclude return values from logging.
 * 
 * @author java developer
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludeAfterReturningLogging {

}
