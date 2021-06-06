package com.ott.iconomi.statistics.domain.events;

import com.ott.iconomi.statistics.domain.model.Snapshot;

public class SnapshotTakenEvent extends DomainEvent {

	private static final long serialVersionUID = 1L;
	
	private Snapshot snapshot;
	
	public SnapshotTakenEvent(Object source, Snapshot snapshot) {
		super(source);
		this.snapshot = snapshot;
	}

	public Snapshot getSnapshot() {
		return snapshot;
	}
	

}
