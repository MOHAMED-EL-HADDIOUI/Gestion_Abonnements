package com.MOHAMED.EL_HADDIOUI.Service;

import com.MOHAMED.EL_HADDIOUI.entities.Client;
import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {
    Client save(Client client);
    Client update(Client client);
    Optional<Client> findClient(Long id);
    void delete(Long id);
    Page<Client> findByNomContains(String keyword , Pageable pageable);
    Client findClientByUsername(String username);
}
