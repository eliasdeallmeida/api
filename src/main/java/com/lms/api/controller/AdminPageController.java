package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import com.lms.api.admin.Admin;
import com.lms.api.admin.AdminRepository;
import com.lms.api.admin.DadosCadastroAdmin;

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
	public String delete(@PathVariable int id) {
		repo.deleteById((long) id);
		return "redirect:/admins";
	}
	
	
	@GetMapping("/admins/{id}")
	public String  search(@PathVariable int id , Model model) {
		
		Optional<Admin> admin = repo.findById((long) id);
		try {	
			model.addAttribute("administrador", admin.get());
		}catch(Exception e) {return "redirect:/admins";}
		return "/admins/edit";
	}
}