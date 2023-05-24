package com.lms.api.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAdmin(@NotNull Long id, @Email String email) {
}
