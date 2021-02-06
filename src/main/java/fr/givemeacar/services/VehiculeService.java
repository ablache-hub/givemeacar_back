package fr.givemeacar.services;

import fr.givemeacar.model.Vehicule;

public interface VehiculeService {


    void updateVehiculesServ(int vehiculeId,
                             Vehicule vehicule
    );

    void updateDispo(int vehiculeId,
                     boolean isAvailable);

    void updateRevision(int vehiculeId,
                        boolean inRevision);

}
