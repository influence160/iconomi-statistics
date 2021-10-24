package com.ott.iconomi.statistics.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "snapshot")
public class SnapshotEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "snapshotsec")
	@SequenceGenerator(name = "snapshotsec", sequenceName = "snapshotsec", allocationSize = 1)
	private int id;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private boolean failed = false;
	
	private String failMessage;

	private String comment;

	@OneToMany(mappedBy = "snapshot", fetch = FetchType.LAZY)
	private List<CurrentStructureEntity> currentStructures;

	@OneToMany(mappedBy = "snapshot", fetch = FetchType.LAZY)
	private List<StructureHistoricalEntity> structureHistoricals;

	@OneToMany(mappedBy = "snapshot", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private List<PriceHistoryEntity> prices;
		
	@PrePersist
	private void preInsert() {
		this.endTime = LocalDateTime.now();
	}
	
	@PreUpdate
	private void preUpdate() {
		throw new RuntimeException("You cant update a Snapshot");
	}
	
	public SnapshotEntity() {
		this.startTime = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public List<CurrentStructureEntity> getCurrentStructures() {
		return currentStructures;
	}

	public void setCurrentStructures(List<CurrentStructureEntity> currentStructures) {
		this.currentStructures = currentStructures;
	}

	public List<StructureHistoricalEntity> getStructureHistoricals() {
		return structureHistoricals;
	}

	public void setStructureHistoricals(List<StructureHistoricalEntity> structureHistoricals) {
		this.structureHistoricals = structureHistoricals;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<PriceHistoryEntity> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceHistoryEntity> prices) {
		this.prices = prices;
	}

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("startTime", startTime)
				.toString();
	}
	
}
