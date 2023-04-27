package com.MOHAMED.EL_HADDIOUI.Service;

import com.MOHAMED.EL_HADDIOUI.entities.Abonnement;
import com.MOHAMED.EL_HADDIOUI.entities.Client;
import com.MOHAMED.EL_HADDIOUI.repositories.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AbonnementServiceImpl implements AbonnementService {
    @Autowired
    AbonnementRepository abonnementRepository;
    @Override
    public Collection<Abonnement> getByClient(Client client) {
        return abonnementRepository.getByClient(client);
    }

    @Override
    public Abonnement save(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public Abonnement update(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public Optional<Abonnement> findAbonnement(Long id) {
        return  abonnementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        abonnementRepository.deleteById(id);
    }
}
