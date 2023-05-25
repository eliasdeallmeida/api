package com.lms.api.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AdminRepository extends CrudRepository<Admin, Integer> {
	@Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from administradores where id = :id", nativeQuery = true)
	public boolean exist(int id);

	@Query(value="select * from administradores where email = :email and senha =:senha", nativeQuery = true)
	public Admin Login(String email, String senha);

}


