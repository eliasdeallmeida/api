package com.lms.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroArmario(
        @NotBlank @Pattern(regexp = "\\d{1}") int tipoArmario,
        @NotBlank @Pattern(regexp = "\\d{1,3}") int quantidadeArmario,
        @NotBlank @Pattern(regexp = "\\d{1,2}") int numeroJanela) {

}