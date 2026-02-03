package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;
import fr.serfa.biblioWeb.model.Membre;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@RestController
public class BibliothequeController {

    private List<Auteur> auteurs = new ArrayList<>();
    private List<Livre> livres = new ArrayList<>();
    private List<Membre> membres = new ArrayList<>();

    public BibliothequeController() {
        Auteur jkr = new Auteur("J.K.Rowling", LocalDate.of(1963,1,1));
        this.auteurs.add(jkr);
        Auteur fred = new Auteur("fred", LocalDate.of(1756,1,1));
        this.auteurs.add(fred);
        Auteur huxley = new Auteur("Aldous Huxley", LocalDate.of(1894,7,26), "1963-11-22");
        this.auteurs.add(huxley);
        Auteur orwell = new Auteur("George Orwell", LocalDate.of(1903,6,25), "1950-01-21");
        this.auteurs.add(orwell);
        Auteur victorhugo = new Auteur("Victor Hugo", LocalDate.of(1802,2,26), "1885-05-22");
        this.auteurs.add(victorhugo);
        Auteur sansLivre = new Auteur("Auteur Sans Livre", LocalDate.of(1900,1,1));


        Livre harryp = new Livre(jkr, 2000, "harry potter", "3456776543");
        livres.add(harryp);
        livres.add(new Livre(jkr, 2002, "harry potter2", "3456776543"));
        livres.add(new Livre(jkr, 2003, "harry potter3", "3456776543"));
        livres.add(new Livre(jkr, 2007, "harry potter5", "3456776543"));

        livres.add(new Livre(fred, 1800, "ce livre", "345677YG"));

        livres.add(new Livre(huxley, 1932, "Le Meilleur des mondes", "9782266165875"));
        livres.add(new Livre(orwell, 1949, "1984", "9780451524935"));
        livres.add(new Livre(victorhugo, 1862, "Les Misérables", "9782070409185"));
        livres.add(new Livre(victorhugo, 1831, "Le Bossu de Notre-Dame", "9782070409192"));
        livres.add(new Livre(victorhugo, 1856, "Les Travailleurs de la mer", "9782070409208"));

        Membre m1 = new Membre("Florent", "2001");
        Membre m2 = new Membre("Sans Emprunt", "2020");
        Membre m3 = new Membre("Jack", "1922");
        Membre m4 = new Membre("Mike", "2026");
        Membre m5 = new Membre("Harry", "2026");

        m1.getEmprunts().add(livres.get(6));
        m1.getEmprunts().add(livres.get(9));

        m5.getEmprunts().add(livres.get(0));
        m5.getEmprunts().add(livres.get(2));
        m5.getEmprunts().add(livres.get(3));
        m5.getEmprunts().add(livres.get(4));

        this.membres.add(m1);
        this.membres.add(m2);
        this.membres.add(m3);
        this.membres.add(m4);
        this.membres.add(m5);



        System.out.println("-------------------------------\nBibliothequeController initialisé avec " + this.livres.size() + " livres et " + this.auteurs.size() + " auteurs.\n-------------------------------");
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



    @GetMapping("/auteurs")
    public List<Auteur>  getAuteurs(){
        return this.auteurs;
    }


    @GetMapping("/auteurs/{id}")
    public Auteur getAuteurParID(@PathVariable int id){
        return this.auteurs.get(id);
    }


    @GetMapping("/auteurs/nom/{nomRecherche}")
    public List<Auteur> getAuteurParNom(@PathVariable String nomRecherche){
        nomRecherche = nomRecherche.toLowerCase(Locale.ROOT);
        List<Auteur> result = new ArrayList<>();
        for (Auteur a : this.auteurs){
            if(a.getNom().toLowerCase().contains(nomRecherche)){
                result.add(a);
            }
        }
        return result;
    }

    // ROUTE POUR RECUPERER TOUS LES LIVRES D'UN AUTEUR PAR SON ID
    @GetMapping("/auteurs/{id}/livres")
    public List<Livre> getLivreParAuteur(@PathVariable int id){
        Auteur auteur =  this.getAuteurParID(id);
        return auteur.getLivres();
    }


    @GetMapping("/admin/membres")
    public List<Membre>  getMembres(){
        return this.membres;
    }

    @GetMapping("/admin/membres/{id}/livres")
    public List<Livre> livresEmpruntesPar(@PathVariable  int id){
        Membre m = this.membres.get(id);
        return m.getEmprunts();
    }

    @GetMapping ("/livres/disponibles")
    public List<Livre> livresDisponibles(){
        List<Livre> result = new ArrayList<>(this.livres);
        for (Membre m : this.membres){
            for (Livre l : m.getEmprunts()){
                result.remove(l);
            }
        }
        return result;
    }

    // pourquoi ca ne marche pas ?
    @GetMapping("/admin/livres/{idLivre}/emprunteur")
    public Membre emprunteurDe(@PathVariable int idLivre){
        Livre cherche = this.livres.get(idLivre);
        for (Membre m : membres){
            for (Livre l : livres) {
                if (cherche == l)
                    return m;
            }
        }
        return null;
    }


    @PostMapping("/admin/autheur/{id}/livres")
    public Livre ajouterLivre(@PathVariable int id, @RequestBody Livre livre){
        Auteur auteurDuLivre = this.getAuteurParID(id);
        auteurDuLivre.getLivres().add(livre);
        livre.setAuteur(auteurDuLivre);
        this.livres.add(livre);
        return livre;
    }






}
