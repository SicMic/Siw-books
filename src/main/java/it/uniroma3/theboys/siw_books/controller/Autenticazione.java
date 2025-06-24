package it.uniroma3.theboys.siw_books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private CredenzialiService credenzialiService;

	@Autowired
	private UtenteService utenteService;

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
	public String getRegistrazione(Model model) {
		model.addAttribute("utenteNuovo", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "registrazione"; // Rimuovi .html, Thymeleaf gestisce le estensioni
	}

    @PostMapping("/registrazione")
    public String registrazioneAutore(@Valid @ModelAttribute("utenteNuovo") Utente utente,
                                       BindingResult userBindingResult,
                                       @Valid @ModelAttribute("credenziali") Credenziali credenziali,
                                       BindingResult credentialsBindingResult,
                                       Model model) {
        if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
            return "registrazione"; // Ritorna al modulo con errori
        }

        if (credenzialiService.getCredenziali(credenziali.getUsername()) != null) {
            model.addAttribute("errore", "Username già esistente."); // Aggiungi un messaggio di errore
            return "registrazione"; // Ritorna al modulo con messaggio di errore
        }

        credenziali.setRuolo("UTENTE");
        credenzialiService.saveCredenziali(credenziali);
        credenziali.setUtente(utente);
        utente.setCredenziali(credenziali);
        utenteService.saveNewUtente(utente);
        return "redirect:/login";
    }

}
