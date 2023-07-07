package com.lms.api.dto;

import com.lms.api.entity.Armario;

public record DadosListagemArmario(Long id, int tipoArmario, int numeroArmario, boolean ativo) {
    public DadosListagemArmario(Armario armario) {
        this(armario.getId(), armario.getTipoAmario(), armario.getNumeroArmario(), armario.isAtivo());
    }
}
