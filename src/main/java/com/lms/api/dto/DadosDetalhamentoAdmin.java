package com.lms.api.dto;

import com.lms.api.entity.Admin;

public record DadosDetalhamentoAdmin(Long id, String nome, String email) {
    public DadosDetalhamentoAdmin(Admin admin) {
        this(admin.getId(), admin.getNome(), admin.getEmail());
    }
}
