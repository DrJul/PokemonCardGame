package com.pokemontcg.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
public class TrenerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int coins;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<CardEntity, Integer> cards = new HashMap<>();

    public void addCards( List<CardEntity> cardsToAdd){
        for (CardEntity newCard: cardsToAdd) {
            addCard(newCard);
        }
    }

    private void addCard(CardEntity card) {
        if(cards.containsKey(card)){
            cards.put(card, cards.get(card)+1);
        } else {
            cards.put(card, 1);
        }
    }

    public void addCard(CardEntity card, int amount) {
        if(cards.containsKey(card)){
            cards.put(card, cards.get(card)+ amount);
        } else {
            cards.put(card, amount);
        }
    }

    public void removeCard(CardEntity cardToRemove, int amount){
        if(cards.containsKey(cardToRemove)){
            cards.remove(cardToRemove, amount);
        }
    }

}
