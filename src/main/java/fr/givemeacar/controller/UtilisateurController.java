package fr.givemeacar.controller;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.repository.UtilisateurRepository;
import fr.givemeacar.services.UtilisateurService;
import fr.givemeacar.services.UtilisateurServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "utilisateur")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurServiceImpl utilisateurService;

    // Renvoie tous nos produits
    @CrossOrigin
    @GetMapping
    List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Renvoie un item via son id
    @CrossOrigin
    @GetMapping(value = "{id}")
    public Optional<Utilisateur> utilisateurById(@PathVariable int id) {
        return utilisateurRepository.findById(id);
    }

    // Mettre à jour un item déjà existant
    @CrossOrigin
    @PutMapping
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    // Changer l'Utilisateur d'Agence
    @CrossOrigin
    @PutMapping(path = "/{utilisateurId}/agence/{newAgenceId}")
    public ResponseEntity<Void> moveUtilisateur(@PathVariable("utilisateurId") int utilisateurId, @PathVariable("newAgenceId") int newAgenceId){
        utilisateurService.moveClientServ(utilisateurId, newAgenceId);
        // on return rien vu que c'est <Void> donc on met null
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Supprimer un item via son Id
    @CrossOrigin
    @DeleteMapping(value = "{id}")
    public void deleteUtilisateur(@PathVariable int id) {
        utilisateurRepository.deleteById(id);
    }

    /*POST Utilisateur*/
    @PostMapping
    @CrossOrigin
    public void postUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }
}
