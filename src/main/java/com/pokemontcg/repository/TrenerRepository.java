package com.pokemontcg.repository;

import com.pokemontcg.entity.TrenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrenerRepository extends JpaRepository<TrenerEntity, Long> {
}
