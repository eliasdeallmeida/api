package com.lms.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.entity.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Page<Aluno> findAllByAtivoTrue(Pageable paginacao);
}
