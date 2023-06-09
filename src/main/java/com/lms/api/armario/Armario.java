package com.lms.api.armario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "armarios")
@Entity(name = "Armario")

public class Armario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipoArmario")
    private int tipoArmario;

    @Column(name = "numeroArmario")
    private int numeroArmario;

    private Boolean ativo;

    public Armario(int tipo, int numero) {
        this.tipoArmario = tipo;
        this.numeroArmario = numero;
        this.ativo = true;
    }

    public int getTipoArmario() {
        return tipoArmario;
    }

    public int getNumeroArmario() {
        return numeroArmario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

}