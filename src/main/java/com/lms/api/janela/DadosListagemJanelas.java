package com.lms.api.janela;

public record DadosListagemJanelas(Long id, int numeroJanela, String codigo_janela, boolean ocupado, boolean ativo) {
    public DadosListagemJanelas(Janela janela) {
        this(
                janela.getId(),
                janela.getNumero_janela(),
                janela.getCodigo_janela(),
                janela.isOcupado(),
                janela.isAtivo());
    }
}
