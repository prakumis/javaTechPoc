package com.nyp.shopping.business.service.adaptor;

import com.nyp.shopping.common.entity.RecordInfo;
import com.nyp.shopping.common.vo.BaseVO;

public class RecordInfoAdaptor {

	public static RecordInfo toRecordInfo(BaseVO baseVO) {

		RecordInfo recordInfo = new RecordInfo();
		recordInfo.setCreatedBy(baseVO.getCreatedBy());
		recordInfo.setCreatedDate(baseVO.getCreatedDate());
		recordInfo.setModifiedBy(baseVO.getModifiedBy());
		recordInfo.setModifiedDate(baseVO.getModifiedDate());
		return recordInfo;
	}

	public static BaseVO fromRecordInfo(BaseVO baseVO, RecordInfo recordInfo) {

		if(null==recordInfo) {
			return null;
		}
		baseVO.setCreatedBy(recordInfo.getCreatedBy());
		baseVO.setCreatedDate(recordInfo.getCreatedDate());
		baseVO.setModifiedBy(recordInfo.getModifiedBy());
		baseVO.setModifiedDate(recordInfo.getModifiedDate());
		return baseVO;
	}

}
