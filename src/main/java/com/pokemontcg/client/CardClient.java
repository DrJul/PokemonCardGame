package com.pokemontcg.client;

import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardClient {
    public static final String URL = "https://api.pokemontcg.io/v2/cards?page=";
    private CardRepository cardRepository;

    @PostConstruct
    public void downloadCards() {
//        Class klasa = int.class;
//        ApiResponse apiResponse = new ApiResponse();
//        Class klasa2 = apiResponse.getClass();
//        Class klasa3 = Class.forName("com.pokemontcg.client.ApiResponse");


        for (int i = 1; i < 61; i++) {
            String fullUrl = URL+i;
            Thread thread = new Thread(() -> {
                long amountOfCardInRepo = cardRepository.count();
                if (amountOfCardInRepo == 0) {
                    RestTemplate restTemplate = new RestTemplate();
                    ApiResponse apiResponse = restTemplate.getForObject(fullUrl, ApiResponse.class); // .class typ - refleksja
                    System.out.println(apiResponse);
                    List<CardEntity> cardEntityList = apiResponse.getData()
                            .stream()
                            .map((e) -> new CardEntity(e.getId(), e.getName(), e.getImages().getSmall(), e.getImages().getLarge()))
                            .collect(Collectors.toList());

                    cardRepository.saveAll(cardEntityList);
                }

            });
            thread.start();
        }
    }
}
