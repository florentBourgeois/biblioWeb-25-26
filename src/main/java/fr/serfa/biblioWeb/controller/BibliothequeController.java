package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;

import java.util.ArrayList;
import java.util.List;

public class BibliothequeController {

    private List<Auteur> auteurs = new ArrayList<>();
    private List<Livre> livres = new ArrayList<>();


    public Livre getPremierLivre(){
        return this.livres.getFirst();
    }

}
