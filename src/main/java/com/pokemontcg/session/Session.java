package com.pokemontcg.session;

import com.pokemontcg.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@Getter
public class Session {
    private UserEntity user;
    private LocalDateTime lastLoginTime;


    public void login(UserEntity user){
        lastLoginTime = LocalDateTime.now();
        this.user = user;
    }

    public boolean isLogged(){
        return lastLoginTime.plusSeconds(360).isAfter(LocalDateTime.now());
    }


}

