package fr.givemeacar.services;

import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.UtilisateurRepository;
import fr.givemeacar.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceServiceImpl implements AgenceService {


    /* On importe notre JPA et on utilise la fonction sur @Service pour nous retourner la fonction du JPA*/
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    // GET stock vehicules
    @Override
    public List<Vehicule> getStockVehicules(int id) {
        return vehiculeRepository.findByAgenceId(id);
    }



    @Override
    public List<Utilisateur> getListClientele(int id) {

        return utilisateurRepository.findByAgenceId(id);

    }
}
