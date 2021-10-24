package com.ott.iconomi.statistics.domain.repository;

import com.ott.iconomi.statistics.data.entity.CurrentStructureEntity;
import com.ott.iconomi.statistics.data.entity.StructureHistoricalEntity;
import com.ott.iconomi.statistics.data.repository.*;
import com.ott.iconomi.statistics.domain.model.Asset;
import com.ott.iconomi.statistics.domain.model.CurrentStructure;
import com.ott.iconomi.statistics.domain.model.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.data.entity.StrategyEntity;
import org.springframework.util.Assert;

@Component
@Slf4j
public class StrategyRepository implements DomainRepository<Strategy> {

	private StrategyEntityRepository strategyEntityRepository;
	private ConversionService conversionService;
	private CurrentStructureEntityRepository currentStructureEntityRepository;
	private StructureHistoricalEntityRepository structureHistoricalEntityRepository;
	private SnapshotEntityRepository snapshotEntityRepository;
	private AssetEntityRepository assetEntityRepository;
	private PriceHistoryEntityRepository priceHistoryEntityRepository;

	public StrategyRepository(StrategyEntityRepository strategyEntityRepository, ConversionService conversionService, CurrentStructureEntityRepository currentStructureEntityRepository, StructureHistoricalEntityRepository structureHistoricalEntityRepository, SnapshotEntityRepository snapshotEntityRepository, AssetEntityRepository assetEntityRepository, PriceHistoryEntityRepository priceHistoryEntityRepository) {
		this.strategyEntityRepository = strategyEntityRepository;
		this.conversionService = conversionService;
		this.currentStructureEntityRepository = currentStructureEntityRepository;
		this.structureHistoricalEntityRepository = structureHistoricalEntityRepository;
		this.snapshotEntityRepository = snapshotEntityRepository;
		this.assetEntityRepository = assetEntityRepository;
		this.priceHistoryEntityRepository = priceHistoryEntityRepository;
	}

	public Strategy save(Strategy strategy) {
		return conversionService.convert(strategyEntityRepository.save(conversionService.convert(strategy, StrategyEntity.class)), Strategy.class);
	}

	public boolean exists(String shortName) {
		return strategyEntityRepository.existsById(shortName);
	}

	public Strategy getOneWithCurrentStructure(String shortName) {
		return conversionService.convert(strategyEntityRepository.getOneWithCurrentStructure(shortName), Strategy.class);
	}

	public void save(Strategy strategy, CurrentStructure currentStructure, int snapshotId) {
		Assert.state(currentStructure.getStrategyShortName().equals(strategy.getShortName()), "Assertion failed : currentStructure.getStrategyShortName().equals(strategy.getShortName())");

		CurrentStructureEntity currentStructureEntity = conversionService.convert(currentStructure, CurrentStructureEntity.class);
		currentStructureEntity.setSnapshot(snapshotEntityRepository.getOne(snapshotId));
		currentStructureEntity.getElements().forEach(structureElementEntity -> {
			structureElementEntity.setAsset(assetEntityRepository.save(structureElementEntity.getAsset()));
			if (!Asset.OTHER_ASSETS_CCY.equals(structureElementEntity.getAsset().getCcy())) {
				structureElementEntity.setPrice(priceHistoryEntityRepository.getByAssetAndSnapshot_Id(structureElementEntity.getAsset(), snapshotId));
			}
		});


		if (strategyEntityRepository.existsById(strategy.getShortName())) {
			log.debug("strategy " + strategy.getShortName() + " exists.");
			CurrentStructureEntity existingCurrentStructure = historizeCurrentStructure(strategy.getShortName());
			merge(existingCurrentStructure, currentStructureEntity);
			currentStructureEntityRepository.save(existingCurrentStructure);
		} else {
			currentStructureEntity.setStrategy(strategyEntityRepository.save(conversionService.convert(strategy, StrategyEntity.class)));
			currentStructureEntityRepository.save(currentStructureEntity);
		}
	}


	public CurrentStructureEntity historizeCurrentStructure(String shortName) {
		log.debug("historizeCurrentStructure " + shortName);
		CurrentStructureEntity currentStructureEntity = currentStructureEntityRepository.getByStrategy_ShortName(shortName);
		StructureHistoricalEntity structureHistoricalEntity = new StructureHistoricalEntity(currentStructureEntity);
		structureHistoricalEntityRepository.save(structureHistoricalEntity);
		return currentStructureEntity;
	}


	//Merge for update
	private void merge(CurrentStructureEntity existing,CurrentStructureEntity newVersion) {
		existing.setDayReturn(newVersion.getDayReturn());
		existing.setWeekReturn(newVersion.getWeekReturn());
		existing.setMonthReturn(newVersion.getMonthReturn());
		existing.setYearReturn(newVersion.getYearReturn());
		existing.setAllTimeReturn(newVersion.getAllTimeReturn());
		existing.setLastChange(newVersion.getLastChange());
		existing.setNumberOfChangesInLast30Days(newVersion.getNumberOfChangesInLast30Days());
		existing.setElements(newVersion.getElements());
		existing.setSnapshot(newVersion.getSnapshot());
	}
}
