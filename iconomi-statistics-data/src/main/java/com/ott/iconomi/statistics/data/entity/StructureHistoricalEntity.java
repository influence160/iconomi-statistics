package com.ott.iconomi.statistics.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Entity
@Table(name = "structure_historical")
public class StructureHistoricalEntity extends StructureEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "current_structure_id")
	private CurrentStructureEntity currentStructure;

	public StructureHistoricalEntity() {
		super();
	}

	public StructureHistoricalEntity(CurrentStructureEntity currentStructureEntity) {
		this.dayReturn = currentStructureEntity.dayReturn;
		this.weekReturn = currentStructureEntity.weekReturn;
		this.monthReturn = currentStructureEntity.monthReturn;
		this.yearReturn = currentStructureEntity.yearReturn;
		this.allTimeReturn = currentStructureEntity.allTimeReturn;
		this.lastChange = currentStructureEntity.lastChange;
		this.numberOfChangesInLast30Days = currentStructureEntity.numberOfChangesInLast30Days;
		this.dateCreated = currentStructureEntity.dateCreated;
		this.dateStarted = currentStructureEntity.dateStarted;
		this.elements = new ArrayList<>(currentStructureEntity.elements);
		this.snapshot = currentStructureEntity.snapshot;
		this.currentStructure = currentStructureEntity;
		currentStructureEntity.getStructureHistoricals().add(this);
	}

	@PrePersist
	public void prePersist() {
		this.dateCreated = LocalDateTime.now();
	}
	
	@PreUpdate
	private void preUpdate() {
		throw new RuntimeException("You cant update a StructureHistorical");
	}
	
	public CurrentStructureEntity getCurrentStructure() {
		return currentStructure;
	}

	public void setCurrentStructure(CurrentStructureEntity currentStructure) {
		this.currentStructure = currentStructure;
	}
	
}
