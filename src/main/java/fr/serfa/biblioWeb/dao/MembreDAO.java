package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.controller.MembreSansPasswordDTO;
import fr.serfa.biblioWeb.model.Livre;
import fr.serfa.biblioWeb.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembreDAO extends JpaRepository<Membre, Long> {

    @Query("select m.id, m.nom, m.dateAbonnement from Membre m")
    List<MembreSansPasswordDTO> findAllSansPassword();

    List<Membre> findByEmprunts_IdNotNull();

    // tous les emprunts d'un membre
    @Query("select emprunts from Membre where id=:id")
    List<Livre> findEmpruntById(Long id);

    // trouver l'emprunteur d'un livre
    Membre findByEmprunts_Id(Long id);

    // trouver les emprunteurs de livres dont l'auteur est id
    List<Membre> findByEmprunts_Auteur_Id(Long id);

    // trouver les emprunteurs de livres dont l'auteur a rédigé un livre contenant le titre
    List<Membre> findByEmprunts_Auteur_Livres_Titre(String titre);


}
