package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.model.Auteur;

import java.util.List;

public interface AuteurDAO {

    public void add(Auteur a);
    public List<Auteur> findAll();
    public Auteur findById(int id);
    //public Optional<Auteur> maybeFindByID(int id);

    public int size();

    // si mon stockage est inteligent / peut filtrer
    public List<Auteur> findByNom(String nom);

    void deleteById(Long id);
}
