package com.lms.api.armario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "armarios")
@Entity(name = "Armario")
@Getter
@EqualsAndHashCode(of = "id")

public class Armario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tipo_armario;
    private int numero_armario;

    private Boolean ativo;

    public Armario() {
    }

    public Armario(int tipo, int numero) {
        this.tipo_armario = tipo;
        this.numero_armario = numero;
        this.ativo = true;
    }

    public int getTipo_armario() {
        return tipo_armario;
    }

    public int getNumero_armario() {
        return numero_armario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

}
