package fr.serfa.biblioWeb.manga.dao;

import fr.serfa.biblioWeb.model.Livre;
import fr.serfa.biblioWeb.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembreDAO extends JpaRepository<Membre, Long> {

}
