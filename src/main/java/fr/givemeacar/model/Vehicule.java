package fr.givemeacar.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
//@Table(name="vehicule")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String marque;
    private String modele;
    private int price;
    private boolean isAvailable;
    private boolean inRevision;
    private int coordonneesGPS;

    @ManyToOne
    @JoinColumn // todo name
    @JsonBackReference
    private Agence agence;

}
