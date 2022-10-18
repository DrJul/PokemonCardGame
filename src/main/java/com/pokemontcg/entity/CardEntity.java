package com.pokemontcg.entity;

import com.pokemontcg.client.Images;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class CardEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String name;
    private String largeImage;
    private String smallImage;

    public CardEntity(String name){
        this.name = name;
    }


}
