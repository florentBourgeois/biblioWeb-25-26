package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.model.Auteur;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class AuteurDAO_List implements AuteurDAO{

    private List<Auteur> auteurs = new ArrayList<>();


    @Override
    public void add(Auteur a) {
        this.auteurs.add(a);
    }

    @Override
    public List<Auteur> findAll() {
        return this.auteurs;
    }

    @Override
    public Auteur findById(int id) {
        return this.auteurs.get(id);
    }

    @Override
    public int size() {
        return this.auteurs.size();
    }

    @Override
    public List<Auteur> findByNom(String nom) {
        List<Auteur> result = new ArrayList<>();
        for (Auteur a : this.auteurs){
            if(a.getNom().toLowerCase().contains(nom)){
                result.add(a);
            }
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        this.auteurs.remove(id);
    }
}
