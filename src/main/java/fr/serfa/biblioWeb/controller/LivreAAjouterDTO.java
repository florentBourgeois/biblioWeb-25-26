package fr.serfa.biblioWeb.controller;

import lombok.Data;


public class LivreAAjouterDTO {

    private String isbn;
    private String titre;
    private int anneePublication;

    private long auteurID;

    public LivreAAjouterDTO() {
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

    public long getAuteurID() {
        return auteurID;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setAuteurID(long auteurID) {
        this.auteurID = auteurID;
    }
}
