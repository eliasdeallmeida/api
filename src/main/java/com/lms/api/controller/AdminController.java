package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.admin.Admin;
import com.lms.api.admin.AdminRepository;
import com.lms.api.admin.DadosAtualizacaoAdmin;
import com.lms.api.admin.DadosCadastroAdmin;
import com.lms.api.admin.DadosListagemAdmin;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private AdminRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroAdmin dados) {
		repository.save(new Admin(dados));
	}
	
	@GetMapping
	public Page<DadosListagemAdmin> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		// return repository.findAll().stream().map(DadosListagemAdmin::new).toList();
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemAdmin::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoAdmin dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		// repository.deleteById(id);
		var medico = repository.getReferenceById(id);
		medico.excluir();
	}
}
