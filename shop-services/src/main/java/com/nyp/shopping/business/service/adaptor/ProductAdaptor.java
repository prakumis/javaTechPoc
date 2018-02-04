/**
 * 
 */
package com.nyp.shopping.business.service.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.nyp.shopping.common.entity.Product;
import com.nyp.shopping.common.entity.ProductCategory;
import com.nyp.shopping.common.vo.ProductVO;

/**
 * @author pmis30
 *
 */
@Component
public class ProductAdaptor extends BaseAdaptor {

	public List<ProductVO> toVO(List<Product> productCategoryList) {

		List<ProductVO> productCategoryVOList = new ArrayList<>();
		ListIterator<Product> listIterator = productCategoryList.listIterator();
		while (listIterator.hasNext()) {
			Product product = listIterator.next();
			ProductVO productVO = toVO(product);
			productCategoryVOList.add(productVO);
		}
		return productCategoryVOList;
	}

	public ProductVO toVO(Product product) {
		ProductVO productVO = new ProductVO();
		BeanUtils.copyProperties(product, productVO, "subCategories");
		//toRecordInfo(productVO);
		return productVO;
	}

	public Product fromVO(ProductVO productVO, boolean isCreateMode) {
		Product product = new Product();
		BeanUtils.copyProperties(productVO, product);
		product.setCategory(new ProductCategory(productVO.getCatId()));
		fromVO(productVO, product, isCreateMode);
		return product;
	}

}
