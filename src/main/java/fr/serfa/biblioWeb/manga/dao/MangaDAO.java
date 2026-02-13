package fr.serfa.biblioWeb.manga.dao;

import fr.serfa.biblioWeb.manga.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaDAO extends JpaRepository<Manga, Long>{



}
