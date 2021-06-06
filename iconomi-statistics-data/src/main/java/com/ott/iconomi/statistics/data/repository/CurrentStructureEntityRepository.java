package com.ott.iconomi.statistics.data.repository;

import com.ott.iconomi.statistics.data.entity.CurrentStructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentStructureEntityRepository extends JpaRepository<CurrentStructureEntity, Long> {

    public CurrentStructureEntity getByStrategy_ShortName(String strategyShortName);

}
