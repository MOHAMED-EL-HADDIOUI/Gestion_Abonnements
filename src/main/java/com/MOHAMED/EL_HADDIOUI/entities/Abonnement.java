package com.MOHAMED.EL_HADDIOUI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Abonnement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Temporal(TemporalType.DATE)
    private Date dateAbonnement;
    private TypeAbonnement typeAbonnement ;
    private float solde;
    private float montantMensuel;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
}

