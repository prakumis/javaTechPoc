package com.nyp.shopping.web.argumentresolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.nyp.shopping.common.vo.UserVO;

public class UserProfileHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {

		UserVO userProfile = new UserVO();
		HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies) {
			for(Cookie cookie : cookies){
			    if(cookie.getName().startsWith("u")){
			        //userProfile.setUserID(Long.valueOf(cookie.getValue()));
			    }
			}
		}
		if(null!=request.getParameter("uid")) {
			userProfile.setLoggedInUserId(Long.valueOf(request.getParameter("uid")));
		}
        if(null==userProfile.getUserID()) {
        	userProfile.setLoggedInUserId(1l);
        }
		return userProfile;
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(UserVO.class);
	}

}
