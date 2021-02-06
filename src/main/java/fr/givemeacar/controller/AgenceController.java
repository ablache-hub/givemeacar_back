package fr.givemeacar.controller;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.services.AgenceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;


@RestController // Controller qui permet de réaliser des requêtes Http CRUD -> Api Rest
@AllArgsConstructor
@RequestMapping(path = "/agence")
public class AgenceController {

    private final AgenceRepository agenceRepository;
    private final AgenceServiceImpl agenceService;

    /*
        GET all agences
    */
    @CrossOrigin
    @GetMapping
    ResponseEntity<List<Agence>> allAgency() {
        return ResponseEntity.ok().body(
                agenceRepository.findAll()
        );
    }

    /*
        GET une agence par son Id
    */
    @CrossOrigin
    @GetMapping(value="{id}")
    public ResponseEntity<Optional<Agence>> agencyById(@PathVariable int id){
        return ResponseEntity.ok().body(
                agenceRepository.findById(id)
        );
    }

    /*
      GET liste vehicules d'une agence
    */
    @CrossOrigin
    @GetMapping(value = "{id}/vehicules/")
    public ResponseEntity<List<Vehicule>> getAllVehicule(
            @PathVariable(value="id") int id) {

        return ResponseEntity.ok().body(
                agenceService.getStockVehiculesServ(id)
        );
    }

    /*
        POST une agence
    */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Void> createAgency(
            @RequestBody Agence agence) {

        agenceRepository.save(agence);

        return ResponseEntity.created(
                URI.create("/agence/" + agence.getId())).build();
    }

    /*
        PUT - Mettre à jour un item déjà existant
    */
    @CrossOrigin
    @PutMapping
    public ResponseEntity<Void> updateAgency(
            @RequestBody Agence agence) {

        agenceRepository.save(agence);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
        Supprimer un item via son Id
    */
    @CrossOrigin
    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable int id){

        agenceRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
        POST - Ajouter vehicule à agence
    */
    @CrossOrigin
    @PostMapping(value="{agenceId}/vehicule/{vehiculeId}")
    public ResponseEntity<Void> addVehiculeToAgency(@PathVariable("agenceId") int agenceId,
                                    @PathVariable("vehiculeId") int vehiculeId) {

        agenceService.addVehiculeToAgencyServ(agenceId, vehiculeId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*
        POST - Ajouter client à agence
    */
    @CrossOrigin
    @PostMapping(value="{agenceId}/client/{clientId}")
    public ResponseEntity<Void> addClientToAgency(@PathVariable("agenceId") int agenceId,
                                  @PathVariable("clientId") int clientId) {

        agenceService.addClientToAgencyServ(agenceId, clientId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*
        DELETE client du stock d'une agence
    */
    @CrossOrigin
    @DeleteMapping(value="{agenceId}/client/{clientId}")
    public ResponseEntity<Void> deleteClientToAgency(@PathVariable("agenceId") int agenceId,
                                     @PathVariable("clientId") int clientId) {

        agenceService.deleteClientToAgencyServ(agenceId, clientId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
        DELETE vehicule du stock d'une agence
    */
    @CrossOrigin
    @DeleteMapping(value="{agenceId}/vehicule/{vehiculeId}")
    public ResponseEntity<Void> deleteVehiculeToAgency(@PathVariable("agenceId") int agenceId,
                                       @PathVariable("vehiculeId") int vehiculeId) {
        agenceService.deleteVehiculeToAgencyServ(agenceId, vehiculeId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
