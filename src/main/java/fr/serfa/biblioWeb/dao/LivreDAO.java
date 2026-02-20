package fr.serfa.biblioWeb.dao;

import fr.serfa.biblioWeb.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivreDAO extends JpaRepository<Livre, Long> {

    List<Livre> findByTitreContains(String titre);

    @Query("select l from Livre l where l.id not in (select li.id from Membre m join m.emprunts li)")
    List<Livre> findLivresDisponibles(); // non empruntés


}
