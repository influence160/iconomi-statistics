package com.ott.iconomi.statistics.domain.repository;

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

	public SnapshotRepository(SnapshotEntityRepository entityRepository, StrategyRepository strategyRepository, ConversionService conversionService, CurrentStructureRepository currentStructureRepository) {
		this.entityRepository = entityRepository;
		this.strategyRepository = strategyRepository;
		this.conversionService = conversionService;
		this.currentStructureRepository = currentStructureRepository;
	}

	@Transactional
	public Snapshot save(Snapshot snapshot) {

		SnapshotEntity snapshotEntity = conversionService.convert(snapshot, SnapshotEntity.class);
		snapshotEntity.setCurrentStructures(new ArrayList<>());
		snapshotEntity = entityRepository.save(snapshotEntity);
		int snapshotId = snapshotEntity.getId();
		snapshot.getCurrentStructures().forEach(currentStructure -> {
			Strategy strategy = snapshot.getStrategies().stream().filter(s -> s.getShortName().equals(currentStructure.getStrategyShortName()))
					.findAny().orElseThrow(RuntimeException::new);
			strategyRepository.save(strategy, currentStructure, snapshotId);
		});

		return conversionService.convert(snapshotEntity, Snapshot.class);
	}

}
