package fr.serfa.biblioWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class Membre {

    private String nom;
    private String dateAbonnement;
    private List<Livre> emprunts = new ArrayList<>();

    public Membre(String nom, String dateAbonnement) {
        this.nom = nom;
        this.dateAbonnement = dateAbonnement;
    }

    @JsonIgnore
    public List<Livre> getEmprunts() {
        return emprunts;
    }
}
