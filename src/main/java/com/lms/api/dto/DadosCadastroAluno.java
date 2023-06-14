package com.lms.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAluno(@NotBlank String nome, @Email String email, @NotBlank String matricula, int codigoArmario) {

}