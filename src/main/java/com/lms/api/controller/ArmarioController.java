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
import org.springframework.web.servlet.ModelAndView;

import com.lms.api.dto.DadosCadastroArmario;
import com.lms.api.dto.DadosListagemArmarios;
import com.lms.api.model.armario.Armario;
import com.lms.api.model.armario.ArmarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/armarios")
public class ArmarioController {

	@Autowired
	private ArmarioRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroArmario dados) {

		for (int i = 1; i <= dados.quantidadeArmario(); i++) {
			for (int j = 1; j <= dados.numeroJanela(); j++) {
				repository.save(new Armario(dados.tipoArmario(), i, j));
			}
		}
	}

	 @GetMapping("/gerenciar-armarios")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("armarios/index");
        return modelAndView;
    }

	 @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("armarios/add");
        return modelAndView;
    }

	@GetMapping
	public ResponseEntity<Page<DadosListagemArmarios>> listar(
			@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemArmarios::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("{id}")
	public ResponseEntity detalharArmario(@PathVariable Long id) {
		var armario = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemArmarios(armario));
	}

	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var armario = repository.getReferenceById(id);
		armario.excluir();
		return ResponseEntity.noContent().build();
	}
}
