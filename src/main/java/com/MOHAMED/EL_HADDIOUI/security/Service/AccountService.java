package com.MOHAMED.EL_HADDIOUI.security.Service;

import com.MOHAMED.EL_HADDIOUI.security.entities.AppRole;
import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;

public interface AccountService {
    AppUser addUser(AppUser appUser);
    AppUser updateUser(String username, String email, String password, String confirmPassword);
    AppRole addRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUser(String username);
}
