/**
 * 
 */
package com.nyp.shopping.business.service.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.nyp.shopping.common.entity.User;
import com.nyp.shopping.common.vo.UserVO;

/**
 * @author pmis30
 *
 */
@Component
public class UserAdaptor extends BaseAdaptor {

	public List<UserVO> toVO(List<User> productCategoryList) {

		List<UserVO> productCategoryVOList = new ArrayList<>();
		ListIterator<User> listIterator = productCategoryList.listIterator();
		while (listIterator.hasNext()) {
			User productCategory = listIterator.next();
			UserVO productCategoryVO = toVO(productCategory);
			productCategoryVOList.add(productCategoryVO);
		}
		return productCategoryVOList;
	}

	public UserVO toVO(User user) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO, "subCategories");
		//recordInfoAdaptor.fromRecordInfo(userVO, user.getRecordInfo());
		return userVO;
	}

	public User fromVO(UserVO userVO) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		//user.setRecordInfo(recordInfoAdaptor.toRecordInfo(userVO));
		return user;
	}

}
