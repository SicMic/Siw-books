package it.uniroma3.theboys.siw_books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.theboys.siw_books.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente, Long>{

}
