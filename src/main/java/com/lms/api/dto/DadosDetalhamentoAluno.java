package com.lms.api.dto;

import com.lms.api.entity.Aluno;

public record DadosDetalhamentoAluno(Long id, String nome, String email, String matricula) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula());
    }
}
