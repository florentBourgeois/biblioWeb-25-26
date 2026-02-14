package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;
import fr.serfa.biblioWeb.model.Membre;
import fr.serfa.biblioWeb.services.AuteurService;
import fr.serfa.biblioWeb.services.AuteurServiceImp;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class BibliothequeController {


    private AuteurService auteurService ;
    private List<Livre> livres = new ArrayList<>();
    private List<Membre> membres = new ArrayList<>();

    public BibliothequeController(AuteurService auteurService) {
        this.auteurService = auteurService;
        /*
        Auteur jkr = auteurService.detailsAuteur(1);
        Livre harryp = new Livre(jkr, 2000, "harry potter", "3456776543");
        livres.add(harryp);
        livres.add(new Livre(jkr, 2002, "harry potter2", "3456776543"));
        livres.add(new Livre(jkr, 2003, "harry potter3", "3456776543"));
        livres.add(new Livre(jkr, 2007, "harry potter5", "3456776543"));

        Auteur fred = auteurService.detailsAuteur(1);
        livres.add(new Livre(fred, 1800, "ce livre", "345677YG"));

        livres.add(new Livre(auteurService.detailsAuteur(2), 1932, "Le Meilleur des mondes", "9782266165875"));
        livres.add(new Livre(auteurService.detailsAuteur(3), 1949, "1984", "9780451524935"));
        livres.add(new Livre(auteurService.detailsAuteur(4), 1862, "Les Misérables", "9782070409185"));
        livres.add(new Livre(auteurService.detailsAuteur(4), 1831, "Le Bossu de Notre-Dame", "9782070409192"));
        livres.add(new Livre(auteurService.detailsAuteur(4), 1856, "Les Travailleurs de la mer", "9782070409208"));

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

        */

        System.out.println("-------------------------------\nBibliothequeController initialisé avec " + this.livres.size() + " livres et " + this.auteurService.nombreAuteurs() + " auteurs.\n-------------------------------");
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
    public ResponseEntity<Livre> getParID(@PathVariable int id){
        if(id > livres.size())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.livres.get(id));
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
        return this.auteurService.consulterLesAuteurs();
    }


    @GetMapping("/auteurs/{id}")
    public Auteur getAuteurParID(@PathVariable int id){
        return this.auteurService.detailsAuteur(id);
    }


    @GetMapping("/auteurs/nom/{nomRecherche}")
    public List<Auteur> getAuteurParNom(@PathVariable String nomRecherche){
        return this.auteurService.rechercheParNom(nomRecherche);
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
        for (Membre m : this.membres){
                if (m.getEmprunts().contains(cherche)){
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

    @PatchMapping("/admin/membre/{idMembre}/emprunter/livres/{idLivre}")
    public ResponseEntity<Object> emprunterLivre(@PathVariable int idMembre, @PathVariable int idLivre){
        Membre m = this.membres.get(idMembre);
        Livre l = this.livres.get(idLivre);
        if(this.livresDisponibles().contains(l)) {
            m.getEmprunts().add(l);
            // permet de renvoyer un code 200 et le livre emprunté
            return ResponseEntity.ok(l);
        }
        Map<String, String> body = new HashMap<>();
        body.put("message","livre emprunté");
        // permet de renvoyer un code 409 et un message à prendre en compte
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    // TODO : reprendre la route /livres/{id} et renvoyer 404 si l'id n'existe pas







}
