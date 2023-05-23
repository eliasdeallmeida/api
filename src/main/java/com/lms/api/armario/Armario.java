package com.lms.api.armario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "armarios")
@Entity(name = "Armario")
@Getter
@EqualsAndHashCode(of = "id")

public class Armario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(name = "tipoArmario")
	private int tipoArmario;

	@Column(name = "numeroArmario")
	private int numeroArmario;

	@Column(name = "numeroJanela")
	private int numeroJanela;

	@Column(name = "codigoArmario")
	private String codigoArmario;

	private boolean ocupado;
	private boolean ativo;

	public Armario() {
	}

	public Armario(int tipo, int numeroArmario, int numeroJanela) {
		this.tipoArmario = tipo;
		this.numeroArmario = numeroArmario;
		this.numeroJanela = numeroJanela;
		this.codigoArmario = gerandoCodigo(tipo, numeroArmario, numeroJanela);
		this.ativo = true;
		this.ocupado = false;
	}

	public String gerandoCodigo(int tipo, int numero, int janela) {
		String charTipo = "" + tipo;
		String charJanela;
		String charNumero;

		if (janela > 0 && janela < 10) {
			charJanela = "0" + janela;
		} else {
			charJanela = "" + janela;
		}

		if (numero > 0 && numero < 10) {
			charNumero = "00" + numero;
		} else if (numero > 9 && numero < 100) {
			charNumero = "0" + numero;
		} else {
			charNumero = "" + numero;
		}

		return charTipo + charNumero + charJanela;
	}

	public void excluir() {
		this.ativo = false;
	}
}
