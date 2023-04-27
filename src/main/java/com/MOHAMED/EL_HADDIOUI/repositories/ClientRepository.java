package com.MOHAMED.EL_HADDIOUI.repositories;


import com.MOHAMED.EL_HADDIOUI.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {


    Page<Client> findByNomContains(String keyword , Pageable pageable);
    Client findByUsername(String username);
}
