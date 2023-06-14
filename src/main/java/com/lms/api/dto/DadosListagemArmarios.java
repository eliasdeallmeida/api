package com.lms.api.dto;

import com.lms.api.model.armario.Armario;

public record DadosListagemArmarios(Long id, int tipoArmario, int numeroArmario, int numeroJanela, String codigoArmario,
        boolean ocupado, boolean ativo) {
    public DadosListagemArmarios(Armario armario) {
        this(
                armario.getId(),
                armario.getTipoArmario(),
                armario.getNumeroArmario(),
                armario.getNumeroJanela(),
                armario.getCodigoArmario(),
                armario.isOcupado(),
                armario.isAtivo());
    }
}