package com.lms.api.entity;

import com.lms.api.dto.DadosCadastroAluno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Aluno")
@Table(name = "alunos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String matricula;
	private boolean ativo;

	public Aluno(DadosCadastroAluno dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.matricula = dados.matricula();
		this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}
}
