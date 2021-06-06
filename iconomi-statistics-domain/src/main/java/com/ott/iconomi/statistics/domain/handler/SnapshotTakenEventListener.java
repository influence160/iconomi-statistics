package com.ott.iconomi.statistics.domain.handler;

import com.ott.iconomi.statistics.domain.repository.SnapshotRepository;
import com.ott.iconomi.statistics.domain.repository.StrategyRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.domain.events.SnapshotTakenEvent;

@Component
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
		snapshotRepository.save(event.getSnapshot());
	}

}
