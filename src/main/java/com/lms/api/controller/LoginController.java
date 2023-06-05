package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.api.admin.Admin;
import com.lms.api.admin.AdminRepository;

import jakarta.transaction.Transactional;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository repo;
    
    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    @PostMapping("/logar")
    @Transactional
    public String logar(Model model, Admin adminParam, String lembrar) {
        Admin adm = this.repo.login(adminParam.getEmail(), adminParam.getSenha());
        if(adm != null) {
            return "redirect:/";
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login/index";
    }
}
