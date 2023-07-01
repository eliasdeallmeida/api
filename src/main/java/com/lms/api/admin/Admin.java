package com.lms.api.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "admins")
@Entity(name = "Admin")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private boolean ativo;

	public Admin(DadosCadastroAdmin dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoAdmin dados) {
		if (dados.email() != null) {
			this.email = dados.email();
		}
	}

	public void excluir() {
		this.ativo = false;
	}
}
