package fr.givemeacar.services;

import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements VehiculeService{

    private final VehiculeRepository vehiculeRepository;

    @Override
//    @Transactional
    public void updateVehiculesServ(int vehiculeId,
                                    Vehicule vehicule
//                                    String marque,
//                                    String modele,
//                                    int price,
//                                    boolean disponibilityLocation,
//                                    boolean inRevision,
//                                    int coordonneesGPS
    ) {


        Vehicule currentVehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new IllegalStateException("Le vehicule " + vehiculeId + " n'existe pas"));

        String modele = vehicule.getModele();

//        if(vehicule.getModele() != null &&
//                name.length()>0 &&
//                !student.getName().equals(name)) {
//            student.setName(name);
//        }
//
//        if(email != null &&
//                email.length()>0 &&
//                !student.getEmail().equals(email)) {
//            Optional<Student> studentEmail = studentRepository.findStudentByEmail(email);
//            if(studentEmail.isPresent() ) {
//                throw new IllegalStateException("Cet email est déjà utilisé");
//            } else {
//                student.setEmail(email);
//            }
//
//        }
//    }

    }
}
