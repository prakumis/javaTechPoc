package com.nyp.shopping.web.controller.advice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.nyp.shopping.common.vo.BaseVO;
import com.nyp.shopping.web.model.ResponseBean;

@ControllerAdvice
public class RestResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		System.out.println("returnType: " + returnType);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		Long elapsedTime = System.currentTimeMillis() - (Long) servletRequest.getAttribute("startTime");
		if (body instanceof List) {
			ResponseBean<List<BaseVO>> responseBean = new ResponseBean<>();
			responseBean.setData((List<BaseVO>) body);
			responseBean.setTotalResponseTime(elapsedTime);
			return responseBean;
		} else if (body instanceof BaseVO) {
			/**
			 * This approach is not suggested. More Read:
			 * https://www.narwhl.com/resource-specific-responses/
			 */
			ResponseBean<Object> responseBean = new ResponseBean<>();
			responseBean.setData(body);
			responseBean.setTotalResponseTime(elapsedTime);
			return responseBean;
		} else if (body instanceof ResponseBean) {
			HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
			servletResponse.setStatus(((ResponseBean<Object>) body).getHttpStatucCode());
			((ResponseBean<Object>) body).setTotalResponseTime(elapsedTime);
			return body;
		}
		return body;
	}
}