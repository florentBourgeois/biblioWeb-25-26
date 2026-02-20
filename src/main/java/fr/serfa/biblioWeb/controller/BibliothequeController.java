package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;
import fr.serfa.biblioWeb.model.Membre;
import fr.serfa.biblioWeb.services.AuteurService;
import fr.serfa.biblioWeb.services.AuteurServiceImp;
import fr.serfa.biblioWeb.services.LivreService;
import fr.serfa.biblioWeb.services.MembreService;
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
    private LivreService livreService;
    private MembreService membreService;

    public BibliothequeController(AuteurService auteurService, LivreService livreService, MembreService membreService) {
        this.auteurService = auteurService;
        this.livreService = livreService;
        this.membreService = membreService;




        System.out.println("-------------------------------\nBibliothequeController initialisé avec " + this.livreService.getNumberLivres() + " livres et " + this.auteurService.nombreAuteurs() + " auteurs.\n-------------------------------");
    }

    @GetMapping("/livres/ajouterCHEAT")
    public Livre ajouterLivreMagique() {
        return this.livreService.creerRandom();
    }

    @GetMapping("/livres/premier")
    public Livre getPremierLivre(){
        return this.livreService.getFirst();
    }

    @GetMapping("/livres")
    public List<Livre>  getLivres(){
        return this.livreService.getAll();
    }

    @GetMapping("/livres/{id}")
    public ResponseEntity<Livre> getParID(@PathVariable Long id){
        Livre l = this.livreService.getById(id);

        if(l == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(l);
    }



    @GetMapping("/livres/titre/{titreRecherche}")
    public List<Livre> getParTitre(@PathVariable String titreRecherche){
       return  this.livreService.getByTitre(titreRecherche);
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
        return this.membreService.getAllMembres();
    }

    @GetMapping("/admin/membres/{id}/livres")
    public List<Livre> livresEmpruntesPar(@PathVariable  Long id){
        Membre m = this.membreService.getParID(id);
        return m.getEmprunts();
    }

    @GetMapping ("/livres/disponibles")
    public List<Livre> livresDisponibles(){
        //TODO a mettre dans le service
        List<Livre> result = new ArrayList<>();
        for (Membre m : this.membreService.getAllMembres()){
            for (Livre l : m.getEmprunts()){
                result.remove(l);
            }
        }
        return result;
    }

    // pourquoi ca ne marche pas ?
    @GetMapping("/admin/livres/{idLivre}/emprunteur")
    public Membre emprunteurDe(@PathVariable int idLivre){
        //TODO a mettre dnas le service
        /*Livre cherche = this.livres.get(idLivre);
        for (Membre m : this.membreService.getAllMembres()){
                if (m.getEmprunts().contains(cherche)){
                    return m;
            }
        }*/
        return null;
    }


    @PostMapping("/admin/autheur/{id}/livres")
    public Livre ajouterLivre(@PathVariable int id, @RequestBody Livre livre){
        //TODO a mettre dnas le service
    /*
        Auteur auteurDuLivre = this.getAuteurParID(id);
        auteurDuLivre.getLivres().add(livre);
        livre.setAuteur(auteurDuLivre);
        this.livres.add(livre);
        return livre;
        */
        return null;
    }

    @PatchMapping("/admin/membre/{idMembre}/emprunter/livres/{idLivre}")
    public ResponseEntity<Object> emprunterLivre(@PathVariable Long idMembre, @PathVariable int idLivre){
        //TODO a mettre dnas le service
/*        Membre m = this.membreService.getParID(id);
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
        */

        return null;
    }
    // TODO : reprendre la route /livres/{id} et renvoyer 404 si l'id n'existe pas







}
