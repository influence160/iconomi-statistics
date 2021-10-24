package com.ott.iconomi.statistics.domain.repository;

import com.ott.iconomi.statistics.data.entity.PriceHistoryEntity;
import com.ott.iconomi.statistics.data.repository.AssetEntityRepository;
import com.ott.iconomi.statistics.data.repository.PriceHistoryEntityRepository;
import com.ott.iconomi.statistics.domain.model.PriceHistory;
import com.ott.iconomi.statistics.domain.model.Strategy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.data.entity.SnapshotEntity;
import com.ott.iconomi.statistics.data.repository.SnapshotEntityRepository;
import com.ott.iconomi.statistics.domain.model.Snapshot;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component
public class SnapshotRepository implements DomainRepository<Snapshot> {
	
	private SnapshotEntityRepository entityRepository;
	private StrategyRepository strategyRepository;
	private ConversionService conversionService;
	private CurrentStructureRepository currentStructureRepository;
	private AssetEntityRepository assetEntityRepository;
	private PriceHistoryEntityRepository priceHistoryEntityRepository;

	public SnapshotRepository(SnapshotEntityRepository entityRepository, StrategyRepository strategyRepository, ConversionService conversionService, CurrentStructureRepository currentStructureRepository, PriceHistoryEntityRepository priceHistoryEntityRepository, AssetEntityRepository assetEntityRepository) {
		this.entityRepository = entityRepository;
		this.strategyRepository = strategyRepository;
		this.conversionService = conversionService;
		this.currentStructureRepository = currentStructureRepository;
		this.priceHistoryEntityRepository = priceHistoryEntityRepository;
		this.assetEntityRepository = assetEntityRepository;
	}

	@Transactional
	public Snapshot save(Snapshot snapshot) {

		final SnapshotEntity snapshotEntity = conversionService.convert(snapshot, SnapshotEntity.class);
		snapshotEntity.getPrices().forEach(p -> {
			p.setSnapshot(snapshotEntity);
			p.setAsset(assetEntityRepository.save(p.getAsset()));
		});
		snapshotEntity.setCurrentStructures(new ArrayList<>());
		SnapshotEntity snapshotEntitySaved = entityRepository.save(snapshotEntity);
		int snapshotId = snapshotEntitySaved.getId();
		snapshot.getCurrentStructures().forEach(currentStructure -> {
			Strategy strategy = snapshot.getStrategies().stream().filter(s -> s.getShortName().equals(currentStructure.getStrategyShortName()))
					.findAny().orElseThrow(RuntimeException::new);
			strategyRepository.save(strategy, currentStructure, snapshotId);
		});

		return conversionService.convert(snapshotEntitySaved, Snapshot.class);
	}

    public int count() {
		return (int) entityRepository.count();
    }
}
