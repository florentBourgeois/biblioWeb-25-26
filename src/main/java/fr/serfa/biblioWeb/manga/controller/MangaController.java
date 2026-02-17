package fr.serfa.biblioWeb.manga.controller;

import fr.serfa.biblioWeb.manga.dao.MangaDAO;
import fr.serfa.biblioWeb.manga.model.Manga;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manga")
public class MangaController {

    private MangaDAO mangaDAO;

    public MangaController(MangaDAO mangaDAO) {
        this.mangaDAO = mangaDAO;

        mangaDAO.save(new Manga(null, "naruto",10,10,12));
        mangaDAO.save(new Manga(null, "bleach",10,1,1));
        mangaDAO.save(new Manga(null, "solo leveling",10,20,15));



    }

    @GetMapping()
    public List<Manga> getAllManga(){
        return  mangaDAO.findAll();
    }

    @GetMapping("/{id}")
    public Manga getMangaID(@RequestParam Long id){
        return mangaDAO.findById(id).get();
    }

    @PostMapping()
    public List<Manga> addManga(@RequestBody Manga manga){
        return List.of();
    }
}
