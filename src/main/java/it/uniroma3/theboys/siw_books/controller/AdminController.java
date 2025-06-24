package it.uniroma3.theboys.siw_books.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.theboys.siw_books.model.Autore;
import it.uniroma3.theboys.siw_books.model.Libro;
import it.uniroma3.theboys.siw_books.model.Recensione;
import it.uniroma3.theboys.siw_books.service.AutoreService;
import it.uniroma3.theboys.siw_books.service.LibroService;
import it.uniroma3.theboys.siw_books.service.RecensioneService;
import jakarta.servlet.http.HttpServletRequest;

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
public String aggiungiLibro(@ModelAttribute Libro libro, 
                            @RequestParam("copertinaFile") MultipartFile copertinaFile) {
    if (!copertinaFile.isEmpty()) {
        try {
            // Salva il file nella cartella "copertine" dentro /static/images (es. src/main/resources/static/images/copertine)
            String nomeFile = System.currentTimeMillis() + "_" + copertinaFile.getOriginalFilename();
            Path percorso = Paths.get("uploads/copertine/" + nomeFile);

            // Assicura che la directory esista
            Files.createDirectories(percorso.getParent());

            // Salva il file
            Files.write(percorso, copertinaFile.getBytes());

            // Imposta il path (visibile via web) nel libro
            libro.setCopertina("uploads/copertine/" + nomeFile);

        } catch (IOException e) {
            e.printStackTrace();
            // Puoi aggiungere logging o messaggi di errore nel model
        }
    }

    libroService.saveNewLibro(libro);
    return "redirect:/index";
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

	@PostMapping("/aggiuntaRecensione")
	public String postAggiuntaNuovaRecensione(Model model, Recensione recensione, HttpServletRequest request) {
		this.recensioneService.saveNewRecensione(recensione);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer; // Reindirizza alla pagina precedente	
	}

	@GetMapping("/eliminazioneLibro/{idLibro}")
	public String eliminazioneLibro(@PathVariable("idLibro") Long idLibro) {
		this.libroService.deleteLibro(idLibro);
		return "redirect:/";
	}

	@GetMapping("/eliminazioneRecensione/{idRecensione}")
	public String eliminazioneRecensione(@PathVariable("idRecensione") Long idRecensione,  HttpServletRequest request) {
		this.recensioneService.deleteRecensione(idRecensione);
        // Reindirizza alla pagina precedente
        String referer = request.getHeader("Referer");
        return "redirect:" + referer; // Reindirizza alla pagina precedente	
	}

	@GetMapping("/eliminazioneAutore/{idAutore}")
	public String eliminazioneAutore(@PathVariable("idAutore") Long idAutore) {
		this.autoreService.deleteAutore(idAutore);
		return "redirect:/autori";
	}

}
