package fr.serfa.biblioWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String dateAbonnement = "";
    private String password = "choucroute,@perdue";

    @OneToMany
    private List<Livre> emprunts = new ArrayList<>();

    public Membre(String nom, String dateAbonnement) {
        this.nom = nom;
        this.dateAbonnement = dateAbonnement;
    }



    public void emprunter(Livre l){
        this.emprunts.add(l);
    }

    public void rendre(Livre l){
        this.emprunts.remove(l);
    }

    public void payerAbonnement(){
        this.dateAbonnement = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public boolean estAbonne(){
        LocalDate dateAbonnement = LocalDate.parse(this.dateAbonnement, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return dateAbonnement.plusYears(1).isAfter(LocalDate.now());
    }
    @JsonIgnore
    public List<Livre> getEmprunts() {
        return emprunts;
    }

    public boolean emprunteDeja(Livre l){
        for (Livre emprunt : emprunts){
            if (emprunt.getId() == l.getId())
                return true;
        }
        return false;
    }
}
