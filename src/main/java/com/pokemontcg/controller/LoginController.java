package com.pokemontcg.controller;

import com.pokemontcg.dto.UserLoginDto;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.exception.LoginServiceException;
import com.pokemontcg.service.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private UserLoginService userLoginService;

    @GetMapping
    public String getLogin(Model model, HttpServletRequest request) {
//        System.out.println((Arrays.stream(request.getCookies()).map(c-> c.getName() + " " + c.getValue()).collect(Collectors.toList())));
        UserLoginDto userLoginDto = new UserLoginDto();
        model.addAttribute("request", userLoginDto);
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        if(userEntity != null){
            System.out.println(userEntity);
            System.out.println(userLoginService.isLogged());
        } else {
            System.out.println("niezalogowany");
        }
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("request") @Valid UserLoginDto user, BindingResult errors) {
    /*    Cookie cookie = new Cookie("log","true");
        cookie.setMaxAge(60);
        cookie.setPath("/");
        response.addCookie(cookie);*/

        if (errors.hasErrors()) {
            return "login";
        }
        try {
            userLoginService.checkUserData(user);
        } catch (LoginServiceException e) {
            errors.addError(new FieldError("request","password",e.getMessage()));
            return "login";
        }
        return "index";
    }
}

// wyulogowanie czas w przyszłosci lub boolean false
// strona z profilem, na ktora wpuszcza tylko jak jestes zalogowany i wypisuje dane uzytkownika