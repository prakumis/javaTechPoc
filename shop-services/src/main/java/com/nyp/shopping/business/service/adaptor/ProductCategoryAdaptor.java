/**
 * 
 */
package com.nyp.shopping.business.service.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nyp.shopping.common.entity.ProductCategory;
import com.nyp.shopping.common.vo.ProductCategoryVO;

/**
 * @author pmis30
 *
 */
@Component
public class ProductCategoryAdaptor extends BaseAdaptor {

	@Autowired
	private RecordInfoAdaptor recordInfoAdaptor;

	public List<ProductCategoryVO> toVO(List<ProductCategory> productCategoryList) {

		List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
		ListIterator<ProductCategory> listIterator = productCategoryList.listIterator();
		while (listIterator.hasNext()) {
			ProductCategory productCategory = listIterator.next();
			ProductCategoryVO productCategoryVO = toVO(productCategory);
			productCategoryVOList.add(productCategoryVO);
		}
		return productCategoryVOList;
	}

	public ProductCategoryVO toVO(ProductCategory productCategory) {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		BeanUtils.copyProperties(productCategory, productCategoryVO, "subCategories");
		recordInfoAdaptor.fromRecordInfo(productCategoryVO, productCategory.getRecordInfo());
		return productCategoryVO;
	}

	public ProductCategory fromVO(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = new ProductCategory();
		BeanUtils.copyProperties(productCategoryVO, productCategory);
		productCategory.setRecordInfo(recordInfoAdaptor.toRecordInfo(productCategoryVO));
		return productCategory;
	}

}
