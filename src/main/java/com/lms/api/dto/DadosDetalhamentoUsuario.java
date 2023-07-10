package com.lms.api.dto;

import com.lms.api.entity.Usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String email) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
