package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreDAO extends JpaRepository<Membre, Long> {

}
