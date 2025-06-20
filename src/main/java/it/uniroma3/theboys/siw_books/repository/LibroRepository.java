package it.uniroma3.theboys.siw_books.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.theboys.siw_books.model.Libro;

//classe per le operazioni della persistenza (CRUD: CREATE, READ, UPDATE, DELETE)
public interface LibroRepository extends CrudRepository<Libro, Long> {

  
}