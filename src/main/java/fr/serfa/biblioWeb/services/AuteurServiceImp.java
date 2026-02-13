package fr.serfa.biblioWeb.services;

import fr.serfa.biblioWeb.model.Auteur;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component @Qualifier
public class AuteurServiceImp implements AuteurService{

    private List<Auteur> auteurs = new ArrayList<>();

    public AuteurServiceImp() {
        Auteur jkr = new Auteur("J.K.Rowling", LocalDate.of(1963,1,1));
        this.auteurs.add(jkr);
        Auteur fred = new Auteur("fred", LocalDate.of(1756,1,1));
        this.auteurs.add(fred);
        Auteur huxley = new Auteur("Aldous Huxley", LocalDate.of(1894,7,26), "1963-11-22");
        this.auteurs.add(huxley);
        Auteur orwell = new Auteur("George Orwell", LocalDate.of(1903,6,25), "1950-01-21");
        this.auteurs.add(orwell);
        Auteur victorhugo = new Auteur("Victor Hugo", LocalDate.of(1802,2,26), "1885-05-22");
        this.auteurs.add(victorhugo);
        Auteur sansLivre = new Auteur("Auteur Sans Livre", LocalDate.of(1900,1,1));
    }

    @Override
    public void ajouterAuteur(String nom, String dateNaissance, String dateDeces) {
        
    }

    @Override
    public List<Auteur> consulterLesAuteurs() {
        return this.auteurs;
    }

    @Override
    public Auteur detailsAuteur(int id) {
        return this.auteurs.get(id);
    }

    @Override
    public List<Auteur> rechercheParNom(String nom) {
        nom = nom.toLowerCase(Locale.ROOT);
        List<Auteur> result = new ArrayList<>();
        for (Auteur a : this.auteurs){
            if(a.getNom().toLowerCase().contains(nom)){
                result.add(a);
            }
        }
        return result;
    }

    @Override
    public int nombreAuteurs() {
        return this.auteurs.size();
    }
}
