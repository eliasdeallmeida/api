package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.armario.Armario;
import com.lms.api.armario.ArmarioRepository;
import com.lms.api.armario.DadosCadastroArmario;
import com.lms.api.armario.DadosListagemArmarios;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/armarios")
public class ArmarioController {

	@Autowired
	private ArmarioRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @valid DadosCadastroArmario dados) {

		for (int i = 1; i <= dados.quantidadeArmario(); i++) {
			repository.save(new Armario(dados, i));
		}
	}

	@GetMapping
	public Page<DadosListagemArmarios> listar(@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemArmarios::new);
	}

	@DeleteMapping("{id}")
	@Transactional
	public void excluir(@PathVariable  Long id){
var armario = repository.getReferenceById(id);
armario.excluir();
	}
}
