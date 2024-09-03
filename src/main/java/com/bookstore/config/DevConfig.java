package com.bookstore.config;

import com.bookstore.service.DBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public DevConfig(DBService dbService){
        this.dbService = dbService;
    }

    @Bean
    public boolean instanciaBaseDeDados() {
        if ("create".equals(strategy)) {
            System.out.println("Criando a base de dados...");
            this.dbService.instanciaBaseDeDados();
            return true;
        }
        System.out.println("A estratégia não é 'create'. Base de dados não criada.");
        return false;
    }



}
