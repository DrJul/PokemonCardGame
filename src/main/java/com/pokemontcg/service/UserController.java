package com.pokemontcg.service;

import com.pokemontcg.dto.CardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
@AllArgsConstructor
@Controller
public class UserController {
    private UserLoginService userLoginService;

    @GetMapping
    public String getUserAccount(Model model){
        List<CardDto> cards = userLoginService.getUserCards();
        System.out.println(cards);
        model.addAttribute("cards", cards);
        return "user";
    }
}
