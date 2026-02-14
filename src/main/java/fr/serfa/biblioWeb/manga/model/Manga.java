package fr.serfa.biblioWeb.manga.model;


import jakarta.persistence.*;
import lombok.*;

@Data

@Getter
@Setter

@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String titre = "";
    private int nbrPage;
    private double evaluation;
    private int ageMini;

    public Manga() {
    }

    public Manga(Long id, String titre, int nbrPage, double evaluation, int ageMini) {
        this.id = id;
        this.titre = titre;
        this.nbrPage = nbrPage;
        this.evaluation = evaluation;
        this.ageMini = ageMini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbrPage() {
        return nbrPage;
    }

    public void setNbrPage(int nbrPage) {
        this.nbrPage = nbrPage;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public int getAgeMini() {
        return ageMini;
    }

    public void setAgeMini(int ageMini) {
        this.ageMini = ageMini;
    }
}
