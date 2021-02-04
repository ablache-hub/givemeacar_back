package fr.givemeacar.repository;

import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    public List<Utilisateur> findByAgenceId(int agenceId);
}
