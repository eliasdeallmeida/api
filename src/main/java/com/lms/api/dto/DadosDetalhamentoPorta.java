package com.lms.api.dto;

import com.lms.api.entity.Aluno;
import com.lms.api.entity.Porta;

// public record DadosDetalhamentoPorta(Long id, int numeroPorta, String chavePorta, boolean ocupado) {
//     public DadosDetalhamentoPorta(Porta porta) {
//         this(porta.getId(), porta.getNumeroPorta(), porta.getChavePorta(), porta.isOcupado());
//     }
// }

public record DadosDetalhamentoPorta(Long id, int numeroPorta, String chavePorta, boolean ocupado, Aluno aluno) {
    public DadosDetalhamentoPorta(Porta porta) {
        this(porta.getId(), porta.getNumeroPorta(), porta.getChavePorta(), porta.isOcupado(), porta.getAluno());
    }
}
