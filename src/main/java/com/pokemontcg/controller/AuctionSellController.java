package com.pokemontcg.controller;

import com.pokemontcg.dto.AuctionDto;
import com.pokemontcg.dto.CardDto;
import com.pokemontcg.service.AuctionService;
import com.pokemontcg.service.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/auction-sell")
@AllArgsConstructor
public class AuctionSellController {
    private UserLoginService userLoginService;
    private AuctionService auctionService;

    @GetMapping
    public String getAuctionSell(Model model){
        List<CardDto> userCards = userLoginService.getUserCards();
        AuctionDto auctionDto = new AuctionDto();
        model.addAttribute("cards", userCards);
        model.addAttribute("auction", auctionDto);
        return "auction-sell.html";
    }

    @PostMapping("/{cardId}")
    public String sellCard(@ModelAttribute AuctionDto auction, Model model,@PathVariable String cardId){
        System.out.println(cardId);
        System.out.println(auction);
        auction.setCardId(cardId);
        auctionService.postAuction(auction);
        model.addAttribute("message", auction);
        return "auction-sell.html";
    }

}
