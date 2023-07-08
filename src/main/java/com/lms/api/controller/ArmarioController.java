package com.lms.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lms.api.repository.PortaRepository;
import com.lms.api.dto.DadosCadastroArmario;
import com.lms.api.dto.DadosDetalhamentoArmario;
import com.lms.api.dto.DadosListagemArmario;
import com.lms.api.entity.Armario;
import com.lms.api.entity.Porta;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/armarios")
public class ArmarioController {

    @Autowired
    private ArmarioRepository armarioRepository;

    @Autowired
    private PortaRepository portaRepository;

    // @PostMapping
    // @Transactional
    // public void cadastrar(@RequestBody @Valid DadosCadastroArmario dados) {
    //     var ultimoArmario = armarioRepository.findTopByOrderByIdDesc();
    //     int ultimo = ultimoArmario == null ? 0 : ultimoArmario.getNumeroArmario();
    //     for(int numeroArmario = ultimo + 1; numeroArmario <= ultimo + dados.quantidadeArmario(); numeroArmario++) {
    //         armarioRepository.save(new Armario(dados.tipoArmario(), numeroArmario));
    //         for(int numeroPorta = 1; numeroPorta <= dados.quantidadePorta(); numeroPorta++) {
    //             Armario armario = armarioRepository.findByNumeroArmario(numeroArmario);
    //             portaRepository.save(new Porta(dados.tipoArmario(), numeroArmario, numeroPorta, armario));
    //         }
    //     }
    // }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroArmario dados) {
        var ultimoArmario = armarioRepository.findTopByOrderByIdDesc();
        int ultimo = ultimoArmario == null ? 0 : ultimoArmario.getNumeroArmario();
        List<Object> lista = new ArrayList<>();
        for(int numeroArmario = ultimo + 1; numeroArmario <= ultimo + dados.quantidadeArmario(); numeroArmario++) {
            var armario = new Armario(dados.tipoArmario(), numeroArmario);
            armarioRepository.save(armario);
            lista.add(armario);
            for(int numeroPorta = 1; numeroPorta <= dados.quantidadePorta(); numeroPorta++) {
                armario = armarioRepository.findByNumeroArmario(numeroArmario);
                var porta = new Porta(dados.tipoArmario(), numeroArmario, numeroPorta, armario);
                portaRepository.save(porta);
                lista.add(porta);
            }
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemArmario>> listar(@PageableDefault(size = 10, sort = {"numeroArmario"}) Pageable paginacao) {
        var page = armarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemArmario::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var armario = armarioRepository.getReferenceById(id);
        List<Porta> portas = portaRepository.findAllByArmarioId(id).stream().toList();
        armario.excluir();
        for(Porta porta : portas) {
            porta.excluir();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var armario = armarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoArmario(armario));
    }
}
