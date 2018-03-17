/**
 * 
 */
package com.nyp.shopping.business.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	@Query("from ProductCategory pc where pc.parentCategory.id = :parentId")
	List<ProductCategory> findCategoriesByParentId(@Param(value = "parentId") Long parentId);

	@Query("from ProductCategory pc where pc.isValid = :status")
	List<ProductCategory> findCategoriesByStatus(@Param("status") Boolean status);

	@Query("from ProductCategory pc where pc.parentCategory.id = :parentId and pc.isValid = :status")
	List<ProductCategory> findCategoriesByParentIdAndStatus(@Param(value = "parentId") Long parentId, @Param("status") Boolean status);

	@Query("from ProductCategory pc where pc.id = :id")
	ProductCategory findOne(@Param(value = "id") Long id);

	@Query("from ProductCategory pc where pc.id = :id and pc.isLeaf = :isLeaf")
	ProductCategory findOne(@Param(value = "id") Long id, @Param(value = "isLeaf") Boolean isLeaf);

	@Modifying
	@Query("update ProductCategory pc set pc.name = :name, pc.description=:description,  pc.modifiedBy.id = :userId, pc.modifiedDate = :modifiedDate, pc.parentCategory.id = :parentId where pc.id=:id")
	void update(@Param("id") Long id, @Param(value = "parentId") Long parentId, @Param("name") String name,
			@Param("description") String description, @Param("userId") Long userId,
			@Param("modifiedDate") Date modifiedDate);

	@Modifying
	@Query("update ProductCategory p set p.isValid = :isValid, p.modifiedBy.id = :userId, p.modifiedDate = :modifiedDate where p.id=:id")
	void updateStatus(@Param("id") Long id, @Param("isValid") Boolean isValid, @Param("userId") Long userId,
			@Param("modifiedDate") Date modifiedDate);
}
