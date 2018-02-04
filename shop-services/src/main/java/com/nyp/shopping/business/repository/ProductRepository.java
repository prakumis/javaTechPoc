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

import com.nyp.shopping.common.entity.Product;

/**
 * @author pmis30
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Refer
	 * [http://stackoverflow.com/questions/2123438/hibernate-how-to-set-null-query-parameter-value-with-hql]
	 * for null parameter at runtime
	 * 
	 * @return
	 */
	@Query("select p FROM Product p where p.category.id = :catId")
	List<Product> findProductsByCatId(@Param(value = "catId") Long catId);

	@Query("select p FROM Product p where p.category.id = :catId and p.id = :id")
	List<Product> findProductByCatIdAndId(@Param(value = "catId") Long catId, @Param(value = "id") Long id);

	@Query("select p FROM Product p where p.category.id = :catId and p.isValid = :status")
	List<Product> findProductByCatIdAndStatus(@Param(value = "catId") Long catId,
			@Param(value = "status") Boolean status);

	@Modifying
	@Query("update Product p set p.name = :name, p.description=:description,  p.modifiedBy.id = :userId, p.modifiedDate = :modifiedDate where p.category.id = :catId and p.id=:id")
	void update(@Param("id") Long id, @Param(value = "catId") Long catId, @Param("name") String name,
			@Param("description") String description, @Param("userId") Long userId,
			@Param("modifiedDate") Date modifiedDate);

	@Modifying
	@Query("update Product p set p.category.id = :newCatId, p.modifiedBy.id = :userId, p.modifiedDate = :modifiedDate where p.id=:id")
	void updateCategory(@Param("id") Long id, @Param("newCatId") Long newCatId, @Param("userId") Long userId,
			@Param("modifiedDate") Date modifiedDate);

	@Modifying
	@Query("update Product p set p.isValid = :isValid, p.modifiedBy.id = :userId, p.modifiedDate = :modifiedDate where p.id=:id")
	void updateStatus(@Param("id") Long id, @Param("isValid") Boolean isValid, @Param("userId") Long userId,
			@Param("modifiedDate") Date modifiedDate);

	@Query("select p from Product p where p.category.id = :catId and p.id = :id")
	Product findOne(@Param(value = "catId") Long catId, @Param(value = "id") Long id);

	@Query("select p from Product p where p.category.id = :catId and p.id = :id and p.isValid = :status")
	Product findOne(@Param(value = "catId") Long catId, @Param(value = "id") Long id,
			@Param(value = "status") boolean status);
}
