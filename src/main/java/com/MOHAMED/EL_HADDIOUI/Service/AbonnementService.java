package com.MOHAMED.EL_HADDIOUI.Service;

import com.MOHAMED.EL_HADDIOUI.entities.Abonnement;
import com.MOHAMED.EL_HADDIOUI.entities.Client;

import java.util.Collection;
import java.util.Optional;

public interface AbonnementService {
    Collection<Abonnement> getByClient(Client client);
    Abonnement save(Abonnement abonnement);
    Abonnement update(Abonnement abonnement);
    Optional<Abonnement> findAbonnement(Long id);
    void delete(Long id);
}
