package com.nyp.shopping.business.service;


import java.util.Map;
import java.util.Set;

import com.nyp.shopping.common.entity.ProductCategory;

public interface DashboardService {

	Map<ProductCategory, Set<ProductCategory>> getProductCategoryMap(Object object);

}
