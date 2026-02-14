package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface AuteurDAO_JPA extends JpaRepository<Auteur, Long>, AuteurDAO{

    public default void add(Auteur a){
        save(a);
    }

    public List<Auteur> findAll();

    public Auteur findById(int id);
    //public Optional<Auteur> maybeFindByID(int id);

    public default int size(){
        return findAll().size();
    }

    // si mon stockage est inteligent / peut filtrer
    public List<Auteur> findByNom(String nom);


}
