package it.uniroma3.theboys.siw_books.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Credenziali {

    public static final String ADMIN_ROLE = "ADMIN";
	public static final String UTENTE_ROLE= "UTENTE";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "credenziali")
    private Utente utente;

    private String ruolo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuole(String ruolo) {
        this.ruolo = ruolo;
    }

}
