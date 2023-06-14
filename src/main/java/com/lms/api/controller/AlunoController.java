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
import org.springframework.web.servlet.ModelAndView;

import com.lms.api.model.aluno.Aluno;
import com.lms.api.model.aluno.AlunoRepository;
import com.lms.api.dto.DadosCadastroAluno;
import com.lms.api.dto.DadosListagemAdmin;
import com.lms.api.dto.DadosListagemAluno;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroAluno dados) {
		repository.save(new Aluno(dados));
	}

	@GetMapping("/add")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("aluno/add");
        return modelAndView;
    }
	@GetMapping
	public Page<DadosListagemAluno> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		// return repository.findAll(paginacao).map(DadosListagemAluno::new);
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		// repository.deleteById(id);
		var aluno = repository.getReferenceById(id);
		aluno.excluir();
	}

}