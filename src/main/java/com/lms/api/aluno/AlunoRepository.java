package com.lms.api.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.admin.Admin;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Page<Aluno> findAll(Pageable paginacao);
}