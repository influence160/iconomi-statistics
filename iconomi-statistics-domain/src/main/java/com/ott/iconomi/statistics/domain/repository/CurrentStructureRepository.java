package com.ott.iconomi.statistics.domain.repository;

import com.ott.iconomi.statistics.data.entity.CurrentStructureEntity;
import com.ott.iconomi.statistics.data.repository.*;
import com.ott.iconomi.statistics.domain.model.CurrentStructure;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentStructureRepository implements DomainRepository<CurrentStructure> {

    private CurrentStructureEntityRepository currentStructureEntityRepository;
    private ConversionService conversionService;
    private SnapshotEntityRepository snapshotEntityRepository;
    private AssetEntityRepository assetEntityRepository;
    private StrategyEntityRepository strategyEntityRepository;
    private StructureHistoricalEntityRepository structureHistoricalEntityRepository;


    public CurrentStructureRepository(CurrentStructureEntityRepository currentStructureEntityRepository, ConversionService conversionService, SnapshotEntityRepository snapshotEntityRepository, AssetEntityRepository assetEntityRepository, StrategyEntityRepository strategyEntityRepository, StructureHistoricalEntityRepository structureHistoricalEntityRepository) {
        this.currentStructureEntityRepository = currentStructureEntityRepository;
        this.conversionService = conversionService;
        this.snapshotEntityRepository = snapshotEntityRepository;
        this.assetEntityRepository = assetEntityRepository;
        this.strategyEntityRepository = strategyEntityRepository;
        this.structureHistoricalEntityRepository = structureHistoricalEntityRepository;
    }

    /**
     * @deprecated
     * @param currentStructures
     * @param snapshotId
     */
    public void saveAll(List<CurrentStructure> currentStructures, int snapshotId) {
        currentStructures.forEach(currentStructure -> {
            CurrentStructureEntity currentStructureEntity = conversionService.convert(currentStructure, CurrentStructureEntity.class);
            currentStructureEntity.setSnapshot(snapshotEntityRepository.getOne(snapshotId));
            currentStructureEntity.setStrategy(strategyEntityRepository.getOne(currentStructure.getStrategyShortName()));
            currentStructureEntity.getElements().forEach(structureElementEntity -> {
                structureElementEntity.setAsset(assetEntityRepository.save(structureElementEntity.getAsset()));
            });
            currentStructureEntityRepository.save(currentStructureEntity);
        });
    }
}
