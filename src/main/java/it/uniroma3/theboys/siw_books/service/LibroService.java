package it.uniroma3.theboys.siw_books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.theboys.siw_books.model.Libro;
import it.uniroma3.theboys.siw_books.repository.LibroRepository;

//classe per definire le operazioni CRUD tramite metodi java: descrive i servizi offerti
@Service
public class LibroService {
	
	@Autowired private LibroRepository libroRepository;	//istanza costruita e inizializzata dal framework


    public Libro getLibroById(Long id) {
        return this.libroRepository.findById(id).get();
    }

	public Iterable<Libro> getAllLibri(){
		return this.libroRepository.findAll();
	}
	
	public Libro saveNewLibro(Libro libro){
		return this.libroRepository.save(libro);
	}

	public void deleteLibro(Long id){
		this.libroRepository.deleteById(id);
	}


}