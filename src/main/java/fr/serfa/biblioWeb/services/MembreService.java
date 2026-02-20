package fr.serfa.biblioWeb.services;

import fr.serfa.biblioWeb.controller.MembreSansPasswordDTO;
import fr.serfa.biblioWeb.dao.MembreDAO;
import fr.serfa.biblioWeb.model.Membre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembreService {

    private MembreDAO membreDAO;

    public MembreService(MembreDAO membreDAO) {
        this.membreDAO = membreDAO;
    }

    public List<Membre> getAllMembres(){
        return membreDAO.findAll();
    }

    public Membre getParID(Long id){
        Optional<Membre> byId = this.membreDAO.findById(id);
        if(byId.isPresent())
            return byId.get();
        return null;
    }

    public List<MembreSansPasswordDTO> getAllMembreSansPassword(){
        return this.membreDAO.findAllSansPassword();
    }

}
