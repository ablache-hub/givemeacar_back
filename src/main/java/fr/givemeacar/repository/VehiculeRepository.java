package fr.givemeacar.repository;

import fr.givemeacar.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule,Integer>{

    //On ajoute une nouvelle fonction qui va nous retourner le stock d'une agence par son @Id
    public List<Vehicule> findByAgenceId(int agenceId);

    public List<Vehicule> findByDisponibilityLocation(boolean disponibility_location);

}
