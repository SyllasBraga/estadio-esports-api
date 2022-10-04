package com.estadioesports.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    
	@GetMapping
    @PreAuthorize("permitAll")
	public String index(){
		return "Olá Mundo!";
	}
}
