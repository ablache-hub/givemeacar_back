package fr.givemeacar.services;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements VehiculeService{

    private final VehiculeRepository vehiculeRepository;
    private final AgenceServiceImpl agenceService;

    @Override
//    @Transactional
    public void updateVehiculesServ(int vehiculeId,
                                    Vehicule vehicule) {

        Vehicule currentVehicule = getVehicule(vehiculeId);


        if (vehicule.getMarque() != null
                && vehicule.getMarque().length() > 0) {
            currentVehicule.setMarque(vehicule.getMarque());
        }

        if (vehicule.getModele() != null
                && vehicule.getModele().length() > 0) {
            currentVehicule.setModele(vehicule.getModele());
        }

        if (vehicule.getPrice() > 0) {
            currentVehicule.setPrice(vehicule.getPrice());
        }

        if (vehicule.getCoordonneesGPS() != 0) {
            currentVehicule.setCoordonneesGPS(vehicule.getCoordonneesGPS());
        }

        vehiculeRepository.save(currentVehicule);

    }

    public void updateDispoServ(int vehiculeId,
                                boolean isAvailable) {

        Vehicule currentVehicule = getVehicule(vehiculeId);
        currentVehicule.setAvailable(isAvailable);
        vehiculeRepository.save(currentVehicule);

    }

    public void updateRevisionServ(int vehiculeId,
                                   boolean inRevision) {

        Vehicule currentVehicule = getVehicule(vehiculeId);
        currentVehicule.setInRevision(inRevision);
        vehiculeRepository.save(currentVehicule);

    }

    public Vehicule getVehicule(int vehiculeId) {
        return vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new IllegalStateException("Le vehicule " + vehiculeId + " n'existe pas"));
    }

    public void moveVehiculeServ(int vehiculeId , int newAgenceId){

        Agence currentAgence = agenceService.checkAgence(newAgenceId);
        Vehicule currentVehicule = agenceService.checkVehicule(vehiculeId);

        // Verifier dans quelle agence est le vehicule: si son agence Id = 0 -> not_found
       // Si il a une agence, on vire l'agence (agenceId dans sql) et on le remplace par newAgenceId , comme ça il a une nouvelle Agence

            currentVehicule.setAgence(currentAgence);
            vehiculeRepository.save(currentVehicule);

    }

}
