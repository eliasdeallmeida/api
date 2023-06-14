package com.lms.api.model.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


//public interface AdminRepository extends CrudRepository<Admin,Long>{
//	// @Query(value = "select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from admins where id = :id", nativeQuery = true)
//    // public boolean exist(int id);
//}

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Page<Admin> findAllByAtivoTrue(Pageable paginacao);

	@Query(value="select * from admins where email = :email and senha =:senha", nativeQuery = true)
	public Admin login(String email, String senha);
}
