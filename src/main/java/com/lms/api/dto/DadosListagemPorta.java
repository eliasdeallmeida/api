package com.lms.api.dto;

import com.lms.api.entity.Porta;

public record DadosListagemPorta(Long id, int numeroPorta, String chavePorta, boolean ocupado) {
    public DadosListagemPorta(Porta porta) {
        this(porta.getId(), porta.getNumeroPorta(), porta.getChavePorta(), porta.isOcupado());
    }
}
