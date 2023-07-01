package com.lms.api.janela;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JanelaRepository extends JpaRepository<Janela, Long>{
    Page<Janela> findAllByAtivoTrue(org.springframework.data.domain.Pageable paginacao);
}
