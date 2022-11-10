package com.pokemontcg.client;

import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.entity.CardPageEntity;
import com.pokemontcg.exception.DownloadException;
import com.pokemontcg.repository.CardPageRepository;
import com.pokemontcg.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardClient {
    public static final String URL = "https://api.pokemontcg.io/v2/cards?page=";
    private CardRepository cardRepository;
    private CardPageRepository cardPageRepository;

    @PostConstruct
    public void downloadAllCards() {
//        Class klasa = int.class;
//        ApiResponse apiResponse = new ApiResponse();
//        Class klasa2 = apiResponse.getClass();
//        Class klasa3 = Class.forName("com.pokemontcg.client.ApiResponse");
        for (int i = 1; i < 61; i++) {
            downloadOnePage(i, 0);

        }
    }

    private void downloadOnePage(int i, int tryCount) {
        String fullUrl = URL + i;
        Thread thread = new Thread(() -> {
            Optional<CardPageEntity> cardPage = cardPageRepository.findById(i);
            if (cardPage.isPresent() && cardPage.get().isDownloaded()) {
                return;
            }

            RestTemplate restTemplate = new RestTemplate();
            try {
//                if (Math.random() > 0.5) {
//                    throw new DownloadException("błąd pobierania: " + fullUrl);
//                }
                        ApiResponse apiResponse = restTemplate.getForObject(fullUrl, ApiResponse.class); // .class typ - refleksja
                        System.out.println(apiResponse);
                        List<CardEntity> cardEntityList = apiResponse.getData()
                                .stream()
                                .map((e) -> new CardEntity(e.getId(), e.getName(), e.getImages().getSmall(), e.getImages().getLarge()))
                                .collect(Collectors.toList());
                        cardRepository.saveAll(cardEntityList);
                CardPageEntity newCardPage = new CardPageEntity(i, true);
                cardPageRepository.save(newCardPage);

            } catch (HttpServerErrorException | DownloadException e) {
                System.out.println(e);
                repair(i, tryCount);

            }

        });
        thread.start();
    }

    public void repair(int pageNr, int tryCount) {
        System.out.println( "not downloaded page number: " + pageNr);
        tryCount++;
        if (tryCount > 5) {
            CardPageEntity newCardPage = new CardPageEntity(pageNr, false);
            cardPageRepository.save(newCardPage);
            return;
        }
        try {
            Thread.sleep(1000);
            downloadOnePage(pageNr, tryCount);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
