package fr.serfa.biblioWeb.services;

import fr.serfa.biblioWeb.model.Auteur;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class AuteurServiceTest implements AuteurService{


    public AuteurServiceTest() {
    }

    @Override
    public void ajouterAuteur(String nom, String dateNaissance, String dateDeces) {
        
    }

    @Override
    public List<Auteur> consulterLesAuteurs() {
        return List.of();
    }

    @Override
    public Auteur detailsAuteur(int id) {
        return new Auteur("test", LocalDate.now(), "");
    }

    @Override
    public List<Auteur> rechercheParNom(String nom) {

        return List.of();
    }

    @Override
    public int nombreAuteurs() {
        return 0;
    }
}
