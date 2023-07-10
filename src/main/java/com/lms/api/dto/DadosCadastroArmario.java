package com.lms.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroArmario(@NotNull int tipoArmario, @NotNull int quantidadeArmario, @NotNull int quantidadePorta) {}
