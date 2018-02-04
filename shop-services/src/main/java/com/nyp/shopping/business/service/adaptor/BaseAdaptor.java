/**
 * 
 */
package com.nyp.shopping.business.service.adaptor;

import java.util.Date;

import com.nyp.shopping.common.entity.AbstractCommonEntity;
import com.nyp.shopping.common.entity.RecordInfo;
import com.nyp.shopping.common.entity.User;
import com.nyp.shopping.common.vo.BaseVO;

/**
 * @author
 *
 */
public class BaseAdaptor {

	public RecordInfo toRecordInfo(BaseVO baseVO, AbstractCommonEntity commonEntity) {

		RecordInfo recordInfo = new RecordInfo();
		recordInfo.setCreatedDate(baseVO.getCreatedDate());
		recordInfo.setModifiedDate(baseVO.getModifiedDate());
		return recordInfo;
	}

	public void fromVO(BaseVO baseVO, AbstractCommonEntity commonEntity, boolean isCreateMode) {

		if(isCreateMode) {
			User createdByUser = new User();
			createdByUser.setId(baseVO.getLoggedInUserId());
			commonEntity.setCreatedBy(createdByUser);
			commonEntity.setCreatedDate(new Date());
		} else {
			User modifiedByUser = new User();
			modifiedByUser.setId(baseVO.getLoggedInUserId());
			commonEntity.setModifiedBy(modifiedByUser);
			commonEntity.setModifiedDate(new Date());
		}
	}

}
