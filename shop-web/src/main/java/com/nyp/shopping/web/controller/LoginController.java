/**
 * 
 */
package com.nyp.shopping.web.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nyp.shopping.business.service.UserSecurityService;
import com.nyp.shopping.common.vo.CustomerVO;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author JavaDeveloper
 *
 */
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {

	@Inject
	private UserSecurityService userSecurityService;

	/**
	 * 
	 */
	public LoginController() {
		// no-arg constructor
	}

    @RequestMapping ( method = RequestMethod.POST, path = "/login" )
    public CustomerVO login(@RequestBody UserVO userVO, @RequestParam String username) {
        logger.debug( "User Login: %s - {}", userVO.getEmailMobile());
       	return userSecurityService.validateUserLogin(userVO);
    }

}
