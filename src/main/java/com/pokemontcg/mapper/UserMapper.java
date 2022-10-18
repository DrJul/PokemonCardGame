package com.pokemontcg.mapper;

import com.pokemontcg.dto.RegisterRequestDto;
import com.pokemontcg.dto.UserLoginDto;
import com.pokemontcg.entity.TrenerEntity;
import com.pokemontcg.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToUserEntity(RegisterRequestDto userRequestDto){
        return UserEntity.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .trener(new TrenerEntity())
                .build();
    }

    public UserLoginDto mapToUserLoginDto(UserEntity userEntity){
        return UserLoginDto.builder()
                .login(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
