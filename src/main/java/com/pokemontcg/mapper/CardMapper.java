package com.pokemontcg.mapper;

import com.pokemontcg.dto.CardDto;
import com.pokemontcg.entity.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toCardDto(CardEntity cardEntity){
        return CardDto.builder()
                .id(cardEntity.getId())
                .name(cardEntity.getName())
                .smallImage(cardEntity.getSmallImage())
                .largeImage(cardEntity.getLargeImage())
                .build();
    }

    public CardEntity toCardEntity(CardDto cardDto){
        return CardEntity.builder()
                .id(cardDto.getId())
                .name(cardDto.getName())
                .smallImage(cardDto.getSmallImage())
                .largeImage(cardDto.getLargeImage())
                .build();
    }

}
