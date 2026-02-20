package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Membre;

public record MembreSansPasswordDTO(String nom, String dateAbonnement, long id) {

    public static MembreSansPasswordDTO fromMembre(Membre m ){
        return new MembreSansPasswordDTO(m.getNom(), m.getDateAbonnement(), m.getId());
    }

}
