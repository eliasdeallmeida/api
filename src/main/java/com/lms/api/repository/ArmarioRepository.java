package com.lms.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.entity.Armario;

public interface ArmarioRepository extends JpaRepository<Armario, Long> {
    Page<Armario> findAllByAtivoTrue(Pageable paginacao);

    @Query(
        nativeQuery = true,
        value = """
            select * from armarios
            where
            numero_armario = :numeroArmario
            and
            ativo = 1
        """
    )
    Armario findByNumeroArmario(int numeroArmario);

    Armario findTopByOrderByIdDesc();
}
