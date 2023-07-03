package com.lms.api.dto;

import com.lms.api.entity.Aluno;

public record DadosListagemAluno(Long id, String nome, String email, String matricula) {
	public DadosListagemAluno(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula());
	}
}
