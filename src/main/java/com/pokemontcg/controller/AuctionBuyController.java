package com.pokemontcg.controller;

import com.pokemontcg.dto.AuctionDto;
import com.pokemontcg.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("/auction-buy")
@AllArgsConstructor
public class AuctionBuyController {
    private AuctionService auctionService;

    @GetMapping
    public String getAuctionBuy(Model model){
//        List<AuctionDto> auctions = auctionService.getAuctions();
//        System.out.println(auctions);
        List<AuctionDto> auctions = auctionService.getAuctions();
        System.out.println(auctions);
        model.addAttribute("auctions", auctions);
        return "auction-buy.html";
    }

    @PostMapping("/{auctionId}")
    public String buyCards(@PathVariable long auctionId){
        System.out.println(auctionId);
        auctionService.buy(auctionId);
        return "redirect:/auction-buy";
    }

}
