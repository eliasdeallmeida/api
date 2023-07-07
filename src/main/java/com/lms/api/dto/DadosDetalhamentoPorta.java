package com.lms.api.dto;

import com.lms.api.entity.Aluno;
import com.lms.api.entity.Porta;

public record DadosDetalhamentoPorta(Long id, int numeroPorta, String chavePorta, Aluno aluno, boolean ocupado) {
    public DadosDetalhamentoPorta(Porta porta) {
        this(porta.getId(), porta.getNumeroPorta(), porta.getChavePorta(), porta.getAluno(), porta.isOcupado());
    }
}
