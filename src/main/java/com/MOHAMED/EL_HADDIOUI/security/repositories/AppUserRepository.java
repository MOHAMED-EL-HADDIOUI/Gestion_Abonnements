package com.MOHAMED.EL_HADDIOUI.security.repositories;

import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
