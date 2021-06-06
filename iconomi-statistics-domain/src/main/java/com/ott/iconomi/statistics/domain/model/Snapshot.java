package com.ott.iconomi.statistics.domain.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.core.style.ToStringCreator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@Getter
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE, makeFinal = true)
public class Snapshot {

	private int id;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private boolean failed;
	
	private String failMessage;

	@Column(length = 4000)
	private String comment;

	private List<CurrentStructure> currentStructures;

	private List<StructureHistorical> structureHistoricals;
	
	private Set<Strategy> strategies;

	public Snapshot(int id, LocalDateTime startTime, LocalDateTime endTime, boolean failed, String failMessage, String comment, List<CurrentStructure> currentStructures, List<StructureHistorical> structureHistoricals, Set<Strategy> strategies) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.failed = failed;
		this.failMessage = failMessage;
		this.comment = comment;
		this.currentStructures = Collections.unmodifiableList(currentStructures);//TODO voir si ca se fait dans lombok
		//et si c mieux dans le builder
		this.structureHistoricals = structureHistoricals;
		this.strategies = strategies;
		if (currentStructures != null) {
			currentStructures.forEach(c -> c.snapshot = this);
		}
		if (structureHistoricals != null) {
			currentStructures.forEach(c -> c.snapshot = this);
		}
	}

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("startTime", startTime)
				.toString();
	}
	
}
