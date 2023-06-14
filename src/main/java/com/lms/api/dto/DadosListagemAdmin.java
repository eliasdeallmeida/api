package com.lms.api.dto;

import com.lms.api.model.admin.Admin;

public record DadosListagemAdmin(Long id, String nome, String email) {
	public DadosListagemAdmin(Admin admin) {
		this(admin.getId(), admin.getNome(), admin.getEmail());
	}
}
