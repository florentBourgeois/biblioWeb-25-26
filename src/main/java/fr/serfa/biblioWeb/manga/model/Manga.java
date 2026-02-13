package fr.serfa.biblioWeb.manga.model;


import jakarta.persistence.*;
import lombok.*;

@Data @AllArgsConstructor

@NoArgsConstructor
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

}
