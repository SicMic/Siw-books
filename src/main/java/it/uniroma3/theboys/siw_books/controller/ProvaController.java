package it.uniroma3.theboys.siw_books.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.theboys.siw_books.model.Credenziali;



@Controller
public class ProvaController {

	// @GetMapping("/login")
	// public String getLogin() {
	// 	return "login.html";
	// }

	@PostMapping("/login")
	public String postLogin(Model model) {
		
		model.addAttribute("credenziali", new Credenziali());

		return "dashboard.html";
	}
	

	@GetMapping("/registrazione")
	public String getRegistrazione() {
		return "registrazione.html";
	}

}
