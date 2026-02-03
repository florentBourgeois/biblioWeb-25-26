package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@RestController
public class BibliothequeController {

    private List<Auteur> auteurs = new ArrayList<>();
    private List<Livre> livres = new ArrayList<>();

    public BibliothequeController() {
        Auteur jkr = new Auteur("J.K.Rowling", LocalDate.of(1963,1,1));
        this.auteurs.add(jkr);
        Auteur fred = new Auteur("fred", LocalDate.of(1756,1,1));
        this.auteurs.add(fred);


        Livre harryp = new Livre(jkr, 2000, "harry potter", "3456776543");
        livres.add(harryp);
        livres.add(new Livre(jkr, 2002, "harry potter2", "3456776543"));
        livres.add(new Livre(jkr, 2003, "harry potter3", "3456776543"));
        livres.add(new Livre(jkr, 2007, "harry potter5", "3456776543"));

        livres.add(new Livre(fred, 1800, "ce livre", "345677YG"));

    }

    @GetMapping("/livres/ajouterCHEAT")
    public Livre ajouterLivreMagique() {
        Auteur jkr = new Auteur("J.K.Rowling", LocalDate.of(1963,1,1));

        Random random= new Random();
        Livre l = new Livre(jkr,1999, "livre ajouté en trichant" + random.nextInt(1000), "isbn"+random.nextInt(9999));
        this.livres.add(l);
        return l;
    }

    @GetMapping("/livres/premier")
    public Livre getPremierLivre(){
        return this.livres.getFirst();
    }

    @GetMapping("/livres")
    public List<Livre>  getLivres(){
        return this.livres;
    }

    @GetMapping("/livres/{id}")
    public Livre getParID(@PathVariable int id){
        return this.getParIndex(id); // TODO faire mieux avec id
    }

    private Livre getParIndex(int id){
        return this.livres.get(id);
    }


    @GetMapping("/livres/titre/{titreRecherche}")
    public List<Livre> getParTitre(@PathVariable String titreRecherche){
        titreRecherche = titreRecherche.toLowerCase(Locale.ROOT);
        List<Livre> result = new ArrayList<>();
        for (Livre l : this.livres){
            if(l.getTitre().toLowerCase().contains(titreRecherche)){
                result.add(l);
            }
        }
        return result;
    }






}
