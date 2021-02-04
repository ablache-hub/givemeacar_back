package fr.givemeacar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Utilisateur extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String adresse;

    @ManyToOne @JoinColumn
    @JsonBackReference
    private Agence agence;

    //ArrayList<Vehicule> historique = new ArrayList<Vehicule>();

}
