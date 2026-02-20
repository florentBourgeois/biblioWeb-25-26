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
