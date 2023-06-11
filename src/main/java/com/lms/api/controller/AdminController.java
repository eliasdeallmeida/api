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

import com.lms.api.admin.Admin;
import com.lms.api.admin.AdminRepository;
import com.lms.api.admin.DadosAtualizacaoAdmin;
import com.lms.api.admin.DadosCadastroAdmin;
import com.lms.api.admin.DadosDetalhesAdmin;
import com.lms.api.admin.DadosListagemAdmin;
import com.lms.api.usuario.Usuario;
import com.lms.api.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody DadosCadastroAdmin dados, UriComponentsBuilder uriBuilder) {
		var admin = new Admin(dados);
		repository.save(admin);
		usuarioRepository.save(new Usuario(dados.nome(), "$2a$12$/4qcygumAheeyp1X15mWkuOVeHiDWJtgmqIXmNmiXi3tLGCAKdu6e"));

		var uri = uriBuilder.path("/admins/{id}").buildAndExpand(admin.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemAdmin(admin));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemAdmin>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAdmin::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity detalharAdmin(@PathVariable Long id) {
		var admin = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhesAdmin(admin));
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAdmin dados) {
		var admin = repository.getReferenceById(dados.id());
		admin.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosListagemAdmin(admin));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var admin = repository.getReferenceById(id);
		admin.excluir();
		return ResponseEntity.noContent().build();
	}
}
