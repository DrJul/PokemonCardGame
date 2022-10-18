package com.pokemontcg.service;

import com.pokemontcg.dto.CardDto;
import com.pokemontcg.dto.UserLoginDto;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.exception.LoginServiceException;
import com.pokemontcg.mapper.CardMapper;
import com.pokemontcg.mapper.UserMapper;
import com.pokemontcg.repository.UserRepository;
import com.pokemontcg.session.Session;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserLoginService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private CardMapper cardMapper;
    @Resource(name = "getSession")
    private Session session;

    public void checkUserData(UserLoginDto userLoginDto) {
        System.out.println(userLoginDto);
        UserEntity userEntity = userRepository.findByEmail(userLoginDto.getLogin())
                .orElseThrow(()-> new LoginServiceException("no such entity"));
        if (!userLoginDto.getPassword().equals(userEntity.getPassword())) {
            throw new LoginServiceException("Password incorrect");
        }
//        session.setAttribute("user", userEntity);
        session.login(userEntity);
//        userEntity.login();
        System.out.println(userEntity);
        userRepository.save(userEntity);
    }

    public UserEntity getLoggedUser(){
        if(isLogged()){
            return session.getUser();
        }
        throw new LoginServiceException("user is not logged");
    }

    public boolean isLogged(){
        return session.isLogged();
    }

    public List<CardDto> getUserCards(){
        return session.getUser().getCards().stream()
                .map((card)-> cardMapper.toCardDto(card))
                .collect(Collectors.toList());
    }

}
