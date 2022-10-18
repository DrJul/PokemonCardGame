package com.pokemontcg.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SessionConfig {

    @Bean
    @SessionScope
    public Session getSession(){
        System.out.println("wywolanie sessionConfig");
        return new Session();
    }
}
