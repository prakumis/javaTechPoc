/**
 * 
 */
package com.nyp.shopping.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nyp.shopping.common.entity.ProductCategory;

/**
 * @author pmis30
 *
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	/**
	 * Refer
	 * [http://stackoverflow.com/questions/2123438/hibernate-how-to-set-null-query-parameter-value-with-hql]
	 * for null parameter at runtime
	 * 
	 * @return
	 */
	@Query("from ProductCategory pc where pc.parentCategory.id is NULL")
	List<ProductCategory> findTopCategories();

}
