package com.lms.api.dto;

import com.lms.api.entity.Admin;

public record DadosListagemAdmin(Long id, String nome, String email) {
    public DadosListagemAdmin(Admin admin) {
        this(admin.getId(), admin.getNome(), admin.getEmail());
    }
}
