package it.uniroma3.theboys.siw_books.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProvaController {

	@GetMapping("/login")
	public String getLogin() {
		return "login.html";
	}

	@GetMapping("/registrazione")
	public String getRegistrazione() {
		return "registrazione.html";
	}

}
