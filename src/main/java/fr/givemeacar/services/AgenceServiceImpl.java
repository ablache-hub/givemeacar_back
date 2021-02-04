package fr.givemeacar.services;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.repository.UtilisateurRepository;
import fr.givemeacar.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class AgenceServiceImpl implements AgenceService {


    /* On importe notre JPA et on utilise la fonction sur @Service pour nous retourner la fonction du JPA*/
    VehiculeRepository vehiculeRepository;
    UtilisateurRepository utilisateurRepository;
    AgenceRepository agenceRepository;

    // GET stock vehicules
    @Override
    public List<Vehicule> getStockVehiculesServ(int id) {
        return vehiculeRepository.findByAgenceId(id);
    }



    @Override
    public List<Utilisateur> getListClienteleServ(int id) {

        return utilisateurRepository.findByAgenceId(id);

    }

    @Override
    public void addVehiculeToAgencyServ(int agenceId, int vehiculeId){
        Agence currentAgence = agenceRepository.findById(agenceId)
                .orElseThrow(()-> new IllegalStateException("L'agence avec l'id " + agenceId + " n'existe pas"));

        Vehicule currentVehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(()-> new IllegalStateException("Le vehicule avec l'id " + vehiculeId + " n'existe pas"));

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
        }else{
            throw new IllegalStateException("Vehicule déjà existant");
        }
    }

    @Override
    public void addClientToAgencyServ(int agenceId, int clientId){
        Agence currentAgence = agenceRepository.findById(agenceId)
                .orElseThrow(()-> new IllegalStateException("L'agence avec l'id " + agenceId + " n'existe pas"));

        Utilisateur currentUtilisateur = utilisateurRepository.findById(clientId)
                .orElseThrow(()-> new IllegalStateException("Le client avec l'id " + clientId + " n'existe pas"));

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
        }else{
            throw new IllegalStateException("Client déjà existant");
        }
    }
}
