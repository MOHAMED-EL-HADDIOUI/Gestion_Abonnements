package com.MOHAMED.EL_HADDIOUI.repositories;


import com.MOHAMED.EL_HADDIOUI.entities.Abonnement;
import com.MOHAMED.EL_HADDIOUI.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AbonnementRepository extends JpaRepository<Abonnement,Long> {


    Collection<Abonnement> getByClient(Client client);
}
