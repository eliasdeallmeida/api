package com.lms.api.dto;

import com.lms.api.entity.Armario;

public record DadosListagemArmario(Long id, int tipoArmario, int numeroArmario) {
    public DadosListagemArmario(Armario armario) {
        this(armario.getId(), armario.getTipoArmario(), armario.getNumeroArmario());
    }
}
