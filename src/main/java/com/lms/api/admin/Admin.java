package com.lms.api.admin;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "admins")
@Entity(name = "Admin")
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Admin {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "O campo nome é obrigatório")
	@Column(name = "nome")
	@Getter
	private String nome;
	@Getter
	private String email;
	@Getter
	private boolean ativo;
	@Getter
	private String senha;
	
	public Admin() {}
	
	public Admin(DadosCadastroAdmin dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.senha = dados.senha();
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
		if(dados.email() != null) {
			this.email = dados.email();
		}
	}
	
	public void excluir() {
		this.ativo = false;
	}
	
	// @Enumerated(EnumType.STRING) : para classe enum
	// @Embedded : para classe endereço (Obs: dentro da classe deve ser adicionada as anotações @Embeddable, @Getter, @NoArgsConstructor, @AllArgsConstructor)
}
