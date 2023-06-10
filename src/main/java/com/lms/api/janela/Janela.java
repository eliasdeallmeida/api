package com.lms.api.janela;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "janelas")
@Entity(name = "Janela")
@Getter
@EqualsAndHashCode(of = "id")

public class Janela {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int numero_janela;
	private String codigo_janela;
	private boolean ocupado;
	private boolean ativo;

	public Janela() {
	}

	public Janela(int tipo, int numeroArmario, int numeroJanela) {
		this.numero_janela = numeroJanela;
		this.codigo_janela = gerandoCodigo(tipo, numeroArmario, numeroJanela);
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
