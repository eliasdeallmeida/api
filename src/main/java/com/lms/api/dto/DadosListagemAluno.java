package com.lms.api.dto;

import com.lms.api.model.aluno.Aluno;

public record DadosListagemAluno(Long id, String nome, String email, String matricula, int codigoArmario) {
	public DadosListagemAluno(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getCodigoArmario());
	}
}
