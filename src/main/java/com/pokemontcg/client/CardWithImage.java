package com.pokemontcg.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CardWithImage {
    private String id;
    private String name;
    private Images images;
}
