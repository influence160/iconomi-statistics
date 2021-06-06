package com.ott.iconomi.statistics.data.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ott.iconomi.statistics.data.entity.StrategyEntity;
import org.springframework.data.jpa.repository.Query;

public interface StrategyEntityRepository extends JpaRepository<StrategyEntity, String> {

    @Query("select s from StrategyEntity s where s.shortName = ?1")
    @EntityGraph(attributePaths = {"currentStructure"})
    public StrategyEntity getOneWithCurrentStructure(String shortName);
}
