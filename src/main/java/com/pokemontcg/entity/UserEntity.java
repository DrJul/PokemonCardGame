package com.pokemontcg.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String email;
    private String password;
    @OneToOne
    private TrenerEntity trener;
    @ManyToMany
    private List<CardEntity> cards;

    public void removeCard(CardEntity cardToRemove){
        cards.remove(cardToRemove);
    }


}
