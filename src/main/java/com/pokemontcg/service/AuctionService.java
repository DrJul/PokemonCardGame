package com.pokemontcg.service;

import com.pokemontcg.dto.AuctionDto;
import com.pokemontcg.dto.CardDto;
import com.pokemontcg.entity.AuctionEntity;
import com.pokemontcg.entity.TrenerEntity;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.mapper.AuctionMapper;
import com.pokemontcg.repository.AuctionRepository;
import com.pokemontcg.repository.TrenerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuctionService {
    private AuctionMapper auctionMapper;
    private AuctionRepository auctionRepository;
    private UserLoginService userLoginService;
    private TrenerRepository trenerRepository;
    private TrenerService trenerService;

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


    public List<AuctionDto> getAuctions(){
        List<AuctionEntity> auctionEntities = auctionRepository.findAll();
        return null;
    }
}
