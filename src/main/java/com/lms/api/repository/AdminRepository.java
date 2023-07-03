package com.lms.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Page<Admin> findAllByAtivoTrue(Pageable paginacao);
}
