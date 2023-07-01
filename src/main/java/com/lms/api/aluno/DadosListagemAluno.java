package com.lms.api.aluno;

public record DadosListagemAluno(Long id, String nome, String email, String matricula) {
	public DadosListagemAluno(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula());
	}
}
