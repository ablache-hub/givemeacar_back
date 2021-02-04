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

public class VehiculeController {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceService agenceService;
    private final VehiculeServiceImpl vehiculeService;

    // Renvoie tous nos produits
    @GetMapping(value="/Vehicule")
    List<Vehicule> allVehicule() {
        return vehiculeRepository.findAll();
    }

    // Renvoie un item via son id
    @GetMapping(value="/Vehicule/{id}")
    public Optional<Vehicule> vehiculeById(@PathVariable int id){
        return vehiculeRepository.findById(id);
    }

    // Créer un item
    @PostMapping(value="/Vehicule")
    public ResponseEntity<Void> createVehicule(@RequestBody Vehicule vehicule ) {
        Vehicule savedVehicule = vehiculeRepository.save(vehicule);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedVehicule.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // Mettre à jour un item déjà existant
//    @PutMapping(value="/Vehicule")
//    public void updateVehicule(@RequestBody Vehicule vehicule ) {
//        vehiculeRepository.save(vehicule);
//    }

    // Supprimer un item via son Id
    @DeleteMapping(value="/Vehicule/{id}")
    public void deleteVehicule(@PathVariable int id){
        vehiculeRepository.deleteById(id);
    }


    /* GET ALL VEHICULES */
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

    /* POST VEHICULE */

    /* PUT VEHICULE */

    @PutMapping(path = "/vehicule/{vehiculeId}")
    public void updateVehicule(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestBody Vehicule vehicule)
//            @RequestParam(required = false) String marque,
//            @RequestParam(required = false) String modele,
//            @RequestParam(required = false) int price,
//            @RequestParam(required = false) boolean disponibilityLocation,
//            @RequestParam(required = false) boolean inRevision,
//            @RequestParam(required = false) int coordonneesGPS

    {
//        vehiculeService.updateVehiculesServ(vehiculeId, marque, modele, price,disponibilityLocation, inRevision,coordonneesGPS);
        vehiculeService.updateVehiculesServ(vehiculeId, vehicule);
    }


    /* DELETE VEHICULE */



}
