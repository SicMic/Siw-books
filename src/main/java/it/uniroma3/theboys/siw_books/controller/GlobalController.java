package it.uniroma3.theboys.siw_books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import it.uniroma3.theboys.siw_books.model.Credenziali;
import it.uniroma3.theboys.siw_books.model.Utente;
import it.uniroma3.theboys.siw_books.service.CredenzialiService;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private CredenzialiService credenzialiService;

    @ModelAttribute("userDetails")

    public UserDetails getUser() {

        UserDetails user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return user;
    }

    @ModelAttribute("ruolo")
    public String getRuolo() {

        String ruolo = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credenziali credenziali = this.credenzialiService.getCredenziali(userDetails.getUsername());
            ruolo = credenziali.getRuolo();
        }
        return ruolo.toLowerCase();
    }

    @ModelAttribute("utente")
    public Utente getUtente() {

        Utente utente = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            Credenziali credenziali = this.credenzialiService.getCredenziali(userDetails.getUsername());
            utente = credenziali.getUtente();
        }

        return utente;
    }

}
