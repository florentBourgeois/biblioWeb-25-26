package fr.serfa.biblioWeb.model;

import jakarta.persistence.*;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String isbn;
    private String titre;
    private int anneePublication;

    @ManyToOne
    @JoinColumn(name = "auteur_ID")
    private Auteur auteur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre() {
    }

    public Livre(String isbn, String titre, int anneePublication) {
        this.isbn = isbn;
        this.titre = titre;
        this.anneePublication = anneePublication;
    }

    public Livre(Auteur auteur, int anneePublication, String titre, String isbn) {
        this.auteur = auteur;
        if(auteur != null)
            this.auteur.addLivre(this);
        this.anneePublication = anneePublication;
        this.titre = titre;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur a) {
        this.auteur = a;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", anneePublication=" + anneePublication +
                ", auteur=" + auteur.getNom() +
                '}';
    }
}
