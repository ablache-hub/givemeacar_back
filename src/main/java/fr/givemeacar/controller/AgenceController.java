package fr.givemeacar.controller;

import fr.givemeacar.model.Agence;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.repository.VehiculeRepository;
import fr.givemeacar.services.AgenceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController // Controller qui permet de réaliser des requêtes Http CRUD -> Api Rest
@AllArgsConstructor
@RequestMapping(path = "/agence")
public class AgenceController {
    
    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AgenceServiceImpl agenceService;

    // Renvoie tous nos produits
    @CrossOrigin
    @GetMapping
     List<Agence> allAgency() {
        return agenceRepository.findAll();
    }
    
    // Renvoie un item via son id
    @CrossOrigin
    @GetMapping(value="{id}")
    public Optional<Agence> agencyById(@PathVariable int id){
        return agenceRepository.findById(id);
    }
    
    // Créer un item
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Void> createAgency(@RequestBody Agence agence ) {
        Agence savedAgence = agenceRepository.save(agence); // on stock dans la var 'savedAgence' l'agence créée, de type Agence

        // On appelle la fonction getId de notre var savedAgence (qui fonctionne vu que c'est de type Agence donc elle a getId())
        // On injecte ensuite cette agence et son id dans notre URI (suite de l'URL)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAgence.getId()).toUri();

        // On return la création/build de notre URI
        return ResponseEntity.created(location).build();
    }

    // Mettre à jour un item déjà existant
    @CrossOrigin
    @PutMapping
    public void updateAgency(@RequestBody Agence agence ) {
         agenceRepository.save(agence);
    }

    // Supprimer un item via son Id
    @CrossOrigin
    @DeleteMapping(value="{id}")
    public void deleteAgency(@PathVariable int id){
        agenceRepository.deleteById(id);
    }

    //Ajouter vehicule à agence
    @CrossOrigin
    @PostMapping(value="{agenceId}/vehicule/{vehiculeId}")
    public void addVehiculeToAgency(@PathVariable("agenceId") int agenceId,
                                    @PathVariable("vehiculeId") int vehiculeId) {
            agenceService.addVehiculeToAgencyServ(agenceId, vehiculeId);
    }

    //Ajouter vehicule à agence
    @CrossOrigin
    @PostMapping(value="{agenceId}/client/{clientId}")
    public void addClientToAgency(@PathVariable("agenceId") int agenceId,
                                    @PathVariable("clientId") int clientId) {
        agenceService.addClientToAgencyServ(agenceId, clientId);
    }
}
