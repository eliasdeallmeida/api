package com.lms.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(@NotNull Long id, String nome, @Email String email, String matricula) {}
