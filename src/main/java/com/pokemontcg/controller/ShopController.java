package com.pokemontcg.controller;

import com.pokemontcg.dto.CardDto;
import com.pokemontcg.entity.CardEntity;
import com.pokemontcg.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {
    private ShopService shopService;

    @GetMapping
    public String getShop(){
        return "shop";
    }

    @PostMapping("/buy") //todo dynamiczne wersje pakietów - różna iloc kart i cena
    public String buyCards(Model model){
        List<CardDto> buyedCards = shopService.buyCars();
        model.addAttribute("cards", buyedCards);
        return "/shop";
    }

  /*  @PostMapping("/buy") //todo dynamiczne wersje pakietów - różna iloc kart i cena
    public ModelAndView buyCards(){
        List<CardDto> buyedCards = shopService.buyCars();
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.getModel().put("cards",buyedCards);
        return  modelAndView;
    }*/

}

//podstrona kupno i podstrona sprzedaz
//sprzedaz - wystawiamy karty ustawiąc jej cene, ilość sztuk które chcemy sprzedać po tej cenie