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

import com.lms.api.dto.DadosAtualizacaoAdmin;
import com.lms.api.dto.DadosCadastroAdmin;
import com.lms.api.dto.DadosDetalhamentoAdmin;
import com.lms.api.dto.DadosListagemAdmin;
import com.lms.api.entity.Admin;
import com.lms.api.entity.Usuario;
import com.lms.api.repository.AdminRepository;
import com.lms.api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroAdmin dados, UriComponentsBuilder uriBuilder) {
		var admin = new Admin(dados);
		adminRepository.save(admin);
		usuarioRepository.save(new Usuario(dados.nome(), "$2a$12$/4qcygumAheeyp1X15mWkuOVeHiDWJtgmqIXmNmiXi3tLGCAKdu6e"));
		var uri = uriBuilder.path("/admins/{id}").buildAndExpand(admin.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoAdmin(admin));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemAdmin>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		var page = adminRepository.findAllByAtivoTrue(paginacao).map(DadosListagemAdmin::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoAdmin dados) {
		var admin = adminRepository.getReferenceById(dados.id());
		admin.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoAdmin(admin));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		var admin = adminRepository.getReferenceById(id);
		admin.excluir();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		var admin = adminRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoAdmin(admin));
	}
}
