package com.lms.api.admin;

public record DadosListagemAdmin(Long id, String nome, String email) {
	public DadosListagemAdmin(Admin admin) {
		this(admin.getId(), admin.getNome(), admin.getEmail());
	}
}
