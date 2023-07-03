package com.lms.api.dto;

import com.lms.api.entity.Janela;

public record DadosListagemJanelas(Long id, int numeroJanela, String codigo_janela, boolean ocupado, boolean ativo) {
    public DadosListagemJanelas(Janela janela) {
        this(janela.getId(), janela.getNumeroJanela(), janela.getCodigoJanela(), janela.isOcupado(), janela.isAtivo());
    }
}
