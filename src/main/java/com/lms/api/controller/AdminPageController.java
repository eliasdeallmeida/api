package com.lms.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/admins-page")
    public String index(Model model) {
 
        return "admins/index";
    }
}