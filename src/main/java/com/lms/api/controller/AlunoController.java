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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lms.api.dto.DadosAtualizacaoAluno;
import com.lms.api.dto.DadosCadastroAluno;
import com.lms.api.dto.DadosDetalhamentoAluno;
import com.lms.api.dto.DadosListagemAluno;
import com.lms.api.entity.Aluno;
import com.lms.api.repository.AlunoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
		var aluno = new Aluno(dados);
		alunoRepository.save(aluno);
		var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemAluno>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		var page = alunoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
		var aluno = alunoRepository.getReferenceById(dados.id());
		aluno.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		var aluno = alunoRepository.getReferenceById(id);
		aluno.excluir();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		var aluno = alunoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
	}
}