/**
 * 
 */
package com.nyp.shopping.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nyp.shopping.common.entity.Customer;

/**
 * @author Java Developer
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * 
	 */
	@Query("from Customer c where c.email = :email or c.mobile = :mobile")
	Customer findCustomerDetails(@Param("email") String email, @Param("mobile") String mobile);

}
