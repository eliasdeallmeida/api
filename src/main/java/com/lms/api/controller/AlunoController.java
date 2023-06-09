package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lms.api.aluno.Aluno;
import com.lms.api.aluno.AlunoRepository;
import com.lms.api.aluno.DadosCadastroAluno;
import com.lms.api.aluno.DadosDetalhesAluno;
import com.lms.api.aluno.DadosListagemAluno;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
		var aluno = new Aluno(dados);
		repository.save(aluno);
		var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemAluno(aluno));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemAluno>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity detalharAluno(@PathVariable Long id) {
		var aluno = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhesAluno(aluno));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var aluno = repository.getReferenceById(id);
		aluno.excluir();
		return ResponseEntity.noContent().build();
	}

}