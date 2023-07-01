package com.lms.api.aluno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "alunos")
@Entity(name = "Aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String nome;
	private String matricula;
	private boolean ativo;

	public Aluno(DadosCadastroAluno dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.matricula = dados.matricula();
	}

	public void excluir() {
		this.ativo = false;
	}
}
