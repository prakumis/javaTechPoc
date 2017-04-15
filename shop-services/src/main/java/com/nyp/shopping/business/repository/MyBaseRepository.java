/**
 * 
 */
package com.nyp.shopping.business.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author pmis30
 *
 */
@NoRepositoryBean
interface MyBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

  T findOne(ID id);

  <S extends T> S save(S entity);
}