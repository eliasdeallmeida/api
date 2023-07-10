package com.lms.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank String nome, @Email String email, String senha) {}
