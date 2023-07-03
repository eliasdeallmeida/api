package com.lms.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroJanela(@NotNull int tipoArmario, @NotNull int quantidadeArmario, @NotNull int numeroJanela) {}
