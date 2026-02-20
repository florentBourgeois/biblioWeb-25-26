package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;
import fr.serfa.biblioWeb.model.Membre;
import fr.serfa.biblioWeb.services.AuteurService;
import fr.serfa.biblioWeb.services.LivreService;
import fr.serfa.biblioWeb.services.MembreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin/membres/forList")
    public List<MembreSansPasswordDTO>  getMembresSansPassword(){
        List<Membre> tousLesMembres = this.membreService.getAllMembres();
        List<MembreSansPasswordDTO> result = tousLesMembres.stream().map(MembreSansPasswordDTO::fromMembre).toList();
        // meme chose que  :
        /*
            for (Membre m : tousLesMembres){
            MembreSansPassword aAjouter  = MembreSansPassword.fromMembre(m);
            result.add(aAjouter);
        }
         */
        return result;
    }

    @GetMapping("/admin/membres/forListDansBDD")
    public List<MembreSansPasswordDTO>  getMembresSansPasswordDansBDD(){
        return this.membreService.getAllMembreSansPassword();
    }

    @GetMapping("/admin/membres/avecEmprunt")
    public List<Membre>  getMembresAvecEmprunt(){
        return this.membreService.getAllMembreAvecEmprunt();
    }



    @GetMapping("/admin/membres/{id}/livres")
    public List<Livre> livresEmpruntesPar(@PathVariable  Long id){
        Membre m = this.membreService.getParID(id);
        return m.getEmprunts();
    }

    @GetMapping("/admin/membres/{id}/livresFromBDD")
    public List<Livre> livresEmpruntesParFromBDD(@PathVariable  Long id){
        return this.membreService.getEmpruntDe(id);
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
    public Membre emprunteurDe(@PathVariable Long idLivre){
        //TODO a mettre dnas le service
        Livre cherche = this.livreService.getById(idLivre);
        for (Membre m : this.membreService.getAllMembres()){
                if (m.getEmprunts().contains(cherche)){
                    return m;
            }
        }
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


    @GetMapping("/livres/forList")
    public List<LivrePourListeDTO> getLivresPourList(){
        List<Livre> tousLesLivres = livreService.getAll();

        List<LivrePourListeDTO> result = new ArrayList<>();
        for (Livre l : tousLesLivres){
            LivrePourListeDTO aAjouter = new LivrePourListeDTO(l.getIsbn(), l.getAuteur().getNom(), l.getTitre(), l.getAnneePublication());
            result.add(aAjouter);
        }

        return result;
    }

    @PostMapping("/livres")
    public Livre ajouterLivre(@RequestBody LivreAAjouterDTO_Record livreAajouterDTO){
        Auteur auteurDuLivre = auteurService.detailsAuteur((int) livreAajouterDTO.auteurID());

        Livre l = new Livre(auteurDuLivre, livreAajouterDTO.anneePublication(), livreAajouterDTO.titre(), livreAajouterDTO.isbn());
        livreService.add(l);

        return l;
    }

    @PatchMapping("/admin/membre/{idMembre}/emprunter/livres/{idLivre}")
    public ResponseEntity<Object> emprunterLivre(@PathVariable Long idMembre, @PathVariable Long idLivre){
        //TODO a mettre dnas le service
        Membre m = this.membreService.getParID(idMembre);
        Livre l = this.livreService.getById(idLivre);
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
