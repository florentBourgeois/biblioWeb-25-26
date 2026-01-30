package fr.serfa.biblioWeb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Auteur {

    private String nom;
    private LocalDate naissance;
    private String mort;
    private List<Livre> livres = new ArrayList();

    public Auteur(String nom, LocalDate naissance) {
        this.nom = nom;
        this.naissance = naissance;
    }

    public Auteur(String nom, LocalDate naissance, String mort) {
        this.nom = nom;
        this.naissance = naissance;
        this.mort = mort;
    }

    public void setMort(String mort) {
        this.mort = mort;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public String getMort() {
        return mort;
    }

    public List<Livre> getLivres() {
        return new ArrayList<>(livres);
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", naissance=" + naissance +
                ", mort='" + mort + '\'' +
                ", livres=" + livres.stream().map(l -> l.getTitre()).toList() +
                '}';
    }

    public void addLivre(Livre livre) {
        this.livres.add(livre);
    }
}
