package com.lms.api.dto;

import com.lms.api.entity.Armario;

public record DadosDetalhamentoArmario(Long id, int tipoArmario, int numeroArmario) {
    public DadosDetalhamentoArmario(Armario armario) {
        this(armario.getId(), armario.getTipoArmario(), armario.getNumeroArmario());
    }
}
