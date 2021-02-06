package fr.givemeacar.services;

import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements VehiculeService{

    private final VehiculeRepository vehiculeRepository;

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

    public void updateDispo(int vehiculeId,
                            boolean isAvailable) {

        Vehicule currentVehicule = getVehicule(vehiculeId);
        currentVehicule.setAvailable(isAvailable);
        vehiculeRepository.save(currentVehicule);

    }

    public void updateRevision(int vehiculeId,
                            boolean inRevision) {

        Vehicule currentVehicule = getVehicule(vehiculeId);
        currentVehicule.setInRevision(inRevision);
        vehiculeRepository.save(currentVehicule);

    }

    public Vehicule getVehicule(int vehiculeId) {
        return vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new IllegalStateException("Le vehicule " + vehiculeId + " n'existe pas"));
    }

}
