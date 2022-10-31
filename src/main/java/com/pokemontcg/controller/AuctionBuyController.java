package com.pokemontcg.controller;

import com.pokemontcg.dto.CardDto;
import com.pokemontcg.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/auction-buy")
@AllArgsConstructor
public class AuctionBuyController {
    private AuctionService auctionService;

    @GetMapping
    public String getAuctionBuy(){

        return "auction-buy.html";
    }

}
