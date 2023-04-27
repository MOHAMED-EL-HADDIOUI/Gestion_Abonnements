package com.MOHAMED.EL_HADDIOUI.security.Service;

import com.MOHAMED.EL_HADDIOUI.security.entities.AppRole;
import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;
import com.MOHAMED.EL_HADDIOUI.security.repositories.AppRoleRepository;
import com.MOHAMED.EL_HADDIOUI.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addUser(AppUser appUser) {
            AppUser appUser1 = appUserRepository.findByUsername(appUser.getUsername());
            if(appUser1!=null) throw new RuntimeException("this user already exist");
            AppUser savedUser = appUserRepository.save(appUser);
            return savedUser;
    }

    @Override
    public AppUser updateUser(String username, String email, String password, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        appUser=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        AppUser savedUser = appUserRepository.save(appUser);
        return savedUser;
    }


    @Override
    public AppRole addRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if (appRole!=null) throw new RuntimeException("Role already exist");
        appRole=AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }
    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        List<AppRole> appRoleList=new ArrayList<>();appRoleList.add(appRole);
        appUser.setAppRoles(appRoleList);
        appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        appUser.getAppRoles().remove(appRole);
        appUserRepository.save(appUser);
    }
    @Override
    public AppUser loadUser(String username) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        return appUser;
    }
}
