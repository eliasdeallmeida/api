package com.lms.api.admin;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAdmin(@NotNull Long id, String email) {
}
