package com.MOHAMED.EL_HADDIOUI.entities;

import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom;
    private String email ;
    private String username;
    @OneToMany(mappedBy = "client")
    private List<Abonnement> abonnement = new ArrayList<>();
    @OneToOne
    private AppUser appUser;

}
