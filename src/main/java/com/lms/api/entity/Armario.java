package com.lms.api.entity;

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

@Entity(name = "Armario")
@Table(name = "armarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Armario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_armario")
    private int tipoAmario;

    @Column(name = "numero_armario")
    private int numeroArmario;
    
    private boolean ativo;

    public Armario(int tipoArmario, int numeroArmario) {
        this.tipoAmario = tipoArmario;
        this.numeroArmario = numeroArmario;
        this.ativo = true;
    }

    public void excluir() {
		this.ativo = false;
	}
}
