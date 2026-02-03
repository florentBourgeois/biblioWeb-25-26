package fr.serfa.biblioWeb.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class Membre {

    private String nom;
    private String dateAbonnement;
    private List<Livre> emprunts = new ArrayList<>();

}
