package it.uniroma3.theboys.siw_books.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.theboys.siw_books.service.LibroService;



@Controller
public class ProvaController {

	@Autowired private LibroService libroService;

	@GetMapping({"/", "/index"})
	public String getVetrina(Model model) {
		model.addAttribute("libri", libroService.getAllLibri());
		return "index.html";
	}

	@GetMapping("/libro/{idLibro}")
	public String getLibro(Model model, @PathVariable("idLibro") Long idLibro) {
		model.addAttribute("libro", libroService.getLibroById(idLibro));
		return "libro.html";
	}



}
