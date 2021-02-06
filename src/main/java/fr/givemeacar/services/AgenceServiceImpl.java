package fr.givemeacar.services;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.repository.UtilisateurRepository;
import fr.givemeacar.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class AgenceServiceImpl implements AgenceService {

    /* On importe notre JPA et on utilise la fonction sur @Service pour nous retourner la fonction du JPA*/
    VehiculeRepository vehiculeRepository;
    UtilisateurRepository utilisateurRepository;
    AgenceRepository agenceRepository;

    // GET stock vehicules d'une agence
    @Override
    public List<Vehicule> getStockVehiculesServ(int id) {
        return vehiculeRepository.findByAgenceId(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Agence inexistante")
                );
    }

    // GET liste clients d'une agence
    @Override
    public List<Utilisateur> getListClienteleServ(int id) {

        return utilisateurRepository.findByAgenceId(id);

    }

    //PUT vehicule dans agence
    @Override
    public void addVehiculeToAgencyServ(int agenceId, int vehiculeId){

        Agence currentAgence = scanAgence(agenceId);

        Vehicule currentVehicule = scanVehicule(vehiculeId);

        List<Vehicule> stockVehicules = currentAgence.getStockVehicules();

        boolean exist = false;

        for (Vehicule item:stockVehicules){
            if(item == currentVehicule){
                exist = true;
                break;
            }
        }

        if(!exist){
            stockVehicules.add(currentVehicule);
            currentAgence.setStockVehicules(stockVehicules);
            currentVehicule.setAgence(currentAgence);
            agenceRepository.save(currentAgence);
            vehiculeRepository.save(currentVehicule);
            System.out.println("Vehicule ajouté");
        } else {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Vehicule déjà present");
        }
    }

    //DELETE vehicule d'agence
    @Override
    public void deleteVehiculeToAgencyServ(int agenceId, int vehiculeId) {

        Agence currentAgence = scanAgence(agenceId);

        Vehicule currentVehicule = scanVehicule(vehiculeId);

        List<Vehicule> stockVehicules = currentAgence.getStockVehicules();

        boolean exist = false;

        for (Vehicule item: stockVehicules){
            if(item .equals(currentVehicule)){
                exist = true;
                break;
            }
        }

        if(exist){
            stockVehicules.remove(currentVehicule);
            currentAgence.setStockVehicules(stockVehicules);
            currentVehicule.setAgence(null);
            agenceRepository.save(currentAgence);
            vehiculeRepository.save(currentVehicule);
            System.out.println("Vehicule supprimé");
        }

        else {
            throw new IllegalStateException("Vehicule " +vehiculeId+ " non trouvé");
        }
    }

    //PUT client dans agence
    @Override
    public void addClientToAgencyServ(int agenceId, int clientId){

        Agence currentAgence = scanAgence(agenceId);

        Utilisateur currentUtilisateur = scanClient(clientId);

        List<Utilisateur> listClient = currentAgence.getClientele();

        boolean exist = false;

        for (Utilisateur item: listClient){
            if(item == currentUtilisateur){
                exist = true;
                break;
            }
        }

        if(!exist){
            listClient.add(currentUtilisateur);
            currentAgence.setClientele(listClient);
            currentUtilisateur.setAgence(currentAgence);
            agenceRepository.save(currentAgence);
            utilisateurRepository.save(currentUtilisateur);
            System.out.println("Client ajouté");
        }

        else {
            throw new IllegalStateException("Client " +clientId+ " déjà existant");
        }
    }

    //DELETE client d'agence
    @Override
    public void deleteClientToAgencyServ(int agenceId, int clientId) {

        Agence currentAgence = scanAgence(agenceId);

        Utilisateur currentUtilisateur = scanClient(clientId);

        List<Utilisateur> listClient = currentAgence.getClientele();

        boolean exist = false;

        for (Utilisateur item: listClient){
            if(item == currentUtilisateur){
                exist = true;
                break;
            }
        }

        if(exist){
            listClient.remove(currentUtilisateur);
            currentAgence.setClientele(listClient);
            currentUtilisateur.setAgence(null);
            agenceRepository.save(currentAgence);
            utilisateurRepository.save(currentUtilisateur);
            System.out.println("Client supprimé");
        }

        else {
            throw new IllegalStateException("Client " +clientId+ " non trouvé");
        }
    }

    Agence scanAgence(int agenceId) {
        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(()-> new IllegalStateException("L'agence d'id " + agenceId + " n'existe pas"));
        return agence;
    }

    Vehicule scanVehicule(int vehiculeId) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(()-> new IllegalStateException("L'agence d'id " + vehiculeId + " n'existe pas"));
        return vehicule;
    }

    Utilisateur scanClient(int clientId) {
        Utilisateur currentUtilisateur = utilisateurRepository.findById(clientId)
                .orElseThrow(()-> new IllegalStateException("L'agence d'id " + clientId + " n'existe pas"));
        return currentUtilisateur;
    }

}
