package fr.givemeacar.controller;

import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.VehiculeRepository;
import fr.givemeacar.services.VehiculeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.givemeacar.services.AgenceService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController // Controller qui permet de réaliser des requêtes Http CRUD -> Api Rest

@RequestMapping(path = "vehicule")
public class VehiculeController {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceService agenceService;
    private final VehiculeServiceImpl vehiculeService;

    /*
        GET all vehicules
    */
    @GetMapping
    public List<Vehicule> allVehicule() {
        return vehiculeRepository.findAll();
    }

    // Renvoie un item via son id
    @GetMapping(value="{id}")
    public ResponseEntity<Optional<Vehicule>> vehiculeById(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(vehiculeRepository.findById(id));
    }


    /*
        GET list vehicules par agence
    */
    @CrossOrigin
    @GetMapping(value = "/agences/{id}/vehicules/")

    public ResponseEntity<List<Vehicule>> getAllVehicule(@PathVariable(value="id") int id) {

        List<Vehicule> listVehicule;
        try {
            listVehicule = agenceService.getStockVehiculesServ(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listVehicule);
    }

    /*
        POST VEHICULE
    */
    @PostMapping
    public ResponseEntity<Void> createVehicule(@RequestBody Vehicule vehicule ) {
        Vehicule savedVehicule = vehiculeRepository.save(vehicule);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedVehicule.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /* PUT VEHICULE
        J'ai crée deux fonctions séparées pour gérer les booléens, si on les traite dans "updateVehicule"
        ils seront set sur false par défaut ce qu'on ne veut pas
    */
    @PutMapping(path = "{vehiculeId}")
    public void updateVehicule(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestBody Vehicule vehicule
    ) {
        vehiculeService.updateVehiculesServ(vehiculeId, vehicule);
    }

    @PutMapping(path = "{vehiculeId}/dispo")
    public void setDisponibility(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestParam boolean dispoCheck
    ) {
        vehiculeService.updateDispo(vehiculeId, dispoCheck);
    }

    @PutMapping(path = "{vehiculeId}/revision")
    public void setRevision(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestParam boolean revisionCheck
    ) {
        vehiculeService.updateRevision(vehiculeId, revisionCheck);
    }

    /*
        DELETE VEHICULE
    */

    @DeleteMapping(value="{id}")
    public void deleteVehicule(@PathVariable int id){
        vehiculeRepository.deleteById(id);
    }

}
