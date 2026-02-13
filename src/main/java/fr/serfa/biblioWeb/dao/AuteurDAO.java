package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.model.Auteur;

import java.util.List;
import java.util.Optional;

public interface AuteurDAO {

    public void add(Auteur a);
    public List<Auteur> findAll();
    public Auteur findByID(int id);
    //public Optional<Auteur> maybeFindByID(int id);

    public int size();

    // si mon stockage est inteligent / peut filtrer
    public List<Auteur> findBytNom(String nom);





}
