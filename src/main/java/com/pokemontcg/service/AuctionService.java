package com.pokemontcg.service;

import com.pokemontcg.dto.AuctionDto;
import com.pokemontcg.entity.AuctionEntity;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.mapper.AuctionMapper;
import com.pokemontcg.repository.AuctionRepository;
import com.pokemontcg.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuctionService {
    private AuctionMapper auctionMapper;
    private AuctionRepository auctionRepository;
    private UserLoginService userLoginService;
    private UserRepository userRepository;

    public void postAuction(AuctionDto auctionDto){
        AuctionEntity auctionEntity = auctionMapper.toAuctionEntity(auctionDto);
        UserEntity user = userLoginService.getLoggedUser();
        auctionEntity.setUser(user);
        auctionRepository.save(auctionEntity);
        user.removeCard(auctionEntity.getCardToSell());
        userRepository.save(user);
    }
}
