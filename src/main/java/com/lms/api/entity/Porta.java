package com.lms.api.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Setter;

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

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "aluno_id", nullable = true)
    @JsonIgnore
    private Aluno aluno;

    private boolean ocupado;
    private boolean ativo;

    public Porta(int tipo, int numeroArmario, int numeroPorta, Armario armario) {
        this.numeroPorta = numeroPorta;
        this.armario = armario;
        this.chavePorta = gerarNumeracao(tipo, numeroArmario, numeroPorta);
        this.ocupado = false;
        this.ativo = true;
    }

    public String gerarNumeracao(int tipo, int numeroArmario, int numeroPorta) {
        return Integer.toString(100000 * tipo + 100 * numeroArmario + numeroPorta);
    }

    // public void atualizarInformacoes(@Valid DadosAtualizacaoPorta dados) {
    // this.aluno = dados.aluno();
    // this.ocupado = ((dados.aluno() != null) ? true : false);
    // }

    public void atualizarInformacoes(Aluno aluno) {
        if (aluno != null) {
            this.aluno = new Aluno(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.isAtivo());
            this.ocupado = true;
        } else {
            this.aluno = null;
            this.ocupado = false;
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
