package com.lms.api.admin;

public record DadosDetalhesAdmin(Long id, String nome, String email, Boolean ativo) {
    public DadosDetalhesAdmin(Admin admin) {
        this(admin.getId(), admin.getNome(), admin.getEmail(), admin.isAtivo());
    }
}
