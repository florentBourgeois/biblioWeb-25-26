Qu'est-ce qu'un EntityGraph ?
Définition
Un EntityGraph est une fonctionnalité JPA (depuis JPA 2.1) qui permet de définir quels champs/relations d'une entité doivent être chargés dans une requête spécifique, en surchargeant temporairement la stratégie de fetch (LAZY/EAGER) par défaut.

C'est une alternative plus flexible et propre au fetch join JPQL.

Problème qu'il résout
Sans EntityGraph :
Tu as Auteur avec livres en LAZY
Si tu fais auteurDAO.findById(1) → Hibernate charge l'auteur mais pas ses livres
Si tu accèdes à auteur.getLivres() hors transaction → LazyInitializationException
Avec EntityGraph :
Tu peux dire à Hibernate : "pour cette requête spécifique, charge aussi livres en même temps"
Hibernate génère un LEFT JOIN FETCH automatiquement
Résultat : tu obtiens l'auteur avec ses livres chargés, sans exception lazy
Comment ça fonctionne : 2 façons de définir un EntityGraph
1. EntityGraph déclaratif (via annotation sur l'entité)
   Tu définis le graphe directement dans l'entité Auteur :

@Entity
@NamedEntityGraph(
name = "Auteur.withLivres",  // Nom du graphe
attributeNodes = @NamedAttributeNode("livres")  // Champs à charger
)
public class Auteur {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

    private String nom;
    
    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livre> livres = new ArrayList();
    
    // ...reste du code...
}
Explications :

@NamedEntityGraph : déclare un graphe réutilisable nommé "Auteur.withLivres"
attributeNodes : liste les champs à charger (ici "livres")
Tu peux avoir plusieurs @NamedEntityGraph avec des noms différents
Puis tu l'utilises dans ton Repository Spring Data JPA :

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {

    @EntityGraph(value = "Auteur.withLivres")
    List<Auteur> findAll();
    
    @EntityGraph(value = "Auteur.withLivres")
    Optional<Auteur> findById(Integer id);
}
Résultat SQL généré :

SELECT a.*, l.*
FROM auteur a
LEFT JOIN livre l ON a.id = l.auteur_id
2. EntityGraph dynamique (via EntityManager)
   Si tu n'utilises pas Spring Data JPA Repository, tu peux créer un EntityGraph programmatiquement dans ton DAO :

@Repository
public class AuteurDAOJPA implements AuteurDAO {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Auteur> findAll() {
        // Créer un EntityGraph dynamique
        EntityGraph<Auteur> graph = em.createEntityGraph(Auteur.class);
        graph.addAttributeNodes("livres");  // Ajouter le champ livres au graphe
        
        // Exécuter la requête avec le graphe
        return em.createQuery("SELECT a FROM Auteur a", Auteur.class)
                 .setHint("jakarta.persistence.fetchgraph", graph)
                 .getResultList();
    }
    
    @Override
    public Auteur findById(int id) {
        EntityGraph<Auteur> graph = em.createEntityGraph(Auteur.class);
        graph.addAttributeNodes("livres");
        
        return em.find(Auteur.class, id, 
                       Map.of("jakarta.persistence.fetchgraph", graph));
    }
}
Explications :

em.createEntityGraph(Auteur.class) : crée un graphe pour Auteur
graph.addAttributeNodes("livres") : dit "charge aussi livres"
setHint("jakarta.persistence.fetchgraph", graph) : applique le graphe à la requête
Hibernate génère automatiquement le JOIN
Différence entre fetchgraph et loadgraph
Il existe 2 types de hints pour EntityGraph :

Hint	Comportement
jakarta.persistence.fetchgraph	Charge uniquement les attributs spécifiés dans le graphe. Tout le reste est LAZY (même si certains champs sont EAGER par défaut).
jakarta.persistence.loadgraph	Charge les attributs du graphe + les attributs EAGER par défaut de l'entité.
Exemple :

Tu as Auteur avec livres (LAZY) et pays (EAGER)
Avec fetchgraph sur livres : charge livres + les champs basiques (id, nom), mais pas pays
Avec loadgraph sur livres : charge livres + pays (car pays est EAGER)
En général, on utilise fetchgraph pour avoir un contrôle précis.

EntityGraph vs Fetch Join JPQL
Fetch Join JPQL (approche classique)
@Query("SELECT a FROM Auteur a LEFT JOIN FETCH a.livres WHERE a.id = :id")
Auteur findByIdWithLivres(@Param("id") int id);
Avantages :

Contrôle total sur la requête (WHERE, ORDER BY, etc.)
Marche partout (JPA standard)
Inconvénients :

Tu dois écrire la requête JPQL manuellement
Si tu veux différentes combinaisons de fetch, tu dois écrire plusieurs méthodes
EntityGraph (approche déclarative)
@EntityGraph(value = "Auteur.withLivres")
Auteur findById(Integer id);
Avantages :

Déclaratif et réutilisable
Pas besoin d'écrire de JPQL
Plus facile à composer (plusieurs graphes : withLivres, withLivresAndEditeur, etc.)
Spring Data génère automatiquement la requête avec les joins
Inconvénients :

Moins de contrôle sur la requête SQL générée
Peut être moins performant dans certains cas complexes
Tableau comparatif des solutions
Critère	EntityGraph	Fetch Join JPQL	@Transactional partout
Flexibilité	✅ Réutilisable, déclaratif	⚠️ Une méthode par cas	❌ Pas flexible
Performance	✅ Une seule requête	✅ Une seule requête	❌ Requêtes N+1 possibles
Simplicité	✅ Pas de JPQL à écrire	⚠️ JPQL manuel	✅ Annotation simple
Contrôle	⚠️ Moins de contrôle SQL	✅ Contrôle total	❌ Aucun contrôle
Cas d'usage avancés : EntityGraph imbriqués
Si tu as des relations imbriquées (ex: Auteur → Livre → Editeur), tu peux les charger toutes :

@NamedEntityGraph(
name = "Auteur.withLivresAndEditeur",
attributeNodes = @NamedAttributeNode(
value = "livres",
subgraph = "livres-subgraph"
),
subgraphs = @NamedSubgraph(
name = "livres-subgraph",
attributeNodes = @NamedAttributeNode("editeur")
)
)
Cela charge : Auteur → livres → editeur en une seule requête.

Exemple concret pour ton projet
Étape 1 : Définir le graphe dans Auteur.java
package fr.serfa.biblioWeb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(
name = "Auteur.withLivres",
attributeNodes = @NamedAttributeNode("livres")
)
public class Auteur {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

    private String nom;
    private LocalDate naissance;
    private String mort;
    
    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livre> livres = new ArrayList();
    
    // ...reste du code...
}
Étape 2 : Utiliser le graphe dans ton DAO
Option A - Si tu utilises EntityManager directement :

@Override
public List<Auteur> findAll() {
EntityGraph<Auteur> graph = em.createEntityGraph(Auteur.class);
graph.addAttributeNodes("livres");

    return em.createQuery("SELECT a FROM Auteur a", Auteur.class)
             .setHint("jakarta.persistence.fetchgraph", graph)
             .getResultList();
}
Option B - Si tu utilises un Repository Spring Data :

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {

    @EntityGraph(value = "Auteur.withLivres", type = EntityGraph.EntityGraphType.FETCH)
    List<Auteur> findAll();
}
Résultat
✅ Quand tu fais auteurDAO.findAll(), Hibernate charge les auteurs avec leurs livres
✅ Ton System.out.println(...) dans le constructeur fonctionne sans LazyInitializationException
✅ Hibernate génère une requête SQL optimisée avec LEFT JOIN
✅ Pas de requêtes N+1 !

Résumé en 3 points
EntityGraph = dire à JPA quoi charger : au lieu de laisser JPA charger selon LAZY/EAGER par défaut, tu spécifies pour chaque requête ce que tu veux

Deux syntaxes : déclarative avec @NamedEntityGraph sur l'entité, ou dynamique avec EntityManager.createEntityGraph()

Avantage principal : évite LazyInitializationException ET optimise les performances (une seule requête SQL au lieu de N+1)

Recommandation pour ton cas : utiliser EntityGraph pour charger les livres quand tu as besoin d'afficher un auteur avec ses livres (comme dans ton toString()).