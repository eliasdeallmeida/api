package com.lms.api.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.api.dto.DadosAtualizacaoAluno;
import com.lms.api.dto.DadosAtualizacaoPorta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Porta")
@Table(name = "portas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Porta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_porta")
	private int numeroPorta;

	@Column(name = "chave_porta")
	private String chavePorta;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "armario_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Armario armario;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aluno_id")
	@JsonIgnore
	private Aluno aluno;

	private boolean ocupado;
	private boolean ativo;

	public Porta(int tipo, int numeroArmario, int numeroPorta, Armario armario) {
		this.numeroPorta = numeroPorta;
		this.armario = armario;
		this.chavePorta = gerandoCodigo(tipo, numeroArmario, numeroPorta);
		this.ocupado = false;
		this.ativo = true;
	}

	public String gerandoCodigo(int tipo, int numero, int janela) {
		String charTipo = "" + tipo;
		String charJanela;
		String charNumero;

		if(janela > 0 && janela < 10) {
			charJanela = "0" + janela;
		} else {
			charJanela = "" + janela;
		}

		if(numero > 0 && numero < 10) {
			charNumero = "00" + numero;
		} else if(numero > 9 && numero < 100) {
			charNumero = "0" + numero;
		} else {
			charNumero = "" + numero;
		}

		return charTipo + charNumero + charJanela;
	}

	// public void atualizarInformacoes(@Valid DadosAtualizacaoPorta dados) {
	// 	this.aluno = dados.aluno();
	// 	this.ocupado = ((dados.aluno() != null) ? true : false);
	// }

	public void atualizarAluno(Aluno aluno) {
		this.aluno = aluno;
		this.ocupado = ((aluno != null) ? true : false);
	}

	public void excluir() {
		this.ativo = false;
	}
}
