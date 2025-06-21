package it.uniroma3.theboys.siw_books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.theboys.siw_books.model.Utente;
import it.uniroma3.theboys.siw_books.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Iterable<Utente> getAllUtenti() {
        return this.utenteRepository.findAll();
    }

    public Utente getUtenteById(Long id) {
        return this.utenteRepository.findById(id).get();
    }

    public Utente saveNewUtente(Utente utente) {
        return this.utenteRepository.save(utente);
    }
    
}
