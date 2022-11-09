package com.pokemontcg.service;

import com.pokemontcg.dto.CardDto;
import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.entity.TrenerEntity;
import com.pokemontcg.repository.TrenerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrenerService {
    private UserLoginService userLoginService;
    private TrenerRepository trenerRepository;

    public List<CardDto> getTrenerCardDtos(){
        TrenerEntity trener = userLoginService.getLoggedTrener();
        Map<CardEntity, Integer> trenerCardEntities = trener.getCards();
        return trenerCardEntities.keySet().stream()
                .map((key) -> CardDto.builder()
                        .id(key.getId())
                        .name(key.getName())
                        .largeImage(key.getLargeImage())
                        .smallImage(key.getSmallImage())
                        .amount(trenerCardEntities.get(key))
                        .build()
                ).collect(Collectors.toList());
    }

    public int getTrenerCoins(){
        return userLoginService.getLoggedTrener().getCoins();
    }

    public void addCoinsToUser(int coinsToAdd){
        TrenerEntity trener = userLoginService.getLoggedTrener();
        trener.addCoins(coinsToAdd);
        trenerRepository.save(trener);
    }
}
