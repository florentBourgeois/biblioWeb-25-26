package fr.serfa.biblioWeb.model;

public class Livre {
    private String isbn;
    private String titre;
    private int anneePublication;
    private Auteur auteur;

    public Livre() {
    }

    public Livre(String isbn, String titre, int anneePublication) {
        this.isbn = isbn;
        this.titre = titre;
        this.anneePublication = anneePublication;
    }

    public Livre(Auteur auteur, int anneePublication, String titre, String isbn) {
        this.auteur = auteur;
        this.auteur.getLivres().add(this);
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
