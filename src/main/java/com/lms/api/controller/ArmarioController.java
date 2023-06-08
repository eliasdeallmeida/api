package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.*;
import com.lms.api.admin.AdminRepository;
import com.lms.api.admin.DadosAtualizacaoAdmin;
import com.lms.api.admin.DadosCadastroAdmin;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class ArmarioController {



	@GetMapping("/gerenciar-armarios")
	public String index(Model model) {
		return "armarios/index";
	}
	
	
}