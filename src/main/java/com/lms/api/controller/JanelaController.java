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

import com.lms.api.janela.Janela;
import com.lms.api.janela.JanelaRepository;
import com.lms.api.armario.Armario;
import com.lms.api.armario.ArmarioRepository;
import com.lms.api.janela.DadosCadastroJanela;
import com.lms.api.janela.DadosListagemJanelas;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/janelas")
public class JanelaController {

	@Autowired
	private JanelaRepository repository;

	@Autowired
	private ArmarioRepository armarioRepository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody DadosCadastroJanela dados) {

		for (int i = 1; i <= dados.quantidade_armario(); i++) {
			for (int j = 1; j <= dados.numero_janela(); j++) {
				repository.save(new Janela(dados.tipo_armario(), i, j));
			}
			armarioRepository.save(new Armario(dados.tipo_armario(), i));
		}
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemJanelas>> listar(
			@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemJanelas::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("{id}")
	public ResponseEntity detalharArmario(@PathVariable Long id) {
		var armario = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemJanelas(armario));
	}

	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var armario = repository.getReferenceById(id);
		armario.excluir();
		return ResponseEntity.noContent().build();
	}
}
