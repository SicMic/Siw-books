package it.uniroma3.theboys.siw_books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import it.uniroma3.theboys.siw_books.model.Credenziali;
import it.uniroma3.theboys.siw_books.model.Utente;
import jakarta.validation.Valid;

import it.uniroma3.theboys.siw_books.service.CredenzialiService;
import it.uniroma3.theboys.siw_books.service.UtenteService;


@Controller
public class Autenticazione {

	@Autowired private CredenzialiService credenzialiService;
	
	@Autowired private UtenteService utenteService;

	@GetMapping("/login")
	public String login(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			// Reindirizza l'utente a un'altra pagina se è già autenticato
			return "redirect:/" + "checazzonesoio";
		}
		return "login.html"; // Mostra la pagina di login se non autenticato
	}

	@GetMapping("/registrazione")
	public String getRegistrazion(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "registrazione.html";
	}

	@PostMapping("/registrazione")
	public String registrazioneAutore(@Valid @ModelAttribute("utente") Utente utente,
			BindingResult userBindingResult, @Valid @ModelAttribute("credenziali") Credenziali credenziali,
			BindingResult credentialsBindingResult,
			Model model) {
		if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			if(credenzialiService.getCredenziali(credenziali.getUsername()) != null)
				return "redirect:/autore/registrazione/errore ";
			credenziali.setRuolo("UTENTE");
			// Salva prima le credenziali
			credenzialiService.saveCredenziali(credenziali);
			// Ora puoi impostare l'utente nelle credenziali
			credenziali.setUtente(utente);
			utente.setCredenziali(credenziali);
			// Salva l'utente
			utenteService.saveNewUtente(utente);
			return "redirect:/login";
		}
		return "errore";
	}




}
