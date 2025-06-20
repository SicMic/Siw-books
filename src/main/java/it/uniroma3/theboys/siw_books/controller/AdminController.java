package it.uniroma3.theboys.siw_books.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.theboys.siw_books.service.LibroService;
import it.uniroma3.theboys.siw_books.service.RecensioneService;
import it.uniroma3.theboys.siw_books.model.Autore;
import it.uniroma3.theboys.siw_books.model.Libro;
import it.uniroma3.theboys.siw_books.model.Recensione;
import it.uniroma3.theboys.siw_books.service.AutoreService;

@Controller
public class AdminController {

	@Autowired
	private LibroService libroService;
	@Autowired
	private RecensioneService recensioneService;
	@Autowired
	private AutoreService autoreService;

	@GetMapping("/libroForm")
	public String getAggiuntaNuovoLibro(Model model) {
		model.addAttribute("libro", new Libro());
		model.addAttribute("autori", autoreService.getAllAutori());
		return "libroForm.html";
	}

	@PostMapping("/aggiuntaLibro")
	public String postAggiuntaNuovoLibro(Model model, Libro libro, @RequestParam("listaAutori") List<Long> autoriIds) {
		this.libroService.saveNewLibro(libro);
		return "redirect:/";
	}

	@GetMapping("/autoreForm")
	public String getAggiuntaNuovoAutore(Model model) {
		model.addAttribute("autore", new Autore());
		return "autoreForm.html";
	}

	@PostMapping("/aggiuntaAutore")
	public String psotAggiuntaNuovoAutore(Model model, Autore autore) {
		this.autoreService.saveNewAutore(autore);
		return "redirect:/";
	}

	@GetMapping("/recensioneForm")
	public String getAggiuntaNuovaRecensione(Model model) {
		model.addAttribute("recensione", new Recensione());
		return "recensioneForm.html";
	}

	@PostMapping("/aggiuntaRecensione")
	public String postAggiuntaNuovaRecensione(Model model, Recensione recensione) {
		this.recensioneService.saveNewRecensione(recensione);
		return "redirect:/";
	}

	@GetMapping("/eliminazioneLibro/{idLibro}")
	public String eliminazioneLibro(@PathVariable("idLibro") Long idLibro) {
		this.libroService.deleteLibro(idLibro);
		return "redirect:/";
	}

	@GetMapping("/eliminazioneRecensione/{idRecensione}")
	public String eliminazioneRecensione(@PathVariable("idRecensione") Long idRecensione) {
		this.recensioneService.deleteRecensione(idRecensione);
		return "redirect:/recensioni";
	}

	@GetMapping("/eliminazioneAutore/{idAutore}")
	public String eliminazioneAutore(@PathVariable("idAutore") Long idAutore) {
		this.autoreService.deleteAutore(idAutore);
		return "redirect:/autori";
	}


}
