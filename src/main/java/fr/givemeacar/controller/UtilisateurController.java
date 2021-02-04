package fr.givemeacar.controller;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.repository.UtilisateurRepository;
import fr.givemeacar.services.AgenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UtilisateurController {

    private UtilisateurRepository utilisateurRepository;
    AgenceService agenceService;

    // Renvoie tous nos produits
    @CrossOrigin
    @GetMapping(value = "/Utilisateur")
    List<Utilisateur> allUtilisateurs() {

        return utilisateurRepository.findAll();
    }

    // Renvoie un item via son id
    @CrossOrigin
    @GetMapping(value = "/Utilisateur/{id}")
    public Optional<Utilisateur> utilisateurById(@PathVariable int id) {
        return utilisateurRepository.findById(id);

    }

    // Mettre à jour un item déjà existant
    @CrossOrigin
    @PutMapping(value = "/Utilisateur")
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur) {


        utilisateurRepository.save(utilisateur);
    }

    // Supprimer un item via son Id
    @CrossOrigin
    @DeleteMapping(value = "/Utilisateur/{id}")
    public void deleteUtilisateur(@PathVariable int id) {


        utilisateurRepository.deleteById(id);
    }

    @PostMapping(value = "/Utilisateur")
    @CrossOrigin
    public void postUtilisateur(@RequestBody Utilisateur utilisateur) {


        utilisateurRepository.save(utilisateur);

    }

    /* GET all Utilisateurs */
    @CrossOrigin
    @GetMapping(value = "/Agence/{id}/Utilisateur")

    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(@PathVariable(value="id") int id) {

        List<Utilisateur> listUtilisateur;
        try {
            listUtilisateur = agenceService.getListClienteleServ(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listUtilisateur);
    }

    /*POST Utilisateur*/

}
