package com.lms.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(@NotNull Long id, @Email String email) {}
