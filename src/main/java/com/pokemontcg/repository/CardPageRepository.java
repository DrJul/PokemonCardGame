package com.pokemontcg.repository;

import com.pokemontcg.entity.CardPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPageRepository extends JpaRepository<CardPageEntity, Integer> {
}
