package fr.givemeacar.services;

import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;

import java.util.List;

public interface AgenceService {

    // On créer une fonction qui va permettre de donner le stock par agence
    public List<Vehicule> getStockVehicules(int id);

    public List<Utilisateur> getListClientele(int id);




}
