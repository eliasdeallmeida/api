package com.lms.api.janela;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroJanela(
        @NotBlank @Pattern(regexp = "\\d{1}") int tipo_armario,
        @NotBlank @Pattern(regexp = "\\d{1,3}") int quantidade_armario,
        @NotBlank @Pattern(regexp = "\\d{1,2}") int numero_janela) {

}
