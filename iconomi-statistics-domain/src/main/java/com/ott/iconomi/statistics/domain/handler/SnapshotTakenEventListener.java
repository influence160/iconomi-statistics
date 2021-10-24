package com.ott.iconomi.statistics.domain.handler;

import com.ott.iconomi.statistics.domain.model.Snapshot;
import com.ott.iconomi.statistics.domain.repository.SnapshotRepository;
import com.ott.iconomi.statistics.domain.repository.StrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.domain.events.SnapshotTakenEvent;

@Component
@Slf4j
public class SnapshotTakenEventListener {
	
	private SnapshotRepository snapshotRepository;
	private StrategyRepository strategyRepository;
	
	public SnapshotTakenEventListener(SnapshotRepository snapshotRepository, StrategyRepository strategyRepository) {
		super();
		this.snapshotRepository = snapshotRepository;
		this.strategyRepository = strategyRepository;
	}

	@EventListener(SnapshotTakenEvent.class)
	public void handle(SnapshotTakenEvent event) {
		Snapshot snapshot = snapshotRepository.save(event.getSnapshot());
		log.info("Snapshot saved " + snapshot);
	}

}
