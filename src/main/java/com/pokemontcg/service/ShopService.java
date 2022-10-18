package com.pokemontcg.service;

import com.pokemontcg.dto.CardDto;
import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.mapper.CardMapper;
import com.pokemontcg.repository.CardRepository;
import com.pokemontcg.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopService {
    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private UserLoginService userLoginService;
    private UserRepository userRepository;

    public List<CardDto> buyCars(){
        Random random = new Random();
        List<CardEntity> cards = cardRepository.findAll();
        List<CardEntity> shuffledCards = new ArrayList<>();
        int j = cards.size();
        for (int i = 0; i < 1; i++){
            int index = random.nextInt(j);
            CardEntity card = cards.get(index);
            shuffledCards.add(card);
        }
        UserEntity loggedUser = userLoginService.getLoggedUser();
        List<CardEntity> currentCards = loggedUser.getCards();
        currentCards.addAll(shuffledCards);
        userRepository.save(loggedUser);
        return shuffledCards.stream().map((entity)-> cardMapper.toCardDto(entity)).collect(Collectors.toList());
    }
}
