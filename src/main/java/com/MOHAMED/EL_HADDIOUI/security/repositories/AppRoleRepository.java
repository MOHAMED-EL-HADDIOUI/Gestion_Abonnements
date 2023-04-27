package com.MOHAMED.EL_HADDIOUI.security.repositories;

import com.MOHAMED.EL_HADDIOUI.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
}
