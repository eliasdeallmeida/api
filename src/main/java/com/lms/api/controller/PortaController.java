package com.lms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.DadosAtualizacaoPorta;
import com.lms.api.dto.DadosDetalhamentoPorta;
import com.lms.api.dto.DadosListagemPorta;
import com.lms.api.repository.AlunoRepository;
import com.lms.api.repository.PortaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/portas")
public class PortaController {

    @Autowired
    PortaRepository portaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping("/armario/{armarioId}")
	public ResponseEntity<?> listar(@PathVariable Long armarioId) {
        List<DadosListagemPorta> portas = portaRepository.findAllByArmarioIdAndAtivoTrue(armarioId).stream().map(DadosListagemPorta::new).toList();
        return ResponseEntity.ok(portas);
    }

    // @PutMapping
    // @Transactional
    // public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoPorta dados) {
    //     var porta = portaRepository.getReferenceById(dados.id());
    //     porta.atualizarInformacoes(dados);
    //     return ResponseEntity.ok(new DadosDetalhamentoPorta(porta));
    // }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoPorta dados) {
        var porta = portaRepository.getReferenceById(dados.id());
        var aluno = dados.alunoId() != null ? alunoRepository.getReferenceById(dados.alunoId()) : null;
        porta.atualizarInformacoes(aluno);
        return ResponseEntity.ok(new DadosDetalhamentoPorta(porta));
    }

    @GetMapping("/{numeroPorta}/armario/{armarioId}")
	public ResponseEntity<?> detalhar(@PathVariable("numeroPorta") int numeroPorta, @PathVariable("armarioId") Long armarioId) {
		var porta = portaRepository.getReferenceByNumeroPortaAndArmarioId(numeroPorta, armarioId);
		return ResponseEntity.ok(new DadosDetalhamentoPorta(porta));
	}
}
