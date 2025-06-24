package it.uniroma3.theboys.siw_books.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.theboys.siw_books.model.Autore;
import it.uniroma3.theboys.siw_books.model.Recensione;

import it.uniroma3.theboys.siw_books.service.AutoreService;
import it.uniroma3.theboys.siw_books.service.LibroService;
import it.uniroma3.theboys.siw_books.service.RecensioneService;



@Controller
public class Pagine {

	@Autowired private LibroService libroService;
	@Autowired private AutoreService autoreService;
	@Autowired private RecensioneService recensioneService;


	@GetMapping({"/", "/index"})
	public String getVetrina(Model model) {
		model.addAttribute("libri", libroService.getAllLibri());
		return "index.html";
	}

	@GetMapping("/libro/{idLibro}")
	public String getLibro(Model model, @PathVariable("idLibro") Long idLibro) {
		model.addAttribute("libro", libroService.getLibroById(idLibro));
		model.addAttribute("recensione", new Recensione());
		return "libro.html";
	}

	@GetMapping("/autori")
	public String getAutori(Model model) {
		model.addAttribute("autori", autoreService.getAllAutori());
		return "autori.html";
	}

	@GetMapping("/autore/{idAutore}")
	public String getAutore(Model model, @PathVariable("idAutore") Long idAutore) {
		Autore autore = autoreService.getAutoreById(idAutore);
		model.addAttribute("autore", autore);
		model.addAttribute("libri", autore.getLibri());
		return "autore.html";
	}

	@GetMapping("/recensioni")
	public String getRecensioni(Model model) {
		model.addAttribute("recensioni", recensioneService.getAllRecensioni());
		return "recensioni.html";
	}

}
