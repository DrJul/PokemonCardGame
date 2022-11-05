package com.pokemontcg.service;

import com.pokemontcg.dto.AuctionDto;
import com.pokemontcg.dto.CardDto;
import com.pokemontcg.entity.AuctionEntity;
import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.entity.TrenerEntity;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.exception.AuctionNotFoundException;
import com.pokemontcg.exception.CardNotFoundException;
import com.pokemontcg.mapper.AuctionMapper;
import com.pokemontcg.repository.AuctionRepository;
import com.pokemontcg.repository.CardRepository;
import com.pokemontcg.repository.TrenerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuctionService {
    private AuctionMapper auctionMapper;
    private AuctionRepository auctionRepository;
    private UserLoginService userLoginService;
    private TrenerRepository trenerRepository;
    private TrenerService trenerService;
    private CardRepository cardRepository;

    public void postAuction(AuctionDto auctionDto){
        AuctionEntity auctionEntity = auctionMapper.toAuctionEntity(auctionDto);
        UserEntity user = userLoginService.getLoggedUser();
        TrenerEntity trener = user.getTrener();
        auctionEntity.setUser(user);
        auctionRepository.save(auctionEntity);
        trener.removeCard(auctionEntity.getCardToSell(), auctionEntity.getAmount());
        trenerRepository.save(trener);
    }

    public List<CardDto> sellAuctionData(){
        return trenerService.getTrenerCardDtos();
    }

    private String getCardName(String cardId) {
        return cardRepository.findById(cardId).orElseThrow( ()-> new CardNotFoundException("card not found")).getName();
    }

    private String getCardImage(String cardId){
        return cardRepository.findById(cardId).orElseThrow( ()-> new CardNotFoundException("card not found")).getSmallImage();
    }

    public List<AuctionDto> getAuctions(){
        List<AuctionEntity> auctionEntities = auctionRepository.findAll();
        return auctionMapper.toAuctionDtos(auctionEntities);
    }

    public void buy(long auctionId) {
        TrenerEntity trener = userLoginService.getLoggedTrener();
        AuctionEntity auction = auctionRepository.findById(auctionId).orElseThrow(()-> new AuctionNotFoundException("Auction not found"));
        CardEntity buyedCard = auction.getCardToSell();
        int amount = auction.getAmount();
        trener.addCard(buyedCard, amount);
        trenerRepository.save(trener);
        auctionRepository.deleteById(auctionId);
    }
}
