package com.ott.iconomi.statistics.data.entity;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "current_structure")
@Slf4j
public class CurrentStructureEntity extends StructureEntity {
	
	private LocalDateTime dateUpdated;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "strategy_id", nullable = false, updatable = false)
	private StrategyEntity strategy;

	@OneToMany(mappedBy = "currentStructure", fetch = FetchType.LAZY)
	private List<StructureHistoricalEntity> structureHistoricals;

	@PrePersist
	public void prePersist() {
		this.dateCreated = LocalDateTime.now();
		this.dateUpdated = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.dateUpdated = LocalDateTime.now();
	}

	public List<StructureHistoricalEntity> getStructureHistoricals() {
		return structureHistoricals;
	}

	public void setStructureHistoricals(List<StructureHistoricalEntity> structureHistoricals) {
		this.structureHistoricals = structureHistoricals;
	}

	public void addStructureHistorical(StructureHistoricalEntity structureHistoricalEntity) {
		structureHistoricals.add(structureHistoricalEntity);
		structureHistoricalEntity.setCurrentStructure(this);
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public StrategyEntity getStrategy() {
		return strategy;
	}

	public void setStrategy(StrategyEntity strategy) {
		this.strategy = strategy;
	}
	

}
