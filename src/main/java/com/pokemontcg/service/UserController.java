package com.pokemontcg.service;

import com.pokemontcg.dto.CardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
@AllArgsConstructor
@Controller
public class UserController {
    private TrenerService trenerService;

    @GetMapping
    public String getUserAccount(Model model){
        List<CardDto> cards = trenerService.getTrenerCardDtos();
        int coins = trenerService.getTrenerCoins();
        System.out.println(cards);
        model.addAttribute("cards", cards);
        model.addAttribute("coins", coins);
        return "user";
    }

    @PostMapping
    public String buyCoins(int buyedCoins){
        trenerService.addCoinsToUser(buyedCoins);
        return "redirect:/user";
    }
}
