package com.lms.api.dto;

import com.lms.api.entity.Usuario;

public record DadosListagemUsuario(Long id, String nome, String email) {
    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
