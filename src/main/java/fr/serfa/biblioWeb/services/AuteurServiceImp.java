package fr.serfa.biblioWeb.services;

import fr.serfa.biblioWeb.dao.AuteurDAO;
import fr.serfa.biblioWeb.model.Auteur;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component @Primary
public class AuteurServiceImp implements AuteurService{

    private AuteurDAO auteurDAO;

    public AuteurServiceImp(AuteurDAO auteurDAO) {
        this.auteurDAO = auteurDAO;

        Auteur jkr = new Auteur("J.K.Rowling", LocalDate.of(1963,1,1));
        this.auteurDAO.add(jkr);
        Auteur fred = new Auteur("fred", LocalDate.of(1756,1,1));
        this.auteurDAO.add(fred);
        Auteur huxley = new Auteur("Aldous Huxley", LocalDate.of(1894,7,26), "1963-11-22");
        this.auteurDAO.add(huxley);
        Auteur orwell = new Auteur("George Orwell", LocalDate.of(1903,6,25), "1950-01-21");
        this.auteurDAO.add(orwell);
        Auteur victorhugo = new Auteur("Victor Hugo", LocalDate.of(1802,2,26), "1885-05-22");
        this.auteurDAO.add(victorhugo);
        Auteur sansLivre = new Auteur("Auteur Sans Livre", LocalDate.of(1900,1,1));

    }

    @Override
    public void ajouterAuteur(String nom, String dateNaissance, String dateDeces) {

    }

    @Override
    public List<Auteur> consulterLesAuteurs() {
        return this.auteurDAO.findAll();
    }

    @Override
    public Auteur detailsAuteur(int id) {
        return this.auteurDAO.findById(id);
    }

    @Override
    public List<Auteur> rechercheParNom(String nom) {
        nom = nom.toLowerCase(Locale.ROOT);
        List<Auteur> result = new ArrayList<>();
        for (Auteur a : this.auteurDAO.findAll()){
            if(a.getNom().toLowerCase().contains(nom)){
                result.add(a);
            }
        }

        return result;
    }

    public List<Auteur> rechercheParNomDansDAO(String nom) {
        nom = nom.toLowerCase(Locale.ROOT);
        return this.auteurDAO.findByNom(nom);
    }

    @Override
    public int nombreAuteurs() {
        return this.auteurDAO.size();
    }
}
