package com.lms.api.model.armario;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmarioRepository extends JpaRepository<Armario, Long>{
    Page<Armario> findAllByAtivoTrue(org.springframework.data.domain.Pageable paginacao);
}