package com.lms.api.dto;

import com.lms.api.entity.Admin;

public record DadosDetalhamentoAdmin(Long id, String nome, String email, boolean ativo) {
    public DadosDetalhamentoAdmin(Admin admin) {
        this(admin.getId(), admin.getNome(), admin.getEmail(), admin.isAtivo());
    }
}
