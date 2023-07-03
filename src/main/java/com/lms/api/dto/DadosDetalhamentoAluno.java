package com.lms.api.dto;

import com.lms.api.entity.Aluno;

public record DadosDetalhamentoAluno(long id, String nome, String email, String matricula, boolean ativo) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.isAtivo());
    }
}
