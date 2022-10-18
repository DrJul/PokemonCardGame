package com.pokemontcg.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AuctionDto {
    private long id;
    private String cardId;
    private long userId;
    private int amount;
    private double priceForOne;
}
