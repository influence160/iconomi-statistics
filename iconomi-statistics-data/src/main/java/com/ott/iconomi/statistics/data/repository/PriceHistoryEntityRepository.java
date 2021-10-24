package com.ott.iconomi.statistics.data.repository;

import com.ott.iconomi.statistics.data.entity.AssetEntity;
import com.ott.iconomi.statistics.data.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryEntityRepository extends JpaRepository<PriceHistoryEntity, Integer> {

    PriceHistoryEntity getByAssetAndSnapshot_Id(AssetEntity asset, int snapshotId);

}
