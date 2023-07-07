package com.lms.api.dto;

import com.lms.api.entity.Aluno;

import jakarta.validation.constraints.NotNull;

public record DadosOcupacaoPorta(@NotNull Long id, @NotNull Aluno aluno) {}
