package com.pokemontcg.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CardPageEntity {
    @Id
    private int pageNr;
    private boolean downloaded;


}
