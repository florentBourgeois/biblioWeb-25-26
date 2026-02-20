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

    @Query("select emprunts from Membre where id=:id")
    List<Livre> findEmpruntById(Long id);



}
