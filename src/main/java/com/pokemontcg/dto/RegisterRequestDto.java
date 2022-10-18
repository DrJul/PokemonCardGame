package com.pokemontcg.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequestDto {
    @NotNull
    private String email;
    @Size(min = 6)
//    @Pattern(regexp = ) // todo regexp
    private String password;
    private String repeatedPassword;

}
