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

import it.uniroma3.theboys.siw_books.model.Credenziali;

import it.uniroma3.theboys.siw_books.service.LibroService;;


@Controller
public class AdminController {


    @Autowired private LibroService libroService;

    @PostMapping("/autore/eliminazioneRaccolta")
	public ResponseEntity<Map<String, String>> eliminazioneRaccoltaAutore(@RequestParam("idLibro") Long idLibro) {
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

}
