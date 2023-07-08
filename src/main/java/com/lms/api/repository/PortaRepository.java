package com.lms.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.entity.Porta;

public interface PortaRepository extends JpaRepository<Porta, Long>{
    Page<Porta> findAllByAtivoTrue(Pageable paginacao);
    List<Porta> findAllByArmarioIdAndAtivoTrue(Long armarioId);
    List<Porta> findAllByArmarioId(Long armarioId);
    Porta getReferenceByNumeroPortaAndArmarioId(int numeroPorta, Long armarioId);
}
