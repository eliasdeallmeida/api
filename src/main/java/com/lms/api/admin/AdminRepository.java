package com.lms.api.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AdminRepository extends CrudRepository<Admin,Long>{
	
}

//public interface AdminRepository extends JpaRepository<Admin, Long> {
//	Page<Admin> findAllByAtivoTrue(Pageable paginacao);
//}
