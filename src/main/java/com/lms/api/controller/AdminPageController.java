package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.*;
import com.lms.api.admin.Admin;
import com.lms.api.admin.AdminRepository;
import com.lms.api.admin.DadosAtualizacaoAdmin;
import com.lms.api.admin.DadosCadastroAdmin;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class AdminPageController {

	@Autowired
	private AdminRepository repo;

	@GetMapping("/admins")
	public String index(Model model) {
		List<Admin> administradores = (List<Admin>) repo.findAll();
		model.addAttribute("administrador", administradores);
		return "admins/index";
	}

	@GetMapping("/admins/add")
	public String add() {
		return "/admins/add";
	}
	
	@PostMapping("admins/create")
	public String create(DadosCadastroAdmin dados) {
		repo.save(new Admin(dados));
		return "redirect:/admins";
		
	}
	
	@GetMapping("/admins/{id}/delete")
	public String delete(@PathVariable Long id) {
		repo.deleteById(id);
		// var admin = repo.getReferenceById(id);
		// admin.excluir();
		return "redirect:/admins";
	}
	
	@GetMapping("/admins/{id}")
	public String search(@PathVariable Long id , Model model) {
		Optional<Admin> admin = repo.findById(id);
		try {
			model.addAttribute("administrador", admin.get());
		} catch(Exception e) {
			return "redirect:/admins";
		}
		return "/admins/edit";
	}

	@PostMapping("/admins/{id}/edit")
	@Transactional
	public String edit(@PathVariable Long id, DadosAtualizacaoAdmin dados) {
		System.out.println(id + ", " + dados);
		if (repo.existsById(id)) {
			var admin = repo.getReferenceById(id);
			admin.atualizarInformacoes(dados);
		}
		return "redirect:/admins";
	}
}