package com.lms.api.dto;

import com.lms.api.entity.Aluno;
import com.lms.api.entity.Porta;

public record DadosListagemPortaArmario(Long id, int numeroPorta, String chavePorta, Aluno aluno, boolean ocupado, boolean ativo) {
    public DadosListagemPortaArmario(Porta porta) {
        this(porta.getId(), porta.getNumeroPorta(), porta.getChavePorta(), porta.getAluno(), porta.isOcupado(), porta.isAtivo());
    }
}
