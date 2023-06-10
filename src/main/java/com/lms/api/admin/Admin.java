package com.lms.api.admin;

import jakarta.persistence.*;
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

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoAdmin dados) {
		if (dados.email() != null) {
			this.email = dados.email();
		}
	}

	public void excluir() {
		this.ativo = false;
	}

	public boolean isAtivo() {
		return ativo;
	}

	// @Enumerated(EnumType.STRING) : para classe enum
	// @Embedded : para classe endereço (Obs: dentro da classe deve ser adicionada
	// as anotações @Embeddable, @Getter, @NoArgsConstructor, @AllArgsConstructor)
}
