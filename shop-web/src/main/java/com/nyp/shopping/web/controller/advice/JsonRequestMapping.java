package com.nyp.shopping.web.controller.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyp.shopping.common.constants.WebConstants;

/**
 * This class is Not Used as it should be.
 * @author pmis30
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@RequestMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" }, headers = { "X-API-Version=v1", "X-API-Version=v1" })
  @RequestMapping(consumes = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1, WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 }, produces = { WebConstants.MEDIA_TYPE_XML_VERSION_0_1, WebConstants.MEDIA_TYPE_JSON_VERSION_0_1 })
public @interface JsonRequestMapping {
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "method")
    RequestMethod[] method() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "params")
    String[] params() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "headers")
    String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "consumes")
    String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "produces")
    String[] produces() default {};
}