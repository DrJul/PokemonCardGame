package com.pokemontcg.client;

import com.pokemontcg.entity.CardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@NoArgsConstructor // wymagania nakłada biblioteka jackson - służy w Springu do mapowania Jsonów na obiekt
@ToString
public class ApiResponse {
    private List<CardWithImage> data;

}
