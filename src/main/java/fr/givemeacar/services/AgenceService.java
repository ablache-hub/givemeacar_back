package fr.givemeacar.services;

import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;

import java.util.List;

public interface AgenceService {


    // On créer une fonction qui va permettre de donner le stock par agence
    public List<Vehicule> getStockVehiculesServ(int id);

    public List<Utilisateur> getListClienteleServ(int id);

    public void addVehiculeToAgencyServ(int agenceId, int vehiculeId);

    public void addClientToAgencyServ(int agenceId, int clientele);


}
