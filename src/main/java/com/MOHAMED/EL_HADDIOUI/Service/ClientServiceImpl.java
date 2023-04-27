package com.MOHAMED.EL_HADDIOUI.Service;
import com.MOHAMED.EL_HADDIOUI.entities.Client;
import com.MOHAMED.EL_HADDIOUI.repositories.ClientRepository;
import com.MOHAMED.EL_HADDIOUI.security.Service.AccountService;
import com.MOHAMED.EL_HADDIOUI.security.entities.AppUser;
import com.MOHAMED.EL_HADDIOUI.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
        @Autowired
        ClientRepository clientRepository;
        @Autowired
        AccountService accountService;
        @Autowired
        AppUserRepository appUserRepository;
        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        public Client save(Client client) {
            if(client.getId()==null)
            {
                System.out.println("add user");
                AppUser appUser = AppUser.builder()
                        .userId(UUID.randomUUID().toString())
                        .username(client.getUsername())
                        .email(client.getEmail())
                        .password(passwordEncoder.encode(client.getEmail()))
                        .build();
                Client client1 = clientRepository.save(client);
                appUser.setClient(client1);
                AppUser appUser1 = accountService.addUser(appUser);
                client1.setAppUser(appUser1);
                accountService.addRoleToUser(client.getUsername(),"USER");
                return clientRepository.save(client1);
            }
            else {
                System.out.println("update user");
                AppUser appUser = appUserRepository.findByUsername(client.getAppUser().getUsername());
                appUser.setEmail(client.getEmail());
                appUser.setUsername(client.getUsername());
                appUser.setPassword(passwordEncoder.encode(client.getEmail()));
                System.out.println("Id : "+client.getId());
                AppUser appUser1 = appUserRepository.save(appUser);
                return clientRepository.save(client);
            }
        }
        @Override
        public Client update(Client client) {
            AppUser appUser = accountService.updateUser(client.getUsername(),client.getEmail(),client.getEmail(),client.getEmail());
            appUser.setClient(client);
            return clientRepository.save(client);
        }
        @Override
        public Optional<Client> findClient(Long id) {
          return clientRepository.findById(id);
        }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
        public Page<Client> findByNomContains(String keyword, Pageable pageable) {
            return clientRepository.findByNomContains(keyword,pageable);
        }

    @Override
    public Client findClientByUsername(String username) {

            return clientRepository.findByUsername(username);
    }



}
