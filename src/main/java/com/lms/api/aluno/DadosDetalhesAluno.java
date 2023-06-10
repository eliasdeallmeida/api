package com.lms.api.aluno;

public record DadosDetalhesAluno(long id, String nome, String email, String matricula,
        boolean ativo) {
    public DadosDetalhesAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(),
                aluno.isAtivo());
    }
}
