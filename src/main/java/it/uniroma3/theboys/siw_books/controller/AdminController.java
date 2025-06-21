package it.uniroma3.theboys.siw_books.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		return "libroForm.html";
	}

	@PostMapping("/aggiuntaLibro")
	public String postAggiuntaNuovoLibro(Model model, Libro libro) {
		this.libroService.saveNewLibro(libro);
		return "redirect:/";
	}

	@GetMapping("/area-riservata/autoreForm")
	public String getAggiuntaNuovoAutore(Model model) {
		model.addAttribute("autore", new Autore());
		return "autoreForm.html";
	}

	@PostMapping("/area-riservata/aggiuntaAutore")
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

	@PostMapping("/eliminazioneLibro")
	public ResponseEntity<Map<String, String>> eliminazioneLibro(@RequestParam("idLibro") Long idLibro) {
		Map<String, String> response = new HashMap<>();
		if (idLibro == null || idLibro < 1) {
			response.put("error", "ID Raccolta non valido");
			return ResponseEntity.badRequest().body(response);
		}
		try {
			if (libroService.getLibroById(idLibro) == null) {
				response.put("error", "Raccolta non trovata nel database");
				return ResponseEntity.ok(response);
			}
			this.libroService.deleteLibro(idLibro);
			response.put("message", "Raccolta eliminata con successo");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("error", "Errore durante l'eliminazione della raccolta: " +
					e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/eliminazioneRecensione")
	public ResponseEntity<Map<String, String>> eliminazioneRecensione(@RequestParam("idRecensione") Long idRecensione) {
		Map<String, String> response = new HashMap<>();
		if (idRecensione == null || idRecensione < 1) {
			response.put("error", "ID Raccolta non valido");
			return ResponseEntity.badRequest().body(response);
		}
		try {
			if (recensioneService.getRecensioneById(idRecensione) == null) {
				response.put("error", "Raccolta non trovata nel database");
				return ResponseEntity.ok(response);
			}
			this.recensioneService.deleteRecensione(idRecensione);
			response.put("message", "Raccolta eliminata con successo");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("error", "Errore durante l'eliminazione della raccolta: " +
					e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/eliminazioneAutore")
	public ResponseEntity<Map<String, String>> eliminazioneAutore(@RequestParam("idAutore") Long idAutore) {
		Map<String, String> response = new HashMap<>();
		if (idAutore == null || idAutore < 1) {
			response.put("error", "ID Raccolta non valido");
			return ResponseEntity.badRequest().body(response);
		}
		try {
			if (autoreService.getAutoreById(idAutore) == null) {
				response.put("error", "Raccolta non trovata nel database");
				return ResponseEntity.ok(response);
			}
			this.autoreService.deleteAutore(idAutore);
			response.put("message", "Raccolta eliminata con successo");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("error", "Errore durante l'eliminazione della raccolta: " +
					e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
