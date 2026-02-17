package fr.serfa.biblioWeb.services;

import fr.serfa.biblioWeb.manga.dao.LivreDAO;
import fr.serfa.biblioWeb.model.Livre;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivreService {

    private LivreDAO livreDAO;

    public LivreService(LivreDAO livreDAO) {
        this.livreDAO = livreDAO;
    }

    public Livre add(Livre l){
        return livreDAO.save(l);
    }

    public Livre creerRandom(){
        Faker faker = new Faker();
        String titre = faker.book().title();
        Livre ajoute = new Livre(null, 2026, titre, "9876543");
        return livreDAO.save(ajoute);
    }

    public Livre getFirst(){
        return livreDAO.findAll().getFirst();
    }

    public List<Livre> getAll(){
        return livreDAO.findAll();
    }

    public Livre getById(Long id){
        return livreDAO.findById(id).get();
    }

    public List<Livre> getByTitre(String titre){
        return livreDAO.findByTitreContains(titre);
    }

    public  int getNumberLivres(){
        return livreDAO.findAll().size();
    }

}
