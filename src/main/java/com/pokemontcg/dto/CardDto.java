package com.pokemontcg.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardDto {
    private String id;
    private String name;
    private String largeImage;
    private String smallImage;
    private int amount;
}
