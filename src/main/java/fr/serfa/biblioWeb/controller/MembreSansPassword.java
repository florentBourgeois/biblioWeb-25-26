package fr.serfa.biblioWeb.controller;

import fr.serfa.biblioWeb.model.Membre;

public record MembreSansPassword(String nom, String dateAbonnement, long id) {

    public static MembreSansPassword fromMembre(Membre m ){
        return new MembreSansPassword(m.getNom(), m.getDateAbonnement(), m.getId());
    }

}
