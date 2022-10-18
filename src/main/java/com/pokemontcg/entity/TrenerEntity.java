package com.pokemontcg.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class TrenerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int coin;
    @ManyToMany()
    private List<CardEntity> cards = new ArrayList<>();


}
