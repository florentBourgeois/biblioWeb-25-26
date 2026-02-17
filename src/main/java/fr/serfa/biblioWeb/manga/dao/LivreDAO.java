package fr.serfa.biblioWeb.manga.dao;

import fr.serfa.biblioWeb.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreDAO extends JpaRepository<Livre, Long> {

    List<Livre> findByTitreContains(String titre);

}
