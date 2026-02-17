package fr.serfa.biblioWeb.manga.dao;

import fr.serfa.biblioWeb.manga.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MangaDAO extends JpaRepository<Manga, Long>{

    List<Manga> findByEvaluationGreaterThan(double evaluation);


}
