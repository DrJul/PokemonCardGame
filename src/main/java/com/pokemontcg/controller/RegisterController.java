package com.pokemontcg.controller;

import com.pokemontcg.dto.RegisterRequestDto;
import com.pokemontcg.exception.RegisterServiceException;
import com.pokemontcg.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {
    private RegisterService registerService;

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String registerUser(String email, String password, String repeatedPassword, Model model){
        System.out.println(email);
        System.out.println(password);
        System.out.println(repeatedPassword);
        RegisterRequestDto userRequestDto = new RegisterRequestDto(email, password, repeatedPassword);
        try{
            registerService.registerUser(userRequestDto);
        } catch (RegisterServiceException e){
            model.addAttribute("errorMessage", e.getMessage());
          return "register";
        }
        return "index";
    }
}