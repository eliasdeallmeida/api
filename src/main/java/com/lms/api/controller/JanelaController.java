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

import com.lms.api.repository.ArmarioRepository;
import com.lms.api.repository.JanelaRepository;
import com.lms.api.dto.DadosCadastroJanela;
import com.lms.api.dto.DadosListagemJanelas;
import com.lms.api.entity.Armario;
import com.lms.api.entity.Janela;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/janelas")
public class JanelaController {

	@Autowired
	private JanelaRepository janelaRepository;

	@Autowired
	private ArmarioRepository armarioRepository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroJanela dados) {
		for (int i = 1; i <= dados.quantidadeArmario(); i++) {
			for (int j = 1; j <= dados.numeroJanela(); j++) {
				janelaRepository.save(new Janela(dados.tipoArmario(), i, j));
			}
			armarioRepository.save(new Armario(dados.tipoArmario(), i));
		}
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemJanelas>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
		var page = janelaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemJanelas::new);
		return ResponseEntity.ok(page);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		var armario = janelaRepository.getReferenceById(id);
		armario.excluir();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalharArmario(@PathVariable Long id) {
		var armario = janelaRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemJanelas(armario));
	}
}
