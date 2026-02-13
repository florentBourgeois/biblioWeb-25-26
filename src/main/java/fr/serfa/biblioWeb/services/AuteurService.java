package fr.serfa.biblioWeb.services;

import fr.serfa.biblioWeb.model.Auteur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuteurService {

    public void ajouterAuteur(String nom, String dateNaissance, String dateDeces);
    public List<Auteur> consulterLesAuteurs();
    public Auteur detailsAuteur(int id);
    public List<Auteur> rechercheParNom(String nom);
    public int nombreAuteurs();

}
