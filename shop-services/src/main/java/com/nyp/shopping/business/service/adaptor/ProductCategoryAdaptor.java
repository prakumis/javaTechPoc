/**
 * 
 */
package com.nyp.shopping.business.service.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.nyp.shopping.common.entity.ProductCategory;
import com.nyp.shopping.common.entity.RecordInfo;
import com.nyp.shopping.common.vo.ProductCategoryVO;

/**
 * @author pmis30
 *
 */
@Component
public class ProductCategoryAdaptor extends BaseAdaptor {

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
		RecordInfoAdaptor.fromRecordInfo(productCategoryVO, productCategory.getRecordInfo());
		return productCategoryVO;
	}

	public ProductCategory fromVO(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = new ProductCategory();
		BeanUtils.copyProperties(productCategoryVO, productCategory);
		return productCategory;
	}

	public ProductCategory fromVO_Old(ProductCategoryVO productCategoryVO) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(productCategoryVO.getId());
		productCategory.setName(productCategoryVO.getName());
		productCategory.setDescription(productCategoryVO.getDescription());
		RecordInfo recordInfo = RecordInfoAdaptor.toRecordInfo(productCategoryVO);
		productCategory.setRecordInfo(recordInfo);
		return productCategory;
	}

	public List<ProductCategoryVO> toVO_old(List<ProductCategory> productCategoryList) {

		List<ProductCategoryVO> productCategoryVOs = new ArrayList<>();
		ListIterator<ProductCategory> listIterator = productCategoryList.listIterator();
		while (listIterator.hasNext()) {
			ProductCategory productCategory = listIterator.next();
			ProductCategoryVO productCategoryVO = toVO_old(productCategory);
			productCategoryVOs.add(productCategoryVO);
		}
		return productCategoryVOs;
	}

	public ProductCategoryVO toVO_old(ProductCategory productCategory) {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		productCategoryVO.setId(productCategory.getId());
		productCategoryVO.setName(productCategory.getName());
		productCategoryVO.setDescription(productCategory.getDescription());
		RecordInfoAdaptor.fromRecordInfo(productCategoryVO, productCategory.getRecordInfo());
		return productCategoryVO;
	}
}
