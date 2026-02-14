package fr.serfa.biblioWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate naissance;
    private String mort;

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livre> livres = new ArrayList();


    public Auteur(){

    }

    public Auteur(String nom, LocalDate naissance) {
        this.nom = nom;
        this.naissance = naissance;
    }

    public Auteur(String nom, LocalDate naissance, String mort) {
        this.nom = nom;
        this.naissance = naissance;
        this.mort = mort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNbrLivres() {
        return this.livres.size();
    }

    @JsonIgnore
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
