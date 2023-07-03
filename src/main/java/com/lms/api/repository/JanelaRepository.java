package com.lms.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.entity.Janela;

public interface JanelaRepository extends JpaRepository<Janela, Long>{
    Page<Janela> findAllByAtivoTrue(org.springframework.data.domain.Pageable paginacao);
}
