package com.MOHAMED.EL_HADDIOUI;

import com.MOHAMED.EL_HADDIOUI.entities.Abonnement;
import com.MOHAMED.EL_HADDIOUI.entities.Client;
import com.MOHAMED.EL_HADDIOUI.entities.TypeAbonnement;
import com.MOHAMED.EL_HADDIOUI.repositories.AbonnementRepository;
import com.MOHAMED.EL_HADDIOUI.repositories.ClientRepository;
import com.MOHAMED.EL_HADDIOUI.security.Service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


