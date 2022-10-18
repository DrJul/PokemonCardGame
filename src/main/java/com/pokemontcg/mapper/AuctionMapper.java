package com.pokemontcg.mapper;

import com.pokemontcg.dto.AuctionDto;
import com.pokemontcg.entity.AuctionEntity;
import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.exception.CardNotFoundException;
import com.pokemontcg.exception.UserNotFoundException;
import com.pokemontcg.repository.CardRepository;
import com.pokemontcg.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuctionMapper {
    private UserRepository userRepository;
    private CardRepository cardRepository;

    public AuctionDto toAuctionDto(AuctionEntity auctionEntity){
        return AuctionDto.builder()
                .id(auctionEntity.getId())
                .userId(auctionEntity.getUser().getId())
                .cardId(auctionEntity.getCardToSell().getId())
                .amount(auctionEntity.getAmount())
                .priceForOne(auctionEntity.getPriceForOne())
                .build();
    }

    public AuctionEntity toAuctionEntity(AuctionDto auctionDto){
        return AuctionEntity.builder()
                .id(auctionDto.getId())
                .user(auctionDto.getUserId() == 0? null: getUser(auctionDto.getUserId()))
                .cardToSell(getCard(auctionDto.getCardId()))
                .amount(auctionDto.getAmount())
                .priceForOne(auctionDto.getPriceForOne())
                .build();
    }

    private CardEntity getCard(String cardId) {
        return cardRepository.findById(cardId).orElseThrow(()-> new CardNotFoundException("card not found"));
    }

    private UserEntity getUser(long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
    }
}
