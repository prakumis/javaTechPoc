package com.nyp.shopping.web.controller;

import com.nyp.shopping.common.constants.WebConstants;
import com.nyp.shopping.common.vo.ProductCategoryVO;

public class BaseControllerTest {

	private static int count = 0;
	public static final String SUPPORTED_CONTENT_TYPE = WebConstants.MEDIA_TYPE_JSON_VERSION_0_1;
	public static String saveRequestJsonString(ProductCategoryVO ad) {
		StringBuilder sb = new StringBuilder().append("{\n ");
		if(null!=ad.getName()) {
			sb.append("  \"name\": \"" + ad.getName() + "\" ");
		}
		if(null!=ad.getDescription()) {
			if(sb.toString().endsWith(" ")) {
				sb.append(",\n ");
			}
			sb.append("  \"description\": \"" + ad.getDescription()+ "\" ");
		}
		if(null!=ad.getParentCategoryId()) {
			if(sb.toString().endsWith(" ")) {
				sb.append(",\n ");
			}
			sb.append("  \"parentCategoryId\": \"" + ad.getParentCategoryId()+ "\" ");
		}
		if(null!=ad.getIsLeaf()) {
			if(sb.toString().endsWith(" ")) {
				sb.append(",\n ");
			}
			sb.append("  \"isLeaf\": \"" + ad.getIsLeaf()+ "\" ");
		}
		sb.append("}");
		return sb.toString();
		//return "{\n" + "  \"name\": \"" + ad.getName() + "\",\n" + "  \"description\": \"" + ad.getDescription()+ "\"\n" + "}";
	}

	public ProductCategoryVO createVO(String pcName) {
		ProductCategoryVO ad = new ProductCategoryVO();
		ad.setName(++count + pcName);
		ad.setDescription("description");
		ad.setIsLeaf(true);
		return ad;
	}

}
